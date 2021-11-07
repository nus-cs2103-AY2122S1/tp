---
layout: page
title: Lee Hern Ping's Project Portfolio Page
---

### Project: Restaurant HR Helper

Restaurant HR Helper (RHRH) is a desktop application that helps organise and keep track of human resources and people related to the restaurant.
This includes neatly storing employees, suppliers, loyal customers information and reservations in the database to easily access them when needed.

### My Contributions to the project

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hernpiblo&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=hernpiblo&tabRepo=AY2122S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Features Implemented

1. **New Feature**: Added the ability to check and return the reservation made on a specified date time (Pull Request [#57](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/57))
   * What it does: Given a datetime, shows the user who are those that made reservations at that date and time specifically. Given a date, shows the user who made reservations at that date for all timings. Given a time, shows the user who made reservation today at that timing.
   * Justification: One user story of restaurant managers is to be able to check the reservations made for some specific date/time

2. **New Feature**: Added all the table related features including a command to set the tables in a restaurant (Pull Request [#101](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/101))
    * What it does: Allows the user to set how many tables and how many seats each table has
    * Justification: This allows the user to accurately replicate their physical restaurants to allocate the tables when trying to make a reservation

#### Enhancements to existing features

* Enhanced the reservation booking feature so that each reservation is linked to a table and the optimal table is assigned to a particular reservation (Pull Request [#101](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/101) and Pull Request [#113](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/113))
  * What it does: When trying to make a reservation, the algorithm picks out the smallest table that is able to fit the number of people in the reservation
  * Justification: This helps the restaurant managers fully optimise their restaurant space and not run into situations where a large table is assigned to a small group of people unnecessarily.
  

* Wrote test cases for `Check`-related classes to improve test coverage (Pull Request [#57](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/57))

 
* Wrote test cases for `Table`-related classes to improve test coverage (Pull Request [#101](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/101), Pull Request [#173](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/173) and Pull Request [#199](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/199))


* Added additional test cases for `Reservation`-related classes to improve test coverage (Pull Request [#173](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/173) and Pull Request [#199](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/199))

#### Contributions to team based tasks

* Released JAR file on GitHub team repo for [v1.2](https://github.com/AY2122S1-CS2103T-T17-1/tp/releases/tag/v1.2)

* Released JAR file on GitHub team repo for [v1.4](https://github.com/AY2122S1-CS2103T-T17-1/tp/releases/tag/v1.4)

#### Review and Mentoring contributions

* Reviewed 16 pull requests on GitHub

* Helped to ensure issues and internal milestones are on schedule

* Managed and discussed issues raised on our app from PE-D (Practical Exam - Dry run)

* Found 10 bugs in team [CS2103T-W11-4's](https://github.com/hernpiblo/ped/issues) software during PE-D.

#### Contributions to the User Guide (UG):

* Updated the section for CheckCommand and SetTablesCommand (Pull request [#73](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/73), Pull request [#121](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/121) and Pull request [#175](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/175))

* Fixed general formatting and aesthetic issues (Pull request [#21](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/21), Pull request [#136](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/136))


#### Contributions to the Developer Guide (DG):

* Added all user stories and glossary (Pull request [#22](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/22) and Pull request [#250](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/250))

* Added section for SetTablesCommand together with UML diagrams (Pull request [#213](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/213) and Pull request [#222](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/222))
