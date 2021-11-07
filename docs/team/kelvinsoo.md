---
layout: page
title: Kelvin's Project Portfolio Page
---

### Project: Uni-Fy

Uni-Fy is a desktop app for managing your university workload and it is designed by university students for university students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=KelvinSoo&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true&tabType=authorship&tabAuthor=KelvinSoo&tabRepo=AY2122S1-CS2103T-W17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added a weekly `progress bar` to keep track of all tasks
  * What it does: allows the user to visualise weekly progress
  * Justification: This feature improves the product significantly because a user is able to keep track of their weekly task completion progress.
  * Highlights: This enhancement affects existing commands and commands to be added in future (commands that perform changes to the task). It required an in-depth understanding of `model`, `logic` and `JavaFX` components. The implementation too was challenging as it required updating of the UI using `Observables` and ensuring that all commands that modify/changes tasks also updates the bar.
  * Contribution: Created the `progress bar` UI element and linking it to `model`: [\#191](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/191)

* **New Feature**: Added the ability to mark a task as `done`.
  * What it does: allows the user to mark a task as done. Also updates the progress bar to show number of task that are done.
  * Justification: This feature improves the product significantly because a user is able to keep track of what tasks they have done and what tasks needs to be done.
  * Highlights: It required an in-depth understanding of `css` for the tag styling/changing of colours.
  * Contribution: Created the state model and linking it to the UI(progress bar): [\#187](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/187)
  
* **New Feature**: Added `priority` property to task.
  * What it does: allows the user to assign priority to their tasks.
  * Justification: This feature improves the product significantly because a user is able to prioritise their tasks.
  * Highlights: It required an in-depth understanding of `css` for the tag styling/changing of colours.
  * Contribution: Created the priority model as well as UI element: [\#209](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/209)

* **New Feature**: Contributed to the `show` feature.
  * What it does: allows the user to show weekly tasks by each day of the week in a weekly panel.
  * Justification: This feature improves the product significantly because a user is able to easily visualise their weekly tasks.
  * Highlights: It required an in-depth understanding of `model`, `logic` and `JavaFX` components. The implementation too was challenging as it required updating of the UI using `Observables` and ensuring that other commands that updates tasks would also update the UI.
  * Contribution: Created the initial framework for the feature. Linking the `logic` and `model` of show with the `UI`: [\#128](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/128), [\#168](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/168)
    
* **Project management**:
  * Managed releases `v1.2` - `v1.3b` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Improve find feature to allow more predicates and find base on date, tag. [\#169](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/169)
  * Improve the GUI design (Pull requests [\#71](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/71), [\#73](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/73), [\#89](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/89), [\#91](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/91), [\#168](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/168), [\#191](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/191), [\#202](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/202))
  
* **Documentation**:
  * User Guide:
    * Added section for navigation, icons and glossary: [\#174](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/174)
    * Added documentation for the features `find` and `list`: [\#18](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/18), [\#183](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/183), [\#286](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/286)
    * Did cosmetic tweaks to existing documentation of features `help`: [\#284](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/284)
    * Improve introduction and command table : [\#184](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/184)
  * Developer Guide:
    * Added implementation details of the `find` feature: [\#145](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/145), [\#159](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/159), [\#161](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/161)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#66](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/66), [\#74](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/74), [\#121](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/121), [\#194](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/194), [\#285](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/285)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/209), [2](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/205), [3](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/203), [4](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/192), [5](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/179), [6](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/172), [7](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/164), [8](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/159), [9](https://github.com/AY2122S1-CS2103T-T09-1/tp/issues/210))

* **Tools**:
  * Integrated a third party library (Apache commons) to the project ([\#278](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/278))

* _{you can add/remove categories in the list above}_
