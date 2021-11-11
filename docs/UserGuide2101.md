---
layout: page
title: User Guide
---
![App Logo](images/intern_watcher.png)

Intern Watcher (IW) is a **desktop app for Human Resource Managers to manage internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, IW can get your applicant management tasks done faster than traditional GUI apps.

You can use Intern Watcher to add, edit and delete the internship applicants. Intern Watcher also allows you to track applicants' details such as grades and skills, as well as filter applicants by their details.

This user guide covers a quick walk-through on how to use this application, as well as the full descriptions of the features available.
* Table of Contents
{:toc}

## Components Of Ui

![UiComponents](images/UIComponents.png)

--------------------------------------------------------------------------------------------------------------------

## Quick start


1. Ensure you have Java `11` or above installed on your computer. You can download it [here](https://www.oracle.com/java/technologies/downloads/#java11). <br>
If you are a Windows user, you may select the Windows tab and download the application as shown in the image below. Mac users can click on the macOS tab and follow the same.

![DownloadJava](images/DownloadJava.png)

2. Download the latest `internwatcher.jar` from [here](https://github.com/AY2122S1-CS2103T-F12-2/tp/releases/download/v1.4/internwatcher.jar). <br>
You may ignore the warning pop-up and click on `Keep` to download Intern Watcher.
![DownloadApp](images/DownloadApp.png)

3. Copy the file to the folder you want to use as the _home folder_ for your Intern Watcher.

|macOS|Windows|
|---|---|
|<img src="images/MacFolder.png" alt="drawing" width="200" />|<img src="images/WindowsFolder.png" alt="drawing" width="200" />|


4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

This didn't work? Refer to the [FAQ](#faq) for more information on alternative ways to launch Intern Watcher.
<br>
![Ui](images/Ui.png)
5. Type any command in the command box and press the "Enter" key to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Here's an example usage of Intern Watcher:

* Let's start by adding a few applicants to Intern Watcher. `add n/John Doe p/85912735 e/johndoe@example.com g/4.50 i/NUS c/Computer Science y/06/2025 j/Software Engineer a/APPLIED` : Adds an applicant named `John` to Intern Watcher.

* You can try adding another applicant to the list: `add n/Jane Doe p/98274537 e/jane_doe@example.com g/4.80 i/NTU c/Computer Science y/06/2025 j/Software Engineer a/APPLIED`.

* Say you only want to interview applicants studying computer science with a GPA greater than or equal to 4.80.
  `filter g/4.80 c/Computer Science` : Lists all applicants satisfying the criterias. Namely, applicant `Jane`.

![Filter](images/QuickstartFilter.png)

* Let's give the filtered applicants the `SCHEDULED` application status to indicate our plans to interview them.
  `edit ALL a/SCHEDULED` : Edits all currently displayed applicants(`Jane`) to have the `SCHEDULED` application status.

* Say you do not wish to keep `John` in our records, since we have no plans to interview him. `find John`: Finds all applicants
  with a name containing `John`.

* `delete 1` will delete the first applicant in our `find` results.

* Perhaps after second consideration, you wish to reconsider `John`. `undo` : Undo the last command that you entered, namely `delete`.

* To view the skills `John` has, try `view 1 T` which will display the skills of the 1st applicant.

* Perhaps due to the incompatible skill set John has with regard to the job requirements, you make the final decision that `John` is not a good fit. `redo` : Reverses the last undo command, and deletes `John`.

* Typing `exit` will save the data we have and exit the application.


* If you encounter any problems, please refer to the [FAQ section](#faq) below.
--------------------------------------------------------------------------------------------------------------------


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command formats:**<br>

* Words in `UPPER_CASE` are [fields](#specification-of-fields) to be supplied by you.<br>
    * e.g. in `add n/NAME`, `NAME` is to be replaced with the name of the applicant you want to add such as `add n/John Doe`.<br>

* Fields in [square brackets](#glossary) are optional.<br>
    * e.g. in `n/NAME [s/SKILL]`, the `SKILL` field is optional. You can enter `n/John Doe s/Python` or just `n/John Doe`.

* Fields with `…+` notation behind them can be specified once or more times.<br>
    * e.g. `[s/SKILL]…+` can be specified as ` ` (i.e. 0 times), `s/Java`, `s/Python s/C` etc since the field is optional and can be specified more than once.



</div>

### How to get help

Format: `help`

If you are lost and wish to refer to help listed in this user guide, type `help` to obtain the user guide’s link.

![help message](images/helpMessage.png)



### How to add an applicant

Format: `add n/NAME p/PHONE e/EMAIL g/GRADE i/INSTITUTION c/COURSE y/GRADUATION_YEAR_MONTH j/JOB [a/APPLICATION_STATUS] [s/SKILL]…+`

If you want to add a new applicant to Intern Watcher, type a command like
`add n/Jonathan Tan p/86565432 e/jonathan_tan@example.com g/4.50 i/NTU c/Computer Science y/06/2025 j/Software Engineer a/SCHEDULED s/Java s/C s/Python` to add an applicant with the specified information.

![Add result](images/AddResult.png)


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant can have any number of skills (including 0)
</div>


### How to list all applicants

Format: `list`

If you want to display the list of all applicants in Intern Watcher, type `list`. All applicants you currently have in the application will be displayed in the applicant list panel.


### How to view an applicant's details

Format: `view INDEX [T]`

If you want to view more details about a specific applicant, type `view` with the corresponding [index](#glossary) of the applicant you wish to view.

For example, to view the first applicant in the currently displayed applicant list, type: `view 1` to see the 1st applicant's academic records.


* If only `INDEX` is specified, it will show the specified applicant's academic records by default.

|Command|Effect|
|---|---|
|<img src="images/ViewOneBefore.png" alt="drawing" />|<img src="images/ViewOneAfter.png" alt="drawing" />|


* If you wish to switch between an applicant's skills and academic tab, type an additional `T` behind your view command.

e.g. `view 2 T` to view the 2nd applicant’s skill tab from the academic tab, and vice versa.

|Command|Effect|
|---|---|
|<img src="images/ViewTwoTBefore.png" alt="drawing" />|<img src="images/ViewTwoTAfter.png" alt="drawing" />|

### How to filter the applicants

Format: `filter [g/GRADE] [i/INSTITUTION]…+ [c/COURSE]…+ [y/GRADUATION_YEAR_MONTH] [j/JOB]…+ [a/APPLICATION_STATUS]…+ [s/SKILL]…+`

If you want to view applicants in Intern Watcher who match certain criteria, type `filter` followed by the criteria specified.

Example:
* `filter g/4.50` will show applicants with grades not less than 4.50.
* `filter y/06/2021 s/Python` will show only applicants who graduate before June 2021 and with skill in Python.

|Command|Effect|
|---|---|
|<img src="images/FilterBefore2101.png" alt="drawing" />|<img src="images/FilterAfter2101.png" alt="drawing" />|

### How to edit a specific applicant

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GRADE] [i/INSTITUTION] [c/COURSE] [y/GRADUATION_YEAR_MONTH] [j/JOB] [a/APPLICATION_STATUS] [s/SKILL]…+`

If you want to edit the details of an existing applicant,type `edit` followed by the corresponding index of the applicant and the field that you wish to update.


Example:
*  `edit 1 p/89274567 e/alex_yeoh@example.com` edits the phone number and email address of the 1st applicant to be `89274567` and `alex_yeoh@example.com` respectively.

|Command|Effect|
|---|---|
|<img src="images/EditIndexBefore.png" alt="drawing" />|<img src="images/EditIndexAfter.png" alt="drawing" />|


### How to edit the application status of multiple applicants

Format: `edit ALL a/APPLICATION_STATUS`

If you want to change the application status of all applicants in the current shown list, type `edit ALL` followed by the status that you wish to change to.
So to change multiple specific applicants, you can use the `filter` command to display the desired applicants before using the `edit ALL` command.

* The `APPLICATION_STATUS` can only be one of these 7 statuses: `ACCEPTED`, `REJECTED`, `INTERVIEWED`, `APPLIED`, `SCHEDULED`, `RECEIVED` and `OFFERED`.


Examples:

*  `filter a/APPLIED` followed by `edit ALL a/INTERVIEWED` updates all applicants with `APPLIED` application status to have the `INTERVIEWED` application status.

|Command|Effect|
|---|---|
|<img src="images/EditAllStatusBefore.png" alt="drawing" />|<img src="images/EditAllStatusAfter.png" alt="drawing" />|

### How to delete a specific applicant

Format: `delete INDEX`

If you want to delete an applicant from the current shown list, type `delete` followed by the corresponding index of the applicant.

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in Intern Watcher.

|Command|Effect|
|---|---|
|<img src="images/DeleteTwoBefore.png" alt="drawing" />|<img src="images/DeleteTwoAfter.png" alt="drawing" />|

### How to delete multiple applicants

Format: `delete ALL`

If you want to delete all applicants in the current shown list, type `delete ALL`.
So to delete multiple specific applicants, you can use the `filter` command to display the desired applicants before using the `delete ALL` command.

Examples:
* `list` followed by `delete ALL` deletes all applicants in Intern Watcher.

|Command|Effect|
|---|---|
|<img src="images/DeleteALLBefore.png" alt="drawing" />|<img src="images/DeleteALLAfter.png" alt="drawing" />|



### How to find applicants by name

Format: `find NAME`

If you want to find applicants by name, simply type `find NAME`.

Examples:
* `find Doe` returns both `Jane Doe` and `John Doe`
  <br>
  ![result for 'find Doe'](images/FindDoe.png)


### How to undo an action

Format: `undo`

If you made a mistake, it can be undone via typing `undo` which will revert that last command.

Examples:
* `undo` (after `delete 2`). The applicant that was removed will be restored in Intern Watcher.

|Command|Effect|
|---|---|
|<img src="images/UndoBefore.png" alt="drawing" />|<img src="images/UndoAfter.png" alt="drawing" />|



### How to reverse an undo

Format: `redo`

If you undo by mistake, by typing `redo` you can revert the `undo` command.

Examples:
* `delete 1` followed by `undo` followed by `redo`. The delete command will be redone.

|Command|Effect|
|---|---|
|<img src="images/RedoDeleteBefore.png" alt="drawing" />|<img src="images/RedoDeleteAfter.png" alt="drawing" />|


### How to exit the program

Format: `exit`

If you want to exit the program, type `exit`.


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Intern Watcher will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------


## FAQ
Listed below are frequently asked questions, and their corresponding answers. If you encounter a problem not listed here, feel free to contact [us](#contact-us).

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InternWatcher home folder.

**Q**: Can I use multiple save files?<br>
**A**: No, only Intern Watcher currently only supports single save files.

**Q**: I couldn't start Intern Watcher by double-clicking on the file, what should I do?<br>
**A**: Go to terminal and type `java -jar internwatcher.jar` command to launch Intern Watcher.

**Q**: Why is my applicant list empty?<br>
**A**: To protect the user from malicious save files, Intern Watcher resets the applicant list if the save file is corrupted. Revert the save to your last backup, or contact us if you need help.

**Q**: Why does my command not work?<br>
**A**: Firstly check if the command is spelled correctly in the specified format. If it is, it could be due to a mismatch in the input fields' [specifications](#specification-of-fields).

**Q**: Is my data automatically saved?<br>
**A**: Yes, Intern Watcher saves automatically after every command.There is no need to save manually.

**Q**: Can I manually alter my data outside applications?<br>
**A**: Yes, Intern Watcher saves data as a JSON file `[JAR file location]/data/internwatcher.json`. Advanced users are welcome to update data directly by editing that data file. Beginner users are advised to do the editing through the application to avoid any incidents.


--------------------------------------------------------------------------------------------------------------------

## Specification of Fields

This section is to check for the validity of fields. If fields do not match the specifications closely, there is a chance that your command will not be valid.

`Name` : The applicant's name. Should only include alphanumeric characters and spaces only. Should not be blank.

`Phone` : The applicant's phone number. Should only contain digits from 0 to 9, with a minimum length of 3 digits.

`Email` : The applicant's email address. Should be in the form of *Local-part*@**Domain**. *Local-part* should contain only alphanumeric characters with only these special characters `+_.-`.
**Domain** can be separated into ***label*** with `.` if necessary. Each ***label*** should only contain alphanumeric characters and separated and is separated by `-` if necessary. Domain is at least 2 characters long and needs to start and end with alphanumeric characters.

`Grade` : The applicant's Grade Point Average (GPA). Should be a number in 2 decimal places from 0.00 - 5.00.

`Institution` : The applicant's school. Should only include alphanumeric characters and space only. Should not be blank.

`Application_Status` : The applicant's application status. Case sensitive. Should only be one of these 7 statuses, `ACCEPTED` , `REJECTED`, `INTERVIEWED`, `APPLIED` , `SCHEDULED`, `RECEIVED` and `OFFERED`.

`Course` : The applicant's course of study in their school. Should only include alphabet characters and space only. Should not be blank.

`Graduation_Year_Month` : The applicant's estimated date of graduation from their school. Should be of format MM/yyyy and be after the date 01/2020.

`Job` : The job/position that the applicant applied for. Should only include alphabet characters and space only. Should not be blank.

`Skill`: The applicant's skillset. Should only include alphanumeric characters, spaces and `+#` symbols only.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME p/PHONE e/EMAIL g/GRADE i/INSTITUTION c/COURSE y/GRADUATION_YEAR_MONTH j/JOB [a/APPLICATION_STATUS] [s/SKILL]…+` <br> e.g. `add n/John p/999 e/a@a.com g/4.00 i/NTU c/CS y/06/2025 j/Software Engineer a/INTERVIEWED s/Python`
**List** | `list`
**View** | `view INDEX [T]` <br> e.g. `view 2 T`
**Filter** | `filter [g/GRADE] [i/INSTITUTION]…+ [c/COURSE]…+ [y/GRADUATION_YEAR_MONTH] [j/JOB]…+ [a/APPLICATION_STATUS]…+ [s/SKILL]…+` <br> e.g. `filter s/JAVA y/06/2022 i/NUS a/REJECTED`
**Edit** | `edit ALL a/APPLICATION_STATUS` , <br>`edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GRADE] [i/INSTITUTION] [c/COURSE] [y/GRADUATION_YEAR_MONTH] [j/JOB] [a/APPLICATION_STATUS] [s/SKILL]…+`<br>e.g. `edit ALL a/REJECTED` , <br> `edit 2 n/James Lee e/jameslee@example.com`
**Delete** | `delete INDEX`, <br> `delete ALL`<br> e.g. `delete 3`, <br> `delete ALL`
**Find** | `find NAME`<br> e.g. `find John`
**Undo** | `undo`
**Redo** | `redo`
**Exit** | `exit`

--------------------------------------------------------------------------------------------------------------------

## Glossary

Word | Definition
--------|------------------
**Applicant** | A person who has applied to a job posting at your company.
**Command Line Interface(CLI)** | Command Line Interface connects a user to a computer program. The command is typed on a specific line following a visual prompt from the computer.
**Graphical User Interface(GUI)** | Graphical user interface is a form of user interface that allows users to interact with a computer program through graphical icons as primary notation, instead of text-based user interfaces.
**JSON file** | JSON is a file format used to store information in an organized and easy-to-access manner. Its full form is JavaScript Object Notation.
**Index** | The numerical position of the Applicant in an ordered list.
**Square Brackets** | Either of a pair of brackets in the form `[ ]`.

--------------------------------------------------------------------------------------------------------------------

## Contact Us
If you have any questions or have suggestions for the application, feel free to contact us at [internwatcher@gmail.com](mailto: internwatcher@gmail.com).

