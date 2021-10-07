# PlannerMD User Guide

PlannerMD is a command-line application that helps clinic receptionists seamlessly integrate the daily appointments and the unique requirements of each patient into a single application. PlannerMD expedites the manual processes found in a clinic and saves clinics receptionists plenty of time while also reducing human error.

* [Quick start](#quick-start)
* [Features](#features)
* [FAQs](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `plannermd.jar` from [here](https://github.com/AY2122S1-CS2103T-T11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for plannerMD.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all patients and doctors.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 dob/25/12/2021` : Adds a patient named `John Doe` to the application.

   * **`edit`**`5 a/Bob street, block 123, #01-01 dob/25/12/2021` : Edits 5th patient's address.

   * **`delete`**`3` : Deletes the 3rd patient as shown in the current list.

   * **`clear`** : Deletes all patients.

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

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

---
### Managing Patients

### Adding a patient: `add`

Adds a patient to the patient records. (Note: for date of birth, PlannerMD will convert any value beyond the last valid day-of-month to be the last valid day-of-month.)

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS dob/DATE_OF_BIRTH [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 dob/20/07/1964`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Bukit Merah p/1234567 dob/20/07/1964 t/COVID`

### Listing all patients : `list`

Shows a list of all patients in the patient records.

Format: `list`

### Editing a patient's information : `edit`

Edits an existing patient in the patient records.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [dob/DATE_OF_BIRTH] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy dob/20/07/1964 Crower t/` Edits the name and date of birth of the 2nd patient to be `Betsy Crower` and `20/07/1964` respectively, and clears all existing tags.

### Adding a remark to a patient's information : `remark`

Adds a remark to an existing patient in the patient records.

Format: `remark INDEX r/REMARK`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​
* Existing remark will be updated to the input remark.

Examples:
*  `remark 1 r/` Edit the remark of the 1st patient to be blank.
*  `remark 2 r/Chronic diabetic, monthly insulin pick up` Edits the remark of the 2nd patient to be `Chronic diabetic, monthly insulin pick up`.

### Locating patients by name: `find`

Finds patients whose names contain any of the given keywords

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `john` will match `John`
* The order of the keywords does not matter. e.g. `John Lee` will match `Lee
  John`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John` will return `John Lee`, `John Tan`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a patient : `delete`

#### Using id: `id/` [coming soon]
Deletes a patient from the patient records using their unique ID.

Format: `delete id/ID`

* Deletes the patient with the specified `ID`.

Examples:
* `delete id/42` deletes patient with id 42
  <br/><br/>
#### Using index

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the patient records.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

---

### Managing Tags `tag`

### Add tags to a patient:

Adds a new tag to a patient’s record.

Format: `tag id/INDEX t/TAG`

* Adds a `TAG` to the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list.
* The index must be a **positive integer** 1, 2, 3, ...
* Tags must be alphanumeric. Whitespace and special characters are not allowed (eg. `t/covid!`, `t/covid 19` are invalid tags).
* Adding a tag will not overwrite existing tags.


Examples:
`tag id/1 t/Covid` tags patient with id 1 with a *Covid* tag.

### Delete a patient's tags : `tag -d`

Deletes a tag from a patient's record.

Format: `tag -d id/INDEX t/TAG`

* Deletes the `TAG` from the patient at the specified `INDEX`.
* The `TAG` must match one of the patient's existing tags.
* Deleting non-existing tags would not be allowed.


Examples:
`tag -d id/1 t/Covid` deletes the *Covid* tag from patient with id 1.

---

### Clearing all entries : `clear`

Clears all patient entries.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PlannerMD data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PlannerMD data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

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
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS dob/DATE_OF_BIRTH [t/TAG]…​` <br> e.g., `add n/James Ho p/98989898 e/jamesho@example.com a/123, Clementi Rd, 1234665 dob/20/07/1964 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [dob/DATE_OF_BIRTH] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Remark** | `remark INDEX r/REMARK`<br> e.g.,`remark 2 r/Chronic diabetic, monthly insulin pick up`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Add a Tag** | `tag id/INDEX t/TAG`<br> e.g, `tag id/1 t/Unvaccinated`
**Delete a Tag** | `tag -d id/INDEX t/TAG`<br> e.g, `tag -d id/1 t/Unvaccinated`
**List** | `list`
**Help** | `help`
