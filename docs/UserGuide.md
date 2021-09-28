---
User Guide
---

SportsPA is a desktop application used to manage membership and training sessions of NUS sports CCAs.

Table of Contents
* [Quick Start](#Quick start)
* [Feature](#Features)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SportsPA.jar` (coming soon).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listf`** : Lists all facilities.

   * **`addf`**`n/Court 1 l/University Sports Centre t/1700 c/10` : Adds the venue named `Court 1` to the facilities list.

   * **`deletem`**`3` : Deletes 3rd member in the members list.

   * **`clearm`** : Clears the members list

   * **`exit`** : Exits the app.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Characters with the slash symbols are used to indicate the type of parameter supplied by user.
  <br> eg. in `addm n/NAME p/PHONE_NUMBER`, `n/` and `p/` are the symbols used before entering a parameter
  for `NAME` and `PHONE_NUMBER` respectively.
  
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `findm KEYWORD`, `KEYWORD` is a parameter which can be used as `findm Ben`.

* Items in square brackets are optional.<br>
  e.g `n/NAME p/PHONE_NUMBER [d/DAYS]` can be used as `n/Ben p/91111111 d/Mon` or as `n/John p/91111111`.
  
* Items with …​ after them can be used any number of times, excluding zero.
  e.g `setm INDEX d/DAYS...` can be used as `setm 3 d/Mon`, `setm 3 d/Mon d/Tue`, etc.
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME l/LOCATION`, `l/LOCATION n/NAME` is also acceptable.
  
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Getting help: `help`

Shows message explaining how to access the help page.

Format: `help

### Adding a facility: `addf` [coming soon]

Adds a facility to the facility list.

Format: `addf n/NAME l/LOCATION t/TIME c/CAPACITY`

* TIME specifies the start time and is to be inputted in the format HH:MM
* CAPACITY specifies the maximum allowed people in the facility
* All fields are required

Examples:

* `addf n/Court 1 l/University Sports Hall t/1500 c/5` adds Court 1 at University Sports Hall at 3pm with a capacity of 5 
  
* `addf n/Court 2 l/University Sports Hall t/1500 c/4` adds Court 2 at University Sports Hall at 3pm with a capacity of 4

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add facility**| `addf n/NAME l/LOCATION t/TIME c/CAPACITY` <br> eg. `addf n/Court 1 l/University Sports Hall t/1500 c/5`
**Add member**| `addm n/NAME p/PHONE_NUMBER [d/DAYS]` <br> eg. `addm n/John Doe p/91111111`, `addm n/John Doe p/91111111 d/Mon`
**Clear facilities**|`clearf`
**Clear member**| `clearm`
**Delete facility**| `deletef INDEX` <br> eg. `deletef 4`
**Delete member**| `deletem INDEX` <br> eg. `deletem 1`
**Exit**| `exit`
**Find member**| `findm KEYWORD` <br> eg. `findm John`, `findm John Bob`
**Find facility**| `findf KEYWORD` <br> eg. `findf Court`, `findf Court Hall`
**Help**| `help`
**List members**| `listm`
**List facilities**| `listf`
**Set member availability**| `setm INDEX d/DAY...` <br> eg.`setm 3 d/Tue d/Wed`
**Split members**| `split d/DAY` <br> eg. `split d/Mon`
