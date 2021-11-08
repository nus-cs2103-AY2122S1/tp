---
layout: page
title: Hong Po's Project Portfolio Page
---

### Project: PlannerMD

PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented functionality and test cases for remark field ([#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41)).
  * What it does: Adds an optional remark field to patient that can contain any text with restriction.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them. 
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: * Implemented functionality and test cases for remark patient command ([#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41)).
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Implemented functionality and test cases for toggle command ([#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58)).
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Implemented functionality and test cases for list doctor command ([#75](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/75)).
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Implemented functionality and test cases for find doctor command ([#74](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/74), [#78](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/78)).
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Updated Model component to be stateful and added integration tests for relevant classes ([#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58), [#70](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/70)).
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hpkoh&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=hpkoh&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Update add tag doctor/patient command to use "add" flag([#105](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/105))
  * Update edit doctor/patient command to propagate changes to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))
  * Update remark doctor/patient command to propagate changes to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))
  * Update tag doctor/patient command to propagate changes to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))
  * Update delete doctor/patient command to propagate deletion to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))

<div style="page-break-after: always;"></div>

* **Documentation**:
  * Contributions to the UG:
    * Update documentation for v1.2 ([#18](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/18))
    * Added remark patient command documentation ([#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41))
    * Added toggle command documentation ([#136](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/136), [#197](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/197))
    * Added stateful PlannerMD documentation ([#136](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/136), [#197](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/197))
    
  * Contributions to the DG:
    * Updated Model component and model class diagrams ([#114](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/114), [#208](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/208))
    * Added implementation details for toggle command ([#114](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/114), [#208](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/208))
    * Added implementation detail for remark command ([#208](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/208))
    * Added implementation details for statefulness ([#114](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/114), [#208](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/208))
    * Added use cases for remark command ([#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250))
    * Added manual testing instructions for remark command ([#232](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/232), [#268](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/268))
    * Added manual testing instructions for tag command ([#232](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/232), [#268](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/268))
    * Standardize use cases ([#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250))
    * Added doctor use cases ([#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250))
    * Standardize manual testing format ([#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250), [#265](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/265))


* **Contributions to team-based tasks**:
  * Check code coverage and testability of implementations
  * Update plannerMD to support doctor features([#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58))
  * Update parser to parse appointment commands([#105](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/105))
  * Fixed bugs found in the PED and manual testing([#235](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/235), [#119](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/119), [#235](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/235))

* **Community**:
  * Reviewed team member's PR and provided comments and suggestions ([#15](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/15), [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39), [#40](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/40), [#95](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/95), [#117](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/117))
  * Reviewed and reported bug in other team's product ([link](https://github.com/hpkoh/ped/issues))

* **Tools**:
  * Used Figma to design a [mockup](https://www.figma.com/file/LA0OQ6FUXr87X3lZMcs15E/CS2103T-tP?node-id=0%3A1) for the applicatio