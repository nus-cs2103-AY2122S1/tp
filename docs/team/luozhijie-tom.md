---
layout: page
title: Luo Zhijie's Project Portfolio Page
---

### Project: NUS Mod Tracker

NUS Mod Tracker is a desktop application designed for NUS Computer Science (CS) students who are enrolled in the Academic Year 2021/2022 and onward to create their own academic plan,
as well as to keep track of their Modular Credits(MC) and modules taken. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `view` command. (Pull request [#77](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/77))
  * What is does: shows user the modules taken in a specific semester.
  * Justification: This feature improves the product significantly because it allows users to see their academic plan in each semester. Without this feature, users can only see modules they have selected in the entire module database, which is very inconvenient.


* **New Feature**: Enabled module cards to show different color in the GUI. (Pull request [#92](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/92))
  * What is does: Each module in the module list is colored based on the current semester and which semester the module is taken.
  * Justification: This feature improves the product significantly because it allows user to see the selected module status intuitively.
  * Highlights: Though the code written for this feature may not seem too much, to make this feature work correctly still needs significant efforts. For example, updating module cards' color when the current semester is changed needs a deeper understanding of the ListView class in `UI` and its link with `Model` which is not elaborated much in the original Developer guide.


* **Enhancements to existing features**:
  * Fixed bugs related to GUI. (Pull request [#165](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/165))
  * Added command formats in the help window. (Pull request [#97](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/97))
  * Added a share of default modules to NUS Mod Tracker. (Pull request [165](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/165))

* **Team-based tasks**: 
  * Updated classes `ModelManager`, `ModuleTracker` and `UserPrefs` in AB3's model component to suit NUS Mod Tracker's needs.(Pull request [#33](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/33), Pull request [#34](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/34))
  * Added the AcademicYear, Semester, and AcademicCalendar class. (Pull request [#57](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/57))
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=LuoZhijie-tom&tabRepo=AY2122S1-CS2103T-W17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management and team tasks**:
  * Created issues with detailed description during each iteration (Example: Issue [#68](https://github.com/AY2122S1-CS2103T-W17-2/tp/issues/68)).
  * Created releases `v1.2` `v1.3.trial` `v1.3`(3 releases) on GitHub.
  
* **Documentation**:
  * User Guide: 
      * updated the documentation for `ViewCommand`(Pull request [#89](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/89))
  * Developer Guide(Pull request [#179](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/179)):
      * Modified the explanation of `UI` and `Logic` component's architecture in Design section.
      * Added the implementation details and UML sequence diagram for `view` and `set` features.
      * Added the use cases for set MC goal, set current semester and view modules in a specific semester.
      * Updated NFR, Glossary and instructions for manual testing.
      * Added instructions for manual testing.
  
* **Community**:
  * Reported bugs and suggestions for other teams in the class (examples [1](https://github.com/LuoZhijie-tom/ped/issues/2), [2](https://github.com/LuoZhijie-tom/ped/issues/8))
  * Contributed to forum discussions (Examples: [Issue 371](https://github.com/nus-cs2103-AY2122S1/forum/issues/371), [Issue 354](https://github.com/nus-cs2103-AY2122S1/forum/issues/354))
