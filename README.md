<!-- for main -->

[![Download](https://img.shields.io/badge/Download-v1.0.0-%23007ec6)](https://github.com/tegonal/minimalist/releases/tag/v1.0.0)
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Code Quality](https://github.com/tegonal/minimalist/workflows/Code%20Quality/badge.svg?event=push&branch=main)](https://github.com/tegonal/minimalist/actions/workflows/code-quality.yml?query=branch%3Amain)
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")

<!-- for main end -->
<!-- for release -->
<!--
[![Download](https://img.shields.io/badge/Download-v1.0.0-%23007ec6)](https://github.com/tegonal/minimalist/releases/tag/v1.0.0)
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
Please have a look at the README of the corresponding release/git tag. Latest version: [README of v1.0.0](https://github.com/tegonal/minimalist/tree/v1.0.0/README.md).

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
    testImplementation("com.tegonal.minimalist:minimalist:1.0.0")
}
```

# License

Minimalist is licensed under  [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Atrium is using
- [junit-jupiter-params](https://github.com/junit-team/junit5) licensed under [EPL 2.0](https://www.eclipse.org/legal/epl-v20.html)