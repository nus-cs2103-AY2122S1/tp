---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)
* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for:
* At app launch: Initializing the components in the correct sequence, and connecting them up with each other.
* At shut down: Shutting down the components and invoking cleanup methods where necessary.

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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component:

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `Track2GatherParser` class to parse the user command.
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
* When called upon to parse a user command, the `Track2GatherParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `Track2GatherParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

**Note:** Command objects which have simpler syntax, e.g. `ClearCommand`, may be created directly within the `Track2GatherParser` class without the use of a `XYZCommandParser` class.

### Model component

**API** : [`Model.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="1000" />

The `Model` component:

* stores the contacts list data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _sorted_ and _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103-W14-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component:
* can save both contacts list data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `Track2GatherStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.Track2Gather.commons` package.

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

**Target user profile**: contact tracing personnel at MOH who:

* have a need to maintain a lot of contact information
* have a need to regularly contact a large set of persons and track their responsiveness over a period of time
* prefer desktop apps over other types
* prefer CLI over GUI

**Value proposition**: The app will manage up to a few thousand contacts, providing basic features for contact tracing personnel to organise and search through them according to personal information (limited to English names and Singaporean contact numbers and addresses), case numbers and Stay-Home-Notice (SHN) periods.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​          | I want to …​                   | So that I can…​                                                         |
| -------- | --------------------| --------------------------------- | ----------------------------------------------------------------------- |
| `* * *`  | new user            | see usage instructions         | refer to instructions when I forget how to use the app                  |
| `* * *`  | user                | save my contacts               | keep track of their details between sessions                            |
| `* * *`  | user                | add a person’s personal information | contact the person regularly to enforce SHN |
| `* * *`  | user                | add a person’s next-of-kin’s information | contact the person’s next-of-kin should an emergency arise |
| `* * *`  | user                | add a person’s SHN period | track the person only within their SHN period |
| `* * *`  | user                | delete multiple persons at once | more easily clean my contacts                                         |
| `* * *`  | user                | update a person's personal information and SHN period without re-adding that person  | avoid re-entering existing data |
| `* *`    | user                | update the SHN periods of all persons      | postpone or bring forward the SHN end dates according to government regulations    |
| `* *`    | user                | remove persons whose SHN period has been completed | quickly and easily clear outdated contacts           |
| `* *`    | user                | find person(s) by name, phone number, case number, SHN start date or SHN end date          | locate specific person(s) without having to go through the entire list  |
| `* *`    | user                | sort contacts by name, case number, SHN start date or SHN end date | more easily browse through the contacts |
| `* *`    | user                | see the number of search results | estimate how much additional filtering I would need to do          |
| `* *`    | user                | view a dynamically filtered list of persons who have not been called in the current SHN enforcement session | know which persons need to be called next |
| `* *`    | user                | mark a person as contacted in the current SHN enforcement session | track who I have tried to contact for the day        |
| `* *`    | user                | mark a person as un-contactable in the current SHN enforcement session | track the person's non-compliance instances for further action       |
| `* *`    | experienced user    | directly adjust my save files  | bypass the CLI for simple bulk tasks                                    |
| `*`      | user                | have Track2Gather dial my contacts for me       | increase efficiency when calling persons              |
| `*`      | user                | be able to search persons with similar postal codes         | find groups of persons who stay within a close vicinity, and take fewer trips while enforcing SHN                |
| `*`      | user                | be given a warning before making any delete operations      |  avoid accidentally losing data             |

### Use cases

(For all use cases below, the **System** is the `Track2Gather` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: UC01 - Add a new person 

**MSS:**

1.  User chooses to add a new person.
2.  Track2Gather requests for details of the person.
3.  User enters the requested details.
4.  Track2Gather adds the person and shows the details of the new person.

    Use case ends.

**Extensions**

* 3a. The format of the given details is invalid.
    * 3a1. Track2Gather shows the correct format for input.
      
      Use case resumes from step 2.
    
#### Use case: UC02 - Delete person(s)

**MSS:**

1.  User chooses to list persons.
2.  Track2Gather shows a list of persons.
3.  User identifies specific person(s) to delete from the list.
4.  Track2Gather deletes the person(s) and shows the details of the deleted person(s).

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 3a. One or more of the given index(s) is invalid.
    * 3a1. Track2Gather shows an error message.

      Use case resumes from step 2.
* 3b. The format of the given details is invalid.
    * 3b1. Track2Gather shows the correct format for input.

      Use case resumes from step 2.

#### Use case: UC03 - Edit details of an existing person

**MSS:**

1.  User chooses to list persons.
2.  Track2Gather shows a list of persons.
3.  User chooses to edit a person.
4.  Track2Gather requests for details of the person to be edited.
5.  User enters the requested details.
6.  Track2Gather edits the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 3a. The given index is invalid.
  * 3a1. Track2Gather shows an error message.

    Use case resumes from step 2.
* 5a. The format of the given details is invalid.
  * 5a1. Track2Gather shows the correct format for input.

    Use case resumes from step 2.

#### Use case: UC04 - Shift SHN end dates of all persons

**MSS:**

1.  User chooses to list persons.
2.  Track2Gather shows a list of persons.
3.  User chooses to shift the SHN end dates of all persons.
4.  Track2Gather requests for the number of days to shift the SHN end dates.
5.  User enters the number of days.
6.  Track2Gather updates all persons' SHN end dates.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 5a. The format of the given details is invalid.
    * 5a1. Track2Gather shows the correct format for input.

      Use case resumes from step 2.

#### Use case: UC05 - Clear persons with completed SHN periods

**MSS:**

1.  User chooses to list persons.
2.  Track2Gather shows a list of persons.
3.  User chooses to clear persons.
4.  Track2Gather clears all persons with completed SHN periods.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 3a. There are no persons with completed SHN periods.

  Use case ends.

#### Use case: UC06 - Find person(s) by name, phone number, case number, SHN start date or SHN end date

**MSS:**

1.  User chooses to list persons.
2.  Track2Gather shows a list of persons.
3.  User chooses to find person(s).
4.  Track2Gather requests for the search keyword(s).
5.  User enters the search keyword(s).
6.  Track2Gather shows the matched person(s) based on the search keyword(s).

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 5a. The format of the given details is invalid.
  * 5a1. Track2Gather shows the correct format for input.

    Use case resumes from step 2.
* 6a. No person found.
  * 6a1. Track2Gather prompts the user that no person is found.

    Use case ends.

#### Use case: UC07 - Sort person(s) by name, case number, SHN start date and/or SHN end date

**MSS:**

1.  User chooses to list persons.
2.  Track2Gather shows a list of persons.
3.  User chooses to sort person(s).
4.  Track2Gather requests for the sorting fields.
5.  User enters the fields to be sorted and their respective sort orders.
6.  Track2Gather displays the list of sorted person(s).

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 3a. The format of the given details is invalid.
  * 3a1. Track2Gather shows the correct format for input.

    Use case resumes from step 2.

#### Use case: UC08 - Enforce a person's SHN

**MSS:**

1.  User chooses to start a new SHN enforcement session.
2.  Track2Gather resets all persons to 'not called' in the new session, and updates the display to show all persons.
3.  User requests to update call statuses for specified person(s).
4.  Track2Gather updates the call statuses for these person(s), and updates the display to remove all called person(s).

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
* 3a. The format of the given details is invalid.
  * 3a1. Track2Gather shows the correct format for input.

    Use case resumes from step 2.

#### Use case: UC09 - Access help page

**MSS:**

1.  User requests for help.
2.  Track2Gather shows a message with a link to the user guide.
3.  User accesses the user guide.
4.  User closes the message.

    Use case ends.

**Extensions**

* 2a. User closes the message.

  Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Private contact detail**: A contact detail that is not meant to be shared with others.
* **Graphical User Interface (GUI)**: Type of user interface through which users interact with electronic devices via visual indicator representations.
* **Command Line Interface (CLI)**: Type of user interface through which users interact with a system or application by typing in text (commands).
* **Stay-Home-Notice (SHN)**: The notice involves a stipulated period consisting of a start and end date, for which a person would have to remain in their place of residence or dedicated facility.
* **SHN enforcement mode**: Refer to [User Guide](UserGuide.md#shn-enforcement-mode).
* **Case number**: The unique identifier assigned to each person in Track2Gather.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder.

    2. Double-click the jar file.
      
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.
      
      Expected: The most recent window size and location is retained.

### Adding a new person

1. Adding a person

   1. Test case: `add n/John Doe p/91234567 e/johndoe@gmail.com cn/7 ha/123 Waterloo`
      
      Expected: New person is added with the given details. Details of the added person shown in the status message.

   2. Test case: `add n/Jane Doe p/98765432 e/janedoe@gmail.com cn/8 ha/123 Toronto wa/456 Toronto qa/789 Toronto sh/2000-01-01 2000-02-02 kn/Mary Jane kp/12345678 ka/555 Montreal`
      
      Expected: New person is added with the given details. Details of the added person shown in the status message.

   3. Test case: `add n/John Doe cn/12`
      
      Expected: No person will be added as not all mandatory details are given. Error details shown in the status message. Status bar remains the same.

   4. Test case: `add`
      
      Expected: No person will be added. Error details shown in the status message. Status bar remains the same.

   5. Test case: `add INVALID_PREFIX/EXAMPLE`
      
      Expected: No person will be added. Error details shown in the status message. Status bar remains the same.

   6. Test case: `add VALID_PREFIX/INVALID_INPUT`
      
      Expected: No person will be added. Error details shown in the status message. Status bar remains the same.

### Editing a person

1. Editing a person while all persons are being shown
   
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `edit 1 n/John Doe`
      
      Expected: First person in the list has their name edited to `John Doe`. Details of the edited field(s) are shown in the status message.

   3. Test case: `edit 1 n/John Doe cn/12`
      
      Expected: First person in the list has their name edited to `John Doe` and their case number edited to `12`. Details of the edited field(s) are shown in the status message.

   4. Test case: `edit 1`
      
      Expected: No persons' contact details will be edited. Error details shown in the status message. Status bar remains the same.

   5. Test case: `edit 1 INVALID_PREFIX/EXAMPLE`
      
      Expected: No persons' contact details will be edited. Error details shown in the status message. Status bar remains the same.

   6. Test case: `edit 1 VALID_PREFIX/INVALID_INPUT`
      
      Expected: No persons' contact details will be edited. Error details shown in the status message. Status bar remains the same.

2. Editing a person while search results are being shown

    Expected: Similar to previous, but person(s) are edited based on their index(s) in the search results instead of the list.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `delete 1`
       
       Expected: First person is deleted from the list. Details of the deleted person shown in the status message.

    3. Test case: `delete 1 2`
       
       Expected: First and second persons are deleted from the list. Details of the deleted persons shown in the status message.

    4. Test case: `delete 3 1 2`
       
       Expected: First, second and third persons are deleted from the list. Details of the deleted persons shown in the status message.

    5. Test case: `delete 1 1 1 2 2 2`
       
       Expected: First and second persons are deleted from the list. Details of the deleted persons shown in the status message.

    6. Test case: `delete 0`
       
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    7. Other incorrect delete commands to try: `delete`, `delete x` (where x is larger than the list size)
       
       Expected: Similar to previous.

2. Deleting a person while search results are being shown

   Expected: Similar to previous, but person(s) are deleted based on their index(s) in the search results instead of the list.

### Updating SHN end dates of all persons

1. Batch-updating SHN periods while all persons are being shown
   
    1. Prerequisites: List all persons using the `list` command. Multiple persons with an SHN period.

    2. Test case: `tshift 3` or `tshift +3`
       
       Expected: All persons' SHN end dates will be postponed by `3` days. Details of the batch-update will be displayed as the status message.

    3. Test case: `tshift -3`
       
       Expected: All persons' SHN end dates will be brought forward by `3` days. Details of the batch-update will be displayed as the status message.

    4. Test case: `tshift 91`
       
       Expected: Unable to shift beyond the limit of `90` days. No persons' SHN end dates will be shifted. Error details shown in the status message. Status bar remains the same.

    5. Test case: `tshift 0`
       
       Expected: Unable to shift by `0` days. No persons' SHN end dates will be shifted. Error details shown in the status message. Status bar remains the same.

### Clearing person(s) with completed SHN periods

1. Clearing person(s) while all persons are being shown
   
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `clear`
      
      Expected: All persons with completed SHN periods are deleted from the list. Success message is shown.
    
   3. Test case: `clear x` (where x is any character)
      
      Expected: Similar to previous. All trailing characters or whitespaces are ignored.

### Finding persons by field

1. Find person(s) while all persons are being shown
   
    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `find n/Alex`
       
       Expected: Only persons whose name contains `Alex` will be shown.
    
    3. Test case: `find n/Alex Alice`
       
       Expected: Only persons whose name contains `Alex` or `Alice` will be shown.

    4. Test case: `find p/123`
       
       Expected: Only persons whose phone number starts with `123` will be shown.

    5. Test case: `find p/123 234`
       
       Expected: Only persons whose phone number starts with `123` or `234` will be shown.

    6. Test case: `find cn/1 2 3`
       
       Expected: Only persons with case number `1`, `2` or `3` will be shown.

    7. Test case: `find sh/start:2021-01-01 2021-01-02`
       
       Expected: Only persons with SHN start date of `2021-01-01` or `2021-01-02` will be shown.
    
    8. Test case: `find sh/end:2021-01-01 2021-01-02`
        
       Expected: Only persons with SHN end date of `2021-01-01` or `2021-01-02` will be shown.

    9. Test case: `find INVALID_PREFIX/EXAMPLE`
       
       Expected: The list is unchanged. Error details shown in the status message. Status bar remains the same.

    10. Test case: `find VALID_PREFIX/INVALID_INPUT`
        
        Expected: The list is unchanged. Error details shown in the status message. Status bar remains the same.
    
### Sorting persons

1. Sorting persons while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `sort n/`
      
      Expected: The list of persons is sorted by name (in ascending order by default).

   3. Test case: `sort sh/start:dsc`
      
      Expected: The list of persons is sorted by SHN start date in descending order.

   4. Test case: `sort sh/end: cn/asc`
      
      Expected: The list of persons is sorted by SHN end date (in ascending order by default), then by case number in ascending order.

   5. Test case: `sort`
      
      Expected: The list is unchanged. Error details shown in the status message. Status bar remains the same.

   6. Test case: `sort INVALID_PREFIX/VALID_DIRECTION`
      
      Expected: The list is unchanged. Error details shown in the status message. Status bar remains the same. 

   7. Test case: `sort VALID_PREFIX/INVALID_DIRECTION`
      
      Expected: The list is unchanged. Error details shown in the status message. Status bar remains the same.

2. Sorting persons while search results are being shown

   Expected: Similar to previous, but only search results are displayed and sorted.

### Enforcing SHN

1. Starting a new SHN enforcement session

   1. Test case: `session`
      
      Expected: All persons in the list are updated to be 'not called' status.
       
   2. Test case: `session x` (where x is any character)
      
      Expected: Similar to previous. All trailing characters or whitespaces are ignored.

2. Showing a dynamically filtered list of all persons who have not been called in the current SHN enforcement session

   1. Test case: `schedule`
      
      Expected: The list is updated to display only persons who have not been called in the current SHN enforcement session.

   2. Test case: `schedule x` (where x is any character)
      
      Expected: Similar to previous. All trailing characters or whitespaces are ignored.

3. Updating a person's call status to successful in the current SHN enforcement session
   
   1. Prerequisites: SHN enforcement mode is activated.

   2. Test case: `scall 1`
      
      Expected: First person displayed in the list is updated as successfully called. The name, case number, and number of failed call attempts of the updated person is shown in the status message. The person disappears from the display.

   3. Test case: `scall 3`
      
      Expected: Third person displayed in the list is updated as successfully called. The name, case number, and number of failed call attempts of the updated person is shown in the status message. The person disappears from the display.

   4. Test case: `scall 0`
      
      Expected: No person is updated. Error details shown in the status message. Status bar remains the same.

   5. Other incorrect scall commands to try: `scall`, `scall x` (where x is larger than the list size)
      
      Expected: Similar to previous.

4. Updating a person's call status to successful in the current SHN enforcement session
   
   1. Prerequisites: SHN enforcement mode is not activated.
      
      Expected: Similar to previous, except the updated person does not disappear from the display.

5. Updating that a failed call was made to a person in the current SHN enforcement session
   
   1. Prerequisites: SHN enforcement mode is activated.

   2. Test case: `fcall 1`
      
      Expected: First person displayed in the list is updated as unsuccessfully called. The name, case number, and number of failed call attempts of the updated person is shown in the status message. The person disappears from the display.

   3. Test case: `fcall 3`
      
      Expected: Third person displayed in the list is updated as unsuccessfully called. The name, case number, and number of failed call attempts of the updated person is shown in the status message. The person disappears from the display.

   4. Test case: `fcall 0`
      
      Expected: No person is updated. Error details shown in the status message. Status bar remains the same.
    
   5. Other incorrect fcall commands to try: `fcall`, `fcall x` (where x is larger than the list size)
      
      Expected: Similar to previous.

6. Updating that a failed call was made to a person in the current SHN enforcement session
   
   1. Prerequisites: SHN enforcement mode is not activated.
      
      Expected: Similar to previous, except the updated person does not disappear from the display.

### Saving data

1. Dealing with missing/corrupted data files
   
    1. Test case: Missing JSON file.
       
       Expected: Sample Track2Gather persons list will be generated with sample persons' information.
       
    2. Test case: Corrupted JSON file.
       
       Expected: Empty Track2Gather persons list will be generated.
