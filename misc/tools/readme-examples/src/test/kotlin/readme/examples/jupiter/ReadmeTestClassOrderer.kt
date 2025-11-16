package readme.examples.jupiter

import org.junit.jupiter.api.ClassDescriptor
import org.junit.jupiter.api.ClassOrderer
import org.junit.jupiter.api.ClassOrdererContext

/**
 * tailored for Variist - initially copied from [Atrium](https://atriumlib.org).
 * Will have diffs once we pull a new version from Atrium, consider how to push them back once it occurs
 */
class ReadmeTestClassOrderer : ClassOrderer.OrderAnnotation() {
	override fun orderClasses(context: ClassOrdererContext) {
		super.orderClasses(context)
		val index = context.classDescriptors.indexOfFirst { it.testClass == WriteReadmeTest::class.java }
		if (index >= 0 && index != context.classDescriptors.size - 1) {
			val descriptor = context.classDescriptors.removeAt(index)
			@Suppress("UNCHECKED_CAST")
			(context.classDescriptors as MutableList<ClassDescriptor>).add(descriptor)
		}
	}
}
