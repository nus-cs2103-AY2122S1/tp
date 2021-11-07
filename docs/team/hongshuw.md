---
layout: page
title: Hongshu Wang's Project Portfolio Page
---

## Project: Sellah

### Overview

**Sellah** is a desktop application written in Java for online sellers who prefer CLI over GUI. 
Sellah helps them to keep track of their products and the contact information and orders of their customers.

### Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hongshu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=HongshuW&tabRepo=AY2122S1-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added the ability to add products.
    * What it does: allows the user to add a new product with compulsory name and unit price, and an optional quantity.
    * Justification: This feature improves the software significantly because this is a basic function that helps users
      to keep track of their products.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth
      analysis of design alternatives. The implementation too was challenging as it required changes to existing
      commands.
    * Pull requests: [\#78](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/78),
      [\#80](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/80),
      [\#98](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/98),
      [\#101](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/101)

* **Enhancements to existing features**:
    * Updated the original add command, changed it into add client command to differentiate it from add product command
      (Pull requests [\#77](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/77),
      [\#79](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/79),
      [\#166](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/166),
      [\#175](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/175))
    * Updated attributes in Client, so that it contains a client's name, phone number, email, address and the orders he
      made; created an Order class (Pull requests [\#97](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/97),
      [\#160](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/160),
      [\#171](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/171),
      [\#172](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/172))
    * Wrote additional tests for existing features and new features to increase coverage (Pull requests
      [\#111](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/111),
      [\#115](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/115),
      [\#210](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/210),
      [\#214](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/214),
      [\#215](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/215),
      [\#229](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/229),
      [\#235](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/235),
      [\#236](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/236),
      [\#289](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/289),
      [\#322](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/322),
      [\#331](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/331))
      
* **Documentation**:
    * User Guide:
        * Added documentation for the features `add client` and `add product`
          [\#39](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/39),
          [\#45](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/45),
          [\#176](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/176),
          [\#227](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/227),
          [\#301](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/301)
    * Developer Guide:
        * Added use cases for `add client/product` and `statistics` features
          [\#49](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/49),
          [\#306](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/306)
        * Added implementation details of the `add client` and `add product` features
          [\#179](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/179)
        * Added instructions for testing for `add client` and `add product` features
          [\#334](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/334)

* **Contributions to team-based tasks**:
    * User Guide:
        * Updated screenshots of all the features in the User Guide
          [\#338](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/338)
    * Developer Guide:
        * Updated sequence diagrams for all features after changing code structure
          [\#183](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/183)
        * Corrected mistakes in document for all features
          [\#184](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/184)
    * Hosted some of the online team meetings

* **Community**:
    * Contributed to forum discussions (examples: [\#24](https://github.com/nus-cs2103-AY2122S1/forum/issues/24))
    * Reported bugs and suggestions for other teams in the class (examples:
      [1](https://github.com/HongshuW/ped/issues/3), [2](https://github.com/HongshuW/ped/issues/4),
      [3](https://github.com/HongshuW/ped/issues/6))
