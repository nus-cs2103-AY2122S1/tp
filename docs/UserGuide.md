---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

TutorMaster is a desktop app for freelance tutors to manage their students’ details and related tasks. It is optimised for use via a Command Line Interface (CLI) while still benefiting from a Graphical User Interface (GUI).

## How To Use This User Guide

1. Install Tutor Master by following the instructions in the [Installation Guide](#installation-guide).
2. To get familiar with the User Guide, you can refer to [About This Document](#about-this-document).
3. Get familiar with the various components of the app by referring to the [User Interface Guide](#user-interface-ui-of-tutor-master).
4. If you are a new user, you can follow our guide in [Quick Start](#quick-start) to familiarise yourself with the app.
5. If you are familiar with the app, you can refer to the [Features](#features) section to see details of all commands or
    look at [Command Summary](#command-summary) to see all commands easily.
6. Refer to our [FAQ](#faq) if you have any questions.

## About This Document
Symbol | Explanation
--------|------------------
`command` | A blue highlight (called a mark-up) indicates a command that can be typed into the Command Panel and executed by Tutor Master
:information_source: | This symbol indicates additional information about the heading or subheading
:exclamation | This symbol indicates crucial information you should be aware of when using Tutor Master


<div style="page-break-after: always;"></div>

## Installation Guide

1. Ensure you have __Java 11__ or above installed in your Computer. You can also install it [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

2. Download the latest `tutormaster.jar` [here](https://github.com/AY2122S1-CS2103T-W16-4/tp/releases).

3. Copy the file to the folder you want to use as the home folder for your TutorMaster.

4. To open the application,
    * If you are a Mac user, open Terminal and navigate to the directory with Tutor Master. Type `java -jar tutormaster.jar` and run the command.
    * If you are Windows user, double-click the jar file to start the app.
    * The GUI similar to below should appear in a few seconds. Note how the app contains some sample data for you to view.<br>

   ![Ui](images/Ui.png)

<div style="page-break-after: always;"></div>

## User Interface (UI) of Tutor Master

   ![Labelled UI](images/imagesInUG/labelledUI.png)

The UI consists of a:
* Command Panel - To enter commands to be executed
* Result Display - Displays result of executed command
* Person Panel - Displays all added students
* Task Panel - Displays all added tasks
* Group Panel - Displays all added groups
* Viewing Panel - Displays either the schedule, group or student to be viewed

<div style="page-break-after: always;"></div>

## Quick Start

Now that you have installed Tutor Master, let us give it a try!

Let us 
* Create a student named "David" 
* Add and assign a task named "Science Report" to "David".
* Add him to a group named "CS2103T"

We shall type commands in the Command Panel and hit `Enter` to execute it.

Here are some starting commands to help you navigate around the application:

### Managing Students

Since the goal is to manage students, let's start by adding a new student to the address book.
Let's try adding a student with the following details:
* Name: David
* Phone Number: 90001111
* Email: david@gmail.com
* Address: Singapore

1. Type the command `student -a n/David p/90001111 e/david@gmail.com a/Singapore` and hit `Enter`.
   * This adds a student with the details specified.
   * You will now be able to see __David__ at the entry in the Person Panel on the left,
   as shown in the image below.
   ![Add David](images/imagesInUG/student-addDavid.png)
   
2. Now lets try editing his address. Note the index number beside his name in the Person Panel, in this case it is 4.<br>
   Type the following command `student -e 4 a/Kent Ridge` and hit `Enter`.
   * This updates the address of David, who is at index __4__ to __Kent Ridge__.
   * Note that the other details are unchanged, you can choose which fields you want to edit.
<br><br>

3. You can add a lesson to David. Let's assign a __Math__ lesson from __10:00__ to __12:00__ on __Friday__. <br>
   Type the following command `student -al 4 s/Math st/10:00 et/12:00 d/Fri` and hit `Enter`.
   * This adds a lesson to David with the given details.
   * You can view your schedule for the week by entering `schedule`, try it!
<br><br>

4. If you wish to view more details of a student, you can use the student view command.<br>
   Type `student -v 4` in the Command Panel and hit `Enter`.
    * This displays the student at `STUDENT_INDEX` 4 in the Viewing Panel.

<div style="page-break-after: always;"></div>

### Managing Tasks

Next, let us add a task with description __Lab Report__ and deadline __12 November 2021__. 

1. Type the command `task -a n/Lab Report d/2021-11-12` in the Command Panel and hit `Enter`.
    * This adds a task with the details specified above. 
    * You should now see __Lab Report__ in the Task Panel in the middle column as shown in the image below.
      ![Add David](images/imagesInUG/task-add.png)

2. Now, let us try editing the description of the task to Science Report. Note the index of the task in the Task Panel, 
   which is 3 in this case. Type `task -e 3 n/Science Report` in the Command Panel and hit `Enter`.
    * This updates the description of the task at index __3__ to __Science Report__.
    * The description remains unchanged.

    <div style="page-break-after: always;"></div>

3. You can also assign this task, __Science Report__ to __David__.
    * Take note of the index of the task you would like to
      assign and the index of the student to whom the task should be assigned. In this case, the index of
      __Science Report__ is __3__ while the index of __David__ is __4__.
    * Type the command `task -as 4 3` in the Command Panel and hit `Enter`.
    * This assigns __Science Report__ to __David__.

### Managing Groups

Let us now add "David" to a group named "CS2103T".

1.  Type `group -a 4 n/CS2103T` in the Command Panel and hit `Enter`.
    * This creates a group named __CS2103T__ and includes __David__ who is at `STUDENT_INDEX` 4 in the Person Panel.
    * __CS2103T__ is now added to the Group Panel. It has an `GROUP_INDEX` of 3 in the Group Panel.
    * This group currently has __David__ inside
      ![Add David To Group](images/imagesInUG/group-addDavidInGroup.png)

2. Type `group -v 3` in the Command Panel and hit `Enter`.
    * This displays the details of the group at `GROUP_INDEX` 3 in the Viewing Panel.

You can refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  * e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets `[]` are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* `INDEX` parameters are very common in this application. Ensure that **they are a positive integer and is less than 2,147,483,648**.

</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Students

#### Adding a student: `student -a`

Adds a student to the Person Panel.

If you have a new student to tutor, you can enter this command with their respective details to add 
them to the Tutor Master.

Format: `student -a n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Adds a student with the specified `NAME`
* `-a` refers to the add command
* Two students are considered the same if all their names, phone numbers and emails are the same. It is a requirement that **same students can not be added to the app simultaneously**.

Examples:
* `student -a n/John p/12345678 e/john@mail.com a/42 Wallaby Way, Sydney`<br>
  adds a student with the name John, phone number 12345678, email john@mail.com and address at 42 Wallaby Way, Sydney
* `student -a n/Barbara p/12344321 e/barber@gmail.com a/123 Sesame Street t/graduate t/actress`<br>
  adds a student with the name Barbara, phone number 12344321, email barber@gmail.com, address at 123 Sesame Street, and tags graduate and actress
  
#### Viewing a student: `student -v`

Views a particular student's details in the Person Panel.

Let's say you want to look at a particular student in detail.
You can type this command to view their full name, phone number, email address, address and any 
other lessons, tasks, exams or groups they belong to.

Format: `student -v STUDENT_INDEX`

* Views the student details specified at `STUDENT_INDEX`. `STUDENT_INDEX` should be a positive number.
* `-v` refers to the view command.

Examples:
* `student -v 3` view the student details at index 3.

![studentview](images/imagesInUG/student-view.png)

<div style="page-break-after: always;"></div>

#### Editing a student: `student -e`

Edits the details of a student in the Person Panel.

Let's say one of your students changed their email address. You can edit the particular student's
email address by entering the command below with the updated email address.

Format: `student -e STUDENT_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the details of the student specified by the `STUDENT_INDEX`. `STUDENT_INDEX` should be a
  positive number and refers to the index number of a student in the Person Panel.
* `-e` refers to the edit command
* It is a requirement that **at least one of the optional fields should be provided**.

Example:

* `student -e 3 p/88888888 e/johnny@mail.com` edits the phone number and email
  address of the third student in the Person Panel to 88888888 and johnny@mail.com
  respectively.


#### Deleting a student: `student -d`

Deletes the specific student in the Person Panel.

If you happen to not teach a particular student anymore, you can enter this command to delete 
the student. Executing this command will also remove the student from any groups they are in.

Format: `student -d STUDENT_INDEX`

* Deletes the student specified by the `STUDENT_INDEX`.
* `-d` refers to the edit command
* `STUDENT_INDEX` should be a positive number and refers to the index number of a student in the Person Panel.

Examples:

* `student -d 3` deletes the third student in the Person Panel

#### Listing all students : `list`
Shows a list of all students in the Person Panel.

Format: `list`

<div style="page-break-after: always;"></div>

#### Locating students by name: `student -f`

Finds students whose names contain any of the given keywords.

Suppose you would like to further edit or manage a students' details, you can narrow down
list of students in the Person Panel to only display students that you search for.

Format: `student -f KEYWORD…​`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `student -f John` returns `john` and `John Doe`
* `student -f alex david` returns `Alex Yeoh`, `David Li`<br>

#### Adding a lesson to a student: `student -al`

Adds a lesson to a student.

Let's say you would like to note down your lesson schedule with your student,
you should specify the lesson subject, as well as the day of week and time of the lesson.

Format: `student -al STUDENT_INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY`

* Adds a lesson to the student specified by the `STUDENT_INDEX`.
* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DAY` takes in the following inputs: `Mon Tue Wed Thu Fri Sat Sun`.
* `START_TIME` and `END_TIME` takes in the time in 24-hour, HH:MM, format. For example `08:00`
  for 8am.
<div markdown="block" class="alert alert-info">

**:information_source: Overlapping lessons:**<br>
* The same student cannot be allocated to multiple lessons that are overlapping. Overlap is defined as being
  on the same day and within the same time frame.
* However, lessons of different students can still overlap. This may be disallowed in a future version.
</div>

Example:
* `student -al 1 s/Biology st/08:00 et/09:00 d/Mon` adds a lesson with the subject name biology
  starting at 8am and ending at 9am on Mondays.

![studentaddlesson](images/imagesInUG/student-addlesson.png)

#### Deleting a lesson from a student: `student -dl`

Deletes a lesson from the specific student.

If you are not continuing a student's lesson anymore, you can enter this command to 
remove the lesson from your schedule.

Format: `student -dl STUDENT_INDEX LESSON_INDEX`

* Specifies the student at `STUDENT_INDEX`.
* Deletes the specified lesson at `LESSON_INDEX` of the student specified.
* Both `STUDENT_INDEX` and `LESSON_INDEX` must be a positive number.
* The `LESSON_INDEX` can be found by using the [student viewing command](#viewing-a-student-student--v) as shown below.

![studentLessonIndex](images/imagesInUG/student-findLesson.png)

Example:
* `student -dl 1 1` deletes the first lesson from the first student.


#### Adding an exam to a student: `student -ae`

Adds an exam to the specific student.

If you would like to note down one of your student's exam dates, you should specify
the date and time of the exam as well as the subject that the exam is for.

Format: `student -ae STUDENT_INDEX s/SUBJECT d/DATE_TIME`

* Adds an exam to the student specified by the `STUDENT_INDEX`.
* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DATE_TIME` takes in a date and time in the following format: `yyyy-MM-dd HH:mm`.
* There are currently no restrictions on `DATE_TIME`, please ensure that the exam dates are correct.

Example:
* `student -ae 1 s/Math d/2021-12-20 14:00` adds an exam with the subject name Math
  on 20th December 2021, 2pm.


#### Deleting an exam from a student: `student -de`

Deletes an exam from the specific student.

If you do not want to note down a particular student's exam anymore, you can remove it by
entering this command.

Format: `student -de STUDENT_INDEX EXAM_INDEX`

* Specifies the student at `STUDENT_INDEX`.
* Deletes the specified exam at `EXAM_INDEX` of the student specified.
* Both `STUDENT_INDEX` and `EXAM_INDEX` must be a positive number.
* The `EXAM_INDEX` can be found by using the [student viewing command](#viewing-a-student-student--v), similarly to
deleting lessons.

Example:
* `student -de 1 1` deletes the first exam from the first student.


--------------------------------------------------------------------------------------------------------------------

### Groups

#### Grouping students: `group -a`

Group students together. 

This is useful for when you have a group of students that has the same lesson schedule or
tasks to complete.

Format: `group -a STUDENT_INDEX…​ n/GROUPNAME`

* Groups students into a group specified at `GROUPNAME`
* `-a` refers to the add group command
* You can specify as many student index numbers as required.
* The student index refers to the student index number shown in the displayed Person Panel.
* The student index **must be a positive integer** 1, 2, 3, …​

Examples:
* `group -a 1 3 5 n/Team2` creates a group "Team2" that includes students at index 1, 3 and 5.

![groupadd](images/imagesInUG/group-add.png)

#### Viewing a group: `group -v`

Views a group of students.

Let's say you want to look at all the students inside one of your groups. 
Instead of viewing each student individually to see the group they are in, you can type this command to 
view your students, your lessons and the tasks with the group.

Format: `group -v GROUP_INDEX`

* Views a group of students specified at `GROUP_INDEX`
* `-v` refers to view group command.
* The group index refers to the group index number shown in the displayed Group Panel.
* The group index **must be a positive integer** 1, 2, 3, …​

Examples:
* `group -v 5` views the group specified at the index 5.

<div style="page-break-after: always;"></div>

#### Deleting a group: `group -d`

Deletes a group of students.

If you have stopped teaching a particular group of students, you can easily remove the group
from the application by typing this command. This will still not remove the students from your Person Panel.

Format: `group -d GROUP_INDEX`

* Deletes a group of students specified at `GROUP_INDEX`
* `-d` refers to delete group command.
* The group index refers to the group index number shown in the Group Panel.
* The group index **must be a positive integer** 1, 2, 3, …​

Examples:
* `group -d 5` deletes the group specified at the index 5.

#### Adding a lesson to a group: `group -al`

Adds a lesson to the specific group.

If you would like to note down your lesson schedule with the entire group, 
you should specify the lesson subject, as well as the day of week and time of the lesson.

Format: `group -al GROUP_INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY`

* Adds a lesson to the group specified by the `GROUP_INDEX`.
* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DAY` takes in the following inputs: `Mon Tue Wed Thu Fri Sat Sun`.
* `START_TIME` and `END_TIME` takes in the time in 24-hour, HH:MM, format. For example `08:00`
  for 8am.

<div markdown="block" class="alert alert-info">

**:information_source: Overlapping lessons:**<br>
* A group cannot be allocated a lesson if any students within the group have overlapping lessons. Overlap is defined as being
  on the same day and within the same time frame.
* However, lessons of different students or groups can still overlap. This may be disallowed in a future version.
</div>

Example:
* `group -al 1 s/Biology st/08:00 et/09:00 d/Mon` adds a lesson with the subject name biology
  starting at 8am and ending at 9am on Mondays.

<div style="page-break-after: always;"></div>

#### Deleting a lesson from a group: `group -dl`

Deletes a lesson from the specific group.

If you are not continuing this lesson with the group anymore, you can type this command to 
remove the lesson from the group.

Format: `group -dl GROUP_
LESSON_INDEX`

* Specifies the group at `GROUP_INDEX`.
* Deletes the specified lesson at `LESSON_INDEX` of the group specified.
  The `LESSON_INDEX` can be found using the [group viewing command](#viewing-a-group-group--v).
* Both `GROUP_INDEX` and `LESSON_INDEX` must be a positive number.

Example:
* `group -dl 1 1` deletes the first lesson from the first group.

--------------------------------------------------------------------------------------------------------------------

### Tasks

#### Adding a task: `task -a`

Adds a task to the Task Panel.

Format: `task -a n/NAME d/DEADLINE`

* Adds a task with the name `NAME` and deadline `DEADLINE`
* The deadline must be in the format `YYYY-MM-DD`
* `-a` refers to the add command

Examples:
* `task -a n/CS2100 Lab 1 d/2021-10-10` adds the task "CS2100 Lab 1"

<div style="page-break-after: always;"></div>

#### Editing a task: `task -e`

Edits a task in the Task Panel.

Format: `task -e TASK_INDEX [n/NAME] [d/DEADLINE]`

* Edits a task with the index `TASK_INDEX`. The index should be a positive number and refers to the index number of the task in the students’ task list
* `-e` refers to the edit command
* It is a requirement that **at least one of the optional fields should be provided!**
* The current value will be updated to the value input by the user

Examples:
* `task -e 4 d/2021-12-31` edits the deadline of the fourth task to 31 December 2021

![taskedit](images/imagesInUG/task-edit.png)

<div style="page-break-after: always;"></div>

#### Deleting a task: `task -d`

Deletes the specific task from the Task Panel.

Format: `task -d TASK_INDEX`

* Deletes the task specified at `TASK_INDEX`. The index should be a positive number.
* `-d` refers to the deletion command.
* The task index refers to the index number shown in the displayed Task Panel.

Examples:

* `task -d 1` deletes the task at index 1

#### Listing all tasks: `task -l`

Shows a list of all the tasks in the Task Panel.

Format: `task -l`

#### Finding a task by name: `task -f`

Find tasks whose names contain any of the given keywords.

Format: `task -f KEYWORD…​`

* The search is case-insensitive. e.g `report` will match `Report`
* The order of the keywords does not matter. e.g. `Report Lab` will match `Lab Report`
* Only the name is searched.
* Only full words will be matched e.g. `Rep` will not match `Report`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Report Submission` will return `Report 1`, `Lab Submission`

Examples:
* `task -f Report` returns `Report 1` and `Report Submission`
* `task -f lab submission` returns `Lab Submission`, `Report Submission`<br>

![taskfind](images/imagesInUG/task-find.png)

#### Assigning tasks to students: `task -as`

Assigns a specific task to students’ task list.

Format: `task -as STUDENT_INDEX TASK_INDEX`

* Assigns the task specified at index `TASK_INDEX` to the “Student” object specified at index `STUDENT_INDEX`.
* `-as` refers to the "assign to student" command.

Examples:
* `task -as 2 4` adds the task at the index 4 to the student at the index 2

<div style="page-break-after: always;"></div>

#### Assigning tasks to groups: `task -ag`

Assigns a specific task to groups’ task list.

Format: `task -ag GROUP_INDEX TASK_INDEX`

* Assigns the task specified at index `TASK_INDEX` to the “Group” object specified at index `GROUP_INDEX`.
* `-ag` refers to the "assign to group" command.

Examples:
* `task -ag 2 4` adds the task at the index 4 to the group at the index 2

#### Unassigning tasks from students: `task -unas`

Unassigns a specific task from students’ task list.

Format: `task -unas STUDENT_INDEX TASK_INDEX`

* Unassigns the task specified at index `TASK_INDEX` from the “Student” object specified at index `STUDENT_INDEX`.
* `-unas` refers to the "unassign from student" command.

Examples:
* `task -unas 2 4` removes the task at the index 4 from the student at the index 2

#### Unassigning tasks from groups: `task -unag`

Unassigns a specific task from groups’ task list.

Format: `task -unag GROUP_INDEX TASK_INDEX`

* Unassigns the task specified at index `TASK_INDEX` from the “Group” object specified at index `GROUP_INDEX`.
* `-unag` refers to the "unassign from group" command.

Examples:
* `task -unag 2 4` removes the task at the index 4 from the group at the index 2

<div style="page-break-after: always;"></div>

#### Marking a task as done by student: `task -do`

Marks that a student has done a task.

Format: `task -do STUDENT_INDEX TASK_INDEX`

* Marks the task specified at index `TASK_INDEX` as done by the “Student” object specified at index `STUDENT_INDEX`.
* `-do` refers to the "mark as done" command.

Examples:
* `task -do 2 4` marks the task at the index 4 as done by the student at the index 2


#### Marking a task as done by group: `task -dog`  (Coming soon)

Marks that a group has done a task.

Format: `task -dog GROUP_INDEX TASK_INDEX`

* Marks the task specified at index `TASK_INDEX` as done by the Group object specified at index `GROUP_INDEX`.
* `-dog` refers to the "mark as done by group" command.

Examples:
* `task -dog 2 4` marks the task at the index 4 as done by the group at the index 2

#### Marking a task as not done by student: `task -undo`

Marks that a student has not done a task.

Format: `task -undo STUDENT_INDEX TASK_INDEX`

* Marks the task specified at index `TASK_INDEX` as not done by the “Student” object specified at index `STUDENT_INDEX`.
* `-undo` refers to the "mark as undone" command.

Examples:
* `task -undo 2 4` marks the task at the index 4 as not done by the student at the index 2

<div style="page-break-after: always;"></div>

#### Marking a task as not done by group: `task -undog`  (Coming soon)

Marks that a group has not done a task.

Format: `task -undog GROUP_INDEX TASK_INDEX`

* Marks the task specified at index `TASK_INDEX` as not done by the Group object specified at index `GROUP_INDEX`.
* `-undog` refers to the "mark as undone for group" command.

Examples:
* `task -undog 2 4` marks the task at the index 4 as not done by the group at the index 2

--------------------------------------------------------------------------------------------------------------------

### Others

#### Viewing schedule : `schedule`

Views the current lesson schedule for the week.

Format: `schedule`

#### Viewing help : `help`

Views an info page for a list of commands and examples

Format: `help`

#### Clearing all entries : `clear`

Clears all entries in Tutor Master.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

#### Saving the data

Tutor Master data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

Tutor Master data are saved as a JSON file `[JAR file location]/data/tutormaster.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Tutor Master will discard all data and start with an empty data file at the next run.
</div>

#### Archiving data files (Coming Soon)

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer? <br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Tutor Master home folder.

**Q**: How do I edit lesson details of a student? <br>
**A**: There is currently no functionality for that, the workaround will be to [delete the lesson](#deleting-a-lesson-from-a-student-student--dl)
first before [adding it again](#adding-a-lesson-to-a-student-student--al) with the new details.


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

Action | Format, Examples
--------|------------------
**Add a student** | `student -a n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `student -a n/John p/12345678 e/john@mail.com a/42 Wallaby Way, Sydney`
**Edit a student** | `student -e STUDENT_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `student -e 3 c/88888888 e/johnny@mail.com`
**View a student** | `student -v STUDENT_INDEX` <br> e.g., `student -v 3`
**Delete a student** | `student -d STUDENT_INDEX` <br> e.g., `student -d 3`
**Find a student** | `student -f KEYWORD…​` <br> e.g., `student -f John`
**List all students** | `list`
**Add a lesson to a student** | `student -al STUDENT_INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY` <br> e.g. `student -al 1 s/Biology st/08:00 et/09:00 d/Mon`
**Delete a lesson from a student** | `student -dl STUDENT_INDEX LESSON_INDEX` <br> e.g. `student -dl 1 1`
**Add an exam to a student** | `student -ae STUDENT_INDEX s/SUBJECT d/DATE_TIME` <br> e.g. `student -ae 1 s/Math d/2021-12-20 14:00`
**Delete an exam from a student** | `student -de STUDENT_INDEX EXAM_INDEX` <br> e.g. `student -de 1 1`
**Group students** | `group -a STUDENT_INDEX…​ n/GROUP_NAME` <br> e.g., `group -a 1 3 5 n/Team2`
**View a group** | `group -v GROUP_INDEX` <br> e.g., `group -v 5`
**Delete a group** | `group -d GROUP_INDEX` <br> e.g., `group -d 2`
**Add a lesson to a group** | `group -al GROUP_INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY` <br> e.g. `group -al 1 s/Chinese st/16:00 et/18:00 d/Thu`
**Delete a lesson from a group** | `group -dl GROUP_INDEX LESSON_INDEX` <br> e.g. `group -dl 1 1`
**Add a task** | `task -a n/NAME d/DEADLINE` <br> e.g., `task -a n/CS2100 Lab 1 d/2021-10-10`
**Edit a task** | `task -e TASK_INDEX [n/NAME] [d/DEADLINE]` <br> e.g., `task -e 4 d/2021-12-31`
**Delete a task** | `task -d TASK_INDEX` <br> e.g., `task -d 1`
**Find a task** | `task -f KEYWORD…​` <br> e.g., `task -f Report`
**Assign a task to a student** | `task -as STUDENT_INDEX TASK_INDEX` <br> e.g., `task -as 2 4`
**Assign a task to a group** | `task -ag GROUP_INDEX TASK_INDEX` <br> e.g., `task -ag 2 4`
**Unassign a task from a student** | `task -unas STUDENT_INDEX TASK_INDEX` <br> e.g., `task -unas 2 4`
**Unassign a task from a group** | `task -unag GROUP_INDEX TASK_INDEX` <br> e.g., `task -unag 2 4`
**Mark task as done by student** | `task -do STUDENT_INDEX TASK_INDEX` <br> e.g., `task -do 2 4`
**Mark task as done by group (coming soon)** | `task -dog GROUP_INDEX TASK_INDEX` <br> e.g., `task -dog 2 4`
**Mark task as not done by student** | `task -undo STUDENT_INDEX TASK_INDEX` <br> e.g., `task -undo 2 4`
**Mark task as not done by group (coming soon)** | `task -undo GROUP_INDEX TASK_INDEX` <br> e.g., `task -undog 2 4`
**Schedule** | `schedule`
**Help** | `help`
**Clear all entries** | `clear`
**Exit** | `exit`
