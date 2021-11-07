---
layout: page
title: Teo Zhi Hao's Project Portfolio Page
---

### Project: CSBook

CSBook is a desktop application for Computer Science (CS) Teaching Assistants (TAs) to manage their students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.
Given below are my contributions to the project.

* **New Feature**: Added the ability to delete groups and their associated students from CSBook
  * What it does: Allows the user to delete a group and its associated students
  * Justification: Allows the user to be able to effectively manage groups that they create, and delete obsolete ones
  * Highlights: The implementation of this command was rather tricky because we had initially decided on an implementation involving tight coupling. It was difficult to fix and improve how objects reference each other for better internal implementation and coding style.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&tabAuthor=junwei26&tabRepo=AY2122S1-CS2103T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=Yttruire&zR=AY2122S1-CS2103T-T09-3%2Ftp%5Bmaster%5D&zACS=220.43386537126995&zS=2021-09-17&zFS=&zU=2021-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Team-based tasks**:
  * Removed old references to AB3 from config file, user guide and documentation guide: [\#49](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/49)
  * Changed `Student` objects to reference `Group` objects using `GroupName` instead of the `Group` object itself to reduce coupling: [\#98](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/98)
  * Added implementation of `UniqueGroupList` and its test cases, which is essential for all future group commands: [\#59](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/59)
  * Added testing support for `Group` related functionality and test cases for `UniqueGroupList` and `deletegroup`: [\#64](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/64) [\#115](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/115)

* **Documentation**:
    * User Guide:
      * Updated documentation for `help`, `add`, `list` and `edit` commands: [\#44](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/44)
      * Added documentation for `listgroups` and `deletegroup` command: [\#41](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/41), [\#96](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/96)
      * Updated style, formatting and content of user guide according to feedback: [\#110](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/110), [\#125](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/125)
    * Developer Guide:
      * Added non-functional requirements and glossary: [\#39](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/39), [\#40](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/40)
      * Added implementation details and UML diagrams of the student and group management main feature: [#\94](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/94), [\#125](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/125)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#103](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/103)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-W08-1/tp/issues/191), [2](https://github.com/AY2122S1-CS2103T-W08-1/tp/issues/202), [3](https://github.com/AY2122S1-CS2103T-W08-1/tp/issues/227))
