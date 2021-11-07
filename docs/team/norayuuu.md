---
layout: page
title: Deng Huaiyu's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Designed and implemented the timetable feature. [\#104](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/104)
  * What it does: In GUI, allows the user to view all scheduled lessons in one week, with the name and time range of each lesson specified in the timetable.
  * Justification: This feature helps users to better visualize the lessons scheduled and quickly check lessons arranged at a specific time.
  * Highlights: The size of timetable is designed to vary with the time of lessons arranged. The GUI implementation is thus challenging as the size and position of cells inserted and font size for each cell need to be computed with care.
* **New Feature**: Added the ability to add students to existing tuition class. [\#35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35), [#51](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/51), [\#75](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/75)
  * What it does: Allows the user to add existing students to existing tuition class use one or more student names or student indices.
  * Justification: Allows users to flexibly move students into tuition classes
  * Highlights: Many situations need to be considered to show correct message to user, namely non-existing students, students already enrolled in class, valid students not enrolled due to class size limit, and valid students enrolled. [\#67](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/67)
* **New Feature**: Added the ability to add students when adding a new tuition class. [\#35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35)
  * What it does: Allows the user to add students when creating a new tuition class.
  * Justification: Adding students to a new tuition class directly is more convenient.
* **New Feature**: Added the ability to sort the tuition list. [\#80](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/80)
  * What it does: Allows the user sort the tuition list by time and alphabetically order. 
  * Justification: Users can now view tuition classes in their preferred order.
* **Enhancement**: Redesigned `Student` and `TuitionClass` data structure. [\#35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35), [#51](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/51)
  * What it does: Stored tuition classes that each student attend using tuition class ID, and store students in each class using student names.
  * Justification: Previously students and tuition classes were saved as objects under each other. This takes extra space for storage and increases running time.
  * Highlights: Major modification is done for `addclass` and `addtoclass` features as well as `JsonAdaptedTuition` and `JsonAdaptedStudent` files for storage.
<div style="page-break-after: always;"></div>

* **Other Enhancements**: 
  * Enabled adding tags automatically when students are enrolled in a tuition class and disallowed users to add tags manually. [\#37](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/37), [\#114](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/114)
  * Prevents same student with names differ only in number of white spaces and letter cases to be added [\#186](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/186)
* **Testing**: Wrote unit tests for `AddToClassCommand`, `StudentList`, `Timetable`, `SortCommand`, `SortCommandParser`, `TimetableCommand`, `TimetableParser`, and `UniqueTuitionList` classes. Increases code coverage for TutAssistor by 11.94%  ([\#142](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/142), [\#145](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/145)), [\#202](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/202))
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T12&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=NoraYUuu&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).

* **Documentation**:
  * User Guide: 
    * Added documentation for the features `addtoclass`([\#55](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/55), [\#75](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/75)), `sort` ([\#81](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/81), [\#184](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/184)), `timetable` ([\#104](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/104), [\#106](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/106)) and modified `addclass` ([\#75](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/75)).
    * Added dropdown list to table of content. [\#207](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/207)
    * Added annotation for UI and explanation for various components of UI. [\#206](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/206)
  * Developer Guide:
    * Added implementation details of the `addtoclass` feature. [\#137](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/137)
    * Added implementation details of the `timetable` feature. [\#112](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/112)
    * Added UML diagrams for `addtoclass` and `timetable` features. [\#200](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/200)
    * Added use cases for TutAssistor.
* **Contributions to the team-based tasks**
  * Set up the GitHub team org and GitHub team repo.
  * Added [codecov](https://github.com/AY2122S1-CS2103T-T12-4/tp/commit/24f790cc130773b81b1a77e603cbe4d81bdec3d2) and updated CI badge to TutAssistor. [\#208](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/208)
  * Proposed and enhanced data structure: modified the relationship between students and tuition classes.
  * Modified documentation to match the coding standard. [\#112](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/112)
  * Updated user guide Table of Content and added annotation for screenshot. [#206](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/206)
  * Updated sample data of TutAssistor. [\#55](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/55), [\#57](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/57)
* **Review/mentoring contributions**
  * Helped teammate debug GUI. [\#35](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/35)
  * PRs reviewed (with non-trivial review comments): [\#103](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/103).
  * Helped teammate to install PlantUML on Mac.
* **Community**
  * Contributed to forum discussions. (example: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/157))
  * Reported [10 bugs](https://github.com/NoraYUuu/ped/issues) in group W14-2's tP during mock PE.
