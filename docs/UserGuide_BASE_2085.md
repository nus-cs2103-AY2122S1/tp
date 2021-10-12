---
layout: page
title: User Guide
---

Insurance Pal is a client management software specially designed to help manage the logistics of being an insurance agent.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `InsurancePal.jar` from [here](https://github.com/AY2122S1-CS2103T-T17-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your InsurancePal.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the address book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

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

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [i/INSURANCE]…​`

* `INSURANCE` is limited to the following, with any capitalization: `life`, `health`, `general`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal i/life`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [i/INSURANCE]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `INSURANCE` is limited to the following, with any capitalization: `life`, `health`, `general`
* When editing tags or insurances, the existing tags/insurances of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags/insurances by typing `t/`/`i/` without
    specifying any tags/insurances after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/ i/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags and insurances.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Adding a claim: `claim`
Adds a insurance claim to an existing person in the address book

Format: `claim INDEX t/TITLE d/DESCRIPTION s/STATUS`
* Adds an insurance claim to the person specified at `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …
* `TITLE` is a unique identifying description of the claim
* `DESCRIPTION` is a string describing the nature of the insurance claim
* `STATUS` is a string describing the status of the insurance claim
* If the person has a claim with the same title exists, the claim is updated instead

Examples:
* `claim 1 t/Hospital Claim d/Broke a leg s/Pending`
* `claim 1 t/Hospital Claim d/Broke left leg`
    * If a claim titled “Hospital Claim” already exists, the description will be updated to “Broke left leg”

### Adding a note: `note`
Adds a note to an existing person in the address book

Format: `note INDEX n/NOTE`
* Adds a note to the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3,...

Examples:
* `list` followed by `note 2 n/Meet for lunch` adds a note that says Meet for lunch to the 2nd person in the address book.
* `find Betsy` followed by `note 1 n/Birthday on 12 Dec` adds a note that says Birthday on 12 Dec  to the 1st person in the results of the find command.

### Schedule appointment: `schedule`
Schedule a meeting with a specific person

Format: `schedule INDEX m/MEETING_TIME`
* Schedule a meeting with the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* `MEETING_TIME` must be of the format `dd-mmm-yyyy hh:mm`
* First letter of month is capitalised while rest are not.
* Only store 1 appointment for each user
* Use the same command with different datetime to change the meeting timing
* You can remove the appointment by specifying `m/` without any datetime after it.

Examples:
* `schedule 3 m/05-Feb-2022 15:30`
* `schedule 5 m/05-Dec-2021 20:00`

### Adding Revenue: `revenue` [coming soon]

Adds revenue earned from an existing person in the address book.

Format: `revenue AMOUNT`

* Adds to the revenue earned from an existing person in the address book by the given `AMOUNT`. The amount refers to the
amount of revenue that the user earn from an existing person in S$. The amount can be **any float number of up to 2 
decimal places 100, 250.11, 50000, ...**
* Existing value will be updated with the current value added to the `AMOUNT` given.
* Default value for revenue of a person will be 0 when he/she is added to the address book.
* Revenue of an existing person in the address book should never be **negative**.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` followed by `revenue 100.95`
will update the revenue of John Doe to be `100.95`.
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal` followed by
`revenue 350` and followed by `revenue -100.11` will update the revenue of Betsy Crowe to be `249.89`.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InsurancePal data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

InsurancePal data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InsurancePal will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InsurancePal home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [i/INSURANCE]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague i/life i/health`
**Claim** | `claim 1 t/Hospital Claim d/Broke a leg s/Pending`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]… [i/INSURANCE]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Note** | `note 2 n/Meet for lunch`
**Revenue** | `revenue -100.11`
**Schedule** | `schedule 3 m/05-Feb-2022 15:30`
