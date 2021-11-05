---
layout: page
title: Muhammad Khair's Project Portfolio Page
---

### Project: TuitiONE

TuitiONE is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a new entity `Lesson` and established bi-directional linkages with `Student` (previously `Person`) entities.
  * What it does: Allows users to establish better relationship and collection of data such as lessons enrolled and subscription prices. Overall, a key element for the application to serve its purpose.
  * Justification: A driving factor in the application to serve its purpose. Establishing the new entity allows the user to fulfill their needs to link `Student` entities with `Lesson` ones.
  * Highlights:
    * Establishing weaker equality checks using a dependent entity `LessonCode`, numerous constraints and abstracting the initial `Lesson` class to dependent field classes such as `Subject` and `Price` in iterations `v1.2` and `v1.3`.
    * Ensuring defensive programming is embedded in the bidirectional relationship with multiple constraints so that it can be tapped on by other packages such as `model`, `storage` as well as `logic`.
    * Abstracting logic for ease of usage within other packages (similar to previous pointer).
    * Ensuring and maintaining the new entity into `storage` integration with Jackson JSON processing library.

* **Enhancements to existing features**:
  * Ensuring and maintaining the new and existing (modified) entities into `storage` integration with Jackson JSON processing library (Pull requests [#76](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/76), [#86](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/86))
  * Wrote additional tests for existing features to increase coverage for new entities and constraints (Pull requests [#104](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/104), [#114](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/114), [#178](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/178), [#195](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/195))
  * Evolve `Lesson` entity from a single container of primitive data to a class that depends on field entities using abstraction (Pull requests [#167](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/167), [#86](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/86))
  * Refactoring message and command logic to ensure easier readability (Pull requests [#287](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/287))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=muhammad-khair&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Project management**:
  * Managed release [`v1.3`](https://github.com/AY2122S1-CS2103T-F13-4/tp/releases/tag/v1.3)

* **Documentation**:
  * User Guide:
    * Did cosmetic and functional (and structural) tweaks overall: [#217](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/217), [#169](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/169/files)
  * Developer Guide:
    * Did cosmetic tweaks to existing documentation of user stories: [#149](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/149)
    * Added lesson related user stories: [#149](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/149)
    * Added implementation details of the `delete-l` feature: [#151](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/151)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#159](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/159), [\#190](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/190)
