---
layout: page
title: Adam's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to open the TutAssistor user guide from the Help window.
  * What it does: in the AB3's implementation for Help window, the user has to click on the "Copy URL" button to retrieve the user guide link. Instead, the new feature allows the user to click the button to open the user guide in the user's browser automatically.
  * Justification: it is tedious for users to manually copy the url link and open the user guide in the browser. It will be a lot more convenient to redirect the users to the user guide in their browser when they click the button.
  * Credits: this implementation was referenced from [samyipsh’s tP](https://github.com/samyipsh/tp) for CS2103T.

* **New Feature**: Added the ability to add remarks to tuition classes.
  * What it does: allows the user to optionally add remarks when creating tuition classes. 
  * Justification: users will use the remark feature extensively to keep track of details such as fee payments and homework. As such, it is important to implement the ability to add remarks to tuition classes as well.
  * Credits: this implementation was referenced from [AB3's Add Remark tutorial](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html).

* **New Feature**: Added the ability to edit remarks with Remark Editor. [\#99](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/99), [\#101](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/101)
  * What it does: allows the user to edit or remove remarks from a student or tuition class.
  * Justification: users are not allowed to edit remarks in the original [AB3's implementation for remarks](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html). In fact, adding remarks will override the previous remarks. However, the remarks is an essential feature for TutAssistor as users will be using it frequently to keep track of student or class details. This feature helps users to edit remarks freely and conveniently.
  * Highlights: automated GUI testing is not implemented yet, so manual testing is required. Details for manual testing can be found [here](https://ay2122s1-cs2103t-t12-4.github.io/tp/DeveloperGuide.html#editing-remarks).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=adam-ky&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed and assigned 1.2 issues to track project tasks
  * Facilitated team direction for week 7 tP requirements

* **Enhancements to existing features**:
  * Updated icons and labels for GUI [\#56](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/56)
  * Updated Help Window to include command summary [\#79](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/79), [\#120](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/120/files)
  * Added sample tuition class for testing

* **Documentation**:
  * User Guide:
    * Updated relevant screenshots used. [\#121](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/121), [\#28](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/28), [\#129](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/129)
    * Added documentation for the features `remark` and `remarkclass` [\#54](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/54/files)
    * Added TutAssistor logo [\#133](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/133/files)
    * Polished formatting and language used throughout the UG. [\#113](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/113/files), [\#129](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/129/files)
  * Developer Guide:
    * Added implementation details of the `remark` feature. [\#113](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/113/files), [\#140](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/140)
    * Added manual testing instructions [\#204](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/204)

* **Community**:
  * Reported 10 bugs for a [team](https://github.com/AY2122S1-CS2103T-T10-3/tp) during the Practical Exam dry run 
  * PRs reviewed (with non-trivial review comments): [\#95](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/95), [\#111](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/111), [\#136](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/136)

