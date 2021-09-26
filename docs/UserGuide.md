---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Uni-Fy.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.
   * Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Italicised words represent parameters that are meant to be supplied by the user
  * e.g. in add *n/task_name d/date*, the *task_name* keyword represents a task and the *date* keyword represents a date, both to be inputted by the user

* Items inside round brackets are optional
  * e.g. in delete *n/task_name (d/date)*, a user can choose to include the date for the task if there are multiples of it

* In user inputted commands:
  * Items with n/ before them represent the exact name of the task
  * Items with tg/ before them represent the tag of the task
  * Items with d/ before them represent the date of the task
  * Items with t/ before them represent the time of the task
    * Date is represented in YYYY-MM-DD format
    * Time is represented in HH:MM format
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add` (coming soon)

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Showing tasks : `show` (coming soon)

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find` (coming soon)

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

### Deleting tasks : `delete` (coming soon)

Delete a specific task that has been added to your app.

Format:
- <code> delete <i>task_id</i> </code>
- <code> delete <i>n/task_name (d/date)</i> </code>

Interpretation
* If <code><i>task_id</i></code> field is provided:
  * Deletes the task corresponding to the <code><i>task_id</i></code>
* If <code><i>n/task_name</i></code> field is provided:
  * Deletes the task with name **exactly matching** that given in the <code><i>n/task_name</i></code> field
  * If <code><i>d/date</code></i> field is provided, search for the task in the given date to delete
    * Useful in cases where there are multiple copies of the same task, recurring tasks, etc.
  * If <code><i>d/date</code></i> field is not provided, deletes **every occurence** of tasks matching the given name

Examples:
* `delete 3` removes the third item in the task list
* `delete n/quiz` removes every `quiz` task in the app
* `delete n/quiz d/2021-10-10` deletes only the `quiz` task on `2021-10-10`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

**Uni-Fy** data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

**Uni-Fy** data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Uni-Fy will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `TODO`
**Delete** | <code>delete <i>task_id</i></code> <br> <code>delete <i>n/task_name (d/date)</i> </code> <br> e.g. <code>delete n/quiz d/2021-10-10 </code>
**Show** | `show`
**Find** | `TODO`
**Tag** | `TODO`
**Help** | `help`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Clear** | `clear`
