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

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2122S1-CS2103T-W12-1/tp/releases). [coming soon]

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

### Listing all facilities : `listf`

Shows a list of all facilities.

Format: `listf`

### Finding a facility : `findf`

Finds facilities whose location contains any of the given keywords

Format: `findf KEYWORD [MORE_KEYWORDS]`
* Search is case-insensitive. Eg. Utown will match utown
* Only the location is searched
* Only full words will be matched eg. Utown will not match town
* Facilities matching at least one keyword will be returned (i.e. OR search) e.g Utown Redhill will return Utown Field, Redhill Sports Complex

Examples:
* `find redhill` returns `Redhill Sports Complex` and `Redhill Field`
  
### Deleting a facility : `deletef`

Deletes a facility from the facility list.

Format: `deletef INDEX`
* Deletes the facility at the specified INDEX
* INDEX refers to the index number shown in the displayed facility list
* INDEX must be a positive integer 1, 2, 3… 

Examples:
* `listf` followed by `deletef 2` deletes the 2nd person in the facility list
* `findf Court 1` followed by `deletef 1` deletes the 1st facility in the results of the findf command

### Adding a member: `addm`

Adds a member to the list of people in the CCA

Format: `addm n/NAME p/PHONE_NUMBER [d/DAYS]`
* `[d/DAYS]` is an optional field indicating a list of days for which the member is available for that week
* Members added without availability will have an empty list of days

Examples:
* `addm n/John p/91234567 d/Mon d/Tues` adds John to the member list and indicates his availability on Monday and Tuesday
* `addm n/Bob p/91228372` adds Bob to the member list with zero available days by default

### Listing all members: `listm`

Shows a list of all members

Format: `listm`

### Finding a member `findm`

Finds members whose names contains any of the given keywords

Format: `findm KEYWORD [MORE_KEYWORDS]`
* Search is case-insensitive. Eg. John will match john
* `[MORE_KEYWORDS]` is an optional field
* Only the name of the member is searched
* Only full words will be matched eg. Johnny will not match John
* Names matching at least one keyword will be returned (i.e. OR search) e.g John Henry will return John, Henry

Examples:
* `findm Bob` returns bob and Bob Doe
* `findm john bobby` returns John Lee, Bobby Tan
  
### Deleting a member : `deletem`

Deletes a member from the member list

Format: `deletem INDEX`

* Deletes the member at the specified `INDEX`. 
* The index refers to the index number shown in the displayed member list. 
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listm` followed by `deletem 2` deletes the member at index 2 of the member list
* `findm John` followed by `deletem 1` deletes the 1st person in the results of the `findm` command 

### Setting member availability: `setm`

Sets the availability of a given member.

Format: `setm INDEX d/DAY [d/DAY]…​`

* Sets the availability of the member at the specified `INDEX` to be the specified `DAY(s)`
* Availability is defined as days of the week when member is free
* `DAY` **must be one of the following:** Mon, Tue, Wed, Thu, Fri, Sat, Sun
* The index refers to the index number shown in the displayed member list
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listm` followed by `setm 5 d/Mon d/Tue` sets the availability of the person at index 5 in the member list to be Monday and Tuesday
* `findm John` followed by `setm 2 d/Mon` sets the availability of the person at index 2 in the results of the `findm` command to be Monday 


### Clearing all entries in facility list: `clearf`

### Splitting members into facilities : `split`

Splits members into facilities based on its capacity and members' availability.

Format: `split d/DAY`

* Allocate members available at the specified `DAY` to each facility
* `DAY` **must be one of the following:** Mon, Tue, Wed, Thu, Fri, Sat, Sun

Examples:
* `split d/Mon` splits members into groups for training on Monday of that week and displays the list of allocations to the user

### Clearing all entries in facility list: `clearf`

Clears all entries from the address book.

Format: `clearf`

### Clearing all entries in member list: `clearm`

Clears all members from the member list.

Format: `clearm`

### Saving the data

* SportsPA data are saved in the hard disk automatically after any command that changes the data. They are saved as a JSON file [JAR file location]/data/sportspa.json
* If changes made to the data file makes its format invalid, SportsPA will discard all data and start with an empty data file at the next run.

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
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
