---
layout: page
title: Developer Guide
---

Ailurus is a **desktop application** designed to aid Organising Committees from the Computing Faculty in managing and accounting for their administrative concerns. It provides users with the ability to plan and manage events and tasks for their members. 

The Developer Guide seeks to provide detailed documentation for developers to set up their environment, and understand the architecture and the different components, as well as their implementations in various commands.
It also informs developers of the requirements and instructions for manual testing for Ailurus.

* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is an extension of [SE-EDU AddressBook Level-3](https://se-education.org/addressbook-level3/)
* Our document formatting and content is referenced from [AY2122S1-CS2103T-T15-1](https://ay2122s1-cs2103t-t15-1.github.io/tp)
* Our project uses Scene Builder for UI components
* Libraries included: [JavaFX 8](https://docs.oracle.com/javase/8/javafx), [Jackson](https://github.com/FasterXML/jackson), [Junit5](https://github.com/junit-team/junit5)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `mdel /m 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

[comment]: <> (TODO: LEEROY)

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `MemberListPanel`, 
`EventListPanel`, `TasKListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` 
class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files 
that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.
com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in 
[`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Member` and `Event` object residing in the `Model`.

#### Current Implementations of UI

The GUI currently reflects the entered events and members recorded in Ailurus. Currently, there are two main windows 
that reflect the `Event` and `Member` objects that are residing in the `Model`. Directly adding or removing `Event` 
or `Member` would update the `EventListPanel` and `MemberListPanel` to show their respective `EventCard` 
and `MemberCard` accordingly. Each of the `EventCard` and `MemberCard` would display the fields under the 
corresponding `Event` and `Member` objects as discussed under [Model Component](#model-component).

### Logic component

[comment]: <> (TODO: LEEROY)
**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `MaddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a member).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("mdel /m 1")` 
API call.

![Interactions Inside the Logic Component for the `mdel /m 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Member` objects (which are contained in a `UniqueMemberList` object), all `Event` objects (which are contained in a `UniqueEventList` object).
* stores a `TaskList` reference that points to the `TaskList` object that contains all the `Task` objects of the currently 'selected' `Member` object (e.g. result of a `tlist` command)
* stores the currently 'selected' `Member`, `Event` and `Task` objects (e.g., results of a search query) as separate _filtered_ lists which are exposed to outsiders as an unmodifiable `ObservableList<Member>`, `ObservableList<Event>` and `ObservableList<Task>` respectively that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

Within the `Model` component,
* `Module` is the superclass of `Member`, `Event` and `Task`. `Module` has a `Name` field as the common attribute of its subclasses.
* `Event` has a `Name` field, `EventDate` field, and a field of a `HashMap<Member, Boolean>` to serve as a participant list with attendance.
* `Member` has its original fields from AB3 and an additional field of a `TaskList` to store all tasks assigned to the member.
* `Task` has a `Name` field, `TaskDeadline` field, and a `Boolean` field to represent the completion status of the task.

An alternative (arguably, a more OOP) model design is given below,

<img src="images/AlternativeModelClassDiagram.png" width="600" />

* `AddressBook` also stores all `Task` objects (which are contained in a `UniqueTaskList` object).
* Each `Member` object references `Task` object in the `UniqueTaskList`, instead of needing its own list of tasks.
However, this design has some issues in storage,
* Since Ailurus uses json files to store user data, json format members should at least store the unique identifier of each task the member had.
In our implementation, the unique identifier is `Name` and `TaskDeadline`, which are actually the main part of a `Task`.
* At the same time, json format member need to store the completion status of each referencing task.
* In all, implementing the storage of this member-task relation by using json file is likely to incur redundancy and error-prone,
so we decided to use an easier implementation, which is the current one.

A better implementation of the alternative design may involve using database management system like PostgreSQL,
a proposed entity relationship model diagram for the member-task relation is given here:[ER_diagram](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/docs/images/ER.png)


[comment]: <> (<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative &#40;arguably,) 

[comment]: <> (a more OOP&#41; model is given below. It has a `Position` list in the `AddressBook`, which `Member` references. This allows `AddressBook` to only require one `Position` object per unique POSITION, instead of each `Member` needing their own set of `Position` objects.<br>)


[comment]: <> (<img src="images/BetterModelClassDiagram.png" width="450" />)

[comment]: <> (</div>)

### Storage component

[comment]: <> (TODO: SAMUEL)
**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component converts data from Ailurus to `json` format and back. It utilizes the `Jackson` library for this purpose.
Address book data is both saved and read from `./data/ailurus.json` while user preference data is from `./preferences.json`.
If the files and directories do not exist, they are created with sample data when the Ailurus application is launched.

The `Storage` component,
* can save both address book data and user preference data in `json` format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (only one) depending on which functionality is needed.
* depends on some classes such as `Member`, `Event` and `Task` in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model` thus depends on them to convert data to json format).

From the name of the classes(beginning with JsonAdapted), we can tell which class they are storing in `json` format. All of these data are stored
in the `JsonSerializableAddressBook`.

For Example,
* `JsonAdaptedTask` stores `Task` in `json` format.
* `JsonAdaptedEvent` stores `Event` in `json` format. 


### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Workflow**

[comment]: <> (TODO: CHI XU)
Here is the Activity Diagram for a User when choosing the module and command to interact with in Ailurus:

![Activity Diagram for User Commands](images/CommandActivityDiagram.png)

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


<!--- TODO
#### Current Implementation of Event
New feature: Events
* Events can be added and deleted from event list via `eadd` and `edel` commands
* The participating members can be listed using the command `mlist /e EVENT_INDEX`
* New events created can have many participants selected from member list.
* <u>Design Decision</u>: Instead of only allowing adding of events and creating a command
  for adding participants separately, eadd command allows creation of complete event to
  minimise commands required to add them individually. The format is similar to `mdel` and `mlist` commands
  for familiarity with similar commands for other modules.

#### Future Plans for Event
Future plans for Events
* Include searching for the list of events for a participant
* Include filtering of events by month or events that are happening today.
* Include sorting of events by date, name or number of participants.
    * Dates should be in reverse chronological order so that upcoming events are shown first
* Include additional remarks or description for an event
#### Current Implementation for adding tasks
The proposed feature is achieved by getting the member(s) from the filtered member list
and use API from the model manager to add the task with given task name to each of the members.
The operations are exposed in the `Model` interface as `Model#getFilteredMemberlist()` and `Model#addTask()`.
Given below is an example usage scenario:
The user executes `tadd /n Take Attendance /d 21/10/2021 23:59 /m 1 /m 2`. The parser will be called upon to create a TaddCommandParser.
The parser will then parse the input to create a TaddCommand with task name as "Take Attendance", task deadline of "21/10/2021 23:59" and member indexes 1 and 2.
This command will add the task "Take Attendance" to the first and second member of the member list.
#### Current Implementation for deleting tasks
The proposed feature is achieved by getting the member(s) from the filtered member list
and use API from the model manager to delete the task with given task index from the member with given member index.
The operations are exposed in the `Model` interface as `Model#getFilteredMemberlist()` and `Model#deleteTask()`.
Given below is an example usage scenario:
The user executes `tdel /t 1`. The parser will be called upon to create a TdelCommandParser.
The parser will then parse the input to create a TdelCommand with task index as 1.
This command will delete the first task from the task list.
### List tasks feature for a member
#### Current Implementation for lists
The proposed feature is achieved by getting the member with given member index from the filtered member list
and use API from the model manager to list all the tasks of the member.
The operations are exposed in the `Model` interface as `Model#getFilteredMemberlist()` and `Model#updateFilteredTaskList()`.
Given below is an example usage scenario:
The user executes `tlist /m 1`. The parser will be called upon to create a TlistCommandParser.
The parser will then parse the input to create a TlistCommand with member index as 1.
This command will display all the tasks of the first member of the member list.
-->

### List members who attended event

[comment]: <> (TODO: Leeroy)

### Add member to an event

[comment]: <> (TODO: Leeroy)

### Add event to Event List

[comment]: <> (TODO: SAMUEL)

### Mark event members as attended

[comment]: <> (TODO: SAMUEL)

### Task add command: `tadd`
This feature allows Ailurus users to add a new task for multiple members identified by their `MEMBER_INDEX` displayed in the currently shown member list.

This feature can be accessed by using `tadd` command with parameters of
* `/n NAME`: the name of the task to add
* `/d DATE_TIME`: the deadline of the task to add
* multiple `/m MEMBER_INDEX`: multiple target members identified by the index displayed in the currently shown member list

Given below is the sequence diagram when a user provides a valid `tadd` command: `tadd /n meeting /d 11/11/2021 20:00 /m 1 /m 2`
to add a new task with its name and deadline to the first and second member displayed in the currently shown member list.

<img src="images/task/TaskAddSequenceDiagram.png" width="600" />

As seen in the diagram above, once the user entered the `tadd` command,
the `Logic` component will parse the parameters and create a `Task` object based on the parameters and a `TaddCommand` object.

<img src="images/task/TaskAddExecutionSequenceDiagram.png" width = "600" />

After `LogicManager` receives the `TaddCommand` object, 
1. `LogicManager` will call the `execute` method of `TaddCommand`.
2. `TaddCommand` will call `Model#getFilteredMemberList` to get the last shown member list.
3. `TaddCommand` will get the list of target members based on the `MEMBER_INDEX` list and the last shown member list.
4. `TaddCommand` will call `Model#addTask(targetMember, taskToAdd)` to add the new task to each target member .
5. `TaddCommand` will create a `CommandResult` object and return it to `LogicManager`.

### Mark a task as done command: `tdone`
This feature allows Ailurus users to mark multiple tasks as done. These tasks are identified by their `TASK_INDEX` displayed in the currently shown task list.

This feature can be accessed by using `tdone` command with multiple `/t TASK_INDEX` parameters.

Given below is the sequence diagram when a user provides a valid `tdone` command: `tdone /t 1 /t 2` to mark the first and second tasks in the task list as done.
<div markdown="span" class="alert alert-info">:information_source: **Note:** The diagram below does not include the details of the construction of `TdoneCommand` object,
because its implementation is similar to the construction of `TaddCommand` shown in the previous example.

</div>

<img src="images/task/TaskDoneSequenceDiagram.png" width = "600" />

After `LogicManager` receives the `TdoneCommand` object,
1. `LogicManager` will call the `execute` method of `TdonedCommand`.
2. `TdoneCommand` will call `Model#getFilteredTaskList` to get the last shown task list.
3. `TdoneCommand` will get the list of target tasks based on the `TASK_INDEX` list and the last shown task list.
4. `TdoneCommand` will construct a completed `Task` object based on each target task.
5. `TdoneCommand` will call `Model#setTask(targetTask, completedTask)` to set each original target task to a completed task.
6. `TdoneCommand` will create a `CommandResult` object and return it to `LogicManager`.

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

Step 2. The user executes `mdel /m 5` command to delete the 5th member in the address book. The `mdel` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `mdel /m 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `madd /n David …​` to add a new member. The `madd` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the member was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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

Step 5. The user then decides to execute the command `mlist`. Commands that do not modify the address book, such as `mlist`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. 
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `mdel`, just save the member being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* prefer desktop apps over other types
* has trouble managing the multitude of details related to the members of his club
* want to assign tasks for members
* has a need to organize and plan events for members
* likes typing and comfortable with CLI
* prefers typing to mouse interactions

**Value proposition**: 

* manage contacts faster than a typical mouse/GUI driven app
* manage club events and tasks/activities for large amount of members
* contact and personal information of members collated in an easily accessible location
* able to update details relating to members
* categorise members into groups for smoother planning


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

#### Member-related Functions

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add a new member               | update the increase or change in members |
| `* * *`  | user                                       | have address fields for members | |
| `* * *`  | user                                       | kick a member                | remove members or troublemakers from the club |
| `* * *`  | user                                       | have email address field for members | |

#### Task Functions

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | create tasks for my members or myself | |
| `* *`  | user                                       | see the completion status and description of tasks for members | know the requirements and status of the task |
| `*`  | user                                       | mark a task as completed, overdue or uncompleted | keep track of my tasks that are on-hand |
| `* *`  | user                                       | add a deadline to task | keep track of who has overdue tasks |
| `* * *`  | user                                       | delete already obscure and unnecessary tasks | have a cleaner task list |


#### Storage Functions

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | load members from other files | access and manage different sets of data |
| `* * *`  | user                                       | write my data to a file as save data | access them and resume at a later date |


#### Event Functions

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add all members of a particular event to one group | send notifications to only those involved |


#### Other miscellaneous Functions

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | find a member by name          | locate details of members without having to go through the entire list |
| `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
| `*`      | user with many members in the address book | sort members by name           | locate a member easily                                                 |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Ailurus` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add a member**

**MSS**

1.  User requests to add a member, providing necessary details.
2.  Ailurus adds the member.

    Use case ends.

**Extensions**

* 1a. Invalid format or incomplete details provided by user

    * 1a1. Ailurus shows an error message about missing or invalid input.

      Use case ends.

**Use case: UC2 - Delete a member**

**MSS**

1.  User requests to list members
2.  Ailurus shows a list of members
3.  User requests to delete a specific member in the list
4.  Ailurus deletes the member

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Ailurus shows an error message.

      Use case resumes at step 2.

**Use case: UC3 - Add a task to a member**

**MSS**

1.  User requests to add a task to a specific member, providing details if necessary.
2.  Ailurus adds the task to the member.

    Use case ends.

**Extensions**

* 1a. Invalid format or incomplete details provided by user

    * 1a1. Ailurus shows an error message about missing or invalid input.

      Use case ends.

* 1b. The given index is invalid.

    * 1b1. Ailurus shows an error message about invalid index.

      Use case ends.

**Use case: UC4 - Delete a task from a member**

**MSS**

1.  User requests to list tasks of a specific member
2.  Ailurus shows a list of tasks
3.  User requests to delete a specific task in the list
4.  Ailurus deletes the task

    Use case ends.

**Extensions**

* 1a. The given index of member is invalid

    * 1a1. Ailurus shows an error message.

      Use case ends.

* 2a. The list is empty.

  Use case ends.

* 3a. The given index of task is invalid.

    * 3a1. Ailurus shows an error message.

      Use case resumes at step 2.

**Use case: UC5 - Mark a task as done**

**MSS**

1.  User requests to list tasks of a specific member
2.  Ailurus shows a list of tasks
3.  User requests to mark a specific task as done
4.  Ailurus marks task as done

    Use case ends.

**Extensions**

* 1a. The given index of member is invalid

    * 1a1. Ailurus shows an error message.

      Use case ends.

* 2a. The list is empty.

  Use case ends.

* 3a. The given index of task is invalid.

    * 3a1. Ailurus shows an error message.

      Use case resumes at step 2.

**Use case: UC6 - Add an event**

**MSS**

1.  User requests to add an event, providing necessary details.
2.  Ailurus adds the event.

    Use case ends.

**Extensions**

* 1a. Invalid format or incomplete details provided by user

    * 1a1. Ailurus shows an error message about missing or invalid input.

      Use case ends.

**Use case: UC7 - Delete an event**

**MSS**

1.  User requests to list events
2.  Ailurus shows a list of events
3.  User requests to delete a specific event in the list
4.  Ailurus deletes the event

    Use case ends.

**Extensions**

* 1a. Invalid format or incomplete details provided by user

    * 1a1. Ailurus shows an error message about missing or invalid input.

      Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 members without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should run on user computer with double-click - no installer or additional libraries required.
5.  The system should respond within two seconds.
6.  The system should work on both 32-bit and 64-bit environments.
7.  The system should be usable by a novice who has never used a CLI app before.
8.  The project is expected to adhere to a schedule that delivers features of a milestone for every two weeks.    
9.  Should be able to hold up to 1000 event managers and participants without a noticeable delay (less than 2 seconds) in performance for typical usage.
10. The product should be for a single user i.e. (not a multi-user product), and should not depend on a remote server and does not require an installer. 
11. The software should work on the Windows, Linux, and OS-X platforms.
12. The GUI should work well with standard screen resolutions 1920x1080 and higher, and
    for screen scales 100% and 125%. It should be usable for resolutions 1280x720 and higher, and
    for screen scales 150%.    
    *{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file. If unable to double click, running `java -jar ailurus.jar` may be required, at the directory where `ailurus.jar` is in. Expected: Shows the GUI with a set of sample events and members. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Member tests

#### Adding a member

#### Deleting a member

1. Deleting a member while all members are being shown

   1. Prerequisites: List all members using the `mlist` command. Multiple members in the list.

   2. Test case: `mdel /m 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

   3. Test case: `mdel /m 0`<br>
      Expected: No member is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `mdel`, `mdel /m x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

#### Listing members of an event

#### Finding all members with a task

### Event tests

#### Adding an event

#### Deleting event

#### Editing an event

#### Adding a member to an event

#### Deleting a member from an event

#### Mark all members of event as attended

#### Mark specific members of event as attended

#### Unmark specific members of event

### Task tests

#### Adding a task

1. Adding a task to one or more members while all members are being shown
    1. Prerequisites: List all members using the `mlist` command. At least 3 members in the list.
    2. Test case: `tadd /n group meeting /d 22/11/2021 12:00 /m 1`<br>
    Expected: A new task will be added to the first member's task list. Details of the added task shown in the status message.
    3. Test caseL `tadd /n submit report /d 11/11/2021 23:59 /m 2 /m 3`<br>
    Expected: A new task will be added to the second and third members' task lists. Details of the added task shown in the status message.
    
#### Deleting a task

1. Deleting a task from the currently selected member's task list while the task list is being shown
   1. Prerequisites: List all members using the `tlist /m 1` command. Multiple tasks in the list.
   2. Test cases: `tdel /t 1`<br>
   Expected: First task is deleted from the currently shown task list. Success message will be shown in the status message.

#### Marking a task as completed

#### Marking a task as incomplete

#### Editing a task

#### Listing of tasks

### Saving data

1. Dealing with missing/corrupted data files

   1. Test case: Add invalid characters such as `@` to the file, such as after any `{`.
   Expected: Ailurus launches with no data.
   Solution: remove data file and restart ailurus application.

## **Appendix: Effort**

[comment]: <> (https://docs.google.com/document/d/10rPMnwmrThbKavWpjAlYiz8w-_u1WaiaiBqwbc30VcA/edit)

### Challenges faced

### Achievements in the project
