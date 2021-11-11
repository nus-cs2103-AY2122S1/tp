---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project was based on the [_AddressBook Level-3_](https://se-education.org/addressbook-level3/) project created by the SE-EDU initiative.
<br> Libraries used: [_JavaFX_](https://openjfx.io/), [_Jackson_](https://github.com/FasterXML/jackson), [_JUnit5_](https://github.com/junit-team/junit5)

--------------------------------------------------------------------------------------------------------------------
## **Introduction**
Welcome to the developer's guide for Trace2Gather! Trace2Gather is a **desktop app for managing hotel rooms and guests, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
<br> This guide is meant for developers who may want to contribute to our code base, or use our codebase to build their own project.

--------------------------------------------------------------------------------------------------------------------
## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

This section shows the various high level components that make up the application, how they interact with one another, and their lower level implementation.

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `guest Alex`.

<img src="images/ArchitectureSequenceDiagramForDG.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

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
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("guest Alex")` API call.

![Interactions Inside the Logic Component for the `guest Alex` Command](images/FindGuestSequenceDiagram.png)

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

* stores the address book data.
    * All `Person` objects are contained in a `UniquePersonList` object.
    * All `Room` objects are contained in a `RoomList` object.
    * All `Residency` objects are contained in a `ResidencyBook` object.
* stores the currently 'selected' object(s) (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
    * `Person` objects are stored in `ObservableList<Person>`
    * `Room` objects are stored in `ObservableList<Room>`
    * `Residency` objects are stored in `ObservableList<Residency>`
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)



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

### Guest and Room search feature

#### Implementation

The search mechanism is facilitated by `LogicManager`. It extends `Logic` and its invocation is via the `AddressBookParser`.

* `AddressBookParser#parseCommand()` — Interprets the command the user inputs to invoke the `FindGuestCommand` and `FindRoomCommand`.
* `FindGuestCommand#execute()` — Finds the guest in the hotel with matching name
* `FindRoomCommand#execute()` — Finds the room in the hotel with matching room number

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario:

1. User searches for the data entry desired. In this case, the user's input is "guest Alex"

2. Hit Enter.

3. The Rooms / Guests that have matching names will appear in their respective lists.

The behaviour of the search mechanism is illustrated by the following sequence diagram.

![Interactions Inside the Logic Component for the `guest Alex` Command](images/FindGuestSequenceDiagram.png)

#### Design considerations:

**Aspect: How search guest / room executes:**

* The string name / room number will be passed into a predicate checker to check if any of the data present contains the information as requested.
    * Pros: Consistent implementation - similar to the other commands.
    * Cons: Increased need for good file system and extensive application of Object-Oriented Principles required.


### Listing rooms by vacancy feature

#### Implementation

The list mechanism is facilitated by `LogicManager`. It extends `Logic` and its invocation is via the `AddressBookParser`.
It implements the following key operations.

* `AddressBookParser#parseCommand()` — Interprets the command the user inputs to invoke the `ListCommand`.
* `ListCommand#execute()` — Executes the relevant `ListCommand`.
* `Model#updateFilteredRoomList()` — Filters the list of rooms based on their vacancy status and updates the internal list of rooms to be displayed by the UI.

This operation is exposed in the `Model` interface as `Model#updateFilteredRoomList()`.

The following sequence diagram shows the interactions between objects of the Logic component for the list vacant room mechanism.

![Interactions Inside the Logic Component for the `list rooms vacant` Command](images/ListRoomsByVacancySequenceDiagram.png)

The `list rooms occupied` command works the same way, except a `RoomIsOccupiedPredicate` is passed as argument when calling `Model#updateFilteredRoomList()`.
The rooms of the specified vacancy status will appear in the room list.

#### Design considerations:

**Aspect: How list room occupied / vacant executes:**

* The `ParserUtil` checks that the `ListRoomArg` is valid (either "occupied" or "vacant" and not any other arguments), and the `ListCommandParser` creates a predicate object for `Model#updateFilteredRoomList()` to filter the rooms based on.
    * Pros: Consistency - similar implementation as the command to list all rooms, list all guests and list all records.
    * Cons: This implementation does not fully adhere to OOP principles like inheritance. No new command classes such as `ListVacantRoomCommand` and `ListOccupiedRoomCommand`.

### Uniqueness of Guests

#### Implementation

The mechanism guaranteeing the uniqueness of Guests is facilitated by the `Nric` class, and its invocation is via `AddressBookParser`.
* `AddressBookParser#parseCommand()`  — Interprets the command the user inputs to invoke the `AddCommandParser`.
* `ParserUtil#parseNric()`  — checks whether a `Person` object already exists with the same Nric.

#### Design considerations:

**Aspect: How duplicates are avoided:**

* An `AddCommand` that wants to add a `Person` with an `Nric` that another existing `Person` already will be considered an invalid command.
    * Uniqueness  —  This mechanism will help to prevent the adding of duplicate `Person` objects.

### Encapsulation of Hotel Stays (The Residency System)

#### Implementation

The stay of guests in a room for a period of time is encapsulated in the `Residency` class, and `Residency` objects are created and handled by the `ResidencyBook` class. Creation of `Residency` objects is invoked via `CheckInCommand`.


#### Design considerations:

**Aspect: The Immutability of Person and Room objects**
* Due to the immutability of these objects, it is difficult to have them store references to each other.
  The creation of the `Residency` association class was thus necessary, and also allows additional information about stays to be stored, such as dates and times of check in and check out, among other possible future features.

**Aspect: Storing References to Person and Room objects**
* Due to `Residency` objects needing to store references to `Person` and `Room` objects, an identification system for the latter two classes had to be created to facilitate JSON storage of the actual references.
  Otherwise, the JSON would only store copies of the `Person` and `Room` objects, which would mean that editing a `Person`'s details via the edit command would not affect the `Person` copy in the `Residency`.

**Aspect: Further Expansion to Store Past Records**
* For current hotel stays, each room can only have one set of guests checked in at any given time, and likewise, any guest should only be checked into one room at a time.
  It thus follows that the `ResidencyBook` class should ensure that each Person and Room object can only be referenced in one Residency at any given time.
  <br><br>
  However, the `ResidencyBook` should have the ability to accommodate the Past Records Feature mentioned below, which will involve multiple `Residency` objects referencing the same room.
  Hence, `ResidencyBook` has a boolean parameter in its constructor for allowing duplicates.

### Past Records Feature

This section describes how past residencies are stored such that it can be displayed/searched for contact tracing.

The past residencies are read from the same json data file as the other components in the `AddressBook`, through the `JsonAdaptedResidencyBook` class.

Past residencies can be searched through the use of the `record` command, where any number of keywords can be entered and any record matching all the keywords are displayed to the user.

Given below is an example of the search function for all the past residencies of room 001.

![Sequence Diagram of record command](images/RecordCommandSequenceDiagram.png)


#### Design considerations:

* Possible location of storage of past residencies in a second file.
    * Pros: Keeping past residency storage separate from the main data storage minimises any mix up in the storing of information.
    * Cons: This requires the file to store its own set of persons and rooms and because the residency keeps minimal information in order to minimise
      space required for the storage file, it results in redundancy when storing the same information across 2 files. Changes also have to be written twice.

* AND vs OR for searching records
    * In contrast to the search for guest showing results matching any of keywords given, searching records shows results matching all keywords. This is to allow for more targeted search such as filtering both date and room at the same time to only show records of a particular room at a particular time. This increases the utility of the function in terms of contact tracing.
* Consistency
    * The `ResidencyBook` of past records in `AddressBook` mirrors the storage of guests, rooms and current residencies. A `FilteredList`
      in `ModelManager` to represent the records also helps maintain the consistency and readability of the code.

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

* hotel receptionist
* has a need to manage a significant number of guests and rooms
* needs a solution for contact tracing within their hotel
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage both guests and rooms faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a guest as a contact       | check them into rooms                                                  |
| `* * *`  | user                                       | check guests into rooms        | admit them into our hotel                                              |
| `* * *`  | user                                       | check guests out of rooms      | free up the room and have their information in the archive                                                  |
| `* * *`  | user                                       | search for vacant rooms        | assign guests to a vacant room                                         |
| `* * *`  | user                                       | delete guests                  | remove them if the wrong details are entered                           |
| `* * *`  | user                                       | list all guests and rooms      | check all the statuses                                                 |
| `*`      | user with many guests in the address book  | sort guests  by name           | locate a guest easily                                                  |
| `* *`    | user                                       | search guests by their name    | find a guest's details easily                                          |
| `* * *`  | user who has to track past records of guests | perform queries on past data | check records of past guests and details of their stay                 |
| `* *`    | user                                       | add rooms with specified tags  | keep track of different types of rooms in my hotel                     |


### Use Cases

Refer to [_Use Cases_](UseCases.md).

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. Should work without being connected to the Internet.
4. Should be able to use most basic commands within a day of usage.
5. User should be able to identify key information on the GUI quickly using colour coded text.
6. The system should respond within two seconds of a command input.
7. The product is not required to handle the printing of reports.
8. The rooms show what type of rooms they are, such as First Class or Standard etc.
9. Guests have tags assigned to them to better identify them.
10. The data integrity of the application is preserved in the event where the application closes unexpectedly.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Guests

1. Adding a guest while all guests are being shown

   1. Prerequisites: List all guests using the `list guests` command. Multiple guests in the list.

   2. Test case: `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 id/S98765432G`<br>
      Expected: A new person object is added to the list of guests, with the name John Doe, and the person's details as described by the test case input.

   3. Test case: `add n/Wilburrito`<br>
      Expected: No new guest is added. This is to be expected as the mandatory fields for `add` are not fulfilled. An error message should appear with the correct command format that one should be following.

   4. Other incorrect add commands to try: `add`, `add wilburrito`<br>
      Expected: Similar to previous.

2. Editing a guest's details

    1. List all guests using the `list guests` command. Multiple guests in the list.

    2. Test case: `edit 1 n/Wilburrito`<br>
       Expected: The first guest in the list of guests will have their name edited to `Wilburrito`, with a success message reiterating the edited details of the person.

    3. Test case: `edit 2 n/Wilburger id/S9999999X`<br>
       Expected: The second person in the list of guests will have their name edited to `Wilburger` as well as their id edited to `S9999999X`.

    4. Test case: `edit 0 n/Wilburroni`<br>
       Expected: No guests in the list will be edited, and an error will appear saying `Invalid Command Format!` due to the index being invalid.

    5. Other incorrect edit commands to try: `edit`, `edit x` where `x` is an index that is larger than the largest index of the guest list.<br>
       Expected: No guests in the list will be edited, and an error will appear saying `Invalid Command Format!`.

3. Checking in a guest into a room

    1. Prerequisites:
       1. List all Person objects using the `list guests` command. Multiple guests in the list.
       2. List all the Room objects using the `list rooms` room. Multiple rooms in the list.
 
    2. Test case: `checkin 001 g/1`<br>
       Expected: The first person in the list of guests gets checked into Room 001, with the success message: `Room Checked In: 001`.

    3. Test case: `checkin 002 g/2 g/3`<br>
       Expected: The second and third person in the list of guests gets checked into Room 002, with the success message: `Room Checked In: 002`.

    4. Test case: `checkin 000 g/4 g/5`<br>
       Expected: The fourth and fifth person in the list of guests **does not** get checked into Room 000, because Room 000 does not exist, as it is an invalid room number.

    5. Other incorrect checkin commands to try: `checkin`, `checkin 1000 g/1`, `checkin g/1`<br>
       Expected: Similar to previous.

4. Checking out a guests from a room

    1. Prerequisites:
       1. List all Person objects using the `list guests` command. Multiple guests in the list.
       2. List all the Room objects using the `list rooms` room. Multiple rooms in the list.
       3. Make sure that at least 1 guest is checked into any Room 001.

    2. Test case: `checkout 001`<br>
       Expected: The guest in Room 001 will be checked out with the message `Room Checked Out: 001` being shown. The room's occupancy status should change from `Occupied` to `Vacant`.

    3. Test case: `checkout 1000`<br>
       Expected: An error saying `The room index provided is invalid. Index should be the one that is displayed in the Room panels below`.

    4. Other incorrect commands to try: `checkout`, `checkout x`, where `x` is an index greater than the largest index in the current Room list.<br>
       Expected: For `checkout`, an error `Invalid command format!` will be shown. For `checkout x`, There will be the same error as described in 6iii.

5. Searching for guests

    1. List all guests using the `list guests` command. Multiple guests in the list. Make sure that there is a guest named `Wilburrito` and a guest named `Bernice` by either editing an existing guest or adding a new one, and also that there is no guest named `zzzzzzzz`. Make sure to do this before testing each of the test cases below.

    2. Test case: `guest wilburrito`<br>
       Expected: The list will show any matches to the name `wilburrito`. If you followed step 4i, this will return at least 1 guest in the guest list.

    3. Test case: `guest wilburrito bernice`<br>
       Expected: The list will show any matches to the name `wilburrito` and `bernice`. If you followed step 4i, this will return at least 2 guests in the guest list.

    4. Test case: `guest zzzzzzzz`<br>
       Expected: The list will show any matches to the name `zzzzzzzz`. If you followed step 4i, this will return 0 guests in the guest list.

    5. Incorrect commands to try: `guest`.

### Rooms

1. Adding rooms

    1. List all rooms using the `list rooms` command. Multiple rooms in the list, not exceeding 900 rooms. 

    2. Test case: `addroom 1 t/luxury`<br>
       Expected: A room will be added to the end of the Room list, and it will appear with the tag `luxury`.

    3. Test case: `addroom 3 t/special`<br>
       Expected: 3 rooms wll be added to the end of the Room list, and they will appear with the tag `special`.

    4. Test case: `addroom 1000 t/shouldnotwork`<br>
       Expected: No rooms will be added, and an error will be shown, saying `Adding 1000 more room(s) would exceed the maximum 999 rooms allowed`.

    5. Other incorrect commands to try: `addroom`, `addroom 1`, `addroom x` where `x` will cause the number of rooms to exceed 1000.
       Expected: `addroom`, `addroom 1` will cause an error to be shown, saying `Invalid command format`. `addroom x` will cause the same error to be shown as described by 5iv.

2. Searching for rooms by room number

    1. List all rooms using the `list rooms` command. Multiple rooms in the list (at least 2 but not more than 900). Make sure to use this command each time before trying a new test case.

    2. Test case: `room 001`<br>
       Expected: The room list should now only show `001`.

    3. Test case: `room 001 002`<br>
       Expected: The room list should now only show `001` and `002`.

    4. Test case: `room 901`<br>
       Expected: The room list should show no rooms, as there were only 900 rooms in the initial room list.

    5. Test case: `room 1000` <br>
       Expected: An error `Invalid command format!` will be shown, and the specified room will not appear as it is not possible for it to exist.

    6. Other invalid commands to try: `room`, `room 000`.
       Expected: An error `Invalid command format!` will be shown. Depending on the command input, a brief description of why the command is invalid may be provided.

3. Listing all rooms

    1. Test case: `list rooms`<br>
       Expected: All rooms are displayed in the Rooms panel.

4. Listing all occupied rooms

    1. Test case: `list rooms occupied`<br>
       Expected: All occupied rooms are displayed in the Rooms panel.

5. Listing all vacant rooms

    1. Test case: `list rooms vacant`<br>
       Expected: All vacant rooms are displayed in the Rooms panel.

### Records

1. Listing all records

    1. Test case: `list records`<br>
    Expected: All past records are displayed in the History panel, sorted from most recent record at the top.


2. Searching for records

    1. List all records using the `list records` command. Multiple records in the list (at least 2). Make sure to use this command each time before trying a new test case. 

    2. Test case: `record Alex`<br>      
       Expected: All records with the keyword `Alex` are displayed in the History panel.  

    3. Test case: `record 001`<br>
       Expected: All records with the keyword `001` are displayed in the History panel

    4. Test case: `record 2021-11-01`<br>
       Expected: All records with the date 2021-11-01(both checkin and checkout) are displayed in the History panel.

    5. Test case: `record Alex 001`<br>
       Expected: ALl records with both keywords `Alex` and `001` are displayed in the History panel.

    6. Invalid command to try: `record`.
       Expected: An error `Invalid command format!` will be shown.

### Saving data

1. Dealing with corrupted data files

    1. Locate the data saved for Trace2Gather in the JSON file `[JAR file location]/data/trace2gather.json`.

    2. Open the file and remove some braces to invalidate the data format of the json file.

    3. Re-run the application.

    4. Expected: Trace2gather runs, showing a GUI with no data. Upon a command that writes to the data file such as adding a room or guest, the old invalid data file is flushed out and replaced by the new one.

2. Dealing with missing data file

    1. Remove the data file saved for Trace2Gather in the directory `[JAR file location]/data`.

    2. Re-run the application.

    3. Expected: Trace2gather creates a sample data file and runs, showing the GUI with the sample data.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

#### Summary
In this project, we experienced challenges when implementing our backend, frontend, editing documentation, and fixing bugs.
<br><br>
In the backend, we had to build on the existing implementation and introduced our own data structures to prevent cyclic-dependencies.
<br><br>
On the frontend, we had to match the specifications as much as possible whilst also ensuring that our new features not only worked well but also stylistically was coherent to our product.
<br><br>
In the documentation, we had to edit many of the diagrams and their accompanying explanations to account for the changes in our application as compared to the original AB3.
<br><br>
#### Backend
The naive implementation would have been for Room objects to contain a set of guests (Persons), and once a room is checked out, all the room's information is moved into a list containing all historical records.
The Person objects would also contain the Room that they are checked into.
However, Person objects are immutable in AB3, and Room objects were made immutable to match. This would make it difficult for both objects to store references to each other, especially since Person objects can be edited frequently.
<br><br>
Our solution was to create an association class, the Residency class, that stores pointers to both the Room and guests. This way, when a guest is edited, we use the guest's information to retrieve the
Residency object that is keeping track of all the rooms that has this same guest inside and all the historical records that have this same guest inside and update the guest to reflect the edited guest's information.
<br><br>
#### Frontend
While we reused many of the original AB3's code extensively, our project was harder due to having 3 different lists as compared to the AB3 which originally only had 1 list.
This was compounded by the fact that we had dependencies between these 3 entities, and we had to make sure that the updates in the backend are reflected in the frontend.
<br><br>
We also made an effort to ensure that stylistically, the new additions were different from the original AB3 but still helped to fit into the overall style of the project.
<br><br>
#### Documentation
As our implementation included new data structures and new commands to interact with those data structures, we had to modify the documentation extensively to reflect these new changes.
This included creating new diagrams and adding new explanations for our features.
<br><br>
Furthermore, our application also removed some commands from the original AB3, most notably the delete command.
Since this command was used as an example along with its accompanying sequence diagram, we had to modify that entire section and replace the sequence diagram too.
