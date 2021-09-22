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

   * **`add`**`n/John Doe r/E401 v/true f/SoC` : Adds a resident named `John Doe` to the application.

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
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

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

Make changes ->
Adds a resident to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A resident can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

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

### Updating FET date : `fet`

Updates the last done FET date for all residents.

Format: `fet d/DATE`

* The date inputted has to be in `dd-mm-yyyy` format
* Date will be stored in a `MMM dd yyyy` format, e.g., (`Sep 21 2021`)
* The last FET date of all residents will be updated by this command

Examples:
* `fet 15-8-2021` sets the FET date of all residents to `Aug 15 2021`

### Searching by resident information: `search`

Finds residents whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Residents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a resident : `delete`

Make changes ->
Deletes the specified resident from the address book.

Format: `delete INDEX`

* Deletes the resident at the specified `INDEX`.
* The index refers to the index number shown in the displayed resident list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd resident in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st resident in the results of the `find` command.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** |  
**Fet** | 
**View** |
**Search** |
**Delete** | 
**Help** | `help`
**Exit** | `exit`
