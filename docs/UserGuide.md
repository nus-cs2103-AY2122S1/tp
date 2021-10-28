---
layout: page
title: User Guide
---

Restaurant HR Helper (RHRH) is a **desktop app for managing restaurant contacts including employees, suppliers and customers, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RHRH can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Getting Started

### Before using this User Guide

Here are some important syntax which are used throughout this document:

<div markdown="block" class="alert alert-warning">

**:information_source: Notes:**<br>
Useful information or tips are displayed here

</div>

<div markdown="block" class="alert alert-danger">

**:bangbang:Warning!**<br>
Dangerous or potentially negative actions are displayed here

</div>

### Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `rhrh.jar` from [here](https://github.com/AY2122S1-CS2103T-T17-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your RHRH.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>  
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>  
   Some example commands you can try:

    - `addC` : Add a customer
    - Format: `addcustomer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS lp/LOYALTYPOINTS [alg/ALLERGIES] [sr/SPECIALREQUESTS] [t/TAG]...`
    - Example: `addC customer n/John Doe p/87654321 e/e12345@u.nus.edu a/Clementi lp/1000`

6. Refer to the [Features](#features) below for details of each command. If you want to have an overview of all the commands, you can refer to [Command Summary](#command-summary) section.

### Command syntax

Commands in RHRH has the syntax: `COMMAND_WORD + [PREAMBLE] + [PREFIX + PARAMETER]...`, where:

- `COMMAND_WORD` is a word that specifies a action of the command, e.g. `addC` for adding a customer, `deleteR` for deleting a reservation.
- `PREAMBLE` is the text before the first valid prefix, usually a positive number (for delete, edit or add reservation commands), or a phrase (for find commands).
- `PREFIX` is a keyword to recognize the beginning of a `PARAMETER`, usually ends with a `'/'` by convention.
- `PARAMETER` is an argument input by user. `PREAMBLE` is also a kind of `PARAMETER`. A command can have 0 or more `PARAMETER`s, which can be compulsory or optional.

Example: `addr 2 p/98765432 at/2021-12-24 2000`

- `addr` is a `COMMAND_WORD` that specifies the action of adding a new reservation.
- `2` is an `PREAMBLE` that specifies number of people.
- `p/`, `at/` are prefixes for phone and date-time, respectively.
- `2`, `98765432` and `2021-12-24 2000` are `PARAMETER`s

This is the list of all prefixes used in RHRH, as well as their corresponding parameter constraints:

| Prefix | Description         | Parameter constraints                             |
| :----: | ------------------- | ------------------------------------------------- |
|  `n/`  | Name                |
|  `p/`  | Phone               | 
|  `a/`  | Address             |
|  `e/`  | Email               |
|  `t/`  | Tag (Optional)      | Contains alphanumeric characters. One entity can have multiple tags.<br>When editing tags, the existing values of these fields will be replaced, i.e editing of these fields are not cumulative.<br> If you want to remove all tags from an entity, you can use edit command with `r/` without specifying any tag after it.
| `lp/`  | Loyalty Point       |
| `alg/` | Allergy (Optional)  | Similar to `t/`
| `sr/`  | Special Request (Optional) | Similar to `t/`
|  `l/`  | Leaves              |
| `jt/`  | Job Title           |
| `sal/` | Salary              |
| `st/`  | Supply Type         |
| `dd/`  | Delivery Details    |
| `at/`  | Reserving Date Time | Format: `yyyy-MM-dd HHmm`, e.g. `2021-12-24 2000` |
|  `r/`  | Remark (Optional)   | Contains alphanumeric characters.<br> If you want to remove the remark, you can use edit command with `r/`, without specifying any remark after it.

This is the list of some repeatedly used preambles used in RHRH, as well as there corresponding constraints:

| Preamble              | Parameter constraints
| :-------------------: | ---------------------------------------------|
| `INDEX`               | Must be a positive integer 1, 2, 3, …
| `KEYWORD`             | 

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Commands are case-insensitive

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add customer n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a customer: `addC`

Adds a customer to RHRH.

Format:
* `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS lp/LOYALTY POINTS [alg/ALLERGIES] [sr/SPECIAL REQUESTS] [t/TAG]`: 
Adds a customer with all specified fields, where `LOYALTY POINTS`, `ALLERGIES` and `SPECIAL REQUESTS` are fields specific to customers.
  * `ALLERGIES`, `SPECIAL REQUESTS` and `TAG` are optional fields that can be omitted.

Examples: 
* `addC n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 10 lp/1000 alg/Kiwi sr/NoAirCon t/friendly`
* `addC n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 10 lp/10000`


### Adding an employee: `addemployee`

Adds an employee to RHRH.

Format: `addemployee n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS l/LEAVES sal/SALARY jt/JOBTITLE [t/TAG]`

* Adds an employee with all specified fields, where `LEAVES`, `SALARY` and `JOBTITLE` are fields specific to employees.
* `TAG` is an optional field that can be omitted.

Examples: 
* `addemployee n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 10 l/14 sal/4000 jt/Junior Developer t/workaholic` adds an employee with the respective fields.
* `addemployee n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 10 l/14 sal/4000 jt/Junior Developer` adds an employee without any optional fields.

### Adding a supplier: `addS`

Adds a supplier to RHRH.

Format: `addS n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS st/SUPPLYTYPE dd/DELIVERYDETAILS [t/TAG]`

* Adds a supplier with all specified fields, where `SUPPLYTYPE` and `DELIVERYDETAILS` are fields specific to suppliers
* `TAG` is an optional field that can be omitted.

Examples:
* `addS n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 10 st/Alcohol dd/2021-11-19 15:00 t/Regular` 
  adds a supplier with the respective fields.
* `addS n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 10 st/Alcohol dd/08:00 24-12-2021` adds a 
  supplier without any optional fields.

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* Order of the `date` and `time` in `DeliveryDetails` does not matter but `time` has to be in `24 hour` 
  format.

The following table shows the acceptable formats and relevant examples for `DeliveryDetails`:

| Acceptable Formats | Examples |
| ----------- | ----------- |
| `yyyy-MM-dd HH:mm` | `2021-09-19 13:00` |
| `dd-MM-yyyy HH:mm` | `19-09-2021 13:00` |
| `HH:mm yyyy-MM-dd` | `14:00 2021-11-10` |
| `HH:mm dd-MM-yyyy` | `14:00 10-11-2021` |

</div>

### Adding a reservation: `addr`

Adds a new reservation to RHRH

Format: `addr NUMBER_OF_PEOPLE p/PHONE at/DATE_TIME [r/REMARK] [t/TAG]…`

* Date-time format is `yyyy-MM-dd HH00`, e.g. `2021-11-11 2000`

Examples:
* `addr 2 p/98765432 at/2021-12-24 2000 r/birthday party t/10 Percent Off t/Free cake` adds a new reservation of 2 pax for customer with
* `addr 5 p/12345668 at/2021-02-14 1200`

<div markdown="block" class="alert alert-warning">

:information_source: **Notes:**<br>

* Tables must be set before reservations can be made. See [settables](#set-the-tables-for-the-restaurant-settables)

* Phone number has to be a valid phone number from a customer in the database

* Time has to be formatted on the hour (i.e. minutes of the time is **00**)

</div>

### Editing a customer : `editC`

Edits an existing customer in RHRH.

Format:
* `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [alg/ALLERGIES] [sr/SPECIALREQUESTS] [t/TAG]…​`: Edits
    the customer at the specified `INDEX`.
  * `INDEX` refers to the index number shown in the displayed customer list. 
  * `INDEX` **must be a positive integer** 1, 2, 3, …​
  * At least one of the optional fields must be provided.
  * Existing values will be updated to the input values.

Examples:
*  `editC 1 alg/Kiwi sr/no air con` Replaces the existing allergies and special requests of the 1st customer to `Kiwi` and `no air con` respectively.
*  `editC 2 n/Betsy Crower t/` Edits the name of the 2nd customer to be `Betsy Crower` and clears all existing tags.

<div markdown="block" class="alert alert-warning">

:information_source: **Notes:**<br>

* For Allergies, Special Requests and Tags
  * the existing values of these fields will be replaced i.e editing of these fields are not cumulative.
  * You can remove the customer's tags by typing `t/` without
    specifying any tags after it. Similar for allergies by typing `alg/` and special requests by typing `sr/`.

</div>

### Editing an employee : `editemployee`

Edits an existing employee in RHRH.

Format:
* `editemployee INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [l/LEAVES] [sal/SALARY] [jt/JOBTITLE] [t/TAG]…​`: 

* Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed employee list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the employee will be removed i.e adding of tags is not cumulative.
* You can remove all the employee’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `editemployee 1 sal/4000` Edits the salary of the 1st employee to be `4000`.
*  `editemployee 2 n/Betsy Crower t/` Edits the name of the 2nd employeee to be `Betsy Crower` and clears all existing tags.

### Editing a supplier : `editS`

Edits an existing supplier in RHRH.

Format: `editS INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [st/SUPPLYTYPE] [dd/DELIVERYDETAILS] [t/TAG]…​`

* Edits the supplier at the specified `INDEX`. The index refers to the index number shown in the displayed supplier list.

Examples:
* `editS 1 p/91234567 st/Beef` Edits the phone number and supply type of the 1st supplier to be `91234567` and `Beef` respectively.
* `editS 2 n/Betsy Crower t/` Edits the name of the 2nd supplier to be `Betsy Crower` and clears all existing tags.

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the supplier will be removed i.e adding of tags is not cumulative.
* You can remove all the supplier’s tags by typing `t/` without specifying any tags after it.

</div>

### Editing a reservation : `editR`

Edits an existing reservation in RHRH

Format: `editR INDEX [r/REMARK] [t/TAG]…`

* Edits the reservation at the specified `INDEX`. The index refers to the index number shown in the displayed supplier list. The index **must be a positive integer** 1, 2, 3, … 

### Deleting a customer : `deleteC`

Deletes the specified customer from RHRH.

Format:
* `deleteC INDEX`: Deletes the customer at the specified `INDEX`.
  * `INDEX` refers to the index number shown in the displayed customer list.
  * `INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteC 2` deletes the 2nd customer displayed in the address book.

### Deleting an employee : `deleteemployee`

Deletes the specified employee from RHRH.

Format: `deleteemployee INDEX`

* Deletes the employee at the specified `INDEX`.
* The index refers to the index number shown in the displayed employee list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteemployee 2` deletes the 2nd person in the address book.

### Deleting a supplier : `deleteSupplier`

Deletes the specified supplier from RHRH.

Format: `deleteS INDEX`

* Deletes the supplier at the specified `INDEX`.
* The index refers to the index number shown in the displayed supplier list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
<<<<<<< HEAD
* `deleteSupplier 2` deletes the 2nd supplier in the address book.

### Deleting a reservation : `deleteR`

Deletes the specified reservation from RHRH

Format: `deleteR INDEX`

* Deletes the reservation at the specified `INDEX`
* The index refers to the index number shown in the display reservation list.
* The index **must be a positive integer** 1, 2, 3, …

Example: 
* `deleteR 1` deletes the 1st reservation in the display reservation list.

### Searching for reservation's made : `check`

Displays the reservations made at the specified date and/or time

Format:
* `check DATE TIME`: Returns all reservations on `DATE TIME`
* `check DATE`: Returns all reservations on `DATE`, for **all timings**
* `check TIME`: Returns all reservations on **today's date**, at `TIME` 
  * `DATE` is formatted as `yyyy-MM-dd`
  * `TIME` is formatted as `HH00`

Examples:
* `check 2021-09-19 1800`
* `check 2021-09-19`
* `check 1800`

<div markdown="block" class="alert alert-warning">

:information_source: **Notes:**<br>

* Time has to be formatted on the hour (i.e. minutes of the time is **00**)

</div>
=======
* `deleteS 2` deletes the 2nd supplier in the address book.
>>>>>>> 4aacb5a1430e2359d87f3867cdb546ecb7760155

### Finding customers based on keywords: `findC`

Find customers that have fields that contain all specified keywords cumulatively

Format:
* `findC [KEYWORD]…​`: Displays all customers that match specified keywords
  * At least one `KEYWORD` must be provided.
  * Only customers that match **all** provided keywords will be returned.


Examples:
* `findC Chetwin Everything`: Finds customers that have both the keywords 'Chetwin' and 'Everything' in their fields.

<<<<<<< HEAD
### Displaying a sorted list of customers: `sortC`
=======
### Finding Suppliers based on keywords: `findS`

Find suppliers that have fields that contain all specified keywords cumulatively.

Format: 
* `findS KEYWORD [MORE_KEYWORDS...]`: Displays all suppliers that contain all specified keywords

Examples:
* `findS Oct AM`: Finds suppliers that have both keywords `Oct` and `AM` in their fields

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* At least one `KEYWORD` must be provided
* The search is case-insensitive. e.g `oct` will match `OCT`
* Partial words can be matched. e.g `oct` will match `October`
* Only suppliers that contain **all** provided keywords will be displayed.

</div>

### Displays a sorted list of customers: `sortC`
>>>>>>> 4aacb5a1430e2359d87f3867cdb546ecb7760155

Sorts and displays the list of customers based on a given field in either ascending or descending order.

Format:
* `sortC by/PREFIX OF SORT KEY o/ORDER OF SORT`: Sorts and displays the list of customers based on provided arguments
    * `PREFIX OF SORT KEY` is the prefix of the field you wish to sort the list of customers by, i.e. lp for loyaltyPoints and alg for allergies
    * `ORDER OF SORT` can be set to 'a' for ascending order or 'd' for descending order

Examples:
* `sortC by/n o/d`: sorts the list of customers by `NAME` in descending order.
* `sortC by/alg o/a`: sorts the list of customers by `ALLERGIES` in ascending order.

<<<<<<< HEAD
=======
### Displaying a sorted list of suppliers: `sortS`
Sorts and displays the list of suppliers based on a given field in either ascending or descending order.

Format:
* `sortS by/PREFIX OF SUPPLIER FIELD o/ORDER OF SORT`: Sorts and displays the list of suppliers based on the sort type 
  and order.

Examples:
* `sortS by/dd o/a`: Sorts the list of suppliers by `DELIVERYDETAILS` in `ascending` order
* `sorts by/N o/d`: Sorts the list of suppliers by `NAME` in `descending` order

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* The prefixes used for the parameter `PREFIX OF SORT KEY` is the same as that when adding or deleting a supplier.
  * For example `n` for name and `dd` for delivery details
* The only acceptable inputs for the `ORDER OF SORT` parameter are `a` for `ascending` and `d` for `descending`.

</div>


### Resetting a sorted supplier list: `resetS`

Resets the sorting of the supplier list to its default state (sorted by name)

Format:
* `resetS`

### Listing all suppliers: `listS`

Shows a list of all suppliers in RHRH.

Format: `listS`

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* RHRH will switch to the supplier list and list all suppliers no matter which list you are current viewing
* You can also switch to the supplier list manually by clicking the `View` tab in the menu bar and selecting 
  `Suppliers`

</div>

### Searching for reservation's made: `check`

Displays the reservations made at the specified date and/or time

Format:
* `check DATE TIME`: Returns all reservations on `DATE TIME`
* `check DATE`: Returns all reservations on `DATE`, for **all timings**
* `check TIME`: Returns all reservations on **today's date**, at `TIME` 
  * `DATE` is formatted as `yyyy-MM-dd`
  * `TIME` is formatted as `HH00`

Examples:
* `check 2021-09-19 1800`
* `check 2021-09-19`
* `check 1800`

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* Time has to be formatted on the hour (i.e. minutes of the time is **00**)

</div>

>>>>>>> 4aacb5a1430e2359d87f3867cdb546ecb7760155
### Setting the tables for the restaurant: `settables`

<div markdown="block" class="alert alert-danger">

:bangbang: **Warning!**<br>
This command will remove **ALL** reservations and overwrite previously set tables <br>

</div>

Sets the tables with the sizes specified so that reservations can be made

Format: `settables LIST_OF_TABLE_SIZES`
  * `LIST_OF_TABLE_SIZES` is formatted as any combination of the following:
    * **(size of one table)**
    * **(size of one table)x(number of tables with this size)**,<br>
      each comma-separated from the next.

Examples:
* `settables 10,8,8,4,4,2x6,1x4`

<div markdown="block" class="alert alert-warning">

:information_source: **Notes:**<br>

* Size of tables and Number of tables with specified size has to be a positive integer

</div>

<<<<<<< HEAD
=======
### Adding a reservation: `addr`

Add a new reservation with number of people, 
customer's phone number and the date & time.

Format: `addr NUMBER_OF_PEOPLE p/PHONE at/DATE_TIME`

* Date-time format is `yyyy-MM-dd HH00`, e.g. `2021-11-11 2000`

Examples: 
* `addr 2 p/98765432 at/2021-12-24 2000`
* `addr 5 p/12345668 at/2021-02-14 1200`

<div markdown="block" class="alert alert-warning">
:information_source: **Notes:**<br>

* Tables must be set before reservations can be made. See [settables](#set-the-tables-for-the-restaurant-settables)

* Phone number has to be a valid phone number from a customer

* Time has to be formatted on the hour (i.e. minutes of the time is **00**)

</div>
>>>>>>> 4aacb5a1430e2359d87f3867cdb546ecb7760155

### Clearing all entries : `clear`

Clears all entries from RHRH.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

RHRH data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

RHRH data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RHRH will discard all data and start with an empty data file at the next run.
</div>

---

## These features will be coming soon!

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RHRH home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Customer** | `addcustomer n/NAME p/PHONE_NUMBER e/EMAIL [alg/ALLERGIES] [sr/SPECIALREQUESTS] [t/TAG]` <br> e.g. `add customer n/John Doe p/87654321 e/e12345@u.nus.edu`
**Add Employee** | `addemployee n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS l/LEAVES sal/SALARY jt/JOBTITLE [t/TAG]` <br> e.g. `add employee n/John Doe p/87654321 e/e12345@u.nus.edu a/Blk 20 Sengkang Ave 2 l/14 sal/4000 jt/Project Manager`
**Add supplier** | `addsupplier n/NAME p/PHONE_NUMBER e/EMAIL st/SUPPLYTYPE dd/DELIVERYDETAILS [t/TAG]` <br> e.g. `add supplier n/John Doe p/87654321 e/e12345@u.nus.edu st/Chicken dd/Every Monday`
**Add reservation** | `addr NUMBER_OF_PEOPLE p/PHONE at/DATE_TIME [r/REMARK] [t/TAG]` <br> e.g. `addr 2 p/98765432 at/2021-12-24 2000`
**Check a reservation availability** | `check DATE TIME`, `check DATE`, `check TIME` <br> e.g. `check 2021-09-19 1800`, `check 2021-09-19`, `check 1800`
**Delete Employee** | `deleteemployee INDEX`<br> e.g., `delete 1`
**Delete Supplier** | `deletesupplier INDEX`<br> e.g., `delete 2`
**Delete Customer** | `deletecustomer INDEX`<br> e.g., `delete 3`
**Delete Reservation** | `deleteR INDEx`
**Edit Employee** | `editemployee INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [l/LEAVES] [sal/SALARY] [jt/JOBTITLE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com sal/7000`
**Edit Supplier** | `editsupplier INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [st/SUPPLYTYPE] [dd/DELIVERYDETAILS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com st/Beef`
**Edit Customer** | `editcustomer INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [alg/ALLERGIES] [sr/SPECIALREQUESTS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com alg/Kiwi`
**Edit Reservation**| `editR INDEX [r/REMARK] [t/TAG]…`<br> e.g. `editR 2 r/surprise birthday party t/10PercentOff`
**Set Tables** | `settables LIST_OF_TABLE_SIZES`<br> e.g., `settables 10,8,8,4,4,2x6,1x4`
**Find [COMING SOON]** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List Customers** | `listC`
**List Employees** | `listE`
**List Suppliers** | `listS`
**List Reservations** | `listR`
**Help** | `help`
