---
layout: page
title: Adam's Project Portfolio Page
---

#### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It has about 20 kLoC.

Given below are my contributions to the project. You may refer to my [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=adam-ky&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) to view my code contributions.

#### New Features Implemented

1. Designed the ability to edit remarks with [Remark Editor](https://github.com/AY2122S1-CS2103T-T12-4/tp/blob/master/docs/images/remarkEditor.png) [\#99](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/99), [\#101](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/101)

   * **What it does**: Allows  users to edit or remove remarks from a student or tuition class.
   * **Justification**: Users were not allowed to edit remarks in the original [AB3's implementation for `remark`](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html). In fact, adding remarks will override the previous remarks. However, the remark feature is essential for TutAssistor users who will be using it frequently to keep track of student and class details. This feature helps users to edit remarks freely and conveniently.
   * **Highlights**: The automated GUI testing is not implemented in the latest version, so manual testing is required. Details for manual testing can be found [here](https://ay2122s1-cs2103t-t12-4.github.io/tp/DeveloperGuide.html#editing-remarks).

2. Implemented the `remarkclass` feature [\#44](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/44)

   * **What it does**: allows users to optionally add remarks when creating tuition classes. 
   * **Justification**: users will use the remark feature extensively to keep track of details such as fee payments and homework. As such, it is important to implement the ability to add remarks to tuition classes.
   * **Highlights**: the remarks for class can be edited with the Remark Editor mentioned above as well.
   * **Credits**: this implementation was referenced from [AB3's Add Remark tutorial](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html).

3. Added the ability to open the TutAssistor user guide from the Help window
  
   * **What it does**: allows the user to click the "**Open User Guide**" button to open the user guide in the user's browser automatically.
   * **Justification**: in the AB3's implementation for the help window, the user has to click on the "**Copy URL**" button to retrieve the user guide link. It is tedious for users to manually copy the url link and open the user guide in their browser. It is more convenient to redirect the users to the user guide in their browser automatically when they click the button.
   * **Credits**: this implementation was referenced from [samyipsh’s tP](https://github.com/samyipsh/tp) for CS2103T.

#### Enhancements to existing features:
  * Improved overall look for GUI, such as icons and labels [\#56](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/56)
  * Updated [Help Window](https://github.com/AY2122S1-CS2103T-T12-4/tp/blob/master/docs/images/helpWindow.png) to include command summary and ability to open user guide [\#79](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/79), [\#120](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/120/files)
  * Created `TuitionClassBuilder` and `TypicalClasses` as utility classes for testing `TuitionClass`-related commands [\#86](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/86)

#### Documentation:

**User Guide**:
  * Ensured consistent formatting throughout the whole UG, including the table of contents, navigational links, and size of screenshots [\#113](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/113/files), [\#129](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/129/files)
  * Designed the [TutAssistor logo](https://github.com/AY2122S1-CS2103T-T12-4/tp/blob/master/docs/images/ta_logo_cropped_white_bg.png) [\#133](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/133/files)
  * Added relevant screenshots, including the UI mock up, Help Window, and Student Info Page [\#121](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/121), [\#28](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/28), [\#129](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/129)
  * Documented `remark` and `remarkclass` features [\#54](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/54/files)
    
**Developer Guide**:
  * Documented implementation details of the `remark` feature [\#113](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/113/files), [\#140](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/140)
  * Created [sequence diagram](https://github.com/AY2122S1-CS2103T-T12-4/tp/blob/master/docs/images/RemarkCommandSequenceDiagram.png) for `remark` implementation [\#122](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/122)
  * Prescribed manual testing instructions for `exit`, `remark`, and `help` features [\#204](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/204)

#### Project management:
  * Managed and assigned 1.2 issues to track project tasks
  * Facilitated team direction for week 7 tP requirements

#### Community:
  * Reported [10 bugs](https://github.com/adam-ky/ped/issues) for team T10-3 during the mock Practical Exam (PE)
  * PRs reviewed (with non-trivial review comments): [\#95](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/95), [\#111](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/111), [\#136](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/136)

