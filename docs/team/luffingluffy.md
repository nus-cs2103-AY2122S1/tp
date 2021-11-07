--- 
layout: page
title: Lutfi Randiva's Project Portfolio Page
---

### Project: Track2Gather

Track2Gather is a desktop application that will manage up to a few thousand contacts, providing basic features for contact tracing personnel to organise and search through them via personal information, case numbers, and Stay-Home-Notice periods. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has
about 10 kLoC.

Given below are my contributions to the project.

* **Project management**:
    * Managed the issue tracker
    * Managed the project board


* **New Feature**: Added the ability to allow addition of optional address information (`workAddress` and `quarantineAddress`) [\#49](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/59)
    * What it does: allows users to add a person's work and quarantine address, if available.
    * Justification: additional address information is always useful when it comes to tracking the location of the person in the case of emergency. It is optional since not everyone has multiple addresses.


* **New Feature**: Implemented tests for `addCommand` and `editCommand` for attributes added in v1.2 (`workAddress`, `quarantineAddress`, `shnPeriod`, `nextOfKinName`, `nextOfKinPhone` and `nextofKinAddress`) [\#84](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/84) [\#106](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/106
    * What it does: increase code coverage and allows detection of potential bugs.
    * Justification: as these attributes are new, tests were written to detect possible bugs.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=luffingluffy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false&tabAuthor=luffingluffy&tabRepo=AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing and new features**:
    * Improved the existing features to be able to save the newly added fields properly to the JSON file [\#57](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/57)
    * Improved the existing features by removing deprecated fields from the original AB3 application [\#66](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/66)


* **Documentation**:
    * User Guide:
        * Added all documentation for the features `delete`, `clear`, `session`, `schedule`, `scall`, and `fcall`. [\#124](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/124), [\#135](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/135), [\#209](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/209)
        * Ensured overall cohesion for milestones `v1.3` and `v1.4`. [\#137](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/137), [\#219](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/219)
    * Developer Guide:
        * Added all documentation for the features `delete`, `clear`, `session`, `schedule`, `scall`, and `fcall`. [\#92](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/92), [\#93](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/93), [\#135](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/135)
        * Updated all UML diagrams for `v1.3` milestone. [\#117](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/117)
        * Ensured overall cohesion for milestones `v1.2` - `v1.4`. [\#81](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/81), [\#137](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/137), [\#218](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/218)


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#47](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/47), [\#86](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/86), [\#114](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/114), [\#130](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/130)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/114), [2](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/119), [3](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/120), [4](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/127), [5](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/131), [6](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/132), [7](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/158), [8](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/137), [9](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/138), [10](https://github.com/AY2122S1-CS2103T-T12-2/tp/issues/139))
