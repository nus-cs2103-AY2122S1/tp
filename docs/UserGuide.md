---
layout: page
title: User Guide
---

Ailurus is a **desktop app** that helps to organise committees account for details of their members. It
provides users with convenient viewing and editing access to all information, thus providing much convenience in their work.

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Ailurus can get your contact management tasks done faster than traditional GUI apps.

## Table of Contents
- [Table of Contents](#table-of-contents)
- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help : `help`](#viewing-help--help)
  - [Add](#add)
    - [Adding a member: `padd`](#adding-a-member-padd)
    - [Adding a task: `tadd`](#adding-a-task-tadd)
    - [Adding an event: `eadd`](#adding-an-event-eadd)
  - [List](#list)
    - [Listing all members : `plist`](#listing-all-members--plist)
    - [Listing all tasks of a member : `tlist`](#listing-all-tasks-of-a-member--tlist)
    - [Listing all events : `elist`](#listing-all-events--elist)
  - [Editing a member : `edit`](#editing-a-member--edit)
  - [Locating members by name: `find`](#locating-members-by-name-find)
  - [Delete](#delete)
    - [Deleting a member : `pdel`](#deleting-a-member--pdel)
    - [Deleting a task belonging to a member : `tdel`](#deleting-a-task-belonging-to-a-member--tdel)
    - [Deleting an event : `edel`](#deleting-an-event--edel)
  - [Mark a task as done : `tdone`](#mark-a-task-as-done--tdone)
  - [Clearing all entries : `clear`](#clearing-all-entries--clear)
  - [Exiting the program : `exit`](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
  - [Opening up the Menu page `[coming in v2.0]`](#opening-up-the-menu-page-coming-in-v20)
- [FAQ](#faq)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ailurus.jar` from [here](https://https://github.com/AY2122S1-CS2103T-T15-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Ailurus.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png) 

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`plist`** : Lists all contacts.

   * **`padd`**`/n John Doe /p 98765432 /e johnd@example.com /a John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`pdel`**`3` : Deletes the 3rd contact shown in the current list of people.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `padd /n NAME`, `NAME` is a parameter which can be used as `padd /n John Doe`.

* Items in square brackets are optional.<br>
  e.g. `/n NAME [/t TAG]` can be used as `/n John Doe /t friend` or as `/n John Doe`.

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

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Add

#### Adding a participant: `padd`

Adds a participant to Ailurus.

Format: `padd /n NAME /p PHONE_NUMBER /e EMAIL [/a ADDRESS] [/t TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A participant can have any number of tags (including 0). A member MUST have a name,
phone number and email address, but mailing address and tags are optional.
</div>

Examples:
* `padd /n John Doe /p 98765432 /e johnd@example.com`
* `padd /n Betsy Crowe /t friend /e betsycrowe@example.com /a Newgate Prison /p 1234567 /t criminal`

#### Adding a task: `tadd`

Adds a task to a participant in Ailurus.

Format: `tadd /n TASKNAME /p MEMBER_ID`

<div markdown="span" class="alert alert-primary">:bulb: Note:
A task must be assigned to a participant.
</div>

Examples:
* `tadd /n Collect payment from participants /p 3`

#### Adding an event: `eadd`

Adds an event to the Ailurus.

Format: `eadd /n EVENTNAME [/p MEMBER_ID]…​`

<div markdown="span" class="alert alert-primary">:bulb: Note:
You can add multiple participants to an event e.g. /p 2 /p 3 /p 4...
</div>

Examples:
* `eadd /n Computing Freshmen Orientation Camp 2021 /p 4 /p 5 /p 6`

### List

#### Listing all participants : `plist`

Shows a list of all participants (of an event optionally).

Format: `plist [/e EVENT_ID]`
* List everyone recorded in Ailurus.
* If  `EVENT_ID` is provided, list everyone who is participating in the event.
* `EVENT_ID` refers to the index number shown in the displayed event list.
* `EVENT_ID` **must be a positive integer** 1, 2, 3, …​

Example:
* `plist` lists everyone in Ailurus.
* `plist /e 3` lists all participants of the event with index number 3.

#### Listing all tasks of a participant : `tlist`

Shows a list of tasks of a participant with the specified id. Only can be used when the user is on participant lists (accessible via `plist [/e EVENT_ID]`).

Format: `tlist /p MEMBER_ID`
* Can only be used in `plist [/e EVENT_ID]`.
* `MEMBER_ID` refers to the index number of the participant of concern in the displayed participant list.
* `MEMBER_ID` **must be a positive integer** 1, 2, 3, …​

Example:
* `tlist /p 2` lists all tasks of the member with index number 2.

#### Listing all events : `elist`

Shows a list of all events.

Format: `elist`

### Editing a member : `edit`

Edits an existing participant in Ailurus. Only can be used when the user is on participant lists (accessible via `plist [/e EVENT_ID]`).

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Can only be used in `plist [/e EVENT_ID]`.
* Edits the participant at the specified `INDEX`. The index refers to the index number shown in the displayed participant list. 
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the member will be removed i.e adding of tags is not cumulative.
* You can remove all the member’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st member to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd member to be `Betsy Crower` and clears all existing tags.

### Locating members by name: `find`

Finds members whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Members matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Delete

#### Deleting a member : `pdel`

Deletes the specified participant from Ailurus. Only can be used when the user is on participant lists (accessible via `plist [/e EVENT_ID]`).

Format: `pdel /p MEMBER_ID`

* Can **only be used in participant list**.
* Deletes the member at the specified `MEMBER_ID`.
* `MEMBER_ID` refers to the index number shown in the displayed member list.
* `MEMBER_ID` **must be a positive integer** 1, 2, 3, …​

Examples:
* `pdel /p 2` deletes the 2nd member in the address book.

#### Deleting a task belonging to a member : `tdel`

Deletes the specified task of a specified participant from Ailurus. Only can be used when the user is on participant lists (accessible via `plist [/e EVENT_ID]`).

Format: `tdel /t TASK_ID /for MEMBER_ID`

* Can **only be used in participant list**.
* Deletes the task at the specified `TASK_ID` belonging to the member at the specified `MEMBER_ID`.
* `MEMBER_ID` refers to the index number shown in the displayed member list.
* `MEMBER_ID` **must be a positive integer** 1, 2, 3, …​
* `TASK_ID` refers to the index number shown in the displayed task list of that specified member.
* `TASK_ID` **must be a positive integer** 1, 2, 3, …​

Examples:
* `tdel /t 3 /for 5` deletes the 3rd task for the 5th member in Ailurus.

#### Deleting an event : `edel`

Deletes the specified event from the address book.

Format: `edel /e EVENT_ID`

* Deletes the event at the specified `EVENT_ID`.
* `EVENT_ID` refers to the index number shown in the displayed member list.
* `EVENT_ID` **must be a positive integer** 1, 2, 3, …​

Examples:
* `edel /e 10` deletes the 10th event in the address book.

### Mark a task as done : `tdone`
Marks the specified task of the specified member as done. Only can be used when the user is on participant lists (accessible via `plist [/e EVENT_ID]`).

Format: `tdone /p MEMBER_ID /t TASK_ID`

* Can **only be used in participant list**.
* Marks the task specified by `TASK_ID` of the member specified by `MEMBER_ID`.
* `MEMBER_ID` refers to the index number shown in the displayed participant list.
* `TASK_ID` refers to the index number shown in the displayed task list of the specified participant.
* `MEMBER_ID` and `TASK_ID` **must be a positive integer** 1, 2, 3, …​

Example:
* `tdone /p 2 /t 3` deletes the 3rd task of the 2nd participants.

### Clearing all entries : `clear`

Clears all entries from Ailurus.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Ailurus data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Ailurus data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Ailurus will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Opening up the Menu page. `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**help** | `help`
**padd** | `padd /n NAME /p PHONE_NUMBER /e EMAIL /a ADDRESS [/t TAG]…​` <br> e.g., `padd /n James Ho /p 22224444 /e jamesho@example.com /a 123, Clementi Rd, 1234665 /t friend /t colleague`
**tadd** | `tadd /n TASKNAME /p MEMBER_ID` <br> e.g., `tadd /n Collect payment from participants /p 3`
**eadd** | `eadd /n EVENTNAME [/p MEMBER_ID]…​` <br> e.g., `eadd /n Computing Freshmen Orientation Camp 2021 /p 4 /p 5 /p 6`
**plist** | `plist [/e EVENT_ID]` <br> e.g., `plist /e 3`
**tlist** | `tlist /p MEMBER_ID` <br> e.g., `tlist /p 2`
**elist** | `elist`
**edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**pdel** | `pdel /p MEMBER_ID` <br> e.g., `pdel /p 6`
**tdel** | `tdel /t TASK_ID /for MEMBER_ID` <br> e.g., `tdel /t 4 for/ 3`
**edel** | `edel /e EVENT_ID` <br> e.g., `edel /e 7`
**tdone** | `tdone /p MEMBER_ID /t TASK_ID`<br> e.g. `tdone /p 2 /t 3`
**clear** | `clear`
**exit** | `exit`
