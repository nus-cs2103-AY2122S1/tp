---
layout: page
title: Justin Ee's Project Portfolio Page
---

### Project: TuitiONE

TuitiONE is a Command Line Interface (CLI) based application that aims to simplify the work of Customer Servicing Officers (CSO) in a tuition centre. It is written in Java and has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter student and lesson lists.
  * What it does: Allows the user to filter the student and lesson lists if a grade condition is specified. If a subject is specified, the lesson list will also filter based on that. It allows filtering by either grade, subject or both together.
  * Justification: This allows the user to see only relevant students and lessons based on the specified grade or subject conditions, instead of having to scroll a lot.
  * Highlights: This enhancement requires the creating more predicate classes to filter the lists, as well as creating a parser and a 'Grade' class with relevant checks to ensure a valid grade is inputted. No checks on subject as there can be a wide variety of subjects.

* **New Feature**: Added the ability to add on remarks to students, and to delete individual remarks.
  * What it does: Allows the user to tag additional remarks to students on top of existing ones, and to also delete specific remarks that they do not need anymore.
  * Justification: The previous implementation needed users to re-type existing remarks if they wanted to keep existing remarks and add new ones. It also did not allow users to delete individual remarks. This feature saves the user time if they want to add or delete remarks, and makes it more convenient for them.
  * Highlights: This required editing existing classes and methods to check for remarks to delete and add. It required analysing the target user and re-designing the feature to better suit their needs. It required considering various new checks on the inputs as well.

* **New Feature**: Added feature where student list is sorted by student names, and lesson list is sorted by grade.
  * What it does: Student list is now displayed in sorted ascending order by student names, and lesson list is sorted by grade, from 'P1' to 'S5'.
  * Justification: This allows users to view a more organised list of students and lessons, making look ups more efficient.
  * Highlights: This required deep analysis of the code to ensure the lists are always sorted regardless of what commands are invoked.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=f13&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=eezj35&tabRepo=AY2122S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed release `v1.4` on GitHub
  * Scheduled and created team meetings
  * Created issues, tags and milestones on Github

* **Enhancements to existing features**:
  * Enhanced edit feature by allowing editing of student's grade. This required ensuring different components interacted with each other correctly depending on what field was to be edited, and required modifying existing classes (Pull requests [\#148](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/148), [\#137](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/137))
  * Updated the GUI (Pull requests [\#286](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/286), [\#282](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/282))
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#94](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/94), [\#181](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/181))
  * Updated validity checks and constraints for existing features (Pull requests [\#282](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/282), [\#203](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/203), [\#187](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/187))
  * Refactored 'Student' class from 'Person' class, and updated related commands (Pull requests [\#65](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/65), [\#29](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/29))
  * Implemented base code for delete lesson feature, which was later further refined (Pull requests [\#94](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/94))
  * Updated edit feature to ensure components that have associations with each other work as expected together. (Pull requests [\#148](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/148))

* **Documentation**:
  * User Guide:
    * Updated formatting ([\#172](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/172))
    * Added upcoming features section ([\#299](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/299/files))
    * Add documentation for `edit`, `add`, `delete` and `roster` feature ([\#188](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/188), [\#78](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/78/files), [\#163](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/163/files))
    * Update command summary ([\#88](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/88/files), [\#163](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/163/files))
  * Developer Guide:
    * Updated formatting [\#172](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/172)
    * Add use case for `find` [\#40](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/40)
    * Add details for `filter` implementation and use case [\#152](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/152)
    * Add use case and user story for `roster` [\#163](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/163)
    * Update model class diagram [\#168](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/168)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#287](https://github.com/AY2122S1-CS2103T-F13-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me), [\#160](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/160), [\#153](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/153), [\#151](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/151), [\#140](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/140)

* **Tools**:
  * Utilize SceneBuilder to build GUI Layout
