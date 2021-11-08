---
layout: page
title: Michael Lee's Project Portfolio Page
---

### Project: TuitiONE

TuitiONE is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a feature called `Enroll`(Pull requests [#75](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/75), [#79](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/79), [#84](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/84), [#98](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/98)) that established bi-directional linkages between `Student` (previously `Person`) and `Lesson` entities.
  * What it does: Allows users to enroll a student of a specific `grade` into a lesson with that same respective `grade`.
  * Justification: A key feature in the application in order to establish the bi-directional linkages between a `Student` and a `Lesson`.
  * Highlights:
    * A `Student` can be enrolled into a `Lesson` if and only if:
      1. The `Student` is not currently enrolled in the specific `Lesson`.
      1. The `Student` has the same grade as the `Lesson`.
      1. The `Student` must not currently be enrolled in **10** or more `Lessons`.
      1. The `Lesson` must not currently have **15** or more `Students` enrolled.
    * Abstracting logic for ease of usage within other packages.
    * Ensuring that model updates the `Student` and `Lesson` to reflect the bi-directional linkage.

* **Enhancements to existing features**:
  * Updated the GUI logos such that they are the same for both Mac OS and Windows OS: (Pull requests [#280](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/280)).
  * Updated the GUI to wrap texts for `Name`, `Email`, and `Address`: (Pull requests [#280](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/280)).
  * Changed EnrollCommand to accept `lessonIndex` instead of `lessonCode`: (Pull requests [#147](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/147)).
  * Wrote additional tests for existing features to increase coverage for new entities and constraints: (Pull requests [#207](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/207), [#190](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/190), [#197](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/197), [#147](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/147), [#112](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/112), [#98](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/98)).
  * Updated `Find` feature to allow partial word searches: (Pull requests [#116](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/116)).
  * Refactored `Person` package into `Student` package: (Pull requests [#117](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/117)).
  * Refactored `Tag` package into `Remark` package: (Pull requests [#140](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/140)).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=michael&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17).

* **Project management**:
  * Managed release [`v1.3.trial`](https://github.com/AY2122S1-CS2103T-F13-4/tp/releases/tag/v1.3.trial).

* **Documentation**:
  * User Guide:
    * [Added documentation for the features `Enroll`](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/290).
    * [Added documentation for About, Introduction, and Quick Start](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/180).
    * Did cosmetic and functional (and structural) tweaks overall: (Pull requests [#298](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/298), [#220](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/220), [#169](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/169/files)).
  * Developer Guide:
    * [Added implementation details of the 'Enroll' feature](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/153).
    * [Did cosmetic tweaks to existing documentation of user stories](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/164).
    * [Updated Enroll sequence, activity, and object diagrams](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/164)
    * [Added documentation for User Stories](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/116).
* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [#95](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/95#discussion_r725457630), [#142](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/142#discussion_r730911339)).

* **Tools**:
  * Utilize SceneBuilder to build GUI Layout
