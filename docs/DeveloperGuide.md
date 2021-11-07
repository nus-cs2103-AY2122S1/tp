---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* The formatting and content of our User Guide and Developer Guide is referenced from [AY2021S2-CS2103T-T11-2/tp](https://ay2021s2-cs2103t-t11-2.github.io/tp/)
* The NUS Mod Tracker icon is created using [Adobe Photoshop](https://www.adobe.com/sg/products/photoshop.html).
* Libraries used:
    - [JavaFX](https://openjfx.io/)
    - [Jackson](https://github.com/FasterXML/jackson)
    - [JUnit5](https://github.com/junit-team/junit5)
    - [TestFX](https://github.com/TestFX/TestFX)
--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-W17-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-W17-2/tp/tree/master/src/main/java/seedu/tracker/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-W17-2/tp/tree/master/src/main/java/seedu/tracker/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/tracker/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ModuleListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/tracker/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W17-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Module` and `Mc` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-W17-2/tp/tree/master/src/main/java/seedu/tracker/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ModuleTrackerParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a Module).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ModuleTrackerParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ModuleTrackerParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W17-2/tp/tree/master/src/main/java/seedu/tracker/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores a `UserPrefs` object that represents the user's preferences.
* stores the `ModuleTracker` data, which contains data of modules, user's information and Mc progress.
* exposes various `ObservableList`s that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list changes.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components).
<br><br>
A `Module` stores a `Title`, `Code`, `Description`, `Mc`, `AcademicCalendar` and zero or more `Tag`s.
<br><br>
A `UserInfo` stores a `Mc` as Mc goal and a `AcademicCalendar` as current semester.


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-W17-2/tp/tree/master/src/main/java/seedu/tracker/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save module tracker data, user's information and user preference data in JSON format, and read them back into corresponding objects.
* inherits from `ModuleTrackerStorage`, `UserInfoStorage` and `UserPrefStorage`, which means it can be treated as any one of the three (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.tracker.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit Module feature

#### Implementation

The `edit` command is implemented via the `EditCommand`, `EditCommandParser` and `EditModuleDescriptor` classes.

The `EditCommandParser` class implements the `Parser` interface.
The `EditCommandParser#parse()` method is responsible for parsing the user input to retrieve the index of the module, fields user want to edit and its new value. The method will return an `EditCommand` object with parsed user input as its argument.

The `EditModuleDescriptor` is responsible for storing the details to edit the module with and replacing each non-empty field value to corresponding field value of the module.

The `EditCommand` class extends the `Command` class and implements the `EditCommand#execute()` method which handles the main logic of the class.
It contains non-null `index` and `editModuleDescriptor` fields.
When the `EditCommand#execute()` method is called,
- The `Module` object corresponding to the `index` is found from the `Model`.
- Create an edited copy of the module, and replace the original module.
- A `CommandResult` is returned with the updated `Model`.

Below is an example sequence diagram and an explanation on how `EditCommand` is executed.
![EditCommand](images/EditCommandSequenceDiagram.png)

**Step 1.** The user enters the command "edit 1 c/2101".

**Step 2.** ModuleTrackerParser takes in the user's input, and calls `EditCommandParser#parse` to create an `EditCommand` object containing the data parsed from the user input.

**Step 3.** The `EditCommand` is then executed by calling its `execute` method.

**Step 4.** The module at the specified index (`2`) in the list is obtained from the `Model`.

**Step 5.** A copy of this module after substituting the edited fields with new values is created.

**Step 6.** The specified module in the `Model` is then replaced by the edited copy. The `Model` is updated to reflect this change in the Mod Tracker.

### Take a module feature

#### Implementation

The `take` command is implemented via the `TakeCommand` and `TakeCommandParser` classes.

The `TakeCommandParser` class implements the `Parser` interface and is responsible for parsing the user input to retrieve the index and `AcademicYear` object which represents the year and semester. <br>
The `TakeCommandParser#parse()` method does this, and returns a `TakeCommand` object containing the index and the `AcademicYear` object.

The `TakeCommand` class extends the `Command` class and implements the `TakeCommand#execute()` method which handles the main logic of the class. <br>
It contains non-null `index` and `academicCalendar` fields. <br>
When the `TakeCommand#execute()` method is called,

- The `Module` object corresponding to the `index` is found from the `Model`.
- A copy of the `Module` object containing the value of `academicCalendar` is created. The value of `academicCalendar` is stored in a corresponding field in this copy.
- The `Module` object in the `Model` is then replaced by this copy.

Note:
- When a new `Module` object is added to the module tracker, its `academicCalendar` field is unassigned by default.
- Removing a schedule from a module is not supported in the `take` command, this functionality is instead moved to a separate `untake` command.
- If the module is already scheduled, its current `academicCalendar` field will be overridden by a new `AcademicCalendar` object.


Below is a sequence diagram, and an example to explain how `TakeCommand` is executed.

![Interactions Inside the Logic Component for the `take 2 y/1 s/2` Command](images/TakeSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `TakeCommand` and `TakeCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

**Step 1.** The user enters the command "take 2 y/1 s/2".

**Step 2.** ModuleTrackerParser takes in the user's input, and calls `TakeCommandParser#parse` to create a TakeCommand object containing the data parsed from the user input.
* In this case, the TakeCommand object contains the specified module index (`2`), and the academic year the specified module is scheduled for (`y/1 s/2`)

**Step 3.** The `TakeCommand` is then executed by calling its `execute` method.

**Step 4.** The module at the specified index (`2`) in the list is obtained from the `Model`.

**Step 5.** A copy of this module containing the specified academic calendar (`y/1 s/2`) is created. This copy is the scheduled module.

**Step 6.** The specified module in the `Model` is then replaced by the scheduled copy. The `Model` is also updated to reflect this change in the Mod Tracker.

Certain details have been omitted from the sequence diagram for simplicity, including:
* Details of how the specified module (`moduleToSchedule`) is obtained from the `Model`.
* Details of how the `Model` is updated to reflect the changes in the Mod Tracker.

#### Design considerations:

**Aspect: How the user can take a module**

- **Alternative 1 (current choice):** User uses a separate command (`take`) to take a module
    - Pros:
        - Allows some flexibility for the user if he has not decided when to take the module.
    - Cons:
        - More steps involved for the user to take a module.
- **Alternative 2:** User indicates the year and semester when adding the module to the tracker.
    - Pros:
        - Easier for the user to take a module as there are fewer steps involved.
    - Cons:
        - The `add` command will contain many arguments, which might make it difficult for users to remember.

**Aspect: Format of user input**

- **Alternative 1 (current choice):** Users use `y/`, `s/` prefixes to specify year and semester.
    - Pros:
        - Easier to parse user inputs to get the needed data.
    - Cons:
        - Less flexible for the user.
- **Alternative 2:** Users are allowed to specify the year and semester using string inputs (i.e. y1s2, year 1 semester 2)
    - Pros:
        - Greater flexibility for the user.
    - Cons:
        - More difficult to parse the user input to extract the needed data.
        - Is inconsistent with the format of other commands.

### Find modules feature

#### Implementation

The `find` command is implemented via the `FindCommand`, `FindCommandParser` and `NameContainsKeywordsPredicate` classes.

The `FindCommandParser` class implements the `Parser` interface and the `FindCommandParser#parse()` method is responsible for parsing the user input to retrieve the `args` String which represents the keywords to search the modules by. <br>
The `FindCommandParser` checks for any invalid input before creating a new `NameContainsKeywordsPredicate` object and passing in `args` as an array.
The `FindCommandParser#parse()` method then parses the user input and returns a `FindCommand` object containing the `NameContainsKeywordsPredicate` object.

The `NameContainsKeywordsPredicate` class implements the `Predicate` interface and the `NameContainsKeywordsPredicate#test()` method is responsible for checking if the given module contains any of the valid keywords. <br>
It contains the non-null `keywords` fields, which is used to find the appropriate modules in the `NameContainsKeywordsPredicate#test()` method. <br>
When the `NameContainsKeywordsPredicate#test()` method is called, it takes in a `Module` and returns a boolean depending on whether the keyword is found inside the `Module`.

The `FindCommand` class extends the `Command` class and implements the `FindCommand#execute()` method which handles the main logic of the class. <br>
It contains the non-null `predicate` field. <br>
When the `FindCommand#execute()` method is called,

- The `Model` object is updated with a new `predicate`.
- The `Model` filters the module list based on the given `predicate`.
- A `CommandResult` is returned with the updated `Model`.

Below is a sequence diagram, and an explanation of how `FindCommand` is executed.
![FindCommand](images/FindSequenceDiagram.png)

**Step 1.** The user enters the command "find cs2103t".

**Step 2.** ModuleTrackerParser takes in the user's input, and calls `FindCommandParser#parse()` to create a `FindCommand` object containing the data parsed from the user input.

**Step 3.** The `FindCommand` is then executed by calling its `execute` method.

**Step 4.** The `predicate` of the `Model` is updated by calling its `updateFilteredModuleList` method.

**Step 5.** The list of modules that do not contain the `predicate` are filtered from the `Model`.

**Step 6.** The `Model` is updated to reflect this change in the Mod Tracker.

#### Design considerations:

**Aspect: How the user can find a module**

- **Alternative 1 (current choice):** User uses a separate command (`find`) to take a module, along with additional optional parameters as filters.
    - Pros:
        - Gives the user more control when finding a specific module.
        - Allows the user to view a more curated/specific set of results.
    - Cons:
        - User might want to view a general set of modules.
        - Might be troublesome for the user to memorise all the optional parameters.

- **Alternative 2:** User uses a separate command (`find`) to take a module, without the use of additional optional parameters.
    - Pros:
        - Easier for the user to use as there is no need to memorise all the optional parameters.
    - Cons:
        - More error-prone (such as users typing `find c` and the application returns all the modules, which has the same effect as using the list command.

**Aspect: Format of user input**

- **Alternative 1 (current choice):** Users use `c/`, `t/` prefixes to specify which components of the module to search in.
    - Pros:
        - Easier to parse user inputs to get the needed data.
    - Cons:
        - Less flexible for the user.
        - Might be troublesome for the user to memorise all the optional parameters.
- **Alternative 2:** Users are allowed to use generic prefixes to specify which components of the module to search in.
    - Pros:
        - Greater flexibility for the user and more intuitive for the user.
    - Cons:
        - Difficult for the application to differentiate between keywords and words for specifying a certain component.
        - Might be error-prone (such as when the user wants to search the entire module with the keyword `title` but the program interprets it as searching inside the module title).
        - Is inconsistent with the format of other commands.
- **Alternative 3:** Users specify what value to search for in each prefix ie `y/3`, `s/2`.
    - Pros:
        - Allows search result to be more specific.
    - Cons:
        - Might be tedious for user to manually specify each field to search for.
        - Requires the user to know what to search for, which might be hard for users who do not have the specific details of the module.
        
### Clear modules feature

#### Implementation

The `clear` command is implemented via the `ClearCommand`, `ClearCommandParser` and `ModuleInSpecificSemesterPredicate` classes.

The `ClearCommandParser` class implements the `Parser` interface.
The `ClearCommandParser#parse()` method is responsible for parsing the user input to retrieve the `AcademicYear` and `Semester` object which specify the semester in which the user want to untake all modules. The method will return a `ClearCommand` object with `AcademicCalendar` as its argument.
The `ClearCommandParser#arePrefixesPresent()` method is responsible to check if all fields stated in the arguments are provided.

The `ClearCommand` class extends the `Command` class and implements the `ClearCommand#execute()` method which handles the main logic of the class.
When the `ClearCommand#execute()` method is called,
- All `Module` objects which has the corresponding `AcademicCalendar` is filtered out from the `Model` and store in a list
- For each `Module` in the list, the `Module` is replaced by a copy of it without the `AcademicCalander` field.
- A `CommandResult` is returned with the updated `Model`.

Below is a sequence diagram, and an explanation of how `ClearCommand` is executed.
![ClearCommand](images/ClearCommandSequenceDiagram.png)

**Step 1.** The user enters the command "clear 2 y/1 s/1".

**Step 2.** ModuleTrackerParser takes in the user's input, and calls `ClearCommandParser#parse` to create a `ClearCommand` object containing the data parsed from the user input.

**Step 3.** The `ClearCommand` is then executed by calling its `execute` method.

**Step 4.** The list of modules that are planned to take in the indicating semester is obtained from the `Model`.

**Step 5.** For each module in the filtered list, the academic plan is removed from the module.

**Step 6.** The `Model` is updated to reflect this change in the Mod Tracker.



#### Design Considerations:

**Aspect: How should clear command work**

- **Alternative 1 (current choice)**: When a clear command is called, all modules in a specific semester will be untaked.
    - Pros:
      - Allow users to untake multiple modules conveniently.
      - User can plan the specific semester from scratch using clear command.
    - Cons:
      - The command name might be confusing
- **Alternative 2:** When a clear command is called, all modules in a specific semester will be deleted.
    - Pros:
      - Allow users to delete multiple modules conveniently.
      - User can plan the specific semester from scratch using clear command.
    - Cons:
      - User need to add all the modules again, if they want to use it later
- **Alternative 3:** When a clear command is called, all modules in the module tracker list will be deleted.
    - Pros:
      - Allow users to delete everything and restart conveniently
    - Cons:
      - All modules stored in the storage in advance will be deleted as well
      - User will need to add all the modules again, once the clear command is called
      - It will be expensive, if the user accidentally uses the clear command


### Delete Module feature

#### Implementation

This section explains the mechanism used to delete a `Module` from the `ModuleTracker`.

The `DeleteCommand` results in the specified module being deleted from the application. This command requires a compulsory field Module Index to specify which module will be deleted.

When the command is executed, a concrete `DeleteCommand` is created containing the specified Module Index.

The `DeleteCommand` implements `DeleteCommandParser` method, which calls the appropriate methods in `Model` to get the specific `Module` and appropriate methods in `ModuleTracker` to remove the module.

Below is a sequence diagram and explanation of how the `DeleteCommand` is executed.

![DeleteCommand](images/DeleteSequenceDiagram.png)

**Step 1.** The user enters the command "delete 1".

**Step 2.** ModuleTrackerParser takes in the user's input, and calls `DeleteCommandParser#parse` to create an `DeleteCommand` object containing the data parsed from the user input.

**Step 3.** The `DeleteCommand` is then executed by calling its `execute` method.

**Step 4.** Since the `Model` is passed to `DeleteCommand#execute`, it is able to call a method `Model#getFilteredModuleList` to get the filtered list.

**Step 5.** From this module list, we can find the correct `Module` to be deleted by calling `get` function with the specified `index`.

**Step 6.** The `Module` will be removed by calling the `deleteModule` method in `Model`.


### Set current semester and MC goal feature

#### Implementation
The `set` command has 2 functionalities: set current semester and set MC goal. Each `SetCommand` can perform one and only one of these two functionalities. `set` command is implemented via the `SetCommand` and `SetCommandParser` classes.

The `SetCommandParser` class implements the `Parser` interface and is responsible for parsing the user input. From user input, it checks whether the user want to set current semester or MC goal, and will then retrieve the corresponding object(`AcademicCalendar` or `Mc`) from user input and create the `SetCommand`.

Each `SetCommand` object has a boolean attribute `isSemChanged` indicating whether this `SetCommand` need to set current semester or MC goal. It also has a `userInfo` field to store the specified semester or MC goal that this `SetCommand` is going to update to.

The `SetCommand` class extends `Command` class and implements the `SetCommand#execute()` method which handles the main logic of the class.
When the `SetCommand#execute()` method is called,
* The `isSemchanged` value is checked to decide whether this command need to change current semester or MC goal.
* If this `SetCommand` is to change current semester, the `academicYear` in `SetCommand`'s `userInfo` field will be retrieved and `Model` is called to update current semester. `Model` will also be called to update `filteredModuleList` so that in the UI, the color of each module card will be updated according to the new current semester.
* If this `SetCommand` is to change MC goal, the `mc` in `SetCommand`'s `userInfo` field will be retrieved and `Model` is called to update MC goal.

Below are the sequence diagrams and explanation of how `SetCommand` is executed. The explanation uses set semester as an example, the mechanism for setting MC goal is similar.

![Sequence diagram of parsing `set` command](images/SetSequenceDiagram.png)

**Step 1.** The user executes the command `set y/1 s/1` and the user input is passed to `LogicManager`.

**Step 2.** `LogicManager` passes the user input to `ModuleTrackerParser` and `ModuleTrackerParser` will call `SetCommandParser#parse`.

**Step 3.** `SetCommandParser` will check whether there is MC prefix `m/` in the user input. If there isn't, it will then check whether there is `AcademicYear` prefix `y/` and `Semester` prefix`s/`.In this case, there is, so it will create a new `AcademicYear` and `Semester`, and use them to create a new `AcademicCalendar`.

**Step 4.** `SetCommandParser` creates a `SetCommand` with the newly created `AcademicCalendar`.

![Sequence diagram of executing `set` command](images/SetExecuteSequenceDiagram.png)

**Step 5.** The `SetCommand` is executed by calling its `execute` method.

**Step 6.** During execution, the `SetCommand` checks whether it needs to set current semester or mc goal by checking the `isSemChanged` property. In this case, it needs to set current semester.

**Step 7.**  Since the `Model` is passed to `SetCommand#execute`, it is able to call the method `Model#setCurrentSemester` to update the current semester in `Model`.

**Step 8.** After the current semester is changed, `SetCommand` will call the method `Model#updateFilteredModuleList()`, which will notify the `ModuleListPanel` in `UI` to update the color of all module cards.

#### Design Considerations:

**Aspect: How can the user set current semester**

- **Alternative 1 (current choice)**: User sets the current semester through the command line.
    - Pros:
        - For a user who is familiar with the app and CLI, they don't need to use mouse to change current semester.
    - Cons:
        - The command is less intuitive than drop-down list, the user might need to memorize the `set` command format.
- **Alternative 2:** A button in the GUI that when clicked, will show drop-down lists of year and semester for user to choose.
    - Pros:
        - It is very straight forward and intuitive.
        - For beginners, and users who are not familiar with CLI, they can change current semester easily.
    - Cons:
        - For users who are familiar with CLI, picking academic year and semester using mouse may slow down their working efficiency.

**Aspect: How the user can set a Mc goal and current semester**

- **Alternative 1(current choice)**: The user can use a single `set` command to perform both set Mc goal and change current semester functionalities.
    - Pros:
        - The command is short, as a user that is familiar with the app, they can execute the command quickly and are less likely to make mistakes.
    - Cons:
        - For beginners, the `set` command might be a bit confusing since the word "set" shows little hints on what the command does.
- **Alternative 2:** The user can use `setMcGoal` and `setCurrentSemester` command to perform the two functionalities respectively.
    - Pros:
        - The command is very clear. For beginners, they can understand the command quickly.
        - `setMcGoal` and `setCurrentSemester` gives hints to user regarding what parameters need to be filled in.
    - Cons:
        - The command is very long, which takes more time to type in. For a user who is familiar with the app, they might not be able to use it efficiently.
        - The command consist of capital letters and lower-case letters and is long. Thus, it might be more prone to errors.

### View modules taken in specific semester feature
The `view` command results in modules taken in a specific semester being shown.

The `view` command is implemented via the `ViewCommand`, `ViewCommandParser` and `ModuleInSpecificSemesterPredicate` classes.

The `ViewCommandParser` class implements the `Parser` interface.
The `ViewCommandParser#parse()` method is responsible for parsing the user input to retrieve the `AcademicYear` and `Semester` object. These 2 objects will be used to create an `AcademicCalendar` object, which will be used to create `ModuleInSpecificSemesterPredicate`.
The method will return a `ViewCommand` object with `ModuleInSpecificSemesterPredicate` as its argument.

The `ViewCommand` class extends the `Command` class and implements the `ViewCommand#execute()` method.
When the `ViewCommand#execute()` method is called,
- All `Module` objects in the specified semester is filtered out based on the `ModuleInSpecificSemesterPredicate` and are store in a filtered list.
- A `CommandResult` is returned with the updated `Model`.

#### Implementation
Below is a sequence diagram and explanation of how the `view` command works.
![Sequence diagram of executing `view` command](images/ViewSequenceDiagram.png)

**Step 1.** The user executes the command `view y/2 s/1` and the user input is passed to `LogicManager`.

**Step 2.** `LogicManager` passes the user input to `ModuleTrackerParser` and `ModuleTrackerParser` will call `ViewCommandParser#parse`.

**Step 3.** `ViewCommandParser` creates a new Academic calendar by parsing the user input.

**Step 4.** `ViewCommandParser` creates a predicate that can check whether the module is in the semester specified by the user.

**Step 5.** `ViewCommandParser` creates a new `ViewCommand` and it is returned to LogicManager.

**Step 6.** LogicManager executes the `ViewCommand` by calling its `execute` method.

**Step 7.** `ViewCommand` calls Model to update filtered module list to only contain modules in the specified semester.

#### Design Considerations:

**Aspect: How can the user view modules in specific semester**
- **Alternative 1:** Set 2 buttons in GUI that when clicked, allows user to navigate to the previous semester or next semester and see the modules taken in that semester.
    - Pros:
        - It is very straight forward and intuitive.
        - For beginners and users who are not familiar with CLI, they can view modules taken in different semesters by simply clicking buttons without needing to remember the commands.
    - Cons:
        - If the specific semester is very far away from the current semester, users need to click the button many times.
        - For a user who is comfortable using the command line, the need to use mouse may lower their working efficiency.


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

**Target user profile**:

* NUS CS students
* has a need to manage their Modular Credits (MCs)
* has a need to track the compulsory modules they are required to take
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: keeps track of the number of MCs completed and how many left to complete for the whole course
of study, which is a feature lacking in current tools.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                   | I want to …​                                         | So that I can…​                                                     |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | user                                                         | see usage instructions                                  | refer to instructions when I forget how to use the App                 |
| `* * *`  | CS student                                                   | view the modules for my major                           | keep track of the modules I may take
| `* * *`  | CS student                                                   | mark the module as selected for the current semester    | make a academic plan for the current semester
| `* * *`  | CS student                                                   | view the modules I'm currently taking                   | remember the modules I am currently taking
| `* * *`  | CS student                                                   | delete modules                                          | remove modules that I no longer intend to take
| `* * *`  | CS student                                                   | edit the module details                                 | modify information for any module entry
| `* * *`  | CS student                                                   | add new modules                                         | specify new modules that I intend to take
| `* * *`  | CS student                                                   | view the modules I have taken                           | keep track of the modules that I have taken
| `* * *`  | CS student                                                   | view the modules I plan to take in the future           | be able to plan which modules I will take in advance
| `* * *`  | CS student                                                   | mark a module I have taken as done                      | only choose the modules I have not taken for my future plan
| `* * *`  | CS student who want to find a specific module                | search for a module by keywords/code                    | search for specific modules easily
| `* * *`  | CS student who is looking for a specific kind of modules     | view my module plan for each semester                   | see my plan for each semester easily
| `* * *`  | first time user                                              | see some sample modules when I open the app             | easily try out its features
| `* * *`  | CS student                                                   | view the number of MCs I have taken                     | keep track of my degree progress
| `* * *`  | CS student who is making a module plan                       | specify modules to take for each semester               | make a academic plan for my whole course of study
| `* *  `  | CS student                                                   | tag modules according to type                           | keep track of my modules for each degree requirement (such as GE/UE/Focus Area)
| `* *  `  | CS student                                                   | view the number of MCs left to take before graduating   | determine if I am on track to graduate on time
| `* *  `  | CS student                                                   | view the number of MCs completed for each course requirement   | keep track of my degree progress
| `* *  `  | CS student                                                   | add "DIY" modules                                       | keep track of "DYOM" modules
| `* *  `  | CS student who is looking for modules                        | sort the modules according to the level (1k/2k/3k…)     | know the workload difficulty of the modules
| `* *  `  | CS student                                                   | see which modules I can exercise my S/U option on        | decide how to use my S/Us better
| `* *  `  | CS student                                                   | see how many S/U credits I have left                    | have a better understanding of S/U credit usage
| `* *  `  | CS student                                                   | view my grade for modules I have taken                  | keep track of my grades
| `* *  `  | CS student                                                   | see the marking scheme for CS modules                   | have a full understanding of the module
| `* *  `  | CS student who needs module help                             | see the contact information of professors for the module| easily reach professors when needed
| `* *  `  | CS student who does not like the default font style/size     | change the font style/size                              | have a more comfortable user experience
| `* *  `  | clueless CS student                                          | see the recommended module plan for my major            | make a better plan according to the suggestion
| `*    `  | CS student                                                   | keep track of my CAP                                    | monitor my learning in NUS
| `*    `  | CS student who is making a module plan                       | change the colour scheme of each module                 | easily differentiate between different modules
| `*    `  | CS student who is making a module plan                       | find free slots in my plan                              | add other modules in these free slots
| `*    `  | forgetful CS student                                         | get notified when it's time for class                   | attend the classes on time
| `*    `  | forgetful CS student                                         | get notified when exam dates are approaching            | be aware of important assessments



### Use cases

(For all use cases below, the **System** is `NUS Mod Tracker` and the **Actor** is the `user`, unless specified otherwise)

#### Database Features
**UC1: Add a Module to the Database**

**MSS**

1. User requests to add a module.
2. NUS Mod Tracker adds the module.

   Use case ends.

**Extensions**

* 1a. The given code argument is invalid.
    * 1a1. NUS Mod Tracker shows an error message.

      Use case resumes at step 1.

* 2a. The given module already exists in the database.
    * 2a1. NUS Mod Tracker shows an error message.

      Use case resumes at step 1.

**UC2: Delete a Module from the Database**

**MSS**

1. User requests to delete a specific module in the list of modules in the database.
2. NUS Mod Tracker deletes the module from the database.

    Use case ends.

**Extensions**

* 1a. The given module index is invalid.
    * 1a1. NUS Mod Tracker shows an error message.

      Use case resumes at step 1.

**UC3: List all modules in the Database**

**MSS**

1. User requests to list all modules in the database.
2. NUS Mod Tracker show a list of all modules in the database.

**UC4: Edit a module**

**MSS**

1. User requests to edit a specific module in the database.
2. NUS Mod Tracker edits the module.

    Use case ends.

**Extension**

* 1a. The given module index or arguments are invalid.
    * 1a1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.
* 1b. The user failed to provide any mandatory details to be edited.
    * 1b1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.
* 1c. The new value that is given to the code field is already exists in the database.
    * 1c1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.

**UC5: Find a module**

**MSS**

1. User requests to find a specific module in the database.
2. NUS Mod Tracker find the module.
2. NUS Mod Tracker show the module to the user.

   Use case ends.

**Extension**

* 1a. The given parameters are invalid.
    * 1a1. NUS Mod Tracker shows an error message.

  Use case resumes at step 1.

#### Academic Plan Features

**UC6: Add a Module to the Academic Plan**

**MSS**

1. User requests to add a specific module to the academic plan.
2. NUS Mod Tracker adds the module to the academic plan under the specified year and semester.

    Use case ends.

**Extensions**
* 1a. The given module index or arguments are invalid.
  * 1a1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.

**UC7: Remove a Module from the Academic Plan**

**MSS**

1. User requests to remove a specific module from the academic plan.
2. NUS Mod Tracker removes the module from the academic plan.

   Use case ends.

**Extensions**
* 1a. The given module index is invalid.
  * 1a1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.
* 1b. The specified module is not in the academic plan.
  * 1b1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.

**UC8: Change current semester**

**MSS**

1.  User requests to change current semester.
2.  Module tracker change the current semester.

    Use case ends.

**Extensions**
* 1a. User does not specify academic year or semester in the request.
    * 1a1. Module Tracker shows an error message.

    Use case resumes at step 1.


* 1b. The academic year and semester user request to change to is invalid.
    * 1b1. Module Tracker shows an error message.

    Use case resumes at step 1.

* a. At any time, User requests to view help(UCxx)


**UC9: Set Mc goal**

**MSS**

1.  User requests to set Mc goal.
2.  Module tracker change Mc goal.

    Use case ends.

**Extensions**
* 1a. User does not specify the number of MC in the request.
    * 1a1. Module Tracker shows an error message.

  Use case resumes at step 1.


* 1b. The MC user request to set is invalid.
    * 1b1. Module Tracker shows an error message.

  Use case resumes at step 1.

* a. At any time, User requests to view help(UCxx)


**UC10: View modules taken in specific semester**

**MSS**

1.  User requests view modules selected in specific semester.
2.  Module tracker shows the modules selected in specific semester.

    Use case ends.

**Extensions**
* 1a. User does not specify the number the specific academic year and semester in the request.
    * 1a1. Module Tracker shows an error message.

* a. At any time, User requests to view help(UCxx)


**UC11: Remove all modules in a specific semester from the academic plan**

**MSS**
1. User requests to remove modules in a specific semester from the academic plan.
2. NUS Mod Tracker removes all modules in that semester from the academic plan.

    Use case ends.

**Extension**

* 1a. The given semester is invalid.
    * 1a1. NUS Mod Tracker shows an error message.

    Use case resumes at step 1.

**UC12: Viewing help**

**MSS**
1. User requests for help.
2. NUS Mod Tracker shows a summary of commands and a link to user guide.

   Use case ends.

### Non-Functional Requirements

* Technical requirement:
  * NUS Mod Tracker Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
  * NUS Mod Tracker should work under common screen resolutions.
  * NUS Mod Tracker Should work properly no matter where the application is stored.

* Performance requirements:
  * NUS Mod Tracker Should be able to hold up to 500 modules without a noticeable sluggishness in performance for typical usage.

* Quality requirement
  * A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
  * New users can have a grasp of the core features quickly with the help of user guide(link).
* Constraints
  * The product is not required to handle the module timetable planning and module bidding process.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **GUI**: Graphical user interface
* **ClI**: Command line interface
* **Common screen resolutions**: minimum _1024x786_, maximum _3840x2160_
* **MSS**: Main Success Scenario
* **Academic Calendar**: A generalization of academic year and semester

--------------------------------------------------------------------------------------------------------------------

## **Appendix B: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample modules. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a module to the database

1. Test case: `add c/ST2131 t/Probability d/The objective of this module is to give an elementary introduction to probability theory m/4 tag/UE`
   Expected: A new module is added to the bottom of the list. Details of the added module are shown in the status message.

2. Test case: `add c/ t/software engineering d/introduces software engineering m/4`
   Expected: No module is added. Error details are shown in the status message.

3. Other incorrect add commands to try: `add `,`add c/ST21312132 t/abcd d/efgh m/4`, `...`
   Expected: Similar to previous.

### Deleting a module

1. Deleting a module while all modules are being shown

   1. Prerequisites: List all modules using the `list` command. Multiple modules in the list.

   2. Test case: `delete 1`<br>
      Expected: First module is deleted from the list. Details of the deleted module are shown in the status message.

   3. Test case: `delete 0`<br>
      Expected: No module is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Editing a module

1. Editing a module while all modules are being shown
    1. Test case: `edit 1 c/MA1100`<br>
       Expected: The first module's code is changed to `MA1100`. Details of the edited module are shown in the status message.

    2. Test case: `edit 1`<br>
       Expected: No module is edited. Error details shown in the status message. Status bar remains the same.

    3. Other incorrect delete commands to try: `edit`, `edit -1 c/MA1100`, `edit x c/MA1100`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Listing all modules

1. Listing all modules in NUS Mod Tracker.

    1. Test case: `list`<br>
       Expected: All modules are shown.

    2. Test case: `list 1111`<br>
       Expected: Similar to the previous.

### Finding modules

1. Finding all modules with the specified keywords.

    1. Test case: `find CS2103T`<br>
       Expected: The modules that have `CS2103T` string in module code, module title, description, and tags are found.

### Adding a module to the academic plan

1. Test case: `take 1 y/2 s/1`<br>
       Expected: A yellow label with the string "year2 sem1" is added to the first module.

2. Test case: `take 1`<br>
        Expected: The module is not taken. Error details shown in the status message.

### Removing a module from the academic plan
N
1. Prerequisites: The specified module has been taken.

2. Test case: `untake 1`<br>
       Expected: The first module is removed from the academic plan. The color of this module is changed to grey.

### Changing settings of MC goal and current semester

1. Resetting MC goal.

    1. Test case: `set m/200`<br>
       Expected: The MC goal is set to 200. It is reflected in the footer.

2. Resetting current semester.

    1. Test case: `set y/2 s/1`<br>
        Expected: The current semester is set to year 2, semester 1. The new current semester information is reflected in the footer.

### Viewing modules scheduled in a specific semester

1. Test case: `view y/2 s/1`<br>
       Expected: Modules scheduled in year 2, semester 1 are shown.

2. Test case: `view y/10 s/2`<br>
        Expected: Error message is shown.

### Unscheduling all modules in a specific semester

1. Prerequisite: Some modules are scheduled in the specified semester

2. Test case: `clear y/2 s/1`<br>
   Expected: All modules taken in year 2, semester 1 are unscheduled and the color of the module is changed to grey.

### Viewing help

1. Test case: `help`<br>
   Expected: A help window with a link to the user guide and command formats are shown.

### Exit

1. Test case: `exit`<br>
   Expected: The application is terminated.



## **Appendix C: Effort**

If the effort required to create AB3 is 100, we would place the effort required to implement the current version of NUS Mod Tracker at 120. Our team has contributed over 10,000 lines of code contributed and over 300 automated tests. Below are some changes in particular, that required a larger amount of effort to implement.

1. Modifying all AB3 components

   We needed to modify the entire AB3 in order to support operations on modules.

   In the `Model` component, we needed to update all models in the `person` folder with a new folder that contains the model for module, as well as all other attributes related to a module (`Code`, `Title`, `Description`, `Mc`, ).

   All other classes in `model` folder had to be modified accordingly.

   In the `Logic` component, we need to modify all command parsers in order to parse modules. All commands also needed to be modified to support the operations on modules.

   In the `Storage` component, we also needed to modify all classes in order to store modules.

   The `UI` component also needed to be modified to display all the attributes of a module clearly,
   and the test cases changed accordingly.


2. Mc progress panel

    In order to allow the user to easily track their course progress, our team had to add a component that allows the user to view the number of MCs completed in total, as well as for each course requirement.

    This involved creating a brand-new UI component from scratch that would display this information to the user.

    Firstly, we altered the application's UI to accommodate the new Mc progress panel.

    As many commands can cause the user's MC progress to change (i.e. setting MC goal, setting the current semester, taking new modules), we had to create a new model for a `McProgress`, as well as a `McProgressList`. These components are then updated accordingly after execution of a command.

    We then link the `McProgressList` to the UI component so that any changes to the `McProgress` will be detected by the UI, and will allow the UI to update accordingly.
