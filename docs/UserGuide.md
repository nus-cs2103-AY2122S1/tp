---
layout: page
title: Uni-Fy User Guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------
## 1. Introduction
Uni-Fy is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Uni-Fy can get your tasks to display faster than traditional GUI apps.

Interested to know more? Take a look at our Quick Start guide. Enjoy!




--------------------------------------------------------------------------------------------------------------------

## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Uni-Fy.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![find_command](images/UIUpdated.png)

1. Type the command in the command box and press Enter to execute it.
   * Refer to the [**Features**](#3-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

This section highlights all the commands that Uni-Fy supports. These include details about the format of the command and example scenarios of each command. 

**:information_source: Notes about the command format:**<br>

* Italicised words represent parameters that are meant to be supplied by the user
  * e.g. in add *n/task_name d/date*, the *task_name* keyword represents a task and the *date* keyword represents a date, both to be inputted by the user

* Items inside round brackets are optional
  * e.g. in delete *n/task_name (d/date)*, a user can choose to include the date for the task if there are multiples of it

* In user inputted commands:
  * Items with n/ before them represent the exact name of the task
  * Items with tg/ before them represent the tag of the task
  * Items with d/ before them represent the date of the task
  * Items with t/ before them represent the time of the task
    * Date is represented in YYYY-MM-DD format
    * Time is represented in HH:MM format
</div>

### 3.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### 3.2 Adding a task: `add` 

Add a task to the task list.

![add_command](images/addCommand.jpeg)

After running the add command the task gets added to the task list as shown below:

![add_command_after](images/addCommandAfter.jpeg)

Format:
- <code> add <i>n/task_name t/time d/date tg/level </i> </code>

Interpretation
* Adds task with the name <code><i>n/task_name</i></code>
* Adds task with the time <code><i>t/time</i></code> 
  * The format should be as follows <code><i>t/hh:mm</i></code>
* Adds task with the date <code><i>d/date</i></code>
  * The format should be as follows <code><i>d/yyyy-mm-dd</i></code>
* Add task with the tag <code><i>tg/level</i></code>
* The task is added for that date only at the given time
  * Useful if it is an event or for marking dates for exams


Examples:
* `add n/CS2103 test t/16:30 d/2021-12-01 tg/Important ` adds the `CS2103 test` task on `2021-12-01`, scheduled for `16:30` with the priority set as `Important`

### 3.3 Locating a task by name: `find` (coming soon)

Find tasks with matching keywords and show them in the task list.

![find_command](images/findCommand.jpeg)

After running the find command, the tasks having the mentioned keyword are displayed as shown below:

![find_command_after](images/findCommandAfter.jpeg)

Format: `find keyword (more_keywords) (d/date)`

Interpretation:
* The search is case-insensitive.
* The order of the keywords does not matter.
* Only the name of the task is searched.
* Incomplete keywords will be matched e.g. Qu will match Quiz
* Only tasks containing all keywords will be shown
* if `d/date` field is provided:
  * Only tasks due on that date is searched.

Examples:
* `find quiz` returns CS2103 Quiz, GEQ1000 Quiz 1, GEQ1000 Quiz 2
* `find Quiz GEQ1000` returns GEQ1000 Quiz 1,  GEQ1000 Quiz 2
* `find Quiz GEQ1000 d/2021-10-10` returns all the GEQ1000 Quiz that is due by 2021-10-10


### 3.4 Showing Tasks: `show` (coming soon)

Show all tasks that have been added to the app in a specific week.

![show_command](images/showCommand41.jpeg)

Format:
- <code> show <i>week_number</i> </code>
- <code> show <i>d/date</i> </code>

Interpretation
* If <code><i>week_number</i></code> field is provided:
  * Searches for the task with the given date and show it on the GUI
  * Useful in cases where user wants to show all tasks in the given week
* If <code><i>d/date</i></code> field is provided:
  * Show tasks matching the given the date

Examples:
* `show 1` displays tasks in week `1`
* `show d/2021-10-10` displays the tasks in the week of `2021-10-10`


### 3.5 Setting Priority Level for Tasks: `tag` (coming soon)

Set a task's priority.

Format:
- <code> <i> tag n/task_name (d/date) tg/level </i> </code>
- <code> <i> tag task_id (d/date) tg/level </i> </code>

Interpretation
* If <code><i>n/task_name</i></code> field is provided:
  * Depending on the <code><i>tg/level </i></code> provided it sets priority of the task with the name exactly matching the <code><i>n/task_name </i></code> or <code><i>task_id</i></code> to
    * Urgent
    * Important
    * Medium
    * Low
  * The <code><i>tg/level</i></code> field is case-insensitive
  * If <code><i>date</i></code> field is provided, it sets priority for the task in the given date
    * Useful in cases when there are multiple copies of the same task , recurring tasks, etc.
  * If <code><i>d/date</i></code> field is not provided, it sets priority for every occurrence of tasks matching the given name

* If <code><i>task_id</i></code> is provided:


Examples:
* `tag 5 tg/Urgent` sets the priority of task 5 in the task list to `Urgent`
* `tag n/quiz d/2021-10-10 tg/Important` sets priority of the quiz task on 2021-10-10 to `Important`


### 3.6 Deleting tasks : `delete` (coming soon)

Delete a specific task that has been added to your app.

![delete_command](images/deleteCommand.jpeg)

After running the delete command, the task at that particular index gets deleted as shown below:

![delete_command_after](images/deleteCommandAfter.jpeg)

Format:
- <code> delete <i>task_id</i> </code>
- <code> delete <i>n/task_name (d/date)</i> </code>

Interpretation
* If <code><i>task_id</i></code> field is provided:
  * Deletes the task corresponding to the <code><i>task_id</i></code>
* If <code><i>n/task_name</i></code> field is provided:
  * Deletes the task with name **exactly matching** that given in the <code><i>n/task_name</i></code> field
  * If <code><i>d/date</code></i> field is provided, search for the task in the given date to delete
    * Useful in cases where there are multiple copies of the same task, recurring tasks, etc.
  * If <code><i>d/date</code></i> field is not provided, deletes **every occurence** of tasks matching the given name

Examples:
* `delete 3` removes the third item in the task list
* `delete n/quiz` removes every `quiz` task in the app
* `delete n/quiz d/2021-10-10` deletes only the `quiz` task on `2021-10-10`

### 3.7 Deleting all tasks : `clear`

Clears all the tasks from the task list of Uni-Fy.

![clear_command_before](images/clearCommandBefore.jpeg)

When you run the clear command, all the tasks in the task list of Uni-Fy get deleted as shown below:

![clear_command_after](images/clearCommandAfter.jpeg)

Format: `clear`

### 3.8 Undoing the previous command : `undo` `[coming in v1.3]`

Reverts your previous command which modified the task list of Uni-Fy.

Format: `undo`

### 3.9 Exiting the program : `exit`

Exits the program.

Format: `exit`

### 3.10 Saving the data

**Uni-Fy** data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 3.11 Editing the data file

**Uni-Fy** data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Uni-Fy will discard all data and start with an empty data file at the next run.
</div>

### 3.12 Retrieving past commands `[coming in v1.3]`

Shows the past commands run on **Uni-Fy**.

Format: `history`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Your history is erased when you close and reopen the app. Do not close the app if you have anything in the history that you need to refer to later.
</div>

### 3.13 Archiving data files `[coming in v2.0]`

_Details coming soon ..._



--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: What are the minimum system requirements for this application?<br>
**A**: If your computer runs any of the Operating Systems found [here](https://www.oracle.com/java/technologies/javase/products-doc-jdk11certconfig.html), you will be able to run **Uni-Fy**.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Tasks home folder.

**Q**: Is the source code of this application freely available?<br>
**A**: Yes, our code is available on our [GitHub repository](https://github.com/AY2122S1-CS2103T-W17-4/tp) under the MIT License. If you wish to contribute to the codebase of this application, please read the [Developer Guide](https://ay2122s1-cs2103t-w17-4.github.io/tp/DeveloperGuide.html) and make a pull request to our repository.

**Q**: I've spotted a bug in your application. How can I report this?<br>
**A**: We would love to hear from you. You can make a GitHub issue at this [link](https://github.com/AY2122S1-CS2103T-W17-4/tp/issues) with the relevant details.

**Q**: Is my data secure with this application?<br>
**A**: Your data is stored safely on your machine. **Uni-Fy** stores the data locally and does not upload any data to servers on the Internet. However, you are recommended to use antivirus software on your machine to prevent unauthorised hackers from accessing the local database.


--------------------------------------------------------------------------------------------------------------------


## 5. Command summary

Action | Format, Examples
--------|------------------
**Add** | <code>add <i>n/task_name t/time d/date tg/level</i></code> <br>e.g `add n/CS3243_Assignment t/13:00 d/2021-12-12 tg/Important`
**Delete** | <code>delete <i>task_id</i></code> <br> <code>delete <i>n/task_name (d/date)</i> </code> <br> e.g. <code>delete n/quiz d/2021-10-10 </code>
**Show** | <code><i>show week_number</i></code>
**Edit** | `TODO`
**Find** | <code>find <i>keyword (more_keywords) (d/date)</i></code> <br> e.g. `find Quiz GEQ1000 d/2021-10-10`
**Tag** | `TODO`
**Help** | `help`
**Retrieve past Commands** | `history`
**Clear** | `clear`
**Undo** | `TODO`
