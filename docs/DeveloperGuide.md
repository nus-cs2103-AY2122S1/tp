---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

**Acknowledgements**

Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)

**Credit**

This project is based on the _AddressBook-Level3_ project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletep 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays objects like `Person`, `CCA`, and `Reminder` residing in their respective `Model`s.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `PersonAddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deletep 1")` API call.

![Interactions Inside the Logic Component for the `deletep 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `PersonDeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450"/>
<img src="images/CcaClassDiagram.png" height="150"/>
<img src="images/PersonClassDiagram.png" height="150"/>
<img src="images/ReminderClassDiagram.png" height="150"/>

The `Model` component,

* stores the address book data i.e., all `Person`, `Cca`, and `Reminder` objects (which are contained in a `UniquePersonList`, `UniqueCcaList`, `UniqueReminderList` object respectively).
* stores the currently 'selected' `Person`, `Cca`, and `Reminder` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>`, `ObservableList<Cca>`, and `ObservableList<Reminder>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103-T14-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="700" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

The Sequence Diagram below illustrates the interactions within the Storage component for the `saveAddressBook()` API call.

<img src="images/StorageSequenceDiagram.png" width="700" />

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Persons

A Person has:

* A name, represented by the `PersonName` class. Name must be unique.
* A phone, represented by the `Phone` class.
* An email, represented by the `Email` class.
* An address, represented by the `Address` class.
* A set of zero or more tags, with the tags represented by the `Tag` class.

Two Persons are considered identical if they have the same name.
A stronger version of equality is also defined: under this version, two Persons are considered equal if they have the same name, same phone, same email, same address, and same tags.

#### Command for Adding Persons

The `addp` command is implemented by `PersonAddCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

#### Command for Deleting Persons

The `deletep` command is implemented by `PersonDeleteCommand`.
Similar to `PersonAddCommand`, `PersonDeleteCommand` extends `Command` to practice polymorphism.

The `PersonDeleteCommand` class has an `Index` which is the index of the Person to delete, specified by the user.
It implements the `execute` method which handles the logic of the delete command.
The `getFilteredPersonList` method is called to obtain a List of Persons, `lastShownList`.
If the given index exists in `lastShownList`, the corresponding Person is deleted using the `deletePerson` method defined in the `ModelManager`.

#### Command for Editing Persons

The `editp` command is implemented by `PersonEditCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

The `PersonEditCommand` class has an `Index` which is the index of the Person to edit, specified by the user.
It implements the `execute` method which handles the logic of the edit command.
The user needs to specify at least one of the fields.
For fields that are not specified, they default to the original values of the unedited person.
The `updateFilteredPersonList`, `updateFilteredCcaList`, and `updateFilteredReminderList` methods are called to update the `UI` component.

#### Command for Finding Persons

The `findp` command is implemented by `PersonFindCommand` which extends `Command`.

The keywords specified by the user are passed as a List to the `NameContainsKeywordsPredicate` constructor.
The `NameContainsKeywordsPredicate` class extends `Predicate<Person>` and implements the `test` method,
which uses streams to filter Persons whose name matches any of the keywords.

### CCAs

A CCA has:

* A CCA name, represented by the `CcaName` class. CCA name must be unique.
* Zero or more people enrolled in it, represented by the HashSet `members`.
* Zero or more reminders associated with it, represented by the HashSet `reminders`.
* A Set of zero or more tags, with the tags being represented by the `Tag` class.

Two CCAs are considered identical if they have the same name.

#### Command for Adding CCAs

The `addc` command is implemented by `CcaAddCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

#### Command for Deleting CCAs

The `deletec` command is implemented by `CcaDeleteCommand`.
Similar to `CcaAddCommand`, `CcaDeleteCommand` extends `Command` to practice polymorphism.

The `CcaDeleteCommand` class has an `Index` which is the index of the CCA to delete, specified by the user.
It implements the `execute` method which handles the logic of the delete command.
The `getFilteredCcaList` method is called to obtain a List of CCAs, `lastShownList`.
If the given index exists in `lastShownList`, the corresponding CCA is deleted using the `deleteCca` method defined in the `ModelManager`.

#### Command for Editing CCAs

The `editc` command is implemented by `CcaEditCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

The `CcaEditCommand` class has an `Index` which is the index of the Cca to edit, specified by the user.
It implements the `execute` method which handles the logic of the edit command.
The user needs to specify at least one of the fields.
For fields that are not specified, they default to the original values of the unedited CCA.
The `updateFilteredPersonList`, `updateFilteredCcaList`, and `updateFilteredReminderList` methods are called to update the `UI` component.

#### Command for Finding CCAs

The `findc` command is implemented by `CcaFindCommand` which extends `Command`.

The keywords specified by the user are passed as a List to the `CcaNameContainsKeywordsPredicate` constructor.
The `CcaNameContainsKeywordsPredicate` class extends `Predicate<Cca>` and implements the `test` method,
which uses streams to filter CCAs whose name matches any of the keywords.

#### Command for Enrolling Person into CCAs

The `enrol` command is implemented by `CcaEnrolCommand` which extends `Command`.

The `CcaEnrolCommand` class has two `Index`es, the index of the CCA to be enrolled into, and the index of the Person to enrol, specified by the user.
It implements the `execute` method which handles the logic of the enrol command.
The `getFilteredCcaList` and `getFilteredPersonList` method is called to obtain a List of CCAs and Persons, `lastShownCcaList` and `lastShownPersonList` respectively.
If the gives `Indexes` exist in `lastShownCcaList` and `lastShownPersonList`, the corresponding Person is enrolled into the corresponding CCA using the `enrolPersonIntoCca` method defined in the `ModelManager`.

#### Command for Expelling Person from CCAs

The `expel` command is implemented by `CcaExpelCommand` which extends `Command`.

The `CcaExpelCommand` class has two `Index`es, the index of the CCA to be expelled from, and the index of the Person to expel, specified by the user.
It implements the `execute` method which handles the logic of the expel command.
The `getFilteredCcaList` and `getFilteredPersonList` method is called to obtain a List of CCAs and Persons, `lastShownCcaList` and `lastShownPersonList` respectively.
If the gives `Indexes` exist in `lastShownCcaList` and `lastShownPersonList`, the corresponding Person is expelled from the corresponding CCA using the `expelPersonFromCca` method defined in the `ModelManager`.

### Reminders

A Reminder has:

* A reminder name, represented by the `ReminderName` class.
* A start date, represented by the `ReminderStartDate` class.
* Zero or one frequency, represented by the `ReminderFrequency` class and making use of enumerations from the `Frequency` class.
* Zero or one occurrence, represented by the `ReminderOccurrence` class.
* Exactly one CCA that it is linked to, represented as a `CcaName` data field as a String.
* An ArrayList of `Date`s which represents all upcoming dates of the reminder.

Two Reminders are considered identical if they have the same name and same start date.
A stronger version of equality is also defined: under this version, two Reminders are considered equal if they have the same name, same CCA, same start date, same frequency, and same occurrence.

#### Command for Adding Reminders

The `addr` command is implemented by `ReminderAddCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

If the user does not specify a frequency and occurrence, the frequency defaults to a One-off frequency and the occurrence defaults to 1 (since we take it that the Reminder only occurs once).
If the user specifies only one of frequency and occurrence, then an error is thrown.

The `ReminderAddCommand` class has an `Index` which is the index of the CCA to add the Reminder to, specified by the user.
It implements the `execute` method which handles the logic of the add command.
The `updateFilteredCcaList` and `updateFilteredReminderList` methods are called to update the `UI` component.

#### Command for Deleting Reminders

The `deleter` command is implemented by `ReminderDeleteCommand`.
Similar to `ReminderAddCommand`, `ReminderDeleteCommand` extends `Command` to practice polymorphism.

The `ReminderDeleteCommand` class has an `Index` which is the index of the Reminder to delete, specified by the user.
It implements the `execute` method which handles the logic of the delete command.
The `getFilteredReminderList` method is called to obtain a List of Reminders, `lastShownList`.
If the given index exists in `lastShownList`, the corresponding Reminder is deleted using the `deleteReminder` method defined in the `ModelManager`.

#### Command for Editing Reminders

The `editr` command is implemented by `ReminderEditCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

If the user does not specify any of the fields, the fields default to the original values of the unedited reminder.

The `ReminderEditCommand` class has an `Index` which is the index of the Reminder to be edited, specified by the user.
It implements the `execute` method which handles the logic of the edit command.
The `updateFilteredPersonList`, `updateFilteredCcaList`, and `updateFilteredReminderList` methods are called to update the `UI` component.

#### Command for Finding Reminders

The `findr` command is implemented by `ReminderFindCommand` which extends `Command`.
Similar to other commands, `ReminderFindCommand` extends `Command` to practice polymorphism.

The keywords specified by the user are passed as a List to the `ReminderNameContainsKeywordsPredicate` constructor.
The `ReminderNameContainsKeywordsPredicate` class extends `Predicate<Reminder>` and implements the `test` method,
which uses streams to filter Reminders whose name matches any of the keywords.

#### Command for Snoozing Reminders

The `snoozer` command is implemented by `ReminderSnoozeCommand`, which extends `Command`.
Polymorphism allows the different Command objects to be passed around and executed without having to know what type of Command it is.

The `ReminderSnoozeCommand` has an `Index` which is the index of the Reminder to snooze, specified by the user.
If the `Index` is not valid, an error message is shown.
It implements the `execute` method which handles the logic of the snooze command.
It edits the Reminder by getting its next date and updating the `UI` components, using the same process as the `ReminderEditCommand`.
If the Reminder is on its last occurrence, this action performs the same action as the `ReminderDeleteCommand` (as the Reminder has no more occurrences after being snoozed).

### Tags

A Tag has:

* A tag name, represented by a `String`

Two Tags are considered identical if they have the same name.

### Storage

The current Storage mechanism is split into two main sections: `AddressBook` storage, for all ePoch-related data, and `UserPrefs` storage, for all user preference-related data.
Whenever ePoch needs to save or update its storage, it converts the relevant object into a json object with the `saveJsonFile` method in `jsonUtil`.

There are three main classes in ePoch that need to be saved: `Person`, `Cca`, and `Reminder`. Each of these classes is converted to its corresponding `JsonAdapted` class,
to be made suitable for json conversion. Because each `Cca` object contains a Set of `Person`s and `Reminder`s as members, `JsonAdaptedPerson` and `JsonAdaptedReminder` will be stored within `JsonAdaptedCca`.
Also, because each `Person` and `Cca` object contains a Set of `Tag`s, `JsonAdaptedTag` will be stored within `JsonAdaptedPerson` and `JsonAdaptedCca` as well.

**Alternatives considered**: instead of storing whole `Person` and `Reminder` objects in `Cca` objects, the alternative of storing unique identifiers for them as `Pid`, `Rid` etc was considered. In the end, this possibility was rejected
because of how time-consuming refactoring the entire project to use this new system would be.

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

* has a need to manage a significant number of contacts.
* prefers desktop apps over other types.
* can type fast.
* prefers typing to mouse interactions.
* is reasonably comfortable using CLI apps.
* has many contacts that are spread across different CCAs.
* needs to keep track of many recurring reminders associated with each CCA.

**Value proposition**:

ePoch helps NUS students keep track of their many contacts and events across various CCAs.
The product allows students to link persons with CCAs and set reminders that repeat over a given time interval, to conveniently organise their commitments and overlapping social circles.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                                     | I want to …                    | So that I can…                                                         |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *` | new user | see usage instructions of ePoch | refer to instructions if I forget how to use ePoch |
| `* * *` | user who dislikes using the mouse or trackpad | interact with an app through a command line interface | rely exclusively on keyboard inputs |
| `* * *` | user who has multiple friends, CCAs, and reminders | be able to view all within a clean and user-friendly UI | view them quickly and efficiently |
| `* *` | user with multiple devices across different operating systems | use ePoch across multiple platforms | access its functionality regardless of the device I am using |
| `* * *` | user who meets many fellow students | be able to add contacts in ePoch | keep track of the people I meet |
| `* * *` | busy user | be able to delete contacts from ePoch | stop keeping track of contacts that are no longer relevant |
| `* * *` | user with multiple CCAs | be able to add all the CCAs I am involved in | keep track of them |
| `* * *` | user who changes CCAs over time | be able to delete CCAs | stop keeping track of those CCAs that I am no longer a part of |
| `* * *` | user | add a person to a CCA | keep track of what CCAs my friends are in |
| `* * *` | user whose friends are joining many CCAs | be able to efficiently add people to CCAs | keep track of my friends from different CCAs |
| `* * *` | user whose friends are experimenting with CCAs | be able to efficiently remove people from CCAs | update ePoch with the latest information |
| `* * *` | user with many CCA commitments | be able to add reminders associated with CCAs | stay on top of things by organising my CCA-specific commitments |
| `* * *` | user with commitments constantly in flux | delete reminders | delete irrelevant or redundant reminders |
| `* *` | user who loves to experiment | be able to quickly delete all data from ePoch at once | try out different configurations of persons, CCAs and reminders |
| `* *` | forgetful user | find any contact that matches my search terms | easily find any contact(s) that I desire |
| `* *` | user who meets many fellow students | be able to edit the details of my contacts in ePoch | update ePoch with the latest information, especially since a contact's details may change regularly |
| `* *` | user with friends in multiple CCAs | be able to quickly view what CCAs my friends are in | keep track of which CCAs to join based on what CCAs my friends are in |
| `* *` | careless user | be able to edit the details of CCAs | fix any mistakes made when adding CCAs |
| `* *` | user who is overcommitted with many CCAs | be able to find any CCA with a title that matches a given search term | easily filter through my many CCAs |
| `* *` | user who has many friends from different CCAs | be able to search for a CCA and view all the people who are part of that CCA | remember who is part of that CCA |
| `* *` | user participating in different types of CCAs | add tags to categorise my CCA into categories like 'Music' and 'Sports' | differentiate and organise my different types of CCAs |
| `* *` | visual user | be able to be visually prompted of overdue reminders | visually differentiate what I need to urgently work on |
| `* *` | user who is overcommitted with many upcoming events and commitments | be able to view all the reminders associated with a CCA | see what upcoming events related to that CCA I have |
| `* *` | user who might make typos | edit reminder names | rectify any errors in the names I set for reminders |
| `* *` | busy user with many recurring meetings | set reminders to repeat at a specific interval | be constantly reminded of my commitments without having to remember to add a new reminder each time |
| `* *` | busy user with many recurring meetings | set reminders to repeat for a given number of occurrences | efficiently set reminders without wasting time on repeated actions |
| `* *` | user who is overcommitted with many commitments | search for reminders with names matching given keywords | find more important reminders by their title |
| `* *` | user with many recurring reminders | snooze reminders that I have completed | clear space for reminders that I have not yet completed |
| `*` | user who manages multiple CCAs | view the number of people in each CCA | easily keep track of CCA enrolment
| `*` | organised user | be able to export my contacts to a JSON file | send an organised list of contacts to others |

### Use cases

(For all use cases below, **system** refers to ePoch, unless specified otherwise)

**Use case: UC1 - List all persons, CCAs, and reminders**

**MSS**

1. User requests to list all persons, CCAs, and reminders.
1. The system displays all persons, CCAs, and reminders currently stored in ePoch. <br>
Use case ends.

**Use case: UC2 - Add a person**

**MSS**

1. User requests to add a person to ePoch by specifying their name, address, email and phone number, optionally specifying additional user tags.
1. The system adds the person with the specified information to ePoch. <br>
Use case ends.

Extension:

- 1a. At least one attribute from name, address, email and phone number is missing or specified improperly.
  - 1a1. The system throws an error. Use case resumes from step 1.

**Use case: UC3 - Add a CCA**

**MSS**

1. User requests to add a CCA to ePoch by specifying the CCA name, optionally specifying additional CCA tags.
1. The system adds the CCA to ePoch. <br>
Use case ends.

Extension:
- 1a. The CCA name is not specified correctly, or is missing.
  - 1a1. The system throws an error. Use case resumes from step 1.

**Use case: UC4 - Add a reminder**

**MSS**

1. User requests to add a reminder to ePoch by specifying the id of the CCA to which it is linked, its name, and its start date, optionally specifying its frequency and occurrences.
1. The system adds the reminder to ePoch. <br>
   Use case ends.

Extension:
- 1a. At least one attribute from CCA id, name, and start date is missing or specified improperly.
    - 1a1. The system throws an error. Use case resumes from step 1.
- 1b. Frequency is specified but occurrence is not specified.
    - 1b1. The system throws an error. Use case resumes from step 1.
- 1c. Occurrence is specified but frequency is not specified.
    - 1c1. The system throws an error. Use case resumes from step 1.

**Use case: UC5 - Find a person**

**MSS**

1. User requests to find a person based on the specified attributes.
1. The system displays all persons that fit the user's specifications, as well as related CCAs and reminders. <br>
   Use case ends.

**Extensions**

- 1a. No attributes are specified, or attributes are specified improperly.
  - 1a1. The system throws an error. Use case resumes from step 1.


**Use case: UC6 - Delete a person**

**MSS**

1. User requests to list persons.
1. The system shows a list of persons (UC1).
1. User requests to delete a person by specifying that person's ID.
1. The system deletes the person. <br>
Use case ends.

**Extensions**

- 2a. The list is empty. <br>
  Use case ends.
- 3a. The specified person does not exist (person ID invalid).
  - 3a1. The system throws an error. Use case resumes from step 2.

**Use case: UC7 - Enrol a person into a CCA**

**MSS**

1. User requests to list persons and CCAs.
1. The system shows a list of persons and CCAs (UC1).
1. User requests to enrol a person into a CCA by specifying the IDs of the desired person and CCA.
1. The system adds the person to the CCA.
Use case ends.

**Extensions**

- 2a. The list of persons is empty. <br>
    * 2a1. The system throws an error message. Use case ends.
- 2b. The list of CCAs is empty. <br>
    * 2b1. The system throws an error message. Use case ends.
- 3a. The specified person does not exist (person ID invalid).
    * 3a1. The system throws an error message. Use case resumes from step 2.
- 3b. The specified CCA does not exist (CCA ID invalid).
    * 3b1. The system throws an error message. Use case resumes from step 2.

**Use case: UC8 - Clear all data**

**MSS**

1. User requests to clear all data.
1. The system clears all data (persons, CCAs, reminders) stored in ePoch. <br>
   Use case ends.

### Non-Functional Requirements

1.  The system should work on any _mainstream OS_ as long as it has Java 11 or above installed.
1.  The system should be able to hold up to 1000 objects (persons, CCAs, reminders) without a noticeable sluggishness in performance for typical usage.
1.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1.  The JAR file should not exceed 100MB in size.
1.  The system should respond within 2 seconds whenever a command is entered by the user.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Person**: A contact that the user wants to save.
* **CCA**: A school-based group that a person may be a member of.
* **Reminder**: A scheduled event associated with a CCA that the user wishes to be notified of.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch

   1. Download the ePoch JAR file and copy it into an empty folder where you wish to test it.

   2. Double-click the JAR file to run it. <br>
      Expected: this should show the GUI with a set of sample contacts. The window size may not be optimally set for your monitor size.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the JAR file.<br>
       Expected: The most recent window size and location should be remembered.

### Adding a person

1. Adding a person

   1. Prerequisites: None.

   2. Test case: `addp n/Ellen Chua a/Tembusu e/ellenchua@u.nus.edu p/98225832` <br>
      Expected: a person named `Ellen Chua`, with address `Tembusu`, email `ellenchua@u.nus.edu`, and phone number `98225832` is added.

   3. Test case: `addp` <br>
      Expected: Error thrown, indicating invalid command format.

   4. Test case: `addp n/Kevin Norton`, i.e. some fields are missing. <br>
      Expected: Similar to previous.

   5. Other incorrect add commands to try: `add`, `addperson` etc. <br>
      Expected: Similar to previous.

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. There must be at least one person in the list to delete.

   1. Test case: `deletep 1`<br>
      Expected: The first contact in the list is deleted. Details of the deleted contact are shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `deletep 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x` (where x is larger than the size of the number of people listed) <br>
      Expected: Similar to previous.

### Adding and deleting CCAs

1. Adding and deleting CCAs can be testing similarly to testing adding and deleting persons, as explained above. <br>
   Instead of `addp` and `deletep`, the commands `addc` and `deletec` are used. <br>
   When adding a CCA, the only attribute that needs to be specified is its name.

### Adding a reminder

1. Adding a reminder to a CCA
   1. Prerequisites: List all CCAs using the `list` command. The CCA that the reminder is to be added to is listed.

   2. Test case: `addr n/Weekly band practice cid/1 sd/2021-09-20` <br>
       Expected: a reminder named `Weekly band practice` which begins on `2021-09-20` is added to the CCA at index 1 (i.e. the first CCA in the list).

   3. Test case: `addr` <br>
      Expected: Error thrown, indicating invalid command format.

   4. Test case: `addr n/Weekly band practice`, i.e. some fields are missing. <br>
      Expected: Similar to previous.

   5. Other incorrect add commands to try: `add`, `addreminder cid/0`, `addreminder cid/x` where `x` is greater than the number of CCAs listed etc. <br>
      Expected: Similar to previous.

### Deleting a reminder

1. Deleting a reminder while all reminders are being shown

    1. Prerequisites: List all reminders using the `list` command. There must be at least one reminder in the list to delete.

    2. Test case: `deleter 1`<br>
       Expected: The first reminder in the list is deleted. Details of the deleted contact are shown in the status message.

    3. Test case: `deletep 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `deleter x` (where x is larger than the number of reminders listed) <br>
       Expected: Similar to previous.

### Enrolling a person into a CCA

1. Enrolling a person to a CCA
    1. Prerequisites: List all persons and CCAs using the `list` command. The person to be enrolled, and the CCA that they are to be enrolled into must be listed.

    2. Test case: `enrol pid/1 cid/1` <br>
       Expected: the first person in the list of persons is enrolled into the first CCA in the list of CCAs. The number of people in the specified CCA increases by 1.

    3. Test case: `enrol` <br>
       Expected: Error thrown, indicating invalid command format.

    4. Test case: `enrol cid/1`, i.e. some fields are missing. <br>
       Expected: Similar to previous.

    5. Other incorrect add commands to try: `enrol`, `enrol cid/0`, `addreminder cid/x` where `x` is greater than the number of CCAs listed etc. <br>
       Expected: Similar to previous.

### Expelling a person from a CCA

1. Expelling a person from a CCA
    1. Prerequisites: List all persons and CCAs using the `list` command. The person to be expelled, and the CCA that they are to be expelled from must be listed. The person to be expelled must already be enrolled inside this CCA.

    2. Test case: `expel pid/1 cid/1` <br>
       Expected: the first person in the list of persons is expelled from the first CCA in the list of CCAs. The number of people in each CCA decreases by 1.

    3. Test case: `expel` <br>
       Expected: Error thrown, indicating invalid command format.

    4. Test case: `expel cid/1`, i.e. some fields are missing. <br>
       Expected: Similar to previous.

    6. Other incorrect add commands to try: `expel`, `expel pid/0`, `expel pid/x` where `x` is greater than the number of persons listed etc. <br>
       Expected: Similar to previous.

   5. Test case: `expel pid/1 cid/1` when the person specified is not already enrolled into the CCA specified.
      Expected: Error thrown, indicating invalid command.

### Saving and reading data

1. Dealing with missing/corrupted data files
   1. Note: All contact data in ePoch (persons, CCAs, reminders) is stored in `data/addressbook.json`. 

   2. Simulate missing file
      1. Prerequisites: Delete `addressbook.json`, if it exists, from the `/data` folder in the directory where the JAR file for ePoch is stored.

      2. Test case: Double-click the JAR file to run it. <br>
         Expected: this should show the GUI with a set of sample contacts. There should be no `addressbook.json` file in the `/data` at this exact moment.

      3. Test case: Run the `clear` command in ePoch. <br>
         Expected: this should clear all sample contacts from ePoch, and the GUI should contain no data at all. An `addressbook.json` file should be created in the `/data` folder.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

ePoch was a highly challenging project that required a significant amount of effort from our team.
This could be attributed mainly to our support of multiple entities, along with allowing users to create complex relations between them.

While AB3 deals with only one entity type (Persons), ePoch deals with three (Persons, CCAs and Reminders).
As such, the development of ePoch was especially challenging as we had to manage not only these three entities, but also the links between them (Persons/CCAs, CCAs/Reminders).
This was an obstacle in many ways.

Firstly, there was a significant amount of development work needed in creating the three entities, integrating them into the current model, and designing how they would link to each other.
It was difficult to decide how to allow these different objects to reference each other.
Due to these associations, a significant amount of care and effort was needed to implement each feature as it could directly affect another feature and cause bugs.
Testing was also difficult for each feature as we had to test not only the feature itself, but also how it may affect features related to other entities.
For example, we had to significantly modify the find commands to filter one list by keywords, and the other two lists by their associations.

Secondly, commands that deleted or edited an entity needed extra effort to implement, as we had to ensure that all associated entities were updated real-time.
For instance, the `editc` command to edit CCAs must also update the CCA name of associated reminders, if the CCA name is changed.
Not only must these reminders be updated, the changes needed to be reflected in the UI.

Thirdly, storing the links between these entities was challenging.
When an entity was updated, it needed to be updated accordingly in the storage, so that the user would not lose these changes.
Implementing a feature often required a large change to the storage format, as we would need to preserve the relationships between different entities.

Finally, due to the large amount of code written to support numerous entities and the relationships between them,
we needed to write a lot more tests to achieve the same percentage of code coverage.
We also had to take greater care in writing unit tests and designing stubs, since our entities would normally have references to other entities.

With numerous challenges faced, our team managed to overcome the technical difficulty of ePoch's implementation and design.
We believe a significant achievement of our relatively bug-free support of operations for three distinct entities,
as well as commands for linking entities and commands that display the links between them
(e.g., `findc` will display filtered CCAs along with the people and reminders associated with them).
