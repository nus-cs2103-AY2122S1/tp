---
layout: page
title: User Guide
---

**TuitiONE** is a _desktop app for managing contacts, optimized for use via a Command Line Interface_ (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, TuitiONE can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## Quick start)

[comment]: <> (1. Ensure you have Java `11` or above installed in your Computer.)

[comment]: <> (1. Download the latest `TuitiONE.jar` from [here]&#40;https://github.com/AY2122S1-CS2103T-F13-4/tp/releases&#41;.)

[comment]: <> (1. Copy the file to the folder you want to use as the _home folder_ for your TuitiONE.)

[comment]: <> (1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>)

[comment]: <> (   ![Ui]&#40;images/Ui.png&#41;)

[comment]: <> (1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>)

[comment]: <> (   Some example commands you can try:)

[comment]: <> (   * **`list`** : Lists all contacts.)

[comment]: <> (   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.)

[comment]: <> (   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.)

[comment]: <> (   * **`clear`** : Deletes all contacts.)

[comment]: <> (   * **`exit`** : Exits the app.)

[comment]: <> (1. Refer to the [Features]&#40;#features&#41; below for details of each command.)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help` [coming soon]

Shows a message explaining how to access the help page.

[comment]: <> (![help message]&#40;images/helpMessage.png&#41;)

Format: `help`

### Adding a student: `add`

Adds a student to the TuitiONE.

Format: `add n/NAME p/PARENT_PHONE_NUMBER e/EMAIL a/ADDRESS g/GRADE [t/TAG]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

* GRADE here can only be in a range of P1-P6 (primary school levels) or S1-S4 (secondary school levels).
* A student can have any number of tags (including 0).
</div>

Examples:
* `add n/John Doe p/98765432 e/jd@gmail.com a/John street, block 123, #01-01 g/P2`
* `add n/Betsy Crowe p/91234567 e/bc@gmail.com a/Bleecker street, block 123, #01-01 g/S5 t/foreign student`

### Adding a lesson: `add-l`

Adds a lesson to the TuitiONE.

Format: `add-l s/SUBJECT g/GRADE d/DAY_OF_WEEK t/TIME_START c/COST`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

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

Shows a list of all students in the TuitiONE.

Format: `list`

### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a student : `delete`

Deletes a student from the TuitiONE.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the student indexed 2 in the TuitiONE.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Deleting a lesson: `delete-l`

Deletes a lesson from the TuitiONE.


Format: `delete-l INDEX`

* Deletes the lesson of the specified `INDEX`.
* The index refers to the index number shown in the displayed lesson list.
* The index **must be a positive integer** 1, 2, 3, …

    
Examples:
* `delete-l 1` deletes the lesson with corresponding index.

### Enrolling a student from lesson: `enroll`

Enroll a specified student from a given TuitiONE lesson.

Format: `enroll INDEX l/LESSON_CODE`

* Enroll the student identified by `INDEX` from the specific `lesson`.
* Enrolling a student is only possible if  the student (1) has the same `grade` as the lesson, (2) is not enrolled to the lesson and (3) has no other lessons with conflicting timing.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …
* LESSON_CODE should have the corresponding format as seen in the program.
    * SUBJECT-GRADE-DAY-TIME
    * The first letter of SUBJECT, GRADE, and DAY is in caps.
    * Time is in 2400 hours format.

Examples:
* `enroll 1 l/Science-P5-Wed-1230` will enroll the student indexed `1` for a `P5` `Science` lesson on `Wed 1230 pm`.

### Unenrolling a student from lesson: `unenroll`

Unenroll a student from a given TuitiONE lesson.

Format: `unenroll INDEX l/LESSON_CODE`

* Unenroll the student identified by `INDEX` from the specific `lesson` using its `lesson code`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …
* LESSON_CODE should have the corresponding format as seen in the program.
    * SUBJECT-GRADE-DAY-TIME
    * The first letter of SUBJECT, GRADE, and DAY is in caps.
    * Time is in 2400 hours format.

Examples:
* `unenroll 1 l/Science-P5-Wed-1230` will unenroll the student indexed `1` from a `P5` `Science` lesson on `Wed 1230 pm`.

### Clearing all entries : `clear`

Clears all student entries from the TuitiONE.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TuitiONE data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TuitiONE data is saved as a JSON file `[JAR file location]/data/TuitiONE.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TuitiONE will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PARENT_PHONE_NUMBER e/EMAIL a/ADDRESS g/GRADE [t/TAG]…` <br> e.g. `add n/Betsy Crowe p/91234567 e/bc@gmail.com a/Bleecker street, block 123, #01-01 g/S5 t/foreign student`
**Add lesson** | `add-l s/SUBJECT g/GRADE d/DAY_OF_WEEK t/TIME_START c/COST` <br> e.g. `add-l s/Science g/P5 d/Wed t/1230 c/12.0`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Delete lesson** | `delete-l INDEX`<br> e.g. `delete-l 1`
**Enroll** | `enroll INDEX l/LESSON_CODE`<br> e.g. `enroll 1 l/Science-P5-Wed-1230`
**Unenroll** | `unenroll INDEX l/LESSON_CODE`<br> e.g. `unenroll 1 l/Science-P5-Wed-1230`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`
**List** | `list`
**Help** | `help`
