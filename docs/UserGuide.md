---
layout: page
title: User Guide
---
# Table of Contents
{:.no_toc}
<!-- @@author wpinrui-reused
Table of Contents from AY2021S1-CS2103T-W16-3/tp
https://github.com/AY2021S1-CS2103T-W16-3/tp/pull/179/commits/aec461182c194c9ca2c67d7c407fcabb376191ff
-->
<div class="toc-no-bullet-points">
  * Table of Contents
  {:toc}
</div>
<!--@@author
-->

# 1. Introduction
Thank you for downloading **TutorAid**! TutorAid is a desktop app **for private tutors to track their students' contacts**.

The main features of TutorAid include:
1. Storing contacts of students and their parents
2. Storing and managing lesson details
3. Tracking students' progress

With TutorAid, you have a one-stop solution for keeping track of your students' contacts and staying up-to-date on their progress. TutorAid is optimised for use via a **Command Line Interface** (CLI) while retaining the benefits of a visually-appealing Graphical User Interface (GUI). If you can type fast, TutorAid can help you to manage your tutoring tasks more efficiently than traditional GUI apps.

We hope that TutorAid will be helpful in adding more organisation to your tutoring career. To get TutorAid up and running, check out the [Quick Start](#3-quick-start) section.

***

# 2. About
This section contains information and terms that may help you to understand the user guide.

## 2.1 User Guide formatting

Formats discussed in this section may be used in relevant areas of this user guide.

**Tips**

Tips are helpful suggestions that may improve your experience using TutorAid.

> :bulb: This is helpful.

**Warnings**

Warnings can inform you of things that should be followed carefully to prevent unwanted behaviour.

> :exclamation: **This is important!**

**Commands**

Commands in these highlighted boxes are typically used to show what you should enter into TutorAid.

`command`

***

## 2.2 TutorAid visual components
This section details the various components in TutorAid and how they will be referred to throughout the guide.
![Labelled Ui](images/labelled-ui.jpg)

#### Menu Bar
{:.no_toc}
This is an area where some features can be found. As TutorAid primarily interacts with you through the command box, this area is infrequently used.

#### Command Box
{:.no_toc}
The Command Box is a field in which you can type instructions (commands) to TutorAid. Text in this user guide that is `highlighted` should typically be copied into the command box exactly (including spaces). After you have typed a command into this box, you can press ENTER :leftwards_arrow_with_hook: on your keyboard to tell TutorAid to execute your command.

If your command is of the wrong [format](#23-command-format), TutorAid will try to show you the correct format using the Console if it is able to guess what command you had intended to provide.

#### Console
{:.no_toc}
The Console is used by TutorAid to communicate with you. Whenever a command is carried out, TutorAid will let you know whether the operation was successful. It may also provide you with details about the changes made during the operation.

#### Student Panel
{:.no_toc}
The Student Panel is where your students are listed. There are two modes for this panel: **Full** and **Minimal**. Full view means that all details about each student is listed, whereas Minimal view allows you to see only their name and their index number. These modes can be set via [the list command](#listing-all-students-and-lessons-list). In the labelled screenshot, the Student Panel is in Minimal view.

> :bulb: The **index number** is important for many commands in TutorAid.

#### Lesson Panel
{:.no_toc}
The Lesson Panel is where your lessons are listed. Just like the Student Panel, there are the **Full** and **Minimal** modes which determine how much information is displayed. In the labelled screenshot, the Lesson Panel is in Full view.

> :bulb: The **index number** of a lesson is important too, and is used in commands just like the index number for students.

#### Status Bar
{:.no_toc}
The status bar shows the path where you can find the save file for TutorAid.

***

## 2.3 Command Format
Commands are text that you can enter into the Command Box to tell TutorAid to perform an operation. Some commands have many components, each of which serve different purposes. The following diagram depicts the components of a command.

![](images/command-syntax.png)

#### Command Word
{:.no_toc}
The command word is how you can tell TutorAid what kind of operation you want to do. These command words are listed [here](#6-command-summary). All commands must contain a command word.

In the example above, `edit` tells TutorAid to perform an *edit* operation.

#### Flag
{:.no_toc}
The flag is used to differentiate between variants of the same operation. For example, the `edit` command word can be used to edit the details of a student or a lesson. To differentiate between these usages, you should pass a flag to TutorAid - `edit -s` to edit a student, or `edit -l` to edit a lesson.

In the example above, `-s` tells TutorAid to perform the edit operation on *students*.

#### Index Number
{:.no_toc}
Some commands perform operations on a specific student or lesson. You should give TutorAid an index number to specify a student or a lesson. The index number can be found by looking at the respective panels - the [Student Panel](#student-panel) or the [Lesson Panel](#lesson-panel).

In the example above, `3` tells TutorAid to perform the edit operation on the *third student*.

#### Parameter
{:.no_toc}
A parameter contains the *specifics* of the command to be executed. There can be **multiple** parameters for a single command, depending on the type of command that you wish to perform. Arguments are prefixed with a few characters followed by a slash (`sn/` and `sp/` for this example). These prefixes help TutorAid to differentiate parameters.

> :bulb: `sn/` is the prefix for Student Name and `sp/` is the prefix for Student Phone.

In the example above, `sn/Matthew Judge` tells TutorAid that the third student should be edited *by changing their name to Matthew Judge*. Similarly, `sp/91263740` tells TutorAid that the third student should be edited *by changing their student mobile number to the specified number*.

### 2.3.1 Command Syntax in this Guide
In this guide, the syntax / format of a command is shown like this:

`edit -s INDEX_NUMBER [sn/STUDENT_NAME] [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`
`add -sl s/STUDENT_INDEX... n/LESSON_INDEX...`

* Words in `UPPER_CASE` are the parameters to be supplied by the user and can contain spaces.
* Items in square brackets are optional.
* Parameters can be in any order.
* If a parameter is expected only once in the command but if you specify it multiple times, only the last occurrence of the parameter will be taken.
* `...` signals that multiple parameters of this type can be accepted(separated by a space), but there must be at least one parameter present.
  e.g. if the format of a command has `s/STUDENT_INDEX...` then both `s/1 2 3` and `s/1` are acceptable inputs, but not `s/ `.
* Extraneous parameters for commands that do not take in parameters will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

***

# 3. Quick Start

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `tutoraid.jar` from [here](https://github.com/AY2122S1-CS2103T-W16-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TutorAid.

4. Double-click the file to start the app. An application window similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br><br>
   ![Ui](images/Ui.png)

5. Type the command in the Command Box and press ENTER to execute it. For example, typing `help` and pressing ENTER will open the help window.<br>

6. Refer to the [Features](#6-command-summary) below to see which commands TutorAid understands.

***

# 4. Beginner's tutorial

### Step 5: Deleting a student

Lastly, imagine one of your students `Roy Balakrishnan` has graduated from secondary school and he no longer requires your tutoring services. You wish to remove his student details
since he is no longer your student. To do so, you can easily delete his data in TutorAid by following the steps below:
1. Type `list` in the command box to see the list of all your students in the Student Panel.
2. Locate `Roy Balakrishnan` in the Student Panel and take note of his index. In this case, it would be `6`.
3. Type `del -s 6` into the command box. Upon pressing enter, all details belonging to `Roy Balakrishnan` will be removed.

Now, the Student and Lesson Panels will be updated accordingly without any data of `Roy Balakrishnan`.

# 5. Features

If you are new to TutorAid, we hope that you have gone through the [Beginner's Tutorial](#4-beginners-tutorial) to gain a better understanding on how TutorAid can help to
ease your tutoring tasks! This section lists all the available commands in TutorAid. The commands can be split into 3 main categories: 'Student' commands, 'Lesson' commands and 'Other' commands.
This section will also be split into these 3 sub-sections for easier navigation.

For more information about how to interpret any of the commands, check out the [Command Format](#23-command-format) and [Command Syntax](#231-command-syntax-in-this-guide) sections.

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpWindow.png)

Format: `help`

## 5.1 Student Commands
### Adding a student: `add -s`
{:.no_toc}
Adds a new student to TutorAid.

Format: `add -s sn/STUDENT_NAME [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`

Examples:
* `add -s sn/John Does sp/81234567 pn/Mrs Doe pp/91234567` adds a student with name `John Does`, student phone number `81234567`, parent's name `Mrs Doe` and parent's phone number `91234567`.

> :bulb: The student's phone number, parent's name and parent's phone number are optional details for tutors to include.

### Listing all students : `list`

Shows a list of all students in TutorAid in the order that they were added. Use the `-a` flag to display all fields, otherwise fields are hidden by default.

Format: `list [-a]`

Examples:

- `list` displays all students and lessons by only showing their names and list indexes.
- `list -a` displays all students and lessons while showing all of their fields' data.

### Deleting a student : `del -s`
{:.no_toc}
Deletes the specified student with the given student index from TutorAid.

Format: `del -s STUDENT_INDEX`

* Deletes the student at the specified STUDENT_INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1,2,3, …​

Example:
* `del -s 2` deletes the 2nd student.

### Editing a student : `edit -s`
{:.no_toc}
Edits the specified student with the given student index from TutorAid.

Format: `edit -s STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`

* Edits the student at the specified STUDENT_INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1,2,3, …​
* At least one of the optional fields should be present

Example:

* `edit -s 2 pp/91112222` updates the 2nd student's parent contact number to `91112222`.

### Viewing a student : `view -s`
{:.no_toc}

Displays the specified student’s name, phone number, progress, and lessons, along with their parent’s name and phone number.

Format: `view -s STUDENT_INDEX`

* Display details of the student at the specified STUDENT_INDEX.
* Display details of the lessons the student at the specified STUDENT_INDEX has.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `view -s 2` shows the details associated with the 2nd student

### Locating students by name: `find -s`
{:.no_toc}
Finds students whose names contain any of the given keywords.

Format: `find -s KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Partial keywords will be matched e.g. `Han` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find -s John` returns `john`, `John Doe` and `Johnny Liu`
* `find -s alex david` returns `Alex Yeoh`, `David Li`<br>


### Adding progress for a student : `add -p`
{:.no_toc}
Adds a given string representing progress to a student with a given student index.

Format: `add -p STUDENT_INDEX PROGRESS`

* Adds `PROGRESS` for the student at the specified `STUDENT_INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `add -p 2 completed homework` adds `completed homework` to the 2nd student in the list.

### Deleting progress from a student : `del -p`
{:.no_toc}
Removes the string representing progress from the student with a given student index.

Format: `del -p STUDENT_INDEX`

* Deletes the `PROGRESS` for the student at the specified `STUDENT_INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `del -p 2` deletes the progress of the 2nd student in the list.

## 5.2 Lesson Commands

### Adding a lesson: `add -l`
{:.no_toc}
Adds a new lesson to TutorAid.

Format: `add -l n/LESSON_NAME [c/LESSON_CAPACITY] [p/LESSON_PRICE] [t/LESSON_TIMING]`

* The lesson name should only contain alphanumeric characters and spaces.
* If provided, the lesson's capacity must be a **positive integer** 1, 2, 3, …
* If provided, the lesson's price must be a **non-negative number** with either 0 or 2 decimal places. Examples of a valid price are `80` and `85.50`.
* The lesson's timing can be anything you want (other than hyphens and slashes) - you could use it to remind yourself what time your lesson is, but you can use this space for any remarks or reminders you may have.

Examples:
* `add -l n/P6 Maths c/20 p/80 t/Monday 1200-1400` adds a lesson with name `P6 Maths`, capacity of `20 students`, price of `$80` and timing `Monday 1200-1400`.

> :bulb: The lesson's capacity, price and timing are optional details for tutors to include.

### Deleting a lesson : `del -l`
{:.no_toc}

Deletes the specified lesson with the given lesson index from TutorAid.

Format: `del -l LESSON_INDEX`

* Deletes the lesson at the specified `LESSON_INDEX`.
* `LESSON_INDEX` refers to the index number shown in the displayed lesson list.
* `LESSON_INDEX` must be a **positive integer** 1,2,3, …​

Examples:
* `del -l 3` deletes the 3rd student.

### Editing a lesson: `edit -l`
{:.no_toc}

Edits the specified lesson by updating its fields

Format: `edit -l LESSON_INDEX [n/LESSON_NAME] [c/LESSON_CAPACITY] [p/LESSON_PRICE] [t/LESSON_TIMING]`

* If provided, the lesson name should only contain alphanumeric characters and spaces.
* If provided, the lesson's capacity must be a **positive integer** 1, 2, 3, …
* If provided, the lesson's price must be a **non-negative number** with either 0 or 2 decimal places. Examples of a valid price are `80` and `85.50`.
* * The lesson's timing can be anything you want (other than hyphens and slashes) - you could use it to remind yourself what time your lesson is, but you can use this space for any remarks or reminders you may have.
* At least 1 out of 4 optional fields must be provided.

> :bulb: After you run this command, the Student Panel will only show students who are taking this lesson. Don't worry: you can easily find your students again using the [`list -a`](#listing-all-students-and-lessons-list) command.

Examples:
* `edit -l 1 c/20 p/80` updates the 1st lesson's capacity to `20 students` and price to `$80`

### Viewing a lesson : `view -l`
{:.no_toc}

Displays the specified lesson’s name, capacity, price and timing, along with names of students who have the specified lesson.

Format: `view -l LESSON_INDEX`

* Display details of the lesson at the specified LESSON_INDEX.
* Display details of the students that have the lesson at the specified LESSON_INDEX.
* The index refers to the index number shown in the displayed lesson list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `view -l 2` shows the details associated with the 2nd lesson

### Locating lessons by name: `find -l`
{:.no_toc}

Finds lessons whose names contain any of the given keywords.

Format: `find -l KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `math` will match `Math`
* The order of the keywords does not matter. e.g. `Math Upper` will match `Upper Math`
* Only the name is searched.
* Partial keywords will be matched e.g. `Sci` will match `Science`
* Lessons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Mathematics Upper` will return `Mathematics Lower`, `Math Upper`

Examples:
* `find -l maths` returns `maths`, `Maths 1` and `Mathematics`

## 5.3 Student and Lesson Commands
### Listing all students and lessons: `list`
{:.no_toc}

Shows a list of all students and lessons in TutorAid in the order that they were added. Use the `-a` flag to display all fields, otherwise most fields are hidden by default.

Format: `list [-a]`

Examples:

- `list` displays all students and lessons in TutorAid by only showing their names and list indexes.
- `list -a` displays all students and lessons in TutorAid while showing all of their fields' data.

### Adding students to lessons: `add -sl`
{:.no_toc}

Adds students to lessons in TutorAid.

Format: `add -sl s/STUDENT_INDEX... l/LESSON_INDEX...`

* Adds the students at the specified student indexes to the lessons at the specified lesson indexes.
* The index refers to the index number shown in the displayed student list and lesson list.
* The index must be a **positive integer** 1, 2, 3, ...

Examples:
* `add -sl s/1 2 3 l/1 2` add students with index 1, 2, 3 into lessons with index 1, 2.

> :exclamation: All of these students must not be attending any of the lessons provided for this command to work.

### Deleting students from lessons: `del -sl`
{:.no_toc}

Deletes students from lessons in TutorAid.

Format: `del -sl s/STUDENT_INDEX... l/LESSON_INDEX...`

* Deletes the students at the specified student indexes from the lessons at the specified lesson indexes.
* The index refers to the index number shown in the displayed student list and lesson list.
* The index must be a **positive integer** 1, 2, 3, ...

Examples:
* `del -sl s/2 3 l/1 2 3` deletes students with index 2, 3 from lessons with index 1, 2, 3.

> :exclamation: All of these students must be attending all the lessons provided for this command to work.

## 5.4 Other Commands
### Viewing help : `help`
{:.no_toc}

Shows a message explaining how to access the help page.

![help message](images/helpWindow.png)

Format: `help`

### Clearing all entries : `clear`
{:.no_toc}

Clears all entries from TutorAid.

Format: `clear`

### Exiting the program : `exit`
{:.no_toc}

Exits the program.

Format: `exit`

## 5.5 Saving and Editing Data

### Saving the data
{:.no_toc}

TutorAid data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
{:.no_toc}

TutorAid data are saved as a JSON file `[JAR file location]/data/tutoraid.json`. Advanced users are welcome to update data directly by editing that data file.


> :exclamation: **If your changes to the data file makes its format invalid, TutorAid will discard all data and start with an empty data file at the next run.**

***

# 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TutorAid home folder.

***

# 7. Command summary

### 7.1 Student commands

|---
Action | Format and Examples
--------|------------------
**[Add student](#adding-a-student-add)** | `add -s sn/STUDENT_NAME [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]…​` <br> e.g., `add -s sn/John Does sp/81234567 pn/Mrs Doe pp/91234567`
**[Delete student](#deleting-a-student--delete)** | `del -s STUDENT_INDEX`<br> e.g., `delete 3`
**[Edit student](#editing-a-student--edit--s)** | `edit -s STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`<br>e.g., `edit -s 2 pp/91112222`
**[View student](#viewing-a-student--view--s)** | `view -s STUDENT_INDEX`<br> e.g., `view -s 2`
**[Find student](#locating-students-by-name-find--s)** | `find -s KEYWORD [MORE_KEYWORDS]`<br>e.g., `find -s roy`
**[Add Progress](#adding-progress-for-a-student--add--p)** | `add -p STUDENT_INDEX PROGRESS` <br> e.g., `add -p 2 completed homework`
**[Delete Progress](#deleting-progress-from-a-student--del--p)** | `del -p STUDENT_INDEX` <br> e.g., `del -p 2`

### 7.2 Lesson commands

|---
Action | Format and Examples
--------|------------------
**[Add lesson](#adding-a-lesson-add--l)** | `add -l n/P6 Maths c/20 p/80 t/Monday 1200-1400`<br>e.g.,`add -l n/P6 Maths c/20 p/80 t/Monday 1200-1400`
**[Delete lesson](#deleting-a-lesson--del--l)** | `del -l LESSON_INDEX` <br>e.g.,`del -l 3`
**[Edit lesson](#editing-a-lesson-edit--l)** | `edit -l LESSON_INDEX [n/LESSON_NAME] [c/LESSON_CAPACITY] [p/LESSON_PRICE] [t/LESSON_TIMING]`<br>e.g., `edit -l c/20 p/80`
**[View lesson](#viewing-a-lesson--view--l)** | `view -l LESSON_INDEX`<br> e.g., `view -l 2`
**[Find lesson](#locating-lessons-by-name-find--l)** | `find -l KEYWORD [MORE_KEYWORDS]`<br>e.g., `find -l maths`

### 7.3 Student and Lesson commands

|---
Action | Format and Examples
--------|------------------
**[List](#listing-all-students-and-lessons-list)** | `list [-a]`<br>e.g., `list`, `list -a`
**[Add students to lessons](#adding-students-to-lessons-add--sl)** | `add -sl s/STUDENT_INDEX... l/LESSON_INDEX...`<br>e.g.,`add -sl s/1 2 3 l/1 2`
**[Delete students from lessons](#deleting-students-from-lessons-del--sl)** | `del -sl s/STUDENT_INDEX... l/LESSON_INDEX...`<br>e.g.,`del -sl s/2 3 l/1 2 3`
**[Clear](#clearing-all-entries--clear)** | `clear`

### 7.4 Other commands

|---
Action | Format and Examples
--------|------------------
**[Help](#viewing-help--help)** | `help`
**[Clear](#clearing-all-entries--clear)** | `clear`
**[Exit](#exiting-the-program--exit)** | `exit`
