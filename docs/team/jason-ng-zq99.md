---
layout: page
title: Jason's Project Portfolio Page
 ---

### Project: contHACKS

contHACKS is a desktop app for Teaching Assistants (TAs) to manage contacts and lessons. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Feature**: Added the ability to find a lesson. (PR [#141](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/141))
* **What it does**: Allows the user to find a lesson within contHACKS by providing information about the lesson.
* **Justification**: This feature improves the product significantly as user can now find the relevant classes based on their searches from contHACKS.
* **Highlights**: On top of searching using the lesson's module code, you can also search via the lesson's start time and the day of the week of the lesson. The once obsolete tag prefix `t/` was recycled to be used to search for lesson start time.
* **Credits**: The design structure is inspired from AddressBook Level 3

**Enhanced Feature**: Enhanced the ability to find person. (PRs [#51](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/51), [#76](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/76), [#84](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/84))
* **What it does**: Allows the user to find person from contHACKS with different information quickly by allowing partial word matches.
* **Justification**: User now need not type the full name just to search for a single person, making the process faster especially for long names.
* **Justification**: While this might return more entries per search, it is still not significantly long enough to affect search experience.

**Team-Based Tasks**: 
* Refactored `moduleClass` class to `moduleLesson` class. (PR [#98](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/98))

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jason-ng-zq99&tabRepo=AY2122S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

**Project management**:
* Helped to regulate and ensure the proper workflow in the project
* Guided the general direction of where project should move towards

**Testing**:
* Wrote tests for all new and enhanced features:
  [#51](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/51),
  [#76](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/76),
  [#84](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/84),
  [#98](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/98),
  [#141](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/141)

**Documentation**:
* User Guide:
    * Added documentation for the features `find`: [#116](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/116), [#146](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/146)
    * Created a structure and added documentation for the feature `findc`: [#152](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/152)

* Developer Guide:
    * Refine product scope and use cases: [#16](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/16)
    * Update implementation of find feature: [#110](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/110)

**Community**:
* PRs reviewed (with non-trivial review comments):
  [#47](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/47),
  [#101](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/101),
  [#119](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/119),
  [#131](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/131),
  [#135](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/135),
  [#136](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/136),
  [#137](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/137)
