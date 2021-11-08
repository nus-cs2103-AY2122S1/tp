---
layout: page
title: Chuang Zhe Quan's Project Portfolio Page
---

### Project: PlannerMD

PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.
* **New Feature**: Added the ability to add appointments [#120](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/120)
  * What it does: allow the user to create appointments 
  * Justification: This feature improves the product significantly because as a clinic receptionist, I would want to use 
  PlannerMD to manage my appointments, which is more convenient
  * Highlights: This feature was unlike adding a patient or doctor because it took in the index of the patient and doctor, rather than the patient or doctor itself,
  so its implementation was challenging, and I had to create a supporting class to implement this feature. Because of the new supporting class,
    writing the test cases were also difficult because I had to create new stubs.
* **New Feature**: Added the ability to delete appointments [#128](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/128)
  * What it is: allow the user to delete appointments
  * Justification: Users would also want to delete appointments to keep their list of appointments clean
  * Highlights: Wrote test cases for this feature.
* **New Feature**: Added the ability to add doctor [#64](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/64)
  * What it is: allow the user to create doctors
  * Justification: Clinic receptions would want to create doctors, so they can easily access their information and create appointments with doctors.
  * Highlights: Wrote test cases for this feature.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=chuangzhequan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabAuthor=ChuangZheQuan&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)

* **Enhancements to existing features**:
  * Added Date of Birth field into the Person class and test cases to test the validity of the date [#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36) 
  * Added test cases for Birth Date field [#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36)

* **Documentation**
  * User Guide:
    * Updated all documentation relating to Patient's and Doctor's personal details with the Birth Date field [#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36)
    * Created add appointment command documentation [#142](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/142)
    * Created delete appointment command documentation [#142](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/142)
    * Added why CLI and how to use CLI [#130](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/130)
    * Added introduction and table of contents [#130](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/130)
    * Reviewed, raised and fixed issues throughout the UG [#207](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/207)
    
  * Developer Guide:
    * Added use cases, target user profile, value propositions and user stories [#15](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/15)
    * Updated Architecture section [#110](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/110)
    * Added cases for manual testing for adding and deleting appointments [#223](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/223)
    * Added cases for manual testing for adding and deleting patients/doctors [#243](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/243)
    * Added implementation for adding and deleting appointments [#144](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/144)
    * Added introduction and table of contents [#110](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/110)
    * Reviewed, raised and fixed issues throughout the DG [#215](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/215)
  
* **Community**:
    * Review, raised and fixed issues in user/developer guide
    * Added test builders for appointments so that features can be implemented with their tests
    * Documented and updated the target user profile, use cases and user stories throughout the project
    * PRs reviewed (with non-trivial review comments): [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39), [#95](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/95), [#114](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/114)
    * Reviewed and reported an above average number of bugs in other team's product: [link](https://github.com/chuangzhequan/ped/issues)
