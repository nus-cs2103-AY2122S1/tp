---
layout: page
title: Developer Guide
---
PlannerMD is an easy-to-use command-line interface (CLI) application that helps clinic receptionists seamlessly integrate the daily appointments and the unique requirements of each patient into a single application. PlannerMD expedites the manual processes found in a clinic and saves clinics receptionists plenty of time while also reducing human error.

The purpose of the Developer Guide is to guide you through the architecture of our program, so you can familiarise yourself with its underlying structure.

## Navigating this Developer Guide
Take note of some syntax we will frequently use throughout the Developer Guide:

| Syntax | Description |
|--------|------------|
| **Bold** | keywords |
| :bulb: **Tip:** | Useful tips |
| `markdown` | Classes or methods |

* Table of Contents
    - [Acknowledgements](#acknowledgements) 
    - [Setting up, getting started](#setting-up)
    - [Design](#design)
        - [Architecture](#architecture)
        - [UI Component](#ui)
        - [Logic Component](#logic)
        - [Model Component](#model)
        - [Storage Component](#storage) 
        - [Common classes](#common-classes)
    - [Implementation](#implementation)
    - [Documentation, logging, testing, configuration, dev-ops](#documentation)
    - [Appendix: Requirements](#appendix-requirements)
        - [Product scope](#product-scope)
        - [User stories](#user-stories)
        - [Use cases](#use-cases)
        - [Non-functional Requirements](#nfr)
        - [Glossary](#glossary)
    - [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
        - [Launch and shutdown](#launch-and-shutdown)
        - [Deleting a person](#delete-person)
        - [Saving data](#saving-data)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements** <a name="acknowledgements"/> 

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started** <a name="setting-up"/>

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design** <a name="design"/>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture <a name="architecture"/>

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/plannermd/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/plannermd/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui): The UI of the App.
* [**`Logic`**](#logic): The command executor.
* [**`Model`**](#model): Holds the data of the App in memory.
* [**`Storage`**](#storage): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component <a name="ui"/>

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonTab`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `PersonTab` is a tab layout that consists of a `PatientListPanel` and a `DoctorListPanel`. Only one of the tabs is displayed to the user at a particular time, and the user can toggle between the 2 tabs.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component <a name="logic"/>

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it gets the current state (i.e. whether the user is on the `Patients` or `Doctors` tab) from the `Model`.
2. It then uses the `PlannerMdParser` class to parse the user command.
3. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddPatientCommand`) which is executed by the `LogicManager`.
4. The command can communicate with the `Model` when it is executed (e.g. to add a patient).
5. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `PlannerMdParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `PlannerMdParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeleteDoctorCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component <a name="model"/>
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/plannermd/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

* stores the plannerMd data i.e., all `Patient` and `Doctor` objects (which are contained in `UniquePersonList<Patient>` and `UniquePersonList<Doctor>` respectively).
* stores the currently active `State` (which determines which list of Person, `Patient` or `Doctor`, to interact with)
* stores the currently 'selected' `Patient` or `Doctor`  objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as unmodifiable `ObservableList<Patient>` and `ObservableList<Doctor>` respectively that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component <a name="storage"/>

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/plannermd/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both PlannerMD data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `PlannerMdStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes  <a name="common-classes"/>

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation** <a name="implementation"/>

This section describes some noteworthy details on how certain features are implemented.

### Stateful PlannerMd
With the introduction of two types of `Person` (`Patient` and `Doctor`) and their respective lists,
a state is used to determine which list should be interacted with.

The state is maintained in `ModelManager`
* Two possible states (`State.PATIENT` and `State.Doctor`)
* `ModelManager::toggleState` is used to switch between states
* The UI displays the list according to the state. (eg. if the state is `State.PATIENT`, UI displays the filtered list of patients)
* Commands are parsed based on the state. (eg. if a valid 'add' command is parsed and the state is `State.PATIENT`, an `AddPatientCommand` is executed)

### \[Upcoming\] Propagating Person Changes to Appointment List
Since specific patients and doctors within the records are directly referenced in appointments,
changes in patients and doctors through user command or otherwise needs to be propagated through the Appointment list.
* When patients or doctor details are changed, these changes will be reflected in appointments they are a part of.
* When patients or doctor deleted, appointments they are a part of will be deleted as well.

The Sequence Diagram below illustrates the interactions within the Model component for the deletePatient(target) API call.

<img src="images/PropagateChangesDiagram.png" width="450" />

### Adding an appointment

### Deleting an appointment

### Editing an appointment

### Filtering appointments

### Storing an appointment

The creation of a `JsonAdaptedAppointment` will create the respective `JsonAdaptedPatient` and `JsonAdaptedDoctor` involved in the appointment The creation of `JsonAdaptedPatient` and `JsonAdaptedDoctor` will create the number of tags that the respective `Patient` and `Doctor` objects have.

We use `JsonAdaptedPatient` and `JsonAdaptedDoctor` that are used to store `Patient` and `Doctor` standalone objects 
as json to ensure that these objects are stored in a consistent format, whether as a standalone `Patient`/`Doctor` or
a `Patient`/`Doctor` in an `Appointment`.

The creation of a `JsonAdaptedAppointment` will also create the `JsonAdaptedSession`
which is synonymous to the `Session` object contained in an `Appointment`.

The Sequence Diagram below illustrates the interactions within the Storage component for the creation of a `JsonAdaptedAppointment`.

<img src="images/AppointmentStorageSequenceDiagram.png" width="550" />

--- 

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**  <a name="documentation"/>

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**  <a name="appendix-requirements"/>

### Product scope  <a name="product-scope"/>

**Target user profile**:

* has a need to manage a significant number of patients
* needs quick access to a patient's data and profile
* needs to arrange/deconflict appointments for doctors
* prefers desktop applications over other types
* can type fast and prefers typing to mouse interactions
* is reasonably comfortable using CLI apps



**Value proposition**: easily manage patients' information and doctors' appointments faster than a typical mouse/GUI driven app


### User stories  <a name="user-stories"/>
These are some user stories we took into account when designing PlannerMD:

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | clinic receptionist                        | add a new patient              |                                                                        |
| `* * *`  | clinic receptionist                        | delete a patient               | remove entries that I no longer need                                   |
| `* * *`  | clinic receptionist                        | view a patient's personal details| view his/her personal details to better understand them and contact them |
| `* * *`  | clinic receptionist                        | view a patient's risk profile| view his/her risk |
| `* * *`  | clinic receptionist                        | edit a patient's personal details| change his/her personal details should it change|
| `* * *`  | clinic receptionist                        | edit a patient's risk profile| change his/her risk profile should it change |
| `* * *`  | clinic receptionist                        | find a patient by name         | locate details of patients without having to go through the entire list |
| `* * *`  | clinic receptionist                        | add a doctor |     |
| `* * *`  | clinic receptionist                        | view a doctor's personal details| view his/her personal details to contact him/her |
| `* * *`  | clinic receptionist                        | edit a doctor's personal details| edit his/her details should it change |
| `* * *`  | clinic receptionist                        | delete a doctor | remove entries that I no longer need |
| `* * *`  | clinic receptionist                        | view a doctor's schedule | schedule appointments during available times                                  |
| `* * *`  | clinic receptionist                        | add an appointment |                                                 |
| `* * *`  | clinic receptionist                        | delete an appointment | cancel the appointment |
| `* * *`  | clinic receptionist                        | edit an appointment | reschedule the appointment when the patient or doctor asks for it |
| `* * *`  | clinic receptionist                        | view the appointments that have been scheduled | see what appointments the clinic has at any time|
| `* *`    | clinic receptionist with many patients to manage| sort patients by name     | locate a patient easily                                                |
| `* *`    | clinic receptionist with many patients to manage| sort patients by risk     | locate a patient easily                                                |
| `* *`    | clinic receptionist with many patients to manage| sort doctors by name      | locate a doctor easily                                                |
| `* *`    | clinic receptionist | add remarks for a patient | add additional information about the patient |            |
| `* *`    | clinic receptionist | edit remarks for a patient| change any additional information about the patient                                             |
| `*`      | clinic receptionist                        | hide private contact details   | minimize chance of someone else seeing them by accident                |
 
### Use cases  <a name="use-cases"/>
These are some use cases to familiarise with the flow of our application: 

(For all use cases below, the **System** is `PlannerMD` and the **Actor** is the `receptionist`, unless specified otherwise)

**Use case: Add a patient/doctor**

**MSS**

1. Receptionist requests to add a patient/doctor by typing in their details
2. PlannerMD adds the patient/doctor which is reflected immediately in the list

    Use case ends.

**Extensions**

* 1a. PlannerMD detects that compulsory details are missing.

    * 1a1. PlannerMD shows an error message.

    Use case resumes at step 1.

* 1b. PlannerMD detects data entered with invalid format.

    * 1b1. PlannerMD shows an error message stating the required format.

    Use case resumes at step 1.

**Use case: Delete a patient/doctor**

**MSS**

1.  Receptionist requests to list patients/doctors
2.  PlannerMD shows a list of patients/doctors
3.  Receptionist requests to delete a specific patient/doctor in the list
4.  PlannerMD deletes the patient/doctor which is reflected immediately in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.
  
  Use case ends.

**Use case: Adding a risk profile to a patient**

**MSS**

1.  Receptionist requests to find a certain patient by typing his/her name in the CLI
2.  PlannerMD shows a list of patients with that name
3.  Receptionist requests to add a risk profile to a specific person in the list
4.  PlannerMD adds the risk profile which is reflected immediately in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

**Use case: Editing the risk profile of a patient (Coming soon)**

**MSS**

1.  Receptionist requests to find a certain patient by typing his/her name in the CLI
2.  PlannerMD shows a list of patients with that name
3.  Receptionist requests to add a risk profile tag of a specific person in the list
4.  PlannerMD adds the risk profile tag which is reflected immediately in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

**Use case: Adding a tag to a patient**

**MSS**

1.  Receptionist requests to list patients
2.  PlannerMD shows a list of patients
3.  Receptionist requests to add a tag to a specific person in the list
4.  PlannerMD adds the tag which is reflected immediately in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

* 3b. The given tag is invalid.

    * 3b1. PlannerMD shows an error message.

      Use case resumes at step 2.

**Use case: Deleting a tag from a patient**

**MSS**

1.  Receptionist requests to list patients
2.  PlannerMD shows a list of patients
3.  Receptionist requests to delete a tag from a specific person in the list
4.  PlannerMD deletes the tag which is reflected immediately in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

* 3b. The given tag is non-existent.

    * 3b1. PlannerMD shows an error message.

      Use case resumes at step 2.

**Use case: Editing personal details of a patient (Coming soon)**

**MSS**

1.  Receptionist requests to find a certain patient by typing his/her name in the CLI
2.  PlannerMD shows a list of patients with that name
3.  Receptionist requests to edit the personal details of a specific person in the list
4.  PlannerMD edits the patient's personal details which is reflected immediately

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

**Use case: Scheduling an appointment**

**MSS**

1.  Receptionist requests to find a certain doctor by typing his/her name in the CLI
2.  PlannerMD shows a list of doctors with that name and the corresponding appointments
3.  Receptionist sees that doctor is available and requests to schedule the appointment
4.  PlannerMD updates the doctor's schedule which is reflected immediately

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

* 4a. The appointment time clashes with the doctor's schedule

    * 4a1. Receptionist decides to reschedule to a different time.
    * 4a2. Receptionist requests to reschedule to a different time.
    * 4a3. PlannerMD updates the doctor's schedule which is reflected immediately.

      Use case ends.

**Use case: Deleting an appointment**

**MSS**
1.  Receptionist requests to list appointments
2.  PlannerMD shows the list of appointments
2.  Receptionist requests to delete an appointment
3.  PlannerMD deletes the appointment from the appointment list which is reflected immediately

    Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. PlannerMD shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements  <a name="nfr"/>

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should work independent of network connection.
3. Should respond to users' commands within two seconds.
4. Should be able to store 5000 unique entries of patients and doctors.
5. The data should be stored on the user's local machine.
6. The data should be available for backup and portable to another computer.
7. The user interface should be simple and intuitive enough for any users.
8. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
9. The project is expected to adhere to a schedule that delivers a feature set every two weeks.

*{More to be added}*

### Glossary <a name="glossary"/>

* **Appointment**: Arrangement to meet between a doctor and a patient
* **CLI**: Command Line Interface
* **GUI**: Graphical User Interface
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Patient**: The individual that visits the clinic
* **Personal details** personal information including a name, contact number, email, address, date of birth, whatever tags the receptionist gives
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Risk profile**: The health status and severity of the condition of a patient
* **Tag**: A label attached to a patient for easy identification or providing additional information
* **MSS**: Main Success Scenario in the use cases.
* **Extensions**: "Add-ons" to the MSS that describes an exceptional/alternative flow of events. 

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing** <a name="appendix-instructions-for-manual-testing"/>

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown  <a name="launch-and-shutdown"/>

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Adding a patient <a name="add-patient"/>

### Deleting a patient  <a name="delete-patient"/>

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Editing a patient <a name="edit-patient"/>

### Finding a patient <a name="find-patient"/>

### Listing all patients <a name="list-patients"/>

### Adding a doctor <a name="add-doctor"/>

### Deleting a doctor  <a name="delete-doctor"/>

### Editing a doctor <a name="edit-doctor"/>

### Finding a doctor <a name="find-doctor"/>

### Listing all doctors <a name="list-doctors"/>

### Adding an appointment <a name="appointment"/>

### Deleting an appointment  <a name="delete-appointment"/>

### Editing an appointment <a name="edit-appointment"/>

### Filtering all appointments <a name="filter-all-appointments"/>

### Filtering upcoming appointments <a name="filter-upcoming-appointments"/>

### Listing all appointments for today <a name="list-appointments"/>


### Saving data  <a name="saving-data"/>

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
