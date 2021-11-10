---
layout: page
title: Cuong's Project Portfolio Page
---

### Project: TutorAid

TutorAid is a desktop application used for helping tech-savvy private tutors, who have busy schedules / many students, to keep track of the details of all their students and lessons. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code contributed**:
  * My contributions to the TutorAid code base can be found [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=W16&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=ErnestCuong&tabRepo=AY2122S1-CS2103T-W16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).

* **New features implemented**:
  * Implemented a `progress` feature for student for v1.2.
    * Relevant PR(s): [#64](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/64/files)
    * Implemented the `add -p` and `del -p` commands to add and delete progress from a student.
    * Worked closely with the `Logic` component to implement the commands and the parser for adding and deleting progress, as well as the `Model` component to add a new `Progress` field to `Student`.
    * Only allowed one progress entry in this iteration.
  * Enhanced the `progress` feature so that each student can have up to 10 progress entries for v1.3.
    * Relevant PR(s): [#105](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/105)
    * Replaced the `Progress` field in `Student` with `ProgressList` that keeps track of up to 10 progress entries.
    * Justification: I wanted to abstract away the management of progress entries from `Student` to preserve the single responsibility principle.
  * Implemented the functionality of `add -sl` and `del -sl` commands that add and remove students from lessons for v1.3.
    * Relevant PR(s): [#125](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/125)
    * Worked closely with the `Logic` component to implement the commands and the parser for adding students to and removing students from lessons, as well as the `Model` component to add a new `Lessons` field to `Student`.

<div style="page-break-after: always;"></div>

* **Contributions to the UG**:
  * Added documentation for the following features in the `Features` and `Command summary` sections:
    * `add -p` and `del -p`.
    * `add -sl` and `del -sl`

* **Contributions to the DG**:
  * Worked on target user profile, value proposition, user stories, use cases, non-functional requirements, glossary.
  * Added documentation and UML diagrams for the implementation details of `add -p` and `add -sl`.

* **Contributions to team-based tasks**:
  * Ensured code quality to a small extent.
  * Involved in the manual testing of the final product.

* **Review/mentoring contributions**:
  * [#90](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/90) - Highlighted the need for single responsibility principle with suggestions to make a new class called `LessonBook`.
  * [#86](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/86) - Coding standard.
  * [#89](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/89) - Suggestions to improve consistency.

* **Contributions beyond the project team**:
  * Forum discussions: [#75](https://github.com/nus-cs2103-AY2122S1/forum/issues/75#issuecomment-904219081)
  * PE-Dry Run: Reported 6 issues for [CS2103T-T10-3](https://github.com/ErnestCuong/ped/issues)

