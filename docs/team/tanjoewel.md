---
layout: page
title: Tan Joe Wel's Project Portfolio Page
---

### Project: TAB

TeachingAssistantBuddy(TAB) is a desktop address book application that can help Teaching Assistants (TAs) manage their students' progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 24 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability add modules to TAB. (Pull request [\#72](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/72))
  * What it does: Module models an academic module offered in the National University of Singapore.
  This feature allows TAs to add modules that they are teaching to TAB.
  * Justification: This feature is a basic feature for the functionality of TAB.

* **New Feature**: Added the ability to edit the name of the module from TAB. (Pull request [\#107](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/107))
  * What it does: TAs can change the name of a module that they are teaching, without having to re-enter
  all the data that already exist in the module
  * Justification: TAs may accidentally type the wrong module code and not realize it until they have entered the data
  of their students.

* **New Feature**: Added the ability to delete a task from a module. (Pull request [\#122](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/122))
  * What it does: TAs delete existing tasks from a module
  * Justification: A module over the course of a semester can have many tasks. Having the ability to delete
  tasks that are no longer relevant can reduce the number of tasks in the module, making TAB neater overall.

* **New Feature**: Added the ability for TAB to store and load existing data. (Pull request [\#130](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/130) and [\#138](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/138))
  * What it does: TAB can store and load existing data using JSON files.
  * Justification: For modules with many students and tasks, having to re-enter all the data upon launching
  TAB is impractical and would not be any more effective than keeping separate notes.

* **Testing**:
  * Wrote test cases for the `add module`, `edit module` and `delete task` features that increased test coverage by 5.91%.
  (Pull request [\#209](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/209))
  * Wrote test cases for the `Storage` feature that increased test coverage by 10.00%.
    Pull request [\#217](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/217))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabAuthor=tanjoewel&tabRepo=AY2122S1-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)

* **Team-based contributions**:
  * Performed original renaming of AddressBook to TAB (Pull request [\#63](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/63))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add module` [\#72](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/72),
    and `delete task` [\#122](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/122)
  * Developer Guide:
    * Added implementation details of the `edit module` feature [\#107](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/107),
    and `delete task` [\#122](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/122)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#117](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/117),
  [\#133](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/133)
  
