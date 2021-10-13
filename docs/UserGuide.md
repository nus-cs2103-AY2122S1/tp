---
layout: page
title: User Guide
---

Source Control is a **desktop app for CS1101S professors to manage the performance of their students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Source Control can give you both a quick overview and a closer look of how your students are doing.

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

    * **`add alloc`**`-g T01A -n John Doe` : Adds student `John Doe` into group `T01`.

    * **`add score`**`-a P01 -n John Doe -s 12`: Adds score for assessment `P01` as `12` for student `John Doe`.

    * **`search`**`-n John Doe` : Searches for student `John Doe`.

    * **`clear`** : Clears all existing data.
    
1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

v1.2 assumes that all inputs are valid, i.e. student id is in the correct format, and the groups which the student belongs to exists.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<angled brackets>` are the parameters to be supplied by the user.<br>
  e.g. in `add -n <student_name>`, `<student_name>` is a placeholder which can be used as `add -n John Doe`.

* Parameters in `(round brackets)` separated by `|` are mutually exclusive options for input. Only one input should to be supplied by the user.<br>
  e.g. `(-n <student_name> | -i <student_id> | -group <group_name>)` can be used as `-n John Doe`, or as `-i E0123456`, or as `-g T02A`

* Parameters in `[square brackets]` are optional. <br>
  e.g. `-n <student_name> [-g <group_name>]` can be used as `-n John Doe -g T01A`, or as `-n John Doe`.

* Items with `...​`  after them can be used multiple times including zero times.<br>
  e.g. `[-g <group_name>]...` can be used as ` ` (i.e. 0 times), or `-g T01A -g R01A`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n <student_name> -g <group_name>`, then `-g <group_name> -n <student_name>` is also acceptable.

* If a parameter is expected only once in the command but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-g T02A -g T03B` and the command only expects one group, only `-g T03B` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `clear`) will be ignored.<br>
  e.g. if the command specifies `clear 123`, it will be interpreted as `clear`.

</div>

### Adding a student : `add student`

Adds a student into the database.

Format: `add student -n <student_name> -i <student_id>  [-g <group_name>] [-t <tag_name>]...`

* Adds a new student with the given name and NUSNET ID into the database.
* Adds the student into the groups the student belongs to.
* Adds a tag to the student if applicable. 

Examples:
* `add student -n John Doe -i E0123456`
* `add student -n Jane Doe -i E0123457 -g T01A -g R01A`
* `add student -n Jane Doe -i E0123458 -t beginner`

### Creating a new group: `add group`

Creates a new group and adds students into the group.

Format: `add group -g <group_name> [(-n <student_name> | -i <student_id>)]...`

* Creates a new group with the given group name.
* Students can be identified by their name or NUSNET ID.

Examples:
* `add group -g FG1 -n John Doe -n Jane Doe`
* `add group -g FG1`
* `add group -g FG1 -n John Doe -i E0123456`
* `add group -g FG1 -i E0123123 -i E0123456`

### Adding a student into a group: `add alloc`

Allocates an existing student into an existing group.

Format: `add alloc -g <group_name> (-n <student_name> | -i <student_id>)`
* Adds the student into an existing group specified by the group name.
* Students can be identified by their name or NUSNET ID.

Examples:
* `add alloc -g T01A -n John Doe`
* `add alloc -g T02A -i E0123456`

### Adding an assessment: `add score`

Adds score of an existing assessment into the database.

Format: `add score -a <assessment_name> (-n <student_name> | -i <student_id>) -s <score>`
* Adds student’s score for an existing assessment into the database.
* Students can be identified by their name or NUSNET ID.

Examples:
* `add score -a P01 -n John Doe -s 12`
* `add score -a P02 -i E0123456 -s 12.5`

### Searching for students: `search`

Finds students who match the input keywords.

Format: `search (-n <student_name> | -i <student_id> | -g <group_name> | -t <tag>)`

* Search for students by their name, NUSNET ID, tag, or the group they belong in. 
Only one type of tag should be used for each search. 
* To search with multiple keywords, separate keywords with spaces. e.g. `search -g T02A R03C`
* The search is case-insensitive. e.g. `hans` will match `Hans`. 
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Persons matching at least one keyword will be returned (i.e. `OR` search). 
e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* For search with name or tag, only full words will be matched. e.g. `Han` will not match `Hans`.
* For search with NUSNET ID or group name, partial search is supported. e.g. `T02` will match `T02A` and `T02B`. 

Examples:
* `search -n John Doe`
* `search -i E0123456`
* `search -g T02B R03C`
* `search -g friends colleagues`


### Importing data: `import`

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


### Resetting all data: `clear`

Clears all existing data.

Format: `clear`


### Saving the data `[coming soon]`

_Details coming soon ..._

### Archiving data files `[coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ `[coming soon]`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Student** | `add student -n <student_name> -i <student_id>  [-g <group_name>]...` <br> e.g. `add student -n Jane Doe -i E0123456 -g T01A -g R01A`
**Add Group** | `add group -g <group_name> [(-n <student_name> | -i <student_id>)]...` <br> e.g. `add group -g FG1`, `add group -g FG1 -n John Doe -i E0123456`
**Add Allocation** | `add alloc -g <group_name> (-n <student_name> | -i <student_id>)` <br> e.g. `add alloc -g T01A -n John Doe`, `add alloc -g T02A -i E0123456`
**Add Assessment** | `add score -a <assessment_name> (-n <student_name> | -i <student_id>) -s <score>` <br> e.g. `add score -a P01 -n John Doe -s 12`, `add score -a P02 -i E0123456 -s 12.5`
**Search** | `search (-n <student_name> | -i <student_id> | -g <group_name>)` <br> e.g. `search -n John Doe` , `search -g T02B`
**Import data** | `import -f <file_path> [-g <number_of_group_columns>] [-a <number_of_assessment_columns>] [-t <number_of_tag_columns>]` <br> e.g. `import -f student_data.csv -g 2 -a 10 -t 1`
**Clear** | `clear data`
