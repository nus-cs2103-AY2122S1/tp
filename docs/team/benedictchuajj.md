---
layout: page
title: Benedict's Project Portfolio Page
---

### Project: LeadsForce

LeadsForce - LeadsForce is a desktop application catered towards student financial advisors (FA) for managing their leads. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the view command functionality. (Pull requests [\#46](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/46), [\#61](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/61))
  * What it does: allows the user to view a specific client's information in the sidebar.
  * Justification: This feature allows the user to pin a specific client in the sidebar for easy reference as well as to read attributes that would otherwise be truncated in the ClientList cards.
  * Highlights: This feature was tricky to implement as it required changes that cuts across the model, logic and GUI portions of the application. Particularly for the GUI, there was a need to ensure that the sidebar takes sufficient space whilst still giving enough reading space for the existing parts of the GUI.

* **New Feature**: Added a client attribute - Next Meeting. (Pull requests [\#103](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/103))
  * What it does: allows the user to store information about upcoming meetings to allow users to have a scheduler.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=benedictchuajj&tabRepo=AY2122S1-CS2103T-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub
  * Kept track of issues and team's progress

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#126](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/126), [\#143](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/143), [\#202](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/202))
  * Added validity checks for date-related client attributes implemented (Pull requests [\#198](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/198), [\#213](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/213))
  * Managed the common classes and improved code quality in it (Pull request [\#86](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/86))

* **Documentation**:
  * User Guide:
    * Added documentation for the `view` feature and client attributes [\#68](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/68)
    * Made minor tweaks to existing documentation for v1.2: [\#80](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/80)
  * Developer Guide:
    * Added implementation details of the `view` feature: [\#117](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/117)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#84](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/84), [\#120](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/120), [\#145](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/145), [\#209](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/209)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-T10-2/tp/issues/161), [2](https://github.com/AY2122S1-CS2103T-T10-2/tp/issues/164), [3](https://github.com/AY2122S1-CS2103T-T10-2/tp/issues/195))
