---
layout: page
title: User Guide
---

## User Guide

Student Insurance Agent Sales Assistant (SIASA) **is a Command Line Interface (CLI) application that enables student financial advisors to quickly and easily keep track of their contacts** (potential clients and existing clients), financial plans sold and provide them with insightful, actionable statistics and querying functionalities to aid their operations.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

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

### Adding a person: `addclient`

Adds a person to the application.

Format: `addclient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `addclient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addclient n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `listclient`

Shows a list of all persons in the application.

Format: `listclient`

### Editing a person : `edit`

Edits an existing person in the application.

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

### Deleting a person : `deleteclient`

Deletes the specified person from the application.

Format: `deleteclient INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listclient` followed by `deleteclient 2` deletes the 2nd person in the application.
* `find Betsy` followed by `deleteclient 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all persons and policies from the application.

Format: `clear`

### Creating A Policy : `addpolicy`

Adds a policy to the policy list.

Format: `addpolicy n/NAME_OF_POLICY e/EXPIRY_DATE p/PRICE c/COMMISSION cl/PERSON_INDEX`

### Deleting A Policy : `deletepolicy`

Deletes a policy from the policy list.

Format: `deletepolicy INDEX`

### Listing All Policies : `listpolicy`

Shows a list of all policies.

Format: `listpolicy`

### List a Person's Policies : `clientpolicy`

Shows the list of policies that belong to a specific person.

Format: `clientpolicy PERSON_INDEX`

- List policies for the person at the specified PERSON_INDEX.
- The index refers to the index number shown in the displayed persons list.
- The index must be a positive integer 1, 2, 3, …​

### Clear Person's Policy : `clearpolicy`

Clear all policies from a person.

Format: `clearpolicy PERSON_INDEX`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SIASA data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SIASA home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Person** | `addclient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete Person** | `deleteclient INDEX`<br> e.g., `delete 3`
**Edit Person** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**List Persons** | `listclient`
**Add Policy** | `addpolicy [n/NAME_OF_POLICY] [e/EXPIRY_DATE] [p/PRICE] [c/COMMISSION] [cl/PERSON_INDEX]`
**Delete Policy** | `deletepolicy INDEX`
**List Policies** | `listpolicy`
**List Person's Policies** | `clientpolicy PERSON_INDEX`
**Clear Person's Policies** | `clearpolicy PERSON_INDEX`
**Clear All** | `clear`
**Help** | `help`
**Exit** | `exit`
