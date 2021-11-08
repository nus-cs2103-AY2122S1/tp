---
layout: page
title: Chua Min Hong's Project Portfolio Page
---

### Project: AniList

AniList is a desktop Command Line Interface app for managing animes that the user is watching, has watched, and finished watching. AniList is written in Java and the GUI is created with JavaFX.

AniList was created based off AddressBook - Level 3, an address book application used for teaching Software Engineering Principles.

Given below are my contributions to the project.

* **New Feature**: Added the functionality to update the Status of an Anime.
  * What it does: Allows the user to update the current status of an anime the user has added into AniList.
  * Justification: This feature is a basic feature of our application as it helps user to keep track of the status of each anime in AniList.
  * Highlights: This required less work as it is similar to UpdateEpisode. To make it more optimized for CLI usage, I added an alias for each status.


* **New Feature**: Added the ability to add / remove genres from Animes.
  * What it does: Allows users to tag or remove genres from an anime. There is a set of pre-defined genres which the users can use.
  * Justification: This feature is essential to our app as it allows for better management of anime. This helped to pave the way for other features such as user statistics and searching based on genre.
  * Highlights: We came up with many ways to implement this feature. In the end, we went with using an action prefix to specify whether the command is an add or delete command. I have also added an alias for each action.
  * Credits: Other team members that assisted with this feature: Cui Langyuan [[github](https://github.com/clyveycui)]


* **New Feature**: Added the ability to list the set of predefined genres.
  * What it does: Allows users to list the set of predefined genres.
  * Justification: This feature will allow users to know which genres they can use for commands relating to genre.
  * Highlights: I had to think of what genres should AniList contain. The implementation was rather easy.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t10&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=auhc99&tabRepo=AY2122S1-CS2103T-T10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub


* **Enhancements to existing features**:
  * Refactoring of the codebase at the start of the project to fit AniList specification (PR [\#37](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/37), [\#50](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/50))
  * Updated the add command to make use of episode and status (PR [\#80](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/80))
  * Updated the help command to include all new features (PR [\#88](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/88))
  * Refactoring of the codebase at the end of the project to improve code quality and test coverage (PR [\#202](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/202), [\#206](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/206))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `status` and `exit` (PR [\#52](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/52/files), [\#194](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/194/files))
    * Updated the details for most features (PR [\#194](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/194))
  * Developer Guide:
    * Updated the UML diagrams to fit AniList specification (PR [\#117](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/117))
    * Added implementation details of the `Tabs` feature (PR [\#117](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/117))
    * Updated the user stories and use cases for all features (PR [\#117](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/117))


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#81](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/81), [\#97](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/97) and many more.
  * Reported 11 bugs for other teams during Mock PE.
