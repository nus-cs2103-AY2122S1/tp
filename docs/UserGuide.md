---
layout: page
title: User Guide
---

ProfAddressBook Level 3 (*P*AB3)  is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PAB3 can get your contact management tasks done faster than traditional GUI apps. PAB3 helps CS2103 Instructors manage both students and TAs contacts within teams and tutorial groups. It is optimized for CLI users so that tasks can be done in bulk especially when dealing with huge number of contacts

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2122S1-CS2103-T16-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

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

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME s/STUDENT_ID N/NUSNet_ID g/GITHUB_ID T/TUTORIAL_ID {r/student|r/tutor} p/PHONE_NUMBER a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/Siddharth Srivastava s/A0226588N N/E0638874 g/Siddharth-Sid T/16 r/student p/98765432 a/John street, block 123, #01-01 t/incomplete ip`
* `add n/Rachel Cheah s/A0894765F N/E0987654 g/RachelCheah T/16 r/student p/12345678 a/123, Jurong West Ave 6, #08-111`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [s/STUDENT_ID] [N/NUSNet_ID] [g/GITHUB_ID] [T/TUTORIAL_ID] [{r/student|r/tutor}] [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `edit 1 n/Siddharth Srivastava t/IncompleteIP` Edits the name of the 1st person to be `Siddharth Srivastava` and replaces all existing tags with the tag `IncompleteIP`.

### Locating persons by name: `find`

Finds persons with the given search criteria and value.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g friend will also match Friend or FrIend or FRIEND.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Names and tags can be searched, by specifying `n/` or `t/` before the keyword respectively.
* Partial words will also be matched e.g. Han will match Hans.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find n/Hans Bo` will return `Hans Gruber`, `Will Han` and `Bo Yang`
* `find t/friend` will return all the contacts with the tag `friend`
* `find John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes a person or multiple contacts from the address book.

Format: `delete {INDEX | -a | -f}`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Displays a confirmation to delete all contacts returned from any previous find queries.
* You can use the optional -f argument to clear all contacts from a previous find queries.
* The -a argument deletes all contacts unless specified by the [-f] argument to delete only the previous find query.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `find Betsy` followed by `delete -f` deletes all the entries of the results of the `find` command.
* `delete -a` deletes all entries in the address book.

### Import existing contacts from JSON: `import`

Merges all contacts in a JSON file with the existing contacts in PAB3.

Format: `import FILENAME`

* Reads the contacts in `FILENAME` and merges them into the existing contacts.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PAB3 data are saved in the hard disk automatically after any command that changes the data. There is no need to save 
manually.

### Editing the data file

PAB3 data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update 
data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous PAB3 home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME S/STUDENT_ID N/NUSNet_ID g/GITHUB_ID T/TUTORIAL_ID {r/student \| r/tutor} [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]...` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete** | `delete {INDEX \| -a \| -f}`  <br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [s/STUDENT_ID] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find FIELD KEYWORD`<br> e.g., `find name James`
**List** | `list`
**Import** | `import FILENAME` <br> e.g., `import tutors.json`
**Help** | `help`
