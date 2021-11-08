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
  * Highlights: This feature required a split screen feature in the UI, using 2 panels to split up the main and additional information.

* **New Feature**: Added a mass operations function to the `edit` resident feature. 
    * What it does: This feature allows the user to edit the details of multiple residents in a single command, by inputting the residents' respective indexes.
    * Justification: This feature improves the product significantly because a user might want to update multiple residents' details, such as their vaccination statuses or last FET dates, all in a single command.

* **New Feature**: Added a mass operations function to the `delete` resident feature.
  * What it does: This feature allows the user to delete multiple residents in a single command, by inputting the residents' respective indexes.
  * Justification: This feature improves the product significantly because a user might want to delete multiple residents at a time, instead of inputting a new command line every time the user wants to delete a single resident.

* **New Feature**: Added the `edit` feature for events.
  * What it does: This feature allows the user to edit event details.
  * Justification: This feature improves the product since the user might want to update certain fields in an event given any changes.
  * Credits: The code written was adapted from the original AB3.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=rebeccalaujx&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Wrote tests for the AddEvent, EditEvent, DeleteEvent, EditPerson, and Switch commands and parsers (PRs: [\#231](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/231), [\#81](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/81))
    * Modified the help guide to include a summary of the command formats, and a hyperlink to our user guide for more information (PRs: [\#141](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/141), [\#246](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/246))
    * Designed the UI mockup for SafeFor(H)All using [Figma](https://www.figma.com/file/Xt0MjUdFjvB438sHOwurRm/safeforhall?node-id=0%3A1)

* **Project management**:
    * Opened issues that addressed bugs (Issues: [\#233](https://github.com/AY2122S1-CS2103T-T15-4/tp/issues/252), [\#242](https://github.com/AY2122S1-CS2103T-T15-4/tp/issues/242), [\#86](https://github.com/AY2122S1-CS2103T-T15-4/tp/issues/86))
    * Reviewed and approved team members' PRs for merging (more details in the **Community** section)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit` and `delete` for both Resident and Event models (PRs: [\#19](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/19), [\#31](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/31), [\#233](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/233))
        * Fixed the bugs that were reported in the PE-D (PRs: [\#233](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/233))
    * Developer Guide:
        * Added and categorised user stories by epics (PRs: [\#36](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/36), [\#101](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/101))
        * Added implementation details of the `edit` and `view` features (PRs: [\#134](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/134))

* **Community**:
    * PRs reviewed (with non-trivial review comments): (PRs: [\#127](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/127), [\#132](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/132), [\#140](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/140), [\#80](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/80))
    
