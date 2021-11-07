---
layout: page
title: Ng Zi Xuan's Project Portfolio Page
---

### Project: ForYourInterest

ForYourInterest - ForYourInterest is a desktop app for managing members in university clubs/societies, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added ability to chain commands with `&&`
  * What it does: allows users to chain multiple commands together into one long command chain. Each command will execute sequentially.
  * Justification: This feature improves the product significantly for advanced level users because a user can chain command they want to execute to be written in a row instead of having to enter each command individually.
  * Highlights: This feature has a clever use of recursion to be able to accept multiple chaining attempts. It extends from the basic command regex checker in `AddressBookParser` and adds an advanced command regex checker.
  
 **New Feature**: Added ability to set and use an alias
  * What it does: allows the user to save a command as an alias and subsequently be able to use that alias to execute the command.
  * Justification: This feature improves the product significantly for advanced level users because a user can set commonly used commands to a shorter alias for speedier usage of the app. 
  * Highlights: This feature made use of many of the previously added utilities and support for command parsing while requiring a deep analysis in to the design to be able to implement the different stages needed for setting an alias and then executing the alias.
 
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=nzixuan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=nzixuan&tabRepo=AY2122S1-CS2103-T16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Lead the discussion for some of the team's weekly meetings.

* **Enhancements to existing features**:
  * Updated `help` to link to ForYourInterest page [\#42](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/42)
  * Added ability for `delete` to delete events using flag `-e` [\#83](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/83)
  * Wrote equals method and testing for `clear`, `exit`, `help` and `list` commands [\#65](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/65)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete`, Chain Command and `alias` [\#19](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/19), [\#83](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/83), [\#65](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/65), [\#71](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/71)
  * Developer Guide:
    * Added implementation details of the Chain Command feature. [\#82](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/82)

* **Community**:
  * PR reviewed (with non-trivial review comments): [\#64](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/64)
