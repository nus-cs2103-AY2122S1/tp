---
layout: page
title: User Guide
---

MrTechRecruiter (MTR) is a **desktop app for managing job applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MTR can get your application management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start (coming soon)

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `mrtechrecruiter.jar` from [here](https://github.com/AY2122S1-CS2103-F10-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for MrTechRecruiter.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list-applicant`** : Lists all applicants.

   * **`add-applicant`**`n/Mary Ann p/98765432 e/johnd@example.com a/Mary street, block 123, #01-01 pos/software engineer github/https://github.com/matoledo` : Adds an applicant named `Mary Ann` to the `software engineer` position.

   * **`delete-applicant`**`1` : Deletes the 1st applicant shown in the current list.
   
   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Adding a position: `add-position`

Adds a position to MrTechRecruiter.

Format: `add-position tit/TITLE des/DESCRIPTION`

Examples:
* `add-position tit/software engineer des/work in a team that builds a facial recognition application`


### Editing a position: `edit-position`
Edits the specified position in MrTechRecruiter.

Format: `edit-position INDEX tit/NEWTITLE des/NEWDESCRIPTION`

* Edit the position with the specified `INDEX`

Examples: 
* `edit-position INDEX tit/Algorithm Engineer des/embed algorithms into the facial recognition application `


### Deleting a position: `delete-position`

Deletes the specified position from MrTechRecruiter.

Format: `delete-position INDEX`

* Deletes the position with the specified `INDEX`.

Examples:
* `delete-position 1` deletes the position with index 1.


### Adding an applicant: `add-applicant`

Adds an applicant to MrTechRecruiter.

Format: `add-applicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/POSITION github/GITHUB_PROFILE_LINK`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant should only have 1 position. This position must exist, otherwise an error message will show.<br>
If the job position exists, its rejection rate will update accordingly.
</div>

Examples:
* `add-applicant n/Mary Ann p/98765432 e/johnd@example.com a/Mary street, block 123, #01-01 pos/software engineer github/https://github.com/matoledo`

### Editing an applicant: `edit-applicant`
Edits the specified applicant in MrTechRecruiter

Format: `edit-applicant INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/POSITION github/GITHUB_PROFILE_LINK` 

* Edit the applicant with the specified `INDEX`
* The `position` must have been added to MrTechRecruiter

Examples:
* `edit-applicant n/Jasmine Doe p/98761432 e/johnd@example.com`
* `edit-applicant n/Betsy p/1234567 pos/database administrator`

### Deleting an applicant: `delete-applicant`

Deletes the specified applicant from MrTechRecruiter.

Format: `delete-applicant INDEX`

* Deletes the applicant with the specified `INDEX`.

Examples:
* `delete-applicant 1` deletes the applicant with index 1.


### Listing all positions : `list position` [coming soon]

Shows a list of all positions in MrTechRecruiter.

Format: `list position`

### Listing all applicants : `list-applicant`

Shows a list of all applicants in MrTechRecruiter.

Format: `list-applicant`

### Searching for applicants using keywords: `find-applicant`

Finds all applicants whose name match the specified search terms.

Format: `find-applicant KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find-applicant John` returns `john` and `John Doe`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

All data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Data is saved as a JSON file `[JAR file location]/data/applicantbook.json` and `[JAR file location]/data/positionbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MrTechRecruiter will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MrTechRecruiter home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Applicant** | `add-applicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/POSITION github/GITHUB_PROFILE_LINK` <br> e.g., `add-applicant n/Mary Ann p/98765432 e/johnd@example.com a/Mary street, block 123, #01-01 pos/software engineer github/https://github.com/matoledo`
**Delete Applicant** | `delete-applicant INDEX`<br> e.g., `delete-applicant 3`
**Find Applicant** | `find-applicant KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List Applicants** | `list-applicant`
**Help** | `help`
