---
layout: page
title: User Guide
---

Managera is a **desktop app that provides event organisers with a convenient method of keeping track of upcoming events and the details of their participants**. It does not handle communication between the event organiser and the participants.

Managera is OS-independent meaning it will work on any operating system.

## Table of Contents
- [**How to use this user guide**](#how-to-use-this-user-guide)
- [**Quick start**](#quick-start)
- [**Features**](#features)
    * [Participant](#participant)
        * [Adding a Participant: `add`](#adding-a-participant-add)
        * [Deleting a Participant: `delete`](#deleting-a-participant--delete)
        * [Editing a Participant: `edit`](#editing-a-participant--edit)
        * [Locating Participants by name: `find`](#locating-participants-by-name-find)
        * [Listing all Participants: `list`](#listing-all-participants--list)
        * [View Participant details: `view`](#view-participant-details-view)
    * [Event](#event)
        * [Adding an event: `addEvent`](#adding-an-event-addevent)
        * [Deleting an event: `deleteEvent`](#deleting-an-event--deleteevent)
        * [Editing an event: `editEvent`](#editing-an-event--editevent)
        * [Mark an Event as done: `done`](#mark-an-event-as-done-done)
        * [Locating Events by name: `findEvent`](#locating-events-by-name-findevent)
        * [Filter Events: `filterEvents`](#filter-events-filterevents)
        * [Sort Events: `sortEvents`](#sort-events-sortevents)
        * [Listing all Events: `listEvents`](#list-events-listevents)
        * [Show Event details: `showDetails`](#show-event-details-showdetails)
        * [Show Event Participants: `showParticipants`](#show-event-participants-showparticipants)
    * [Interaction](#interaction)
        * [Add Participant to Event: `enroll`](#add-participant-to-event-enroll)
        * [Remove Participant from Event: `expel`](#remove-participant-from-event-expel)
    * [Miscellaneous](#miscellaneous)
        * [Viewing help : `help`](#viewing-help--help)
        * [Clearing all Data: `clear`](#clearing-all-data--clear)
        * [Exiting the program: `exit`](#exiting-the-program--exit)
        * [Saving the data](#saving-the-data)
        * [Editing the data file](#editing-the-data-file)
- [**FAQ**](#faq)
- [**Command summary**](#command-summary)

--------------------------------------------------------------------------------------------------------------------
## How to use this user guide

If this is the first time you are using Managera, you are strongly advised to visit the Quick Start section to learn how to set up Managera
and get started.

If there are any doubts on how certain commands are used, you can look for the description of the command in the Features
section or have a brief overview in the Command Summary section.

For any other questions about Managera, you may refer to the FAQ section.

You can quickly jump to any of the sections by using the Table of Contents above.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `managera.jar` from [here](https://github.com/AY2122S1-CS2103T-T10-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Managera.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window. <br>
   Some example commands you can try:

   * **`list`** : Lists all Participants.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a Participant named `John Doe` to Managera.

   * **`delete`**`3` : Deletes the 3rd Participant shown in the current list.

   * **`clear`** : Deletes all Participants and Events.

   * **`exit`** : Exits the app.
     
   * **`addEvent n/My First Event d/2021-01-01`** : Adds a new Event to the list called 'My First Event'.
     
   * **`sortEvents`** : Sorts the current list of Events in chronological order.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addEvent n/NAME d/DATE`, `n/NAME` and `d/DATE` are parameters which can be used as `addEvent n/CS2103T Final d/2021-11-23`.

* Items in square brackets are optional.<br>
  e.g. `addEvent n/NAME d/DATE [t/TIME]` can be used as `addEvent n/CS2103T Final d/2021-11-23 t/1700` or as `addEvent n/CS2103T Final d/2021-11-23`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME d/DATE`, `d/DATE n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `t/1700 t/1800`, only `t/1800` will be taken.

* Extraneous parameters for commands that do not take in parameters (namely `help`, `list`, `clear`, `sortEvents` and `exit`) will be ignored.<br>
  e.g. if the command specifies `sortEvents 123`, it will be interpreted as `sortEvents`.

</div>

## Participant

The following commands deal with the handling of Participants in Managera. They can help you add Participants, delete Participants, view their details etc.

### Adding a Participant: `add`

Adds a Participant to Managera.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/BIRTHDATE]`

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Deleting a Participant : `delete`

Deletes the specified Participant from Managera.

Format: `delete INDEX`

* Deletes the Participant at the specified `INDEX`.
* The index refers to the index number shown in the displayed Participant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd Participant in the current list.
* `find Betsy` followed by `delete 1` deletes the 1st Participant in the results of the `find` command.

### Editing a Participant : `edit`

Edits an existing Participant in Managera.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/BIRTHDATE]`

* Edits the Participant at the specified `INDEX`. The index refers to the index number shown in the displayed Participant list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` - Edits the phone number and email address of the 1st Participant to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower` - Edits the name of the 2nd Participant to be `Betsy Crower`.

### Locating Participants by name: `find`

Finds Participant whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Participants matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Listing all Participants : `list`

Shows a list of all Participants in Managera.

Format: `list`

### View Participant details: `view`

Displays the details of a Participant in Managera.

Format: `view INDEX`

* Views the details of the Participant at the specified `INDEX`.
* The index refers to the index number shown in the displayed Participant list.
* The index **must be a positive integer** 1, 2, 3, …​

Example Usage:
* `view 1` - Displays the details of the 1st Participant in the current list.

## Event

The following commands deal with the handling of Events in Managera. They can help you add Events, delete Events, mark Events as done etc.

### Adding an event: `addEvent`

Creates an Event at the specified date and time and adds it to Managera.

Format: `addEvent n/NAME d/DATE [t/TIME]` 

Example Usage:
* `addEvent n/CS2100 Finals d/2021-11-20 t/0900` - Creates an Event "CS2100 Finals" on 20th November 2021 9:00am.
* `addEvent n/240Km Marathon d/2022-08-20` - Creates a full day Event "240 km Marathon" on 20th August 2022.

### Deleting an event : `deleteEvent`

Deletes the specified Event from Managera.

Format: `deleteEvent INDEX`

* Deletes the Event at the specified `INDEX`.
* The index refers to the index number shown in the displayed Events list.
* The index **must be a positive integer** 1, 2, 3, …​

Example Usage:
* `listEvents` followed by `deleteEvent 2` deletes the 2nd Event on the current list.
* `filterEvents d/2021-09-18` followed by `deleteEvent 1` deletes the 1st Event in the results of the `filterEvents` command.

### Editing an event : `editEvent`

Edits an existing event in Managera.

Format: `editEvent INDEX [n/EVENTNAME] [d/EVENTDATE] [t/EVENTTIME]`

* Edits the Event at the specified `INDEX`.
* The index refers to the index number shown in the displayed Events list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove the time of event by typing `t/` and leaving blank after it.

Example Usage:
* `editEvent 1 n/241Km Marathon` - Edits the event name of the 1st Event to be `241Km Marathon`.
* `editEvent 2 n/2103T milestone v1.3 d/2021-10-21 t/2359` - Edits the event name, event date and event time of the 2nd Event to be `2103T milestone v1.3`, `2021-10-21` and `2359` respectively.

### Mark an Event as done: `done`

Marks the specified Event in Managera as done.

Format: `done INDEX`

* Marks the Event at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed Events list.
* The index **must be a positive integer** 1, 2, 3, …​

Example Usage:
* `listEvents` followed by `done 2` marks the 2nd Event in the current list as done.
* `filterEvents d/2021-09-18` followed by `done 1` marks the 1st Event in the results of the `filterEvents` command as done.

### Locating Events by name: `findEvent`

Finds Events whose names contain any of the given keywords.

Format: `findEvent KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `marathon` will match `Marathon`
* The order of the keywords does not matter. e.g. `Marathon Commencement` will match `Commencement Marathon`
* Only the name is searched.
* Only full words will be matched e.g. `Marath` will not match `Marathon`
* Events matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Marathon Commencement` will return `240Km Marathon`, `Marathon Commencement`

Examples:
* `findEvent party` returns `beach party` and `Christmas party`
* `findEvent marathon meeting` returns `240Km Marathon`, `project meeting`<br>

### Filter Events: `filterEvents`

Filters the list of events for events occurring on a specific date and optionally by time too.

Format: `filterEvents d/DATE [t/TIME]`

Example Usage:
* `filterEvents d/2021-09-18` - Filters the list of events to show only events occurring on 18th September 2021.
* `filterEvents d/2021-09-18 t/0900` - Filters the list of events to show only events occurring on 18th September 2021 9am.

### Sort Events: `sortEvents`

Sorts the current list of events in chronological order with the earlier events at the top and later events at the bottom.

Format: `sortEvents`

### List Events: `listEvents`

Show a list of all events in Managera.

Format: `listEvents`

### Show Event details: `showDetails`

Displays the details of the Event at the specified index.

Format: `showDetails INDEX`

* Views the details of the Event at the specified `INDEX`.
* The index refers to the index number shown in the displayed Event list.
* The index **must be a positive integer** 1, 2, 3, …​

Example Usage:
* `showDetails 1` - Displays the date and time of the 1st Event in the current list.

### Show Event Participants: `showParticipants`

Displays the list of participants of the Event at the specified index.

Format: `showParticipants INDEX`

* Views the Participants of the Event at the specified `INDEX`.
* The index refers to the index number shown in the displayed Event list.
* The index **must be a positive integer** 1, 2, 3, …​

Example Usage:
* `showParticipants 4` - Displays the list of participants of the 4th Event in the current list.

## Interaction

The following commands deal with the handling of Events and Participants in Managera. They can help you add Participants to Events or remove Participants from Events.

### Add Participant to Event: `enroll`

Adds a Participant with the first specified index to the Event with the second specified index.

Format: 
`enroll INDEX_1 INDEX_2`

* Adds the Participant at specified `INDEX_1` to the Event at specified index `INDEX_2`.
* The index refers to the index number shown in the displayed Participant/Event list.
* The indexes **must be positive integers** 1, 2, 3, …​

Example Usage:
* `enroll 1 2` - Adds the 1st Participant in the displayed Participant list to the 2nd Event in the displayed Event list.

### Remove Participant from Event: `expel`

Removes the Participant with the first specified index from the Event with the second specified index.

Format: `expel INDEX_1 INDEX_2`

* Removes the Participant at specified `INDEX_1` from the Event at specified index `INDEX_2`.
* The index refers to the index number shown in the displayed Participant/Event list.
* The indexes **must be positive integers** 1, 2, 3, …​

Example Usage:
* `expel 3 1` - Removes the 3rd Participant in the displayed Participant list from the 1st Event in the displayed Event list.

## Miscellaneous

The following features are additional and deal with other actions that you may want to do in Managera.

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Clearing all Data : `clear`

Clears all Events and Participants from Managera.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Managera data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Managera data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Managera will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Managera home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Participant** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`
**Delete Participant** | `delete INDEX`<br> e.g., `delete 3`
**Edit Participant** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find Participant** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List Participants** | `list`
**View Participant details** | `view INDEX` <br> e.g., `view 1`
**Add Event** | `addEvent n/NAME d/DATE [t/TIME]` <br> e.g., `addEvent n/CS2100 Finals d/2021-11-20 t/0900`
**Delete Event** | `deleteEvent INDEX`  <br> e.g., `deleteEvent 1`
**Edit Event** | `editEvent INDEX [n/EVENT_NAME] [d/EVENT_DATE] [t/EVENT_TIME]` <br> e.g., `editEvent n/241Km Marathon`
**Mark an Event as done** | `done INDEX` <br> e.g., `done 1`
**Find Event** | `findEvent KEYWORD [MORE_KEYWORDS]`<br> e.g., `findEvent Marathon Commencement`
**Filter Events** | `filterEvents d/DATE [t/TIME]` <br> e.g., `filterEvents d/2021-09-18`
**Sort Events** | `sortEvents`
**List Events** | `listEvents`
**Show Event Details** | `showDetails INDEX` <br> e.g., `showDetails 1`
**Show Event Participants** | `showParticipants INDEX` <br> e.g., `showParticipants 3`
**Add Participant to Event** | `enroll INDEX_1 INDEX_2` <br> e.g. `enroll 1 2`
**Remove Participant from Event** | `expel INDEX_1 INDEX_2` <br> e.g. `expel 3 1`
**Help** | `help`
**Clear** | `clear`
**Exit** | `exit`
