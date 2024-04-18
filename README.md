<!-- for main -->

[![Download](https://img.shields.io/badge/Download-v1.1.0-%23007ec6)](https://central.sonatype.com/artifact/com.tegonal.minimalist/minimalist/1.1.0)
[![EUPL 1.2](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Quality Assurance](https://github.com/tegonal/minimalist/actions/workflows/quality-assurance.yml/badge.svg?event=push&branch=main)](https://github.com/tegonal/minimalist/actions/workflows/quality-assurance.yml?query=branch%3Amain)
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")

<!-- for main end -->
<!-- for release -->
<!--
[![Download](https://img.shields.io/badge/Download-v1.1.0-%23007ec6)](https://central.sonatype.com/artifact/com.tegonal.minimalist/minimalist/1.1.0)
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")
-->
<!-- for release end -->



# Minimalist

![Minimalist](https://raw.githubusercontent.com/tegonal/minimalist/main/.idea/icon.png) M like Minimalist, 
a library which helps you in setting up parameterized tests and prioritise them in case you don't have enough time 
to execute all of them.


---
❗ You are taking a *sneak peek* at the next version. It could be that some features you find on this page are not released yet.  
Please have a look at the README of the corresponding release/git tag. Latest version: [README of v1.1.0](https://github.com/tegonal/minimalist/tree/main/README.md).

---

**Table of Content**
- [Installation](#installation)
- [License](#license)


# Installation

Minimalist is published to maven central.

*build.gradle.kts*:
```kotlin
repositories {
    mavenCentral()
}
dependencies {
    testImplementation("com.tegonal.minimalist:minimalist:1.1.0")
}
```

# Documentation

Code documentation can be found on github-pages: <https://tegonal.github.io/minimalist/latest#/kdoc>.

# License

Minimalist is licensed under [European Union Public Licence 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Minimalist is using
- [junit-jupiter-params](https://github.com/junit-team/junit5) licensed under [EPL 2.0](https://www.eclipse.org/legal/epl-v20.html)
