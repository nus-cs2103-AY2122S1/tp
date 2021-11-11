---
layout: page
title: ClassMATE User Guide
---

## Introduction

Are you having trouble with administrative work as a CS2101 tutor? Do current tools like Excel sheets or the pen and paper cause a lot of pain when trying to organize and search for students or recording class participation? Could there be a way to streamline this process while providing you a better way to visualise the information? Well, ClassMATE has it all covered!

**ClassMATE** is a **desktop app designed for you to manage student contacts and organize them into their tutorial classes and groups neatly, as well as recording of students' class participation without much hassle. Having both a Command Line Interface** ([CLI](#glossary)) and a **Graphical User Interface** ([GUI](#glossary)), it saves you time on your CS2101 administrative work while providing a pleasant experience at the same time.

This user guide for ClassMATE will teach you how to:
* Create students, classes and groups
* Organize groups in their respective classes
* Add students to a class and their respective oral presentation groups within the class
* Filter and search students by groups and classes
* Add class participation marks for students

Check out the first few sections on how to set up ClassMATE and an overview of the symbols and syntax we use in the user guide. You can go through the remaining sections to learn how to use the various efficient features of ClassMATE, comprising the Tutorial Class, Student, Student Marks, and Tutorial Group Commands.

Use the Table of Contents below to navigate to relevant sections to learn the commands for ClassMATE.

Click on a section title to head there now!

* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## How to use this User Guide

This User Guide helps you familiarize yourself with ClassMATE, its features, commands and uses!

* Use the Quick Start [below](#Quick-Start) :arrow_down_small: to set up ClassMATE on your Computer.
* You may view the entire list of ClassMATE's Features by clicking [here](#features).
* Alternatively, you can refer to the Command Summary Table at the bottom, or by clicking [here](#Command-Summary).
* You can check out a list of frequently-asked questions (FAQs) [here](#FAQ) and the glossary [here](#glossary)

Throughout the User Guide,

**:information_source: Notes** will be used to give additional points regarding features

**:bulb:Tips** will be used to provide quick information bits about features

**:warning:Warning** will be used to provide warnings about features

We hope you find this User Guide helpful when using ClassMATE!

------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer. (Go to [this website](https://codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk) and follow the instructions to download and install Oracle JDK 11, which is _basically_ [Java 11](#Glossary).)
1. Download the latest `classmate.jar` from [here](https://github.com/AY2122S1-CS2103T-W15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ClassMATE. 

   :information_source: **Note:**	Remember which folder is your home folder, as it might be required if you need to make any manual changes!

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data (*Not necessarily the same data*).<br>
   ![Ui](images/Ui-annotated.png)
   
1. Type the command in the **Command-Line Input** and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   Check out some examples in the [Tutorial](#cli-tutorial)
   
1. Refer to the [Features](#features) below for details of each command.

## CLI Tutorial

In this section, you will familiarize yourself with the use of CLI to facilitate your experience when using ClassMATE.
All commands have to be typed in the **Command-Line Input** located at the top of the user interface as shown in the image below.

![Command Line Input](images/CommandLineInput.png)

Once you have familiarised yourself with the layout of the application, try out some example commands!

Some example commands you can try:
* **`liststu`** : Lists all students. All students currently stored in ClassMATE will be displayed in the **Student Panel**.

* **`addc c/G06 s/Tuesday 2:00pm to 4:00pm, Friday 2:00pm to 4:00pm`**: Adds a tutorial class with the code `G06`.

  The **Tutorial Class Panel** should reflect the updated list of tutorial classes including your new class, `G06`.

* **`addstu n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/G06`**: Adds a student named `John Doe` to ClassMATE.
  
  The **Student Panel** Should reflect the updated list of students including your new student, `John Doe`.
  
* **`deletestu 3`**: Deletes the 3rd student shown in the current list.

* **`clear`** : Deletes all data from ClassMATE.

* **`exit`** : Exits the app.

Once you have attempted these commands, you're ready to go!

### Command Format

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>
These are general rules applying to all explanations and command formats listed below!

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addstu n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* You can add parameters in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, you can also type `p/PHONE_NUMBER n/NAME` for the parameters.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* If you add parameters for commands that do not take in parameters (such as `help`, `liststu`, `exit` and `clear`), they will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* `INDEX` items must be positive integers. Negative integers, decimal values, and zero will not be accepted for any command `INDEX`. <br>

  e.g. if the command `viewstu -5` is entered, it will throw an invalid command format error, telling you to enter a positive integer `INDEX` only!

</div>

## Features

Here, you can find instructions on how to use ClassMATE's various features. The features are divided into five main subsections, each covering different groups of commands:
1. [Tutorial Class Commands](#tutorial-class-commands)
2. [Student Commands](#student-commands)
3. [Student Marks Commands](#student-marks-commands)
4. [Tutorial Group Commands](#tutorial-group-commands)
5. [Other Commands](#other-commands)

Each subsection will provide you with an overview, followed by the individual command formats, instructions on how to use them, examples of their usage and the expected outcome of executing these commands. 

## Tutorial Class Commands

This section covers all the commands you can use to manage information pertaining to tutorial classes!

Features include the ability to:
1. [Add a tutorial class](#adding-a-tutorial-class--addc)
1. [List all tutorial classes](#listing-all-classes--listc)
1. [View all students in a tutorial class](#viewing-a-class-viewc)
1. [Find a tutorial class by its class code](#finding-classes-by-class-codes-findc)
1. [Delete a tutorial class](#deleting-a-class-deletec)

### Adding a tutorial class : `addc`

Adds a tutorial class to ClassMATE.

Entering format: `addc c/CLASS_CODE s/SCHEDULE [t/TAG]…​`

**:information_source: Note:**<br>

* Class Code should consist of 'G' followed by two numerical digits (i.e. any value from 'G01' to 'G99').

* Schedule consists of only 2 weekly timeslots. Each time slot should be written in the format `Day H:MM am/pm to H:MM am/pm` (e.g. `Wed 12:00pm to 2:00pm`)

* The `Day` should be start with a capital letter. Short form for days are also accepted (e.g. Tues for Tuesday).

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
  Entering wrong/impossible timings is possible and will not be stopped. Enter schedule timings carefully. :warning:
  </div>

Examples:

* `addc c/G06 s/Tuesday 2:00pm to 4:00pm, Friday 2:00 to 4:00pm`
* `addc c/G01 s/Monday 10:00am to 12:00pm, Thursday 10:00am to 12:00pm`

### Listing all classes : `listc`

Shows a list of all classes in ClassMATE.

Entering format: `listc`

### Viewing a class: `viewc`

![viewing a class](images/viewc.png)

Displays a class and its students in ClassMATE, as shown above.

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br>
`viewc` highlights the class at the given INDEX, and filters out only students in that class!
</div>

Entering format: `viewc INDEX`

* Views the class details at the specified INDEX.
* Details of a class includes students in the class and the class schedule.
* The index refers to the index number shown in the displayed list of classes, **not the Class Code**.

**:information_source: Note:**<br>
* The index **must** be a positive integer 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
In order to find a specific class, use the `findc` command to find a particular class before viewing it.
</div>

Examples:

* `listc` followed by `viewc 2` shows the 2nd class in the displayed class list.

### Finding classes by class codes: `findc`

Finds a class by its class code.

Entering format: `findc CLASS_CODE [MORE_CLASS_CODES]`

**:information_source: Note:**<br>
* The search is absolute. e.g. `G0` will not match `G06`

Examples:

* `findc G02` returns `G02` if it exists.<br>

### Deleting a class: `deletec`

Deletes a class from ClassMATE.

Entering format: `deletec INDEX`

* Deletes the class at the specified INDEX.
* The index refers to the index number shown in the displayed list of classes.

**:information_source: Note:**<br>
* The index **must be a positive integer** 1, 2, 3, …​
* Students formerly belonging to the deleted class would now be assigned to `No Class`. Groups that students were assigned to in the class will no longer exist and students will have no groups.

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
This command deletes a class, **ALL** its groups and is irreversible. :warning:
</div>

![deleteClass](images/deletecScreenshot.png)

Examples:

* `listc` followed by `deletec 2` deletes the 2nd class in the list of classes.
* `findc G06` followed by `deletec 1` deletes the 1st class in the results of the `findc` command.

## Student Commands

This part of the guide covers all the commands you can use to manage student information!

You can:
1. [Add a new student](#adding-a-student-addstu)
1. [Edit an existing student](#editing-a-student--editstu)
1. [View a student's details](#viewing-a-student--viewstu)
1. [List all students](#listing-all-students--liststu)
1. [Find students by name](#finding-students-by-name-findstu)
1. [Delete a student](#deleting-a-student--deletestu)

### Adding a student: `addstu`

Adds a student to ClassMATE.

Entering format: `addstu n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS_CODE [t/TAG]…​`

* The Name of a student accommodates special characters such as hyphens, apostrophes and slashes.
* A student can have any number of tags (including 0)

**:information_source: Note:**<br>
* The phone number should be at least 3 digits long.
* The tutorial class with the given Class Code must already exist in classmate.

Examples:
* If class G06 has not been created, add the class first using `addc`.
* `addstu n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/G06`
* `addstu n/Betsy Crowe t/proactive e/betsycrowe@example.com a/10 Kent Dr, #02-02 p/1234567 c/G06 t/teamPlayer`

### Editing a student : `editstu`

Edits an existing student in ClassMATE.

Entering format: `editstu INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_CODE] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​

  **:information_source: Note:**<br>

* At least one of the optional fields must be provided.

  <div markdown="span" class="alert alert-primary">:warning: **Warning:**
  An error message will be displayed to provide at least one parameter if no parameters are provided, regardless of whether the INDEX is valid or not. The INDEX will be checked after at least one optional parameter is provided. :warning:
  </div>

* The index must be a positive integer 1, 2, 3, …​

* Existing values will be updated to the input values.

* When editing tags, the existing tags of the student will be removed i.e. adding of tags is not cumulative.

* You can remove all the student’s tags by typing `t/` without specifying any tags after it.

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
When editing a student, the previous information in the parameter being updated will be irreversibly deleted so be careful! :warning:
</div>

Examples:
*  `editstu 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `editstu 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Viewing a student : `viewstu`

![view student](images/viewstu.png)

Views a student's details in ClassMATE, as shown in the picture above.

Format: `viewstu INDEX`

* Views the student's details at specified `INDEX`
* The index refers to the index number shown in the displayed student list.

**:information_source: Note:**<br>
* The index must be a positive integer 1, 2, 3, …​
* `viewstu` is the only way to view a student's marks!

Examples:

* `liststu` followed by `viewstu 2` shows second student in the student list.
* `findstu Betsy` followed by `viewstu 1` shows the 1st student in the results of the find command.

### Listing all students : `liststu`

Shows a list of all students in ClassMATE.

Entering format: `liststu`

* All students stored are listed.

### Finding students by name: `findstu`

Finds students whose names contain any of the given keywords.

Entering format: `findstu KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`

Examples:
* `findstu John` returns `john` and `John Doe`
* `findstu alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a student : `deletestu`

Deletes the specified student from the student list.

Entering format: `deletestu INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.

**:information_source: Note:**<br>
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
This command deletes a student and is irreversible. :warning:
</div>

Examples:
* `liststu` followed by `deletestu 2` deletes the 2nd student in the student list.
* `findstu Betsy` followed by `deletestu 1` deletes the 1st student in the results of the `findstu` command.

## Student Marks Commands

This section of the guide covers all the commands you can use to manage student marks!

You can:
1. [Add the latest mark](#adding-the-latest-mark-addlm)
1. [Delete the latest mark](#deleting-latest-mark-deletelm)
1. [Delete all marks for a student](#deleting-all-marks-deleteam)

There are 6 types of Marks that can be assigned to a student for each session that the student attends. These marks are meant to be a class participation score, but you can use it flexibly. The marks that can be assigned are:

* Poor
* Low
* Avg (short for Average)
* Good
* High
* Excellent

### Adding the Latest Mark: `addlm`

Adds a mark to a student for their latest session.

Entering format: `addlm INDEX m/MARK`

* Mark is not case-sensitive.
* The index refers to the index number shown in the displayed student list.

**:information_source: Note:**<br>
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `liststu` followed by`addlm 2 m/Low` assigns LOW mark to the latest session for 2nd student in the student list.
* `findstu Betsy` followed by `addlm 1 m/excellent` assigns EXCELLENT mark to the latest session for 1st student in the results of `findstu`.

### Deleting Latest Mark: `deletelm`

Deletes the mark of a student for their latest session.

Entering format: `deletelm INDEX`

* Deletes mark as the mark for latest session.
* Student has to have some marks assigned previously.
* The index refers to the index number shown in the displayed student list.

**:information_source: Note:**<br>
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `liststu` followed by `addlm 2 m/Low`  and `deletelm 2` deletes the LOW mark assigned to the 2nd student in the student list.
* `findstu Betsy` followed by `deletelm 1` deletes the latest sessions' mark for 1st student in the results of `findstu`.

### Deleting All Marks: `deleteam`

Deletes all marks for a student.

Entering format: `deleteam INDEX`

* Student has to have some marks assigned previously.
* The index refers to the index number shown in the displayed student list.

**:information_source: Note:**<br>
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `liststu` followed by `addlm 2 m/Low`  and `deleteam 2` deletes all marks assigned to the 2nd student in the student list.
* `findstu Betsy` followed by `deleteam 1` deletes all sessions' mark for 1st student in the results of `findstu`.

## Tutorial Group Commands

These are the commands that involve tutorial groups!

The first part covers interactions between groups and classes, while the second part covers interactions between students and groups.

You can:
1. [Add a new tutorial group](#adding-a-tutorial-group-addcg)
1. [Views all students in a tutorial group](#viewing-a-tutorial-group-viewg)
1. [Delete a tutorial group](#deleting-a-tutorial-group-deletecg)
1. [Add a student to a tutorial group](#adding-students-to-a-tutorial-group-addsg)
1. [Delete a student from a tutorial group](#deleting-student-from-a-group-deletesg)

### Adding a Tutorial Group: `addcg`

Adds a tutorial group to a particular tutorial class in ClassMATE.

Entering format: `addcg gn/GROUP_NUMBER c/CLASS_CODE type/GROUP_TYPE`

**:information_source: Note:**<br>
* GROUP_NUMBER should be a single digit
* GROUP_TYPE refers to the assignment that the group will work for. It should only be OP1 or OP2.
* The class must have already been added to ClassMATE first. If you are unsure, you can refer to
  [Adding a Tutorial Class](#adding-a-tutorial-class-:-`addc`)
* Tutorial groups are identified by GROUP_NUMBER, GROUP_TYPE and CLASS_CODE. This means that
  any two tutorial groups are identical if all three fields are identical, which is not allowed.

Examples:
* `addcg gn/1 c/G01 type/OP1` adds Group 1 to class `G01` assigned to the task `OP1`

### Viewing a Tutorial Group: `viewg`

Displays all students in a particular tutorial group in ClassMATE.

Entering format: `viewg c/CLASS_CODE type/GROUP_TYPE gn/GROUP_NUMBER`

**:information_source: Note:**<br>
* Only `OP1` and `OP2` are accepted as Group Types.

Examples:
* `viewg c/G06 type/OP2 gn/1` lists the students in `OP2` Group `1` of class `G06`

### Deleting a Tutorial Group: `deletecg`

Deletes a tutorial group from ClassMATE.

Entering Format: `deletecg c/CLASS_CODE type/GROUP_TYPE gn/GROUP_NUMBER`

**:information_source: Note:**<br>
* GROUP_NUMBER should be a single digit

* GROUP_TYPE refers to the assignment that the group will work for. It should only be OP1 or OP2.

* All students in the deleted group will no longer be in the group.

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
This command deletes a group and is irreversible. :warning:
</div>

Examples:
* `deletecg c/G06 type/OP2 gn/1` deletes the `OP2` Group `1` of class `G06`

### Adding Students to a Tutorial group: `addsg`

Adds a student to a tutorial group.

Entering format: `addsg INDEX gn/GROUP_NUMBER c/CLASS_CODE type/TYPE`

* Adds the student to a group in the class

**:information_source: Note:**<br>
* Only `OP1` and `OP2` are accepted as Group Types.
* A student can only be added to a group if the group has been created under the same class already!

Example:
* `addsg 1 gn/1 c/G06 type/OP1` adds the student at index 1 to OP1 Group 1 in class G06

### Deleting Student from a group: `deletesg`

Deletes a student from a group.

Entering format: `deletesg INDEX g/GROUP_NUMBER c/CLASS_CODE type/TYPE`

* Deletes the student from a group in the class
* Type refers to the assignment that the group will work together for

**:information_source: Note:**<br>
* Only `OP1` and `OP2` are accepted as Group Types.


Example:
* `liststu c/G06`shows that Betsy is a student in class G06 with Index 1.
  `deletesg 1 gn/1 c/G06 type/OP1` then removes Betsy from OP1 Group 1 in class G06

## Other Features

These are other commands and features you can use in ClassMATE!

### Viewing help : `help`

Shows a message explaining how to access the help page, which directs you to this user guide. So, don't worry about forgetting commands, just refer to this guide!

![help message](images/helpMessage.png)

Entering format: `help`

### Clearing all data : `clear` :warning:

Clears all data from ClassMATE. Below is how it would look like.

![clear](images/clear.png)

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
This command deletes **ALL** data and is irreversible :warning:
</div>

Entering format: `clear`

### Exiting the program : `exit`

Exits the program.

Entering format: `exit`

### Saving the data

Saving ClassMATE data is a hassle-free process. Data is saved in the hard disk *automatically* after any command that changes the data. **There is no need to save manually.**

### Editing the data file

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Recommended to be done only by advanced users.
</div>

In order to find a specific class, use the `findc` command to find a particular class before viewing it.

ClassMATE data are saved as a [JSON](#Glossary) file `[Home Folder/JAR File Location]/data/classmate.json`.

Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ClassMATE will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the [JSON](#glossary) file that contains the data of your previous ClassMATE home folder.

**Q**: Can I create tutorial groups that are not for Oral Presentation 1 or 2?<br>
**A**: No, currently ClassMATE only supports the creation of Tutorial Groups for Oral Presentations 1 and 2 only.

**Q**: Can I view students only belonging to a certain Tutorial Class alone?<br>
**A**: Yes, use the `viewc` command to do so. The `viewc` command is explained [here](#viewing-a-class:-`viewc`)

--------------------------------------------------------------------------------------------------------------------

## Command Summary

### Tutorial Class Commands

Action | Format, Examples
--------|------------------
**[Add class](#adding-a-tutorial-class--addc)** | `addc c/CLASS_CODE s/SCHEDULE [t/TAG]…​`<br> e.g., `addc c/G06 s/Tuesday 2:00pm to 4:00pm, Friday 2:00pm to 4:00pm`
**[List all classes](#listing-all-classes--listc)** | `listc`
**[View students in class](#viewing-a-class-viewc)** | `viewc INDEX`<br> e.g., `listc` followed by `viewc 3`
**[Find class](#finding-classes-by-class-codes-findc)** | `findc CLASS_CODE [MORE_CLASS_CODES]`<br> e.g., `findc G02`
**[Delete class](#deleting-a-class-deletec)** | `deletec INDEX`<br> e.g., `listc` followed by `deletec 2`

### Student Commands

Action | Format, Examples
:-------|------------------
**[Add student](#adding-a-student-addstu)** | `addstu n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS_CODE [t/TAG]…​`<br> e.g., `addstu n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/G01 t/attentive`
**[Edit student](#editing-a-student--editstu)** | `editstu INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_CODE] [t/TAG]…​`<br> e.g., `editstu 2 n/James Lee e/jameslee@example.com`
**[List students](#listing-all-students--liststu)** | `liststu`<br> e.g., `liststu` 
**[View student](#viewing-a-student--viewstu)** | `viewstu INDEX`<br> e.g., `liststu` followed by `viewstu 2`
**[Find student](#finding-students-by-name-findstu)** | `findstu KEYWORD [MORE_KEYWORDS]`<br> e.g., `findstu John`
**[Delete student](#deleting-a-student--deletestu)** | `deletestu INDEX`<br> e.g., `liststu` followed by `deletestu 3`

### Student Marks

Action | Format, Examples
--------|------------------
**[Add latest mark](#adding-the-latest-mark-addlm)** | `addlm INDEX m/MARK`<br> e.g., `liststu` followed by `addlm 1 m/excellent`
**[Delete latest mark](#deleting-latest-mark-deletelm)** | `deletelm INDEX`<br> e.g., `liststu` followed by `deletelm 2`
**[Delete all marks](#deleting-all-marks-deleteam)** | `deleteam INDEX`<br> e.g., `liststu` followed by `deleteam 3`

### Tutorial Groups

Action | Format, Examples
--------|------------------
**[Add tutorial group](#adding-a-tutorial-group-addcg)** | `addcg gn/GROUP_NUMBER c/CLASS_CODE type/TYPE` <br> e.g.,`addcg gn/1 c/G11 type/OP1`
**[View students from group](#viewing-a-tutorial-group-viewg)** | `viewg gn/GROUP_NUMBER c/CLASS_CODE type/GROUP_TYPE` <br> e.g., `viewg gn/1 c/G01 type/OP1`
**[Delete tutorial group](#deleting-a-tutorial-group-deletecg)** | `deletecg gn/GROUP_NUMBER c/CLASS_CODE type/GROUP_TYPE` <br> e.g., `deletecg gn/1 c/G01 type/OP1` 
**[Add student to group](#adding-students-to-a-tutorial-group-addsg)** | `addsg INDEX gn/GROUP_NUMBER c/CLASS_CODE type/GROUP_TYPE` <br> e.g., `addsg 1 gn/1 c/G01 type/OP1` 
**[Delete student from group](#deleting-student-from-a-group-deletesg)** | `deletesg INDEX gn/GROUP_NUMBER c/CLASS_CODE type/GROUP_TYPE` <br> e.g., `deletesg  1 gn/1 c/G01 type/OP1`

### Other Commands

Action | Format, Examples
--------|------------------
**[Help](#viewing-help--help)** | `help`
**[Clears ClassMATE Data](#clearing-all-data--clear-warning)** | `clear`
**[Exit ClassMATE](#exiting-the-program--exit)** | `exit`

## Contact Us

For any assistance or difficulties, feel free to drop us an email at [classMateys@gmail.com](mailto:classMateys@gmail.com) !

## Glossary

Term | Definition
--------|------------------
**[Java 11](#quick-start)** | Java is a programming language, more on it [here](https://en.wikipedia.org/wiki/Java_(programming_language)).
**[JSON](#editing-the-data-file)** | a JSON file is an open standard file format, more on it [here](https://en.wikipedia.org/wiki/JSON).
**[CLI](#cli-tutorial)** | Command Line Interface (CLI)  enables users to interact with a program by typing in text commands following visual prompts from the program.
**[GUI](#quick-start)** | Graphical User Interface (GUI) is a system of interactive visual components that allows users to interact with a program through graphical icons.

[Back to top](#introduction)
