---
layout: page
title: Chen Yiyang's Project Portfolio Page
---

### Project: SeniorLove

SeniorLove is a contact management desktop app which aims to facilitate community workers' visits to the elderly. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Senior Love can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

- **New Feature**: Added the ability to mark visits as done.
  - What it does: allows the user to make the next visit to an elderly as done using elderly's index. When a visit is marked as done, the `LastVisit` field will be updated. If the visit marked is recurring, it will be replaced with the next one following the recursion frequency.
  - Justification: This feature improves the product significantly because a user can proceed with a sequence of visits of an elderly and visits are at the core of SeniorLove.
  - Highlights: This command is dependent on the `Visit`, `Frequency`, `Occurrence` and `LastVisit` fields of an elderly in the system. Implementation for the command was challenging as many scenarios need to be taken into account. Defensive programming was also used to ensure smooth running of the programme should certain fields involved be missing or invalid.


- **New Feature**: Added the ability to view incoming visits.
  - What it does: allows the user to see all the visits that are scheduled in the next 7 or 30 days depending on the command parameter.
  - Justification: This feature improves the user experience as the user can view incoming visits easily and plan their schedule accordingly.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t14&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Chen-Yiyang&tabRepo=AY2122S1-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
    * Updated the GUI to highlight elderly with overdued visits (Pull request [\#134](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/134))
    * Implemented warnings for inappropriate command usage. (Pull requests [\#96](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/96), [\#192](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/192))
    * Improve Date to Datetime for all time-related fields. (Pull request [\#71](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/71))
  

* **Documentation**:
    * User Guide:
        * Added documentation for the features `done`, `list` and `datetime`. [\#33](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/33), [\#123](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/123)
        * Checked for consistency of terms used. [\#188](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/188)
    * Developer Guide:
        * Added implementation details for the features `datetime`, `done` and `list`. [\#103](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/103), [\#213](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/213)
        * Added use cases for `add`, `delete`, `done` and `list` commands [\#34](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/34), [\#211](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/211)


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#130](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/130), [\#187](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/187)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Chen-Yiyang/ped/issues/2), [2](https://github.com/Chen-Yiyang/ped/issues/4), [3](https://github.com/Chen-Yiyang/ped/issues/5))
  * Reported bugs for tools used for the module: [\#1602](https://github.com/reposense/RepoSense/issues/1602)
