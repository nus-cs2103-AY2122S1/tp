---
layout: page
title: Germaine Seah's Project Portfolio Page
---

### Project: contHACKS

contHACKS is a desktop app for Teaching Assistants (TAs) to manage contacts and lessons. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Feature**: Added the ability to delete a lesson. (PR [#135](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/135))
* **What it does**: allows the user to delete a lesson into contHACKS by providing information about the lesson.
* **Justification**: This feature improves the product significantly as user can now delete the irrelevant lessons from contHACKS.
* **Credits**: The design structure is inspired from AddressBook Level 3

**Enhanced Feature**: Enhanced the ability to delete a person. (PRs [#14](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/14), [#47](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/47))
* **What it does**: allows the user to delete a person in multiple ways like individually and in batches.
* **Justification**: ensure that the TAs can easily and quickly delete irrelevant contacts by specified indexes or by the module code.
* **Justification**: when TAs end a module, deleting by the module that they are no longer teaching would ensure that contHACKS is organized with no irrelevant information.
* **Highlight**: deleting by module code and lesson code are allowed. There was a need to include a way to filter the list before deleting. Some part of the code used for the Delete feature relied on components used for the Find feature. This made the feature challenging as it required the cooperation of both the persons in charge of Delete and Find Feature.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=driip-ddrop&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

**Project management**:
* Updated Developer Guide user stories and refactor UML Diagrams.
* Updated README to fit our project

**Testing**:
* Wrote tests for all new and enhanced features:
 ( [#57](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/57),
  [#131](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/131),
  [#139](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/139),
  [#145](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/145) )

**Documentation**:
* User Guide:
    * Added documentation for the feature `delete` and updated formatting : [#105](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/105)
    * Added documentation for feature `deletec` : [#153](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/153)

* Developer Guide:
    * Updated the Developer Guide for the User stories: [#106](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/106)
    * Updated documentation details for delete feature : [#219](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/219)

**Community**:
* PRs reviewed (with non-trivial review comments):
  [#101](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/101),
[#208](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/208)
