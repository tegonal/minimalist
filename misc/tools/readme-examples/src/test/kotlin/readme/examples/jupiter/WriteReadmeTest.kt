package readme.examples.jupiter

import ch.tutteli.kbox.failIf
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.io.path.absolutePathString
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText
import kotlin.test.fail

/**
 * tailored for Minimalist - initially copied from [Atrium](https://atriumlib.org).
 * Will have diffs once we pull a new version from Atrium, consider how to push them back once it occurs
 */
class WriteReadmeTest {

	@Test
	fun updateReadme() {
		check(ReadmeState.code.isNotEmpty()) {
			"no code collected, did you run WriteReadmeTest without other tests?"
		}
//        check(ReadmeState.examples.isNotEmpty()) {
//            "no examples collected, did you run WriteReadmeTest without other tests?"
//        }

		val testContents = ReadmeState.testClasses
			.asSequence()
			.map { it.replace('.', '/') }
			.map { qualifiedClass ->
				qualifiedClass to fileContent("src/test/kotlin/$qualifiedClass.kt")
			}
			.toList()
		testContents.forEach { (qualifiedClass, specContent) ->
			extractSnippets(qualifiedClass, specContent)
		}

		check(ReadmeState.snippets.isNotEmpty()) {
			"no snippets found, something is wrong"
		}
		var readmeContent = fileContent(README_PATH)

		ReadmeState.examples.forEach { (exampleId, output) ->
			readmeContent = updateExampleLikeInReadme(readmeContent, testContents, exampleId, output)
		}
		ReadmeState.code.forEach { codeId ->
			readmeContent = updateCodeInReadme(readmeContent, testContents, codeId)
		}
		ReadmeState.snippets.forEach { (snippetId, snippetContent) ->
			readmeContent = updateSnippetInReadme(readmeContent, snippetId, snippetContent)
		}
		Paths.get(README_PATH).writeText(readmeContent)
	}


	private fun fileContent(path: String): String {
		val file = Paths.get(path)
		check(file.exists()) { "could not find ${file.absolutePathString()}" }
		return file.readText()
	}


	private fun extractSnippets(qualifiedClass: String, specContent: String) {
		Regex("//(snippet-.+)-start([\\S\\s]*?)//(snippet-.+)-end").findAll(specContent).forEach {
			val (tag, content, endTag) = it.destructured
			failIf(tag != endTag) { "tag $tag-start did not end with $tag-end but with $endTag" }
			failIf(ReadmeState.snippets.containsKey(tag)) { "snippet $tag already defined, found a second time in $qualifiedClass" }
			ReadmeState.snippets[tag] = content.trimIndent()
		}
	}


	private fun updateExampleLikeInReadme(
		readmeContent: String,
		testContents: List<Pair<String, String>>,
		exampleId: String,
		exampleOutput: String,
	): String = when {
		exampleId.startsWith("ex-") ->
			updateExampleInReadme(readmeContent, testContents, exampleId, exampleOutput)

		exampleId.startsWith("exs-") ->
			updateSplitExampleInReadme(readmeContent, testContents, exampleId, exampleOutput)

		else -> {
			fail("unknown example kind $exampleId")
		}
	}

	private fun updateExampleInReadme(
		readmeContent: String,
		testContents: List<Pair<String, String>>,
		exampleId: String,
		exampleOutput: String,
	): String = updateTagInReadme(
		readmeContent,
		testContents,
		exampleId,
		"example"
	) { qualifiedClass, lineNumber, sourceCode ->
		"""```kotlin
        |$sourceCode
        |```
        |↑ <sub>[Example](https://github.com/robstoll/atrium/${System.getenv("README_SOURCETREE")}/misc/tools/readme-examples/src/test/kotlin/$qualifiedClass.kt#L$lineNumber)</sub> ↓ <sub>[Output](#$exampleId)</sub>
        |<a name="$exampleId"></a>
        |```text
        |$exampleOutput
        |```
        """.trimMargin()
	}


	private fun updateSplitExampleInReadme(
		readmeContent: String,
		testContents: List<Pair<String, String>>,
		exampleId: String,
		exampleOutput: String,
	): String {
		val updatedReadme = updateTagInReadme(
			readmeContent, testContents, exampleId, "ex-code"
		) { _, _, sourceCode ->
			"""```kotlin
            |$sourceCode
            |```""".trimMargin()
		}

		return updateTagInReadme(updatedReadme, "$exampleId-output", "exs-output") {
			"""```text
            |$exampleOutput
            |```""".trimMargin()
		}
	}

	private fun updateCodeInReadme(
		readmeContent: String,
		testContents: List<Pair<String, String>>,
		snippetId: String,
	): String = updateTagInReadme(readmeContent, testContents, snippetId, "code") { _, _, sourceCode ->

		"""```kotlin
        |$sourceCode
        |```
        """.trimMargin()
	}


	private fun updateTagInReadme(
		readmeContent: String,
		testContents: List<Pair<String, String>>,
		tag: String,
		kind: String,
		content: (String, Int, String) -> String
	): String = updateTagInReadme(readmeContent, tag, kind) {
		val (qualifiedClass, lineNumber, sourceCode) = extractSourceCode(testContents, tag)
		content(qualifiedClass, lineNumber, sourceCode)
	}

	private fun extractSourceCode(
		testContents: List<Pair<String, String>>,
		testId: String,
	): Triple<String, Int, String> {
		var lineNumber: Int? = null
		val sb = StringBuilder()

		testContents.forEach { (qualifiedClass, testContent) ->
			testContent.lineSequence().forEachIndexed { index, line ->
				if (lineNumber != null) {
					if (line.startsWith("\t}")) return Triple(
						qualifiedClass,
						lineNumber!!,
						sb.toString().trimIndent()
					)
					sb.append(line).append("\n")

				} else if (line.trim().startsWith("fun `$testId`")) {
					lineNumber = index + 1
				}
			}
		}
		fail("cannot find source code for $testId")
	}

	private fun updateTagInReadme(
		readmeContent: String,
		tag: String,
		kind: String,
		contentProvider: () -> String
	): String {
		val tagRegex = Regex("( *)<$tag>[\\S\\s]*</$tag>")
		return if (!tagRegex.containsMatchIn(readmeContent)) {
			fail("$kind tag <$tag> not found in $README_PATH")
		} else {
			tagRegex.replace(readmeContent) {
				"""<$tag>
                |
                |${contentProvider()}
				|
                |</$tag>
                """.trimMargin().prependIndent(it.destructured.component1())
			}
		}
	}

	private fun updateSnippetInReadme(
		readmeContent: String,
		snippetId: String,
		snippetContent: String,
	): String {

		val snippet = Regex("//$snippetId-insert")
		return if (!snippet.containsMatchIn(readmeContent)) {
			fail("$snippetId is never inserted in $README_PATH")
		} else {
			snippet.replace(readmeContent) { snippetContent }
		}
	}

	companion object {
		private const val README_PATH = "../../../README.md"
	}
}
