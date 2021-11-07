---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

Welcome to the User Guide of **TuitiONE**!

**TuitiONE** is a Command Line Interface (CLI) based application that aims to **simplify the work of Customer Servicing Officers (CSO) in a tuition centre**.
The application also incorporates the benefits of a Graphical User Interface (GUI).
**TuitiONE** allows CSOs to input the details of students and parents through simple and intuitive commands. With our application, a CSO's work would be reduced and efficient.

If you can type fast, **TuitiONE** can get your contact management tasks done faster than most other GUI apps. The GUI application would allow you to interact with the application
through graphical icons (such as buttons).

However you do not have to worry even if you are new to CLI / GUI applications. **TuitiONE** uses easy to learn and simple CLI commands that usually fall under one sentence.
Moreover, this User Guide will take you through every feature of **TuitiONE**, providing you with the easiest and best user experience possible.

If you are interested, jump to [Quick start](#quick-start) to learn how to start up **TuitiONE** in a simple and quick manner.

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have Java 11 or above installed in your Computer. You may follow the instructions and install it [here](https://www.oracle.com/java/technologies/downloads/#java11).

2. Download the latest `TuitiONE.jar` from [here](https://github.com/AY2122S1-CS2103T-F13-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TuitiONE.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
  _Note how the app contains some sample data_.<br>

   ![Ui](images/Ui.png)<br>
   <center><i>Image: <b>TuitiONE</b> upon loading for the first time.<br></i></center>

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

   Some example commands you can try:

   * **`list`** : Lists all students and lessons.

   * **`add`** `n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 g/S3 r/friends r/owesMoney` : Adds a student named `John Doe` to the **TuitiONE** app.

   * **`delete`** `3` : Deletes the 3rd student shown in the student list.

   * **`clear`** : Deletes all data (students and lessons).

   * **`exit`** : Exits the app.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** After running **TuitiONE**, you will notice there are some additional files created in the _home folder_. Please do not delete these files, as they contain your data and important configurations for **TuitiONE** to run smoothly.

</div>

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **About this document**

### Structure of this document

This document serves as a guide to help you get started on how you can set up and use **TuitiONE**. This User Guide has been structured to help you find what you require in a quick and simple manner.
Here are several useful tips on how to read and intrepret this guide.

### Reading the User Guide

This section introduces you to the technical terms, symbols and syntax that are used inside this User Guide.
This would be useful for you should they be unclear to you.

#### Technical terms

The table below are the interpretations of a few technical terms that you may encounter in this User Guide.

| Technical term  | What it means |
| ------------- | ------------- |
| CLI | The Command Line Interface (CLI) is the interface that accepts text input to execute the functions of **TuitiONE**. |
| GUI  | The Graphical User Interface (GUI) is the user interface which has graphical indicator representations that the user may interact with. Graphics, icons, windows, menus, cursor and buttons are all components of a GUI. |
| Parameter  | Parameter refers to the user input required after the user is prompted by the TuitiONE GUI  |

#### General Symbols and Syntax

The table below are the interpretations of symbols and formatting used in this document.

| Syntax  | What it means |
| ------------- | ------------- |
| `highlights`  | 	A grey highlighted block represents an executable command, or possible parameters that can be entered into the CLI.  |
| :information_source:  | Indicates additional information  |
| :bulb:  | Indicates tips  |
| :exclamation:  | Indicates that you should take caution  |

There are multiple examples provided for you in the section below [Features](#features). Each simulated scenario include expeced outputs by the **TuitiONE** application.

If there are still any doubts on the terms and usage of this app, you can refer to the [Glossary](#glossary) and [FAQ](#faq) located at the end of the document.

For each command specifically, you can view them in the relevant sections (such as in [Managing Students](#managing-students) and [Managing Lessons](#managing-lessons)) to learn more in detail.
The [Command summary](#command-summary) lists a table with all the commands present and their syntax.

--------------------------------------------------------------------------------------------------------------------

## **Application Layout**

This section presents the various elements in our **TuitiONE** application.

![Result of `list`](images/UserGuideImage/default.PNG)

<center><i>Image: Layout of <b>TuitiONE</b>.</i></center><br>
<br>

**No.** | **Section** | **Description**
--------|-------------|----------------
1 | Toolbar pane | Here you can select the quit button or view the help window.
2 | Student panel | Here you can see the list of students present in your application. This list can be skimmed down using commands such as [`find`](#locating-students-by-name-find) and [`filter`](#filtering-of-list-filter).
3 | Lesson panel | Here you can see the list of lessons present in your application. This list can be skimmed down using commands such as [`filter`](#filtering-of-list-filter) and [`roster`](#viewing-of-lesson-roster-roster).
4 | Result panel | Here is where you will receive the various messages from the application after running your commands. There are a variety of messages, ranging from success messages to error messages.
5 | Command input box | Here is where you type your commands to run in the application.
6 | Send button | A button that helps submit your input command to run. Using the `Enter`-key on your keyboard after typing in the **Command input box** performs the same job here.
7 | Storage file indicator | This portion displays the location of your saved **TuitiONE** data file in your device.

<center><i>Table: <b>Legend of TuitiONE</b>.<br></i></center>

## **Features**

This section outlines all the features that **TuitiONE** has. You will be able to see the purpose of each feature, the format of each command and possible examples and images of what you should expect to see.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [r/REMARK]` can be used as `n/John Doe r/friend` or as `n/John Doe`.

* Items with `…` after them can be used as many times as the user would like.<br>
  e.g. `[r/REMARK]…` can be used multiple times like `r/sick` or `r/absent r/graduated`, or can be omitted altogether.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PARENT_CONTACT`, `p/PARENT_CONTACT n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will ignore follow-up inputs.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


<div markdown="block" class="alert alert-warning">

**:exclamation: Caution on use of the symbol `/` in commands:**<br>

* For all commands, the symbol `/` should only be used in the **representation of prefixes**, such as `n/`, `p/` and `r/`, etc.

* You **should not** use the symbol `/` when filling up any of the parameters.

* For example,  in `add` command,

  * **Acceptable** command: `add n/John Doe p/98765432 e/jd@gmail.com a/John street, block 123, #01-01 g/P2`.

  * **Invalid** command: `add n/John Doe p/98765432 e/jd@gmail.com a/John stre/et, block 123, #01-01 g/P2`.<br>_*notice the additional `/` used in the parameter of `a/ADDRESS`._

</div>

### General Commands

#### Viewing help: `help`

TuitiONE will display the help panel which shows you a summary of the command syntax that is usable to the current version of TuitiONE.

Command Format: `help`

#### Listing all students: `list`

Shows you a list of all students and lessons in the **TuitiONE**. Students will be sorted in ascending alphabetical order by their name. Lessons will be sorted by grade, from `P1` to `S5`.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

Sorting of the lists by other fields (eg. day, time, subject) is not available in the current version of **TuitiONE**, and will be an upcoming feature.

</div>

Command Format: `list`

![Result of `list`](images/UserGuideImage/list.PNG)

<center><i>Image: Expected output of <code>list</code> command.</i></center><br>
<br>

Upon entering the `list` command, both student and lesson panels will be updated to show all the students and lessons present.

#### Filtering of list: `filter`

Filter the respective list(s) to display entries that correspond to the conditions as specified.

Command Format: `filter [g/GRADE] [s/SUBJECT]`

:information_source: **Details:**

* You can filter by `GRADE`, `SUBJECT`, or both.

    * If you are only filtering by `GRADE`, both of the student list and lesson list will be filtered to display the respective entries that correspond to the `GRADE` as specified.

    * If you are only filtering by `SUBJECT`, only the lesson list will be filtered to display the respective lessons that correspond to the `SUBJECT` as specified.

    * If you are filtering by both `GRADE` and `SUBJECT`, both of the student list and lesson list will be filtered to display the respective entries that correspond to the `GRADE` and `SUBJECT` as specified.

* `GRADE` refers to the educational level of the student. It can be specified in lower- or upper- case (i.e. `P5` and `p5` represents the same grade).

* `SUBJECT` can be specified in lower- or upper- cases (i.e. `MATH` and `math` represents the same subject which is `Math`). See [`add-l` (add lesson)](#adding-a-lesson-add-l) command for more information.

Example(s):

* `filter g/P2` will filter both of the student list and lesson list by grade of `P2` and display the corresponding entries in the respective lists.

* `filter s/science` will filter the lesson list by subject of `Science` and display the corresponding entries in the respective list.

* `filter s/SCIENCE g/p2` will filter the lesson list by subject of `Science` and grade of `P2`, and the student list by grade of `P2`, and display the corresponding entries in the respective lists.

![Result of `filter`](images/UserGuideImage/filter.PNG)

<center><i>Image: Expected output of <code>filter g/P5 s/Science</code> command.</i></center><br>
<br>

### Managing Students

#### Adding a student: `add`

Adds a student to the TuitiONE.

Command Format: `add n/NAME p/PARENT_CONTACT e/EMAIL a/ADDRESS g/GRADE [r/REMARK]…`

:information_source: **Details:**

* `NAME` can only be alphanumeric and within a maximum of 150 characters. `NAME` will also be set to have the first character of each space separated word to be capital while the rest becomes lower case (i.e. `samUel oNg` becomes `Samuel Ong`). This is to comply with the majority of naming regulations worldwide.

* `PARENT_CONTACT` can only be 8 digits long and start with `6`, `8` or `9` (as this application is currently based for Singapore use).

* `EMAIL` can only have a maximum of 100 characters and have the conventional `@` as well as a domain name.
  * As there can be a variety of possible email address namings and domains, the application will not run a through check on your input. If you happen to input the wrong email address, you can use the `edit` command [here](#editing-a-student--edit).

* `ADDRESS` can only have a maximum of 150 characters.

* `GRADE` refers to the educational level of the student. It can only be in a range of `P1`-`P6` (primary school levels) or `S1`-`S5` (secondary school levels). Here specifying lower case will also be a valid grade input (e.g. `p3` is allowed and will be read in the application as `P3`).

* `REMARK` can have a maximum of 25 characters, single worded without spacings in between them. A student can have any number of remarks, capped at 5. (including 0). (e.g `smart` is valid, while `needs help` is invalid)

* Each student must have a unique name.

* Phone numbers are not unique as multiple students may share the same parent.

Example(s):

* `add n/John Doe p/98765432 e/jd@gmail.com a/John street, block 123, #01-01 g/P2`

* `add n/Betsy Crowe p/91234567 e/bc@gmail.com a/Bleecker street, block 123, #01-01 g/S5 r/foreign student`

![Outcome of `add`](images/UserGuideImage/add.PNG)
<center><i>Image: Expected output of <code>add</code> command.</i></center><br>
<br>

#### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Command Format: `find KEYWORD [MORE_KEYWORDS]`

:information_source: **Details:**

* The search is case-insensitive. e.g `hans` will match `Hans`.

* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.

* Only keywords based on name will be searched.

* Prefixed matching words will be supported e.g. `Han` will match `Hans`.

* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Example(s):

* `find John` returns `john` and `John Doe`

* `find alex david` returns `Alex Yeoh`, `David Li`<br>

![Result of `find alex`](images/UserGuideImage/find.PNG)
<center><i>Image: Expected output of <code>find tan</code> command.</i></center><br>
<br>

#### Deleting a student : `delete`

Deletes a student from the TuitiONE.

Command Format: `delete INDEX`

:information_source: **Details:**

* Deletes the student at the specified `INDEX`.

* The index refers to the index number shown in the displayed student list.

* Deleting a student also unenrolls (see [`unenroll`](#unenrolling-a-student-from-lesson-unenroll)) themselves from their lessons.

* The index **must be a positive integer** `1`, `2`, `3`, …

Example(s):

* `list` followed by `delete 2` deletes the student indexed `2` in the TuitiONE.

* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

![Outcome of `delete`](images/UserGuideImage/delete.PNG)

<center><i>Image: Expected output of <code>delete 1</code> command.</i></center><br>
<br>

#### Editing a student : `edit`

Edits a student's particulars.

Command Format: `edit INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GRADE] [r/REMARK_TO_ADD]... [dr/REMARK_TO_DELETE]...`

:information_source: **Details:**

* Edits the student at the specified `INDEX` based on the fields given.

* You can edit any number of fields.

* The index refers to the index number shown in the displayed student list.

* The index **must be a positive integer** `1`, `2`, `3`, …

* You can edit a student to have any number of remarks, capped at 5 (including 0). The number of characters each remark can have is capped at 25.

* Remarks are unique, and you cannot tag more than one of the same remark to the same student. For example, `edit 2 r/overdueFees r/overdueFees` will only tag a single `overdueFees` remark to the student at index `2`.

* See [`add` command](#adding-a-student-add) for other constraints on defining a student.

* If you enter `edit r/[REMARK_TO_ADD]`, TuitiONE will add on the given `REMARK` to the existing set of remarks.

* If you enter `edit dr/[REMARK_TO_DELETE]`, TuitiONE will delete the `REMARK` from the existing set of remarks, if it is present in the set.

* If you were to add and remove remarks in the same command, TuitiONE will remove specified remarks before adding the new ones.

* **Note that if you change a student's grade, TuitiONE will unenroll the student from all the classes he or she was previously taking in the previous grade**.

Example(s):

* `edit 2 p/98765432` changes the parent contact number information of the second student in the student list.

* `edit 2 g/S2` changes the grade of the second student in the student list from its current grade to `S2`, and he or she will be unenrolled from all classes in his or her previous grade.

* `edit 2 n/Ben Lim e/benlim@gmail.com` changes the name and email of the second student in the student list.

* `edit 2 r/discounted dr/unpaid` removes the `unpaid` remark from the second student's set of remarks, before adding the `discounted` remark.

![Outcome of `edit 2 r/discounted`](images/UserGuideImage/edit.PNG)

<center><i>Image: Expected output of <code>edit</code> command.</i></center><br>
<br>

#### Enrolling a student from lesson: `enroll`

Enroll a specified student to a given TuitiONE lesson.

Command Format: `enroll STUDENT_INDEX l/LESSON_INDEX`

:information_source: **Details:**

* Enroll the student identified by `STUDENT_INDEX` in the displayed student list to the specific lesson identified by `LESSON_INDEX` in the displayed lesson list.

* Enrolling a student is only possible if the student:
  1. has the same `grade` as the lesson,

  2. is not enrolled to the lesson and,

  3. has no other lessons with conflicting timing.

* `STUDENT_INDEX` refers to the index number shown in the displayed student list.

* `LESSON_INDEX` refers to the index number shown in the displayed lesson list.

* Both indexes **must be a positive integer** `1`, `2`, `3`, …

* Students can only be enrolled to a **maximum of 10 lessons**.

* Lessons can only have enrolled a **maximum of 15 students**.

Example(s):

* `enroll 1 l/2` will enroll the student indexed at `2` in the displayed student list to the lesson indexed at `2` in the displayed lesson list.

![Outcome of `enroll 1 l/2`](images/UserGuideImage/enroll.PNG)

<center><i>Image: Expected output of <code>enroll 1 l/2</code> command.</i></center><br>
<br>

#### Unenrolling a student from lesson: `unenroll`

Unenroll a student from a given TuitiONE lesson.

Command Format: `unenroll STUDENT_INDEX l/LESSON_INDEX`

:information_source: **Details:**

* Unenroll the student identified by `STUDENT_INDEX` in the displayed student list from the specific lesson identified by `LESSON_INDEX` in the displayed lesson list.

* `STUDENT_INDEX` refers to the index number shown in the displayed student list.

* `LESSON_INDEX` refers to the index number shown in the displayed lesson list.

* Both indexes **must be a positive integer** `1`, `2`, `3`, …

Example(s):

* `unenroll 1 l/1` will unenroll the student indexed `1` in the displayed student list from the lesson indexed at `1` in the displayed lesson list.

![Outcome of `unenroll 1 l/1`](images/UserGuideImage/unenroll.PNG)

<center><i>Image: Expected output of <code>unenroll 1 l/1</code> command.</i></center><br>
<br>

### Managing Lessons

#### Adding a lesson: `add-l`

Adds a lesson to the TuitiONE with the specified prefixes.

Command Format: `add-l s/SUBJECT g/GRADE d/DAY_OF_WEEK t/START_TIME c/COST`

:information_source: **Details:**

* `GRADE` refers to the level of education a lesson is catering for. It follows the similar requirements when adding a student. See [`add`](#adding-a-student-add) command for more details regarding grade.

* `SUBJECT` can only be a single word limited to `20` characters, and its first letter will be capitalized.

* `DAY_OF_WEEK` can only be these form: `Mon`, `Tue`, `Wed`, `Thu`, `Fri`, `Sat`, `Sun` (the first letter need not be capitalized, i.e. `mon` is allowed but not `MON`).

* `START_TIME` is in `2400` hours format and can only be between `0900` and `1900` (as lessons can only be conducted between 9am to 9pm). It must also be in intervals of `30` minutes: `1300` and `1330` are acceptable timings but not `1315`.

* Lessons are fixed at **two** hour periods. In upcoming features, we will give you the power to define your lesson timing ranges.

* Lessons with the same `SUBJECT` and `GRADE` cannot have the same `DAY_OF_WEEK` and `START_TIME`.

* The cost must be a non-negative number `0.0`, `2.0`, `3.3`, … The currency used here in **TuitiONE** is Singapore dollar, SGD. The maximum value for a lesson, for practical reasons, is capped at SGD $ 200.00 inclusive. The cost will be displayed in the lesson list rounded off to two decimal places.

Example(s):

* `add-l s/Science g/P5 d/Wed t/1230 c/12.0`

* `add-l s/Mathematics g/S4 d/Fri t/1500 c/10.3`

* `add-l s/Mathematics g/S4 d/fri t/1500 c/10.3`


![Outcome of `add-l`](images/UserGuideImage/add-l.PNG)

<center><i>Image: Expected output of <code>add-l</code> command.</i></center><br>
<br>

#### Deleting a lesson: `delete-l`

Deletes a lesson from the TuitiONE.

Command Format: `delete-l INDEX`

:information_source: **Details:**

* Deletes the lesson of the specified `INDEX`.

* The index refers to the index number shown in the displayed lesson list.

* The index **must be a positive integer** `1`, `2`, `3`, …

Example(s):

* `delete-l 1` deletes the lesson with corresponding index `1`.

![Outcome of `delete-l`](images/UserGuideImage/delete-l.PNG)

<center><i>Image: Expected output of <code>delete-l 1</code> command.</i></center><br>
<br>

#### Viewing of lesson roster: `roster`

Shows you the student roster of a specified lesson in the student panel. The names of the students will also be displayed in the result panel.

Command Format: `roster LESSON_INDEX`

:information_source: **Details:**

* Displays the student roster of the lesson of the specified `LESSON_INDEX`.

* The index refers to the index number shown in the displayed lesson list.

* The index **must be a positive integer** `1`, `2`, `3`, …

Examples:
* `roster 1` will display the students currently enrolled in the lesson indexed at `1` in the student panel.

![Outcome of `roster 1`](images/UserGuideImage/roster.PNG)

<center><i>Image: Expected output of <code>roster 1</code> command.</i></center><br>
<br>

### Others

#### Clearing all entries : `clear`

Clears all students and lessons data stored in TuitiONE.

Command Format: `clear`

![Outcome of `clear`](images/UserGuideImage/clear.PNG)

<center><i>Image: Expected output of <code>clear</code> command.</i></center><br>
<br>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** Using this command removes all data from <b>TuitiONE</b>. Only use this command if you want to reset all information on the application and start anew.

</div>

#### Exiting the program : `exit`

Exits the program.

Command Format: `exit`

### Managing Data

#### Saving the data

<b>TuitiONE</b> data is saved in the hard disk automatically after any command that changes the data. There is no need for you to save manually.

#### Editing the data file

<b>TuitiONE</b> data is saved as a `.json` file `[JAR file location]/data/TuitiONE.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**

If the changes you made to the data file render its format invalid, <b>TuitiONE</b> will discard all data and start with an empty data file at the next run.

</div>

### Upcoming Features

You can expect these features in upcoming versions of **TuitiONE**.

#### Editing lessons

With this feature, you will have the flexibility in managing the lessons in your tuition center. You will be able to change the day and time of the lesson, its subject, its grade, and its price.

#### Customised sorting

With this feature, you will have the flexibility to order the student and lesson lists to your preference. For students, you can expect to be able to sort the student list by address, number of lessons and even by their fees. For lessons, you can expect to sort the lesson list by day and time, and number of students enrolled.

#### Verification of student particulars

With this feature, **TuitiONE** will help you check whether given student particulars are legitimate. This includes verifying the email and address of newly added students.

#### Tutor management

With this feature, you will be able to store information about tutors into **TuitiONE**. The information can include the lessons they are teaching, their qualifications and their schedule.

#### Importing/exporting files

With this feature, you will be able to import existing files, such as csv and excel files, into **TuitiONE**, and **TuitiONE** will automatically format them for you in its GUI.
You can also export current data in **TuitiONE** as other file types for compatibility with other applications.

#### Data Analytics

With this feature, you will be able to view statistics on the performance of students, popular lessons, times, and tutors. This allows you to gain better business insights about your centre.

--------------------------------------------------------------------------------------------------------------------

## **Command summary**

Action | Format | Examples
--------|-------|----------
**Add** | `add n/NAME p/PARENT_PHONE_NUMBER e/EMAIL a/ADDRESS g/GRADE [r/REMARK]…` | `add n/Betsy Crowe p/91234567 e/bc@gmail.com a/Bleecker street, block 123, #01-01 g/S5 r/foreign student`
**Add lesson** | `add-l s/SUBJECT g/GRADE d/DAY_OF_WEEK t/TIME_START c/COST` | `add-l s/Science g/P5 d/Wed t/1230 c/12.0`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GRADE] [r/REMARK_TO_ADD] [dr/REMARK_TO_DELETE]` | `edit 2 n/Ben Lim e/benlim@gmail.com`
**Delete** | `delete INDEX` | `delete 3`
**Delete lesson** | `delete-l INDEX` | `delete-l 1`
**Enroll** | `enroll STUDENT_INDEX l/LESSON_INDEX` | `enroll 1 l/1`
**Unenroll** | `unenroll STUDENT_INDEX l/LESSON_INDEX` | `unenroll 1 l/1`
**Find** | `find KEYWORD [MORE_KEYWORDS]` | `find James Jake`
**Filter** | `filter [g/GRADE] s[SUBJECT]` | `filter g/P2`
**Roster** | `roster LESSON_INDEX` | `roster 1`
**List** | `list` |
**Help** | `help` |
**Clear** | `clear`
**Exit** | `exit` |

--------------------------------------------------------------------------------------------------------------------

## **Glossary**

* **Java**: A widely used programming language

* **JAR**: An executable java file for you to open the app

* **GUI**: Graphical User Interface

* **Json**: Javascript object notation file. This is used to store your preferences and data.

* **Command**: The user inputs that run the specific features of the app.

* **Parameter**: A specific detail required for a command to run.

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

1. Where can I view the list of commands?

    > You can type `help` or you can click on the 'Help' tab on the top left of the app window. (see [Help](#viewing-help-help) for more)

1. Why are some usual email address inputed valid, such as 'jd@gmail.com.this.that.this'?

    > There are many possible email addresses and domains such as school email address and personal domains, hence **TuitiONE** will not provide a thorough checking in this current version. If there is any scenario where you have inputted the wrong email address and would like to change it, refer to the the `edit` command [here](#editing-a-student--edit).

1. Why am I unable to add a student with the same name as another student?

    > Currently our system identifies uniqueness of students by their name, hence you are unable to add students with the same name. We are working on an update to identify uniqueness through the combination of name and phone number which will counter this problem.

1. How do I edit a lesson?

    > Unfortunately, in the current version of TuitiONE, you will need to use `delete-l` and `add-l` to make your edits, then re-enroll the students. In the upcoming update, there will be an `edit-l` command that will allow for the editing of lessons.

1. How do I edit a remark?

    > To edit a remark, you will need to use the `dr/` and `r/` prefixes in the `edit` command to make any changes to remarks. (see [Editing a student](#editing-a-student--edit) for more).

1. Am I able to add or edit `Remarks` to have spacings within them?

    > No. The number of characters each `Remark` can have is capped at 25, and must be single words. (see [Adding a student](#adding-a-student-add) for more)

1. Am I able to use "4PM" instead of "1600" for my timings when creating a new lesson?

    > No. **TuitiONE** only accepts timings that follow the `2400` hours format. Additionally, timings must also be in intervals of 30 minutes For instance, `1400` and `1415` are valid inputs, while `1415` is an invalid input. (see [Adding a lesson](#adding-a-lesson-add-l) for more)

1. How long can my name be for adding a new `Student`?

    > We have imposed a `150` character limit for the respective names of `Students`. `Students` with names longer than 150 characters should use initials to represent their full name instead. (see [Adding a student](#adding-a-student-add) for more)

1. Can `Lessons` of the same `Subject` and `Grade` start at the same time?

    > No. **TuitiONE** would consider a `Lesson` of the same `Subject` and `Grade` that start at the same time on the same day as a conflict. (see [Adding a lesson](#adding-a-lesson-add-l) for more)

1. How many `Lessons` can a `Student` be enrolled in?

    > A `Student` can be enrolled in a maximum of 10 `Lessons` at any time. **TuitiONE** will not allow a `Student` to be enrolled in more than **10** `Lessons` (see [Enrolling a student from lesson](#enrolling-a-student-from-lesson-enroll) for more).

1. How many `Students` can a `Lesson` contain?

    > A `Lesson` can have up to 15 `Students` enrolled in at any time. **TuitiONE** will not allow a `Lesson` to have more than **15** `Students` enrolled in at one time (see [Enrolling a student from lesson](#enrolling-a-student-from-lesson-enroll) for more)

1. Why are there some unusual files present in my folder after I run **TuitiONE**?

    > **TuitiONE** currently is a local desktop application, and hence the application would need to store the data you have inputted into these files. These files contain your personal preferences as well as the student and lesson data your tuition center holds. As such do not delete these files as this may cause **TuitiONE** to reset the next time you run it, potentially losing all your data. You may wish to edit these files directly, but we do not recommend such as well (see [Managing data](#managing-data) for more).
