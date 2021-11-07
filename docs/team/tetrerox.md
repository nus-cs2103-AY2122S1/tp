---
layout: page
title: Surya's Project Portfolio Page
---

### Project: Staff'd

Staff’d is a desktop staff tracking application that manages F&B staff, their schedule and salaries. The user interacts 
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to swap shifts between 2 staff.
  * _What it does_: Takes a shift from each of the 2 staff and swaps them between the 2 of them.
  * _Justification_: This allows staff to have flexibility in their work schedule to take breaks while not affecting the business. The user only has to input 1 command instead of using 2 `delete` and 2 `add` commands.

* **New Feature**: Added the ability for the user to delete a shift from a staff's schedule.
  * _What it does_: It locates a staff with the given index or staff's name and deletes the shift with the given date and slot.
  * _Justification_: This command is necessary for the manager to manage his/her staff's shifts and overall schedule.

* **Enhancements to existing features**:
  * Modified the delete command to locate staff using their names and also to support mass deletion using staff role and status.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tetrerox&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Tetrerox&tabRepo=AY2122S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub.
  * Managed milestone tracking.

* **Documentation**:
  * User Guide:
    * Updated the documentation for `delete` and added documentation for `deleteShift` and `swapShift`.
  * Developer Guide:
    * Updated the product scope and user stories.
    * Added introduction, overview and effort in the appendix.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#136](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/136), [#155](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/155)
