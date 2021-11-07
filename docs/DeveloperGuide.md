---
layout: page
title: Developer Guide
---

## **Table of Contents**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is a brown-field team project based on
the [AddressBook-Level 3 (AB3)](https://github.com/se-edu/addressbook-level3) project.

Credits:
* [iText7 Event Handlers](https://kb.itextpdf.com/home/it7kb/ebooks/itext-7-jump-start-tutorial-for-java/chapter-3-using-renderers-and-event-handlers)
* [Java DateTime Format](https://www.javatpoint.com/java-get-current-date)
* [Rounding to 2 decimal places by Bharat Sinha](https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java)
* [Create borderless cells in iText7 by Samuel HuylebroeckAdd ](https://stackoverflow.com/questions/41607891/itext-7-borderless-table-no-border)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2122S1-CS2103T-W12-3/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="300" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes
called [`Main`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

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
the command `deletevendor vid/123`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `GuestListPanel`
, `VendorListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Guest` and `Vendor` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `PocketHotelParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses
   e.g., `CheckInNewGuestCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to check in a guest).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for
the `execute("deletevendor vid/123")` API call.

![Interactions Inside the Logic Component for the `deletevendor vid/123` Command](images/DeleteSequenceDiagramVendor.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteVendorCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `PocketHotelParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `CheckInNewGuestCommandParser`) which uses the other classes shown
  above to parse the user command and create a `XYZCommand` object (e.g., `CheckInNewGuestCommand`) which
  the `PocketHotelParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `CheckInNewGuestCommandParser`, `DeleteVendorCommandParser`, ...) inherit from
  the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

![ModelClassDiagram](images/ModelClassDiagram.png)

The `Model` component,

* stores the vendorbook and guestbook data i.e., all `Vendor` and `Guest` objects.
* stores the currently 'selected' `Guest` or `Vendor` objects (e.g., results of a search query) as a separate _filtered_
  list which is exposed to outsiders as an unmodifiable `ObservableList<Guest>` or `ObservableList<Vendor>` that can
  be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list
  change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `GuestBook`, which `Guest` references. This allows `GuestBook` to only require one `Tag` object per unique tag, instead of each `Guest` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="600" />

The `Storage` component,

* can save both vendor book data, guest book data, archive data, and user preference data in json format, and read them
  back into corresponding objects.
* inherits from `GuestBookStorage`, `VendorBookStorage`, `ArchiveStorage` and `UserPrefStorage`, which means it can be
  treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Book Management

**PH** allows the user to manage vendors and guests.

As mentioned in the `Model` section earlier, there are three different types of lists:

1. GuestBook (Management of guests)
2. VendorBook (Management of vendors)
3. Archive (Management of archived guests; currently not exposed to the user)

All the lists are managed by the `ModelManager` which supports some common operations across them, with certain
variations depending on which list we are currently executing the operation on.

Some common operations include:

* `addvendor` for vendors and `checkin` for guests - creates the entity and adds to the respective books.
* `editvendor` for vendors and `editguest` for guests - edits the entity and updates the book accordingly.
* `deletevendor` for vendors and `deleteguest` for guests - removes the existing entity from the respective book.
* `listvendor` for vendors and `listguest` for guest - renders all the entities of the respective book.
* `clearvendor` for vendors and `clearguest` for guest - removes all the entities from the respective book.

By segregating the model into its respective books/lists, we felt that this embraced **OOP** concepts, as it reduces
coupling and increases cohesion.

The following class diagram shows the general structure of a `GuestBook`. The same concepts were applied when building
the `VendorBook` and `Archive`.

<img src="images/GuestBookClassDiagram.png" width="500" />

The `GuestBook` implements the `ReadOnlyGuestBook` interface. The `getGuestList()` method returns an `ObservableList` of
guests. `ObservableList` makes use of the Observer pattern, as it notifies the `ModelManager` of any changes that occur
in the guest list, and reflect those changes onto the **GUI**.

### Toggling between vendor and guest list

Other than toggling between the two lists via the **GUI**. The user can make use of the commands `listguest`
and `listvendor` to toggle between the two. After certain commands like `filterguest` and `filtervendor`, the list also
gets toggled automatically for the user. The toggling is executed depending on the state of the `CommandResult`, after
executing the user command.

`MainWindow` has a function `toggleTab()` that reads in the state of the `CommandResult` and renders the correct list
accordingly.

The following activity diagram illustrates what happens to the `MainWindow` of the UI component when a user inputs a
command.

![ToggleTabActivityDiagram](images/ToggleTabActivityDiagram.png)

### Checking in a Guest

#### Implementation

The implementation of the `checkin` command was mostly based off the original AB3 implementation, with changes made to
support the `Archive` and with curated fields tied to a guest. The `checkin` command makes use of the filtered guest
list to search for the guest to be checked in to see whether they are a duplicate guess and `Archive` class to determine
if the user is trying to check in a guest that has been archived, which are both not allowed. This is done through the
implementation of `Model` called `ModelManager`. The operation
`ModelManager#getFilteredGuestList()` gets the last shown list of guests in the UI, after which a search is done to see
if the list contains the guest to check in. If the guest is not found, the details of the guest will be added. If the
guest is found, which means that there is a duplicate guest, the operation will not be allowed. In the event that the
guest cannot be found in the last shown list, the operation
`ModelManager#getArchivedGuest(PassportNumber passportNumber)` checks if the user is trying to check in a guest that has
been archived, which is not allowed.

The `checkin` command is facilitated by the `CheckInNewGuestCommandParser` and `CheckInNewGuestCommand` of PH. The
following sequence diagram shows how the `checkin` operation works:

![CheckInNewGuestSequenceDiagram](images/tracing/CheckInNewGuestSequenceDiagram.png)

The following activity diagram shows what happens when a user executes a checkin command.

![CheckInNewGuestActivityDiagram](images/tracing/CheckInNewGuestActivityDiagram.png)

### Checking in a returning Guest

#### Implementation

The implementation of the `returncheckin` command was mostly based off the original AB3 implementation, with changes
made to support the `Archive` and with curated fields tied to a guest. The `returncheckin` command makes use of the
filtered guest list to search for the guest to be checked in to see whether they are a duplicate guess, which is not
allowed, and `Archive` class to determine if the user is trying to check in a guest that has been archived. This is done
through the implementation of `Model` called `ModelManager`. The operation
`ModelManager#getFilteredGuestList()` gets the last shown list of guests in the UI, after which a search is done to see
if the list contains the guest to edit. If the guest is not found, the details of the guest will be added. If the guest
is found, which means that there is a duplicate guest, the operation will not be allowed. In the event that the guest
canno bet found in the last shown list, the operation
`ModelManager#getArchivedGuest(PassportNumber passportNumber)` checks if the user is trying to check in a guest that has
been archived, which is the purpose of this command.

The `returncheckin` command is facilitated by the `CheckInReturningGuestCommandParser` and
`CheckInReturningGuestCommand` of PH. The following sequence diagram shows how the `checkin` operation works:

![CheckInReturningGuestSequenceDiagram](images/tracing/CheckInReturningGuestSequenceDiagram.png)

The following activity diagram shows what happens when a user executes a checkin command.

![CheckInReturningGuestActivityDiagram](images/tracing/CheckInReturningGuestActivityDiagram.png)

### Editing a Guest

#### Implementation

The implementation of the `editguest` command was largely based off the original AB3 implementation, with changes made
to support the `Archive` and edit by the guest details instead of index in list. The `editguest` command makes use of
the filtered guest list to search for the guest to be edited and `Archive` class to determine if the user is trying to
edit a guest that has been archived, which is not allowed.

This is done through the implementation of `Model` called `ModelManager`. The operation
`ModelManager#getFilteredGuestList()` gets the last shown list of guests in the UI, after which a search is done to see
if the list contains the guest to edit. If the guest is found, the details of the guest will be edited.

In the event that the guest cannot found in the last shown list, the operation
`ModelManager#getArchivedGuest(PassportNumber passportNumber)` checks if the user is trying to edit a guest that has
been archived, which is not allowed.

It is worth noting that the passport number of a guest cannot be edited.

![EditSequenceDiagramGuest](images/EditSequenceDiagramGuest.png)

### Editing a Vendor

#### Implementation

The implementation of the `editvendor` command was largely based off the original AB3 implementation, with changes made
to edit a different model, `Vendor` and edit by the vendor details instead of index in list.

The difference between the Guest and Vendor model is that Vendors cannot be archived. Therefore, the implementation of
the `editvendor` command is the same as the `editguest` command, but only the `VendorBook`
(the `GuestBook` equivalent for vendors) has to be searched.

![EditSequenceDiagramVendor](images/EditSequenceDiagramVendor.png)

### Checking out a Guest

#### Implementation

The implementation of the `checkout` command builds upon the original AB3 implementation of 'delete', with changes made
to delete the guest from the model by the guest details instead of index in list. The 'checkout' command first generate
an invoice for the guest if vendor services were engaged during his/her stay. After which, the guest is deleted from the
model, and then archived.

The `checkout` command makes use of the filtered guest list to search for the guest to be checked out and `Archive`
class to archive the guest.

This is done through the implementation of `Model` called `ModelManager`. The operation
`ModelManager#getFilteredGuestList()` gets the last shown list of guests in the UI, after which a search is done to see
if the list contains the guest to check out.

If the guest is found, a check is done to see if the guest engaged any vendor services (by seeing if he/she has any
chargeables). As mentioned earlier, the invoice is generated only if the guest has chargeables.

Once the invoice (if any) is generated, the chargeables (if any) of the guest will be cleared using the operation
`Guest#clearChargeables()`.

The guest is then deleted from the model using `ModelManager#deleteGuest(Guest guest)` and added to the archive using
`ModelManager#addArchivedGuest(Guest guest)`.

![CheckoutSequenceDiagram](images/CheckoutSequenceDiagram.png)

### Filter feature

#### Implementation

The filter feature makes use of the `Model#updateFilteredGuestList` and `Model#updateFilteredVendorList` operations,
where each function takes in a `Predicate<Guest>` and `Predicate<Vendor>` respectively. To avoid repetition, we will
cover how the
`filterguest` command is implemented. The `filtervendor` command follows the exact same logic with its own unique
fields.

The `filterguest` command is facilitated by the `FilterGuestCommandParser` and `FilterGuestCommand` of **PH**. The
following sequence diagram shows how the `filterguest` operation works:

![FilterGuestSequenceDiagram](images/FilterGuestSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `FilterGuestCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Given above is an example of a user filtering guests by a tag, deluxe. The end result is a filtered list of guests with
the tag, deluxe. The execution of the above example follows the same flow as all the other commands. One important to
take note is that, the `FilterGuestCommandParser`
returns a `GuestPredicate`. This `GuestPredicate` implements `Predicate<Guest>`, and the instance instantiated by the
parser is what gets passed into `Model#updateFilteredGuestList` to achieve the end result. `filtervendor` makes use of a
class `VendorPredicate` that follows the same idea.

The following activity diagram shows what happens when a user executes a `filterguest` command, `filtervendor` follows
the same flow.

![FilterGuestActivityDiagram](images/FilterGuestActivityDiagram.png)

### Deleting a Guest

#### Implementation

The implementation of the `deleteguest` command was largely based off the original AB3 implementation, with changes made
to support the `Archive` and delete by the guest details instead of index in list. The `deleteguest` command makes use
of the `GuestBook` and `Archive` class to search for the guest to be deleted.

This is done through the implementation of `Model` called `ModelManager`. The operations
`ModelManager#getGuest(PassportNumber passportNumber)` and`ModelManager#getArchivedGuest(PassportNumber passportNumber)`
are used to check if the guests details can be found in Pocket Hotel (Either in the archive or currently checked in). If
the guest details is found in either locations, it would be deleted.

<img src="images/DeleteSequenceDiagramGuest.png" width="800" />

### Deleting a Vendor

#### Implementation

The implementation of the `deletevendor` command was largely based off the original AB3 implementation, with changes
made to delete a different model, `Vendor` and delete by the vendor details instead of index in list.

The difference between the Guest and Vendor model is that Vendors cannot be archived. Therefore, the implementation of
the `deletevendor` command is the same as the `deleteguest` command, but only the `VendorBook`
(the `GuestBook` equivalent for vendors) has to be searched.

<img src="images/DeleteSequenceDiagramGuest.png" width="800" />

### Invoice Generation

#### Implementation

Invoices in Pocket Hotel was created using the [iText7 Core](https://itextpdf.com/en/products/itext-7/itext-7-core)
library, which provides an API to create PDF documents in Java.

The implementation of invoice generation can be found in the `Invoice` class which contains almost all the code for
generating invoices. The `Invoice` class is meant to be used as a static method and should not be instantiated. It has
only one method that has the public access modifier is the static method `Invoice#generatePdfInvoice`
The other static methods are private helper functions to perform the generation of the PDF.

<img src="images/InvoiceBreakdown.png" width="450" />

An invoice has 5 components:

1. Invoice header
2. Billing details: Includes the guest name and their allocated room number
3. The invoice table: Contain services the guest used during their stay as well as the total cost. Each row contains,
   the item number (row number), the name of the vendor, their vendor ID, service type, quantity and cost per unit, as
   well as line cost (quantity multiplied by cost per unit) are included
4. A short note of thanks
5. Page number

Given below is the sequence diagram of how the invoice is created by `Invoice#generatePdfInvoice`.

<img src="images/DeleteSequenceDiagramGuest.png" width="800" />

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`
, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix A: Requirements**

### Product scope

**Target user profile**: Front-desk receptionists at small-scale hotels

* has a need to manage a significant number of contacts (both vendors and guests)
* prefers to have everything centralized in one application
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* tired of using pen and paper to keep track of contacts

**Value proposition**: Automate front-desk operations, elevating guest experience and lightens the front desk's
workload.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​            | I want to …​                                     | So that I can…​                                                                    |
| -------- | ----------------------| --------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `* * *`  | user                  | add details of vendors                              | look up vendors that suit the guest's needs and phone them.                           |
| `* * *`  | user                  | edit contact details of vendors and guests          | have the most updated information.                                                    |
| `* * *`  | user                  | delete guests/vendors                               | keep track of only guests checked into the hotel and vendors working with hotel.      |
| `* * *`  | user                  | see help instructions                               | get help on how to use the app                                                        |
| `* * *`  | user                  | save the details I enter                            |                                                                                       |
| `* * *`  | user                  | check in new guests                                 | manage all guests currently checked into the hotel.                                   |
| `* * *`  | user                  | have a faster check in for returning guests         | reduce the average check-in time at the front desk                                    |
| `* * *`  | user                  | check out my guests                                 | archive them and generate an invoice form for them.                                   |
| `* *  `  | new user              | generate an invoice form                            | charge the guest for their stay.                                                      |
| `* *  `  | user                  | charge my guests for vendors hired                  | generate an invoice form for them when they check out.                                |
| `* *  `  | new user              | clear all current data                              | get rid of sample data                                                                |
| `* *  `  | potential user        | see app populated with sample data                  | easily learn and get a feel for the app                                               |
| `* *`    | user                  | filter guests and vendors                           | look at them in more manageable lists.                                                |
| `* *`    | user                  | add tags to vendors/guests                          | easily categorize and filter them                                                     |
| `*`      | expert user           | personalize my GUI to my liking                     | optimise the layout to cater to my needs                                              |
| `*`      | CLI user              | add aliases to my commands                          | execute commands quickly with shorter syntax                                          |
| `*`      | new user              | learn how to use the app (Tutorial)                 | get more familiar with the features they offer and how I can use it better            |

### Use cases

(For all use cases below, the **System** is the `PH` and the **Actor** is the `user`, unless specified otherwise)

#### Navigation

```
UC01 - Viewing the Vendor List

MSS:
    1. User requests to go the vendor list.
    2. Pocket Hotel switches to the vendor list, where user can see all the vendors working with the hotel.
    Use case ends.
```

```
UC02 - Viewing the Guest List

MSS:
    1. User requests to go the guest list.
    2. Pocket Hotel switches to the guest list, where user can see all guests currently checked into the hotel.
    Use case ends.
```

#### Utility

```
UC03 - Viewing the help tab

MSS:
    1. User requests to go to the help tab.
    2. Pocket Hotel opens the help window directing the user to a guide on how to use the application.
    Use case ends.
```

```
UC04 - Exiting Pocket Hotel

MSS:
    1. User requests to exit the app.
    2. Pocket Hotel closes the application window.
    Use case ends.
```

```
UC05 - Saving your data

MSS:
    1. User executes a valid action.
    2. Pocket Hotel's data gets modified accordingly, and replaces the existing local save-file.
    3. Pocket Hotel shows a success message to user indicating action has been executed successfully.
    Use case ends.

Extensions:
    1a. Action carried out by user is invalid.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    2a. Unable to overwrite save file.
        2a1. Pocket Hotel shows an error message.
        Use case ends.
```

#### Managing Guests

```
UC06 - Checking in new guests

Preconditions: Guest must not already be currently checked into the hotel.
Guarantees: Guest list gets updated with the new guest checked in.

MSS:
    1. User requests to check in a new guest.
    2. Pocket Hotel adds the requested guest.
    3. Pocket Hotel shows a success message to user indicating guest has been successfully checked in.
    Use case ends.

Extensions:
    1a. Guest is already checked in.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Guest is a returning guest.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
    1c. Given fields such as passport number, name, etc. is invalid.
        1c1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC07 - Checking in returning guests

Preconditions: Guest must not already be currently checked into the hotel and must be a returning guest.
Guarantees: Guest list gets updated with the returning guest checked in.

MSS:
    1. User requests to check in the returning guest.
    2. Pocket Hotel adds the requested guest.
    3. Pocket Hotel shows a success message to user indicating guest has been successfully checked in.
    Use case ends.

Extensions:
    1a. Guest is already checked in.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Guest is not a returning guest.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
    1c. Given fields such as passport number or room number is invalid.
        1c1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC08 - Editing guests

Preconditions: Guest must exist and be currently checked into the hotel.
Guarantees: Guest list gets updated with the guest edited.

MSS:
    1. User requests to edit a guest.
    2. Pocket Hotel updates the guest with its new details.
    3. Pocket Hotel shows a success message to user indicating guest has been successfully edited.
    Use case ends.

Extensions:
    1a. Guest with given passport number is not checked in.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Given fields such as room number, name, etc. is invalid.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
    1c. No field is given to edit.
        1c1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC09 - Deleting guests

Preconditions: Guest must exist and be currently checked into the hotel.
Guarantees: Guest list gets updated with the guest removed.

MSS:
    1. User requests to delete a guest.
    2. Pocket Hotel deletes the guest.
    3. Pocket Hotel shows a success message to user indicating guest has been successfully deleted.
    Use case ends.

Extensions:
    1a. Guest is not checked in.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Given identifier, passport number is invalid.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC10 - Checking out guests

Preconditions: Guest must exist and be currently checked into the hotel.
Guarantees: Guest list gets updated with the guest archived and invoice form generated.

MSS:
    1. User requests to check out a guest.
    2. Pocket Hotel archives the guest and generates the invoice form.
    3. Pocket Hotel shows a success message to user indicating guest has been successfully checked out.
    Use case ends.

Extensions:
    1a. Guest is not checked in.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Given identifier, passport number is invalid.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC11 - Clearing guests

Preconditions: None.
Guarantees: Guest list gets cleared.

MSS:
    1. User requests to clear all guest data.
    2. Pocket Hotel clears all guest data.
    3. Pocket Hotel shows a success message to user indicating all guest data has been successfully cleared.
    Use case ends.
```

```
UC12 - Filtering guests

Preconditions: Only guest fields are given for the filter.
Guarantees: Guest list gets filtered to user's specifications.

MSS:
    1. User requests to filter guests.
    2. Pocket Hotel filters the guests, and only shows those that fit the user's specifications.
    3. Pocket Hotel shows a success message to user with the number of guests that have been filtered.
    Use case ends.

Extensions:
    1a. No fields are given to filter.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Fields given do not follow correct syntax.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC13 - Charging guests

Preconditions: Guest must exist and be currently checked into the hotel.
Guarantees: Pocket Hotel keeps track of the vendors hired by the guest.

MSS:
    1. User requests to charge a guest.
    2. Pocket Hotel keeps track of the vendor hired by the guest.
    3. Pocket Hotel shows a success message to user indicating the guest has been successfully charged.
    Use case ends.

Extensions:
    1a. Guest is not checked in.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Vendor hired does not exist.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
```

#### Managing Vendors

```
UC14 - Adding vendors

Preconditions: Vendors must not already exist in the app.
Guarantees: Vendor list gets updated with the new vendor added.

MSS:
    1. User requests to add a new vendor.
    2. Pocket Hotel adds the requested vendor.
    3. Pocket Hotel shows a success message to user indicating the vendor has been successfully added.
    Use case ends.

Extensions:
    1a. Vendor already exists.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Given operating hour start time is after the end time.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
    1c. Given fields such as vendor id, name, etc. is invalid.
        1c1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC15 - Editing vendors

Preconditions: Vendor must exist in the app.
Guarantees: Vendor list gets updated with the vendor edited.

MSS:
    1. User requests to edit a vendor.
    2. Pocket Hotel updates the vendor with its new details.
    3. Pocket Hotel shows a success message to user indicating vendor has been successfully edited.
    Use case ends.

Extensions:
    1a. Vendor with given vendor id does not exist.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Given fields such as email, name, etc. is invalid.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
    1c. No field is given to edit.
        1c1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC16 - Deleting vendors

Preconditions: Vendor must exist in the app.
Guarantees: Vendor list gets updated with the vendor removed.

MSS:
    1. User requests to delete a vendor.
    2. Pocket Hotel deletes the vendor.
    3. Pocket Hotel shows a success message to user indicating vendor has been successfully deleted.
    Use case ends.

Extensions:
    1a. Vendor with given vendor id does not exist.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Given identifier, vendor id is invalid.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
```

```
UC17 - Clearing vendors

Preconditions: None.
Guarantees: Vendor list gets cleared.

MSS:
    1. User requests to clear all vendor data.
    2. Pocket Hotel clears all vendor data.
    3. Pocket Hotel shows a success message to user indicating all vendor data has been successfully cleared.
    Use case ends.
```

```
UC18 - Filtering vendors

Preconditions: Only vendor fields are given for the filter.
Guarantees: Vendor list gets filtered to user's specifications.

MSS:
    1. User requests to filter vendors.
    2. Pocket Hotel filters the vendors, and only shows those that fit the user's specifications.
    3. Pocket Hotel shows a success message to user with the number of vendors that have been filtered.
    Use case ends.

Extensions:
    1a. No fields are given to filter.
        1a1. Pocket Hotel shows an error message.
        Use case ends.
    1b. Fields given do not follow correct syntax.
        1b1. Pocket Hotel shows an error message.
        Use case ends.
```

### Non-Functional Requirements

1. Should work on any **Mainstream OS** as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. **PH** should retain all functionalities even without a connection to the internet.
5. **PH** is meant to be used by single user at any given time.
6. **PH** should be user-friendly for any receptionist who can use a computer, and does not require any technical
   knowledge or previous experience of **CLI** apps.
7. **PH** should not crash on any incorrect user input, this should be handled safely with exceptions. Ideally,
   rendering a useful error message to the user.
   *{More to be added}*

### Glossary

* **PH**: Acronym for Pocket Hotel
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Guest**: A person staying in Pocket Hotel
* **Staff**: An employee of Pocket Hotel
* **OOP**: Object-oriented programming. A programming paradigm that relies on the idea of designing data around objects
  and classes.
* **GUI**: Graphical user interface
* **AB3**: Address Book 3

--------------------------------------------------------------------------------------------------------------------

## **Appendix B: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder<br><br>Perform one of the steps (Option 2 recommended for mac)

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

    2. Run `java -jar PH.jar` in the directory that you placed your jar

4. Saving window preferences

    3. Resize the window to an optimum size. Move the window to a different location. Close the window.

    4. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Check in a guest

1. Check in a new guest to PH

    1. Test case: `checkin n/Bobby pn/S1234 e/bobby@email.com r/23 t/VIP t/Deluxe Room`<br>
       Expected: A guest card will be created with the passport number S1234 with name "Bobby", email "bobby@gmail.com",
       room number "23", and tags "VIP" and "Deluxe Room"

    2. Test case: `checkin n/bobby`<br>
       Expected: Invalid command format error

### Editing a guest

1. Editing a guest while all guests are being shown

    1. Prerequisites: List all guests using the 'listguest' command. Alternatively, click on the "Guests" tab to view
       the list of guests.

    1. Test case: `editguest pn/S1234 n/Alexander Poon`<br>
       Expected:  The guest card of the guest identified by passport number S1234 should be updated to reflect the new
       name, "Alexander Poon". The result display shows the details of the guest that has just been edited.

    1. Test case: `editguest pn/`<br>
       Expected:  No guest is edited. Error details shown in the result display.

    1. Other incorrect editguest commands to try: `editguest pn/S1234`, `editguest n/Bernice Yu`.<br>
       Expected: Similar to previous.

### Charging a guest for services

1. Charges a guest a service

    1. Test case: `chargeguest pn/S123 vid/001`<br>
       Expected: Service from <VENDOR> has been billed to <GUEST>

<div markdown="span" class="alert alert-info">:information_source: **Note:** Please perform this test case twice, as it will be used in the invoice generation test case.

</div>

### Checking out a guest

1. Checking out a guest while all guests are being shown

    1. Prerequisites: List all guests using the 'listguest' command. Alternatively, click on the "Guests" tab to view
       the list of guests.

    1. Test case: `checkout pn/S1234`<br>
       Expected:  The guest card of the guest identified by passport number S1234 should no longer be visible in the
       guests list. The result display shows the details of the guest that has just been checked out. An invoice is
       generated for the guest as well.

    1. Test case: `checkout pn/`<br>
       Expected:  No guest is checked out. Error details shown in the result display.

    1. Other incorrect editguest commands to try: `checkout pn/A123`, `editguest pn/@@@@@`. Expected: Similar to
       previous.

### Viewing invoice generated

1. Upon performing the `checkout` command in the previous section, a PDF invoice of all the guests expenses will be
   generated.

    1. Test case: From previous step<br>
       Expected: Check directory which contains jar file for PDF named `S1234 <CURRENT_TIME>`, PDF should contain base
       price of hotel stay and the 2 charges by vendor 001

### Return check in

1. Return check in for guests whose details have been previously entered into the hotel

    1. Test case: `returncheckin pn/S1234 r/411`<br>
       Expected: Checked in guest.

### Filter guest

1. Filter guests with fields

    1. Test case: `filter guest n/Ale`, filters all guest that name starts with "Ale"
       Expected: Message saying `X guest listed`

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br>Name field is case sensitive

</div>

### Show all guests

1. Removes filters and switches to the guest list

    1. Test case:

        1. Perform filter guest example above

        2. Click on vendor list

        3. `listguest`<br>
           Expected: List will switch to guest and remove filters

### Delete guest

1. Deletes guest based on its passport number.

    1. Test case: `deleteguest pn/S1234`<br>
       Expected: Message notifying that guest is deleted

    2. Test case (Deleting an archived guest):

        1. `checkin n/Bobby pn/S1234 e/bobby@email.com r/23 t/VIP t/Deluxe Room`

        2. `checkout pn/1234`

        3. `deleteguest pn/1234`<br>
           Expected: Message notifying that guest is deleted

### Clear guest

1. Deletes all guests from PH, even archived ones

    1. Test case:

        1. `checkin n/Bobby pn/S1234 e/bobby@email.com r/23 t/VIP t/Deluxe Room`

        2. `checkout pn/1234`

        3. `clearguest`

        4. `returncheckin pn/S1234 r/111`<br>
           Expected: All guests from guest list will be cleared, `returncheckin` command will throw an error as guest
           cannot be found in archive

### Adding a vendor

1. Add vendor to list of vendors

    1. Test case:

        1. `addvendor vid/123 n/Wang's Satay e/satayMan@email.com p/84711231 a/Geylang Street 31 sn/Satay c/5 oh/15 0800-2000`<br>
           Expected: Adds vendor with vendor ID 123, called Wang's Satay with email address satayMan@email.com, phone
           number 84711231, address Geylang Street 31 , service name "Satay", and operating hours Monday and Friday
           0800-2000.

### Editing a vendor

1. Editing a vendor while all vendors are being shown

    1. Prerequisites: List all vendors using the 'listvendor' command. Alternatively, click on the "Vendors" tab to view
       the list of vendors.

    1. Test case: `editvendor vid/001 n/Jeremy Western Delivery`<br>
       Expected:  The vendor card of the vendor identified by vendor id 001 should be updated to reflect the new name, "
       Jeremy Western Delivery". The result display shows the details of the vendor that has just been edited.

    1. Test case: `editvendor vid/`<br>
       Expected:  No vendor is edited. Error details shown in the result display.

    1. Other incorrect editvendor commands to try: `editvendor vid/001`, `editvendor n/Bing Massage Parlour`.<br>
       Expected: Similar to previous.

### Filter vendor

1. Filters vendors according to filter

    1. Test case: `filtervendor oh/5 0800`<br>
       Expected: Filters vendors that are open at 0800 and displays to the GUI

    2. Test case: `filtervendor oh/5 0800-1300`<br>
       Expected: Filters all vendors that operate anywhere between 0800 and 1300 on a Friday and displays them to the
       GUI

    3. Test case: `filtervendor sn/Food`<br>
       Expected: Filters all vendors that have a service name field of food.

### Show all vendors

1. Removes filters and switches to the vendor list

    1. Test case:

        1. Perform filter vendor example above

        2. Click on guest list

        3. `listvendor`<br>
           Expected: List will switch to vendor and removes filters

### Deleting a vendor

1. Deletes a vendor based on its vendor ID

    1. Test case: `deletevendor vid/123`<br>
       Expected: Deletes vendor with vid 123 from PH

### Clear vendor

1. Deletes all vendors from PH.

    1. Test case: `clearvendor`<br>
       Expected: Deletes all vendors from PH, vendor list will be empty.

### Saving data

1. Dealing with missing/corrupted data files

   i. Test case: go to `data\addressbook.json` and corrupt the file. On bootup of the program, there should be a
   notification in the command box saying
   "File corrupted! Restored a new file." and the program will delete and load a fresh new file.

   ii. Rename `data\addressbook.json` to something else like `data\addressbook.json` would cause the addressbook to be
   not found and load the sample contacts into the addressbook.

--------------------------------------------------------------------------------------------------------------------

## **Appendix C: Effort**

### Challenges faced

Prior to the 1.3b iteration, **PH** was aimed at managing **Guests** and **Staff**. However, upon meeting with our
CS2101 lecturer, we realised that our use cases were ill-defined and we had failed to adequately address the needs of
our target audience. Coming up with meaningful use cases for that iteration was difficult and our team gathered to
brainstorm ideas on what core features we should include and who our target audience should really be. We eventually
came to a consensus that our application would be front desk oriented and ended up redesigning the entire workflow of
the application. Instead of managing Guests and Staff, our app would now manage guests and external vendors, with a
focus on streamlining all guest-related processes such as check in, checkout, charging guests etc. That was one of the
longest meetings spanning over 5 hours as we cleared out most of the edge case scenarios and came up with features that
would be useful to the application.

With 5 days left to the submission deadline, we set out to achieve what most groups would take 4 weeks to do. We had to
make significant changes to refactor the original codebase. Our original commands such as `edit`, `add` (which was
converted to `checkin` and `returncheckin`) and `delete` had to be duplicated for both guests and vendors, which greatly
increased the workload for implementation and testing. Additionally, we made changes to the original `Model` by
introducing an `Archive`, which would be used to store checked out guests and implemented several new features that were
dissimilar to any of those in **AB3**, such as invoice generation for guests who engaged vendor services during their
stay. We also have more json files to manage such as `guests.json`, `vendors.json` and `archive.json`, which increased
the amount of testing and implementation required.

In particular, with the additional feature of generating invoices, we have a new workflow to allow this functionality
with the added logic for charging guests and checking out a guest for a smoother process. The parsing of the information
and generation of the layout was also tricky.

Two major entities are involved: `Guest` and `Vendor`:
The two classes have some fields in common but are different from one another. For example, Vendors have operating hours
while Guests have a list of things that they are charged for.

The difference in workflow also greatly increases the workload of implementation and testing for both the Guest and
Vendors due to the separate command logic and storage required to handle them.

### Achievements

We managed to revamp our existing v1.3 iteration to a whole new **PH** in v1.3b iteration within a week with all the new
features added such as filter, archive, invoice, chargeable, and returning guest check in.

Our team managed to deliver a convincing pitch and demo of our product and both our CS2101 lecturer and CS2103T tutor
were impressed with our work.




