//import kotlin.io.path.ExperimentalPathApi
//import kotlin.io.path.copyToRecursively

plugins {
	id("build-logic.published-kotlin-jvm")
	id("code-generation.generate")
	alias(libs.plugins.nexus.publish)
	id("me.champeau.jmh") version "0.7.3"
}

version = "2.0.0-RC-1"
group = "com.tegonal.minimalist"
description = "Library which helps to setup and prioritise parameterized tests"


dependencies {
	api(libs.junit.jupiter.params)
	implementation(libs.kbox)
	implementation(kotlin("reflect"))

	testImplementation(kotlin("test"))
	testImplementation(libs.atrium.fluent)
}

val generationFolder: ConfigurableFileCollection = project.files("src/main/generated/kotlin")
val generationFolderJava: ConfigurableFileCollection = project.files("src/main/generated/java")
val generationTestFolder: ConfigurableFileCollection = project.files("src/test/generated/kotlin")
val generationTestFolderJava: ConfigurableFileCollection = project.files("src/test/generated/java")

kotlin {
	sourceSets {
		main {
			kotlin.srcDir(generationFolder)
		}
		test {
			kotlin.srcDir(generationTestFolder)
		}
	}
}
java {
	sourceSets {
		main {
			java.srcDir(generationFolderJava)
			java.srcDir(project.files("src/main/lib/java"))
		}
		test {
			java {
				srcDir(generationTestFolderJava)
			}
		}
	}
}

// current workaround for java so that we can execute `main` methods
// intellij uses the module path and apparently it is not possible to patch modules if it contains only
// the module-info.class -- moreover, intellij adds only the java sources to the module path, so as for now it is
// easier to just copy the kotlin .class files to the output folder of java
//project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
//	doLast {
//		this.outputs.files.filter { it.name == "main" }.forEach {
//			@OptIn(ExperimentalPathApi::class)
//			it.toPath().copyToRecursively(
//				project.layout.buildDirectory.file("classes/java/main/").get().asFile.toPath(),
//				followLinks = false,
//				overwrite = true
//			)
//		}
//	}
//}

nexusPublishing {
	repositories {
		sonatype()
	}
}

jmh {
	profilers = listOf("gc")
	// run ./gradlew jmh -Pjmh.include=... to run a specific bench
	// transform the txt to csv via ./scripts/jmh-txt-to-csv.sh
	includes.set(project.findProperty("jmh.include")?.let { listOf(it.toString()) } ?: emptyList())
}

/*
Release & deploy a commit
--------------------------------

1. update main:


export MNLMST_PREVIOUS_VERSION=1.1.0
export MNLMST_VERSION=1.2.0
find ./ -name "*.md" | xargs perl -0777 -i \
   -pe "s@$MNLMST_PREVIOUS_VERSION@$MNLMST_VERSION@g;" \
   -pe "s@tree/main@tree/v$MNLMST_VERSION@g;" \
   -pe "s@latest#/kdoc@$MNLMST_VERSION/kdoc@g;"
perl -0777 -i \
  -pe "s@$MNLMST_PREVIOUS_VERSION@$MNLMST_VERSION@g;" \
  -pe "s/version = \"${MNLMST_VERSION}-SNAPSHOT\"/version = \"$MNLMST_VERSION\"/;" \
  ./build.gradle.kts
perl -0777 -i \
  -pe 's/(<!-- for main -->\n)\n([\S\s]*?)(\n<!-- for release -->\n)<!--\n([\S\s]*?)-->\n(\n# <img)/$1<!--\n$2-->$3\n$4\n$5/;' \
  -pe 's/(---\n❗ You are taking[^-]*?---)/<!$1>/;' \
  ./README.md
git commit -a -m "v$MNLMST_VERSION"

check changes (CONTRIBUTING.md, build.gradle.kts, README.md)
git push


2. prepare release on github
    a) git tag "v$MNLMST_VERSION"
    b) git push origin "v$MNLMST_VERSION"
    c) Log in to github and create draft for the release

The tag is required for dokka in order that the externalLinkDocumentation and source-mapping works

3. update github pages:
Assumes you have a minimalist-gh-pages folder on the same level as minimalist where the gh-pages branch is checked out

Either use the following commands or the manual steps below (assuming MNLMST_PREVIOUS_VERSION and MNLMST_VERSION
is already set from commands above)

Increment MNLMST_GH_PAGES_VERSIONS_JS_VERSION and MNLMST_GH_PAGES_VERSIONS_JS_VERSION__NEXT

export MNLMST_GH_PAGES_LOGO_CSS_VERSION="1.3"
export MNLMST_GH_PAGES_ALERT_CSS_VERSION="1.1"
export MNLMST_GH_PAGES_VERSIONS_JS_VERSION="1.3.0"
export MNLMST_GH_PAGES_VERSIONS_JS_VERSION_NEXT="1.4.0"

gr dokkaHtml

cd ../minimalist-gh-pages
git add . && git commit -m "dokka generation for v$MNLMST_VERSION"

perl -0777 -i \
  -pe "s@$MNLMST_PREVIOUS_VERSION@$MNLMST_VERSION@g;" \
  ./index.html
perl -0777 -i \
  -pe "s@$MNLMST_PREVIOUS_VERSION@$MNLMST_VERSION@g;" \
  ./latest/index.html
perl -0777 -i \
  -pe "s/(\s+)\"$MNLMST_PREVIOUS_VERSION\",/\$1\"$MNLMST_VERSION\",\$1\"$MNLMST_PREVIOUS_VERSION\",/;" \
  ./scripts/versions.js


find "./$MNLMST_VERSION" -name "*.html" | xargs perl -0777 -i \
    -pe "s@<script.*src=\"https://unpkg\.com.*</script>@@;" \
    -pe "s@(<div class=\"library-name\">[\S\s]+?)Minimalist@\$1<span>Minimalist</span>@;" \
    -pe "s@\"((?:\.\./+)*)styles/logo-styles.css\" rel=\"Stylesheet\">@\"../../\${1}styles/logo-styles.css?v=$MNLMST_GH_PAGES_LOGO_CSS_VERSION\" rel=\"Stylesheet\">\n<link href=\"../../\${1}styles/alert.css?v=$MNLMST_GH_PAGES_ALERT_CSS_VERSION\" rel=\"Stylesheet\">\n<script id=\"versions-script\" type=\"text/javascript\" src=\"\../../\${1}scripts/versions.js?v=$MNLMST_GH_PAGES_VERSIONS_JS_VERSION\" data-version=\"$MNLMST_VERSION\" async=\"async\"></script>@g;" \
    -pe "s@((?:\.\./+)*)images/logo-icon.svg\"([^>]+)>@../../\${1}images/logo-icon.svg\"\$2>\n<meta name=\"og:image\" content=\"../../\${1}images/logo_social.png\"/>@g;" \
    -pe "s@(<a class=\"library-name--link\" href=\"(?:\.\./+)*)index.html\">@\$1../../index.html\" title=\"Back to Overview Code Documentation of Minimalist\">@g;" \
    -pe "s@<html@<html lang=\"en\"@g;" \
    -pe "s@<head>@<head>\n<meta name=\"keywords\" content=\"Kotlin, junit, junit-jupiter, test, Testing, parameterized tests, minimal test set\">\n<meta name=\"author\" content=\"Tegonal Genossenschaft\">\n<meta name=\"copyright\" content=\"Tegonal Genossenschaft\">@g;" \
    -pe "s@<title>([^<]+)</title>@<title>\$1 - Minimalist $MNLMST_VERSION</title>\n<meta name=\"description\" content=\"Code documentation of Minimalist $MNLMST_VERSION: \$1\">@g;" \
    -pe "s@(<code class=\"runnablesample[^>]+>)[\S\s]+?//sampleStart[\n\s]*([\S\s]+?)\s+//sampleEnd[\n\s]*\}@\${1}\${2}@g;"

find "./" -name "*.html" | xargs perl -0777 -i \
    -pe "s@(scripts/versions\.js\?v\=)$MNLMST_GH_PAGES_VERSIONS_JS_VERSION@\${1}$MNLMST_GH_PAGES_VERSIONS_JS_VERSION_NEXT@g;"

cp "./$MNLMST_PREVIOUS_VERSION/index.html" "./$MNLMST_VERSION/index.html"
perl -0777 -i \
  -pe "s/$MNLMST_PREVIOUS_VERSION/$MNLMST_VERSION/g;" \
  -pe "s@Released .*</p>@Released $(LC_ALL=en_GB date '+%b %d, %Y')</p>@;" \
  "./$MNLMST_VERSION/index.html"
git add . && git commit -m "v$MNLMST_VERSION"

check changes
git push

cd ../minimalist

3. deploy to maven central:
(assumes you have an alias named gr pointing to ./gradlew)
    a) echo "enter the sonatype user token"
	   read SONATYPE_PW
    b) java -version 2>&1 | grep "version \"11" && ORG_GRADLE_PROJECT_sonatypePassword="$SONATYPE_PW" CI=true gr clean publishToSonatype
    c) Log into https://oss.sonatype.org/#stagingRepositories
    d) check if staging repo is ok
    e) close repo
    f) release repo

4. publish release on github
    1) Log in to github and publish draft

Prepare next dev cycle
-----------------------
    1. update main:


export MNLMST_VERSION=1.1.0
export MNLMST_NEXT_VERSION=1.2.0
find ./ -name "*.md" | xargs perl -0777 -i \
   -pe "s@tree/v$MNLMST_VERSION@tree/main@g;" \
   -pe "s@$MNLMST_VERSION/kdoc@latest#/kdoc@g;" \
   -pe "s/add \\\`\@since $MNLMST_VERSION\\\` \(adapt to current/add \\\`\@since $MNLMST_NEXT_VERSION\\\` \(adapt to current/g;"
perl -0777 -i \
  -pe "s/rootProject.version = \"$MNLMST_VERSION\"/rootProject.version = \"${MNLMST_NEXT_VERSION}-SNAPSHOT\"/;" \
  -pe "s/MNLMST_VERSION=$MNLMST_VERSION/MNLMST_VERSION=$MNLMST_NEXT_VERSION/;" \
  ./build.gradle.kts
perl -0777 -i \
  -pe 's/(<!-- for main -->\n)<!--\n([\S\s]*?)-->(\n<!-- for release -->)\n([\S\s]*?)\n(\n# <img)/$1\n$2$3\n<!--$4-->\n$5/;' \
  -pe 's/<!(---\n❗ You are taking[^-]*?---)>/$1/;' \
  -pe "s@(latest version: \[README of v$MNLMST_VERSION\].*tree/)main/@\$1v$MNLMST_VERSION/@;" \
  ./README.md
git commit -a -m "prepare dev cycle of $MNLMST_NEXT_VERSION"

check changes
git push

*/
