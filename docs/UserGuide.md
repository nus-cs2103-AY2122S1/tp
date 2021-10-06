---
layout: page
title: User Guide
---

ContactSH is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, contactSH can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `contactSH.jar` from [here](https://github.com/AY2122S1-CS2103T-W10-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ContactSH.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to ContactSH.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

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

### Give a list of instructions : `help`

Shows the user a list of instructions that can be used to navigate the app.

Format: help [“command-name”]

* If no command-name is entered, the user will see the list of all the keywords for each command

* If the user enters a valid command-name, the user will see the full details of that command, which includes the format and description of the command.

Sample outcome (User typed “help sort”):

```
Sort persons by the alphabetical order of their name
Format: sort [-r]
-r returns a sorted list in reverse order
```

Format: `help`


### Adding a person: `add`

Adds a person to ContactSH.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in ContactSH.

Format: `list`

### Editing a person : `edit`

Edits an existing person in ContactSH.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

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

Deletes the specified person from ContactSH.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in ContactSH.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Add task:

Add a task to the current list of tasks attached to a person.

Format: `addtask INDEX task/TASKNAME`

* Adds a task to the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* The name of the task is specified by the TASKNAME.

Examples:
* `addtask 1 task/call for meeting` Adds the task "call for meeting" to the list of task of the person in 1st person.
### Delete task:

Deletes a task from the specified person.

Format: `deltask INDEX ti/TASK_INDEX`

* Deletes a task attached to the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* The task deleted is specified by the TASK_INDEX. The task_index refers to the index number displayed in the tasklist of said person. The index must be a positive integer 1, 2, 3, …​

Examples:
* `deltask 2 ti/2` Deletes the 2nd task attached to the 2nd person.

### Edit task:

Edit an existing task in ContactSH.

Format: `edittask INDEX ti/TASK_INDEX [task/TASKNAME]`

* Edits a task attached to the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* The task edited  is specified by the TASK_INDEX. The task_index refers to the index number displayed in the tasklist of said person. The index must be a positive integer 1, 2, 3, …​
* Existing values will be updated to the input value.
* At least one of the optional fields must be provided.

Examples:
* `edittask 3 ti/2 task/Group Project Meeting` Changes the description of the 2nd task attached to the 3rd person in the list to `Group Project Meeting`.

### Clearing all entries : `clear`

Clears all entries from ContactSH.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Viewing list of tasks of a person: `viewtask`

Shows the user a list of tasks that has been attached to a specific person.

Format: `viewtask INDEX`

* Displays the list of tasks attached to the person at the specified`INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `list` followed by delete 2 deletes the 2nd person in the address book.
* `find` Betsy followed by `delete 1` deletes the 1st person in the results of the `find`
command.

### Sorting persons by name: `sort`

Sort persons by the alphabetical order of their name.

Format: `sort [-r]`

* The default sort with no options provided displays a list of persons sorted in ascending ASCII alphabetical order of their name.
* If the optional -r flag is provided, a list of persons sorted in reverse order is displayed.

### Saving the data

ContactSH data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ContactSH data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ContactSH will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ContactSH home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Sort** | `sort [-r]`
**Help** | `help`
**Add Task** | `addtask INDEX task/TASKNAME` <br> e.g., `addtask 2 task/celebrate $1 million revenue`
**Delete Task** | `deltask INDEX ti/TASK_INDEX` <br> e.g., `deltask 2 ti/2`
