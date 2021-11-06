---
layout: page
title: User Guide
---

EdRecord is a **desktop app for managing student contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a TA that can type fast, EdRecord can get your student contact management tasks done faster than traditional GUI apps.

## Before you start
This user guide will take you through the steps of setting up EdRecord and introduce you to EdRecord's functionalities, so that you, as a teaching assistant, can better manage your student contacts and their learning progress.

Some symbols used in this guide:

| Symbol | Meaning |
|--------|---------|
| :information_source: | Important information |
| :bulb: | Useful tips |
| :exclamation: | Cautions |


--------------------------------------------------------------------------------------------------------------------
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `edrecord.jar` from [here](https://github.com/AY2122S1-CS2103-W14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your EdRecord.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. You can take a look at the sample data to get an overview of what information EdRecord can store.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all students contacts.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts. This command can be used to clear the sample data.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) section below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: About the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `n/NAME`, `NAME` is a parameter that you should provide. For example, if the student's name is "John Doe", you should type `n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times, including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (blank, i.e. 0 times), `t/friend` (1 time), or `t/friend t/family` (2 times) etc.

* Items in round brackets are the set of possible values that you can supply. You must provide one and only one value from this set.<br>
  e.g. for `view (contacts/asg)`, you can either use `view contacts` or `view asg`. It is invalid to input only `view`, or input both `view contacts asg`. 

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* **Scope of commands**: All commands work within the currently selected module. If no module is selected, then the commands will work on all students across all modules. (see also [cd](#working-within-a-specific-module-cd))

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

### Managing student details

#### Adding a student: `add`

Adds a student to EdRecord.

Format: `add n/NAME p/PHONE e/EMAIL m/MODULE c/CLASS [i/INFO] [t/TAG]…​`

* If specified, the module and class must already exist (see [mkmod](#create-module-mkmod) and [mkclass](#create-class-mkclass) for creating module and class).

<div markdown="span" class="alert alert-primary">:bulb:
A student can have any number of tags and info fields (including 0).
</div>

Examples:

* `add n/John Doe p/98765432 e/E1234567@u.nus.edu i/telegram @JohnD`
* `add n/Betsy Crowe p/12345678 e/betsycrowe@u.nus.edu m/CS2103 c/W-14-3 i/telegram @betsy t/diligent`

#### Editing a student's details : `edit`

Edits an existing student in EdRecord.

Format: `edit INDEX [n/NAME] [p/PHONE] [i/INFO] [t/TAG]…​`

* Edits the student at the specified `INDEX`.
* The index refers to the index number shown in the student list being displayed on EdRecord. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed, to be replaced by new tags provided by your input, i.e adding of tags is **not cumulative**.
  
<div markdown="span" class="alert alert-primary">:bulb:
Thus, you can remove all the student’s tags by typing `t/` without specifying any tags after it.
</div>

Examples:

* `edit 1 p/98009800 e/johndoe@u.nus.edu` Edits the phone number and email of the 1st student to be `98009800` and `johndoe@u.nus.edu` respectively.
* `edit 2 n/Betsy Crower i/telegram @BetBet t/` Edits the name of the 2nd student to be `Betsy Crower`, edits the info of the student to `i/telegram @BetBet` and clears all existing tags.

#### Deleting a student : `delete`

Deletes the specified student from EdRecord.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the student list being displayed.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd student in EdRecord.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find Betsy` command.

#### Listing all students: `list`

Shows a list of all students in the current module matching the specified tags or list of all students in the current module if no tags are specified.

#### Locating students by name: `find`

Finds students whose names contain any of the given keywords *in the current module*.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* If no module is selected, the scope of the command would be across all modules.

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>


### Managing modules

Format: `list [TAG]…​`

#### Working within a specific module: `cd`

Changes the working directory to a specific module in EdRecord, and shows the students of that module.

Format: `cd MODULE`

* Changes the scope of the displayed student list to show only students under `MODULE`.
* `MODULE` should already exist in EdRecord (see [mkmod](#creating-a-module-mkmod) for creating a module).
* Many commands such as [mkasg](#create-assignment-mkasg), [dlasg](#delete-assignment-dlasg) only work after you `cd` into a module.

<div markdown="span" class="alert alert-primary">:information_source: 
Use `cd *` to "move out" of any selected module. This command is useful to get an overview of all students in all modules.
</div>

<div markdown="span" class="alert alert-primary">:information_source: 
When you first start up EdRecord, you will not be inside any module. EdRecord will show all modules with all their students.
</div>

Example:

* To work with module CS2103, use `cd CS2103`. EdRecord will now show you students in module CS2103 only. 

####  Listing modules: `lsmod`

Lists all modules available in EdRecord.

Format: `lsmod`

#### Creating a module: `mkmod`

Creates a new module.

Format: `mkmod MODULE`

Examples:

* `mkmod CS2103`

#### Deleting a module: `dlmod`

Deletes a module.

Format: `dlmod MODULE`

Examples:

* `dlmod CS2103`

#### Moving students into an existing class and module : `mv`

Move a particular student into a particular module and class.

Format: `mv INDEX [INDEX]... m/MODULE c/CLASS`

* Edits the module and class of the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​

#### Removing students from an existing class and module : `rm`

Remove a particular student from a particular module and class.

Format: `rm INDEX m/MODULE c/CLASS`

* Edits the module and class of the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​


### Managing classes in a module

#### Listing classes: `lsclass`

Lists all available classes for a specified module.

Format: `lsclass`

* This command can only be made after changing directory to a particular module (i.e `cd MODULE`).

#### Creating a class: `mkclass`

Creates a new class in the specified module.

Format: `mkclass m/MODULE c/CLASS`

Examples:

* `mkclass m/CS2103 c/T09`

#### Delete a class: `dlclass`

Deletes the class in the specified module.

Format: `dlclass m/MODULE c/CLASS`

Examples:

* `dlclass m/CS2103 c/T09`


### Managing module assignments

As a teaching assistant, you are very likely to be responsible for keeping track of students' progress in their assignments for the module. EdRecord provides a set of commands to help you manage those assignments efficiently.

#### Toggling the view: `view`

Toggles the view between showing student details and showing the module's assignments.

Format: `view (contacts/asg)`

* The default view when the application launches is the student details view.
* The only valid parameters are `contacts` or `asg`
* `view contacts` toggles the view to display the student's contact details for each student listed.
* `view asg` toggles the view to display the assignment completion status and/or grade for each student.

#### Creating an assignment: `mkasg`

Creates an assignment in the currently selected module.

Format: `mkasg n/NAME w/WEIGHTAGE s/MAXSCORE`

* This command can only be made after changing directory to a particular module (i.e `cd MODULE`).
* `WEIGHTAGE` is a number from 0 to 100 with at most 2 decimal places, indicating the weightage of the assignment in percentage.
* `MAXSCORE` is a non-negative decimal value, indicating the maximum score attainable for the assignment.

<div markdown="span" class="alert alert-primary">:information_source: 
You won't be able to create an assignment if adding that assignment makes the total weightage of all assignments in the module exceed 100%.
</div>

Example:

* If you are in module CS1010S, `mkasg n/Midterm w/12.5 s/50` will create a new assignment called `Midterm` with weightage 12.5% and maximum score 50 under that module.

#### Editing an assignment: `edasg`

Edits an assignment under the currently selected module.

Format: `edasg ID [n/NAME] [w/WEIGHTAGE] [s/MAXSCORE]`

* This command can only be made after changing directory to a particular module (i.e `cd MODULE`).
* `ID` represents the unique ID of the assignment, as displayed in the [assignment view](#toggling-the-view-view).
* Edits the assignment identified by `ID`.

<div markdown="span" class="alert alert-primary">:bulb: 
We recommend that you switch to Assignment View before editing an assignment, so that you have a clear idea what its ID is. Editing assignments without Assignment View is more prone to mistakes and can lead to unexpected results.
</div>

* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing weightage, EdRecord will report an error if the new weightage causes the total module weightage to exceed 100%.
* If the maximum score is edited but this new `MAXSCORE` is lower than an existing student grade for that assignment, EdRecord will also reject the edit.

Example:

* If you are in module CS2103, `edasg 1 n/PE Dry Run w/0` will edit the assignment with ID number 1 in CS2103, and change its name to `PE Dry Run` with a new weightage of 0%.

#### Deleting an assignment: `dlasg`

Deletes an assignment in the currently selected module.

Format: `dlasg ID`

* This command can only be made after changing directory to a particular module (i.e `cd MODULE`).
* `ID` represents the unique ID of the assignment, as displayed in the [assignment view](#toggling-the-view-view).
* Deletes the assignment identified by `ID`. 
* Similar to editing assignment, we recommend that you use the [assignment view](#toggling-the-view-view) when deleting assignments.

Example:

* `dlasg 2` will delete the assignment with ID number 2 in the module you are currently in.


### Managing students' grades

Keeping assignments without the students' grades is not of much use to teaching assistants. EdRecord can help you record the grades of students for each submission!

#### Grading a student's assignment: `grade`

Grades a student for the specified assignment.

Format: `grade INDEX id/ID st/STATUS [s/SCORE]`

- This command can only be made after changing directory to a particular module (i.e `cd MODULE`).
- Assigns a grade to the student identified specified `INDEX` used in the displayed student list. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
- `ID` represents the unique ID of the assignment, as displayed in the assignments view (`view asg`)
- Status has 3 possible inputs: Not submitted, Submitted or Graded
- Score must be less than or equal to the assignment's maximum score
- If the student has an existing grade for this assignment, it will be overwritten.

Examples:

- `grade 2 id/3 st/submitted`
- `grade 3 id/4 st/graded s/35`

#### Deleting a student's grade : `dlgrade`

Deletes a grade for the specified assignment from the student at the specified `INDEX` used in the displayed student list.

Format: `dlgrade INDEX id/ID`

- This command can only be made after changing directory to a particular module (i.e `cd MODULE`),
- `ID` represents the unique ID of the assignment, as displayed in the assignments view (`view asg`)

Example:

- `dlgrade 3 id/4`

### Clearing all entries : `clear`

Clears all entries, both contacts and modules/groups, from EdRecord.

Format: `clear`

### Exiting the program : `exit`

Exits EdRecord.

Format: `exit`

### Saving the data

EdRecord data is saved in the hard disk automatically (as a JSON file `[JAR file location]/data/edrecord.json`) after any command that changes the data. There is no need to save manually.

### Editing the data file

EdRecord data is saved as a JSON file `[JAR file location]/data/edrecord.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, EdRecord will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous EdRecord home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                  | Format, Examples                                                                                                                                                      |
| --------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add Student**                         | `add n/NAME p/PHONE e/EMAIL m/MODULE c/CLASS [i/INFO] [t/TAG]…​` <br> e.g., `add n/James Ho p/85436543 e/jamesho@u.nus.edu i/Currently on SHN t/strong t/careless` |
| **Edit Student**                        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [i/INFO] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@u.nus.edu`                                              |
| **Delete Student**                      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **List Module**                         | `cd MODULE`<br> e.g.,`cd CS2103`, `cd *`                                                                                                                              |
| **Create Module**                       | `mkmod MODULE`<br> e.g., `mkmod CS2103`                                                                                                                               |
| **Delete Module**                       | `dlmod MODULE`<br> e.g., `dlmod CS2103`                                                                                                                               |
| **List Class**                          | `lsclass`<br> e.g., `lsclass`                                                                                                                                         |
| **Create Class**                        | `mkclass m/MODULE c/CLASS`<br> e.g., `mkclass m/CS2103 c/T09`                                                                                                         |
| **Delete Class**                        | `dlclass m/MODULE c/CLASS`<br> e.g., `dlclass m/CS2103 c/T09`                                                                                                         |
| **Move Student into Class in Module**   | `mv INDEX m/MODULE c/CLASS`<br> e.g.,`mv 2 m/CS2103 c/T10`                                                                                                            |
| **Remove Student from Class in Module** | `rm INDEX m/MODULE c/CLASS`<br> e.g.,`rm 2 m/CS2103 c/T10`                                                                                                            |
| **Create Assigment**                    | `mkasg n/ASSIGNMENT w/WEIGHTAGE s/MAXSCORE`<br> e.g., `mkasg n/Side quest 10 w/20 s/50`                                                                               |
| **Edit Assignment**                     | `edasg ID [n/NAME] [w/WEIGHTAGE] [s/MAXSCORE]`<br> e.g., `edasg 1 n/PE Dry Run w/12.5 s/10`                                                                           |
| **Delete Assignment**                   | `dlasg ID`<br> e.g., `dlasg 1`                                                                                                                                        |
| **Grade an Assignment**                 | `grade INDEX id/ID st/STATUS [s/SCORE]`<br> e.g.,`grade 4 id/2 st/Graded s/50`                                                                                        |
| **Delete student's grade**              | `dlgrade INDEX id/ID`<br> e.g.,`dlgrade 3 id/3`                                                                                                               |
| **Toggle view**                         | `view (contacts/asg)`<br> e.g.,`view contacts`                                                                                                                        |
| **Find**                                | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**                                | `list [TAG]…​`                                                                                                                                                        |
| **Clear**                               | `clear`                                                                                                                                                               |
| **Help**                                | `help`                                                                                                                                                                |
| **Exit**                                | `exit`                                                                                                                                                                |
