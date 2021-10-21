---
layout: page
title: User Guide
---

tApp is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI). If you are a TA that is looking for an app that integrates different tools into a centralised platform and tracks your TA tasks, tApp is the app for you.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tApp.jar` from [here](https://github.com/AY2122S1-CS2103-W14-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your tApp.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`students`** : Lists all students.

   * **`add`**`Tom Lim` : Adds a student named `Tom Lim` to the student list.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add NAME`, `NAME` is a parameter which can be used as `add John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME s/STUDENT_NUMBER`, `s/STUDENT_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Lists all students: `students`

Shows a list of all students inputted into tApp together with their attendance and participation scores for tutorials.

Format: `students`

Examples:
* `students` returns a list of all students with their information and attendance scores

### Add a student : `addStudent`

Adds a student to the student list. 

Format: `addStudent n/NAME e/EMAIL s/STUDENTNUMBER [u/GITHUB USERNAME] [r/REPO NAME] [t/TAG]...`

* Github link will only be shown if both `GITHUB USERNAME` and `REPO NAME` are specified.
* `STUDENTNUMBER` must consist of a character, followed by 7 digits, followed by another character. 
  Case of character does not matter.
* If only either `GITHUB USERNAME` or `REPO NAME` are specified, the data will still be saved but not shown.
* If the same person is edited and the remaining empty data field is entered, a proper Github link will be shown.

Examples:
* `addStudent n/John Doe e/johnd@example.com s/A0123456B u/user r/ip t/friends t/owesMoney` adds John Doe to the list 
  and displays a confirmation output with a proper Github link
* `addStudent n/John Doe e/johnd@example.com s/A0123456B u/user` adds John Doe to the list and displays a confirmation
  output without a proper Github link, but storing the `GITHUB USERNAME` in memory nonetheless.


### Lists all groups: `groups`

Shows a list of all groups inputted into tApp together with its group members and group Github link if any are added.

Format: `groups`

Examples:
* `groups` returns a list of all groups with their information.

### Add a group: `addGroup`

Adds a group to the group list. The group will only be initialized with a `GROUP NAME` and `TAG` if any.

Format: `addGroup g/GROUP NAME [t/TAG]...`

* `GROUP NAME` must be contain a character, followed by 2 digits, a dash (-), followed by another digit. 
  The case of the character does not matter.

Examples:
* `addGroup g/W14-4 t/tApp` adds group W14-4 to the list and displays a confirmation output with no Github link and
  group members

### Delete a group: `deleteGroup`

Deletes the specified group from the group list.

Format: `deleteGroup INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed group list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `deleteGroup 1` deletes the group with index 1 in group list and displays a confirmation output with the group members and Github
link of the group deleted

### Add a student to a group: `addSG`

Adds a student in student list to an existing group in the group list. 

Format: `addSG INDEX g/GROUP`

* Adds the student specified at `INDEX` to the group with name specified by `GROUP`.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​
* `GROUP` must be an existing group in the displayed group list.

Examples:
* `addSG 1 g/W14-4` adds the student with index 1 in the student list to group W14-4 and displays a confirmation output
  that the student has been added to the group


### Add a Github link to a group: `addGG`

Adds a Github link to a specified group.

Format: `addGG INDEX y/YEAR r/REPO NAME`

* Add a Github link to the group specified at `INDEX`.
* The index refers to the index number shown in the displayed group list.
* The index must be a positive integer 1, 2, 3, …​
* The formatted Github link is of the form https://github.com/ `YEAR`-CS2103-`GROUP NAME`/`REPO NAME`

Examples:
* `addGG 1 y/AY20212022 r/tp` adds a Github link in the above format to the group with index 1 in the group list and
displays a confirmation output the link has been added to the group.


### Listing the task list : `tasks`

Displays all the tasks currently in the list.

Format: `tasks`

### Adding a todo task: `todo n/TASK_NAME`

Adds a TODO task with the given name.

Format: `todo n/TASK_NAME`

* Adds a todo task with the specified `TASK_NAME`

Examples:
* `todo n/study` creates the todo task "study".
* `todo n/play` creates the todo task "play".

### Adding an event task: `event n/TASK_NAME on/DATE`

Adds an event task with the given name and a specified taskDate.

Format: `event n/TASK_NAME on/DATE`

* Adds an event task with the specified `TASK_NAME`
* The event task has the taskDate `DATE`
* The taskDate must be in the format `YYYY-MM-dd`

Examples:
* `event n/party on/2021-09-23` creates the event task "party", which is to be held on the given date.
* `event n/exam on/2021-10-04` creates the event task "exam", which is to be held on the given date.

### Adding an deadline task: `deadline n/TASK_NAME by/DATE`

Adds an deadline task with the given name and a specified taskDate.

Format: `event n/TASK_NAME by/DATE`

* Adds an deadline task with the specified `TASK_NAME`
* The deadline task has the taskDate `DATE`
* The taskDate must be in the format `YYYY-MM-dd`

Examples:
* `deadline n/tutorial participation on/2021-09-23` creates the deadline task "tutorial participation", which is to be completed by the given date.
* `deadline n/assignment submission on/2021-10-04` creates the deadline task "assignment submission", which is to be completed by the given date.


### Marking a student as present: `marka`

Marks the specified student in the student list as present or absent.

Format: `marka INDEX /wWEEK NUMBER`

* Marks the person as present in the specified `INDEX`.
* The index must refer to the index number shown in the displayed students list.
* The index and week number must be a positive number: 1, 2, 3…
* If the student at the specified `INDEX` is marked as present, the command toggles the attendance to absent.

Examples:

* `students` followed by `marka 1 /w1` marks the 1st person in the students list as present in week 1.
* Another `marka 1 /w1` instance will mark the 1st person in the student list as absent in week 1.

Sample Usage:

`> marka 1 /w1`

    > Kho Tze Jit is marked as present for week 1!

`> marka 1 /w1`
    
    > Kho Tze Jit is marked as absent for week 1!

### Marking a student's participation: `markp`

Marks the specified student in the student list as participated or not participated.

Format: `markp INDEX /wWEEK NUMBER`

* Marks the person as present in the specified `INDEX`.
* The index must refer to the index number shown in the displayed students list.
* The index and week number must be a positive number: 1, 2, 3…
* If the student at the specified `INDEX` is marked as present, the command toggles the attendance to absent.

Examples:

* `students` followed by `markp 1 /w1` marks the 1st person in the students list as participated in week 1.
* Another `mark 1 /w1` instance will mark the 1st person in the student list as not participated in week 1.

Sample Usage:

`> markp 1 /w1`

    > Kho Tze Jit is marked as participated for week 1!

`> markp 1 /w1`

    > Kho Tze Jit is marked as not participated for week 1!

### Deleting a student contact: `delete INDEX`

Deletes the specified student from the student list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index must refer to the index number shown in the displayed students list.
* The index and week number must be a positive number: 1, 2, 3…

Examples:

* `students` followed by `delete 1` removes the 1st person in the students list.

Sample Usage:

`> delete 1`

    > Kho Tze Jit is removed from the student list!

### Locating student by name: `findStudent`

Finds persons whose names contain any of the given keywords.

Format: `findStudent KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Partial names will be matched e.g. `Han` will match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find Joh` returns `john` and `John Doe`
* `find alex davi` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a task: `deleteTask INDEX`

Deletes the specified task from the task list.

Format: `deleteTask INDEX`

* Deletes the task at the specified `INDEX`.
* The index must refer to the index number shown in the displayed task list.
* The index and week number must be a positive number: 1, 2, 3…

Examples:

* `deleteTask 1` removes the 1st task in the task list.

Sample Usage:

`> deleteTask 1`

    > Studying is removed from the student list!

### Marking a task as done: : `doneTask`

Mark the specified task from the list as done.

Format: `doneTask INDEX`

* Mark the task as done at the specified `INDEX`.
* The index refers to the index number shown in the task list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `doneTask 2` marks the 2nd task in the task list as completed.

### Clearing all entries from student list : `clearStudents`

Clears all entries from the student list.

Format: `clearStudents`

### Clearing all entries from task list : `clearTasks`

Clears all entries from the task list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

tApp data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[comment]: <> (TODO)
### Viewing help : `help` `[coming in v1.3]`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

[comment]: <> (TODO)
### Editing a person : `edit` `[coming in v1.3]`

Edits an existing person in the students list.

Format: `edit INDEX [n/NAME] [s/STUDENT_NUMBER] [e/EMAIL] [g/GITHUB_LINK] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 s/A0221111L e/johndoe@example.com` Edits the student number and email address of the 1st person to be `A0221111L` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Editing the data file `[coming in v1.3]`

tApp data are saved as a JSON file `[JAR file location]/data/tApp.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, tApp will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

[comment]: <> (TODO)
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous tApp home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List Students** | `students`
**List Tasks** | `tasks`
**List Groups** | `groups`
**Add Student** | `addStudent n/NAME s/STUDENT_NUMBER e/EMAIL g/GITHUB_LINK [t/TAG]…​` <br> e.g., `addStudent n/James Ho s/A0221111L e/jamesho@example.com g/https://github.com/james t/W14-4`
**Edit Student** | `editStudent INDEX n/NAME s/STUDENT_NUMBER e/EMAIL g/GITHUB_LINK [t/TAG]…​`<br> e.g., `editStudent 1 n/James Ho s/A0221111L e/jamesho@example.com g/https://github.com/james t/W14-4`
**Delete Student** | `deleteStudent INDEX`<br> e.g., `deleteStudent 3`
**Mark Student Attendance** | `marka INDEX w/WEEK` <br> e.g., `marka 1 w/1`
**Mark Student Participation** | `markp INDEX w/WEEK` <br> e.g., `marka 1 w/1`
**Find Student** | `findStudent KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James`
**Clear Students** | `clearStudents`
**Add (Student) Group** | `addGroup g/NAME` <br> e.g., `addGroup g/W14-4 t/tApp`
**Delete (Student) Group** | `deleteGroup INDEX`<br> e.g., `deleteGroup 1`
**Add Student to Group** | `addSG INDEX g/GROUP`<br> e.g., `addSG 1 g/W14-4`
**Add Github Link to Group** | `addGG INDEX y/YEAR r/REPO NAME`<br> e.g., `addGG 1 y/AY20212022 r/tp`
**Add Todo Task** | `todo n/TASK_NAME [t/TAG]…​` <br> e.g., `todo n/study t/W14-4`
**Add Event Task** | `event n/TASK_NAME by/DATE [t/TAG]…​` <br> e.g., `event n/study on/2021-10-31 t/W14-4`
**Add Deadline Task** | `deadline n/TASK_NAME by/DEADLINE [t/TAG]…​` <br> e.g., `deadline n/study by/2021-10-31 t/W14-4`
**Mark Done Task** | `doneTask INDEX` <br> e.g., `doneTask 1`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Delete Task** | `deleteTask INDEX`<br> e.g., `deleteTask 3`
**Edit Task** | `editTask INDEX [n/TASK_NAME] [by/DATE] [t/TAG]…​`<br> e.g.,`editTask 2 n/study by/2012-10-31 t/W14-4`
**Clear Tasks** | `clearTasks`
**Clear Address Book** | `clearAll`
**Help** | `help`
