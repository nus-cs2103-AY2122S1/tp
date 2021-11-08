---
layout: page
title: Linda Tom's Project Portfolio Page
---

### Project: WedFast

WedFast is a desktop address book application used to better orgnaise your wedding through effective categorisation and management of relevant contacts (e.g. photographer, caterer, guests). The user interacts with
it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete contacts by tag.
  * What it does: allows the user delete multiple contacts at once as long as they share the same tag.
  * Justification: This feature improves the product's efficiency significantly because a user can delete many contacts at once without having to type out the contacts' names or indexes one by one.
  * Highlights: This enhancement affects existing `delete` command. It required an in-depth analysis of design alternatives.

* **New Feature**: Added a report command that allows user to view the breakdown of the status and expenditure across different tags
  * What it does: shows the user the estimated expenses and number of people involved in user's wedding
  * Justification: By being able to view and analyse the report, this feature helps the user better track and manage the overall progress and costs of their wedding.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=w10-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Linda124&tabRepo=AY2122S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2.1` - `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Implemented basic GUI changes at the beginning (Pull requests [\#54](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/54))
  * Wrote additional tests for existing delete command which helped increase total coverage by 1.31% (Pull requests [\#195](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/195))
  * Added a confirmation popup window for clear command to alert the user that addressbook has been cleared (Pull requests [\#95](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/95), [\#104](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/104), [\#184](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/184))
  * Made minor amendments to parser and other commands due to fields like address, price, important information being made optional (Pull requests [\#95](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/95), [\#184](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/184))
  * Made minor amendements to edit command due to additional fields such as important information, price and status being included (Pull requests [\#54](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/54), [\#95](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/95))

* **Documentation**:
  * User Guide:
    * Added documentation for `add`, `edit` and `track` commands during the earlier stages of implementation
      (Pull requests [\#34](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/88))
    * Added documentation for `report` command
      (Pull requests [\#104](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/104))
    * Did minor cosmetic changes to documentation by changing wording of content
      (Pull request [\#206](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/206))
  * Developer Guide:
    * Added implementation and testing related information for the `delete by tag` and `report` features. Relevant diagrams were also included.
      (Pull request [\#214](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/214))
    * Added a part of the documentation for use cases during the earlier stages of implementation
      (Pull requests [\#24](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/24))
    * Made modifications to user stories 
      (Pull requests [\#231](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/231))

* **Contributions to team-based tasks**:
  * Maintaining the issue tracker
  * Setting up milestone
  * Updating user and developer docs that are not specific to a feature i.e. documenting the product overview, about the
    user guide, and glossary

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#175](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/175)
  * Reported bugs and suggestions for other teams in the class (examples:
    [2](https://github.com/Linda124/ped/issues/2), [3](https://github.com/Linda124/ped/issues/3), [4](https://github.com/Linda124/ped/issues/4),
    [5](https://github.com/Linda124/ped/issues/5), [6](https://github.com/Linda124/ped/issues/6), [7](https://github.com/Linda124/ped/issues/7),
    [8](https://github.com/Linda124/ped/issues/8))
