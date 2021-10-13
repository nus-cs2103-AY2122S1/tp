---
layout: page
title: User Guide
---

SafeFor(H)All is a **desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SafeFor(H)All can get your hall management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `safeforhall.jar` from [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ).

1. Copy the file to the folder you want to use as the _home folder_ for your SafeFor(H)All Application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`view`** : Lists all contacts.

   * **`add`**`n/John Doe r/A100 v/true f/SoC` : Adds a resident named `John Doe` to the application.

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
  e.g `view [INDEX]` can be used as `view` or as `view 100.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[c/CCA]…​` can be used as ` ` (i.e. 0 times), `c/Volleyball`, `c/Frisbee c/Hockey` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME f/FACULTY`, `f/FACULTY n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `v/true v/false` only `v/true` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Make changes ->
Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a resident’s information : `add`

Adds a resident and their information to the application. 

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROOM v/VACCINATION_STATUS f/FACULTY [c/CCA]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A resident can have any number of CCAs (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com r/A100 v/true f/SoC`
* `add n/Betsy Crowe c/Basketball e/betsyc@example.com v/false r/B400 p/1234567 c/Hockey f/FASS`

### Viewing residents’ information : `view`

Shows a numbered list of all the residents in the address book.

The optional argument [INDEX] will show the details of the
resident at the specified index.

The index of the resident is the corresponding number in the list
shown when `view` (without the [INDEX] parameter) is called.

Format: `view [INDEX]`
* For an index i, 1 &leq; i &leq; n, where n is the number of
residents in the address book

Examples:

* `view` shows a list of all the residents
* `view 30` shows the details of the resident at index 30

### Retrieve a list of residents with their fet due : `list`

Retrieve a list of residents whose ART collection or FET are due within the range of the current date and the optional given date.

Format: `list [FLAG] [d/DATE]`

* The flag can be either -f for fet or -c collection
* The date inputted has to be in `dd-mm-yyyy` format
* The given Date must be a date later than the current date

Examples:
* `list -f` retrieves a list of residents whose FET is due today
* `list -f 30-9-2021` retrieves a list of residents whose FET is due some day between today and `Sep 21 2021`
* `list -c` retrieves a list of residents whose FET is due today
* `list -c 30-9-2021` retrieves a list of residents whose ART Collection is due some day between today and `Sep 21 2021`

### Searching by resident information: `search`

Filters for residents by the provided keywords for each available parameter.

Format: `search n/KEYWORD [MORE_KEYWORDS] [FLAG/KEYWORD]...`

* The search is case-insensitive. e.g `hans` will match `Hans`, `True` will match `true`
* The order of the keywords provided for the name does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Residents matching at least one keyword for the name will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `search n/John` returns `john` and `John Doe`
* `search n/alex david v/true` returns vaccinated residents, `Alex Yeoh` and `David Li`
* `search v/false f/soc` returns un-vaccinated residents from SoC <br>

### Editing a person : `edit`

Edits an existing resident in the address book.

Format: `edit INDEX [MORE_INDICES] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/ROOM] [v/VACCINATION_STATUS] [f/FACULTY] [c/CCA]…​`

* Edits the resident at the specified `INDEX`. The index refers to the index number shown in the displayed resident list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing CCAs, the existing CCAs of the resident will be removed i.e adding of CCAs is not cumulative.
* You can remove all the resident’s CCAs by typing `c/` without specifying any CCAs after it.
* Able to edit multiple residents at once by inputting multiple indexes, each separated by a space.

Examples:
*  `edit 1 e/johndoe@example.com r/A101` Edits the email address and room number of the 1st person to be `johndoe@example.com` and `A101` respectively.
*  `edit 2 n/Betsy Crower c/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing CCAs.
*  `edit 1 2 3 v/true` Sets the vaccination status of the 1st, 2nd, and 3rd resident as vaccinated.

### Deleting a resident : `delete`

Deletes the specified resident from the address book.

Format: `delete INDEX [MORE_INDICES]`

* Deletes the resident at the specified `INDEX`.
* The index refers to the index number shown in the displayed resident list.
* The index **must be a positive integer** 1, 2, 3, …​
* Able to delete multiple residents at once by inputting multiple indexes, each separated by a space.

Examples:
* `view` followed by `delete 1 2 3` deletes the first 3 people in the address book.
* `search n/Anne` followed by `delete 1` deletes the 1st person named Anne in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** |  `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROOM v/VACCINATION_STATUS f/FACULTY [c/CCA]…​` <br> e.g. `add n/John Doe p/98765432 e/johnd@example.com r/A100 v/true f/SoC c/Frisbee`
**View** | `view [INDEX]` <br> e.g. `view 30`
**List** | `list [FLAG] [d/DATE]` <br> e.g. `list -f 15-8-2021`
**Search** | `search n/KEYWORD [MORE_KEYWORDS] [FLAG/KEYWORD]...` <br> e.g. `search n/john alex v/false f/fass` 
**Edit** | `edit INDEX [MORE_INDICES] [FLAG/UPDATED_PARTICULARS]...`<br> e.g., `edit 1 2 3 v/true`
**Delete** | `delete INDEX [MORE_INDICES]` <br> e.g. `delete 1 2 3`
**Help** | `help`
**Exit** | `exit`
