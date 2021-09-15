---
layout: page
title: User Guide
---

Ailurus is a **desktop app** that helps organising committees account for details of their members. It
provides users with convenient viewing and editing access to all information, thus providing much convenience in their work.

It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png) ![tp_sketch](images/tp_sketch.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`padd`**`/n John Doe /p 98765432 /e johnd@example.com /a John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `padd /n NAME`, `NAME` is a parameter which can be used as `padd /n John Doe`.

* Items in square brackets are optional.<br>
  e.g `/n NAME [/t TAG]` can be used as `/n John Doe /t friend` or as `/n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[/t TAG]…​` can be used as ` ` (i.e. 0 times), `/t friend`, `/t friend /t family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `/n NAME /p PHONE_NUMBER`, `/p PHONE_NUMBER /n NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `/p 12341234 /p 56785678`, only `/p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Add

### Adding a person: `padd`

Adds a person to the Ailurus.

Format: `padd /n NAME /p PHONE_NUMBER /e EMAIL [/a ADDRESS] [/t TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0). A person MUST have a name,
phone number and email address, but mailing address and tags are optional.
</div>

Examples:
* `padd /n John Doe /p 98765432 /e johnd@example.com`
* `padd /n Betsy Crowe /t friend /e betsycrowe@example.com /a Newgate Prison /p 1234567 /t criminal`

### Adding a task: `tadd`

Adds a task to the Ailurus.

Format: `tadd /n TASKNAME /p PERSON_ID`

<div markdown="span" class="alert alert-primary">:bulb: Note:
A task must be assigned to a person.
</div>

Examples:
* `tadd /n Collect payment from participants /p 3`

### Adding an event: `eadd`

Adds an event to the Ailurus.

Format: `eadd /n EVENTNAME [/p PERSON_ID]…​`

<div markdown="span" class="alert alert-primary">:bulb: Note:
You can add multiple people to an event e.g. /p 2 /p 3 /p 4...
</div>

Examples:
* `eadd /n Computing Freshmen Orientation Camp 2021 /p 4 /p 5 /p 6`


### Listing all persons :

Shows a list of all persons (of an event optionally).

Format: `plist [/e EVENT_ID]`
* List all persons (specified by `EVENT_ID`).
* The `EVENT_ID` refers to the index number shown in the displayed event list.
* The `EVENT_ID` **must be a positive integer** 1, 2, 3, …​

Example:
* `plist` lists all persons.
* `plist /e 3` lists all participants of the event with index number 3.

### Listing all tasks of a person : 

Shows a list of tasks of a person with the specified id.

Format: `tlist /p PERSON_ID`
* The `PERSON_ID` refers to the index number shown in the displayed participant list.
* The `PERSON_ID` **must be a positive integer** 1, 2, 3, …​

Example:
* `tlist /p 2` lists all tasks of the person with index number 2

### Listing all events :

Shows a list of all events.

Format: `elist`

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

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.


### Mark a task as done
Marks the specified task of the specified person as done.

Format: `tdone /p PERSON_ID /t TASK_ID`

* Marks the task specified by `TASK_ID` of the person specified by `PERSON_ID`.
* The `PERSON_ID` refers to the index number shown in the displayed participant list.
* The `TASK_ID` refers to the index number shown in the displayed task list of the specified participant.
* The `PERSON_ID` and `TASK_ID` **must be a positive integer** 1, 2, 3, …​

Example:
* `tdone /p 2 /t 3` deletes the 3rd task of the 2nd participants.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
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
**Add** | `padd /n NAME /p PHONE_NUMBER /e EMAIL /a ADDRESS [/t TAG]…​` <br> e.g., `padd /n James Ho /p 22224444 /e jamesho@example.com /a 123, Clementi Rd, 1234665 /t friend /t colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**tdone** | `tdone /p PERSON_ID /t TASK_ID`<br> e.g. `tdone /p 2 /t 3`
**Help** | `help``
