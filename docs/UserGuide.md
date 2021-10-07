---
layout: page
title: User Guide
---

ClassMATE is a **desktop app for managing student contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ClassMATE can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `classmate.jar` from [here](https://github.com/AY2122S1-CS2103T-W15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ClassMATE.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`liststu`** : Lists all students.

   * `addstu n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/G06 atd/1 as/OP1` : Adds a student named `John Doe` to ClassMATE.

   * `deletestu 3` : Deletes the 3rd student shown in the current list.

   * **`clear`** : Deletes all students.

   * **`exit`** : Exits the app.

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

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a student: `addstu`

Adds a student to ClassMATE.

Format: `addstu n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS_CODE atd/ATTENDANCE as/ASSIGNMENT [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:
* `addstu n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/G06 atd/1 as/OP1`
* `addstu n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal c/G06 atd/0 as/OP1`

### Editing a student : `editstu`

Edits an existing student in ClassMATE.

Format: `editstu INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `editstu 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `editstu 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Viewing a student : `viewstu`

View a student's details in ClassMATE

Format: `viewstu INDEX`

* Views the student's details at specified `INDEX`
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2, 3, …​

Examples:

* `liststu` followed by `viewstu 2` shows second student in the student list.
* `findstu Betsy` followed by `viewstu 1` shows the 1st student in the results of the find command.

### Finding students by name: `findstu`

Finds students whose names contain any of the given keywords.

Format: `findstu KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`

Examples:
* `findstu John` returns `john` and `John Doe`
* `findstu alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a student : `deletestu`

Deletes the specified student from the student list.

Format: `deletestu INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `liststu` followed by `deletestu 2` deletes the 2nd student in the student list.
* `findstu Betsy` followed by `deletestu 1` deletes the 1st student in the results of the `findstu` command.

### Listing all students : `liststu`

Shows a list of all students in ClassMATE.

Format: `liststu [c/CLASS_CODE]`

* If the optional field is not provided, all students stored are listed. Otherwise, only students that belong to the field specified are listed.

Examples:

* `liststu c/G06` Lists all students stored in the class `G06`

### Adding a class : `addc`

Adds a class to ClassMATE.

Format: `addc c/CLASS_CODE s/SCHEDULE [t/TAG]…​`

* The schedule is stored as a String.

Examples:
* `addc c/G06 s/Tuesdays and Fridays 2-4pm`

### Deleting a class: `deletec`

Deletes a class from ClassMATE

Format: `deletec INDEX`

* Deletes the class at the specified INDEX.
* The index refers to the index number shown in the displayed list of classes.
The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listc` followed by `deletec 2` deletes the 2nd class in the list of classes.
* `findc G06` followed by `deletec 1` deletes the 1st class in the results of the `findc` command.

### Viewing a class

Views a class in ClassMATE

Format: `viewc INDEX`

* Views the class details at the specified INDEX.
* Details of a class includes students in the class and the class schedule.
* The index refers to the index number shown in the displayed list of classes.
* The index must be a positive integer 1, 2, 3, …​

Examples:

* `listc` followed by `viewc 2` shows the 2nd class in the displayed class list.

### Listing all classes : `listc`

Shows a list of all classes in ClassMATE.

Format: `listc`

### Finding classes by class codes: `findc`

Find classes by class codes.

Format: `findc KEYWORD [MORE_KEYWORDS]`

* The search is not absolute. e.g `G0` will match `G06`

Examples:
* `findc A02` returns `A02` if it exists
* `findc E` returns `E01`, `E02`, `E03`<br>

### Adding a group: `addsg` [Coming Soon]
Adds a group to ClassMATE

Format: `addsg n/NAME tp/TYPE g/GROUP_NAME [t/TAG]`

* Adds the student to a group in the class
* Type refers to the assignment that the group will work together for

Example:
* `liststu c/G06`shows that Betsy is a student in class G06.
  `addsg n/Betsy tp/OP1 g/A` then adds a student called Betsy to OP1 Group A in class G06

### Viewing a Group: `viewg` [Coming Soon]
View a group's details in ClassMATE

Format: `viewg INDEX`

* Views the group's details at the specified INDEX.
* The index refers to the index number shown in the displayed group list.
* The index must be a positive integer 1, 2, 3...

Examples:
* `listg n/G06` followed by `viewg 2` shows the 2nd group in the list of group in class G06

### Listing all groups : `listg` [Coming Soon]

Shows a list of all groups in a specific class in ClassMATE.

Format: `listg c/CLASS_CODE`

Examples:
* `listg c/G06` Lists all groups in the class `G06`

### Deleting a Group: `deleteg` [Coming Soon]
Delete a group from ClassMATE by their index in the group list

Format: `deleteg INDEX`

* Deletes the group at the specified INDEX.
* The index refers to the index number shown in the displayed group list.
* The index must be a positive integer 1, 2, 3...

Examples:
* `listg n/G06` followed by `deleteg 2` deletes the 2nd group in the list of group in class G06

### Clearing all students : `clear`

Clears all students from memory.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ClassMATE data is saved in the hard disk automatically after any command that changes the data. **There is no need to save manually.**

### Editing the data file

ClassMATE data are saved as a JSON file `[JAR file location]/data/classmate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ClassMATE will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClassMATE home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add student** | `addstu n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS_CODE atd/ATTENDANCE as/ASSIGNMENT [t/TAG]…​`<br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/G01 atd/1 as/OP1 t/nice`
**Edit student** | `editstu INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_CODE] [atd/ATTENDANCE] [as/ASSIGNMENT] [t/TAG]…​`<br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**View student** | `viewstu INDEX`<br> e.g., `liststu` followed by `viewstu 2`
**Find student** | `findstu KEYWORD [MORE_KEYWORDS]`<br> e.g., `findstu John`
**Delete student** | `deletestu INDEX`<br> e.g., `liststu` followed by `deletestu 3`
**List students** | `liststu [c/CLASS_CODE]`<br> e.g., `liststu c/G06`
**Add class** | `addc c/CLASS_CODE s/SCHEDULE [t/TAG]…​`<br> e.g., `addc c/G06 s/Tuesdays and Fridays 2-4pm`
**Delete class** | `deletec INDEX`<br> e.g., `listc` followed by `deletec 2`
**View class** | `viewc INDEX`<br> e.g., `listc` followed by `viewc 3
`**List all classes** | `liststu`
**Find class** | `findc KEYWORD [MORE_KEYWORDS]`<br> e.g., `findc A02`
**Clear all students** | `clear`
**Exit ClassMATE** | `exit`
