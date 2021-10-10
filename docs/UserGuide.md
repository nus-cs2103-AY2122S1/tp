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

### Adding a person: `add`

Adds a person to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the application.

Format: `list`

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

### Deleting a person : `delete`

Deletes the specified person from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the application.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SIASA data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

## Upcoming Features

### Creating A Client `[coming in v1.2]`

Adds a client to the list

Format: `addclient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`

### Deleting A Client

Deletes the specified client from the client list.

Format: `delete INDEX`

- Deletes the client at the specified INDEX.
- The index refers to the index number shown in the displayed client list.
- The index must be a positive integer 1, 2, 3, …​

### Listing All Clients

Shows a list of all clients in the client list.

Format: `listclient`

### List Of Client\'s Policies

Shows the list of policies that belong to a specific client.

Format: `listpolicy CLIENT_INDEX`

- List policies for the client at the specified CLIENT_INDEX.
- The index refers to the index number shown in the displayed client list.
- The index must be a positive integer 1, 2, 3, …​

### Clear All
Clear all clients and policies.

Format: `clear`

### Creating A Policy

Adds a policy to the policy list.

Format: `addpolicy n/NAME_OF_POLICY e/EXPIRY_DATE p/PRICE c/COMMISSION cl/CLIENT_INDEX`

### Deleting A Policy

Deletes a policy from the policy list.

Format: `deletepolicy INDEX`

### Listing All Policies

Shows a list of all policies.

Format: `listpolicy`

### Clear Client's Policy

Clear all policies from a client.

Format: `clearpolicy CLIENT_INDEX`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SIASA home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**List** | `list`
**Help** | `help`
