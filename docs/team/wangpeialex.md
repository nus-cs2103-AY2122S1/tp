---
layout: page 
title: Wang Pei's Project Portfolio Page 
parent: The Team
---

### Project: BogoBogo

BogoBogo is a desktop inventory management application developed for learning Software Engineering principles. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented the core components to enable placing orders.
    * What it does: allows the user to order items, add and remove items from order, and place the order to make a
      transaction.
    * Justification: This feature improves the product significantly because a user can not only log items in inventory
      but also monitor business dynamics via the order system.
    * Highlights: This enhancement affects existing model structures and new features to be added in the future. It
      required an in-depth analysis of design alternatives. The implementation too was challenging as it required
      changes to multiple existing components while designing good interacting logic and abstraction between them.

* **New Feature**: Added transaction history.
    * What it does: allows the user to check past transactions made via order system and calculates essential historical
      business statistics.
    * Justification: This feature improves the product significantly because a user can track transaction histories to
      better retrospect and manage business.
    * Highlights: This enhancement is added on top of the order system implemented in last feature to extend and make
      the order system interaction more complete and powerful.

* **Functional Code**: Added commands to enable order related operations.
    * What it does: allows the `Ui` component to parse user commands of order operations and activates the `Model`
      and `Storage` components to execute the operations and update the internal states.


* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=wangpeialex&tabRepo=AY2122S1-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36]()
      , [\#38]())

* **Documentation**:
    * User Guide:
        * Drafted the skeleton of the user guide in v1.1 [\#11](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/11)
        * Added existing functionalities and features of v1.3 to user
          guide. [\#50](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/50)
    * Developer Guide:
        * Drafted the skeleton of the developer guide in
          v1.3 [\#76](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/76)
        * Drew the architecture class diagrams of `Ui`, `Model`, `Storage`
          components. [\#93](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/93)
        * Add use cases for all commands and features implemented in
          v1.3 [\#93](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/93)
        * Update and drew new class diagrams of `Model` components to reflect new design
          structure [\#177](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/177)
        * Refine and update class diagrams to make them more and clean and
          readable [\#178](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/178/files?authenticity_token=cnwcniV%2B3SlxviaiSsFhbzbBAYOnwhmeY2huI3o3cGoSb8zemdrQDT4cW%2B9c4lGKPdVT4ZxW%2BANMgfyP%2FE269A%3D%3D&file-filters%5B%5D=.png)
        * Draw object diagrams for Observer pattern. [\#184](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/184)

* **Manual Testing**:
   * System Testing:
        * Tests all commands with positive and negative EP cases.
        * Log and report bugs to team for bug fix solutions.
   * Acceptance Testing:
        * Tests the app against User Guide.
        * Fix User Guide inconsistency, ambiguity, and errors.
        * Log and report bugs and possible improvements to team for bug fix solutions. (
            [\#197](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/197), 
            [\#198](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/198),
            [\#199](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/199))
   

* **Community**:
    * PRs reviewed: [\#37](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/37)
      , [\#38](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/38)
      , [\#42](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/42)
      , [\#92](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/92)
    * Reported bugs and suggestions for other teams in the class (
      examples: [PE-D](https://github.com/wangpeialex/ped/issues))
