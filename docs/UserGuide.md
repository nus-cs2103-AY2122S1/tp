---
layout: page
title: User Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction
Financial Advisor Smart Tracker (FAST) is a free open-source desktop app for Financial Advisors to manage their contacts. 
More importantly, FAST is **optimized for those who prefer to work with a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
For Financial Advisors that can type fast, FAST can get your contact management tasks done faster than traditional GUI apps. 
Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fast.jar` from [here](https://github.com/AY2122S1-CS2103T-T09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FAST.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
   
   <div markdown="block" class="alert alert-info">
   :information_source: Icons shown above will be added in future iterations<br>
   </div>

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

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

* `INDEX` refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

</div>

### Viewing help: `help`

Opens a new window that contains command usage, and a quick start guide.
In the help window, you can access all the command usages using the dropdown menu.

![help window](images/helpWindow.png)

Format: `help [COMMAND]`

Examples:
* `help` will just open the default help window
* `help add` will open the help window and directly navigate to the `Add` command help page.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The help window can also be quickly accessed by entering using the F1 key on your keyboard!
</div>


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons: `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person: `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Searching for people: `find`

Finds people by their name, priority, tags or remarks.

Format: `find QUERY [MORE_QUERIES]` OR `find pr/PRIORITY [MORE_PRIORITIES]`
OR `find t/TAG [MORE TAGS]` OR `find r/REMARK [MORE REMARKS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the queries does not matter. e.g. `Hans Bo` will match `Bo Hans`
* You can search by name, priority, tags or remarks using the formats shown above.
* Names with words starting with the query will match. e.g. `Han` will match `Solo Hans`.
* For priority searches, there are 3 priorities, `pr/low`, `pr/med`, or `pr/high`.
* Any remarks containing the searched remark will match. e.g. `r/piz` will match `likes pizza`.
* Persons matching at least one search query will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find pr/high med` returns all entries with `HighPriority` and `MediumPriority`.
* `find t/friends enemies` returns all entries tagged with `friends` or `enemies`.
* `find r/good` returns all entries with remarks containing `good`.

### Deleting a person: `del`

Deletes the specified person from the address book.

Format: `del INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `del 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `del 1` deletes the 1st person in the results of the `find` command.

### Adding a remark: `rmk`

Adds a remark to an existing person in the address book.

Format: `rmk INDEX [r/REMARK]`

* Adds a remark to the person at the specified `INDEX`.
* Remarks have a character limit of 100 characters.
* To delete a remark, leave the remark parameter `[r/REMARK]` empty.

Examples:
* `rmk 1 r/loves to eat`  adds a remark `loves to eat` to the first person.
* `rmk 1` removes the remark from the first person.<br>

### Adding an appointment: `appt`

Adds a scheduled appointment with the person.

Format: `appt INDEX d/DATE [t/TIME] [v/VENUE]`
<div markdown="span" class="alert alert-primary">:bulb: Tip:
The order of the DATE, TIME and VENUE parameter be can interchanged.
</div>

* Adds a scheduled appointment with the person at the specified `INDEX` if no appointment exists yet.
* `INDEX`: refers to the index shown in the displayed person list. **Must be a positive integer**.
* `DATE`: the date of the appointment in the format **`yyyy-mm-dd`**.
* `TIME`: the time of the appointment in the 24-hour format **`HH:mm`**.
* `VENUE`: the location of the appointment, which can at most be 30 characters long (including whitespaces).
* The `DATE` field **must be present**.
* `TIME` and `VENUE` fields can be left out.

Examples:
* `appt 1 d/2021-03-27` adds an appointment with date `27 Mar 2021` to the first person in FAST.
* `appt 3 d/2021-03-27 t/18:00` adds an appointment with date `27 Mar 2021` and time `1800hrs` to the third person in FAST.
* `find Matthew John` followed by `appt 3 d/2021-03-27 t/18:00 v/Velocity` adds an appointment with date `27 Mar 2021`, time `1800hrs` and venue `Velocity` to the third person in the results of the `find` command.

### Editing an appointment: `eppt`

Edits a scheduled appointment with the person.

Format: `eppt INDEX [d/DATE] [t/TIME] [v/VENUE]`
<div markdown="span" class="alert alert-primary">:bulb: Tip:
The order of the DATE, TIME and VENUE parameter be can interchanged.
</div>

* Edits a scheduled appointment with the person at the specified `INDEX` if the appointment exist.
* Existing details will be updated with the input data.
* `INDEX`: refers to the index shown in the displayed person list. **Must be a positive integer**.
* `DATE`: the date of the appointment in the format **`yyyy-mm-dd`**.
* `TIME`: the time of the appointment in the 24-hour format **`HH:mm`**.
* `VENUE`: the location of the appointment, which can at most be 30 characters long (including whitespaces).
* At least **one** of the optional fields must be present.

Examples:
* `eppt 1 d/2021-03-27` edits the appointment date to be `27 Mar 2021` of the first person.
* `eppt 3 v/  t/18:00` edits the appointment time to be `1800hrs` and clears the appointment venue of the third person.

### Deleting an appointment: `dappt`

Deletes a scheduled appointment with the person.

Format: `dappt INDEX`

* Deletes a scheduled appointment with the person at the specified `INDEX` if the appointment exist.
* Existing details will be deleted.
* `INDEX`: refers to the index shown in the displayed person list. **Must be a positive integer**.

Examples:
* `dappt 1` deletes the appointment of the first person.
* `find Ben` followed by `dappt 3` deletes the appointment the third person in the result of the `find` command.

### Updating completed appointment: `done`

Updates the completed appointment counter with the person.

Format: `done INDEX`
* Increment the completed appointment count with the person at the specified `INDEX` if the appointment exist.
* Existing details will be deleted.
* `INDEX`: refers to the index shown in the displayed person list. **Must be a positive integer**.

Examples:
* `done 1` updates the completed appointment counter of the first person.
* `find Matthew` followed by `done 3` updates the completed appointment counter of the third person in the result of the `find` command.

### Sorting all persons: `sort`

Sorts all existing person by the given keyword.

Format: `sort KEYWORD`
* There are only 3 values for `KEYWORD`: `name`, `appointment`, `priority`.
* `name` : Sorts all persons in alphabetical order.
* `appointment`: Sorts all persons by appointment date.
* `priority` : Sorts all persons by the highest priority tag.

Examples:
* `sort priority` Sorts all existing persons by the priority from highest priority tag to lowest priority tag.

### Editing a tag: `tag`

Adds or removes tags from a specified contact.

Format: `tag INDEX [a/TAG] [d/TAG]`
* Use `a/` to add a tag, and `d/` to delete a tag.
* Does not affect any unmentioned tags, unlike `edit`.
* `INDEX`: refers to the index shown in the displayed person list. **Must be a positive integer**.
* Delete operations are performed first before add operations, regardless of their order in the input.
* Tags have a maximum length of 20 characters, and may only contain alphanumeric characters.

Example: 
* `tag 1 a/thin d/fat` will add the `thin` tag while deleting the `fat` tag, given the `fat` tag already exists..
* `tag 1 d/pr/low a/pr/high` will delete the `LowPriority` tag before adding the `HighPriority` tag.
* `tag 1 a/ip/property` will add a `PropertyInsurance` tag to the contact.

### Using Priority tags: `pr/`

A set of fixed tags to indicate the priority level of contacts.

Usage: replace any fields requiring `[TAG]` with one of the following terms:
* LowPriority: `pr/low`
* MediumPriority: `pr/med`
* HighPriority: `pr/high`

Example:
* `tag 2 a/pr/low` will add a `LowPriority` tag to the specified contact.
* `edit 1 t/pr/med` will set the specified contact to have only the `MediumPriority` tag.

### Using Investment Plan tags: `ip/`

A set of fixed tags to indicate the type of investment plans purchased by contacts.

Usage: replace any fields requiring `[TAG]` with one of the following terms:
* HealthInsurance: `ip/health`
* Investment: `ip/invest`
* LifeInsurance: `ip/life`
* MotorInsurance: `ip/motor`
* PropertyInsurance: `ip/property`
* Savings: `ip/save`
* TravelInsurance: `ip/travel`

Example: 
* `tag 2 a/ip/life` will add a `LifeInsurance` tag to the specified contact.
* `edit 1 t/ip/motor` will set the specified contact to have only the `MotorInsurance` tag.

### Clearing all entries: `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

FAST data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FAST data are saved as a JSON file `[JAR file location]/data/fast.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FAST will discard all data and start with an empty data file at the next run.
</div>

### Viewing statistics

FAST comes with built-in statistics to provide you with an overview of your data. 
To view the statistics, simply click the "Stats" menu item on the top bar or press `F2`.
Currently, FAST supports these statistics:
* Priority Tag Chart
* Insurance Plan Chart (Coming soon!)

![stats window](images/statsWindow.png)


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Appointment** | `appt INDEX d/DATE [t/TIME] [v/VENUE]`<br> e.g. `appt 3 d/2021-03-27 t/18:00 v/Clementi Park`
**Add Contact** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete Appointment** | `dappt INDEX`<br> e.g. `dappt 1`
**Delete Contact** | `del INDEX`<br> e.g. `del 3`
**Edit Appointment** | `eppt INDEX [d/DATE] [t/TIME] [v/VENUE]`<br> e.g. `eppt 3 v/Clementi Town d/2021-03-27 t/18:00`
**Edit Contact** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`
**Edit Remark** | `rmk INDEX r/REMARK` OR `rmk INDEX`<br> e.g. `rmk 1 r/likes dogs`
**Edit Tag** | `tag INDEX a/[TAG] d/[TAG]` <br> e.g. `tag 1 a/friend d/ip/life`
**Find** | `find QUERY [MORE_QUERIES]` OR `find pr/PRIORITY [MORE_PRIORITIES]` OR `find t/TAG [MORE TAGS]` OR `find r/REMARK [MORE REMARKS]`<br> e.g. `find James Jake`
**Help** | `help [COMMAND]` <br> e.g. `help add`
**Investment Plan Tag** |HealthInsurance: `ip/health`<br>Investment: `ip/invest`<br>LifeInsurance: `ip/life`<br>MotorInsurance: `ip/motor`<br>PropertyInsurance: `ip/property`<br>Savings: `ip/save`<br>TravelInsurance: `ip/travel`
**List** | `list`
**Priority Tag** | LowPriority: `pr/low`<br>MediumPriority: `pr/med`<br>HighPriority: `pr/high`
**Remark** | `rmk INDEX [r/REMARK]`
**Sort** | `sort KEYWORD`
**Update Completed Appointment** | `done INDEX`<br> e.g. `done 5`
