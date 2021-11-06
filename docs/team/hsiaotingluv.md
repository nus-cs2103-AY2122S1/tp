---
layout: page
title: Hsiao Ting's Project Portfolio Page
---
### 1. Introduction

This is my project portfolio for Socius. The document outlines my contributions to the project, including the features and enhancements that I have implemented.

#### 1.1 About the team

Our team consists of Hsiao Ting, Choon Yong, Kevin, Boon Kee and Nathan. We are a group of Computer Science students from the National University of Singapore, and members of AY2022S1-CS2103T-W08-4 in the CS2103T Software Engineering module.

#### 1.2 About the project

The team project (tP) uses a generic application called [AddressBook-Level3 (AB3)](https://se-education.org) as the starting point.

Accordingly, the tP is structured to resemble an intermediate stage of a non-trivial real-life brownfield software project in which we will,
* conceptualize and implement enhancements to a given product, and,
* have it ready to be continued by future developers

Our team has decided to evolve AB3 into a more powerful or more optimized contact management app, Socius.

Socius is a desktop application that can help you, as a CS2103T Software Engineering student, to
* manage your classmates’ contacts
* make friends and
* find teammates!

Socius is optimized for use via a *Command Line Interface (CLI)* while still having the benefits of a *Graphical User Interface (GUI)*.
If you can type fast, Socius can get your contact management tasks done faster than traditional *GUI* apps.

### 2. Summary of contributions

I served as Team Lead for the project, and my team responsibilities include:
  1. Team lead: Responsible for overall project coordination.
  2. Scheduling and tracking: In charge of defining, assigning, and tracking project tasks.
  3. Deliverables and deadlines: Ensure project deliverables are done on time and in the right format.

My responsibilities as a developer include:
  1. Logic package gatekeeper: In charge of merging any commits related Logic package on main repo.
  2. Feature developer: Develop new features and enhancements for Socius.

In the following sections, I will illustrate the above-mentioned enhancements in greter detail, along with the corresponding documentation that I have written for them within the user and developer guides.

#### 2.1 New features and enhancements added

The following describes the enhancements and new features that I added to the project.

#### 2.1.1 Add remark to a person (Feature)
* What it does: allows the user to add a remark to a person by index.
* Justification: This feature improves the product by allowing the user to customise messages for his or her contacts based on his or her impression of the person.
* Highlights: This enhancement affects existing commands and commands to be added in future as it introduces new parameter to the `Person` class.
* Credits: The remark model was taught in Week 6 on the CS2103T website [here](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html).

#### 2.1.2 Delete multiple persons (Feature)
* What it does: allows the user to delete all contacts with matching keywords.
* Justification: This feature improves the product significantly because a user may want to delete multiple contacts and the app should provide a convenient way to do it all with one command.
* Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of the design alternatives.

#### 2.1.3 Sort persons (Feature)
* What it does: allows the user to sort all contacts based on a specified field.
* Justification: This feature improves the product because a user may want to sort his or her contacts and the app should provide a convenient way to do so.
* Highlights: This enhancement required an in-depth analysis of the design alternatives. The implementation too was challenging as it required additional `Comparator` classes for every field.

#### 2.1.4 Add nationality of a person (Enhancement)
* What it does: allows a person's contact to contain nationality defined by the user.
* Justification: This feature improves the product by offering more personal details of a classmate which will aid user in forming project group that meet the nationality ratio requirements.
* Highlights: This enhancement affects existing commands and commands to be added in future as it introduces new parameter to the `Person` class.

#### 2.1.5 Command history (Enhancement)
* What it does: keeps a list of all the other commands that have been run from that terminal session, and allows the user to replay or reuse those commands using the up and down arrow keys instead of retyping them.
* Justification: This feature improves the product significantly because it increases efficiency and convenience for any user who may want to revisit their commands.
* Highlights: The implementation was challenging as it required an in-depth analysis of the `JavaFX` and `Ui`.

#### 2.2 Code contributed

Click on the following links to view the code that I have contributed:

* [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hsiaoting&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=hsiaotingluv&tabRepo=AY2122S1-CS2103T-W08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
* [Functional Code](https://github.com/AY2122S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address)
* [Test Code](https://github.com/AY2122S1-CS2103T-W08-4/tp/tree/master/src/test/java/seedu/address)

#### 2.3. Other contributions

The following describes the various other contributions that I have made to the project.

#### 2.3.1 Project management
  * Set up 77 issues and managed 42 issues on GitHub.
  * Managed 6 milestones, `v1.1` to `v1.4`, on GitHub.
  * Made 25 pull requests on GitHub.
  * Reviewed 35 pull requests on GitHub.
  * Arranged and led the weekly team discussions in setting goals and dividing work amongst members.
  * Keep track of team progress and weekly submission deadlines.

#### 2.3.2 Enhancements to existing features
  * Added a welcome message that will be shown everytime when application is launched. (Pull request [\#262](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/262))
  * Added a help message that summarises all commands available under the Help Command. (Pull requests [\#156](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/156), [\#261](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/261))
  * Wrote additional tests for existing features. (Pull request [\#158](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/158))
  * Wrote tests for `Nationality` model, `remark`, `deletem`, `sort`, comparators and `CommandHistory`. (Pull requests [\#51](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/51), [\#61](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/61), [\#98](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/98), [\#122](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/122), [\#149](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/149))

#### 2.3.3 Documentation

##### 2.3.3.1 About Us
  * Added documentation for AboutUs, including roles and responsibilities of each member. (Pull request [\#49](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/49))

##### 2.3.3.2 User Guide (UG)
  * Added introduction message and table of contents to the UG. (Pull request [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
  * Standardised UG formatting such as titles, table of contents and others. (Pull requests [\#173](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/173), [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
  * Updated and added examples for every command. (Pull requests [\#173](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/173), [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
  * Added app screenshots of examples to aid user understanding. (Pull request [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
  * Fix UG bugs. (Pull requests [\#177](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/177), [\#184](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/184), [\#193](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/193/files), [\#263](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/263))

##### 2.3.3.3 Developer Guide (DG)
  * Added documentation for user profile and value proposition. (Pull request [\#17](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/17))
  * Standardised DG formatting such as titles, table of contents and others. (Pull request [\#108](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/108))
  * Updated details and UML diagrams of the `Logic` class. (Pull request [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))
  * Added implementation details and UML diagrams for the `remark` feature. (Pull request [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))
  * Added implementation details and UML diagrams for the `deletem` feature. (Pull requests [\#101](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/101/files), [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))
  * Added implementation details and UML diagrams for the `sort` feature. (Pull request [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))
  * Added use cases of the `deletem` feature. (Pull request [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))

#### 2.3.4 Community
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#91](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/91), [\#94](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/94), [\#128](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/128), [\#168](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/168))
  * Reported bugs and suggestions for other teams in the class. (examples: [1](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/136), [2](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/147), [3](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/152), [4](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/154), [5](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/155))
