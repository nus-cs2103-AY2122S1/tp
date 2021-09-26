---
layout: page
title: User Guide
---

# User Guide
ProgrammerError (P|E) is a desktop app for managing students' information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Designed for CS2100 Lab TAs, this application is optimized to track personal particulars, grades and attendance of your students across different classes. If you are familiar with Unix commands, this is definitely for you!

## Table of Contents
[Quick Start](#quick-start)
[Features](#features)
  1.  [View Sample Data](#view-sample-data)
  2.  [Purge All Data: `purge`](#purge-all-data)
  3.  [View Help: `help`](#view-help)
  4.  [Add Student: `add`](#add-student)
  5.  [View Student Details: `view`](#view-student)
  6.  [Update Student's Grade: `update`](#update-student)
  7.  [Delete Student: `delete`](#delete-student)
  8.  [Saving the data](#save-the-data)
[Command Summary](#command-summary)

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `programmerError.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ProgrammerError.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add -n Sherwin -sid A1234567X -cid B01`**: Adds a student named `Sherwin` to the PE with his Student ID and Class ID.

   * **`view -sid A1234567X`** Views a particular student's by its Student ID

   * **`update -sid A1234567X -grade 100`** Updates a student's grade with the -grade flag

   * **`delete -sid A1234567X`**  : Deletes a student's record by the student ID

   * **`purge`**: Deletes all existing contacts

   * **`exit`**: Exits the app.

1. Refer to the [Features](#features) below for details of each command.

## Features

**Notes about the command format (Unix Command Syntax):**

- Flags will be used to specify different options for the commands. For example, the `-sid` flag can be used to specify a student's student ID.
- Parameters can be in any order.e.g. if the command specifies `-n NAME -sid STUDENT_ID`,
  `-sid STUDENT_ID -n NAME`is also acceptable.
- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken. e.g. if you specify `-n Allard -n Xian Yi` , only `-n Xian Yi` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `purge`) will be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### 1. Viewing sample data

Sample Data will be available on starting PE.

### 2. Purge all data: `purge`

Purges all existing data to start adding your own data. \
Clears all entries data in PE. \
Example: `purge`

### 3. Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Example: `help`

### 4. Adding a student: `add`

Adds a student to ProgrammerError with the his/her student ID and class ID.

Format: `add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID>`

Examples:
  * `add -n Sherwin -sid A1234567X -cid B01`: Adds the student called Sherwin with student ID A1234567X and class ID B01 to ProgrammerError.

  * `add -n Betsy -sid A2345678X -cid B02`: Adds the student called Betsy with student ID A2345678X and class ID B02 to ProgrammerError.
