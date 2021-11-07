---
layout: page
title: Jia Yi's Project Portfolio Page
---

### Project: PlannerMD

#### Overview
PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

#### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jiayi1129&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=jiayi1129&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements implemented**:
  * Added the Risk field to the Patient class
  * Implemented the ability for adding and editing the Risk field from the Patient class
  * Added persistent Storage in Json for the Doctor class
  * Added the Model and Logic required for the Appointment entity in the application
  * Added persistent storage in Json for the Appointment class

* **Enhancements to existing features**:
  * Updated the `isSamePerson` to check for more fields to define equality between people instead of just comparing the names (It is a known fact that people can share the same names)
  * Changed `clear` command to `IWANTTOCLEAREVERYTHING` and updated the corresponding documentation

* **Contributions to the UG**:
  * Added the Delete Appointment Command section
  * Reviewed, raised and fixed issues throughout the UG

* **Contributions to the DG**:
  * Updated the Storage Class Diagram
  * Added the implementation for storing an appointment
  * Added a sequence diagram to illustrate how an appointment is stored
  
* **Contributions to team-based tasks**:
  * Set up the GitHub team org/repo
  * Refactored and renamed the codebase from AddressBook to PlannerMd
  * Updated the README file to match current project
  * Updated Site Wide Settings in `Documentation.md`, `_config.yml`, `_base.scss` and `index.md` files to match current project
  * Managed the Release for every iteration
  * Reviewed, raised and fixed issues in the user/developer guide
  * Used Figma to design a [mockup](https://www.figma.com/file/LA0OQ6FUXr87X3lZMcs15E/CS2103T-tP?node-id=0%3A1) for the application
  * Planned the agenda for the weekly team discussions to ensure meetings are focused and productive
  * Led team discussions and set clear checkpoints and deadlines for the team
  * Added "skeleton" code to have less merge conflicts [#53](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/53) [#60](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/60)
  * Added test builders before features are implemented so that features can be implemented concurrent with their tests [#54](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/54)

* **Review/mentoring contributions**:
  * Addressed a teammate's PR regarding implementation issues and gave tips and advices and how to resolve the mentioned issues [#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58#issuecomment-937614694)
  * Reviewed a teammate's PR and provided multiple rounds of reviews while giving tips regarding IDE settings [#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36)

* **Contributions beyond the project team**:
  * Reviewed and reported bugs in other team's product [link](https://github.com/jiayi1129/ped/issues)
  
