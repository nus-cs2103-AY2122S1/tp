# User Guide
LeadsForce is a desktop app that is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
It provides a new way to streamline the process for student financial advisors to find the right clients to contact. 
We aim to help you manage your leads by making it effortless to store information regarding them and retrieving this information seamlessly. 
Finding your next lead has never been easier.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

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

* In the format for the commands provided, words which are in UPPERCASE refers to input that the user must key in

* Inputs in square brackets are optional input:<br>
  e.g. `KEYWORD [OTHER_KEYWORD]` can be in the form of `firstName` or `firstName lastName`

* Inputs with `…`​ at the end refers to inputs that can be used multiple times in that command
  .<br>
  e.g. `[/tag ATTRIBUTE]…​` can be in the form of `/email @gmail.com` or `/email @gmail.com /risk-appetite 5`

</div>

### Create New Contact: `create`

Adds a new client to the address book.

Format: `create {client’s name} /<email> {email} /<phone-no> {phone number} /<risk-appetite> {risk appetite}...​`

* A client must have minimally the name and email tag filled during creation
* Any other tags are optional
* Tags that can be added are as seen in the client information in the Client Info Section

Examples:
* `create Benedict Chua /email benchua@hotmail.com`
* `create Keith /email keithtan@ymail.com /phone-no 12345678 /risk-appetite 4`

### Delete particular contact : `delete`

Deletes an existing client from the address book using any specified attribute to identify the client.

Format: `delete /<attribute> {value}`

* Attributes would be limited to client id, email or contact number.
* It is possible to bulk delete multiple clients by inputting multiple keys separated by ‘,’.

Examples:
* `delete /id 4,6,7`(deletes clients with client id 4, client id 6 and client 7)
* `delete /email keithtan@gmail.com`(deletes a client whose email address is keithtan@gmail.com)

### Sort Contacts : `sort`

Sorts clients in order based off the inputted attribute

Format: `sort /<attribute> {ASC/DESC}`

* The asc and desc tag dictates whether filtered client list is sorted in ascending or descending order

Examples:
* `sort /risk-appetite ASC`

### Saving the data

LeadsForce's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

LeadsForce's data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LeadsForce home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Examples
--------|---------|---------
**Create** | `create {client’s name} /<email> {email} /<phone-no> {phone number} /<risk-appetite> {risk appetite}`| create benedict /email benedict@gmail.com /phone-no 90909898 /risk-appetite 3 |
**View** | `view {client’s id number}` | view 123 |
**Delete** | `delete /<attribute> {value}` | delete /id 4,6,7  |
**Update** | `update {Client’s id number} /<attribute> {change value of attribute}` | update 1234 /name Dominic /phone-number 12345678 |
**List** | `list` | - |
**Search** | `search KEYWORD [OTHER_KEYWORD] [/tag ATTRIBUTE]...` | search * /email doe@gmail.com /risk-appetite 5 |
**Sort** | `sort /<attribute> {ASC/DESC}` | sort /risk-appetite asc |
**Exit** | `exit` | - |
