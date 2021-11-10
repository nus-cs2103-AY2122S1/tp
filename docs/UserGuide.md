---
layout: page
title: User Guide
---

<img src = "https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/logo.jpeg?raw=true" align = "left" width="100" height="100" style="margin-right: 1.25em">
<div>
  
  <h1> CohortConnect v1.4 </h1>
  
  <b> Type. Explore. Connect. </b>
  
</div>

<br><br>

CohortConnect is an advanced desktop address book which facilitates networking among Computer Science (CS) students. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

With advanced features for managing large groups of contacts, CohortConnect is intended for use in a university setting. At the start of the semester, professors will distribute `CSV` or `JSON` files containing a list of students. Instantly load them into CohortConnect with a single **Import** command. With data collected from students before the semester, our **Find A Buddy** feature helps you find potential groupmates by leveraging GitHub’s metadata using a proprietary algorithm. In the **Events** tab, you can identify events and hackathons that your peers will be attending.

## Table of Contents
* Table of Contents
{:toc}

## Purpose

This User Guide is intended for basic to advanced users of CohortConnect. It provides sufficient information for users to set up the application and learn all its features.

## Prerequisites

1. Basic knowledge about `CSV` and `JSON` type file formats.
2. Basic knowledge about Technical Terms like `CLI`, `UI` and `JAR`.
3. `Java 11` Installed on the System.

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `CohortConnect.jar` release from [here](https://github.com/AY2122S1-CS2103T-T10-1/tp/releases).
3. Place `CohortConnect.jar` in an empty folder.
4. Double-click the jar file to start the program.
5. The UI would look like this:

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/WelcomeSplashScreenUi.png?raw=true">
</p>

> * Welcome Splash Screen

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/ProfileSetUpWindow.png?raw=true">
</p>

> * This Window is only displayed once, when the Student launches the App for the first time.
6. Enter Your Name in the `Name Field`.
7. Enter Your __VALID__ Telegram Handle in the `Telegram Field`.
8. Enter Your __VALID__ GitHub Username in the `GitHub Field`.

<div markdown="span" class="alert alert-primary">

:bulb: TIP:
<br/>

1. Telegram Handle Conventions:  
• Handles can use a-z, 0-9 and underscores.
<br/>
• Handles are case-insensitive.
<br/>
• Handles must be at least five characters long, and maximum is 39 characters.
<br/>
<br/>  
2. GitHub Username Conventions:
<br/>
• GitHub username may only contain alphanumeric characters or hyphens.
<br/>
• GitHub username cannot have multiple consecutive hyphens. 
<br/>
• GitHub username cannot begin or end with a hyphen. 
<br/>
• Maximum is 39 characters.
<br/>

</div>

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/MainWindow.png?raw=true">
</p>

> * Main Window.

## User Interface

![User Interface](images/UiLabelled.png)

1. **Menu Bar**: Contains links and keyboard shortcuts for navigating to other UI components.
2. **Profile Window**: Opens the Profile Window, where users can see the details they have entered during profile set up.
3. **Events Icon**: Switches to the events tab.
4. **Command Box**: A text field for users to enter their commands and view previous commands command-line style.
5. **Result Box**: Displays the results of commands entered into the Command Box.
6. **Tab Switcher**: Shows all tabs, highlights the current tab, and switches between tabs by clicking or keyboard shortcuts.
7. **Contact List**: Shows all contacts in a scrollable list.
8. **Contact Details**: Shows all details of the currently selected contact.

## Keyboard Mappings

1. <kbd>⌘</kbd> + <kbd>1</kbd>: To Switch to Contacts Tab.
2. <kbd>⌘</kbd> + <kbd>2</kbd>: To Switch to Favorites Tab.
3. <kbd>⌘</kbd> + <kbd>3</kbd>: To Switch to Events Tab.
4. <kbd>⌘</kbd> + <kbd>4</kbd>: To Switch to Find a Buddy Tab.
5. <kbd>⌘</kbd> + <kbd>P</kbd>: To Launch the Profile Window.
6. <kbd>↑</kbd>: To Retrieve the Last Entered Command (Similar to Terminal).
7. <kbd>↓</kbd>: To Retrieve the Next Entered Command (Similar to Terminal).
8. <kbd>F1</kbd>: To Launch the Help Window.

<div markdown="span" class="alert alert-primary">
:bulb: TIP: <kbd>⌘</kbd> for Mac = <kbd>Ctrl</kbd> for Windows
</div>

## Features

Notes about command format:

- `[x/xxx]` refers to an optional field

<div markdown="span" class="alert alert-primary">
:bulb: TIP: In the Command Box, use <kbd>↑</kbd> and <kbd>↓</kbd> arrow keys to navigate the command history.
</div>

### Adding a new Student - `add`

Adds a Student to the Address Book.

Format:

```
add n/<NAME> te/<TELEGRAM> g/<GITHUB> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>]
[t/<TAG>]
```

Example:

```
add n/John Doe te/johndoe g/john-doe p/98765432 e/johnd@eg.com
a/John Street, Blk 12, #01-01
```

### Delete a Student - `delete`

Deletes the specified Student Contact from the Address Book.

Format:

```
delete <INDEX>
```

Example:

```
delete 7
```

### Edit a Student - `edit`

Edits an existing Student Contact in the Address Book.

Format: 

```
edit <INDEX> [n/<NAME>] [te/<TELEGRAM>] [g/<GITHUB>] [p/<PHONE>] [e/<EMAIL>]
[a/<ADDRESS>] [t/<TAG>]
```

Example:

```
edit 1 n/John te/john_123 t/TA
```

### Edit your Profile - `edit profile`

Edits the Profile linked to the Address Book.

Format:

```
edit profile [n/<NAME>] [te/<TELEGRAM>] [g/<GITHUB>]
```

Example:
```
edit profile te/bob_osum
```

### Find a Student - `find`

Finds students contacts whose names, Telegram handles, GitHub usernames or assigned tags contain any of the given keywords.

Format 1: 
```
find <NAME> [MORE_NAMES]
```


* Searches for matching names

  **:information_source: Name must contain only alphabetical characters.**<br>
* Matches contact names with the **exact** keyword or those that contain the keyword as substring. e.g. `find al` will return <b><u>Al</u></b>ex Yeoh as well as Roy B<b><u>al</u></b>akrishnan
* The search **is case-insensitive**.  e.g. alex will match Alex
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

Examples:

* Search by single name: `find alex`
* Search by multiple names: `find ai bob dom` 

<br>

Format 2:
```
find <TAG> [MORE_TAGS]
```


* Searches for contacts with matching tags.
* Matches students with the **exact** tag name. e.g. `find t/friend` will only display student contacts with the tag `friend` and not `friends`.
* The search **is case-insensitive**. e.g `find t/Friends` will match contacts with the tag `friends` too.

Examples:

* Search by single tag: `find t/classmates` 
* Search by multiple tags: `find t/friends neighbours`

<br>

Format 3: 
```
find te/<TELEGRAM_HANDLE> [MORE_TELEGRAM_HANDLES]
```


* Searches for a student contact by telegram handle.
* Matches student contacts with the exact Telegram handle or Telegram handle that contain the keyword as a substring. e.g `find te/Al` will display student contacts with the Telegram handles `al_x1` and `randall_xo`.
* The search **is case-insensitive**. e.g `find te/Al` will find the contact with the Telegram handle `alex` too.

Examples:

* Search by single Telegram handle: `find te/dY` 
* Search by multiple Telegram handles: `find te/bob al_x1` 

<br>

Format 4: 
```
find g/GITHUB_USERNAME [MORE_GITHUB_USERNAMES]
```

* Searches for a student contact by GitHub username.
* Matches student contacts with the exact GitHub username or GitHub username that contain the keyword as a substring. e.g `find g/ai` will display student contacts with the GitHub usernames `ai-coder` and `kaira1208`.
* The search **is case-insensitive**. e.g `find g/Al` will find the contact with the github username `Alex`.

Examples:

* Search by single GitHub username: `find g/dY` 
* Search by multiple GitHub usernames: `find g/ai-coder kaira1208`

<h3 id = "Show"> </h3>

### Show a Student Contact - `show`

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

### Exporting Student Contacts - `export`

You can export your entire address book to a specified `JSON` or `CSV` file. This can be used to share contacts with others, or to store contacts in an external format. 

<div markdown="span" class="alert alert-primary">
:bulb: TIP: `CSV` files can be opened and edited with Excel.
</div>

The exported file will be in the same folder as the application. Exporting will not overwrite existing files.

Format 1:

```
export <FILENAME>.json
```

Example 1:

```
export Friends.json
```

Format 2:

```
export <FILENAME>.csv
```

Example 2:

```
export Friends.csv
```

### Importing Student Contacts - `import`

You can import contacts from a specified `JSON` or `CSV` file, saving you the hassle of adding contacts individually. This can be used to load the module contacts provided at the start of the semester, or any other contacts shared with you.

<div markdown="span" class="alert alert-primary">
:bulb: TIP: `CSV` files can be opened and edited with Excel.
</div>

The import file must be in the same folder as the application. Importing will not remove existing contacts, and ignores duplicate contacts.

Format 1:

```
import <FILENAME>.json
```

Example 1:

```
import CS2103T.json
```

Format 2:

```
import <FILENAME>.csv
```

Example 2:

```
import CS2103T.csv
```

### Setting a Contact as Favorite - `fav`

Helps to set a particular Student Contact as a Favorite only if the contact has not been favorited.

Format:

```
fav <INDEX>
```

Example:

```
fav 1
```

### Setting a Contact as Unfavorite - `unfav`

Helps to set a particular Student Contact as an Unfavorite only if the contact has been favorited.

Format:

```
unfav <INDEX>
```

Example:

```
unfav 1
```

### Displaying the Help Window - `help`

Displays the Help Window, which consists of a list of possible Commands, and a link to this UserGuide.

Format:

```
help
```

<p align="center">
<img src="https://github.com/AY2122S1-CS2103T-T10-1/tp/blob/master/docs/images/HelpWindowUi.png?raw=true">
</p>

### Listing All Student Contacts - `list`

Lists all the Student Contacts present.

Format:

```
list
```

### Deleting All Student Contacts - `clear`

Deletes all the Student Contacts present.

Format:

```
clear
```

<div markdown="span" class="alert alert-danger">
:exclamation: DANGER: Use With Caution!
</div>

### Open Telegram of Current Contact

Opens the current contact's Telegram link in your default web browser. If you have Telegram installed on your device, you will be redirected to the application.

<div markdown="span" class="alert alert-primary">
:bulb: TIP: Select a contact with `show <INDEX>` or `show <NAME>`.
</div>

Format:

```
te
```

### Open GitHub of Current Contact

Opens the current contact's GitHub profile in your default web browser.

Format:

```
g
```

### Retrieve Command History - <kbd>↑</kbd> / <kbd>↓</kbd>

Similar to a Command Line Interface, <kbd>↑</kbd> retrieves the previous command, while <kbd>↓</kbd> retrieves the next command.

### Exiting the App - `exit`

Exits the App.

Format:

```
exit
```

## FAQ

Q: On typing and running commands, the error message "XXXX" is shown, what shall I do?

A: Please **strictly** follow the command format to avoid such issues.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Feature | Command(s) |
| ------ | ------ |
| Add a new Student | `add n/<NAME> te/<TELEGRAM> g/<GITHUB> [p/<PHONE>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]` |
| Delete All Student Contacts Present | `clear` |
| Delete a Student | `delete <INDEX> ` |
| Exit the App | `exit` |
| Edit a Student | `edit <INDEX> [n/<NAME>] [te/<TELEGRAM>] [g/<GITHUB>] [p/<PHONE>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>]` |
| Edit your Profile | `edit profile [n/<NAME>] [te/<TELEGRAM>] [g/<GITHUB>]` |
| Exporting Student Contacts | `export <FILENAME>.json` <br> `export <FILENAME>.csv` |
| Favorite a Student Contact | `fav <INDEX>` |
| Find a Student (by Name) | `find <STRING> ` |
| Find a Student (by Tag) | `find t/<TAG>` |
| Find a Student (by Telegram Handle) | `find te/<TELEGRAM>` |
| Import Student Contacts | `import <FILENAME>.json` <br> `import <FILENAME>.csv` |
| List all the Student Contacts Present | `list` |
| Show the Help Window | `help` |
| Show a Student Contact (by Name)| `show <NAME>` |
| Show a Student Contact (by Index) | `show <INDEX>` |
| Unfavorite a Student Contact | `unfav <INDEX>` |
