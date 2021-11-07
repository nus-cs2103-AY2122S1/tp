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
  * Added the Patient class and Risk field to the Patient class [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39)
  * Added persistent Storage in Json for the Doctor class [#72](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/72)
  * Added the Model and Logic required for the Appointment entity in the application [#108](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/108/files)
  * Added persistent storage in Json for the Appointment class [#117](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/117)

* **Enhancements to existing features**:
  * Updated the `isSamePerson` to check for more fields to define equality between people instead of just comparing the names (It is a known fact that people can share the same names) [#38](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/38)
  * Changed `clear` command to `IWANTTOCLEAREVERYTHING` and updated the corresponding documentation [#206](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/206)
  * Consistently added additional tests to improve our test suites [#76](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/76) [#196](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/196)
  * Bugfixes [#71](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/71) [#206](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/206)

* **Contributions to the UG**:
  * Added the Delete Appointment Command section [#147](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/147)
  * Reviewed, raised and fixed issues throughout the UG

* **Contributions to the DG**:
  * Updated the Storage Class Diagram [#113](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/113)
  * Added the implementation for storing an appointment [#198](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/198)
  * Added a sequence diagram to illustrate how an appointment is stored [#198](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/198)
  * Added the Find Patients/Doctors manual testing [#237](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/237)
  * Added Saving data manual testing [#237](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/237)
  
* **Contributions to team-based tasks**:
  * Set up the GitHub team org/repo
  * Updated the README file to match current project [#10](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/10)
  * Updated Site Wide Settings in `Documentation.md`, `_config.yml`, `_base.scss` and `index.md` files to match current project [#11](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/11)
  * Used Figma to design a [mockup](https://www.figma.com/file/LA0OQ6FUXr87X3lZMcs15E/CS2103T-tP?node-id=0%3A1) for the application
  * Refactored and renamed the codebase from AddressBook to PlannerMd [#34](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/34)
  * Managed the Release for every iteration
  * Reviewed, raised and fixed issues in the user/developer guide
  * Planned the agenda for the weekly team discussions to ensure meetings are focused and productive
  * Led team discussions and set clear checkpoints and deadlines for the team
  * Added "skeleton" code to have less merge conflicts [#53](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/53) [#60](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/60)
  * Added test builders before features are implemented so that features can be implemented concurrent with their tests [#54](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/54)
  * Refactored packages to make codebase more organised [#199](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/199)  

* **Review/mentoring contributions**:
  * Addressed a teammate's PR regarding implementation issues and gave tips and advices and how to resolve the mentioned issues [#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58#issuecomment-937614694)
  * Reviewed a teammate's PR and provided multiple rounds of reviews while giving tips regarding IDE settings [#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36)
  * PRs reviewed (with non-trivial review comments): [#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41) [#62](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/62) [#107](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/107)

* **Contributions beyond the project team**:
  * Reviewed and reported bugs in other team's product [link](https://github.com/jiayi1129/ped/issues)
