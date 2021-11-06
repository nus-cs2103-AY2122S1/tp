---
layout: page
title: Jared's Project Portfolio Page
---
### Project: SafeFor(H)All

SafeFor(H)All is a desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort residents and events
  * What it does: allows the user to sort residents by any field and order without modifying the original order of the list, `view` command also reverts list to original ordering.
  * Justification: This feature improves the product significantly because the hall admin can now view the residents or events in an organised manner. E.g. being able to sort events by date and time allows the admin to view the events chronologically.
  * Highlights: This feature required an understanding of ObservableList and SortedList. It was challenging to ensure that the original ordering was not modified and straightforward one-way sorting implementations had to be avoided. Individual fields also had to be comparable.

* **New Feature**: Added the ability to export the list of email addresses into a csv file
  * What it does: allows the user to have easy access to the list of email addresses of the residents currently displayed in the application.
  * Justification: This feature improves the use case of the product as hall admins can easily contact the residents they wish. They can simply `find` to get a filtered list of residents and `export` to view these email addresses in a csv file. They can then copy and paste these addresses into an email client to send their desired email.
  * Highlights: This feature required the use of a file writer, which involved utilising the opencsv library previously used in the `export` command.
  * Credits: [opencsv](http://opencsv.sourceforge.net/), was used to aid the writing of csv files.

* **New Feature**: Added the ability to view events
  * What it does: allows the user to view the original unfiltered list of events in the `Event` tab
  * Justification: This feature was necessary for hall admins to view the original list after commands such as `find` and `sort` are executed.
  * Credits: The code written was adapted from `view` in the original AB3.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jared&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jaredlhf&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Feature
    * Allow `add` to add residents with more fields such as room, vaccination, faculty and optional fields `FET_DATE` and `COLLECTION_DATE` [\#48](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/48)
  * UI
    * Added tabs to separate `Event` and `Resident` into two tabs [\#76](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/76)
    * Added GUI visualisation of events [\#76](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/76)
    * Relocate command box and result box to bottom of GUI [\#76](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/76)
    * Highlights any past events with grey colored border or red if event contains non-vaccinated residents [\#96](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/96)
    * Displays number of non-vaccinated residents on event card GUI [\#96](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/96)
  * Testing
    * Wrote additional tests for storage component to increase coverage of entire code base by 3% [\#125](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/125)
    * Added testing in `PaserUtil` for new fields and `add`, `sort`, `export` commands [\#48](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/48), [\#234](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/234), [\#146](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/146)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` and `sort` [\#95](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/95), [\#156](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/156), [\#254](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/254)
    * Reformat `Command Format` section: [\#254](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/254)
  * Developer Guide:
    * Added implementation details of the `add` feature [\#99](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/99)
    * Added Use Cases of project [\#41](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/41)
    * Update UML diagrams for `model` and `UI` components [\#107](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/107)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#45](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/45), [\#85](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/85), [\#128](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/128), [\#130](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/130)
  * Reported [bugs and suggestions](https://github.com/jaredlhf/ped/issues) for other teams in the class
