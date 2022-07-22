---
layout: page
title: Ammar's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It has about 20 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

Access my [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=amzhy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=amzhy&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) to view the code that I have contributed.

#### Enhancements implemented
* **Create edit features**: Designed and created `EditCommand`, `EditClassCommand` and their related `Parser` classes. (Pull request [\#125](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/125))
  * What it does: Allows user to edit details of a particular student or tuition class in **TutAssistor**.
  * Justification:  This improves the user experience as users do not have to delete and recreate entire students or classes, in order to change a few details.
  * Highlights:
    * Changing the `Timeslot` of a class has to be considered carefully, as it cannot overlap with any timeslot of existing classes.

* **Create delete features**: Designed and created `DeleteCommand`, `DeleteClassCommand` and their corresponding `Parser` classes. (Pull requests [\#46](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/46))
  * What it does: Allows user to delete a student or tuition classes in **TutAssistor**.
  * Justification:  This enables the user to remove entire records pertaining to multiple students or tuition classes.
  * Highlights:
    * Duplicate indices in user inputs have to be removed to ensure that only one student/class is deleted at a particular index.
    * The order of deletion has to be considered carefully by sorting the indices before deletion, as the 
list of students/classes adapts while students/classes are being deleted respectively.
  
* **Implement `RemoveStudent` feature**: Implemented `RemoveStudentCommand` and `RemoveStudentCommandParser`. (Pull request [\#58](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/58))
  * What it does: Allows user to remove existing students from a tuition class in **TutAssistor**.
  * Justification:  This allows the user to update class enrollment promptly whenever students drop out of a tuition class.
  * Highlights: 
    * Efforts are taken to ensure that students who are not enrolled in the class are not removed and this is conveyed to users.
  
* **Refactor Timeslot class**: Refactor Timeslot class to use Java `LocalTime` and `Date` for class timings instead of`String`. (Pull request [\#125](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/125))
  * What it does: Parses and saves the timing of any tuition class as `Date` and `LocalTime` to compare timeslots across classes. 
  * Justification: Previously, the time slot was a `String` which created scheduling conflicts between classes and classes to be created at invalid timings. (E.g classes could span across days). Following the refactoring of the `Timeslot`, classes were created only at valid timings and the timetable conflicts were resolved more easily 
using the existing Java API.
  * Highlights:
    * This is a major refactor as the `Timeslot` is crucial for the logic of `Timetable` and general management of all the tuition classes. 

* **Refactor some commands to use multiple indices**: Refactor `DeleteCommand`, `DeleteClassCommand` and `RemoveStudentCommand` to use multiple student or class indices as arguments. (Pull request [\#65](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/65))
  * What it does: Allows user to delete multiple students or tuition classes, as well as remove multiple students from a class.
  * Justification: Improve efficiency so that the user is able to delete multiple students or classes at once instead of 
repeating similar commands multiple times.

* **Testing**: Create tests for the `EditClassCommandParser`, `EditCommandParser`, `DeleteCommandParser`, `DeleteClassCommandParser` and `RemoveStudentCommandParser` as well as their related classes to increase coverage. (Pull Request [\#216](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/216), [\#230](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/230))

#### Contributions to the User Guide
* Added to the FAQ and Introduction, formatted figures and enabled quicklinks for the command summary. (Pull Request [\#139](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/139))
* Added implementation details for `EditClassCommand`, `EditCommand`, `DeleteClassCommand`. (Pull Request [\#217](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/217))
* Fix some documentation bugs from PE-D. (Pull Request [\#187](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/187))
* Added screenshots for the `DeleteCommand` and `EditClassCommand`. (Pull Request [\#217](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/217), [\#233](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/233))

<br>

#### Contributions to the Developer Guide
* Added the implementation details of `EditClassCommand`. (Pull Request [\#135](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/135))
* Added UML diagrams for `RemoveStudent` and`DeleteClassCommand`. features (Pull Request [\#209](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/209))
* Added implementation details of `EditCommand` and `DeleteCommand`. (Pull Request [\#217](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/217))
* Added use cases for TutAssistor.


#### Contributions to the team-based tasks
* Proposed the idea for TutAssistor.
* Proposed the feature to enable command shortcuts for long commands.
* Proposed the idea of input history navigation feature to improve the design of TutAssistor to model a CLI application.
* Enabled status checks and approvals for all PRs to prevent CI failures for the team's repository.

#### Review/mentoring contributions
* PRs reviewed: [\#198](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/198)

#### Contributions beyond the project team
* Reported [20 bugs](https://github.com/amzhy/ped/issues) in group F13-2's tP during mock PE.

