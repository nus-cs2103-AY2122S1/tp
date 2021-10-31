---
layout: page
title: User Guide
---

<p align = "center"><img src="images/tour_booth.png" width="400"></p>

<h2 align ="center"> WhereTourGo: A Tour Guide’s Best Friend! </h2>

**WhereTourGo (WTG)** is a desktop app for managing your tour contacts, optimized for use via a Command-Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). A CLI processes your commands in the form of lines of text, while a GUI is another form of user interface that allows you to interact with our app through graphical icons.

The app helps you easily collate and access contacts of a variety of several services such as F&B, accommodations, attractions etc.

Once you are familiar with our commands, typing fast in CLI allows you to get your contact management tasks done faster and more efficiently than traditional GUI apps. Handling repetitive tasks also becomes easier as we offer access to input history, allowing you to access past commands conveniently.

This User Guide will help you set up your contact list and familiarise yourself with the features of WhereTourGo.


## Table of Contents

* [Quick Start](#quick-start)
* [About](#about)
  * [Structure of this document](#structure-of-this-document)
  * [Reading this document](#reading-this-document)
    * [Technical Terms](#technical-terms)
    * [General Symbols and Syntax](#general-symbols-and-syntax)
    * [Format of commands](#format-of-commands)
    * [Restrictions for Commands](#restrictions-for-commands)
* [Features](#features)
  * [Modifying Contacts](#modifying-contacts)
    * [Adding a contact: `add`](#adding-a-contact-add)
    * [Editing a contact: `edit`](#editing-a-contact-edit)
    * [Deleting a contact: `delete`](#deleting-a-contact-delete)
  * [Retrieving Contacts](#retrieving-contacts)
    * [Listing all contacts: `list`](#listing-all-contacts-list)
    * [Locating contacts: `find`](#locating-contacts-find)
    * [Filtering by category: `filter`](#filtering-contacts-filter)
    * [Sorting contacts: `sort`](#sorting-the-contacts-sort)
    * [Summarizing contacts: `sum`](#summarizing-contacts-sum)
    * [Viewing a contact : `view`](#viewing-a-contact-view)
  * [Navigating WhereTourGo](#navigating-wheretourgo)
    * [Navigating input history](#navigating-input-history)
    * [Clicking on Contacts](#clicking-on-contacts)
  * [Undoing and Redoing](#undoing-and-redoing)
    * [Undoing operations : `undo`](#undoing-operations-undo)
    * [Redoing operations : `redo`](#redoing-operations-redo)
  * [Managing Data](#managing-data)
    * [Exporting data: `export`](#exporting-data-export)
    * [Clearing all entries : `clear`](#clearing-all-entries-clear)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Exiting the program : `exit`](#exiting-the-program-exit)
  * [Help](#help)
    * [Viewing help: `help`](#viewing-help-help)
    * [Displaying commands: `cmd`](#displaying-commands-cmd)

* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

# Quick start
1. Ensure you have Java 11 or above installed in your Computer. You may install it here
   [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
1. Download the latest `wheretourgo.jar` from [here](update on final)

1. Copy the file to the folder you want to use as the _home folder_ for your WhereTourGo

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data<br>
   ![Ui](images/sample.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window<br>
   Some example commands you can try:

  * **`list`** : Lists all contacts

  * **`add`**`c/att n/Mandarin Oriental p/98765432 e/Mandarin_Oriental@example.com a/Marina Bay Sands, Singapore 123432` : Adds a contact named `Mandarin Oriental`

  * **`delete`**`3` : Deletes the 3rd contact shown in the current list

  * **`clear`** : Deletes all contacts

  * **`exit`** : Exits the app

1. Refer to the [Features](#features) below for details of every command

--------------------------------------------------------------------------------------------------------------------

# About

### Structure of this document
This User Guide is crafted to assist you in finding what you need easily and efficiently.
In the next subsection, [Reading this document](#reading-this-document), there are also several tips on how to read this guide.
The following section, [Features](#features), details the features in WhereTourGo and how to use them.

### Reading this document
This section introduces some technical terms, symbols and syntax used in this guide. Do try to familiarize yourself with them before moving on to the subsequent sections.

#### Technical Terms
The table below explains some technical terms frequently used throughout this User Guide.

| Term |  What it means |
|:----------:|-------------|
| CLI |  The Command-Line Interface (or CLI for short) is the user interface in which NUSMaze is based. Unlike traditional Graphical User Interface (GUI) base applications, it may be less intuitive to new users. However upon familiarisation, fast typists may find it faster to use.  |
| Command word| The Command word refers to the keywords which NUSMaze recognises to invoke specific commands. These command words will be indicated by `COMMAND WORD` in the various sections below.|
|Parameter|Parameter refers to the user input required after the user is prompted by NUSMaze|

#### General Symbols and Syntax
The table below explains the general syntax used throughout the user guide.

| Syntax |  What it means |
|:----------:|-------------|
| `command` |  A grey highlighted block specifies an executable command, or parameters that can be entered into the command box.  |
| _italics_ | Italicised text indicates that the text has a definition specific to NUSMaze, or it is a caption for a Figure in the guide. |
|<div markdown="block" class="alert alert-info"> :information_source: </div>  | An exclamation mark indicates that the following text is a tip. |
|<div markdown="block" class="alert alert-danger"> :warning: </div> | A warning sign indicates that the following text is important. |

#### Format of Commands
The following points explain the format of a command.
More examples will be provided for each command in [Features](#features).

* Words in `UPPER_CASE` are the parameters to be supplied by the user<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Mandarin Oriental`.

* Items in **square brackets** are **optional**<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Mandarin Oriental t/hotel` or as `n/Mandarin Oriental`.

* Items with `…`​ after them can be used multiple times including zero times<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/food`, `t/wine t/food` etc.

* Parameters can be in any order<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Duplicate contact names are not allowed
  e.g. if there exists a contact named ‘Marina Bay Sands’, adding or editing a contact with the name ‘Marina Bay Sands’ is not allowed.<br>

#### Restrictions for Commands
* Contact Name has a limit of 100 characters
* Address has a limit of 150 characters
* Emails must have a domain name i.e. @gmail.com, @yahoo.com etc.
* Phone number has a limit of 15 digits
* Category Codes only take in these 5 possible inputs:
  * Attraction: `att`
  * F&B: `fnb`
  * Commerce: `com`
  * Accomodation: `acc`
  * Transport: `tpt`
  * OTHERS: `oth`

<div markdown="block" class="alert alert-danger"> :warning: 
 Only alphanumeric symbols are allowed. i.e. ‘&’, ‘!’, ‘?’ are not allowed
</div>
 
--------------------------------------------------------------------------------------------------------------------

## Features

## Modifying Contacts

### Adding a contact: `add`

Adds a contact to the contact list.

Format: `add c/CATEGORY_CODE n/CONTACT_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [rv/REVIEW] [ra/RATING] [t/TAG]…​​`

<div markdown="span" class="alert alert-primary">:bulb: Tip:
A contact can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-primary">:bulb: Tip:
A contact can have a review
</div>

Examples:
* `add c/att n/Singapore Flyers p/92345678 e/123@example.com a/30 Raffles Ave, Singapore 039803 ra/4`

Expected Outcome for `add c/att n/Singapore Flyers p/92345678 e/123@example.com a/30 Raffles Ave, Singapore 039803 ra/4` :
![Add](images/add.png)

### Editing a contact: `edit`

Edits an existing contact in the contact list.

Format: `edit INDEX [n/CONTACT_NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [rv/REVIEW] [c/CATEGORY_CODE][ra/RATING]  [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided
* Existing values will be updated to the input values
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative
* You can remove all the contact’s tags by typing `t/` without
  specifying any tags after it

Examples:
*  `edit 1 p/92345678 e/Mandarin_Oriental@example.com` Edits the phone number and email address of the 1st contact to be `92345678` and `Mandarin_Oriental@example.com` respectively
*  `edit 2 n/Marina Bay Sands t/` Edits the name of the 2nd contact to be `Marina Bay Sands` and clears all existing tags
* `edit 3 ra/3` Edits the rating of the 3rd contact to be `3`

Expected Outcome for `edit 1 p/92345678 e/Mandarin_Oriental@example.com`:

Before:

![EditBefore](images/list.png)

After:

![EditAfter](images/edit.png)

### Deleting a contact: `delete`

Deletes the specified contact from the contact list, either by name or index.

Format: `delete INDEX`

* Deletes the contact at the specified INDEX
* The index refers to the index number shown in the displayed contact list
* The index must be a positive integer 1, 2, 3, ...


Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the contact list
* `find Mandarin Oriental` followed by `delete 1` deletes the 1st contact in the results of the find command

Format: `delete CONTACT_NAME`

* Deletes the contact specified by CONTACT_NAME
* The name must refer to a contact’s full name in the displayed contact list
* The name is case-sensitive and must match exactly

Examples:
* `list` followed by `delete Singapore DUCKTours` deletes the contact with name ' Singapore DUCKTours’
* `find fullerton` followed by `delete The Fullerton Hotel` deletes the contact with name 'The Fullerton Hotel'

## Retrieving Contacts

### Listing all contacts: `list`

Shows a list of all contacts in the contact list.

Format: `list`



### Locating contacts: `find`

Finds contacts whose names, addresses, phone numbers, email addresses or reviews contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive, e.g.,  `hotel` will match `Hotel`
* The order of the keywords does not matter. e.g. `Park Hyatt` will match `Hyatt Park`
* The name, phone, email, address and review will be searched
* Contacts matching at least one keyword will be returned . e.g. `Park` will return `Park Hyatt Singapore`, `Park Royale`

Examples:
* `find hotel` returns `D’ Hotel` and `The Fullerton Hotel`
* `find bay` returns `Gardens by the Bay` and `Marina Bay Sands`

Expected Outcome for `find bay`:

![find](images/find.png)



### Filtering contacts: `filter`
Shows a list of all contacts in the specified category or with the specified rating.

Format: `filter [c/CATEGORY] [ra/NUMBER] ... `

Category codes:
* Attraction :`c/att`
* F&B :`c/fnb`
* Commerce :`c/com`
* Accommodation :`c/acc`
* Transport :`c/tpt`
  Ratings :
* 1-star : `ra/1`
* 2-star : `ra/2`
* 3-star : `ra/3`
* 4-star : `ra/4`
* 5-star : `ra/5`

<div markdown="span" class="alert alert-primary">:bulb: Tip:
You can filter contacts in one or many categories
</div>

Examples:
* `filter c/fnb` returns McDonald's, KFC, and Burger King (all F&B contacts)
* `filter c/acc c/fnb` returns Mandarin Oriental, Hotel81, and KFC (all accommodation and F&B contacts)
* `filter ra/3` returns all contacts with 3-star rating from all categories
* `filter c/fnb ra/3` returns all F&B contacts with 3-star rating

Expected Outcome for `filter c/fnb`:

![filter](images/filter.png)

### Sorting the contacts: `sort`
Sorts the list of contacts in a specified order. The sort feature is sorted by Contact name (in alphabetical order) or by Rating (in descending order).

Format : `sort FIELD`

Examples :
* `sort name` returns the same list of contacts displayed in an alphabetical order
* `sort rating` returns the same list of contacts displayed from the contacts with the highest to lowest rating

### Summarizing contacts: `sum`
Shows a summary of the content of WhereTourGo. The data presented as a summary are as follows:
* total number of contacts in the contact list
* the proportion of contacts for each rating
* the proportion of contacts for each category defined by [CategoryCode](#restrictions-for-commands)

Format: `sum`

Expected Outcome for `sum`:
![sum](images/sum.png)

### Viewing a contact: `view`

Displays the specified contact from the contact list.

Format: `view INDEX`

* Displays the contact at the specified INDEX
* The index refers to the index number shown in the displayed contact list
* The index must be a positive integer 1, 2, 3, ...

Examples:
* `list` followed by `view 2` displays the 2nd contact in the contact list
* `find Mandarin Oriental` followed by `view  1` displays the 1st contact in the results of the find command

Format: `view CONTACT_NAME`

* Displays the contact specified by CONTACT_NAME
* The name must refer to a contact’s full name in the displayed contact list
* The name is case-sensitive and must match exactly

Examples:
* `list` followed by `view Marina Bay Sands` displays the contact with name 'Marina Bay Sands'
* `find Gardens By The Bay` followed by `view Gardens By The Bay` displays the contact with name 'Gardens By The Bay'

## Navigating WhereTourGo

### Navigating input history
When typing commands, you can use the up and down arrow keys to access previously entered inputs.
<div markdown="span" class="alert alert-warning">:exclamation: Caution:
Input history will be reset whenever you exit the app.
</div>

### Clicking on contacts
Click on any contact in the [List Panel](Link to box diagram thing) to view more information about the contact.
This is equivalent to using a [`view`](#viewing-a-contact-view) command.

## Undoing and Redoing
### Undoing operations: `undo`
Undoes the last change made to the list of contacts stored.

Format: `undo`

Examples :
* If you execute the command `delete Marina Bay Sands`, then execute the command `undo` , the Marina Bay Sands contact will be restored

### Redoing operations: `redo`
Redoes the last change undone on the list of contacts stored.

Format: `redo`

Examples :
* If you execute the command `delete Marina Bay Sands`, then execute the command `undo` , the Marina Bay Sands contact will be restored.
  If you then execute `redo` , the Marina Bay Sands contact will be deleted again
<div markdown="span" class="alert alert-primary">:bulb: Tip:
Only commands that directly change the stored data can be redone and undone. These commands include add, delete, edit, sort and clear.
</div>

## Managing Data
### Exporting data: `export`

Exports all the contacts from the contact list to a well-formatted `.txt` file.

Format: `export`
* Exports the specified contact from the contact list
* The text file will be written with the path `data/export.txt`.

Format: `export INDEX`

* Exports the contact at the specified INDEX
* The index refers to the index number shown in the displayed contact list
* The index must be a positive integer 1, 2, 3, ...

Examples:
* `list` followed by `export 2` exports the 2nd contact in the contact list
* `find Mandarin Oriental` followed by `export  1` exports the 1st contact in the results of the find command

ADD A SCREENSHOT HERE !!!!!!!!!!!!!!!!!!!!!!! (show exported file?)

### Clearing all entries: `clear`

Clears all entries from the contact list.

Format: `clear`

### Saving the data

WhereTourGo data is saved in the hard disk automatically after any command that changes the data. You do not need to save your changes manually. :)

### Editing the data file

WhereTourGo data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: Caution:
If your changes to the data file makes its format invalid, WhereTourGo will discard all data and start with an empty data file at the next run.
</div>

## Help

### Viewing help: `help`

Open the User Guide in your default Browser.
Format: `help`
<div markdown="span" class="alert alert-warning">:exclamation: Caution:
If the User Guide cannot be opened in the Browser, a link to the User Guide will be provided. 
</div>
Please refer to the FAQ for more information.
### Undoing operations: `undo`
Undoes the last change made to the list of contacts stored.

### Displaying commands: `cmd`

Open the command list in your default Browser.
Format: `cmd`
<div markdown="span" class="alert alert-warning">:exclamation: Caution:
If the command list cannot be opened in the Browser, a link to the User Guide will be provided. 
</div>

Please refer to the FAQ for more information.


### Exiting the program: `exit`

Exits the program.

Format: `exit`




 
--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WhereTourGo home folder.

**Q**: Why does cmd/help command not work as intended?
**A**:
 
--------------------------------------------------------------------------------------------------------------------

# Command summary

Action | Format, Examples
--------|------------------
**Add** | `add c/CATEGORY_CODE n/CONTACT_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add c/att n/Singapore Flyers p/92345678 e/123@example.com a/30 Raffles Ave, Singapore 039803 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`<br> `delete n/CONTACT_NAME`<br> e.g., `delete n/Marina Bay Sands`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/Adventure Cove e/adventurecove@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find hotel sands`
**List** | `list`
**Filter** | `filter c/CATEGORY`<br> e.g.,`filter c/fnb`
**Sort** | `sort name` or `sort rating`
**View** | `view INDEX <br> e.g., `view 1`  view n/CONTACT_NAME<br> e.g., `view n/Marina Bay Sands`
**Undo** | `undo`
**Redo** | `redo`
**Export** | `export` or `export INDEX` <br> e.g., `export 2`
**Contact Summary** | `sum`
**Command Summary** | `cmd`
**Help** | `help`
**Exit** | `exit`
