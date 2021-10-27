---
layout: page
title: User Guide
---
Welcome to **Source Control** User Guide! 

Source Control is a **desktop app for CS1101S professors to manage the performance of their students**. 
This application allows you to **store comprehensive data** of each student quickly, and allows you to search through them easily. 
With Source Control, you will never have to worry about your large student cohort and tracking numerous assessments! <br>
Source Control can also give you both a quick overview and a closer look of how your students are performing.
This application provides **in-depth data analysis** of the performance of your students in each assessment, 
giving you timely feedback on the pace and difficulty level of the module.

Source Control is **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Source Control can help you track your students' performance faster than traditional GUI apps! 

This guide takes you through all the _latest features_ of Source Control. If you are a new user, this guide provides you 
all the basic knowledge to get started with Source Control, and is simple and easy to read.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `sourcecontrol.jar` from [here](https://github.com/AY2122S1-CS2103T-W08-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Source Control.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`add student`**`-n John Doe -i E0123456` : Adds a student named `John Doe` with NUSNET ID `E0123456` into the database.

    * **`add group`**`-g T01A -n John Doe` : Adds a group called `T01A` into the database and student `John Doe` into the group.

    * **`add score`**`-a P01 -n John Doe -s 12`: Adds score for assessment `P01` as `12` for student `John Doe`.

    * **`search`**`-n John Doe` : Searches for student `John Doe`.
   
    * **`show`**`-n John Doe` : Displays information of student `John Doe`.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* `<angled brackets>`: <br>
  Words in `<angled brackets>` are the parameters to be supplied by the user.<br>
  e.g. in `add -n <student_name>`, `<student_name>` is a placeholder which can be used as `add -n John Doe`.

* `(round brackets)`: <br>
  Parameters in `(round brackets)` separated by `|` are mutually exclusive options for input. Only one input should to be supplied by the user.<br>
  e.g. `(-n <student_name> | -i <student_id> | -group <group_name>)` can be used as `-n John Doe`, or as `-i E0123456`, or as `-g T02A`

* `[square brackets]`: <br>
  Parameters in `[square brackets]` are optional. <br>
  e.g. `-n <student_name> [-g <group_name>]` can be used as `-n John Doe -g T01A`, or as `-n John Doe`.

* `...​`: <br>
  Items with `...​`  after them can be used multiple times, including zero times.<br>
  e.g. `[-g <group_name>]...` can be used as ` ` (i.e. 0 times), or `-g T01A -g R01A`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n <student_name> -g <group_name>`, then `-g <group_name> -n <student_name>` is also acceptable.

* If a parameter is expected only once in the command, but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-g T02A -g T03B` and the command only expects one group, only `-g T03B` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `clear`) will be ignored.<br>
  e.g. if the command specifies `clear 123`, it will be interpreted as `clear`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Listing all students : `list`

Shows a list of all students in the address book.

Format: `list`


### Adding a student : `add student`

Adds a student into the database.

Format: `add student -n <student_name> -i <student_id> [-g <group_name>]... [-t <tag_name>]...`

* Adds a new student into the database with the given name and NUSNET ID.
* Adds the student into the specified groups if applicable. If group does not already exist, a new group would be created. 
* Adds tags to the student if applicable.
* NUSNET ID input cannot already exist in address book. 

Examples:
* `add student -n Jonas Chow -i E0123456` adds the student Jonas Chow with the given NUSNET ID. 
* `add student -n Jonas Chow -i E0123456 -g T01A -g R01A` adds the student Jonas Chow and allocate him into groups `T01A` and `R01A`. 
* `add student -n Jane Doe -i E0123456 -t beginner` adds the student Jonas Chow and tag him with `beginner`. 


### Creating a new group : `add group`

Creates a new group and adds students into the group.

Format: `add group -g <group_name> [(-n <student_name> | -i <student_id>)]...`

* Creates a new group with the given group name.
* Group must not already exist, and student should not already exist in group.
* Students can be identified by their name or NUSNET ID. 
Only full name is allowed e.g. `Jonas` will not match `Jonas Chow`. 
* If multiple students have the same name, NUSNET ID needs to be used to identify them.  

Examples:
* `add group -g T01A` creates group `T01A`
* `add group -g T01A -n Hong Yao -n Hong Fai` creates group `T01A` and adds `Hong Yao` and `Hong Fai` into the group. 
* `add group -g T01A -n Hong Yao -i E0123456` creates group `T01A` and adds `Hong Yao` and student with NUSNET ID `E0123456` into the group.
* `add group -g T01A -i E0123123 -i E0123456` creates group `T01A` and adds two students with corresponding NUSNET ID into the group. 


### Adding a student into a group : `add alloc`

Allocates an existing student into an existing group.

Format: `add alloc -g <group_name> (-n <student_name> | -i <student_id>)`
* Adds the student into an existing group specified by the group name.
* Students can be identified by their name or NUSNET ID.

Examples:
* `add alloc -g T01A -n John Doe`
* `add alloc -g T02A -i E0123456`

### Adding an assessment : `add assessment`

Adds a new assessment into the database.

Format: `add assessment -a <assessment_name>`
* Adds an assessment only if the assessment is not already in the database.
* Assessment name can only be alphanumeric.

Examples:
* `add assessment -a P01`

### Adding a score : `add score`

Adds score of an existing assessment into the database.

Format: `add score -a <assessment_name> (-n <student_name> | -i <student_id>) -s <score>`
* Adds student’s score for an existing assessment into the database.
* Updates the student's score if the student already has a score for the assessment.
* Students can be identified by their name or NUSNET ID.

Examples:
* `add score -a P01 -n John Doe -s 12`
* `add score -a P02 -i E0123456 -s 12.5`

### Searching for students : `search`

Finds students who match the input keywords.

Format: `search (-n <student_name> | -i <student_id> | -g <group_name> | -t <tag>)`

* Search for students by their name, NUSNET ID, tag, or the group they belong in.
Only one type of tag should be used for each search.
* To search with multiple keywords, separate keywords with spaces. e.g. `search -g T02A R03C`
* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Students matching at least one keyword will be returned (i.e. `OR` search).
e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* For search with name, only full words will be matched. e.g. `Han` will not match `Hans`.
* For search with NUSNET ID, group name or tag, partial search is supported. e.g. `T02` will match `T02A` and `T02B`.
`friend` will match `friends`.

Examples:
* `search -n John Doe`
* `search -i E0123456`
* `search -g T02B R03C`
* `search -t friends colleagues`

### Showing assessment result analysis : `show`

Shows the in-depth data analysis of individual, group, and cohort's performance for assessments.

Format: `show (<index> | -n <student_name> | -i <student_id> | -g <group_name> | -a <assessment_name> ) [-f <export_file_path>]`

* Using `<index>`, `-n <student_name>` or `-i <student_id>` displays the information of the particular student's performance in all his graded assessments. 
* The `<index>` refers to the index number shown in the displayed student list. The `<index>` must be a positive integer 1, 2, 3, …
* Using `-g <group_name>` displays the information of the group's performance in all their graded assessments. 
* Using `-a <assessment_name>` displays the information of the cohort's performance in the particular assessment.
* Does not support partial searching. e.g. `T01` does not match `T01A`. Full name must be entered. 
* Entering `-f <export_file_path>` exports the graph produced from the command to the specified location. 

Examples:
* `show -n Alex Yeoh` displays line chart of Alex Yeoh's performance in his assessments. 
* `show 2 -f /home/prof/CS1101S/` exports the line chart produced of the 2nd student in the address book. 
* `show -g T02A` displays line chart of group T02A's performance in their assessments. 
* `show -a P01` displays bar chart of the distribution of scores in assessment P01. 


### Editing a student : `edit`

Edits the information of an existing student.

Format: `edit <index> [-n <student_name>] [-i <student_id>] [-g <group_name>]... [-t <tag>]...`

* Edits the student at the specified `<index>`. The `<index>` refers to the index number shown in the displayed student list. The `<index>` must be a positive integer 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags and groups, the existing tags and groups of the student will be removed i.e adding of tags and groups are not cumulative.
* You can remove all the student’s tags or groups by typing -t or -g without specifying any values after it.

Examples:
* `edit 1 -n John Doe`
* `edit 1 -n John Doe -i E1234567 -g T01 -g R01`
* `edit 2 -t`

### Deleting a student : `delete`

Deletes the specified student from the address book.

Format: `delete <index>`

* Deletes the student at the specified `<index>`.
* The `<index>` refers to the index number shown in the displayed student list.
* The `<index>` **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the address book.
* `search -n Alex` followed by `delete 1` deletes the 1st student in the results of the `search` command.


### Importing data : `import`

Loads data as specified in the provided CSV file.

Format: `import -f <file_path> [-g <number_of_group_columns>] [-a <number_of_assessment_columns>] [-t <number_of_tag_columns>]`

* The file path can be either the absolute path or the relative path.
* The first row of the CSV file needs to be headers for the respective columns.
* The header for the assessment columns should the name of the assessment. For example, `Rune Trials`.
* The header for every other column does not matter.
* Every row apart from the first represents a student.
* The first two columns refer to the student’s name and NUSNET ID.
* The next i columns, where i is the specified number of group columns, refer to the student’s groups.
* The next j columns, where j is the specified number of assessment columns, refer to the student’s grade in the respective assessments.
* The next k columns, where k is the specified number of tag columns, refer to the student's tags.
* The number of group columns, assessment columns, and tag columns are assumed to be 0 if they are not specified.

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**<br>

* If the student does not have as many groups as the number of group columns, you should leave several group columns blank.

* If the student does not have a grade for some assessment, you should leave the corresponding assessment column blank.

</div>

Examples:
* `import -f /home/prof/CS1101S/student_data.csv -g 2 -a 10 -t 1`
* `import -f student_data.csv -g 3 -a 30`

### Exporting data : `export`

//TODO: add more instructions on how command works

Exports data in the address book to (... what file_location).

Format: `export`

* ...

### Resetting all data : `clear`

Clears all existing data.

Format: `clear`

### Closing the app : `exit`

Exits the application.

Format: `exit`

### Saving the data

SourceControl data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SourceControl data are saved as a JSON file `[JAR file location]/data/sourcecontrol.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SourceControl will discard all data and start with an empty data file at the next run.
</div>

### Setting customised alias for commands keywords

//TODO: add more instructions on how command works

Adds new alias for existing commands, so that experienced users can customise commands to increase efficiency. 

Format: `alias <alias> -> <existing_command>`

* `<existing_commands>` refers to the command keywords e.g. `search`, `add alloc`, `add student`. 
* New alias can be one single alphanumeric word. 
* Multiple alias can be set for each command. 
* Default and existing alias can still be used after new alias is added. e.g. after `alias as -> add student`, both `as` and `add student` can be used. 

Examples:
* `alias as -> add student` adds new alias to `add student` command i.e. `as -n Zhiying -i E7654321` adds student `Zhiying` to database. 
* `alias alloc -> add alloc` adds new alias to `add alloc` command i.e. `alloc -g T02A -n Zhiying` adds student `Zhiying` into group `T02A`. 
* `alias as2 -> add score` adds new alias to `add score` command. 

--------------------------------------------------------------------------------------------------------------------

## FAQ `[coming soon]`

--------------------------------------------------------------------------------------------------------------------

## Glossary

Below is a table of the parameter tags used in our document. 

Tag | Full Form | Usage
-------|--------|----------
-n | name | `-n <student_name>`
-i | id | `-i <student_id>`
-g | group | `-g <group_name>`
-a | assessment | `-a <assessment_name>`
-s | score | `-s <score>`
-t | tag | `-t <tag>`
-f | file | `-f <file_path>`

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>

In `import` command, `-g`, `-a`, `-t` are used differently from other commands. More information can be found in the
Import command description.
</div>


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Examples
--------|--------|----------
**Help** | `help` |
**List** | `list` |
**Add Student** | `add student -n <student_name> -i <student_id> [-g <group_name>]... [-t <tag>]...` | e.g. `add student -n Jonas Chow -i E0123456 -g T01A -g R01A -t beginner`
**Add Group** | `add group -g <group_name> [(-n <student_name> | -i <student_id>)]...` | e.g. `add group -g FG1`, `add group -g FG1 -n John Doe -i E0123456`
**Add Allocation** | `add alloc -g <group_name> (-n <student_name> | -i <student_id>)` | e.g. `add alloc -g T01A -n John Doe`, `add alloc -g T02A -i E0123456`
**Add Assessment** | `add assessment -a <assessment_name>` | e.g. `add assessment -a P01`
**Add Score** | `add score -a <assessment_name> (-n <student_name> | -i <student_id>) -s <score>` | e.g. `add score -a P01 -n John Doe -s 12`, `add score -a P02 -i E0123456 -s 12.5`
**Search** | `search (-n <student_name> | -i <student_id> | -g <group_name> | -t <tag>)` | e.g. `search -n John Doe`, `search -g T02B R04D`
**Show Analysis** | `show (<index> | -n <student_name> | -i <student_id> | -g <group_name> | -a <assessment_name>) [-f <export_file_path>]` | e.g. `show -n Alex Yeoh`, `show -a P01`
**Edit Student** | `edit <index> [-n <student_name>] [-i <student_id>] [-g <group_name>]... [-t <tag>]...` | e.g.`edit 1 -n John Doe -i E1234567 -g T01 -g R01`
**Delete Student** | `delete <index>` | e.g. `delete 2`
**Import Data** | `import -f <file_path> [-g <number_of_group_columns>] [-a <number_of_assessment_columns>] [-t <number_of_tag_columns>]` | e.g. `import -f student_data.csv -g 2 -a 10 -t 1`
**Export Data** | `export` |
**Clear Data** | `clear` |
**Exit App** | `exit` |
**Add Alias** | `alias <alias> -> <existing_command>` | e.g. `alias as -> add student`
