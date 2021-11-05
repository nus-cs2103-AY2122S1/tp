---
layout: page
title: Ong Xing Wei's Project Portfolio Page
---

### Project: SportsPA

SportsPA is a desktop application used to manage membership and training sessions of NUS sports CCAs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to import member details from a CSV file.
  * What it does: Allows the user to add and update the details of multiple members without having to repeatedly add members individually from the CLI.
  * Justification: This feature improves user experience of the product because instead of repeatedly typing the command word and related fields with their prefixes into the CLI, 
  a user will be able to reduce the amount he/she types by simply preparing a CSV file and importing the member details from the CSV file.
  * Highlights: The implementation was challenging as it required something
  * Pull request: [#137](#https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/137)

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=moley456)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
