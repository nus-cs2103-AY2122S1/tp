---
layout: page
title: User Guide
---

[Jump to Quick Start](#quick-start)

Welcome to Socius User Guide! 

Socius is a desktop application that can help you, as a student taking CS2103T Software Engineering, to manage your classmates’ contacts, so that you can have a easier time looking for teammates for group projects. This User Guide hopes to get you familarized with the commands of Socius and the flow of using the program so that you can get the most out of it.

We hope that you will have a great time using Socius! :)

Here are some example usages you can derive from Socius:
* Keep track of details of classmates who are also taking CS2103T
* Delete details of less acquainted classmates
* Tag classmates based on assigned project groups
* Filter classmates based on different project groups

<div markdown="block" class="alert alert-info">
Socius is optimized for use via a *Command Line Interface (CLI)* while still having the benefits of a *Graphical User Interface (GUI)*. If you can
type fast, Socius can get your contact management tasks done faster than traditional *GUI* apps.
</div>

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

* Table of Contents
{:toc}

<!---
## Table of Contents
1. Quick Start
2. Features
     1. Viewing help `help`
     2. Adding a person `add`
     3. Importing contacts from a file `import`
     4. Listing all persons `list`
     5. Editing a person `edit`
     6. Locating persons by name `find`
     7. Deleting a person `delete`
     8. Computing statistics of a tutorial group : `stats`
     9. Clearing all entries `clear`
     10. Exiting the program `exit`
3. Arguments constraints
3. FAQ
4. Command Summary
--->

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Before you can use the application, you need to have ***Java 11*** installed in your Computer.

1. Download the latest `Socius.jar` on our [Releases](https://github.com/AY2122S1-CS2103T-W08-4/tp/releases) page.

1. Move the file (`Socius.jar`) to a folder you want to use as the _home folder_ for your Socius Application.

1. Double-click the file to start the app. You should see the following screen similar to the one below appear within a few seconds. (If double-click does not work, you can go to the terminal and type in `java -jar Socius.jar`. Ensure that the terminal is in the directory of the `Socius.jar` file.)

   ![Ui](images/Ui.png)

* Note that the app comes with some sample data by default. Type **`clear`** in the command box to remove those sample data.

* You can refer to the next section on [Features](#features) for more details of each command. Alternatively, you can go to [Command Summary](#command-summary) for an overview of all commands.

* The following are some example of commands you can try. Type the command in the command box and press `⤷Enter` to execute it.

    * **`list`** : Lists all contacts.

    * **`add`** `n/John Doe tg/W08 nat/Singaporean` : Adds a contact named `John Doe` to Socius.

    * **`delete`** `3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpWindow.png)

Format: **`help`**

### Adding a person: `add`

Adds a person to the address book.

Format: **`add`** `n/NAME [p/PHONE_NUMBER] [e/EMAIL] [nat/NATIONALITY] [g/GENDER] [tg/TUTORIAL GROUP] [h/SOCIALHANDLE]…​ [r/REMARK] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The [Parameter Constraints](#parameter-constraints) section shows the constaints for each parameter.
</div>

* Only `n/NAME` field is *compulsory*, while other fields are *optional*.
* The order of the fields does not matter
* The format of `SOCIALHANDLE` is `PLATFORM:HANDLENAME` where `PLATFORM` is a two letter shorthand for the platform name (e.g. `ig` for Instagram, `tg` for Telegram) and `HANDLENAME` is the the identifying username for that platform.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0) and social handles (including 0)
</div>

Examples:

* **`add`** `n/Dwight Halpert` adds `Dwight Halpert` to the list.
* **`add`** `n/John Doe p/98765432 e/johnd@example.com g/M` adds `John Doe` to the list together with his information, such as his email and gender.
* **`add`** `n/Betsy Crowe t/friend e/betsycrowe@example.com p/1234567 tg/T07` adds `Betsy Crowe` to the list together with her information, such as her tag, email, phone number and tutorial group.
* **`add`** `n/Tan Ah Gao nat/Singapore h/tg:TanAhCow r/Funny Guy` adds `Tan Ah Gao` to the list together with his information, such as his nationality, Telegram handle and remark.
* **`add`** `n/Alex h/tg:alex3324 h/ig:alexxx` adds `Alex` to the list together with his information, such as his Telegram handle and his Instagram handle.

### Importing contacts from a file `import`

Imports and adds every person that is listed in the specified file.

Format: **`import`** `FILE_NAME.json`

* File must be in JSON format.
* File must be located in the `./data` directory in the same directory as `Socius.jar`.

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: **`list`**

### Editing a person : `edit`

Edits an existing person in the address book.

Format: **`edit`** `INDEX FIELD_PREFIX/VALUE [FIELD_PREFIX/VALUE]…​`

* `FIELD_PREFIX/VALUE` can be any of the following:
     * `n/NAME`
     * `g/GENDER`
     * `p/PHONE`
     * `e/EMAIL`
     * `nat/NATIONALITY`
     * `tg/TUTORIAL_GROUP`
     * `h/SOCIAL_HANDLE`
     * `r/REMARK`
     * `t/TAG`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.
* If duplicated fields are given, only the latest one will be taken (except for `h/SOCIAL_HANDLE` and `t/TAG`)
* When editing tags, the existing tags of the person will be removed (i.e adding of tags is not cumulative).
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:

* **`edit`** `1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567`
  and `johndoe@example.com` respectively.
* **`edit`** `2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds a person or a list of persons using `KEYWORD`.

Format: **`find`** `FIELD_PREFIX/KEYWORD [FIELD_PREFIX/KEYWORD]…​`

* `FIELD_PREFIX/KEYWORD` can be any of the following:
     * `n/NAME`
     * `g/GENDER`
     * `p/PHONE`
     * `e/EMAIL`
     * `nat/NATIONALITY`
     * `tg/TUTORIAL_GROUP`
     * `h/SOCIAL_HANDLE`
     * `r/REMARK`
     * `t/TAG`
* The search is case-insensitive. (e.g `hans` will match `Hans`)
* As long as `KEYWORD` is part of the actual value, it will be matched. (e.g. `A` will match `Alex`, and `la` will match `Alan`)
* Persons matching at least one keyword will be returned (i.e. `OR` search). (e.g. `n/Hans n/Bo` will
  return `Hans Gruber`, `Bo Yang`)

Examples:

* **`find`** `n/John` returns `john` and `John Doe`.
* **`find`** `n/alex n/david` returns `Alex Yeoh`, `David Li`.<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* **`find`** `nat/Singapore` <br>
  ![result for 'find nat/Singapore'](images/findNatSingapore.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: **`delete`** `INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* **`list`** followed by **`delete`** `2` deletes the 2nd person in the address book.
* **`find`** `Betsy` followed by **`delete`** `1` deletes the 1st person in the results of the **`find`** command.

### Computing statistics of a tutorial group : `stats`

Computes the gender and nationality statistics of the specified tutorial group.

Format: **`stats`** `TUTORIAL_GROUP`

* If there are no tutorial groups with the given name/number, it will notify the user.

Examples:

* **`stats`** `T09` computes the gender and nationality statistics of the tutorial group `T09`.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: **`clear`**

### Exiting the program : `exit`

Exits the program.

Format: **`exit`**

--------------------------------------------------------------------------------------------------------------------

## Parameter Constraints

### `n/NAME`
`NAME` should only contain alphanumeric characters and spaces.
(e.g. `n/Zayden Tan Bee Hoon`)

### `g/GENDER`
`GENDER` can be `M` for male, `F` for female or `O` for others.
(e.g. `g/M`)

### `p/PHONE`
`PHONE` should only contain numbers, and it should be at least 3 digits long.
(e.g. `98739283`)

### `e/EMAIL`
`EMAIL` should be of the format `local-part@domain` and adhere to the following constraints:
* The local-part should only contain alphanumeric characters and these special characters: `+_.-`.
* The local-part may not start or end with any special characters.
* The domain name is made up of domain labels separated by periods.
* The domain name must:
     * end with a domain label at least 2 characters long
     * have each domain label start and end with alphanumeric characters
     * have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
 
(e.g. `e/e3029834@u.nus.edu`)

### `nat/NATIONALITY`
`NATIONALITY` can take any values.
(e.g. `nat/Singaporean`)

### `tg/TUTORIAL_GROUP`
`TUTORIAL_GROUP` should only contain one letter that is either M/T/W/F followed by two digits.
(e.g. `tg/T09`)

### `h/SOCIAL_HANDLE`
`SOCIAL_HANDLE` should be of the format `platform:username` and adhere to the following constraints:
* The platform should only contain the 2 letter shorthand for the social platform:
     * `ig` for Instagram
     * `tg` for Telegram
     * `fb` for Facebook
     * `tw` for Twitter
     * `gh` for Github
     * `ln` for Linkedin
* The username should only contain alphanumeric characters

(e.g. `h/tg:alexx9384`)

### `r/REMARK`
`REMARK` can take any values.
(e.g. `r/Stays in PGP`)

### `t/TAG`
`TAG` names should be alphanumeric
(e.g. `t/friends`)
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can change the default colour of the tag with `t/TAG:#HEX_COLOUR` (e.g. `t/friends:#FF0000` for a red colour tag)
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I save the data?<br>
**A**: Socius data are automatically saved to the computer after any command that changes the data. Therefore, there is no need to save manually. The data will be automatically loaded back into the application the next time you use it.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Socius home folder.

**Q**: Can I edit the data file directly?<br>
**A**: Socius data are saved as a JSON file (i.e. `JAR_FILE_LOCATION/data/addressbook.json`). Advanced users are welcome to update data directly by editing that data file. However, be warned that changes that makes the data format invalid will cause Socius to discard the data file and start with a new empty data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Socius will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command Summary

|Action | Format | Examples|
|--------|------------------|------------------------------------|
|**Help** | **`help`** | **`help`** |
|**Add** | **`add`** `n/NAME [p/PHONE_NUMBER] [e/EMAIL] [nat/NATIONALITY] [g/GENDER] [tg/TUTORIAL GROUP] [h/SOCIALHANDLE]…​ [r/REMARK] [t/TAG]…​`| **`add`** `n/James Ho p/22224444 e/jamesho@example.com g/M tg/12 h/tg:friendlyjames r/Friendly t/colleague`|
|**Import** | **`import`** | **`import`** 'addressbook.json'|
|**List** | **`list`** | **`list`** |
|**Edit** | **`edit`** `INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [nat/NATIONALITY] [g/GENDER] [tg/TUTORIAL GROUP] [h/SOCIALHANDLE]…​ [r/REMARK] [t/TAG]…​` | **`edit`** `2 n/James Lee e/jameslee@example.com` |
|**Find** | **`find`** `FIELD_PREFIX/KEYWORD [FIELD_PREFIX/KEYWORD]…​` | **`find`** `g/F tg/07` |
|**Delete** | **`delete`** `INDEX` | **`delete`** `3`|
|**Stats** | **`stats`** `TUTORIAL_GROUP` | **`stats`** `T09`|
|**Clear** | **`clear`** | **`clear`** |
|**Exit** | **`exit`** | **`exit`** |
