---
layout: page
title: User Guide
---

# User Guide

CONNECTIONS is a **desktop app for managing contacts, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CONNECTIONS can get your contact management tasks done faster than traditional GUI apps.


* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing help : `help`](#viewing-help--help)
  * [Adding a person: `add`](#adding-a-person-add)
  * [Listing all persons : `list`](#listing-all-persons--list)
  * [Editing a person : `edit`](#editing-a-person--edit)
  * [Adding Tags : `tag`](#adding-tags--tag)
  * [Removing Tags : `untag`](#removing-tags--untag)
  * [Locating persons by name: `find`](#locating-persons-by-name-find)
  * [Locating persons by tag (case insensitive): `findTag`](#locating-persons-by-tag-findtag)
  * [Locating persons by tag (case sensitive): `findTagC`](#locating-persons-by-tag-findtagC)
  * [Deleting a person : `delete`](#deleting-a-person--delete)
  * [Clearing all entries : `clear`](#clearing-all-entries--clear)
  * [Exiting the program : `exit`](#exiting-the-program--exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `connections.jar` from [here](https://github.com/AY2122S1-CS2103-F09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home directory_ for your CONNECTIONS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Click [here](#command-summary) for example commands you can try!

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

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

#### Format:
* `help` - List out all available commands
* `help COMMAND` - Shows help message for the command
* `help d/` - Opens link to documentation

**Sample Usage:**

`help add`

**Sample Outcome:**

```
add: Adds a person to the address book.
Parameters: n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]...
Example: add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney
```

### Adding a person: `add`

Adds a person to the address book.

#### Format:
* `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BIRTHDAY] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**
Format birthday as ddMMyyyy
</div>

**Sample Usage:**
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison b/25121999 p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

#### Format:
* `list`

### Editing a person : `edit`

Edits an existing person in the address book.

#### Format:
* `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`

Notes:
* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

**Sample Usage:**
* `edit 1 p/91234567 e/johndoe@example.com`
  * Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/`
  * Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Adding Tags : `tag`

Adds tag to an existing person in the address book.

#### Format:
* `tag INDEX [t/TAG]…​`

Notes:
* Adds tag to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* New tags will be added on top of existing tags i.e. tags added is cumulative.

**Sample Usage:**
* `tag 2 t/friend t/NUS`
    * Adds the tags `friend` and `NUS` to the 2nd person.

### Removing Tags : `untag`

Removes an existing tag from an existing person in the address book.

#### Format:
* `untag INDEX [t/TAG]…​`

Notes:
* Adds tag to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* Only tags that exist will be removed.

**Sample Usage:**
* `untag 2 t/friend t/NUS`
    * Removes the tags `friend` and `NUS` from the 2nd person.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

#### Format:
* `find KEYWORD [MORE_KEYWORDS]`

Notes:
* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

**Sample Usage:**
* `find John`
  * returns `john` and `John Doe`
* `find alex david`
  * returns `Alex Yeoh`, `David Li`<br>
    ![result for 'find alex david'](images/findAlexDavidResult.png

### Locating persons by tags: `findTag`

Finds persons whose contact contain any of the given tags.

#### Format:
* `findTag TAG [MORE_TAGS]`

Notes:
* The search is case-insensitive. e.g. `friend` will match `Friend`
* The order of the tags does not matter.
* Only full tags will be matched e.g. `Friend` will not match `Friends`
* Persons matching all tags will be returned (i.e. `AND` search).

**Sample Usage:**
* `find Friend NUS`
    * returns people tagged with both `Friend` and `NUS`
    * returns people tagged with both `friend` and `nus`

### Locating persons by tags: `findTagC`

Finds persons whose contact contain any of the given tags.

#### Format:
* `findTagC TAG [MORE_TAGS]`

Notes:
* The search is case-sensitive. e.g. `friend` will not match `Friend`
* The order of the tags does not matter.
* Only full tags will be matched e.g. `Friend` will not match `Friends`
* Persons matching all tags will be returned (i.e. `AND` search).

**Sample Usage:**
* `find Friend NUS`
  * returns people tagged with both `Friend` and `NUS`
  * does not return people tagged with `friend` and `nus`

### Deleting a person : `delete`

Deletes the specified person from the address book.

#### Format:
* `delete INDEX`

Notes:
* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

**Sample Usage:**
* `list` followed by `delete 2`
  * deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1`
  * deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

#### Format:
* `clear`

### Exiting the program : `exit`

Exits the program.

#### Format:
* `exit`

### Saving the data

CONNECTIONS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CONNECTIONS data are saved as a JSON file `[JAR file location]/data/connections.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CONNECTIONS will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Summary | Format, Examples
--------|--------|----------------
**Add** | Adds a person | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BIRTHDAY] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 b/23062001 t/friend t/colleague`
**Clear** | Clears all entries | `clear`
**Delete** | Deletes a person | `delete INDEX`<br> e.g., `delete 3`
**Edit** | Edits a person | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com b/30012000`
**Exit** | Exits the program | `exit`
**Find** | Locates persons by name | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**FindTag** | Locates persons by tags | `findTag TAG [MORE_TAGS]`<br> e.g., `find friend NUS`
**Help** | Displays help information | `help [COMMAND] [/d]`
**List** | Lists all persons | `list`
**Tag** | Tags a person | `tag INDEX [t/TAG]…​`<br> e.g., `tag 2 t/friend t/NUS`
**Untag** | Untags a person | `untag INDEX [t/TAG]…​`<br> e.g., `untag 2 t/colleague`
