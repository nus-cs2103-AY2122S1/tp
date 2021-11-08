---
layout: page
title: Jia Yi's Project Portfolio Page
---

### Project: PlannerMD

#### Overview
PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project


* **New Feature**: Added the `Patient` class and Risk field to the `Patient` class [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39)
  * What it does: Allows the user to add, edit and remove the risk field from the `Patient` class
  * Justification: This feature improves the product significantly as clinic receptionists now can conveniently view, add, edit and remove the risk profiles of their patients quickly and identify whether any extra follow-ups or measures need to be taken.
  * Highlights: This feature requires the creation of a new `Patient` class that extends from the `Person` class to avoid code duplication as we planned to have another `Doctor` class that extends from the same `Person` class as well (Both `Patient` and `Doctor` is a `Person`).
    Much refactoring has to be done to the codebase as well to change the common class used from `Person` to `Patient`, as `Patient` becomes our object of concern in this project.

* **New Feature**: 
  * What it does: Allows the user to execute commands pertaining to appointments and receive feedback in the appointment list.
  * Justification: This feature is crucial to the product as the `Model` and `Logic` class is essential for the logical flow of the application. 
  * Highlights: This feature requires the creation of a new `UniqueAppointmentList` to be used in the `Model` class to manage an appointment list. 
    The `Appointment` class now implements the `Comparable` interface to have natural ordering, and the UI then displays a sorted appointment list that uses this natural comparator.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jiayi1129&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=jiayi1129&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

<div style="page-break-after: always;"></div>

* **Project Management**:
  * Set up the GitHub team org/repo
  * Planned the agenda for the weekly team discussions to ensure meetings are focused and productive
  * Led team discussions and set clear checkpoints and deadlines for the team
  * Managed release `v1.2`, `v1.2.1`, `v1.3` and `v1.4` (4 releases) on Github

* **Enhancements to existing features**:
  * Added persistent storage in Json for the Doctor class [#72](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/72)
  * Added persistent storage in Json for the Appointment class [#117](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/117)
  * Updated the `isSamePerson` to check for more fields to define equality between people instead of just comparing the names [#38](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/38)
  * Changed `clear` command to `IWANTTOCLEAREVERYTHING` and updated the corresponding documentation [#206](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/206)
  * Consistently added additional tests to improve our test suites [#76](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/76) [#196](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/196)
  * Bugfixes [#71](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/71) [#206](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/206)

* **Documentation**:
  * User Guide:
    * Added the Delete Appointment Command section [#147](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/147)
    * Reviewed, raised and fixed issues throughout the UG [#226](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/226) [#277](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/277)
  * Developer Guide:
    * Updated the Storage Class Diagram [#113](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/113)
    * Added the implementation and sequence diagram for storing an appointment [#198](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/198)
    * Added Find Patients/Doctors and Saving data manual testing [#237](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/237)
  * Formatted the UG and DG documents and optimize for PDF conversion [#278](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/278)

* **Community**:
  * Addressed a teammate's PR regarding implementation issues and gave tips and advices and how to resolve the mentioned issues [#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58#issuecomment-937614694)
  * Reviewed a teammate's PR and provided multiple rounds of reviews while giving tips regarding IDE settings [#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36)
  * PRs reviewed (with non-trivial review comments): [#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41) [#62](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/62) [#107](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/107)
  * Reported bugs and suggestions for other teams in the class [link](https://github.com/jiayi1129/ped/issues)

* **Contributions to team-based tasks**:
  * Updated the README file to match current project [#10](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/10)
  * Updated Site Wide Settings to match the current project [#11](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/11)
  * Refactored and renamed the codebase from AddressBook to PlannerMd [#34](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/34)
  * Added "skeleton" code to have less merge conflicts [#53](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/53) [#60](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/60)
  * Added test builders before features are implemented so that features can be implemented concurrent with their tests [#54](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/54)
  * Refactored packages to make codebase more organised [#199](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/199)

* **Tools**:
  * Used Figma to design a [mockup](https://www.figma.com/file/LA0OQ6FUXr87X3lZMcs15E/CS2103T-tP?node-id=0%3A1) for the application
