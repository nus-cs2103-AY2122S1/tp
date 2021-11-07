---
layout: page
title: Developer Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

This document provides the relevant information for developers-as-users and developers-as-maintainers to learn more about the design architecture and user-related considerations made in designing this application.

To learn more about the design, you can explore the [Design](https://ay2122s1-cs2103t-f13-4.github.io/tp/DeveloperGuide.html#design) section for the implementations and schematics.
The [Requirements](https://ay2122s1-cs2103t-f13-4.github.io/tp/DeveloperGuide.html#appendix-requirements) section in the Appendix explains on the relevant user stories, use cases and non-functional requirements taken into consideration. The [Instructions for manual testing](https://ay2122s1-cs2103t-f13-4.github.io/tp/DeveloperGuide.html#appendix-instructions-for-manual-testing) section at the end of the Appendix shows the relevant manual tests one can perform as a sanity check.

Relevant non-trivial terminologies used are explained in the [Glossary](#glossary) Section.

Here are the interpretations of symbols and formatting used in this document:

* `highlights` represents code.
* :information_source: indicates additional information.
* :bulb: indicates tips.
* `Tuitione` is used in referencing code due to code syntax.
* **TuitiONE** is used when referencing the application.

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the [AddressBook-Level3](https://se-education.org/addressbook-level3/) project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-F13-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<center>
<img src="images/DeveloperGuideImage/ArchitectureDiagram.png" width="280" />
</center>

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/MainApp.java). It is responsible for,
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

<center>
<img src="images/DeveloperGuideImage/ArchitectureSequenceDiagram.png" width="574" />
</center>

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<center>
<img src="images/DeveloperGuideImage/ComponentManagers.png" width="300" />
</center>

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/ui/Ui.java)

<center>
<img alt="Structure of the UI Component" src="images/DeveloperGuideImage/UiClassDiagram.png" width="650"/>
</center>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<center>
<img src="images/DeveloperGuideImage/LogicClassDiagram.png" width="550"/>
</center>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TuitioneParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

<center>
<img alt="Interactions Inside the Logic Component for the `delete 1` Command 0" src="images/DeveloperGuideImage/DeleteSequenceDiagram0.png"/>
</center>

<center>
<img alt="Interactions Inside the Logic Component for the `delete 1` Command 1" src="images/DeveloperGuideImage/DeleteSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser`, `DeleteCommand` and `Student s` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<center>
<img src="images/DeveloperGuideImage/ParserClasses.png" width="650"/>
</center>

How the parsing works:
* When called upon to parse a user command, the `TuitioneParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `TuitioneParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/model/Model.java)

<center>
<img alt="Structure of the Model Component" src="images/DeveloperGuideImage/ModelClassDiagram.png" width="650"/>
</center>

The `Model` component,

* stores the tuitione data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the tuitione data i.e., all `Lesson` objects (which are contained in a `UniqueLessonList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the currently 'selected' `Lesson` objects (e.g., results of a filter) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Lesson>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">

:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Remark` list in the `TuitiONE`, which `Student` references. This allows `TuitiONE` to only require one `Remark` object per unique remark, instead of each `Student` needing their own `Remark` objects. A similar design can be considered for `Grade` as well.<br><br>

<center>
<img src="images/DeveloperGuideImage/BetterModelClassDiagram.png" width="500" />
</center>

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-F13-4/tp/blob/master/src/main/java/seedu/tuitione/storage/Storage.java)

<center>
<img src="images/DeveloperGuideImage/StorageClassDiagram.png" width="650" />
</center>

The `Storage` component,
* can save both tuitione data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `TuitioneStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the [`seedu.tuitione.commons`](https://github.com/AY2122S1-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/tuitione/commons) package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Lesson feature

#### Implementation

The add lesson operation is facilitated by `AddLessonCommand` and `AddLessonCommandParser`. `AddLessonCommand` first parses the user input to extract out the command and the arguments, after which the `AddLessonCommand#execute(model)` method is invoked in the `LogicManager` class to add the lesson with the corresponding details.

The add lesson feature is relative similar to that of the original add student feature. The differences lay in the properties which a lesson could hold as compared to a student. See below for the properties of a lesson:

- `GRADE`:
  1. This is a common property between the student class and the lesson class.
  2. When enrolling a student for a particular lesson, compatible and matching `GRADE` between student and lesson will be required to successful enrolment.
  3. In the current version of implementation, `GRADE`'s input constraints are:
     1. Primary School: "P`X`" <where `X` can be 1, 2, 3, 4, 5, 6>.
     2. Secondary School: "S `Y`" <where `Y` can be 1, 2, 3, 4>.
     3. Expansion is allowed to cater for tertiary educational subjects.
- `SUBJECT`:
  1. In the current version of implementation, `SUBJECT`'s input constraints are:
     1. Limited to 20 characters only.
     2. First letter is required to be capitalized.
     3. Expansion is allowed to cater for more advanced subjects with naming convention more than 20 characters.
- `DAY_OF_WEEK`:
  1. In the current version of implementation, `DAY_OF_WEEK`'s input constraints are:
     1. Only acceptable input: Mon, Tue, Wed, Thu, Fri, Sat, Sun (With first character capitalized)
     2. Expansion is allowed to cater for more format variants of `DAY_OF_WEEK` entries.
- `START_TIME`:
  1. In the current version of implementation, `START_TIME`'s input constraints are:
     1. In 2400 hours format.
     2. Each lesson is presumed to consume 2 hours only.
     3. 0900 <= `START_TIME` <= 1900, as lesson timing in tuition centre is presumed to be from 0900 to 2200 at most.
     4. Expansion is allowed to cater for late night classes that extent beyond 2200.
- `COST`:
  1. In the current version of implementation, `COST`'s input constraints are:
     1. 0 <= `COST`.

After deconstructing all the relevant properties and validating their validity, a `Lesson` will be generated with a unique `lesson code` that is specific to that lesson. This `lesson code` will be used as a unique string identifier.

- `lesson code` Format: `<SUBJECT>`-`<GRADE>`-`<DAY_OF_WEEK>`-`<START_TIME>`

Given below is an example usage scenario and how the add lesson operation behaves.

_Note: For this usage, we only consider the main successful scenarios (i.e. The lesson we're adding does not exist in TuitiONE and all properties entered are within the constraints)_

Example: `add-l s/Science g/P2 d/Wed t/1200 c/10.50`

<u>Step 1:</u>

When the user has entered the command, `AddLessonCommandParser` object will proceed on to parse and check the validity of each property entered. Assuming successful, `AddLessonCommandParser` object will proceed on to produce a `Lesson` with the relevant details filled. The object state diagram is as such:

<center>
<img alt="AddLessonState0" src="images/DeveloperGuideImage/AddLessonState0-Initial_state.png"/>
</center>

<u>Step 2:</u>

With all checks done, `Lesson` object will be added into the `Model` of TuitiONE. The final object state diagram is as such:

<center>
<img alt="AddLessonState1" src="images/DeveloperGuideImage/AddLessonState1-Final_state.png"/>
</center>

The following sequence diagram shows how add lesson operation works:

<center>
<img alt="AddLessonSequenceDiagram0" src="images/DeveloperGuideImage/AddLessonSequenceDiagram0.png"/>
</center>

<center>
<img alt="AddLessonSequenceDiagram1" src="images/DeveloperGuideImage/AddLessonSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifelines for `AddLessonCommandParser` and `AddLessonCommand` should end at destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

<center>
<img alt="AddLessonActivityDiagram" src="images/DeveloperGuideImage/AddLessonActivityDiagram.png"/>
</center>

#### Design considerations:

<ins>Aspect: How to design the syntax</ins>
* Command Word Style
  * Option 1: `add-l`
    * Pros:
      * Non-space-separated word allows easier parsing of command word
      * Unique command word allows command keys to be easily distinguished
    * Cons:
      * Redundant creation of a new command word, when there is an existing `add` command word.
      * Might not be as intuitive as there are now 2 add commands with different keywords.
  * Option 2: `add -l`
    * Pros:
      * More intuitive, `-l` flag can be used to determine that a lesson is to be added, while omitting it means a student is to be added.
    * Cons:
      * Harder to parse, as the `-l` flag is space separated from the command keyword.
      * User might forget to include the `-l` flag, accidentally adding a student instead.

* Command Informational Field Style
  * Option 1: `LESSON_CODE`
    * Pros:
      * Only one continuous line of code is required to be entered.
    * Cons:
      * One long string will be to be entered, which the team would then be required to come up with another implementation of parser to parse this piece of information. This would be redundant since we can make use of the existing parser using the CliSyntax.
      * User would have to key in the string in a predetermined order, which the user could get easily confused.
  * Option 2: `s/SUBJECT g/GRADE d/DAY t/START_TIME c/COST`
    * Pros:
      * Different fields can be placed in any order. Hence, making it easier for user to enter the relevant fields.
      * Informational fields are now more distinct. Hence, it is easier for the user to follow through.
    * Cons:
      * More `spacebar` and CliSyntax would have to be pressed to cater for each individual fields.

<ins>Decision</ins>

Command Word Style: Option 1 (`add-l`) is chosen as it requires lesser modification to the existing code base parsing utilities. Additionally, there is not much significance in having an especially pretty command syntax as efficiency(i.e. entering commands fast and correctly) is desired. At the same time, the accidental addition of a student rather than the intended lesson is a likely scenario, hinting that Option 2 (`add -l`) should only be implemented once an undo/redo feature is implemented.

Command Informational Field Style: Option 2 (`s/SUBJECT g/GRADE d/DAY t/START_TIME c/COST`) is chosen as it requires lesser modification to the existing code base parsing utilities. Additionally, similar behaviour with the add student command would help the user to pick up the command syntax easier since there are lesser things to remember.

### Delete Lesson feature

#### Implementation

The delete lesson operation is facilitated by `DeleteLessonCommand` and `DeleteLessonCommandParser`. `DeleteLessonCommandParser` first parses the user input to extract out the command and the arguments, after which the `DeleteLessonCommand#execute(model)` method is invoked in the `LogicManager` class to delete the lesson.

The delete lesson feature is very similar to that of the original delete student feature. There are however some differences due to the linkages of lessons to multiple students, of which we have to unenroll the students before deletion. Given below is an example usage scenario and how the delete lesson operation behaves.
_Note: For this usage, we only consider the main success scenario (i.e. the lesson specified exists as well as the students enrolled to the lesson)._

<u>Step 1:</u>

User has a list of students and lessons presented in their TuitiONE application. For this case, the user has one lesson `l` that is enrolled by 2 students `John` and `Alice`. The object state diagram is as such:

<center>
<img alt="DeleteLessonState0" src="images/DeveloperGuideImage/DeleteLessonState0.png"/>
</center>

<u>Step 2:</u>

Upon running the delete lesson command, the application runs a few internal steps:

* The `Tuitione` model obtains the lesson to remove.
* The command executor then extracts the students that are in the lesson.
* If there are students enrolled:
   * The lesson unenrolls the students.
   * The mentioned student details are updated subsequently.
* Finally, the lesson is safe to be removed.
* Relevant UI and Storage procedures are run to complete the execution in full.

The final object state diagram is as such:

<center>
<img alt="DeleteLessonState1" src="images/DeveloperGuideImage/DeleteLessonState1.png"/>
</center>

Notice how there are no more associations between the Lesson and the Students.

The following sequence diagram shows how the delete lesson operation works:

<center>
<img alt="DeleteLessonSequenceDiagram0" src="images/DeveloperGuideImage/DeleteLessonSequenceDiagram0.png"/>
</center>

<center>
<img alt="DeleteLessonSequenceDiagram1" src="images/DeveloperGuideImage/DeleteLessonSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifelines for `DeleteLessonCommandParser`, `DeleteLessonCommand` and `Lesson l` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes the delete lesson command:

<center>
<img alt="DeleteLessonActivityDiagram" src="images/DeveloperGuideImage/DeleteLessonActivityDiagram.png"/>
</center>

#### Design considerations:

<ins>Aspect: How to design the syntax</ins>
* Option 1: `delete-l LESSON_INDEX`
  * Pros:
    * Non-space-separated word allows easier parsing of command word
    * Unique command word allows command keys to be easily distinguished
  * Cons:
    * Redundant creation of a new command word, when there is an existing `delete STUDENT_INDEX` command.
    * Might not be as intuitive as there are now 2 delete commands with different keywords.
* Option 2: `delete -l LESSON_INDEX`
  * Pros:
    * More intuitive, `-l` flag can be used to determine that a lesson is to be deleted, while omitting it means a student is to be deleted.
  * Cons:
    * Harder to parse, as the `-l` flag is space separated from the command keyword.
    * User might forget to include the `-l` flag, accidentally deleting a student instead.

<ins>Decision</ins>

Ultimately, Option 1 (`delete-l LESSON_INDEX`) is chosen as it requires lesser modification to the existing code base parsing utilities.
Additionally, there is not much significance in having an especially pretty command syntax as efficiency (i.e. entering commands fast and correctly) is desired. At the same time, the accidental deletion of a student rather than the intended lesson is a likely scenario, hinting that Option 2 (`delete -l LESSON_INDEX`) should only be implemented once an undo/redo feature is implemented.

### Enroll feature

#### Implementation

The Enroll feature establishes a bi-directional relationship
between a student and a specific lesson. This student to be enrolled must meet a set of conditions.
1. The student has to have the same `Grade` as specified in the lesson.
2. The student cannot already be enrolled in the lesson.

When enrolled, the student will have that lesson added to their list
of lessons they have currently signed up for, and lesson size would increase by 1.

Given below is an example usage scenario and how the enroll operation behaves.
_Note: For this usage, we only consider the main success scenario
(i.e. the student exists, has the same `grade` as the specific lesson,
and has not been enrolled in the lesson yet)._

<ins>Step 1:</ins>

User has a list of students and lessons presented in their
TuitiONE application. The user has a `Lesson` with the lessoncode Math-P2-Wed-1800 and `grade`
P2 that they would like to enroll a `Student` named Alice of `grade` P2 into.
The object state diagram is as such:

<center>
<img alt="EnrollLessonState0" src="images/DeveloperGuideImage/EnrollLessonState0.png"/>
</center>

The initial size of the lesson is 0, and the student has yet to enroll
into the lesson.

<ins>Step 2:</ins>

Upon running the Enroll command, the application runs a few internal steps:

* The `Tuitione` model obtains the student to enroll into lesson.
* The command executor checks if the student is eligible to be enrolled into lesson.
* The command executor checks if the student is currently enrolled in the lesson
* Finally, the student is ready to be enrolled into the lesson.
* Relevant UI and Storage procedures runs to complete the execution in full.

The final object state diagram is as such:

<center>
<img alt="EnrollLessonState0" src="images/DeveloperGuideImage/EnrollLessonState1.png"/>
</center>

The following sequence diagram shows how the enroll lesson operation works:

<center>
<img alt="EnrollLessonSequenceDiagram0" src="images/DeveloperGuideImage/EnrollLessonSequenceDiagram0.png"/>
</center>

<center>
<img alt="EnrollLessonSequenceDiagram1" src="images/DeveloperGuideImage/EnrollLessonSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifelines for `EnrollCommandParser` and `EnrollCommand` should end at destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what
happens when a user executes the enroll lesson command:

<center>
<img alt="EnrollLessonActivityDiagram" src="images/DeveloperGuideImage/EnrollLessonActivityDiagram.png"/>
</center>

#### Design considerations:

<ins>Aspect: How to design the syntax</ins>
* Option 1: `enroll STUDENT_INDEX l/LESSON_CODE`
    * Pros:
        * More intuitive for user to type out lesson code as it contains critical information
          about the lesson itself (i.e grade, starting time, subject)
        * Unique command word allows command keys to be easily distinguished
    * Cons:
        * Long command might be inconvenient for users when typing multiple enroll commands
* Option 2: `enroll STUDENT_INDEX l/LESSON_INDEX`
    * Pros:
        * Much shorter to type lesson index when looking at GUI, instead of entire lesson code
        * Can refer easily to GUI lesson index
    * Cons:
        * Less intuitive for the user as they have to refer to the GUI to ensure they have the correct lesson before enrolling
        * Might require user to filter lesson first before referring to GUI, incurring an extra step

<ins>Decision</ins>

Ultimately, Option 2 (`enroll STUDENT_INDEX l/LESSON_INDEX`) is chosen as our team felt that a user would value efficiency when typing multiple enroll commands rather than typing out an entire lesson code.
The user is still able to refer to the GUI in this instance, with the help with filter commands, making it rather easy for the user to achieve the same result as using a lesson code.

Our team's main goal is to ensure that whatever the user does and types is done in an efficient and easy method. Ultimately, Option 2 achieves this goal better than option 1.

### Unenroll feature

#### Implementation

The unenroll operation is facilitated by the `UnenrollCommand` and `UnenrollcommandParser`. `UnenrollCommandParser`
first parses the user input to extract out the command and the arguments, after which the
`UnenrollCommand#execute(model)` method is invoked in the `LogicManager` class to unenroll the specified student from
the specified lesson.

The unenroll feature removes the student from the list of students in the specified lesson object. Subsequently, the
lesson is removed from the set of lessons in the specified student object. The student must be enrolled in the lesson
in order for the unenroll operation to be successful.

Given below is an example usage scenario and how the unenroll operation works.

<ins>Step 1:</ins>

User has a list of students and lessons presented in their TuitiONE application. For this case, the user has a
lesson `l1` that has two students (`John` and `Alice`). The object state diagram is as such:

<center>
<img alt="UnenrollState0" src="images/DeveloperGuideImage/UnenrollState0.png"/>
</center>

Let 1 be the index of `John`, 2 be the index of `Alice` and let the index of the lesson be 1.

<ins>Step 2:</ins>

The user uses the command `unenroll 2 l/1`. Upon running the unenroll command, the application runs a few
internal steps.

* The `Tuitione` model obtains the student specified. In this case, the student is `Alice`.
* The `Tuitione` model obtains the lesson specified. In this case, the lesson is `l1`.
* The command executor checks if the student, `Alice`, is enrolled in the lesson `l1`.
* If the student is enrolled, the `Alice` will be removed from the list of students in the lesson object `l1`.
* Subsequently, the lesson `l1` will be removed from the set of lessons in the student object `Alice`.
* Relevant UI and Storage procedures are run to complete the execution in full.

The final object state diagram is as such:

<center>
<img alt="UnenrollState1" src="images/DeveloperGuideImage/UnenrollState1.png"/>
</center>

Notice how there is no longer any association between the student `Alice` and lesson `l1`.

The following sequence diagram shows how the unenroll operation works:

<center>
<img alt="UnenrollSequenceDiagram0" src="images/DeveloperGuideImage/UnenrollSequenceDiagram0.png"/>
</center>

<center>
<img alt="UnenrollSequenceDiagram1" src="images/DeveloperGuideImage/UnenrollSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifelines for `UnenrollCommandParser` and `UnenrollCommand` should end at destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes the unenroll lesson command:

<center>
<img alt="UnenrollActivityDiagram" src="images/DeveloperGuideImage/UnenrollActivityDiagram.png"/>
</center>

#### Design considerations:

<ins>Aspect: How to design the syntax</ins>
* Option 1: `unenroll STUDENT_INDEX l/LESSON_CODE`
    * Pros:
        * Unique lesson code clearly specifies the lesson that the student is to be unenrolled from.
    * Cons:
        * More difficult to type as the lesson code is quite long.
* Option 2: `unenroll STUDENT_INDEX l/LESSON_INDEX`
    * Pros:
        * Much faster to type and execute.
    * Cons:
        * User may specify the wrong index and unenroll student from the wrong lesson.

<ins>Decision</ins>

Ultimately, Option 2 (`unenroll STUDENT_INDEX l/LESSON_INDEX`) is chosen as it is faster and easier to type.
This makes our app faster and easier to use. Additionally, there is not much significance in specifying the lesson
through a lesson code as although it cleary specifies the lesson, the chances of the user keying in the wrong index and
subsequently unenrolling the student from the wrong lesson is not high. Even if it does happen, the short command
syntax should make it easy to fix the mistake.

### Filter feature

#### Implementation

The filter operation is facilitated by `FilterCommand` and `FilterCommandParser`. `FilterCommandParser` first parses the user
input to extract out the command and the arguments, after which the `FilterCommand#execute(model)` method is invoked in
the `LogicManager` class to filter the `filteredStudents` and/or the `filteredLessons` list(s) in the `model` based on the given user inputs.

The filter performs differently based on the inputs given (grade, subject, or both):
* If only grade is given as input, TuitiONE filters both the student list and the lesson list based on the given grade.
* If only subject is given as input, TuitiONE filters only the lesson list based on the given subject.
* If both are given as input, TuitiONE filters the student list by the given grade, but filters the lesson list based
on both the given grade and subject.

Given below is an example usage scenario and how the filter operation works.

<u>Step 1:</u>

The user launches the app with the stored student list holding the initial student data and the lesson list holding the
initial lesson data in TuitiONE (only the fields of each object relevant to the filter feature are shown in the diagrams below).

<center>
<img alt="FilterState0" src="images/DeveloperGuideImage/FilterState0.png"/>
</center>

<u>Step 2:</u>

The user executes `filter g/S2 s/English`  to filter out S2 English lessons and S2 students. The `filter` command causes
the `FilterCommand#execute(model)` method to be called which then filters the respective lists to only show the relevant objects.

<center>
<img alt="FilterState1" src="images/DeveloperGuideImage/FilterState1.png"/>
</center>

<u>Step 3:</u>

The user can execute the command `list` to get back the initial lists before the filter command.

The following sequence diagram shows how the filter operation works:

<center>
<img alt="FilterSequenceDiagram0" src="images/DeveloperGuideImage/FilterSequenceDiagram0.png"/>
</center>

<center>
<img alt="FilterSequenceDiagram1" src="images/DeveloperGuideImage/FilterSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifelines for `FilterCommandParser` and `FilterCommand` should end at destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes the filter command:

<center>
<img alt="FilterActivityDiagram" src="images/DeveloperGuideImage/FilterActivityDiagram.png"/>
</center>

#### Design considerations:

<u>Aspect: How to implement filter</u>
* Option 1: one filter command that handles both grade and subject filtering
    * Pros: Less commands to remember, user will not feel overwhelmed.
    * Cons: Slightly more difficult to implement, as one command has to handle the 3 cases of user input as mentioned above.
* Option 2: 3 separate filter commands, one for each scenario stated above
    * Pros: Slightly more straightforward to implement.
    * Cons: Too many existing commands in the application, and may not be as intuitive to use.

<ins>Decision</ins>

Ultimately we chose option 1 as we felt that there are already many existing commands, and just having one filter command
handle multiple scenarios would be less daunting to use.

#### Implementation

The roster operation is facilitated by `RosterCommand` and `RosterCommandParser`. `RosterCommandParser` first parses 
the user input to extract out the command and the arguments, after which the `RosterCommand#execute(model)` method is 
invoked in the `LogicManager` class to filter the `filteredStudents` and the `filteredLessons` list(s) in the `model` 
based on the given user input (`LESSSON_INDEX`).

The roster feature functions like a more specific version of the filter feature. The aim of this feature is to provide 
the CSOs a quick way to see all the students who are enrolled in a specific lesson. Hence, in the roster feature, it 
performs a filtering of the student list and the lesson list based on a specific lesson identified by the`LESSON_INDEX`.
This will extract out the relevant students and display them to the GUI with the requested lesson.

Given below is an example usage scenario and how the roster operation works.

<u>Step 1:</u>

The user launches the app with the stored student list holding the initial student data and the lesson list holding the
initial lesson data in TuitiONE (only the fields of each object relevant to the roster feature are shown in the diagrams below).

<center>
<img alt="RosterState0" src="images/DeveloperGuideImage/RosterState0.png"/>
</center>

<u>Step 2:</u>

The user executes `roster 1` to filter out and display all the corresponding students enrolled in `Math-P4-Wed-1800`.
The `roster` command causes the `RosterCommand#execute(model)` method to be called which then filters the respective 
lists to only show the relevant objects.

<center>
<img alt="RosterState1" src="images/DeveloperGuideImage/RosterState1.png"/>
</center>

<u>Step 3:</u>

The user can execute the command `list` to get back the initial lists before the roster command.

The following sequence diagram shows how the roster operation works:

<center>
<img alt="RosterSequenceDiagram0" src="images/DeveloperGuideImage/RosterSequenceDiagram0.png"/>
</center>

<center>
<img alt="RosterSequenceDiagram1" src="images/DeveloperGuideImage/RosterSequenceDiagram1.png" width="650"/>
</center>

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifelines for `RosterCommandParser` and `RosterCommand` should end at destroy marker 
(X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes the roster command:

<center>
<img alt="RosterActivityDiagram" src="images/DeveloperGuideImage/RosterActivityDiagram.png"/>
</center>

#### Design considerations:

<u>Aspect: How to implement roster</u>

* Option 1: `roster LESSON_INDEX`
    * Pros: Less key travels needed, user can potentially save more time in the repeated usage of this command.
    * Cons: User may not understand the use of the `LESSON_INDEX` on the first instance.
* Option 2: `roster l/LESSON_INDEX`
    * Pros: With the usage of the flag `l/`, the purpose of the command is clearer.
    * Cons: As the user gets familiar with the application, the extra `l/` could potentially result in a loss of efficiency due to extra key travels.

<ins>Decision</ins>

Ultimately we chose option 1 as we felt that in a long run, the time saved through a fast and easy command is more essential, in order to keep TuitiONE on the competitive edge.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedTuitione`. It extends `Tuitione` with an undo/redo history, stored internally as an `tuitioneStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedTuitione#commit()` — Saves the current tuitione state in its history.
* `VersionedTuitione#undo()` — Restores the previous tuitione state from its history.
* `VersionedTuitione#redo()` — Restores a previously undone tuitione state from its history.

These operations are exposed in the `Model` interface as `Model#commitTuitione()`, `Model#undoTuitione()` and `Model#redoTuitione()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

<u>Step 1.</u>

The user launches the application for the first time. The `VersionedTuitione` will be initialized with the initial tuitione state, and the `currentStatePointer` pointing to that single tuitione state.

<center>
<img alt="UndoRedoState0" src="images/DeveloperGuideImage/UndoRedoState0.png"/>
</center>

<u>Step 2.</u>

The user executes `delete 5` command to delete the 5th student in the tuitione. The `delete` command calls `Model#commitTuitione()`, causing the modified state of the tuitione after the `delete 5` command executes to be saved in the `tuitioneStateList`, and the `currentStatePointer` is shifted to the newly inserted tuitione state.

<center>
<img alt="UndoRedoState1" src="images/DeveloperGuideImage/UndoRedoState1.png"/>
</center>

<u>Step 3.</u>

The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitTuitione()`, causing another modified tuitione state to be saved into the `tuitioneStateList`.

<center>
<img alt="UndoRedoState2" src="images/DeveloperGuideImage/UndoRedoState2.png"/>
</center>

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitTuitione()`, so the tuitione state will not be saved into the `tuitioneStateList`.

</div>

<u>Step 4.</u>

The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoTuitione()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous tuitione state, and restores the tuitione to that state.

<center>
<img alt="UndoRedoState3" src="images/DeveloperGuideImage/UndoRedoState3.png"/>
</center>

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial Tuitione state, then there are no previous Tuitione states to restore. The `undo` command uses `Model#canUndoTuitione()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

<center>
<img alt="UndoSequenceDiagram" src="images/DeveloperGuideImage/UndoSequenceDiagram.png"/>
</center>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoTuitione()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the tuitione to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `tuitioneStateList.size() - 1`, pointing to the latest tuitione state, then there are no undone Tuitione states to restore. The `redo` command uses `Model#canRedoTuitione()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

<u>Step 5.</u>

The user then decides to execute the command `list`. Commands that do not modify the tuitione, such as `list`, will usually not call `Model#commitTuitione()`, `Model#undoTuitione()` or `Model#redoTuitione()`. Thus, the `tuitioneStateList` remains unchanged.

<center>
<img alt="UndoRedoState4" src="images/DeveloperGuideImage/UndoRedoState4.png"/>
</center>

<u>Step 6.</u>

The user executes `clear`, which calls `Model#commitTuitione()`. Since the `currentStatePointer` is not pointing at the end of the `tuitioneStateList`, all tuitione states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<center>
<img alt="UndoRedoState5" src="images/DeveloperGuideImage/UndoRedoState5.png"/>
</center>

The following activity diagram summarizes what happens when a user executes a new command:

<center>
<img src="images/DeveloperGuideImage/CommitActivityDiagram.png" width="250" />
</center>

#### Design considerations:

<ins> Aspect: How undo & redo executes:</ins>

* Option 1: Saves the entire tuitione.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* Option 2: Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendices**

### **Appendix A: Requirements**

#### Product scope

**Target user profile (Customer Service Officer at tuition center)**:

* has a need to manage a significant number of student details in a tuition centre
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Provide a more streamlined platform, as compared to conventional excel which might have numerous irrelevant functions. This platform also offers a more intuitive UI - with a clean and minimalist layout. Helps manage student admin information faster than a typical mouse-driven app. The app is only used for one tuition centre.


#### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                      | So that I can …​                                              |
| -------- | ------------------------------------------ | ------------------------------------ | -----------------------------------------------------------------|
| `* * *`  | Customer Service Officer                   | add a new student                    | keep track of student count and details                          |
| `* * *`  | Customer Service Officer                   | delete a student                     | keep track of student count and details                          |
| `* * *`  | Customer Service Officer                   | know a student's grade               | schedule them into the right lessons (eg. P5 student to P5 lessons) |
| `* * *`  | Customer Service Officer                   | look up a student by their name      | find out the student’s details                                   |
| `* * *`  | Customer Service Officer                   | know the lessons a student is enrolled in  | so that I can keep track of what lessons they need to pay for                                          |
| `* * *`  | Customer Service Officer                   | enroll a students into a lesson      | keep track of student count and details in lessons               |
| `* * *`  | Customer Service Officer                   | unenroll a student from a lesson     | keep track of student count and details in lessons               |
| `* * *`  | Customer Service Officer                   | know the contact of a student’s parent | call the parent if he is late or did not attend                |
| `* * *`  | Customer Service Officer                   | know a student's address             | send physical letters/ documents                                 |
| `* * *`  | Customer Service Officer                   | know a student's grade               | keep track of a student's grade                                  |
| `* * *`  | Customer Service Officer                   | know the email of a student’s parent | send emails to update the student                                |
| `* * *`  | Customer Service Officer                   | know the list of all lessons         | check the status of other lessons                                |
| `* * *`  | Customer Service Officer                   | add a new lesson                     | enroll students into the lesson                                  |
| `* * *`  | Customer Service Officer                   | delete an existing lesson            | remove outdated lessons                                          |
| `* * *`  | Customer Service Officer                   | know what time lessons start/end     | update relevant parties about the lesson timings                 |
| `* *`    | Customer Service Officer                   | update a student’s address           | ensure the details are updated to date                           |
| `* *`    | Customer Service Officer                   | update a student’s grade             | ensure the details are updated to date                           |
| `* *`    | Customer Service Officer                   | update the contact of a student’s parent | ensure the details are updated to date                       |
| `* *`    | Customer Service Officer                   | update the email of a student’s parent | ensure the details are updated to date                         |
| `* *`    | Customer Service Officer                   | filter students by their grade       | categorise students by their grades                              |
| `* *`    | Customer Service Officer                   | filter lessons by their grade        | categorise lessons by their grades                               |
| `* *`    | Customer Service Officer                   | filter lessons by their subject        | categorise lessons by their subjects                               |
| `* *`    | Customer Service Officer                   | know how many students are in the lesson | help tutors plan resources accordingly for the lesson        |
| `* *`    | Customer Service Officer                   | know the command format to enter     | learn to use the application                                     |
| `* *`    | Customer Service Officer                   | know how much a student has to pay per week | remind the parents to pay punctually                      |
| `* *`    | Customer Service Officer                   | leave remarks                        | make lessons more convenient for tutors and students in the case they are unable to make it for a specific lesson |
| `* *`    | Customer Service Officer                   | view the roster of a specific lesson | see the list of students attending the lesson, and make an attendance sheet |
| `*`      | Customer Service Officer                   | know a student's attendance          | inform teachers or find out why students are missing lessons     |
| `*`      | Customer Service Officer                   | record a student's attendance        | keep track of student's attendance                               |
| `*`      | Customer Service Officer                   | update details of existing lessons   | change the specifics of the lesson                               |
| `*`      | Customer Service Officer                   | update a student’s performance stats | inform tuition teachers of their proficiency                     |
| `*`      | Customer Service Officer                   | know the lessons each teacher has    | remind them of their lessons                                     |
| `*`      | Customer Service Officer                   | add a teacher                        | keep track of their details                                      |
| `*`      | Customer Service Officer                   | delete a teacher                     | delete irrelevant teachers                                       |
| `*`      | Customer Service Officer                   | assign teachers to lessons           | keep track of their classes                                      |
| `*`      | Customer Service Officer                   | know students performance stats      | update the teachers/ students if they need that information      |
| `*`      | Customer Service Officer                   | keep track of student’s outstanding payments | know who has paid and remind parents to pay if they have yet to complete payment |
| `*`      | Customer Service Officer                   | read excel sheet                     | import my pre-existing administrative details                    |
| `*`      | Customer Service Officer                   | write to excel sheet                 | export my existing administrative details                        |


#### Use cases

For all use cases below, the **System** is the `TuitiONE` and the **Actor** is the `Customer Service Officer (CSO)`, unless specified otherwise.

##### UC01: View all Students and Lessons

**MSS**

1. CSO enters to view all information.
2. TuitiONE displays all students and lessons present.

    Use case ends.

##### UC02: Add a Student

**MSS**

1. CSO enters details to add a student.
2. TuitiONE adds specified student to the list.

    Use case ends.

**Extensions**

* 1a. Missing compulsory details in command.
    * 1a1. TuitiONE requests CSO to input a valid command.
    * 1a2. CSO enters new command.
      Steps 1a1-1a2 are repeated until the data entered are correct.

    Use case resumes from step 2.

* 1b. TuitiONE detects an error in entered command.
    * 1b1. TuitiONE requests CSO to input a valid command.
    * 1b2. CSO enters new command.
      Steps 1b1-1b2 are repeated until the data entered are correct.

    Use case resumes at step 2.

* 1c. Student already exists in TuitiONE.
    * 1c1. TuitiONE informs that there already exist such a student.

    Use case ends.

##### UC03: Look up Student(s)

**MSS**

1. CSO enters keywords(s) to find specific student(s).
2. TuitiONE shows a list of relevant students.
3. CSO looks through the given results of student(s) and their details, such as:
    * Their grade
    * Their parent’s contact number
    * Their address
    * Their enrolled lessons
    * Their tuition subscription

    Use case ends.

**Extensions**

* 1a. TuitiONE detects an error in entered command.
    * 1a1. TuitiONE requests CSO to input a valid command.
    * 1a2. CSO enters new command.
      Steps 1a1-1a2 are repeated until the data entered are correct.

    Use case resumes at step 2.

* 2a. TuitiONE cannot find any results relevant to the search keyword.
    * 2a1. TuitiONE informs that there are no such students.

    Use case ends.

##### UC04: Filter Students by grade and Lessons by grade and/or subject

**MSS**
1. CSO wants to filter the student and/or lesson list by their grade and/or subject.
2. TuitiONE lists the students and/or lessons that matches the filter criteria.

    Use case ends.

**Extension**

* 1a. TuitiONE detects an error in entered command.
    * 1a1. TuitiONE requests CSO to input a valid command.
    * 1a2. CSO enters new command.
      Steps 1a1-1a2 are repeated until the input entered is correct.

    Use case resumes at step 2.

* 1b. CSO decides to filter by grade only
    * 1b1. TuitiONE filters the student and lesson list based on the given grade.

    Use case resumes at step 2.

* 1c. CSO decides to filter by subject only
    * 1c1. TuitiONE filters the lesson list based on the given subject.

    Use case resumes at step 2.

* 1d. CSO decides to filter by grade and subject
    * 1d1. TuitiONE filters the student list based on the given grade, and filters the lesson list based on both the
      given grade and subject.

    Use case resumes at step 2.

* 1e. TuitiONE detects an error in the input fields for grade and/or subject.
    * 1e1. TuitiONE requests CSO to input command with valid fields.
    * 1e2. CSO enters new command.
      Steps 1a1-1a2 are repeated until the input entered is correct.

    Use case resumes at step 2.

##### UC05: Delete a Student

**MSS**

1. CSO views the current list of students, <ins>look up student(s) (UC03)</ins>, or <ins>filter student(s) by their grade (UC04)</ins>.
2. CSO enters a specific student to delete from the list.
3. TuitiONE deletes the student.

    Use case ends.

**Extensions**

* 1a. The list is empty.

    Use case ends.

* 2a. TuitiONE detects an error in entered command.
    * 2a1. TuitiONE requests CSO to input a valid command.
    * 2a2. CSO enters new command.
      Steps 2a1-2a2 are repeated until the data entered are correct.

    Use case resumes at step 3.

* 2b. The student provided is not present in the list.
    * 2b1. TuitiONE informs that there is no such student.

    Use case ends.

##### UC06: Add a Lesson

**MSS**

1. CSO requests to add a lesson with relevant details.
2. TuitiONE adds the lesson.

    Use case ends.

**Extensions**

* 1a. TuitiONE detects an error in entered command.
    * 1a1. TuitiONE requests CSO to input a valid command.
    * 1a2. CSO enters new command.
      Steps 1a1-1a2 are repeated until the data entered are correct.

    Use case resumes at step 2.

* 1b. Lesson already exists in TuitiONE.
    * 1b1. TuitiONE informs that there already exist such a Lesson.

    Use case ends.

##### UC07: View details of a Lesson

**MSS**

1. CSO views a list of lessons.
2. TuitiONE shows a list of lessons, with their respective details:
    * Lesson subject
    * Grade
    * Time period
    * Price
    * Number of students enrolled

    Use case ends.

##### UC08: Delete a Lesson

**MSS**

1. CSO views the current list of lessons, or <ins>filter lesson(s) by their grade and/or subject (UC04)</ins>.
2. CSO requests to delete a specific lesson in the list.
3. TuitiONE deletes the lesson.

    Use case ends.

**Extensions**

* 1a. The list is empty.

    Use case ends.

* 2a. TuitiONE detects an error in entered command.
    * 2a1. TuitiONE requests CSO to input a valid command.
    * 2a2. CSO enters new command.
      Steps 2a1-2a2 are repeated until the data entered are correct.

    Use case resumes at step 3.

* 2b. Lesson does not exist in TuitiONE.
    * 2b1. TuitiONE informs that there does not exist such a Lesson.

    Use case ends.

##### UC09: Update a specific Student’s Details

**MSS**

1. CSO finds a student to update from the existing list, by <ins>looking up student(s) (UC03)</ins>, or by <ins>filtering student(s) by their grade (UC04)</ins>.
2. CSO enters the student and the details to update.
3. TuitiONE reflects the updated details of the student.

    Use case ends.

  **Extension**

* 2a. TuitiONE detects an error in entered command.
    * 2a1. TuitiONE requests CSO to input a valid command.
    * 2a2. CSO enters new command.
      Steps 2a1-2a2 are repeated until the data entered are correct.

    Use case resumes at step 3.

* 2b. The student requested to edit is not present in the list.
    * 2b1. TuitiONE informs that there is no such student.

    Use case ends.

##### UC10: Enroll a Student to a Lesson

**MSS**

1. CSO finds a student to enroll from the existing list, by <ins>looking up student(s) (UC03)</ins>, or by <ins>filtering student(s) by their grade (UC04)</ins>.
2. CSO finds a lesson for the student to enroll to based on the existing list, or by <ins>filtering lesson(s) by their grade and/or subject (UC04)</ins>.
3. CSO enters a student and a lesson to enroll the student to the said lesson.
4. TuitiONE adds the student to the lesson.

    Use case ends.

**Extensions**

* 3a. TuitiONE detects an error in entered command.
    * 2a1. TuitiONE requests CSO to input a valid command.
    * 2a2. CSO enters new command.
      Steps 2a1-2a2 are repeated until the data entered are correct.

    Use case resumes at step 4.

* 3b. TuitiONE cannot find the lesson.
    * 3b1. TuitiONE requests CSO to enter a valid lesson.
    * 3b2. CSO enters new command.
      Steps 3b1-3b2 are repeated until the data entered are valid.

    Use case resumes at step 4.

* 3c. TuitiONE cannot find the student.
    * 3c1. TuitiONE requests CSO to enter a valid student.
    * 3c2. CSO enters new command.
      Steps 3c1-3c2 are repeated until the data entered are valid.

    Use case resumes at step 4.

* 3d. Student is already enrolled to lesson.
    * 3d1. TuitiONE informs that student is already enrolled to lesson.

    Use case ends.

##### UC11: Unenroll a Student from a Lesson

**MSS**

1. CSO finds a student to unenroll from the existing list, by <ins>looking up student(s) (UC03)</ins>, or by <ins>filtering student(s) by their grade (UC04)</ins>.
2. CSO finds a lesson for the student to unenroll from based on the existing list, or by <ins>filtering lesson(s) by their grade and/or subject (UC04)</ins>.
3. CSO requests for student to be unenrolled from the lesson.
4. TuitiONE removes the student from the lesson.

    Use case ends.

**Extensions**

* 3a. TuitiONE detects an error in entered command.
    * 3a1. TuitiONE requests CSO to input a valid command.
    * 3a2. CSO enters new command.
      Steps 3a1-3a2 are repeated until the data entered are correct.

    Use case resumes at step 4.

* 3b. TuitiONE cannot find the lesson.
    * 3b1. TuitiONE requests CSO to enter a valid lesson.
    * 3b2. CSO enters new command.
      Steps 3b1-3b2 are repeated until the data entered are valid.

    Use case resumes at step 4.

* 3c. TuitiONE cannot find the student.
    * 3c1. TuitiONE requests CSO to enter a valid student.
    * 3c2. CSO enters new command.
      Steps 3c1-3c2 are repeated until the data entered are valid.

    Use case resumes at step 4.

* 3d. Student is not enrolled to lesson.
    * 3d1. TuitiONE informs that student is not enrolled to lesson.

    Use case ends.

##### UC12: Review Commands

**MSS**

1. CSO enters help.
2. TuitiONE provides the basic commands, as well as the user guide link.

    Use case ends.

##### UC13: View Lesson Roster

**MSS**

1. CSO views the current list of lessons, or <ins>filter lesson(s) by their grade and/or subject (UC04)</ins>.
2. CSO requests to view the roster of a specific lesson.
3. TuitiONE presents the lesson roster.

    Use case ends.

**Extensions**

* 2a. TuitiONE detects an error in entered command.
    * 2a1. TuitiONE requests CSO to input a valid command.
    * 2a2. CSO enters new command.
      Steps 2a1-2a2 are repeated until the data entered are correct.

    Use case resumes at step 3.

* 2b. There are no students enrolled in the lesson.
    * 2b1. TuitiONE informs that there are no students in the lesson roster.

    Use case ends.

#### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
    * Performance requirements: the system should respond within 2 seconds.
3. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands)
   should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Technical requirements: The system should work in both 32-bit and 64-bit environments.
5. Quality requirements:
    * User interface not produce excessive colour changes/flashing on command execution.
    * The user interface should use readable text styling, i.e. appropriate size and font.
    * All string output must be in UTF-8 encoding.

#### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **CSO**: Customer Service Officer
* **GUI**: Graphical User Interface

--------------------------------------------------------------------------------------------------------------------

### **Appendix B: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

#### Launch and Preferences

1. Initial launch

    * Download the jar file and copy into an empty folder

    * Double-click the jar file<br>
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

    * Resize the window to an optimum size. Move the window to a different location. Close the window.

    * Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

#### Adding a student

1. Adding a new student with a unique name

    * Prerequisites: There is no student with the name "John Doe" in the TuitiONE application.

    * Test case: `add n/John Doe p/91234567 e/jd@gmail.com a/John street, block 123, #01-01 g/P2`<br>
      Expected: New student with specified details is added to the student list. Details of the added student are shown in the status message.

    * Test case: `add n/Peter Parker p/92345678 e/pp@gmail.com a/Peter street, block 123, #01-01`<br>
      Expected: No student is added. Proper command format is shown in the status message.

    * Other incorrect add commands to try: use the `add` command while missing out one of the following required fields (`n/`, `p/`, `e/`, `a/` and `g/`). <br>
      Expected: No student is added. Proper command format is shown in the status message.

2. Adding a new student with a duplicate name

    * Prerequisite: There is a student with the name "John Doe" in the TuitiONE application. Otherwise, replace the name "John Doe" in the test case with a name of a student that is already added to the TuitiONE application.

    * Test case: `add n/John Doe p/93456789 e/jd@gmail.com a/John street, block 123, #01-01 g/P2`<br>
      Expected: No student is added. Error message stating the student already exists is displayed in the status message.

    * Test case: `add n/John Doe p/99999999 e/jd3000@gmail.com a/John street, block 500, #02-02 g/S5`<br>
      Expected: No student is added. Error message stating the student already exists is displayed in the status message.

3. Adding a new student with any incorrect input

    * Test case: `add n/Jane Doe p/222 e/jd4000@gmail.com a/Jane street, block 500, #02-02 g/S5`<br>
    Expected: No student is added. An alert message corresponding to the wrongly entered parameter (in this case, parent contact number) will be shown in the message box to remind the user of the correct command format. <br>
      _Note: Only the alert message corresponds to the first encountered incorrect parameter will be shown. User is expected to correct the input parameters one by one._

#### Deleting a student

1. Deleting a student while all students are being shown

    * Prerequisites: Multiple "Students" shown in the list of students.

    * Note: We will be using index `1` to conduct manual testing for positive tests, but feel free to test with any valid index (any positive integer shown in the student list).

    * Test case: `delete 1`<br>
      Expected: `Student` of index `1` is deleted from the list. `Student` will be unenrolled from the `Lesson`, and `Lesson` with `Student` inside will decrease its size by `1`. Details of the deleted student shown in the status message.

    * Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message.

    * Other incorrect delete commands to try: `delete`, `delete x`, `delete y` (where x is larger than the list size, and y is a negative integer)<br>
      Expected: Similar to previous.

#### Listing all students and lessons

1. Listing all students and lessons when a filtered list of students is shown

    * Prerequisites: A filtered list of students is shown

    * Test case: `list`<br>
      Exptected: Status message will state that all students and lessons are listed. The student and lesson list will refresh and show all students and lessons.

2. Listing all students and lessons when all students and lessons are shown 

   * Test case: `list`<br>
      Exptected: Status message will state that all students and lessons are listed. The student and lesson list will refresh and show all students and lessons.

#### Editing a student

1. Editing a student's name

    * Prerequisites: List all students using the `list` command. Multiple students in the list.
    
    * Test case: `edit 1 n/Alex Ye`<br>
      Expected: First student's name is edited. The message box displays a message stating that the command has succeeded, with details of the edited student shown.
      
    * Test case: `edit 1 n/`<br>
      Expected: First student's name is not edited. The message box displays a message alerting the user of the constraints on names.
      
    * Other incorrect edit name commands to try: `edit 1 n/y` (where y is not an alphanumeric character, `edit 1 n/x` (where x is longer than 150 characters)<br>
      Expected: Similar to previous

1. Editing a student's parent contact

    * Prerequisites: List all students using the `list` command. Multiple students in the list.
    
    * Test case: `edit 1 p/98765432`<br>
      Expected: First student's parent contact is edited. The message box displays a message stating that the command has succeeded, with details of the edited student shown.
      
    * Test case: `edit 1 p/`<br>
      Expected: First student's parent contact is not edited. The message box displays a message alerting the user of the constraints on parent contact numbers.
      
    * Other incorrect edit parent contact commands to try: `edit 1 p/abc`, `edit 1 p/x` (where x is a number shorter than 8 digits), `edit 1 p/x1111111` (where x is a number that is not '6', '8' or '9')<br>
      Expected: Similar to previous
      
1. Editing a student's email

    * Prerequisites: List all students using the `list` command. Multiple students in the list.
    
    * Test case: `edit 1 e/alexye@gmail.com`<br>
      Expected: First student's email is edited. The message box displays a message stating that the command has succeeded, with details of the edited student shown.
      
    * Test case: `edit 1 e/`<br>
      Expected: First student's email is not edited. The message box displays a message alerting the user of the constraints on emails.
      
    * Other incorrect edit email commands to try: `edit 1 e/abc`, `edit 1 e/!a@example.com` <br>
      Expected: Similar to previous
      
1. Editing a student's address

    * Prerequisites: List all students using the `list` command. Multiple students in the list.
    
    * Test case: `edit 1 a/Blk 20 Hello Street #01-01`<br>
      Expected: First student's address is edited. The message box displays a message stating that the command has succeeded, with details of the edited student shown.
      
    * Test case: `edit 1 a/`<br>
      Expected: First student's address is not edited. The message box displays a message alerting the user of the constraints on addresses.
      
    * Other incorrect edit address commands to try: `edit 1 a/Blk 20/ Hello Street #01-01`<br>
      Expected: Similar to previous
      
1. Editing a student's grade

    * Prerequisites: List all students using the `list` command. Multiple students in the list.
    
    * Test case: `edit 1 g/S2`<br>
      Expected: First student's grade is edited. The message box displays a message stating that the command has succeeded, with details of the edited student shown. If the student was enrolled in any lessons in his previous grade, applying this edit will unenroll him from those lessons. The lessons he was enrolled in will also reflect a decrease in class size by 1.
      
    * Test case: `edit 1 g/`<br>
      Expected: First student's grade is not edited. The message box displays a message alerting the user of the constraints on grade levels.
      
    * Other incorrect edit grade commands to try: `edit 1 g/A5`, `edit 1 g/p`<br>
      Expected: Similar to previous
      
1. Editing a student's remarks

    * Prerequisites: List all students using the `list` command. Multiple students in the list.
    
    * Test case: `edit 1 r/discounted`<br>
      Expected: Remark is added onto the first student's existing remarks. The message box displays a message stating that the command has succeeded, with details of the edited student shown.
      
    * Test case: `edit 1 r/`<br>
      Expected: First student's remarks is not edited. The message box displays a message alerting the user of the constraints on remarks.
      
    * Test case: `edit 1 r/test1 r/test2 r/test3 r/test4 r/test5` (assuming first student already has 1 remark tagged)<br>
      Expected: First student's remarks is not edited. The message box displays a message alerting the user of that only a maximum of 5 remarks can be tagged to a student.
      
    * Other incorrect edit remarks commands to try: `edit 1 r/test test`, `edit 1 r/!`<br>
      Expected: Similar to previous
      
    * Test case: `edit 1 dr/discounted` (assuming 'discounted' remark already tagged to first student)<br>
      Expected: Remark is deleted from student's existing remarks. The message box displays a message stating that the command has succeeded, with details of the edited student shown.

    * Test case: `edit 1 dr/`<br>
      Expected: First student's remarks is not edited. The message box displays a message alerting the user of the constraints on remarks.

    * Test case: Other incorrect edit remarks commands to try: `edit 1 dr/test test`, `edit 1 dr/!`<br>
      Expected: Similar to previous
          
    * Test case: `edit 1 dr/notTaggedToStudent` (assuming 'notTaggedToStudent' remark is not tagged to first student)<br>
      Expected: First student's remarks is not edited. The message box displays a message alerting the user that the remark he or she wishes to delete does not exist.
      
1. Editing multiple fields and misc scenarios

    * Test case: `edit 1 n/Alex p/87654321 g/s4`<br>
      Expected: First student's name, parent contact and grade are edited. The message box displays a message stating that the command has succeeded, with details of the edited student shown.
      
    * Test case: `edit 0 n/Alex`<br>
      Expected: First student is not edited. The message box displays a message alerting the user that the given index is not valid.

#### Enrolling a student into a lesson

1. Enrolling a `Student` into a `Lesson`, while the students and lessons are being shown

    * Prerequisites: 
        * Multiple `Students` and `Lessons` shown in the list of students and lessons. `Students` must not currently be enrolled in the `Lesson` the user wishes to enroll the student in.
        * `Student` must have the same `grade` as the `Lesson` to be enrolled in
        * `Student` must not currently be enrolled in `Lesson` of interest
        * `Student` must not have any existing classes that clash with the duration in `Lesson`
        * `Student` must currently be enrolled in fewer than `10` lessons
        * `Lesson` must currently have fewer than `15` students enrolled inside the lesson
        * Should any of the above conditions not be met, the `Student` will not be able to be enrolled in the `Lesson` of interest

    * Note: 
        * We will be using index `2` to conduct manual testing for positive tests, but feel free to test with any valid index (any positive integer shown in the student and lesson lists).
        * In this case, we will be using the sample data given when loading up **TuitiONE** for the first time (delete tuitione.json file if it is not your first time). 
        * The `Student` of index `2` (Bernice Yu of grade P4) will be enrolled into `Lesson` of `index` 2 (Math-P4-Wed-1800).<br>

    * Test case: `enroll 2 l/2`<br>
    Expected: `Student` of index `2` is enrolled in `Lesson` of index `2` . Details of the student and lesson enrolled in shown in the status message.

    * Test case: `enroll 0 l/0`<br>
    Expected: No student is enrolled in any lesson. Error details shown in the status message.

    * Other incorrect enroll commands to try: `enroll`, `enroll 1`, `enroll 1 l/0`, `enroll 0 l/1`, `enroll x l/y`, `enroll y l/x` (where x is larger than the list size, and y is a negative integer)<br>
    Expected: Similar to previous.

#### Unenrolling a student

1. Unenrolling a `Student` from a `Lesson`, while all students and lessons are being shown

    * Prerequisites: List all students and lessons using the `list` command. Multiple "Students" and "Lessons" in the list.

    * Note:
        * We will be using index `1` to conduct manual testing for positive tests, but feel free to test with any valid index (any positive integer shown in the student and lesson lists).
        * In this case, we will be using the sample data given when loading up **TuitiONE** for the first time (delete tuitione.json file if it is not your first time).
        * The `Student` of index `1` (Alex Yeoh of grade S1) is enrolled in the `Lesson` of `index` 3 (Science-S1-Fri-1330).
        * The `Lesson` of index `1` is (English-P2-Mon-0900).<br>

    * Test case: `unenroll 1 l/3`<br>
      Expected: The `Student` of index `1` (Alex Yeoh of grade S1) is unenrolled the `Lesson` of `index` 3 (Science-S1-Fri-1330). The details of the unenroll is displayed in the status message.

    * Test case: `unenroll 1 l/1`<br>
      Expected: The `Student` of index `1` (Alex Yeoh of grade S1) is unable to be unenrolled from The `Lesson` of index `1` (English-P2-Mon-0900). Status message stating student is not enrolled in the lesson is displayed.

    * Other incorrect enroll commands to try: `unenroll`, `unenroll 1`, `unenroll 1 l/0`, `unenroll 0 l/1`, `unenroll x l/y`, `unenroll y l/x` (where x is larger than the list size, and y is a negative integer)<br>
      Expected: An error message corresponding to the incorrect field or an error message showing the correct command format is displayed.

#### Finding a student

1. Finding a student while all students are being shown
   
    * Prerequisites: List all students using the `list` command. Multiple "Students" in the list.

    * Test case: `find c`<br>
        Expected: All students whose name that starts with the letter `c` are shown in 
   the student list. Lesson list will not be changed. No student shown if there is no student whose name starts with 
   the letter `c`. An update message showing the number of students found will also be shown in the 
   message box.<br>_Note: both first and last name of the student will be checked, to see if the student's name consists the letter `c`._
   
    * Test case: `find Alex`<br>
        Expected: All students whose name consists of `Alex` are shown in the student list. Lesson list will not be
   changed. No student shown if there is no student whose name consists of `Alex`. An update message showing 
   the number of students found will also be shown in the message box. <br>_Note: both the first and last name of the student will be checked, to see if the student's name consists of `Alex`._

    * Test case: `find -`, `find .`, `find '`<br>
      Expected: No student will be found since name should only contain alphanumeric characters and spaces. An update
message showing that `No students found.` will also be displayed in the message box. 


#### Adding a lesson

1. Adding a new lesson

    * Prerequisites: All parameters are entered correctly as according to the DG's [Add Lesson Feature](#add-lesson-feature)
and the lesson to be added does not exist in TuitiONE yet.

    * Test case: `add-l s/Science g/P2 d/Wed t/1200 c/10.50`<br>
      Expected: A new lesson `Science-P2-Wed-1200` will be added into the lesson list. Lesson list will be updated
while there is no change to the student list. An update message will also be shown in the message box to inform the user
that a new lesson is successfully added.

    * Test case: use the `add-l` command while missing out one of the following required fields (`s/`, `g/`, `d/`, `t/` and `c/`). <br>
      Expected: No lesson is added. Proper command format is shown in the status message.

1. Addition of a duplicate lesson

    * Prerequisites: The lesson to be added already exists in TuitiONE. Follow the instructions from 'Adding a new lesson' above  if the lesson has not been added.

    * Test case: `add-l s/Science g/P2 d/Wed t/1200 c/10.50`<br>
        Expected: The lesson will not be added. An alert message will be shown in 
the message box to inform the user that the lesson already exists in TuitiONE.

1. Addition of a lesson by passing in any incorrect input

    * Test case: `add-l s/Math g/A1 d/Fri t/1500 c/10.50`<br>
    Expected: No lesson is added. An alert message corresponding to the wrongly entered parameter (in this case, grade) will be shown in the message box to remind the user of the correct command format. <br>
      _Note: Only the alert message corresponds to the first encountered incorrect parameter will be shown. User is expected to correct the input parameters one by one._


#### Deleting a lesson

1. Deleting a lesson while all lessons are being shown.

    * Prerequisites: Multiple "Lessons" shown in the list of lessons

    * Note: We will be using index `1` to conduct manual testing for positive tests, but feel free to test with any valid index (any positive integer shown in the lesson list).

    * Test case: `delete-l 1`<br>
      Expected: Students currently enrolled in `Lesson` of index `1` would be unenrolled. `Lesson` of index `1` is deleted from the list. Details of the deleted lesson shown in the status message.

    * Test case: `delete-l 0`<br>
  Expected: No lesson is deleted. Error details shown in the status message.

    * Other incorrect delete commands to try: `delete-l`, `delete-l x`, `delete-l y` (where x is larger than the list size, and y is a negative integer)<br>
      Expected: Similar to previous.


#### Viewing a lesson's roster 

1. View a lesson's roster using `LESSON_INDEX` while all students and lessons are being shown

    * Prerequisites: List all students and lessons using the `list` command. Multiple `Students` and `Lessons` in the 
list with some `Students` already enrolled in some of the `Lessons`.

    * Test case: `roster 2`<br>
        Expected: Only students who are enrolled in the lesson identified by the lesson index of `2` will be shown in 
the student list. The lesson list will be updated to show the lesson identified by the lesson index of `2`. No student
or lesson shown if there is no student enrolled in the lesson identified by the lesson index of `2`. An update message
showing the number and the name of the students who are enrolled in the lesson will also be displayed in the message box.

    * Test case: `roster 100` (passing in a a `LESSON_INDEX` that is larger than the number of `LESSONS`)<br> 
      Expected: No roster will be applied. An error message will be shown in the message box, warning the user to only
use a valid `LESSON_INDEX`.

    * Incorrect roster commands to try: `roster`, `roster a`, `roster -`, 
`roster LESSON_CODE`<br>
        Expected: No roster will be applied. An alert message will be shown in the message box, warning the user to
follow the command format by using `LESSON_INDEX`.


#### Filtering

1. Filtering by grade

    * Note: We will be using `S1` to conduct manual testing for positive tests, but feel free to test with any grade you wish (from `P1` to `S5`).

    * Test case: `filter g/S1`<br>
      Expected: Only students and lessons of grade `S1` are shown in the student list and lesson list respectively. No student or lesson shown if there are none of grade `S1`. An update message showing the number of students and lessons found will also be shown in the message box.
      
    * Test case: `filter g/s1`<br>
      Expected: Similar to previous.
      
    * Test case: `filter g/J8`<br>
      Expected: No filter is applied, and no change to both lists. The message box displays a message alerting the user of the constraints of the grade condition to be inputted. 
      
    * Other incorrect filter by grade commands to try: `filter g/abc`, `filter g/123`, `filter g/g/`<br>
      Expected. Similar to previous.
      
    * Test case: `filter s2`<br>
      Expected: No filter is applied, and no change to both lists. The message box displays a message alerting the user that the command format inputted is invalid, along with a description of what the filter command does, its parameters, and an example usage.

1. Filtering by subject

    * Note: We will be using the `English` subject to conduct manual testing for positive tests.

    * Test case: `filter s/English`<br>
      Expected: Only lessons with subject matching `English` are shown in the lesson list. No lessons shown if none have matching subject. No change to student list. An update message showing the number of lessons found will also be shown in the message box.
      
    * Test case: `filter s/english`<br>
      Expected: Similar to previous.
      
    * Test case: `filter english`<br>
      Expected: No filter is applied, and no change to both lists. The message box displays a message alerting the user that the command format inputted is invalid, along with a description of what the filter command does, its parameters, and an example usage.
      
1. Filtering by both grade and subject

    * Note: It will be good to add more lessons of grade `S1` but of different subject (e.g. English, Math etc.) to better test this feature. (refer to [Add Lesson Feature](#add-lesson-feature) on how to use the add lesson feature)
    
    * Test case: `filter s/English g/S1`<br>
      Expected: Only students of grade `S1` will be displayed in the student list. Only lessons with subject matching `English` and of grade `S1` will be displayed in the lesson list. No lessons or students shown if none have matching the given filter conditions. An update message showing the number of students and lessons found will also be shown in the message box.
      
    * Test case: `filter english S1`<br>
      Expected: No filter is applied, and no change to both lists. The message box displays a message alerting the user that the command format inputted is invalid, along with a description of what the filter command does, its parameters, and an example usage.

#### Help

1. Getting help by entering help command

    * Test case: `help` <br>
      Expected: Status message should inform you that a help window is opened. A help window that displays all the commands of TuitiONE will appear.

2. Getting help by using the help button

    * Test case: Click on the help button in the top left of the TuitiONE application. A dropdown list with the option "Help F1" will appear. Click on the "Help F1" option.
      <center>
      <img src="images/DeveloperGuideImage/help_ui.png"/>
      </center><br>
    Expected: A help window that displays all the commands of TuitiONE will appear.

3. Getting help by pressing the F1 key

    * Prerequisite: TuitiONE is the active window on your computer. If you are unsure, click on the TuitiONE application to make it the active window.

    * Test case: Press the F1 key on your keyboard.
      Expected: A help window that displays all the commands of TuitiONE will appear.

#### Clearing data

1. Clearing all data with students and lessons present

    *  Prerequisites: There should be students and lessons present in the application. Use `list` to view all the entities present.

    *  Test case: `clear`<br>
       Expected: Status messages should inform you that all information is cleared. Both panels on the application should be empty, i.e. there should be no more students and lessons. Checking the storage file in `./data/tuitione.json` should show no entities present.

1. Clearing all data with no data present

    *  Prerequisites: There should be no students and lessons present in the application. Use `list` to view all the entities present.

    *  Test case: `clear`<br>
       Expected: Same as in previous test scenario. Having no data present should not yield any difference in output.

#### Exiting application

1. Exiting using the window close button

    *  Test case: Press the close button on the window of the application.<br>
       Expected: The window should close.

1. Exiting using the application's exit button

    *  Test case: Click on the file button on the top left of the application. A dropdown list with one option of `exit` should be present. Click on that button.<br>
       <center>
       <img src="images/DeveloperGuideImage/exit_ui.png"/>
       </center><br>
       Expected: The UI should display the elements correctly and the window should close.<br>

#### Managing data externally

1. Loading the application with no data files present

    *  Prerequisites: There should be no files present in the directory you are going to work with, apart from `TuitiONE.jar`.

    *  Test case: Run the application.<br>
       Expected: Upon UI loading, verify the directory has these few files `tuitione.log.0`, `preferences.json`, `config.json` (if you also have `tuitione.log.0.lck`, that is okay as well).

    *  Test case (continued): Then type in the `list` command and hit `ENTER`.<br>
       Expected (continued): After the UI responds, there should now a new directory `data` being created and there is a `tuitione.json` file present.

1. Verifying that data is stored

    *  Prerequisites: There should be files already initialised in your working directory and there should be entities present in the application. Commands in the application run as expected.

    *  Test case: Perform a positive (successful) insertion/deletion/modification of the entities. See the [User Guide](https://ay2122s1-cs2103t-f13-4.github.io/tp/UserGuide.html#features) for more on the relevant commands that can perform such. After such, verify the `./data/tuitione.json` file to see if the contents match the changes made by your command.<br>
       Expected: After the UI responds, there should now a new directory `data` being created and there is a `tuitione.json` file present.

1. Modifying saved data, but preserving its correctness to the application's constraints

    *  Prerequisites: There should be files already initialised in your working directory and there should be entities present in the application. Commands in the application run as expected.

    *  Test case: Insert/Delete/Modify an entry in `./data/tuitione.json` that produces a data file that conforms to the constraints mentioned in the application. Below are some critical constraints that are unique in the storage file. For other possible inputs to test with, view the other sections presented under [Appendix B](#appendix-b-instructions-for-manual-testing).<br>
       Expected: The application should load up as per normal, and the new changes in the storage file is presented in the UI.

       :information_source: **Note:**

       * Particulars in a `student` object must conform to the constraints of the application.

       * Particulars in a `lesson` object must conform to the constraints of the application.

       * The field `lessonCodes` in a `student` represents the `Lessons` the student is enrolled in an encoded form.<br>
         A `lesson` can be converted to its encoded form by creating a string with the format `subject-grade-day-startTime`.<br>
         A `lessonCode` of `"Math-P4-Wed-1800"` represents a json `lesson` object of (`price` can be of any amount that meets the constraints):
         ``` json
         {
           "subject" : "Math",
           "grade" : "P4",
           "startTime" : "1800",
           "day" : "Wed",
           "price" : 15.9
         }
         ```
         Do note once again that enrolling a student using the data storage must fulfil the enrollment constraints.<br>
         Review [Appendix B](#appendix-b-instructions-for-manual-testing) for the remaining constraints present.<br>

    *  Test case (continued): Then type in the `list` command and hit `ENTER`.<br>
       Expected (continued): After the UI responds, verify that `./data/tuitione.json` is not modified, and holds the changes you made in the initial step.

1. Modifying saved data, but losing its correctness to the application's constraints

    *  Prerequisites: There should be files already initialised in your working directory and there should be entities present in the application. Commands in the application run as expected.

    *  Test case: Modify an entry in `./data/tuitione.json` that violates the entity and/or relationship constraints, see the previous test scenario's explanation to perform a counter modification to the data file.<br>
       Expected: Upon application and UI load up, the application should be presented with no data present, i.e. no lessons or students. The content in `./data/tuitione.json` should not be modified yet (see next step).

    *  Test case (continued): Then type in the `list` command and hit `ENTER`.<br>
       Expected (continued): After the UI responds, verify that `./data/tuitione.json` holds no entities.
