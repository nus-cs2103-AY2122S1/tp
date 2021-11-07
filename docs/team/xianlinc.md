---
layout: page
title: Chng Xian Lin's Project Portfolio Page
---

### Project: InsurancePal

InsurancePal is a client management software specially designed
to help manage the logistics of being an insurance agent.

### Summary of Contributions

Given below are my contributions to the project.

**Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=xianlinc)

**Enhancements Implemented**:

New feature: **Implemented the** `note` **command** [#53](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/53)

- **What:** The note command is a feature that allows the user to add a note to a client in InsurancePal.
- **Justification**: One thing an insurance agent needs to do is keep track of information about the client. Although the other features in InsurancePal keep track of certain details of clients, their scope is too limited and sometimes information does not fall into any of the existing categories. The user would benefit from having a feature that allows them to type long strings of text as if it was a notepad. Therefore, the `note` command allows the insurance agent to store any extra notes about the client such as their birthday or their existing medical history.
- **Classes created**:
    - `NoteCommandParser`
    - `NoteCommand`
    - `Note`
- **Tests Written**:
    - `NoteCommandTest`
- **Effort Expended**:
    - The `note` command requires a good level of understanding of the entire application. This is because it makes changes to the `Logic`, `Model` and `Storage` components of the application.

New feature: **Display a sorted list of upcoming appointments of the clients in a panel in the UI**  [#109](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/109)

- **What**: Uses the filtered list of persons to show a sorted list of upcoming appointments in the UI. The appointment list updates itself when the filtered list of persons changes.
- **Justification**: An insurance agent would like to know what is their latest appointment and the appointments that come after that in chronological order.
- **Effort Expended**: Making modifications to the UI requires a good level of understanding of the `UI` component as well as the way it interacts with the `Logic`, `Model` components. Good knowledge of `javafx` is needed to create the panels and create cards using `ListCell` and a custom `CellFactory`. Precise and detailed work was required when adjusting the layout of UI components.

New feature: **Display all claims of the clients in a panel in the UI** [#109](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/109)

- **What**: Uses the filtered list of persons to show a list of claims in the UI. The list of claims updates itself when the filtered list of persons changes.
- **Justification**: An insurance agent would like to know what is their latest appointment and the appointments that come after that in chronological order.
- **Effort Expended**: Similarly to the appointment list feature mentioned above, good knowledge of the `UI` component and `javafx` is needed. Much more effort was expended due to the difficulties mentioned below.
- **Difficulties faced**:
    - There was a bug where `ClaimPanel` only showed one `ClaimCard` per `Person` (very difficult to fix). [#178](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/178)
        - Fixing this bug was very difficult and required knowledge and practical application of the observer pattern.
    - There were **truncation issues** with the contents of the three cards. [#183](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/183)
        - Solving this issue was difficult and required extensive research into `javafx` and the interactions between `Label`s and `FlowPane`s.
        - Using text wrap to solve the issue introduced another bug where the size of each card did not adjust itself to accommodate the new height of the `Label`s due to text wrapping, causing text overrun.
        - Fixing this new bug required good knowledge of the **SceneBuilder** tool and how to use `FXML` to compute the size of each card based on its contents.

<div style="page-break-after: always;"></div>

**Code quality contribution**:

- Made sure that code written adheres to [Java coding standard](https://se-education.org/guides/conventions/java/intermediate.html).
- Checked peer pull requests to ensure good code readability.

**User Guide contribution**:

- Added documentation for `note` feature. [#33](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/33)

**Developer Guide contribution**:

- Updated developer guide to include use cases. [#36](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/36)
- Added information about `note` feature into developer guide. [#105](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/105)
    - Added an overview of what the feature does.
    - Included explanation of current implementation and design considerations.
    - Created an activity diagram, sequence diagram and class diagram to aid in explanation.
- Updated UI component to include the changes made in the major UI changes ([#109](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/109) [#178](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/178) [#183](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/183)) mentioned above. [#105](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/105)
    - Edited the `UiClassDiagram` to include classes of the panels and cards created in PRs listed above.

**Review contribution**:

- Reviewed a total of **16** PRs.
- PRs reviewed with non-trivial comments: [#34](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/34) [#78](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/78) [#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174) [#184](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/184)

* Reported bugs and suggestions for other teams in the class: [7 bugs in PE-D](https://github.com/xianlinc/ped/issues)