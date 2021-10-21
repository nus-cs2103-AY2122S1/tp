---
layout: page
title: User Guide
---

**TuitiONE** is a _desktop app for managing contacts, optimized for use via a Command Line Interface_ (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, TuitiONE can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your work station.

1. Download the latest `TuitiONE.jar` from [here](https://github.com/AY2122S1-CS2103T-F13-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TuitiONE.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. _Note how the app contains some sample data_.<br>

   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

   Some example commands you can try:

   * **`list`** : Lists all students and lessons.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 g/S3 r/friends r/owesMoney` : Adds a student named `John Doe` to the TuitiONE app.

   * **`delete`**`3` : Deletes the 3rd student shown in the student list.

   * **`clear`** : Deletes all data (students and lessons).

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [r/REMARK]` can be used as `n/John Doe r/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[r/REMARK]…` can be used as ` ` (i.e. 0 times), `r/sick`, `r/absent r/graduated` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PARENT_CONTACT`, `p/PARENT_CONTACT n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

TuitiONE will display the help panel which shows a summary of the command syntax that is usable to the current version of TuitiONE.

[comment]: <> (![help message]&#40;images/helpMessage.png&#41;)

Command Format: `help`

### Adding a student: `add`

Adds a student to the TuitiONE.

Command Format: `add n/NAME p/PARENT_CONTACT e/EMAIL a/ADDRESS g/GRADE [r/REMARK]…`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**<br>

* GRADE here can only be in a range of P1-P6 (primary school levels) or S1-S4 (secondary school levels).

* A student can have any number of remarks (including 0).

</div>

Examples:
* `add n/John Doe p/98765432 e/jd@gmail.com a/John street, block 123, #01-01 g/P2`
* `add n/Betsy Crowe p/91234567 e/bc@gmail.com a/Bleecker street, block 123, #01-01 g/S5 r/foreign student`

### Adding a lesson: `add-l`

Adds a lesson to the TuitiONE.

Command Format: `add-l s/SUBJECT g/GRADE d/DAY_OF_WEEK t/START_TIME c/COST`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**<br>

* Adds the lesson with the specified prefixes.

* GRADE here follows the similar requirements when adding a student.

* SUBJECT is limited to 20 characters, and its first letter will be capitalized.

* DAY_OF_WEEK can only be these form (with the first letter capitalized): Mon, Tue, Wed, Thu, Fri, Sat, Sun.

* START_TIME is in 2400 hours format and can only be between 0900 and 1900 (as lessons can only be conducted between 9am to 9pm).

* Lessons are fixed at two hour periods.

* The cost must be a non-negative number 0.0, 2.0, 3.3, …

</div>

Examples:
* `add-l s/Science g/P5 d/Wed t/1230 c/12.0`
* `add-l s/Mathematics g/S4 d/Fri t/1500 c/10.3`

### Listing all students: `list`

Shows a list of all students and lessons in the TuitiONE.

Command Format: `list`

### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Command Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only keywords based on name will be searched.
* Partially matched words will be supported e.g. `Han` will match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/UserGuideImage/findAlexDavidResult.png)

### Deleting a student : `delete`

Deletes a student from the TuitiONE.

Command Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the student indexed 2 in the TuitiONE.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Deleting a lesson: `delete-l`

Deletes a lesson from the TuitiONE.

Command Format: `delete-l INDEX`

* Deletes the lesson of the specified `INDEX`.
* The index refers to the index number shown in the displayed lesson list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete-l 1` deletes the lesson with corresponding index.

### Enrolling a student from lesson: `enroll`

Enroll a specified student from a given TuitiONE lesson.

Command Format: `enroll STUDENT_INDEX l/LESSON_INDEX`

* Enroll the student identified by `STUDENT_INDEX` in the displayed student list to the specific lesson identified by `LESSON_INDEX` in the displayed lesson list.
* Enrolling a student is only possible if the student:
  1. has the same `grade` as the lesson,
  2. is not enrolled to the lesson and,
  3. has no other lessons with conflicting timing.
* `STUDENT_INDEX` refers to the index number shown in the displayed student list.
* `LESSON_INDEX` refers to the index number shown in the displayed lesson list.
* Both indexes **must be a positive integer** 1, 2, 3, …

Examples:

* `enroll 1 l/1` will enroll the student indexed at `1` in the displayed student list to the lesson indexed at `1` in the displayed lesson list.

### Unenrolling a student from lesson: `unenroll`

Unenroll a student from a given TuitiONE lesson.

Command Format: `unenroll STUDENT_INDEX l/LESSON_INDEX`

* Unenroll the student identified by `STUDENT_INDEX` in the displayed student list from the specific lesson identified by `LESSON_INDEX` in the displayed lesson list.
* `STUDENT_INDEX` refers to the index number shown in the displayed student list.
* `LESSON_INDEX` refers to the index number shown in the displayed lesson list.
* Both indexes **must be a positive integer** 1, 2, 3, …

Examples:
* `unenroll 1 l/1` will unenroll the student indexed `1` in the displayed student list from the lesson indexed at `1` in the displayed lesson list.

### Filtering of list: `filter`

Filter the respective list to display entries that correspond to the conditions as specified.

Command Format: `filter [g/GRADE] [s/Subject]`

* When filtering, filtering conditions `GRADE` and `SUBJECT` can be coexistent. The order of the 2 conditions can be interchanged.
* When filtering, there must be at least 1 filtering condition.
* Filter and display the corresponding the respective list by:
    * `GRADE`: If only filtering by `GRADE`, both of the student list and lesson list will be filtered to display the respective entries that correspond to the `GRADE` as specified.
    * `SUBJECT`: If only filtering by `SUBJECT`, only the lesson list will be filtered to display the respective lessons that correspond to the `SUBJECT` as specified.
    * Both `GRADE` and `SUBJECT`: If filtering by both conditions, both of the student list and lesson list will be filtered to display the respective entries that correspond to the `GRADE` and `SUBJECT` as specified.


Examples:
* `filter g/P2` will filter both of the student list and lesson list by grade of `P2` and display the corresponding entries in the respective list.
* `filter s/Science` will filter the lesson list by subject of `Science` and display the corresponding entries in the respective list.

### Viewing of lesson roster: `roster`

Displays the student roster of a specified lesson in the student panel.

Command Format: `roster INDEX`

* Displays the student roster of the lesson of the specified `INDEX`.
* The index refers to the index number shown in the displayed lesson list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `roster 1` will display the students currently enrolled in the lesson indexed at `1` in the student panel.

### Clearing all entries : `clear`

Clears all student and lesson entries from the TuitiONE.

Command Format: `clear`

### Exiting the program : `exit`

Exits the program.

Command Format: `exit`

### Saving the data

TuitiONE data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TuitiONE data is saved as a `.json` file `[JAR file location]/data/TuitiONE.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**

If the changes you made to the data file render its format invalid, TuitiONE will discard all data and start with an empty data file at the next run.

</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PARENT_PHONE_NUMBER e/EMAIL a/ADDRESS g/GRADE [r/REMARK]…` <br> e.g. `add n/Betsy Crowe p/91234567 e/bc@gmail.com a/Bleecker street, block 123, #01-01 g/S5 r/foreign student`
**Add lesson** | `add-l s/SUBJECT g/GRADE d/DAY_OF_WEEK t/TIME_START c/COST` <br> e.g. `add-l s/Science g/P5 d/Wed t/1230 c/12.0`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Delete lesson** | `delete-l INDEX`<br> e.g. `delete-l 1`
**Enroll** | `enroll STUDENT_INDEX l/LESSON_INDEX`<br> e.g. `enroll 1 l/1`
**Unenroll** | `unenroll STUDENT_INDEX l/LESSON_INDEX`<br> e.g. `unenroll 1 l/1`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`
**Filter** | `filter [g/GRADE] s[SUBJECT]`<br> e.g. `filter g/P2`
**View roster** | `roster INDEX` <br> e.g. `roster 1`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
