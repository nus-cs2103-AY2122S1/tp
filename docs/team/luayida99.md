---
layout: page
title: Lua Yi Da's Project Portfolio Page
---

### Project: SeniorLove

SeniorLove is a contact management desktop app which aims to facilitate community workers' visits to the elderly. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Senior Love can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add and edit visits for elderly.
    * What it does: Allows the user to add a visit to an elderly with the elderly's index. Using this command with an elderly that already has a next visit will overwrite the current visit.
    * Justification: This feature is crucial to SeniorLove as it is the main way through which the user can add visits to an elderly. Visits are what we want to manage in SeniorLove, hence it is important to have such a feature.
    * Highlights: This feature is central to many of the other features present in SeniorLove. It affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation was challenging because it entailed many changes to existing functionality.

* **New Feature**: Added the ability to download existing elderly data into a CSV file.
    * What it does: Allows the user to download existing elderly data from `data\addressbook.json` into a CSV file on their local machines.
    * Justification: Existing elderly data can be difficult to look through quickly because the information is displayed in GUI to be presentable and is not as compact. The ability to download existing elderly data into a CSV file makes it more convenient to access this data without having to launch SeniorLove again.
    * Highlights: The implementation was challenging because I had never dealt with JSON to CSV conversions before, and took some time to understand the documentation and write the code.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t14&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=luayida99&tabRepo=AY2122S1-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Update `help` command to reflect SeniorLove documentation (Pull request [\#56](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/56))
    * Enhance `add` command with functionality to add a new visit with the elderly (Pull request [\#65](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/65))
    * Enhance `find` command to search through all elderly attributes (Pull requests [\#76](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/76), [\#83](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/83))
    * Enhance `edit` command to allow editing and removal of `LAST VISIT` (Pull request [\#117](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/117))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit`, `find`, `visit`, `download` and `help` (Pull requests [\#74](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/74), [\#83](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/83), [\#117](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/117), [\#119](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/119))
        * Added glossary and resolved bugs in User Guide: (Pull requests [\#172](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/172), [\#180](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/180))
    * Developer Guide:
        * Added implementation details of the `visit` and `find` feature (Pull requests [\#98](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/98), [\#106](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/106))
        * Added instructions for manual testing (Pull request [\#206](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/206))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#54](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/54), [\#72](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/72), [\#88](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/88), [\#96](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/96), [\#120](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/120), [\#186](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/186)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/luayida99/ped/issues/1), [2](https://github.com/luayida99/ped/issues/4), [3](https://github.com/luayida99/ped/issues/3))
