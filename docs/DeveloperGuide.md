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

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

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
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

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

* has a need to manage a significant number of contacts
* sociable and has multiple different friend groups that can partially overlap
* regularly organises birthday parties for these contacts
* regularly invites other contacts to these parties
* needs to keep track of different groups who are invited to different parties
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* receive reminders about birthdays or lookup upcoming birthdays
* retrieve contact details of all members of a group quickly and easily
* partition contacts by group
* keep track of upcoming birthdays and invite lists of these parties


### User stories

Chosen user stories v1.2

| As a... | I want to... | So that I can... |
|---|---|---|
| Person who loves Birthdays    | Track birthdays                       | Easy way of checking the birthday of friends
| Sociable person               | Partition frequent contacts           | Easy to access people within each group
| Party Organiser               | Add people to a party list            | Easily extend a party invite list
| Party Organiser               | Remove people to a party list         | Easily Remove people from only one party
| Party Organiser               | Search contacts by invited party      | Generate invite list
|  |  |  |
| User                          | App need to be bug free               | So that I can rely on the app to give me accurate contact details
| Fast typer                    | Use sentences to interact with the app| Can utilise the functions of the app quicker without using a mouse
| Sociable person               | To be able to save up to 100 contacts | I can keep all my friends contacts
| User with many contacts       | Search timings to be reasonable       | Do not have to wait too long for search results
| Forgetful user                | Track contacts                        | Retrieve Contact details whenever I need to contact somebody

Nice to have user stories v1.2

| As a... | I want to... | So that I can... |
|---|---|---|
| Beginner user                 | View feedback on wrong commands       | Format commands better
| User                          | Can modify contact details            | So that I can update my contact details
| Beginner user                 | View feedback on wrong commands       | To format commands better
| Advanced user                 | Add nicknames to my contacts          | Find my close friends using their nicknames

Future versions user stories

| As a... | I want to... | So that I can... |
| --- | --- | --- |
| Beginner              | See sample commands                               | To see what I should be typing.
| Beginner              | View tutorials                                    | To see how the app should be used
| Beginner              | View helpful prompts                              | To see what can be done with the app
| New user              | Immediately add my first contact                  | Use the product without reading a manual
|  |  |  |
| Advanced user         | Disable suggestions                               | Avoid accidentally pressing on them
| Experienced user      | Quickly add contact without my mouse              | Just type
| Experienced user      | Get suggestions on commonly run commands          | don't have to keep typing the commands they use frequently.
|  |  |  |
| Forgetful person      | Use the help button                               | Remind myself how to use the app
| Forgetful person      | Select from existing tags                         | Have consistent labelling
|  |  |  |
| Party Organiser       | Look for contacts details for all my friends      | Send out my invites
| Party Organiser       | Generate mailing list from my contacts            | Contact all invitees as a group
| Party Organiser       | Generate csv file of my invitees                  | Take attendance or make external notes
| Party Organiser       | Generate a mail to link for my selection          | Send emails to a group
|  |  |  |
| Loves Birthdays       | Birthday reminders                                | Won’t miss any of his/her friends’ birthdays
| Sociable person       | Pin frequent contacts                             | Access these contacts easily
|  |  |  |
| Fast typer            | Use the app with little lag                       | Can utilise functions of the app quickly
|  |  |  |
| Person                | Add new optional fields                           | Add my own types of data
| Youth                 | Use Emojis                                        | Add <3 to people i like and poop to people I don’t
| University student    | User experience to be smooth                      | Find who they want to contact easily
|  |  |  |
| User with many contacts   | Show search suggestions                       | Easily find contacts
| User with many contacts   | Search timings to be reasonable               | Do not have to wait too long for search results

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a person**

**MSS**

1.  User chooses to add a person
2.  User provides the person's details
3.  Connections create an entry for that person's details

    Use case ends.

**Extensions**

* 2a. User provides incomplete details
    
    * 2a1. Connections show an error message.
      
      Use case resumes at step 2
    
* 2b. User provides details identical to an existing entry in Connections

    * 2b1. Connections remind User that this is a duplicate
    
      Use case resumes at Step 1
    

**Use case: Clear all entries**

**MSS**

1.  User request to clear all entries
2.  Connections deletes all entries

    Use case ends.

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  Connections show a list of persons
3.  User requests to delete a specific person in the list
4.  Connections delete the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

**Use case: Edit a person's details**

**MSS**

1.  User requests to list persons
2.  Connections show a list of persons
3.  User provides his/her index in the list along with the replacement information
4.  Connections reflect the edits that were made

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    
    * 3a1. Connections show an error message.
      
      Use case resumes at step 2.

**Use case: Exit the program**

**MSS**

1.  User request to exit
2.  Connections exit

    Use case ends.

**Extensions**

**Use case: Find a person**

**MSS**

1.  User chooses to look for an entry
2.  Use provides the search term
3.  Connections return all entries that matches the search term

    Use case ends.

**Extensions**

* 2a. Connections is empty

    * 2a1. Connections display a message to indicate no entries
    
      Use case ends.

* 2a. No existing entries match the search term provided
    
    * 2a1. Connections display a message to indicate no matching entries
    
      Use case resumes at step 2.

**Use case: Find people via Tags**

**MSS**

1.  User choose to look for an entry
2.  User provides the tag
3.  Connections return all entries that matches the tag

    Use case ends.

**Extensions**

* 2a. Connections is empty

    * 2a1. Connections display a message to indicate no entries

      Use case ends.

* 2a. No existing entries match the tag provided

    * 2a1. Connections display a message to indicate no matching entries

      Use case resumes at step 2.
    

**Use case: List everyone in the address book**

**MSS**

1.  User requests to list all entries
2.  Connections display all entries
    
    Use case ends.

**Extensions**

* 2a. Connections is empty

  Use case ends.

**Use Case: Obtaining more information about a command**

**MSS**

1.  User requests for help
2.  User provides the command
3.  Connections explain how to use the command and provide examples

    Use case ends.

**Extensions**

* 2a. Command provided is not supported

    * 2a1. Connections display an error message
      
      Use case ends.

**Use case: Tag a person**

**MSS**

1.  User requests to list persons
2.  Connections show a list of persons
3.  User provides his index in the list along with the tags to be added
4.  Connections add those tags from that entry

    Use case ends.

**Extensions**

* 2a. Connections is empty

  Use case ends.

* 3a. Index provided is out of range

    * 3a1. Connections display an error message
    
      Use case resumes at step 2.
    
* 3b. Target entry already has the tag specified

    * 3b1. Connections display an error message
    
      Use case resumes at step 2.

**Use case: Untag a person**

**MSS**

1.  User requests to list persons
2.  Connections show a list of persons
3.  User provides his index in the list along with the tags to be removed
4.  Connections remove those tags from that entry

    Use case ends.

**Extensions**

* 2a. Connections is empty

  Use case ends.

* 3a. Index provided is out of range

    * 3a1. Connections display an error message

      Use case resumes at step 2.

* 3b. Target entry do not have the tag specified

    * 3b1. Connections display an error message

      Use case resumes at step 2.
    
*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.


*{More to be added}*

### Glossary
* **Connections**: The name of our product
* **Entry**: An item written or printed in a diary, list, account book, or reference book.
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Search Term**: A search term is what users key in when they want to find something specific
* **Tag**: A label attached to someone or something for the purpose of identification or to give other information.

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
