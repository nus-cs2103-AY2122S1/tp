---
layout: page
title: Timothy's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. 
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). 
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It has about 20 kLoC.

Given below are my contributions to the project.

#### Code contributed
* Access my 
[Reposense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=timiditi&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
to view the code I have contributed.

#### Enhancements implemented

* **New feature:** Dedicated multi-use information display panel (Pull request [#42](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/42))
  * **What it does:** Adds a dedicated panel in GUI to display information.
  * **Justification:** 
  * **Highlights:** The implementation of this GUI element allowed for future expansions of features that involve
    users pulling up detailed information on command. 
    
    Features added later in the project that utilized the display panel include the 
    [View Student/Class Commands](https://ay2122s1-cs2103t-t12-4.github.io/tp/UserGuide.html#32-view-studenttuition-class), 
    [View Timetable Command](https://ay2122s1-cs2103t-t12-4.github.io/tp/UserGuide.html#310-view-timetable-timetable--tt) 
    and [View Today's Classes Command](https://ay2122s1-cs2103t-t12-4.github.io/tp/UserGuide.html#311-view-todays-classes-today--td).
* **New Feature:** View student/class command ([#42](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/42))
  * **What it does:** Allows users to view detailed information on students and tuition classes in info panel via the `student` and `class` commands.
  * **Justification:** Full information about a student or class can be long, especially if a user has added remarks to them.
    By enabling users to view detailed information about students and tuition classes on command,
    this feature allows less information about students and tuition to be displayed on the student and tuition class lists displayed by default on the GUI.
    Hence, users to have a less cluttered overview of their student and class lists, only accessing more detailed information when desired.
    Furthermore, this allows more students and classes to be displayed in their respective lists at a time.
* **New Feature:** Command input history navigation ([#111](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/111))
  * **What it does:** Allows users to access and navigate through previously entered inputs using the `↑` `↓` arrow keys.
  * **Justification:** TutAssistor uses CLI, in which users may want to access previously entered inputs to redo commands with some modifications, without having to retype an entire long command.
  * **Acknowledgement:** This feature was inspired by a similar feature in [YaleChen299's ip](https://github.com/yalechen299/ip) for CS2103T,
    though its implementation in this project is new.
* **Enhancement:** Command shortcuts ([#111](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/111))
  * **What it does:** Adds shorter alternatives to command keywords.
  * **Justification:** While the full command keywords such as `addtoclass` or `timetable` may be more intuitive to understand, 
  shortcuts such as `atc` and `tt` can greatly speed up the workflow of users more familiar with the software and CLI.
    
#### Contributions to team-based tasks
* Managed scheduling and agendas for weekly team meetings.
* Refactored `Person` related classes in codebase to `Student` classes for clarity. ([#123](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/123))
* Managed all [software releases](https://github.com/AY2122S1-CS2103T-T12-4/tp/releases).

#### Documentation
* **User Guide:**
  * Added guide for `find` and `list` commands. ([#87](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/87))
  * Added information on command shortcuts. ([#111](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/111))
  * Added command format information guide. ([#117](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/117))
  * Adjusted overall grammar, formatting and clarity. ([#117](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/117))
* **Developer Guide:**
  * Added user stories and product uses cases. ([commit 341b3ef](https://github.com/AY2122S1-CS2103T-T12-4/tp/commit/341b3ef390b8fc7286c2f0bce3ac0a9662886ab6#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b))
  * Added implementation details for CLI History Navigation. ([#224](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/224))
  
#### Contributions beyond project team
* Reported [7 bugs and issues](https://github.com/timiditi/ped/issues/) with non-trivial comments for other team's tP.
