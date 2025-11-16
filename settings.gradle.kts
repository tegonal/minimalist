pluginManagement {
	repositories {
//        mavenLocal()
		gradlePluginPortal()
	}
	includeBuild("gradle/build-logic")
	includeBuild("gradle/build-logic-conventions")
	includeBuild("gradle/code-generation")
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
	}
}

rootProject.name = "variist"

include("misc/tools", "readme-examples")

fun Settings_gradle.include(subPath: String, projectName: String) {
	val dir = file("${rootProject.projectDir}/$subPath/$projectName")
	if (!dir.exists()) {
		throw GradleException("cannot include project $projectName as its projectDir $dir does not exist")
	}
	include(projectName)
	project(":$projectName").projectDir = dir
}
