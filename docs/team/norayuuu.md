---
layout: page
title: Deng Huaiyu's Project Portfolio Page
---

### Project: AddressBook Level 3

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It has about 20 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

Access my [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T12&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=NoraYUuu&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) to view the code that I have contributed.

#### Enhancements implemented

* **New Feature**: Designed and implemented the timetable feature for user to view a timetable. Created corresponding `TimetableInfoPage`, `TimetableCommand`, `TimetableParser`, and `Timetable` classes. [\#104](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/104)
  * What it does: Allows the user to view all scheduled lessons in one week, with the name and time range of each lesson specified in the timetable.
  * Justification: Previously, user needs to go through the whole tuition list to check what the lessons are or when the lessons are scheduled. With the timetable feature, users can see all lessons at one glance and easily find lessons scheduled at a specific time.
  * Highlights: The size of timetable varies with the earliest starting and latest ending time of lessons. The implementation of GUI is thus challenging as the number and position of cells inserted to the timetable need to be computed with care. The font size for lesson details also varies according to lesson length. Besides, `Timetable` is refactored after `TimeSlot` was changed.

* **New Feature**: Added the ability to add students to existing tuition class and created corresponding `AddToClassCommand` and `AddToClassCommandParser` classes. [\#75](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/75)
  * What it does: Allows the user to add existing students to existing tuition class use both student names and student indices. Moreover, users can choose to add multiple students using names or indices at once.
  * Justification: This feature gives user more flexibility to move students into tuition classes. Adding multiple students together is also more efficient for users.
  * Highlights: There are many situations to consider, namely non-existing students, students already enrolled in class, valid students not enrolled due to class size limit, and valid students enrolled successfully. Efforts are put in to categorize students properly and ensure correct message is shown to user. [\#67](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/67)

* **New Feature**: Added the ability to add students when adding a new tuition class. Modified `AddClassCommand` and `AddClassCommandParser`. [\#35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35)
  * What it does: Allows the user to add students when creating a new tuition class.
  * Justification: It is tedious for users to first create the tuition class and add in students later. Adding students to the tuition class directly is more convenient to user.

* **New Feature**: Added the ability to sort the tuition list and created corresponding `SortCommand` and `SortCommandParser` classes. [\#80](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/80)
  * What it does: Allows the user sort the tuition list according to time order, alphabetically ascending order, and alphabetically descending order. 
  * Justification: This feature allows user to view tuition classes in their preferred order and makes it easier for them to find a certain tuition class.

* **Enhancement**: Designed and modified existing `Student` and `TuitionClass` data structure. `JsonAdaptedTuition` and `JsonAdaptedStudent` are modified accordingly. [\#35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35), [#51](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/51)
  * What it does: Stored tuition classes that each student attend using tuition class ID, and store students in each class using student names.
  * Justification: Previously students are stored as `Student` object under each `TuitionClass` and each tuition class a student attend is stored as `TuitionClass` object under `Student`. This takes extra space for storage and increases running time as updates are needed at several places when commands are executed.
  * Highlights: This requires major modification of `AddClass` and `AddToClass` features as well as `JsonAdaptedTuition` and `JsonAdaptedStudent` files for storage.

* **Enhancement**: Disallowed users to add tags manually when creating new student while enabling adding tags automatically when students are enrolled in a tuition class. Modified `AddCommandParser` and `Tag` classes. [\#114](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/114), [\#37](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/37)
  * What it does: Reserves `Tag` for showing classes each student attend and prevents users from adding their own tags.
  * Justification: Makes clear distinguish between function of `Tag` and `Remark`. Additional notes the user wants to add to a student should be added using `Remark` feature. And `Tag` only serves the purpose of showing the name and time of tuition classes each student enrolled in. 
  * Highlights: The Java regular expression (regex) of `Tag` need to be changed to allow adding `TuitionClass` as tags.

* **Enhancement**: Prevents same student to be added [\#186](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/186)
  * What it does: Names with different number of multiple whitespaces in between or differ in letter cases are considered the same.
  * Justification: Trivial difference of white spaces and letter cases should not make the name different. Most of the cases, these differences are resulted from mistyping or different habits of typing.

* **Testing**: Created tests for:
  * `AddToClassCommand` (coverage from 0% to +89.43%), `StudentList` (coverage increased by 25.00%). Increased coverage for TutAssistor by +6.58%. [\#142](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/142)
  * `Timetable` (coverage from 0% to +74.19%) increased coverage for TutAssistor by +2.41%. [\#202](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/202)
  * `SortCommand` (coverage from 0% to +86.66%), `SortCommandParser` (coverage from 0% to +100.00%), `TimetableCommand` (coverage from 0% to +100.00%), `TimetableParser` (coverage from 0% to +100.00%), `UniqueTuitionList` (coverage increased by +16.17%). Increased coverage for TutAssistor by 2.95%. [\#145](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/145)

#### Contributions to Documentation
* **User Guide**:
  * Added documentation for the features `addtoclass`([\#55](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/55), [\#75](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/75)), `sort` ([\#81](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/81), [\#184](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/184)), `timetable` ([\#104](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/104), [\#106](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/106)) and modified `addclass` ([\#75](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/75)).
  * Added dropdown list to table of content. [\#207](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/207)
  * Added annotation for UI(user interface) and explanation for various components of UI. [\#206](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/206)
  * Updated screenshot in index page. [\#206](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/206)

* **Developer Guide**:
  * Added implementation details of the `addtoclass` feature. [\#137](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/137)
  * Added implementation details of the `timetable` feature. [\#112](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/112)
  * Added UML diagrams for `addtoclass` and `timetable` features. [\#200](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/200)
  * Added use cases for TutAssistor.

#### Contributions to the team-based tasks
* Set up the GitHub team org.
* Set up the GitHub team repo.
* Added [codecov](https://github.com/AY2122S1-CS2103T-T12-4/tp/commit/24f790cc130773b81b1a77e603cbe4d81bdec3d2) and updated CI badge to TutAssistor. [\#208](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/208)
* Proposed and enhanced data structure: modified the relationship between students and tuition classes.
* Modified documentation to match the coding standard. [\#112](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/112)
* Updated user guide Table of Content and added annotation of UI component. [#206](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/206), [\#207](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/207)
* Updated sample data of TutAssistor. [\#55](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/55), [\#57](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/57)

#### Review/mentoring contributions
* Helped teammate debug GUI. [\35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35)
* PRs reviewed (with non-trivial review comments): [\#103](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/103).
* Helped teammate clarify how to install PlantUML on Mac.


#### Contributions beyond the project team
* Contributed to forum discussions. ([1](https://github.com/nus-cs2103-AY2122S1/forum/issues/157))
* Reported [10 bugs](https://github.com/NoraYUuu/ped/issues) in group W14-2's tP during mock PE.
