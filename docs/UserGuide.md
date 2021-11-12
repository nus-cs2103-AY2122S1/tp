---
layout: page
title: User Guide
---

## Introducing CallMeMaybe

CallMeMaybe (CMM) is a **desktop app** that helps Singaporean Telemarketers in customer contact management.
CMM provides a solution to quickly catalog contacts based on whether they have already been called or not.
The in-built tracking functionality serves as a reminder for you to reach back on previously unreachable customers.
Importing and exporting of existing customer database is also supported by CMM to facilitate team-based environments.


CMM is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

If you can type fast, CMM can get your contact management tasks done faster than traditional GUI apps.


## Navigating the User Guide

This user guide aims to help you easily find the information you desire for any command. <br>
Each Command section is separated into:<br>
* Brief description of the command

* Command Format

* Things to note

* Example

* Common issues

Throughout this guide, you may encounter the following icons:

* **:exclamation: Caution:** Warns you about possible irreversible behaviours <br>
* **:information_source: Notes:** Provides general information that you might find useful <br>
* **:bulb: Tip:** Provides useful practical advice about their respective features <br>

<div markdown="block" class="alert alert-info">

**:information_source: Note:** Straightforward commands such as `clear` may not have the sections "Things to note", "Example" and "Common issues"
</div>

It is highly recommended that you familiarise yourself with the [Command Notations](#4-command-notations) we use. <br>

For new users, head over to [Quick Start](#2-quick-start) to get started. You can learn the command notations along the way as well. <br>

For more experienced users, feel free to skip over to the [Features](#5-overview-of-features) section to check out detailed descriptions of the features we offer. <br>

If you have any questions about CMM, please refer to the [FAQ](#6-faq). <br>

Hope you have a great time with CMM.

--------------------------------------------------------------------------------------------------------------------

## 1. Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 2. Quick start

1. Ensure you have Java 11 or above installed in your computer. You can learn how to install the latest version of Java [here](#71-installing-java).
If you already have Java installed, you can follow [these steps](#72-checking-java-version) to check if you have an appropriate version of java.

2. Download the latest CallMeMaybe.jar from [here](https://github.com/AY2122S1-CS2103T-T13-4/tp/releases).

3. Copy the file to the folder you want to use as the home folder for CMM.

4. Double-click the file to start the app. A prompt will popup asking whether you want to import any new contacts. 
The prompt will look like the screenshot below :

   ![importPopUpUi](images/importPopUpMessage.png) <br>
   To learn more about what each option does in detail, click [here](#514-importing-the-data-into-database).
   An example file import can be downloaded [here](https://github.com/AY2122S1-CS2103T-T13-4/tp/releases/tag/Test_Files)
    <div style="page-break-after: always;"></div>
5. After you click any button on the prompt, CMM will execute the selected option, and a GUI similar to the screenshot 
below should appear in a few seconds. Note how the app contains some sample data upon first startup.

   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g., typing "help" and pressing `Enter` will 
open the help window. You can find the commands that CallMeMaybe supports below:
   - `add n/John Doe p/98765432 e/johnd@example.com` : Adds a contact named John Doe to the CMM database
   
   - `list` : Lists all contacts
   
   - `called 2` : Marks the 2nd contact shown in the current list as called
   
   - `edit 1 n/Bob p/62353535` : Edits the name and phone number of the first contact in the displayed list
   
   - `findAny n/alex g/m` : Finds the contacts that have the substring `alex`in their name **AND/OR** are males
   
   - `findAll n/alex g/m` : Finds the contacts that have both the substring `alex` in their name **AND** are males
   
   - `delete 3` : Deletes the 3rd contact shown in the current list
   
   - `display 4` : Displays the full contact details of the fourth contact in the displayed list
   
   - `filter called` : Sorts the list to display uncalled contacts first
   
   - `clear` : Deletes all contacts
   
   - `exit` : Exits the app

7. Remember to clear the sample data using `clear` command before adding your own data.

8. Refer to the [Features](#5-overview-of-features) below for details of each command.


--------------------------------------------------------------------------------------------------------------------
## 3. Interface layout

**Command Panel**

![Ui](images/Command Panel.png)<br>
*Image of CMM's Command Panel*

* Commands are entered in the Command Line (section labeled `Enter command here...`)

* The Feedback Box (the bottom section) displays the result of the command, which can be an error message if the command is invalid

**Display List**

![Ui](images/Display List.png) <br>
*Image of CMM's Display List*

* Displays the list of contacts along with their contact details which includes the name, phone number, email address and isCalled attributes

**Person Card**

![Ui](images/PersonCard.png) <br>
*Image of CMM's Person Card*

* Displays additional information of each contact in the list of contacts. 
This includes the attributes age, gender, address and interests

* When application is started, the first contact in the list is displayed. If the contact list is empty,
CMM displays an 'empty contact' with all the fields set to 'N.A'

--------------------------------------------------------------------------------------------------------------------

## 4. Command Notations

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user<br>
  e.g., in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`

* Items in square brackets are optional<br>
  e.g., `n/NAME [g/GENDER]` can be used as `n/John Doe g/M` or as `n/John Doe`

* Items with `…`​ after them can be used multiple times including zero times<br>
  e.g., `[i/INTEREST]…​` can be used as ` ` (i.e., 0 times), `i/running`, `i/running i/watching tv` etc

* Parameters can be in any order<br>
  e.g., if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable

* If a parameter is expected only once in the command is specified multiple times, only the last occurrence of the parameter will be taken<br>
  e.g., if you specify `p/99998888 p/66667777`, only `p/66667777` will be taken

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored<br>
  e.g., if the command specifies `help 123`, it will be interpreted as `help`

</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 5. Overview of Features

### 5.1 Adding a contact: `add`
Adds a contact to the CMM database.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [g/GENDER] [age/AGE] [i/INTEREST]…​`

**Things to note:**
* `Name`, `Phone_number` and `Email` fields are mandatory

* `Name` must contain only alphanumeric characters

* `Phone_number` must contain exactly 8 digits

* numbers must start with "6", "8" or "9"

* `Email` should be in the form of `local-part@domain-name` <br>

  * The local-part should only contain alphanumeric characters and these special characters, `+_.-` <br>
  
  * The local-part may not start or end with any special characters<br>
  
  * The domain-name is made up of domain labels separated by periods (eg. @nus.edu.sg)<br>
  
  * The domain-name must end with a domain label at least 2 characters long<br>
  
  * The domain-name must have each domain label start and end with alphanumeric characters<br>
  
  * The domain-name must have each domain label consist of alphanumeric characters, separated only by hyphens, if any<br>
  
* `Gender` can only be "m", "f", "n.a" (case-insensitive)

* `Age` must only contain numbers

* `Address` and `Interest` can be in any format

* All the fields are case-insensitive  

<div markdown="span" class="alert alert-primary">:bulb: 

**Tip:** <br>
`Address`, `Gender`, `Age`, `Interest` fields are optional. A contact can have multiple interests.
</div>

**Example:**
  
* `add n/Betsy Crowe p/92345679 e/betsycrowe@example.com a/her house i/running i/swimming i/eating` Adds a contact with the following fields:

    * `Name`: Betsy Crowe
  
    * `Phone`: 92345679
  
    * `Email`: betsycrowe@example.com
  
    * `Address`: her house
  
    * `Intrests`: running, swimming, eating
  
    * All other unspecified fields will be set to N.A by default

The screenshot below shows the expected result. To display the full contact details, you can use the [display command](#58-displaying-full-contact-details--display).

![addCommandExample](images/AddCommandExample.png)

**Common issues:**

* _Invalid command format!_: <br>

  a. Omitted one or more of the mandatory fields <br>

  b. Used the wrong prefix (e.g., '/n' instead of 'n/') <br>

### 5.2 Listing all contacts : `list`

Shows a list of all contacts in the CMM database.

Format: `list`

**Things to note:**

* `list` shows all contacts in ascending order by name

The screenshot below shows the expected result.

![listCommandExample](images/ListCommandExample.png)

### 5.3 Marking a contact as called : `called`

Marks the specified contact from the address book as called. (i.e., contact has already been called)

Format: `called INDEX`

**Things to note:**

* `INDEX` refers to the index number shown in the displayed list

* `INDEX` **must be a positive integer** (e.g., 1, 2, 3, …​)

* If `INDEX` is valid, the `Called` field will be updated to 'True'

* No changes would be made if the `Called` field is already 'True'

* If you want to un-call a contact, use the [edit command](#54-editing-a-contact--edit)

**Example:**

* `called 3` marks the 3rd contact in the displayed list as Called

The screenshot below shows the expected result. Note how the "❌" changes to a "✔️".

![calledCommandExample](images/CalledCommandExample.png)

**Common issues:**

* _The index provided is invalid_: <br>

  a. Displayed list does not contain contact at `INDEX` <br>

  b. Index specified is larger than 2147483647 <br>

  c. Index specified is not a positive integer <br>

<div style="page-break-after: always;"></div>

### 5.4 Editing a contact : `edit`

Edits an existing contact in the CMM database.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [age/AGE] [c/CALLED] [i/(OPTIONAL INTERESTSLIST INDEX) INTEREST]...
[i/(INTERESTSLIST INDEX) remove]... [i/INTEREST]..`

**Things to note:**

* All the fields are case-insensitive

* `INDEX` refers to the index number shown in the displayed list

* `INDEX` **must be a positive integer** (e.g., 1, 2, 3, …​)

* `INTERESTSLIST INDEX` refers to the index number shown in the displayed interests list of the contact

* `INTERESTSLIST INDEX` **must be a positive integer** (e.g., 1, 2, 3, …​)

* You can find the Interests list of a contact here (refer to the screenshot below) and can be displayed by using the [display command](#58-displaying-full-contact-details--display)
   ![interestsList](images/interestsList.png)

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:** <br>

* If all 3 operations are specified in the same command, editing the interest is always done first, followed by removing, then adding the interest.

* Specifying (INTERESTLIST INDEX) is optional as well

* If (INTERESTLIST INDEX) is specified, the interest at that index would be updated

* If it is not, then an interest would be added to the list instead

* You can refer to the example below for a better understanding of the edit command functionality

</div>

**Example:**

* `edit 1 n/Malan g/F i/Painting i/(2) Swimming` Edits the following fields of the **first contact in the displayed list** :<br>

    * Edit `Name` to 'Malan'

    * Edit `Gender` to 'F' 
  
    * Adds 'painting' as a *new* `Interest`
  
    * Edits the **second** `Interest` to 'Swimming'

<div style="page-break-after: always;"></div>

The screenshot below shows the expected result.

![editCommandExample](images/EditCommandExample.png)

**Common issues:**

* _The index provided is invalid_: <br>

  a. Displayed list does not contain contact at `INDEX`

* _The interests list index provided is invalid_: <br>

  a. Displayed interests list does not contain an interest at `INTERESTSLIST INDEX`

* _Invalid command format!_: <br>

  a. No fields provided <br>

  b. Used the wrong prefix. (e.g., 'i/[1]' instead of 'i/(1)') <br>

* _Invalid command arguments_: <br>

  a. Duplicate of the edited contact already exists on the address book <br>
  (e.g., editing name from 'Bob' to 'bob' is not allowed. Since names are case-insensitive, both 'Bob' and 'bob' are recognised
  as the same name)

<div style="page-break-after: always;"></div>

### 5.5 Deleting a contact : `delete`

Deletes the specified contact from the CMM database.

Format: `delete INDEX`

**Example:**

* `delete 2` deletes the *second contact in the displayed list*

<div markdown="span" class="alert alert-warning">:exclamation: 

**Caution:** Deleting is irreversible, please use with caution.
</div>

**Things to note:**

* `INDEX` refers to the index number shown in the displayed contacts list

* `INDEX` **must be a positive integer** (e.g., 1, 2, 3, …​)

**Common issues:**

* _The index provided is invalid_: <br>

  a. Displayed list does not contain contact at `INDEX` <br>

  b. Index specified is larger than 2147483647 <br>

  c. Index specified is not a positive integer <br>

<div style="page-break-after: always;"></div>

### 5.6 Finding contacts that match **ANY** of the keywords specified: `findAny`

Finds **any** contacts that match any of the fields specified by the user

Format: `findAny [n/NAME…​] [p/PHONE…​] [e/EMAIL…​] [a/ADDRESS…​] [g/GENDER…​] [age/AGE…​]
[c/CALLED…​] [i/INTEREST…​]`

**Things to note:**

* FindAny requires at least one field. But it is optional for you to include all the fields <br>

* If there are duplicate fields, CMM will only take the right-most field <br>
  (e.g., `findAny n/alex n/david` returns the same results as `findAny n/david`)

* The search is case-insensitive. <br>
  (e.g `n/hans` will return the same result `n/Hans`) <br>

* Any number of keywords can be specified within each field <br>

* The order of the keywords within each field does not matter. <br>
  (e.g., `n/Hans Bo` will return the same result as `n/Bo Hans`)<br>

* Keywords for `Called` are limited to `true`, `false`, `t`, `f`

* Keywords for `Gender` are limited to `male`, `female`, `m`, `f`

* All the fields are case-insensitive

* You can refer to the example below for a better understanding

<div markdown="block" class="alert alert-primary"> 

**:bulb: Tip: FindAny vs FindAll**

* `findAny` searches for contacts that satisfy **ANY** of the fields specified

* `findAll` searches for contacts that satisfy **ALL** the fields specified

Click [here](#57-finding-contacts-that-match-all-the-keywords-specified--findall) to learn more about findAll

</div>

<div style="page-break-after: always;"></div>

**Example:**

* `findAny findAny n/o p/87` Finds all contacts that have **either** the letter `o` in their name **or** 
the substring '87' in their phone number **or** both

The screenshot below shows the expected result.

![findAnyCommandExample](images/FindAnyCommandExample.png)

**Common issues:**

* _Unknown command_: <br>

  a. Using `findany` instead of `findAny` (not capitalising the 'A')

* _Invalid command format!_: <br>

  a. No fields provided<br>

  b. Used the wrong prefix (eg. '/n' instead of 'n/') <br>

  c. Did not specify either 't', 'f', 'true', 'false' after `c/` <br>

  d. Did not specify either 'm', 'f', 'male', 'female', 'n.a' after `g/` <br>

<div style="page-break-after: always;"></div>

### 5.7 Finding contacts that match **ALL** the keywords specified : `findAll`

Format: `findAll [n/NAME…​] [p/PHONE…​] [e/EMAIL…​] [a/ADDRESS…​] [g/GENDER…​] [age/AGE…​] [c/CALLED…​] [i/INTEREST…​]`

Finds only the contacts that match **all** the keywords specified in their respective fields

**Things to note:**

* `findAll` requires at least one field. But it is optional for you to include all the fields. <br>

* If there are duplicate fields, CMM will only take the right-most field <br>
  (e.g., `findAll n/alex n/david` returns the same results as `findAny n/david`)

* The search is case-insensitive. e.g., <br>
`findAll n/hans` will return the same result `findAll n/Hans` <br>

* Any number of keywords can be specified within each field <br>

* The order of the keywords within each field does not matter. <br>
  (e.g., `n/Hans Bo` will return the same result as `n/Bo Hans`)<br>

* Keywords for `Called` are limited to `true`, `false`, `t`, `f`

* Keywords for `Gender` are limited to `male`, `female`, `m`, `f`

* All the fields are case-insensitive

* You can refer to the example below for a better understanding

<div markdown="block" class="alert alert-primary"> 

:bulb: **Tip: FindAll vs FindAny** <br>

* `findAll` searches for contacts that satisfy **ALL** the fields specified.

* `findAny` searches for contacts that satisfy **ANY** of the fields specified.<br>

Click [here](#56-finding-contacts-that-match-any-of-the-keywords-specified-findany) to learn more about findAny

</div>

<div style="page-break-after: always;"></div>

**Example:**

* `findAll n/o p/87` Finds all contacts that have **both** the letter 'o' in their name and the substring '87' in their phone number

The screenshot below shows the expected result.

![findAllCommandExample](images/FindAllCommandExample.png)

**Common issues:**

* _Unknown command_: <br>

  a. Using `findall` instead of `findAll` (not capitalising the 'A')

* _Invalid command format!_: <br>

  a. No fields provided<br>

  b. Used the wrong prefix (e.g., '/n' instead of 'n/') <br>

  c. Did not specify either 't', 'f', 'true', 'false' after `c/` <br>

  d. Did not specify either 'm', 'f', 'male', 'female', 'n.a' after `g/` <br>

<div style="page-break-after: always;"></div>

### 5.8 Displaying full contact details : `display`

Displays additional information of a particular contact stored in the application. These include the
`Address`, `Gender`, `Age` and their `Interests`.

You can find the full details of the displayed contact in the Person Card (refer to the screenshot below).

![displayUi](images/displayUi.png)

Format: `display INDEX`

**Things to note:**

* `INDEX` refers to the index number shown in the displayed list

* `INDEX` **must be a positive integer** (e.g., 1, 2, 3, …​)

<div style="page-break-after: always;"></div>

**Example:**

* `display 3` displays full contact details of the 3rd contact in the displayed list

The screenshot below shows the expected result.

![displayCommandExample](images/DisplayCommandExample.png)

**Common issues:**

* _The index provided is invalid_: <br>

  a. Displayed list does not contain contact at `INDEX` <br>

  b. Index specified is larger than 2147483647 <br>

  c. Index specified is not a positive integer <br>
<div style="page-break-after: always;"></div>

### 5.9 Filtering contacts : `filter`

Filters the existing contacts in the CMM database. The displayed list will show contacts sorted by the chosen category.

Format: `filter CATEGORY [COUNT]`

**Things to note:**

* `CATEGORY` field is mandatory

* `CATEGORY` refers to the category used to filter the contacts

* `CATEGORY` **must be one of the following:** called, gender

* `COUNT` refers to the number of contacts to be shown in the displayed list. If `COUNT` is more than the total number
  of contacts, all contacts will be shown

* `COUNT` **must be a positive integer less than 2147483648** (e.g., 1, 2, 3, …, 2147483647 ​)

* If more arguments are given than what is required, the last two arguments are taken into account
  (e.g., `filter gender called 2` will be interpreted as `filter called 2`)

* If the last argument is not an integer, it will be interpreted as a `CATEGORY`
  (e.g., `filter gender called` will be interpreted as `filter called`)

* If you enter more than one category, the last category will be interpreted as the `CATEGORY`
  (e.g., `filter gender called 3` will be interpreted as `filter called 3`)

* If only one argument is given, it will be interpreted as a `CATEGORY`
  (e.g., `filter 1000` will cause `1000` to be interpreted as a `CATEGORY`)

<div style="page-break-after: always;"></div>

**Example:**

* `filter called 2` Filters the displayed list to prioritise uncalled contacts and limits the size of the list to 2

The screenshot below shows the expected result.

![filterCommandExample](images/FilterCommandExample.png)

**Common issues:**

* _Invalid command format!_: <br>

  a. No fields provided <br>

* _Category can only be either "called" or "gender"_: <br>

  a. The category specified is not `called` or `gender` <br>

  b. Category not specified

* _Count is not a non-zero unsigned integer. It cannot be bigger than 2147483647 (i.e., MAX_VALUE)_: <br>

  a. The last argument is not a positive integer <br>

  b. The last argument is bigger than 2147483647 <br>

### 5.10 Clearing all contacts : `clear`

Clears all contacts from the CMM database.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: 

**Caution:**
Clearing is irreversible, please use with caution.

</div>

### 5.11 Viewing help : `help`

Shows a message explaining how to access the User Guide.

![help message](images/helpMessage.png)

Format: `help`

### 5.12 Exiting the program : `exit`

Exits the program.

Format: `exit`

### 5.13 Saving the data

CMM data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 5.14 Importing the data into database

CMM is able to import Excel files into CMM. The import files have to be in an Excel Csv format (semicolon delimited).

**Steps to Import from Excel file:**

1. Ensure that the Excel file is a CSV file type that is **semicolon delimited**.

   Instructions on how to import to this file type can be found [here](SettingImportFileType.md).

2. Ensure that the import file is named import.csv under the "data" folder found in the same directory as the CMM jar file.

3. Upon CMM application startup, a prompt will popup with 3 options : `Add On Imports`, `Start New Using Import`, `Don't Import`. The popup will look like the image below:

![importPopUpUi](images/importPopUpMessage.png)

**Import options:**

`Add On Imports`

- Adds on new imports into existing database

`Start New Using Import`

- Exports and reset the current database. CMM will then populate the reset database with new imports

- Previous database **before import** can be found in a CSV file under the "data" folder found in the same directory as the CMM jar file. It will have the following file name : `export[Date HH:MM:SS].csv`

`Don't Import`

- CMM will not import anything and application will start normally

- Closing the prompt will also choose this option

<div markdown="span" class="alert alert-warning">:exclamation:

**Caution:**
Currently, import is **irreversible**. Please double-check before importing. This is especially important when choosing the add-on import option.
</div>

**Things to note:**

* CMM will prompt user for imports upon **every** application startup

* CMM will not import data rows with missing details (Compulsory for data to have `Name`, `Phone`, `Email` filled)

* CMM will treat two people with the exact `Name`, `Phone`, `Email`  as duplicates

* CMM will update duplicate imports **only when** `Called` status is true

* `Interest` column in the csv should be seperated using a comma **within the same cell**

* Import is only allowed during application startup. To import after the startup, simply reopen application to get the import prompt

* Unsuccessful file import will result in an empty database. The previous database can be recovered in the latest export file

**Common Issues:**

* _CSV file not found in data\import.csv_<br>

    * This could either mean that the file **does not exist** at the specified location or <br>
  
    * The file was **incorrectly named**. File has to be named `import.csv`
  
* _Missing/Extra Headers, Please check file_<br>

   * **Files has invalid headers** <br>
  
        * First row of Excel file is reserved for datatype headers. <br>
     
        * Headers must include `Name`, `Phone`, `Email`, `Address`, `Gender`, `Age`, `Interest` and `Called` from the left to right, starting from the cell 'A1' <br>
     
        * Headers are not case-sensitive<br>
     
   * File may have **incorrect CSV type**. Please refer to this [guide](SettingImportFileType.md)
   to correctly format your file.
  
* _Wrong header detected,please double check file_<br>

    * **Files has invalid headers**. Please refer to Common issue `Missing/Extra Headers, Please check file` to resolve
  
* Data not imported despite **correct import file placement, naming AND headers** <br>

    * File may not be in the correct CSV type. Please refer to this [guide](SettingImportFileType.md)
         to correctly format your file.
  
* Unable to understand why a specific row was not able to be imported <br>

    * Row could either be duplicate or there was an error reading the row
  
    * Detailed reasons for any import error can be found in the logs of the CMM

<div markdown="span" class="alert alert-primary">:bulb: 

**Tip:**
An example file import can be downloaded [here](https://github.com/AY2122S1-CS2103T-T13-4/tp/releases/tag/Test_Files)
</div>
    
### 5.15 Exporting state of database

CMM is able to export the current database as semicolon delimited CSV files. As such, there is no need for you to ensure that the formatting is right for future use.

**Steps to Export data as CSV file:**

1. Closing the application in any way would prompt user to export the current database. <br>
![exportPopup](images/exportPopup.png)

2. The CMM will export the database to the data file location.

3. Export file will have the following file name : `export[Date HH:MM:SS].csv` where date and time will follow your system settings.

**How to find the exported file:**

1. Go to the directory that the CMM jar file is stored in and click on the 'data' folder. <br>

    ![findingExport1](images/FindingExportFile1.png)

2. You can find the exported CSV file here. <br>

    ![findingExport2](images/FindingExportFile2.png)

<div style="page-break-after: always;"></div>

### 5.16 Editing the data file (JSON)

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: 

**Caution:**<br>
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder. 
Data found can be found at `[JAR file location]/data/addressbook.json`.

**Q**: How do I install Java for my computer<br>
**A**: Head over to [Installing Java](#71-installing-java) to find the installation guide.

**Q**: How do I know what version of Java I have running on my computer?<br>
**A**: Steps to checking your Java version can be found [here](#72-checking-java-version)
--------------------------------------------------------------------------------------------------------------------

## 7. Troubleshooting

### 7.1 Installing Java 

You can follow these guides to install Java 17:

* [Installing Java 17 for Windows](https://java.tutorials24x7.com/blog/how-to-install-java-17-on-windows)
* [Installing Java 17 for MacOS](https://java.tutorials24x7.com/blog/how-to-install-java-17-on-mac)
* [Installing Java 17 on Linux](https://java.tutorials24x7.com/blog/how-to-install-openjdk-17-on-ubuntu-20-04-lts)

### 7.2 Checking Java version

1. Open the Command line.

    * Windows: Type 'cmd' in the windows search bar and press enter
   
    * MacOS: Use the keyboard shortcut **Command** + **Space** to bring up the search bar, then type in 'terminal' and press **enter**
   
    * Linux: Use the keyboard shortcut **ctrl** + **alt** + **T**
    
    Click [here](https://towardsdatascience.com/a-quick-guide-to-using-command-line-terminal-96815b97b955) 
for other ways to open the Command line if the above shortcuts do not work for you.

    <div markdown="block" class="alert alert-info">

    **:information_source: Note:** The screenshots below use the Windows Command Line as examples. However, the Command Lines for 
    the other Operating Systems should be similar.
    </div>


   Once done, you should see an interface similar to the screenshot below. <br>

   ![Cli1](images/Cli1.png)
   <div style="page-break-after: always;"></div>

2. Enter the command 'java -version' and press enter.  <br>

   ![Cli2](images/Cli2.png)

3. The Java version will be displayed here. As long as you have a Java version higher than 11, CMM will work as expected.

   ![Cli3](images/Cli3.png)

    <div style="page-break-after: always;"></div>
    
4. If you get a response similar to the one below, it is likely that you do not have Java installed, or did not properly set up Java after installing. 
Head over to [Installing Java](#71-installing-java) to find the steps to easily fix this problem.

   ![Cli4](images/Cli4.png)

## 8. Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [g/GENDER] [age/AGE] [i/INTEREST]…​` <br> e.g., `add n/Labuschagne Ho p/22224444 e/labuschagne@example.com a/my house g/F age/95 i/Sleeping`
**List** | `list`
**Called** | `called INDEX` <br> e.g., `called 2`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [age/AGE] [c/CALLED] [i/(OPTIONAL INTERESTSLIST INDEX) INTEREST]... [i/(INTERESTSLIST INDEX) remove]... [i/INTEREST]...` <br/> e.g., `edit 1 n/Bob p/68889444 e/email@email.com a/his house g/M age/33 i/Eating i/(2) Swimming`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**FindAny** | `findAny [n/NAME…​] [p/PHONE…​] [e/EMAIL…​] [a/ADDRESS…​] [g/GENDER…​] [age/AGE…​] [d/DONE…​] [i/INTEREST…​]`<br> e.g., `findAny n/alex g/m`
**FindAll** | `findAll [n/NAME…​] [p/PHONE…​] [e/EMAIL…​] [a/ADDRESS…​] [g/GENDER…​] [age/AGE…​] [d/DONE…​] [i/INTEREST…​]`<br> e.g., `findAll n/alex g/m`
**Display** | `display INDEX` <br> e.g., `display 4`
**Filter** | `filter CATEGORY [COUNT]` <br> e.g., `filter gender 5`
**Clear** | `clear`
**Exit** | `exit`
**Help** | `help`


Thank you for reading the User Guide. Hope you have a pleasant experience with CallMeMaybe.


