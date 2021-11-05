---
layout: page
title: Adam's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to open the TutAssistor user guide from the Help window.
  * What it does: in the AB3's implementation for Help window, the user has to click on the "Copy URL" button to retrieve the user guide link. Instead, the new feature allows the user to click the button to open the user guide in the user's browser automatically.
  * Credits: this implementation was referenced from [samyipsh’s tP](https://github.com/samyipsh/tp) for CS2103T.

* **New Feature**: Added the ability to add remarks to Tuition Classes.
  * What it does: allows the user to optionally add remarks when creating tuition classes. 
  * Credits: this implementation was referenced from [AB3's Add Remark tutorial](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html).

* **New Feature**: Added the ability to edit remarks with Remark Editor.
  * What it does: allows the user to edit or remove remarks from a student or tuition class.
  * Justification: users are not allowed to edit remarks in the original AB3's implementation for [remarks](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html). In fact, adding remarks will override the previous remarks. However, the remarks is an essential feature for TutAssistor as users will be using it frequently to keep track of student or class details. This feature helps users to edit remarks freely and conveniently.
  * Highlights: 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=adam-ky&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
