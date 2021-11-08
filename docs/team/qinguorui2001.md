---
layout: page
title: QIN GUORUI's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA<sup>2</sup> is a desktop application designed for teaching assistants from the School of Computing (SoC) at the National University of Singapore (NUS)
to manage student information and keep track of students’ assignment submissions.The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java}*

* **New Feature**: Added the ability to add assignments into the student's assignment list.
    * What it does: Allows the user to add a specific assignment to a student. Newly added assignment will be immediately shown in the assignment list.
    * Justification: This feature ensures the basic function of the product because tutors are responsible for allocating assignments to their students.
    * Highlights: This enhancement provides the basis for other features. (example: `giveall`) The implementation was challenging as it involves the analysis of how it can 
      respond to user input and manipulate the relationships with other classes like `Assignment` class.

* **New Feature**: Added the ability to delete assignments from the student's assignment list.
    * What it does: Allows the user to delete a specific assignment from a student's assignments. Newly deleted assignment will be immediately removed from the assignment list.
    * Justification: This feature ensures the basic function of the product because tutors should be able to delete assignments which they wrongly assigned.
    * Highlights: This enhancement provides the basis for other features. (example: `clean`) The implementation was challenging as it refers to the command pattern of existing commands, and 
      it involves detailed analysis of current backend code to figure the pattern out.

* **New Feature**: Added the ability to mark assignments as completed in the student's assignment list.
  * What it does: Allows the user to mark a specific assignment of a student as completed. Newly marked assignment will be immediately shown as `Completed` in the assignment list.
  * Justification: This feature ensures the basic function of the product because tutors are responsible for marking assignments of their students.
  * Highlights: The implementation was challenging as it involves how the method in `Model` interface can finally change
    the particular `Assignment` instance over a series of internal interactions between each component in `Model`.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=qinguorui2001&tabRepo=AY2122S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Reviewed the code quality of team members.

* **Enhancements to existing features**:
    * Improve the internal implementation of undo command. (Pull requests [\#122](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/122), [\#224](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/224))

* **Documentation**:
    * User Guide:
        * Updated the draft of quick start. [\#251](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/251)
        * Added documentation for the features `give`, `done`, `remove`, `undo` and `redo`. [\#56](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/56), [\#139](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/139)
        * Fixed bugs reported in PE dry run and improve the view of the guide. [\#210](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/210)
    * Developer Guide:
        * Added implementation details of the `remove`, `give`, `undo`, `done` and `redo` feature. [\#120](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/120), [\#116](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/116)
        * Polished the non-functional requirements. [\#239](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/239)
        * Drafted user stories and glossary. [\#49](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/49)
        * Added use case for `give` feature. [\#69](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/69)
        * Added Manual test case for some scenarios [\#243](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/243)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#104](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/104), [\#216](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/216)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/305), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/224), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/81))


