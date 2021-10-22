---
layout: page
title: User Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction
Financial Advisor Smart Tracker (FAST) is a free open-source desktop app for Financial Advisors to manage 
their contacts. More importantly, FAST is **optimized for those who prefer to work with a Command Line 
Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). For Financial Advisors that 
can type fast, FAST will get your contact management tasks done faster than traditional GUI apps!

### How to use this user guide
We recommend first time users to read the user guide in the order that is written.
Throughout the user guide, we include links which will jump to the corresponding section of the user guide. These
links allow you to quickly navigate this document.

### Features overview
Here is an overview of the features FAST offers:
* Keep track of your client's information (i.e. Name, Phone number, Email address, Home address, and Remarks) in our
  all-in-one app!
* Record and keep track of your client's appointment dates. Never miss another appointment date again!
* Tag your clients with the insurance plan they have purchased or are interested in.
* View built-in analysis of your client base to better understand your portfolio.

Interested? Jump to [Quick Start](#quick-start) to get started! 


## Quick start

1. Ensure you have Java `11` or above installed in your Computer. FAST can be used 
on any operating system including Windows, MacOS or Linux

1. Download the latest `fast.jar` from [here](https://github.com/AY2122S1-CS2103T-T09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FAST.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
   When you first start up, FAST will contain some sample data for you to explore the different features.
   ![Ui](images/Ui.png)
   
   <div markdown="block" class="alert alert-info">
   :information_source: Icons shown above will be added in future iterations<br>
   </div>
1. You are now ready to start managing your clients!
   
### User Interface
Referring to the image above, the _Menu bar_ at the top is where you can access the _Help page_, _Stats page_ and exit 
FAST. <br><br>
Next, the _Results display_ is where FAST gives you feedback to your commands. For example, if you make a typo
in your command, FAST will inform you here! <br><br>
Following that, we have the _Client information_ cards. Each client is represented by a card, which contains
all the information of that client. <br><br>
Lastly, the _Command box_ is where you can type in all your commands!

### Try it out!

We recommend that you play around with FAST to get a better idea of the features and their usages. Don't worry, these
are all sample data! Here are some commands you can try to see what they do:

    
1. Add a contact named "John Doe" to FAST
   * `add n/John Doe`
    
2. Delete the 3rd contact in FAST
    * `delete 3`
    
3. View all your saved contacts
    *  **`list`**
    
<div markdown="block" class="alert alert-info">
:information_source: When you are ready to use FAST to keep track of your clients, you can use 
`clear` to delete this sample data. <br>
</div>


 Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

Take note that for all the commands listed below, we follow these notations and style!

* Words in `UPPER_CASE` are the parameters to be added in by the user.<br>
  * e.g. for `add n/NAME`, `NAME` is where you would replace it with the actual client's name such as `add n/John Doe`.

* Items in square brackets are optional.<br>
  * e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used zero or more times.<br>
  * e.g. `[t/TAG]…​` can be `t/friend`, `t/friend t/family` or just left empty.

* Parameters can be in any order.<br>
  * e.g. if the command uses this format: `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the
  last occurrence of the parameter will be taken.<br>
  * e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* For commands that do not take in any parameter (such as  `list`, `exit` and `clear`), anything written after
  the command will be ignored.<br>
  * e.g. `help 123` will simply be interpreted as `help`.

* `INDEX` refers to the index number shown in the client list. The index **must be a positive integer** 1, 2, 3, …​

</div>

### Viewing help: `help`

Opens a new window that contains command usage, and a quick start guide.
In the help window, you can access all the command usages using the dropdown menu.

![help window](images/helpWindow.png)

Format: `help [COMMAND]`

Examples:
* `help` will just open the default help window
* `help add` will open the help window and directly navigate to the `Add` command help page.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The help window can also be quickly accessed by entering using the F1 key on your keyboard!
</div>


### Adding a client: `add`

Adds a client to FAST.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` will add a new client called 
  `John Doe`, who has phone number of `98765432`, has an email address `johnd@example.com` and stays at 
  `John street, block 123, #01-01`.
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal` will add a new client 
   called `Betsy Crowe`, has a phone number of `1234567`, has an email address `betsycrowe@example.com`, 
  stays at `Newgate Prison` and is tagged as both a `friend` and `criminal`.

### Listing all clients: `list`

Shows a list of all your clients in FAST. This command is useful to return to viewing your full client list
after a [find](#searching-for-clients-find) command has been executed.

Format: `list` 

### Editing a client: `edit`

Edits an existing client in FAST. This can be used if the client's information has changed, or if you
entered an incorrect entry previously.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list.
* At least one of the optional fields must be provided.
* Only the edited fields will be updated to the input values, while the unedited values are unchanged.
* When editing tags, all existing tags of the client will be replaced with the new tags.
* You can remove all the client’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the first client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the second client to be `Betsy Crower` and clears all existing tags.

### Searching for clients: `find`

Finds clients by their name, [priority](#using-priority-tags-pr), [tags](#editing-a-tag-tag) or 
[remarks](#adding-a-remark-rmk). This is useful if your have many clients in FAST and wish to quickly find a
specific client. To return to the full client list, you can use the [list](#listing-all-clients-list) command

Format: `find QUERY [MORE_QUERIES]` OR `find pr/PRIORITY [MORE_PRIORITIES]`
OR `find t/TAG [MORE TAGS]` OR `find r/REMARK [MORE REMARKS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the queries does not matter. e.g. `Hans Bo` will match `Bo Hans`
* You can search by name, priority, tags or remarks using the formats shown above.
* Names with words starting with the query will match. e.g. `Han` will match `Solo Hans`.
* For priority searches, there are 3 priorities, `pr/low`, `pr/med`, or `pr/high`.
* Any remarks containing the searched remark will match. e.g. `r/piz` will match `likes pizza`.
* clients matching at least one search query will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find pr/high med` returns all clients with `HighPriority` and `MediumPriority`.
* `find t/friends enemies` returns all clients tagged with `friends` or `enemies`.
* `find r/good` returns all clients with remarks containing `good`.

### Deleting a client: `del`

Deletes the specified client from the address book. This is useful when a client has stopped using your services.

Format: `del INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.

Examples:
* `list` followed by `del 2` deletes the second clients in FAST.
* `find Betsy` followed by `del 1` deletes the first client in the results of the `find` command.

<div markdown="span" class="alert alert-primary">:bulb: Tip:
Can be used to delete up to **10** contacts in a single `del` command by supplying more `INDEX`.

Format 1: `del INDEX INDEX [INDEX]...`
Format 2: `del INDEX-INDEX`

<div markdown="span" class="alert alert-primary">:exclamation: Reminder:
If *Format 1* is used, there should be a space in between each `INDEX`.
If *Format 2* is used, first `INDEX` should be smaller than second `INDEX`. There *should not* be any spaces in between '`INDEX`-`INDEX`'. 
</div>

Examples:
* `del 2 4 6 8 10` deletes the 2nd, 4th, 6th, 8th and 10th person in FAST.
* `del 3-5` deletes the 3rd, 4th and 5th person in FAST.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This CANNOT be undone!</div>

### Adding a remark: `rmk`

Adds a remark to an existing client in FAST. This is useful for adding additional client information such 
as their preferred meeting timing, allowing you to better keep track of your clients' preferences and habits.

Format: `rmk INDEX [r/REMARK]`

* Adds a remark to the client at the specified `INDEX`.
* Remarks have a character limit of 100 characters.
* To delete a remark, leave the remark parameter `[r/REMARK]` empty.

Examples:
* `rmk 1 r/loves to eat`  adds a remark `loves to eat` to the first client.
* `rmk 1` removes the remark from the first client.<br>

### Adding an appointment: `aa`

Adds a scheduled appointment with the client. An appointment includes a date, time and venue. This allows you to keep
track of all your clients' appointment dates all within the same app. You can also [edit](#editing-an-appointment-eppt),
[delete](#deleting-an-appointment-dappt), or [mark as completed](#updating-completed-appointment-done) an appointment.

Format: `aa INDEX d/DATE [t/TIME] [v/VENUE]`

* Only able to add a scheduled appointment with the client at the specified `INDEX` if no appointment exists yet.
* `INDEX` refers to the index shown in the displayed client list.
* `DATE` the date of the appointment in the format **`yyyy-mm-dd`**.
* `TIME` the time of the appointment in the 24-hour format **`HH:mm`**.
* `VENUE` the location of the appointment, which can at most be 30 characters long <br>(including whitespaces).

Examples:
* `aa 1 d/2021-03-27` adds an appointment with date `27 Mar 2021` to the first client in FAST.
* `aa 3 d/2021-03-27 t/18:00` adds an appointment with date `27 Mar 2021` and time `1800hrs` to the third client in FAST.
* `find Matthew John` followed by `aa 3 d/2021-03-27 t/18:00 v/Velocity` adds an appointment with date `27 Mar 2021`, 
  time `1800hrs` and venue `Velocity` to the third client in the results of the `find` command.

### Editing an appointment: `ea`

Edits a scheduled [appointment](#adding-an-appointment-appt) with the client. This command is useful when your appointment has been rescheduled or 
has a change in location.

Format: `ea INDEX [d/DATE] [t/TIME] [v/VENUE]`

* Edits a scheduled appointment with the client at the specified `INDEX` if the appointment exist.
* Existing details will be updated with the input data.
* `INDEX` refers to the index shown in the displayed client list.
* `DATE` the date of the appointment in the format **`yyyy-mm-dd`**.
* `TIME` the time of the appointment in the 24-hour format **`HH:mm`**.
* `VENUE` the location of the appointment, which can at most be 30 characters long <br>(including whitespaces).
* At least **one** of the optional fields must be present.

Examples:
* `ea 1 d/2021-03-27` edits the appointment date to be `27 Mar 2021` of the first client.
* `ea 3 v/  t/18:00` edits the appointment time to be `1800hrs` and clears the appointment venue of the third client.

### Deleting an appointment: `da`

Deletes a scheduled [appointment](#adding-an-appointment-appt) with the client. This command should be used when 
the appointment has been cancelled with a client.

Format: `da INDEX`

* Deletes a scheduled appointment with the client at the specified `INDEX` if the appointment exist.
* Existing details will be deleted.
* `INDEX` refers to the index shown in the displayed client list. 

Examples:
* `da 1` deletes the appointment of the first client.
* `find Ben` followed by `da 3` deletes the appointment the third client in the result of the `find` command.

### Updating completed appointment: `ma`

Marks the appointment [appointment](#adding-an-appointment-appt) with the client as completed. This also allows you to keep 
track of the number of completed appointments with your client.

![markAppointment](docs/images/UG-Screenshots/MarkAppointmentScreenshot.PNG)

Format: `ma INDEX`
* Increment the completed appointment count with the client at the specified `INDEX` if the appointment exist.
* Existing details will be deleted.
* `INDEX` refers to the index shown in the displayed client list. 

Examples:
* `ma 1` updates the completed appointment counter of the first client.
* `find Matthew` followed by `ma 3` updates the completed appointment counter of the third client in the result of 
  the `find` command.

### Sorting all clients: `sort`

Sorts all existing client by the given condition. Currently, you can sort by name, [appointment date](#adding-an-appointment-appt),
and [priority tag](#using-priority-tags-pr). This allows you to keep your clients list orderly and well-organised.
It can also be used to quickly sieve through large client lists.

Format: `sort KEYWORD`
* There are only 3 values for `KEYWORD`: `name`, `appointment`, `priority`.
* `name` will sort all clients in alphabetical order from A to Z.
* `appointment` will sort all client by appointment date from earliest date to latest.
* `priority` will sort all client by priority tag from highest priority to lowest priorty.

Examples:
* `sort priority` Sorts all existing clients by the priority from highest priority tag to lowest priority tag.

### Editing a tag: `tag`

Modifies a tag from a specified client. Tags are split into 3 categories: general tags, [priority tags](#using-priority-tags-pr),
and [investment plan tags](#using-investment-plan-tags-ip). 

Format: `tag INDEX [a/TAG] [d/TAG]`
* Use `a/` to add a tag, and `d/` to delete a tag.
* Does not affect any unmentioned tags, unlike `edit`.
* `INDEX` refers to the index shown in the displayed client list. 
* Delete operations are performed first before add operations, regardless of their order in the input.
* Tags have a maximum length of 20 characters, and may only contain alphanumeric characters.

Example: 
* `tag 1 a/thin d/fat` will add the `thin` tag while deleting the `fat` tag.
* `tag 1 d/pr/low a/pr/high` will delete the `LowPriority` tag before adding the `HighPriority` tag.
* `tag 1 a/ip/property` will add a `PropertyInsurance` tag to the contact.

### Using Priority tags: `pr/`

A set of fixed tags to indicate the priority level of clients. To use a priority tag, refer to the
[tags](#editing-a-tag-tag) section.

Usage: replace any fields requiring `[TAG]` with one of the following terms:
* LowPriority: `pr/low`
* MediumPriority: `pr/med`
* HighPriority: `pr/high`

Example:
* `tag 2 a/pr/low` will add a `LowPriority` tag to the specified client.
* `edit 1 t/pr/med` will set the specified client to have only the `MediumPriority` tag.

### Using Investment Plan tags: `ip/`

A set of fixed tags to indicate the type of investment plans purchased by clients. To use an investment
plan tag, refer to the [tags](#editing-a-tag-tag) section.

Usage: replace any fields requiring `[TAG]` with one of the following terms:
* HealthInsurance: `ip/health`
* Investment: `ip/invest`
* LifeInsurance: `ip/life`
* MotorInsurance: `ip/motor`
* PropertyInsurance: `ip/property`
* Savings: `ip/save`
* TravelInsurance: `ip/travel`

Example: 
* `tag 2 a/ip/life` will add a `LifeInsurance` tag to the specified client.
* `edit 1 t/ip/motor` will set the specified client to have only the `MotorInsurance` tag.

### Viewing statistics

FAST comes with built-in statistics to provide you with an overview of your data.
To view the statistics, simply click the "Stats" menu item on the top bar or press `F2`.
Currently, FAST supports these statistics:
* Priority Tag Chart
* Insurance Plan Chart (Coming soon!)

![stats window](images/statsWindow.png)

### Clearing all entries: `clear`

Clears all entries from FAST. This command is useful to remove the default sample data in FAST.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This CANNOT be undone! 
</div>

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

FAST data are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

FAST data are saved as a JSON file `[JAR file location]/data/fast.json`. Advanced users are welcome to update data 
directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FAST will discard all data and start with an empty data file at the next run.
</div>




--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FAST home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Appointment** | `appt INDEX d/DATE [t/TIME] [v/VENUE]`<br> e.g. `appt 3 d/2021-03-27 t/18:00 v/Clementi Park`
**Add Contact** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete Appointment** | `dappt INDEX`<br> e.g. `dappt 1`
**Delete Contact** | `del INDEX`<br> e.g. `del 3`
**Edit Appointment** | `eppt INDEX [d/DATE] [t/TIME] [v/VENUE]`<br> e.g. `eppt 3 v/Clementi Town d/2021-03-27 t/18:00`
**Edit Contact** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`
**Edit Remark** | `rmk INDEX r/REMARK` OR `rmk INDEX`<br> e.g. `rmk 1 r/likes dogs`
**Edit Tag** | `tag INDEX a/[TAG] d/[TAG]` <br> e.g. `tag 1 a/friend d/ip/life`
**Find** | `find QUERY [MORE_QUERIES]` OR `find pr/PRIORITY [MORE_PRIORITIES]` OR `find t/TAG [MORE TAGS]` OR `find r/REMARK [MORE REMARKS]`<br> e.g. `find James Jake`
**Help** | `help [COMMAND]` <br> e.g. `help add`
**Investment Plan Tag** |HealthInsurance: `ip/health`<br>Investment: `ip/invest`<br>LifeInsurance: `ip/life`<br>MotorInsurance: `ip/motor`<br>PropertyInsurance: `ip/property`<br>Savings: `ip/save`<br>TravelInsurance: `ip/travel`
**List** | `list`
**Priority Tag** | LowPriority: `pr/low`<br>MediumPriority: `pr/med`<br>HighPriority: `pr/high`
**Remark** | `rmk INDEX [r/REMARK]`
**Sort** | `sort KEYWORD`
**Update Completed Appointment** | `done INDEX`<br> e.g. `done 5`
