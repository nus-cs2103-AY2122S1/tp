---
layout: page
title: Developer Guide
---

SalesNote is a **desktop app for tailors, optimized for use via a Command Line Interface** (CLI), suitable for fast
typists. SalesNote aims to help tailors simplify their administrative tasks, with the main features split between helping to manage:
* Client information (e.g. contact details, measurements, notes)
* Tasks to be done
* Sales orders and accounts

The purpose of the Developer Guide is to guide you through our application's architecture, so you are familiar with the underlying structure.

## Table of Contents

- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
- [Design](#design)
    - [Architecture](#architecture)
    - [UI component](#ui-component)
    - [Logic component](#logic-component)
    - [Model component](#model-component)
    - [Storage component](#storage-component)
    - [Common classes](#common-classes)
- [Implementation](#implementation)
    - [Task and order package](#task-and-order-package)
    - [Adding order feature](#adding-order-feature)
    - [Updating person changes in order list and task list](#updating-person-changes-in-order-list-and-task-list)
    - [Sorting orders feature](#sorting-orders-feature)
    - [Displaying clients' total orders feature](#displaying-clients-total-orders-feature)
- [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
- [Appendix: Requirements](#appendix-requirements)
    - [Product scope](#product-scope)
    - [User stories](#user-stories)
    - [Use cases](#use-cases)
    - [Non-functional requirements](#non-functional-requirements)
    - [Glossary](#glossary)
- [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
    - [Launch and shutdown](#launch-and-shutdown)
    - [Deleting a client](#deleting-a-client)
    - [Saving data](#saving-data)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Acknowledgements**

* This project is based on the AB3 project created by the [SE-EDU initiative](https://se-education.org).
* Application logo - Copyright by [yupiramos](https://www.canva.com/media/MADeEQ5DO1Y)
* Adapted code - [`formatTotalColumn`](https://stackoverflow.com/a/34924734/13896417) and [`setCloseOnEsc`](https://stackoverflow.com/a/42104595/13896417)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="300" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-W08-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletetask 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="600" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

<img src="images/UiClassDiagram.png" width="800"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W08-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person`, `Task` and `Order` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="600"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `SalesNoteParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a client).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deletetask 1")` API call.

![Interactions Inside the Logic Component for the `deletetask 1` Command](images/DeleteTaskSequenceDiagram.png)
    
<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteTaskCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `SalesNoteParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `SalesNoteParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/src/main/java/seedu/address/model/Model.java)

Here are class diagrams for the important components in the `Model` package. This first image shows the relations between the important classes in the package:

<img src="images/ModelClassDiagram.png" width="800" />

Class Diagram of the `Person`'s entity:

<img src="images/PersonClassDiagram.png" />

Class Diagram of the `Task`'s entity:

<img src="images/TaskClassDiagram.png" />

Class Diagram of the `Order`'s entity:

<img src="images/OrderClassDiagram.png" />

The `Model` component,

* stores these three types of data in SalesNote
  * Address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
  * Task book data i.e., all `Task` objects (which are contained in a `UniqueTaskList` object).
  * Order book data i.e., all `Order` objects (which are contained in a `UniqueOrderList` object).
* stores the currently 'selected' `Person`, `Task`, and `Order` objects (e.g., results of a search query) as separate _filtered_ lists which are exposed to outsiders as unmodifiable `ObservableList<Person>`, `ObservableList<Task>` and `ObservableList<Order>` respectively  that can be 'observed' e.g. the UI can be bound to these lists so that the UI automatically updates when the data in the lists change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components).

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-W08-3/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="800" />

How the Storage component works:
* Saves address book, task book, sales order book and user preference data in json format, and read them back into corresponding objects.
* The main storage class inherits from all of `AddressBookStorage`, `TaskBookStorage`, `OrderBookStorage`, and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* Each book storage (`AddressBookStorage`, `TaskBookStorage`, `OrderBookStorage`) has a `JsonSerializable` class which is in charge of converting the model's data into correct json file and retrieving the data from the json file to convert it to a model data.
* Each `JsonSerializable` class implements its own `JsonAdapted` class which specifies methods to convert model object (i.e `Person`, `Task`, `Order`) into json object and vise versa. 
* The `JsonSerializable` class and `JsonAdapted` class also checks the correctness of the json files format, and in the case when any of the format is wrong, it will then throw a `DataConversionException` and  `IllegalValueException`.

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Task and order package

The implementation of both of these packages is largely similar to the `Person` package. In the original AB3, there is a
`Person` class, stored in a `UniquePersonList` that handles list operations, further stored in a `AddressBook` that handled
other utility functions like data management.

Following this structure and outline, we had a `Task` class, stored in a `UniqueTaskList` stored in a `TaskBook`, and an
`Order` class, stored in a `UniqueOrderList` stored in an `OrderBook`. The updated model diagram reflecting these
changes can be seen in the [`Model component`](#model-component).

Similar to the `Person` class, the `Task` and `Order` classes have fields, as seen here:

![`Order Class Diagram`](images/OrderClassDiagram.png)

![`Task Class Diagram`](images/TaskClassDiagram.png)

These fields satisfy the following conditions:
* Both:
    * `Date`, `Label` : Nonempty block of alphanumeric characters of length at most 100 characters. We felt this was a reasonable
      length for both fields, and would guarantee the UI display works the way we intended.

* Task:
    * `TaskTag` : This is a tag that is either `General`, or `SO{ID}` where `ID` is the `Id` field of some `Order` object.
    * `isDone` : Boolean flag to indicate whether or not the task is done.

* Order:
    * `Amount` : Accepts any string that can be parsed by Double.parseDouble(), that results in a non-negative real number.
      Represents the amount the user charges for a given `Order`
    * `Customer` : Blocks of 1 or more alphanumeric characters, separated by at most one space. Represents the `Person` the order
      is addressed to.
    * `id` : Long used to uniquely identify `Order` objects. In this case, we did not deal with potential overflow given that
      the range of a Long in Java is up to 2^63 (which is > 10^18!). We judged that this should be more than sufficient for
      any realistic use of the application.
    * `isComplete`: Boolean flag to indicate when the `Order` is complete, and payment has been received.

Something alluded to in the fields above, is that there are implicit dependencies between the `Task`, `Order`, and `Person` classes.
To add an `Order` to SalesNote, we decided it made the most sense for there to already be a `Person` in the application
the `Order` was addressed to. So for instance, to add an order from a client named `Jamie Tan`, the user would need to ensure
that a `Person` with `Name` `Jamie Tan` existed in the application first.

Another link we thought would make sense to allow for, was to make it possible to tie an `Order` object to related `Task` objects.
`Task` objects were meant to help users manage their work, and so we felt there should be a way for a user to relate a `Task`
to a specific `Order` if they wanted to.

In both of these cases, we did not link the classes directly, hence there is no arrow between the `Order` and `Person` class
and no arrow between the `Order` and `Task` class in the diagram above. Instead, we simply made use of the fact that SalesNote
maintains both a `UniquePersonList` and a `UniqueOrderList`. To relate an `Order` to a `Person`, it is enough to remember the
`Name` field (in `UniquePersonList`, two `Person` objects with the same `Name` are considered equal). To relate a `Task` to
an `Order`, we can make use of the fact that `Order` objects have unique `id` fields.

##### Design choices
A very reasonable alternative one might consider is linking the classes directly. For instance, allowing a `Person`, to
have a list of `Order` objects related to the `Person`, and an `Order`, to have a list of `Task` objects related to the `Order`.
This was an alternative method we considered, that would come with a cost in complexity by relating the `Person`,
`Task` and `Order` objects. We felt that the method we chose that made use of the `UniqueXList` properties and kept the
classes more distinct better adhered to the Separation of Concerns Principle.

#### Updating related fields in Person class
In implementing the fields for the `Task` and `Order` object and considering possible feature flaws, we decided to update
the fields for the `Person` class as well. The original AB3 treated two people as equal only if their names were spelt exactly
the same, with this being case-sensitive. We decided that multiple clients having the exact same name was rare
enough that this notion of equality made sense, however, we felt it should apply regardless of case, i.e. a `Person` with `Name` `john doe`
should be recognised as the same a `Person` with **`Name`** `JOHN DOE`.

We updated the equality check to account for this, and also updated the input validation for `Name` to allow at most one
space between blocks of characters (previously `John   Doe` would be different from `John Doe`. We felt this likely to be
a mistake and should be avoided).
    
### Adding order feature

As mentioned above, to add an `Order`, the `Person` the `Order` is addressed to should already be in `SalesNote`. The
following is a sequence diagram showing the execution of the command:

<img src="images/AddOrderSequenceDiagram.png" width="800"/>

Focusing on after `AddOrderCommand:execute` is called,

1. First the application calls `model:hasOrder(toAdd)` to check if `toAdd` is already in the model.
2. Next, the application calls `model:hasPersonWithName(toAdd.getCustomer.getName())` to check if the `Person` the `Order`
   is addressed to is already in the application
4. If both checks pass, the application finally calls `model:addOrder(toAdd)` to add the `Order`.

Note that in the diagram above, the "else" clause for the `alt` boxes have been omitted for brevity. If either check fails, 
an exception is thrown and an error is displayed back to the user. 

Here is an activity diagram to more clearly illustrate the logic of the application:
<img src="images/AddOrderActivityDiagram.png" width="450"/>

#### Result
An Order can only be added to SalesNote if the `Person` it is addressed to is already in SalesNote, and the `Order`
is unique and does not duplicate an existing `Order` object in SalesNote.

### Updating person changes in order list and task list

Other commands where the relationships between the `Order`, `Person` and `Task` classes comes up is in
editing and deletion of clients. We wanted to achieve the following:

* When a client is deleted, their orders and the tasks linked to the orders will be deleted as well.
* When a client name is modified, this change will be updated in their existing orders.

#### Execution

The sequence diagram below shows the interaction between the `Logic` and `Model` components when a `DeleteCommand` is executed.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

1. `DeleteCommand::execute` is called and deletes the client from the list.
2. Related orders are obtained by matching the client's name and the customer of the existing orders.
3. Tasks linked to the orders and the orders themselves are deleted.

The sequence diagram below shows the interaction between the `Logic` and `Model` components when a `EditCommand` is executed.

![Interactions Inside the Logic Component for the `edit 1 n/[new name]` command ](images/EditSequenceDiagram.png)

1. `EditCommand::execute` is called and the client's details are modified.
2. Related orders are obtained by matching the client's **old** name and the customer of the existing orders.
3. The related orders' customer's name are updated.

#### Result
The changes in person objects are updated in their order and task objects.

### Other related commands

Note that similar considerations are addressed in the following commands:
* `DeleteOrderCommand` where `Task` objects related to the deleted `Order` is also deleted.
  
* `AddTaskCommand` and `EditTaskCommand` where if the user attempts to tag a `Task` to a `Order`, SalesNote first checks if the
`Order` already exists. 
  
We have omitted these as their implementation concerns are largely similar to the two above that we have already presented.

### Sorting orders feature

By default, orders are sorted in ascending order of their `id`. 
This arrangement is also followed when SalesNote starts up or the `listorders` command is executed.

The `sortorders` command sorts the orders in the `OrderBook` based on a field and an ordering specified by the user.

The field is represented using the `SortFieldType` enumeration which is encapsulated as a `SortField` object.
Currently, SalesNote supports sorting by the following fields, which have been adapted to implement the `Comparable` interface:
1. `Amount` - Represented by `SortFieldType.AMOUNT`
2. `Date` - Represented by `SortFieldType.DATE`

The ordering is represented using the `SortOrderingType` enumeration which is encapsulated as a `SortOrderingType` object. SalesNote supports sorting in: 
1. Ascending order - Represented by `SortOrderingType.ASCENDING`
2. Descending order - Represented by `SortOrderingType.DESCENDING`


The class structure of the feature is shown in the diagram below:

![SortOrdersCommandClassDiagram](images/SortOrdersCommandClassDiagram.png)

Orders are sorted using the `SortDescriptor`, which implements the `Comparator<Order>` interface.
Its `compare` method uses the `SortField` and `SortOrdering` to compare orders based on the user specified arrangement.

#### Execution

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `parseCommand("sortorder f/a o/asc")` API call. 
Details about tokenizing the user input to retrieve the field and ordering have been omitted.

![SortOrdersParserSequenceDiagram](images/SortOrdersParserSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `SortOrdersCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>


When the `SortOrderCommand` is executed, the following interactions take place in the `Logic` and the `Model` components.

![SortOrdersModelSequenceDiagram](images/SortOrdersModelSequenceDiagram.png)

After the `OrderBook` has been sorted by the amount field in ascending order, 

1.  The `SortCommand` obtains the relevant success message by invoking `sortDescriptor.generateSuccessMessage()`.
2.  A `CommandResult` object is then instantiated using the message, and returned to `LogicManager`.
3.  The UI proceeds to display the sorted list of orders.

#### Design choices

* **Alternative 1 (current choice):**  Mutating the `OrderList`.
    * Pros: Allows the sorting functionality to be less coupled with the `FilteredList` of orders.
      * This allows the user the flexibility to combine various filtering and sorting commands.
      * For instance, executing `incompleteorders` followed by `sortorders f/d o/desc` would list the incomplete orders sorted by their date in descending order.
    * Cons: Commands that mutate the list might disrupt the ordering of the sorted list. 
      * For instance, adding an `Order` to an `OrderBook` simply appends it
        at the end of the `OrderList`. Thus, the `OrderBook` needs to be reverted to its default arrangement by calling `ModelManager.resetOrderView()` whenever an order is added.
      * Note that this is not a concern for the `markorder` and `deleteorder` commands since they do not disrupt the ordering of the list.
* **Alternative 2:** Wrapping the `FilteredList` around the `SortedList`. 
    * Pros: 
      * Maintains the immutability of the order list. 
      * Ensures that the sorting arrangement is always preserved, even when another command e.g. `addorder` mutates the underlying list.
    * Cons: More difficult to implement since it entails more coupling with the `FilteredList` of orders.
    
### Displaying clients' total orders feature

The feature displays the total orders for all clients except those without orders in a new window. 
Its mechanism is a mix of the mechanisms for `MainWindow` and `HelpWindow`.

Similar to `help` and `exit`, `CommandResult` has a dedicated `boolean` field to indicate whether the command is a 
`totalorders` command. There is also a dedicated method to handle `totalorders` command in `MainWindow` class.
By calling this method, the data of total orders is reloaded similar to loading other data (`Client/Task/Order`) in the Main Window, i.e. 
through the `Logic` component. After reloading data, that method shows or focuses the total orders window similar to that of the 
help window.

#### Execution

The sequence diagram below shows the interaction within the `UI` component when a `totalorders` command is executed.

![Interactions Inside the UI Component for the `totalorders` Command](images/TotalOrdersSequenceDiagram0.png)

The sequence diagram below shows the interaction within the `Logic` component when the `UI` component calls 
`execute("totalorders")`. Note that there is no need to have a `TotalOrdersCommandParser`. This is because the 
`SalesNoteParser` can directly create and return a `TotalOrdersCommand`, similar to that of `help` and `exit` commands.  

![Interactions Inside the Logic Component for the `totalorders` Command](images/TotalOrdersSequenceDiagram1.png)


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of clients
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* needs help managing tasks
* needs help keeping financial record of sales

**Value proposition**: <br>
Lightweight application that helps manage contacts faster than a typical mouse/GUI driven app, 
and provides simple but helpful features to assist with managing a business.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                             |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add a deadline to a sales order                            | I can keep track of when I must complete it by  |
| `* * *`  | user                                       | keep track of the contact details of my clients            |it is easier to find their contact details      |
| `* * *`  | user                                       | maintain the preferences and body dimensions of my clients | I can cater to their requirements |
| `* * *`  | user                                       | mark tasks as done                                         |        |
| `* * *`  | user                                       | key customer orders into the application                   | I know what I need to complete   |
| `* * *`  | user                                       | delete a task                                              | get rid of task no longer needed to track       |
| `* * *`  | user                                       | know the valid commands when I key in the wrong command    | to help me remember what I should do     |
| `* * *`  | new user                                   | categorise my customers, to group them by categories relevant to me     | I can find the group of customers that I want easily   |
| `* * *`  | non-tech-savvy user                        | add users in a simple and straightforward way              | I can get up to speed easily   |
| `* * *`  | user                                       | easily update contact details as these are likely to change over time.  | I can get up to speed easily   |
| `* * *`  | forgetful user                             | have reminders about deadlines                             | I am on track with my work   |
| `* * *`  | user                                       | know how much in total sales each of my customers has brought in for me  |      |
| `* * `   | user                                       | track how much money I’m making each month                 | I can plan my budget accordingly    |
| `* * `   | experienced user                           | clear past entries                                         | the application is less cluttered   |
| `* * `   | user                                       | sort my deadlines by date                                  | I can keep track of which are the most urgent.   |
| `* * `   | user                                       | add notes to tasks                                         | I can add details about special requests or notes for myself, for tasks that might be more difficult and/or take up more time  |
| `* * `   | user                                       | update the requirements of my clients as their needs and preferences change | I am aware of their latest preference |
| `* * `   | new user                                   | have a tutorial to guide my on how to use the product      | I can get myself started   |
| `* * `   | user with poor eyesight                    | the text in the interface to be easily readable            |    |
| `* * `   | experienced user                           | the application to be fast and easy                        |    |
| `* * `   | user                                       | keep track of the sales                                    | I don’t have to remember them   |
| `*`      | user                                       | keep track of outstanding payments                         |    |
| `*`      | user                                       | know the breakdown of my sales each month                  | to understand better which of my products are more popular   |
| `*`      | user                                       | backup/undo my actions                                     | I can recover my lost data easily    |
| `*`      | experienced user                           | sort my clients by how much in sales they have brought in for me | I can focus more on clients that brings me more profit   |
| `*`      | skilled user                               | define my own shortcuts for commands                       |       |
| `*`      | user who is picky about UI                 | redesign aspects of the application.                       |       |
| `*`      | user                                       | know how much I spent on materials                         | I don’t exceed my budget      |
| `*`      | secure my data/information                 | secure my data/information                                 |     |
| `*`      | experienced user                           | use the app to track my material usage                     | I know when to order more in advance   |


### Use cases

For the use cases that are very similar, only the differences between them have been highlighted.

(For all use cases below, the **System** is the `SalesNote` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Add a client

**MSS**

1. User requests to add a specific client to the client list.
2. SalesNote adds the client to the list.

    Use case ends.

**Extensions**

* 1a. The format / details of the request are invalid.

  * 1a1. SalesNote shows an error message. 
  * 1a2. User enters a new request. 
  * Steps 1a1-1a2 are repeated until the request is valid.
  
    Use case resumes from step 2.
  
* 2a. The client already exists in the client list

  * 2a1. SalesNote shows an error message

    Use case resumes from step 1.

#### Use case: Add a task

Analogous to the use case for [adding a client](#use-case-add-a-client).

**Additional Extensions**

* 2b. The tag specified for the task does not correspond to an existing sales order.

  * 2b1. SalesNote shows an error message.

    Use case resumes from step 1.

#### Use case: Add an order

Analogous to the use case for [adding a client](#use-case-add-a-client).

**Additional Extensions**

* 2b. The customer specified for the order does not correspond to an existing client.

    * 2b1. SalesNote shows an error message.

      Use case resumes from step 1.

#### Use case: Delete a client

**MSS**

1. User requests to list clients.
2. SalesNote shows a list of clients.
3. User requests to delete a specific client in the list.
4. SalesNote deletes the client.
5. SalesNote deletes the orders related to that client.
6. SalesNote deletes the tasks related to those orders.

    Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.

* 3a. The given index is invalid.

    * 3a1. SalesNote shows an error message.

      Use case resumes at step 2.
  
* 5a. No related orders found.

    Use case ends.

* 6a. No related tasks found.

    Use case ends.
    
#### Use case: Delete a task

**MSS**

1. User requests to list tasks.
2. SalesNote shows a list of tasks.
3. User requests to delete a specific task in the list.
4. SalesNote deletes the task.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SalesNote shows an error message.

      Use case resumes at step 2.

#### Use case: Delete an order

**MSS**

1. User requests to list orders.
2. SalesNote shows a list of orders.
3. User requests to delete a specific order in the list.
4. SalesNote deletes the order.
5. SalesNote deletes the tasks related to the order.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SalesNote shows an error message.

      Use case resumes at step 2.
    
* 5a. No related tasks found.

  Use case ends.

#### Use case: Edit a task

**MSS**

1. User requests to list tasks.
2. SalesNote shows a list of tasks.
3. User requests to edit the details of a specific task in the list.
4. SalesNote edits the details of the task.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The format / details of the request are invalid.

    * 3a1. SalesNote shows an error message.
    * 3a2. User enters a new request.
    * Steps 3a1-3a2 are repeated until the request is valid.

      Use case resumes from step 4.

* 4a. The user has not made any changes to the task details.

    Use case resumes at step 3.

* 4b. A task with the edited details already exists in the task list.

    * 2b1. SalesNote shows an error message.

      Use case resumes from step 3.


#### Use case: Edit a client

Analogous to the use case for [editing a task](#use-case-edit-a-task).

**Additional Extensions**

* 4c. The user edits the name of the client.

    * 4d1. SalesNote updates all orders linked to the client with the new client name.

      Use case ends.

* 4d. The user updates the gender of a client without updating their dimensions.

    * 4d1. SalesNote shows an error message.

      Use case resumes from step 3.

#### Use case: Find a client

**MSS**

1. User requests to find clients by a given keyword.
2. SalesNote shows a list of clients whose details match at least 1 keyword.

   Use case ends.

**Extensions**

* 2a. None of the clients match the keyword.

    * 2a1. SalesNote displays an empty client list.

      Use case ends.

#### Use case: Find a task

Analogous to the use case for [finding a client](#use-case-find-a-client).

#### Use case: Find an order

Analogous to the use case for [finding a client](#use-case-find-a-client).

#### Use case: Mark a task as done

**MSS**

1. User requests to list incomplete tasks.
2. SalesNote shows a list of incomplete tasks.
3. User requests to mark a specific task in the list as done.
4. SalesNote marks the task as done.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SalesNote shows an error message.

      Use case resumes at step 2.

#### Use case: Mark an order as complete

Analogous to the use case for [marking a task as done](#use-case-mark-a-task-as-done).

#### Use case: Sort orders

**MSS**

1. User requests to list orders.
2. SalesNote shows a list of orders.
3. User requests to sort the orders by a specified field in either ascending or descending order.  
4. SalesNote displays the sorted list of orders.

   Use case ends.

**Extension**

- 2a. The list is empty.

  Use case ends.

* 3a. The format / details of the request are invalid

    * 3a1. SalesNote shows an error message.
    * 3a2. User enters a new request.
    * Steps 3a1-3a2 are repeated until the request is valid.

      Use case resumes from step 3.

### Non-functional requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Currency amounts, dates, and times should follow Singapore standards.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Client**: A customer in the database, identified by their name. 
* **Order**: A sales order from a customer scheduled for a target date.
* **Task**: A task for the user that has a due date and a completion status.
* **Tag**: A short string descriptor attached to a `Person` or `Task` object.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.

   1. Double-click the jar file.<br>
      Expected: Shows the GUI with sample clients, tasks and orders. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

1. Shutdown

   1. Shut down the app using the `exit` command.<br>
      Expected: The app shuts down.

### Deleting a client

1. Deleting a client while all clients are being shown

   1. Prerequisites: List all clients using the `listclients` command. Multiple clients in the list.

   1. Test case: `deleteclient 1`<br>
      Expected: First client is deleted from the list. Details of the deleted client shown in the status message.

   1. Test case: `deleteclient 0`<br>
      Expected: No client is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete client commands to try: `deleteclient`, `deleteclient x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. Deleting a client while no clients are being shown

   1. Prerequisite: Use `findclients keyword` command (where keyword matches none of the clients) to display an empty list of clients.<br>

   1. Test case: `deleteclient 1`<br>
      Expected: No client is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other delete client commands to try: `deleteclient`, `deleteclient x`, `...` (where x is anything)<br>
      Expected: Similar to previous. Note that there is no valid command because the client list is empty.

### Saving data

1. Dealing with missing data files

   1. Prerequisite: The folder containing the jar file contains a `data` folder.

   1. Delete the `data` folder then launch the app by double-clicking the jar file.<br>
      Expected: Shows the GUI with sample clients, tasks and orders. The window size may not be optimum.

1. Dealing with corrupted data files

   1. Prerequisites: The folder containing the jar file contains a `data` folder, which contains `addressBook.json`,  
      `taskBook.json` and `salesBook.json`.

   1. Test case: Corrupt the `addressBook.json` file by deleting a closing curly bracket (`}`) or any other brackets.<br>

      1. Scenario: There exists at least one order, or there is at least one task tagged with an order ID.<br>
         Expected: Shows the GUI without any data.

      1. Scenario: There are no orders, and all tasks have the `General` tag.<br>
         Expected: Shows the GUI without any clients or orders, but with previously saved tasks. 

   1. Test case: Corrupt the `taskBook.json` file by deleting a closing curly bracket (`}`) or any other brackets.<br>
      Expected: Shows the GUI without any tasks, but with previously saved clients and orders.

   1. Test case: Corrupt the `salesBook.json` file by deleting a closing curly bracket (`}`) or any other brackets.

      1. Scenario: All tasks have the 'General' tag.<br>
         Expected: Shows the GUI with previously saved clients and tasks.

      1. Scenario: There exists at least one task tagged with an order ID.<br>
         Expected: Shows the GUI without any data.
