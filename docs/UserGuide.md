---
layout: page
title: User Guide
---

**SeniorLove** is a contact management desktop app which aims to facilitate community workers' visits to the elderly. It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Senior Love can get your contact management tasks done faster than traditional GUI apps.

##Table of Contents
- [Quick start](#quick-start)
- [Features](#features)
  - [Adding elderly details](#add-elderly-details--add)
  - [Deleting an elderly or corresponding visit](#delete-an-elderly-or-corresponding-visit--delete)
  - [Listing all elderly](#listing-all-persons--list)
  - [Locating elderly by attribute](#locating-elderly-by-attribute--find)
  - [Scheduling a visit to an elderly](#scheduling-a-visit-to-an-elderly--visit)
  - [Marking one visit as done](#marking-one-visit-as-done--done)
  - [Viewing help](#viewing-help--help)
  - [Downloading data](#downloading-data--download)  
  - [Clearing all entries](#clearing-all-entries--clear)
  - [Exiting the app](#exiting-the-program--exit)
- [FAQ](#faq)
- [Command summary](#command-summary)
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `seniorLove.jar` from [here](https://github.com/AY2122S1-CS2103-T14-1/tp).

3. Copy the file to the folder you want to use as the _home folder_ for your SeniorLove.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:


   * **`add`**`n/John Doe p/98765432 l/Chinese a/John street, block 123, #01-01` : Adds an elderly contact named `John Doe` to the SeniorLove.

   * **`delete`**`3` : Deletes the 3rd elderly shown in the current list.

   * **`list`** : Lists all elderly.

   * **`find`**`Hans` : Find all elderly whose attributes start with Hans.(Case insensitive)

   * **`clear`** : Deletes all elderly.

   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Features
* Add elderly with contact details
* Delete elderly or deadline
* List elderly details
* Sort elderly by visit
* Find an elderly
* Schedule a visit to an elderly
* Mark the current scheduled visit as done (update the last visited date and delete the current visit)
* View help


## Notes about command format:

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [h/HEALTH_CONDITION]` can be used as `n/John Doe h/dementia` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[h/HEALTH_CONDITION]…​` can be used as ` ` (i.e. 0 times), `h/dementia`, `h/dementia h/diabetes` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Add elderly details : `add`

Adds an elderly and all relevant details into SeniorLove.

Format: `add n/NAME p/PHONE_NUMBER l/LANGUAGE a/ADDRESS [lv/LAST_VISIT] [v/VISIT] [h/HEALTH_CONDITION]`

* Adds a new elderly with the following information: `NAME`, `PHONE_NUMBER`, `LANGUAGE`, `ADDRESS`, `LAST_VISITED`, `VISIT` and `HEALTH_CONDITION` may be optionally included.
* `NAME` is the elderly’s name. **This must be included.**
* `PHONE_NUMBER` is the elderly’s phone number. **This must be included.**
* `LANGUAGE` is the elderly’s preferred language for communication. **This must be included.**
* `ADDRESS` is the elderly’s address to be visited. **This must be included.**
* `LAST_VISIT` is the last datetime that the user has visited the elderly. **This is optional to include.**
* `VISIT` is the next scheduled datetime for the elderly’s visit. **This is optional to include.**
* `HEALTH_CONDITION` is the elderly's health condition. **This is optional to include.**

Examples:
* `add n/John p/12345678 l/English a/College Avenue East 18, New College` adds an elderly and details without the `LAST_VISITED`, `VISIT` and `HEALTH_CONDITION`.
* `add n/Jane p/54867392 l/Chinese a/200 Toa Payoh Avenue 56  lv/2021-09-30 10:00 v/2021-10-31 16:00 h/dementia` adds an elderly and details with `LAST_VISITED`, `VISIT` and `HEALTH_CONDITION`.


### Delete an elderly or corresponding visit : `delete`

Deletes either an elderly’s details or an elderly’s visit from SeniorLove.

Format: `delete [v/] INDEX`

* Deletes either the elderly’s details or the corresponding elderly’s visit at list index `INDEX`. `INDEX` is a **strictly positive integer, and must be included.**
* `v/` denotes if the item to be deleted is an elderly’s details or the elderly’s corresponding visit. **This is optional to include.** The presence of v/ indicates that it is the elderly’s visit that is to be deleted, while its exclusion indicates that it is the elderly’s details that is to be deleted.

Examples:
* `delete 1` deletes the elderly’s details of the elderly at list index 1.
* `delete v/ 1` deletes the elderly’s visit of the elderly at list index 1.


### Editing a person : `edit`

Edits an existing elderly's attributes in SeniorLove.

Format: `edit INDEX [n/NAME] [p/PHONE] [l/LANGUAGE] [a/ADDRESS] [lv/LAST_VISIT] [h/HEALTH_CONDITION]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing health conditions, the existing health conditions of the person will be removed i.e adding of health conditions is not cumulative.
* You can remove all the person’s health conditions by typing `h/` without
    specifying any health conditions after it.
* You can remove the person's last visit by typing `lv/` without specifying any datetime after it.

Examples:
*  `edit 1 p/91234567 l/English` Edits the phone number and language of the 1st person to be `91234567` and `English` respectively. All other attributes are not modified.
*  `edit 2 n/Betsy Crower h/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing health conditions. All other attributes are not modified.
*  `edit 1 lv/2021-10-10 13:00` Edits the last visit of the 1st person to be `10 Oct 2021 13:00`. All other attributes are not modified.
*  `edit 1 lv/` Removes the last visit for the 1st person. All other attributes are not modified.


### Listing all persons : `list`

Shows a list of all the elderly and their associated details in SeniorLove address book in alphabetical order of their name.

Format: `list`

### Sort elderly by visit: `sort`

Shows a sorted list of elderly and their associated details in SeniorLove either by `last visit` date (the latest first) or by next `visit` date (the earliest first).

Format: `sort [FIELD_TO_BE_SORTED]`

* `sort lv/` sorts elderly in descending order of their `last visit` date (the latest first).
* `sort v/` sorts elderly in ascending order of their next `visit` date (the earliest first).
* There should be exactly one `FIELD_TO_BE_SORTED` at any time.
* Currently, `sort` only supports fields of `last visit` or `visit`.

Example: `sort lv/` sorts the person list in descending order of `last visit` date.

### Locating elderly by attribute : `find`

Finds elderly whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will not match `Bo Hans`
* All attributes are searched (`NAME`, `PHONE`, `LANGUAGE`, `ADDRESS`, `VISIT`, `LAST_VISIT`)
* Partial words will be matched, and will return any person who has the partial word as a substring of any attribute e.g. `Han` will match `Hans Bo` and `Rohan Tan`
* `VISIT` and `LAST_VISIT` are in the `yyyy-MM-dd HH:mm` format.
* Persons matching all given keywords will be returned (i.e. `AND` search).
  e.g. `Hans English` will return `Hans Gruber`, `Hanson Lim`, both of which have `LANGUAGE` English

Examples:
* `find John` returns `john` and `John Doe`
* `find alex English` returns `Alex Yeoh`, `Alex Lim`, both with `LANGUAGE` English.<br>


### Scheduling a visit to an elderly : `visit`

Updates the time for the next visit to the elderly with the given index of the elderly in the address book.

Format: `visit INDEX  at/VISIT [f/FREQUENCY o/OCCURRENCE]`

* `INDEX` corresponds to the elderly’s index in the address book. It is a **strictly positive integer, and must be included.**
* The `VISIT` is in the format of `yyyy-mm-dd HH:mm`, and it must be included.
* The `FREQUENCY` and `OCCURRENCE` are optional parameters, and must both be included or excluded.
* `FREQUENCY` has to take on one of the following values: `Daily`, `Weekly`, `Biweekly`, `Monthly` and `Quarterly`.
* `OCCURRENCE` is a **strictly positive integer**.
* If there is already an existing visit scheduled for the elderly, this command will schedule a new visit that overwrites the existing one.

Examples:
* `visit 1 at/2021-12-31 17:00` schedules a meeting to the first elderly in the address book on 31th December 2021, 5PM.


### Marking one visit as done : `done`

Marks a scheduled visit to an elderly as done.

Format: `done INDEX`

* `INDEX` is the index of the elderly visited by the user. It is a **strictly positive integer, and must be included.** There must be a scheduled visit for the elderly before the visit can be marked as done.
* Once a scheduled visit is marked as done, it will update the last visited time of the elderly, and now there is no longer a scheduled next visit for the elderly.

Examples:
* `done 1` marks the visit to the first elderly as done, assuming there was a scheduled visit for the elderly before running this command.


### Viewing help : `help`

Show a message linking users to the user guide.

![help message](images/helpMessage.png)

Format: `help`

### Downloading data : `download`

Downloads elderly data onto user's computer.

Format: `download`


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`


### Exiting the program : `exit`

Exits the program.

Format: `exit`


### Saving the data

SeniorLove data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

SeniorLove data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SeniorLove will discard all data and start with an empty data file at the next run.
</div>


### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SeniorLove home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**add** | `add n/NAME p/PHONE_NUMBER l/LANGUAGE a/ADDRESS [lv/LAST_VISIT] [v/VISIT] [h/HEALTH_CONDITION]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 lv/1900-11-08 18:00 v/2021-11-08 17:00`
**delete** | `delete [v/] INDEX`<br> e.g., `delete 3` (delete the third senior) <br> e.g., `delete v/2` (delete the scheduled visit of the second senior)
**visit** | `visit INDEX at/VISIT [f/FREQUENCY o/OCCURRENCE]`<br> e.g.,`visit 3 at/1900-11-08 f/Weekly o/2`
**edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HEALTH_CONDITION]…​`<br> e.g.,`edit 3 n/James`
**find** | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find alex english`
**list** | `list`
**sort** | `sort [FIELD_TO_BE_SORTED]`<br> e.g., `sort v/`<br> e.g., `sort lv/`
**clear** | `clear`
**exit** | `exit`
**help** | `help`
