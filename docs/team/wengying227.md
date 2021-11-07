---
layout: page
title: Weng Ying's Project Portfolio Page
---

### Project: NUS Mod Tracker

NUS Mod Tracker is a desktop app designed for NUS Computer Science (CS) students who are enrolled in the Academic Year 2021/2022 and onward to create their own academic plan, as well as to keep track of their Modular Credits(MC) and modules taken.
<br>
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Set Command Feature**: Added the ability to set user's information.(Pull request [\#61](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/c1d6b581a906724bd7709ff4e79a350bafcd687b))
    * What it does: allows the user to set the current semester or user's MC goal and store the information in the database.
    * Justification: This feature improves the product significantly because 
      1. the app needs to store the current semester information so that it can indicate a module in user's plan is taken already or is to be taken in the future.
      2. a user should indicate his/her MC goal and the app should display the MCs obtained and target MCs.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation was challenging as it required a completely new implementation.

* **UserInfo Storage Added**: Added the ability to read `UserInfo` data from JSON file and store them to the file. (Pull request [\#85](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/07ce7d0bb89204f0d1cf4a55fca76bede3eb27cf))
    * What it does: allows the current information and the MC goal a user set to store in the database.
    * Justification: This feature improves the product significantly because the app should store user's information in the database and should display the previous information when the app refreshes.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation was challenging as it required a complete understanding of how JSON files are handled.
    
* **Module Model Added**: Added the Module Model. (Pull requests [\#32](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/b07d77f7ba5fdc8a7f70177aeec6dc12d7feb334) and [\#42](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/5f80f2e37e0f55e851e7b856858781b1f6d504ef))
    * What it does: creates basic models for the app and allows later features to be implemented.
    * Justification: This feature improves the product significantly because all the features later need the basic models.
    * Highlights: This enhancement affects commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation was challenging as it required forward-looking vision.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=wengYing227&tabRepo=AY2122S1-CS2103T-W17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements implemented**:
    * Wrote tests for existing features to increase coverage (Pull requests [\#43](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/08df3017cc5f0a1ae9a9531fd0878aaad6874ed7), [\#44](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/100fbee3ab964697f09583296a0858ea96fd2e14))
    * Deleted `InfoCommand` feature
    
* **Documentation**:
    * User Guide:
        * Added documentation for the features `set` (Pull request [\#87](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/4cc3f92e7f2b966c241df3d917590be118fbdbb5))
    * Developer Guide:
        * Added implementation details of the `Storage` component and `Model` component. (Pull request [\#174](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/6aa7cecf92b99af8bef07b998b4cdcdabf085227), [\#177](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/e13e8bcf5a7f60f9f3f49564eab82d16372db6e6))
        * Added implementation details of the `delete` feature. (Pull request [\#181](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/f9e21b6be55928737e3f5ab29ee6c85d02c3cc73))
        * Added use cases for `add` and `list` feature. (Pull request [\#181](https://github.com/AY2122S1-CS2103T-W17-2/tp/commit/c0e78bcd7b94c730d8e866da67adfa891fb1f9df))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#183](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/183#pullrequestreview-799494859))
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/337), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/249))
    * Reported bugs and suggestions for other teams in the class
