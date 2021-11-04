---
layout: page
title: Javier's Project Portfolio Page
---

### Project: Restaurant HR Helper

Restaurant HR Helper(RHRH) is a desktop application that helps to streamline restaurant operations by allowing 
managers to easily organise and keep track of their customers, employees, suppliers and reservations in their 
restaurants.

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T17&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Javiier-pzk&tabRepo=AY2122S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **New Feature**: Implemented the [`add,edit and delete Supplier`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/53) functionality.
    * What it does: Add/Edit/Delete a `Supplier` with the required and optional fields to the list of `Suppliers`.
    * Justification: We need to separate the different people, between employees, customers and suppliers and thus, 
      we separated the functions of Suppliers.
<br>

* **New Feature**: Implemented the [`sort supplier`]((https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/116)) and [`sort customer`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/122) functionality.
    * What it does: Sorts the `Supplier` list according to a supplier field and order that the user specifies
    * Justification: For suppliers, HR managers may want to sort the supplier list by any of the supplier fields for 
      greater ease of tracking and viewing. This is especially useful when HR managers want to see delivery dates of 
      suppliers from earliest to latest or vice versa.


* **New Feature**: Implemented the [`find supplier`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/105) functionality.
    * What it does: Finds all suppliers that contain the string of words that user has inputted to find.
    * Justification: This feature allows managers to filter the `Supplier` list based on any keyword they wish to 
      search for such as searching for how many suppliers are delivering in the month of October.


* **New Feature**: Implemented the [`list supplier`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/53/commits/055fb36ec972a5d7a78fe12206de45e711e2d277) functionality.
    * What it does: Clears all filters and shows the entire list of `Suppliers`.
    * Justification: Easy way to clear all filters and display the entire list of suppliers again. It also serves as 
      a quick way to switch to the supplier list if a user is currently viewing another list in RHRH.


* **Enhancements to existing features**:
   * Implemented [list switching](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/53/commits/055fb36ec972a5d7a78fe12206de45e711e2d277) functionality into the UI.
   * Enhanced the [`DeliveryDetails`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/100) class by allowing it to 
     accept `LocalDateTime` inputs instead of a `String`.
   * Improved flexibility of all [`LocalDateTime`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/182) inputs of all classes in RHRH.
   * Fix PE-D related bugs: Standardise [`Delivery Details`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/174) date time format with the rest of classes in RHRH that 
     uses `LocalDateTimes` as well.
   * Added and improved integration and unit testing for all `Supplier` related functionality. 
     (Pull Requests [\#53](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/53/commits), 
     [\#65](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/65), [\#176](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/176), [\#180](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/180), [\#191](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/191))
   * Improved [UI aesthetics](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/112/commits/42b2239993da42c6afe0f57a80f3d347b9fb3497) and made command words 
     [case-insensitive](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/112/commits/1d60b41c935d193b92d299b3a4db05cba50c7b50).


* **Documentation**:
  * Contributions to the User Guide (UG):
    * Added [Overview, Target Audience and Purpose]() in the UG
    * Documented all [`Supplier`](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/138) related functionality in the UG
    * Filled in the [Command Summary](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/139/commits/b5b235adaaa69f8aa6f563afa2dab364e80d7000) section in the UG
    * Fixed minor [formatting](https://github.com/AY2122S1-CS2103T-T17-1/tp/commit/5546f827656a3165d52abfc85f62dc9cae3886ce) and [grammatical mistakes](https://github.com/AY2122S1-CS2103T-T17-1/tp/commit/13bc3512c6774fc966a25c11f96265ae78f46fd3) in the UG
  * Contributions to the Developer Guide (DG):
      * Renamed AB3 references in the DG to fit RHRH.
      * Draw UML diagrams for [to be completed]
      * Added [use case](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/31) for `Add Reservation` in DG


* **Contributions to team based tasks**:
  * Set up GitHub team repo and organisation
  * Added [CodeCov integration](https://github.com/AY2122S1-CS2103T-T17-1/tp/commit/9688cc4370a301d91f077615994ff5a57907ac30) to the team repo
  * Set up [project website](https://github.com/AY2122S1-CS2103T-T17-1/tp/commit/1ccfbb48a4aa04adfe125b8473c3a915ae4e95bf)
  * Updated [AboutUs](https://github.com/AY2122S1-CS2103T-T17-1/tp/commit/1b7c1347609726197c3b0f2f0afd96cd420f2485) file
  * Maintained Issue tracker for milestones v1.1 and v1.2 on GitHub
  * Released JAR files on GitHub team repo for [v1.3 (trial)](https://github.com/AY2122S1-CS2103T-T17-1/tp/releases/tag/v1.3(trial)) and [v1.3 (final)](https://github.com/AY2122S1-CS2103T-T17-1/tp/releases/tag/v1.3(final))


* **Review and Mentoring contributions:**
    * PRs reviewed with non-trivial comments: PRs [\#33](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/33), 
      [\#127](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/127), [\#189](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/189)
  

* **Contributions beyond the team project**:
  * Evidence of helping others: 
    * CS2103T AY2122S1 forum [Issue #297](https://github.com/nus-cs2103-AY2122S1/forum/issues/297)
    * Found bugs in team [CS2103T-T15-3's](https://github.com/Javiier-pzk/ped/issues) product.
