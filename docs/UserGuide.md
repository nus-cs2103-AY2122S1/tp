---
layout: page
title: User Guide
---

Staff’d helps F&B managers manage details and schedules of their staff. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Copy the file to the folder you want to use as the _home folder_ for your Staff’d.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>

  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Basic management of Staff Details


### Tag legend - `todo: standardize tags for all commands`

|Tag|Description|
|---|-----------|
|n/|Name|
|s/|Status (as a full-time/part-time worker)|
|r/|Role (e.g. Cook, Staff Management)|
|a/|Address|
|$/|Salary|
|i/|Index|
|e/|Email|
|t/|Extra tags|

### View a staff - `view`

View the staff details of a single staff.

Examples:

`view n/Candice`\
`view i/123`

### Adding a staff - `add`

* Adds a staff to the system. The tags and information are optional and can be presented in any order.
* Upon creation of a staff, the system creates an index for them which can be used to refer to them and access the system.

Format:

`add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS $/SALARY [r/ROLE] [s/STATUS] [t/TAG...]`

Examples:

`add n/Joe s/fulltime r/manager p/98765432 $/1234789 e/Joe@example.com a/John street, block 123, #01-01`\
`add n/Candice s/parttime p/91234567 $/2 e/candice@example.com a/Newgate Prison`

### Listing all persons : `list`

Shows a list of all staffs in the staff list.

Format: `list`

### Deleting a Staff : `delete`

Deletes the specified staff from the staff list.

Formats:

`delete n/name`\
`delete i/index`\
`delete r/role`\
`delete s/status`

* Deletes the staff(s) with the specified `NAME`, `ROLE`, `STATUS`, `INDEX`.
* The index refers to the index number shown in the displayed staff list. It **must be a positive integer** 1, 2, 3, …​

Examples:

`delete n/Candice`\
`delete i/12345678`\
`delete r/cashiers`\
`delete s/full-time`

[comment]: <> (* `list` followed by `delete 2` deletes the 2nd staff in the staff list.)
[comment]: <> (* `find Betsy` followed by `delete 1` deletes the 1st staff in the results of the `find` command.)

### Editing a staff : `edit`

Edits an existing staff in the Staff List.

Formats:

`edit -n NAME [n/NAME] [p/PHONE] [e/EMAIL] [r/ROLE] [s/STATUS] [$/SALARY] [a/ADDRESS] [t/TAGS...]`\
`edit -i INDEX [n/NAME] [p/PHONE] [e/EMAIL] [r/ROLE] [s/STATUS] [$/SALARY] [a/ADDRESS] [t/TAGS...]`


* Edits the staff of the specified `NAME`, `INDEX`
The index refers to the index number shown in the displayed staff list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:

`edit -i 1 p/91234567 e/johndoe@example.com`\
`edit -n Bob p/69696969 e/candicepleasedateme@tinder.com`\
`edit -n Candice r/cook`

|Tag|Name|Description|
|---|----|-----------|
|-n|Name|Contact with the name will be edited.|
|-i|Index|Contact with that index will be edited.|


### Locating persons by name: `find`

Finds staff whose names contain any of the given keywords.

Format:

`find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Staff matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li` 


  ![result for 'find alex david'](images/findAlexDavidResult.png)


### Clearing all entries : `clear`

Clears all entries from the Staff List.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Basic Management of Staff Schedules

### Adding a shift to staff's schedule: `addShift`

Adds a time period where the staff is working to the staff’s schedule.

Formats:

`addShift n/name d/fullDayName-shiftNumber` \
`addShift i/index d/fullDayName-shiftNumber`


* There are two ways to identify the staff to add the time period to: by their `name` or by their staff `index`.
* The `fulldayname` field required to specify shifts are not case sensitive.

Examples:

`addShift n/Candice d/Monday-1` \
`addShift i/1234 d/tuesday-0`

### View a staff schedule : `viewSchedule`

Views a specific staff’s schedule.

Formats:

`viewSchedlue n/name` \
`viewSchedlue i/index`

Examples:

`viewSchedule n/Candice` \
`viewSchedule i/123`


### Deleting a staff schedule: `deleteSchedule`

Deletes a time period from the staff schedule.  There are two ways to identify the staff to delete the time period from: by their `name` or by their staff `index`. The deleted period must be the same as a period previously entered by the manager.

Formats:

`deleteSchedule n/name d/fullDayName-shiftNumber` \
`deleteSchedule i/index d/fullDayName-shiftNumber`

Examples:

`deleteSchedule n/Joe d/tuesday-2` \
`deleteSchedule i/1278 d/friday-1`

### Editing a staff schedule: `editSchedule`

Edits a staff schedule start and end date time. There are two ways to identify the staff who’s schedule will be edited: by their name or by their staff ID.

Formats:

`editSchedule n/name old/fullDayName-shiftNumber new/fullDayName-shiftNumber` \
`editSchedule id/ID old/fullDayName-shiftNumber new/fullDayName-shiftNumber`

Examples:

`editSchedule n/Candice old/tuesday-1 new/tuesday-2` \
`editSchedule n/12345678 old/wednesday-2 new/thursday-2`


### Saving the data

Staff'd data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Staff'd data are saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Staff'd will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Staff'd home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**View** | `view n/name` <br> `view i/index`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE] [s/STATUS]`
**Delete** | `delete n/name` <br> `delete i/index` <br> `delete r/role` <br> `delete s/status`
**Edit** | `edit -n NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE] [s/STATUS]` <br> `edit -i INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE] [s/STATUS]`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**View staff schedule** | `viewSchedlue n/name` <br> `viewSchedlue i/index`
**Add staff schedule** | `addSchedule n/name d/fullDayName-shiftNumber` <br> `addSchedule i/index d/fullDayName-shiftNumber`
**Edit staff schedule** | `editSchedule n/name old/fullDayName-shiftNumber new/fullDayName-shiftNumber` <br> `editSchedule i/index old/fullDayName-shiftNumber new/fullDayName-shiftNumber`
**Delete staff schedule** | `deleteSchedule n/name d/fullDayName-shiftNumber` <br> `deleteSchedule i/index d/fullDayName-shiftNumber`
**List** | `list`
**Help** | `help`
**Clear** | `clear`
**Exit** | `exit`
