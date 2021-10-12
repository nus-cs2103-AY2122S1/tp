---
layout: page
title: User Guide
---

TutorMaster is a desktop app for freelance tutors to manage their students’ grades, assignment submission and tuition fee payment. It is optimised for use via a Command Line Interface (CLI) while still benefiting from a Graphical User Interface (GUI).

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

   * **`student -a n/John Doe`**: Adds a contact named `John Doe` to the students list.

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

</div>

### Viewing help : `help`

Views a info page for a list of commands and examples

Format: `help`


### Adding a student: `student -a`

Adds a student to the students list.

Format: `student -a -n NAME [-c CONTACT] [-em EMAIL] [-s DAY/STARTTIME/ENDTIME]​`

* Adds a student with the specified `NAME`
* `-a` refers to the add command
* Commands in `[ ]` are optional
* `DAY` takes in the following inputs: `MON TUE WED THU FRI SAT SUN` while `STARTTIME` and `ENDTIME` takes in the time in 24 hour format, for example 0800 for 8am.


Examples:
* `student -a -n John -c 12345678 -em john@mail.com -s TUE/1600/1800`<br>
  adds a student with the name John, contact number 12345678, email john@mail.com and a lesson every tuesday from 4pm to 6pm
* `student -a -n Barbara -c 12344321`<br>
  adds a student with the name Barbara and contact number 12344321

### Viewing a student: `student -v` `[coming in v1.2]`

Views a particular student's details given by the specified index from the 
persons list.

Format: `student -v INDEX`

* Views the student detail specified at `INDEX`. `INDEX` should be a positive number.
* `-v` refers to the view command.

Examples:
* `student -v 3` view the student detail at index 3.

### Editing a student: `student -e` `[coming in v1.2]`

Edits the detail of a specific student.

Format: `student -e INDEX [n/NAME] [c/CONTACT] [e/EMAIL]`

* Edits the detail of the student specified by the `INDEX`. `INDEX` should be a 
  positive number and refers to the index number of a student in the list of students.
* `-e` refers to the edit command
* It is a requirement that **at least one of the optional fields should be provided**.

Example:

* `student -e 3 c/88888888 e/johnny@mail.com` Edits the contact number and email
  address of the third student in the student list to 88888888 and johnny@mail.com
  respectively.
  
### Adding a lesson to a student: `student -al` `[coming in v1.2]`

Adds a lesson to the specific student

Format: `student -al INDEX s/SUBJECT st/START_TIME et/END_TIME d/DAY`

* `SUBJECT` takes in any input with at least one alphanumeric character.
* `DAY` takes in the following inputs: `Mon Tue Wed Thu Fri Sat Sun`.
* `START_TIME` and `END_TIME` takes in the time in 24-hour, HH:MM, format. For example `08:00`
  for 8am.
* Overlapping tasks cannot be added.

* `student -al 1 s/Biology st/08:00 et/09:00 d/Mon` Adds a lesson with the subject name biology
  starting at 8am and ending at 9am on Mondays.

### Deleting a student: `student -d` `[coming in v1.2]`

Deletes the specific student given by the specified index from the persons list.

Format: `student -d INDEX`

* Deletes the student specified by the `INDEX`. 
* `-d` refers to the edit command
* `INDEX` should be a positive number and refers to the index number of a student in the list of students.

Examples:

* `student -d 3` deleted the third student in the persons list

### Listing all students : `list`

Shows a list of all students in the students list.

Format: `list`

### Locating students by name: `student -f`

Finds students whose names contain any of the given keywords.

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


### Grouping students  `[coming in v1.2]`

Groups students by their names together. This grouping helps to distinguish between various tuition groups.

Format: `student -g GROUPNAME INDEX…​`

* Groups students into a group specified at `GROUPNAME`
* `-g` refers to the group command
* You can specify as many index numbers as required.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `student -g Team2 1 3 5` creates a group "Team2" that includes students at index 1, 3 and 5.

### Adding a task `[coming in v1.2]`

Adds a task to the task list.

Format: `task -a n/NAME d/DEADLINE`

* Adds a task with the name `NAME` and deadline `DEADLINE`
* The deadline must be in the format `YYYY-MM-DD`
* `-a` refers to the add command

Examples:
* `task -a n/CS2100 Lab 1` adds the task "CS2100 Lab 1"

### Viewing a task `[coming in v1.2]`

Views a task in the task list.

Format: `task -v INDEX`

* Views a task with the index `INDEX`
* `-v` refers to the view command

Examples:
* `task -v 2` shows the attributes of the 2nd task on the student’s task list

### Editing a task `[coming in v1.2]`

Edits a task in the task list.

Format: `task -e INDEX [n/NAME] [d/DEADLINE] [c/COMPLETED]`

* Edits a task with the index `INDEX`. The index should be a positive number and refers to the index number of the task in the students’ task list
* `-e` refers to the edit command
* It is a requirement that **at least one of the optional fields should be provided**
* The current value will be updated to the value input by the user

Examples:
* `task -e 4 d/20211231 c/t` edits the deadline and status of completion of the 4th task on the students’ task list to 20211231 and true respectively

### Deleting a task `[coming in v1.2]`

Deletes the specific task from the students' task list.

Format: `task -d INDEX`

* Deletes the task specified at `INDEX`. The index should be a positive number.
* `-d` refers to the deletion command.
* The index refers to the index number shown in the displayed task list.

Examples:
* `task -d 1` deletes the task at index 1

### Assigning tasks to students `[coming in v1.2]`

Assigns a specific task to students’ task list.

Format: `task -ass STUDENTINDEX TASKINDEX` 

* Assigns the task specified at index `TASKINDEX` to the “Student” object specified at index `STUDENTINDEX`.
* `-ass` refers to the assignment command.

Examples:
* `task -ass 2 4` adds the task at the index 4 to the student at the index 2

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
**Edit a student** | `student -e INDEX [n/NAME] [c/CONTACT] [e/EMAIL] [s/DAY/START_TIME/END_TIME]​` <br> e.g., `student -e 3 c/88888888 e/johnny@mail.com` 
**View a student** | `student -v INDEX​` <br> e.g., `student -v 3`
**Delete a student** | `student -d INDEX​` <br> e.g., `student -d 3`
**Find a student** | `find KEYWORD [MORE_KEYWORDS]​` <br> e.g., `find alex david`
**List all students** | `list`
**Group students** | `student -g GROUPNAME INDEX1 INDEX2 INDEX3 ...​` <br> e.g., `student -g Team2 1 3 5`
**Add a task** | `task -a n/NAME​` <br> e.g., `task -a n/CS2100 Lab 1`
**Edit a task** | `task -e INDEX [n/NAME] [d/DEADLINE] [c/COMPLETED]​` <br> e.g., `task -e 4 d/20211231 c/true`
**View a task** | `task -v INDEX​` <br> e.g., `task -v 2`
**Delete a task** | `task -d INDEX​` <br> e.g., `task -d 1`
**Assign a task to a student** | `task -ass STUDENTINDEX TASKINDEX​` <br> e.g., `task -ass 2 4`
**Clear all entries** | `clear`
**Exit** | `exit`
