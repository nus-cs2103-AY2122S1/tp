---
layout: page
title: Tao Jun's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA<sup>2</sup> is a desktop application developed for Teaching Assistants (TA) from the School of Computing (SoC) at the
National University of Singapore (NUS) to help them manage student information and keep track of students' assignment submissions.
TA<sup>2</sup> primarily uses a Command Line Interface (CLI) for user interaction. This allows SoC TAs to fully utilise
TA<sup>2</sup> as they are able to type fast.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add assignment to many students at once
  * What it does: This feature allows users to add a specific assignment to all students under the same module.
  * Justification: This feature provides convenience for users who want to add an assignment to many students at once. Since
  students under the same module will likely to have the same assignments, this feature allows users to save time by 
  adding the assignment to all students under the same module for them. With this feature, users do not have to add the 
  same assignment to students individually.
  * Highlights: This feature takes into account some scenarios which may result in inconsistencies in the same assignment
  for different students. It considers any inconsistencies in due dates of the same assignment and the letter cases
  of the description of the assignment.
  * Credits: This feature is implemented as `AddAssignmentToAllCommand` and follows the structure of other classes which
  extends the `Command` class.

* **New Feature**: Added the ability to clear all completed assignments
  * What it does: This feature allows users to remove completed assignments for all students.
  * Justification: This feature provides better management of assignments for users by reducing the clutter of assignments
  and removing assignments that users no longer need to view.
  * Highlights: This feature was challenging to implement as simply removing completed assignments for all persons may
  result in the displayed assignment list to change to that of another student's. It has to be ensured that the current
  displayed assignment list does not change to reduce confusion for the user.
  * Credits: This feature is implemented as `CleanAssignmentCommand` and follows the structure of other classes which
  extends the `Command` class.
<br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=taojun&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=TaoJun99&tabRepo=AY2122S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Contribution to team-based tasks**:
  * Documenting value proposition and target user profile in Developer Guide
  * Updating content of the homepage of project website
  * Fixing format issues of Developer Guide page in project website

* **Enhancements to existing features**:
  * Modified the storage component to be able to read, write and save assignments as Jackson format. (Pull request [\#70](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/70))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `giveall` and `clean` (Pull requests [\#130](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/130), [\#106](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/106))
  * Developer Guide:
    * Added implementation details of the `giveall` and `clean` feature. (Pull request [\#254](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/254))
    * Added manual testing details on `giveall` and `clean` commands. (Pull request [\#254](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/254))
    * Added manual testing details on saving data to data file. (Pull request [\#254](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/254))
