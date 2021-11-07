---
layout: page
title: User Guide
---

![Logo](images/ContactSh_Ug_logo.png)

As a student entrepreneur with a busy schedule, do you always find yourself missing an appointment/meeting with
your clients or friends? Or can't seem to find a way to easily recall details of a certain someone
who is probably stored as one of your many contacts in your Excel sheet? Fret not,
as we have designed an application called **ContactSH** just for you! It is a Desktop application for managing your contacts
as well as tasks related to each of them. It boasts a highly effective search function and contact-task tracking system.
It is also optimized for use via a Command Line Interface(CLI) that is similar in style as Linux CLIs,
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, you will be able to manage
business tasks related to your contacts more efficiently than using other traditional GUI apps.

The [Quick Start](#quick-start) section shows you how to quickly get ContactSH up and running on your computer.
If you are a new user, it is recommended to go through the instructions under [Features](#features) one by one.
If you are an experienced user, and just want to refer to the list of features, you may use the [Table of contents](#table-of-contents)
to quickly navigate to the command of your choice.

---

## Table of Contents

[Quick Start](#quick-start) <br>
[Features](#features)
* [Viewing details of instructions : `man`](#viewing-details-of-instructions-man)
* [Adding a person : `add`](#adding-a-person-add)
* [Editing a person : `edit`](#editing-a-person-edit)
* [Deleting a person : `rm`](#deleting-a-person-rm)
* [Listing all persons : `ls`](#listing-all-persons-ls)
* [Sorting persons by name: `sort`](#sorting-persons-by-name-sort)
* [Locating persons by name : `find`](#locating-persons-by-name-find)
* [Adding a task : `add`](#adding-a-task-add)
* [Editing a task : `edit`](#editing-a-task-edit)
* [Deleting a task : `rm`](#deleting-a-task-rm)
* [Viewing tasks : `cat`](#viewing-tasks-cat)
* [Marking a task as done : `donetask`](#marking-a-task-as-done-donetask)
* [Marking a task as not done : `undotask`](#marking-a-task-as-not-done-undotask)
* [Setting the number of days before tasks are considered due soon : `reminder `](#setting-the-number-of-days-before-tasks-are-considered-due-soon-reminder)
* [Clearing the screen : `clear`](#clearing-the-screen-clear)
* [Accessing the cache](#accessing-the-cache)
* [Saving the data](#saving-the-data)
* [Editing the data file](#editing-the-data-file)
* [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)

[FAQ](#faq)<br>
[Command Summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ContactSH.jar` from [here](https://github.com/AY2122S1-CS2103T-W10-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ContactSH.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`man`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`ls`** : Lists all contacts.
   * **`add -nJohn Doe -p98765432 -ejohnd@example.com -aJohn street, block 123, #01-01`** : Adds a contact named `John Doe` to ContactSH.
   * **`rm 3`** : Deletes the 3rd contact shown in the current list.
   * **`rm -A`** : Deletes all contacts.


1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter to be specified: `add -n John Doe`.

* Items in square brackets are optional parameters.<br>
  e.g `-n NAME [-l LABEL]` can be specified as `-n John Doe -l friend` or `-n John Doe`.

* Items with `…`​ after them can be specified multiple times including zero times.<br>
  e.g. `[-l LABEL]…​` can be specified as `` (i.e. 0 times), `-l friend`, `-l friend -l family` etc.

* Parameters can be in any order.<br>
  e.g. if the command format is `-n NAME -p PHONE_NUMBER`, `-p PHONE_NUMBER -n NAME` is also acceptable.

* If a parameter is expected only once in the command format but you specified it multiple times, only the last occurrence of the parameter will be accepted.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be accepted.<br> There are a few exceptions to this such as the find command. In such cases, if a parameter is expected only once, an error message will appear.

* Extraneous parameters for commands that do not take in parameters (such as `ls`, `exit` and `clear`) will be ignored.<br>
  e.g. if you specifiy `ls 123`, the command will be interpreted as `ls`.

</div>

### Viewing details of instructions: `man`

Shows you details of instructions that can be used to navigate the app.

Format: `man [COMMAND_NAME]`

* If no `[COMMAND_NAME]` is entered, you will see a table of instructions, in a new window, that can be used to navigate the app.

* If you enter a valid `[COMMAND_NAME]`, you will see the details of that command, which includes the format and taskName of the command.

Examples:
* `man sort` Shows the full details of `sort` command as below.
![result for `man sort`](images/mansortResult.png)

### Adding a person: `add`

Adds a person to ContactSH.

Format: `add -nNAME -pPHONE_NUMBER -eEMAIL -aADDRESS [-dDescription] [-lLABEL]... [-imptIMPORTANCE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of labels (including 0)
</div>

Notes:

* Person in ContactSH are considered the same person if they have the same name. Duplicate person are not allowed in ContactSH.

* `IMPORTANCE` is either "true" or "false" (Case insensitive)

Examples:

* `add -nJohn Doe -p98765432 -ejohnd@example.com -aJohn street, block 123, #01-01` Adds a person with name `John Doe`, phone number `98765432`, email `johnd@example.com`, address `John street, block 123, #01-01`.
* `add -nBetsy Crowe -lfriend -ebetsycrowe@example.com -aNewgate Prison -p1234567 -dBald -lcriminal` Adds a person with name `Betsy Crowe`, phone number `1234567`, email `betsycrowe@example.com`, address `Newgate Prison`, label `criminal` and `friend`, description `Bald`.

### Editing a person: `edit`

Edits an existing person in ContactSH.

Format: `edit INDEX [-nNAME] [-pPHONE] [-eEMAIL] [-aADDRESS] [-dDESCRIPTION] [-lLABEL]… [-imptIMPORTANCE]`

* Edits the person at the specified `INDEX`. `INDEX` refers to the index number shown in the displayed person list. `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing labels, the existing labels of the person will be removed (i.e editing labels overwrites previous labels).
* You can remove all the person’s labels by typing `-l` without
    specifying any labels after it.
* `IMPORTANCE` is either "true" or "false" (Case insensitive)

Examples:

* `edit 1 -p91234567 -ejohndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 -nBetsy Crower -l` Edits the name of the 2nd person to be `Betsy Crower` and removes all their existing labels.

### Deleting a person: `rm`

1. Deletes a specified person from ContactSH.

   Format: `rm INDEX`

   * Deletes the person at the specified `INDEX`.
   * `INDEX` refers to the index number shown in the persons list displayed.
   * `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647

   Examples:

   * `ls` followed by `rm 2` deletes the 2nd person listed in ContactSH.
   * `find -n Betsy` followed by `rm 1` deletes the 1st person in the results of the `find` command.


2. Deletes all persons from ContactSH.

   Format: `rm -A`

### Listing all persons: `ls`

Shows a list of all persons in ContactSH.

Format: `ls`

### Sorting persons by name: `sort`

Sorts persons by the alphabetical order of their name.

Format: `sort [-r]`

* The default sort with no options provided displays a list of persons sorted in ascending ASCII alphabetical order of their name.
* If the optional `-r` flag is provided, a list of persons sorted in reverse order is displayed.

### Locating persons: `find`

Finds persons whose attribute contains any of the given keywords.

Format: `find [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-d DESCRIPTION] [-l LABEL] [-tn TASK_NAME]`

* A space is **necessary** between a command word, a flag and a keyword.
* Only 1 attribute can be specified per find command.
* The search is case-insensitive. e.g. hans will match Hans
* The order of the keywords matters. e.g. Hans Bo will not match Bo Hans
* Only the specified attribute is searched.
* At least one of the optional fields is required.
* Abbreviations will be matched e.g. Han will match Hans
* Persons matching **ALL** the keywords will be returned. e.g. A Y will return Alex Yeoh, Alexandra Yee, but not Aileen

Examples:

* `find -n Alex` Finds all people whose name matches the keyword "Alex".

![result for `find -n Alex`](images/findAlexResult.png)
<div markdown="span" class="alert alert-primary">
:memo:**Note**: The keywords will only be matched against the start of any word. If any of the keywords are not the 
start of any word in a person's name or if the keywords do not appear in the same order in the persons' name as they do 
in the given input, it will not match that particular task. For instance, `find -n alex` will match a person with 
the name "Alex Yeoh". However, `find -n lex` and `find -n yeo alex` will not match the same person with the name 
"Alex Yeoh".
</div>

### Adding a task: `add`

Adds a task to the current list of tasks attached to a person.

Format: `add INDEX -tnTASK_NAME [-tdTASK_DATE] [-ttTASK_TIME] [-taTASK_ADDRESS]`

* Adds a task to the person at the specified `INDEX`. `INDEX` refers to the index number shown in the persons list displayed. `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* `TASK_DATE` should follow the format of `YYYY-MM-DD`.
* `TASK_TIME` should follow the format of `HH:MM`.

Examples:
* `add 1 -tncall for meeting -td2021-12-03 -tt14:30` Adds the task `call for meeting` with date `2021-12-03` and time `14:30` to the task list of the 1st person listed.
* `add 3 -tnEvening Run -td2022-01-05 -tt18:00 -taClementi Park` Adds the task `Evening Run` with date `2022-01-05`, time `18:00` and address `Clementi Park`.

### Editing a task: `edit`

Edits an existing task in ContactSH.

Format: `edit INDEX -tiTASK_INDEX [-tnTASK_NAME] [-tdTASK_DATE] [-ttTASK_TIME] [-taTASK_ADDRESS]…​`

* Edits a task attached to the person at the specified `INDEX`. `INDEX` refers to the index number shown in the persons list displayed. `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* The tasks edited  is specified by the `TASK_INDEX`. `TASK_INDEX` refers to the index number shown in the task list displayed. `TASK_INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* Multiple tasks of one person can be edited in one command. All fields provided after a `TASK_INDEX` are taken to be edited fields for the task at the `TASK_INDEX`.
* Existing values will be updated to the input value.
* For each `TASK_INDEX` provided, at least one of the optional fields must be provided.
* `TASK_DATE` should follow the format of `YYYY-MM-DD`.
* `TASK_TIME` should follow the format of `HH:MM`.

Examples:

* `edit 3 -ti2 -tnGroup Project Meeting` Changes the name of the 2nd task attached to the 3rd person in the list to `Group Project Meeting`.
* `edit 2 -ti3 -tnPresentation Meeting -taZoom -ti5 -td2021-12-20` Changes the 3rd and 5th task of the 2nd person in the list. Name and address of the 3rd task is changed to`Presentation Meeting` and `Zoom` respectively. Date of the 5th task is changed to `2021-12-20`.

### Deleting a task: `rm`

Deletes a task attached to a specified person.

Format: `rm INDEX -tiTASK_INDEX…​`

* Deletes a task attached to the person at the specified `INDEX`. `INDEX` refers to the index number shown in the persons list displayed. `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* The task deleted is specified by `TASK_INDEX`. `TASK_INDEX` refers to the index number shown in the task list displayed. `TASK_INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647.
If the same `TASK_INDEX` is specified more than once, the task will only be deleted once.
* Multiple tasks of one person can be deleted in one command.

Examples:

* `rm 2 -ti2` Deletes the 2nd task attached to the 2nd person listed.
* `rm 1 -ti2 -ti3` Deletes the 2nd and 3rd task attached to the 1st person listed.

### Viewing tasks: `cat`

Displays a list of tasks that has been attached to a specific person.

Format: `cat INDEX`

### Viewing list of tasks of a person: `cat`

Shows you a list of tasks that has been attached to a specific person.

Format: `cat INDEX [-f KEYWORDS]`

1. Displays the list of tasks attached to the person at the specified `INDEX`.
   * `INDEX` refers to the index number shown in the displayed person list.
   * `INDEX` **must be a positive integer** 1, 2, 3, ...

   Examples:

   * `cat 1` Displays the list of tasks attached to the 1st person.
   ![result for `cat 1`](images/catAlexResult.png)

2. Displays a filtered list of tasks that has been attached to a specific person.

   Format: `cat INDEX -f KEYWORDS…`

   * Displays a filtered lists of tasks for a specific person according to the keywords provided.
   * Only tasks that matches the keywords will be shown. A task matches the keywords if the task name contains a word that starts with any of the `KEYWORDS`.
   * A space is **necessary** between the flag and the keywords.

   Examples:

   * Alex is the first person in the address book and has the tasks [Work, project meeting at NUS]. `cat 1 -f work` will display the task "work"
   * `cat 1 -f work` Displays the list of tasks attached to the 1st person that match the given keywords(s).
   ![result for `cat 1 -f work`](images/cat-fResult.png)

<div markdown="span" class="alert alert-primary">
:memo:**Note**: The keywords will only be matched against the start of any word. If any of the keywords are not the 
start of any word in a task name or if the keywords do not appear in the same order in the task name as they do in the
given input, it will not match that particular task. For instance, `cat 1 -f work` will match a task with the name 
"Work at 5pm". However, `cat 1 -f ork` and `cat 1 -f 5pm work" will not match the same task with the name "Work at 5pm".
</div>

3. Displays the task list of all persons in ContactSH.

   Format: `cat -A`
   ![result for `cat -A`](images/catAllResult.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    Alternatively, you could also click on the person in the GUI to open said person's tasks!
    </div>

### Marking a task as done: `donetask`

Marks an existing task in ContactSH as done.

Format: `donetask INDEX -tiTASK_INDEX…​`

* Marks tasks attached to the person at the specified `INDEX` as done. `INDEX` refers to the index number shown in the displayed person list. `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* The tasks marked is specified by the `TASK_INDEX`. `TASK_INDEX` refers to the index number displayed in the tasklist of said person. `TASK_INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* Multiple tasks of one person can be marked as done in one command.

Examples:

* `donetask 4 -ti1 -ti5` Marks the 1st and 5th task of the 4th person in the list as done.

### Marking a task as not done: `undotask`

Marks existing tasks in ContactSH as not done.

Format: `undotask INDEX -tiTASK_INDEX…​`

* Marks tasks attached to the person at the specified `INDEX` as not done. `INDEX` refers to the index number shown in the displayed person list. `INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* The tasks marked is specified by the `TASK_INDEX`. `TASK_INDEX` refers to the index number displayed in the tasklist of said person. `TASK_INDEX` **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
* Multiple tasks of one person can be marked as not done in one command.

Examples:
* `undotask 4 -ti1 -ti5` Marks the 1st and 5th task of the 4th person in the list as not done.

### Setting the number of days before tasks are considered due soon: `reminder`

1. Displays the current number of days prior to the date of tasks for them to be considered due soon.

   Format: `reminder`

    * The default value is 3 days.


2. Sets the number of days prior to the date of tasks for them to be considered as due soon. This affects all tasks.

   Format: `reminder -s DAYS`

   * `DAYS` refer to the number of days prior to a task's date.
     The day **must be a positive integer less than or equal to 2147483647:** 1, 2, 3, …​, 2147483647
   * All tasks share the same number of days before it is considered as due soon.

   Example:

   * `reminder -s 10` All tasks that are due within a 10-day period from the current system date will be considered due soon.

<div markdown="span" class="alert alert-primary">
:memo:**Note**: A task will turn orange in colour as a reminder that it is due soon. A task is defined as being
due soon when the number of days between the current system date is less than or equal to {DAYS}, the task is not overdue.
</div>

### Clearing the screen: `clear`

Clears the persons list and task list displayed. Does not delete any persons or tasks.

Format: `clear`

Example:
* `clear` Clears the persons list and task list in the GUI

### Accessing the cache

* ContactSH guarantees it saves up to the last 25 `commands` (both valid and invalid) that you input. You can browse through the previous input using the up and down arrow key on your keyboard when typing in the command box.

* ContactSH may save up to 50 newest `commands`. However, once the cache is full, half (25) of the oldest `command` will be deleted. Hence, the oldest 25 `commands` is not guaranteed to be saved.

* Cache will be emptied once you end the session (the application is closed). Hence, `commands` from previous sessions cannot be retrieved.

### Saving the data

* ContactSH data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

* ContactSH data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ContactSH will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ContactSH home folder.

---

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add -nNAME -pPHONE_NUMBER -eEMAIL -aADDRESS [-dDescription] [-lLABEL]…​` <br> e.g., `add -nJames Ho -p22224444 -ejamesho@example.com -a123, Clementi Rd, 1234665 -dRich -lfriend -lcolleague`
**Clear** | `clear`
**Delete** | `rm INDEX`<br> e.g., `rm 3`
**Edit** | `edit INDEX [-nNAME] [-pPHONE_NUMBER] [-eEMAIL] [-aADDRESS] [-dDescription] [-lLABEL]…​`<br> e.g.,`edit 2 -nJames Lee -ejameslee@example.com`
**Find** | `find [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-d Description] [-l LABEL] [-tn TASK_NAME]`<br> e.g., `find -n Alex`
**List** | `ls`
**Sort** | `sort [-r]`
**Help** | `man`
**Add Task** | `add INDEX -tnTASKNAME` <br> e.g., `add 2 -tnCelebrate $1 million revenue -tnContact Professor to get help`
**Delete Task** | `rm INDEX -tiTASK_INDEX` <br> e.g., `rm 2 -ti2 -ti3`
**Edit Task** | `edit INDEX -tiTASK_INDEX [-tnTASK_NAME] [-tdTASK_DATE] [-ttTASK_TIME] [-taTASK_ADDRESS]…​` <br> e.g., `edit 1 -ti2 -tnInternship Interview -tt15:45 -ti4 -td2022-09-20`
**Mark Task Done** | `donetask INDEX -tiTASK_INDEX…​` <br> e.g., `donetask 3 -ti2 -ti5`
**Undo Mark Task Done** | `undotask INDEX -ti TASK_INDEX…​` <br> e.g., `undotask 3 -ti2 -ti5`
**View Tasks** | `cat INDEX`<br>e.g.,`cat 4`<br><br>`cat -A`
**View Reminder Days** | `reminder`
**Set Reminder Days** | `reminder -s DAYS`<br>e.g., `reminder -s 21`
