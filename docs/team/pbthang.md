---
layout: page
title: Pham Ba Thang's Project Portfolio Page
---

### Project: Restaurant HR Helper

Restaurant HR Helper (RHRH) is a desktop application that helps organise and keep track of human resources and people related to the restaurant.
This includes neatly storing employees, suppliers, loyal customers information and reservations in the database to easily access them when needed.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add/delete/edit `Reservation` information (Pull Request [#54](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/54), [#92](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/92), and [#125](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/125))
  * What it does: Add/Edit/Delete an `Reservation` the same way it does a `Person` with the required and optional prefixes to the list of `Reservation`.
  * Highlights: These commands are later enhanced by [hernpiblo](https://ay2122s1-cs2103t-t17-1.github.io/tp/team/hernpiblo.html), where you can add reservations based on the current table setup of the restaurants as well as the timing availability.
  
* **New Feature**: Added the GUI for `Reservation` (Pull request [#88](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/88))
  * What it does: `Reservation` is separated into a separated view that you can switch to by clicking the menu <kbd>Reservations</kbd> option or by using the list reservation command.
  * Justifications: Since reservation might be expanded to hold more information in the future, instead of displaying them in `Customer` view, I decided to separate it to reduce clutter.
  
* **New Feature**: Added the ability to get the corresponding customer information who made a particular reservation (Pull request [#107](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/107))
  * What it does: From the display `Reservation` list, users can get the information of the customer who made a particular reservation
  * Justifications: Displaying customer information with reservation may result in cluttered user interface, data repetition and coupling in the codebase. Hence,this command helps user to easily navigate between a reservation and the corresponding customer information.
  
* **New Feature**: Implemented sorting order by date-time ascending for `Reservation` list.
  * What it does: Reservations are sorted by ascending date-time.
  * Justifications: Reservations should be in chronological order for easy referencing, and date-time ascending order is chosen so that the latest reservations are always on top of the list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=pbthang&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=pbthang&tabRepo=AY2122S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project Management**: 
  * Reviewed 11 pull requests on GitHub
  
* **Enhancements to existing features**:
  * Added `Remark` and `Tag` list as optional fields to `Reservation` (Pull request [#119](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/119))
  * Wrote test cases for `Reservation`-related classes to improve test coverage (Pull request [#64](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/64) and [#195](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/195))
  
* **Documentation**:
  * User Guide: 
    * Updated the 'Getting started' section and added the prefix and preamble summary tables (Pull request [#134](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/134))
    * Updated the section for add/edit/delete/list reservations and get corresponding customer commands (Pull request [#140](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/140))
    * Fixed some styling errors and section ordering, as well as some documentation bugs (Pull request [#144](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/144))
  * Developer Guide:
    * Added use case for adding/editing reservation (Pull request [#247](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/247))
    * Added UML Diagrams for add reservation command (Pull request [#210](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/210))
    * Updated Model Class Diagram and added UML Diagrams for `GetCustomerReservingCommand` (Pull request [#227](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/227))
    * Updated manual test cases for add reservation and get customer reserving commands (Pull request [#241](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/241))
  
* **Community**:
  * Spotted 5 bugs for team CS2103T-W08-3 (Refer to [`pbthang/ped` repo issues](https://github.com/pbthang/ped/issues))
