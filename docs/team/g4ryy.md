---
layout: page
title: Gary Yansen's Project Portfolio Page
---

## Project: NewAddressBook

NewAddressBook is a desktop address book application used by Singapore secondary school teachers to manage their student and teacher contacts, optimized for use via a Command Line Interface (CLI). The user interacts with it using a CLI, and
it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

## Contributions Summary

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=g4ryy&tabRepo=AY2122S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


### New Features
* **New Feature**: Refactored existing code to support student and teacher classes by applying polymorphism. [[#38](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/38), [#57](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/57)]
  * What it does: allows the user to store 2 different types of person contacts (students and teachers). These two types of persons share a number of fields, but they also have different fields associated with them.
  * Justification: Previously, the user can only manage one type of contact, which does not allow the user to store student-specific fields 
  and teacher-specific fields. This feature improves the product significantly because the user can now manage their student contacts and teacher contacts
  separately, each with their own set of fields. For example, the `form class` field is present in a student contact, but not in a teacher contact. Similarly, a teacher
  contact contains the `office table number` field, which is not present in a student contact.
  * Highlights: This enhancement provides the skeleton for the subsequent implementation of student-specific and teacher-specific commands. It also provides support for the subsequenet
  implementation of two separate lists that allows the user to view and operate on students contacts and teachers contacts separately.

* **New Feature**: Added feature to schedule upcoming meetings. [[#96](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/96), [#137](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/137)]
  * What it does: allows the user to schedule upcoming school-related meetings.
  * Justification: This feature improves the product significantly because it now allows the user to not only manage their contacts,
  but also keep track of upcoming school-related meetings.
  * Highlights: This feature required the implementation of a meetings list that ensures that there is no conflict between existing
  meetings and the meeting to be added. The meetings list is also implemented such that it is sorted in ascending order of time 
  whenever a new meeting is added so that the user can focus on the most recent upcoming meetings.

* **New Feature**: Added feature to automatically remove expired meetings. [#96](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/96)
  * What it does: automatically removes expired meetings whenever the user launches the app.
  * Justification: This feature improves the product significantly because it saves the user the trouble of having to enter commands to remove expired meetings.

* **New Feature**: Added feature to clear all meetings. [#148](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/148)
  * What it does: allows user to remove all meetings in one go.
  * Justification: allows user to remove all meetings from the app with just one command in the event that they want to cancel all upcoming meetings.
  
### Project Management
* Set up the GitHub team organisation and repository
* Managed releases `v1.2`, `v1.3.trial`, `v1.3`, `v1.3.1`, `v1.4` (5 releases) on GitHub
* Set up and maintained issue tracker on GitHub
  

### Documentation
* **User Guide:**
  * Made various changes to improve readability, e.g. adding table of contents, separating into different sections. [#129](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/129)
  * Added various UI images in the `User Interface (UI)` section. [#151](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/151)

* **Developer's Guide**:
  * Added implementation details of the `meet` command. [#100](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/100)
  * Added more non-functional requirements. [#286](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/286)
  * Added Effort appendix. [#286](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/286)
  * Added user stories. [#286](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/286)

### Community
  * PRs reviewed: [#74](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/74), [#93](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/93)
  * Reported bugs and suggestions for other teams in the class: [#1](https://github.com/AY2122S1-CS2103-F10-1/tp/issues/134), [#2](https://github.com/AY2122S1-CS2103-F10-1/tp/issues/133), [#3](https://github.com/AY2122S1-CS2103-F10-1/tp/issues/126), [#4](https://github.com/AY2122S1-CS2103-F10-1/tp/issues/123)



