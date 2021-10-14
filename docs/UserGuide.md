---
layout: page title: User Guide
---

BogoBogo is a **desktop app for managing inventories, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, BogoBogo can get your
inventory management tasks done faster than traditional GUI apps.

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `BogoBogo.jar` from [here](https://github.com/AY2122S1-CS2103-F10-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>

**TODO: Repalce sample ui image when ui is done**
![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    - **`add`**`apple -sp 8.5 -s A012345 -c 1000 -cp 5.2` : Adds apple with sales price 8.5, serial number A012345,
      quantity 1000, and cost price 5.2, into the inventory.

    - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    - **`clear`** : Deletes all contacts.

    - **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `{}` are the parameters to be supplied by the user.<br>
  e.g. in `add -n {name}`, `name` is a parameter which can be used as `add -n milk`.

- Items in square brackets with pipes are exclusively optional (user must specify at least one of the option).<br>
  e.g `delete [-n {name} | -s {serial number}]` should be supplied with either `name` or `serial number`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [Coming soon]

Shows a message explaining how to access the help page.

**TODO: Replace help ui image**
![help message](images/helpMessage.png)

Format: `help`

### Adding an item: `add` [Coming soon]

Adds an item to the inventory.

Format: `add {name} -sp {sales price} -s {serial number} -c {count} -cp {cost price}`

Flag    |  Argument    | Description
--------|--------------|-------------
&nbsp;  | name          | name of the item to add.
`-sp`   | sell price    | Sell price of the item to add.
`-s`    | serial number | Serial number of item to add.
`-c`    | count         | Quantity of the item to add.
`-cp`   | cost price    | Cost price of the item to add.

Examples:

```
add apple -sp 8.5 -s A012345 -c 1000 -cp 5.2
add banana -sp 7.5 -s A012346 -c 800 -cp 4.8
```

### Deleting an item : `delete` [coming soon]

Deletes the specified item from the inventory.

Format: `delete [-n {name} | -s {serial number}] -c {count}`

Flag    |  Argument      | Description
--------|----------------|-------------
`-n`    | name           | Name of the item to delete.
`-s`    | serial number  | Serial number of the item to delete.
`-c`    | count          | Quantity of the item to delete.

- Decreases the specified item's quantity by the count specified.
- An item can be specified by either name or serial number.
- If both name and serial number are specified, the command will take the name as reference.
- The `count` **must be a positive integer**.

Examples:

```
delete -n milk -c 10  // delete by name
delete -s A01234 -c 10  // delete by serial number
delete -n milk -s A01111 -c 12  // delete 12 milks
```

### Get quantity of the item: `count` [Coming soon]

Gets the quantity of the item specified.

Format: `count [-n {name} | -s {serial number}]`

Flag    |  Argument      | Description
--------|----------------|-------------
`-n`    | name           | Name of the item to delete.
`-s`    | serial number  | Serial number of the item to delete.
`-c`    | count          | Quantity of the item to delete.

Examples:

```
count milk  // count by name
count A01111  // count by serial number
```

### Manage orders

Remove items from the inventory by logging in an order. To enter order logging mode, use `sorder`. Exit the mode
with `eorder`.

![mange orders workflow](images/manageOrdersWorkflow.png)

### Start ordering: `sorder`

Starts a new list of orders.

Format: `sorder`

Example:

```
sorder
>> Please enter item name and quantity.
```

### Input an item into order: `iorder`

Add an item into the current list of orders.

Format:
`iorder n/{name} c/{count}`

`iorder [-n {name} | -s {serial number}] -c {count}`

Flag    |  Argument      | Description
--------|----------------|-------------
`-n`    | name           | Name of the item to add.
`-s`    | serial number  | Serial number of the item to add.
`-c`    | count          | Quantity of the item to add.

Example:

```
iorder -n milk -c 5  // Input order of item named milk
iorder -s 12345 -c 5  // Input order of item with serial no.12345
```

### Cancel an item from order: `corder`

Cancels the specified order from the current list of orders.

Format:
`corder n/{name}`

`corder [-n {name} | -s {serial number}]`

Flag    |  Argument      | Description
--------|----------------|-------------
`-n`    | name           | Name of the item to add.
`-s`    | serial number  | Serial number of the item to add.

Example:

```
corder -n milk  // Cancel order of item named milk
corder -s 12345  // Cancel order of item with serial no. 12345
```

### End ordering: `eorder`

Saves the current list of orders

Format: `eorder`

Example:

```
eorder
>> Order is placed!
```

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ [Coming soon]

**Q**:
**A**:

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------

**
Add** | `add {name} -sp {sales price} -s {serial number} -c {count} -cp {cost price}` <br>
e.g., `add apple -sp 8.5 -s A012345 -c 1000 -cp 5.2`
**Delete** | `delete [-n {name} \| -s {serial number}] -c {count}` <br> e.g., `delete -n milk -c 10`
**Count** | `count [-n {name} \| -s {serial number}]` <br> e.g., `count milk`
**Start ordering** | `sorder`
**Input item to order** | `iorder [-n {name} \| -s {serial number}] -c {count}` <br> e.g., `iorder -n milk -c 5`
**Cancel item in order** | `corder [-n {name} \| -s {serial number}]` <br> e.g., `corder -n milk`
**End ordering** | `eorder`
