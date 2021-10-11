---
layout: page
title: User Guide
---

ComputingConnection is for computer science university students who prefer to use CLI over GUI, and want to keep track of the large amounts of friends and staff they have encountered in university.

Through an address book, this product aims to store information, such as faculty, course, frequency of meeting, phone numbers, etc, of friends or staff, so that the student will be able to remember and refer when needed.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `computingconnection.jar` from [here](https://github.com/AY2122S1-CS2103T-W10-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ComputingConnection.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com` : Adds a contact named `John Doe` to the address book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Viewing help: `help`
Shows a message explaining how to access the help page.
Format: `help`

### Adding a contact: `add`
Adds a contact to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROLE f/FACULTY m/MAJOR  [t/TAG]...`

A contact may have any number of tags (including 0)

Examples: 

* `add n/John Doe p/98765432 e/johnd@nus.edu.sg r/staff f/computing m/computer science mod/CS2040S`
* `add n/Seth r/student f/computing m/computer science mod/CS2103T`

List of personal detail tags:
* n/: name
* p/: phone number
* e/: email

List of school-related tags:
* r/: role - staff/student/admin
* f/: faculty
* m/: major
* cca/: cca
* mod/: module

### Search Contacts : `search`
Search for contacts with specified keyword.
Format: `search KEYWORD`
Examples:

* `search John` returns `john` and `John Doe`
* `search alex` returns `Alex Yeoh`

### List view of contacts : `list`

Shows a list of all contacts in the address book.

Format: `list`

### Sort contacts : `sort`

Sorts contacts and shows the list of contacts in alphabetical order.

Format: `sort`

### Editing a contact : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [r/ROLE] [f/FACULTY] [m/MAJOR] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without
    specifying any tags after it.

### Detailed View of Contacts : view
Get a detailed view of specific contact(s).
Format: `view n/NAME`
Examples:

*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Locating contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Filter a contact : `filter`

Filters the contacts by tags.

Format: `filter f/FACULTY [t/TAG]`

* Filters a contact according to a tag
Examples:
* `filter f/computing` returns all users who have been assigned the f/computing tag.
* `filter t/staff f/computing` returns all users who have been assigned the t/staff tag and f/computing tag .

### Deleting a contact : `delete`

Deletes the specified contact from the address book.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Clearing all entries : `clear`

* `view John` returns John’s profile, with his categories, tags, particulars and details
* `view David` returns all profiles named David. Assuming there are two profiles named David, it would return both detailed views, with each contact’s particulars and details.

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
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`


