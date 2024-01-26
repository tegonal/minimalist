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

rootProject.name = "minimalist"
