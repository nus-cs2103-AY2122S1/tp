---
layout: page
title: User Guide
---

NUS Mod Tracker is a **desktop app** for **NUS Computer Science (CS) students** to **manage their modules and track their academic progress.**
It is optimized for use via a Command Line Interface (CLI), while still having the benefits of a Graphical User 
Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `modtracker.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Mod Tracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all modules.

   * `add c/CS2103T t/Software Engineering d/Covers the main areas of software development n/4 tag/core` : Adds a module named `CS2103T` to the Mod Tracker.

   * **`delete`**`3` : Deletes the 3rd module shown in the current list.


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add m/MODULE`, `MODULE` is a parameter which can be used as `add m/GEQ1000`.

* Items in square brackets are optional.<br>
  e.g. `c/CODE [tag/TAG]` can be used as `c/CS2103T tag/core` or as `c/CS2103T`.
  
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/GEQ1000 m/GEQ1000`, only `m/GEQ1000` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [Coming soon]

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing current modules: `list`

Shows a list of all modules taken for the current semester.

Format: `list`

### Adding a module: `add`

Adds a module to the module tracker.

Format: `FORMAT: add c/CODE t/TITLE d/DESCRIPTION n/MC [tag/TAG]`
* Commands must follow the specified format, **no fields should be left blank**.
* **MC** must be a **positive integer** (1,2,3...).
* **CODE** must follow the NUSMods module code format.

Examples:
* `add c/CS2103T t/Software Engineering d/Covers the main areas of software development n/4 tag/core`


### Editing a module : `edit` [Coming soon]

Edits an existing module in the mod tracker.


### Deleting a module : `delete`

Deletes a module from the module tracker

Format: `delete INDEX`

* Deletes a module at the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `delete 2` deletes the 2nd module in the module tracker.



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add c/CODE t/TITLE d/DESCRIPTION n/MC [tag/TAG]` <br> e.g. `add c/ST2334 t/Probability and Statistics d/Introduces students to basic probability theory and statistical inference n/4`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Edit** [Coming soon] | `edit`
**List** | `list`
**Help** [Coming soon] | `help`
