---
layout: page
title: User Guide
---
## Overview <br>
Notor is a desktop application for mentors to keep tabs on their mentees, **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type faster than the average typing speed, Notor allows you to take notes quickly and efficiently, while keeping them in an easy to reference format, which is vital if you are taking notes during meetings with mentees.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start
### 1. Setup
Ensure you have Java `11` or above installed in your computer. You can install Java `11` from [here](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html).
### 2. Installing the project
Download the latest `notor.jar` [here](), and copy the file to the folder you want to use as the _home folder_ for your **Notor**.
### 3. Running the application
Double-click the file to start the application. If you have set up Java `11` correctly, the application should open, and the GUI similar to below should appear in a few seconds. 
Note how the app contains some sample data. <br> 
![Ui](images/Ui.png)


### 4. Try Running Examples!
Type the command in the command box and press Enter to execute it. e.g. Typing **help** and pressing Enter will 
open the help window. <br>
Some example commands you can try: 

* **`person /list`** : Lists all contacts.
* **`person 3 /delete`** : Deletes the 3rd contact (contact with index number `3`) shown in the current list.
* **`person User /create`** : Creates a person named `User`.
* **`group 1 /create t:Students`** : Creates a group at index 1` tagged with `Students`.
* **`person 3 /add g:Orbital`** : Adds the person with index `3` to the group `Orbital`.
* **`group /list`** : Lists all groups.
* **`group 1 /note`** : Edits the group note for the group with index number `1`.
* **`group 1 /create n:Artemis`** : Creates a subgroup `Artemis` inside the group with index number `1`.
* **`group 1 /untag t:Students`** : Removes the tag `Students` from the group with index number `1`.
* **`clear`** : Deletes all contacts.
* **`exit`** : Exits the application. <br>

Refer to the [Features](#Features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `person /create n:NAME`, `NAME` is a parameter which can be used as `/create n:John Doe`.

* Round brackets `()` refer to COMPULSORY arguments.<br>
  e.g `g:(GROUP_NAME)` means that the group name must be entered a that position.
  
* Items in square brackets are optional.<br>
  e.g `n:NAME [g:GROUP_NAME]` can be used as `n:Elton g:Orbital` or as `n:Elton`.

[comment]: <> (MAY DELETE TAGS)
* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

[comment]: <> (Check if this is actually the correct format!!)
* Parameters can be in any order.<br>
  e.g. if the command specifies `g:GROUP_NAME sg:SUBGROUP_NAME`, `sg:SUBGROUP_NAME g:GROUP_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p:12341234 p:56785678`, only `p:56785678` will be taken.)

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## General Commands

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Clearing all entries : `clear`

Clears all entries from Notor. Be warned; data will be deleted and **will not be saved**. The intended use of this 
command is to clear the dummy starting data, but you can also use it to reset your Notor from within the program.

Format: `clear`
* Take care not to confuse this command with the more specific`/clearnote` command.

#### Exiting the program : `exit`

Exits the program and saves your data.

Format: `exit`

## Filtering with Notor

Sometimes, you will want to view all people, groups, subgroups, or tags to understand what you have saved in your Notor. At other times, you will want to find those which fit into certain parameters.

### List all ___: `list`
List can show all persons, groups, subgroups, or tags which you have added to Notor. Ensure you have one and only one parameter which determines what is to be shown.

**_Show all people_**

Format: `list /people`<br>
Advanced user Format: `list /p`

**_Show all groups_**

Format:  `list /group`<br>
Advanced user Format: `list /g`

**_Show all subgroups_**

Format: `list /subgroup g:GROUP_NAME`<br>
Advanced user Format: `list /sg g:GROUP_NAME`
Examples:

* `list /subgroup g:Orbital`
* `list /sg g:Orbital`

### Find a person : `find`

Find allows you to obtain the results that match with the keyword specified. You can filter in this way on people, groups, and subgroups. In addition, you may add additional parameters to your search, in order to narrow the search further.

**_Finding people_**
Format: `list /person n:KEYWORD [g:GROUP_NAME]`<br>
Advanced user Format:  `find /p n:KEYWORD [g:GROUP_NAME]`

* Find all people whose metadata matches the `KEYWORD` in that particular field. 
* Filter based on the group if `GROUP_NAME` is specified. For example, if you specify `g:Final Year Project`, you will only find people who belong to the group `Final Year Project`.
* The keyword must not include backslash (`/`) or underscore (`_`).

Examples:

* `find /person n:Elton g:Orbital Team 1`
* `find /p n:John`

### Find a group : `find`

Find all the groups with the keyword specified.

Format:  `find /group g:KEYWORD`
Advanced user Format: `find /g g:KEYWORD`

* Find all the group that matches the `KEYWORD`.
* The keyword must not include backslash (`/`) or underscore (`_`).

Examples:

* `find /group g:Orbital_Team_1`
* `find /g g:W08`

### Find a subgroup: `find`

Find all the subgroups with the group and subgroup name specified.

Format: `find /subgroup sg:KEYWORD g:GROUP_NAME`<br>
Advanced user Format: `find /sg sg:KEYWORD g:GROUP_NAME`

* Find all the subgroups that matches the `KEYWORD` in `GROUP_NAME`
* The keyword must not include backslash (`/`) or underscore (`_`).

Examples:

* `find /subgroup sg:W08 g:CS2103T`
* `find /subgroup sg:Artemis g:Orbital`

## Working with people
The base functionality of Notor is to allow you to mantain notes on people who you mentor. These are the commands you can use with the `person` prefix to manage your contacts.

### Adding a person: `person /create`

Creates a person.

Format: `person (INDEX) /create n:NAME [p:phone] [e:email] [g:GROUP_NAME]`<br>
Advanced user Format: `p (INDEX) /c n:NAME [p:phone] [e:email] [g:GROUP_NAME]`

* Creates a person with the `NAME`.
* Optional arguments:
  * 
  * `GROUP_NAME`: name of the group to add the user to.

Examples:

* `person /create n:John Lim g:CS2103T`
* `p /c n:Mary`

### Adding a person to a group or subgroup: `person /add`

Adds a person to a specified group.

Format: `person NAME /add g:GROUP_NAME [sg:SUP_GROUP_NAME]`<br>
Advanced user Format: `p  NAME /a g:GROUP_NAME [sg:SUP_GROUP_NAME]`

* Adds a person with the NAME to GROUP_NAME.
* Optional arguments:
* `SUB_GROUP_NAME`: name of the subgroup to add the user to.

Examples:

* `person John /add Lim g:CS2103T`
* `p Mary /a g:CS2103T sg:W08`
* 
### Deleting a person : `person /delete`

Deletes an existing person.

Format: `person /delete n:NAME`<br>
Advanced user Format: `p /d n:NAME`

* Deletes an existing person with the `NAME`.

Examples:

* `person /delete n:John Lim `
* `p /d n:Mary`

### Editing a person : `person /edit`

Edit an existing person's data.

[comment]: <> (I believe you should be able to edit more than name... Need to edit)
Format: `person (INDEX) /edit [n:NAME] [p:PHONE] [e:EMAIL]`<br>
Advanced user Format:`p (INDEX) /e [n:NAME] [p:phone] [e:email]`

* Edits the person at the index `INDEX` and replaces the fields specified with the new parameters.
* Please specify at least one field to be edited.

Examples:

* `person John Lim /edit n:John Cena`
* `p Mary /e n:Little Lamb`

### Removing a person from group or subgroup : `person /remove`

Removes an existing person from a group or subgroup.

Format: `person NAME /remove g:GROUP_NAME [sg:SUB_GROUP_NAME]`<br>
Advanced user Format:`p NAME /r g:GROUP_NAME [g:SUB_GROUP_NAME]`

* Removes an existing person with the `NAME` from a `GROUP_NAME`.
* Optional Argument:
    * `SUB_GROUP_NAME`: Removes an existing person with the name `NAME` from a `SUB_GROUP_NAME` in `GROUP_NAME`.

Examples:

* `person John Lim /remove g:CS2103T`
* `p John Lim /r g:CS2103T sg:W08`

### Taking notes for a person : `person /note`

Pops up a note window to take note for an existing person.

Format: `person NAME /note`<br>
Advanced user Format:`p NAME /n`

* Pops up a note window for an existing person with the `NAME` to take note.

Examples:

* `person John Lim /note`
* `p John Lim /n`

## Working with groups/subgroups
:warning: **These commands will work only will only work when groups or subgroups are listed.** :warning:
### Creating a group: `group (GROUP_NAME) /create`

Creates a group.

Format: `group (GROUP_NAME) /create`<br>
Advanced user Format: `g (GROUP_NAME) /c`

* Creates a new group with the name `GROUP_NAME`.
* The new group must not have a same name with other existing groups.
* The new group's name must not include backslash (`/`) or underscore (`_`).

Examples:

* `group CS2103T /create` will create a new group called CS2103T.
* `g CS2103T /c`

### Deleting a group: `group (INDEX) /delete`

Deletes an existing group.

Format: `group (INDEX) /delete`<br>
Advanced user Format: `g (INDEX) /d`

* Deletes the group at the index specified.

Examples :

* `group 1 /delete` will delete the group at index 1.
* `g 1 /d`

### Editing a group: `group (INDEX) /edit [n:NEW_NAME]`

Edits the name of an existing group.

Format: `group (INDEX) /edit [n:NEW_NAME]`<br>
Advanced user Format: `group (INDEX) /edit [n:NEW_NAME]`

* Renames an existing group at the index specified to `NEW_NAME` .
* The new group must not have a same name with other existing groups.
* The new group's name must not include backslash (`/`) or underscore (`_`).

Examples :

* `group 1 /edit n:CS2101` will rename the group at index 1 to CS2101.
* `g 1 /e CS2101`

### Creating a subgroup: `group (INDEX) /create n:SUBGROUP_NAME`
Creates a new subgroup. **This command only works when group are listed and not when subgroups are listed.**

Format: `group (INDEX) /create n:SUBGROUP_NAME`<br>
Advanced user Format: `g (INDEX) /c n:SUBGROUP_NAME`

* Creates a new subgroup of group at the index specified with the name `SUBGROUP_NAME`.
* The new subgroup must not have a same name with other existing subgroups in the same group.
* The new subgroup's name must not include backslash (`/`) or underscore (`_`).

Examples :

* `group 1 /create n:Artemis` will create a new subgroup Artemis in group at index 1.
* `g 1 /create n:Artemis`


### Adding notes on a group: `group (INDEX) /note`

Add notes on a group and saves the time when the note is added.

Format: `group (INDEX) /note`<br>
Advanced user Format: `group (INDEX) /note`

* Add notes on a group at the index specified.
* Entering the command will lead to a popup window where the user can edit notes for group at that index`.
* The time when the note is edited will be saved.

Examples :

* `group 1 /note` will prompt a popup window where the user can edit the notes for group at index 1.
* `g 1 /n`

### Adding tags to a group: `group (INDEX) /tag [t:TAG1,TAG2,...]`

Add tags to a group.

Format: `group (INDEX) /tag [t:TAG1,TAG2,...]`<br>
Advanced user Format: `g (INDEX) /t [t:TAG1,TAG2,...]`

* Add tags to a group at the index specified.
* The tags must not have a same name with other existing tags in the same group.

Examples :

* `group 1 /tag t:orbital,CS2103` add tags orbital and CS2103 to group 1.
* `g 1 /t t:orbital,CS2103`

### Untag tags from a group: `group (INDEX) /untag [t:TAG1,TAG2,...]`

Untag tags from a group.

Format: `group (INDEX) /untag [t:TAG1,TAG2,...]`<br>
Advanced user Format: `g (INDEX) /u [t:TAG1,TAG2,...]`

* Remove tags from a group at the index specified.

Examples :
* `group 1 /untag t:orbital,CS2103` remove tags orbital and CS2103 to group 1.
* `g 1 /ut t:orbital,CS2103`

### Clear tags from a group: `group (INDEX) /cleartags`

Clear tags from a group.

Format: `group (INDEX) /cleartags`<br>
Advanced user Format: `g (INDEX) /ct`

* Clear all tags from a group at the index specified.

Examples :

* `group 1 /cleartags` clears all tags from group 1.
* `g 1 /ct`

## Miscellaneous information

### Saving the data

Notor data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

Notor data are saved as a JSON file `[JAR file location]/data/Notor.json`. Advanced users are welcome to update data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Notor will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Notor home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary
Round brackets `()` refer to COMPULSORY arguments.
Square brackets `[]` refer to optional arguments.<p>
*TODO: Make command action words below link to their entries above.*

### Person
**Note**:
* For the **Create** and **List** commands, if you want to automatically add them to a group, please use the **List** command to make sure the `Group` you want to
  use the command on is displayed before using them via `GROUP_INDEX`.
* For the **Add** and **Remove** commands, please make sure that the `GROUP_NAME` is typed exactly as how it is spelt on
  the card.

Action                    | Format                                                                        | Advanced Format
--------------------------|-------------------------------------------------------------------------------|--------------------------------------------------------------------
**Create**                | `person (NAME) /create [p:PHONE] [e:EMAIL] [t:TAG1,TAG2,...] [g:GROUP_INDEX]` | `p (NAME) /c [p:phone] [e:email] [t:TAG1,TAG2,...] [g:GROUP_INDEX]`
**Edit**                  | `person (INDEX) /edit [n:NAME] [p:PHONE] [e:EMAIL]`                           | `p (INDEX) /e [n:NAME] [p:phone] [e:email]`
**Delete**                | `person (INDEX) /delete`                                                      | `p (INDEX) /d`
**Add**                   | `person (INDEX) /add (g:GROUP_NAME) `                                         | `p (INDEX) /a (g:GROUP_NAME)`
**Remove**                | `person (INDEX) /remove (g:GROUP_NAME) `                                      | `p (INDEX) /r (g:GROUP_NAME)`
**Note**                  | `person (INDEX) /note`                                                        | `p (INDEX) /n`
**Tag**                   | `person (INDEX) /tag [t:TAG1,TAG2,...]`                                       | `p (INDEX) /t [t:TAG1,TAG2,...]`
**Untag**                 | `person (INDEX) /untag [t:TAG1,TAG2,...]`                                     | `p (INDEX) /u [t:TAG1,TAG2,...]`
**Clear Tags**            | `person (INDEX) /cleartags`                                                   | `p (INDEX) / ct`
**List Persons**          | `person /list`                                                                | `p /list`
**List Persons in Group** | `person [INDEX] /list`                                                        | `p [INDEX] /l`
**Find**                  | `person /find (n:QUERY)`                                                      | `p /f (n:QUERY)`

### Group

Action                 | Format                                         | Short Format
-----------------------|------------------------------------------------|---------------------------------------
**Create Group**       | `group (GROUP_NAME) /create [t:TAG1,TAG2,...]` | `g (GROUP_NAME) /c  [t:TAG1,TAG2,...]`
**Create Subgroup**    | `group (INDEX) /create n:SUBGROUP_NAME`        | `g (INDEX) /c n:SUBGROUP_NAME`
**Edit**               | `group (INDEX) /edit [n:NEW_NAME]`             | `g (INDEX) /e [n:NEW_NAME]`
**Delete**             | `group (INDEX) /delete`                        | `g (INDEX) /d`
**Note**               | `group (INDEX) /note`                          | `g (INDEX) /n`
**Tag**                | `group (INDEX) /tag [t:TAG1,TAG2,...]`         | `g (INDEX) /t [t:TAG1,TAG2,...]`
**Untag**              | `group (INDEX) /untag [t:TAG1,TAG2,...]`       | `g (INDEX) /u [t:TAG1,TAG2,...]`
**Clear Tags**         | `group (INDEX) /cleartags`                     | `g (INDEX) / ct`
**List Groups**        | `group /list`                                  | `g /l`
**List Out Subgroups** | `group (INDEX) /list`                          | `g (INDEX) /l`
**Find**               | `group /find (n:QUERY)`                        | `g /f (n:QUERY)`

**Note**: for the **List** command, the `INDEX` argument can be either a `Group` or a `Person`, depending on what you
have listed after using `person /list` or `group /list`.

Action   | Format              | Short Format
---------|---------------------|---------------
**List** | `tag [INDEX] /list` | `t [INDEX] /l`

### General

Action    | Format  | Advanced Format
----------|---------|----------
**Help**  | `help`  | `h`
**Exit**  | `exit`  | `e`
**Clear** | `clear` | `c`
