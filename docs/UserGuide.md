---
layout: page
title: User Guide
---
## User Guide

* Table of Contents
{:toc}

## Overview

Student Insurance Agent Sales Assistant (SIASA) **is a Command Line Interface (CLI) application for Student Insurance
Agents that aids their operations by:
* [Managing their contacts (such as potential clients and existing clients)](#contact-management)
* [Managing financial policies that they have sold](#policy-management)
* [Providing insightful statistics](#statistics)
* [Offering helpful querying and sorting functionalities](#querying-and-sorting-functionalities)

--------------------------------------------------------------------------------------------------------------------

## Quick start
This section provides instructions to begin using Siasa.
1. Ensure you have Java `11` or above installed in your Computer. Siasa has been tested on Java `11` and running it on other versions might result in bugs.
2. Download the latest release [here](https://github.com/AY2122S1-CS2103-F10-4/tp/releases) and move it to the folder you wish to use as the home folder for Siasa.
3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   
![First Startup UI Image](images/Ui.png)

4. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
   Some example commands you can try:
   * `addcontact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`: Adds a contact named John Doe to the contacts list.
   * `deletecontact 2`: Deletes the 2nd contact shown in the current contact list.
   * `clear`: Deletes all contacts and policies.
   * `exit`: Exits the app.
  
5. Refer to the [Features](#features) below for details of each command. 

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `allcontact`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Contact Management

#### Adding a contact: `addcontact`

Adds a contact to the application.

Format: `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `addcontact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addcontact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Listing all contacts : `allcontact`

Shows a list of all contacts in the application.

Format: `allcontact`

#### Editing a contact : `editcontact`

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

#### Deleting a contact : `deletecontact`

Deletes the specified contact and all of his/her policies from the application.

Format: `deletecontact INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `allcontact` followed by `deletecontact 2` deletes the 2nd contact in the application.
* `findcontact Betsy` followed by `deletecontact 1` deletes the 1st contact in the results of the `find` command.

### Policy Management

#### Common - Policy Details
* **Name** - `n/POLICY_NAME`
  * Should be unique
* **Payment structure of the policy** -`p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMT]`:

| Payment Type                                       | Sample Command| Represents                                     |
|----------------------------------------------------|-----------------------|---------------------------------------------|
| Single lump sum                                    | /p 10000               | Single payment of $100                      |
| X payments per year, indefinite number of payments | /p 10000 12            | Monthly payments of $100, indefinitely       |
| X payments per year, definite number of payments   | /p 10000 12 120        | Monthly payments of $100, 120 total payments |


* **Commission structure of the policy** - `c/COMMISSION_% NUM_OF_COMM`:
  * Receives a percentage commission (`COMMISSION_%`) from the payment amount (`PMT_AMOUNT_CENTS`) for the first number of payments (`NUM_OF_COMM`) in the payment structure.
  * `COMMISSION_%`: percentage of each payment that goes to commission
  * `NUM_OF_COMM`: the number of payments that the agent will receive commission for
  *  E.g. `/c 6 5` Receives 6% commission for the first 5 payments.
  
* **Contact that the policy belongs to** - `cl/CONTACT_INDEX`
  * Current index of that contact in the contact list.
  * The index **must be a positive integer** 1, 2, 3, …​
* **Coverage expiry** - `e/COVERAGE_EXPIRY_DATE`
  * Date that the coverage expires in YYYY-MM-DD format, optional.
* **Tags** - `t/TAG...`
  * can have more than one tag

#### Creating A Policy : `addpolicy`

Adds a policy to the policy list.

Format: `addpolicy n/POLICY_NAME p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTS] c/COMMISSION_% NUM_OF_COMM
cl/CONTACT_INDEX [e/COVERAGE_EXPIRY_DATE] [t/TAG]…​`

<div markdown="block" class="alert alert-warning">
**:grey_exclamation:**
A policy should not have less payments than the number of commissions, since every commission is tied to one payment!
</div>

Examples:
* `addpolicy n/Aviva full life plan B p/10050 c/10 1 cl/1 e/2021-12-12 t/Life Insurance` Adds a policy named Aviva full life plan B,
single payment of $100.50, commission of 10% on first payment, belonging to the contact with index 1,
coverage expires on 2021-12-12, tagged Life Insurance.
* `addpolicy n/AIA diabetes care  p/30000 4 120 c/10 30 cl/2` Adds a policy titled AIA diabetes care,
quarterly payments of $300, 120 total payments, commission of 10% on the first 30 payments,
belonging to the contact with index 2.

#### Editing a policy : `editpolicy`

Edits an existing policy in the application.

Format: `editpolicy INDEX [n/POLICY_NAME] [p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTs]] [c/COMMISSION_% NUM_OF_COMM]
[cl/CONTACT_INDEX] [e/COVERAGE_EXPIRY_DATE] [t/TAG]…​`

<div markdown="block" class="alert alert-warning">
**:grey_exclamation:**
Careful changing the number of commissions or payments. A policy should not have less payments than the number of commissions.
</div>

* Edits the policy at the specified `INDEX`. The index refers to the index number shown in the displayed policy list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the policy will be removed i.e adding of tags is not cumulative.
* You can remove all the policy’s tags by typing `t/` without
  specifying any tags after it.

Examples:
* `editpolicy 1 p/1000 12 c/20 5` Edits the payment structure to be indefinite monthly payments of $10 and
  the commission structure to be 20% for the first 5 payments.
* `editpolicy 2 n/AIA Gold Health t/` Edits the name of the 2nd policy to be `AIA Gold Health` and clears all existing tags.

#### Listing All Policies : `allpolicy`

Shows a list of all policies.

Format: `allpolicy`

#### Deleting A Policy : `deletepolicy`

Deletes a policy from the policy list.

Format: `deletepolicy INDEX`

#### List a Contact's Policies : `contactpolicy`

Shows the list of policies that belong to a specific contact.

Format: `contactpolicy CONTACT_INDEX`

- List policies for the contact at the specified CONTACT_INDEX.
- The index refers to the index number shown in the displayed contacts list.
- The index must be a positive integer 1, 2, 3, …​

#### Clear Contact's Policy : `clearpolicy`

Clear all policies from a contact.

Format: `clearpolicy CONTACT_INDEX`

### Querying and Sorting Functionalities

#### Finding contacts: `findcontact`

Finds and lists all contacts in address book whose name contains any of the argument keywords.

Format: `findcontact KEYWORD`
* Keyword matching is case-insensitive.

Example:
* `findcontact john` returns a list with all the contacts containing the name john.

#### Show Expired/Expiring Policies: `expiringpolicy`

Show policies that are expired or expiring in a month.

Format: `expiringpolicy`

#### Sorting contacts : `sortcontact`

Sorts the contact list alphabetically by the order specified. Case-insensitive.

Format: `sortcontact SORTER`

These are the current sorters implemented:
* `asc`: Sorts the contacts in ascending order alphabetically based on the saved name.
* `dsc`: Sorts the contacts in descending order alphabetically based on the saved name.

#### Sorting policies: `sortpolicy`

Sorts the policy list using the sorter specified.

Format: `sortpolicy SORTER`

These are the current sorters implemented:
* `titleasc`: Sorts the policies in ascending order alphabetically based on the saved title (Case-insensitive)
* `titledsc`: Sorts the policies in descending order alphabetically based on the saved title (Case-insensitive)
* `paymentasc`: Sorts the policies in ascending order based on the total payment amount (indefinite payments count as infinite)
* `paymentdsc`: Sorts the policies in descending order based on the total payment amount (indefinite payments count as infinite)
* `commasc`: Sorts the policies in ascending order based on the total commission amount
* `commdsc`: Sorts the policies in descending order based on the total commission amount
* `expiryasc`: Sorts the policies in ascending order based on the saved expiry date, if it exists
* `expirydsc`: Sorts the policies in descending order based on the saved expiry date, if it exists

### Statistics

#### Download useful statistics as TXT : `download`

Download a .txt file containing useful statistics for the user. This includes
- Most valuable contacts + total commission from each of them
- Number of policies for each contact
- Average number of policies per contact
- Total commission

The file is stored in '/data' folder

Format: `download`

### Others

#### Clearing all entries : `clear`

Clears all contacts and policies from the application.

Format: `clear`

#### Getting Help : `help`

Provides a helpful guide of the commands.

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

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
**List Contacts** | `allcontact`
**Find Contacts** | `findcontact KEYWORD`
**Add Policy** | `addpolicy n/POLICY_NAME p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTS] c/COMMISSION_% NUM_OF_COMM cl/CONTACT_INDEX [e/COVERAGE_EXPIRY_DATE] [t/TAG]…​`
**Delete Policy** | `deletepolicy INDEX`
**Edit Policy** | `editpolicy INDEX [n/POLICY_NAME] [p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTs]] [c/COMMISSION_% NUM_OF_COMM] [cl/CONTACT_INDEX] [e/COVERAGE_EXPIRY_DATE] [t/TAG]…​`
**List Policies** | `allpolicy`
**List Contact's Policies** | `contactpolicy CONTACT_INDEX`
**Clear Contact's Policies** | `clearpolicy CONTACT_INDEX`
**Show Expired/Expiring Policies** | `expiringpolicy`
**Sort Policies** | `sortpolicy SORTER`
**Sort Contacts** | `sortcontact SORTER`
**Clear All** | `clear`
**Download** | `download`
**Help** | `help`
**Exit** | `exit`
