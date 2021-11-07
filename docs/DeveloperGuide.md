---
layout: page
title: Developer Guide
---

<img src = "https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/logo.jpeg?raw=true" align = "left" width="100" height="100" style="margin-right: 1.25em">
<div>

  <h1> CohortConnect v1.4 </h1>

  <b> Type. Explore. Connect. </b>
</div>

<br><br>

CohortConnect is an advanced desktop address book which facilitates networking among Computer Science (CS) students. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

With advanced features for managing large groups of contacts, CohortConnect is intended for use in a university setting. At the start of the semester, professors will distribute `csv` or `json` files containing a list of students. Instantly load them into CohortConnect with a single **Import** command. With data collected from students before the semester, our **Find A Buddy** feature helps you find potential groupmates by leveraging GitHub’s metadata using a proprietary algorithm. In the **Events** tab, you can identify events and hackathons that your peers will be attending.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Purpose

This guide is intended for current or future developers working on features for CohortConnect, or developers who want to learn
more about how CohortConnect is built. It explains the requirements of CohortConnect, the implementation of each
individual component, as well as how they are pieced together to execute user commands.

--------------------------------------------------------------------------------------------------------------------

## Prerequisites

1. General knowledge of Java.
2. Object-Oriented Programming.
3. UML Notation.
4. Basic knowledge of `csv` and `json` file formats.
5. Basic knowledge of technical terms like `CLI`, `UI`, and `JAR`.
6. `Java 11` installed on the system.

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* This project uses [jackson-dataformat-csv](https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv) for reading from and writing to CSV files.

--------------------------------------------------------------------------------------------------------------------

## Getting Started

Refer to our [Quick Start](https://ay2122s1-cs2103t-t10-1.github.io/tp/UserGuide.html#QuickStart) in our User Guide.

--------------------------------------------------------------------------------------------------------------------

## Design

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder.
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

## Implementation

This section describes noteworthy details on how certain features are implemented.

### Telegram

#### Implementation

The telegram handle field is facilitated by the `Telegram` class. It is stored interally as a `String` in the data file `addressbook.json` and is then initialized as a `Telegram` object. 

The `Telegram` class implements the following operation:

* `Telegram#isValidTelegram(String test)` — Returns true if a given string is a valid telegram handle.

Regex used in verifying the validity of telegram handle:

`public static final String VALIDATION_REGEX = "\\w{5,32}";`
* `\w` — **Word**. Any word character (alphanumeric & underscore)
* `{5,32}` — **Quantifier**. Match between 5 and 32 of the preceding token.

The `Telegram` class is first integrated into the `Person` class and added as a new field to the `Person` class. This is illustrated by the class diagram below, where every field, including the `Telegram` field, is compulsory except the `Tag` field.

![PersonWithTelegramClassDiagram](images/PersonWithTelegramClassDiagram.png)

### Optional Fields

#### Implementation

The `Phone`, `Email` and `Address` fields were modified such that these fields are no longer compulsory. The class diagram below illustrates the `Person` class after the addition of the `Telegram` field. The `Name` and `Telegram` fields are compulsory while the rest are optional.

![PersonOptionalFieldClassDiagram](images/PersonOptionalFieldClassDiagram.png)

In order to accomodate to the above mentioned new optional fields, the respective constructors were modified such that the following examples are considered valid inputs.

Example 1: Adding new contact without email and address.

```
add n/John Doe te/@johndoe123 p/98765432 
```

Example 2: Adding new contact without phone, email and address.

```
add n/John Doe te/@johndoe123
```

A more interesting input would be as such.

Example 3: Adding new contact without email and address but with empty phone input.

```
add n/John Doe te/@johndoe123 p/
```

In such a case, the constructors are modified such that the above input is also deemed as valid. The rationale behind this is that there is nothing for the `VALIDATION_REGEX` to verify, unlike in the following example.

Example 4: Adding new contact without email and address but with invalid phone input.

```
add n/John Doe te/@johndoe123 p/invalidPhoneNumber
```

For the case above, the respective constructors will carry out validation on the given input.

In order to allow for optional fields, the `AddCommandParser` also has to be modified. In particular, the following methods are modified
* `AddCommandParser#arePrefixesPresent(argumentMultimap, prefixes)`
* `AddCommandParser#parse(args)`

For the `arePrefixesPresent` method, the prefixes provided were changed to only include the following mandatory fields:
* `PREFIX_NAME`
* `PREFIX_TELEGRAM`

`AddCommandParser#arePrefixesPresent(argumentMultimap, prefixes)` uses the static parsing methods in `ParserUtil` to parse the different fields in `Person`. The individual fields are first initialised with an empty string, which is now a valid input. The method then calls the `arePrefixesPresent` method to check if the provided prefix is present. If present, the method will then call the respective static parsing methods in `ParserUtil`.

### GitHub

#### Implementation

The GitHub field is facilitated by the `Github` class. It is stored internally as a `String` in the data file `addressbook.json` and is then initialized as a `Github` object. 

The `Github` class implements the following operation:

* `Github#isValidGithub(String test)` — Returns true if a given string is a valid GitHub username.

Regex used in verifying the validity of GitHub username:

`public static final String VALIDATION_REGEX = "[a-zA-Z\d](?:[a-zA-Z\d]|-(?=[a-zA-Z\d])){0,38}";`
* `[a-zA-Z\d]` — **Character set**. Match any character in the set.
* `a-z` — **Range**. Matches a character in the range "a" to "z" (char code 97 to 122). Case sensitive.
* `A-Z` — **Range**. Matches a character in the range "A" to "Z" (char code 65 to 90). Case sensitive.
* `\d` — **Digit**. Matches any digit character (0-9).
* `(?:[a-zA-Z\d]|-(?=[a-zA-Z\d]))` — **Non-capturing group**. Groups multiple tokens together without creating a capture group.
* `|` — **Alternation**. Acts like a boolean OR. Matches the expression before or after the sign.
* `-` — **Character**. Matches a "-" character (char code 45).
* `{0,38}` — **Quantifier**. Match between 0 and 38 of the preceding token.

The `Github` class is first integrated into the `Person` class and added as a new field to the `Person` class. This is illustrated by the class diagram below, where only the `Name`, `Telegram` and `Github` fields are compulsory.

![PersonWithGithubClassDiagram](images/PersonWithGithubClassDiagram.png)

### Export command

#### Implementation

The Export command exports the current list of contacts to the specified JSON or CSV file, with help from [Jackson](https://github.com/FasterXML/jackson), an external library. It does not overwrite existing files.

Executing an Export Command can be summarized with the following steps:
1. User command is handed over to `ExportCommandParser`. After verification of arguments, an ExportCommand is generated.
2. Upon executing the `ExportCommand`, one of the 2 methods are called, depending on the file type specified.
   * `ImportCommand#exportAddressBookToJson(ReadOnlyAddressBook)`
   * `ImportCommand#exportAddressBookToCsv(ReadOnlyAddressBook)`

Firstly, the user command is handed over to `ExportCommandParser`. `ExportCommandParser` implements `Parser<ExportCommand>`. It implements the `parse()` method, which parses the user's command and returns an `ExportCommand` with the filename. In `parse()`, it verifies 3 requirements:
* Arguments are not empty
* Not more than 1 argument
* Filename provided is a .csv or .json file

The Sequence Diagram below illustrates the interactions within the `Logic` component during the first step of executing `execute("export cs2103t.json")`.

![ExportSequenceDiagram](images/ExportSequenceDiagramStep1.png)

Next, The `ExportCommand` is then executed in `LogicManager`. The `ExportCommand` class extends `Command`. It stores the filename as a class variable and its implementation of `Command#execute()`. Here, the filename is checked again to verify that it is a .csv or .json file. The current address book is obtained by `Model#getAddressBook()`. Then, depending on the file type, one of the following methods are called:

1. `exportAddressBookToJson(ReadOnlyAddressBook)`

   A temporary instance of `JsonAddressBookStorage` is created by passing in the filename, and `saveAddressBook(ReadOnlyAddressBook)` saves the current address book to the specified file.
    ```
   temporaryStorage.saveAddressBook(currentAddressBook, filePath, true);
   ```
   <div markdown="span" class="alert alert-primary">
    :bulb: **Note:** The third argument `true` indicates that `saveAddressBook(ReadOnlyAddressBook)` is triggered by an export command. This distinction is necessary as default saving of contact data to the address book is performed with the same method, but requires overwriting of the storage file, whereas the export command does not overwrite files.
    </div>

   In the Storage component, utility classes are used to check, create and write to the JSON file.
    1. `FileUtil#isFileExists(Path)` checks if there exists a file with the specified name. If there is, a `FileAlreadyExistsException` is thrown.
    2. `FileUtil#createIfMissing(Path)` creates the JSON file.
    3. The current `ReadOnlyAddressBook` is used to instantiate a new `JsonSerializableAddressBook`, a JSON-friendly equivalent of `ReadOnlyAddressBook` containing Jackson annotations. It stores a list of `JsonAdaptedPerson` instead of `Person`, which is also JSON-friendly.
    4. `JsonUtil#saveJsonFile(T, Path)` serializes the address book and writes to the file, using `ObjectMapper`, a Jackson class.

2. `exportAddressBookToCsv(ReadOnlyAddressBook)`

   A temporary instance of `CsvAddressBookStorage` is created by passing in the filename, and `saveAddressBook(ReadOnlyAddressBook)` saves the current address book to the specified file.
    ```
   temporaryStorage.saveAddressBook(currentAddressBook, filePath);
   ```
   In the Storage component, utility classes are used to check, create and write to the JSON file.
    1. `FileUtil#isFileExists(Path)` checks if there exists a file with the specified name. If there is, a `FileAlreadyExistsException` is thrown.
    2. `FileUtil#createIfMissing(Path)` creates the CSV file.
    3. The current `ReadOnlyAddressBook` is used to instantiate a new `CsvSerializableAddressBook`, a CSV-friendly equivalent of `ReadOnlyAddressBook` containing Jackson annotations. It stores a list of `CsvAdaptedPerson` instead of `Person`, which is also CSV-friendly.
    4. `CsvUtil#saveCsvFile(T, Path)` serializes the address book and writes to the file, using `CsvMapper`, a Jackson class.


The Sequence Diagram below illustrates the interactions during the second step of executing `execute("export cs2103t.json")`.

![ExportSequenceDiagram](images/ExportSequenceDiagramStep2.png)

### Import command

The Import command imports contacts from a JSON or CSV file and adds them to the current contact list, with help from [Jackson](https://github.com/FasterXML/jackson), an external library. Import does not remove existing contacts, and ignores duplicate contacts.

Executing an Import command can be summarized with the following steps:
1. Similar to Export, the first step is parsing the user command, which is done in `ImportCommandParser`. After verification, an `ImportCommand` is generated.
2. Upon executing the `ImportCommand`, one of the 2 methods are called to obtain a `List<Person>` from the specified file.
   * `ImportCommand#getImportedPersonsFromJson()`
   * `ImportCommand#getImportedPersonsFromCsv()`
3. By iterating through the list, each person is added to the current contact list in `Model`, skipping duplicates.
4. Command Result with success message is returned.

The sequence diagram for the first step is similar to the Export Command. The following sequence diagram illustrates the execution of an import command from part 2 onwards.

![ImportSequenceDiagram](images/ImportSequenceDiagram.png)

#### Reading JSON / CSV file

In both scenarios, a new `JsonAddressBookStorage` or `CsvAddressBookStorage` is created. The `AddressBookStorage` method `readAddressBook()` then reads the respective file using `JsonUtil#readJsonFile()` or `CsvUtil#readCsvFile()`. In both cases, the files are read using Jackson's `ObjectMapper` or `CsvMapper` classes respectively.


### Find command

#### Implementation

The Find command accepts names, tags, Telegram handles or GitHub usernames as parameters with the following prefixes :
* no prefix : for names
* `t/` : for tags
* `te/` : for Telegram handles
* `g/` : for GitHub usernames

and allows users to search for contacts based on the specified criterion.
It is facilitated by the `FindCommandParser` class, which implements `Parser<FindCommand>`.
It implements the `parse()` method, which parses the find parameter (eg: name, tag, etc) and returns a `FindCommand`, to be executed in
`LogicManager`.

The `FindCommand` class extends `Command`. Its instance is created by providing a predicate (condition to be fulfilled by the elements of 
the `FilteredPersonList` that contains the contact(s) matching the find parameters). Its implementation of
`Command#execute()` is where the updation of the `FilteredPersonList` to reflect the search performed on the contacts in the address book.

The Sequence Diagram below illustrates the interactions within the `Logic` and `Model` components for
the `execute("find te/alex_1")` API call.

![FindSequenceDiagram](images/FindSequenceDiagram.png)

### Welcome Window

#### Implementation

The class `WelcomeWindow` is responsible displaying the welcome window at the
start, when the application is launched. It is facilitated by `WelcomeWindow.fxml` file
which is responsible for how various components inside this window are arranged.

The `WelcomeWindow` class extends `UiPart<Stage>`.

When the app is launched, an instance of this class is created, and the 
`WelcomeWindow#start` is invoked to display the window. Various methods
including `fadeTransition` and `displayAnimatedText` are used within this
class to achieve the fading image and character typing effect respectively.

![WelcomeWindowSequenceDiagram](images/WelcomeWindowSequenceDiagram.png)

### Show command

#### Implementation

The Show command accepts the index in list or name of a contact as a parameter, and shows all the details of the contact to the
Person Details Pane on the right-hand side of the screen. It is facilitated by the `ShowCommandParser` class, which implements `Parser<ShowCommand>`.
It implements the `parse()` method, which parses the index/name and returns a `ShowCommand`, to be executed in
`LogicManager`.

The `ShowCommand` class extends `Command`. It stores the index/name as a class variable and its implementation of
`Command#execute()` calls `Command#executeWithIndex()` or `Command#executeWithName()` based on whether parameter is index or name.

#### Implementation of `Command#executeWithIndex()`

This is called if the parameter is a parsable Integer. 
The method calls `model.setSelectedIndex(index)` which displays the details of the contact on the Details Pane.



The Sequence Diagram below illustrates the interactions within the `Logic`, `Model` and `UI` components for
the `execute("show 5")` API call.

![ExportSequenceDiagram](images/ShowWithIndexSequenceDiagram.png)

#### Implementation of `Command#executeWithName()`

This is called if the parameter is **NOT** a parsable Integer.
The method calls `model.setSelectedIndex(index)` with index gotten from the `filteredList` which displays the details of the contact on the Details Pane.
If there are multiple contacts with names that contain the parameter keyword then a list of such persons are shown.
If there is no contact with name that contain the parameter keyword in the `filteredList`, then the entire addressbook is searched.


The Sequence Diagram below illustrates the interactions within the `Logic`, `Model` and `UI` components for
the `execute("show alex")` API call.

![ExportSequenceDiagram](images/ShowWithNameSequenceDiagram.png)

### Command History (Keyboard Shortcut)

In the Command Box, users can use up and down arrow keys to navigate through previous commands, similar to the Command Line. This is achieved by interactions between `CommandHistory` and `CommandBox`.

The `CommandHistory` class contains these key variables and methods:
* An `ArrayList<String>` which stores previous user commands
* An `int` which indicates the current index
* Methods `add(String)`, `getPrevious()`, `getNext()`, `resetIndex()`

When CohortConnect UI is initialized, the `CommandBox` class representing the Command Box is created, containing a single instance of `CommandHistory`. It interacts with `CommandHistory` in 3 ways:
1. If `KeyCode.UP` is detected in the `TextField`, `CommandHistory#getPrevious()` is called, and the returned String is set to the text field.

<div markdown="span" class="alert alert-primary">
:bulb: **Note:** If the history is empty, UP will return an empty string. If the current index is at the earliest command, it will continue to return the same command. 
</div>

The activity diagram below illustrates what happens when a user presses the UP arrow key.

![CommandHistoryActivityDiagramUp](images/CommandHistoryActivityDiagramUp.png)

2. If `KeyCode.DOWN` is detected in the `TextField`, `CommandHistory#getNext()` is called, and the returned String is set to the text field.

<div markdown="span" class="alert alert-primary">
:bulb: **Note:** If the history is empty or the current index is at the latest command, DOWN will return an empty string.   
</div>

The activity diagram below illustrates what happens when a user presses the DOWN arrow key.

![CommandHistoryActivityDiagramDown](images/CommandHistoryActivityDiagramDown.png)

3. When the user presses enter to execute a command, the command is saved using `CommandHistory#add(String)`, and the current index is reset using `CommandHistory#resetIndex()`.

### Switching Between Tabs (Keyboard Shortcut)

![WindowMenu](images/WindowMenu.png)

**Accelerators** are shortcuts to Menu Items, which can be associated with `KeyCombinations`. To switch between the 4 tabs, a new Menu Window was added in the MenuBar, containing 4 Menu Items which can be accessed using Cmd/Ctrl + 1/2/3/4. Each of the Menu Items have an `onAction` method declared in their FXML files, and defined in `MainWindow.java`.

1. Accelerators for the 4 Menu Items are set during initialization of `MainWindow.java`.

```
private void setAccelerators() {
    setAccelerator(contactsMenuItem, KeyCombination.valueOf("Shortcut+1"));
    setAccelerator(favoritesMenuItem, KeyCombination.valueOf("Shortcut+2"));
    setAccelerator(eventsMenuItem, KeyCombination.valueOf("Shortcut+3"));
    setAccelerator(findABuddyMenuItem, KeyCombination.valueOf("Shortcut+4"));
    ...
}
```

<div markdown="span" class="alert alert-primary">
:bulb: **Note:** When detecting KeyCombination, Javafx automatically switches its interpretation of "Shortcut" as "Ctrl" for Windows and "Cmd" for macOS. The symbols in the MenuItem (shown above) change as well.
</div>

2. When their respective key combinations are detected, the MenuItem's onAction methods are called. For example, the following method is called when Cmd/Ctrl + 4 is detected.

```
@FXML
public void handleFindABuddy() {
    tabPaneHeader.activateFindABuddy(logic);
}
```

3. `TabPaneHeader` then switches to the Find A Buddy tab using `tabPane.getSelectionModel().select(3)`.


### GitHub and Telegram Commands

In the Command Box, users can enter `g` or `te` to open the currently selected user's GitHub or Telegram in a browser, using their respective username.

The following links are used
- GitHub: `https://github.com/{username}`
- Telegram: `https://t.me/{username}`

Similar to other commands, the commands `g` and `te` are parsed in `AddressBookParser`, where it is checked that there are no other arguments.

During their execution, it is checked that there is a current user selected, using `Model#getSelectedIndex()`. If the returned value is `-1`, then a `CommandException` is thrown.

The CommandResult returned indicates whether it is triggered by a GitHub or Telegram command using booleans variables.

In `MainWindow`, if the command result `isGithub()` or `isTelegram()`, the GitHub and Telegram links in `PersonDetails` will be triggered using `PersonDetails#openTelegram()` and `PersonDetails#openGithub()`. 

--------------------------------------------------------------------------------------------------------------------

## Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## Appendix: Requirements

### Product scope

**Target user profile**:

* Computer Science students
* needs to manage a significant number of contacts
* needs help finding teammates
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Facilitates networking among Computer Science students.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                             |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | delete contacts                | make sure that the address book remains relevant and up to date        |
| `* * *`  | user                                       | edit individual contacts       | update any of the fields when necessary                                |
| `* * *`  | user                                       | add individual contacts        | reach out to them later                                                |
| `* *`    | user                                       | search for contact(s) by tag(s)| contact them based on their grouping                                   |
| `* *`    | user                                       | search for contact(s) by Telegram handles(s)| contact them conveniently                                 |
| `* *`    | user                                       | search for contact(s) by GitHub username(s)| contact them conveniently                                 |
| `* * *`  | professor                                  | be able to export a set of contacts | let other professors, TAs and students get a set of contacts quickly |
| `* * *`  | new user                                   | be able to import a set of contacts          | have some to begin with                                  |
| `* * *`  | new user                                   | have an introduction splash screen           | utilise the app and its feature well                     |
| `* * *`  | CLI user                                   | avoid using my mouse as much as possible     | -                                                        |
| `* * *`  | user                                       | group/tag people into teammates, classmates, TA's and Profs | easily manage my contacts                 |
| `* * *`  | user                                       | connect with seniors who have taken the same module         | have more guidance                        |
| `* * *`  | Teaching assistant                         | be able to get in touch with the students in my class through Telegram handles | -                          |
| `* * *`  | new user                                   | know all the different commands                             | fully utilise the tools available         |
| `* * *`  | new user                                   | learn the command formats                                   | perform tasks quickly and efficiently     |
| `* * *`  | user                                       | be able to store my contact omitting certain fields         | save contact without having to include email or address |
| `* *`    | user                                       | have a clean and uncluttered GUI                           | navigate easily between different functions in the application |
| `*`      | user                                       | retrieve previous and next commands with up and down arrow key | browse my command history to retype misspelled commands |
| `* * *`  | user                                       | sync my data with GitHub account | identify my colleagues by their profiles and connect with other users |
| `* * *`  | user                                       | view the profiles of my contacts | learn more about them and connect with them |
| `* * *`  | user                                       | navigate to a contact's Telegram or GitHub in a single click | easily contact and interact with them |
| `* * *`  | professor                                  | import and export contacts using CSV files | easily create list of contacts using Excel, for my students to import |
| `* * *`  | user                                       | favorite and unfavorite my contacts | easily identify important people |
| `* * *`  | user                                       | enter my details | make use of the Find A Buddy feature |  
| `* * *`  | user                                       | find a buddy | work with them for group projects or as a study buddy |
| `* *`    | user                                       | switch between tabs using keyboard shortcuts | navigate between tabs quickly |
| `* *`    | user                                       | edit my profile | modify my details is they are misspelled during set up |
| `* *`    | user                                       | see my profile | verify that my details are correct |
| `* * *`  | user                                       | open a contact's Github or Telegram with a single command | easily browse their Github page or reach them on Telegram |


### Use cases

(For all use cases below, the **System** is the `CohortConnect` and the **Actor** is the `user`, unless specified otherwise)

**Use Case 1: Add user**

MSS

1. User enters command to add a contact.
2. CohortConnect shows a successfully added message.
3. CohortConnect shows the updated list of contacts.

Extensions

* 1a. The input command is invalid.
  * 1a1. CohortConnect shows an error message.
  * 1a2. CohortConnect requests for correct format.
  * 1a3. User enters new data.
  * 1a1-1a3 are repeated until the data entered are valid.
  * Use case resumes from step 2.

* 1b. User enters an existing name.
  * 1b1. CohortConnect prompts that name is already taken.
  * 1b2. CohortConnect requests for correct format.
  * 1b3. User enters new data.
  * Steps 1b1-1b3 are repeated until the data entered are valid.
  * Use case resumes from step 2.

**Use Case 2: Edit user**

MSS

1. User enters command to edit a contact.
2. CohortConnect shows a successfully edited message.
3. CohortConnect shows the updated list of contacts.

   Use case ends.

Extensions

* 1a. The given index is not present.
  * 1a1. CohortConnect shows an error message.
  * 1a2. CohortConnect requests for correct format.
  * 1a3. User enters new data.
  * Steps 1a1-1a3 are repeated until the data entered are valid.
  * Use case resumes from step 2.

**Use Case 3: Delete user**

MSS

1. User enters command to delete a contact.
2. CohortConnect shows a successfully deleted message.
3. CohortConnect shows the updated list of contacts.

   Use case ends.

Extensions
    
* 1a. The given index is not present.
  * 1a1. CohortConnect shows an error message.
  * 1a2. CohortConnect requests for correct format.
  * 1a3. User enters new data.
  * Steps 1a1-1a3 are repeated until the data entered are valid.
  * Use case resumes from step 2.
  
**Use Case 4: Find a contact using name**

MSS

1. User enters command to find a contact by name.
2. CohortConnect shows list of contacts with matching name.

   Use case ends.
        
Extensions

* 1a. The given name is not present.
  * 1a1. CohortConnect shows an error message.
  * Use case ends.

* 2a. The list is empty.
  * Use case ends.
    
**Use Case 5: Find a contact using tag**

MSS

1. User enters command to find a contact by tag.
2. CohortConnect shows list of contacts labelled with matching tag.
    
    Use case ends.

Extensions
    
* 1a. The given tag doesn't exist.
  * 1a1. CohortConnect shows an error message.
  * Use case ends.
        
* 2a. The list is empty.
  * Use case ends.


**Use Case 6: Show a person's details using Index**

MSS

1.  User requests to list persons.
2.  CohortConnect shows a list of persons.
3.  User requests to show details of person at a specific index in the list.
4.  CohortConnect shows a pop-up with the person's details.

    Use case ends.

Extensions

* 2a. The list is empty.
  * Use case ends.

* 3a. The given index is invalid.
  * 3a1. CohortConnect shows an error message.
  * Use case resumes at step 2.
    

**Use Case 7: Show a person's details using Name**

MSS

1.  User requests to list persons.
2.  CohortConnect shows a list of persons.
3.  User requests to show details of a specific person in the list.
4.  CohortConnect shows a pop-up with the person's details.
    
    Use case ends.

Extensions

* 2a. The list is empty.
  * Use case ends.

* 3a. The given name is not present.
  * 3a1. CohortConnect shows an error message.
  * Use case resumes at step 2.

* 3b. Multiple matching names.
  * 3b1. CohortConnect shows an error message.
  * 3b2. Displays list of users with the same name.
  * Use case resumes at step 2.

**Use Case 18: Import contacts from JSON or CSV file**

MSS

1. User enters command to import from a JSON or CSV file.
2. CohortConnect shows the updated list of contacts.
   
   Use case ends.

Extensions

* 1a. CohortConnect cannot find the JSON or CSV file.
  * 1a1. CohortConnect shows an error message.
  * 1a2. User enters new filename.
  * Steps 1a1-1a2 are repeated until the filename received is valid.

* 1b. Filename entered does not end with `.json` or `.csv`
    * 1b1. CohortConnect shows an error message.
    * 1b3. User enters new filename.
    * Steps 1b1-1b2 are repeated until the filename received is valid.

* 1c. The JSON or CSV file is formatted wrongly. 
    * 1c1. CohortConnect shows an error message.
    Use case ends.

**Use Case 19: Export contacts to JSON or CSV file**

MSS

1. User enters command to export contacts to a named JSON or CSV file.
2. CohortConnect shows a success message.
   
   Use case ends.

Extensions

* 1a. File name already exists.
  * 1a1. CohortConnect shows an error message.
  * 1a2. CohortConnect prompts for new filename.
  * 1a3. User enters new filename.
  * Steps 1a1-1a3 are repeated until the filename received is valid.

**Use Case 20: Opening a contact's GitHub**

MSS

1. User navigates to the contact using Find (UC8-11) and / or Show command (UC12-15).
2. User clicks on the contact's GitHub username, or enters the command to open GitHub page.
3. The contact's GitHub profile is shown in a new browser.

   Use case ends.

Extensions

* 2a. The contact list is empty and the user enters the GitHub command.
  * 2a1. CohortConnect shows an error message prompting the user to select a user.
  Use case ends.

* 2b. The contact list is empty and the user tries to click the GitHub username.
  * 2b1. The contact details will be empty and there will be nothing for the user to click.
  Use case ends.

* 2c. The contact has an invalid GitHub username.
  * 2c1. The browser shows GitHub's 404 page.
  Use case ends. 

**Use Case 21: Opening a contact's Telegram**

MSS

1. User navigates to the contact using Find (UC8-11) and / or Show command (UC12-15).
2. User clicks on the contact's Telegram username, or enters the command to open Telegram.
3. The contact's Telegram profile is shown in a browser window, and redirected to the Telegram app if it is installed.

   Use case ends.

Extensions

* 2a. The contact list is empty and the user enters the Telegram command.
    * 2a1. CohortConnect shows an error message prompting the user to select a user.
      Use case ends.

* 2b. The contact list is empty and the user tries to click the Telegram username.
    * 2b1. The contact details will be empty and there will be nothing for the user to click.
      Use case ends.

* 2c. The contact has an invalid Telegram username.
    * 2c1. The invalid Telegram profile is shown in the browser window, and Telegram shows an error message when opening the profile in the Telegram application.
      Use case ends.


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Data should be saved locally.
5. Product is not required to handle communication between users.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Main Success Scenario (MSS)**: The most straightforward interaction for a given use case, which assumes that nothing goes wrong.
* **JSON**: JavaScript Object Notation, is a common file format which stores data in key-value pairs and arrays. 

--------------------------------------------------------------------------------------------------------------------

## Appendix: Instructions for manual testing

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


### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.


### Saving data

1. Dealing with missing/corrupted data files

   1. Navigate to and open `/data/addressbook.json` in a text editor.

   2. Delete one line of code from a Person object, such as `"tagged" : [ "friends" ]`

   3. Restart CohortConnect.

   4. CohortConnect will start with no contacts.

   5. An error message will be shown in the `ResultDisplay`, stating that the file is corrupted.
   

