---
layout: page
title: Chew Ee Jia's Project Portfolio Page
---

## **Project: Academy Directory**

Academy Directory is a desktop address book application used for CS1101S tutors to keep track of information related to their students. The user interacts with it using a CLI and mouse, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=cheweejia&tabRepo=AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

### **Project management**:
* Managed releases `v1.2b` - `v1.3b` (2 releases) on GitHub

### **Features**

**New Feature**:
* Added the ability to keep track of students' grades for CS1101S assessments `GradeCommand`. (Pull request [\#55](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/55), [\#84](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/84))
    * What it does: Allows tutors to store students' grades for various CS1101S assessments for tracking and reviewing purposes.
    * Justification: This feature allows tutors to track their students' grades, so that they are able to better monitor their students' progress and tailor their lessons to suit the needs of their students.
    * Highlights: The assessments are currently predetermined based on the module, but it can be modified for tutors to add/delete assessments. It is a standalone feature and would have no impact on most other existing features.

**New Feature**:
* Added the ability to collate and display students' grades for a specified CS1101S assessment `ShowCommand`. (Pull request [\#74](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/74))
    * What it does: Collates the grades of a specified assessment of all students into a list and calculates the average score.
    * Justification: This features supplements the `GradeCommand`, which enables tutors to quickly analyse their students' results without having to calculate the statistics manually.
    * Highlights: Currently only includes the average score, but can be expanded to provide other useful statistics. It is a standalone feature and would have no impact on most other existing features.


**New Feature**:
* Added the ability to manage student's tags `TagCommand`. (Pull request [\#97](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/97))
    * What it does: Allows tutors to add, edit, and remove a student's tag(s).
    * Justification: This feature allows tutors to utilise the tags to identify students who are weaker in particular topics or assessments, which allows them to provide targeted help to their students. 
    * Highlights: The command takes over the responsibility of managing tags from Edit Command. It is a standalone feature and would have no impact on most other existing features.


**Enhancements to existing features**:
* Refactored the Edit Command to deal with only personal details (Name, Phone, Email, Telegram). (Pull request [\#125](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/125))
* Refactored the Find Command to Filter Command, and enhanced the functionality to allow it to filter through both names and tags. (Pull request [\#136](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/136))

### **Community**:
* PR reviewed (with non-trivial review comments): [\#65](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/65)
* Releases for v1.2b and v1.3.trial ([Release](https://github.com/AY2122S1-CS2103T-T15-3/tp/releases/tag/v1.3.trial))
* Demo for v1.2b and v1.3b ([v1.2b](https://drive.google.com/file/d/1nBK5hk6BAzGnTFF1O5X_osgbb-lwmSNy/view?usp=sharing), [v1.3b](https://drive.google.com/file/d/1vr6rXs9irfG7YAMfCa34pnEbdA_7EnLh/view?usp=sharing))

### **Documentation**:

* **User Guide**:
    * Added documentation for the features `grade`, `show`, `tag` and `filter`. (Pull request [\#246](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/246/files))
    * Reformatted the Table of Content and standardised the styling and format of the UG for peer review. (Pull request [\#99](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/99))

* **Developer Guide**:
    * Consolidated and added user stories.
    * Added implementation details for `grade`, `show`, `tag` and `filter` features. (Pull request [\#96](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/96), [\#100](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/100/files), [\#246](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/246/files))
    * Restructured table of content and formatting of DG for peer review and v1.4. (Pull request [\#100](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/100/files))
    * Added manual test cases for `grade`, `show`, `tag` and `filter` features. (Pull request [\#246](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/246/files))
