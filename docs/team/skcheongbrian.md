---
layout: page
title: Cheong Sze Kai, Brian's Project Portfolio Page
---

### Project: CSBook

CSBook is a desktop application for Computer Science (CS) Teaching Assistants (TAs) to manage their students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.
Given below are my contributions to the project.

* **New Feature**: Added groups and descriptions of the groups to the project.
  * What it does: Allows the user to create groups with descriptions so that students can be grouped together in groups.
  * Justification: Our product focuses on grouping students in groups so that it is more organised and easier for the user to filter, find and manage their students.
  * Highlights: Implementation of this was tricky as I had to figure out what relevant information constitutes a group, as well as what data structure to store the references of the students in the group. I had to decide between storing the whole student in the group or just referencing their names. Furthermore I had to decide whether it should be only top down, where only the group can see its students or whether students should also have a reference to their group. In the end to allow easier management and retrieval of information, each student also stored a reference to their group which later on changed to a reference to the name of the group.

* **New Feature**: Added a notes feature.
  * What it does: Allows the user to add notes about a particular student.
  * Justification: As a user I would like to easily take notes on my students without having to swap over to an external application to do so.

* **New Feature**: Change the group which a student belongs in.
  * What it does: Allows the user to change the group in which a student belongs in.
  * Justification: As a user I would want to be able to change the group a student belongs in, because I may have added them to the wrong group or would like to assign them to a group with a different purpose.
  * Highlights: Implementation of this was challenging because of how student contains the group name and the group only contains the student name, it was challenging to retrieve the student object and the group object. it was essential to update the old group, by removing the student, the new group, by adding the student, as well the student themselves by changing its group name reference.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=SKCheongBrian&tabRepo=AY2122S1-CS2103T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide:
    * Added documentation for the `note` and `changegroup` feature. [\#119](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/119)
    * Added documentation for the `addgroup` feature. [\#92](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/92)
  * Developer Guide:
    * Updated UML diagrams to reflect the new model of the project. [\#92](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/92)
    
* **Community**:
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/SKCheongBrian/ped/issues/4), [2](https://github.com/SKCheongBrian/ped/issues/1), [3](https://github.com/SKCheongBrian/ped/issues/8))
