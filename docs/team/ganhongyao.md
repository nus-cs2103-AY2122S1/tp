---
layout: page
title: Gan Hong Yao's Project Portfolio Page
---

### Project: Source Control

Source Control is a desktop application for CS1101S professors to manage the performance of their students. Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add groups.
    * What it does: allows the user to add groups within the module. Students can also be added to the new group within the same command.
    * Justification: This feature is necessary for the target user to know which groups a student belongs to. It also allows for detailed analysis of the performance of a particular group, relative to the cohort.
    * Highlights: This enhancement is the sole entrypoint for the creation of groups. The implementation  was challenging as there were many edge cases to consider, such as if a student to be added was specified more than once.

* **New Feature**: Added the ability to render charts for better visualisation of assessment performance.
  * What it does: allows the user to visualise how the cohort performed in an assessment using a histogram.
  * Justification: This feature is necessary for the target user to analyse assessment results easily, such as whether the score distribution follows a desired bell-curve shape.
  * Highlights: This enhancement is the first feature in Source Control that dealt with JavaFX Charts. The implementation was not trivial as JavaFX Charts were not easy to work with, and many style customisations had to be made for the chart to fit well with the application's theme.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ganhongyao)

* **Project management**:
    * Set up project issue tracker with relevant issue tags

* **Enhancements to existing features**:
  * Adapted code from Address Book 3 to suit the context of Source Control (Pull requests [\#92](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/92), [\#94](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/94))
  * Updated the application title and icon (Pull request [\#180](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/180))
  * Wrote additional tests for existing features to increase coverage from 66% to 71% (Pull request [\#256](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/256))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addgroup` (Pull request [\#41](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/41))
        * Did cosmetic tweaks to existing documentation of `help` feature and glossary: (Pull request [\#189](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/189))
    * Developer Guide:
        * Added implementation details of the `addgroup` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests [\#168](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/168), [\#177](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/177), [\#127](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/127))
    * Proposed working solution to UI bugs faced by several classmates (see [\#168](https://github.com/nus-cs2103-AY2122S1/forum/issues/168))
    * Contributed to forum discussions (examples: [\#191](https://github.com/nus-cs2103-AY2122S1/forum/issues/191), [\#141](https://github.com/nus-cs2103-AY2122S1/forum/issues/141))
    * Reported bugs and suggestions for other teams in the class (examples: [\#269](https://github.com/AY2122S1-CS2103T-F13-3/tp/issues/269), [#245](https://github.com/AY2122S1-CS2103T-F13-3/tp/issues/245), [\#239](https://github.com/AY2122S1-CS2103T-F13-3/tp/issues/239))

* **Tools**:
    * Integrated JavaFX Charts to the project ([\#158](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/158))


