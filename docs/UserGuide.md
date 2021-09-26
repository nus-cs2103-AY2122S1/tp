---
layout: page
title: User Guide
---

# User Guide

RecruitIn is a desktop app for recruiters in Singapore to keep track of the plethora of clients with different skill sets, availability and experience. It is optimized for quick text-based inputs via a Command Line Interface (CLI) while still having the ease of use of a Graphical User Interface (GUI). This product will make recruiters’ lives easier through categorisation and filter features to easily access candidates they have in mind.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `RecruitIn.jar` from here.

1. Copy the file to the folder you want to use as the _home folder_.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list employer`** : Lists all employers.

   * **`add applicant`**`n/John Doe p/98765432 e/johnd@example.com s/Finance` : Adds an applicant named `John Doe` to the Address Book.

   * **`delete`**`applicant/3` : Deletes the 3rd applicant shown in the list of all applicants.

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an applicant/employer: `add`

Adds an applicant to the address book.

Format: `add c/applicant n/NAME p/CONTACT_NUMBER e/EMAIL_ADDRESS s/PREFERRED_SECTOR [t/TAG]​`

Adds an employer to the address book.

Format: `add c/employer n/NAME p/CONTACT_NUMBER e/EMAIL_ADDRESS s/PREFERRED_SECTOR [t/TAG]​`

Examples:
* `add c/applicant n/Bob p/87654321 e/bob@gmail.com s/Software_Engineering`
* `add c/employer n/Cat p/81234567 e/cat@gmail.com s/Financial_Services`

### Listing all applicants/employers : `list`

Shows a list of all applicants in the address book.

Format: `list c/applicant`

Shows a list of all employers in the address book.

Format: `list c/employer`

### Deleting an applicant/employer : `delete`

Deletes a specific applicant and/or employer by index from the address book.

Format: `delete c/applicant INDEX`

* Delete command can handle either prefix `c/applicant` or `c/employer`.
* The index given for `INDEX` **must be a positive integer** 1, 2, 3, …​
* The index given for `INDEX` uses **1-based indexing**.
* Prefix `c/applicant` and Value `INDEX`:
  * Deletes an applicant at the specified `INDEX` in the list of all applicants.
  * `INDEX` refers to the index number of the applicant to be deleted in the list of all applicants.
  * `INDEX` should not exceed the total applicant count.
* Prefix `c/employer` and Value `INDEX`:
  * Deletes an employer at the specified `INDEX` in the list of all employers.
  * `INDEX` refers to the index number of the applicant to be deleted in the list of all applicants.
  * `INDEX` should not exceed the total employer count.

Examples:
* `delete c/applicant 2` deletes the 2nd applicant in the list of all applicants.
* `delete c/employer 3` deletes the 3rd employer in the list of all employers.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ReceruitIn data is saved to the hard disk whenever there is a command that edits, updates or adds data.
There is no need to save data manually with a command. Data also automatically loads when the 
application runs.


### Editing the data file

RecruitIn data is saved as a String in `/data/applicants.json` for applicant data.
Advanced users are welcome to update data directly by editing that data file.

Example of format of data for one applicant in applicants:

```JSON
"applicants" : [ {
 "name" : "Alice Yeoh",
 "phone" : "87438807",
 "email" : "alexyeoh@example.com",
 "preferred sector" : "Software Engineering",
 "tagged" : [ ]
}]
```
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RecruitIn will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RecruitIn home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add c/applicant n/NAME p/CONTACT_NUMBER e/EMAIL s/PREFERRED_SECTOR [t/TAG]…​` <br> e.g., `add c/applicant n/James Ho p/22224444 e/jamesho@example.com s/Software Engineering t/junior dev`
**Delete** | `delete c/applicant INDEX`<br> e.g., `delete c/applicant 3`
**List** | `list c/applicant`
**Help** | `help`
