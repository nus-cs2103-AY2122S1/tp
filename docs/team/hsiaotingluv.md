---
layout: page
title: Hsiao Ting's Project Portfolio Page
---
### Project: Socius

Socius is a desktop application that can help you, as a CS2103T Software Engineering student, to manage your classmates’ contacts, make friends and find teammates!
It is optimized for use via a CLI while still having the benefits of a GUI.

I served as Team Lead for the project, and my team responsibilities include:
1. Team lead: Responsible for overall project coordination.
2. Scheduling and tracking: In charge of defining, assigning, and tracking project tasks.
3. Deliverables and deadlines: Ensure project deliverables are done on time and in the right format.

My responsibilities as a developer include:
1. Logic package gatekeeper: In charge of merging any commits related Logic package on main repo.
2. Feature developer: Develop new features and enhancements for Socius.

Given below are my contributions to the project.

**New Feature:** Added `Remark` model to a `Person` class.
* What it does: allows the user to add a remark to a person by index.
* Justification: This feature improves the product by allowing the user to customise messages for his or her contacts based on his or her impression of the person.
* Highlights: This enhancement affects existing commands and commands to be added in future as it introduces new parameter to the `Person` class.
* Credits: The `Remark` model was taught in Week 6 on the CS2103T website [here](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html).

**New Feature:** Added the ability to delete multiple persons.
* What it does: allows the user to delete all contacts with matching keywords.
* Justification: This feature improves the product significantly because a user may want to delete multiple contacts and the app should provide a convenient way to do it all with one command.
* Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of the design alternatives.

**New Feature:** Added the ability to sort contacts.
* What it does: allows the user to sort all contacts based on a specified field.
* Justification: This feature improves the product because a user may want to sort his or her contacts and the app should provide a convenient way to do so.
* Highlights: This enhancement required an in-depth analysis of the design alternatives. The implementation too was challenging as it required additional `Comparator` classes for every field.

**New Enhancement:** Added `Nationality` model to a `Person` class.
* What it does: allows a person's contact to contain nationality defined by the user.
* Justification: This feature improves the product by offering more personal details of a classmate which will aid user in forming project group that meet the nationality ratio requirements.
* Highlights: This enhancement affects existing commands and commands to be added in future as it introduces new parameter to the `Person` class.

**New Enhancement:** Added command history which allows for the ability to navigate between all previously entered commands.
* What it does: keeps a list of all the other commands that have been run from that terminal session, and allows the user to replay or reuse those commands using the up and down arrow keys instead of retyping them.
* Justification: This feature improves the product significantly because it increases efficiency and convenience for any user who may want to revisit their commands.
* Highlights: The implementation was challenging as it required an in-depth analysis of the `JavaFX` and `Ui`.

**Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hsiaoting&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=hsiaotingluv&tabRepo=AY2122S1-CS2103T-W08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

**Project management:**
* Set up 77 issues and managed 42 issues on GitHub.
* Managed 6 milestones, `v1.1` to `v1.4`, on GitHub.
* Made 25 pull requests on GitHub.
* Reviewed 35 pull requests on GitHub.
* Arranged and led the weekly team discussions in setting goals and dividing work amongst members.
* Keep track of team progress and weekly submission deadlines.

**Enhancements to existing features:**
* Added a welcome message that will be shown everytime when application is launched. (Pull request [\#262](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/262))
* Added a help message that summarises all commands available under the Help Command. (Pull requests [\#156](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/156), [\#261](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/261))
* Wrote additional tests for existing features. (Pull request [\#158](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/158))
* Wrote tests for `Nationality` model, `remark`, `deletem`, `sort`, comparators and `CommandHistory`. (Pull requests [\#51](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/51), [\#61](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/61), [\#98](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/98), [\#122](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/122), [\#149](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/149))

**Documentation:**
* About Us:
    * Added documentation for AboutUs, including roles and responsibilities of each member. (Pull request [\#49](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/49))
* User Guide (UG)
    * Added introduction message and table of contents to the UG. (Pull request [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
    * Standardised UG formatting such as titles, table of contents and others. (Pull requests [\#173](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/173), [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
    * Updated and added examples for every command. (Pull requests [\#173](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/173), [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
    * Added app screenshots of examples to aid user understanding. (Pull request [\#280](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/280))
    * Fix UG bugs. (Pull requests [\#177](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/177), [\#184](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/184), [\#193](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/193/files), [\#263](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/263))
* Developer Guide (DG)
    * Added documentation for user profile and value proposition. (Pull request [\#17](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/17))
    * Standardised DG formatting such as titles, table of contents and others. (Pull request [\#108](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/108))
    * Updated details and UML diagrams of the `Logic` class. (Pull request [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))
    * Added implementation details and UML diagrams for the `remark`, `deletem` and `sort` features. (Pull requests [\#101](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/101/files), [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))
    * Added use cases of the `deletem` feature. (Pull request [\#270](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/270))

**Community**
* PRs reviewed (with non-trivial review comments): (Pull requests [\#91](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/91), [\#94](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/94), [\#128](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/128), [\#168](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/168))
* Reported bugs and suggestions for other teams in the class. (examples: [1](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/136), [2](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/147), [3](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/152), [4](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/154), [5](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/155))
