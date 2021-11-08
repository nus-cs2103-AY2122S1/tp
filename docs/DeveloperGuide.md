---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* [AB3](https://github.com/se-edu/addressbook-level3) source code for the initial starting code
* [opencsv](http://opencsv.sourceforge.net/) was used to read and write csv files

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

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/ui/Ui.java)

<img src="images/UiClassDiagram.png" width="700"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/logic/Logic.java)

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
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/model/Model.java)

<img src="images/ModelClassDiagram.png" width="1100" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object) and all `Event` objects (which are contained in a `UniqueEventList` object).
* stores the currently 'selected' `Person` or `Event` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` or `ObservableList<Event>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-T15-4/tp/blob/master/src/main/java/safeforhall/storage/Storage.java)

![StorageClassDiagram](images/StorageClassDiagram.png)

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `safeforhall.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Command

This command allows the user to add residents or events to the SafeFor(H)All application depending on the currently active tab.

The workflow of the Add command is shown in the Activity diagram illustrated below.

![AddActivityDiagram](images/logic/commands/addcommand/AddActivityDiagram.png)

#### Design considerations:

**Aspect: Optional `LastDate` fields when adding residents**

* **Alternative 1 (current choice):** `lastFetDate` and `lastCollectionDate` are optional fields
  * Pros:
    - Provides more flexibility for users when adding residents to the application, as users have the choice to include or exclude these fields.
    - Saves time as all fields can be added in a single command
  * Cons: The length of command is increased as there are potentially more fields for the user to type.

* **Alternative 2:** Add `lastFetDate` and `lastCollectionDate` by editing the Person object
  * Pros: Makes add command more user-friendly as the command is more succinct
  * Cons: User has to go through a two-step process of `add` and `edit` to initialise a residents information

### Delete Command

This command allows the user to delete residents or events to the SafeFor(H)All application depending on the currently active tab.

The workflow of the Delete command is shown in the Activity diagram illustrated below.

![AddActivityDiagram](images/logic/commands/deletecommand/DeleteActivityDiagram.png)

#### Design considerations:

**Aspect: Delete the correct resident/event:**

* **Alternative 1 (current choice):** `Index` field
    * Pros: No need to type out the full name of the resident/event, and risk typos. `Index` is also unique, which prevents the user
      from deleting the wrong resident/event.
    * Cons: The user needs to scroll through the GUI to find the index of the resident/event to be deleted.

* **Alternative 2:** `Name` and `eventName` fields for Resident and Event respectively.
    * Pros: The user does not need to scroll through the GUI to find the index of the resident/event to be deleted.
    * Cons: There is a higher risk of erroneous user input, as a `Name`/`eventName` field will inevitably be longer than
      an index.


### Edit Command

This command allows the user to edit residents or events to the SafeFor(H)All application depending on the currently active tab.

The workflow of `EditPersonCommand` is shown in the Activity diagram illustrated below.

![EditActivityDiagram](images/logic/commands/editcommand/EditActivityDiagram.png)
`EditEventCommand` follows a similar flow of actions.

Note:
- Mass operations for residents can be carried out by inputting multiple indexes after the command `edit`, each separated by a whitespace.
- `Residents` field in `Event` is not editable by `edit` command

#### Design considerations:

**Aspect: Edit parameters:**

* **Alternative 1 (current choice):** Excluding `Residents` parameter for Events.
    * Pros:
        - Simpler implementation as there are less `EditDescriptors` to maintain.
        - `include` and `exclude` commands exist to enhance the updating of the `Residents` field.
          `edit` currently replaces the specified fields with the user input, which will not be user-friendly for the `Residents` field.
          It might also cause confusion of possible overlapping functionality on the user's side.
    * Cons: Increases the number of commands the user has to remember.

* **Alternative 2:** `edit` is used to edit the residents involved in an event
    * Pros: More instinctive, and less commands for the user to remember.
    * Cons: Users will have to rewrite the entire list of residents involved in the event whenever they want to modify the list of residents involved.


### View Command

This command allows the user to view the additional details of a specific resident or event in the sidebar, depending on the currently active tab.

How it works:
1. When the app is started, the `Ui` component calls on `Logic` to get the `Model` to be displayed in the sidebar. `Model` is first set to an empty list.
2. When a `ViewCommand` with a valid index is executed, the `Model` is updated to contain only the specified resident or event.
3. When the `ViewCommand` is executed without index parameters, the main panel will show all residents or events, and the sidebar will be cleared.
4. If the command is run in the `ResidentTab`, the details of the resident with the corresponding index being displayed in the sidebar. Vice versa for `EventTab`.

The following sequence diagram demonstrates what happens when the `ViewPersonCommand` is executed:

![ViewCommandSequenceDiagram](images/logic/commands/viewcommand/ViewPersonCommandSequenceDiagram.png)
`ViewEventCommand` follows a similar sequence of interactions.

#### Design considerations:

**Aspect: How to reference residents/ events in the CLI:**

* **Alternative 1 (current choice):** Reference by `Index`.
    * Pros:
      - Easy to reference and no need to type out the whole `residentName`/ `eventName`.
      - `Index` is unique.
    * Cons: Need to first determine the `Index` of the resident/ event in the UI. `View` could become a two-step process if the database is large.

* **Alternative 2:** Reference by `residentName`/ `eventName`.
    * Pros: Do not have to first determine the `Index` of the resident/ event.
    * Cons:
      - Hard to type when the `residentName`/ `eventName` is long.
      - `eventName` is not unique, which might cause issues.


### Find Person Command

This command allows searching for residents subjected to 1 or more filters for the different available parameters.

The workflow of the Find Person command can be illustrated with an activity diagram as follows:

<img src="images/logic/commands/findcommand/FindActivityDiagram.png" width="900" />

How the parsing works:
1. When `Logic` is called upon to execute the command, it uses the `AddressBookParser` class to parse the user command.
1. If the command was run in the `ResidentTab` it results in a `FindCommandParser` object created and it's `parse` method called with the user input.
1. The parsing attempts to create a `FindCommand` object. For each existing prefix, it sets the relevant field of a new `FindCompositePredicate` object.
1. Parsing of any of the provided values can throw a `ParseException` if invalid. If at least one field is set, a `FindCommand` object is returned. If all are unspecified, an exception is thrown.
1. The command is executed and the result encapsulated as a `CommandResult` object which is returned back from `Logic`.

Note:
 - Name can take in multiple keywords separated by whitespace
 - `lastFetDate` and `lastCollectionDate` are not included
 - Room filtering is extended to allow block, level and block-level filtering as well


The following sequence diagram demonstrates what happens when the `FindCommand` is executed:

<img src="images/logic/commands/findcommand/FindCommandSequenceDiagram.png" width="900" />

The command extends the `Command` class and implements `FindCommand#execute()` to execute the command.

The crucial logic underlying is encapsulated in the `FindCompositePredicate` class. This class holds the filtering variables and constructs the required predicate for filtering. The `test` method creates and combines the predicates as shown:

```java
@Override
public boolean test(Person person) {
    List<Predicate<Person>> allPredicates = Arrays.asList(
        p -> getName().orElse(x -> true).test(p),
        p -> getRoom().orElse(x -> true).test(p),
        p -> getPhone().orElse(x -> true).test(p.getPhone()),
        p -> getEmail().orElse(x -> true).test(p.getEmail()),
        p -> getVaccStatus().orElse(x -> true).test(p.getVaccStatus()),
        p -> getFaculty().orElse(x -> true).test(p.getFaculty()));

    return allPredicates
            .stream()
            .reduce(p -> true, Predicate::and)
            .test(person);
}
```

Most variables are checked against using their respective `equals` methods except for `Name` and `Room` for which separate predicates implementing `Predicate<Person>` have been created. This is done to support:
1. Multiple keywords matching for name and 
2. Room matching by block, level and block-level.

#### Design considerations:

**Aspect: Filtering parameters:**

* **Alternative 1 (current choice):** Excluding `lastFetDate` and `lastCollectionDate` parameters.
    * Pros:
        - Simpler implementation as there are less filtering predicates to maintain.
        - `list` command exists to enhance the usage of these 2 fields to extract information. A simple equality check on date is less likely from the user's POV and `list` handles this. Thus excluding this, prevents confusion of possible overlapping functionality on the user's side.
    * Cons:
        - The user is unable to search for an exact fet/collection date alongside other filters.


### Include Command

This command adds multiple residents to an event by referencing the `Event` by its `Index` and the `Person` to 
add by their `Name` or `Room` through the `AddressBook#findPerson()` method.

The following activity diagram illustrates how the `AddressBook#findPerson()` method works:

![FindPersonActivityDiagram](images/logic/commands/includecommand/FindPersonActivityDiagram.png)

The command extends the `Command` class and implements `IncludeCommand#execute()` to execute the command. 
A `ResidentList` which contains a list of `Person` to add to an `Event`, is a field added to an `Event`.

When `IncludeCommand#createEditedEvent()` is called, two methods of `Event` are invoked:
* `Event#getCombinedDisplayString()` creates a display String with just the names of each `Person` in the combination 
  of current `Person` in the Event` and all the `Person` in `toAdd` with no duplicate.
* `Event#getCombinedStorageString()` creates a storage String with the full information of each `Person` in the 
  combination of current `Person` in the Event` and all the `Person` in `toAdd` with no duplicate. 

The following sequence diagram demonstrates what happens when the `IncludeCommand` is executed:

![IncludeCommandSequenceDiagram](images/logic/commands/includecommand/IncludeCommandSequenceDiagram.png)

The following activity diagram summarizes what happens when the `IncludeCommand` is executed:

![IncludeCommandActivityDiagram](images/logic/commands/includecommand/IncludeCommandActivityDiagram.png)

#### Design considerations:

**Aspect: How to reference event in the CLI:**

* **Alternative 1 (current choice):** Reference by `Index`.
    * Pros: Easy to reference and no need to type out the whole `eventName`, `Index` is also unique.
    * Cons: Need to find the `Index` of the `Event` in the UI to know what `Index` the `Event` has if the number of `Event` is large.

* **Alternative 2:** Reference by `eventName`.
  itself.
    * Pros: Do not need to have the `Index` in UI to know what `Event` it is, can just reference it by its name.
    * Cons: Hard to type when the `eventName` is long, `eventName` not being unique will also cause issues.

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

* administrator of on-campus halls and residences
* has a need to manage a large amount of resident information
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Manage residents' information faster than a typical mouse/GUI driven app and allow easy enforcement of Covid-19 restrictions


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

[EPIC] Basic CRUD Functionality

| Priority | As a …​                                 | I want to …​                                                                                       | So that I can…​                                                                                                                    |
| -------- | ------------------------------------------ | ----------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `* * *`  | admin in a hall/ residence                 | add a new resident                                                                                    | keep track of the residents' data                                                                                                     |
| `* * *`  | admin in a hall/ residence                 | add a new event                                                                                       | keep track of current and upcoming events happening in the hall/ residence                                                            |
| `* * *`  | admin in a hall/ residence                 | add residents to an event                                                                             | keep track of the residents attending a specific hall event                                                                           |
| `* * *`  | admin in a hall/ residence                 | delete a resident                                                                                     | remove the data of a resident who has moved out                                                                                       |
| `* * *`  | admin in a hall/ residence                 | delete many residents in a single command                                                             | save a lot of time when deleting multiple residents                                                                                   |
| `* * *`  | admin in a hall/ residence                 | delete an event                                                                                       | remove an event that has been cancelled                                                                                               |
| `* * *`  | admin in a hall/ residence                 | delete many events in a single command                                                                | save a lot of time when deleting multiple events                                                                                      |
| `* * *`  | admin in a hall/ residence                 | delete residents from an event                                                                        | remove residents who are no longer attending a specific hall event                                                                    |
| `* * *`  | admin in a hall/ residence                 | update a resident’s details                                                                           | update and reflect any changes in the residents’ details                                                                              |
| `* * *`  | admin in a hall/ residence                 | update the particulars of many residents in a single command                                          | save a lot of time when editing the details of multiple residents                                                                     |
| `* * *`  | admin in a hall/ residence                 | update an event's details                                                                             | update an event’s details if there are any changes                                                                                    |
| `* * *`  | admin in a hall/ residence                 | add and update a resident's last FET date                                                             | keep track of the residents’ last FET dates and update the dates whenever they take a new FET                                         |


[EPIC] Information Retrieval

| Priority | As a …​                                 | I want to …​                                                                                       | So that I can…​                                                                                                                    |
| -------- | ------------------------------------------ | ----------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `* * *`  | admin in a hall/ residence                 | view all residents                                                                                    | see all the current residents                                                                                                         |
| `* * *`  | admin in a hall/ residence                 | view all events                                                                                       | see all the current events                                                                                                            |
| `* * *`  | admin in a hall/ residence                 | view a list of residents who were present at an event                                                 | identify who is at risk if someone in the group catches COVID                                                                         |
| `* * *`  | admin in a hall/ residence                 | search for the residents by their name, room, email, phone number                                     | find a resident based on the information given                                                                                        |
| `* * *`  | admin in a hall/ residence                 | filter the residents by faculty                                                                       | easily disseminate faculty-specific information to the residents                                                                      |
| `* * *`  | admin in a hall/ residence                 | filter the residents by block and level                                                               | easily contact a group of students in order to disseminate group-specific information                                                 |
| `* * *`  | admin in a hall/ residence                 | filter the residents by their vaccination status                                                      | use the information to disseminate information or guidelines that may be different for vaccinated and unvaccinated individuals        |
| `* * *`  | admin in a hall/ residence                 | immediately see residents who have missed their FET deadlines                                         | disseminate a reminder to these residents to take a new FET test                                                                      |
| `* * *`  | admin in a hall/ residence                 | immediately see which events contain unvaccinated people                                              | ensure that COVID restrictions are adhered to, and that everyone attending the event is vaccinated, by removing the unvaccinated residents from the event list   |
| `* * *`  | admin in a hall/ residence                 | retrieve all residents whose FETs are due within a given date                                         | ensure residents do not miss their FET deadlines by reminding them to do their FETs                                                   |
| `* * *`  | admin in a hall/ residence                 | retrieve a resident's test kit collection deadlines                                                   | ensure residents do not miss their test kit collections by reminding them to collect their kits on time                               |
| `* * *`  | admin in a hall/ residence                 | retrieve the date and venue of the events                                                             | identify who was in contact with the infected person on the day of the event                                                          |
| `* * *`  | admin in a hall/ residence                 | retrieve the maximum capacity of an event venue and the number of residents attending the event       | ensure that the number of residents attending the event will not exceed the capacity of the event venue                               |
| `* * *`  | admin in a hall/ residence                 | easily carry out contact tracing                                                                      | quarantine can be done quickly in the case where one person in the group catches COVID                                                |
| `*`      | admin in a hall/ residence                 | check which CCA booked a certain facility                                                             | find out which CCA is responsible in case trouble arises                                                                              |
| `*`      | admin in a hall/ residence                 | check a resident’s prior events                                                                       | find out which group has come into contact with the infected person                                                                   |


[EPIC] Miscellaneous

| Priority | As a …​                                 | I want to …​                                                                                       | So that I can…​                                                                                                                    |
| -------- | ------------------------------------------ | ----------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `* * *`  | admin in a hall/ residence                 | view the help guide whenever I need to                                                                | refresh my memory on how to use the app                                                                                               |
| `* *`    | admin in a hall/ residence                 | view a summary of the app functions                                                                   | have an overview of what the app does                                                                                                 |
| `* *`    | admin in a hall/ residence                 | import user data from a CSV file                                                                      | input multiple residents' information into the system at once without having to add each resident's information line-by-line          |
| `* *`    | admin in a hall/ residence                 | export the emails of the residents whose FET/collection are due soon into a file                      | disseminate information to the residents more easily                                                                                  |
| `*`      | admin in a hall/ residence                 | view all details of a specific resident or event only when I need to                                  | easily find the specific information I am looking for, without having the large amount of information stored cluttering the app       |
| `*`      | admin in a hall/ residence                 | see a auto-suggestion of the command format once I type it                                            | quickly refer to the correct format of the command                                                                                    |

### Use cases

(For all use cases below, the **System** is the `SafeFor(H)All app` and the **Actor** is the `Hall admin`, unless specified otherwise)

**Use case: UC01 - Delete a resident**

**MSS**

1. Actor requests to list residents.
2. System shows a list of residents.
3. Actor requests to delete a specific resident in the list.
4. System deletes the resident.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.


* 3a. The given index is invalid.
    * 3a1. System shows an error message.
  
      Use case resumes at step 2.


**Use case: UC02 - View details of an event**

**MSS**

1. Actor navigates to the `Event` tab of the application.
2. Actor requests to view an event from the list of events.
3. System shows the relevant information of the event and list of residents involved in the event.

   Use case ends.

**Extensions**

* 2a. The given index is invalid.
    * 2a1. System shows an error message.
  
      Use case resumes at step 2.


* 3a. The list is empty.

    Use case ends.


**Use case: UC03 - Include a resident to an event**

**MSS**

1. Hall admin navigates to the `Event` Tab.
2. Hall admin views the event to include residents in.
3. Hall admin enters the residents to be included in the selected event.
4. SafeFor(H)all reflects the added residents in the side window.

    Use case ends.

**Extensions**

* 3a. SafeFor(H)all detects invalid residents entered.
    * 3a1. SafeFor(H)all displays an error message.
    * 3a2. SafeFor(H)all requests for valid residents.
    * 3a3. Hall admin enters new data.

      Steps 3a1-3a2 are repeated until the residents entered are valid.
      Use case resumes from step 4.

**Use case: UC04 - Exclude a resident from an event**

**MSS**

1. Hall admin navigates to the `Event` Tab.
2. Hall admin views the event to exclude residents from.
3. Hall admin enters the residents to be excluded in the selected event.
4. SafeFor(H)all reflects the remaining residents in the side window after removing the given residents.

    Use case ends.

**Extensions**

* 3a. SafeFor(H)all detects invalid residents entered.
    * 3a1. SafeFor(H)all displays an error message.
    * 3a2. SafeFor(H)all requests for valid residents.
    * 3a3. Hall admin enters new data.

      Steps 3a1-3a3 are repeated until the residents entered are valid.
  
      Use case resumes from step 4.

**Use case: UC05 - List residents who missed their FET**

**MSS**

1. Hall admin navigates to the `Resident` Tab.
2. Hall admin filters residents whose FET were due before today.
3. SafeFor(H)all displays the filtered residents.

    Use case ends.

**Extensions**

* 2a. SafeFor(H)all detects invalid date entered.
    * 2a1. SafeFor(H)all displays an error message.
    * 2a2. SafeFor(H)all requests for a valid date.
    * 2a3. Hall admin enters a new date.

      Steps 2a1-2a2 are repeated until the date entered are valid.
  
      Use case resumes from step 3.

**Use case: UC06 - List residents whose FET or Test Kit collection dates are due soon**

**MSS**

1. Hall admin navigates to the `Resident` Tab.
2. Hall admin filters residents whose FET or Test Kit Collection will be due between two given dates.
3. SafeFor(H)all displays the filtered residents.

    Use case ends.

**Extensions**

* 2a. SafeFor(H)all detects invalid dates entered.
    * 2a1. SafeFor(H)all displays an error message.
    * 2a2. SafeFor(H)all requests for valid dates.
    * 2a3. Hall admin enters new dates.

      Steps 2a1-2a2 are repeated until the dates entered are valid.

      Use case resumes from step 3.

**Use case: UC07 - Update a resident's last FET date**

**MSS**

1.  Actor requests to list residents
2.  System shows a list of residents
3.  Actor requests to update a specific resident's last FET date
4.  System updates the specified resident's last FET date
5.  Actor requests to view all details of the specified resident to confirm that the details have been updated correctly
6.  System shows all details of the specified resident

* 2a. The list is empty.

  Use case ends.


* 3a. SafeFor(H)all detects invalid index entered.
    * 3a1. SafeFor(H)all displays an error message.
    * 3a2. SafeFor(H)all requests for a valid index.
    * 3a3. Hall admin enters a new index.

      Steps 3a1-3a2 are repeated until the index entered is valid.

      Use case resumes from step 4.
    

* 3b. SafeFor(H)all detects invalid date entered.
    * 3b1. SafeFor(H)all displays an error message.
    * 3b2. SafeFor(H)all requests for a valid date.
    * 3b3. Hall admin enters a new date.
      
      Steps 3b1-3b2 are repeated until the date entered is valid.

      Use case resumes from step 4.
    

* 5a. SafeFor(H)all detects invalid index entered.
    * 5a1. SafeFor(H)all displays an error message.
    * 5a2. SafeFor(H)all requests for a valid index.
    * 5a3. Hall admin enters a new index.

      Steps 5a1-5a2 are repeated until the index entered is valid.

      Use case resumes from step 6.

**Use case: UC08 - Export current list of residents' email as csv**

**MSS**

1. Actor requests for a list of the email addresses of the residents shown.
2. Actor enters filename of file to be created.
3. System outputs the list of email addresses in the form of a csv file.

   Use case ends.

**Extensions**

* 2a. System detects duplicate file.
    * 2a1. System displays an error message.
      Use case resumes at step 2.

**Use case: UC09 - Remind residents to take FET**

**MSS**

1. Actor <u>lists residents who missed their FET (UC05)</u>
2. Actor <u>exports current list of residents' email as csv (UC08)</u>
3. Actor sends an email to these residents to remind them to take their FET soon.

   Use case ends.

**Extensions**

* 1a. The list is empty.

    Use case ends.

**Use case: UC10 - Remind residents to collect FET kits**

**MSS**

1. Actor <u>lists residents whose FET or Test Kit collection dates are due soon (UC06)</u>
2. Actor <u>exports current list of residents' email as csv (UC08)</u>
3. Actor sends an email to these residents to remind them to collect their FET kits soon.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

**Use case: UC11 - Find close contacts of a resident**

**MSS**

1. Actor views the list of residents and locates the resident to trace.
2. Actor filters the resident list by that resident's name or room and specifies a number of days to trace back to.
3. System shows a filtered resident list.

   Use case ends.

**Extensions**

* 2a. System cannot find the resident in question.
    * 2a1. System displays an error message.
    * 2a2. System requests for a valid name or room.
    * 2a3. Actor retries with different information.

      Use case resumes from step 2.

* 2b. System detects an invalid duration that is negative or more than a month.
    * 2b1. System displays an error message.
    * 2b2. System requests for a valid duration.
    * 2b3. Actor retries with different input.

      Use case resumes from step 2.

**Use case: UC12 - Inform close contacts of a resident**

**MSS**

1. Actor <u>filters for residents who are close contacts of a given resident (UC11)</u>
2. Actor <u>exports current list of residents' email as csv (UC08)</u>
3. Actor sends an email to these residents to remind them to self-isolate.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

**Use case: UC13 - Find all residents on the same floor as a positive case**

**MSS**

1. Actor views all residents.
2. Actor locates the positive resident and finds their room number.
3. Actor filters the resident list based on the block and floor extracted from the room number.
4. System shows a filtered resident list.

   Use case ends.

**Extensions**

* 3a. System detects and invalid block character or out of range floor number.
    * 3a1. System displays an error message.
    * 3a2. System requests for a valid block floor combination.
    * 3a3. Actor retries with different information.

      Use case resumes from step 3.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be targeted towards a single user and not multi-users.
5.  Data stored locally should be in a human editable text file.
6.  Do not make use of a DBMS to manage data.
7.  Final software should be platform-independent.
8.  The software shouldn't depend on our own remote server.
9.  Application is to be packaged into a single JAR file.
10. JAR file size to be limited to 100MB and documents to 15MB/file.
11. UG and DG are to be pdf-friendly.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **API**: An Application Programming Interface is a connection between computers or between computer programs. It is a type of software interface, offering a service to other pieces of software.
* **GUI**: A Graphical User Interface (GUI) is a form of user interface through which users interact with electronic devices via visual indicator representations.
* **CLI**: A Command Line Interface (CLI) processes commands to a computer program in the form of lines of text.
* **FET**: Fast and Easy Test for COVID-19, which is self-administered using Antigen Rapid Test (ART) kits.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample residents. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

<br>
<div markdown="span" class="alert alert-info">:information_source: **Note:** 
For all Resident commands, ensure that you are on the Residents tab before continuing.
</div>

### Adding a resident

1. Adding a resident and their information into the app

   1. Test case: `add n/Tommy r/A123 p/87654321 e/tom@gmail.com v/t f/SOC fd/10-10-2020 cd/20-10-2020`<br>
      Expected: A resident named `Tom` with the relevant information is added into the app, shown in the GUI. Success message is shown.

2. Adding a duplicate resident with the same name or same room

   1. Prerequisites: A resident with the same name `Tommy` or room `A123` is already in the app.

   2. Test case: `add n/Tommy r/A101 p/87654321 e/bern@gmail.com v/t f/SOC fd/10-10-2020 cd/20-10-2020`<br>
      Expected: Error message shown, `This resident or room already exists in the address book`
   
   3. Test case: `add n/Tom r/A123 p/87654321 e/tom@gmail.com v/t f/SOC fd/10-10-2020 cd/20-10-2020`<br>
      Expected: Error message shown, `This resident or room already exists in the address book`
   
3. Adding a resident with invalid parameters

   1. Test case: `add n/Tom! r/A201 p/87654321 e/tom@gmail.com v/t f/SOC fd/10-10-2020 cd/20-10-2020`<br>
      Expected: Error message shown, `Names should only contain alphabetic characters and spaces, and it should not be blank`

   2. Test case: `add n/Tom r/A201 p/87654321 e/tom@gmail.com v/true f/SOC fd/10-10-2020 cd/20-10-2020`<br>
      Expected: Error message shown, `Vaccination status can be T or F (case insensitive).`

### Viewing a resident

1. View a list of all the residents in the app, or the information on a specific resident

    1. Prerequisites: NIL

    2. Test case: `view`<br>
       Expected: A list of all the residents is displayed in the app's GUI

    3. Test case: `view 3`<br>
       Expected: The details of the resident at index 3 (meaning the 3rd resident in the list when `view` without the 
       index parameter is called) of the address book are displayed in the GUI.

    4. Other incorrect delete commands to try: `view 0`, `view x` (where x is larger than the list size)<br>
       Expected: Error message shown

### Listing residents by FET/Collection Deadlines
1. Listing residents' deadline with normal keyword and valid dates

    1. Prerequisites: There are residents whose FET deadline lies between the 2 gates given
   
    2. Test case: `deadline k/f d1/10-10-2021 d2/15-10-2021`<br>
       Expected: Residents whose FET deadline lies between these 2 dates are listed.
    

2. Listing residents' deadline with late keyword and valid dates

    1. Prerequisites: There are residents whose FET deadline is due before the given date
   
    2. Test case: `deadline k/lf d1/10-10-2021`<br>
       Expected: Residents whose FET is due before the given date is listed.


3. Listing residents' deadline with invalid parameters

    1. Test case: `deadline k/f d1/10-10-2021`<br>
       Expected: The result box will indicate that the given command format is invalid.

    2. Test case: `deadline k/f d1/12-10-2021 d2/10-10-2021`<br>
       Expected: The result box will indicate that the second date is earlier than the first.

### Finding residents
1. Finding residents' who are not vaccinated

    1. Prerequisites: A non-empty resident list

    2. Test case: `find v/f`<br>
       Expected: Residents who are not vaccinated (no syringe icon) are listed.


2. Finding residents' who are from `SoC` faculty and are vaccinated

    1. Prerequisites: A non-empty resident list

    2. Test case: `find f/soc v/t`<br>
       Expected: Residents who are vaccinated (syringe icon) and from `SoC` are listed.


3. Finding residents' who have the word `alex` in their name and are from  block `A`

    1. Prerequisites: A non-empty resident list
    
    2. Test case: `find n/alex r/a`<br>
       Expected: Residents who fit the criteria are listed.

4. Finding residents' who are in an invalid block `P`

    2. Test case: `find n/alex r/p`<br>
       Expected: Error message specifying that an invalid room is entered.

### Editing residents

1. Edit multiple residents' details while all residents are being shown
   
    1. Prerequisites: View all residents using the `view` command. Multiple residents in the list. 
       Edited resident does not already exist in the address book.

    1. Test case: `edit 1 2 v/T`<br>
       Expected: First and second residents' vaccination statuses are updated to vaccinated. First and second residents' names shown in the status message.

    1. Test case: `edit 0`<br>
       Expected: No resident is edited. Error details shown in the status message.

    1. Other incorrect delete commands to try: `edit`, `edit x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.
       
### Deleting residents

1. Deleting multiple resident while all residents are being shown

   1. Prerequisites: View all resident using the `view` command. Multiple residents in the list.

   1. Test case: `delete 1 2`<br>
      Expected: First and second residents are deleted from the list. Names of the deleted residents shown in the status message.

   1. Test case: `delete 0`<br>
      Expected: No resident is deleted. Error details shown in the status message. 

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Tracing residents
1. Tracing a resident who has been in contact with 2 others in one event over the past week

    1. Prerequisites: A resident in room `A213` who shares an event with 2 others in the past week

    2. Test case: `trace r/a213`<br>
       Expected: 2 close contact residents are listed.

2. Tracing a resident who has been in contact with 2 others in one event over the past week, of which one has been in contact with another in the past 9 days.

    1. Prerequisites: A resident in room `A213` who shares an event with 2 others of which one shares another event with one other in the past 9 days.

    2. Test case: `trace r/a213 d/2 t/9`<br>
       Expected: 3 close contact residents are listed.

3. Tracing a resident for a 0 depth

    2. Test case: `trace r/a213 d/0`<br>
       Expected: Error message specifying that an invalid depth is entered.

4. Tracing a resident for over 31 days

    2. Test case: `trace r/a213 t/58`<br>
       Expected: Error message specifying that an invalid duration is entered.

### Sorting residents

1. Sorting the list of residents by valid fields and order

   1. Test case: `sort by/n o/a`<br>
      Expected: List of residents are sorted by their names in the alphabetical order.


2. Sorting the list of residents by invalid fields or order

   1. Test case: `sort by/z o/a`<br>
      Expected: Error message shown, `FIELD should be one of the following: n, e, r, p, f, v, fd, cd`

   2. Test case: `sort by/n o/z`<br>
      Expected: Error message shown, `ORDER should be one of the following: a, d`

### Importing resident data

1. Import 5 residents from a correctly formatted csv

     1. Prerequisites: A correctly formatted csv with 6 rows named `safeforhall`

     1. Test case: `import safeforhall`<br>
       Expected: 5 residents displayed with correct information and all resident lists cleared from events.

2. Non existent filename provided

    1. Prerequisites: csv file `nonexistent.csv` should not be present in the `data/` folder
    2. Test case: `import nonexistent`<br>
       Expected: Error message shown specifying the csv file has not been found.

2. Missing fields for a specific resident row

    1. Prerequisites: csv file `safeforhall.csv` has one resident row with missing vaccination status

    2. Test case: `import safeforhall`<br>
       Expected: Error message shown specifying the row at which the error occurred.

### Exporting residents' emails

1. Export email addresses of list of residents

   1. Test case: `export testEmailExport`<br>
      Expected: Csv file filled with column of email addresses of the residents displayed in the app.
   

2. Duplicate filename provided

   1. Prerequisites: csv file `testDuplicateExport.csv` is already in existing `/data/exports` directory
   2. Test case: `export testDuplicateExport`<br>
      Expected: Error message shown, `This filename already exists`

<br>
<div markdown="span" class="alert alert-info">:information_source: **Note:** 
For all Event commands, ensure that you are on the Events tab before continuing.
</div>

### Adding an event

1. Adding an event and its information into the app

    1. Test case: `add n/Swim v/Pool d/10-10-2021 t/1900 c/10` <br>
       Expected: An event named `swim` with the relevant information is added into the app, shown in the GUI. Success message is shown.

2. Adding a duplicate event

    1. Prerequisites: An event with the same name, venue, date and time exists.
    2. Test case: `add n/Swim v/Pool d/10-10-2021 t/1900 c/10` <br>
       Expected: Error message shown specifying that such an event already exists.

3. Adding an event with invalid parameters

    1. Test case: `add n/Swim v/Pool d/10-10-2021 t/1900 c/0` <br>
       Expected: Error message shown specifying that capacity needs to be a positive integer.

    1. Test case: `add n/Swim v/Pool d/10-10-2021 t/2500 c/5` <br>
       Expected: Error message shown specifying that time provided is invalid.

    1. Test case: `add n/Swim v/Pool d/30-02-2021 t/1900 c/0` <br>
       Expected: Error message shown specifying that date provided is invalid.

### Viewing an event

1. View a list of all the events in the app, or the information of a specific event

    1. Test case: `view`<br>
       Expected: A list of all the events is displayed in the app's GUI

    2. Test case: `view 3`<br>
       Expected: Additional details of the event currently at index 3 will be displayed in the GUI.
       
2. Invalid indexes provided
   1. Test case: `view x` (where x is larger than the list size)<br>
      Expected: Error message shown, `The event index provided is invalid`

### Finding an event

1. Shows a list of events that match the provided keywords for different available parameters.

    1. Prerequisites: NIL

    1. Test case: `find c/5`<br>
       Expected: A list of all the events with capacity 5 is displayed in the app's GUI

    1. Test case: `find n/Football Training`<br>
       Expected: A list of all the events which contain the words "Football" and "Training" is displayed in the app's GUI

    1. Other incorrect delete commands to try: `find`, `find d/03-01` (invalid date input)<br>
       Expected: Error message shown

### Editing an event

1. Edit an event's details while all events are being shown

    1. Prerequisites: View all events using the `view` command. Multiple events in the list.
       Edited event does not already exist in the address book.

    1. Test case: `edit 1 c/5`<br>
       Expected: First event capacity is updated to 5. Updated event details shown in the status message.

    1. Test case: `edit 0`<br>
       Expected: No event is edited. Error details shown in the status message.

    1. Other incorrect delete commands to try: `edit`, `edit x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Deleting an event

1. Deleting an event while all persons are being shown

    1. Prerequisites: List all events using the `view` command (without any parameters). Multiple events in the list.

    2. Test case: `delete 3`<br>
       Expected: The third event is deleted from the list. Details of the deleted event shown in the status message.

    3. Test case: `delete 0`<br>
       Expected: No event is deleted. Error details shown.

    4. Other incorrect delete commands to try: `delete -1`, `delete x` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Sorting events

1. Sorting the list of events by valid fields and order

    1. Test case: `sort by/n o/a`<br>
       Expected: List of events are sorted by their names in the alphabetical order.

2. Sorting the list of events by invalid fields or order

    1. Test case: `sort by/z o/a`<br>
       Expected: Error message shown, `FIELD should be one of the following: n, d, c, v`

    2. Test case: `sort by/n o/z`<br>
       Expected: Error message shown, `ORDER should be one of the following: a, d`
    

### Adding residents to an Event
1. Add a single valid resident by name to a valid Event
    1. Prerequisites: There is resident with name "Alex Yeoh" and room "A101", and an event with index 1. 
    2. Test case: `include 1 r/Alex Yeoh`<br>
       Expected: The given resident will be added to the event. Sidebar will reflect that the resident is in the event.

2. Add a single valid resident by room to a valid Event
    1. Prerequisites: There is resident with name "Alex Yeoh" and room "A101", and an event with index 1.
    2. Test case: `include 1 r/A101`<br>
       Expected: The given resident will be added to the event. Sidebar will reflect that the resident is in the event.


3. Add multiple valid residents by names to a valid Event
    1. Prerequisites: There are two residents with names "Alex Yeoh" and "Bernice Yu", with rooms "A101" and "A102" 
       respectively, and an event with index 1.
    2. Test case: `include 1 r/Alex Yeoh, Bernice Yu`<br>
       Expected: The given residents will be added to the event. Sidebar will reflect that the residents are in the
       event.


4. Add multiple valid residents by rooms to a valid Event
    1. Prerequisites: There are two residents with names "Alex Yeoh" and "Bernice Yu", with rooms "A101" and "A102"
       respectively, and an event with index 1.
    2. Test case: `include 1 r/A101, A102`<br>
       Expected: The given residents will be added to the event. Sidebar will reflect that the residents are in the
       event.


5. Add a valid resident to an valid Event but without comma separating the rooms
    1. Prerequisites: There are two residents with names "Alex Yeoh" and "Bernice Yu", with rooms "A101" and "A102"
       respectively, and an event with index 1.
    2. Test case: `include 1 r/A101 A102`<br>
       Expected: The given residents are not added to the event. The result box will indicate that names/rooms have to
       be separated by a comma.


5. Add a valid resident to an invalid Event
    1. Prerequisites: There is resident with name "Alex Yeoh" and room "A101", and there is no event with index 1.
    2. Test case: `include 1 r/Alex Yeoh`<br>
       Expected: The given resident is not added to the event. The result box will show that the given index is invalid.


6. Add an invalid resident to a valid Event
    1. Prerequisites: There is no resident with name "Alex Yeoh" or room "A101", but there is an event with index 1.
    2. Test case: `include 1 r/A101`<br>
       Expected: The given resident is not added to the event. The result box will show that no residents with the
       given information could be found.


### Removing residents from an Event
1. Remove a single valid resident by name from a valid Event
    1. Prerequisites: There is resident with name "Alex Yeoh" and room "A101", and an event with index 1.
    2. Test case: `exclude 1 r/Alex Yeoh`<br>
       Expected: The given resident will be removed from the event. Sidebar will reflect that the resident is no longer
       in the event.


2. Remove a single valid resident by room from a valid Event
    1. Prerequisites: There is resident with name "Alex Yeoh" and room "A101", and an event with index 1.
    2. Test case: `exclude 1 r/A101`<br>
       Expected: The given resident will be removed from the event. Sidebar will reflect that the resident is no longer
       in the event.


3. Remove multiple valid residents by names from a valid Event
    1. Prerequisites: There are two residents with names "Alex Yeoh" and "Bernice Yu", with rooms "A101" and "A102"
       respectively, and an event with index 1.
    2. Test case: `exclude 1 r/Alex Yeoh, Bernice Yu`<br>
       Expected: The given residents will be removed from the event. Sidebar will reflect that the residents are no
       longer in the event.


4. Remove multiple valid residents by rooms from a valid Event
    1. Prerequisites: There are two residents with names "Alex Yeoh" and "Bernice Yu", with rooms "A101" and "A102"
       respectively, and an event with index 1.
    2. Test case: `exclude 1 r/A101, A102`<br>
       Expected: The given residents will be removed from the event. Sidebar will reflect that the residents are no
       longer in the event.


5. Remove a valid resident from an valid Event but without comma separating the rooms
    1. Prerequisites: There are two residents with names "Alex Yeoh" and "Bernice Yu", with rooms "A101" and "A102"
       respectively, and an event with index 1.
    2. Test case: `exclude 1 r/A101 A102`<br>
       Expected: The given residents are not removed from the event. The result box will indicate that names/rooms have
       to be separated by a comma.


5. Remove a valid resident from an invalid Event
    1. Prerequisites: There is resident with name "Alex Yeoh" and room "A101", and there is no event with index 1.
    2. Test case: `exclude 1 r/Alex Yeoh`<br>
       Expected: The given resident is not removed from the event. The result box will show that the given index is
       invalid.


6. Remove an invalid resident from a valid Event
    1. Prerequisites: There is no resident with name "Alex Yeoh" or room "A101", but there is an event with index 1.
    2. Test case: `exclude 1 r/A101`<br>
       Expected: The given resident is not removed from the event. The result box will show that no residents with the
       given information could be found.

### Switch between tabs

1. Switch between the event and resident tabs

    1. Prerequisites: NIL

    1. Test case: `switch`<br> when the user is at the Event tab
       Expected: The GUI switches from displaying the Event tab to the Resident tab


### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
