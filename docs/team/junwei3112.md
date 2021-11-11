---
layout: page
title: Yeo Jun Wei's Project Portfolio Page
---

### Project: TutorAid

TutorAid is a desktop application used for helping tech-savvy private tutors, who have busy schedules / many students, to keep track of the details of all their students and lessons.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project:

* **Code contributed**:
  * My contributions to the code base can be found [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=JunWei3112&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=JunWei3112&tabRepo=AY2122S1-CS2103T-W16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).

* **Enhancements implemented**:
  * Implemented the functionality of the `paid` and `unpaid` commands for v1.2.
    * Relevant PR(s): [#63](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/63)
    * Allows private tutors to set and unset payment status of their students.
    * Removed from TutorAid for v1.4 due to its limited functionality.
  * Set up the storage system for TutorAid to store and fetch `Lesson` objects from memory.
    * Relevant PR(s): [#90](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/90)
    * Worked closely with the `Storage` and `Model` components to implement storing, retrieving and handling of `Lesson` objects within TutorAid.
    * It was quite hard to implement since there were no concrete methods implemented to modify and view lessons in TutorAid at the time. As such, I wrote extensive test cases, alongside my functional code, to make sure that the storage system worked properly.
  * Implemented the functionality of the `add -l` and `del -l` commands.
    * Relevant PR(s): [#119](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/119)
    * Allows private tutors to add and delete lessons with the specified lesson details.

* **Contributions to the UG**:
  * Added documentation for the following features in the `Features` and `Command summary` sections
    * `list`
    * `add -l`, `del -l`

* **Contributions to the DG**:
  * Updated the documentation and class diagram for the `Storage` component under `Architecture`.
  * Added documentation and UML diagrams for the implementation details of `add -l`.
  * Contributed to the following sections:
    * Target user profile, value proposition, user stories, use cases, non-functional requirements, glossary

* **Contributions to team-based tasks**:
  * Set up the GitHub team organisation and repository.
  * Set up Codecov.
  * In charge of maintaining the issue tracker and milestones.
  * Updated `index.md` to fit TutorAid.

* **Review/mentoring contributions**:
  * Some examples of PRs reviewed with non-trivial review comments from me
    * [#110](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/110) - Highlighted documentation errors.
    * [#112](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/112) - Reported a bug and proposed a fix to resolve it. Highlighted some issues on code quality too.
    * [#125](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/125) - Reported a bug and highlighted some issues on code quality.
    * [#116](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/116) - Highlighted documentation errors.

* **Contributions beyond the project team**:
  * Reported 11 bugs for AY2122S1-CS2103-T11-1's application during the Practical Exam Dry Run.
