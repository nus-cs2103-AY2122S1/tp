---
layout: page
title: Jodi Choo's Project Portfolio Page
---

### Project: NUS Mod Tracker

NUS Mod Tracker is a desktop app designed for NUS Computer Science (CS) students who are enrolled in the Academic Year 2021/2022 and onward to create their own academic plan, as well as to keep track of their Modular Credits (MC) and modules taken.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented the MC progress panel which tracks the user's MCs.
  * What it does: Calculates the number of MCs completed for each course requirement, as well as in total, and displays it as an updatable UI component.
  * Justification: It is a key feature of our application as it allows the user to keep track of their completed MCs, and keep track of their progress for their course requirements.
  * Highlights: The implementation for this feature was challenging.
    * The UI of the application had to be modified to accommodate the MC progress panel.
    * Many commands can cause the UI component to update. The components had to be designed in a way that allows changes to the many various components to be captured, and the UI updated accordingly.
    * Measures were taken so that the UI updates will not cause large performance issues with large amounts of data within the application.


* **New Feature**: Added the ability to add (`take`) or remove (`untake`) a module from the academic plan.
    * What it does: 
      * `take`: Allows the user to take a module in a specified semester and add the module into their academic plan.
      * `untake`: Allows the user to remove a module from their academic plan.
    * Justification: These are key features in our application as it allows the user to create and modify their academic plan.
    * Highlights: This enhancement affects existing commands and commands to be added in the future.
        * It required changes to existing Model components as our application had to handle additional data for the year and semester of each module.
        * It required changes to existing Storage components as the additional data for year and semester of each module had to be saved.
        

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=w17-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=jodichoo&tabRepo=AY2122S1-CS2103T-W17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
    * Added additional input format restrictions and the necessary checks to the Code and Mc components: [\#167](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/167), [\#169](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/169)
    * Updated the GUI color scheme: [\#93](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/93)
    * Fixed various issues from the Practical Examination Dry Run: [\#167](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/167), [\#171](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/171)


* **Team-based tasks**
  * Added a share of default modules to NUS Mod Tracker: [\#167](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/167)
  * Updated AB3's logic components for the `add` command to suit NUS Mod Tracker's needs: [\#40](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/40) 
  * Update sections of the AB3 user guide and developer guide to suit NUS Mod Tracker: [\#22](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/22/files)


* **Documentation**:
    * User Guide:
        * Added documentation for the MC goal progress component: [\#88](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/88)
        * Added documentation for the features `take` and `untake`: [\#80](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/80)
        * Updated documentation for `add` feature: [\#45](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/45)
        * Update documentation to reflect additional input format restrictions: [\#167](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/167)
        * Fix issues with the application screenshots: [\#173](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/173)
    * Developer Guide:
        * Added implementation details of the `take` feature: [\#80](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/80), [\#176](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/176)
        * Added use cases for `take` and `untake` features, updated use case for `delete` feature: [\#173](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/173)
        * Updated user stories: [\#173](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/173)


* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-T11-4/tp/issues/130), [2](https://github.com/AY2122S1-CS2103T-T11-4/tp/issues/149), [3](https://github.com/AY2122S1-CS2103T-T11-4/tp/issues/147))
    * PRs reviewed (with non-trivial review comments): [\#172](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/172), [\#178](https://github.com/AY2122S1-CS2103T-W17-2/tp/pull/178)
