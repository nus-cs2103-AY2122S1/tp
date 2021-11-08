---
layout: page
title: Chen Xiaotong's Project Portfolio Page
---

### Project: NUS Mod Tracker

NUS Mod Tracker is a desktop app designed for NUS Computer Science (CS) students who are enrolled in the Academic Year 2021/2022 and onward to create their own academic plan, as well as to keep track of their Modular Credits(MC) and modules taken.
<br>
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `clear` command(Pull request [#83](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/83))
    * What it does: Allows the user to remove all modules in a specific semester from the academic plan.
    * Justification: This feature improves the product significantly because it allows a user to remove a set of modules from academic plan using one command. Without this feature, when users want to replan a specific semester, they need to find the modules in that semester first and run `untake` command for each module, which is very troublesome. 
    * Highlights: The 
* **New Feature**: Added set MC goal command feature(Pull request [#76](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/76))
    * What it does: Allows the user to set their own target Mc goal.
    * Justification: This feature improves the product significantly because it allows a user to change the default Mc goal and set a new one based on their own condition. It improves the flexibility of the product.
    * Highlights:  

* **Enhancements to existing features**:
  * Fixed bugs related to GUI and message output. (Pull request [#103](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/103))
  * Updated implementation of `edit` to suit NUS Mod Tracker's needs. (Pull request [#55](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/55))

* **Team-based tasks**:
  * Added a share of default modules to NUS Mod Tracker. (Pull request [\#166](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/166))
  * Updated AB3's logic components for the `delete` command to suit NUS Mod Tracker's needs.( [\#55](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/55))
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=LuoZhijie-tom&tabRepo=AY2122S1-CS2103T-W17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide:
    * updated the documentation for `ClearCommand` and `EditCommand`(Pull request [#83](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/83))
  * Developer Guide(Pull request [#179](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/179)):
    * Added the implementation details and UML sequence diagram for `clear` and `edit` features.(Pull request [#178](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/178))
    * Added the use cases for remove all modules in a specific semester from the academic plan and edit a module.(Pull request [#178](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/178))

* **Community**:
  * Reported bugs and suggestions for other teams in the class.
  * PRs reviewed: [\#167](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/167), [\#165](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/165)
