---
layout: page
title: Zhu Tianqi's Project Portfolio Page
---

### Project: ManageMe

ManageMe is a **lightweight but powerful desktop application built to help university students manage their school life, available on Windows, Linux and Mac**. You can add your modules, tasks, schedules and online learning resources easily into ManageMe and access them with simple commands.

Given below are my contributions to the project.

* **New Feature**: Added `addMod` and `deleteMod` command
    * What it does: Allows users to add a Module to ManageMe.
    * Justification: Essential functionality for Modules to appear in ManageMe.
    * Difficulty: This function was not difficult to implement, but was modified in different stages of the project
      due to changing needs. Initially in v1.2, a module contains both a name and a Link, thus addMod must be able to add both.
      However, later in v1.3, Link was extracted out to become a standalone class with its own commands,
      thus all dependencies with Link was removed from addMod.

* **New Feature**: Introduced link functionality with both local filepath and website URL.
    * What it does: Define a link to be either a local filepath or a web URL.
    * Justification: This allows users to store, and open links in the app conveinently with the least amount of commands to remember.
    * Difficulty: Medium. The design of linkAddress class requires some thought to incorporate both filepath and website URL. It was also
      a difficult decision whether to use a global `UniqueLinkList` or use a `UniqueLinkList` for each `Module`.

* **New Feature**: Added `findLink` and `listLink` command.
    * What it does: Allows users to search for links to ManageMe.
    * Justification: This allows users to quickly find a module by keywords instead of browsing through the whole module list and also enables
      them to go back to the full list.
    * Difficulty: This is similar to the brown field project and not difficult to implement. I changed the matching criteria from matching full words
      to matching based on characters to better suit our functionality.

* **New Feature**: Added `openLink` command.
    * What it does: Allows users to open a file or a folder using the system default application from ManageMe, or open a
      url using a local web browser.
    * Justification: This allows users to open the folders and websites that they frequently access with only one command,
      which is very convenient to use and saves users the time of clicking through the folders or copying and pasting the links.
    * Difficulty: This is challenging. Java desktop doesn't perform well on Linux and cross platform compatability is troublesome
      to ensure and inconvenient to test. OS specific command line implementation is time-consuming to google and check if they work well.

* **Integration**:
    * Refractored AddressBook to ManageMe and removed irrelevant code.
    * Extracted common classes and remove duplicate code.
        * Extracted generic abstract class `UniqueObjectsList` out of `UniqueModuleList`, `UniqueTaskList`, and `UniqueLinkList`.
        * Extracted `TagModule` class out of `LinkModule` and `TaskModule`.
        * Extracted `Name` class out of `LinkName`, `TaskName` and `ModuleName`.
        * Extracted object specific methods in `ModelManager` such as `addTask`, `removeModule` etc. to generic `add`, `remove`, `has` and `set` methods.

* **Testing**:
    * Wrote test cases for Link related functionalities.
    * Wrote test cases for `AddMod` and `DeleteMod` commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Tianqi-Zhu&tabRepo=AY2122S1-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Set up Github organization and project documentation folder.
    * Managed the issue trackers on GitHub.
    * Managed releases `v1.2.1` and `v1.3` (2 releases) on GitHub.

* **Documentation**:
    * User Guide:
        * Added documentation for the commands `addMod`, `deleteMod`, and all link related commands.
    * Developer Guide:
        * Conversion of the Archituecture section of the developer guide from AB3.
        * Optimized user stories at `v1.2`.
        * Modified final DG to suit integrated implementation.
        * Added Link implementation and Module implementation except `ReadMod`.
