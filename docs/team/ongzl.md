---
layout: page
title: Ong Zheng Lin's Project Portfolio Page
---

### Project: TimesTable

TimesTable is a desktop class management application for tuition teachers to keep track of their students 
and classes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, 
and has about 25 kLoC.

Given below are my contributions to the project.

* **New feature**: Added a schedule command that filters and sort students by their class timing (V1.2)
  * What it does: Allows the user to filter students by day of which they are attending their class, so that user 
    can see who he is teaching and when.
  * Justification:  Provides early functionality to sort students by their class timing during the early phases of 
    the application. Provides some early work towards functions like `sort` and back-end processing of 
    `TuitionClass` and `ClassTiming`.
  * Highlights: Provide a foundation for later iteration which improved the functionality of `TuitionClass` and `ClassTiming`.
  * Pull request: [\#62](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/62), [\#71](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/71)

* **New feature**: Added the basic GUI for the `TuitionClass list` in the `classes` tab 
    * What it does: Allows the user to view the classes currently in TimesTable.
    * Justification: Allows the user to know the details of classes currently in TimesTable, so that they can have a 
      better idea of their workload and schedule.
    * Highlights: Provide a foundation for later improvement and iterations in the `classes` tab.
    * Commits: [1](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/e632b2de0b0f44bc67e84de601d8632e28696bf1)
    
* **New feature** Added the backend support for `UniqueClassList` and `TuitionClass`
    * What it does: Create support for adding and removing `TuitionClass` in `UniqueClassList` by ensuring no 
      overlap and proper updates from and to storage.
    * Justification: Ensures that there is smooth reading of the JSON file into the model and overlapping class 
      timing between tuition classes are not allowed during editing and adding of class.
    * Highlights: Provide support for `AddClassCommand`, `EditClassCommand` and safe reading of JSON file into model.
    * Commits: [1](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/7ddac4e24d6abc6da6b669c0daf50a85601c3f5a),
    [2](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/d344612297e2d21c988653b51a7eaa7d409cdacb),
    [3](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/39d388fd3f0bf55dd3dcab9995d41ccf0419f0c6)  

* **Code contributed**: [Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=f11&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Ongzl&tabRepo=AY2122S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
  
* **Enhancements to existing features**:
    * Link the `JSON` file and `UniqueClassList` in model, allowing the model to save (Commits
      [1](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/7ddac4e24d6abc6da6b669c0daf50a85601c3f5a),
      [2](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/d344612297e2d21c988653b51a7eaa7d409cdacb),
      [3](https://github.com/AY2122S1-CS2103T-F11-1/tp/commit/39d388fd3f0bf55dd3dcab9995d41ccf0419f0c6))
    * Wrote additional tests for existing features to increase coverage (Pull request 
      [\#234](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/234),
      [\#259](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/259),
      [\#281](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/281))
    * Bug fixes for features `Class size` in `classes` tab and `Student list` in `classes` tab (Pull request 
      [\#106](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/106),
      [\#137](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/137),
      [\#246](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/246),
      [\#249](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/249),
      [\#251](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/251),
      [\#252](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/252))
    * Fix old test cases which were outdated due to change in commands (Pull request [\#115](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/115), [\#155](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/155))
  
* **Documentation**
  * User Guide:
    * Added basic documentation for features `addclass`, `deleteclass`, `addtoclass`, `editclass`, `findclass`, 
      `listclass` [\#130](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/130)
    * Added documentation on filtering `students` [\#281](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/281)
  * Developer Guide
    * Added use cases (Pull request 
      [\#17](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/17),
      [\#265](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/265))
    * Updated `Storage` component and UML diagram [\#290](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/290)
    * Added sequence diagrams and implementation detail for `AddClassCommand` and edited other UML diagrams (Pull request
      [\#294](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/294),
      [\#295](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/295),
      [\#333](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/333))
    * Added challenges faced in `AddClassCommand` [\#344](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/344)
    * Added documentation and sequence diagrams for `DeleteClassCommand` [\#320](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/320)
      [\#333](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/333),
      [\#339](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/339))     
    * Added documentation and sequence diagrams for `DeleteClassCommand` (Pull request 
      [\#320](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/320),
      [\#333](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/333))
    * Added method for manual testing of `deleteclass`, `removefromclass`, `findtag` and `view` [\#332](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/332)
* **Community**
  * PRs reviewed (with non-trivial review comments): (Pull request 
    [\#26](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/26)
    [\#40](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/40),
    [\#47](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/47#partial-pull-merging),
    [\#53](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/53),
    [\#92](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/92),
    [\#152](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/152),
    [\#237](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/237),
    [\#253](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/253),
    [\#254](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/254),
    [\#260](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/260))
  * Reported bugs for other teams during PE-D: [ped issues github](https://github.com/Ongzl/ped/issues)
  
