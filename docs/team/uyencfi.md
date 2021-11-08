---
layout: page
title: Ngo Ngoc Phuong Uyen's Project Portfolio Page
---
# Project: EdRecord

## Overview
EdRecord is a **desktop student management app, for teaching assistants to manage their student contacts**. The EdRecord codebase was initially adapted from [AddressBook Level 3](https://se-education.org/addressbook-level3/), a desktop address book application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

## Summary of Contributions

### Features

* **New Feature**: Create assignments.
    * What it does: Allows the user to create assignments in a module.
      
    * Justification: This feature is highly relevant to the product because it supports an important aspect of being a teaching assistant—managing module assignments. Users are able to add in new assignments, which can be used for recording submission status and grades later.
      
    * Highlights: A list is required to store the assignments under a module. Adapting test code for Module and Storage was quite challenging, as certain tests and functionality was comparing Module codes for equality only. After adding assignments, there needs to be two separate forms of equality: `isSameModule()` which checks solely for module code equality, and `equals()` which also checks equality of assignment list.
    

* **New Feature**: Delete assignments.
    * What it does: Allows the user to delete assignments in a module.

    * Justification: The user should be able to remove assignments in case they made mistakes, or when the module coordinators make changes to the assessment components.

    * Highlights: When an assignment is deleted, all grades for that assignment should also be deleted. This requires a way to pass the assignment between Model and EdRecord for finding and deleting, without introducing unnecessary coupling.


* **New Feature**: Edit assignments.
    * What it does: Allows the user to edit assignments in a module.

    * Justification: This feature improves the product significantly because users can edit an incorrect assignment, instead of having to delete and create a new assignment.

    * Highlights: It is important to ensure that the new assignment is valid, i.e. the edited maximum score doesn't fall below any existing student grade, or the edited weightage doesn't bring the module's total weightage to above 100%. These conditions are validated by checking the current state of `Model`, which includes checking the person list and the assignment list. 


### Code contributed

[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=CS2103-W14-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=uyencfi&tabRepo=AY2122S1-CS2103-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

[Pull requests](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=is%3Apr+author%3Auyencfi)

### Documentation

* **User Guide**:
  * Completed documentation for assignments features: [\#205](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/205)
  * Reorganizing and formatting: [\#205](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/205)
* **Developer Guide**:
  * Added all use cases: [\#65](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/65) and [\#76](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/76)
  * Added implementation details and diagrams of Assignments features: [\#224](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/224)
  * Updated diagrams: [\#227](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/227)

### Community

* PRs reviewed (with non-trivial review comments): [\#74](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/74), [\#119](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/119), [\#136](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/136)
* Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/109), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/211), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/145#issuecomment-909212519))
* Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/208), [2](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/204), [3](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/200), [4](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/194), [5](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/196))
