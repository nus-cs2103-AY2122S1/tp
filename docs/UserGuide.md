---
layout: page
title: User Guide
---

Managera is a **desktop app that provides event organisers with a convenient method of keeping track of upcoming events and the details of their participants and collaborators**. It does not handle communication between the event organiser and the participants or collaborators.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

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

### Adding an event: `addEvent`

Creates an Event at the specified date and time.

Format: `addEvent n/NAME d/DATE [t/TIME]` 

Examples:
* `addEvent n/CS2100 Finals d/2021-11-20 t/0900` - Creates an Event "CS2100 Finals" on 20th November 2021 9:00am.
* `addEvent n/240Km Marathon d/2022-08-20` - Creates a full day Event "240km Marathon" on 20th August 2022.

### Removing an event : `removeEvent`

Removes an Event. If there are multiple events with the same name, specify the DATE (in YYYY-MM-DD format) and TIME (24h format) to remove that event.

Format: `removeEvent n/NAME [d/DATE] [t/TIME]` 

Examples:
* `removeEvent n/CS2100 Finals d/2021-11-20  t/0900` - Removes the Event “CS2100 Finals” on 20th November 2021 9:00am.
* `removeEvent n/240km Marathon d/2022-08-20` - Removes the Event “240km Marathon” on 20th August 2022.

### Mark an Event as done: `doneEvent`

Format:
`doneEvent n/NAME [d/DATE] [t/TIME]` - Marks an Event as done, if there are multiple events with the same name, specify the DATE (in YYYY-MM-DD format) and TIME (24h format) to select that event.

Example Usage:
* `doneEvent n/CS2100 Finals d/2021-11-20  t/0900` - Marks the Event “CS2100 Finals” on 20th November 2021 9:00am as done.
* `doneEvent n/240km Marathon d/2022-08-20` - Marks the Event “240km Marathon” on 20th August 2022 as done.


### Sort Events: `sortEvents`
Sorts the current list of Events in chronological order with the earlier events at the top and later events at the bottom.

Format:
`sortEvents`

### Filter Events: `filterEvents`
Filters the list of events for events occurring on a specific date.

Format:
`filterEvents DATE`

Example Usage:

* `filterEvents 2021-09-18` - Filters the list of events to show only events occurring on 18th September 2021.

### Add Participant to Event: `addParticipant`

Format: 
`addParticipant e/EVENT_NAME p/PARTICIPANT_ID` - Adds Participant with matching participantID to Event.

Example Usage: 
`addParticipant e/CS2103T Finals p/mikerowe1` - Adds Mike Rowe whose participantID is mikerowe1 to Event CS2103T Finals.

### Remove Participant to Event: `removeParticipant`

Format:
`removeParticipant e/EVENT_NAME p/PARTICIPANT_ID` - Removes Participant with matching participantID from event.

Example Usage:
`removeParticipant e/CS2103T Finals p/mikerowe1` - Removes Mike Rowe whose participant ID is mikerowe1 from event CS2103T Finals.

### Show Event details: `showDetails`

Format:
`showDetails EVENT_NAME` - Displays the details of the Event matching the given name.

Example Usage:
* `showDetails CS2103T Finals` - Displays the date and time of the ‘CS2103T Finals’ Event.

### Show Event Participants: `showParticipants`

Format:
`showParticipants EVENT_NAME` - Displays the list of participants of the Event matching the given name.

Example Usage:
* `showParticipants CS2103T Finals` - Displays the list of participants of the ‘CS2103T Finals’ Event.

### Find Participant and access details: `findParticipant`

Format:
`findParticipant KEYWORD` - Finds any participants whose name contains the given KEYWORD and returns the details about each participant.

Example Usage:
* `findParticipant Mike` - Filters the list of Participants for Participants who have names containing “Mike”.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

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
**Help** | `help`
**Add Event** | `addEvent n/NAME d/DATE [t/TIME]` <br> e.g., `addEvent n/CS2100 Finals d/2021-11-20 t/0900`
**Remove Event** | `removeEvent n/NAME [d/DATE] [t/TIME]`  <br> e.g., `removeEvent n/CS2100 Finals d/2021-11-20  t/0900`
**Mark an Event as done** | `doneEvent n/NAME [d/DATE] [t/TIME]` <br> e.g. `doneEvent n/240km Marathon d/2022-08-20`
**Sort Events** | `sortEvents`
**Filter Events** | `filterEvents DATE` <br> e.g. `filterEvents 2021-09-18`
**Show Event Details** | `showDetails EVENT_NAME` <br> e.g., `showDetails CS2103T Finals`
**Show Event Participants** | `showParticipants EVENT_NAME` <br> e.g., `showParticipants CS2103T Finals`
**Find Participants** | `findParticipant KEYWORD` <br> e.g. `findParticipant Mike`
