---
layout: page
title: User Guide
---

# User Guide

RecruitIn is a desktop app for recruiters in Singapore to keep track of the plethora of clients with different skill sets, availability and experience. It is optimized for quick text-based inputs via a Command Line Interface (CLI) while still having the ease of use of a Graphical User Interface (GUI). This product will make recruiters’ lives easier through categorisation and filter features to easily access candidates they have in mind.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `RecruitIn.jar` from here.

3. Copy the file to the folder you want to use as the _home folder_.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
    
    * **`list`** : Lists all applicants.
    
    * **`add`**`n/John Doe p/98765432 e/johnd@example.com s/3000` : Adds an applicant named `John Doe` to RecruitIn.
   
    * **`find`**`n/John Mary` : Finds all applicants with either `John` or `Mary` as values for name prefix.

    * **`delete`**`3` : Deletes the 3rd applicant shown in the list of all applicants.

    * **`show`**`n/` : Displays a list of all unique applicant names.

    * **`mark`**`1` : Marks the 1st applicant shown in the list of all applicants.

    * **`unmark`**`1` : Unmarks the 1st applicant shown in the list of all applicants.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an applicant: `add`

Adds an applicant to RecruitIn.

Format: `add n/NAME p/CONTACT_NUMBER e/EMAIL_ADDRESS r/ROLE et/EMPLOYMENT_TYPE s/EXPECTED_SALARY l/LEVEL_OF_EDUCATION y/YEARS_OF_EXPERIENCE [t/TAG] [i/INTERVIEW]​`

Examples:
* `add n/Bob p/87654321 e/bob@gmail.com r/Software Engineering et/Full time s/4000 l/High School y/2 i/2021-10-21 20:00`

Prefix Input Specifications:

**Note**: **Alphanumeric** characters refers specifically to characters a-z, A-Z and 0-9.
* ####NAME `n/`
  * A NAME should only contain **alphanumeric** characters. Spaces between words are allowed.
  * For example:
    * NAME inputs such as `John`, `Mary Sue` and `9ine 6ix` are acceptable.
    * NAME inputs such as `J@hn`, `Mary S^e` and `B{}b` are not acceptable.
* ####CONTACT_NUMBER `p/`
  * A CONTACT_NUMBER should contain a minimum of 3 digits. No characters other than the digits 0-9 are allowed.
  * For example:
    * CONTACT_NUMBER inputs such as `99999999` and `999` are acceptable.
    * CONTACT_NUMBER inputs such as `9999 9999` and `88` are not acceptable.
* ####EMAIL_ADDRESS `e/`
  * An EMAIL_ADDRESS should contain a **local part** and a **domain part**, separated by an `@` character.
  * The **local part**:
    * must contain **at least 1** alphanumeric character. 
    * It can contain alphanumeric characters separated by any 1 of these characters `+_.-`. e.g. `John-a-bc`
    * It must **start with** and **end with** an alphanumeric character.
  * The **domain part**:
    * must contain **at least 2** alphanumeric characters.
    * It can contain sections, each containing alphanumeric characters separated by `_`. Each section must end with
    `.`. The sections must be followed by **at least** 2 alphanumeric characters. e.g. `John-a.a-b-c.bc`
  * For example:
    * EMAIL_ADDRESS inputs such as `PeterJack_1190@example.com` and `e1234567@u.nus.edu` are acceptable.
    * EMAIL_ADDRESS inputs such as `peterjack@example.c` and `peter..jack@example.com` are unacceptable.
* ####ROLE `r/`
  * A ROLE should only contain **alphanumeric** characters. Spaces between words are allowed.
  * For example:
    * ROLE inputs such as `Software Engineer` and `Sales Assistant` are acceptable.
    * ROLE inputs such as `Softw@re Engin^^r` and `Day + Night Security Guard` are not acceptable.
* ####EMPLOYMENT_TYPE `et/`
  * An EMPLOYMENT_TYPE should
  * For example:
* ####EXPECTED_SALARY `s/`
  * An EXPECTED_SALARY should only represent non-negative integers.
    * Non-negative integers range from 0 to 2^(31) - 1 inclusive.
  * For example:
    * EXPECTED_SALARY inputs such as `0` and `3500` are acceptable.
    * EXPECTED_SALARY inputs such as `-600` and `~350` are not acceptable.
* ####LEVEL_OF_EDUCATION `l/`
  * A LEVEL_OF_EDUCATION should be one of the following: `Elementary`, `Middle School`, `High School`, `University`, `Bachelors`, `Masters` or `PhD`.
  * A LEVEL_OF_EDUCATION is case-insensitive.
  * For example:
    * LEVEL_OF_EDUCATION inputs such as `Middle School` and `PhD` are acceptable.
    * LEVEL_OF_EDUCATION inputs such as `miDDlE scHoOL` and `phD` are acceptable.
    * LEVEL_OF_EDUCATION inputs such as `Kindergarten` are not acceptable.
* ####YEARS_OF_EXPERIENCE `y/`
  * A YEARS_OF_EXPERIENCE should
  * For example:
* ####TAG `t/`
  * A TAG should only contain **alphanumeric** characters. Spaces between words are **not** allowed.
  * For example:
    * TAG inputs such as `friends` and `colleagues` are allowed.
    * TAG inputs such as `best friends`, `old colleagues` and `seni@r` are not allowed.

### Listing all applicants : `list`

Shows a list of all applicants in RecruitIn.

Format: `list`

### Finding an applicant : `find`

Finds applicants by specific prefixes.

Format: `find [n/NAME] [p/CONTACT_NUMBER] [e/EMAIL_ADDRESS] [r/ROLE] [et/EMPLOYMENT_TYPE] [s/EXPECTED_SALARY] [l/LEVEL_OF_EDUCATION] [y/YEARS_OF_EXPERIENCE]  [t/TAG]  [i/INTERVIEW]`

* Find command must take at least 1 prefix input.
* Find command can only take 1 input for each prefix.
* If you input multiple of the same prefix, only the last prefix will be used for the search of that category.
* Input for each prefix can contain multiple search terms separated by whitespace, e.g. `n/John Mary`, `t/friend colleague`
* Inputs are **case-insensitive**

Prefix Input Specifications:

***Note***: Italicised word refers to a stored prefix value. e.g. *Name* could refer to the value `John` stored as a name in the application.
* *Name* `n/`
  * Each additional keyword for *Name* leads to a more **accommodating** search.
  * A *Name* is considered matching if at least 1 keyword is equal to at least 1 word in the *Name*.
  * NAME input must comply with input specifications for add [here](####name).
  * For example:
    * A `John` input can match with *Name*s such as `John Tan` or `John Lee`. 
    * A `John Mary` input can match with *Name*s such as `Mary John`, `Mary Lee` or `Long John`.


* *Contact Number* `p/`
  * Each additional keyword for *Contact Number* leads to a more **accommodating** search.
  * A *Contact Number* is considered matching if at least 1 keyword is equal to at least 1 word in the *Contact Number*
  * CONTACT_NUMBER input must comply with input specifications for add [here](####contact_number).
  * For example:
    * A `99999999` input can only match with *Contact Number*s that are `99999999`.
    * A `99999999 88888888` input can only match with *Contact Number*s that are `99999999` and `88888888`.


* *Email* `e/`
  * Each additional keyword for *Email* leads to a more **accommodating** search.
  * An *Email* is considered matching if at least 1 keyword is equal to at least 1 word in the *Email*.
  * EMAIL_ADDRESS input must comply with input specifications for add [here](####email_address).
  * For example:
    * A `alexyeoh@example.com` input can match with *Email*s such as `alexyeoh@example.com`.
    * A `alexyeoh@example.com marysue@gmail.com` input can match with *Email*s such as `alexyeoh@example.com`
and `marysue@gmail.com`.


* *Role* `r/`
  * Each additional keyword for *Role* leads to a more **restrictive** search
  * A *Role* is considered matching only if every single keyword is equal to at least 1 word in the *Role*.
  * ROLE input must comply with input specifications for add [here](####role).
  * For example:
    * A `Software` input can match with *Role*s such as `Software Engineer`, `Software` or `Software Developer`
    * A `Software Engineer` input can match with *Role*s such as `Software Engineer` or `Senior Software Engineer`
but not with *Role*s such as `Software` or `Software Developer`.


* *Employment Type* `et/`
  * Each additional keyword for Email leads to a more **accommodating** search, as long as all keywords are **valid**.
  * A *Employment Type* is considered matching only if it starts with any of the keywords and all keywords are valid.
  * EMPLOYMENT_TYPE input must comply with input specifications for add [here](####employment_type).
  * For example:
    * A `Full time` or `full time` or `full` input will match only with *Employment Type*s that are ```Full time```
    * A ```Full part``` input will match with all *Employment Type*s that are ```Full time``` or ```Part time```
    * A ```temp Intern``` input will match with all *Employment Type*s that are ```Temporary``` or ```Internship```
    * A ```full time bob``` input will throw an exception as ```bob``` is not a term any of the *Employment Type*s start
    with.


* *Expected Salary* `p/`
    * Each additional keyword for *Expected Salary* leads to a more **accommodating** search.
    * An *Expected Salary* is considered matching if at least 1 keyword is within `500` from at least 1 *Expected Salary*.
    * EXPECTED_SALARY input must comply with input specifications for add [here](####expected_salary).
    * For example:
        * A `3000` input can match with *Expected Salary*s that range from `2500` to `3500` inclusive.
        * A `2500 5000` input can match with *Expected Salary*s from the ranges `2000` to `3000` inclusive, and `4500` to `5500` inclusive.


* *Level of Education* `l/`
  * Each additional keyword for *Level of Education* leads to a more **accommodating** search.
  * *Level of Education* has a fixed number of levels, being `Elementary`, `Middle School`, `High School`, `University`, `Bachelors`, `Masters` and `PhD`.
  * A *Level of Education* is considered matching only if keyword is equal to at least 1 letter in the possible types of *Level of Education*
  * LEVEL_OF_EDUCATION input must comply with input specifications for add [here](####level_of_education).
  * For example:
    * A `H` input can match with *Level of Education*s such `High School`, but not with *Level of Education*s such as `PhD`
    * A `High School` input can match with *Level of Education*s such as `High School`, but not with *Level of Education*s such as `Middle School`


* *Years of Experience* `y/`
    * Each additional keyword for *Year of Experience* leads to a more **accommodating** search.
    * A *Year of Experience* is considered matching if the value is larger than or equal to the keyword.
    * YEARS_OF_EXPERIENCE input must comply with input specifications for add [here](####years_of_experience).
    * For example:
        * A `3` input can match with *Year of Experience*s that are higher than or equal to 3.
        * A `2 3` input can match with *Year of Experience*s that are higher than or equal to 2.


* *Tag* `t/`
    * Each additional keyword for *Tag* leads to a more **accommodating** search.
    * A *Tag* is considered matching if at least 1 keyword is equals to at least 1 *Tag*.
    * TAG input must comply with input specifications for add [here](####tag).
    * For example:
        * An `old` input can match with applicants that have the *Tag* `old`
        * An `experienced old` input can match with applicants that have the *Tag* `experienced`, or `old`, or both.


* *Interview* `i/`
* Each additional keyword for *Interview* leads to a more **accommodating** search.
    * A *Interview* is considered matching if the time string contains the complete keyword.
    * For example:
        * A `2021` input can match with applicants that have the *Interview* in year 2021.
        * A `20:21` input can match with applicants that have the *Interview* at time 20:21 on any date.
        * A `21` input can match with *Interviews* `2021-10-10, 10:00`, `2020-10-21, 10:00`, `2020-10-10, 21:00` or `2020-10-10, 10:21`. 


Examples:
* `find n/John Mary` finds all applicants with either `John` or `Mary` as values for name prefix.
* `find t/friend colleague` finds all applicants with `friend` or `colleague` as values for tag prefix.
* `find n/John Mary t/friend colleague`
* `find n/Bob p/87654321 e/bob@gmail.com r/Software Engineering et/Full time s/4000 l/High School y/2`

### Deleting an applicant : `delete`

Deletes applicants by their index from the list in RecruitIn.

Format: `delete INDEX...`

* Deletes the applicant at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed applicants list.
* At least one `INDEX` must be given. (i.e. `delete ` is not a valid command)
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* `INDEX` uses **1-based indexing**.
* `INDEX` should not exceed the total number of applicants in the displayed applicants list.

Examples:
* `list` followed by `delete 1` deletes the 1st applicant listed in RecruitIn.
* `find n/John` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.
* `list` followed by `delete 2 4 7` deletes the 2nd, 4th and 7th applicants listed in RecruitIn.

### Deleting marked applicants: `delete_marked`

Deletes all applicants that are marked as done.

Format: `delete_marked`

### Showing search terms : `show`

Shows unique search terms available for a specific prefix.

Format: `show [n/] [p/] [e/] [r/] [et/] [s/] [l/] [y/] [t/]`

* Finds and displays a list of unique search terms available for a specific prefix.
* **At least 1** prefix must be given.
* Unique search terms will only be found for the **first** prefix input.

Examples:

**Note**: Suppose the applicant data includes 3 applicants with name and role `John, Software Developer`, `Mary, Cook` and `Mary, Cleaner`.
* `show n/` will display a list consisting of `John` and `Mary`.
* `show r/ n/` will display a list consisting of `Software Developer`, `Cleaner` and `Cook`.

### Marking an applicant : `mark`

Marks the specified applicant by index from the list in RecruitIn as "Done" (have been attended to).

Format: `mark INDEX…​`

* Marks the applicant at the specified `INDEX` as "Done".
* The `INDEX` refers to the index number shown in the displayed applicants list.
* At least one `INDEX` must be given. (i.e. `mark ` is not a valid command)
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* `INDEX` uses **1-based indexing**.
* `INDEX` should not exceed the total number of applicants in the displayed applicants list.

Examples:
* `list` followed by `mark 2` marks the 2nd applicant listed in RecruitIn as "Done".
* `find n/John` followed by `mark 1` marks the 1st applicant in the results of the `find` command.
* `list` followed by `mark 2 4 6` marks the 2nd, 4th and 6th applicant listed in RecruitIn as "Done".

### Unmarking an applicant : `unmark`

Unmarks the specified applicant by index from the list in RecruitIn to "Not Done" (have not been attended to).

Format: `unmark INDEX…​`

* Unmarks the applicant at the specified `INDEX` to "Not Done".
* The `INDEX` refers to the index number shown in the displayed applicants list.
* At least one `INDEX` must be given. (i.e. `unmark ` is not a valid command)
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* `INDEX` uses **1-based indexing**.
* `INDEX` should not exceed the total number of applicants in the displayed applicants list.

Examples:
* `list` followed by `ummark 2` unmarks the 2nd applicant listed in RecruitIn to "Not Done".
* `find n/John` followed by `unmark 1` unmarks the 1st applicant in the results of the `find` command.
* `list` followed by `unmark 2 4 6` unmarks the 2nd, 4th and 6th applicant listed in RecruitIn to "Not Done".

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
      "done": "Not Done"
    }
  ]
}
```
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RecruitIn will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RecruitIn home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/CONTACT_NUMBER e/EMAIL_ADDRESS r/ROLE et/EMPLOYMENT_TYPE s/EXPECTED_SALARY l/LEVEL_OF_EDUCATION y/YEARS_OF_EXPERIENCE [t/TAG] [i/INTERVIEW]​` <br> e.g., `add n/Bob p/87654321 e/bob@gmail.com r/Software Engineering et/Full time s/4000 l/High School y/2 t/friend i/2021-10-21, 20:00`
**List** | `list`
**Delete** | `delete INDEX...`<br> e.g., `delete 3 2 5 4`
**Delete Marked** | `delete_marked`
**Find** | `find [n/NAME] [p/CONTACT_NUMBER] [e/EMAIL_ADDRESS] [r/ROLE] [et/EMPLOYMENT_TYPE] [s/EXPECTED_SALARY] [l/LEVEL_OF_EDUCATION] [y/YEARS_OF_EXPERIENCE] [t/TAG] [i/INTERVIEW]`<br> e.g., `find n/John Mary`
**Show** | `show [n/] [p/] [e/] [r/] [et/] [s/] [l/] [y/] [t/]`<br> e.g., `show r/ n/`
**Mark** | `mark INDEX…​`<br> e.g., `mark 3`
**Unmark** | `unmark INDEX…​`<br> e.g., `unmark 3`
**Help** | `help`
