---
layout: page
title: Chris's Project Portfolio Page
---

### Project: LeadsForce

LeadsForce - LeadsForce is a desktop application catered towards student financial advisors (FA) for managing their leads.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Search command. (Pull requests: [\#41](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/41), [\#65](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/65))
  * What it does: allows the search command to be able to search for clients whose attributes match the given attribute keywords as well as generic keywords.
  * Justification: This feature improves the user experience for the FAs when managing a large addressbook by providing them with the ability to search for specific for clients with greater flexibility.
  * Highlights: The implementation was challenging as the searching logic is complicated which is dependent on both the given generic keywords and attribute keywords.
  This requires a predicate to check all the client's attributes against all the generic keyword and each client's attribute with their respective attribute keyword, which is also used by the filter command.

* **New Feature**: Added Filter command. (Pull requests: [\#58](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/58), [\#65](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/65))
  * What it does: allows the user to filter the current displayed client's list based on given keywords similar to the search command.
  * Justification: This allows the users to find clients incrementally based on keywords.

* **New Feature**: Added Multiple AddressBook functionality. (Pull requests: [\#110](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/110), [\#129](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/129))
  * What it does: allows the user to create/delete/switch between different addressbook.
  * Justification: This feature will allow the FAs to manage different group of clients under separate addressbook that can also be easily ported over to other devices.
  * Highlights: This feature was challenging as it requires direct manipulation of the data files as well as changing multiple different components. 

* **New Feature**: Added Batch Edit/Delete functionality (Pull requests: [\#102](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/102), [\#196](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/196))
  * What it does: allows the user to edit and delete multiple clients at the same time.
  * Justification: Reduce the number of command needed to be executed for performing multiple edit and delete.
  * Highlights: For the batch edit command, the edit command will now have to check whether after applying the edits to the clients will there be duplicate before applying any of the edit.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=wyrchris&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=wyrchris&tabRepo=AY2122S1-CS2103T-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Improve sort command by:
    1) allowing sorting by multiple attribute at once, (Pull request: [\#146](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/146))
    2) allowing for sorting of non-string based attributes, (Pull request: [\#144](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/144))
    3) simplifying the comparator usage ([\#84](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/84))
  * Simplify prefix usage by (Pull request: [\#84](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/84))
    1) providing support for handling multiple prefix usage,
    2) simplify logic which requires the use of specific methods dependent on the prefix with PrefixMapper class
  * Allowed for the toggling between different themes (Pull requests: [\#137](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/137), [\#197](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/197))
  * Update the clear command to display a prompt for confirmation before clearing the addressbook (Pull request: [\#123](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/123), [\#194](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/194))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `search`, `filter` [\#41](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/41), [\#58](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/58)
    * Added documentation for the addressbook command `ab create`,`ab delete`, `ab list` [\#129](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/129)
    * Update documentation for existing command `clear` [\#123](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/123)
  * Developer Guide:
    * Added implementation details of the `search` and `filter` feature [\#116](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/116)
    * Added implementation details of the addressbook command `ab create`,`ab delete`, `ab list` [\#205](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/205)
    * Added implementation details for IgnoreNullComparable interface and PrefixMapper class [\#230](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/230)

* **Community**:
  * PRs reviewed (with non-trivial review comments) (Pull requests: [\#46](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/46),
  [\#51](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/51), [\#136](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/136),
  [\#64](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/64))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103-T14-1/tp/issues/160),
  [2](https://github.com/AY2122S1-CS2103-T14-1/tp/issues/170), [3](https://github.com/AY2122S1-CS2103-T14-1/tp/issues/173))
