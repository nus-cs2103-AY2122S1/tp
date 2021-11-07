---
layout: page
title: Jian Wei's Project Portfolio Page
---

## Project: Academy Directory

Academy Directory is a desktop address book application used for CS1101S tutors to keep track of information related to their students. The user interacts with it using a CLI and mouse, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jianoway&tabRepo=AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


### **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

### **Features**

**New Feature**: 
* Added the ability to keep track of a student's Studio Attendance (Pull requests [\#49](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/49), [\#60](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/60))
    * What it does: Keeps track of whether a student has attended a Studio.
    * Justification: This is a core feature for tutors to be able to keep track of their student's Attendance.
    * Highlights: Easy to use and intuitive since te information required for a tutor to key in is minimal. It is a standalone feature and would have no impact on most other existing features (e.g Add and Edit).

**New Feature**: 
* Added the ability to keep track of a student's Studio Participation Score (Pull request [\#64](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/64))
  * What it does: Keeps track of a student's Participation Score in any Studio session.
  * Justification: This is a core feature for tutors to be able to keep track of their student's Participation.
  * Highlights: Currently set to a range of 0 to 500 inclusive to fit the needs of CS1101S, but is easily changeable with future iterations such as an admin console. It is a standalone feature and would have no impact on most other existing features (e.g Add and Edit).


**New Feature**: 
* Added the ability to sort AcademyDirectory's student list. (Pull request [\#87](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/87))
  * What it does: Sorts the student list stored in the AcademyDirectory based on performance in specific assessments or on average, participation in tutorials as well as their name. Sorting can be done in either ascending or descending order.
  * Justification: This feature improves the product significantly as it allows tutors to sort the student list to suit their needs at any given time with a simple CLI input.
  * Highlights: Sorts with the optimized `List` sort with comparator method. It is a standalone feature and would have no impact on most other existing features (e.g Add and Edit).


**Enhancements to existing features**:
* Refactored the original code for Address Book 3 to suit Academy Directory. (Pull request [\#47](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/47))
* Updated the `Student` class (was `Person`) to support additional field `StudioRecord`.

### **Documentation**:

  * **User Guide**:
    * Added documentation for the features `attendance` and `participation`. (Pull request [\#69](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/69))
    * Fix typo from original Address Book 3 (Pull request [\#201](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/201))
    * Improved Table of Contents. (Pull request [\#128](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/128)) 
    
  * **Developer Guide**:
      * Added implementation details of the existing `add` and `edit` features.
      * Added Non-Functional Requirements. (Pull request [\#201](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/201))
      * Add implementation details of `participation`, `attendance` and `sort` features. (Pull requests [\#91](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/91), [\#92](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/92))
        * Designed the UML diagrams for all 3 features.
      * Refactored existing Address Book 3 diagrams to fit Academy Directory. (Pull request [\#91](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/91))

  * **Community**:
      * PRs reviewed (with non-trivial review comments): [\#148](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/148), [\#198](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/198), [\#229](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/229)
      * Actively contributed to all team meetings and discussions for all related releases.
      * Reported bugs and suggestions for other teams in the class i.e. PED.

