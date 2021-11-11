---
layout: page
title: Tianyue's Project Portfolio Page
---

### Project: InternSHIP

InternSHIP is a desktop app for university students to manage their internship applications.
InternSHIP has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the `Position` field.
  * What it does: Allows the user to record internship position (Pull request [\#51](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/51)).
  * Justification: Being able to remind users about application details on which role they applied for.
  * Highlights: This feature affected all existing commands as a new field had to be added to all commands. Many test cases needed to be changed too.
  * The implementation of this feature was later enhanced by the team.

* **New Feature**: Added the `Deadline` field.
  * What it does: Allows the user to record application deadline (Pull request [\#51](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/51)).
  * Justification: Being able to remind user not to miss important deadlines.
  * Highlights: This feature affected all existing commands as a new field had to be added to all commands. Many test cases needed to be changed too.
  * The implementation of this feature was later enhanced by the team.

* **New Feature**: Added the ability to find application(s).
    * What it does: Allows the user to find application(s) based on a given criterion and keyword.
    * Justification: This feature improves the product significantly because a user could find applications with specific characteristics in a long list.
    * This serves as a filter feature as well, helping users to view only the entries of their current interest.
    * Highlights: This enhancement affects existing commands and many test cases needed to be changed too.
    * The implementation was challenging as it required changes to existing commands and perhaps future commands.
    * This enhancement went through a few stages. In the beginning, `find` only allowed finding by company name and position.
    * It was then improved to allow the user to specify other fields such as completion status, application outcome, priority and requirements.
    * In the final version, the `find` command can also check if input for keyword is valid according to the input restriction for different parameters.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tianyue58&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Project management**:
    * Set up the GitHub team codecov.
    * Updated Readme (Pull request [\#28](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/28)).
    * Added v1.2 demo in collaborative project notes.  
    * Managed deadlines and deliverables for each iteration, including updating the issue tracker and milestones.

* **Enhancements to existing features**:
    * Refactored code base to remove all traces of AddressBook, including renaming of classes, methods, files, etc (Pull request [#51](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/51)).
    * Changed the product icon (Pull request [\#51](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/51)).
    * Refactored the all the test cases to remove the tag functionality (Pull request [\#122](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/122)).
    * Wrote additional tests to increase test coverage (Pull request [#250](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/250)).  
    * Fixed bugs from Practical Examination Dry Run (Pull request [\#214](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/214)).

* **Documentation**:
    * User Guide:
        * Added the documentation for the `find` feature (Pull requests [\#113](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/113), [\#221](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/221)).
    * Developer Guide:
        * Added implementation details of the `find` feature. (Pull request [\#105](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/105)).
        * Updated the appendix for team effort summary (Pull request [\#223](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/223)).
        * Updated the uml diagram and use case for find command (Pull requests [\#245](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/245), [\#254](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/254)).
  
* **Community**:
  * Reported an above-average number of bugs in the PE-D (Examples: Bugs [#4](https://github.com/tianyue58/ped/issues/4), [#10](https://github.com/tianyue58/ped/issues/10), [#11](https://github.com/tianyue58/ped/issues/11)). 
  * Contributed to forum discussions (Examples: Issues [#40](https://github.com/nus-cs2103-AY2122S1/forum/issues/40), [#385](https://github.com/nus-cs2103-AY2122S1/forum/issues/385)).
