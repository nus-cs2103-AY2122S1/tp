---
layout: page
title: Dion Neo's Project Portfolio Page
---

### Project: AddressBook Level 3

*ComputingConnection* is for entrepreneurial students in NUS Computing who want to **keep track of other students’ skill sets so that they can easily look for suitable people to work with on future projects.** *ComputingConnection* is optimized for Command Line Interface (CLI) over a Graphical User Interface (GUI) for efficiency with a keyboard.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add and remove interactions to contacts. 
  * Main implementation: (Pull request [\#66](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/66))
  * What it does: allows the user append or delete interactions one at a time.
  * Justification: This feature improves the product significantly because a user can now record down details about their interactions they had with the other party and refer back to them in the future.
  * Highlights: This enhancement was carefully designed to be isolated from existing commands and and lays the foundation of future features such as the projects feature, through inheritance. The implementation too was challenging as it's design is vastly different from exisiting commands.

* **New Feature**: Added a compatability field to track a contact's compatibility rating. 
  *  Main implementation: (Pull request [\#105](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/105))
  * What it does: allows the user to record down a integer value from 0 to 100 that represents the compatibility rating between him and the contact.
  * Justification: This feature improves the product vastly because a user can now easily filter his/her contacts based on their compatibiity ratings.
  * Highlights: This enhancement affects existing commands mainly the add and edit command. Because of it's nature, the implementation required it to be an optional field, making it slightly more challenging as it required the careful use of Java's Optional class to handle null cases.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103T-W10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=noobmaster19&tabRepo=AY2122S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
  * Converted the edit command to handle for new fields (Pull request [\#41](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/41), subsequent amendments to resolve bugs were made subsequently but in seperate commits)
* **Documentation**:
  * User Guide:
    * Added documentation for the feature `interaction` [\#82](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/82), [\#100](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/100)
    * Added documentation for the feature  `edit` [\#18](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/18)
    * Tweaked the documentation for features `add` and `edit` to accommodate for the new  `compatibility` field [\#107](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/107)
    * Added FAQ to intuitively explain the `compatibility` field [\#181](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/181)
  * Developer Guide:
    * Added implementation details of the `interaction` feature.

* **Community**:
  * PRs reviewed: [\#11](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/11), [\#16](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/16), [\#17](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/17), [\#20](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/20), [\#21](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/21), [\#22](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/22), [\#23](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/23), [\#24](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/24), [\#57](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/57), [\#67](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/67), [\#115](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/115), [\#117](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/117) 
  * Reported bugs and suggestions for other teams in the class (offline)
  * Some parts of the interactions feature I added was adopted by several other class mates
