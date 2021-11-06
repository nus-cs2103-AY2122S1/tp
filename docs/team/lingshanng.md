---
layout: page
title: Ng Lingshan's Project Portfolio Page
---

### Project: TuitionAddressBook (TAB)

Tuition Address Book (TAB) is an all-in-one desktop application for 1-to-1 private home tutors that helps to keep track of students and their respective lesson information. TAB utilises a Command Line Interface (CLI) to allow tutors to perform tasks efficiently, while providing a clean Graphical User Interface (GUI) for tutors to easily view student and lesson data.

Given below are my contributions to the project.

* **New Feature**: Added the ability to cancel lessons in TAB, and display recurring lessons with cancelled dates on the Calendar.
  * What it does: Allows the user to cancel and uncancel lessons in TAB.
  * Justification: This feature is necessary so that tutors are able to reschedule lessons by cancelling a lesson date.
  * Highlights: This enhancement required an in-depth analysis of design alternatives in considering how to represent cancelled lessons. The implementation too was challenging as CalendarFX did not support excluding specific dates from recurring lesson entries, and I had to implement a work around for it. In addition, the implementation of the lesson clashing logic needed to be well thought out as there were many side cases that could easily introduce bugs if missed.

* **Enhancement to existing Feature**: Adapting from the existing find feature, I added the ability to find students by all student fields, and specify a match condition. 
  * What it does: Allows the user to find students by all other student fields. It also allows users to customise the match condition, allowing for a more powerful search function.
  * Justification: This feature is necessary so that tutors are able to search and filter the list for a specific group of students.
  * Highlights: This enhancement required much consideration of users' needs, to implement a search feature that is most flexible and usable. This included consideration of whether keywords should be a partial word match or a whole word match, and whether all keywords are required to be matched. It also complements the fee calculator, where tutors may want to search for students with the "unpaid" tag, for instance. The inital design of the code was well thought out, allowing easy extension of the feature to search for students by lesson fields afterwards.
  
* **Other Enhancements**:
  * Retrieve last command [\#111](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/111)
  * Refactoring AB3 terms to TAB [\#127](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/127)
  * Improve sample data with lessons [\#183](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/183)
  * Improve index error messages [\#280](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/280)
  * Improve lesson ordering [\#311](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/311)
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=lingshan&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lingshanng&tabRepo=AY2122S1-CS2103T-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed closing of milestones `v1.1` and `v1.3` on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` and cancelling section of `ledit` [\#351](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/351)
    * About this guide section [\#153](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/153)  
    * Did the cosmetic formatting and language check of the user guide [\#90](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/90), [\#91](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/91), [\#96](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/96), [\#153](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/153)
  * Developer Guide:
    * Added implementation details of the `find` feature and lesson cancelling.
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [\#19](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/19), 
    [\#25](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/25), 
    [\#38](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/38),
    [\#39](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/39)
    [\#109](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/109)
    [\#112](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/112)
    [\#118](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/118)
    [\#126](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/126)
    [\#170](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/170)
    [\#186](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/186)
    [\#191](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/191)
    [\#208](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/208)

* _{you can add/remove categories in the list above}_
