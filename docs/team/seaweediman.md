---
layout: page
title: Mohamed Noriman's Project Portfolio Page
---

### Project: HR Manager

HR Manager is a candidate and interview management application used for helping HR Managers of small organisations
or businesses in managing candidates, job positions and interviews all in one application.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* Code contributed: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=seaweed&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=seaweediman&tabRepo=AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added the ability to remark candidates. `remark_c`
  * This feature allows users to give remarks to a specific candidate, allowing users to give comments to noteworthy candidates.

* **New Feature**: Added the ability to add job positions. `add_p`
  * This feature allows users to add job positions into HR Manager, allowing them to keep track of positions in a company.

* **New Feature**: Added the ability to delete interviews. `delete_i`
  * This feature allows users to delete interviews from HR Manager, enabling them delete unwanted interviews from HR Manager to reduce clutter.

* **New Feature**: Added the ability to 'unassign' candidates from a specific interview. `unassign`
  * Since `edit_i` can't be used to remove candidates from an interview, this feature was added such that users can remove candidates from a scheduled interview.
  
* **Enhancements: Updated commands to allow multiple candidate indexes after a single `c=` prefix input**
  * Before, to add candidate indexes to add_i command, you had to do `c=1 c=2`. Now, you can put it under a single prefix input, 
   `c=1 2`.
  * Highlights : This enhancement required the use of implementing a new `parseCandidateIndexes` method to parse multiple candidate indexes at the same time.
  * Refer to [Issue #131](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/131) and [PR #144](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/144) for more details.

* **Enhancements: Updated commands to automatically update all relevant lists displayed on the GUI at the end of a command's execution.**
  * Before, after every command, the lists will not update automatically and only updates when you click on the person, position or interview card. Now, after each command, the lists will be updated automatically.
  * Highlights : This required changing the constructor of `CommandResult` to take in an enum `CommandType`. In `MainWindow`, new methods were created to handle updating specific lists for different types of commands.
  * Refer to [Issue #219](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/219) and [PR #232](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/232) for more details.

* **Enhancements: Updated `add_c` and `edit_c` so that it throws an error when a closed `Position` is inputted.**
  * Before, you could use `add_c` to add a candidate who is applying for a closed position in the company. Similarly, you could use `edit_c` to edit a candidate to apply for a closed position in the company.
  * Refer to [Issue #79](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/79) and [PR #97](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/97) for more details.

* **Enhancements: Updated commands that take in position such that regardless of what the user inputs, the position that is stored in the candidate or interview object has the same naming convention as the one in the position list.**
  * Before, `BOOKKEEPER` and `Bookkeeper` did not refer to the same position, making it possible to have both of them in the app at the same time. With this change, they both refer to the same position.
  * Refer to [Issue #206](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/206) and [PR #235](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/235) for more details.
  
**Contributions to team-based tasks** 
* Integrated Java CI into the team repository
* Separated candidate, position, interview classes into separate packages
* Helped setup github pages for the team repository
* Updated index.md to change all AB3 references to HR Manager references.

**Review contributions**
* [Full list of bugs and suggestions for other teams](https://github.com/seaweediman/ped/issues/)
* [Full list of PRs reviewed](https://github.com/AY2122S1-CS2103T-W13-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aseaweediman)

**Contributions to the UG**
* Added the documentation for the following features: `remark_c`, `add_p`, `delete_i`, `unassign`.

**Contributions to the DG**
* Added a section for add features which provides an overview of the implementation of all the add commands. The section also includes a UML diagram for the sequence diagram of the add
  position command `add_p`. Refer to [PR #259](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/259)
* Updated class diagram for Model, Logic and UI Component. Refer to [PR #281](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/281) and [PR #301](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/301) for more details.
