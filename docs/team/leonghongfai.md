---
layout: page
title: Leong Hong Fai's Project Portfolio Page
---

### Project: Source Control

Source Control is a desktop application for CS1101S professors to manage the performance of their students. Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add an individual student.
    * What it does: allows the user to add an individual student into the database. If groups are provided, then the student is added to the group too.
    * Justification: The feature is necessary for the target user as there needs to be a way to add in individual students after using the import function. This makes adding students to the database quicker for smaller number of additions than doing edits on the csv file.
    * Highlights: This feature is the alternative method for adding students into the database besides the import function. Implementation was not too challenging, but involved a lot of refactoring as a whole from the old AddressBook3 for the team. 
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=leonghongfai&tabRepo=AY2122S1-CS2103T-W08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
    * Updated site-wide settings
      [\#43](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/43)


* **Enhancements to existing features**:
    * Revamped the base layout of the GUI (Pull requests
      [\#154](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/154),
      [\#156](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/156))
    * Minor GUI updates
      [\#106](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/106)
    * Enhanced ABParser to take in two-word commands (Pull requests
      [\#95](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/95),
      [\#96](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/96),
      [\#97](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/97),
      [\#126](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/126),
      [\#127](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/127))
    * Revert ABParser back to take in only single word commands due to issues caused by `alias` command
      [\#239](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/239)
    * Enhanced `addalloc`, `addgroup`, and `addscore` commands to show the list of students with naming conflicts
      [\#174](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/174)
    * Wrote additional tests for existing features to increase coverage (Pull requests
      [\#98](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/98),
      [\#191](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/191))


* **Documentation**:
    * User Guide:
        * Added documentation for the add feature [\#54](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/54)
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Updated NFR subsection [\#44](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/44)
        * Added `addstudent` feature implementation (Pull requests
          [\#147](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/147),
          [\#190](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/190),
          [\#191](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/191),
          [\#288](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/288))


* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#148](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/148),
      [\#179](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/179),
      [\#183](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/183),

    * Reported bugs and suggestions for other teams in the class
      (examples:
      [1](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/111),
      [2](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/112),
      [3](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/118),
      [4](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/119),
      [5](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/121),
      [6](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/127),
      [7](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/134),
      [8](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/135),
      [9](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/163),
      [10](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/140),
      [11](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/142),
      [12](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/144))
