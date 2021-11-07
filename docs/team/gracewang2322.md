---
layout: page 
title: Grace's Project Portfolio Page
parent: The Team
---

### Project: BogoBogo

BogoBogo is a desktop inventory management application developed for small business owners. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Enhancement**: Enhancing logic of Commands to correctly detect existence of items in the inventory.
  * What it does: Allows Add, Delete, Remove and order commands to show appropriate error message when the user:
    1. Tries to access items with existing id but nonexistent name, vise versa
    2. Add in extra flags that are unnecessary
  * Justification: This feature allows detection of non-existent items whose logic is originally not present in AB3. 
  * Highlights: This enhancement required a major revamp to AB3's existing command logic.
    1. In AB3, the items are considered present in the current inventory as long as one of the name or id field is present. However, this causes false identification 
    of items in Bogobogo as there may be a situation where one of the field inputted matches an existing item but other fields does not exist.
    2. Originally, AB3 does not recognise extra flags and treats them as a Parse error for required fields. However, the user might be instead putting new fields that are
    extra. Thus, Bogobogo has to detect the extra flags and decide whether to output error or just a warning. 
  * Links to PRs: [\#103](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/103)

* **Enhancement**: Adding specific restrictions for different fields in an item.
  * What it does: Apply restrictions for user inputs regarding name and id.
  * Justification: This feature repurposes the parsing requirements to be customised for Bogobogo. 
  * Highlights: This enhancement required considerations of all edge cases on user inputs as the restriction on the fields are vastly different from the requirements of AB3.
  * Links to PRs: [\#78](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/78), [\#52](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/52), [\#27](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/27)
    

* **New Feature**: Find Command
  * What it does: Allows the user to find items that matches the id or tag given and allows finding of multiple ids, names and tags as well.
  * Justification: AB3 only supports finding by name. However, as an inventory tracker, BogoBogo would need to allow users 
  * to track by id and tag as well. Users would need to find by id as id provides unique identification of items and tag such as 'popular' would allow
  * user to quick find the items that are welcomed by buyers to better make inventory decisions. 
  * Links to PRs: [\#106](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/106), [\#25](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/25)
  
* **New Feature**: Help Command
  * What it does: Allows new users to query how to use each command. 
  * Justification: AB3 only supports generic help message which links the users back to user guide. However, Bogobogo is more user-friendly in allowing
  * query of specific commands and displaying each of their message usages. 
  * Links to PRs: [\#47](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/47), [\#174](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/174)
  

* **Testing**: 
  * System Testing:
    * Did internal testing of Bogobogo and fixed bugs on commands found.
    * Links to PR: 
      [\#196](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/196),
      [\#180](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/180),
      [\#174](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/174),
      [\#173](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/173),
      [\#103](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/103)
  * Acceptance Testing:
    * Fix User Guide and Developer Guide inconsistency, ambiguity, and errors.
    * Links to PR:
      [\#203](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/203),
      [\#186](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/186)
      

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=gracewang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=GraceWang2322&tabRepo=AY2122S1-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Documentation**:
  * User Guide:
    * Updated use cases for Find, Help, Add commands
    * Proofread and fix User Guide inconsistency, ambiguity, and errors.
    * Links to PR:
      [\#203](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/203)
  * Developer Guide:
    * Add implementation features for Sort, Find and Mutating Inventory
      [\#164](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/164)
    * Update use cases for commands 
      [\#181](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/181)
      [\#146](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/146)
    * Proofread and Fix inconsistency, ambiguity, and errors. 
      [\#186](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/186)
    

* **Community**:
* PRs reviewed:
     [\#44](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/44)
     [\#50](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/50)
     [\#92](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/92)
     [\#98](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/98)
     [\#100](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/100)
     [\#101](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/101)
* Reported bugs and suggestions for other teams during PE-D 
     ([PE-D](https://github.com/GraceWang2322/ped/issuess))
 
