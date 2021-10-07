---
layout: page
title: User Guide
---

SalesNote is a **desktop app for managing contacts and tasks, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). Fast typists can get more out of the application 
than from traditional GUI apps. The application and guide are based on AB3 with new added features.

This project is based on the AB3 project created by the [SE-EDU initiative](https://se-education.org).

* Table of Contents
{:toc}

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

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS m/MEASUREMENT g/GENDER [r/REMARK] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0). The remark is also optional.
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street Blk 92 g/M m/170_100_40`
* `add n/Betsy Crowe e/bcrowe@example.com a/Sesame Street p/1234567 t/important g/F d/160_85_35_81`

### Listing all persons : `list`

Shows a list of all persons in the application.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the application.

Format: `edit INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS m/MEASUREMENT g/GENDER [r/REMARK] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

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

### Deleting a person : `delete`

Deletes the specified person from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the application.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SalesNote data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SalesNote data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SalesNote will discard all data and start with an empty data file at the next run.
</div>

### Listing all tasks: `listtask`

Shows a list of all tasks in the application.

### Adding a task: `addtask`

Adds a task to the application.

Format: `addtask l/LABEL d/DATE`

Examples:
* `addtask l/sew buttons onto blazer d/20th August 2021`

### Deleting a task : `deletetask`

Deletes the specified task from the application.

Format: `deletetask INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listtask` followed by `deletetask 2` deletes the 2nd person in the application.

### Editing a task : `edittask`

Edits an existing task in the application.

Format: `edittask INDEX l/LABEL d/DATE`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edittask 1 l/order cloth d/19th September 2021` Edits the label and date of the 1st task to be `order cloth` and `19th September 2021` respectively.

### Mark a task as done: `markdone`

Marks a specified task from the application as complete.

Format: `deletetask INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listtask` followed by `deletetask 2` deletes the 2nd person in the application.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SalesNote home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS m/MEASUREMENT g/GENDER [r/REMARK] [t/TAG]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street Blk 92 g/M m/170_100_40 t/friend`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS m/MEASUREMENT g/GENDER [r/REMARK] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**ListTask** | `listtask`
**AddTask** | `addtask l/LABEL d/DATE` e.g. `addtask l/sew buttons onto blazer d/20th August 2021`
**DeleteTask** | `deletetask INDEX` e.g. `deletetask 1`
**EditTask** | `edittask INDEX l/LABEL d/DATE` e.g. `edittask 1 l/order cloth d/19th September 2021`
**MarkDone** | `markdone INDEX` e.g. `markdone 2`

