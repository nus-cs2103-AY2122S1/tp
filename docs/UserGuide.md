---
layout: page
title: User Guide
---
## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Introduction

Are you a university student looking to score a holiday internship? Do you feel overwhelmed by all the application details you have to keep track of? Have you missed any application deadlines because there are just too many to remember? Have you ever wished that there were a comprehensive app that could help you track your entire internship application process? If you answered yes to any of these questions, we have just the application for you!
<div align = "center">
    <img alt="logo" src="images/logos/InternSHIP.png">
</div>

InternSHIP is a **desktop app for university students to manage their internship application deadlines.** Here are its main features:
* Tracking all essential internship application details (e.g. submission deadlines, interview date and time, completion status, etc...)
* Viewing specific applications

Now you will never miss any internship application deadlines or interviews again!

InternSHIP has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InternSHIP can get your internship management tasks done faster than traditional GUI apps.

We hope you find InternSHIP to be very useful in your internship hunt!

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Information about this User Guide

This section aims to provide some information on the purpose as well as target audience of our User Guide.

### Purpose

This guide aims to:
* Teach first-time users how to [start using InternSHIP](#quick-start).
* Show users [how to use each command in detail](#features) should they encounter any difficulty using any of the commands in InternSHIP.
* Answer some commonly asked questions in the [FAQ](#faq) section.
* Provide experienced users with [tips](#tips-bulb) on how to further improve their experience using InternSHIP.
* Provide existing users with a comprehensive summary of all the [commands](#command-summary) they can use in InternSHIP and the [prefixes](#prefix-summary) used in these commands.
* Show any interested party (e.g. other developers) the [references](#acknowledgements) used in the creation of InternSHIP.

### Target Audience

As the target audience of InternSHIP is university students, this User Guide is mainly targeted towards the same demographic. This guide is therefore aimed at users who:
* Are familiar with navigating around a webpage using hyperlinks.
* Are comfortable downloading and installing software on their computer.
* Are able to type fast and are comfortable using a CLI. <br>
This User Guide **does not assume that the reader has a technical background**. Therefore, university students of all majors should be able to use this guide. The definitions for certain technical terms can be found in the [glossary](#glossary).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## How to use this User Guide

This section aims to explain how to properly utilise our User Guide.

### How to navigate this document
This document is separated into different sections:
1. [Quick Start](#quick-start): Download, install and start using InternSHIP as a first-time user.
2. [Features](#features): Find detailed information about how to use each command in InternSHIP.
3. [FAQ](#faq): Find answers to some commonly-asked questions.
4. [Tips](#tips-bulb): View tips on how to enhance your experience with InternSHIP.
5. [Command Summary](#command-summary): View a summary list of all the commands supported by InternSHIP.
6. [Prefix Summary](#prefix-summary): View a summary list of all the prefixes for the different fields used in InternSHIP commands.
7. [Glossary](#glossary): Find the definitions of terms used in this User Guide.
8. [Acknowledgements](#acknowledgements): Find the references used in the creation of InternSHIP.
For a more detailed overview, refer to this [Table of Contents](#table-of-contents) at the start of our User Guide.

### Special formats

Below are some special formats used throughout this User Guide:

**Information boxes**

<div markdown="span" class="alert alert-info"> :information_source: **Info:** Information boxes provide extra information that might be useful!
</div>

**Tips**

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** Tips are pointers that can help to improve your experience using InternSHIP!
</div>

**Warnings**

<div markdown="span" class="alert alert-danger"> :warning: **Warning:** Warnings are **very important** messages to take note of to avoid any unintended consequences of a command!!
</div>

**Highlights**

`Highlights are used to emphasise special text such as parameters, field values, commands, file names or any user inputs.`

<div style="page-break-after: always;"></div>

### Navigating the GUI

Different sections of the application window have been annotated in the image shown below:

![Anotated UI](images/AnnotatedUi.png)

The UI has been designed and optimized to be navigated via the Command Line Interface (CLI) although some buttons are clickable.

Users can enter commands into the command box and press `ENTER` to execute them. The results will be displayed on the result box, showing appropriate messages. Depending on the command entered, the main panel may be updated accordingly.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To scroll through the list of applications, users can do so by simply scrolling up and down or clicking on an entry and pressing the arrow keys `UP` and `DOWN`
</div>

### Command format

All the commands in the [Features](#features) section adhere to the following rules:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. if the User Guide states `add COMPANY_NAME`, users can input `add Shopee`, or any other company name.

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** Go to our [glossary](#glossary) and [parameters](#parameters) sections to find out more about parameters!
</div>

* Items in square brackets are optional. <br>
  e.g `c/COMPANY_NAME [r/REQUIREMENT]` can be used as `c/Shopee r/CV` or as `c/Shopee`.

* Items with `…​` after them can be used multiple times including zero times. <br>
  e.g. `[r/REQUIREMENT]…​` can be used as `r/` (0 time), `r/CV` (1 time), `r/CV r/Resume` (2 times) etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/COMPANY_NAME p/INTERNSHIP_POSITION`, `p/INTERNSHIP_POSITION c/COMPANY_NAME`
  is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `c/Grab c/Shopee`, only `c/Shopee` will be taken.

* For parameters that can be specified any number of times(`APPLICATION_REQUIREMENTS` and `INTERVIEW_DATE_AND_TIME`), if the same values are specified multiple times, only one instance will be taken.<br>
  e.g. if you specify `r/CV r/CV r/CV`, only `r/CV` will be taken.

* For commands that do not require any parameters (such as `list` and `clear`), any parameters input by the user will be ignored.<br>
  e.g. `list 123` will be interpreted as just `list`.

### Parameters

Here is a summary of all the parameters used in InternSHIP commands:

Parameter | Refers to | Required format | Application field? | Notes
--------|-------|-------|----|---
**APPLICATION_DEADLINE** | The deadline for the application submission. | It should be a valid date in `YYYY-MM-DD` format. | Yes
**APPLICATION_PRIORITY** | The user-set priority of the application. | It can only take 3 values: high, medium or low. | Yes
**APPLICATION_REQUIREMENTS** | The required deliverables for the application submission. | It can take any value. | Yes | e.g. CV, portfolio...
**APPLICATION_OUTCOME** | The outcome of the application (also called application status). This is the company's decision on whether the candidate has been accepted or rejected. | It can only take 3 values: Pending, Accepted or Rejected. | Yes | This is **up to the company, not the user**.
**COMPANY_NAME** | The name of the company the application was made to. | It should only contain [alphanumeric](#glossary) characters and spaces, and should not be blank. | Yes
**COMPLETION_STATUS** | Whether or not the application has been completed and submitted by the user. | It can only take 2 values: completed or uncompleted. | Yes | Unlike the APPLICATION_OUTCOME, this is **up to the user, not the company**.
**DAYS** | The number of days from today. | It must be a **non-negative integer** (e.g. 0, 1, 2, ...) and cannot be more than the maximum integer value (i.e 2147483647). Otherwise, it will be considered invalid. | No
**FIELD** | Any one of the application fields. | It should be in its prefix form, as shown in the [prefix summary](#prefix-summary). | No
**INDEX** | The index number of the application as shown in the **currently displayed application list**. |It must be a **positive integer** (e.g. 1, 2, 3, ...) and cannot be more than the maximum integer value (i.e 2147483647). Otherwise, it will be considered invalid. | No
**INTERNSHIP_POSITION** | The job applied for in the application. | It should only contain alphanumeric characters and spaces, and should not be blank. | Yes
**INTERVIEW_DATE_AND_TIME** | The date and time of the interview(s) required for an application. | It should be a valid date and time in `YYYY-MM-DD HHmm` format. The date and time are separated by a space, and the time is represented in a 24-hour system. | Yes

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `InternSHIP.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your InternSHIP app.

4. Double-click the file to start the app. 
   <div markdown="span" class="alert alert-primary"> :bulb: **Tip:** If double-clicking does not work for you, try to open the app via terminal. To do so, open the terminal, key in `java -jar InternSHIP.jar` and press `Enter`.</div>

5. A GUI similar to the one shown below should appear in a few seconds. 
   Note how the app contains some sample data.<br>
   <br>
   ![Ui](images/Ui.png)

    <div markdown="span" class="alert alert-primary"> :bulb: **Tip:** Use the [`clear`](#clearing-all-entries--clear) command to erase all the sample data when you no longer need them. </div>

6. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:
    
    * **`help`** : Opens up the `Help` Window.
    * **`list`** : Lists all the entries in the internship list.

    * **`add c/shopee p/software engineer d/2021-12-12`**: 
   Adds a company `shopee` with the position `software engineer` at deadline `2021-12-12` to the application list.

    * **`delete 3`** : Deletes the 3rd entry in the application list.

    * **`clear`** : Deletes all the application entries.

    * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Features

This section shows all of InternSHIP's supported commands and how they should be used.

The commands are split in terms of InternSHIP's main features:
* [Tracking application details](#tracking-application-details)
* [Viewing specific applications](#viewing-specific-applications)
* [Others](#others)

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** To make the best use of this section, make sure to familiarise yourself with the [command format rules](#command-format) and [parameters](#parameters) used!
</div>

### Tracking application details

#### Adding an application : `add`

Adds a new entry to the application list in InternSHIP. <br>

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
Minimally, the following fields are required when a new entry is added: <br>
- `COMPANY_NAME` <br>
- `INTERNSHIP_POSITION` <br>
- `APPLICATION_DEADLINE` <br>
<br>
There are three fields that receive their default values. <br>
These fields can be changed later using the `edit`, `complete` or `accept/reject` commands respectively. <br>
- `APPLICATION_PRIORITY`: set as `Medium` <br>
- `COMPLETION_STATUS`  set as `Uncompleted` <br>
- `APPLICATION_OUTCOME` (i.e., application status) set as `Pending` <br>
<br>
Additionally, `APPLICATION_REQUIREMENTS`, and `INTERVIEW_DATE_AND_TIME` are optional fields that can either be specified or not. <br>
If not specified at this stage, it can still be added and edited later using the `edit` command.<br>
<br>
Adding an application entry with the same `COMPANY_NAME` and `INTERNSHIP_POSITION`  as an existing entry is not allowed. 
</div>

Format:
`add c/COMPANY_NAME p/INTERNSHIP_POSITION d/APPLICATION_DEADLINE [r/APPLICATION_REQUIREMENTS]…​ [i/INTERVIEW_DATE_AND_TIME]…​`

* Adds a new application with the specified field values to the list.

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** You can input more than one `INTERVIEW_DATE_AND_TIME` since many companies have multiple rounds of interviews for a single application!
</div>

Examples:
* `add c/Shopee p/software engineer d/2021-12-12` adds a new internship application to `Shopee` as a `software engineer`, with the application deadline `12 December 2021`.
* `add c/Shopee p/software engineer d/2021-12-12 r/resume` adds a new internship application with the same details as the previous example, but with an additional application requirement of a `resume`.
* `add c/Shopee p/software engineer d/2021-12-12 r/resume i/2021-12-18 1030` adds a new internship application with the same details as the previous example, but with an interview on `18 Dec 2021 10:30`.

![addBefore](images/features/addBefore.png)
> Before the command `add c/Shopee p/software engineer d/2021-12-12`
 
![addAfter](images/features/addAfter.png)
> After the command `add c/Shopee p/software engineer d/2021-12-12`
<hr>

#### Editing the details of an existing entry : `edit`

Edits the fields of a specified existing entry in the application list.

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
The fields that can be edited using this command are: <br>
- `COMPANY_NAME` <br>
- `INTERNSHIP_POSITION` <br>
- `APPLICATION_DEADLINE` <br>
- `APPLICATION_PRIORITY` <br>
- `APPLICATION_REQUIREMENTS` <br>
- `INTERVIEW_DATE_AND_TIME` <br>
Editing any field is optional, but least one field must be edited each time. <br>
Multiple fields can be edited at the same time.<br>
<br>
For the edited fields, existing values will be overwritten and updated to the new input values. The values for the rest of the fields will remain the same.<br>
<br>
Editing an application entry such that the `COMPANY_NAME` and `INTERNSHIP_POSITION` become the same as an existing entry is not allowed. 
</div>

<div markdown="span" class="alert alert-danger"> :warning: **Warning:** For the optional fields `INTERVIEW_DATE_AND_TIME` and `APPLICATION_REQUIREMENTS`, editing them overrides all their existing values! <br>
To add on to the existing values or edit an existing value, all the existing values need to be copied over (e.g. to add "CV" as a requirement to an entry that already has "resume" as a requirement, use `edit INDEX r/resume r/CV` instead of just `edit INDEX r/CV`, or else "resume" will disappear).
</div>

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** <br>
For the optional fields `APPLICATION_REQUIREMENTS` and `INTERVIEW_DATE_AND_TIME`, you can remove all the requirements or interview date and times by using `edit r/` or `edit i/` respectively without specifying anything after the prefix.
</div>

Format: `edit INDEX [c/COMPANY_NAME] [p/INTERNSHIP_POSITION] [d/APPLICATION_DEADLINE] [pr/APPLICATION_PRIORITY] [r/APPLICATION_REQUIREMENTS]…​ [i/INTERVIEW_DATE_AND_TIME]…​`

* Edits the application at the specified `INDEX`.

Examples:
* `edit 1 c/Grab d/2021-12-20` changes the company name and deadline of the 1st application to `Grab` and `2021-12-20` respectively.
* `edit 2 p/UI designer` changes the internship position of the 2nd application to `UI designer`.

![editBefore](images/features/editBefore.png)
> Before the command `edit 1 c/Grab d/2021-12-20`

![editAfter](images/features/editAfter.png)
> After the command `edit 1 c/Grab d/2021-12-20`
<hr>

#### Completing an application : `complete`

Updates the completion status of an application from **'Uncompleted'** to **'Completed'**.

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
The completion status of the application represents **whether the user has completed and submitted the application (and all related requirements)** or not.
</div>

Format: `complete INDEX`

* Marks the entry at the specified `INDEX` as completed.

Examples:
* `complete 3` marks the 3rd entry in the application list as completed.

![completeBefore](images/features/completeBefore.png)
> Before the command `complete 3`

![completeAfter](images/features/completeAfter.png)
> After the command `complete 3`

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
Trying to mark an application as completed when it has already been marked as completed will produce an error message.
</div>

<hr>

#### Updating the application outcome to 'Accepted': `accept`

Updates the application outcome (status) of an application to **'Accepted'**.

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
The application outcome is the **decision/outcome provided by the company** on whether the candidate has been `Accepted` or not for the role. <br>
It is different from the completion status! <br>
<br>
The `accept` command also automatically marks the application entry at the specified index as 'Completed', because it is assumed
that if the user has already received the application outcome, then they must have completed and submitted the application.
</div>

Format: `accept INDEX`

- Marks the entry at the specified `INDEX` as 'Accepted'.

Examples:
* `accept 4` marks the 4th entry in the application list as 'Accepted'.


![acceptBefore](images/features/acceptBefore.png)
> Before the command `accept 4`

![acceptAfter](images/features/acceptAfter.png)
> After the command `accept 4`

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
Trying to mark an application as accepted when it has already been marked as accepted will produce an error message.
</div>

<hr>

#### Updating the application outcome to 'Rejected': `reject`

Updates the application outcome (status) of an application to **'Rejected'**.

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
The application outcome is the **decision/outcome provided by the company** on whether the candidate has been `Rejected` or not for the role. <br>
It is different from the completion status! <br>
<br>
The `reject` command also automatically marks the application entry at the specified index as 'Completed', because it is assumed
that if the user has already received the application outcome, then they must have completed and submitted the application.
</div>

Format: `reject INDEX`

- Marks the entry at the specified `INDEX` as 'Rejected'.

Examples:
* `reject 3` marks the 3rd entry in the application list as 'Rejected'.


![rejectBefore](images/features/rejectBefore.png)
> Before the command `reject 3`

![rejectAfter](images/features/rejectAfter.png)
> After the command `reject 3`

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
Trying to mark an application as rejected when it has already been marked as rejected will produce an error message.
</div>

<hr>

### Viewing specific applications

#### Listing upcoming deadlines : `soon`

Shows all the applications with submission deadlines or interview times that are within a specified number of days from today.

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
The available fields are: <br>
- APPLICATION_DEADLINE `d/`  <br>
- INTERVIEW_DATE_AND_TIME `i/`
</div>

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** For `APPLICATION_DEADLINE`, `soon` will only show applications that are not completed yet!
</div>

Format: `soon FIELD DAYS`

* Find applications that are within `DAYS` days.
* Only one field and one keyword can be provided each time.

Examples:

* `soon d/5` shows applications whose submission deadlines are within 5 days from today's date.


![soonBefore](images/features/soonBefore.png)
> Before the command `soon d/5`

![soonAfter](images/features/soonAfter.png)
> After the command `soon d/5`
 
<hr>

#### Finding entries by keyword : `find`

<div markdown="span" class="alert alert-danger"> :warning: **Warning:** This command will display a filtered list. If you want to go back and view the full list of applications in InternSHIP, use the `list` command (explained below) after this command instead of the `undo` command!
</div>

Finds application(s) from the application list.

<div markdown="span" class="alert alert-info">
:information_source: **Info:** <br>
The fields available to be found are: <br>
- COMPANY_NAME `c/` <br>
- INTERNSHIP_POSITION `p/` <br>
- COMPLETION_STATUS `c1/` <br>
- APPLICATION_OUTCOME (i.e. STATUS) `s/` <br>
- APPLICATION_PRIORITY `pr/` <br>
- APPLICATION_REQUIREMENTS `r/` <br>
</div>

Format: `find FIELD keyword`

* Finds all application(s) from the application list that match the keyword in the given field.
* Format of `keyword` should follow the required format of the `FIELD` as specified in the [Parameter Summary](#parameters).
* Only one field and keyword(s) can be provided each time. 
* For completion status, application outcome and application priority, exactly one keyword can be provided each time.

<div style="page-break-after: always;"></div>

Examples:
* `find c/Grab` shows all the applications whose company name is Grab.
* `find p/software engineer` shows all the applications whose position is software engineer.
* `find c1/Completed` shows all the applications that are completed.
* `find s/Accepted` shows all the applications whose application outcome is accepted.  
* `find pr/High` shows all the applications whose priority is high.
* `find r/cv` shows all the applications that include cv as a requirement.


![findBefore](images/features/findBefore.png)
> Before the command `find pr/High`

![findAfter](images/features/findAfter.png)
> After the command `find pr/High`

<hr>

#### Listing all entries : `list`

Shows a list of all the entries in the application list in InternSHIP.

Format: `list`

![listBefore](images/features/listBefore.png)
> Before the command `list` (assume the previous command results in only partial list being displayed)

![listAfter](images/features/listAfter.png)
> After the command `list` (full application list is displayed)
> 
<hr>

#### Sorting the entries : `sort`

<div markdown="span" class="alert alert-danger"> :warning: **Warning:** The `undo` command cannot undo list sorting!
</div>

Sorts all entries in the **currently displayed** application list by a specified field.

<div markdown="span" class="alert alert-info"> 
:information_source: **Info:** <br>
The fields available to sort by are: <br>
- COMPANY_NAME `c/` (ascending alphabetical order) <br>
- INTERNSHIP_POSITION `p/` (ascending alphabetical order) <br>
- APPLICATION_DEADLINE `d/` (sooner to later deadline) <br>
- INTERVIEW_DATE_AND_TIME `i/` (sooner to later interview) <br>
- APPLICATION_PRIORITY `pr/` (higher to lower priority)
</div>

Format: `sort FIELD`

* Sorts the application list by `FIELD`.

Examples:
* `sort c/` shows all the applications saved in the current InternSHIP sorted by company name, in alphabetical order.

![sortBefore](images/features/sortBefore.png)
> Before the command `sort c/`

![sortAfter](images/features/sortAfter.png)
> After the command `sort c/`
> 
<hr>

<div style="page-break-after: always;"></div>

### Others

#### Deleting an entry : `delete`

Deletes the specified entry from the application list.

Format: `delete INDEX`

* Deletes the entry at the specified `INDEX`.

Examples:
* `delete 2` deletes the 2nd entry in the main application list.

![deleteBefore](images/features/deleteBefore.png)
> Before the command `delete 2`

![deleteAfter](images/features/deleteAfter.png)
> After the command `delete 2`

<hr>

#### Clearing all entries : `clear`

Clears all entries from InternSHIP.

Format: `clear`

![clearBefore](images/features/clearBefore.png)
> Before the command `clear`

![clearAfter](images/features/clearAfter.png)
> After the command `clear`

<hr>

#### Undoing a change: `undo`

Undoes a change made to the application list. 

<div markdown="span" class="alert alert-danger"> :warning: **Warning:** This command is to be used after a command that actually **makes some change** to the application list. Inappropriate use may result in unexpected behaviors. </div>

<div markdown="span" class="alert alert-info"> :information_source: **Info:** <br>
Commands that can be undone: <br>
- `add` <br>
- `edit` <br>
- `complete` <br>
- `accept` <br>
- `reject` <br>
- `delete` <br>
- `clear` <br>
Commands that cannot be undone and thus should not be followed by `undo`: <br>
- `soon` <br>
- `find` <br>
- `list`<br>
- `sort` <br>
</div>


<div markdown="span" class="alert alert-danger"> :warning: **Warning:** When successive changes are made, the `undo` command will undo the **most recent** change.

Successive `undo` commands will undo the previous changes from the most recent to the oldest, until there are no more changes that can be undone.
</div>


Format: `undo`

Examples:
* `delete 1` followed by `undo` undoes the deletion of the first entry. As a result, that entry will reappear in the application list.
* `edit 1 p/tester` followed  by `undo` undoes the change made to the first entry's position field. 
As a result, the `position` field of that entry will be restored to its previous value.

![undoBefore](images/features/undoBefore.png)
> Before the command `undo` (application at index 1 is deleted)

![undoAfter](images/features/undoAfter.png)
> After the command `undo` (application at index 1 is added back to the list)
<hr>

#### Redoing a change : `redo`

Redoes a change made to the application list.


<div markdown="span" class="alert alert-danger"> :warning: **Warning:** This command is to be used **directly after** the `undo` command.<br>
If `undo` undoes a change (e.g., `delete 1` followed by `undo`), 
and is then followed by a command that makes some new change to the application list (e.g., `edit 2 pr/High`), then `redo` can no longer redo the undone change (i.e. `delete 1` cannot be redone).
</div>


<div markdown="span" class="alert alert-danger"> :warning: **Warning:** If `undo` is used multiple times, `redo` will redo the **most recent** undone change. <br>
Successive `redo` commands will redo the undone changes from the most recent to the oldest, until there are no more undone changes to be redone.
</div>

Format: `redo`

Examples:
* `delete 1` followed by `undo` and then `redo` redoes the deletion of the first entry after it is restored by `undo`. As a result, that entry will no longer be in the application list.
* `edit 1 p/tester` followed by `undo` and then `redo` redoes the change made to the first entry's position field after it is undone by `undo`.
  As a result, the value of the `position` field of that entry will be changed to "tester".

![redoBefore](images/features/redoBefore.png)
> Before the command `redo` (deletion of the first application was undone)

![redoAfter](images/features/redoAfter.png)
> After the command `redo` (the first application was deleted once again)
 
<hr>

#### Exiting the program : `exit`

Exits the InternSHIP app.

Format: `exit`

<hr>

#### Viewing help : `help`

Shows a message directing the user to the [Command Summary](#command-summary) section of InternSHIP's User Guide.

![help message](images/features/helpMessage.png)

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates 
with the file that contains the data of your previous InterSHIP app home folder.

**Q**: How can I save my data in the app?<br>
**A**: InternSHIP data is saved in the hard disk automatically after any command changes the data. 
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Tips :bulb:

* Commands that change the displayed list in InternSHIP (e.g. `find`, `sort`) can be used in conjunction with commands that take in `INDEX` as a parameter (e.g. `delete`, `complete`) so that you can work faster!
  * e.g. `find c/Grab` followed by `complete 1` can help you quickly find the application you made to Grab and mark it as completed, instead of slowly searching through your entire list of applications!
<br>
* Commands that change the displayed list in InternsHIP (e.g. `find`, `sort`) can also be used in conjunction with each other!
  * e.g. `find c/Grab` followed by `sort pr/` shows all the applications you made to Grab, sorted from higher to lower priority!


--------------------------------------------------------------------------------------------------------------------

## Command summary

This section gives an overview of all the commands supported by InternSHIP.

### Tracking application details

Action | Format | Example | Notes
--------|-------|----|----
**Add** | `add c/COMPANY_NAME p/INTERNSHIP_POSITION d/APPLICATION_DEADLINE [r/APPLICATION_REQUIREMENTS]…​ [i/INTERVIEW_DATE_AND_TIME]…​` | `add c/Shopee p/software engineer d/2021-12-12` |
**Edit** | `edit INDEX [c/COMPANY_NAME] [p/INTERNSHIP_POSITION] [d/APPLICATION_DEADLINE] [pr/APPLICATION_PRIORITY] [r/APPLICATION_REQUIREMENTS]…​ [i/INTERVIEW_DATE_AND_TIME]…​` | `edit 1 c/Grab d/2021-12-20`
**Complete** | `complete INDEX` | `complete 1` |
**Accept** | `accept INDEX` | `accept 2` | 
**Reject** | `reject INDEX` | `reject 2` | 


### Viewing specific applications

Action | Format | Example | Notes
--------|-------|----|----
**Find** | `find PREFIX KEYWORD` |  `find pr/High` | `PREFIX` refers to the field to match the keyword to (`c/` COMPANY_NAME, `p/` INTERNSHIP_POSITION, `c1/` COMPLETION_STATUS, `s/` APPLICATION_OUTCOME (i.e. STATUS), `pr/` APPLICATION_PRIORITY, `r/` APPLICATION_REQUIREMENTS)
**Soon** | `soon PREFIX DAYS`| `soon d/5` | `PREFIX` refers to the field with coming deadline or interview (`d/` APPLICATION_DEADLINE, `i/` INTERVIEW_DATE_AND_TIME)
**List** | `list`| `list` | No parameter required.
**Sort** | `sort PREFIX` | `sort c/` | `PREFIX` refers to the application detail to be sorted by (`c/` COMPANY_NAME, `p/` INTERNSHIP_POSITION, `d/` APPLICATION_DEADLINE, `pr/` APPLICATION_PRIORITY, `i/` INTERVIEW_DATE_AND_TIME)


### Others

Action | Format | Example | Notes
--------|-------|----|----
**Delete** | `delete INDEX` | `delete 3` |
**Clear** | `clear` | `clear` | No parameter required.
**Undo** | `undo`  | `undo` | No parameter required.
**Redo** | `redo` | `redo` | No parameter required.
**Exit** | `exit` | `exit`  | No parameter required.
**Help** | `help` | `help` | No parameter required.

--------------------------------------------------------------------------------------------------------------------

## Prefix summary

This section provides a summary of all the fields and their corresponding prefixes as used in InternSHIP commands.

Field | Prefix | Used in
--------|-------|----
APPLICATION_DEADLINE | **d/** | `add`, `edit`, `sort`, `soon`
APPLICATION_PRIORITY | **pr/** | `edit`, `find`, `sort`
APPLICATION_REQUIREMENTS | **r/** | `add`, `edit`
APPLICATION_OUTCOME (i.e. STATUS) | **s/** | `find`
COMPANY_NAME | **c/** | `add`, `edit`, `find`, `sort`
COMPLETION_STATUS | **c1/** | `find`
INTERNSHIP_POSITION | **p/** | `add`, `edit`, `find`, `sort`
INTERVIEW_DATE_AND_TIME | **i/** | `add`, `edit`, `sort`, `soon`

<div markdown="span" class="alert alert-danger"> :warning: **Warning:** Please make sure you enter the prefix correctly! <br> 
The list above is a complete list of **all** the prefixes used in InternSHIP commands. Any other prefixes are considered **invalid**, and may be interpreted incorrectly or lead to unexpected error messages if used.
</div>

--------------------------------------------------------------------------------------------------------------------

## Glossary

This section explains some terms used in this User Guide.

Term | Definition | Notes
--------|-------|----
**Command Line Interface (CLI)** | Allows users to type in their commands to use an application instead of clicking on buttons on the screen.
**Graphical User Interface (GUI)** | The display of an application with text formatting, background colours, etc... An application without any GUI would only show plain text to the user.
**Java** | A software that allows certain applications (like InternSHIP) to run on the computer. | You will not be able to run InternSHIP on your computer without Java!
**application** | The application for an internship at a specific company.
**field** | The detail of an application. | e.g. company name, internship position, etc...
**command** | The instruction typed by the user for InternSHIP to execute.
**parameter** | A part of the command that consists of information input by the user. | In the context of InternSHIP, this information refers to the actual data of the application fields.
**prefix** | An abbreviation for the application field. For commands like `add` or `edit`, the prefix should be typed in right before the parameter for that field. | Always ends with a slash (/).
**alphanumeric** | A character that is either a letter or a number.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Acknowledgements

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* The formatting and content of this User Guide is referenced from [AY2021S2-CS2103T-T11-2/tp](https://github.com/AY2021S2-CS2103T-T11-2/tp/blob/master/docs/UserGuide.md)
* The ship in the InternSHIP icon was obtained from [flaticon.com](https://www.flaticon.com/free-icon/ship_4012401?term=ship&page=1&position=66&page=1&position=66&related_id=4012401&origin=search)
* [Canva](https://www.canva.com) was used to make the InternSHIP icon.
* Libraries used:
    - [JavaFX](https://openjfx.io/)
    - [Jackson](https://github.com/FasterXML/jackson)
    - [JUnit5](https://github.com/junit-team/junit5)
    - [TestFX](https://github.com/TestFX/TestFX)
