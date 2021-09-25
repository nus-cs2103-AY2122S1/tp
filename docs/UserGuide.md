---
layout: page
title: User Guide
---

<img src = "https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/logo.jpeg?raw=true" align = "left" width="100" height="100" style="margin-right: 20px">
<div>
  
  <h1> CohortConnect v1.2 </h1>
  
  <b> Type. Explore. Connect. </b>
  
</div>

<br><br>

CohortConnect is a desktop application for students, professors, and teaching assistants to connect, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface.

## Table of Contents
 - ### [Quick Start](#QuickStart)
 - ### [Features](#Features)
    - #### [Adding a new Contact - add](#Add)
    - #### [Delete a Contact - delete](#Delete)
    - #### [Edit a Contact - edit](#Edit)
    - #### [Find a Contact - find](#Find)
    - #### [Show a Contact - show](#Show)
    - #### [Importing Contacts - import](#Import)
    - #### [Exporting Contacts - export](#Export)
  - ### [FAQ](#FAQ)
  - ### [Command Summary](#CommandSummary)


<h2 id = "QuickStart"> </h2>

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `addressbook.jar`  release from [here](https://github.com/AY2122S1-CS2103T-T10-1/tp/releases).
3. Place `addressbook.jar` in an empty folder.
4. Using Terminal or Command Line, navigate to the folder containing `addressbook.jar`.
5. Execute `java -jar addressbook.jar`


<h2 id = "Features"> </h2>

## Features

Notes about command format:

- `[x/xxx]` refers to an optional field

<h3 id = "Add"> </h3>

## Adding a new Contact - `add`

Adds a person to the address book.

Format:

```lua
add n/<NAME> te/<TELEGRAM> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]
```

Example:

```lua
add n/John Doe te/@johndoe123 p/98765432 e/johnd@example.com a/John street, block 123, #01-01
```

<h3 id = "Delete"> </h3>

## Delete a Contact - `delete`

Deletes the specified person from the address book.

Format:

```bash
delete <INDEX>
```

Example:

```bash
delete 7
```

<h3 id = "Edit"> </h3>

## Edit a Contact - `edit`

Edits an existing person in the address book.

Format: 

```bash
edit <INDEX> [n/<NAME>] [p/<PHONE>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]
```

Example:

```bash
edit 1 n/John t/TA
```

<h3 id = "Find"> </h3>

## Find a Contact - `find`

Finds an existing contact using the given string or tag.

Format 1: 

```bash
find <STRING>
```

Example 1:

```bash
find John
```

Format 2: 

```bash
find t/<TAG>
```

Example 2:

```bash
find friends
```

<h3 id = "Show"> </h3>

## Show a Contact - `show`

Shows detailed information of a contact. This can be done using the name of the contact or the index.

Format 1: 

```lua
show <NAME>
```

Example 1:

```bash
show John Doe
```

If the exact name is not given, the show function will act similar to find and then you can pick out one contact from the given list.

Format 2: 

```lua
show <INDEX>
```

Example 2:

```bash
show 4
```

<h3 id = "Import"> </h3>

## Importing Contacts - `import`

Imports contacts from the specified JSON file.

Format:

```bash
import filename.JSON
```

Example:

```bash
import CS2103T.JSON
```

<h3 id = "Export"> </h3>

## Exporting Contacts - `export`

Exports contacts to the specified JSON file.

Format

```bash
export filename.JSON
```

Example:

```bash
export Friends.JSON
```

<h2 id = "FAQ"> </h2>

## FAQ

Q: On typing and running commands, the error message "XXXX" is shown, what shall I do?

A: Please **strictly** follow the command format to avoid such issues.

--------------------------------------------------------------------------------------------------------------------

<h2 id = "CommandSummary"> </h2>

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
