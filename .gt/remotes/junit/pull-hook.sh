#!/usr/bin/env bash
set -eu -o pipefail

function gt_pullHook_junit_before(){
  : # no op, nothing to do
}

function gt_pullHook_junit_after(){
  local -r tag=$1 source=$2 target=$3

  perl -0777 -i \
  	-pe 's/package org.junit/package com.tegonal.minimalist.export.org.junit/g;' \
  	-pe 's/(import (?:static )?)(org.junit.platform.commons.(util|logging))/$1com.tegonal.minimalist.export.$2/g;' \
  	"$target"
}
