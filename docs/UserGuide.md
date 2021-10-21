---
layout: page
title: User Guide
---
Are you a hall administrator in-charge of managing residents in NUS? Do you have to fumble through multiple different excel sheets just to look for a resident's information? Are you worried residents might be subject to the dangers of the pandemic? Well look no further as SafeFor(H)All is the application you need!

SafeFor(H)All is a **desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SafeFor(H)All can get your hall management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `safeforhall.jar` from [here](https://github.com/AY2122S1-CS2103T-T15-4/tp/releases/tag/v1.3.trial).

1. Copy the file to the folder you want to use as the _home folder_ for your SafeFor(H)All Application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`view`** : Lists all residents.

   * **`add`**`n/John Doe r/A100 v/t f/SoC` : Adds a resident named `John Doe` with the given information to the application.

   * **`delete`**`3` : Deletes the 3rd resident shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `add n/NAME [fd/LAST_FET_DATE]` can be used as `add n/John` or as `add n/John fd/09-09-2021`.

* An ellipsis (...) implies multiple of that parameter can be provided.<br>
  e.g `edit INDEX...` can be used as `edit 1` or as `edit 1 2 3 5 8`.
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME f/FACULTY`, `f/FACULTY n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `v/t v/f` only `v/t` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit`, `view`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* [TODO] INCLUDE A DESCRIPTION ON DATE FORMATS

</div>

### For Residents

These commands will function as specified when run under the `Resident` tab.

#### Adding a resident’s information : `add`

Adds a resident and their information to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROOM v/VACCINATION_STATUS f/FACULTY [fd/LAST_FET_DATE] [cd/LAST_COLLECTION_DATE]`

* `NAME` has to be unique
* `ROOM` is of the form `BLOCK` + `LEVEL` + `NUMBER`, where `BLOCK` is an alphabet, `LEVEL` and `NUMBER` make up 3 digits. e.g. `A100`
* `VACCINATION_STATUS` can be `T` or `F` (case insensitive)
* `FACULTY` has to be a single alphabetical word
* The dates inputted for `LAST_FET_DATE` and `LAST_COLLECTION_DATE` has to be in dd-mm-yyyy format

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com r/A100 v/t f/SoC`
* `add n/Betsy Crowe e/betsyc@example.com v/F r/B400 p/1234567 f/FASS fd/20-10-2021 cd/23-10-2021`

#### Viewing residents’ information : `view`

Shows a numbered list of all the residents in the address book.

The index of the resident is the corresponding number in the list
shown when `view` (without the [INDEX] parameter) is called.

Format: `view [INDEX]`
* For an index i, 1 &leq; i &leq; n, where n is the number of residents in the address book

Examples:

* `view` shows a list of all the residents
* `view 30` shows the details of the resident at index 30

#### Listing residents by fet/collection deadlines : `list`

Lists residents whose ART collection or FET tests are due within the range of the given date or the range of the 2 dates given.

Format: `list k/KEYWORD d1/DATE1 d2/DATE2` or `list k/LATE_KEYWORD d1/DATE1`

* Normal keywords are `f` for fet and `c` for collection
* Late keywords are `lf` for late fet and `lc` for late collection
* The date inputted has to be in `dd-mm-yyyy` format  
* When a normal keyword is given, both date1 and date2 have to be inputted
* The given Date2 must be a date later than the given Date1
* `date1` is the start date and `date2` is the last date inclusive  
* When a late keyword is given, only date1 should be given
* Anyone whose fet and collection is due before but not on `date1` is outputted  

Examples:
* `list k/f d1/10-10-2021 d2/12-10-2021` retrieves a list of residents whose `FET` is due between `10 Oct 2021` and `12 Oct 2021`, inclusive
* `list k/f d1/15-10-2021 d2/20-10-2021` retrieves a list of residents whose `Test Kit Collection` is due some day between `15 Oct 2021` and `20 Oct 2021`, inclusive
* `list k/lf d1/11-10-2021` retrieves a list of residents whose `FET` is due before `11 Oct 2021`
* `list k/lc d1/12-10-2021` retrieves a list of residents whose `Test Kit Collection` is due before `12 Oct 2021`

#### Searching by resident information: `search`

Shows a list of residents that match the provided keywords for different available parameters.

Format: `find [PREFIX/KEYWORD]...`

* Allowed flags include; `n/`, `r/`, `e`, `p/`, `f/` and `v/`
* Flags for `LAST_FET_DATE` and `LAST_COLLECTION_DATE` are not used. Refer to [List Command](#Listing residents by fet/collection deadlines) on how to make use of these fields.
* Notes on searching by name:
    - It is case-insensitive. e.g `hans` will match `Hans`, `True` will match `true`
    - The order of the keywords provided for the name does not matter. e.g `Hans Bo` will match `Bo Hans`
    - Only full words will be matched e.g `Han` will not match `Hans`
    - Residents matching at least one keyword for the name will be returned (i.e. `OR` search).
  e.g `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* Notes on searching by room:
    - A block can be used as a search. e.g `r/A`
    - A level can be used as a search. e.g `r/2`
    - A block-level can be used as a search. e.g `r/A2`
    - A full valid room can be used as a search. e.g `r/A210`
* All other fields are subject to the same validity conditions as in the [Add Command](#Adding a resident's information)

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david v/true` returns vaccinated residents, `Alex Yeoh` and `David Li`
* `find v/false f/soc` returns un-vaccinated residents from SoC <br>

#### Editing a resident : `edit`

Edits the details of existing residents in the address book.

Format: `edit INDEX… [n/NAME] [r/ROOM] [p/PHONE] [e/EMAIL] [v/VACCINATION_STATUS] [f/FACULTY] [fd/LAST_FET_DATE] [cd/LAST_COLLECTION_DATE]`

* Edit the residents at the specified `INDEXES`. 
* Each index refers to the index number shown in the displayed resident list. 
* The indexes **must be positive integers** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Edit multiple residents in a single command by inputting multiple indexes, each separated by a space.

Examples:
*  `edit 1 e/johndoe@example.com r/A101` Edits the email address and room number of the 1st resident to be `johndoe@example.com` and `A101` respectively.
*  `edit 1 2 3 v/true fd/20-10-2021` Sets the vaccination status of the 1st, 2nd, and 3rd resident as vaccinated, and sets their last FET dates to 20-10-2021.

#### Deleting a resident : `delete`

Deletes specified residents from the address book.

Format: `delete INDEX…`

* Delete the residents at the specified `INDEXES`.
* Each index refers to the index number shown in the displayed resident list.
* The indexes **must be positive integers** 1, 2, 3, …​
* Delete multiple residents in a single command by inputting multiple indexes, each separated by a space.

Examples:
* `view` followed by `delete 1 2 3` deletes the first 3 residents in the address book.
* `find n/Anne` followed by `delete 1` deletes the 1st resident named Anne in the results of the `find` command.

### For Events

These commands will function as specified when run under the `Event` tab.

#### Adding an event : `add`

Adds a new event to the address book.

Format: `add n/EVENT_NAME v/VENUE c/CAPACITY d/DATE [r/RESIDENTS]`

[TODO]
* The combination of the 4 required parameters should be unique
* `FACULTY` has to be a single alphabetical word
* `FACULTY` has to be a single alphabetical word

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com r/A100 v/t f/SoC`
* `add n/Betsy Crowe e/betsyc@example.com v/F r/B400 p/1234567 f/FASS fd/20-10-2021 cd/23-10-2021`

#### Editing an event : `edit`

Edits an existing event in the address book.

Format: `edit INDEX [n/EVENT_NAME] [d/EVENT_DATE] [l/VENUE] [c/CAPACITY]`

* Edits the event at the specified `INDEX`.
* The index refers to the index number shown in the displayed event list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 n/Football Training l/Field c/50` Edits the name, venue, and capacity of the 1st event in the event list to be `Football Training`, `Field`, and `50` respectively.

#### Add residents to an Event: `include`

Add multiple residents to an event based on the information given(name or room number), a resident is only expected to be given one piece of information.

Format: `include INDEX r/INFORMATION [, MORE INFORMATION]`

* Resident information can be given in the form of name or room, but all has to be all rooms or all names
* When adding multiple residents, each piece of information is separated by a comma
* The information inputted is case-insensitive
* If one or more of the given information is invalid, an error message is outputted and none of the residents are added to the event

Examples:
* `include 1 r/A101` adds the resident who stays in room A101 to the first event in the address book
* `include 2 r/A101, A102, A103` adds the residents who stay in rooms A101, A102 and A103 to the second event in the address book
* `include 3 r/John Doe` adds John Doe to the third event in the address book
* `include 4 r/John Doe, Jane Doe` adds John Doe and Jane Doe to the fourth event in the address book

### Commons

These commands will function the same in either tab.

#### Viewing help : `help`

Provides the link for the user to reach this online user guide.

![help message](images/helpMessage.png)

Format: `help`

#### History

The `up` and `down` arrow keys when used with the input box in focus, allows traversal of past input commands to increase of use and efficiency.

#### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** |  `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROOM v/VACCINATION_STATUS f/FACULTY [fd/LAST_FET_DATE] [cd/LAST_COLLECTION_DATE]` <br> e.g. `add n/Betsy Crowe e/betsyc@example.com v/F r/B400 p/1234567 f/FASS fd/20-10-2021 cd/23-10-2021`
**View** | `view [INDEX]` <br> e.g. `view 30`
**List** | `list k/KEYWORD d1/DATE1 d2/DATE` <br> e.g. `list k/f 15-8-2021 20-08-2021`
**Find** | `find [PREFIX/KEYWORD]...` <br> e.g. `find n/john alex v/false f/fass` 
**Edit** | **Resident:** <br> `edit INDEX… [FLAG/UPDATED_PARTICULARS]…`<br> e.g., `edit 1 2 3 v/true fd/20-10-2021` <br><br> **Event:** <br> `edit INDEX [FLAG/UPDATED_PARTICULARS]…`<br> e.g., `edit 1 n/Football Training l/Field`
**Delete** | **Resident:** <br> `delete INDEX…` <br> e.g. `delete 1 2 3`
**Include** | `include INDEX r/INFORMATION [,MORE_INFORMATION]` <br> e.g. `include 1 r/A102, E416`
**Help** | `help`
**Exit** | `exit`
