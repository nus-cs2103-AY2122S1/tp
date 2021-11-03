---
layout: page
title: Zhang Ziqing's Project Portfolio Page
---

### Project: SeniorLove

SeniorLove is a contact management desktop app which aims to facilitate community workers' visits to the elderly. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Senior Love can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

- **New Feature**: Added the ability to delate visits for elderly.

  - What it does: allows the user to delete visits for elderly with a shceduled next visit using elderly's index.
  - Justification: This feature improves the product significantly because a user can cancel a scheduled visit of an elderly. Visits are at the core of SeniorLove, hence it is important to ensure the user has the ability to perform deletion on a visit.
  - Highlights: This enhancement affects existing commands such as `DeleteCommand` in SeniorLove. It required an in-depth OOP analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

- **New Feature**: Added the classes of `Frequency` and `Occurrence` for a visit.

  - What it does: expands the functionality of visit by including frequency (such as weekly or monthly) and the number of occurrence.
  - Justification: This feature enhances the functionality of visits in SeniorLove by enabling recurring visit. As social workers ususally visit seniors regularly, the addition of these attritbutes enables the implementation of recurring visit.
  - Highlights: This enhancement required an in-depth OOP analysis of design alternatives. The implementation too was challenging as it required changes from scratch and thorough consideration of test cases.

- **New Feature**: Added the ability to sort elderly based on visit or last visit.

  - What it does: allows the user to sort elderly based on visit or last visit.
  - Justification: This feature enhances the functionality of SeniorLove by improving the effectiveness of display of information. As visits are at the heart of SeniorLove, sorting boosts the productivity by reducing the time to manually search for the timeline of visit.
  - Highlights: The implementation was challengins as there are many design alternatives and in-depth understanding of JavaFX features. I managed to find a way to maintain immutability of the lists while making the list of elderly sortable.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=ziqing26&tabRepo=AY2122S1-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

- **Project management**:

  - Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

- **Enhancements to existing features**:

  - Updated the GUI color scheme for PersonCard and add icons for better readbility (Pull requests [\#132](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/132), [\#140](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/140), [\#181](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/181))
  - Wrote additional tests for features such as `SortCommand` and `SortCommandParser` to increase coverage by 0.68% (Pull requests [\#68](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/68))

- **Documentation**:

  - Enhances User Guide and Developer Guide: [\#23](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/23), [\#42](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/42), [\#102](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/102)
  - User Guide:
    - Added documentation for (Pull requersts [\#26](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/26)):
      - description for SeniorLove
      - feature list
      - command summary
      - `delete`, `sort` command
    - Did cosmetic tweaks to existing documentation of features `help`: [\#26](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/26)
    - Added alert boxes to highlight key informations and updated links in the guide: [\#184](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/184)
  - Developer Guide:
    - Added implementation details of the `delete` and `sort` feature (Pull requests [\#102](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/102), [\#139](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/139))
    - Added relevant glossary of terms for SeniorLove (Pull requests [\#42](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/42))
    - Updated links in the guide: [\#184](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/184)

- **Community**:
  - PRs reviewed (with non-trivial review comments): [\#49](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/49), [\#54](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/54), [\#101](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/101), [\#117](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/117), [\#180](https://github.com/AY2122S1-CS2103-T14-1/tp/pull/180)
  - Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/66), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/86), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/353))
  - Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/ziqing26/ped/issues/9), [2](https://github.com/ziqing26/ped/issues/2), [3](https://github.com/ziqing26/ped/issues/8))
