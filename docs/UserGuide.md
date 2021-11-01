---
layout: page
title: User Guide
---

<h1 align="center"> LeadsForce User Guide </h1>
<p align="center">
<img  src=images/LeadsForceLogo.jpg alt="alt text" width="125"> 
</p>
<p align="center">
Welcome to LeadsForce's User Guide! Find answers and step-by-step instructions to the features in LeadsForce, scroll down the table of contents below to get started ☺️
</p>

## Table of Contents

* [**1. Introduction**](#1-introduction)
  * [1.1 Reading this User Guide](#11-reading-this-user-guide)
* [**2. Quick Start**](#2-quick-start)
* [**3. Client Information**](#3-client-information)
    * [3.1 Next Meeting format](#31-nextmeeting) 
* [**4. Features**](#4-features)
    * [4.1 Create new contact: add](#41-create-new-contact--add)
    * [4.2 Retrieve particular contact: view](#42-retrieve-particular-contact--view)
    * [4.3 Update existing contact: edit](#43-update-existing-contact--edit)
    * [4.4 Delete particular contact: delete](#44-delete-particular-contact--delete)
    * [4.5 List all contacts: list](#45-list-all-contacts--list)
    * [4.6 Sort Contacts: sort](#46-sort-contacts--sort)
    * [4.7 Find meeting schedule: schedule](#47-find-meeting-schedule--schedule)
    * [4.8 Locating clients by keywords: search](#48-locating-clients-by-keywords--search)
    * [4.9 Filter current list: filter](#49-filter-current-list-by-keywords--filter)
    * [4.10 Clearing all entries: clear](#410-clearing-all-entries--clear)
    * [4.11 Exiting the program: exit](#411-exiting-the-program--exit)
    * [4.12 Saving data](#412-saving-the-data)
    * [4.13 Edit data file](#413-edit-data-file)
* [**5. Multiple Address Books**](#5-multiple-address-books-feature)
    * [5.1 Create new address book: ab create](#51-create-new-address-book--ab-create)
    * [5.2 Delete existing address book: ab delete](#52-delete-existing-address-book-ab-delete--ab-delete)
    * [5.3 Switch to different address book: ab switch](#53-switch-to-different-address-book--ab-switch)
    * [5.4 List all address book: ab list](#54-list-all-address-book--ab-list)
* [**6. FAQ**](#6-faq)
* [**7. Command Summary**](#7-command-summary)

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction
Are you a student financial advisor (FA) who finds it difficult to **manage client information**, and would like a software that could help to **keep track of 
meetings**? If so, LeadsForce might just be the app for you! 

LeadsForce is a client management software that helps entry-level and student financial advisors to effortlessly keep track of essential client information and
meetings. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Managing your leads has never been easier.

### 1.1 Reading this User Guide
When reading our User Guide, here are some important information to take note of. 

### 1.1.1 Icons and its meaning
| **Icon** | **Meaning of icons** |
| :-: | --- |
| <img src=images/info_icon.png width="50"> | Notes are specific conditions or behaviours of a feature. |
| <img src=images/tip_icon.png width="50"> | tips are suggestions that can help LeadsForce run faster. |
| <img src=images/warn_icon.png width="50"> | warnings are important information to take note of when using LeadsForce. When these warnings are not followed, this can result in LeadsForce crashing or have corrupted data files. |
 
## 2. Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `leadsforce.jar` from [here](https://github.com/AY2122S1-CS2103T-T17-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your LeadsForce.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. Here are some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`** : Creates a contact named `John Doe` to the Address Book.

    * **`delete 3`** : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#4-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Client Information

This section details the client information that financial advisors can keep track of with LeadsForce. Every client that is registered in LeadsForce have the following attributes that has the corresponding type and argument tag. The argument tags specified here will be used in the commands of several [features](#4-features). 

Client Attribute | Type of Attribute | Argument Tag
-----------------|-----------------|-----------------
Client ID (**Unique**) | Integer (assigned on creation of new contact) | i/
Name (**Compulsory**) | String | n/
Email (**Compulsory**)| String (email address)| e/
Address | String | a/
Current financial plans | List of Strings | c/
Disposable Income | Integer | d/
Last met | Date | l/
Next Meeting | Composite (More information below table) | m/
Contact number | Integer (8 digits long)| p/
Risk appetite | Integer from 1-5, <br>where 1 is very low risk tolerance and 5 is very high risk tolerance| r/
Tag | String | t/

### 3.1 Next Meeting attribute 
The `Next Meeting` attribute refers to the next meeting that the financial advisor using has with the client. Each `Next Meeting` consists of a *date*, *start time*, *end time* and a *location*. 

A NextMeeting needs to be inputted in the following format:

`m/dd-MM-yyyy (hh:mm~hh:mm), {non-empty location string)`

where
* *date* is in the format `dd-MM-yyyy`, where day, month and year are numerical values
* *startTime* and *endTime* are in `hh:mm` (24 hour format)
* *location* is a non-empty string

| example | `m/25-12-2021 (00:00~23:59), Santa's Workshop`|
| :-: | :-- |
| <img src=images/info_icon.png width="50"> | • `Next Meeting` will automatically be updated to null when the current time passes the date and end time of the meeting and this happens whenever the application is booted up. At the same time, the `Last Met` attribute will be updated to take on the current date. |


--------------------------------------------------------------------------------------------------------------------

## 4. Features

This section details the various features available in a single address book in LeadsForce. These features are generally used in the management of client information and client meetings. 

<div markdown="block" class="alert alert-info">
 <p><img src=images/info_icon.png width="40"/><b>Notes about the command format:</b></p>
 
* When `<attribute>` is given, it means that the any *attribute tag* can be used, with the exception of *client id* in some cases
* In the format for the commands provided, words which are in `UPPERCASE` refers to the `input` that the user must key in
* If the inputs are wrapped in curly brackets `{}`, they are inputs that are related to the preceeding argument tag

* Inputs in square brackets are optional input:<br>
  e.g. `KEYWORD [OTHER_KEYWORD]` can be in the form of `firstName` or `firstName lastName`

* Inputs with `…`​ at the end refers to commands that can accept multiple attribute inputs
  <br>
  e.g. `<email>/{EMAIL}…​` can be in the form of `e/@gmail.com` or `e/@gmail.com r/5`
</div>


### 4.1 Create New Contact : `add`

Adds a new client to the address book.
| Format | `add n/{CLIENT'S NAME} e/{EMAIL} <attribute>/{OTHER ATTRIBUTES} ...​`|
| :-: | --- |
| **example** | • `add n/Benedict Chua e/benchua@hotmail.com` <br> • `add n/Keith e/keithtan@ymail.com p/12345678 r/4`|
| <img src=images/info_icon.png width="50"> | • A client must have minimally the name and email tag filled during creation <br> • Any other tags are optional <br> • Tags that can be added are as seen in the client information in the Client Info Section |

### 4.2 Retrieve Particular Contact : `view`

View client's information in detail. 

| Format | `view CLIENT'S ID` |
| :-: | --- |
| **example** | • `view 2` would be used to view client 2's information|
 
### 4.3 Update Existing Contact : `edit`

Update the attributes of existing users using the tag of the client’s attribute, and set/edit meetings with clients.  

* Multiple attributes could be changed with one command.
* Multiple clients can be edited at the same time with the provided attributes by indicating their ids separated by a space.
 
| Format | `edit CLIENT'S ID... <attribute>/{CHANGED VALUE OF ATTRIBUTE}...`|
| :-: | --- |
| **example** | • `edit 3 p/12345678 r/5` command changes client 3's contact number to “12345678” and the risk appetite to 5. <br> • `edit 15 13 r/3` command changes the risk appetite of client 13 & 15 to “3”. <br> • `edit 3 4 5 m/21-09-2021 (09:00~10:00), Mcdonalds` sets a meeting for clients 3, 4 and 5 to be on 21st September 2021 from 9am to 10 am at Mcdonalds |
| <img src=images/tip_icon.png width="50"> | • multiple clients and their attributes can be updated with one `edit` command! For instance, if you're planning to organise a meeting with several different clients, you could simply use this feature to do so. |
 
### 4.4 Delete particular contact : `delete`

Deletes an existing client from the address book using their client id.

Format: `delete {CLIENT'S ID}...`

* Multiple clients can be deleted at the same time by their ids separated by a space.

Examples:
* `delete 7` will deletes client with client id 7
* `delete 4 8 6` will deletes the clients whose client id is 4, 6 and 8

### 4.5 List all contacts : `list`

Shows the full list of all clients in the address book.

Format: `list`


### 4.6 Sort Contacts : `sort`

Sorts clients in order based off the inputted attribute

Format: `sort <attribute>/{ASC/DSC}...`

* The asc and dsc tag dictates whether filtered client list is sorted in ascending or descending order.
* If multiple attributes are provided, then the clients will be sorted by the attributes sequentially.
e.g. `sort d/asc m/dsc`, this will sort the clients by disposable income first, then for those clients whose
disposable income are the same, they will be sorted by next meeting in descending.
* Sorting by the attribute Tag (t/) is not supported.
* The tags are case-insensitive. (ASC and asc are both okay.)

Examples:
* `sort r/ASC` will sort the list by ascending risk-appetite
* `sort i/dsc` will sort the list by descending client id

### 4.7 Find meeting schedule : `schedule`

Finds the meeting schedule that the user has on a specified date.

Format: `schedule {DATE}`

* `DATE` has to be in the format of dd-MM-yyyy, where Day(dd), Month(MM) and Years(yyyy) are numerical values.
* if the `{date}` is not specified, all meetings will be displayed.

Example:
* `schedule 22-09-2021` allows the user to view the schedule that the user has on the 22nd September 2021.
* `schedule` displays all meetings

### 4.8 Locating clients by keywords : `search`

Finds clients whose contacts match with the given keywords.

Format: `search {KEYWORD}... <attribute>/{ATTRIBUTE_KEYWORD}...`

* `KEYWORD` will be used to match with all attribute of the client.
* `<attribute>/` refers to the argument tag for the client's attribute.
* `{ATTRIBUTE_KEYWORD}` refers to the keyword that is to be matched with the corresponding client attribute.
* If no `KEYWORD` is provided, search will be based on `<attribute>/{ATTRIBUTE_KEYWORD}` only.
* The search is case-insensitive. e.g `keith` will match `Keith`.
* The order of the keywords does not matter. e.g. `John Doe` will match `Doe John`.
* Clients matching at least one keyword will be returned).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* If attribute keyword is provided, only clients whose attribute matches with the attribute keyword will be returned.
  e.g. `Tom Tim e/@gmail.com` will return `Tom Lee e/Tom@gmail.com` and not `Tim Shum e/Tim@yahoo.com`.

Examples:
* `search John` returns `john` and `John Doe`
* `search alex david` returns `Alex Yeoh`, `David Li`<br>

### 4.9 Filter current list by keywords : `filter`

Filter the current list by the given keywords.

Format: `filter {KEYWORD}... <attribute>/{ATTRIBUTE_KEYWORD}...`

* Works similar to `search` but `filter` works based on the current list shown as opposed to entire lists of contacts.
* `KEYWORD` will be used to match with all attribute of the client.
* If no `KEYWORD` is provided, then filter will be based on `<attribute>/{ATTRIBUTE_KEYWORD}`
* `<attribute>/` refers to the argument tag for the client's attribute.
* `{ATTRIBUTE_KEYWORD}` refers to the keyword that is to be matched with the corresponding client attribute.
* The filter is case-insensitive. e.g `keith` will match `Keith`.
* The order of the keywords does not matter. e.g. `John Doe` will match `Doe John`.
* Clients matching at least one keyword will be returned).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* If attribute keyword is provided, only clients whose attribute matches with the attribute keyword will be returned.
  e.g. `Tom Tim e/@gmail.com` will return `Tom Lee e/Tom@gmail.com` and not `Tim Shum e/Tim@yahoo.com`.

Examples:
* `search John` returns `john` and `John Doe`
* `search alex david` returns `Alex Yeoh`, `David Li`<br>

### 4.10 Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

* After inputting `clear`, another prompt will appear requesting for confirmation to clear the address book.
* The input required for the confirmation will either be:
  * `yes`: to confirm and proceed with the clear command.
  * `no`: to cancel the clear command.

### 4.11 Exiting the program : `exit`

Exits the program.

Format: `exit`

### 4.12 Saving the data

LeadsForce's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 4.13 Edit data file

LeadsForce's data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### 5. Multiple Address Books Feature 

### 5.1 Create new address book : `ab create`

Create a new address book by the name provided and switch to it.

Format: `ab create {ADDRESSBOOK_NAME}`

* `{ADDRESSBOOK_NAME}` refers to the name to be given to the new address book.
* The name of the addressbook cannot be the same as an existing address book.

Examples:
* 'ab create vip clients' will create a new address book named `vip clients`

### 5.2 Delete existing address book: ab delete : `ab delete`

Delete an address book that currently exists.

Format: `ab delete {ADDRESSBOOK_NAME}`

* `{ADDRESSBOOK_NAME}` refers to the name of the address book to be deleted .
* The current address book cannot be deleted, switch to another address book first before deleting the address book.

Examples:
* 'ab delete test' will delete the address book named `test`


### 5.3 Switch to different address book : `ab switch`

Switch to a different address book that currently exists.

Format: `ab switch {ADDRESSBOOK_NAME}`

* `{ADDRESSBOOK_NAME}` refers to the name of the address book to switched to .

Examples:
* 'ab switch other' will switch over to the address book named `other`


### 5.4 List all address book : `ab list`

List all the name of all the existing address books

Format: `ab list`


--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LeadsForce home folder.

--------------------------------------------------------------------------------------------------------------------

## 7. Command summary

Action | Format | Examples
--------|---------|---------
**Create** | `add <name>/{CLIENT'S NAME} <email>/{EMAIL} <phone-no>/{PHONE NUMBER} <risk-appetite>/{RISK-APPETITE} ...`| add n/benedict e/benedict@gmail.com p/90909898 r/3 |
**View** | `view {CLIENT'S ID}` | view 123 |
**Edit** | `edit {CLIENT'S ID}... <attribute>/{CHANGED VALUE OF ATTRIBUTE}...` | edit 1234 n/Dominic p/12345678 |
**Delete** | `delete {CLIENT'S ID}...` | delete 4  |
**List** | `list` | - |
**Sort** | `sort <attribute>/{ASC/DESC}...` | sort r/asc |
**Schedule** | `schedule {DATE}` | schedule 22-09-2021 |
**Search** | `search {KEYWORD}... <attribute>/{ATTRIBUTE_KEYWORD}...` | search * e/doe@gmail.com r/5 |
**Filter** | `filter {KEYWORD}... <attribute>/{ATTRIBUTE_KEYWORD}...` | filter * e/doe@gmail.com p/9 |
**Clear** | `clear` | - |
**Exit** | `exit` | - |
**Create Address Book** | `ab create {ADDRESSBOOK_NAME}` | ab create vip
**Delete Address Book** | `ab delete {ADDRESSBOOK_NAME}` | ab delete book
**Switch Address Book** | `ab switch {ADDRESSBOOK_NAME}` | ab switch another
**List Address Book** | `ab list` | -

