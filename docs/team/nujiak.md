---
layout: page
title: Chua Kai Jun's Project Portfolio Page
---

### Project: AddressBook Level 3

ProfBook is an AddressBook Application targeted at CS2103 instructors to manage both students and TAs contacts within teams and tutorial groups. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: Allows the user to undo any commands executed, up to the point of the last program start-up.
    * Justification: This feature improves the product significantly because a user may make a mistake in executing a command, and the undo/redo command provides a convenient method to reverse any unintended changes.
    * Highlights: This enhancement does not affect existing commands but may affect new commands implementing new data manipulation methods. The implementation was challenging as it was implemented without affecting existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103-T16-2%2Ftp%5Bmaster%5D&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=nujiak&tabRepo=AY2122S1-CS2103-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=nujiak&zR=AY2122S1-CS2103-T16-2%2Ftp%5Bmaster%5D&zACS=137.47457627118644&zS=2021-09-17&zFS=AY2122S1-CS2103-T16-2%2Ftp%5Bmaster%5D&zU=2021-11-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Enhancements to existing features**:
    * Merged the `clear` command into the `delete` command with arguments (Pull request [\#59](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/59))
    * Add option for `delete` command to delete all persons shown in window (Pull request [\#59](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/59))

* **Documentation**:
    * User Guide:
        * Contributed to documentation for `clear` and `delete` (Pull requests [\#79](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/79), [\#177](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/177))
        * Added documentation for `undo` and `redo`: (Pull requests [\#105](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/105), [\#177](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/177))
    * Developer Guide:
        * Added implementation details of the `delete` feature. [\#79](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/79)
        * Added implementation details of the `undo` and `redo` features/ [\#193](https://github.com/AY2122S1-CS2103-T16-2/tp/pull/193)
  
* **Community**:
    * Reported bugs and suggestions for [AY2122S1-CS2103-F09-4](https://github.com/AY2122S1-CS2103-F09-4/tp) during PE-D.
