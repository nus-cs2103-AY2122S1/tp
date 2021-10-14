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

* managers of restaurant in charge of overseeing the list of customers, employees and suppliers
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: This application is created with the purpose of helping restaurant managers to manage the employees’ information (shift schedule, contact), diners (reservation timing, diner contacts) and suppliers (supplier meet up timing/weekly collection schedule, contacts), and at the same time manage contacts faster than a typical mouse/GUI driven app


### User stories

Categories: Administrative, Customer, Employee, Supplier

Priorities: High, Low

| As a ... | I can ...                                                                                                             | so that ...                                                                            | Category       | Priority |
| -------- | --------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- | -------------- | -------- |
| Manager  | log in                                                                                                                | \-                                                                                     | Administrative | High     |
|          | View specific table capacity                                                                                          | I can assign the correct group to the correct table                                    | Administrative | High     |
|          | Update table availability                                                                                             | I can ensure that people will not assign any more customers to that table              | Administrative | High     |
|          | Check table availability                                                                                              | I can assign a new group to the table                                                  | Administrative | High     |
|          | Add reservations (number of people, time, reserve table availability)                                                 | \-                                                                                     | Customer       | High     |
|          | Check if a particular timeslot is available for reservation                                                           | Organise the customer's reservation slots                                              | Customer       | High     |
|          | Add/View/Change/Delete particulars for customer(number, initials, allergies, special requests, number of people etc.) | I can contact the customers and also provide them with personalised dining experiences | Customer       | High     |
|          | View the amount of unclaimed loyalty points a customer has                                                            | I can give them the option of redeeming the points for a reward                        | Customer       | High     |
|          | Change the amount of loyalty points a customer has                                                                    | We can track the amount of loyalty points a customer has                               | Customer       | High     |
|          | View reservations on a particular day                                                                                 | manage the reservations better                                                         | Customer       | High     |
|          | Add customers to waiting list                                                                                         | Manage walk in customers                                                               | Customer       | Low      |
|          | Add waiting list customers particulars                                                                                | Organise walk in customers turn to enter the restaurant                                | Customer       | Low      |
|          | View customer's special payment method(vouchers)                                                                      | I can keep track of finances correctly                                                 | Customer       | Low      |
|          | Add/View/Change/Delete employees' personal information                                                                | \-                                                                                     | Employee       | High     |
|          | View/Change the number of leaves remaining a particular employee has                                                  | I can ensure he is only claiming leaves that he is entitled to                         | Employee       | High     |
|          | View/Change who is supposed to be on shift today                                                                      | I know who is turning up for work today                                                | Employee       | High     |
|          | View/Change who is supposed to be on shift this week                                                                  | I am aware of the week's scheduling                                                    | Employee       | High     |
|          | View/Change who is supposed to be on shift this month                                                                 | I know who is turning up for work this month                                           | Employee       | High     |
|          | View work shifts that have yet to be filled                                                                           | I can assign employees in to fill the manpower needs                                   | Employee       | High     |
|          | Add employees to available slots in a work shift                                                                      | there would be no lack of personnel                                                    | Employee       | High     |
|          | View/Change the monthly salary for a particular employee                                                              | I know if he is earning too much or too little                                         | Employee       | High     |
|          | View/Change the job type for a particular employee (full time, part time etc)                                         | I know his payout scheme and if he deserves his pay                                    | Employee       | High     |
|          | View which shifts a particular employee is working in a week                                                          | I know when he should be turning up                                                    | Employee       | Low      |
|          | Add observations of an employee during a shift                                                                        | I can review them with him/her afterwards                                              | Employee       | Low      |
|          | View/Change number of hours worked by a particular employee per period                                                | I can reward employees (employee of the month)                                         | Employee       | Low      |
|          | Add/View/Update/Delete supplier particular(name, contact info, food type)                                             | \-                                                                                     | Supplier       | High     |
|          | View all suppliers delivering today                                                                                   | I can know who to expect today                                                         | Supplier       | High     |
|          | View all suppliers delivering this week                                                                               | I can know who to expect this week                                                     | Supplier       | High     |
|          | View all deliveries for a particular supplier                                                                         | I can know the deliveries made by the supplier                                         | Supplier       | High     |
|          | Add/View/Update/Delete delivery datetime by a particular supplier                                                     | I know what deliveries i will have for what time                                       | Supplier       | High     |
|          | View status of deliveries                                                                                             | I know which delivies have been completed and which are outstanding                    | Supplier       | High     |
|          | Update deliveries as done                                                                                             | I won't accidentally wait for deliveries that have already been completed              | Supplier       | High     |
|          | View past deliveries                                                                                                  | I can keep a receipt of the deliveries made in a specific period                       | Supplier       | High     |
|          | View all suppliers by food type                                                                                       | I can know which supplier i have for a particular food type                            | Supplier       | Low      |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `RHRH` and the **Actor** is the `user`, unless specified otherwise)

**Use case (UC01): Add a Customer**

**MSS**

1. User keys in basic information shared among different classes of people like **name, email, phone number**.
2. RHRH prompts the user to enter the class of the person being entered into the system.
3. User enters the keyword corresponding to the customer class.
4. RHRH prompts the user to enter the following optional customer-specific information 1 at a time:
   1. Customer ID
   2. Loyalty points
   3. Allergies
   4. Special request
5. Upon entering the last customer-specific information, the customer will be added successfully.
Use case ends

**Extensions**

* 1a1. RHRH requests for the format to be corrected.
     * 1a2. User enters the command again. <br/>

     Steps 1a1 - 1a2 are repeated until the command is correctly formatted.


     Use case resumes from step 2.

 * 1b. RHRH detects missing details in the command entered.
     * 1b1. RHRH requests for the missing details to be filled in.
     * 1b2. User enters missing data.

     Steps 1b1 - 1b2 are repeated until the command is correctly formatted.


     Use case resumes at step 2.
     
     
 * 1c. RHRH detects an incorrect format for the email/phone number entered. 
     * 1c1. RHRH requests for the format to be corrected.
     * 1c2. User enters the command again.
     
     Steps 1c1 - 1c2 are repeated until the command is correctly formatted.

     
     Use case resumes from step 2.

 * 3a. RHRH detects an invalid class entered. 
   * 3a1. RHRH requests for the class to be corrected.
   * 3a2. User enters the class again.

   Steps 3a1 - 3a2 are repeated until the command is correctly formatted.

 
    Use case resumes from step 4.

**Use case (UC02): Add a Supplier**

**MSS**

1. User keys in basic information shared among different classes of people like **name, email, phone number**.
2. RHRH prompts the user to enter the class of the person being entered into the system.
3. User enters the keyword corresponding to the supplier class.
4. RHRH prompts the user to enter the following supplier-specific information 1 at a time:
   1. Supplier ID
   2. Type of supply 
5. Upon entering the last supplier-specific information, the supplier will be added successfully.
Use case ends


**Extensions**

* 1a. RHRH detects an incorrect format command entered.
     * 1a1. RHRH requests for the format to be corrected.
     * 1a2. User enters the command again. <br/>

     Steps 1a1 - 1a2 are repeated until the command is correctly formatted.


     Use case resumes from step 2.


 * 1b. RHRH detects missing details in the command entered.
     * 1b1. RHRH requests for the missing details to be filled in.
     * 1b2. User enters missing data.

     Steps 1b1 - 1b2 are repeated until the command is correctly formatted.


     Use case resumes at step 2.
     
     
 * 1c. RHRH detects an incorrect format for the email/phone number entered. 
     * 1c1. RHRH requests for the format to be corrected.
     * 1c2. User enters the command again.
     
     Steps 1c1 - 1c2 are repeated until the command is correctly formatted.


     Use case resumes from step 2.

 * 3a. RHRH detects an invalid class entered. 
   * 3a1. RHRH requests for the class to be corrected.
   * 3a2. User enters the class again.

   Steps 3a1 - 3a2 are repeated until the command is correctly formatted.

 
    Use case resumes from step 4.
    
**Use case (UC03): Add an Employee**

**MSS**

1. User keys in basic information shared among different classes of people like **name, email, phone number**.
2. RHRH prompts the user to enter the class of the person being entered into the system.
3. User enters the keyword corresponding to the employee class.
4. RHRH prompts the user to enter the following employee-specific information 1 at a time:
   1. Employee ID
   2. Employment type
   3. Monthly salary
5. Upon entering the last employee-specific information, the employee will be added successfully.
Use case ends


**Extensions**

* 1a. RHRH detects an incorrect format command entered.
     * 1a1. RHRH requests for the format to be corrected.
     * 1a2. User enters the command again. <br/>

     Steps 1a1 - 1a2 are repeated until the command is correctly formatted.


     Use case resumes from step 2.


 * 1b. RHRH detects missing details in the command entered.
     * 1b1. RHRH requests for the missing details to be filled in.
     * 1b2. User enters missing data.

     Steps 1b1 - 1b2 are repeated until the command is correctly formatted.


     Use case resumes at step 2.
     
     
 * 1c. RHRH detects an incorrect format for the email/phone number entered. 
     * 1c1. RHRH requests for the format to be corrected.
     * 1c2. User enters the command again.
     
     Steps 1c1 - 1c2 are repeated until the command is correctly formatted.

 
     Use case resumes from step 2.

 * 3a.  RHRH detects an invalid class entered. 
   * 3a1. RHRH requests for the class to be corrected.
   * 3a2. User enters the class again.

   Steps 3a1 - 3a2 are repeated until the command is correctly formatted.

 
    Use case resumes from step 4.

**Use case (UC04): Search for reservation availability**

**MSS**

1. User requests to search for reservation availability at a date and time.
2. RHRH shows the availability for the indicated date time.  
   Use case ends.

**Extensions:**

* 1a. User requests to search for reservation availability at a date.
    * 1a1. RHRH shows all the available slots on the indicated date.
      
    Use case ends.


* 1b. User requests to search for reservation availability at a time.
    * 1b1. RHRH shows the availability for the indicated time today
      
    Use case ends.


* 1c. RHRH detects information missing from the command entered.
    * 1c1. RHRH requests for the missing details to be filled in.
    * 1c2. User enters missing data.

    Steps 1c1 - 1c2 are repeated until the date/time is correctly formatted.


    Use case resumes from step 2.

* 1d. RHRH detects an incorrect format of date/time.
    * 1d1. RHRH requests for the format to be corrected.
    * 1d2. User enters the command again.

    Steps 1d1 - 1d2 are repeated until the command is correctly formatted.


    Use case resumes from step 2.

**Use case (UC05): Add a reservation**

**MSS**

1. User keys in reservations details.
2. RHRH <u>finds the reservation slot based on the date and/or time entered (UC04)</U>.
3. RHRH displays all reservation slots on that date and/or time to users.
4. User selects the reservation slot.
5. RHRH requests for confirmation on the selected reservation slot.
6. User confirms.
7. Reservation is added.

Use case ends.

**Extensions**

* 1a. RHRH detects an incorrect format command entered.
    * 1a1. RHRH requests for the format to be corrected.
    * 1a2. User enters the command again. <br/>
    
    Steps 1a1 - 1a2 are repeated until the command is correctly formatted.


    Use case resumes from step 2.
      

* 1b. RHRH detects missing details in the command entered.
    * 1b1. RHRH requests for the missing details to be filled in.
    * 1b2. User enters missing data.
      
    Steps 1b1 - 1b2 are repeated until the command is correctly formatted.


    Use case resumes from step 2.

* 2a. RHRH is unable to find a reservation slot based on the date and/or time entered.
    * 2a1. RHRH informs the user that the reservation slot is taken.
    * 2a2. RHRH prompts user to choose another date and/or time.
    * 2a3. User enters new date and/or time. <br/>
      
    Steps 2a1 - 2a2 are repeated until there is a reservation slot available.
  

    Use case resumes from step 3.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should not be stored in a **Database Management system**(DBMS).
5.  Should be stored *locally* and can be *manually* edited by the user.
6.  Program should be delivered to users in a single **JAR** file.
7.  Program should be able to run with just a *double-click* and no CLI required.
8.  Should be runnable on both `32-bit` and `64-bit` processors.
9.  New users should be able to navigate and use the program effectively.
10.  Software should not exceed **100MB**.
11.  Documents should not exceed **15MB** per file.
12.  Developer Guide and User Guide should be *PDF-friendly*(no expandable panels, embedded videos, animated GIFs etc.).
13.  Does not require a server component as the software will not perform automated tasks.
14.  Should only allow use by **authorized** personnel.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Contact**: A contact containing details about the particular person
* **Customer**: One of the types of contact, used to represent a customer of the restaurant
* **Loyalty** points: Points stored by each customer
* **Employee**: One of the types of contact, used to represent an employee of the restaurant
* **Employment type** : Used to distinguish the different categories of employees (Part-time, Full-time etc.)
* **Shift**: Time slot that employee is supposed to be working
* **Supplier**: One of the types of contact, used to represent a supplier of the restaurant
* **Reservation**: Reservations can be made by customers for a specific timeslot in the restaurant
* **Waiting list**: List of customers who are unable to get a reservation due to maxed-out reservations

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
