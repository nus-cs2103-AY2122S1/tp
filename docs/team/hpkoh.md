---
layout: page
title: Hong Po's Project Portfolio Page
---

### Project: PlannerMD

PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented functionality and test cases for remark field ([#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41)).
  * What it is: An optional remark field for patients and doctors which contains text without any restriction.
  * Justification: Remark provides a field for important miscellaneous information about patients or doctors such as allergies and chronic conditions.
  * Highlights: This enhancement introduced a new field to the existing `Person` object with a new prefix to consider. Since most of the architecture and test classes have dependencies with `Person`, many classes had to be meticulously updated. (**Note**: `Patient` and `Doctor` are subclasses of `Person`)

* **New Feature**: Implemented functionality and test cases for remark patient command ([#41](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/41)).
  * What it does: Allows user to add remarks to a specific patient.
  * Justification: This feature improves the product because it allows the user to easily edit remarks through an independent command instead of coupling the functionality with add and edit patient commands and needlessly clutter the possible fields these commands can take. 
  Also, since the remark would likely be updated frequently to reflect patients' current considerations, having a separate command without the relatively more sophisticated logic used in the execution path (including parsing) of edit command allows the execution of remark command to be quicker.
  * Highlights: Since this is an entirely new command added which edits `Patient` details, the execution path required not only consideration for parsing the user input but also interactions with the current list of patients.

<div style="page-break-after: always;"></div>

* **New Feature**: Implemented functionality and test cases for toggle command ([#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58)).
  * What it does: Allows the user to toggle between patient state and doctor state.
  * Justification: This feature improves the product because it allows the user to toggle between the two states to execute commands according to the state.
  * Highlights: Since toggling of states also involves toggling the list displayed to the user in the UI, careful considerations were required during implementation to minimize the coupling amongst `UI`, `Model` and `Logic` components. 

* **New Feature**: Implemented functionality and test cases for list doctor command ([#75](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/75)).
  * What it does: Allows the user to list all doctors
  * Justification: This feature improves the product because it allows the user to list all doctors within PlannerMD. The user can thus view all the doctors' details and execute commands on the listed doctors.
  * Highlights: Since this command should only be executed when PlannerMD is in the `Doctor` state, parsing of the command needs to be sensitive to the state.

* **New Feature**: Implemented functionality and test cases for find doctor command ([#74](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/74), [#78](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/78)).
  * What it does: Allows the user to find a list of doctors according to keyword(s).
  * Justification: This feature improves the product because it allows the user to quickly search for doctors according to keyword(s). The user can thus view all the filtered doctors' details and execute commands on the listed doctors.
  * Highlights: Since this command should only be executed when PlannerMD is in the `Doctor` state, parsing of the command needs to be sensitive to the state.

* **New Feature**: Updated PlannerMD architecture to be stateful and added integration tests for relevant classes ([#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58), [#70](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/70)).
  * What it does: Implement state within the architecture of PlannerMD
  * Justification: To keep our commands as simple as possible, without explicitly referencing patient or doctor.
  * Highlights: This enhancement affects existing commands and commands to be added as commands executed now depends on the state as well. Existing parser thus needed to be updated to be sensitive to the state as well. 

<div style="page-break-after: always;"></div>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hpkoh&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=hpkoh&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Update add tag doctor/patient command to use "add" flag([#105](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/105))
  * Update edit doctor/patient command to propagate changes to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))
  * Update remark doctor/patient command to propagate changes to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))
  * Update tag doctor/patient command to propagate changes to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))
  * Update delete doctor/patient command to propagate deletion to appointments and updated test cases to test for propagation([#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122))

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
    * Added use cases for doctor commands ([#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250))
    * Standardize manual testing format ([#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250), [#265](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/265))


* **Contributions to team-based tasks**:
  * Check code coverage and testability of implementations
  * Updated PlannerMD to support doctor features([#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58))
  * Updated parser to parse appointment commands([#105](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/105))
  * Fixed bugs found in the PED and manual testing([#235](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/235), [#119](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/119), [#235](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/235))

* **Community**:
  * Reviewed team member's PR and provided comments and suggestions ([#15](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/15), [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39), [#40](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/40), [#95](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/95), [#117](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/117))
  * Reviewed and reported bug in other team's product ([link](https://github.com/hpkoh/ped/issues))

* **Tools**:
  * Used Figma to design a [mockup](https://www.figma.com/file/LA0OQ6FUXr87X3lZMcs15E/CS2103T-tP?node-id=0%3A1) for the applicatio
