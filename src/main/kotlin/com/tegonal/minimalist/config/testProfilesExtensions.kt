package com.tegonal.minimalist.config

/**
 * @since 2.0.0
 */
operator fun TestProfiles.contains(testType: TestType) = contains(testType.name)

/**
 * @since 2.0.0
 */
operator fun TestProfiles.get(testType: TestType, env: Env) = get(testType.name, env.name)

/**
 * Predefined test type names (e.g. to use as profile names for [TestProfiles]).
 *
 * @since 2.0.0
 */
enum class TestType {
	Unit,
	Integration,
	E2E,
	SystemIntegration,
	;

	/**
	 * Helper constants so that you can use them in [com.tegonal.minimalist.providers.ArgsSourceOptions].
	 */
	@Suppress("ConstPropertyName")
	object ForAnnotation {
		const val Unit = "Unit"
		const val Integration = "Integration"
		const val E2E = "E2E"
		const val SystemIntegration = "SystemIntegration"
	}
}

/**
 * Predefined environment names.
 *
 * The following descriptions are just suggestions, you can interpret them as you wish.
 *
 * @since 2.0.0
 */
enum class Env {
	/**
	 * Running tests on a local machine
	 */
	Local,

	/**
	 * Running tests in a PR pipeline which shall be merged (eventually back to the main branch)
	 */
	PR,

	/**
	 * Running tests once a PR is merged to main.
	 */
	Main,

	/**
	 * Running tests as part of a deployment to the Test staging environment.
	 */
	DeployTest,

	/**
	 * Running tests as part of a deployment to the Int staging environment.
	 */
	DeployInt,

	/**
	 * Running tests as a nightly job on the Test staging environment.
	 */
	NightlyTest,

	/**
	 * Running tests as a nightly job on the Int staging environment.
	 */
	NightlyInt,

	/**
	 * Running tests in a PR pipeline which shall be merged to a hotfix branch
	 */
	HotfixPR,

	/**
	 * Running tests once a PR is merged back to a hotfix branch.
	 */
	Hotfix,
	;
}

