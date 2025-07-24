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

if ! [[ -v dir_of_tegonal_scripts ]]; then
	dir_of_tegonal_scripts="$scriptsDir/../lib/tegonal-scripts/src"
	source "$dir_of_tegonal_scripts/setup.sh" "$dir_of_tegonal_scripts"
fi
sourceOnce "$dir_of_tegonal_scripts/releasing/prepare-next-dev-cycle-template.sh"
sourceOnce "$scriptsDir/before-pr.sh"

function prepareNextDevCycle() {
	source "$dir_of_tegonal_scripts/releasing/common-constants.source.sh" || die "could not source common-constants.source.sh"

	# shellcheck disable=SC2034   # they seem unused but are necessary in order that parseArguments doesn't create global readonly vars
	local version projectsRootDir additionalPattern beforePrFn
	# shellcheck disable=SC2034   # is passed by name to parseArguments
	local -ra params=(
		version "$versionParamPattern" 'the version for which we prepare the dev cycle'
		projectsRootDir "$projectsRootDirParamPattern" "$projectsRootDirParamDocu"
		additionalPattern "$additionalPatternParamPattern" "is ignored as additional pattern is specified internally, still here as release-template uses this argument"
		beforePrFn "$beforePrFnParamPattern" "$beforePrFnParamDocu"
	)
	parseArguments params "" "$MINIMALIST_VERSION" "$@"
	# we don't check if all args are set (and neither set default values) as we currently don't use
	# any param in here but just delegate to prepareNextDevCycleTemplate.

	function prepare_next_afterVersionHook() {
		local version projectsRootDir additionalPattern
		parseArguments afterVersionHookParams "" "$MINIMALIST_VERSION" "$@"

		local -r devVersionWithoutLeadingV="${version:1}-SNAPSHOT"

		perl -0777 -i \
			-pe "s/(version\s*=\s*\")[^\"]+\"/\${1}$devVersionWithoutLeadingV\"/g;" \
			"$projectsRootDir/build.gradle.kts"
	}

	# similar as in release.sh, you might need to update it there as well if you change something here
	local -r additionalPattern="(MINIMALIST_VERSION=['\"])[^'\"]+(['\"])"

	prepareNextDevCycleTemplate \
		--project-dir "$projectsRootDir" \
		"$@" \
		--pattern "$additionalPattern" \
		--after-version-update-hook prepare_next_afterVersionHook
}

${__SOURCED__:+return}
prepareNextDevCycle "$@"
