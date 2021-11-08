---
layout: page
title: Sourabh's Project Portfolio Page
---

### Project: Uni-Fy

Uni-Fy is a desktop app for managing your university workload and it is designed by university students for university students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=srj31&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=srj31&tabRepo=AY2122S1-CS2103T-W17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=srj31&zR=AY2122S1-CS2103T-W17-4%2Ftp%5Bmaster%5D&zACS=128.8181818181818&zS=2021-09-17&zFS=srj31&zU=2021-11-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)


* **Modifying Name class**
  [\#61](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/61)
  * I modified the name field in AB3 from the full name of the person to the task name of the person. This also involved changing the fields in test file as well


* **Modifying the Add Command**
  [\#94](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/94)
  [\#171](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/171)
  [\#285](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/285)
  * I modified the Add Command to be able to add tasks instead of the addresses as in AB3. Initially the tasks were compared based on their names only hence tasks with different dates or tags were not added. In order to allow for addition of tasks with same name but different date, time or tags had to update the equals function.
  * This also involved numerous changes in the test files, in sample data, in storage files since checking of duplicate tasks was updated and additional test files had to be created


* **Integrating the Daily Panel**
  [\#95](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/95)
  * I modified the hard coded daily panels such that each daily panel would now store a list of tasks, for the given day of the week and integrated these daily panels with the weekly panel
  * The further GUI enhancements were passed to another team member, due to his proficiency in handling UI componets.


* **Project management**:
  * Managed releases `v1.3c` (1 release) on GitHub


* **Enhancements to existing features**:
  * Updated the GUI for the Help window
    [\#296](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/296)
  * Created tests for new features `Done`, `Undone`, `Tag`, `CommandHistory`, `TaskContainsTagPredicate`, `Show`
    [\#303](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/303)
    [\#304](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/304)
    [\#305](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/305)
    [\#315](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/315)
    [\#323](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/323)
    [\#346](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/346)
    [\#367](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/367)
  * Created tests for parsers `TagCommandParser`, `UnifyParser`, `UndoneCommandParser`
    [\#324](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/324)
    [\#343](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/343)
  * Created tests for models `Time`, `Task`, `Priority`, `State`
    [\#327](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/327)
    [\#366](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/366)
    


* **Documentation**:
  * User Guide:
    * Added documentation for the features `history`(now called `/prev`), `find`, `sort`
      [\#152](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/152)
      [\#215](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/215)
    * Added FAQs about our product
      [\#155](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/155)
    * Did cosmetic tweaks to existing documentation of features `add`, `tag`, `history`, `clear`
      [\#170](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/170)
      [\#172](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/172)
      [\#173](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/173)
      [\#178](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/178)
      [\#180](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/180)
      [\#181](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/181)
      [\#182](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/182)
      [\#338](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/338)
    * Update UG to include missing information about all the parameters and edit documentation about `tag`
      [\#170](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/170)
      [\#296](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/296)
      [\#300](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/300)
  * Developer Guide:
    * Added implementation details of the `add` feature
      [\#329](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/329)
    * Updated DG to fix naming issues as well as images
      [\#146](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/146)
      [\#149](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/149)


* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#145](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/145)
    [\#295](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/295)
    [\#348](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/348)
  * Contributed to team discussions:
    [1](https://docs.google.com/document/d/1TmplkyQY3q2S0ZLLVPJjo6Isc8zxYnXRj32cDAxKhuk/edit)
  * Reported bugs and suggestions for other teams in the class:
    [1](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/190),
    [2](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/188),
    [3](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/185),
    [4](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/181),
    [5](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/178),
    [6](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/168),
    [7](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/165),
    [8](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/161),
    [9](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/160),
    [10](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/156),
    [11](https://github.com/AY2122S1-CS2103T-W11-3/tp/issues/153)
  
