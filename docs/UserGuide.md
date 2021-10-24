---
layout: page
title: User Guide
---

Ailurus is a **desktop application** designed to aid Organising Committees from the Computing Faculty in managing and accounting for their administrative concerns. It provides users with the ability to plan and manage events and tasks for their members.

Ailurus is catered for people that are familiar with Command Line Interfaces (CLI), and are able to type fast. It also preserves the benefits of a Graphical User Interface (GUI) via JavaFX.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ailurus.jar` from [here](https://github.com/AY2122S1-CS2103T-T15-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Ailurus.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/sampleUi.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
open the help window.<br>
   Some example commands you can try:

   * **`mlist`** : Lists all members in Ailurus.

   * **`madd`**`/n John Doe /ph 98765432 /em johnd@example.com /a John street, block 123, #01-01` : Adds a contact named `John Doe` to Ailurus.

   * **`mdel`**`3` : Deletes the 3rd contact shown in the current list of people.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Commands are usually abbreviated to facilitate faster typing for user convenience. As a general rule of thumb, `m`, `e` and `t` are used to represent `Member`, `Event` and `Task` respectively.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `madd /n NAME`, `NAME` is a parameter which can be used as `madd /n John Doe`.

* Items in square brackets are optional.<br>
  e.g. `/n NAME [/p POSITION]` can be used as `/n John Doe /p friend` or as `/n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[/p POSITION]…​` can be used as ` ` (i.e. 0 times), `/p friend`, `/p friend /p family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `/n NAME /ph PHONE_NUMBER`, `/ph PHONE_NUMBER /n NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `/ph 12341234 /ph 56785678`, only `/ph 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored.
  <br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Some commands require the user to enter the `EVENT_ID`, `MEMBER_ID` or `TASK_ID`. The `ID` refers to the order on 
  the display list.<br>
  e.g. `Financial Planning` has a `EVENT_ID` of `1` and `Charlotte Oliveiro` has a `MEMBER_ID` 
  of `3` according to the diagram below.
  ![idNumberExample](images/idNumberExample.png)

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Member Commands

#### Adding a member: `madd`

Adds a member to Ailurus.

Format: `madd /n NAME /ph PHONE_NUMBER /em EMAIL [/a ADDRESS] [/p POSITION]…​`

<div markdown="span" class="alert alert-primary"> 
:bulb: **Tip:** A member can have any number of positions (including 0). A member MUST have a name, phone number and email address, but mailing address and positions are optional.
</div>

Examples:
* `madd /n John Doe /ph 98765432 /em johnd@example.com`
* `madd /n Betsy Crowe /t friend /em betsycrowe@example.com /a Newgate Prison /ph 1234567 /p criminal`

#### Listing all members : `mlist`

Shows a list of all members (of an event optionally).

Format: `mlist [/e EVENT_ID]`

* `EVENT_ID` **must be a positive integer** 1, 2, 3, …​
* List everyone recorded in Ailurus if `EVENT_ID` is not given.
* If  `EVENT_ID` is provided, list everyone who is participating in the event.
* `EVENT_ID` refers to the index number shown in the displayed event list.

Example:
* `mlist` lists everyone in Ailurus.
* `mlist /e 3` lists all members of the event with index number 3.

#### Editing a member : `medit`

Edits an existing member in Ailurus. 

Format: `edit INDEX [/n NAME] [/ph PHONE] [/em EMAIL] [/a ADDRESS] [/p POSITION]…​`

* `INDEX` **must be a positive integer** 1, 2, 3, …​
* Edits the member at the specified `INDEX`. The index refers to the index number shown in the displayed member list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing positions, the existing positions of the member will be removed i.e adding of positions is not cumulative.
* You can remove all the member’s positions by typing `/t` without specifying any positions after it.

Examples:
* `edit 1 /p 91234567 /e johndoe@example.com` Edits the phone number and email address of the 1st member to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 /n Betsy Crower /t` Edits the name of the 2nd member to be `Betsy Crower` and clears all existing positions.

#### Locating members by name: `mfind`

Finds members whose names contain any of the given keywords.

Format: `mfind KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Members matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavid.png)

#### Deleting a member : `mdel`

Deletes the specified member from Ailurus.

Format: `mdel /m MEMBER_ID`

* `MEMBER_ID` **must be a positive integer** 1, 2, 3, …​
* Deletes the member at the specified `MEMBER_ID`.
* `MEMBER_ID` refers to the index number shown in the displayed member list.

Examples:
* `mdel /m 2` deletes the 2nd member in the address book.

### Task Commands

#### Adding a task: `tadd`

Adds a task to a member in Ailurus.

Format: `tadd /n TASKNAME /m MEMBER_ID`

<div markdown="span" class="alert alert-primary">
:bulb: Note: A task must be assigned to a member.
</div>

Examples:
* `tadd /n Collect payment from members /m 3`

#### Listing all tasks of a member : `tlist`

Shows a list of tasks of a member with the specified `MEMBER_ID`. 

Format: `tlist /m MEMBER_ID`

* `MEMBER_ID` **must be a positive integer** 1, 2, 3, …​
* `MEMBER_ID` refers to the index number of the member of concern in the displayed member list.


Example:
* `tlist /m 2` lists all tasks of the member with index number 2.

#### Deleting a task belonging to a member : `tdel`

Deletes the specified task of a specified member from Ailurus. Only can be used when the task list has entries (accessible via `tlist /m MEMBER_ID`).

Format: `tdel /t TASK_ID`

* Can **only be used when task list has entries**.
* `TASK_ID` **must be a positive integer** 1, 2, 3, …​
* Deletes the task according to the specified `TASK_ID`.
* `TASK_ID` refers to the index number shown in the displayed task list.

Examples:
* `tdel /t 3` deletes the 3rd task on the displayed task list in Ailurus.

#### Mark a task as done : `tdone`
Marks the specified task of the specified member as done. Only can be used when the task list has entries (accessible via `tlist /m MEMBER_ID`).

Format: `tdone /t TASK_ID`

* Can **only be used when task list has entries**.
* `TASK_ID` **must be a positive integer** 1, 2, 3, …​
* Marks the task specified by `TASK_ID`.
* `TASK_ID` refers to the index number shown in the displayed task list.

Example:
* `tdone /t 3` deletes the 3rd task on the displayed task list in Ailurus.

### Event Commands

#### Adding an event: `eadd`

Adds an event to the Ailurus.

Format: `eadd /n EVENTNAME [/m MEMBER_ID]…​`

<div markdown="span" class="alert alert-primary">:bulb: Note:
You can add multiple members to an event e.g. /m 2 /m 3 /m 4...
</div>

Examples:
* `eadd /n Computing Freshmen Orientation Camp 2021 /m 4 /m 5 /m 6`

#### Listing all events : `elist`

Shows a list of all events of a member.

Format: `elist [/m MEMBER_ID]`

* `MEMBER_ID` **must be a positive integer** 1, 2, 3, …​
* List all events recorded in Ailurus if `MEMBER_ID` is not given.
* If  `MEMBER_ID` is provided, list all events that the member is participating in.
* `MEMBER_ID` refers to the index number shown in the displayed member list.

#### Deleting an event : `edel`

Deletes the specified event from the address book.

Format: `edel /e EVENT_ID`

* `EVENT_ID` **must be a positive integer** 1, 2, 3, …​
* Deletes the event at the specified `EVENT_ID`.
* `EVENT_ID` refers to the index number shown in the displayed member list.


Examples:
* `edel /e 10` deletes the 10th event in Ailurus.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Ailurus data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Ailurus data are saved as a JSON file `[JAR file location]/data/Ailurus.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">
:exclamation: **Caution:** If your changes to the data file makes its format invalid, Ailurus will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Ailurus home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**help** | `help`
**madd** | `madd /n NAME /ph PHONE_NUMBER /em EMAIL /a ADDRESS [/p POSITION]…​` <br> e.g., `madd /n James Ho /ph 22224444 /em jamesho@example.com /a 123, Clementi Rd, 1234665 /p friend /p colleague`
**mlist** | `mlist [/e EVENT_ID]` <br> e.g., `mlist /e 3`
**medit** | `medit INDEX [/n NAME] [/ph PHONE_NUMBER] [/em EMAIL] [/a ADDRESS] [/p POSITION]…​`<br> e.g.,`edit 2 /n James Lee /em jameslee@example.com`
**mfind** | `mfind KEYWORD [MORE_KEYWORDS]`<br> e.g., `mfind James Jake`
**mdel** | `mdel /m MEMBER_ID` <br> e.g., `mdel /m 6`
**tadd** | `tadd /n TASKNAME /m MEMBER_ID` <br> e.g., `tadd /n Collect payment from members /m 3`
**tlist** | `tlist /m MEMBER_ID` <br> e.g., `tlist /m 2`
**tdel** | `tdel /t TASK_ID` <br> e.g., `tdel /t 4`
**tdone** | `tdone /t TASK_ID`<br> e.g. `tdone /t 3`
**eadd** | `eadd /n EVENTNAME [/m MEMBER_ID]…​` <br> e.g., `eadd /n Computing Freshmen Orientation Camp 2021 /m 4 /m 5 /m 6`
**elist** | `elist [/m MEMBER_ID]` <br> e.g., `elist /m 1`
**edel** | `edel /e EVENT_ID` <br> e.g., `edel /e 7`
**exit** | `exit`
