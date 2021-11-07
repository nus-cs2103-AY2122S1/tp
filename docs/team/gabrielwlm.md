---
layout: page
title: Gabriel Waikin Loh Matienzo's Project Portfolio Page
---

### Project: ClassMATE

ClassMATE is a desktop application designed for instructors of the CS2101 Module in NUS to easily store class and student details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/)

* Enhancements implemented:
  * New feature: Added the ability to find classes: 
    * PR [\#130]()
    * Allows the user to find a specific tutorial class among all the tutorial classes, so they can use other features of ClassMATE with the desired tutorial class.
    * This implementation was a bit tough as it was my first feature implementation, which involved configuring several edits to the existing code, including the creation of `FindClassCommand` and `FindClassCommandParser`, which define the find class command. I also modified several files to integrate the `FindClassCommand` into ClassMATE, including `ClassmateParser`.
  * New feature: Added the ability to view students in a group:
    * PR [\#151]()
    * Allows the user to only see students who are in a specific tutorial group. The student list is filtered to show only students who are in the desired tutorial group, for easy access to perform other features of ClassMATE with students in the tutorial group.
    * This implementation required an understanding of the `TutorialGroup` feature (contributed by Yirui and Yiyang) and required the creation of more `Predicate` classes to filter students in the student list, including `GroupMemberPredicate` and `TutorialGroupContainsKeywordPredicate`. 

* **Documentation**:
    * Edited various documents to repurpose AddressBook into ClassMATE [\#78]()
    * User Guide:
        * Added documentation for the features `addc`, `deletec` and `viewc` [\#76]()
        * Rearranged order of the features to improve readability [\#76](), [\#238]()
        * Updated command summary [\#76](), [\#238]()
    * Developer Guide:
        * Added glossary terms [\#81]()
        * Added use cases [\#81]()
        * Added non-functional requirements [\#81]()

* **Refactoring**:
    * Refactored Person to Student
      * PR [\#93]()
      * I refactored all mentions and instances of `Person` to `Student` to make ClassMATE more specific to its target users and its purpose, which in short is to keep a record of students.
        * Refactored AddressBook to Classmate
          * PR [\#116]()
          * I refactored all mentions and instances of `AddressBook` to `ClassMATE` to differentiate ClassMATE from AddressBook which we adapted, as it is now a different application.

* **Team-Based tasks**:
    * Managed documentation for releases `v1.1` - `v1.4` (4 releases) on GitHub
    * Conducted manual testing of features based on descriptions provided in the User Guide.
    * **Bug Fixes**:
      * IsSameStudent bug [\#130]()
      * Findc bug [\#170]()
      * Viewg bug [\#179]()
      * Non-existent class/group bug [\#241]()
      * Documentation bugs [\#170](), [\#220]()

* **Review Contributions**
    * PRs Reviewed with comments: [\#77](), [\#131](), [\#135](), [\#138](), [\#142](), [\#207]()

* **Community Contributions**
    * Bugs reported in other teams work:
      * PE-D [1](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/146), [2](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/152), [3](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/162), [4](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/170), [5](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/172), [6](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/181), [7](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/182), [8](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/184), [9](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/185), [10](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/187)
    * [Smoke Test CATcher](https://github.com/GabrielWLM/ped)
