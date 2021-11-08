---
layout: page
title: User Guide
---

EdRecord is a **desktop app for managing student contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a TA that can type fast, EdRecord can get your student contact management tasks done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `edrecord.jar` from [here](https://github.com/AY2122S1-CS2103-W14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your EdRecord.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all students contacts.

   - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts and modules/groups.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
- Values in round brackets indicate possible values.<br>
  e.g `view (contacts/asg)`
- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Scope of commands: All commands work within the currently selected module. If no module is selected, then the commands will work on all students across all modules.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

### Adding a student: `add`

Adds a student to EdRecord.

Format: `add n/NAME p/PHONE e/EMAIL m/MODULE c/CLASS [i/INFO] [t/TAG]…​`

- If specified, the module and class must already exist.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com i/telegram @JohnD`
- `add n/Betsy Crowe p/1234567 e/betsycrowe@example.com m/CS2103 c/W-14-3 i/telegram @betyoass t/strong`

### Editing a student's details : `edit`

Edits an existing student in EdRecord.

Format: `edit INDEX [n/NAME] [p/PHONE] [i/INFO] [t/TAG]…​`

- Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
- You can remove all the student’s tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email of the 1st student to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower i/telegram @betmyass t/` Edits the name of the 2nd student to be `Betsy Crower`, edits info of the student and clears all existing tags.

### Deleting a student : `delete`

Deletes the specified student from EdRecord.

Format: `delete INDEX`

- Deletes the student at the specified `INDEX`.
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd student in EdRecord.
- `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Listing all students: `list`

Shows a list of all students in the current module matching the specified tags or list of all students in the current module if no tags are specified.

Format: `list [TAG]…​`

### Listing module's students: `cd`

Change the working directory to a specific module, showing the students, in EdRecord.

Format: `cd MODULE`

- Alternatively, the user can use `cd *` to get an overview of all students in all modules.

Examples:

- `cd CS2103`
- `cd *`

### Moving students into an existing class and module : `mv`

Move a particular student into a particular module and class.

Format: `mv INDEX [INDEX]... m/MODULE c/CLASS`

- Edits the module and class of the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `mv 2 m/CS2103 c/T10`

### Removing students from an existing class and module : `rm`

Remove a particular student from a particular module and class.

Format: `rm INDEX m/MODULE c/CLASS`

- Edits the module and class of the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …

Examples:

- `rm 3 m/CS2103 c/T10`


### Toggle view: `view`

Toggle the view between showing student details and showing module's assignments. This command works on the filtered list, toggling the view for students currently listed.

Format: `view (contacts/asg)`

- The default view when the application launches is the student details.
- The only valid parameters are `contacts` or `asg`
- When no module is selected, the assignment view only shows submissions of each student. When a module is selected, the assignment view also include assignments under that module that each student has yet to submit.
 
Examples:

- `view contacts`: toggles the view to display the student's contact details for each student listed.
- `view asg`: toggles the view to display the assignment completion status and/or grade for each student.

### List modules: `lsmod`

Lists all modules available in EdRecord.

Format: `lsmod`

### Create module: `mkmod`

Creates a new module.

Format: `mkmod MODULE`

Examples:

- `mkmod CS2103`

### Delete module: `dlmod`

Deletes a module.

Format: `dlmod MODULE`

Examples:

- `dlmod CS2103`

### List class: `lsclass`

Lists all available classes for a specified module.

Format: `lsclass`

- This command can only be made after changing directory to a particular module (i.e `cd MODULE`).

### Create class: `mkclass`

Creates a new class in the specified module.

Format: `mkclass m/MODULE c/CLASS`

Examples:

- `mkclass m/CS2103 c/T09`

### Delete class: `dlclass`

Deletes the class in the specified module.

Format: `dlclass m/MODULE c/CLASS`

Examples:

- `dlclass m/CS2103 c/T09`

### Create assignment: `mkasg`

Creates an assignment in the specified module.

Format: `mkasg n/ASSIGNMENT w/WEIGHTAGE s/MAXSCORE`

- Weightage is the weightage of the assignment out of 100%. Total weightage of all assignments cannot exceed 100%.
- Max score is the maximum attainable score for the assignment.
- This command can only be made after changing directory to a particular module (i.e `cd MODULE`).

Examples:

- `mkasg n/Midterm w/20 s/50`

### Edit assignment: `edasg`

Edits an assignment in the specified module.

Format: `edasg ID [n/NAME] [w/WEIGHTAGE] [s/MAXSCORE]`

- This command can only be made after changing directory to a particular module (i.e `cd MODULE`).
- `ID` represents the unique ID of the assignment, as displayed in the assignments view (`view asg`)
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:

- `edasg 1 n/PE Dry Run w/12.5 s/10`

### Delete assignment: `dlasg`

Deletes an assignment in the specified module.

Format: `dlasg ID`

- This command can only be made after changing directory to a particular module (i.e `cd MODULE`).
- `ID` represents the unique ID of the assignment, as displayed in the assignments view (`view asg`)

Examples:

- `dlasg 2`

### Grade a student's assignment : `grade`

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

### Delete a student's grade : `dlgrade`

Deletes a grade for the specified assignment from the student at the specified `INDEX` used in the displayed student list.

Format: `dlgrade INDEX id/ID`

- This command can only be made after changing directory to a particular module (i.e `cd MODULE`),
- `ID` represents the unique ID of the assignment, as displayed in the assignments view (`view asg`)

Examples:

- `dlgrade 3 id/4`

### Locating students by name: `find`

Finds students whose names contain any of the given keywords *in the current module*. 

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* If no module is selected, the scope of the command would be across all modules.

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Clearing all entries : `clear`

Clears all entries, both contacts and modules/groups, from EdRecord.

Format: `clear`

### Exiting the program : `exit`

Exits EdRecord.

Format: `exit`

### Saving the data

EdRecord data is saved in the hard disk automatically (as a JSON file `[JAR file location]/data/edrecord.json`) after any command that changes the data. There is no need to save manually.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, EdRecord will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous EdRecord home folder.

---

## Command summary

| Action                                  | Format, Examples                                                                                                                                                      |
| --------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add Student**                         | `add n/NAME p/PHONE e/EMAIL m/MODULE c/CLASS [i/INFO] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com i/Currently on SHN t/friend t/colleague` |
| **Edit Student**                        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [i/INFO] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                              |
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
