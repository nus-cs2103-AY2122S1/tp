---
layout: page
title: Developer Guide
nav-text: Developer Guide
---

<p align="center">
  <img src="https://i.imgur.com/dLt7IDY.png" />
</p>

<p align="center">
  <img src="https://i.imgur.com/FKI1H1U.png" width="300"/>
</p>

# CLITutors Developer Guide (v1.4)

<div style="page-break-after: always;"></div>

## Table of Contents
{: .no_toc}
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

`CliTutors` is a desktop application for managers to manage private tutoring jobs, optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). CLITutors is also highly optimized for fast typists and can be fully operated through keyboard commands.

This developer guide is for users who would like to extend and modify the application. For certain terms that are unique to `CliTutors`, a [glossary](#glossary) has been provided for readers.

Readers are also advised to [download](https://github.com/AY2122S1-CS2103T-T17-2/tp/releases) our latest release from our main website in order to test out the application.

--------------------------------------------------------------------------------------------------------------------
## **Navigating this Developer Guide**
Before diving into the rest of the contents in our developer guide, the following are a few important syntaxes to take note of to facilitate your reading:

| Syntax                                                                  | Description                                         |
| ----------------------------------------------------------------------- | --------------------------------------------------- |
| `Codeblock`                                                              | Denotes distinct classes, their methods or examples |
| <div markdown="span" class="alert alert-info">:information_source: Note</div> | Important things to take note of                    |
| *Italics* | Terms to be defined in the [glossary](#glossary)                   |


<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
## **Design**

<div markdown="span" class="alert alert-info"> :information_source: * **Note:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-T17-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.<br>
 * **Note:** The lifelines for `Parser` and `Command` classes in the sequence diagrams should end at the destroy marker (X) but due to a limitation of PlantUML, the lifelines reach the end of diagram.
</div>

### Architecture

![Architecture Diagram](images/ArchitectureDiagram.png)

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of the main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for:
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`Ui`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from and writes data to the hard disk.

Each of the four components:

* defines its API in an interface with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API interface mentioned in the previous point).

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic.java` interface.

![LogicClassDiagram](images/LogicClassDiagram.png)

<div style="page-break-after: always;"></div>

**How the architecture components interact with each other**

The Sequence Diagram below shows how the components interact with each other for the scenario where the user issues the command `delete s 1`.

![Architecture Sequence Diagram](images/ArchitectureSequenceDiagram.png)

The sections on the next few pages will give more details about each component.

<div style="page-break-after: always;"></div>

### UI component
**API** : [`Ui.java`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

Here's a class diagram of the `Ui` component:

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts like `CommandBox`, `ResultDisplay`, `PersonListPanel`, `HelpWindow`.
All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities
between classes that represent parts of the visible GUI.
The `PersonListPanel` is split up into `TutorCard` and `StudentCard` on the UI as 2 lists: Tutor List, Student List. The `MatchListPanel` contains only `TutorCard`.

The `Ui` component uses the JavaFx UI framework. The layout of these UI parts is defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `Ui` component:

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `Ui` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

![Logic Class Diagram](images/LogicClassDiagram.png)

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CliTutorsParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete t 1")` API call.

![DeleteCommandSequenceDiagram](images/DeleteCommandSequenceDiagram.png)

Taking a deeper look into the classes in `Logic` that are used for parsing a user command:

![Insert class diagram for Parser component](images/ParserClasses.png)

How the parsing works:
* When called upon to parse a user command, the `CliTutorsParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g. `AddCommandParser`) which uses the other classes shown above to parse the user command and create an `XYZCommand` object (e.g. `AddCommand`) which the `CliTutorsParser` returns as a `Command` object.
* All `XYZCommandParser` classes (e.g. `AddCommandParser`, `DeleteCommandParser`, ...) inherit the `Parser` interface so that they can be treated similarly where possible e.g. during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

Here's a (partial) class diagram of the `Model` component:

![ModelClassDiagram](images/ModelClassDiagram.png)

The `Model` component:

* stores the CLITutors data i.e. all `Tutor` and `Student` objects (which are contained in a `UniqueTutorList` and `UniqueStudentList` object respectively).
* stores the currently displayed `Tutor` and `Student` objects (e.g. results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Tutor>` and `ObservableList<Student>` respectively that can be observed e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to other classes as a `ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `CliTutors`, which `Tutor` and `Student` references. This allows `CliTutors` to only require one `Tag` object per unique tag, instead of each `Tutor` and `Student` needing their own `Tag` objects.<br>

![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

### Storage component
**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

Here's a class diagram of the `Storage` component:

![Storage component diagram](images/StorageClassDiagram.png)

The `Storage` component:
* can save both CLITutors data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `CliTutorsStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------
## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add feature

#### What it is

Adds a tutor/student to the Tutor/Student List respectively. The `add` command must have all the fields required for tutor/student.

#### Implementation details

##### Sequence of action
{:.no_toc}

Given below is an example usage scenario and how the `add` command implementation behaves at each step:

1. The user input `add t n/Jonathan Chan p/92102339 g/M q/2 t/PM SM` is passed to `LogicManager` to be executed.
2. `LogicManager` calls on `CliTutorsParser#parseCommand` method which in turn creates a new `AddCommandParser`.
3. The `AddCommandParser#parse` method is then called which will return a new `AddCommand` if the input is valid.
4. `AddCommand` will then update the Tutor List using the `Model#addTutor` method.
5. Lastly, a new `CommandResult` with the success message is returned to the `LogicManager`.

Given below is a sequence diagram to show how the `add` implementation works:

The string `t n/Johnathan Chan p/92102339 g/M q/2 t/PM SM` is represented by a variable `John` in the sequence diagram to prevent clutter.

![AddCommandSequenceDiagram](images/AddCommandSequenceDiagram.png)

Given below is an activity diagram to show how the `add` command works for a tutor:

<p align="center">
  <img src="images/AddCommandActivityDiagram.png" />
</p>

#### Design Considerations:

##### Aspect: How `add` executes
{:.no_toc}

- **Alternative 1 (current choice)**: User must `add` using all the required prefixes.
    - Pros: It prevents the potential matching of tutors and students to have missing fields that are required when matching.
    - Cons: It is troublesome to type everything in at once.
- **Alternative 2**: User can add the tutor/student in multiple increments.
    - Pros: Simpler for the user to add details to the tutor or student.
    - Cons: Will cause many potential errors if there are missing fields required when matching them.

### Delete feature
#### What it is
Deletes the tutor or student at the specified `INDEX` from the displayed Tutor/Student List.

#### Implementation details
Similar to the `AddCommand` class above, the `DeleteCommand` class extends the `Command` class. The actual execution of the `delete` command is similar to that of the `add` command, except the `delete` command decides on the tutor/student to delete based on the `INDEX` of the tutor/student displayed on the `filteredTutors` list or the `filteredStudents` list of the `ModelManager` class.

##### Sequence of action
{:.no_toc}

Given below is an example usage scenario and how the `delete` command implementation behaves at each step:

1. The user input `delete t 1` is passed to `LogicManager` to be executed.
2. The `LogicManager` calls `CliTutorsParser#parseCommand` to parse the given user input.
3. The `CliTutorsParser` identifies the `delete` command and creates a new `DeleteCommandParser` object. It then calls `DeleteCommandParser#parse` with the command arguments as the parameter.
4. `DeleteCommandParser` then generates a `DeleteCommand` object with the `INDEX` (of the tutor/student to be deleted) and `PersonType` as parameters.
5. As the `PersonType` is a tutor, `DeleteCommand` retrieves the `Tutor` (to be deleted) from the `filteredTutors` list of the `ModelManager`. `DeleteCommand` will then call `Model#deleteTutor`, which will delete the tutor from the Tutor List.
6. Lastly, a new `CommandResult` with the success message is returned to the `LogicManager`.

Given below is a sequence diagram to show how the `delete` implementation works for a **valid** `delete` tutor input:

![DeleteCommandSequenceDiagram](images/DeleteCommandSequenceDiagram.png)

Given below is an activity diagram to show how the `delete` command works for a `delete` tutor input:

<p align="center">
  <img src="images/DeleteCommandActivityDiagram.png" />
</p>

#### Design Considerations
##### Aspect: How `delete` is executed
{:.no_toc}

- **Alternative 1 (current choice)**: User can `delete` only one tutor/student at a time.
    - Pros: Reduces the lack of potential errors due to the decreased complexity of the code. It is also easier to implement since there are lesser use cases to consider.
    - Cons: It is more troublesome for the user if they want to delete a range of tutors/students as they are now limited by having to `delete` each tutor/student one by one.

<div markdown="span" class="alert alert-info"> :information_source: **Note:** In our current implementation, users can delete all tutors/students from their respective lists using the `clear` command. This is to make the app more intuitive for users with clear goals on the command they want to execute while reducing any unnecessary complexity in our `delete` command.
</div>

- **Alternative 2**: User can `delete` multiple tutors/students using the command at the same time.
    - Pros: Allows the user more flexibility in deleting tutors/students instead of just deleting them one by one.
      For example, users can choose to `delete` one or many tutors/students, depending on their command input.
    - Cons: This will cause the code to become more complex in dealing with many possible inputs and scenarios, leading to a higher amount of potential errors.

### Edit feature
#### What it is
Edits a tutor/student in the Tutor/Student List respectively. All fields are **optional** in the `edit` command, but **at least one field** must be specified for the command to execute successfully.

#### Implementation details
##### Sequence of action
{: .no_toc}

Given below is an example usage scenario and how the `edit` command implementation behaves at each step:

1. The user input `edit t 1 p/94203825` is passed to `LogicManager` to be executed.
2. `LogicManager` calls `CliTutorsParser#parseCommand` which in turn creates a new `EditCommandParser`.
3. The `EditCommandParser` calls its own `parse()` method which will return a new `EditCommand` if the input is valid.
4. `EditCommand` will then update the Tutor List in the `Model` class by replacing the existing tutor (to be edited) with the edited tutor.
5. Lastly, a new `CommandResult` with the success message is returned to the `LogicManager`.

Given below is a sequence diagram to show how the `edit` implementation works for a **valid** `edit` tutor input:

![EditCommandSequenceDiagram](images/EditCommandSequenceDiagram.png)

Given below is an activity diagram to show how the `edit` command works on a tutor:

<p align="center">
  <img src="images/EditCommandActivityDiagram.png" />
</p>

#### Design Considerations
##### Aspect: How `edit` executes
{: .no_toc}

- **Alternative 1 (current choice)**: `edit` is handled separately for tutors and students via `EditTutorDescriptor` and `EditStudentDescriptor` respectively.
    - Pros: It allows the user to edit the details of tutors/students even if they have different fields.
    - Cons: `EditCommandParser` would be more complex since it has to handle edits for tutors and students separately due to differing requirements. The higher complexity may lead to a higher chance of creating bugs.
- **Alternative 2**: `edit` is handled using `EditPersonDescriptor` for both tutors and students.
    - Pros: Simpler to implement and maintain since changes to `EditPersonDescriptor` will be propagated to both tutors and students when editing them.
    - Cons: Prevents custom fields that a `Tutor` should have but a `Student` should not, or vice-versa. A `Tutor` and `Student` must have the same attributes for this implementation to work, and is hence not extensible should `Tutor` and `Student` be required to contain a different set of attributes.

### List feature
#### What it is

Lists all tutors/students in the list. The `list` command only accepts `s` or `t` as a preamble.
#### Implementation details

##### Sequence of action
{:.no_toc}

Given below is an example usage scenario and how the `list` command implementation behaves at each step:

1. The user input `list s` is passed to LogicManager to be executed.
2. `LogicManager` calls the `CliTutorsParser#parseCommand` method which in turn creates a new `ListCommandParser`.
3. The `ListCommandParser#parse` method is then called which will return a new `ListCommand` if the input is valid.
4. `ListCommand` will then show all students in the Student List in the `Model` class.
5. Lastly, a new `CommandResult` with the success message is returned to the `LogicManager`.

Given below is a sequence diagram to show how the `list` implementation works:

![ListCommandSequenceDiagram](images/ListCommandSequenceDiagram.png)

Given below is an activity diagram to show how the `list s` command works:

<p align="center">
  <img src="images/ListCommandActivityDiagram.png" />
</p>

#### Design Considerations:

##### Aspect: How `list` executes
{:.no_toc}

- **Alternative 1 (current choice)**: User must specify which list that they would like to see.
    - Pros: It allows the user to act on each list independently.
    - Cons: It is troublesome to type the command twice if the user wanted to view all the tutors and students.
- **Alternative 2**: User inputs `list` and all tutors and students are shown.
    - Pros: Simpler for the user to list all tutors and students.
    - Cons: Will remove all filters applied to both the tutor and Student Lists and show all tutors and students. The user is unable to customise the view of Tutor/Student Lists independently.

### Find feature

#### What it is

Searches and displays all tutors/students that matches the parameters specified. The `find` command must have **at least one valid parameter** (i.e. `find s NOT_A_PARAMETER` will **not** work).

#### Implementation details

A noteworthy change in the implementation of our `FindCommand` from the standard AB3 `FindCommand` is in `FindCommandParser`. We expanded on the `FindCommand` functionality such that it can find multiple attributes for tutors/students. Because of this, we opted to create a new `ChainedPredicate` class that encapsulates the idea of chaining multiple predicates together. Each attribute specified by the user creates its own predicate which is then chained together using the `Predicate#and` method and the resulting predicate is then stored in the `ChainedPredicate` class.

##### Sequence of action
{:.no_toc}

Given below is an example usage scenario and how the `find` command implementation behaves at each step:

1. The user input `find s n/David` is passed to `LogicManager` to be executed.
2. `LogicManager` calls on `CliTutorsParser#parseCommand` method which creates a new `FindCommandParser`.
3. The `FindCommandParser` then calls its own `parse()` method which will return a new `FindCommand` if the input is valid.
4. `LogicManager` will execute the `FindCommand` through `FindCommand#execute`.
5. `FindCommand` will then search and update the Student List in the `Model` class using the `Model#updateFilteredStudentList` method.
6. Lastly, a new `CommandResult` with the success message is returned to the `LogicManager`.

Given below is a sequence diagram to show how a **valid** `find` implementation works for a `find` student input:

![FindCommandSequenceDiagram](images/FindCommandSequenceDiagram.png)

Given below is an activity diagram to show how a `find` implementation works for a `find` student input:

<p align="center">
  <img src="images/FindCommandActivityDiagram.png" />
</p>

#### Design Considerations

##### Aspect: How `find` is executed
{:.no_toc}

- **Alternative 1 (current choice)**: User can `find` using multiple prefixes at once.
  - Pros: It allows the user to find tutors/students more easily by their fields.
 <br>(e.g. they can find all tutors who are women and teaches Primary Math)
  - Cons: It would be more complex since it has to parse multiple prefixes and chain predicates together depending on the user input. The higher complexity may lead to a higher chance of creating bugs.
- **Alternative 2**: User can only `find` using one prefix at a time.
  - Pros: Simpler to implement since there are lesser use cases to consider and hence, making the code less prone to bugs.
  - Cons: Significant impact on the overall user experience since finding a person with only one prefix may generate a large list if there are many tutors/students stored. user may not be able to find what he/she specifically wants.

### Match feature

#### What it is

The Match feature involves taking a student specified by the user and matching the student with tutors who have **one or more** matching tags with the identified student. It automatically sorts the `matchedTutorList` according to the number of matching tags that a tutor has with the student.

#### Implementation details
Upon the user's entry of the command, the validity of the user's input is checked. If the input is valid, a `MatchCommand` object is created. `MatchCommand` is a class that extends the `Command` abstract class, with `MatchCommand` implementing the `execute()` method. Upon execution, the student is identified via the `INDEX` given by the user and a `TagsContainTagPredicate` object is created, which will be used to determine if the `tags` of the tutor contains one or more `Tag` that the student has. It will then update and sort the `matchedTutorList` such that the tutor with more matching `Tag` is at the front of the list.

##### Sequence of action
{:.no_toc}
Given below is an example valid usage scenario and how the `match` command implementation behaves at each step.

**Prerequisite**: There are students in the Student List and there are tutors which have `tags` that the student has (i.e. there are matches available for the student).

Steps:
1. The user input `match 1` (i.e. match the first student in the Student List) is passed to `LogicManager` to be executed.
2. `LogicManager` calls on `CliTutorsParser#parseCommand`, which creates a new `MatchCommandParser` object.
3. The `MatchCommandParser` object calls on its own `parse()` method which will validate the user input and return a new `MatchCommand`.
4. `LogicManager` will execute the `MatchCommand` through `MatchCommand#execute`, which will be responsible for matching the `Student`.
5. `MatchCommand` will find the first student in the Student List and a `TagsContainTagPredicate` object is created.
6. The `matchTutorList` in the `Model` will then be updated via `Model#updateMatchedTutor`.
7. Lastly, a new `CommandResult` with the success message is returned to the `LogicManager`.

Given below is a sequence diagram to show how the `match` implementation works for a **valid** `match` input:

![MatchCommandSequenceDiagram](images/MatchCommandSequenceDiagram.png)

Given below is an activity diagram to show how the `match` command works for a `match` input:

<p align="center">
  <img src="images/MatchCommandActivityDiagram.png" />
</p>

#### Design Considerations

##### Aspect: How `match` is executed
{:.no_toc}
- **Alternative 1 (current choice)**: User can `match` students with multiple `tags` to tutors.
    - Pros: It prevents the user from having to create one `Student` object for every subject that the student is looking for, which allows the database to store lesser unnecessary/duplicate information about the student, making it less prone to bugs.
    - Cons: The code will become more complex as there is a need to create a `TagsContainTagPredicate` to assist in finding all the relevant matching tutors.
- **Alternative 2**: User can `match` students with only one `Tag` to tutors.
    - Pros: Simpler implementation which requires a less complex predicate, making it easier for developers to understand the code.
    - Cons: Higher storage cost due to more `Student` objects being created if a specific student wants to learn multiple subjects as students are only limited to one `Tag`.

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

#### Target user profile

* Needs to manage a significant number of private tutors and students
* Likes a lightweight tutor management application to reduce *bloatware*
* Wants to have a platform to match students to private tutors
* Prefers desktop app over other platforms
* Can type fast
* Prefers typing to mouse interactions
* Is comfortable with using a CLI

#### Value Proposition

Manage the matching of tutors and students for teaching jobs more efficiently than a regular GUI-driven application.

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ...                                       | I want to ...                                         | So that I can …                                                   |
| -------- | ---------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------------------- |
| `* * *`  | new user ready to adopt the app for my own use | purge all data                                        | clear all dummy data and begin to use the app                     |
| `* * *`  | user                                           | delete students and tutors from the list              | remove those people who do not use the service anymore            |
| `* * *`  | user                                           | add new students and tutors to the app                |                                                                   |
| `* * *`  | user                                           | view my saved data                                    | recall the information of my tutors/students                      |
| `* * *`  | user                                           | find people that match some keywords                  | avoid scrolling through thousands of entries of data              |
| `* * *`  | user                                           | assign tags to tutors and students                    | categorize them by their subjects                                    |
| `* * *`  | user                                           | match students and tutors according to their subjects    |                                                                   |
| `* * *`  | user                                           | get feedback from the system                          | see if my commands are working                                    |
| `* * *`  | user                                           | type the commands I want to execute                   |                                                                   |
| `* * *`  | busy user                                      | use shorter commands                                  | save time keying in full commands                                 |
| `* *`    | first-time user                                | see some sample data when I open the app              | easily try out the features without needing to add my data first  |
| `* *`    | user                                           | edit personal details                                 | update any new particulars of the tutors/students                 |
| `* *`    | user                                           | sort by tags                                          | easily find groups of people with the same tags                   |
| `* *`    | forgetful user                                 | see the available commands for the program            | know what command exists                         |
| `* *`    | user                                           | filter from the list of matched tutors | find more suitable tutors for the student based on their requirements            |
| `* *`    | user                                           | sort the list of matched tutors by number of matched tags | see the most suitable tutors at the top of the list           |
| `*`    | user                                           | toggle the app's theme | customize the app           |

### Use Cases

(For all use cases below, the System is `CliTutors` and the Actor is the user, unless otherwise specified)

#### Use case (UC01): Add a Tutor
{:.no_toc}

**MSS**

1. User keys in the tutor's details.
2. Tutor is added to the database.
   <br>Use case ends.

**Extensions**

* 1a. User keys in an incorrect format.
    * 1a1. System displays an error message to tell the user about the format error.
      <br>Use case ends.

#### Use case (UC02): Add a Student
{:.no_toc}

**MSS**

1. User keys in the student's details.
2. Student is added to the database.
   <br>Use case ends.

**Extensions**

* 1a. User keys in an incorrect format.
    * 1a1. System displays an error message to tell the user about the format error.
      <br>Use case ends.

#### Use case (UC03): Edit a Student name
{:.no_toc}

**MSS**

1. User enters the command to edit the name of Student A.
2. System replies with a confirmation message that the edit is successful.
   <br>Use case ends.

**Extensions**

* 1a. User keys in an incorrect prefix for editing name.
    * 1a1. System displays an error message to tell the user about the format error.
      <br>Use case ends.

#### Use case (UC04): Edit a Student phone number
{:.no_toc}

**MSS**

1. User enters the command to edit the phone number of Student B.
2. System replies with a confirmation message that the edit is successful.
   <br>Use case ends.

**Extensions**

* 1a. User keys in an incorrect prefix for editing the phone number.
    * 1a1. System displays an error message to tell the user about the format error.
      <br>Use case ends.
* 1b. User types in an existing phone number.
  * System displays an error message to tell the user about the duplicate.
    <br>Use case ends.

#### Use case (UC05): Delete a Tutor
{:.no_toc}

**MSS**

1. User requests to list tutors.
2. `CliTutors` shows a list of tutors.
3. User requests to delete a specific tutor in the list.
4. `CliTutors` deletes the tutor.
   <br>Use case ends.

**Extensions**

* 1a. The list is empty.
  <br>Use case ends.
* 3a. The given index is invalid.
    * 3a1. `CliTutors` shows an error message.
    * 3a2. User enters in a valid index.
      <br>Use case resumes at step 4.

#### Use case (UC06): Delete a Student
{:.no_toc}

**MSS**

1. User requests to list students.
2. `CliTutors` shows a list of students.
3. User requests to delete a specific student from the list.
4. `CliTutors` deletes the student.
   <br>Use case ends.

**Extensions**

* 1a. The list is empty.
  <br>Use case ends.
* 3a. The given index is invalid.
    * 3a1. `CliTutors` shows an error message.
    * 3a2. User enters in a valid index.
      <br>Use case resumes at step 4.

#### Use case (UC07): Find a Tutor
{:.no_toc}

**MSS**

1. User requests to find a tutor using his/her name.
2. `CliTutors` shows all tutors that contain that specific name.
   <br>Use case ends.

**Extensions**

* 1a. The list is empty.
  * 1a1. `CliTutors` shows an error message.
    <br>Use case ends.
* 1b. There is no one with that specific name.
  <br>Use case ends.

#### Use case (UC08): Find a Student
{:.no_toc}

**MSS**

1. User requests to find a student using his/her name.
2. `CliTutors` shows all students that contain that specific name.
   <br>Use case ends.

**Extensions**

* 1a. The list is empty.
  <br>Use case ends.
* 1b. There is no one with that specific name.
  <br>Use case ends.

#### Use case (UC09): Match a student with the tutors
{:.no_toc}

**MSS**

1. User requests to match a student with tutors with the required qualifications.
2. `CliTutors` shows the tutors that match with the specified student in a window.
   <br>Use case ends.

**Extensions**

* 1a. There are no tutors that match the requirements of the student.
  <br>Use case ends.

#### Use case (UC10): Filter tutors after matching
{:.no_toc}

**MSS**

1. User requests to filter tutors after matching a student.
2. `CliTutors` shows the tutors that match the required parameters.
   <br>Use case ends.

**Extensions**

* 1a. No match command has been called prior to the filter command.
  * 1a1. `CliTutors` shows an error message prompting the user to input a match command.
  <br>Use case ends.

### Non-Functional Requirements

1. Should work on any *mainstream OS* as long as it has Java 11 or above installed.
2. Should be able to run on both 32-bit and 64-bit systems.
3. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
4. Should not require the use of remote databases.
5. System should be able to run even if the data file has errors arising from a user manually editing it.
6. Response to user commands (add, delete, edit, match) should be visible within 2 seconds.
7. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
8. The system should be usable by a novice who may not be familiar with CLI. i.e. Users are not expected to key in complicated commands to get desired outputs.
9. Product should not be able to send out any information to the student.
10. Product should not be able to verify the legitimacy of the information of the student or private tutor.

### Glossary

| Term              | Meaning                                                                                                                                           |
|:----------------- |:------------------------------------------------------------------------------------------------------------------------------------------------- |
| **CLI**           | Command-Line Interface                                                                                                                            |
| **JSON**          | JSON stands for ***JavaScript Object Notation*** which is a lightweight format for data storage                                                   |
| **Mainstream OS** | Windows, macOS, Linux                                                                                                                             |
| **Index**         | Index number that is shown in the displayed list. The index must be a positive integer 1, 2, 3, …                                                         |
| **Qualification** | How qualified the tutor is with regards to these levels:<br>0.Pre-University<br>1.University Student<br>2.Graduate<br>3.MOE-Trained              |
| **Tag**           | Subjects each Tutor teaches are saved under [tags](https://ay2122s1-cs2103t-t17-2.github.io/tp/UserGuide.html#valid-tags) as ``[X][Y]`` (X is Level code and Y is Specific Subject code). eg. `PM` stands for Primary Math.|
| **Bloatware**     | Software that uses excessive memory and disk space, which makes the program run slow                                                              |

--------------------------------------------------------------------------------------------------------------------
## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br> * These instructions only provide a starting point for testers to work on, testers are expected to do more exploratory testing.
 * All tutors and students used in the test cases below are based on the sample data given when the application is first launched.
 * Please reload the sample data after testing each section below.
</div>

### Launch and shutdown
{:.no_toc}

1. Initial launch
    1. Download the JAR file and copy it into an empty folder.
    1. Double-click the JAR file <br>Expected: Shows the GUI with a set of sample data.
1. Exiting the app
    1. With the app still open, enter `exit` in the command box or click on the close window button.<br>
       Expected: App closes.

### Add a Tutor/Student
{:.no_toc}

1. Add a tutor to the Tutor List.
   1. Test case: `add t n/John Doe p/98265432 g/M q/2 r/Prefers teaching in the West t/PM`<br>
      Expected: The tutor John Doe is added to the Tutor List. Details of the added tutor are shown in the result display.
   2. Test case: `add t n/John Doe p/98765432 g/M q/2 r/Prefers teaching in the West`<br>
      Expected: No tutor is added. Error details are shown in the result display, with a result message `Invalid command format! ...`.
   3. Test case: `add t n/李白 p/98765432 g/M q/2 r/Prefers teaching in the West t/PM`<br>
      Expected: No tutor is added. Error details are shown in the result display, with a result message `Names should only contain alphanumeric characters and spaces, and it should not be blank`.
   4. Test case: `add t n/John Doe p/98765432 g/M q/2 r/Prefers teaching in the West t/GH`<br>
      Expected: No tutor is added. Error details are shown in the result display, with a result message `Please enter valid tag(s)`.
   5. Test case: `add t n/John Doe p/98765432 g/M q/5 r/Prefers teaching in the West t/PM`<br>
      Expected: No tutor is added. Error details are shown in the result display, with a result message `Qualification should be '0', '1', '2' or '3'`.

2. Add a student to the Student List.
    1. Test case: `add s n/John Doe p/98765132 g/M t/PM`<br>
       Expected: The student John Doe is added to the Student List. Details of the added student are shown in the result display.
    2. Test case: `add s n/John Doe p/98765432 g/M q/2 t/PM`<br>
       Expected: No student is added. Error details are shown in the result display, with a result message `Students do not have any qualifications tagged to them!`.
    3. Test case: `add s n/李白 p/98765432 g/M t/PM`<br>
       Expected: No student is added. Error details are shown in the result display, with a result message `Names should only contain alphanumeric characters and spaces, and it should not be blank`.
    4. Test case: `add s n/John Doe p/98765432 g/M t/GH`<br>
       Expected: No student is added. Error details are shown in the result display, with a result message `Please enter valid tag(s)`.
    5. Test case: `add s n/John Doe p/98765432 g/MF t/PM`<br>
       Expected: No student is added. Error details are shown in the result display, with a result message `Gender should be 'M' / 'm' or 'F' / 'f'`.

### Edit Tutor/Student List
{:.no_toc}

1. Edit a tutor in the Tutor List.
    1. Test case: `edit t 1 n/John Doe`<br>
       Expected: The first tutor Alex Yeoh is renamed to John Doe. Details of the edited tutor are shown in the result display.
    2. Test case: `edit t 1`<br>
       Expected: No tutor is edited. Error details are shown in the result display, with a result message `At least one field to edit must be provided`.
    3. Test case: `edit t 1 g/Z`<br>
       Expected: No tutor is edited. Error details are shown in the result display, with a result message `Gender should be 'M' / 'm' or 'F' / 'f'`.
    4. Prerequisite: All tutors in the Tutor List are the default values loaded from `SampleDataUtil`<br>
       Test case: `edit t 2 n/Bernice Yu p/99272758 g/F q/1 t/SC SP`<br>
       Expected: No tutor is edited. Error details are shown in the result display, with a result message `This tutor is unchanged`.
    5. Test case: `edit t 2 p/87438807`<br>
       Expected: No tutor is edited. Error details are shown in the result display, with a result message `Some other person already has this phone number...` since tutor Alex Yeoh already has this phone number.

2. Edit a student in the Student List.
    1. Test case: `edit s 1 n/John Doe`<br>
       Expected: The first student David Li is renamed to John Doe. Details of the edited student are shown in the result display.
    2. Test case: `edit s 1`<br>
       Expected: No student is edited. Error details are shown in the result display, with a result message `At least one field to edit must be provided`.
    3. Test case: `edit s 1 g/Z`<br>
       Expected: No student is edited. Error details are shown in the result display, with a result message `Gender should be 'M' / 'm' or 'F' / 'f'`.
    4. Test case: `edit s 2 n/Irfan Ibrahim p/92492021 g/M t/SC`<br>
       Expected: No student is edited. Error details are shown in the result display, with a result message `This student is unchanged`.
    5. Test case: `edit s 2 p/91031282`<br>
       Expected: No student is edited. Error details are shown in the result display, with a result message `Some other person already has this phone number...` since student David Li already has this phone number.

### Delete a Tutor/Student
{:.no_toc}

1. Delete a tutor from the Tutor List.
    1. Test case: `delete t 1`<br>
       Expected: First tutor is deleted from the Tutor List. Details of the deleted tutor are shown in the result display.
    2. Test case: `delete t 0`<br>
       Expected: No tutor is deleted. Error details are shown in the result display, with a result message `The tutor index provided is invalid`<br>
    3. Test case: `delete t x` (where x is an integer larger than the list size)<br>
       Expected: No tutor is deleted. Error details are shown in the result display, with a result message `The tutor index provided is invalid`<br>
    4. Test case: `clear t` then `delete t 1`<br>
   Expected: Error details are shown in the result display, with a result message `Tutor list is empty!`
    5. Test case: `clear t` then `delete t -10`<br>
       Expected: Error details are shown in the result display, with a result message `The tutor index provided is invalid`
1. Delete a student from the Student List.
    1. Test case: `delete s 1`<br>
       Expected: First student is deleted from the Student List. Details of the deleted student are shown in the result display.
    2. Test case: `delete s 0`<br>
       Expected: No student is deleted. Error details are shown in the result display, with a result message `The student index provided is invalid`
    3. Test case: `delete s x` (where x is an integer larger than the list size)<br>
       Expected: No student is deleted. Error details are shown in the result display, with a result message `The student index provided is invalid`<br>
    4. Test case: `clear s` then `delete s 1`<br>
      Expected: Error details are shown in the result display, with a result message `Student list is empty!`
    5. Test case: `clear s` then `delete s -10`<br>
      Expected: Error details are shown in the result display, with a result message `The student index provided is invalid`

### Find Tutor/Student
{:.no_toc}

[comment]: <> (Solution below adapted from https://ay2021s2-cs2103t-t12-4.github.io/tp/DeveloperGuide.html)

1. Find a tutor while all tutors are shown.
   1. Prerequisites: List all tutors using the `list t` command. There exist multiple tutors in the Tutor List.
   2. Test case: `find t n/alex`
      <br>Expected: Only tutors with names containing 'alex' (case-insensitive) should be displayed in the Tutor List.
      <br>e.g. Tutors with the names: Alex Yeoh, Alex Lim and Alex will be shown.
   3. Test case: `find t n/alex q/1`
      <br>Expected: Only tutors with names containing 'alex' (case-insensitive) with qualification 1 (undergraduate) will be displayed in the Tutor List.
      <br>e.g A tutor named Alex Yeoh with qualification 1 will be shown but a tutor named Alex Tan with qualification 2 will not be shown.
   4. Test case: `find`
      <br>Expected: Tutor List does not get updated. Error details are shown in the result display, with a result message `Invalid command format!...`.
   5. Test case: `find t`
      <br>Expected: Tutor List does not get updated. Error details are shown in the result display, with a result message `Invalid command format!...`.

2. Find a student while all students are shown.
    1. Prerequisites: List all students using the `list s` command. There exist multiple students in the Student List.
    2. Test case: `find s n/david`
       <br>Expected: Only students with names containing 'david' (case-insensitive) should be displayed in the Student List.
       <br>e.g. Students with the names: David Li, David Tan and John David will be shown.
    3. Test case: `find s n/david t/pm`
       <br>Expected: Only students with names containing 'david' (case-insensitive) and has the tag `PM` should be displayed in the Student List.
       <br>e.g A student named David Li and has tag `PM` will be shown but a student named David and has tag `PS` will not be shown.
    4. Test case: `find`
       <br>Expected: Student List does not get updated. Error details are shown in the result display, with a result message `Invalid command format!...`.
    5. Test case: `find s q/2`
       <br>Expected: Student List does not get updated. Error details are shown in the result display, with a result message `Students do not have any qualifications...`.
    6. Test case: `find s`
       <br>Expected: Student List does not get updated. Error details are shown in the result display, with a result message `Invalid command format!...`.
3. Find a tutor/student while the Tutor/Student List is empty.
   1. Prerequisites: Clear the Tutor and Student Lists using the `clear t` and `clear s` command respectively. There should not be any tutors or students shown in the respective lists.
   2. Test case: `find t n/alex`
      <br>Expected: Error details are shown in the result display, with a result message `Tutor list is empty!`.
   3. Test case: `find s n/david`
      <br>Expected: Error details are shown in the result display, with a result message `Student list is empty!`.

### Match Student
{:.no_toc}
1. Match a student while all students are being shown
    1. Prerequisites: List all students using the `list s` command. There exists multiple students in the list and at least one matching tutor.
    2. Test case: `match 1`<br>
        Expected: First student from the Student List is matched. Details of the matched student are shown in the result display and matched tutors are displayed on the matched Tutor List.
    3. Test case: `match`<br>
       Expected: No student is matched. Error details are shown in the result display, with a result message `Invalid command format!...`
    4. Test case: `match 0`<br>
       Expected: No student is matched. Error details are shown in the result display, with a result message `The student index provided is invalid`
       Other incorrect match commands to try: `match x` (where x ≤ 0 or x > Student List size).

### Filter matched Tutor List
{:.no_toc}

1. Filter tutors before matching tutors to a student.
   1. Prerequisites: No `match` command was entered.
   2. Test case: `filter q/3`<br>
      Expected: No tutors are filtered. Error details are shown in the result display, with a result message `Failed to filter as no match command was given prior.`.
2. Filter tutors after matching tutors to a student.
   1. Prerequisites: A valid match command was entered.
   2. Test case: `filter q/3`<br>
      Expected: All tutors with the qualification `MOE-Trained` will be filtered out.
3. Filter tutors after matching tutors to a student.
    1. Prerequisites: A valid match command was entered.
    2. Test case: `filter p/12341234`<br>
       Expected: No tutors are filtered. Error details are shown in the result display, with a result message `Invalid command format! ...`.

### List all Tutors/Students
{:.no_toc}

1. List all tutors.
    1. Prerequisites: There are tutors are in the Tutor List.
    2. Test case: `list t`<br>
       Expected: All stored tutors are shown in the Tutor List, with a result message `Listed all tutors!`.
2. List all tutors from the Tutor List after a `find` command
   1. Prerequisites: List all tutors using the `list t` command. There exist multiple tutors in the list. Perform a `find` command such that the Tutor List shows less than the actual number of tutors.
   2. Test case: `list t`<br>
      Expected: Filter from the `find` will be cleared and all tutors are shown in the Tutor List, with a result message `Listed all tutors!`.
3. List all students
    1. Prerequisites: All students are in the Student List.
    2. Test case: `list s`<br>
       Expected: All students are shown in the Student List, with a result message `Listed all students!`.
4. List all students from the Student List after a `find` command
    1. Prerequisites: List all students using the `list s` command. There exists multiple students in the list. Perform a `find` command such that the Student List shows less than the actual number of students.
    2. Test case: `list s`<br>
       Expected: Filter from the `find` will be cleared and all students are shown in the Student List, with a result message `Listed all students!`.
5. List all tutors from the Tutor List after a `clear t` command
    1. Prerequisites: List all tutors using the `list t` command. There exist multiple tutors in the list. Perform a `clear t` command such that all tutors are cleared from the Tutor List.
    2. Test case: `list t`<br>
       Expected: No tutors are shown in the Tutor List, with a result message saying `List is empty!`.
6. List all students from the Student List after a `clear s` command
    1. Prerequisites: List all students using the `list s` command. There exists multiple students in the list. Perform a `clear s` command such that all students are cleared from the Student List.
    2. Test case: `list s`<br>
       Expected: No students are shown in the Student List, with a result message saying `List is empty!`.

### Clear Tutor/Student List
{:.no_toc}

1. Clear all tutors from the Tutor List
   1. Test case: `clear t`<br>
       Expected: All tutors are cleared from the Tutor List with a result message `Tutor data has been cleared!`.
   2. Test case: `clear t 123`<br>
      Expected: Tutor List not cleared with a result message `Invalid command format! ...`.
2. Clear all students from the Student List
   1. Test case: `clear s`<br>
       Expected: All students are cleared from the Student List with a result message `Student data has been cleared!`.
   2. Test case: `clear s 123`<br>
      Expected: Student List not cleared with a result message `Invalid command format! ...`.

### Saving data
{:.no_toc}

[comment]: <> (@@author tenebrius1-reused)
[comment]: <> (Reused from https://ay2021s2-cs2103t-t12-4.github.io/tp/DeveloperGuidehtml#appendix-h-instructions-for-manual-testing with minor modifications)
1. Saving data between sessions
   1. Launch the app by double-clicking on the JAR file.
   2. Add a new tutor with a valid `add t` command. Confirm that the tutor has been added by checking whether it is shown in the Tutor List in the GUI.
   3. Close the app by using the `exit` command.
   4. Relaunch the app by double-clicking the JAR file.
      <br>Expected: The newly added tutor is still present in the Tutor List.

2. Dealing with corrupted data files
   1. Prerequisite: Opened and closed (by entering the command `exit`) the application and ensured that `data/clitutors.json` is created.
   2. Corrupt the save file (`data/clitutors.json`). The easiest way is to type '-' somewhere in one of the saved tutor's phone number. Another way may be to add random characters that make the JSON format unreadable.
   3. Relaunch the app by double-clicking on the JAR file.
      <br>Expected: Shows the GUI with no data.
   4. Delete the current save file.
   5. Relaunch the app by double-clicking on the JAR file.
      <br>Expected: Shows the GUI with sample data.

### Opening help window
{:.no_toc}

1. Opening help window
    1. Test case: `help` <br>
       Expected: Help window with command examples and link to User Guide appears.
    1. Test case: `help Randomstring123` <br>
       Expected: Help window with command examples and link to User Guide appears.
