---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-T09-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `ModuleLessonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` and `ModuleLesson` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class uses `CommandWord` to find out which `Command` the user wants. `AddressBookParser` then creates the required `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` and `ModuleLesson` objects (which are contained in a `UniquePersonList` and `UniqueModuleLessonList` object respectively).
* stores the currently 'selected' `Person` and `ModuleLesson` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` and `ObservableList<ModuleLesson>` that can be 'observed' e.g. the UI can be bound to those lists so that the UI automatically updates when the data in the lists change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-T09-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Find feature

#### Implementation
The find command returns contacts that matches the input keywords. Initially, it only returns contacts that fully matches the keywords.
Given the following example contact book:
* Contact #1: Jason
* Contact #2: Jasmine
* Contact #3: Bob

A search with the command `find n/Jas` would return nothing, as `Jas` is not a complete match with any of the names of the three contacts.

To better fit our target user, someone who wishes to work fast, we have decided to allow partial matches for the find command.
This is done by altering the condition in the search predicate, from accepting full word matches to accepting even partial word matches:

`return Arrays.stream(wordsInPreppedSentence).anyMatch(preppedWord::equalsIgnoreCase);`

changed to

`return Arrays.stream(wordsInPreppedSentence).anyMatch(x -> x.toLowerCase().contains(preppedWord.toLowerCase()));`

Also, to facilitate filtered searches, we have implemented finding using prefixes:
* `find n/` to find by name
* `find m/` to find by module code

Similar to other commands, this is done using a `argumentTokenizer` to parse for the above prefixes,
before the correct `Predicate` is instantiated and used for finding the contact.

#### Design considerations:

**Aspect: Previous and current version of find command:**

* **Alternative 1 (previous version):** Search keyword needs to match entirely.
    * Pros: Only the correct contact is returned.
    * Cons: Takes time to type entire names to find the contact you want.

* **Alternative 2 (current version):** Search keyword just needs to match partially.
    * Pros: Faster searches with just partial keywords.
    * Cons: Contacts you did not mean to retrieve are also displayed.

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
* student
* tech savvy
* is a Teaching Assistant (TA)
* does not have much time and wants to get things done fast
* lazy and do not want complicated steps


**Value proposition**:<br />
manage contacts faster than a typical mouse/GUI driven app<br />
with a categorization ability and streamlined creation/reading/updating/deleting processes.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *` | potential user | be able to download the app | use it to keep track of my contacts |
| `* * *` | potential user | be able to start the app | see how the app would look like |
| `* * *` | user | be able to create a new contact in the address book | save a contact |
| `* * *` | user | be able to retrieve an existing contact in the address book | retrieve more details about the contact |
| `* * *` | user | be able to update an existing contact in the address book | change information about my student if I made a mistake when adding them |
| `* * *` | user | be able to delete an old contact in the address book | delete contacts whom I am not in contact with anymore |
| `* * *` | user | be able to see their contact details | copy the contact details to contact them |
| `* * *` | new user | be able to find out what kind of commands are available | use the app as intended |
| `* * *` | user | be able to close the app | stop using it |
| `* *` | user | be able to purge all current data | get rid of sample/experimental data I used for exploring the app |
| `* *` | user | be able to view my contacts in alphabetical order | see the contacts in more intuitive way |
| `* *` | user | be able to delete by module | delete all contacts linked to that module |
| `* *` | user | be able to delete contacts by groups | delete many contacts in one go |
| `* *` | user | be able to see the group a contact belongs to | recall how I know the particular person |
| `* *` | user | be able to search for contacts | save time have to scroll the entire contact list |
| `* *` | user | be able to retrieve data fast | get the contact in the fastest time possible |
| `* *` | user | be able to search by categories | ignore unrelated contacts |
| `* *` | user | be able to add a description about a contact | add more details about the contact |
| `* *` | user | have access to a help page | know how to navigate the app |
| `* *` | user | be able to create a class card | see my current classes |
| `* *` | user | be able to see class details | recall the specifics of the class |
| `* *` | user | be able to update class details | ensure my class details are correct |
| `* *` | user | be able to delete a class | remove the classes that are irrelevant |
| `* *` | potential user exploring the app | be able to see the app populated with sample data | easily see how the app works |
| `*` | forgetful user | be able to have reminders about upcoming classes | conduct classes punctually |
| `*` | user | be able to set up profile picture for the contact | recognise the person |
| `*` | user | be able to set up profile picture for myself | add a personal touch to the application |
| `*` | Expert user | be able to set up shortcuts | do things faster |


### Use cases

(For all use cases below, the **System** is `contHACKS` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Creating a contact**

**MSS**

1. User requests to create a contact and inputs contact details
2. contHACKS creates the contact and displays the newly added contact

Use case ends

**Extensions**
* 1a. User fails to provide compulsory field
    * 1a1. contHACKS shows an error message<br />
    Use case ends


* 1b. User input details in a wrong format
    * 1b1. contHACKS shows an error message<br />
    Use case ends


**Use case: Finding a contact**

**MSS**

1. User requests to find a contact based on input details
2. contHACKS shows a list of contacts that match input details

Use case ends

**Extensions**
* 1a. There are no contacts that matches the input details
    * 1a1. contHACKS displays an empty list<br />
    Use case ends


* 1b. User finds by name
    * 1b1. contHACKS displays a list of contacts with names that match the input<br />
    Use case ends


* 1c. User finds by module code
    * 1c1. contHACKS displays a list of contacts that are tagged with the module code<br />
    Use case ends


* 1d. User input details in a wrong format
    * 1d1. contHACKS shows an error message<br />
    Use case ends


**Use case: Updating a contact**

**MSS**

1. User requests to update a specific contact and inputs the new contact details
2. contHACKS updates the contact with the inputted details and displays the updated contact

Use case ends

**Extensions**
* 1a. Contact does not exist
    * 1a1. contHACKS shows an error message<br />
    Use case ends


* 1b. User input details in a wrong format
    * 1b1. contHACKS shows an error message<br />
    Use case ends


**Use case: Adding remark to a contact**

**MSS**

1. User requests to add a remark to a specific contact
2. contHACKS updates the contact with the inputted remark and displays the updated contact

Use case ends

**Extensions**
* 1a. Contact does not exist
    * 1a1. contHACKS shows an error message<br />
      Use case ends


* 1b. User input the remark in a wrong format
    * 1b1. contHACKS shows an error message<br />
      Use case ends


**Use case: Deleting a contact**

**MSS**

1. User requests to delete a specific contact
2. contHACKS deletes the contact and displays the updated contact list

Use case ends

**Extensions**
* 1a. Contact does not exist
    * 1a1. contHACKS shows an error message.<br />
      Use case ends


**Use case: Listing the contact list**

**MSS**

1. User requests to for the whole contact list
2. contHACKS displays the whole contact list

Use case ends


**Use case: Clear all contacts**

**MSS**

1. User requests to clear all contacts
2. contHACKS delete all the contacts and displays an empty contact list

Use case ends


**Use case: Getting help manual**

**MSS**

1. User requests to for a help manual
2. contHACKS displays the help manual

Use case ends


**Use case: Exiting the application**

**MSS**

1. User requests to exit the application
2. contHACKS closes

Use case ends


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The user should have basic knowledge of the Command Line Interface to operate the application smoothly.
5.  Should be able to hold more than 100 contacts with no noticeable drop in performance.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Tag**: A categorisation given to a contact


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
