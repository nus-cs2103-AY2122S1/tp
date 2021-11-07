---
layout: page
title: Shivam Tiwari's Project Portfolio Page
---

### Project: AddressBook Level 3

*ComputingConnection* is for entrepreneurial students in NUS Computing who want to **keep track of other students’ skill sets so that they can easily look for suitable people to work with on future projects.** *ComputingConnection* is optimized for Command Line Interface (CLI) over a Graphical User Interface (GUI) for efficiency with a keyboard.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter contacts based on the tags attached to them for e.g. skills, faculty, major etc.
    * Main implementation: (Pull request [\#73](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/73))
    * What it does: allows the user to filter the contacts in the address book based on the tags attached to them. The tags include their skills, faculty, major, language, framework and some miscellaneous tags. 
    * Justification: This feature improves the product significantly because the user can now find contacts having similar tags in just one command.
    * Highlights: This feature doesn't affect the existing commands and is an addon to the find feature. Hence, it was easier to implement comparatively but it required to be altered a couple of times in order to add more fields. Although it does affect the list of contacts in view on the screen.

* **New Feature**: Added the ability to add a new organisation to the address book.
    * Main implementation: (Pull request [\#88](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/88))
    * What it does: allows the user to create a new organisation in the address book with a name and an email address.
    * Justification: This feature improves the product significantly because the user may want to remember an organisation or an institute and its email address for future use. Moreover, the user can also store contacts in relation to the organisation to keep the point of contact of the user safe for future purposes.
    * Highlights: This feature was particularly hard to implement because this is basically a new address book running simultaneously with the contact list. It needed a new storage form and a new GUI.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103T-W10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Shivlock221b&tabRepo=AY2122S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Head of scheduling and deliverables - worked on making sure that we were on time for every milestone and release.

* **Enhancements to existing features**:
    * Updated the GUI to accommodate both contact list and organisation list.
* **Documentation**:
  * User Guide:
      * Added documentation for the `delete` feature [\#35](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/35)
      * Added documentation for the `Add orgaisation` feature [\#68](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/68)
      * Did cosmetic tweaks to existing documentation of features like `deleteorg` and enhanced the `value proposition`: [\#113](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/113)
      * Added `organisation specific commands` summary table [\#113](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/113)
  * Developer Guide:
      * Added use cases of the `delete` feature.
      * Added use cases of the `filter` feature.
      * Added use cases of the `addorg` feature.
      * Added implementation details of `filter` command

* **Community**:
    * PRs reviewed: [\#187](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/187), [\#186](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/186), [\#185](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/185), [\#172](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/172), [\#117](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/117), [\#99](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/99), [\#86](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/86), [\#80](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/80)
    * Reported bugs and suggestions for other teams in the class (offline)
    * I helped implement the GUI for the app to accommodate Organisations which was used by the team.
