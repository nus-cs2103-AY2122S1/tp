---
layout: page
title: Zhao Peiduo's Project Portfolio Page
---

### Project: ProgrammerError

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) (P|E) is a desktop application which helps CS2100 tutors manage their students’ lab results in a simple and
efficient manner, allowing them to spend less time on administrative processes and more time teaching students.

Given below are my contributions to the project. My contribution to the project code base is also shown in the
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)
.

### Features

#### Added to ability to show a student's lab results on the side panel

* What it does: Allow CS2100 TAs to check a student's lab record on the side panel.
* Justification: This feature provides a convenient way for CS2100 TAs to view a student's lab record on
  ProgrammerError's UI.
* Highlights:
    * Developing the ability to use javaFX to design UI by creating a side panel on the MainWindow.
    * Immediate update of student's information and lab results after every command, which requires a modification to
      all existing commands to keep track of the selected student and his/her lab results.
* Credits: [JavaFX8Docs](https://docs.oracle.com/javase/8/javafx/api/toc.htm) for guidelines on how to use different
  containers.

#### Added colour-coded tags to each student in ProgrammerError

* What it does: Green tags represent the marked labs while red ones represent the unmarked labs
* Justification: This feature allows CS2100 TAs to have a bird view of the marking status of the labs, without a need to
  show student by student.
* Highlights: Immediate update of tags' color after every command requires a record of the status of the labs similar to
  the show feature.
* Credits:

### Other Contributions

#### **Enhancements to existing features**:

* Improve the help feature by adding a table of command syntax to the HelpWindow.
* Add a button bar with buttons for exit, help, download, upload and dashboard features. Enabled keyboard shortcut to be
  more keyboard-friendly.
* Add/Modify test cases for various commands: [\#69](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/69),
* Bug fixes: [\#238](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/238),
  [\#280](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/280),
  [\#351](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/351),
  [\#357](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/357),
  [\#374](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/374),
  [\#381](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/381).

#### **Project management**:

* Utilize the issue tracker for bug reports and new feature proposals.

#### **Documentation**:

* User Guide:
    * Added documentation for the features `delete`
      and `show`: [\#34](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/34)
      , [\#279](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/279).
* Developer Guide:
    * Added use cases for ProgrammerError: [\#46](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/46/files).
    * Added implementation details of the `show` feature: [\#159](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/159).
    * Updated the UML diagrams for ProgrammerError: [\#158](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/159)
      , [\#393](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/393).

#### **Community**:

* PRs reviewed (with non-trivial review comments): [\#194](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/194).
* Contributed to forum discussions: [\#290](https://github.com/nus-cs2103-AY2122S1/forum/issues/290).
* Reported bugs and suggestions for other teams in the class: [\#3](https://github.com/ZhaoPeiduo/ped/issues/3), [\#5](https://github.com/ZhaoPeiduo/ped/issues/5) from PE-D
