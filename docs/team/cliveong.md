---
layout: page
title: Clive Ong's Project Portfolio Page
---

### Project: NewAddressBook

NewAddressBook is a desktop address book application used by teachers to manage their student and teacher contacts, optimised for use via a Command Line Interface (CLI). The user interacts with it using a CLI, and
it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `filterStudent`, `filterTeacher`.
  * What it does: Allows the user to filter the existing student/teacher list by involvement and/or tags.
  * Justification: This feature improves the product significantly because the user is usually managing a large number of contacts and being able to quickly find information of all contacts within a subset allows for easier communication with them. 
  * Highlights: This feature was constantly revised to match the ever higher standards that we are striving for, and thus has had several iterations.

* **New Feature**: Added the feature to mass delete a displayed list via `clearStudent` and/or `clearTeacher`
  * What it does: Allows the user delete every single contact in the current displayed list.
  * Justification: This feature improves the product significantly as allows mass management of contacts e.g. A class has recently graduated, so the user can the filter command to find the students in that particular class and then delete the contacts.
  * Highlights: The command will leave the non-displayed contacts intact and in the same order.

* **New Feature**: Added a `Gender` field
  * What it does: Allows the user to add/edit a `Gender` field to students/teachers to keep track of their gender.
  * Justification: The user might want to track the gender of the contacts for administrative purposes.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an
    in-depth analysis of design alternatives. The implementation required changes to
    existing commands like the adding and editing of contacts.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=cliveong&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Documentation**:
  * User Guide:
    * Added documentation for the `filter` command: [\#12](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/12), [\#101](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/101), [\#200](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/200), [\#197](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/197)
    * Fixed multiple errors in the UG: [#243](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/243)
  * Developer Guide:
    * Added implementation details/UML diagrams of the `filter` feature. [\#99](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/99), [\#139](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/139)
    * Add on to the ClearSequenceDiagram [\#197](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/197)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#251](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/251)
  * Reported bugs and suggestions for other teams in the class (examples: [2](https://github.com/cliveong/ped/issues/2), [3](https://github.com/cliveong/ped/issues/3), [4](https://github.com/cliveong/ped/issues/4), [5](https://github.com/cliveong/ped/issues/5), [6](https://github.com/cliveong/ped/issues/6), [7](https://github.com/cliveong/ped/issues/7), [8](https://github.com/cliveong/ped/issues/8))

* **Others**:
  * Added features that were ultimately not used/changed in the final product
    * `massDelete` function which immediately deletes a subset of contacts [\#106](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/106), [\#134](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/134)

