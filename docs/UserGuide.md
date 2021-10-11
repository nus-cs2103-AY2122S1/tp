---
layout: page
title: User Guide
---

Notor is a desktop app for mentors to keep tabs on their mentees, **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Notor allows you to take notes quickly and efficiently, and keeping them in an easy to reference format, which is vital if you are taking notes during meetings with mentees.

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `notor.jar`.

3. Copy the file to the folder you want to use as the _home folder_ for your Notor.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#Features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n:NAME`, `NAME` is a parameter which can be used as `add n:John Doe`.

* Items in square brackets are optional.<br>
  e.g `n:NAME [g:GROUP_NAME]` can be used as `n:Elton g:Orbital` or as `n:Elton`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `g:GROUP_NAME sg:SUBGROUP_NAME`, `sg:SUBGROUP_NAME g:GROUP_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>

[comment]: <> (  Change this example e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.)

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### General Commands

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Clearing all entries : `clear`

Clears all entries from Notor.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Filtering with Notor

#### Listing all persons : `list`

Shows a list of all persons.

Format: `list /people`<br>
Advanced user Format: `list /p`

#### Listing all groups : `list`

Shows a list of all groups.

Format:  `list /group`<br>
Advanced user Format: `list /g`

#### Listing all subgroups of group : `list`

Shows a list of subgroups in group specified.

Format: `list /subgroup g:GROUP_NAME`<br>
Advanced user Format: `list /sg g:GROUP_NAME`
Examples:

* `list /subgroup g:Orbital`
* `list /sg g:Orbital`

#### Find a person : `find`

Find all the people with the keyword specified. Optionally, group can be specified too.

Format: `list /person n:KEYWORD [g:GROUP_NAME]`<br>
Advanced user Format:  `find /p n:KEYWORD [g:GROUP_NAME]`

* Find all the person that matches the `KEYWORD`.
* Filter based on the group if `GROUP_NAME` is specified.
* The keyword must not include backslash (`/`) or colon (`:`).

Examples:

* `find /person n:Elton g:Orbital Team 1`
* `find /p n:John`

#### Find a group : `find`

Find all the groups with the keyword specified.

Format:  `find /group g:KEYWORD`
Advanced user Format: `find /g g:KEYWORD`

* Find all the group that matches the `KEYWORD`.
* The keyword must not include backslash (`/`) or colon (`:`).

Examples:

* `find /group g:Orbital_Team_1`
* `find /g g:W08`

#### Find a subgroup: `find`

Find all the subgroups with the group and subgroup name specified.

Format: `find /subgroup sg:KEYWORD g:GROUP_NAME`<br>
Advanced user Format: `find /sg sg:KEYWORD g:GROUP_NAME`

* Find all the subgroups that matches the `KEYWORD` in `GROUP_NAME`
* The keyword must not include backslash (`/`) or colon (`:`).

Examples:

* `find /subgroup sg:W08 g:CS2103T`
* `find /subgroup sg:Artemis g:Orbital`

### Working with people

#### Adding a person: `person /create`

Creates a person.

Format: `person /create n:NAME [g:GROUP_NAME]`<br>
Advanced user Format: `p /c n:NAME [g:GROUP_NAME]`

* Creates a person with the `NAME`.
* Optional arguments:
* `GROUP_NAME`: name of the group to add the user to.

Examples:

* `person /create n:John Lim g:CS2103T`
* `p /c n:Mary`

#### Adding a person to a group or subgroup: `person /add`

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
#### Deleting a person : `person /delete`

Deletes an existing person.

Format: `person /delete n:NAME`<br>
Advanced user Format: `p /d n:NAME`

* Deletes an existing person with the `NAME`.

Examples:

* `person /delete n:John Lim `
* `p /d n:Mary`

#### Editing a person : `person /edit`

Edit an existing person's data.

[comment]: <> (I believe you should be able to edit more than name... Need to edit)
Format: `person NAME /edit n:NEW_NAME`<br>
Advanced user Format:`p NAME /e n:NEW_NAME`

* Edits an existing person with the name `NAME` and replaces the fields with the new parameters.

Examples:

* `person John Lim /edit n:John Cena`
* `p Mary /e n:Little Lamb`

### Removing a person from group or subgroup : `person /remove`

Removes an existing person from a group or subgroup.

Format: `person NAME /remove g:GROUP_NAME [sg:SUB_GROUP_NAME]`<br>
Advanced user Format:`p NAME /r g:GROUP_NAME [g:SUB_GROUP_NAME]`

* Removes an existing person with the `NAME` from a `GROUP_NAME`.
* Optional Agrument:
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

### Working with groups

### Creating a group: `group /create`

Creates a group.

Format: `group /create g:GROUP_NAME`<br>
Advanced user Format: `g /c g:GROUP_NAME`

* Creates a new group with the name `GROUP_NAME`.
* The new group must not have a same name with other existing groups.
* The new group's name must not include backslash (`/`) or colon (`:`).

Examples:

* `group /create g:CS2103T` will create a new group called CS2103T.
* `g /c g:Orbital`

### Deleting a group: `group /delete`

Deletes an existing group.

Format: `group /delete g:GROUP_NAME`<br>
Advanced user Format: `g /d g:GROUP_NAME`

Examples :

* `group /delete g:CS2103T` will delete the group CS2103T.
* `g /d g:Orbital`

### Editing a group: `group /edit`

Edits the name of an existing group.

Format: `group GROUP_NAME /edit g:NEW_NAME`<br>
Advanced user Format: `g GROUP_NAME /e g:NEW_NAME`

* Renames an existing group `GROUP_NAME` to `NEW_NAME` .
* The new group must not have a same name with other existing groups.
* The new group's name must not include backslash (`/`) or colon (`:`).

Examples :

* `group CS2103T /edit g:CS2101` will rename the group CS2103T to CS2101
* `g Orbital /e g:Orbital3`

### Creating a subgroup: `group GROUP_NAME /create`

Creates a new subgroup.

Format: `group GROUP_NAME /create sg:SUBGROUP_NAME`<br>
Advanced user Format: `g GROUP_NAME /c sg:SUBGROUP_NAME`

* Creates a new subgroup of `GROUP_NAME` with the name `SUBGROUP_NAME`.
* The new subgroup must not have a same name with other existing subgroups in the same group.
* The new subgroup's name must not include backslash (`/`) or colon (`:`).

Examples :

* `group Orbital /create sg:Artemis` will create a new subgroup Artemis in group Orbital.
* `g CS2103T /c sg:ip`

### Deleting a subgroup: `group GROUP_NAME /delete`

Deletes an existing subgroup from a group.

Format: `group GROUP_NAME /delete sg:SUBGROUP_NAME`<br>
Advanced user Format: `g GROUP_NAME /d sg:SUBGROUP_NAME`

* Deletes an existing subgroup of `GROUP_NAME` with the name `SUBGROUP_NAME`.

Examples :

* `group Orbital /delete sg:Artemis` will delete the subgroup Artemis from Orbital.
* `g CS2103T /d sg:ip`

### Editing a subgroup: `group GROUP_NAME /edit`

Edits the name of an existing subgroup from a group.

Format: `group GROUP_NAME:SUP_GROUP_NAME /edit sg:NEW_SUP_GROUP_NAME`<br>
Advanced user Format: `g GROUP_NAME:SUP_GROUP_NAME /e sg:SUB_GROUP_NAME`

* Renames an existing subgroup of `GROUP_NAME` with the name `SUP_GROUP_NAME` to `NEW_SUP_GROUP_NAME`.
* The new subgroup must not have a same name with other existing subgroups in the same group.
* The new subgroup's name must not include backslash (`/`) or colon (`:`).

Examples :

* `group g:Orbital:Artemis /edit sg:Apollo` will rename the subgroup Artemis to Apollo.
* `g g:CS2103T:tp /e sg:ip`

### Adding notes on a group: `group GROUP_NAME /note`

Adds notes on a group and saves the time when the note is added.

Format: ` group GROUP_NAME /note`<br>
Advanced user Format: `g GROUP_NAME /n`

* Entering the command will lead to a popup window where the user can edit notes for `GROUP_NAME`.
* The time when the note is edited will be saved.

Examples :

* `group Orbital /note sg:Team1` will prompt a popup window where the user can edit the notes for Orbital.
* `g CS2103T /n`


### Adding notes on a subgroup: `group GROUP_NAME /note`

Adds notes on a subgroup and saves the time when the note is added.

Format: ` group GROUP_NAME /note sg:SUB_GROUP_NAME`<br>
Advanced user Format: `g GROUP_NAME /n sg:SUB_GROUP_NAME`

* Entering the command will lead to a popup window where the user can edit notes for `SUB_GROUP_NAME`.
* The time when the note is edited will be saved.

Examples :
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
* For the **Create** and **List** commands, please use the **List** command to make sure the `Group` you want to
  use the command on is displayed before using them via `GROUP_INDEX`.
* For the **Add** and **Remove** commands, please make sure that the `GROUP_NAME` is typed exactly as how it is spelt on
  the card.


Action     | Format                                                                        | Advanced Format
-----------|-------------------------------------------------------------------------------|--------------------------------------------------------------------
**Create** | `person (NAME) /create [p:phone] [e:email] [t:TAG1,TAG2,...] [g:GROUP_INDEX]` | `p (NAME) /c [p:phone] [e:email] [t:TAG1,TAG2,...] [g:GROUP_INDEX]`
**Edit**   | `person (INDEX) /edit [n:NAME] [p:phone] [e:email]`                           | `p (INDEX) /e [n:NAME] [p:phone] [e:email]`
**Delete** | `person (INDEX) /delete`                                                      | `p (INDEX) /d`
**Add**    | `person (INDEX) /add (g:GROUP_NAME) `                                         | `p (INDEX) /a (g:GROUP_NAME)`
**Remove** | `person (INDEX) /remove (g:GROUP_NAME) `                                      | `p (INDEX) /r (g:GROUP_NAME)`
**Note**   | `person (INDEX) /note (NOTE)`                                                 | `p (INDEX) /n (NOTE)`
**Tag**    | `person (INDEX) /tag [t:TAG1,TAG2,...]`                                       | `p (INDEX) /t [t:TAG1,TAG2,...]`
**Untag**  | `person (INDEX) /untag [t:TAG1,TAG2,...]`                                     | `p (INDEX) /u [t:TAG1,TAG2,...]`
**List**   | `person /list [GROUP_INDEX]`                                                  | `p /l [GROUP_INDEX]`
**Find**   | `person /find (n:QUERY)`                                                      | `p /f (n:QUERY)`

### Group
Action     | Format                                         | Short Format
-----------|------------------------------------------------|---------------------------------------
**Create** | `group (GROUP_NAME) /create [t:TAG1,TAG2,...]` | `g (GROUP_NAME) /c  [t:TAG1,TAG2,...]`
**Edit**   | `group (INDEX) /edit [n:NEW_NAME]`             | `g (INDEX) /e [n:NEW_NAME]`
**Delete** | `group (INDEX) /delete`                        | `g (INDEX) /d`
**Note**   | `group (INDEX) /note (NOTE)`                   | `g (INDEX) /n (NOTE)`
**Tag**    | `group (INDEX) /tag [t:TAG1,TAG2,...]`         | `g (INDEX) /t [t:TAG1,TAG2,...]`
**Untag**  | `group (INDEX) /untag [t:TAG1,TAG2,...]`       | `g (INDEX) /u [t:TAG1,TAG2,...]`
**List**   | `group /list`                                  | `g /l`
**Find**   | `group /find (n:QUERY)`                        | `g /f (n:QUERY)`

### Subgroup
**Note**: For the **Edit**, **Delete**, **Note**, **Tag**, **Untag**, **List** and **Find** commands, please use the
**List** command to list the `Subgroup` objects of a `Group` before running operations on them.

Action     | Format                                               | Short Format
-----------|------------------------------------------------------|----------------------------------------------
**Create** | `group (INDEX:SUBGROUP_NAME) /create`                | `g (INDEX:SUBGROUP_NAME) /c`
**Edit**   | `group (INDEX) /edit [n:NEW_NAME] [t:TAG1,TAG2,...]` | `g (INDEX) /e [n:NEW_NAME] [t:TAG1,TAG2,...]`
**Delete** | `group (INDEX) /delete`                              | `g (INDEX) /d`
**Note**   | `group (INDEX) /note (NOTE)`                         | `g (INDEX) /n (NOTE)`
**Tag**    | `group (INDEX) /tag [t:TAG1,TAG2,...]`               | `g (INDEX) /t [t:TAG1,TAG2,...]`
**Untag**  | `group (INDEX) /untag [t:TAG1,TAG2,...]`             | `g (INDEX) /u [t:TAG1,TAG2,...]`
**List**   | `group (INDEX) /list`                                | `g (INDEX) /l`
**Find**   | `group /find (n:QUERY)`                              | `g /f (n:QUERY)`

### Tags
**Note**: for the **List** command, the `INDEX` argument can be either a `Group` or a `Person`, depending on what you
have listed after using `person /list` or `group /list`.

Action   | Format                | Short Format
---------|-----------------------|------------------------------------------------
**List** | `tag /list [p:INDEX]` | `t /l [p:INDEX] [g:GROUP_NAME]:[SUBGROUP_NAME]`

### General
Action    | Format  | Advanced Format
----------|---------|----------
**Help**  | `help`  | `h`
**Exit**  | `exit`  | `e`
**Clear** | `clear` | `c`
