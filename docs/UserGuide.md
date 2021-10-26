---
layout: page
title: User Guide
* Table of Contents
  {:toc}
---
## 1. Introduction
![NUS Mod Tracker Icon](images/nus_mod_tracker.png)

NUS Mod Tracker is a **desktop app** designed for **NUS Computer Science (CS) students who are enrolled before the Academic Year 2020/2021** to **create their own academic plan**,
as well as to **keep track of their Modular Credits(MC) and modules taken**.
It is optimized for use via a Command Line Interface (CLI), while still having the benefits of a Graphical User
Interface (GUI).

For more information about our features, you can head over to our [Features](#features) section. Better yet, 
head over to our [Quick Start](#quick-start) and try it out for yourself!

### 1.1 Is this guide for you?

Are you someone who is trying our application for the very first time? If so, our guide is just the thing you need to get started on your journey to create your very own academic plan! 
Just simple read along and we will guide you step by step on how to use our application.

Are you someone who has maybe forgotten how some of our features work? Fret not, this user guide is also for you!
Simply head over to either our [Features](#features) section or our [Command Summary](#command-summary) section to solve your problems.

### 1.2 How to use the user guide 

We strongly recommend first time users of our application to read through the user guide in order to be familiarised with how our application works.
Throughout our user guide, we have included links which will direct you to the corresponding sections of the user guide. We have also added certain useful tips and tricks 
to help you get more out of our application. 
If you ever want to skip through any section of our user guide, do feel free to refer to use the table of contents right below this section to help you with the navigation.

### 1.3 Table of Contents

* [1. Introduction](#1-introduction)
    * [1.1 Is this guide for you?](#11-is-this-guide-for-you)
    * [1.2 How to use the user guide](#12-how-to-use-the-user-guide)
    * [1.3 Table of Contents](#13-table-of-contents)
* [2. Quick Start](#2-quick-start)
* [3. About](#3-about)
    * [3.1 Special Icons](#31-special-icons)
    * [3.2 Application homepage explanation](#32-application-homepage-explanation)
    * [3.3 Command format](#33-command-format)
    * [3.4 Command terminology](#34-command-terminology)
* [4. Features](#4-features)
    * [4.1 Module](#41-module)
        * [4.1.1 Add](#411-adding-a-module-add)
        * [4.1.2 Delete](#412-deleting-a-module--delete)
        * [4.1.3 List](#413-listing-current-modules-list)
        * [4.1.4 Find](#414-finding-a-module-find)
        * [4.1.5 Edit](#415-editing-a-module--edit)
    * [4.2 Academic Calendar](#42-academic-calendar)
      * [4.2.1 Take](#421-taking-a-module--take)
      * [4.2.2 Un-take](#422-removing-the-schedule-from-untake-a-module--untake)
      * [4.2.3 Info](#423-viewing-user-information--info)
      * [4.2.4 Set](#424-setting-users-mc-goal-or-current-semester--set)
      * [4.2.5 View](#425-see-modules-taken-in-a-specific-semester--view)
      * [4.2.6 Clear](#426-remove-the-schedules-for-a-specific-semester-from-modules--clear)
    * [4.3 Miscellaneous](#43-miscellaneous)
      * [4.3.1 Help](#431-viewing-help--help)
* [5. FAQ](#5-faq)
* [6. Command Summary](#6-command-summary)


--------------------------------------------------------------------------------------------------------------------

## 2. Quick start

This section gives you a quick summary on how to download and run our application.

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `modtracker.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-2/tp/releases/tag/v1.3.trial).

1. Copy the file to the folder you want to use as the _home folder_ for your Mod Tracker.

1. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all modules.

   * **`add c/CS2103T t/Software Engineering d/Covers the main areas of software development m/4 tag/core`** : Adds a module named `CS2103T` to the Mod Tracker.

   * **`delete`**`3` : Deletes the 3rd module shown in the current list.


Kindly refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. About

This section helps to familiarise you with how our application works as well as the terminologies we use throughout our application.

### 3.1 Special Icons

Here are some of the icons used in this user guide and what each of them represent.

**Tips**

Tips are useful information that can help you have a better experience with our application.

<div markdown="span" class="alert alert-primary">:bulb:
Tip: Tips are useful!
</div><br>

**Important Information**

Important Information are messages that are crucial for you to know in order to use our application smoothly.

<div markdown="span" class="alert alert-info">:information_source:
Important Information: Remember the importance of this icon!
</div>

### 3.2 Application homepage explanation

Here is how our application's homepage works

![UiExplanation](images/UiExplanation.png)

#### 3.2.1 Help Buttons (pink)
* Buttons have been placed at the top of our application to help you to exit the application and access the help function using your mouse.

#### 3.2.2 Command Box (yellow)
* Location for you to key in your inputs.

#### 3.2.3 Output Display (orange)
* Here is where our application will echo out your inputs entered.
* Results will also be displayed in this section when the appropriate commands have been added.

#### 3.2.4 Module List (purple)
* Here are the modules stored in our module tracker database.
* Do note that we have included some modules for you to test out our [features](#4-features).

#### 3.2.5 MC goal Progress (green)

Tracks and displays the number of Mcs completed in total, and for each course requirement. <br>

![Mcs Display](images/mcsDisplay.png)

The user's progress is displayed in the form of:
* The number of Mcs completed for that category, over the number of required Mcs.
* A progress bar filled according to the level of completion.

**Total Mcs completed display:**

![total Mcs Display](images/totalMcsCompletedDisplay.png)
* Shows the total number of Mcs taken, over the user's set Mc goal.
* The total number of Mcs taken is calculated from all modules taken before the current semester.
    * For example: If the current semester is year 1 semester 2, the total number of Mcs will be calculated
      from all modules taken in year 1 semester 1 only.

**Course requirements display:**

![requirements Mcs Display](images/courseRequirementsDisplay.png)
* Shows the total number of Mcs taken for that requirement, over the number of Mcs needed to satisfy the requirement.
* The total number of Mcs taken is calculated from modules taken before the current semester, which have been tagged with the specific tags.
    * For example: Mcs for modules taken which are tagged with "ge" (`tag/ge`) will be counted into the total number of MCs taken for the GE requirement.
* Modules with the following tags will be used in calculating the Mcs completed for the corresponding requirement:

Requirement | Tag (not case sensitive)
------------|-----------
GE | "ge"
UE | "ue"
Foundation | "foundation"
Breadth and Depth | "breadth and depth"
IT Professionalism | "it professionalism"
Math and Science | "math and science"

Notes:
* If the Mc requirement has been satisfied, the completed Mcs and progress bar will be coloured light blue.
* If the Mc requirement is not satisfied, the completed Mcs and progress bar will be coloured pink.

#### 3.2.6 Current Semester (dark blue)
* Displays the current year and semester that the application is in.

#### 3.2.7 Current MC goal (light blue)
* Displays your current mc goal.

### 3.3 Command format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the inputs to be entered by the user.<br>
  e.g. in `add m/MODULE`, `MODULE` is a parameter which is entered by the user(such as `add m/GEQ1000` or `add m/CS2030S`).

* Items in square brackets are optional.<br>
  e.g. `c/CODE [tag/TAG]` can be used as `c/CS2103T tag/core` or as `c/CS2103T`.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/GEQ1000 m/GEQ1000`, only `m/GEQ1000` will be taken.

* Inputs for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 3.4 Command terminology

<div markdown="span" class="alert alert-primary">:bulb:
Tip: Do take a good look at this section to understand the common terminologies that we have used in our Features Section below!
</div><br>

`module tracker database`
* Refers to all the modules that is currently stored in the application.

`academic plan`
* Refers to modules that the user has assigned with a specific semester and year.
* Contributes to the calculation of the MC requirements.
  ![requirements Mcs Display](images/courseRequirementsDisplay.png)

`INDEX`
* The `INDEX` refers to the index number shown in the displayed module list.
* The `INDEX` **must be a positive integer** (1, 2, 3 ...).
  ![IndexExplanation](images/IndexExplanation.png)
* For example, with the screenshot above, an `INDEX` of 1 would represent the Module CS2030S, and an `INDEX` of 2 would represent the Module CS2040S

`CODE`
* Represents the `module`'s code component.
* `CODE` must follow the NUSMods module code format.

`TITLE`
* Represents the `module`'s title component.

`DESCRIPTION`
* Represents the `module`'s description component.

`MC`
* Stands for Modular Credits
* Represents the `module`'s mc component.
* `MC` must be a **positive integer** (1,2,3...).

`TAG`
* Represents the `module`'s tag component.

`YEAR`
* Represents Academic Year
* The `YEAR` must be a positive integer from 1-6.

`SEM`
* Represents Semester
* The `SEMESTER` must be a positive integer from 1-4.
* Special semesters 1 and 2 are represented by integer values 3 and 4 respectively

--------------------------------------------------------------------------------------------------------------------

## 4. Features

This section gives you a detailed explanation on how each of our features work.

For an easy reference, we have decided to split our features into 3 different sub-categories:
* Module features
* Academic Calendar features
* Miscellaneous features

The following sub-categories will explain the intended purpose for the set of features.

### 4.1 Module

Module features consist of features that allows you to be able to add and remove modules from our database. 
Do note that these features do not directly affect the modules that are currently stored in your academic plan.
(we will explain how such features can **indirectly** affect the modules that are currently stored in your academic plan  in the [FAQ](#5-faq) section) <br>

Such features include adding a new module into the database, removing a module from the database, as well as finding, editing, and listing modules that are in the database.

#### 4.1.1 Adding a module: `add`

Adds a module to the module tracker database.

Format: `FORMAT: add c/CODE t/TITLE d/DESCRIPTION m/MC [tag/TAG]`
* `MC` must be a **positive integer** (1,2,3...).
* `CODE` must follow the NUSMods module code format.

Examples:
* `add c/CS2103T t/Software Engineering d/Covers the main areas of software development m/4 tag/core`
is equivalent to adding a Module with a code of `CS2103T`, a title called `Software Engineering`, a description `Covers the main areas of software development`, consists of `4` MCs and a `core` tag to the database. 
  
#### 4.1.2 Deleting a module : `delete`

Deletes a module from the module tracker database.

Format: `delete INDEX`

* Deletes a module at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed module list.
* The `INDEX` **must be a positive integer** (1, 2, 3 ...).

Example:
* `delete 2` is equivalent to deleting the 2nd module in the module tracker database.

#### 4.1.3 Listing current modules: `list`

Shows a list of all modules that are in the module tracker database.

Format: `list`

#### 4.1.4 Finding a module: `find`

Shows a list of all the modules in the module tracker database that contains the given keyword(s).

Format: `FORMAT: find [c/] [t/] [d/] [m/] [tag/] [y/] [s/] KEYWORDS`
* `KEYWORDS` refers to the words that the application will search the modules by.
* If no optional parameters are entered, the application will search within all the modules'
  components for matching `KEYWORDS`
* If optional parameters are entered, the application will search within the modules'
  specified components for matching `KEYWORDS`

Examples:
* `find CS`is equivalent to displaying any modules that contain the word "CS" in the code, title, description, mc or tag.
* `find c/ t/ CS GE` is equivalent to displaying any modules that contain the words "CS" or "GE" in the code or title.
* `find c/ CS2040S` is equivalent to displaying any modules that contain the word "CS2040S" in the code.
* `find tag/ UE` is equivalent to displaying any modules that contain the word "UE" in the tag.


#### 4.1.5 Editing a module : `edit`

Edits an existing module in the module tracker's database.

Format: `edit INDEX [c/CODE] [t/TITLE] [d/DESCRIPTION] [m/MC] [tag/TAG]`

* At least one of the optional fields must be provided.
* The given value for the field(s) must be **different** from value that it is replacing.
* For values supplied to the `CODE` field, the value must **NOT** be identical with any other module's `CODE` field in the module tracker database .
* The `INDEX` refers to the index number shown in the displayed module list.
* The `INDEX` **must be a positive integer** (1, 2, 3 ...).

Examples:
* `edit 1 c/CS2103T t/Software Engineering` is equivalent to 
  editing the code of the 1st module in the database to be CS2103T, 
  as well as editing the title of the 1st module in the database to be Software Engineering respectively.
* `edit 3 m/2` is equivalent to editing the 3rd module in the database to have a MC of 2.

### 4.2 Academic Calendar

Academic Calendar features consist of features that allows you to be able to create and customize your own academic plan.
Do note that these features are the ones that allows you to update and edit your academic plans
(such as the visual display of which modules you have taken, as well as the progress bar for each of the University's requirements).

Such features include taking/un-taking a module in a specific semester, viewing and setting the current semester or mc goal,
as well as viewing and clearing modules in a specific semester.

#### 4.2.1 Taking a module : `take`

Take a module in the specified semester and adds it to the academic plan.

Format: `take INDEX y/YEAR s/SEMESTER`

* Schedules the module at the specified `INDEX` for the specified `YEAR` and `SEMESTER`.
* If the specified module has already been scheduled, its schedule will be overridden.
* The `INDEX` refers to the index number shown in the displayed module list.
* The `INDEX` **must be a positive integer** (1, 2, 3 ...).
* The `YEAR` must be a positive integer from 1-6.
* The `SEMESTER` must be a positive integer from 1-4.
* Special semesters 1 and 2 are represented by integer values 3 and 4 respectively (see examples below).

Example:
* `take 2 y/2 s/1` is equivalent to scheduling the 2nd module in the module tracker database for year 2 semester 1.
* `take 1 y/1 s/3` is equivalent to scheduling the 1st module in the module tracker database for year 1 special semester 1.
* `take 1 y/1 s/4` is equivalent to scheduling the 1st module in the module tracker database for year 1 special semester 2.


#### 4.2.2 Un-take a module : `untake`

Removes a module from the academic plan.

Format: `untake INDEX`

* Removes the schedule from the module at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed module list.
* The `INDEX` **must be a positive integer** (1, 2, 3 ...).
* If the module has not been added to the academic plan, nothing happens.

Example:
* `untake 1` is equivalent to removing the schedule from the 1st module in the module tracker database.

#### 4.2.3 Viewing user information : `info`

Shows a message containing the user's Mc goal and current semester.

![info message](images/infoMessage.png)

Format: `info`

#### 4.2.4 Setting user's Mc goal OR current semester : `set`

Updates user's Mc goal or current semester.

Format: `set m/MC` `set y/YEAR s/SEM`
* `MC` must be a **positive integer** (1,2,3...).
* The `YEAR` must be a positive integer from 1-6.
* The `SEMESTER` must be a positive integer from 1-4.
* Special semesters 1 and 2 are represented by integer values 3 and 4 respectively (see examples below).

Examples:
* `set m/160` - is equivalent to setting the Mc goal to 160 credits
* `set y/2 s/1` - is equivalent to setting the current semester to year 2 semester 1
* `set y/3 s/3` - is equivalent to setting the current semester to year 3 special semester 1

#### 4.2.5 See modules taken in a specific semester : `view`

See all the modules the user have taken in a specific semester.

Format: `view y/YEAR s/SEM`
* The `YEAR` must be a positive integer from 1-6.
* The `SEMESTER` must be a positive integer from 1-4.
* Special semesters 1 and 2 are represented by integer values 3 and 4 respectively (see examples below).

Examples:
* `view y/2 s/1` - is equivalent to seeing all the modules taken in year 2 semester 1.
* `view y/2 s/3` - is equivalent to seeing all the modules taken in year 2 special semester 1.

#### 4.2.6 Remove the schedules for a specific semester from modules : `clear`

Remove the schedules for a specific semester from the academic plan in the module tracker.

Format: `clear y/YEAR s/SEMESTER`

* The `YEAR` must be a positive integer from 1-6.
* The `SEMESTER` must be a positive integer from 1-4.
* Special semesters 1 and 2 are represented by integer values 3 and 4 respectively (see examples below).

Example:
* `clear y/1 s/1` is equivalent to removing all modules in year 1 semester 1 of the academic plan.

### 4.3 Miscellaneous

Miscellaneous features consist of features that does not directly affect the database nor the academic plan. 
Currently, we only have the help feature in this sub-category.

#### 4.3.1 Viewing help : `help`

Shows a page that displays the command summary, as well as a page to the user guide.

![help message](images/helpMessage.png)

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

This section gives you the solutions to commonly asked questions.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NUS Module Tracker home folder.

**Q**: If I have a module inside my academic plan and I delete the module from the database using the "delete" command, would the module still appear in my academic plan?<br>
**A**: No, deleting a module from the database removes the module from your academic plan.
--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

This section gives you a summary on how to use each of the features.

Action | Format, Examples
--------|------------------
**Add** | `add c/CODE t/TITLE d/DESCRIPTION m/MC [tag/TAG]` <br> e.g. `add c/ST2334 t/Probability and Statistics d/Introduces students to basic probability theory and statistical inference m/4`
**Clear** | `clear y/YEAR s/SEMESTER` <br> e.g. `clear y/1 s/1`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Edit** | `edit INDEX [c/CODE] [t/TITLE] [d/Description] [m/MC] [tag/TAG]` <br> e.g `edit 2 c/CS2103T t/Software Engineering`
**Find** | `find [c/] [t/] [d/] [m/] [tag/] [y/] [s/] KEYWORDS` <br> e.g. `find c/ CS2040S`
**List** | `list`
**Take** | `take INDEX y/YEAR s/SEMESTER` <br> e.g. `take 2 y/2 s/1`
**Untake** | `untake INDEX` <br> e.g. `untake 1`
**Help**| `help`
**Info**| `info`
**Set**| `set m/MC` or `set y/YEAR s/SEMESTER` <br> e.g. `set m/120` `set y/2 s/1`
**View**| `view y/YEAR s/SEMESTER` <br> e.g. `view y/2 s/1`
