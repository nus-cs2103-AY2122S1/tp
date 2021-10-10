---
layout: page
title: User Guide
---

ProgrammerError (P\|E) is a desktop app for managing students' information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Designed for CS2100 Lab TAs, this application is optimized to track personal particulars, grades and attendance of your students across different classes. If you are familiar with Unix commands, this is definitely for you!

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  1. [View Sample Data](#view-sample-data)
  2. [Purge All Data: `purge`](#purge-all-data)
  3. [Fill Sample Data: `fill`](#fill-sample-data)
  4. [View Help: `help`](#view-help)
  5. [Add Student: `add`](#add-student)
  6. [View Student Details: `view`](#view-student)
  7. [Update Student's Grade: `update`](#update-student)
  8. [Delete Student: `delete`](#delete-student)
  9. [Exit: `exit`](#exit)
  10. [Save data](#save-data)
- [Command Summary](#command-summary)


## <a name="quick-start"></a>Quick Start
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `programmerError.jar` from [here](https://github.com/se-edu/addressbook-level3/releases) [coming soon].

3. Copy the file to the folder you want to use as the _home folder_ for your ProgrammerError.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add -n Sherwin -sid A1234567X -cid B01`**: Adds a student named `Sherwin` to the PE with his Student ID and Class ID.

   * **`view -sid A1234567X`** Views a particular student's by its Student ID

   * **`update -sid A1234567X -grade 100`** Updates a student's grade with the -grade flag

   * **`delete -sid A1234567X`**  : Deletes a student's record by the student ID

   * **`purge`**: Deletes all existing contacts
    
   * **`fill`**: Fills the app with sample data

   * **`exit`**: Exits the app.

6. Refer to the [Features](#features) below for details of each command.

## <a name="features"></a>Features

**Notes about the command format (Unix Command Syntax):**

- Flags will be used to specify different options for the commands. For example, the `-sid` flag can be used to specify a student's student ID.
- Parameters can be in any order.e.g. if the command specifies `-n NAME -sid STUDENT_ID`,
  `-sid STUDENT_ID -n NAME`is also acceptable.
- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken. e.g. if you specify `-n Allard -n Xian Yi` , only `-n Xian Yi` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `purge`) will be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### <a name="view-sample-data"></a>1. View sample data

Sample Data will be available on starting PE.

### <a name="purge-all-data"></a>2. Purge all Data: `purge`

Purges all pre-existing sample data to start adding your own data or purge the user data. \
Clears all data in PE. \
Example: `purge`

### <a name="fill-sample-data"></a>2. Fills Sample Data: `fill`

If no data present, fills it with sample data. Otherwise, throws an error message. \
Example: `fill`

### <a name="view-help"></a>3. View Help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Example: `help`

### <a name="add-student"></a>4. Add Student: `add`

Adds a student to ProgrammerError with the his/her student ID and class ID.

Format: `add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID>`

Examples:
- `add -n Sherwin -sid A1234567X -cid B01`: Adds the student called Sherwin with student ID A1234567X and class ID B01 to ProgrammerError.

- `add -n Betsy -sid A2345678X -cid B02`: Adds the student called Betsy with student ID A2345678X and class ID B02 to ProgrammerError.

### <a name="view-student"></a>5. View Student Details: `view`

Views all the related information of a student stored on ProgrammerError with his/her student ID.

Format: `view -sid <STUDENT_ID>`

Examples:

- `view -sid A1234567X` Views all related information of the student with student ID A1234567X in ProgrammerError.

### <a name="update-student"></a>6. Update Student's Grade : `update`

Updates the grade of an existing student's in the PE.

Format:`update -sid <STUDENT_ID> -grade <GRADE_SCORE>`

- Updates the student with `<STUDENT_ID> with the grade <GRADE_SCORE>`
- Existing values will be updated to the input values.

Examples:

- `update -sid A1234567X -grade 100` Updates the grade of the student with student ID A1234567X to be 100.
- `update -sid A2345678X -grade 97.5` Updates the grade of the student with student ID A2345678X to be 97.5.

### <a name="delete-student"></a>7. Delete Student: `delete`

Deletes the specified student from the PE.

Format: `delete -sid <STUDENT_ID>`

- Deletes the student with student ID of `<STUDENT_ID>`.

Examples:

- `delete -sid A1234567X` Deletes the data of the student with student ID A1234567X
- `delete -sid A2345678X` Deletes the data of the student with student ID A2345678X

### <a name="exit"></a>8. Exit: `exit`

Exits ProgrammerError and closes the GUI.

### <a name="save-data"></a>9. Save Data

ProgrammerError data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## <a name="command-summary"></a>Command Summary

Command | Format & Examples
--------| ----------------------
**Purge** | `purge`
**Fill** | `fill`
**Help** | `help`
**Add** | `add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID>`
**View** | `view -sid <STUDENT_ID>`
**Update** | `update -sid <STUDENT_ID> -grade <GRADE_SCORE>`
**Delete** | `delete -sid <STUDENT_ID>`
**Exit** | `exit`
