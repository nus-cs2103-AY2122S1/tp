---
layout: page
title: User Guide
---

Teaching Assistant’s Buddy (TAB) is a **desktop app for NUS Teaching Assistants to help them manage students, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your student management
tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `tab-v1.3.0.jar` from [here](https://github.com/AY2122S1-CS2103-F09-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for TAB.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`add module m/CS2105`** and pressing Enter will add the module CS2105 to TAB.<br>
   Some example commands you can try:

   * **`add module m/CS2105`** : Adds the module CS2105 to TAB

   * **`add student m/CS2103 i/A1234567A n/John Doe t/@johndoe e/johnd@example.com`** : Adds student John Doe to module CS2105

6. Refer to the [Features](#features) below for details of each command.

7. Refer to the [Command summary](#command-summary) below for a quick table of reference to all valid commands.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<angle brackets>` are the parameters to be supplied by the user.<br>
  e.g. in `add module m/<module name>`, `<module name>` is a parameter which can be used as `add module m/CS2105`.

* Parameters must be in the specified order.<br>
  e.g. if the command specifies `add task m/<module name> ti/<task id> a/<task name> d/<task deadline>`, `add task ti/<task id> m/<module name> a/<task name> d/<task deadline>` will not work.

* Parameters must be prefixed with their specific prefixes.<br>
  e.g. the command `add module m/CS2105` will add the module CS2105 to TAB, the command `add module CS2105` will not work.

* Parameters must only contain permitted characters.

</div>


### Creating a new module: `add module`

Adds a new module to TAB.

Format: `add module m/<module name>`

Examples:
* `add module m/CS2103`: Adds module CS2103 to TAB
* `add module m/CS2100`: Adds module CS2100 to TAB


### Adding new task to a module: `add task`

Creates a new task for a specific module.<br>
Task id must begin with a capital letter 'T', followed by numbers only.<br>
Task name must only contain alphanumeric characters and spaces, and it must not be blank.<br>
Task deadline must only contain alphanumeric characters and dashes.

Format: `add task m/<module name> ti/<task id> a/<task name> d/<task deadline>`

Examples:
* `add task m/CS2103 ti/T1 a/assignment1 d/2021-10-12`:
  * Adds the task assignment1 with deadline on 12 October 2021 to module CS2103
* `add task m/CS2100 ti/T1 a/lab1 d/2021-10-13 `:
  * Adds the task lab1 with deadline on 13 October 2021 to module CS2100


### Editing a module's name: `edit module`

Edits the name of the module.

Format: `edit module m/<old module name> mn/<new module name>`

Examples:
* `edit module m/CS2103 mn/CS2105`: 
  * Renames the module "CS2103" to "CS2105"


### Add student to a specific module: `add student`

Add a new student to the specified module.<br>
Also automatically copies over the task list from the module to the student.

Format: `add student m/<module name> i/<student id> n/<student name> t/<student tele handle> e/<student email>`

Examples:
* `add student m/CS2103 i/A0123456A n/John Doe t/@johndoe e/john.doe@u.nus.edu`: 
  * Adds student with id A0123456A to module CS2103
* `add student m/CS2100 i/A0123457A n/Amy t/@amylee e/amy@u.nus.edu`: 
  * Adds student with id A0123457A to module CS2100

### Delete student from a specific module: `delete student`

Deletes the specified student from the specified module.

Format: `delete student m/<module name> i/<student id>`

Examples:
* `delete student m/CS2103 i/A1234567A`: 
  * Removes student with id A1234567A from module CS2103’s list of students
* `delete student m/CS2100 i/A7654321A`: 
  * Removes student with id A7654321A from module CS2100’s list of students

### Edit a student's information: `edit student`

Edits a student's information.<br>
The identity fields(module name/student id) must be provided.<br>
At least one editable field (name/tele handle/email) must be provided, and only provided editable fields will be changed.

Format: `edit student m/<module name> i/<student id> n/<edited student name> t/<edited student tele handle> e/<edited student email>`

Examples:
* `edit student m/CS2103 i/A0123456A n/John Doe`: 
  * Edits the name of the student with id A0123456A in module CS2103 to John Doe
* `edit student m/CS2100 i/A0123457A t/@amylee e/amy@u.nus.edu`: 
  * Edits the tele handle and email of the student with id A0123457A in module CS2100 to @amylee and amy@u.nus.edu respectively

### Edit a task's information: `edit task`

Edits a task's information.<br>
The identity fields (module name/task id) must be provided.<br>
At least one editable field (name/deadline) must be provided, and only provided editable fields will be changed.<br>
Task name must only contain alphanumeric characters.<br>
Task deadline must only contain alphanumeric characters and dashes.

Format: `edit task m/<module name> ti/<task id> a/<edited task name> d/<edited task deadline>`

Examples:
* `edit task m/CS2103 ti/T10 a/Final exam d/20-11-2021`: 
  * Edits the name and deadline of the task with task id 'T10' to 'Final exam' and '20-11-2021' respectively
* `edit task m/CS2103 ti/T10 d/23-11-2021`: 
  * Edits only the deadline of the task with task id 'T10' to '20-11-2021'

### Delete a task from a module: `delete task`

Deletes the specified task from the specified module using the TaskID.<br>
Also deletes the task from all the students in that module.

Format: `delete task m/<module name> ti/<task id>`

Examples:
* `delete task m/CS2103 ti/T1`: 
  * Deletes the task with task ID T1 from CS2103
* `delete task m/CS2105 ti/T2`: 
  * Deletes the task with task ID T2 from CS2105

### Mark a task as done: `mark done`

Mark the specified task from the specified module under the specified student as done.<br>
This means that this student has completed this task of this module.

Format: `mark done m/<module name> i/<student id> ti/<task id>`

Examples:
* `mark done m/CS2103 i/A1234567A ti/T1`:
  * Marks the task with ID T1 under student with ID A1234567A of module CS2103 as done.
  * This means that student A1234567A has completed task T1 of module CS2103.

### Mark a task as undone: `mark undone`

Mark the specified task from the specified module under the specified student as undone.<br>
This means that this student has not completed this task of this module, possibly to correct a mistake by the TA.

Format: `mark undone m/<module name> i/<student id> ti/<task id>`

Examples:
* `mark undone m/CS2103 i/A1234567A ti/T1`:
  * Mark the task with ID T1 under student with ID A1234567A of module CS2103 as undone. 
  * Student A1234567A has not completed task T1 of module CS2103 yet. 
  * But for some reason (e.g. human error) the task is marked as done.

### Find a student: `find`

Find the student in the module specified along with the student's information.

Format: `find m/<module name> i/<student id>`

Examples:
* `find m/CS2103 i/A0123456A`: 
  * Finds and shows student with ID A0123456A from module CS2103

### Delete module from TAB: `delete module`

Deletes the specified module from TAB.

Format: `delete module m/<module name>`

Examples:
* `delete module m/CS2103`: 
  * Deletes module CS2103 from TAB
* `delete module m/CS2100`: 
  * Deletes module CS2100 from TAB

### Go to homepage: `home`

Returns to homepage which shows the complete list of modules and students along with their tasks.

Format: `home`

### Clear all information: `clear`

Clears all information stored in the TAB application.

Format: `clear`

### Exit the application: `exit`

Closes the TAB application.

Format: `exit`

## FAQ

**Q**: How do I assign tasks to students?<br>
**A**: Adding the student to the module will automatically add the tasks in the module to the student.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples (optional parameters may be omitted from examples)
--------|------------------
**Add Module** | `add module m/<module name>` <br> e.g., `add module m/CS2103`
**Add Student** | `add student m/<module name> i/<student id> n/<student name> t/<student tele handle> e/<student email>` <br> e.g., `add student m/CS2103 i/A0123456A n/John Doe t/@johndoe e/john.doe@u.nus.edu`
**Add Task** | `add task m/<module name> ti/<task id> a/<task name> d/<task deadline>` <br> e.g., `add task m/CS2103 ti/T1 a/assignment1 d/2021-10-12`
**Clear** | `clear` <br> e.g., `clear`
**Delete Module** | `delete module m/<module name>` <br> e.g., `delete module m/CS2103`
**Delete Student** | `delete student m/<module name> i/<student id>` <br> e.g., `delete student m/CS2103 i/A1234567A`
**Delete Task** | `delete task m/<module name> ti/<task id>` <br> e.g., `delete task m/CS2103 ti/T1`
**Edit Module** | `edit module m/<old module name> mn/<new module name>` <br> e.g., `edit module m/CS2103 mn/CS2105`
**Edit Student** | `edit student m/<module name> i/<student id> n/<edited student name> t/<edited student tele handle> e/<edited student email>` <br> e.g., `edit student m/CS2100 i/A0123457A t/@amylee e/amy@u.nus.edu`
**Edit Task** | `edit task m/<module name> ti/<task id> a/<edited task name> d/<edited task deadline>` <br> e.g., `edit task m/CS2103 i/T10 a/Final exam d/20-11-2021`
**Exit** | `exit` <br> e.g., `exit module m/CS2103`
**Find** | `find m/<module name> i/<student id>` <br> e.g., `find m/CS2103 i/A0123456A`
**Home** | `home` <br> e.g., `home`
**Mark Done** | `mark done m/<module name> i/<student id> ti/<task id>` <br> e.g., `mark done m/CS2103 i/A1234567A ti/T1`
**Mark Undone** | `mark undone m/<module name> i/<student id> ti/<task id>odule m/<module name>` <br> e.g., `mark undone m/CS2103 i/A1234567A ti/T1`
