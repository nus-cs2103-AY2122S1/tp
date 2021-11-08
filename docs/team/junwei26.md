---
layout: page
title: Jun Wei's Project Portfolio Page
---

### Project: CSBook

CSBook is a desktop application for Computer Science (CS) Teaching Assistants (TAs) to manage their students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.
Given below are my contributions to the project.

* **New Feature**: Added the ability to view all students in a particular group.
    * What it does: allows the user to view all the students that are in a group.
    * Justification: This is allows our user to quickly find the details of all students in a group.
    * Highlights: Implementation of this command was challenging as I could not manipulate the student list directly. I had to implement a new predicate class to manipulate the student list indirectly through `Model#updateFilteredStudentList`.

* **New Feature**: Add ability for CSBook to save group data.
  * Highlight: Deciding how we store the data was challenging. Since the user could also edit the data file, data validation was also important, especially to make sure that each student had a valid group.

* **New Feature**: Add ability for users to encrypt or decrypt their data

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=junwei26&tabRepo=AY2122S1-CS2103T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Set up Team repo
  * Managed triaging of issues for `v1.4`.

* **Enhancements to existing features**:
    * Enhanced the GUI by adding pop ups for result messages, made GUI responsive, restructured main view of application (Pull request [\#114](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/114))
    * Added test cases for `AddGroupCommand`, `AddGroupCommandParser`, `ChangeGroupCommand`, `ChangeGroupCommandParser` , `DecryptCommand` and
      `EncryptCommand`(Pull request [\#204](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/204))

* **Documentation**:
    * User Guide:
        * Added documentation for the `find`, `delete`, `clear` and `viewgroup` features. (Pull request [\#35](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/35))
    * Developer Guide:
        * Added implementation details and UML diagrams for loading data in the Implementations section. (Pull request [\#91](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/91))

* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/junwei26/ped/issues/6), [2](https://github.com/junwei26/ped/issues/1), [3](https://github.com/junwei26/ped/issues/9))
