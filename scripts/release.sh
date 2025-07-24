#!/usr/bin/env bash
#
#    __                          __
#   / /____ ___ ____  ___  ___ _/ /       This script is provided to you by https://github.com/tegonal/minimalist
#  / __/ -_) _ `/ _ \/ _ \/ _ `/ /        Copyright 2023 Tegonal Genossenschaft <info@tegonal.com>
#  \__/\__/\_, /\___/_//_/\_,_/_/         It is licensed under European Union Public License 1.2
#         /___/														Please report bugs and contribute back your improvements
#
#                                         Version: v0.1.0-SNAPSHOT
###################################
set -euo pipefail
shopt -s inherit_errexit
unset CDPATH
MINIMALIST_VERSION="v0.1.0-SNAPSHOT"

if ! [[ -v scriptsDir ]]; then
	scriptsDir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]:-$0}")" >/dev/null && pwd 2>/dev/null)"
	readonly scriptsDir
fi

if ! [[ -v projectDir ]]; then
	projectDir="$(realpath "$scriptsDir/../")"
	readonly projectDir
fi

if ! [[ -v dir_of_github_commons ]]; then
	dir_of_github_commons="$projectDir/.gt/remotes/tegonal-gh-commons/lib/src"
	readonly dir_of_github_commons
fi

if ! [[ -v dir_of_tegonal_scripts ]]; then
	dir_of_tegonal_scripts="$scriptsDir/../lib/tegonal-scripts/src"
	source "$dir_of_tegonal_scripts/setup.sh" "$dir_of_tegonal_scripts"
fi
sourceOnce "$dir_of_github_commons/gt/pull-hook-functions.sh"
sourceOnce "$dir_of_tegonal_scripts/releasing/release-template.sh"
sourceOnce "$scriptsDir/before-pr.sh"
sourceOnce "$scriptsDir/prepare-next-dev-cycle.sh"

function release() {
	if ! java --version | grep 11; then
		logError "Please switch to Java 11, current version in use:"
		java >&2 --version
		exit 1
	fi

	local prepareOnlyParamPatternLong
	source "$dir_of_tegonal_scripts/releasing/common-constants.source.sh" || die "could not source common-constants.source.sh"

	local version prepareOnly
	# shellcheck disable=SC2034   # they seem unused but are necessary in order that parseArguments doesn't create global readonly vars
	local branch nextVersion
	# shellcheck disable=SC2034   # is passed by name to parseArguments
	local -ra params=(
		version "$versionParamPattern" "$versionParamDocu"
		branch "$branchParamPattern" "$branchParamDocu"
		nextVersion "$nextVersionParamPattern" "$nextVersionParamDocu"
		prepareOnly "$prepareOnlyParamPattern" "$prepareOnlyParamDocu"
	)
	parseArguments params "" "$MINIMALIST_VERSION" "$@"
	# we don't check if all args are set (and neither set default values) as we currently don't use
	# any param in here but just delegate to releaseTemplate.

	function release_afterVersionHook() {
		local version projectsRootDir additionalPattern
		parseArguments afterVersionHookParams "" "$MINIMALIST_VERSION" "$@"

		local -r githubUrl="https://github.com/tegonal/minimalist"
		replaceTagInPullRequestTemplate "$projectsRootDir/.github/PULL_REQUEST_TEMPLATE.md" "$githubUrl" "$version" || die "could not fill the placeholders in PULL_REQUEST_TEMPLATE.md"

		local -r versionWithoutLeadingV="${version:1}"

		find ./ -name "*.md" | xargs perl -0777 -i \
			-pe "s@(https://github.com/tegonal/minimalist/(?:tree|blob)/)$branch@\${1}/v$version@g;" \
			-pe "s@latest#/kdoc@$versionWithoutLeadingV/kdoc@g;"

		perl -0777 -i \
			-pe "s/(version\s*=\s*\")[^\"]+\"/\${1}$versionWithoutLeadingV\"/g;" \
			"$projectsRootDir/build.gradle.kts"

		perl -0777 -i \
			-pe "s/(testImplementation(\"com.tegonal.minimalist:minimalist:)[^\"]+\"/\${1}$versionWithoutLeadingV\"/g;" \
			"$projectsRootDir/README.md"
	}

	function release_releaseHook() {
		rm "$projectDir/target"
		sbt clean doc
		mv "$projectDir/target/scala-*/api/*" "$projectDir/docs/"

		echo "Please enter the sonatype user token (input is hidden)"
		read -r -s SONATYPE_USER
		echo "Please enter the sonatype access token (input is hidden)"
		read -r -s SONATYPE_PW
		SONATYPE_PW="$SONATYPE_PW" SONATYPE_USER="$SONATYPE_USER" sbt publishSigned

		if [[ $prepareOnly != true ]]; then
			logInfo "artifacts were uploaded to sonatype. Log into https://oss.sonatype.org/#stagingRepositories and check if the staged repo looks good, close it and release once checks have passed. Press ENTER to continue"
			read -r _dummy
		else
			logInfo "artifact were uploaded to sonatype but as \`%s true\` we don't do anything in addition" "$prepareOnlyParamPatternLong"
		fi
	}

	# similar as in prepare-next-dev-cycle.sh, you might need to update it there as well if you change something here
	local -r additionalPattern="(SCALA_COMMONS_(?:LATEST_)?VERSION=['\"])[^'\"]+(['\"])"

	releaseTemplate \
		--project-dir "$projectDir" \
		--pattern "$additionalPattern" \
		"$@" \
		--after-version-update-hook release_afterVersionHook \
		--release-hook release_releaseHook
}

${__SOURCED__:+return}
release "$@"
