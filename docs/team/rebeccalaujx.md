---
layout: page
title: Rebecca's Project Portfolio Page
---
### Project: SafeFor(H)All

SafeFor(H)All is a desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Modify `view` command to view more Resident/ Event details.
  * What it does: This feature allows the user to use `view` to see additional the Resident/ Event details in a sidebar when the user inputs the index of the specified event. Otherwise, it can be used to view the entire list of Residents/ Events when its index parameters are removed.
  * Justification: This feature improves the product significantly because having all the information in the main panel takes up a lot of space, making the app less user-friendly. This feature allows the user to retrieve the less commonly required information only if he/ she wants to.

* **New Feature**: Added a mass operations function to the `edit` resident feature. 
    * What it does: This feature allows the user to edit the details of multiple residents in a single command, by inputting the residents' respective indexes.
    * Justification: This feature improves the product significantly because a user might want to update multiple residents' details, such as their vaccination statuses or last FET dates, all in a single command.

* **New Feature**: Added a mass operations function to the `delete` resident feature.
  * What it does: This feature allows the user to delete multiple residents in a single command, by inputting the residents' respective indexes.
  * Justification: This feature improves the product significantly because a user might want to delete multiple residents at a time, instead of inputting a new command line every time the user wants to delete a single resident.

* **New Feature**: Added the `edit` feature for events.
  * What it does: This feature allows the user to edit event details.
  * Justification: This feature improves the product since the user might want to update certain fields in an event given any changes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=rebeccalaujx&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Wrote tests for the AddEvent, EditEvent, DeleteEvent, and Switch commands (Pull requests [\#231](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/231), [\#81](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/81))
    * Modified the help guide to include a summary of the command formats, and a hyperlink to our user guide for more information (Pull requests [\#141](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/141), [\#246](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/246))
    * Designed the UI of the app

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit` and `delete` for both Resident and Event (Pull requests [\#19](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/19), [\#31](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/31), [\#233](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/233))
        * Fixed the bugs that were reported in the PE-D [\#233](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/233)
    * Developer Guide:
        * Added and categorised user stories by epics (Pull requests [\#36](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/36), [\#101](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/101))
        * Added implementation details of the `edit` and `view` features [\#134](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/134)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#127](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/127), [\#132](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/132), [\#140](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/140), [\#80](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/80))
    
