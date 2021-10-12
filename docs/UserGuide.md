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
 - [Quick Start](#QuickStart)
 - [Features](#Features)
    - [Adding a new Contact - add](#Add)
    - [Delete a Contact - delete](#Delete)
    - [Edit a Contact - edit](#Edit)
    - [Find a Contact - find](#Find)
    - [Show a Contact - show](#Show) (Coming Soon)
    - [Importing Contacts - import ](#Import) (Coming Soon)
    - [Exporting Contacts - export](#Export) (Coming Soon)
  - [Coming Soon](#ComingSoon)
  - [FAQ](#FAQ)
  - [Command Summary](#CommandSummary)


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

```
add n/<NAME> te/<TELEGRAM> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]
```

Example:

```
add n/John Doe te/@johndoe123 p/98765432 e/johnd@example.com a/John street, block 123, #01-01
```

<h3 id = "Delete"> </h3>

## Delete a Contact - `delete`

Deletes the specified person from the address book.

Format:

```
delete <INDEX>
```

Example:

```
delete 7
```

<h3 id = "Edit"> </h3>

## Edit a Contact - `edit`

Edits an existing person in the address book.

Format: 

```
edit <INDEX> [n/<NAME>] [te/TELEGRAM] [p/<PHONE>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]
```

Example:

```
edit 1 n/John te/john_123 t/TA
```

<h3 id = "Find"> </h3>

## Find a Contact - `find`

Finds an existing contact using the given string or tag.

Format 1: 

```
find <STRING>
```

Example 1:

```
find John
```

Format 2: 

```
find t/<TAG>
```

Example 2:

```
find friends
```

<h3 id = "Show"> </h3>

## Show a Contact - `show`

Shows detailed information of a contact. This can be done using the name of the contact or the index.

Format 1: 

```
show <NAME>
```

Example 1:

```
show John Doe
```

If the exact name is not given, the show function will act similar to find and then you can pick out one contact from the given list.

Format 2: 

```
show <INDEX>
```

Example 2:

```
show 4
```

<h3 id = "Import"> </h3>

## Importing Contacts - `import`

Imports contacts from the specified JSON file.

Format:

```
import filename.JSON
```

Example:

```
import CS2103T.JSON
```

<h3 id = "Export"> </h3>

## Exporting Contacts - `export`

Exports contacts to the specified JSON file.

Format

```
export filename.JSON
```

Example:

```
export Friends.JSON
```

<h2 id = "ComingSoon"> </h2>

## Coming Soon

1. Splash Screen
2. Optional Fields and Telegram handle
3. Find using Tags
4. Import and Export Contacts 
5. Show Function to display details

<h2 id = "FAQ"> </h2>

## FAQ

Q: On typing and running commands, the error message "XXXX" is shown, what shall I do?

A: Please **strictly** follow the command format to avoid such issues.

--------------------------------------------------------------------------------------------------------------------

<h2 id = "CommandSummary"> </h2>

## Command summary

| Feature | Command(s) |
| ------ | ------ |
| Add a new Contact | add n/\<NAME> te/\<TELEGRAM> [p/\<PHONE>] [e/\<EMAIL>] [a/\<ADDRESS>] [t/\<TAG>] |
| Delete a Contact | delete \<INDEX> |
| Edit a Contact | edit \<INDEX> [n/\<NAME>] [te/\<TELEGRAM>] [p/\<PHONE>] [e/\<EMAIL>] [a/\<ADDRESS>] [t/\<TAG>] |
| Find a Contact (by Name) | find \<STRING> |
| Find a Contact (by Tag) | find t/\<TAG> |
| Show a Contact (by Name)| show \<NAME> |
| Show a Contact (by Index) | show \<INDEX> |
| Import Contacts | import filename.JSON |
| Exporting Contacts | export filename.JSON |
