---
layout: page
title: Chng Xian Lin's Project Portfolio Page
---

# Chng Xian Lin's Project Portfolio Page

### Project: InsurancePal

InsurancePal is a client management software specially designed
to help manage the logistics of being an insurance agent.

### Summary of Contributions

Given below are my contributions to the project.

**Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=xianlinc)

**Enhancements Implemented**:

- **Implemented the** `note` **command** [#53](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/53)
    - **What:** The note command is a feature that allows the user to add a note to a client in InsurancePal.
    - **Justification**: One thing an insurance agent needs to do is keep track of information about the client. Although the other features in InsurancePal keep track of certain details of clients, their scope is too limited and sometimes information does not fall into any of the existing categories. The user would benefit from having a feature that allows them to type long strings of text as if it was a notepad. Therefore, the `note` command allows the insurance agent to store any extra notes about the client such as their birthday or their existing medical history.
    - **Classes created**:
        - `NoteCommandParser`
        - `NoteCommand`
        - `Note`
    - **Tests Written**:
        - `NoteCommandTest`
    - **Effort Expended**:
        - The note command requires a good level of understanding of the entire application. This is because it makes changes to the `Logic`, `Model` and `Storage` components of the application.
- **Made major changes to the UI** [#109](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/109) [#178](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/178) [#183](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/183)
    - **What**:
        - Added functionality to display a sorted list of upcoming appointments of the clients in a panel in the UI.
        - Added functionality to display all claims of the clients in a panel in the UI.
        - Set style of the `Appointment` panel and `Claim` panel and the text inside it using CSS.
    - **Justification**: An Insurance agent would have an easier time accessing these critical pieces of information (`Appointment` and `Claim`)  of their clients if it was displayed as an aggregated list that they can scroll through.
    - **Effort Expended**
        - Making modifications to the UI requires a good level of understanding of the `UI` component as well as the way it interacts with the `Logic`, `Model` components.
        - Good knowledge of `javafx` is needed to create the panels and create cards using `ListCell` and a custom `CellFactory`.
            - 2 panels were created, namely `AppointmentListPanel` and `ClaimListPanel`.
            - 2 cards were created, namely `AppointmentCard` and `ClaimCard`.
        - Precise and detailed work was required when adjusting the layout of UI components.
        - Knowledge of CSS was needed to make adjustments to the layout and style of UI components.
    - **Difficulties Faced**:
        - There was a bug where `ClaimPanel` only showed one `ClaimCard` per `Person` (very difficult to fix). [#178](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/178)
            - Fixing this bug was very difficult and required knowledge and practical application of the observer pattern.
            - The intial logic of the `ClaimListPanel` was to create multiple `ClaimCard` from the `Claim`s of each person in the `personList`.
            - The above logic did not work properly because the `CellFactory` only creates one `ClaimCard` for each client in the `personList`.
            - To rectify this problem, a change in logic was needed.
                - The `CellFactory` now generates `ClaimCard`s using a list of `Claim`s instead of a `personList` meaning each `Claim` in the list creates one `ClaimCard`, solving our previous problem.
                - This change in logic created another bug where the list would not automatically update when `personList` is changed.
                - To fix this, a `ListChangeListener` was added to personList. When the `ListChangeListener` is triggered upon a change in `personList`, the `update` method is called, which creates a `claimList` from the changed `personList` and uses it to generate new and updated `ClaimCards`.
        - There were **truncation issues** with the contents of the three cards. [#183](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/183)
            - The `Tag` and `Insurance` **truncation issue** (difficult to fix):
                - Both `Tag` and `Insurance` face the same issue, so the explanation below will focus on `tag` to explain the issue for both `tag` and `insurance`.
                - If the `tag` created exceeds the panel width, the length of the `tag` that exceeds the panel would not be visible.
                - Solving this issue was difficult and required extensive research into `javafx` and the interactions between `Label`s and `FlowPane`s.
                - In `javafx`, a `Label` is unable to be text wrapped in a `FlowPane`, therefore alternatives were tested, such as using a `Text` instead of `Label` to display our tags.
                - However, a `Text` has limited styles, therefore it did not fit our needs.
                - I determined that based on the limitations of `javafx`, it would be better to impose a character length for `tag` and `insurance`.
            - There was a `Label` **truncation issue** (difficult to fix):
                - If the `Label` created exceeds the panel width, the length of the `Label` that exceeds the panel would not be visible.
                - To fix this, text wrapping was applied on every label in a card, so `Label`s would not exceed the panel width.
                - This introduced another bug where the size of each card did not adjust itself to accommodate the new height of the `Label`s due to text wrapping, causing text overrun.
                - Fixing this new bug required good knowledge of the **SceneBuilder** tool and how to use `FXML` to compute the size of each card based on its contents.

**Code quality contribution**:

- Made sure that code written adheres to [Java coding standard](https://se-education.org/guides/conventions/java/intermediate.html).
- Checked peer pull requests to ensure good code readability.

**User Guide contribution**:

- Added documentation for 'note' feature. [#33](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/33)

**Developer Guide contribution**:

- Updated developer guide to include use cases. [#36](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/36)
- Added information about `note` feature into developer guide. [#105](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/105)
    - Added an overview of what the feature does.
    - Included explanation of current implementation and design considerations.
    - Created an activity diagram, sequence diagram and class diagram to aid in explanation.
- Updated UI component to include the changes made in the major UI changes ([#109](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/109) [#178](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/178) [#183](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/183)) mentioned above. [#105](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/105)
    - Edited the UiClassDiagram to include classes of the panels and cards created in PRs listed above.

**Review contribution**:

- Reviewed a total of **16** PRs.
- PRs reviewed with non-trivial comments: [#34](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/34) [#78](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/78) [#174](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/174) [#184](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/184)

* Reported bugs and suggestions for other teams in the class: [7 bugs in PE-D](https://github.com/xianlinc/ped/issues)
