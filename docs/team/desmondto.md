---
layout: page
title: Desmond To's Project Portfolio Page
---

### Project: WedFast

WedFast is a desktop address book application used for managing contacts of your dream wedding. The user interacts with
it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete contact by specifying name.
  * What it does: allows the user to delete a contact without referring to the index in the contact list.
  * Justification: This feature improves the product significantly because a user can delete a contact quickly as long
                   as he/she remembers the name, without referring to the index of the contact list (which is slow). 
                   Thus, in the long run, this feature saves them large amount of time.
  * Highlights: This enhancement affects existing `delete` command. It required an in-depth analysis of design alternatives. 
                The implementation too was challenging as it required changes to existing `delete` command.

* **New Feature**: Added a countdown command that helps the user calculating the number of days left until his/her wedding.
  * What it does: shows the user the countdown to his/her wedding.
  * Justification: This feature is helpful in tracking if their wedding planning progress matches with their 
                   expectation, so they can rectify their plan periodically.

* **New Feature**: Added a cost-sum-checking command that helps the user calculating the total cost of his wedding (or 
                   a particular item/category).
  * What it does: shows the user the total expenses of his/her wedding (or the cost of people he/she hiring for the
                  wedding).
  * Justification: This feature is helpful in tracking if the expenses is exceeding the couple's ability, so they can
                   eliminate unnecessary expenses, thus saving money.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=w10-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=DesmondTo&tabRepo=AY2122S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2.1` - `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Wrote additional tests for features implemented to increase coverage by 4.16% (Pull requests [\#187](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/187),
    [\#188](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/188), [\#189](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/189))

* **Documentation**:
  * User Guide:
    * Updated major contents of the documentation, including updating the `table of contents`, `product overview`, 
      `features implemented`(screenshots and descriptions), `command summary`.
      (Pull requests [\#88](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/88), [\#94](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/94),
       [\#100](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/100/))
    * Did cosmetic tweaks to existing documentation by adding `cover page`, `about the user guide`, `glossary`, 
      rearranging `features` in chronological use
      (Pull request [\#94](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/94))
  * Developer Guide:
    * Updated the design details/diagrams of the `Logic`, `Model`, and `Parser` component.
      (Pull request [\#186](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/186))
    * Added implementation details of the `delete-by-name`, `countdown-to-the-wedding`, and `cost-sum-checking` features.
      Several UML diagrams were added such as object diagrams, sequence diagrams and activity diagrams.
      (Pull request [\#186](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/186))

* **Contributions to team-based tasks**:
  * Setting up the GitHub team organization and repo
  * Setting up Gradle
  * Release management
  * Setting up milestone
  * Updating user and developer docs that are not specific to a feature i.e. documenting the product overview, about the
    user guide, and glossary
  * Setting up the project website
  * Incorporating more useful tool into the project workflow, i.e. Codecov

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#29](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/29), 
    [\#35](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/35), [\#43](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/43),
    [\#49](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/49), [\#64](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/64)
    [\#65](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/65), [\#67](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/67),
    [\#82](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/82), [\#87](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/87),
    [\#93](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/93), [\#95](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/95),
    [\#107](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/107)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/DesmondTo/ped/issues/1),
    [2](https://github.com/DesmondTo/ped/issues/2), [3](https://github.com/DesmondTo/ped/issues/3), [4](https://github.com/DesmondTo/ped/issues/4),
    [5](https://github.com/DesmondTo/ped/issues/5), [6](https://github.com/DesmondTo/ped/issues/6), [7](https://github.com/DesmondTo/ped/issues/7),
    [8](https://github.com/DesmondTo/ped/issues/8), [9](https://github.com/DesmondTo/ped/issues/9), [10](https://github.com/DesmondTo/ped/issues/10),
    [11](https://github.com/DesmondTo/ped/issues/11), [12](https://github.com/DesmondTo/ped/issues/12), [13](https://github.com/DesmondTo/ped/issues/13))

* **Tools**:
  * Integrated a new Github plugin (CircleCI) to the team repo
