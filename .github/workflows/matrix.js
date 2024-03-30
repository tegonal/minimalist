//
//    __                          __
//   / /____ ___ ____  ___  ___ _/ /       This file is provided to you by https://github.com/tegonal/minimalist
//  / __/ -_) _ `/ _ \/ _ \/ _ `/ /        Copyright 2023 Tegonal Genossenschaft <info@tegonal.com>
//  \__/\__/\_, /\___/_//_/\_,_/_/         It is licensed under European Union Public License v. 1.2
//         /___/                           Please report bugs and contribute back your improvements
//
//                                         Version: v0.17.0
//##################################
// adapted version of https://github.com/vlsi/github-actions-random-matrix/blob/main/examples/matrix.js
//##################################
const {MatrixBuilder} = require('./matrix_builder');
const os = require("os");
const fs = require("fs");

// see https://github.com/actions/toolkit/issues/1218
function setOutput(key, value) {
  // Temporary hack until core actions library catches up with github new recommendations
  const output = process.env['GITHUB_OUTPUT']
  fs.appendFileSync(output, `${key}=${value}${os.EOL}`)
}

const matrix = new MatrixBuilder();

matrix.addAxis({
  name: 'java_distribution',
  values: [
   	'corretto',
    'liberica',
	'microsoft',
	// seems to have problems with kotlin https://youtrack.jetbrains.com/issue/KT-61836
    //'semeru',
    'temurin',
	'zulu',
  ]
});
matrix.addAxis({
  name: 'java_version',
  title: x => 'Java ' + x,
  values: [
    '11',
    '17',
    // TODO update gradle to latest and activate again
    //'21',
  ]
});

matrix.addAxis({
  name: 'os',
  title: x => x.replace('-latest', ''),
  values: [
    'ubuntu-latest',
    'windows-latest',
    'macos-latest'
  ]
});

// This specifies the order of axes in CI job name (individual titles would be joined with a comma)
matrix.setNamePattern(['java_version', 'java_distribution', 'os']);

matrix.generateRow({java_version: matrix.axisByName.java_version.values[0]});
matrix.generateRow({java_version: matrix.axisByName.java_version.values.slice(-1)[0]});
matrix.generateRow({os: 'windows-latest'});
matrix.generateRow({os: 'ubuntu-latest'});

const include = matrix.generateRows(process.env.MATRIX_JOBS || 4);
if (include.length === 0) {
  throw new Error('Matrix list is empty');
}
// Sort jobs by name, however, numeric parts are sorted appropriately
// For instance, 'windows 8' would come before 'windows 11'
include.sort((a, b) => a.name.localeCompare(b.name, undefined, {numeric: true}));

console.log(include);
setOutput('matrix', JSON.stringify({include}));

