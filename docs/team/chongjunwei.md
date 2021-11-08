---
layout: page
title: Jun Wei's Project Portfolio Page
---
### Project: SafeFor(H)All

SafeFor(H)All is a desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **Key Contribution**: Created an `Event` model [\#63](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/63)
  * What it does: Creates a backbone for which events can be added to the address book along with the associated functionalities, similar to the preexisting `Person` model
  * Justification: This feature improves the product because the `Event` model is one of the core selling points of our product.
  * Credits: Some parts of the model were refactored from the preexisting `Person` model
  
* **New Feature**: Added `add` command for events [\#63](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/63)
  * What it does: Allows the user to add events
  * Justification: This feature improves the product because the user can utilise the CLI to add events with ease, similar to the add person command in the `Person` model

* **New Feature**: Added `find` command for events [\#135](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/135)
  * What it does: Allows the user to find events matching the given parameter(s) (eg `capacity = 5`)
  * Justification: This feature improves the product significantly because the user can find the desired events matching the given
  parameter(s) with ease

* **New Feature**: Added `delete` command for events [\#88](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/88)
  * What it does: Allows the user to delete events
  * Justification: This feature improves the product because the user can utilise the CLI to delete unwanted events
  * Credit: This command was created by adapting the `delete` command in the `Person` model.
    
* **New Feature**: Added `view` command for residents [\#49](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/49)
  * What it does: Allows the user to view a numbered list of all the residents in the address book
  * Justification: This feature improves the product because the user can utilise the CLI to quickly view all the residents in the address book

* **New Feature**: Added `switch` command [\#136](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/136)
  * What it does: Allows the user to switch between Event and Resident tabs
  * Justification: This feature improves the product because the user can utilise the CLI to quickly switch tabs
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=chongjunwei&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Added `time` field for events [\#127](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/127)
    * Wrote tests for Event model [\#103](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/103)
  
* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/72)
        * Fixed bugs that were reported in the PE-D [\#237](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/237)
        * Added an introduction on the app interface [\#261](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/261)
    * Developer Guide:
        * Added implementation details of the `view` and `delete` event features [\#106](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/106), [\#131](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/131)
        * Formatting [\#131](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/131)
        * Update the glossary [\#43](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/43), [\#44](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/44)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#85](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/85), [\#129](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/129)
