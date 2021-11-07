---
layout: page
title: Zhao Jinxin's Project Portfolio Page
---

### Project: ManageMe

ManageMe is a **lightweight but powerful desktop application built to help university students manage their school life, available on Windows, Linux and Mac**. 
You can add your modules, tasks, schedules and online learning resources easily into ManageMe and access them with simple commands.

Given below are my contributions to the project.

* **New Feature**: Added `editMod` command (PR [#58](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/58))
  * What it does: Allows users to edit an existing module.
  * Justification: This allows users to update the information of the modules they take. For example,
    when a user take new modules in a new school term, or found a typo in a previously entered module,
    the user can change the module information accordingly.
  * Difficulty: This function was not difficult to implement, however it was modified in different stages of the project
    due to changing needs. Initially in `v1.2`, a module contains both a `name` and a `Link`, thus editMod must be able to edit both.
    However, later in `v1.3`, `Link` was extracted out to become a standalone class with its own commands,
    thus all dependencies with Link was removed from editMod.
  

* **New Feature**: Added `findMod` command (PR [#106](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/106))
  * What it does: Find modules whose names contain given keywords.
  * Justification: This allows users to quickly find a module by keywords instead of browsing through the whole module list.
  * Difficulty: This function was not difficult to implement, however again it was modified in different stages of the project
    for improvement. Initially, `findMod` can only search for whole words that match the keyword entirely. However,
    we later realised that since the module code is a single word containing different characters (like CS2100), we should enable
    searching for words that partially match the characters given in the keyword. The search by character functionality was
    added by Tianqi (PR [#229](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/229)).

* **New Feature** Added `listMod` command (PR [#106](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/106))
  * What it does: Return to displaying all modules after a `findMod` search.
  * Justification: After a `findMod` command, the module list is filtered and only modules containing 
    the keyword will be shown. The `listMod` command is required to return to the full list of modules.
  * Difficulty: This feature was easy to implement as I only needed to bring out the full list.


* **Skeleton Code**: Added skeleton code for all `Module` features (PR [#49](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/49))
  * What it does: Lay out the basics for implementing `Module` features. This includes creating the `Module` class with its
    attributes and its corresponding `Parser` and `Command` classes. `Storage` files are also modified to accommodate Module
    objects.
  * Difficulty: This task was not difficult, but time-consuming since I needed to go through the entire code base
    and edit its `Logic`, `Model` and `Storage` components.
  

* **Testing**:
  * Wrote test utilities for Module features. (PR [#84](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/84))
  * Wrote test cases for editMod and findMod features. (PR [#84](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/84), [#106](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/106))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=JinxinZhao315&tabRepo=AY2122S1-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Documentation**:
  * User Guide:
    * Wrote documentation for features `editMod` and `findMod`(PR [#58](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/58), PR [#106](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/106)).
    * Added most of the feature screenshots (PR [#224](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/224)).
    * Updated `Introduction` and `Quick Start` sections (PR [#224](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/224)).
    * Enhanced readability through color coding titles and formatting (PR [#224](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/224)).
    * Added content table and "back to content table" navigation. (PR [#224](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/224))
  * Developer Guide:
    * Wrote architecture descriptions for the `Logic` component (PR [#110](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/110)).
    * Wrote implementation details of the `Read Module` feature (PR [#236](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/236)).
    * Updated `User stories` as the project progressed.(PR [#241](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/241)).
    * Updated `Use cases` as the project progressed (PR [#225](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/225)).

