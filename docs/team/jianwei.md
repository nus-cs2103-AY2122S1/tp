---
layout: page
title: Jian Wei's Project Portfolio Page
---

### Project: Academy Directory

Academy Directory is a desktop address book application used for CS1101S tutors to keep track of information related to their students. The user interacts with it using a CLI and mouse, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to keep track of a student's Studio Attendance
    * What it does: Keeps track of whether a student has attended a Studio.
    * Justification: This is a core feature for tutors to be able to keep track of their student's Attendance.
    * Highlights: Easy to use and intuitive since te information required for a tutor to key in is minimal. It is a standalone feature and would have no impact on most other existing features (e.g Add and Edit).

* **New Feature**: Added the ability to keep track of a student's Studio Participation Score
  * What it does: Keeps track of a student's Participation Score in any Studio session.
  * Justification: This is a core feature for tutors to be able to keep track of their student's Participation.
  * Highlights: Currently set to a range of 0 to 500 inclusive to fit the needs of CS1101S, but is easily changeable with future iterations such as an admin console. It is a standalone feature and would have no impact on most other existing features (e.g Add and Edit).

* **New Feature**: Added the ability to sort AcademyDirectory's student list.
  * What it does: Keeps track of a student's Participation Score in any Studio session.
  * Justification: This is a core feature for tutors to be able to keep track of their student's Participation.
  * Highlights: Currently set to a range of 0 to 500 inclusive to fit the needs of CS1101S, but is easily changeable with future iterations such as an admin console. It is a standalone feature and would have no impact on most other existing features (e.g Add and Edit).


* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jianoway&tabRepo=AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

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
