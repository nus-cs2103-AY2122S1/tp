---
layout: page
title: Li Xue's Project Portfolio Page
---

### Project: PlannerMD

PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit doctors [#73](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/73)
  * What it does: Allows the user to edit the details of doctor records.
  * Justification: This feature improves the product significantly because users can edit doctor records without having to delete an existing one and add a new record.
  * Highlights: This feature ensures that users cannot edit doctor records to become duplicates in the existing list of doctors.

* **New Feature**: Created the `Appointment` class to represent appointments [#95](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/95)
  * What it does: Encapsulates the patient, doctor, date, session and remark of an appointment.
  * Justification: This feature improves the product significantly because it allows other features related to managing appointments to be implemented.
  * Highlights: This feature ensures that clashing appointments do not exist in the appointment list [#129](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/129).

* **New Feature**: Added the ability to edit appointments [#121](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/121)
  * What it does: Allows the user to edit the details of appointments.
  * Justification: This feature improves the product significantly because users can edit appointments without having to delete an existing one and add a new appointment.
  * Highlights: This feature ensures that users cannot edit appointments to clash with existing ones in the appointment list.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=pualixue&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=pualixue&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
  * Ensured code quality and assigned reviewers for Pull Requests


* **Enhancements to existing features**:
  * Allowed adding and deleting individual tags from a patient's record instead of overwriting all existing tags and added test cases [#40](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/40)

<div style="page-break-after: always;"></div>

* **Documentation**:
  * User Guide:
    * Updated documentation for adding and deleting individual tags commands [\#40](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/40)
    * Added documentation for commands related to managing doctors [#106](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/106)
    * Added documentation for edit appointment command [#134](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/134)
    * Reviewed, raised and fixed issues throughout the UG
  * Developer Guide:
    * Added non-functional requirements and glossary [#20](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/20)
    * Added use cases for adding and deleting tags [#40](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/40)
    * Updated class diagram and sequence diagram for `Logic` component [#112](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/112)
    * Added use cases for adding and deleting a patient/doctor [#112](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/112)
    * Added a sequence diagram to illustrate how appointments are edited [#195](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/195)
    * Added use cases for editing doctors and appointments [#195](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/195)
    * Added manual testing instructions for editing patients, doctors and appointments [#233](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/233)
    * Added manual testing instructions for listing patients and doctors [#233](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/233)
    * Reviewed, raised and fixed issues throughout the DG


* **Community**:
  * Reviewed Pull Requests (with non-trivial comments): 
  [#15](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/15),
  [#34](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/34),
  [#35](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/35),
  [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39),
  [#54](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/54),
  [#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58),
  [#105](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/105),
  [#107](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/107),
  [#115](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/115),
  [#120](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/120),
  [#144](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/144)
  * Reviewed and reported bugs in other team's product ([link](https://github.com/pualixue/ped/issues))


* **Contributions to team-based tasks**:
  * Updated the User Guide template to have less merge conflicts [#106](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/106)
  * Reviewed, raised and fixed issues in the User/Developer Guide


* **Tools**:
  * Used Figma to design a [mockup](https://www.figma.com/file/LA0OQ6FUXr87X3lZMcs15E/CS2103T-tP?node-id=0%3A1) for the application
