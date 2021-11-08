---
layout: page 
title: Bernard's Project Portfolio Page
parent: The Team
---

### Project: BogoBogo

BogoBogo is a desktop inventory management application developed for small business owners. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Introduction of count field + replenish function + optional flags. Link to PR: [\#24](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/24) [\#42](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/42)
    * What it does: While the original `add` command only allowed addition of new items, now if `add` is used on existing item on inventory, it will replenish the item according to the count instead. When replenishing, only either name or id is a mandatory flag, rest is optional (count is assumed to be 1 if empty).
    * Justification: Unlike `Person` in AB3, an `Item` has a count field which indicates the stock of the `Item`. Thus, adding an existing item should replenish the item rather than throwing an error. The usage of optional fields is to bring convenience to the businessowner as providing all details correctly goes against the efficiency of CLI app.
    * Highlights: This requires quite a major change from the AB3 implementation, as AB3 did not support optional flags.
    
* **New Feature**: Introduction of Cost Price and Sales Price fields. Link to PR: [\#65](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/65)
    * What it does: Allows user to store cost price and sales price of each Item. Cost price is the price at which the business owner bought the item while sales price is the price at which the business owner sells the item. The flags for each are `/cp` and `/sp`.
    * Justification: It will eventually be used for bookkeeping purposes.
  
* **New Feature**: Save Transactions and BookKeeping as persistent data (json). Link to PR: [\#92](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/92) [\#95](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/95)
    * What it does: Previously, transaction data are only stored within a session (i.e. once the user exits the app, the data won't be there anymore). Now, all transaction data are stored inside a json file. Furthermore, to facilitate financial bookkeeping purposes, total costs, total revenue, and total profit are stored in bookkeeping.json as well. Both BookKeeping and Transactions data can be found by typing `list txns`
    * Justification: It's to allow the app to be useful for more than 1 session, which clearly has to be the case.
    * Highlights: I honestly struggled with understanding the design of the storage class at the start, thus the initial PR worked, but does not follow the existing dessign pattern closely. Upon further trials, the fixed code with correct design is given in this PR [\#161](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/161).

* **Testing**:
    * I was in charge of jacking up the testing coverage for all existing classes overall. The following PRs show the testing code I did to increase Codecov testing coverage [\#185](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/185), [\#201](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/201), [\#204](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/204). As a result, our team's [Codecov](https://app.codecov.io/gh/AY2122S1-CS2103-F10-2/tp/) coverage increased from 74.00% to 80.16%.
    * Did internal testing of BogoBogo. Sample issue: [\#182](https://github.com/AY2122S1-CS2103-F10-2/tp/issues/182).
    * Fix bugs found from internal testing: [\#185](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/185), [\#172](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/172), [\#109](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/109), [\#98](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/98)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=bernarduskrishna&tabRepo=AY2122S1-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
 
* **Documentation**:
   * User Guide
      * Revise the UG partially for v1.3 and Mock PE [\#101](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/101/files)
      * Include screenshots for the UG [\#105](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/105)
   * Developer Guide
      * Wrote the implementation details of `Ordering` and drew the required UMLs [\#161](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/161/files)
      * Helped a team member with the implementation details of `Mutating Inventory` and drew the required UMLs [\#185](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/185/files)
      * Wrote part of use cases [\#73](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/73)
   * Proofread the final version of UG and DG. Links to PR: [\#215](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/215), [\#216](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/216), [\#217](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/217), [\#218](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/218)

* **Community**:
   * Team-based Tasks:
      * Wrote most of the issues during meetings. [Issues](https://github.com/AY2122S1-CS2103-F10-2/tp/issues?q=is%3Aclosed+is%3Aissue+author%3Abernarduskrishna+)
      * Wrote AboutUs Page. [AboutUs](https://ay2122s1-cs2103-f10-2.github.io/tp/AboutUs.html)
   * PR Reviews: [\#27](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/27), [\#37](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/37), [\#52](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/52), [\#93](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/93), [\#192](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/192)
   * Reported bugs and suggestions for other teams during PE-D ([PE-D](https://github.com/bernarduskrishna/ped/issues))
