---
layout: page
title: Yukun's Project Portfolio Page
---

# Project: Notor

Notor aims to help mentors who wish to informally keep tabs on their mentees, by providing a quick way to take notes and
mantain records on their mentees. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is
written in Java, and has about 10 kLoC.

## My Contributions

### **Code contributed**

My code contributed can be found at
this [RepoSense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?tabOpen=true&tabType=authorship&tabAuthor=Yukun99&tabRepo=AY2122S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipFileTypes=docs~functional-code~test-code)
.

### **New Features**:

* [Trie](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/106)
    * What it does: Allows commands and tags to be stored into a Trie data structure to be autocompleted after each
      keystroke.
    * Justification: Autocompletion was a vision we had for ease of use in the early stages of planning for the app.
      However, with command aliases shortening this and the lack of time to implement these features, we have to shelf
      the feature or at the very least put it off to a further date beyond the last submission.
    * Highlights: It should build the groundwork for any member of the group to easily implement related features in
      future. In fact, autocomplete can even be used in other fields, not simply tags and commands.
    * Credits: No code reused, but the data structures learnt during the CS2040S module were useful in knowing
      immediately how to implement tries.
* [Basic Note Command](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/49)
    * What it does: Allows users to apply notes to persons stored in Notor with a simple command.
    * Justification: We wanted mentors to be able to easily attach notes to students to keep track of them, be it
      individually or as a group. This was a proof of concept that was already done as part of the CS2103T AB-3
      tutorial, thus it was a trivial matter to add it into our application for preliminary testing. It also allowed for
      code to be reused in later portions of the app.
    * Highlights: It adds the Note class, which is used in the Person class as a note field. This includes the parsing
      rules that we use for deciding if the user entered note is valid. This is used later on to make our full fledged
      note GUI.
    * Credits: [AB-3 AddRemark Command Tutorial](https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html)
* [Archive/Unarchive Commands](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/144)
    * What it does: Allows users to archive unused persons in Notor.
    * Justification: We wanted mentors with many different mentees to be able to easily hide users that are less
      relevant to their daily work, yet retain the data for reference or future contact, since many of their mentees may
      be valuable industry contacts. Archiving allows them to put these users away if needed, and bring them back up if
      required as well.
    * Highlights: Using our display on the right of the app, we are able to use filters on the person list. From then
      on, users can choose to either archive all displayed users or archive a specific user using the displayed index.
      This allows users to do rougher operations to save time on larger groups, as well as gives them the granular
      control they require for fine tuning their list of mentees. As for unarchiving, the listarchive command allows
      users to see the archived users, and then choose to unarchive them from there as required. Archived persons are
      written to file so that the data remains for future reference as well.
    * Credits: No code reused, but it was inspired by a similar archiving feature that I added for the CS2103T module
      individual project.

### **Enhancements to existing features**

* Revised code throughout to improve code quality
    * [Refactor Command Structure](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/101)
        * What it does: Simple refactoring of how commands are structured in Notor to allow us to add more commands.
        * Justification: The default AB-3 command structure did not allow us to add the commands we wanted in a way that
          made sense and was easy to use.
        * Highlights: Split commands into Parser, Command and Executor classes. This allows easy separation of
          parsing/object creation, command creation and command execution. This lets the team easily see what the
          classes do. The general information that identities a command, i.e. its command words and usage guide, are
          also situated in the command class itself, and can be easily referenced or changed without seeing unnecessary
          execution or parsing related code.
* General functionality
    * [Update Command Refactoring](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/131)
        * What it does: Adds command aliases and alias recognition functionality to the parser, as well as fix parsing
          to allow more values to be accepted.
        * Justification: Aliases allow commands to be typed faster, while allowing more values allows names to more
          easily fit our constraints.
    * [Refactor Command Parsing](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/134)
        * What it does: Update commands to follow our new command structure when parsed.
        * Justification: We changed the command structure to accomodate more commands and the older commands required
          updates to the code to actually be parsed properly.
* Remove deprecated code
    * [Address Field](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/56)
        * Justification: Due to this being an app for mentors, we do not expect them to refer to/access their mentees'
          address often. Thus, the feature was removed to save on valuable UI space.

### **Project management**

* [Standardise command structure for Notor](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/89)
* Standardise commit/programming messages
* Standardise code formatting/structure
* [Standardise user stories in Developer Guide](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/61)
* Label pull requests correctly for clarity when referring
* General comments for code quality when reviewing PRs
* General reminders for deadlines where possible
* Fix bugs concerning low level/backend portions of the project

### **Documentation**

#### **Developer Guide**

```markdown
Thank you for your interest in the developing of Notor! This is an open-source project aimed at helping mentors take
quick, efficient notes to facillitate effective and efficient mentoring of many mentees. The design principles
scaffolding Notor are as follows. In particular, we tackle the needs of mentor professors, who tend to be busy and are
assigned mentees they are unlikely to personally know or even contact often outside of the mentor relationship. Key
features of Notor which scaffold this are:
AB-3)_](https://nus-cs2103-ay2122s1.github.io/tp/DeveloperGuide.html) project. All features we have are in addition to
those already present in AB-3. Removed features may or may not be listed as well.

* Table of Contents {:toc}

* **Command structure**: The order in which parameters and command words must be written in order for the command to be
  correctly parsed
* **Dummy data**: Sample data used in testing or example data present on first launch of application
* **Group**: A container containing `Person` objects with shared traits that is created by the user
* **Key power features**: Essential features that will be used often when running the software application
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Metadata**: Personal data about a `Person` object
* **Note**: A general description of each `Person` to record their activities, with last edit timestamp attached
* **Pin**: Fixing a `Person` to the top of the current list of `Person` objects or a `Group`
* **Subgroup**: A child of a `Group` used to store multiple persons based on a more specific category than `Group`. A **
  Subgroup** can be created by specifying the parent group of the **Subgroup**. A person in a **Subgroup** is
  automatically in the parent `Group` as well
* **Tag**: A string descriptor attached to `Group` objects or `Person` objects
* **Ungrouped**: Used to describe a `Person` object with no grouping

--------------------------------------------------------------------------------------------------------------------
The **Architecture** of our iteration is built upon AB-3. Please refer to the AB-3 **Architecture**
section for the general Architectural design of the app. Only changes will be listed here.

### Logic Changes

#### Command Changes

![CommandClassDiagram](images/ParserClasses.png)

Due to the addition of many new commands and refactoring of the command structure, we have amended the classes related
to managing commands.

* `Parser` now takes in generic `T extends Command` since we have multiple types of commands, each with their own unique
  parser
* `Executor` instances are created by each `Command` class, which then handle the actual execution of commands and
  returning of `CommandResult`
* Commands now come in 3 general types, `PersonCommand`, `GroupCommand` and `Command`
    * `PersonCommand` operates on `Person` objects
    * `GroupCommand` operates on `Group` objects
    * `Command` operates without either a `Person` or `Group` object
* `Parser` and `Executor` classes come in the same 3 categories as `Command` classes
* `NotorParser` now parses both the `commandWord` and `subCommandWord` for user commands
    * `commandWord` refers to either `Person`, `Group` or one of the object agnostic commands
    * `subCommandWord` refers to an operation that can be carried out on a `Person` or `Group`, such as `find` or `tag`

New Workflow for Adding Commands:

1. Create a `XYZCommand` class that extends either `PersonCommand`, `GroupCommand` or simply `implements Command`.
2. Create a `XYZCommandParser` class that extends the same type of `Parser` as the `Command` above is.
3. Add the new `XYZCommandParser` to the `parse()` method in `NotorParser`.
4. Create a `XYZCommandExecutor` class that extends the same type of `Executor` as the `Command` from step 1.
5. Implement all required methods and ensure all fields used by the methods are present.

### Model Changes

*(placeholder API for now, will update to our own link later when implemented.)*

**
API** : [`Model.java`](https://github.com/se-edu/Notor-level3/tree/master/src/main/java/seedu/address/model/Model.java)

![ModelClassDiagram](images/ModelClassDiagram.png)

* `Person` does not contain the `Address` field anymore.
* `Person` contains a new `Note` field.
* `Person` contains `HashSet<String>` containing `SuperGroup` and `SubGroup` names for display purposes.
    * These are just `String` objects, and are not actual references to `Group` objects.
* `Name` must start with an alphabet character.
    * This is due to the fact that our parser will be unable to differentiate between indexes and names when parsing
      commands.
* `Name` may contain `-` and `.` characters.
    * This is to account for people with these special characters in their names.

Here is the better class structure to be implemented:
![ModelClassDiagram2](images/BetterModelClassDiagram.png)

* `Trie` allows tags to be autocompleted as commands are entered.
* Storing `String` objects in a `Trie` in Notor allows all tags to only get created once instead of once per object.
* Storing tags as `String` objects in a trie is simpler than a dedicated `Tag` class.
* This feature is planned for a future update, as the `Trie` data structure has already been implemented.
* This feature could also not be implemented due to short form commands already being very user friendly.

### Storage component

*(placeholder API for now, will update to our own link later when implemented.)*

**
API** : [`Storage.java`](https://github.com/se-edu/Notor-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

![StorageClassDiagram](images/StorageClassDiagram.png)

The `Storage` component,

* now includes a new `Archive` Storage component
* `Archive` allows users to temporarily remove `Person`s from their Address Book

### Common classes

**
API** : [`Trie.java`](https://github.com/AY2122S1-CS2103T-W08-1/tp/blob/master/src/main/java/seedu/address/commons/core/trie/Trie.java)

* Allows grouping and autocompletion of `Tag` and `Command` objects.
* Supports addition and deletion of items.
* Supports finding of first item.
* Supports finding of first item that starts with specified keyword. The proposed undo/redo mechanism is facilitated
  by `VersionedNotor`. It extends `Notor` with an undo/redo history, stored internally as an `NotorStateList`
  and `currentStatePointer`. Additionally, it implements the following operations:
  Step 1. The user launches the application for the first time. The `VersionedNotor` will be initialized with the
  initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitNotor()`, causing the modified state of the address book after the `delete 5` command executes to be
saved in the `NotorStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.
The `redo` command does the opposite — it calls `Model#redoNotor()`, which shifts the `currentStatePointer` once to the
right, pointing to the previously undone state, and restores the address book to that state. as `list`, will usually not
call `Model#commitNotor()`, `Model#undoNotor()` or `Model#redoNotor()`. Thus, the `NotorStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitNotor()`. Since the `currentStatePointer` is not pointing at
the end of the `NotorStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no
longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications
follow.
![CommitActivityDiagram](images/CommitActivityDiagram.png)
**Target user profile**: mentor professors

* has groups of contacts that have different needs Priorities:<p>

* High - must have<p>
* Medium - nice to have<p>
* Low - unlikely to have<p>
* Default - already implemented)

|As a …                                                                                      |I want to …                                                                                                   |So that I can …                                            |Priority    |Status     |When?         |
|--------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|------------|-----------|--------------|
|on the go user                                                                              |add notes without wifi or internet access                                                                     |use the app anywhere                                       |Default     |           |Iteration 1.2 |
|new user                                                                                    |have dummy data                                                                                               |see what my entries look like                              |Default     |           |Iteration 1.2 |
|new user                                                                                    |remove all dummy entries easily                                                                               |start doing work quickly                                   |Medium      |           |Iteration 1.2 |
|general user, mentor professor, module professor                                            |take notes with timestamps                                                                                    |see my notes chronologically                               |High        |           |Iteration 1.2 |
|general user, mentor professor, module professor                                            |edit the groups or tags of a student                                                                          |                                                           |High        |           |Iteration 1.2 |
|mentor professor                                                                            |group the students based on the mentoring subjects                                                            |tag or comment on each group separately                    |High        |           |Iteration 1.2 |
|general user, mentor professor, module professor                                            |have easy-to-remember commands for inputting information                                                      |                                                           |High        |           |Iteration 1.2 |
|general user                                                                                |delete groups/subgroups                                                                                       |                                                           |High        |Not started|Iteration 1.2 |
|experienced user, general user                                                              |add tags cumulatively                                                                                         |not retype my old tags                                     |High        |Not started|Iteration 1.2 |
|new user                                                                                    |see clear error messages that explains how to enter the correct command                                       |learn the right syntax from my errors                      |High        |Not started|Iteration 1.2b|
|experienced user, general user, mentor professor, module professor                          |edit previous notes I have taken                                                                              |                                                           |High        |           |Iteration 1.2b|
|mentor professor, module professor, new user                                                |have an easily accessible help page                                                                           |                                                           |High        |           |Iteration 1.2b|
|experienced user, general user, mentor professor, module professor, new user, on the go user|search by tag/category                                                                                        |find students based on tag/category                        |High        |Not started|Iteration 1.2b|
|general user, mentor professor, module professor                                            |have notes attached to categories                                                                             |save notes relevant to a whole group                       |Medium      |           |Iteration 1.2b|
|general user                                                                                |create general notes                                                                                          |take down any thoughts I have on the fly                   |Medium      |Not started|Iteration 1.2b|
|new user                                                                                    |see a confirmation message if I choose to delete something                                                    |avoid accidental deletions                                 |Medium      |           |Iteration 1.2b|
|general user                                                                                |display all of the user information in an easy to reference format                                            |read large amounts of information at once easily           |Medium      |           |Iteration 1.3 |
|mentor professor                                                                            |see the last time I contacted a student                                                                       |know if I need to check up on them                         |Medium      |           |Iteration 1.3 |
|experienced user                                                                            |see personal metadata such as number of high-priority students & number of contacts                           |determine my own usage                                     |Low         |           |Iteration 1.3b|
|general user, mentor professor, module professor, on the go user                            |export the data to PDF & CSV / Excel                                                                          |reference the information in another format                |Low         |           |Iteration 1.3b|
|general user, mentor professor, module professor, on the go user                            |archive students that are not as relevant in my current contacts                                              |keep my mentee list short and easy to read                 |Low         |           |Iteration 1.3b|
|experienced user, module professor                                                          |set my own command aliases                                                                                    |use my own commands when I am used to them                 |Low         |           |Delay         |
|experienced user, mentor professor, module professor                                        |use shorter commands                                                                                          |save time                                                  |Medium      |           |Delay         |

### Use cases

(For all use cases below, the **System** is the `Notor` and the **Actor** is the `user`, unless specified otherwise)

1. User requests to add a note to the person
2. Notor shows a list of persons
3. User requests to add a note to a specific person in the list
4. Notor opens up a pop up dialogue for the user to type the note for the person
5. User requests to save the note to the person

* 2a. The list is empty. Use case ends.

* 3a. The given index is invalid.
    * 3a1. Notor shows an error message. Use case resumes at step 2.

**Use case: User types a command**

**MSS**

1. User starts typing a command in Notor
2. Notor shows possible commands starting with what user has typed
3. User presses tab to select the right command
4. User presses enter to execute the selected command
5. Notor <u>runs command (UC1)</u>

   Use case ends.

**Extensions**

* 2a. The typed string is not in any command.

3. A user with above average typing speed for regular English text (i.e., not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Installing a new update shall not in any way, modify or erase existing data and value from the previous version, and
   the new update should be compatible with the data produced earlier within the system.
5. Should be able to store notes in English language, and provisions shall be made to support all languages.
6. The system should be able to handle notes with at most 1000 lines without any noticeable decrease in performance, so
   that users can keep extensive notes on their mentees.
7. The user should not lose any data if the system exits prematurely.
8. The system should be able to reply to the prompt or command from the user within 3 seconds.
9. The system should be intuitive to use for a mentor professor.
10. Should ensure personal data privacy and security of data access.
11. Software testing will require the use of automated testing. The test will be deleted after successful implementation
    of the software system.
1. _{ more test cases ... }_
1. _{ more test cases ... }_
```

#### **User Guide**

```markdown
Round brackets `()` refer to COMPULSORY arguments. Square brackets `[]` refer to optional arguments.<p>
*TODO: Make command action words below link to their entries above.*

### Person

**Note**:

* For the **Create** and **List** commands, if you want to automatically add them to a group, please use the **List**
  command to make sure the `Group` you want to use the command on is displayed before using them via `GROUP_INDEX`.
* For the **Add** and **Remove** commands, please make sure that the `GROUP_NAME` is typed exactly as how it is spelt on
  the card.
  --------------------------|-------------------------------------------------------------------------------|--------------------------------------------------------------------
  **Create**                | `person (NAME) /create [p:PHONE] [e:EMAIL] [t:TAG1,TAG2,...] [g:GROUP_INDEX]`
  | `p (NAME) /c [p:phone] [e:email] [t:TAG1,TAG2,...] [g:GROUP_INDEX]`
  **Edit**                  | `person (INDEX) /edit [n:NAME] [p:PHONE] [e:EMAIL]`
  | `p (INDEX) /e [n:NAME] [p:phone] [e:email]`
  **Delete**                | `person (INDEX) /delete`
  | `p (INDEX) /d`
  **Tag**                   | `person (INDEX) /tag [t:TAG1,TAG2,...]`
  | `p (INDEX) /t [t:TAG1,TAG2,...]`
  **Untag**                 | `person (INDEX) /untag [t:TAG1,TAG2,...]`
  | `p (INDEX) /ut [t:TAG1,TAG2,...]`
  **Clear Tags**            | `person (INDEX) /cleartags`
  | `p (INDEX) / ct`
  **List Persons**          | `person /list`                                                                | `p /l`
  **List Persons in Group or SubGroup** | `person [INDEX] /list`
  | `p [INDEX] /l`
  **Find**                  | `person /find (n:QUERY)`
  | `p /f (n:QUERY)`
  **Archive**               | `person (INDEX) /archive`
  | `p (INDEX) /ar`
  **Archive All**           | `person /archive`                                                             | `p /ar`
  **List Archived Persons**       | `person /listarchive`
  | `p /lar`
  **Unarchive**             | `person (INDEX) /unarchive`
  | `p (INDEX) /uar`

### Group

Action                 | Format                                         | Short Format
-----------------------|------------------------------------------------|---------------------------------------
**Create Group**       | `group (GROUP_NAME) /create` | `g (GROUP_NAME) /c`
**Create Subgroup**    | `group (INDEX) /create n:SUBGROUP_NAME`        | `g (INDEX) /c n:SUBGROUP_NAME`
**Delete**             | `group (INDEX) /delete`                        | `g (INDEX) /d`
**Note**               | `group (INDEX) /note`                          | `g (INDEX) /n`
**List Groups**        | `group /list`                                  | `g /l`
**List Out Subgroups** | `group (INDEX) /list`                          | `g (INDEX) /l`
**Find**               | `group /find (n:QUERY)`                        | `g /f (n:QUERY)`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Before using the group actions, remember to use `g /list` or `g /find` to change the list display so that you can access the indexes of the group
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
for the **List** command, the `INDEX` argument can be either a `Group` or a `Person`, depending on what is in the list display. Change the `person /list` or `group /list`.
</div>

### General

Action     | Format   | Short Format
-----------|----------|----------
**Help**   | `help`   | `h`
**Exit**   | `exit`   | `e`
**Clear**  | `clear`  | `c`
**Export** | `export` | `exp`
```

### **Community**:

I tried to provide feedback as well as my personal input on how I would implement certain features due to my prior
experience in Java. I also tried to make sure that pull requests reviewed by me do not simply work, but are also up to a
certain coding standard. I'm quite a detail-oriented person so I try to look out for the smaller issues that might be
missed by others.

### **Tools**

I shared some external tools that may help with the conversion of items that we have in our notion docs to items that we
can actually use in our documentation files. I also helped members of the team setup PlantUML and other integrations in
IntelliJ. I provided my updated styling xml file for IntelliJ as well so that the team does not have to spend as much
time on formatting issues, and could submit pull requests with less time taken for formatting, since not all members of
the team may be used to making their code adhere to the coding standard even while they are programming.
