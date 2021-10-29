---
layout: page title: User Guide
---

BogoBogo is a **desktop app for managing inventories, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, BogoBogo can get your
inventory management tasks done faster than traditional GUI apps. You can use Bogo Bogo as your personal inventory 
manager which helps you:
1. track your transactions
2. track your stock items
3. book keep 

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `BogoBogo.jar` from [here](https://github.com/AY2122S1-CS2103-F10-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>

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
   
- Parameters in `()` are optional.<br>
  e.g. in `add {name} (c/{count}), the `count` parameter can be omitted from the command.

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

### Viewing help : `help`

Shows a message explaining how to access the help page.
If a command word is specified, explains how to use the specified command.

Format: 
- `help`: Shows help page url.
- `help {command}` : Explains how to use the specified command.

Examples:
```
help sorder // Explains how to use sorder command
```

Screenshot:

![help message](images/helpMessage.png)

### Adding an item: `add`

Adds an item to the inventory.

Format: `add [{name} | id/{id number}] (c/{count}) (cp/{cost price}) (sp/{sell price})`

Flag    |  Argument     | Description                   | Remarks                                 |
--------|-------------- |-------------------------------|-----------------------------------------|
&nbsp;  | name          | Name of the item to add.      | Only alphanumeric characters allowed.   |
`id/`   | id number     | Id number of item to add.     | Must be a 6 digit number.               |
`c/`    | count         | Quantity of the item to add.  | If omitted, value defaulted as 1.       |
`cp/`   | cost price    | Cost price of the item to add.|                                         |
`sp/`   | sell price    | Sell price of the item to add.|                                         |

- name, id number, cost price, and sales price are compulsory when adding an item for the first time.

Examples:

```
add apple id/192028 c/2 cp/1.1 sp/2.4
add banana id/192023 c/5 cp/1.0 sp/2.2
```

Screenshot:

![add](images/screenshots/add_item.png)

### Deleting an item : `delete`

Deletes the specified item from the inventory.
Use this command to delete items wrongly keyed into the inventory. Costs initially incurred from the item is removed too.

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

Screenshot:

![delete](images/screenshots/delete_item.png)

### Removing an item : `remove`

Removes a specified amount of a particular item from the inventory.

Format: `remove [{name} | id/{id number}] c/{count}`

Flag    |  Argument      | Description                      | Remarks                           |
--------|----------------|----------------------------------|-----------------------------------|
`n/`    | name           | Name of the item to remove.      |                                   |
`id/`   | id number      | Id number of the item to remove. |                                   |
`c/`    | count          | Quantity of the item to remove.  | If omitted, value defaulted to 1. |

- An item can be specified by either name or serial number.
- If both name and serial number are specified, the command will take the name as reference.

Examples:

```
remove Apple c/2 // remove 2 apples from inventory
remove id/181817 c/5 // remove 5 items with id 181817
```

Screenshot:

![remove](images/screenshots/remove_item.png)

### Editing an item : `edit`

Edit a particular item in the inventory.

Format: `edit {index} [n/{name}] [id/{id}] [cp/{cp}] [sp/{sp}] [t/{tag}]...`

Flag    |  Argument    | Description
--------|--------------|-------------
&nbsp;  | index         | index of the item to edit.
`n/`    | name          | new name for the item.
`id/`   | id number     | new id number for the item.
`cp/`   | cost price    | new cost price for the item.
`sp/`   | sell price    | new sell price for the item.
`t/`    | tag           | new tag for the item.

- Count cannot be edited, use add, delete or remove instead.

Examples:

```
edit 1 id/192028  // edit first item's id to 192028
edit 2 n/Panadol  // edit second item's name to Panadol
```

Screenshot:

![edit](images/screenshots/edit_item.png)

### Listing items: `list`

List items in the inventory, current order, or past transactions.

Format: 

 - `list`: List items in the inventory.
 - `list order`: List items in the current order (if any).
 - `list txns`: List past transactions.
 - `list txns {id}`: List items in the specified transaction.

Screenshot:

![list](images/screenshots/list_items.png)

### Sorting items: `sort`

Sort items in the inventory.
Note that display must be in inventory mode (see `list`).

Format: 

 - `sort n/`: Sort items in the inventory by name.
 - `list id/`: Sort items in the inventory by id.

Screenshot:

![sort](images/screenshots/sort_items.png)

### Finding items: `find`

Find items in the inventory.
Note that display must be in inventory mode (see `list`).

Format: `find [ n/{name}... | id/{id}... | t/{tag}]`

Searching by 1 or more names, or 1 or more ids, or 1 or more tags is supported. However, searching by multiple tags simultaneously is not supported.

Examples: 

```
find n/Cookie n/Apple  // find and list items with names "Cookie" and "Apple"
find id/123456  // find item with id 123456
```

Screenshot:

![find](images/screenshots/find_item.png)

### Clearing items: `clear`

Clears the entire inventory.

Format: `clear`

Screenshot:

![clear](images/screenshots/clear.png)

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

Screenshot:

![sorder](images/screenshots/sorder.png)

### Inputting an item into order: `iorder`

Add an item into the current order.

Format:
`iorder [ {name} | id/{id} ] (c/{count})`

Flag    |  Argument      | Description                            | Remarks                           |
--------|----------------|----------------------------------------|-----------------------------------|
&nbsp;  | name           | Name of the item to add to order.      |                                   |
`id/`   | id number      | Id number of the item to add to order. |                                   |
`c/`    | count          | Quantity of the item to add to order.  | If omitted, value defaulted to 1. |

Example:

```
iorder milk c/5  // Add 5x milk into order
iorder id/12345  // Add 1x item with id 12345 into order
```

Screenshot:

![iorder](images/screenshots/iorder.png)

### Cancelling an item in the order: `corder`

Cancels the specified order from the current order.

Format:
`corder [ {name} | id/{serial number}]`

Flag    |  Argument      | Description                                 | Remarks                           |
--------|----------------|---------------------------------------------|-----------------------------------|
&nbsp;  | name           | Name of the item to remove from order.      |                                   |
`id/`   | id number      | Id number of the item to remove from order. |                                   |
`c/`    | count          | Quantity of the item to remove from order.  | If omitted, value defaulted to 1. |

Example:

```
corder milk c/5  // Remove 5x milk from the order
corder id/12345  // Remove 1x item with id 12345 from the order
```

Screenshot:

![corder](images/screenshots/corder.png)

### End ordering: `eorder`

Process the current order and save it.

Format: `eorder`

Example:

```
eorder
>> Order is placed.
```

Screenshot:

![eorder](images/screenshots/eorder.png)

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
