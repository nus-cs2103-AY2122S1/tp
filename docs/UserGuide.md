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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listcontact`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a contact: `addcontact`

Adds a contact to the application.

Format: `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `addcontact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addcontact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all contacts : `listcontact`

Shows a list of all contacts in the application.

Format: `listcontact`

### Finding contacts: `findcontact`

Finds and lists all contacts in address book whose name contains any of the argument keywords. 

Format: `findcontact KEYWORD`
* Keyword matching is case insensitive.

Example:
*  `findcontact john` returns a list with all the contacts containing the name john.

### Editing a contact : `editcontact`

Edits an existing contact in the application.

Format: `editcontact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `editcontact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `editcontact 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Deleting a contact : `deletecontact`

Deletes the specified contact from the application.

Format: `deletecontact INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listcontact` followed by `deletecontact 2` deletes the 2nd contact in the application.
* `findcontact Betsy` followed by `deletecontact 1` deletes the 1st contact in the results of the `find` command.

### Sorting a contact : `sortcontact`

Sorts the client list alphabetically by the order specified.

Format: `sortclient SORTER`

These are the current sorters implemented:
* `asc`: Sorts the clients in ascending order based on the saved name
* `dsc`: Sorts the clients in descending order based on the saved name


### Clearing all entries : `clear`

Clears all contacts and policies from the application.

Format: `clear`

### Creating A Policy : `addpolicy`

Adds a policy to the policy list.

Format: `addpolicy n/NAME_OF_POLICY p/PMT_AMOUNT [PMT_FREQ] [NUM_OF_PMT] c/COMMISSION_% NUM_OF_PMT cl/CONTACT_INDEX [t/TAGS] [e/COVERAGE_EXPIRY_DATE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A policy can have any number of tags (including 0)
</div>

* `COVERAGE_EXPIRY_DATE` refers to the date that the coverage expires in YYYY-MM-DD format, optional.
* Payment structure of the policy is defined by:
  * `PMT_AMOUNT`: fixed amount per payment
  * `PMT_FREQ` (optional): number of payments per year
  * `NUM_OF_PMT` (optional): total number of payments required
  * If only `PMT_AMOUNT` is provided, single lump sum payment is assumed
  * If only `PMT_AMOUNT` and `PMT_FREQ` is provided, indefinite fixed periodic payments is assumed.
  * If all three are provided, this represents periodic payments up till `NUM_OF_PMT`
* Commission structure of the policy is defined by:
  * `COMMISSION_%`: percentage of each payment that goes to commission
  * `NUM_OF_PMT`: number of payments that the agent will receive commission for
* `CONTACT_INDEX` refers to the current index of the contact in the contact list you wish to attach this policy to.

Examples:
* `addpolicy n/full life e/2021-12-12 p/10000 c/10 1 cl/1 t/Aviva` Adds a policy titled full life, coverage till 2021-12-12, lump sum payment of $100, commission of 10% on 1 payment, tagged Aviva, belonging to client with index 1.
* `addpolicy n/critical illness p/30000 12 120 c/10 12 cl/2` Adds a policy titled critical illness, monthly payments of $3000, 120 total payments, commission of 10% on 12 payments, belonging to client with index 2.

### Listing All Policies : `listpolicy`

Shows a list of all policies.

Format: `listpolicy`

### List a Contact's Policies : `contactpolicy`

Shows the list of policies that belong to a specific contact.

Format: `clientpolicy CONTACT_INDEX`

- List policies for the contact at the specified CONTACT_INDEX.
- The index refers to the index number shown in the displayed contacts list.
- The index must be a positive integer 1, 2, 3, …​

### Editing a policy : `editpolicy`

Edits an existing policy in the application.

Format: `editpolicy INDEX [n/NAME_OF_POLICY] [p/PMT_AMOUNT [PMT_FREQ] [NUM_OF_PMT]] [c/COMMISSION_% [NUM_OF_PMT]] [cl/PERSON_INDEX] [t/TAG] [e/COVERAGE_EXPIRY_DATE] …​`

* Edits the policy at the specified `INDEX`. The index refers to the index number shown in the displayed policy list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the policy will be removed i.e adding of tags is not cumulative.
* You can remove all the policy’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `editpolicy 1 p/100 c/20` Edits the payment amount and commission of the 1st policy to be `100` and `20%` respectively.
*  `editpolicy 2 n/Life Policy t/` Edits the name of the 2nd policy to be `Life Policy` and clears all existing tags.

### Deleting A Policy : `deletepolicy`

Deletes a policy from the policy list.

Format: `deletepolicy INDEX`

### Sorting a Policy: `sortpolicy`

Sorts the policy list alphabetically by the order specified.

Format: `sortpolicy SORTER`

These are the current sorts implemented:
* `titleasc`: Sorts the policies in ascending order based on the saved title
* `titledsc`: Sorts the policies in descending order based on the saved title
* `priceasc`: Sorts the policies in ascending order based on the saved payment
* `pricedsc`: Sorts the policies in descending order based on the saved payment
* `commasc`: Sorts the policies in ascending order based on the saved commission
* `commdsc`: Sorts the policies in descending order based on the saved commission
* `dateasc`: Sorts the policies in ascending order based on the saved expiry date
* `datedsc`: Sorts the policies in descending order based on the saved expiry date

### Clear Contact's Policy : `clearpolicy`

Clear all policies from a contact.

Format: `clearpolicy PERSON_INDEX`

### Show Expiring Policies: `expiringpolicy`

Show policies that are expiring in a month.

Format: `expiringpolicy`

### Download useful statistics as CSV : `download`

Download a CSV file containing useful statistics for the user. This includes
- Most valuable contacts + commission from them
- Number of policies per contact
- Average number of policies per contact
- Total commission

The file is stored in '/data' folder

Format: `download`

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
**Add Contact** | `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addcontact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete Contact** | `deletecontact INDEX`<br> e.g., `deletecontact 3`
**Edit Contact** | `editcontact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`editcontact 2 n/James Lee e/jameslee@example.com`
**List Contacts** | `listcontact`
**Find Contacts** | `findcontact KEYWORD`
**Add Policy** | `addpolicy n/NAME_OF_POLICY p/PMT_AMOUNT [PMT_FREQ] [NUM_OF_PMT] c/COMMISSION_% NUM_OF_PMT cl/CONTACT_INDEX [t/TAGS] [e/COVERAGE_EXPIRY_DATE]`
**Delete Policy** | `deletepolicy INDEX`
**Edit Policy** | `editpolicy INDEX [n/NAME_OF_POLICY] [p/PMT_AMOUNT [PMT_FREQ] [NUM_OF_PMT]] [c/COMMISSION_% [NUM_OF_PMT]] [cl/PERSON_INDEX] [t/TAG] [e/COVERAGE_EXPIRY_DATE] …`
**List Policies** | `listpolicy`
**List Contact's Policies** | `clientpolicy PERSON_INDEX`
**Clear Contact's Policies** | `clearpolicy PERSON_INDEX`
**Show Expiring Policies** | `expiringpolicy`
**Clear All** | `clear`
**Download** | `download`
**Help** | `help`
**Exit** | `exit`
