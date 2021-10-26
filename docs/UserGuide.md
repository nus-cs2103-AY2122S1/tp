---
layout: page
title: User Guide
---

<img src = "https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/logo.jpeg?raw=true" align = "left" width="100" height="100" style="margin-right: 1.25em">
<div>
  
  <h1> CohortConnect v1.2 </h1>
  
  <b> Type. Explore. Connect. </b>
  
</div>

<br><br>

CohortConnect is a desktop application for CS students to network, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Our app makes it easy to connect with like minded students in your module. Our **Find A Buddy** feature matches you with students who have similar interests by leveraging Github’s metadata using a proprietary algorithm.

## Table of Contents
 - [Purpose](#Purpose)
 - [Quick Start](#QuickStart)
 - [Features](#Features)
    - [Adding a new Student - add](#Add)
    - [Delete a Student - delete](#Delete)
    - [Edit a Student - edit](#Edit)
    - [Find a Student - find](#Find)
    - [Show a Student Contact - show](#Show)
    - [Importing Student Contacts - import ](#Import)
    - [Exporting Student Contacts - export](#Export)
    - [Setting a Contact as Favourite - fav](#SetFavourite)
    - [Setting a Contact as Unfavourite - unfav](#SetUnFavourite)
    - [Displaying the Help Window - help](#Help)
    - [Listing All Student Contacts - list](#List)
    - [Deleting All Student Contacts - clear](#Clear)
    - [Exiting the App - exit](#Exit)
 - [Coming Soon](#ComingSoon)
 - [FAQ](#FAQ)
 - [Command Summary](#CommandSummary)

<h2 id = "Purpose"> </h2>

## Purpose

This document is the User Guide of CohortConnect. It is intended to provide all the necessary information to use this software.

<h2 id = "QuickStart"> </h2>

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `CohortConnect.jar`  release from [here](https://github.com/AY2122S1-CS2103T-T10-1/tp/releases).
3. Place `CohortConnect.jar` in an empty folder.
4. Double click the jar file to start the program.
5. The UI would look like this:

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/WelcomeSplashScreenUi.png?raw=true">
</p>

> * Welcome Splash Screen

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/ProfileWindowUi.png?raw=true">
</p>

> * This Window is only displayed once, when the Student launches the App for the first time.

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/Ui.png?raw=true">
</p>

> * Main Window.
  
<h2 id = "Features"> </h2>

## Features

Notes about command format:

- `[x/xxx]` refers to an optional field

<h3 id = "Add"> </h3>

## Adding a new Student - `add`

Adds a Student to the Address Book.

Format:

```
add n/<NAME> te/<TELEGRAM> g/<GITHUB> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]
```

Example:

```
add n/John Doe te/@johndoe123 g/john-doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01
```

<h3 id = "Delete"> </h3>

## Delete a Student - `delete`

Deletes the specified Student Contact from the Address Book.

Format:

```
delete <INDEX>
```

Example:

```
delete 7
```

<h3 id = "Edit"> </h3>

## Edit a Student - `edit`

Edits an existing Student Contact in the Address Book.

Format: 

```
edit <INDEX> [n/<NAME>] [te/TELEGRAM] [g/GITHUB] [p/<PHONE>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]
```

Example:

```
edit 1 n/John te/john_123 t/TA
```

<h3 id = "Find"> </h3>

## Find a Student - `find`

Finds an existing Student Contact by name(s), tag(s), or telegram handle(s).

Format 1: 

```
find <NAME>
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

Format 3: 

```
find @<TELEGRAM>
```

Example 3:

```
find @alex_1 bobx2
```

<h3 id = "Show"> </h3>

## Show a Student Contact - `show`

Shows detailed information of a Student Contact. This can be done using the name 
of the contact or the index.

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

## Importing Student Contacts - `import`

Imports Student Contacts from the specified JSON file.

Format:

```
import filename.JSON
```

Example:

```
import CS2103T.JSON
```

<h3 id = "Export"> </h3>

## Exporting Student Contacts - `export`

Exports the Student Contacts to the specified JSON file.

Format

```
export filename.JSON
```

Example:

```
export Friends.JSON
```

<h3 id = "SetFavourite"> </h3>

## Setting a Contact as Favourite - `fav`

Helps to set a particular Student Contact as a Favourite.

Format

```
fav <INDEX>
```

Example:

```
fav 1
```

<h3 id = "SetUnFavourite"> </h3>

## Setting a Contact as Unfavourite - `unfav`

Helps to set a particular Student Contact as an Unfavourite, only if it
was set as a Favourite earlier.

Format

```
unfav <INDEX>
```

Example:

```
unfav 1
```

<h3 id = "Help"> </h3>

## Displaying the Help Window - `help`

Displays the Help Window, which consists of a list
of possible Commands, and a link to this UserGuide.

Format

```
help
```

Example:

```
help
```

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/HelpWindowUi.png?raw=true">
</p>

<h3 id = "List"> </h3>

## Listing All Student Contacts - `list`

Lists all the Student Contacts present.

Format

```
list
```

Example:

```
list
```

<h3 id = "Clear"> </h3>

## Deleting All Student Contacts - `clear`

Deletes all the Student Contacts present.

Format

```
clear
```

Example:

```
clear
```

> WARNING: Use With Caution!

<h3 id = "Exit"> </h3>

## Exiting the App - `exit`

Exits the App.

Format

```
exit
```

Example:

```
exit
```

<h2 id = "ComingSoon"> </h2>

## Coming Soon

1. Splash Screen
2. Optional Fields and Telegram handle
3. Find using Tags
4. Import and Export Student Details 
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
| Add a new Student | `add n/\<NAME> te/\<TELEGRAM> g/\<GITHUB> [p/\<PHONE>] [e/\<EMAIL>] [a/\<ADDRESS>] [t/\<TAG>]` |
| Delete All Student Contacts Present | `clear` |
| Delete a Student | `delete \<INDEX> ` |
| Exit the App | `exit` |
| Edit a Student | `edit \<INDEX> [n/\<NAME>] [te/\<TELEGRAM>] [g/\<GITHUB>] [p/\<PHONE>] [e/\<EMAIL>] [a/\<ADDRESS>] [t/\<TAG>]` |
| Exporting Student Contacts | `export filename.JSON` |
| Favourite a Student Contact | `fav \<INDEX>` |
| Find a Student (by Name) | `find \<STRING> ` |
| Find a Student (by Tag) | `find t/\<TAG>` |
| Find a Student (by Telegram Handle) | `find @\<TELEGRAM>` |
| Show the Help Window | `help` |
| Import Student Contacts | `import filename.JSON` |
| List all the Student Contacts Present | `list` |
| Show a Student Contact (by Name)| `show \<NAME>` |
| Show a Student Contact (by Index) | `show \<INDEX>` |
| Unfavourite a Student Contact | `unfav \<INDEX>` |
