---
layout: page title: Developer Guide parent: For Developers nav_order: 1
---

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
  original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.
</div>

### Architecture

![Architecture Diagram](images/ArchitectureDiagram.png)

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes
called [`Main`](https://github.com/nus-cs2103-AY2122S1/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/nus-cs2103-AY2122S1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is
responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete Milk -c 5`.

//TODO: modify `saveInventory()` later
![Delete Sequence Diagram](images/ArchitectureSequenceDiagram.png)

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

![Component Managers](images/ComponentManagers.png)

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**
API** : [`Logic.java`](https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

![Logic class diagram](images/LogicClassDiagram.png)

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
   executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add an item).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete Milk -c 5` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**
API** : [`Model.java`](https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

![Model class diagram](images/ModelClassDiagram.png)

The `Model` component

- stores an `Inventory` object that represents the inventory data.
- stores an optional `Order` object that represents the current order data.
- stores a `TransactionList` object that represents the transaction history of orders.
- stores a `UserPref` object that represents the user’s preferences.
- does not depend on any of the other three components (as the Model represents data entities of the domain, they should
  make sense on their own without depending on other components)

![Model Displayable class diagram](images/ModelDisplayableClassDiagram.png)

- The `Model` component interacts with `Ui` component through a `Displayable` interface.

![Model Low Level class diagram](images/ModelLowLevelClassDiagram.png)

Low level architecture of `Model` component:

- `Inventory` and `Order` are each consists of an `UniqueItemList` which contains `Items`.
- The `TransactionList` stores `TransactionRecord` which are the records of history orders.
- Each `TransactionRecord` has a unique id and timestamp, with a list of `Item` transacted in the history order.

### Storage component

**
API** : [`Storage.java`](https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

![Storage class diagram](images/StorageClassDiagram.png)

The `Storage` component,

* can save both inventory data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `InventoryStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the
  functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Ordering

### Implementation

When ModelManager is initialised, optionalOrder is set to Optional.empty(). 
At this point, the user has 1 order record with 2 items in his transaction list.

![Initial_State](images/OrderInitialState.png)

Step 1. The user enters ordering mode via the `sorder` command.

Upon entering the ordering mode, optionalOrder now has a new Order() which is empty

![Sorder_State](images/OrderSorderState.png)

Step 2. The user adds an item to the order via the `iorder` command.

Upon entering `iorder Banana c/1`, the order now contains 1 banana item.

![Iorder_State](images/OrderItem1State.png)

Next, upon entering `iorder Strawberry c/1`, the order now contains 1 strawberry item.

![Iorder_State](images/OrderItem2State.png)

Step 3. The user transacts the order via the `eorder` command.

After the transaction is done, optionalOrder is reinitialised to Optional.empty()

![Initial_State](images/OrderFinalState.png)

Step 4. The new transactions are saved to json file.

![Transact_Order_Sequence_Diagram](images/TransactOrderSequenceDiagram.png)

### Mutating Inventory

This section explains how various commands update the list of items and display the result.

As a background context, all the item objects are contained in a `UniqueItemList` object which enforces uniqueness between
items and prevent duplicates. The `Inventory` manipulates the '`UniqueItemList` to update its content which then update the
`ObservableList<Item>`. The `ObservableList<Item>` is bounded to the UI so that the UI automatically updates when the
data in the list change.

`UniqueItemList` is involved when the items are manipulated to ensure the uniqueness of the items. This, the design needs to
ensure that every command mutates the `UniqueItemList` through `Inventory`.

The general flow of inventory manipulation through AddCommand is as below:
1. The `AddCommand` object in `Logic` component interacts with `Model` component by calling the `Model#addItem()` if a
   new item is added and `Model#restockItem()` if an existing item is restocked.
2. The `Model#addItem()` and `Model#restockItem()` methods then call methods with the same method signature in `Inventory`, `Inventory#addItem()` and `Inventory#restockItem()`.
3. The `Inventory` then manipulates the `UniqueItemList` by calling the methods with the same method signature, `UniqueItemList#addItem()` and `UniqueItemList#restockItem()`.
4. UniqueItemList then updates the `ObservableList#add` and `ObservableList#set` methods which updates the list to be returned to the user.
   The returned list has added a new item or incremented the count of the existing item.


Flow:`AddCommand` -> `Model` -> `Inventory` -> `UniqueItemList` -> `ObservableList<Item>`

The above flow applies for all the other similar commands that manipulates the inventory.
The detailed flow for each command is found below:

**`AddCommand:`**      
AddCommand#execute() -> Model#addItem() or Model#restockItem() -> Inventory#addItem() or Inventory#restockItem()
-> UniqueItemList#addItem() or UniqueItemList#setItem() -> ObservableList<Item>#add() or ObservableList<Item>#set()

**`RemoveCommand:`**    
RemoveCommand#execute() -> Model#removeItem() -> Inventory#removeItem() -> UniqueItemList#setItem() -> ObservableList<Item>#set()

**`EditCommand:`**       
EditCommand#execute() -> Model#setItem() -> Inventory#setItem() -> UniqueItemList#setItem() -> ObservableList<Item>#set()

**`ClearCommand:`**       
ClearCommand#execute() -> Model#setItem() -> Inventory#resetData() -> Inventory#setItems() -> UniqueItemList#setItem() -> ObservableList<Item>#set()

**`DeleteCommand:`**      
DeleteCommand#execute() -> Model#deleteItem() -> Inventory#deleteItems() -> UniqueItemList#removeItem() -> ObservableList<Item>#remove()

**`SortCommand:`**      
SortCommand#execute() -> Model#sortItem() -> Inventory#sortItems() -> UniqueItemList#sortItem() -> ObservableList<Item>#sort()

#### Design considerations:

**Aspect:**

* **Finding Multiple Names, Ids or Tags:** The FindCommand supports finding by multiple names, ids or tags.
`IdContainsNumberPredicate`, `NameContainsKeywordsPredicate` and `TagContainsKeywordsPredicate` takes in a list of 
strings which allows storing of multiple predicates. The items in the list are then matched with each predicate to 
update the filtered list. Thus, the displayed list contains items that matches multiple predicates given.

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

* small business owner / entrepreneur
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage a small business' inventory and finances faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                         | I want to …​                                               | So that I can…​                                      |
| -------- | ------------------------------- | ---------------------------------------------------------- | ---------------------------------------------------- |
| `* * *`  | small business owner            | add items into the inventory list                          | account for newly stocked items                      |
| `* * *`  | small business owner            | record item related information (name, price, cost, id)    | record and track items' details easily               |
| `* * *`  | small business owner            | delete items from the inventory                            | account for discarded or sold items                  |
| `* * *`  | small business owner            | look at all my inventory items, with their count           | review and track my inventory                        |
| `* * *`  | small business owner            | record new orders and update the inventory accordingly     | account for newly confirmed orders                   |
| `* * *`  | small business owner            | list past transactions                                     | review verify transaction details                    |
| `* * *`  | budget-savvy business owner     | track the cost that each item incurs                       | manage business costs closely                        |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Bogo Bogo` and the **Actor** is the `user`, unless specified otherwise)

**UC01 - Adding an item**

**Actor:** User

**MSS**

1. User adds item into inventory.
2. BogoBogo saves item into inventory.

   Use case ends.

**Extensions**

* 1a. User is adding the item for the first time, and did not specify the id, cost price or sell price of the item.
    * 1a1. BogoBogo informs user of the missing details.
    * 1a2. User reenters with the missing details.

      Use case resumes at step 2.

* 1b. User is adding item that has been added before, and only specifies either name or id without the other fields.
    * 1b1. BogoBogo will replenish the item according to the count indicated (count defaults to 1)

      Use case ends.

* 1c. User is adding an item that has been added before, but provides an id that corresponds to another item.
    * 1c1. BogoBogo notifies user of the mismatch and shows the list of possible matches.
    * 1c2. User reenters with the correct details.
    * 1c3. BogoBogo will replenish the item according to the count indicated (count defaults to 1)

      Use case ends.

**UC02 - Deleting an item**

**Actor:** User

**MSS**

1. User requests to delete item from inventory.
2. BogoBogo deletes item from inventory.

   Use case ends.

**Extensions**

* 1a. User did not specify the name or serial number of the item.
    * 1a1. BogoBogo notifies user of missing details.

      Use case ends.

* 1b. The specified item is not in the inventory.
    * 1b1. BogoBogo notifies user that item is not found.

      Use case ends.

* 1c. The given id does not match with the given name.
    * 1c1. BogoBogo notifies user of the mismatch.

      Use case ends.

**UC03 - Finding items through matching keywords**

**Actor:** User

**MSS**

1. User searches for an item in the inventory by stating id, name or tag.
2. BogoBogo finds item from inventory that matches the keywords.

   Use case ends.

**Extensions**

* 1a. User specified an id which is not a positive integer or is not of 6 digits.
    * 1a1. BogoBogo notifies user of invalid id input.

      Use case ends.

* 1b. The specified item is not in the inventory.
    * 1b1. BogoBogo outputs an empty list.

      Use case ends.

* 1c. User tries to find by 2 different fields at the same time.
    * 1b1. BogoBogo notifies user that only one field can be inputted.

      Use case ends.

**UC04 - Placing an Order**

**Actor:** User

**MSS**

1. User requests to start an order.
2. BogoBogo creates an order and requests for item names and quantities.
3. User adds an item into the order.
4. BogoBogo saves item into the order.

   Step 3-4 is repeated until the user wishes to end the order.

5. User requests to end entering item and place the order.
6. BogoBogo transacts the order and updates inventory and transaction history.

   Use case ends.

**Extensions**

* 3a. User adds an item that is not in the inventory.
    * 3a1. BogoBogo notifies user that item is not found.

      Use case resumes at step 3.

* 3b. There is an insufficient supply of added items in the inventory.
    * 3a1. BogoBogo notifies user of the supply shortage.

      Use case resumes at step 3.

* 4a. User incorrectly added an item into the order.
    * 3a1. User removes specified item from the order (UC05).

      Use case resumes at step 3.

* 6a. The order is empty.
    * 7a1. BogoBogo notifies user that the order is empty.

      Use case ends.

**UC05 - Remove an item from order**

**Actor:** User

**MSS**

1. User requests to remove the specified item from the order.
2. User enters the item to remove.
3. BogoBogo removes the item from the order.

   Use case ends.

**Extensions**

* 1a. There is no order created.
    * 1a1. BogoBogo notifies user there is no order.

      Use case ends.

* 2a. The item is specified in wrong format.
    * 2a1. BogoBogo notifies user the item specification format is wrong.

      Use case ends.

* 3a. The specified item is not in the order
    * 3a1. BogoBogo notifies user the specified item is not in the order.

      Use case ends.

**UC06 - Sorting**

**Actor:** User

**MSS**

1. User requests to sort the inventory (either by name or count).
2. BogoBogo sorts the inventory accordingly.

   Use case ends.

**Extensions**

* 1a. User specifies to sort by both name and count.
    * 1a1. BogoBogo notifies user that user can only sort by either name or count, not both.

      Use case ends.

**UC06 - Help**

**Actor:** User

**MSS**

1. User requests to know what are the commands available.
2. BogoBogo shows the commands available to the user.

   Use case ends.

**Extensions**

* 1a. User specifies which command exactly he wants to know how to use.
    * 1a1. BogoBogo notifies the user what that exact command does.

      Use case ends.

* 1b. User specifies an inexistent command.
    * 1a1. BogoBogo notifies the user that the command does not exist, then proceed to display URL to userguide.

      Use case ends.

**UC07 - Remove certain amount of item**

**Actor:** User

**MSS**

1. User requests to remove certain amount of an item.
2. User enters the item name and amount.
3. BogoBogo removes the specified amount of that item.

   Use case ends.

**Extensions**

* 2a. The name and amount is specified in wrong format.
    * 2a1. BogoBogo notifies user the format is wrong.

      Use case ends.

* 2b. There are multiple matching items in inventory.
    * 2b1. BogoBogo lists out all the matching items and let user choose one.
    * 2b2. User chooses the desired item.
    * BogoBogo removes the specified amount of that item.

      Use case ends.

* 3a. The item is not in the inventory.
    * 3a1. BogoBogo notifies user there is on such item.

      Use case ends.

* 3b. The specified amount is greater than what inventory has.
    * 3b1. BogoBogo notifies user the actual amount of item in the inventory.

      Use case ends.

**UC08 - Edit an item**

**Actor:** User

**MSS**

1. User requests to edit an item.
2. User enters the item index, fields and new values to change.
3. BogoBogo updates the fields of the item at the specified index with the new values given.

   Use case ends.

**Extensions**

* 3a. The edited item is a duplicate of another item in the inventory.
    * 3a1. BogoBogo notifies user of the duplication.

      Use case ends

* 3b. The specified index is invalid.
    * 3b1. BogoBogo notifies user the index is invalid.

      Use case ends

* 3c. The specified change of the field is invalid.
    * 3b1. BogoBogo notifies user that the new value specified is invalid.

      Use case ends

**UC09 - Listing out inventory**

**Actor:** User

**MSS**

1. User requests to list out all inventory
2. BogoBogo lists out all items in inventory.

   Use case ends

**UC10 - Listing out order**

**Actor:** User

**MSS**

1. User requests to list out current order.
2. BogoBogo lists out all items in current order.

   Use case ends

**Extensions**

* 2a. There is currently no order to list.
    * 2a1. BogoBogo notifies user there is currently no order.

      Use case ends.

**UC11 - Exit the application**

**Actor:** User

**MSS**

1. User requests to exit the application
2. BogoBogo acknowledges the request and exits.

   Use case ends.

**UC12 - Clear the inventory**

**Actor:** User

**MSS**

1. User requests to clear the inventory.
2. BogoBogo acknowledges the request and clears the inventory.

   Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 distinct inventory items without a noticeable sluggishness in performance for
   typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Item**: An inventory good that the business owner is/was selling
* **Order**: Information regarding a transaction whereby the business sells a list of items to a customerfor revenue

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   a. Download the jar file and copy into an empty folder

   b. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
   optimum.

2. Saving window preferences

   a. Resize the window to an optimum size. Move the window to a different location. Close the window.

   b. Re-launch the app by double-clicking the jar file.<br>
   Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Adding an item

1. Adding a new item into the inventory

   a. Test case: `add Milk id/111111 c/1 sp/2.4 cp/1.2`<br>
   Expected: Item Milk is added to the list. Milk should have the id #111111, count 1, sales price $2.40, and cost price
   $1.20.

   b. Test case: `add Milk c/1 sp/2.4 sp/1.2`<br>
   Expected: No Milk added to the inventory. BogoBogo notifies user to specify id as well.

   c. Test case: `add n/Milk c/1 sp/2.4 sp/1.2`<br>
   Expected: No Milk added to the inventory. BogoBogo notifies of incorrect command format.

2. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
