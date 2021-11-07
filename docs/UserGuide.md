---
layout: page
title: User Guide
---

**SeniorLove** is a contact management desktop app which aims to facilitate community workers' visits to the elderly. It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SeniorLove can get your contact management tasks done faster than traditional GUI apps.

## Table of Contents
- [Quick start](#quick-start)
- [Glossary](#glossary)
  - [Terminology used](#terminology-used)
  - [Structure of an elderly contact](#structure-of-an-elderly-contact)
  - [Definition of Date Range](#definition-of-date-range)
- [Features](#features)
  - [Add elderly with contact details](#add-elderly-with-contact-details--add)
  - [Delete an elderly or corresponding visit](#delete-an-elderly-or-corresponding-visit--delete)
  - [Edit an elderly](#edit-an-elderly--edit)
  - [List all or selected elderly](#list-all-or-selected-elderly--list)
  - [Sort elderly by visit](#sort-elderly-by-visit--sort)
  - [Find elderly by attribute](#find-elderly-by-attribute--find)
  - [Schedule a visit to an elderly](#schedule-a-visit-to-an-elderly--visit)
  - [Mark one visit as done](#mark-one-visit-as-done--done)
  - [View summary statistics](#view-summary-statistics--summary)
  - [View help](#view-help--help)
  - [Download data](#download-data--download)
  - [Clear all entries](#clear-all-entries--clear)
  - [Exit the app](#exit-the-program--exit)
- [FAQ](#faq)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `seniorlove.jar` from [here](https://github.com/AY2122S1-CS2103-T14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SeniorLove.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:


   * `add n/John Doe p/98765432 l/Chinese a/John street, block 123, #01-01` : Adds an elderly contact named `John Doe` to the SeniorLove.

   * `delete 3` : Deletes the 3rd elderly shown in the current list.

   * `list n/` : Lists all elderly.

   * `find Hans` : Find all elderly whose attributes contain Hans.(Case insensitive)

   * `clear` : Deletes all elderly.

   * `exit` : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Glossary

This section provides information on commonly used terms throughout SeniorLove.

### Terminology used

Term            | Meaning
----------------|-----------------
Elderly | A person in SeniorLove
Attribute | Data that is assigned to an elderly
Visit | A visit from a social worker to an elderly


### Structure of an elderly contact

Category        | Specific flags | Details | Valid inputs | Requirement
----------------|-----------------|------------|----------|-----------------
Elderly personal attributes | 1. `n/`: Name <br><br> 2. `p/`: Phone number <br><br> 3. `l/`: Language <br><br> 4. `a/`: Address | 1. Elderly's name <br><br> 2. Elderly's contact number <br><br> 3. Elderly's preferred language <br><br> 4. Elderly's residential address | 1. Alphanumeric characters and spaces <br><br> 2. 8 digits, local (No country codes) <br><br> 3. Any string <br><br> 4. Any string | Compulsory
Elderly visit attributes | 1. `v/`: Visit <br><br> 2. `lv`: Last visit <br><br> 3. `f/`: Frequency <br><br> 4. `o/`: Occurrence | 1. Elderly's next visit <br><br> 2. Last time elderly was visited <br><br> 3. Frequency at which elderly is being visited (if recurring) <br><br> 4. Number of times elderly has to be visited (if recurring) | 1. Datetime formatted as `yyyy-MM-dd HH:mm` <br><br> 2. Datetime formatted as `yyyy-MM-dd HH:mm` <br><br> 3. One of the following enumerations: `Daily`, `Weekly`, `Biweekly`, `Monthly`, `Quarterly` <br><br> 4. Strictly positive integer and less than or equals to 999 | Optional

### Definition of Date Range

Date Range | Meaning
----------------|-----------------
Weekly | Every 7 days
Biweekly | Every 14 days
Monthly | Every 30 days
Quarterly | Every 90 days

--------------------------------------------------------------------------------------------------------------------
## Features
* Add elderly with contact details
* Delete an elderly or corresponding visit
* Edit an elderly
* List all or selected elderly
* Sort elderly by visit or last visit
* Find elderly by attribute
* Schedule a visit to an elderly
* Mark visits as done
* View summary statistics
* View help
* Download data
* Clear all entries

## Notes about command format:

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [h/HEALTH_CONDITION]` can be used as `n/John Doe h/dementia` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[h/HEALTH_CONDITION]…​` can be used as ` ` (i.e. 0 times), `h/dementia`, `h/dementia h/diabetes` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

<div markdown="block" class="alert alert-info">

**:exclamation: Extra input in a command? Take note!**<br>

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* If a parameter is expected but two consecutive flags are given (i.e. no spaces between two flags), only the first parameter will be taken.<br>
  e.g. if you specify `sort v/lv/`, only `v/` will be taken.
* 
* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Commands only check values associated to valid flags but not additional input other than valid flags.
</div>

### Add elderly with contact details : `add`

Adds an elderly and all relevant details into SeniorLove.

Format: `add n/NAME p/PHONE_NUMBER l/LANGUAGE a/ADDRESS [lv/LAST_VISIT] [v/VISIT] [f/FREQUENCY o/OCCURRENCE] [h/HEALTH_CONDITION]`

<div markdown="block" class="alert alert-info">
:bulb: What to include?
* Adds a new elderly with the following information: `NAME`, `PHONE_NUMBER`, `LANGUAGE`, `ADDRESS`.
* `LAST_VISIT`, `VISIT` and `HEALTH_CONDITION` may be optionally included.
* `PHONE_NUMBER` must be a 8 digits numeric value for local phone number, with no country codes.
* `FREQUENCY` and `OCCURRENCE` must both be included or excluded.
* `VISIT` must be included for `FREQUENCY` and `OCCURRENCE` to be included.
* A warning message will be shown if the new `VISIT` datetime entered is in the past.
* A warning message will be shown if the new `LAST_VISIT` datetime entered is in the future.
* A detailed breakdown of the terms being used can be found [here](#structure-of-an-elderly-contact).
</div>

<div markdown="block" class="alert alert-info">
:information_source: Do not specify duplicate flags!
* If you specify duplicate flags, the program will take the last one out of the duplicates.
</div>

Examples:
* `add n/John p/12345678 l/English a/College Avenue East 18, New College`
  > Adds an elderly and details without optional flags.
* `add n/Jane p/54867392 l/Chinese a/200 Toa Payoh Avenue 56  lv/2021-09-30 10:00 v/2021-10-31 16:00 f/weekly o/3 h/dementia`
  > Adds an elderly and details with `LAST_VISIT`, `VISIT`, `HEALTH_CONDITION`, `FREQUENCY` and `OCCURRENCE`.


### Delete an elderly or corresponding visit : `delete`

Deletes either an elderly’s details or the corresponding elderly’s next visit at list index `INDEX` from SeniorLove.

Format: `delete [v/] INDEX`

<div markdown="block" class="alert alert-info">
:exclamation: Take note!
* `INDEX` is a **strictly positive integer, and must be included.**
</div>

<div markdown="block" class="alert alert-info">
:information_source: How do I know if I am deleting the visit or not?
* `v/` denotes if the item to be deleted is an elderly’s details or the elderly’s corresponding visit. **This is optional to include.** The presence of v/ indicates that it is the elderly’s visit that is to be deleted, while its exclusion indicates that it is the elderly’s details that is to be deleted.
</div>


Examples:
* `delete 1`
  > Deletes the elderly’s details of the elderly at list index 1.
* `delete v/ 1`
  > Deletes the elderly’s visit of the elderly at list index 1.


### Edit an elderly : `edit`

Edits an existing elderly's attributes in SeniorLove.

Format: `edit INDEX [n/NAME] [p/PHONE] [l/LANGUAGE] [a/ADDRESS] [lv/LAST_VISIT] [h/HEALTH_CONDITION]…​`

* Edits the elderly at the specified `INDEX`. The index refers to the index number shown in the displayed elderly list. The index **must be a positive integer**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* A warning message will be shown if the new `VISIT` datetime entered is in the past.
* A warning message will be shown if the new `LAST_VISIT` datetime entered is in the future.


<div markdown="block" class="alert alert-info">
:bulb: Tips
* You can remove all the elderly’s health conditions by typing `h/` without
    specifying any health conditions after it.
* You can remove the elderly's last visit by typing `lv/` without specifying any datetime after it.
* A detailed breakdown of the terms being used can be found [here](#structure-of-an-elderly-contact).
</div>

<div markdown="block" class="alert alert-info">
:exclamation: Take note!
* When editing health conditions, the existing health conditions of the elderly will be removed i.e adding of health conditions is not cumulative.
</div>

Examples:
*  `edit 1 p/91234567 l/English`
   > Edits the phone number and language of the 1st elderly to be `91234567` and `English` respectively. All other attributes are not modified.
*  `edit 2 n/Betsy Crower h/`
   > Edits the name of the 2nd elderly to be `Betsy Crower` and clears all existing health conditions. All other attributes are not modified.

### List all or selected elderly : `list`

Shows a list of all or selected elderly and their associated details in SeniorLove address book in alphabetical order of their name.

Format: `list CRITERION_FOR_LISTING`

* `list all/` displays all the elderly in the alphabetical order of their names.
* `list w/` displays all the elderly with an incoming visit in the next week.
* `list m/` displays all the elderly with an incoming visit in the next month.

<div markdown="block" class="alert alert-info">
:exclamation: Take note!
* Exactly one of the `all/`, `w/` and `m/` fields need to be present.
</div>

Examples:
* `list all/`
  > Displays all the elderly.
* `list m/`
  > Displays all the elderly with an incoming visit in the next month.
* `list w/`
  > Displays all elderly with an in

### Sort elderly by visit : `sort`

Shows a sorted list of elderly and their associated details in SeniorLove either by `last visit` date (the latest first) or by next `visit` date (the earliest first).

Format: `sort [FIELD_TO_BE_SORTED]`

* `sort lv/` sorts elderly in descending order of their `last visit` date (the latest first).
* `sort v/` sorts elderly in ascending order of their next `visit` date (the earliest first).
* A detailed breakdown of the terms being used can be found [here](#structure-of-an-elderly-contact).

<div markdown="block" class="alert alert-info">
:exclamation: Take note!
* There should be exactly one `FIELD_TO_BE_SORTED` at any time.
* `sort` acts on the list currently being displayed.
* Currently, `sort` only supports flags of `last visit` or `visit`.
</div>

Example:
* `sort lv/`
  > Sorts the elderly list in descending order of `last visit` date.
* `sort v/`
  > Sorts the elderly list in ascending order of `visit` date.

### Find elderly by attribute : `find`

Finds elderly whose names contain any of the given keywords. All elderly attributes are searched using the giving [format](#structure-of-an-elderly-contact).

Format: `find KEYWORD [MORE_KEYWORDS]`

<div markdown="block" class="alert alert-info">
:exclamation: Take note!
* The search is case-insensitive. e.g `hans` will match `Hans`
* Partial words will be matched, and will return any elderly who has the partial word as a substring of any attribute. e.g. `Han` will match `Hans Bo` and `Rohan Tan`
* Elderly matching all given keywords will be returned (i.e. `AND` search).
  e.g. `Hans English` will return `Hans Gruber`, `Hanson Lim`, both of which have `LANGUAGE` English
</div>

Examples:
* `find John`
  > Returns `john` and `John Doe`
* `find alex English`
  > Returns `Alex Yeoh`, `Alex Lim`, both with `LANGUAGE` English.<br>


### Schedule a visit to an elderly : `visit`

Updates the time for the next visit to the elderly with the given index of the elderly in the address book.

Format: `visit INDEX  at/VISIT [f/FREQUENCY o/OCCURRENCE]`

* `INDEX` corresponds to the elderly’s index in the address book. It is a **strictly positive integer, and must be included.**
* The `VISIT` is in the format of `yyyy-mm-dd HH:mm`, and it must be included.
* The `FREQUENCY` and `OCCURRENCE` are optional parameters, and must both be included or excluded.
* A warning message will be shown if the new `VISIT` datetime entered is in the past.

<div markdown="block" class="alert alert-info">
:information_source: How do I know if I am deleting the visit or not?

* If there is already a next visit scheduled for the elderly, this command will schedule a new visit that overwrites the existing one.
* A detailed breakdown of the terms being used can be found [here](#structure-of-an-elderly-contact).
</div>

Examples:
* `visit 1 at/2021-12-31 17:00`
  > Schedules a meeting to the first elderly in the address book on 31th December 2021, 5PM.
* `visit 1 at/2021-12-31 17:00 f/weekly o/3`
  > Schedules a recurring weekly meeting for 3 times to the first elderly in the address book on 31th December 2021, 5PM.


### Mark one visit as done : `done`

Marks a next visit to an elderly as done.

Format: `done INDEX`

* `INDEX` is the index of the elderly visited by the user. It is a **strictly positive integer, and must be included.**
  There must be a next visit for the elderly before the visit can be marked as done.

<div markdown="block" class="alert alert-info">
:information_source: Where does the marked visit go?
* Once a scheduled visit is marked as done, it will update the last visited time of the elderly. If the visit is not recurring or is on its last occurrence, there will be no next visit. Otherwise, the next visit will be updated to reflect the next visit date.
</div>

Example:
* `done 1`
  > Marks the visit to the first elderly as done, assuming there was a scheduled visit for the elderly before running this command.


### View summary statistics : `summary`

Shows a summary of the overdue visits, upcoming visits and recent last visits statistics.

Format: `summary`

### View help : `help`

Show a message linking users to the user guide.

![help message](images/helpMessage.png)

Format: `help`

### Download data : `download`

Downloads elderly data onto user's computer.

Format: `download`

<div markdown="block" class="alert alert-info">
:information_source: What happens if `addressbook.json` is corrupted?

* If `addressbook.json` is corrupted (invalid format), attempting to download would result in a file that is wrong being downloaded.
</div>


### Clear all entries : `clear`

Clears all entries from the address book.

Format: `clear`

<div markdown="block" class="alert alert-info">
:exclamation: Be careful!
</div>

### Exit the program : `exit`

Exits the program.

Format: `exit`


### Save the data

SeniorLove data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Edit the data file

SeniorLove data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file make its format invalid, SeniorLove will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SeniorLove home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**add** | `add n/NAME p/PHONE_NUMBER l/LANGUAGE a/ADDRESS [lv/LAST_VISIT] [v/VISIT] [f/FREQUENCY o/OCCURRENCE] [h/HEALTH_CONDITION]…​` <br> e.g., `add n/James Ho p/22224444 l/english a/123, Clementi Rd, 1234665 lv/1900-11-08 18:00 v/2021-11-08 17:00`
**delete** | `delete [v/] INDEX`<br> e.g., `delete 3` (delete the third elderly) <br> e.g., `delete v/2` (delete the scheduled visit of the second elderly)
**visit** | `visit INDEX at/VISIT [f/FREQUENCY o/OCCURRENCE]`<br> e.g.,`visit 3 at/2021-11-12 16:30 f/Weekly o/2`
**edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HEALTH_CONDITION]…​`<br> e.g.,`edit 3 n/James`
**find** | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find alex english`
**list** | `list [CRITERION_FOR_LISTING]` <br> e.g., `list all/`<br> e.g., `list w/`<br> e.g., `list m/`
**sort** | `sort [FIELD_TO_BE_SORTED]`<br> e.g., `sort v/`<br> e.g., `sort lv/`
**summary** | `summary`
**clear** | `clear`
**exit** | `exit`
**help** | `help`
**download** | `download`
