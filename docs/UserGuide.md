---
layout: page
title: User Guide
---

Teaching Assistant’s Buddy (TAB) is a **desktop app for managing tutees, optimized for use via a Command Line Interface** (CLI) while still 
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your tutee management 
tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tab.jar` from [here](https://github.com/AY2122S1-CS2103-F09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for TAB.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`add teachingAssistantBuddy CS2103`** and pressing Enter will add the teachingAssistantBuddy CS2103 to TAB.<br>
   Some example commands you can try:

   * **`teachingAssistantBuddy add CS2103`** : Adds the teachingAssistantBuddy CS2103 to TAB

   * **`student add A0123456A, John Doe, @johndoe, john.doe@u.nus.edu`** : Adds student John Doe to TAB

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<angle brackets>` are the parameters to be supplied by the user.<br>
  e.g. in `teachingAssistantBuddy add <teachingAssistantBuddy name>`, `<teachingAssistantBuddy name>` is a parameter which can be used as `add teachingAssistantBuddy CS2103`.

* Parameters must be in the specified order.<br>
  e.g. if the command specifies `teachingAssistantBuddy add <teachingAssistantBuddy name>, <task name>`, `teachingAssistantBuddy add <task name>, <teachingAssistantBuddy name>` will not work

* Commas are used to separate parameters supplied by the user.

* In the current version, refrain from using commas within the parameters itself.

</div>


### Module add task: `add task`

Create a new task for a specific teachingAssistantBuddy

Format: `teachingAssistantBuddy add <teachingAssistantBuddy name>, <task name>`

Examples:
* `teachingAssistantBuddy add CS2103, assignment1`: Adds the task assignment1 to teachingAssistantBuddy CS2103
* `teachingAssistantBuddy add CS2100, lab1`: Adds the task lab1 to teachingAssistantBuddy CS2100

### Creating a new teachingAssistantBuddy: `teachingAssistantBuddy add`

Adds a new teachingAssistantBuddy to TAB

Format: `teachingAssistantBuddy add <teachingAssistantBuddy name>`

Examples:
* `teachingAssistantBuddy add CS2103`: Adds teachingAssistantBuddy CS2103 to TAB
* `teachingAssistantBuddy add CS2100`: Adds teachingAssistantBuddy CS2100 to TAB

### Creating a new task for a specific teachingAssistantBuddy: `teachingAssistantBuddy add task`

Adds a new task to the specified teachingAssistantBuddy

Format: `teachingAssistantBuddy add <teachingAssistantBuddy name>, <task name>`

Examples:
* `teachingAssistantBuddy add CS2103, assignment1`: Adds the task assignment1 to teachingAssistantBuddy CS2103
* `teachingAssistantBuddy add CS2100, lab1`: Adds the task lab1 to teachingAssistantBuddy CS2100

### Add student to a specific teachingAssistantBuddy: `teachingAssistantBuddy add student`

Add a new student to the specified teachingAssistantBuddy
Also automatically copies over the task list from the teachingAssistantBuddy to the student

Format: `teachingAssistantBuddy add <teachingAssistantBuddy name>, <student name>`

Examples:
* `teachingAssistantBuddy add CS2103, A1234567A`: Adds student with id A1234567A to teachingAssistantBuddy CS2103
* `teachingAssistantBuddy add CS2100, A7654321A`: Adds student with id A7654321A to teachingAssistantBuddy CS2100

### Delete student from a specific teachingAssistantBuddy: `teachingAssistantBuddy delete student`

Deletes the specified student from the specified teachingAssistantBuddy

Format: `teachingAssistantBuddy delete <teachingAssistantBuddy name>, <student id>`

Examples:
* `teachingAssistantBuddy delete CS2103, A1234567A`: Removes student with id A1234567A from teachingAssistantBuddy CS2103’s list of students
* `teachingAssistantBuddy delete CS2100, A7654321A`: Removes student with id A7654321A from teachingAssistantBuddy CS2100’s list of students

### Delete teachingAssistantBuddy from TAB: `teachingAssistantBuddy delete`

Deletes the specified teachingAssistantBuddy from TAB

Format: `teachingAssistantBuddy delete <teachingAssistantBuddy name>`

Examples:
* `teachingAssistantBuddy delete CS2103`: Deletes teachingAssistantBuddy CS2103 from TAB
* `teachingAssistantBuddy delete CS2100`: Deletes teachingAssistantBuddy CS2100 from TAB

### Create a new student: `student add`

Create a new student

Format: `student add <student id>, <student name>, <student teleHandle>, <student email>`

Examples:
* `student add A0123456A, John Doe, @johndoe, john.doe@u.nus.edu`: add student John Doe

### Get student information: `student get`

Get the student’s information (name, id, telehandle, email)

Format: `student get <student id>`

Examples:
* `student get A0123456A`: get information of the student with student id A0123456A

## FAQ

**Q**: How do I assign tasks to students<br>
**A**: Adding the student to the teachingAssistantBuddy will automatically add the tasks in the teachingAssistantBuddy to the student

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `teachingAssistantBuddy add <teachingAssistantBuddy name>` <br> e.g., `teachingAssistantBuddy add CS2103`
**Delete** | `teachingAssistantBuddy delete <teachingAssistantBuddy name>`<br> e.g., `teachingAssistantBuddy delete CS2103`
