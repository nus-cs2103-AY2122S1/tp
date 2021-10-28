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

    - **`add`** `Apple id/139827 c/3 cp/1.3 sp/2.4` : Adds apple with id 139827, quantity 3, 
      cost price 1.3, and sales price 2.4, into the inventory.

    - **`delete`**` cookie` : Deletes the cookie item from inventory.

    - **`clear`** : Deletes all items in inventory.

    - **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `{}` are the parameters to be supplied by the user.<br>
  e.g. in `add {name}`, `name` is a parameter which can be used as `add milk`.

- Items in square brackets with pipes are exclusively optional (user must specify at least one of the option).<br>
  e.g `delete [{name} | id/{id_number}]` should be supplied with either `name` or `id number`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/popular`, `t/popular t/baked` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `id/{id_number} cp/{cost_price}`, `cp/{cost_price} id/{id_number}` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `id/123413 id/567856`, only `id/567856` will be taken.

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

Format: `add {name} id/{id number} c/{count} cp/{cost price} sp/{sell price}`

Flag    |  Argument    | Description
--------|--------------|-------------
&nbsp;  | name          | name of the item to add.
`/id`   | id number     | Id number of item to add.
`/c`    | count         | Quantity of the item to add.
`/cp`   | cost price    | Cost price of the item to add.
`/sp`   | sell price    | Sell price of the item to add.

Examples:

```
add apple id/192028 c/2 cp/1.1 sp/2.4
add banana id/192023 c/5 cp/1.0 sp/2.2
```

### Deleting an item : `delete` [coming soon]

Deletes the specified item from the inventory.

Format: `delete [{name} | id/{id number}]`

Flag    |  Argument      | Description
--------|----------------|-------------
`n/`    | name           | Name of the item to delete.
`id/`   | id number      | Serial number of the item to delete.

- Delete the specified item entirely from the inventory.
- An item can be specified by either name or serial number.
- If both name and serial number are specified, the command will take the name as reference.

Examples:

```
delete Apple // delete by name
delete id/181817  // delete by serial number
```

### Removing an item : `remove` [coming soon]

Removes a specified amount of a particular item from the inventory.

Format: `remove [{name} | id/{id number}] c/{count}`

Flag    |  Argument      | Description
--------|----------------|-------------
`n/`    | name           | Name of the item to remove.
`id/`   | id number      | Id number of the item to remove.
`c/`    | count          | Quantity of the item to remove.

- An item can be specified by either name or serial number.
- If both name and serial number are specified, the command will take the name as reference.
- Count must be positive integer

Examples:

```
remove Apple c/2 // remove 2 apples from inventory
remove id/181817 c/5 // remove 5 items with id 181817
```

### Editing an item : `edit` [coming soon]

Edit a particular item in the inventory.

Format: `edit {index} [n/{name}] [id/{id}] [cp/{cp}] [sp/{sp}] [t/{tag}]...`

Flag    |  Argument    | Description
--------|--------------|-------------
&nbsp;  | index         | index of the item to edit.
`n/`    | name          | new name for the item.
`/id`   | id number     | new id number for the item.
`/cp`   | cost price    | new cost price for the item.
`/sp`   | sell price    | new sell price for the item.
`/t`    | tag           | new tag for the item.

- Count cannot be edited

Examples:

```
edit 1 id/192028  // edit first item's id to 192028
edit 2 n/Panadol  // edit second item's name to Panadol
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
