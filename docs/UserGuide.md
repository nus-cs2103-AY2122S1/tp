---
layout: page
title: User Guide
---

TutorMaster is a desktop app for freelance tutors to manage their students’ details and related tasks. It is optimised for use via a Command Line Interface (CLI) while still benefiting from a Graphical User Interface (GUI).

* Table of Contents
  {:toc}
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutormaster.jar`.

1. Copy the file to the folder you want to use as the home folder for your TutorMaster.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`help`** : Displays a help message with a link to the User Guide.

   * **`student -a n/Alice p/12345678 e/alice@gmail.com a/42 Wallaby Way`** : Adds a contact named `Alice` to the students list.

   * **`student -v 3`**: Views the 3rd student in the persons list.

   * **`clear`** : Clears all entries.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
* `INDEX` parameters are very common in this application. Ensure that they are a positive integer and is less than 2,147,483,648.

</div>

### Viewing help : `help`

Views a info page for a list of commands and examples

Format: `help`

### Adding a student: `student -a`
<details markdown="1"><summary>Adds a student to the students list.</summary>

Format: `student -a n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Adds a student with the specified `NAME`
* `-a` refers to the add command
* Commands in `[ ]` are optional

Examples:
* `student -a n/John p/12345678 e/john@mail.com a/42 Wallaby Way, Sydney`<br>
  adds a student with the name John, phone number 12345678, email john@mail.com and address at 42 Wallaby Way, Sydney
* `student -a n/Barbara p/12344321 e/barber@gmail.com a/123 Sesame Street t/graduate t/actress`<br>
  adds a student with the name Barbara, phone number 12344321, email barber@gmail.com, address at 123 Sesame Street, and tags graduate and actress
</details>

### Viewing a student: `student -v`
<details markdown="1">
<summary>Views a particular student's details given by the specified index from the persons list.</summary>

Format: `student -v INDEX`

* Views the student detail specified at `INDEX`. `INDEX` should be a positive number.
* `-v` refers to the view command.

Examples:
* `student -v 3` view the student detail at index 3.

![studentview](images/imagesInUG/student-view.png)

</details>

### Editing a student: `student -e`
<details markdown="1">
<summary>
Edits the detail of a specific student.
</summary>

Format: `student -e INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the detail of the student specified by the `INDEX`. `INDEX` should be a
  positive number and refers to the index number of a student in the list of students.
* `-e` refers to the edit command
* It is a requirement that **at least one of the optional fields should be provided**.

Example:

* `student -e 3 p/88888888 e/johnny@mail.com` Edits the phone number and email
  address of the third student in the student list to 88888888 and johnny@mail.com
  respectively.
</details>

### Deleting a student: `student -d`
<details markdown="1">
<summary>
Deletes the specific student given by the specified index from the persons list.
</summary>

Format: `student -d INDEX`

* Deletes the student specified by the `INDEX`.
* `-d` refers to the edit command
* `INDEX` should be a positive number and refers to the index number of a student in the list of students.

Examples:

* `student -d 3` deleted the third student in the persons list
</details>

### Listing all students : `list`
<details markdown="1">
<summary>
Shows a list of all students in the students list.
</summary>

Format: `list`
</details>

### Locating students by name: `student -f`
<details markdown="1">
<summary>
Finds students whose names contain any of the given keywords.
</summary>

Format: `student -f KEYWORD…​`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `student -f John` returns `john` and `John Doe`
* `student -f alex david` returns `Alex Yeoh`, `David Li`<br>
</details>

### Adding a lesson to a student: `student -al`

<details markdown="1">
<summary>
Adds a lesson to the specific student
</summary>

Format: `student -al INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY`

* Adds a lesson to the student specified by the `INDEX`.
* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DAY` takes in the following inputs: `Mon Tue Wed Thu Fri Sat Sun`.
* `START_TIME` and `END_TIME` takes in the time in 24-hour, HH:MM, format. For example `08:00`
  for 8am.
* Overlapping lessons cannot be added. These include any lessons in groups that the student is in.

Example:
* `student -al 1 s/Biology st/08:00 et/09:00 d/Mon` Adds a lesson with the subject name biology
  starting at 8am and ending at 9am on Mondays.

![studentaddlesson](images/imagesInUG/student-addlesson.png)
</details>

### Deleting a lesson from a student: `student -dl`
<details markdown="1">
<summary>
Deletes a lesson from the specific student
</summary>

Format: `student -dl PERSON_INDEX LESSON_INDEX`

* Specifies the student at `PERSON_INDEX`.
* Deletes the specified lesson at `LESSON_INDEX` of the student specified.
* Both `PERSON_INDEX` and `LESSON_INDEX` must be a positive number.

Example:
* `student -dl 1 1` deletes the first lesson from the first student.
</details>

### Adding an exam to a student: `student -ae`
<details markdown="1">
<summary>
Adds an exam to the specific student
</summary>

Format: `student -ae INDEX s/SUBJECT d/DATE_TIME`

* Adds a lesson to the student specified by the `INDEX`.
* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DATE_TIME` takes in a date and time in the following format: `yyyy-MM-dd HH:mm`.

Example:
* `student -ae 1 s/Math d/2021-12-20 14:00` Adds an exam with the subject name Math
  on 20th December 2021, 2pm.
</details>

### Deleting an exam from a student: `student -de`
<details markdown="1">
<summary>
Deletes a lesson from the specific student
</summary>

Format: `student -de PERSON_INDEX EXAM_INDEX`

* Specifies the student at `PERSON_INDEX`.
* Deletes the specified lesson at `EXAM_INDEX` of the student specified.
* Both `PERSON_INDEX` and `EXAM_INDEX` must be a positive number.

* `student -de 1 1` deletes the first exam from the first student.
</details>

### Grouping students
<details markdown="1">
<summary>
Groups students by their names together.
</summary>

Format: `group -a INDEX…​ n/GROUPNAME`

* Groups students into a group specified at `GROUPNAME`
* `-a` refers to the add group command
* You can specify as many index numbers as required.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `group -a 1 3 5 n/Team2` creates a group "Team2" that includes students at index 1, 3 and 5.

![groupadd](images/imagesInUG/group-add.png)

</details>

### Deleting a group
<details markdown="1">
<summary>
Deletes a group of students.
</summary>

Format: `student -d INDEX`

* Deletes a group of students specified at `INDEX`
* `-d` refers to delete group command.
* The index refers to the index number shown in the displayed group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `student -d 5` deletes the group specified at the index 5.
</details>

### Adding a lesson to a group: `group -al`

<details markdown="1">
<summary>
Adds a lesson to the specified group.
</summary>

Format: `group -al INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY`

* Adds a lesson to the group specified by the `INDEX`.
* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DAY` takes in the following inputs: `Mon Tue Wed Thu Fri Sat Sun`.
* `START_TIME` and `END_TIME` takes in the time in 24-hour, HH:MM, format. For example `08:00`
  for 8am.
* Overlapping lessons cannot be added. This will be cross-checked with all lessons for every student in the group.

Example:
* `group -al 1 s/Biology st/08:00 et/09:00 d/Mon` Adds a lesson with the subject name biology
  starting at 8am and ending at 9am on Mondays.
</details>

### Deleting a lesson from a group: `group -dl`
<details markdown="1">
<summary>
Deletes a lesson from the specified group
</summary>

Format: `student -dl GROUP_INDEX LESSON_INDEX`

* Specifies the group at `GROUP_INDEX`.
* Deletes the specified lesson at `LESSON_INDEX` of the group specified.
* Both `GROUP_INDEX` and `LESSON_INDEX` must be a positive number.

Example:
* `group -dl 1 1` deletes the first lesson from the first group.
</details>

### Adding a task
<details markdown="1">
<summary>
Adds a task to the task list.
</summary>

Format: `task -a n/NAME d/DEADLINE`

* Adds a task with the name `NAME` and deadline `DEADLINE`
* The deadline must be in the format `YYYY-MM-DD`
* `-a` refers to the add command

Examples:
* `task -a n/CS2100 Lab 1 d/2021-10-10` adds the task "CS2100 Lab 1"
</details>

### Editing a task 
<details markdown="1">
<summary>
Edits a task in the task list.
</summary>

Format: `task -e INDEX [n/NAME] [d/DEADLINE]`

* Edits a task with the index `INDEX`. The index should be a positive number and refers to the index number of the task in the students’ task list
* `-e` refers to the edit command
* It is a requirement that **at least one of the optional fields should be provided!**
* The current value will be updated to the value input by the user

Examples:
* `task -e 4 d/2021-12-31` edits the deadline to 31 December 2021

![taskedit](images/imagesInUG/task-edit.png)

</details>

### Deleting a task: `task -d`
<details markdown="1">
<summary>
Deletes the specific task from the addressbook.
</summary>

Format: `task -d INDEX`

* Deletes the task specified at `INDEX`. The index should be a positive number.
* `-d` refers to the deletion command.
* The index refers to the index number shown in the displayed task list.

Examples:

* `task -d 1` deletes the task at index 1
</details>

### Listing all tasks 
<details markdown="1">
<summary>
Shows a list of all the tasks in the task list. 
</summary>

Format: `task -l`
</details>

### Finding a task by name
<details markdown="1">
<summary>
Find tasks whose names contain any of the given keywords.
</summary>

Format: `task -f KEYWORD…​`

* The search is case-insensitive. e.g `report` will match `Report`
* The order of the keywords does not matter. e.g. `Report Lab` will match `Lab Report`
* Only the name is searched.
* Only full words will be matched e.g. `Rep` will not match `Report`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Report Submission` will return `Report 1`, `Lab Submission`

Examples:
* `task -f Report` returns `Report 1` and `Report Submission`
* `task -f lab submission` returns `Lab Submission`, `Report Submission`<br>

![taskfind](images/imagesInUG/task-find.png)

</details>

### Assigning tasks to students
<details markdown="1">
<summary>
Assigns a specific task to students’ task list.
</summary>

Format: `task -as STUDENTINDEX TASKINDEX`

* Assigns the task specified at index `TASKINDEX` to the “Student” object specified at index `STUDENTINDEX`.
* `-as` refers to the "assign to student" command.

Examples:
* `task -as 2 4` adds the task at the index 4 to the student at the index 2
</details>

### Assigning tasks to groups
<details markdown="1">
<summary>
Assigns a specific task to groups’ task list.
</summary>

Format: `task -ag GROUPINDEX TASKINDEX`

* Assigns the task specified at index `TASKINDEX` to the “Group” object specified at index `GROUPINDEX`.
* `-ag` refers to the "assign to group" command.

Examples:
* `task -ag 2 4` adds the task at the index 4 to the group at the index 2
</details>

### Unassigning tasks to students
<details markdown="1">
<summary>
Unassigns a specific task from students’ task list.
</summary>

Format: `task -unas STUDENTINDEX TASKINDEX`

* Unassigns the task specified at index `TASKINDEX` from the “Student” object specified at index `STUDENTINDEX`.
* `-unas` refers to the "unassign from student" command.

Examples:
* `task -unas 2 4` removes the task at the index 4 from the student at the index 2
</details>

### Unassigning tasks to groups
<details markdown="1">
<summary>
Unassigns a specific task from groups’ task list.
</summary>

Format: `task -unag GROUPINDEX TASKINDEX`

* Unassigns the task specified at index `TASKINDEX` from the “Group” object specified at index `GROUPINDEX`.
* `-unag` refers to the "unassign from group" command.

Examples:
* `task -unag 2 4` removes the task at the index 4 from the group at the index 2
</details>

### Mark task as done by student
<details markdown="1">
<summary>
Marks that a student has done a task.
</summary>

Format: `task -do STUDENTINDEX TASKINDEX`

* Marks the task specified at index `TASKINDEX` as done by the “Student” object specified at index `STUDENTINDEX`.
* `-do` refers to the "mark as done" command.

Examples:
* `task -do 2 4` marks the task at the index 4 as done by the student at the index 2
</details>

### Mark task as not done by student
<details markdown="1">
<summary>
Marks that a student has not done a task.
</summary>

Format: `task -undo STUDENTINDEX TASKINDEX`

* Marks the task specified at index `TASKINDEX` as not done by the “Student” object specified at index `STUDENTINDEX`.
* `-undo` refers to the "mark as undone" command.

Examples:
* `task -undo 2 4` marks the task at the index 4 as not done by the student at the index 2
</details>

### Clearing all entries : `clear`

Clears all entries in Tutor Master.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Tutor Master data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Tutor Master data are saved as a JSON file `[JAR file location]/data/tutormaster.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Tutor Master will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Tutor Master home folder.


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Adding a student** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
**Edit a student** | `student -e INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `student -e 3 c/88888888 e/johnny@mail.com`
**View a student** | `student -v INDEX​` <br> e.g., `student -v 3`
**Delete a student** | `student -d INDEX​` <br> e.g., `student -d 3`
**Find a student** | `find KEYWORD [MORE_KEYWORDS]​` <br> e.g., `find alex david`
**List all students** | `list`
**Add a lesson to a student** | `student -al INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY` <br> e.g. `student -al 1 s/Biology st/08:00 et/09:00 d/Mon`
**Remove a lesson from a student** | `student -dl PERSON_INDEX LESSON_INDEX` <br> e.g. `student -dl 1 1`
**Add an exam to a student** | `student -ae INDEX s/SUBJECT d/DATE_TIME` <br> e.g. `student -ae 1 s/Math d/2021-12-20 14:00`
**Remove an exam from a student** | `student -de PERSON_INDEX EXAM_INDEX` <br> e.g. `student -de 1 1`
**Group students** | `student -g GROUPNAME INDEX1 INDEX2 INDEX3 ...​` <br> e.g., `student -g Team2 1 3 5`
**Add a task** | `task -a n/NAME​` <br> e.g., `task -a n/CS2100 Lab 1`
**Edit a task** | `task -e INDEX [n/NAME] [d/DEADLINE] [c/COMPLETED]​` <br> e.g., `task -e 4 d/20211231 c/true`
**View a task** | `task -v INDEX​` <br> e.g., `task -v 2`
**Delete a task** | `task -d INDEX​` <br> e.g., `task -d 1`
**Assign a task to a student** | `task -as STUDENTINDEX TASKINDEX​` <br> e.g., `task -as 2 4`
**Assign a task to a group** | `task -ag GROUPINDEX TASKINDEX​` <br> e.g., `task -ag 2 4`
**Unassign a task from a student** | `task -unas STUDENTINDEX TASKINDEX​` <br> e.g., `task -unas 2 4`
**Unassign a task from a group** | `task -unag GROUPINDEX TASKINDEX​` <br> e.g., `task -unag 2 4`
**Mark task as done by student** | `task -do STUDENTINDEX TASKINDEX​` <br> e.g., `task -do 2 4`
**Mark task as not done by student** | `task -undo STUDENTINDEX TASKINDEX​` <br> e.g., `task -undo 2 4`
**Clear all entries** | `clear`
**Exit** | `exit`
