---
layout: page
title: Eliana's Project Portfolio Page
---

### Project: Tuition Address Book

Given below are my contributions to the project.

* **New Feature**: Added the ability to manage lessons in TAB.
  * Functionality: Allow the user to add, edit and delete lessons for students in TAB.
  * Justification: This feature is necessary for private 1-to-1 tutors to manage their lessons with respect to their students. Private tutors can have a consolidated record of all their students' information in a single application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to the existing model and UI of the original AB3. Many considerations had to be taken into account for the detection of clashes between lessons as well. Additionally, this enhancement affects commands to be added in the future.
  * Credits: *Ole V.V. for the [detection of overlapping dates](https://stackoverflow.com/questions/60785426/)*

* **New Feature**: Added a view command to view lessons in TAB.
  * Functionality: Allow user to view the list of lessons for a particular student by typing in the command.
  * Justification: This feature allows user to be able to select students to view their lessons without having to use the mouse. This isolates the lessons with respect to the student for tutors to manage the lessons of each student better.
  * Highlights: This enhancement required a good understanding of the UI and how to incorporate a new component to the existing UI of the application.

* **Code contributed**: [RepoSense link]() (Relevant pull requests [\#25](), [\#89](), [\#118](), [\#184](), [\#186](), [\#250]())

* **Project management**:
  * Managed releases `v1.2` (1 release) on GitHub

* **Enhancements to existing features**:
  * Updated the fees calculator logic to consider end date of recurring lessons. (Pull requests [\#208]())
  * Wrote additional tests for existing features to increase coverage from 62.63% to 68.30% (Pull requests [\#120]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `ladd`, `ledit` and `ldelete` ([\#63](), [\#119](), [\#149]())
    * Did cosmetic tweaks to existing documentation for Quick Start: [\#76]()
    * Add brief overview for each section in UG: [\#162]()
    
  * Developer Guide:
    * Added implementation details and test cases for the `ladd` feature.
    * Added implementation details and test cases for the `ledit` feature.
    * Added implementation details and test cases for the `ldelete` feature.
    * Added use cases for lesson management features.
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#261](), [\#170](), [\#93](), [\#183](), [\#126](), [\#105](), [\#109](), [\#165]()
  
