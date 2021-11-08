---
layout: page
title: User Guide
---

ProgrammerError (PE) is a desktop app for managing students' information, optimized for use via a Command Line
Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Designed for CS2100 Lab Teaching
Assistants (TAs), this application is optimized to track personal particulars, emails and lab results of your students across different
classes. If you are familiar with Unix commands, this is definitely for you!

## Table of Contents

- [Glossary](#Glossary)
- [Quick Start](#quick-start)
- [Features](#features)
  1. [Parameter Summary](#parameter-summary)
  2. [General Features](#general-features)
        1. [View Help: `help`](#view-help)
        2. [View Dashboard `dashboard`](#view-dashboard)
        3. [Exit: `exit`](#exit)
  3. [Data Features](#data-features)
      1. [Fill Sample Data: `fill`](#fill-sample-data)
      2. [Purge All Data: `purge`](#purge-all-data)
      3. [Upload Data: `upload`](#upload-data)
      4. [Download Data: `download`](#download-data)
      5. [Edit The Data File Directly (For advanced users)](#edit-data-file)
  4. [Student Features](#student-features)
      1. [Add Student: `add`](#add-student)
      2. [Edit Student Details: `edit`](#edit-student)
      3. [Delete Student: `delete`](#delete-student)
      4. [Filter Students: `filter`](#filter-student)
      5. [Show Student Details: `show`](#show-student)
      6. [List All Students: `list`](#list-students)
  5. [Lab Features](#lab-features)
      1. [Add Lab: `addlab`](#add-lab)
      2. [Edit Lab: `editlab`](#edit-lab)
      3. [Delete Lab: `dellab`](#delete-lab)
- [Command Summary](#command-summary)

## <a name="Glossary"></a>Glossary

- **Student ID**: NUS student matriculation number (AXXXXXXXY)
- **Email**: NUS student email (eXXXXXXX@u.nus.edu)
- **TA**: CS2100 lab teaching assistant
- **PE**: ProgrammerError
- **CSV**: Comma-Separated Values

## <a name="quick-start"></a>Quick Start

1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest `programmerError.jar`
   from [here](https://github.com/AY2122S1-CS2103-F09-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your ProgrammerError.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
    <p align="center">
        <img src="images/Ui_v1.4.png" width="600"/>
    </p>
5. Type the command in the command box and press Enter to execute it. For example, typing **`help`** and pressing Enter
   will open the help window.<br>
   Here are some example commands you can try:
    * `add -n Sherwin -sid A1234567X -cid B01 -email e0523451@u.nus.edu`: Adds a student named `Sherwin` to PE with his
      Student ID, Class ID and Email.
    * `edit 1 -n Sherwin`: Updates the name of the student at index 1 of the displayed list (1-indexed) to Sherwin.
    * `delete 1`: Deletes the student at index 1 of the displayed list (1-indexed).
    * `filter -sid A1234567X`: Filters the list of students to display the student(s) whose student ID contains
   `A1234567X` (case-insensitive).
6. Refer to the [Features](#features) below for details on all available commands. Take note that command parameters
   wrapped with `[` `]` listed in this guide signifies that the parameter is optional.

## <a name="features"></a>Features

<div markdown="block" class="alert alert-info">

**Notes about the command format (Unix Command Syntax):**

- Similar to Unix CLI, the up and down arrow keys can be used to navigate the history of commands executed.
    - Up Arrow Key: Navigate to an older command.
    - Down Arrow Key: Navigate to a more recent command.
    - Command will only be stored after `Enter` has been pressed.
- Flags will be used to specify different options for the commands. For example, the `-sid` flag can be used to specify
  a student's student ID.
- Parameters can be in any order. <br> e.g. if the command specifies `-n <NAME> -sid <STUDENT_ID>`
  , `-sid <STUDENT_ID> -n <NAME>`is also acceptable.
- If a parameter is expected only once in the command, but you specified it multiple times, only the **last** occurrence
  of the parameter will be taken. <br> e.g. if you specify `-n Allard -n Xian Yi` , only `-n Xian Yi` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `purge`) will be
  ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


## <a name="parameter-summary"></a>1. Parameter Summary
<div markdown="span" class="alert alert-warning">

:exclamation: **Note:**
Take note that all the commands that require the corresponding parameter follows the constraints as specified in the table below, apart from `filter` command.
<br/>
`filter` command filters the list based on partial character sequence matching and hence does not have to follow the constraints strictly.
</div>

Parameter | What it means | Constraints
--------| ---------------------- | -------
**-n** | Name of Student | Alphanumeric characters and spaces
**-sid** | Student ID of Student | AXXXXXXXY, where X is a digit from 0 to 9 and Y is an alphabet
**-cid** | Class ID of Student | BXX, where X is a digit from 0 to 9
**-email** | Email of Student | eXXXXXXX@u.nus.edu, where X is a digit from 0 to 9
**-ln** | Lab Number | An integer between 1 and 13 (inclusive)
**-nln** | New Lab Number | An integer between 1 and 13 (inclusive)
**-ts** | Lab Total Score | A positive integer between 1 and 100 (inclusive)
**-s** | Lab Score of Student | A non-negative integer, not greater than the corresponding lab total score


## <a name="general-features"></a>2. General Features

### <a name="view-help"></a>2.1 View Help: `help` or F2 on keyboard

Shows a message explaining how to access the help page.

### <a name="view-dashboard"></a>2.2 View Dashboard: `dashboard`

Displays a dashboard window that updates dynamically showing the following data:

1. Number of students
2. Number of classes
3. Number of labs
4. Number of labs unmarked for each class

Below is an example of a dashboard window when the `dashboard` command is executed.
<p align="center">
<img src="images/commands/DashboardCommand/dashboard.png" width="500"/>
</p>

### <a name="exit"></a>2.3 Exit: `exit` or F1 on keyboard

Exits ProgrammerError and closes the GUI.


## <a name="data-features"></a>3. Data Features

### <a name="fill-sample-data"></a>3.1 Fill Sample Data: `fill`

Fills the program with sample data if no data is already present. Otherwise, throws an error message.

<div markdown="span" class="alert alert-warning">:exclamation: **Note:**
As this is a mass operation, it might take a couple of seconds to complete.
</div>

### <a name="purge-all-data"></a>3.2 Purge all Data: `purge`

Clears all data in PE. This can be used to delete pre-existing sample data or existing student data.
Otherwise, throws an error if no pre-existing data.

### <a name="upload-data"></a>3.3 Upload Data: `upload` or F4 on keyboard

Uploads student data via a CSV file with the following header: `studentId,classId,name,email`. Note that the data
should contain **only** the student ID, class ID, name and email field respectively and spaces directly before or after
commas should be avoided.

Here is an [example CSV file](https://github.com/AY2122S1-CS2103-F09-3/tp/blob/master/sample_upload/validDataForUpload.csv) on GitHub.
You may download files from GitHub following the instructions [here](https://stackoverflow.com/questions/4604663/download-single-files-from-github).

<div markdown="block" class="alert alert-warning">:exclamation: **Note:**
the CSV should *not* contain students' lab results since this functionality is only meant for the TA to automate the adding of students to PE.

- If there are already existing students, the upload will **overwrite** the existing data rather than
  append to it. This is because in a typical use case, the TA would not want to have the existing data kept if they
  would like to use student data from their own CSV file.
- Furthermore, simply uploading the CSV obtained via the `download` command will result in the file being rejected

</div>

In summary:
1. Select a valid CSV file (with header: `studentId,classId,name,email`) from the file chooser.
2. Note that this command is **not** meant for uploading lab results. Rather, it is only for automating the adding of
   students to PE.

### <a name="download-data"></a>3.4 Download Data: `download` or F3 on keyboard

Downloads the student data to a CSV file in the chosen directory. The file will be automatically named `programmerError.csv`.

### <a name="edit-data-file"></a>3.5 Edit The Data File Directly (For advanced users)

Instead of using commands to edit students' details and lab results, you may change the json file directly.

Note that ProgrammerError will only accept data files fulfilling the restrictions specified in the [parameter summary](#parameter-summary).
The absence of an attribute and/or invalid value for an attribute will result in an error message shown in the logger, and ProgrammerError 
will start with an empty json data file instead. 

Here are some examples of corrupted data that is not accepted by ProgrammerError.


      //Absent attribute example: labNumValue attribute is missing
      labResultList" : [ {
      "actualScoreValue" : 10,
      "totalScoreValue" : 20
      }

      //Invalid attribute example: labNumValue is not between 1 to 13
      "labResultList" : [ {
      "labNumValue" : 20,
      "actualScoreValue" : 15,
      "totalScoreValue" : 20
      }


## <a name="student-features"></a>4. Student Features

### <a name="add-student"></a>4.1 Add Student: `add`

Adds a student to ProgrammerError with their student ID, class ID and email. Both the student ID and email must be unique.

Format: `add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID> -email <EMAIL>`

<div markdown="block" class="alert alert-success">

**Example:**

- `add -n Sherwin -sid A1234567X -cid B01 -email e0542421@u.nus.edu` Adds the student called Sherwin with student ID
  A1234567X, class ID B01 and email e0542421@u.nus.edu PE.

</div>

### <a name="edit-student"></a>4.2 Edit Student's Details or Lab Result : `edit`

Edits the details or grade of an existing student in PE as specified by the `INDEX_IN_LIST` argument.
The corresponding fields of the student will be updated according to the input arguments.

* Both the student ID and email must be unique.
* 1-indexing is used here as according to the displayed list.
* Any combination of the optional arguments can be provided.
* At least one of the optional argument must be provided.
* Take note that the parameters `-ln` and `-s` needs to be specified together.

Format: `edit <INDEX_IN_LIST> [-n <NAME>] [-sid <STUDENT_ID>] [-cid <CLASS_ID>] [-email <EMAIL>] [-ln <LAB_NUM> -s <ACTUAL_SCORE>]`

<div markdown="block" class="alert alert-success">

**Examples:**

- `edit 1 -n Elon Musk` Updates the name of the student at index 1 (1-indexed) to 'Elon Musk'.
- `edit 1 -ln 4 -s 29` Updates the lab 4 result of the student at index 1 (1-indexed) to 29.

</div>

### <a name="delete-student"></a>4.3 Delete Student: `delete`

Deletes the specified student from the PE. Take note that `INDEX_IN_LIST` is 1-indexed.

Format: `delete <INDEX_IN_LIST>`

<div markdown="block" class="alert alert-success">

**Example:**

- `delete 23` Deletes the data of the student at index 23

</div>

### <a name="filter-student"></a>4.4 Filter Students: `filter`

Filter the students in ProgrammerError based on the specified arguments provided (name, student ID, class ID and email).
* Any combination of the optional arguments can be provided.
* At least one of the optional argument must be provided.
* Arguments can be provided in any order and are **case-insensitive**.
* ProgrammerError will display the filtered list of students whose details matches all the specified arguments.
* The arguments are matched to the corresponding details as long as the details of the students contains the
sequence of characters provided in the argument string.
* `filter` command does not follow the constraints as specified in [parameter summary](#parameter-summary).

Format: `filter [-n <NAME>] [-sid <STUDENT_ID>] [-cid <CLASS_ID>] [-email <EMAIL>]`

Below shows an example when `filter -cid B01` is executed with 4 students from class B01 filtered out.
<p align="center">
<img src="images/commands/FilterCommand/filter.png" width="600"/>
</p>


<div markdown="block" class="alert alert-success">

**Examples:**

- `filter -sid A1234567X` Lists all students whose student ID contains `A1234567X`.
- `filter -n abc` Lists all students whose name contains `abc`.
- `filter -cid B01` Lists all students whose class ID contains the character sequence `B01`.
- `filter -email e1234567` Lists all students whose email contains the character sequence `e1234567`.
- `filter -n Sherwin -cid B01` Lists all students whose name contains the character sequence `Sherwin` and belongs to a
  class with the class ID containing the character sequence `B01`.

</div>

### <a name="show-student"></a>4.5 Show Student Details: `show`

Shows a particular student's details including their scores for each lab.

The student particular and lab results will be updated if other commands modify their fields.

Format: `show <INDEX_IN_LIST>`

<p align="center">
<img src="images/commands/ShowCommand/show.png" width="600"/>
</p>

<div markdown="block" class="alert alert-success">

**Example:**

- `show 1` Shows the particulars and lab results of student at index 1 as displayed in the list.

</div>

### <a name="list-students"></a>4.6 List All Students: `list`

Displays a list of all students in the left panel. This list is ordered first by class ID followed by student name.




## <a name="lab-features"></a>5. Lab Features

<div markdown="span" class="alert alert-warning">:exclamation: **Note:**
As the following commands are mass operations, they might take slightly longer than usual.
</div>

### <a name="add-lab"></a>5.1 Add Lab: `addlab`

Adds a lab to every student with the lab number and total score.

Format: `addlab -ln <LAB_NUM> -ts <TOTAL_SCORE>`


<div markdown="block" class="alert alert-success">

**Examples:**

- `addlab -ln 1 -ts 20` Adds a lab with number 1 and total score 20 for all students.
- `addlab -ln 2 -ts 30` Adds a lab with number 2 and total score 30 for all students.

</div>

### <a name="edit-lab"></a>5.2 Edit Lab: `editlab`

Edits the details of an existing lab for every student.
* Any combination of the optional arguments can be provided.
* At least one of the optional argument must be provided.

Format: `editlab -ln <LAB_NUM> [-nln <NEW_LAB_NUM>] [-ts <NEW_LAB_SCORE>]`

<div markdown="block" class="alert alert-success">

**Examples:**

- `editlab -ln 1 -ts 30` Changes the total score of lab 1 to 30 for all students.
- `editlab -ln 1 -nln 2` Changes the number of an existing lab from 1 to 2 for all students.
- `editlab -ln 1 -nln 2 -ts 30` Changes the number of an existing lab from 1 to 2 and the total score of the same lab to 30 for all students.

</div>

### <a name="delete-lab"></a>5.3 Delete Lab: `dellab`

Deletes an existing lab from every student in ProgrammerError.

Format: `dellab -ln <LAB_NUM>`

<div markdown="block" class="alert alert-success">

**Example:**

- `dellab -ln 1`: Deletes lab 1 for all students if it exists.

</div>


## <a name="command-summary"></a>Command Summary

Command | Format & Examples
--------| ----------------------
**Add** | `add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID> -email <EMAIL>`
**Add Lab** | `addlab -ln <LAB_NUM> -ts <TOTAL_SCORE>`
**Dashboard** | `dashboard`
**Delete** | `delete <INDEX_IN_LIST>`
**Delete Lab** | `dellab -ln <LAB_NUM>`
**Download** | `download`
**Edit** | `edit <INDEX_IN_LIST> [-n <NAME>] [-sid <STUDENT_ID>] [-cid <CLASS_ID>] [-email <EMAIL>] [-ln <LAB_NUM> -s <ACTUAL_SCORE>]`
**Edit Lab** | `editlab -ln <LAB_NUM> [-nln <NEW_LAB_NUM>] [-ts <NEW_LAB_SCORE>]`
**Exit** | `exit`
**Fill** | `fill`
**Filter** | `filter [-n <NAME>] [-sid <STUDENT_ID>] [-cid <CLASS_ID>] [-email <EMAIL>]`
**Help** | `help`
**Purge** | `purge`
**Show** | `show <INDEX_IN_LIST>`
**Upload** | `upload`


