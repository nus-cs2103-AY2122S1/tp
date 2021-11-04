---
layout: page
title: User Guide
---

### About RecruitIn

RecruitIn is a desktop app for recruiters in Singapore to keep track of the plethora of clients with different skill sets, availability and experience. It is optimised for quick text-based inputs via a Command Line Interface (CLI) while still having the ease of use of a Graphical User Interface (GUI). This product will make recruiters’ lives easier through categorisation and filter features to easily access candidates they have in mind.
![Ui](images/Ui.png)
### About this guide

This guide aims to help users get familiar with using RecruitIn's features.
* **New users** can get started by following the steps under [Quick start](#quick-start). New users may also view
descriptions of the usage of each component in RecruitIn under [Usages](#usages)
* **Existing users** can view existing features under [Features](#features) or refer to the [Table of Contents](#table-of-contents) below to view specific features. A **summary** of existing features can also be viewed under [Command summary](#command-summary).
* **Advanced users** can view in-depth usage of RecruitIn's features by visiting links marked with ***{Advanced}*** under the [Table of Contents](#table-of-contents).
* Commonly addressed questions can be viewed under [FAQ](#faq). 

### Table of Contents
  * [Quick start](#quick-start)
  * [Usages](#usages)
  * [Features](#features)
    + [Viewing help : `help`](#viewing-help--help)
    + [Adding an applicant: `add`](#adding-an-applicant-add)
    + [Editing an applicant: `edit`](#editing-an-applicant--edit)
    + [Listing all applicants : `list`](#listing-all-applicants--list)
    + [Finding an applicant : `find`](#finding-an-applicant--find)
    + [Deleting an applicant : `delete`](#deleting-an-applicant--delete)
    + [Showing search terms : `show`](#showing-search-terms--show)
    + [Marking an applicant : `mark`](#marking-an-applicant--mark)
    + [Unmarking an applicant : `unmark`](#unmarking-an-applicant--unmark)
    + [Deleting marked applicants: `delete_marked`](#deleting-marked-applicants-delete_marked)
    + [Exiting the program : `exit`](#exiting-the-program--exit)
    + [Saving the data](#saving-the-data)
    + [Editing the data file](#editing-the-data-file)
  * [Prefix Input Specifications ***{Advanced}***](#prefix-input-specifications-advanced)
  * [FAQ](#faq)
  * [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. If not, you can install it from [here](https://www.oracle.com/java/technologies/downloads/).
Download the correct version based on your operating system (e.g. Linux, macOS, Windows) and follow the Java installation instructions.

2. Download the latest `recruitIn.jar` from [here](https://github.com/AY2122S1-CS2103T-F11-2/tp/releases/tag/v1.3b).
Scroll down to the bottom and click on `recruitIn.jar`.

3. Copy the file to the folder you want to use as the _home folder_.

4. Double-click the file to start the app. The GUI should be similar to the below image. Note how the app contains some sample data.<br>
   ![Ui](images/description.png)
   
   Applicant data are displayed as a list in the **Applicant Panel**.
   ![Applicant Diagram](images/ApplicantDiagram.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   
   * **`clear`** : Clears all sample data.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com r/Teacher et/Full time s/3000 l/Bachelors y/4` : 
     Adds an applicant named `John Doe` to RecruitIn, where `98765432`is his phone number, `johnd@example.com` is his email, 
     `Teacher` is the role he applied for, `Full time` is his desired employment type, `3000` is his expected salary,
     `Bachelors` is his highest level of education, and he has `4` years of experience.
    
   * **`add`**`n/Mary Poppins p/91131513 e/maryp@example.com r/Receptionist et/Temporary s/3000 l/High School y/2` : 
     Adds an applicant named `Mary Poppins` to RecruitIn, where `91131513`is her phone number, `maryp@example.com` is her email, 
     `Receptionist` is the role she applied for, `Temporary` is her desired employment type, `3000` is her expected salary, 
     `High School` is her highest level of education, and she has `2` years of experience.
    
   * **`list`** : Lists all applicants.
   
   * **`find`**`n/John Mary` : Finds all current applicants in RecruitIn whose names are `John` or `Mary`.
   
   * **`delete`**`2` : Deletes `Mary`, who is the 2nd applicant in the list. After deletion, the list of all applicants will contain only 'John'.

   * **`show`**`s/` : Displays all unique expected salaries currently in RecruitIn, which would be 1 result that is `3000`.

   * **`mark`**`1` : Marks the 1st applicant shown in the list of all applicants as done, which would be `John`.

   * **`unmark`**`1` : Unmarks the 1st applicant shown in the list of all applicants, which would be `John`.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Words that are ***bold & Italicised*** refers to a stored prefix value. 
  e.g. ***Name*** could refer to the value `John` stored as a name in the application.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be repeated multiple times, including zero times.<br>
  e.g. `delete INDEX...` can be used as `delete 1` (i.e. `INDEX` repeated 0 times), `delete 1 2`, `delete 2 4 3` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


<div markdown="block" class="alert alert-primary">

**:information_source: Notes about the applicants:**<br>

* Duplicate applicants are not allowed. For two applicants to be duplicate, they must have either the same ***Phone Number*** or ***Email*** or both. <br>
  An error message will show if you attempt to `add` or `edit` applicants in a manner that will lead to duplicate stored applicants.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an applicant: `add`

Adds an applicant to RecruitIn.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL_ADDRESS r/ROLE et/EMPLOYMENT_TYPE s/EXPECTED_SALARY l/LEVEL_OF_EDUCATION y/YEARS_OF_EXPERIENCE [t/TAG] [i/INTERVIEW] [nt/NOTES]​`

* To add multiple tags, multiple `t/` prefixes should be used.
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Providing multiple tag values in a single `t/` prefix will lead to an error. (i.e. `add n/John p/90909090 e/john@gmail.com r/Software Tester et/Full time s/4500 l/High School y/3 t/smart helpful` will lead to an error)
</div>
* Inputs for each prefix is taken as a single value. (i.e. `r/software engineer` has the value `software engineer`)

Examples:
* `add n/Bob p/87654321 e/bob@gmail.com r/Software Engineering et/Full time s/4000 l/High School y/2 i/2021-10-21, 20:00 nt/This applicant has the credentials needed for this job.`
* `add n/John p/90909090 e/john@gmail.com r/Software Tester et/Full time s/4500 l/High School y/3 t/smart t/helpful` to add a person named `John` with two tags `smart` and `helpful`

<div markdown="block" class="alert alert-secondary">
**:information_source: Prefix inputs for `add` command must follow the following input specifications:**<br>

* You may
    * refer to [**Add Input Specifications**](#add-inputs) for detailed input specifications.
    * refer to add input specifications for specific prefixes by clicking on relevant links in the table below.

| Input | Prefix | Specifications |
| :---: | :---: | :---: |
| NAME | `n/` | [**name**](#name-n) |
| PHONE_NUMBER | `p/` | [**phone_number**](#phone_number-p) |
| EMAIL_ADDRESS | `e/` | [**email_address**](#email_address-e) |
| ROLE | `r/` | [**role**](#role-r) |
| EMPLOYMENT_TYPE | `et/` | [**employment_type**](#employment_type-et) |
| EXPECTED_SALARY | `s/` | [**expected_salary**](#expected_salary-s) |
| LEVEL_OF_EDUCATION | `l/` | [**level_of_education**](#level_of_education-l) |
| YEARS_OF_EXPERIENCE | `y/` | [**years_of_experience**](#years_of_experience-y) |
| TAG | `t/` | [**tag**](#tag-t) |
| INTERVIEW | `i/` | [**interview**](#interview-i) |
| NOTES | `nt/` | [**notes**](#notes-nt) |

</div>

### Editing an applicant : `edit`

Edits an applicant's with specified index in RecruitIn.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL_ADDRESS] [r/ROLE] [et/EMPLOYMENT_TYPE]
 [s/EXPECTED_SALARY] [l/LEVEL_OF_EDUCATION] [y/YEARS_OF_EXPERIENCE] [t/TAG] [i/INTERVIEW] [nt/NOTES]`
 
 * Edit command must take at least 1 prefix input.
 * The `INDEX` refers to the index number shown in the displayed applicants list.
 * For `t/` prefix in particular, if **only** a single tag prefix is provided like so `t/` with no values, it will erase
remove tags from the applicant.
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Giving more than 1 tag prefix input with 1 or more having no value will instead lead to an error. (i.e. `edit 1 t/ t/smart` leads to an error)
</div>
 * Inputs for each prefix is taken as a single value. (i.e. `r/software engineer` has the value `software engineer`)

Examples:
* `edit 1 r/Software Engineer` will change the ***role*** of the applicant with the index number 1
* `edit 1 t/` will remove all ***tags***s from the applicant with index number 1
* `edit 1 n/John t/` will change the name of the applicant with index number 1 to `John` and remove all the applicant's ***tag***s

<div markdown="block" class="alert alert-secondary">
**:information_source: Prefix inputs for `edit` command must follow the same input specifications as `add` command:**<br>

* You may 
  * refer to [**Add Input Specifications**](#add-inputs) for detailed input specifications.
  * refer to add input specifications for specific prefixes by clicking on relevant links in the table below.

| Input | Prefix | Specifications |
| :---: | :---: | :---: |
| NAME | `n/` | [**name**](#name-n) |
| PHONE_NUMBER | `p/` | [**phone_number**](#phone_number-p) |
| EMAIL_ADDRESS | `e/` | [**email_address**](#email_address-e) |
| ROLE | `r/` | [**role**](#role-r) |
| EMPLOYMENT_TYPE | `et/` | [**employment_type**](#employment_type-et) |
| EXPECTED_SALARY | `s/` | [**expected_salary**](#expected_salary-s) |
| LEVEL_OF_EDUCATION | `l/` | [**level_of_education**](#level_of_education-l) |
| YEARS_OF_EXPERIENCE | `y/` | [**years_of_experience**](#years_of_experience-y) |
| TAG | `t/` | [**tag**](#tag-t) |
| INTERVIEW | `i/` | [**interview**](#interview-i) |
| NOTES | `nt/` | [**notes**](#notes-nt) |

</div>

### Listing all applicants : `list`

Shows a list of all applicants in RecruitIn.

Format: `list`

### Finding an applicant : `find`

Finds applicants by specific prefixes.

Format: `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL_ADDRESS] [r/ROLE] [et/EMPLOYMENT_TYPE] [s/EXPECTED_SALARY] [l/LEVEL_OF_EDUCATION] [y/YEARS_OF_EXPERIENCE]  [t/TAG]  [i/INTERVIEW] [nt/NOTES] [d/DONE]`

* Find command must take **at least 1** prefix input.
* If you input multiple of the same prefix, **only the last** prefix will be used for the search of that category.
* Input for each prefix can contain multiple **keywords** separated by whitespace, e.g. `n/John Mary`, `t/friend colleague`

<div markdown="block" class="alert alert-warning">

* You can search for tags using multiple keywords by providing your keywords separated by whitespace in a `t/` prefix. (i.e. `find t/smart kind`)
    * This is different from `add` and `edit` commands which require tag inputs to be provided separately. (i.e. `edit 1 t/smart t/kind`)

</div>

* Inputs for all prefixes are **case-insensitive**.

<div markdown="block" class="alert alert-warning">

* Applicants will not be filtered by prefixes that are not provided.
* Applicants will also not be filtered by prefixes that are provided, yet have empty inputs. (i.e. `find d/` will return all applicants unfiltered by their ***done*** status)

</div>

Examples:
* `find n/John Mary` finds all applicants with either `John` or `Mary` as values for name prefix.
* `find n/John n/Mary` finds all applicants with only `Mary` as values for name prefix.
* `find t/friend colleague` finds all applicants with `friend` or `colleague` as values for tag prefix.
* `find n/John Mary t/friend colleague`
* `find n/Bob p/87654321 e/bob@gmail.com r/Software Engineering et/Full time s/4000 l/High School y/2 nt/has the credentials d/Not Done`

<div markdown="block" class="alert alert-dark">
**:information_source: Prefix inputs for `find` command must follow the following input specifications:**<br>

* You may
    * refer to [**Find Input Specifications**](#find-inputs) for detailed input specifications.
    * refer to find input specifications for specific prefixes by clicking on relevant links in the table below.

| Input | Prefix | Specifications |
| :---: | :---: | :---: |
| NAME | `n/` | [**name**](#name-n-1) |
| PHONE_NUMBER | `p/` | [**phone_number**](#phone_number-p-1) |
| EMAIL_ADDRESS | `e/` | [**email_address**](#email_address-e-1) |
| ROLE | `r/` | [**role**](#role-r-1) |
| EMPLOYMENT_TYPE | `et/` | [**employment_type**](#employment_type-et-1) |
| EXPECTED_SALARY | `s/` | [**expected_salary**](#expected_salary-s-1) |
| LEVEL_OF_EDUCATION | `l/` | [**level_of_education**](#level_of_education-l-1) |
| YEARS_OF_EXPERIENCE | `y/` | [**years_of_experience**](#years_of_experience-y-1) |
| TAG | `t/` | [**tag**](#tag-t-1) |
| INTERVIEW | `i/` | [**interview**](#interview-i-1) |
| NOTES | `nt/` | [**notes**](#notes-nt-1) |
| DONE | `d/` | [**done**](#done-d) |

</div>
        
### Filtering interviews : `filter_interview`
Filters applicants by interview dates based on whether they are upcoming or in the past (based on current date and time).
Differs from ```find i/``` as it is not returning all applicants with interviews matching a specific date but 
rather all applicants with interviews that fall into the same group (`past` or `future`).

Format: `filter_interview past` or `filter_interview future`

* `filter_interview past` returns applicants with interviews that were in the past (compared to the current date and time).
* `filter_interview future` returns applicants with interviews that are coming up in the future (compared to the current date and time).
* Either `past` or `future` must be given as an input after `filter_interview`, and is case-insensitive.
* Only one input must be given (`filter_interview past future` will not work).

Examples:
* If the current date and time is 29th October 2021, 1600, then `filter_interview past` or 
 `filter_interview pAsT` will return applicants with interviews that were before and including
  29th October 2021, 1600.

* If the current date and time is 29th October 2021, 1600, then `filter_interview future` or 
 `filter_interview fUTURE` will return applicants with interviews that are after 29th October 2021, 1600.

### Deleting an applicant : `delete`

Deletes applicants by their index from the list in RecruitIn.

Format: `delete INDEX...`

* Deletes the applicant at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed applicants list.
* At least one `INDEX` must be given. (i.e. `delete ` is not a valid command)
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* `INDEX` uses **1-based indexing**.
* Duplicate `INDEX`s are not allowed. (i.e. `delete 2 2` is not a valid command)
* `INDEX` should not exceed the total number of applicants in the displayed applicants list.

Examples:
* After using the command `list`, `delete 1` deletes the 1st applicant listed in RecruitIn.
* After using the command `find n/John`, `delete 1` deletes the 1st applicant in the results of the `find` command.
* After using the command `list`, `delete 2 4 7` deletes the 2nd, 4th and 7th applicants listed in RecruitIn.

### Showing search terms : `show`

Shows unique search terms available for a specific prefix.

Format: `show [n/] [p/] [e/] [r/] [et/] [s/] [l/] [y/] [t/]`

* Finds and displays a list of unique search terms available for a specific prefix.
* **At least 1** prefix must be given.
* Unique search terms will only be found for the **first** prefix input.

Examples:

Suppose the applicant data includes 3 applicants with name and role `John, Software Developer`, `Mary, Cook` and `Mary, Cleaner`.
* `show n/` will display a list consisting of `John` and `Mary`.
* `show r/ n/` will display a list consisting of `Software Developer`, `Cleaner` and `Cook`.

### Marking an applicant : `mark`

Marks the specified applicant by index from the list in RecruitIn as "Done" (have been attended to).

Format: `mark INDEX…​`

* Marks the applicant at the specified `INDEX` as "Done".
* An applicant that is has status "Done" cannot be marked again.
* The `INDEX` refers to the index number shown in the displayed applicants list.
* At least one `INDEX` must be given. (i.e. `mark ` is not a valid command)
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* `INDEX` uses **1-based indexing**.
* Duplicate `INDEX`s are not allowed. (i.e. `mark 2 2` is not a valid command)
* `INDEX` should not exceed the total number of applicants in the displayed applicants list.

Examples:
* After using the command `list`, `mark 2` marks the 2nd applicant listed in RecruitIn as "Done".
* After using the command `find n/John`, `mark 1` marks the 1st applicant in the results of the `find` command.
* After using the command `list`, `mark 2 4 6` marks the 2nd, 4th and 6th applicant listed in RecruitIn as "Done".

### Unmarking an applicant : `unmark`

Unmarks the specified applicant by index from the list in RecruitIn to "Not Done" (have not been attended to).

Format: `unmark INDEX…​`

* Unmarks the applicant at the specified `INDEX` to "Not Done".
* An applicant that is has status "Not Done" cannot be unmarked again.
* The `INDEX` refers to the index number shown in the displayed applicants list.
* At least one `INDEX` must be given. (i.e. `unmark ` is not a valid command)
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* `INDEX` uses **1-based indexing**.
* Duplicate `INDEX`s are not allowed. (i.e. `delete 2 2` is not a valid command)
* `INDEX` should not exceed the total number of applicants in the displayed applicants list.

Examples:
* After using the command `list`, `ummark 2` unmarks the 2nd applicant listed in RecruitIn to "Not Done".
* After using the command `find n/John`, `unmark 1` unmarks the 1st applicant in the results of the `find` command.
* After using the command `list`, `unmark 2 4 6` unmarks the 2nd, 4th and 6th applicant listed in RecruitIn to "Not Done".

### Deleting marked applicants: `delete_marked`

Deletes all applicants that are marked as done.

Format: `delete_marked`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

RecruitIn data is saved to the hard disk whenever there is a command that edits, updates or adds data.
There is no need to save data manually with a command. Data also automatically loads when the application runs.

### Editing the data file

RecruitIn data is saved as a String in `/data/applicants.json` for applicant data.
Advanced users are welcome to update data directly by editing that data file.

Example of format of data for one applicant in applicants:

```JSON
{
  "persons": [
    {
      "name" : "Alice Yeoh",
      "phone" : "87438807",
      "email" : "alexyeoh@example.com",
      "role" : "Software Engineering",
      "employmentType" : "Full time",
      "expectedSalary" : "3600",
      "levelOfEducation" : "High School",
      "experience" : "2",
      "tagged" : [],
      "interview" : "-",
      "notes" : "This applicant is a suitable candidate for the job!",
      "done": "Not Done"
    }
  ]
}
```
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RecruitIn will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Prefix Input Specifications ***{Advanced}***

**Note**: **Alphanumeric** characters refers specifically to characters a-z, A-Z and 0-9.

### Add Inputs

<div markdown="block" class="alert alert-secondary">

* Return to [**Add**](#adding-an-applicant-add).
* Return to [**Edit**](#editing-an-applicant--edit).

* ##### NAME `n/`
    * A NAME should only contain alphanumeric characters. Spaces between words are allowed.
    * For example:
        * NAME inputs such as `John`, `Mary Sue` and `9ine 6ix` are acceptable.
        * NAME inputs such as `J@hn`, `Mary S^e` and `B{}b` are not acceptable.
* ##### PHONE_NUMBER `p/`
    * A PHONE_NUMBER should contain a minimum of 3 digits. No characters other than the digits 0-9 are allowed.
    * For example:
        * PHONE_NUMBER inputs such as `99999999` and `999` are acceptable.
        * PHONE_NUMBER inputs such as `9999 9999` and `88` are not acceptable.
* ##### EMAIL_ADDRESS `e/`
    * An EMAIL_ADDRESS should contain a **local part** and a **domain part**, separated by an `@` character.
    * The **local part**:
        * must contain **at least 1** alphanumeric character.
        * It can contain alphanumeric characters separated by any 1 of these characters `+_.-`. (i.e. `John-a-bc`)
        * It must **start with** and **end with** an alphanumeric character.
    * The **domain part**:
        * must contain **at least 2** domain labels. Each domain label, **except the final domain label**, must start with an
          alphanumeric character and end with a `.`.
        * The final domain label must have **at least 2** alphanumeric characters, but does not need to end with
          a `.` unlike its preceding domain labels. (i.e. `John@u.sg` is valid)
        * The domain label can contain alphanumeric characters separated by `-`. (i.e. `John@u-u.sg`)
    * For example:
        * EMAIL_ADDRESS inputs such as `PeterJack_1190@example.com` and `e1234567@u.nus.edu` are acceptable.
        * EMAIL_ADDRESS inputs such as `peterjack@example.c` and `peter..jack@example.com` are unacceptable.
* ##### ROLE `r/`
    * A ROLE should only contain **alphanumeric** characters. Spaces between words are allowed.
    * For example:
        * ROLE inputs such as `Software Engineer` and `Sales Assistant` are acceptable.
        * ROLE inputs such as `Softw@re Engin^^r` and `Day + Night Security Guard` are not acceptable.
* ##### EMPLOYMENT_TYPE `et/`
    * An EMPLOYMENT_TYPE should be one of the following: `Full time`, `Part time`, `Temporary` or `Internship`.
    * An EMPLOYMENT_TYPE is **case-insensitive**.
    * For example:
        * EMPLOYMENT_TYPE inputs such as `Full time` and `Internship` are acceptable.
        * EMPLOYMENT_TYPE inputs such as `fUlL tiMe` and `iNtErnShIP` are acceptable.
        * EMPLOYMENT_TYPE inputs such as `Long term` are not acceptable.
* ##### EXPECTED_SALARY `s/`
    * An EXPECTED_SALARY should only represent non-negative integers.
        * Non-negative integers range from 0 to 2^(31) - 1 inclusive.
    * For example:
        * EXPECTED_SALARY inputs such as `0` and `3500` are acceptable.
        * EXPECTED_SALARY inputs such as `-600` and `~350` are not acceptable.
* ##### LEVEL_OF_EDUCATION `l/`
    * A LEVEL_OF_EDUCATION should be one of the following: `Elementary`, `Middle School`, `High School`, `University`, `Bachelors`, `Masters` or `PhD`.
    * A LEVEL_OF_EDUCATION is **case-insensitive**.
    * For example:
        * LEVEL_OF_EDUCATION inputs such as `Middle School` and `PhD` are acceptable.
        * LEVEL_OF_EDUCATION inputs such as `miDDlE scHoOL` and `phD` are acceptable.
        * LEVEL_OF_EDUCATION inputs such as `Kindergarten` are not acceptable.
* ##### YEARS_OF_EXPERIENCE `y/`
    * A YEARS_OF_EXPERIENCE should be a **non-negative number** with intervals of **0.5** and smaller than or equals to **67** (re-employment age in Singapore).
    * For example:
        * YEARS_OF_EXPERIENCE inputs such as `0` and `10` are acceptable.
        * YEARS_OF_EXPERIENCE inputs such as `0.5` and `3.0` are acceptable.
        * YEARS_OF_EXPERIENCE inputs such as `-1`, `3.1`, and `100` are not acceptable.
* ##### TAG `t/`
    * A TAG should only contain alphanumeric characters. Spaces between words are **not** allowed.
    * For example:
        * TAG inputs such as `friends` and `colleagues` are allowed.
        * TAG inputs such as `best friends`, `old colleagues` and `seni@r` are not allowed.
* ##### INTERVIEW `i/`
    * An INTERVIEW should be on a legitimate date and follows the DateTime format `yyyy-M-d, H:m`. (Year should be strictly 4-digit while others can be either 1 or 2 digit)
    * For example:
        * INTERVIEW inputs such as `2021-08-09, 13:00` and `2022-1-3, 3:00` are acceptable.
        * INTERVIEW inputs such as `morning`, `2021.10.21`, `2021-10-22 13:00` and `2021-02-30, 10:30` (not an existing date) are not acceptable.
* ##### NOTES `nt/`
    * A NOTES can contain any character, number or symbol as there are no restrictions in place.
    * For example:
        * NOTES inputs such as `This candidate is good!` and `@Applicant123 is suitab13 for th3 job!` are acceptable.

</div>

### Find Inputs

<div markdown="block" class="alert alert-dark">

* Return to [**Find**](#finding-an-applicant--find)

* ##### NAME `n/`
    * A NAME is considered matching with a ***Name*** only if **at least 1** keyword is equal to **at least 1** word in the ***Name***.
    * All keywords provided as NAME input must comply with input specifications for add given [**here**](#name-n).
    * For example:
        * A `John` input can match with ***Name*** s such as `John Tan` or `John Lee`.
        * A `John Mary` input can match with ***Name*** s such as `Mary John`, `Mary Lee` or `Long John`.

* ##### PHONE_NUMBER `p/`
    * A PHONE_NUMBER is considered matching with a ***Contact Number*** only if **at least 1** keyword is equal to **at least 1** word in the ***Contact Number***
    * All keywords provided as PHONE_NUMBER input must comply with input specifications for add given [**here**](#phone_number-p).
    * For example:
        * A `99999999` input can only match with ***Contact Number*** s that are `99999999`.
        * A `99999999 88888888` input can only match with ***Contact Number*** s that are `99999999` and `88888888`.

* ##### EMAIL_ADDRESS `e/`
    * An EMAIL_ADDRESS is considered matching with an ***Email Address*** only if **at least 1** keyword is equal to **at least 1** word in the ***Email Address***.
    * All keywords provided as EMAIL_ADDRESS input must comply with input specifications for add given [**here**](#email_address-e).
    * For example:
        * A `alexyeoh@example.com` input can match with ***Email*** s such as `alexyeoh@example.com`.
        * A `alexyeoh@example.com marysue@gmail.com` input can match with ***Email*** s such as `alexyeoh@example.com`
          and `marysue@gmail.com`.

* ##### ROLE `r/`
    * A ROLE is considered matching with a ***Role*** only if **each of every** provided keyword is equal to **at least 1** word in the ***Role***.
    * All keywords provided as ROLE input must comply with input specifications for add given [**here**](#role-r).
    * For example:
        * A `Software` input can match with ***Role*** s such as `Software Engineer`, `Software` or `Software Developer`
        * A `Software Engineer` input can match with ***Role*** s such as `Software Engineer` or `Senior Software Engineer`
          but not with ***Role*** s such as `Software` or `Software Developer`.

* ##### EMPLOYMENT_TYPE `et/`
    * An EMPLOYMENT_TYPE is considered matching with an ***Employment Type*** only if it **starts with any** of the keywords in the ***Employment Type***.
    * All keywords provided as EMPLOYMENT_TYPE input must comply with input specifications for add given [**here**](#employment_type-et).
    * For example:
        * A `Full time` or `full time` or `full` input will match only with ***Employment Type*** s that are ```Full time```
        * A ```Full part``` input will match with all ***Employment Type*** s that are ```Full time``` or ```Part time```
        * A ```temp Intern``` input will match with all ***Employment Type*** s that are ```Temporary``` or ```Internship```
        * A ```full time bob``` input will throw an exception as ```bob``` is not a term any of the ***Employment Type*** s start
          with.

* ##### EXPECTED_SALARY `s/`
    * An EXPECTED_SALARY is considered matching with a ***Expected Salary*** only if **at least 1** keyword is within a range of `500` from **at least 1** keyword in the ***Expected Salary***.
    * All keywords provided as EXPECTED_SALARY input must comply with input specifications for add given [**here**](#expected_salary-s).
    * For example:
        * A `3000` input can match with ***Expected Salary*** s that range from `2500` to `3500` inclusive.
        * A `2500 5000` input can match with ***Expected Salary*** s from the ranges `2000` to `3000` inclusive, and `4500` to `5500` inclusive.

* ##### LEVEL_OF_EDUCATION `l/`
    * A LEVEL_OF_EDUCATION is considered matching with a ***Level of Education*** only if it **starts with any** of the keywords in the ***Level of Education***.    
    * All keywords provided as LEVEL_OF_EDUCATION input must comply with input specifications for add given [**here**](#level_of_education-l).
    * For example:
        * A `H` input can match with ***Level of Education***s such `High School`, but not with *Level of Education*s such as `PhD`.
        * A `High School` input will match with all *Level of Education*s that are `High School`, but not with *Level of Education*s such as `Middle School`.
        * A `High Middle` input will match with all *Level of Education*s that are `High School` and `Middle School`.
        * A `High School bob` input is invalid as `bob` is not a term any of the *Level of Education*s start with.

* ##### YEARS_OF_EXPERIENCE `y/`
    * A YEARS_OF_EXPERIENCE is considered matching with a ***Years Of Experience*** only if the value represented by **at least 1** keyword is larger than or equal to the value represented by the ***Years Of Experience***.
    * All keywords provided as YEARS_OF_EXPERIENCE input must comply with input specifications for add given [**here**](#years_of_experience-y).
    * For example:
        * A `3` input can match with ***Year Of Experience*** s that are higher than or equal to 3.
        * A `2 3` input can match with ***Year Of Experience*** s that are higher than or equal to 2.

* ##### TAG `t/`
    * Each applicant can have many stored tags, so ***Tags*** will be used to refer to each applicant's stored ***Tag*** s.
    * A TAG is considered matching with a ***Tags*** only if ***at least 1*** keyword is **exactly equals** to **at least 1** ***Tag*** within the ***Tags***..
    * All keywords provided as TAG input must comply with input specifications for add given [**here**](#tag-t).
    * For example:
        * An `old` input can match with applicants that have the ***Tag*** `old`
        * An `experienced old` input can match with applicants that have the ***Tag*** `experienced`, or `old`, or both.

* ##### INTERVIEW `i/`
    * An INTERVIEW is considered matching with a ***Interview*** only if the keyword is a whole word (i.e. separated by space) contained in the ***Interview***  .
    * All keywords provided as INTERVIEW input must comply with the displayed time format (e.g. 20 March 2021, 10:30).
    * For example:
        * A `2021` input can match with applicants that have the ***Interview*** in year 2021.
        * A `20:21` input can match with applicants that have the ***Interview*** at time 20:21 on any date.
        * A `mar` input can match with applicants that have the ***Interview*** in March.
        * However, a `9:30` input cannot be matched with the ***Interview*** displayed as `20 Mar 2021, 09:30` (as 9:30 is not a whole word but 09:30 is).

* ##### NOTES `nt/`
    * NOTES are considered matching with  ***Notes*** only if  ***Notes*** contains **the entire** keyword.
    * All keywords provided as NOTES input must comply with input specifications for add given [**here**](#notes-nt).
    * For example:
        * A `good in this field` input can match with applicants that have ***Notes*** containing the `good in this field`.
        * A `passionate` input can match with applicants that have Notes such as `passionate but inexperienced` and `passionate and experienced`.

* ##### DONE `d/`
    * An DONE is considered matching only if the ***Done*** input is either `Done` or `Not Done`.    
    * For example:
        * A `Done` input can match with applicants that have their ***Done*** status marked as Done.
        * A `Not Done` input can match with applicants that have their ***Done*** status unmarked as Not Done.
        * Any other non-empty input is considered invalid.

</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RecruitIn home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL_ADDRESS r/ROLE et/EMPLOYMENT_TYPE s/EXPECTED_SALARY l/LEVEL_OF_EDUCATION y/YEARS_OF_EXPERIENCE [t/TAG] [i/INTERVIEW] [nt/NOTES]​` <br> e.g., `add n/Bob p/87654321 e/bob@gmail.com r/Software Engineering et/Full time s/4000 l/High School y/2 t/friend i/2021-10-21, 20:00 nt/This applicant is a good candidate for the job!`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL_ADDRESS] [r/ROLE] [et/EMPLOYMENT_TYPE] [s/EXPECTED_SALARY] [l/LEVEL_OF_EDUCATION] [y/YEARS_OF_EXPERIENCE] [t/TAG] [i/INTERVIEW] [nt/NOTES]` <br> e.g., `edit 1 p/90909090 s/4500`
**List** | `list`
**Delete** | `delete INDEX...`<br> e.g., `delete 3 2 5 4`
**Find** | `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL_ADDRESS] [r/ROLE] [et/EMPLOYMENT_TYPE] [s/EXPECTED_SALARY] [l/LEVEL_OF_EDUCATION] [y/YEARS_OF_EXPERIENCE] [t/TAG] [i/INTERVIEW] [nt/NOTES] [d/DONE]`<br> e.g., `find n/John Mary`
**Show** | `show [n/] [p/] [e/] [r/] [et/] [s/] [l/] [y/] [t/]`<br> e.g., `show r/ n/`
**Mark** | `mark INDEX…​`<br> e.g., `mark 3`
**Unmark** | `unmark INDEX…​`<br> e.g., `unmark 3`
**Delete marked** | `delete_marked`
**Clear**| `clear`
**Help** | `help`
