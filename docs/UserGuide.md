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

## Quick Start

1. Ensure you have Java `11` or above installed on your computer.
You may install Java `11` from [here](https://www.oracle.com/java/technologies/downloads/#java11).
2. Download the latest `SalesNote.jar` from [here](https://github.com/AY2122S1-CS2103T-W08-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your SalesNote.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
The app provides some sample data to help you get started.
![Ui](images/UiFirstLaunch.png)
5. Type the command in the command box and press Enter to execute it.
e.g. typing `**listtask**` and pressing Enter will list all tasks.
Some example commands you can try:
   * `**list**`: List all clients.
   * `**add** n/John Doe g/Male p/98765432 e/johnd@example.com m/170_80_100 a/311, Clementi Ave 2, #02-25 r/loves blue t/friends t/owesMoney`: Adds a client named `John Doe`.
   * `**delete** 2`: Deletes the 2nd client shown in the current client list.
   * `**clear**`: Deletes all clients.
   * `**exit**`: Exits the app.
6. Refer to the [Features](#features) below to learn more about the commands.

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

## Person Commands

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

<<<<<<< HEAD
### Clearing all entries : `clear`

Clears all entries (Clients, Tasks, SalesOrder) from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SalesNote data is saved in the hard disk (As a JSON file) automatically after any command that changes the data. There is no need to save manually.
=======
## Task Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the task commands:**<br>

* The task tags should either be 'General' or have prefix `SO` followed by at least 1 digit.
* The number after the 'SO' prefix should correspond to the id of an existing order.

</div>

### Listing all tasks : `listtask`

Shows a list of all tasks in the application.

Format: `listtask`

### Adding a task : `addtask`

Adds a task to the application.

Format: `addtask l/LABEL d/DATE [t/TASKTAG]`

<div markdown="span" class="alert alert-primary">:information_source: **Note:**
The new task will automatically be assigned the 'General' tag if the task tag parameter is not supplied.
</div>

Examples:
* `addtask l/sew buttons onto blazer d/20th August 2021 t/SO1` adds the task to the application if there is an order with the id of SO1.

### Deleting a task : `deletetask`

Deletes the specified task from the application.

Format: `deletetask INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list on the **Tasks**'s tab.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listtask` followed by `deletetask 2` deletes the 2nd task in the application.
* `findtask sew` followed by `deletetask 1` deletes the 1st task in the results of the `findtask` command.

### Finding a task: `findtask`

Finds tasks whose label, date or task tag contain any of the given keywords.

Format: `findtask KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `orders` will match `Orders`
* The order of the keywords does not matter. e.g. `Cloth Orders` will match `Orders Cloth`
* Only full words will be matched. e.g. `Order` will not match `Orders`
* Tasks matching at least one keyword will be returned (i.e. OR search). e.g. `Order clothes` will return `Order scissors`, `Sew clothes`.

Examples:
* `findtask buttons` returns `order buttons` and `deliver red buttons`
* `findtask SO1` returns order with id of `SO1`

![result for 'findtask SO1'](images/findtaskSO1.png)

### Editing a task : `edittask`

Edits an existing task in the application.

Format: `edittask INDEX [l/LABEL] [d/DATE] [t/TASKTAG]`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list on the **Tasks**'s tab. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edittask 1 l/order cloth d/19th September 2021` edits the label and date of the 1st task to be `order cloth` and `19th September 2021` respectively.
* `edittask 2 t/General` edits the tag of the 2nd task to be `General`

### Mark a task as done : `marktask`

Marks a specified task from the application as done.

Format: `marktask INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list on the **Tasks**'s tab.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listtask` followed by `marktask 2` marks the 2nd task in the application as done.

### Clearing all entries : `clear`

Clears all entries (Clients, Tasks, SalesOrder) from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SalesNote data is saved in the hard disk (As a JSON file) automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SalesNote saves each data in a different json file.
- AddressBook (Clients information) : as a JSON file `[JAR file location]/data/addressbook.json`.
- TaskBook (Tasks information)  : as a JSON file `[JAR file location]/data/taskBook.json`.
- OrderBook (Sales order information) : as a JSON file `[JAR file location]/data/orderBook.json`.

Advanced users are welcome to update data directly by editing that data file.

_Example usage_

1. Open the JSON file in any of your favorite text editor.
2. Edit the entities in the JSON file as you wish.
3. Re-open SaleNote and changes should be reflected.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SalesNote will discard all data related with the incorrect formatted JSON file and start with an empty data file at the next run.
</div>
ing that data file.

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

