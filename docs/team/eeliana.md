---
layout: page
title: Eliana's Project Portfolio Page
---

### Project: Tuition Address Book
Tuition Address Book (TAB) is an all-in-one desktop application for 1-to-1 private home tutors that helps to keep track of their students, and their respective lesson information Everything on TAB can be done without lifting your hands from the keyboard! TAB empower tutors to provide the best quality home tuition service. With TAB, users can now manage students’ contact details faster than a typical mouse/GUI driven app.

Given below are my contributions to the project.

* **New Feature**: Added the ability to manage lessons in TAB.
  * Functionality: Allow the user to add, edit and delete lessons for students in TAB.
  * Justification: This feature is necessary for private 1-to-1 tutors to manage their lessons with respect to their students. Private tutors can have a consolidated record of all their students' information in a single application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to the existing model and UI of the original AB3. Many considerations had to be taken into account for the detection of clashes between lessons as well. Additionally, this enhancement affects commands to be added in the future.
  * Credits: *Ole V.V.* for the [detection of overlapping dates](https://stackoverflow.com/questions/60785426/)

* **New Feature**: Added a view command to view lessons in TAB.
  * Functionality: Allow user to view the list of lessons for a particular student by typing in the command.
  * Justification: This feature allows user to be able to select students to view their lessons without having to use the mouse. This isolates the lessons with respect to the student for tutors to manage the lessons of each student better.
  * Highlights: This enhancement required a good understanding of the UI and how to incorporate a new component to the existing UI of the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=eeliana) (Relevant pull requests [\#25](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/25), [\#89](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/89), [\#118](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/118), [\#184](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/184), [\#186](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/186), [\#250](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/250))

* **Project management**:
  * Managed releases `v1.2` (1 release) on GitHub

* **Enhancements to existing features**:
  * Updated the fees calculator logic to consider end date of recurring lessons. (Pull requests [\#208](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/208))
  * Wrote additional tests for existing features to increase coverage from 62.63% to 68.30% (Pull requests [\#120](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/120))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `ladd`, `ledit` and `ldelete` ([\#63](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/63), [\#119](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/119), [\#149](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/149))
    * Did cosmetic tweaks to existing documentation for Quick Start: [\#76](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/76)
    * Add brief overview for each section in UG: [\#162](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/162)
    * Added lesson parameters table for easier reference. (Pull request [\#307](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/307))
    
  * Developer Guide:
    * Added implementation details and test cases for the `ladd` feature.
    * Added implementation details and test cases for the `ledit` feature.
    * Added implementation details and test cases for the `ldelete` feature.
    * Added use cases for lesson management features.
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#261](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/261), [\#170](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/170), [\#93](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/93), [\#183](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/183), [\#126](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/126), [\#105](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/105), [\#109](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/109), [\#165](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/165)

#### Contributions to the User Guide
The following contributions listed are made with the help of my teammates and their valuable contributions. 

Major contributions:<br>
* [Managing Lessons](../UserGuide.md#managing-lessons)

Minor contributions:<br>
* [Overview of the Command Format](../UserGuide.md#overview-of-the-command-format)
* Sectioning and adding links to [Command Summary](../UserGuide.md#command-summary)


#### Contributions to the Developer Guide
The following contributions listed are made with the help of my teammates and their valuable contributions.

Major contributions:<br>
* Majority of the [Lesson Management](../DeveloperGuide.md#lesson-management-features) section
* [Manual Testing - Adding a lesson](../DeveloperGuide.md#adding-a-lesson)
* [Manual Testing - Editing a lesson of a student](../DeveloperGuide.md#editing-a-lesson-of-a-student)
* [Manual Testing - Deleting a lesson from a student](../DeveloperGuide.md#deleting-a-lesson-from-a-student)

Minor contribution:<br>
* [Use cases](../DeveloperGuide.md#use-cases) 5, 6 and 7
