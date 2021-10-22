---
layout: page
title: User Guide
---

Track2Gather is a **desktop app for contact tracing personnel at the [Ministry of Health (MOH)](https://www.moh.gov.sg/), optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `track2gather.jar` from [here](https://github.com/AY2122S1-CS2103-W14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Track2Gather.

4. To start the app, double-click the file or enter `java -jar track2gather.jar` into the terminal in the _home folder_.

5. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`add`** : Adds a person to the persons list.

    * **`delete`** : Deletes a person at the specified index.

    * **`edit`** : Edit an existing person’s details at the specified index.
   
    * **`find`** : Find a person by name.

    * **`tshift`** : Shifts all person's SHN end dates by the specified number of days.
   
    * **`list`** : Shows a list of all persons.

    * **`clear`** : Deletes all persons with SHN periods that are completed at time of command call.
    
    * **`help`** : Shows a message explaining how to access the help page.

    * **`exit`** : Exits the app.

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [wa/WORK_ADDRESS]` can be used as `n/John Doe wa/Home` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a person: `add`

Adds a person to the persons list for tracking.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL cn/CASE_NUMBER ha/HOME_ADDRESS [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`
* There cannot be multiple persons with the same name (case-sensitive)

Examples:
* `add n/Alex p/98765432 e/alex@email.com cn/600204 ha/123 Orchard Road #01-100 800123`
* `add n/Jane p/12345678 e/jane@email.com cn/600204 ha/123 Changi Road #01-100 700123 wa/50 Jurong Road 120050 qa/12 Harbourfront Ring 123012 sh/2021-01-01 2021-01-14 kn/Peter kp/90011234 ka/73 Yishun Drive #10-301 310073`

### Editing a person : `edit`

Edits the details of the person identified by the index number used in the displayed person list. Existing values will be overwritten by the input values.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [cn/CASE_NUMBER] [ha/HOME_ADDRESS]`
* Edits the person at the specified `INDEX`
* The index **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index **must not exceed the total number of persons** in the persons list
* There cannot be multiple persons with the same name (case-sensitive)
* At least one field to edit must be provided
* Editing optional fields, coming soon in v1.3

Examples:
* `edit 1 n/Henry Hugh` edits the name of the first person in the list to be `Henry Hugh`

### Finding persons by name: `find`

Finds all persons whose names contain any of the specified keywords and displays them as a list with index numbers.

Format: `find KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

### Finding persons by name: `tshift`

Shifts all person's SHN end dates by the specified number of days.

Format: `tshift [PLUS_MINUS_SIGN]DAYS`
* `DAYS` should be a number between `1` and `90` inclusive
* Postpones the SHN end date if the evaluated value is positive
* Brings forward the SHN end date if the value is negative
* The SHN end dates will only be brought forward up to and including a day after the person's SHN start date

Examples:
* `tshift 3` postpones all SHN end dates by 3 days. This is identical to `tshift +3`
* `tshift -3` brings forward all SHN end dates by up to 3 days

### Deleting a person : `delete`

Deletes the person(s) identified by the specified index number(s) used in the displayed persons list.

Format: `delete INDEX [MORE_INDICES]`

* Deletes the contact(s) at the specified `INDEX`(s).
* The index(s) **must be a positive integer** (e.g. 1, 2, 3, ..)
* The index(s) **must be given in ascending order** (e.g. `delete 1 4 5`)
* The index(s) **must not exceed the total number of contacts** in the address book

Examples:
* `sort` followed by `delete 2` deletes the 2nd person in the contacts list when sorted by name. 
* `sort` followed by `delete 1 4 5` deletes the 1st, 4th and 5th persons in the contacts list when sorted by name.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command, if any.

### Listing all persons : `list`

Shows a list of all persons.

Format: `list`

### Clearing all persons : `clear`

Deletes all persons with SHN periods that are completed at time of command call from the app.

Format: `clear`

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Track2Gather data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Track2Gather data are saved as JSON files `[JAR file location]/data/track2gather.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Track2Gather will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming soon! 

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL cn/CASE_NUMBER ha/HOME_ADDRESS [wa/WORK_ADDRESS] [qa/QUARANTINE_ADDRESS] [sh/ADD_SHN_PERIOD] [kn/NEXT_OF_KIN_NAME] [kp/NEXT_OF_KIN_PHONE] [ka/NEXT_OF_KIN_ADDRESS]`<br> e.g., `add n/Jane p/12345678 e/jane@email.com cn/600204 ha/123 Changi Road #01-100 700123 wa/50 Jurong Road 120050 qa/12 Harbourfront Ring 123012 sh/2021-01-01 2021-01-14 kn/Peter kp/90011234 ka/73 Yishun Drive #10-301 310073`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [cn/CASE_NUMBER] [ha/HOME_ADDRESS]`<br> e.g., `edit 1 n/Henry Hugh`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**TShift** | `tshift [PLUS_MINUS_SIGN]DAYS`<br> e.g., `tshift 3`
**Delete** | `delete [INDEX] [MORE_INDICES]`<br> e.g., `delete 3` `delete 1 4`
**List** | `list`
**Clear** | `clear`
**Help** | `help`
**Exit** | `exit`
