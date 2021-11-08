---
layout: page
title: Kong Fanji's Project Portfolio Page
---

### Project: Restaurant HR Helper

Restaurant HR Helper(RHRH) is a desktop application that helps organise and keep track of human resources and people related to the restaurant.
This includes neatly storing employees, suppliers and loyal customers information in the database to easily access them when needed.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add/edit/delete `Employee` information (Pull request [\#56](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/56))
    * What it does: Add/Edit/Delete an `Employee` the same way it does a `Person` with the required and optional prefixes to the list of `Employee`.
    * Justification: We need to separate the different people, between employees, customers and suppliers and thus, we separated the functions of employees.

* **New Feature**: Added a Sort command for `Employee` (Pull request [\#127](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/127))
    * What it does: Sorts the `Employee` list according to the prefix and order that the user specifies
    * Justification: For employees, HR managers may want to view the `Employee` list sorted based on leaves remaining before the calendar year ends or salary when needed.
    
* **New Feature**: Added a Find command for `Employee` (Pull request [\#96](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/96))
    * What it does: Finds the employee that has the string of words that user has inputted to find.
    * Justification: This feature allows managers to filter the `Employee` list based on date and time such that they can view who is on shift on a particular day or particular shift. Managers can also filter by other general information such as filter by the name needed or number of leaves remaining.

* **New Feature**: Added a list command for `Employee` (Pull request [\#96](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/96))
    * What it does: Clears all filters and shows the entire list of `Employees`.
    * Justification: Easy way to clear all filters and display the entire list of employees again.
      Also a quick way to switch to the window that displays a list of all employees. 
    * Clarification: list command was previously `employee`, later on we modified it to become `listE` to clear all filters and display entire list of employees.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T17-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=clementkfj&tabRepo=AY2122S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Mostly github related responsibilities
    * Managed Github issues and PRs 
        * Ensuring that internal deadlines and milestones(e.g. `v1.3` etc.) are met.
    * Managed and vet through PE-D bugs found by peers
        * Ensured bugs were fixed or issues raised are closed if not important.
    * Reviewed 37 PRs throughout RHRH implementation

* **Enhancements to existing features**:
    * Removed `Person` related features in the Ui (Pull request [\#85](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/85))
    * Wrote additional tests for existing features to increase coverage (Pull requests [\#61](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/61), [\#56](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/56), [\#94](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/94), [\#181](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/181), [\#186](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/186))
    * Improve `Find` Employees command to search more accurately for `Shift` (Pull request [\#131](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/131))
    * Improve `Find` Suppliers and Customers command to search more accurately (Pull request [\#131](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/131))
    * Fix PE-D related bugs, changing validity checkers to enforce stricter rules for entering fields so people will not find bugs in it (Issues [\#154](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/154), [\#155](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/155) and [\#162](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/162), Pull request [\#177](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/177))
    * Removed all instances of address book in RHRH (Pull requests [\#201](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/201))

* **Documentation**:
    * User Guide:
        * Created first draft User Guide for `Customer`, `Supplier` and `Employee` (Pull request [\#67](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/67))
        * Fixed errors for formatting issues and updated `Employee` related commands' details (Pull request [\#142](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/142))
        * Updated new information for `Shift`, `Leaves`, `Salary` and `Loyalty Points` with new REGEX checker (Pull request [\#197](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/197))
        * Fix raised points about improvements in User guide during PE-D (Issues [\#159](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/159), [\#161](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/161), [\#165](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/165), [\#166](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/166), [\#168](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/168) and [\#172](https://github.com/AY2122S1-CS2103T-T17-1/tp/issues/172), Pull request [\#177](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/177))
    * Developer Guide:
        * Added and made improvements to `AddEmployeeCommand` section for Developer's guide (Pull request [\#208](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/208), [\#217](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/217), [\#220](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/220))
        * Added NFRs (Pull request [\#30](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/30), [\#39](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/39))
        * Changed majority of the UML diagrams to better suit RHRH (Pull request [\#208](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/208))
        * Added `Add` and `Edit` commands use cases and `Add` Customer/Employee/Supplier manual testing (Pull request [\#242](https://github.com/AY2122S1-CS2103T-T17-1/tp/pull/242))

* **Contributions beyond the team project**:
    * Found 6 bugs for PE-D for team [W14-2](https://github.com/clementkfj/ped/issues) 
