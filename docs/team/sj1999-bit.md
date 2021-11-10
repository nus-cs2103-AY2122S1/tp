---
layout: page
title: sj1999-bit's Project Portfolio Page
---

### Project: WedFast

WedFast is a desktop address book application used for managing contacts of your dream wedding. The user interacts with
it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add tag to multiple individuals at the sametime.
  * What it does: add tags to multiple users in one command instead of many
  * Justification: This feature improves the product significantly by reducing the amount of tedious steps needed to categorise a large group of
                    individuals into the same tag.
  * Highlights: This command is independent from the original tag features, and thus it increases the flexibility of the app's utility.

* **New Feature**: Added a tagInfo command to generate a report on the number of contacts categorised under each tag.
  * What it does: goes through a list of specified tags or all the current tags in the system, summarise the total number of contacts for each tag.
  * Justification: This feature is helpful in tracking the number of contacts under each tag, to give the user a much clearer picture for how many more contacts
                    he may need for each tag.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=sj1999-BIT&tabRepo=AY2122S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

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

  * Developer Guide:
    * Updated the design details/diagrams of the `Logic`, `Model`, and `Parser` component.
      (Pull request [\#186](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/186))
    * Added implementation details of the `delete-by-name`, `countdown-to-the-wedding`, and `cost-sum-checking` features.
      Several UML diagrams were added such as object diagrams, sequence diagrams and activity diagrams.
      (Pull request [\#186](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/186))

* **Contributions to team-based tasks**:
  * Release management
  * Setting up milestone
  * Incorporating more useful tool into the project workflow, i.e. Codecov

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#29](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/29), 
    [\#17](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/17), [\#23](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/23),
    [\#35](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/35), [\#49](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/49),
    [\#75](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/75), [\#84](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/84),
    [\#85](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/85), [\#87](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/87),
    [\#96](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/96), [\#101](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/101),
    [\#180](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/180), [\#181](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/181),
    [\#182](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/182), [\#191](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/191),
    [\#203](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/203),
    
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/DesmondTo/ped/issues/1),
    [2](https://github.com/sj1999-BIT/ped/issues/2),[2](https://github.com/sj1999-BIT/ped/issues/2), [3](https://github.com/sj1999-BIT/ped/issues/3), [4](https://github.com/sj1999-BIT/ped/issues/4),

* **Tools**:
  * Integrated a new Github plugin (CircleCI) to the team repo
