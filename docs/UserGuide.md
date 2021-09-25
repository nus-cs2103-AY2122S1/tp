---
layout: page
title: User Guide
---

Sellah is a desktop application optimized for online sellers who prefer CLI over GUI. It is an address book that
contains the contact information and order details related to clients and partners.

* Table of Contents
    * [Quick Start](#quick-start)
    * [Features](#features)
        * [Add (Coming Soon)](#adding-add-coming-soon)
        * [Edit (Coming Soon)](#editing-edit-coming-soon)
        * [View (Coming Soon)](#viewing-view-coming-soon)
        * [Delete (Coming Soon)](#deleting-delete-coming-soon)
        * [List (Coming Soon)](#listing-list-coming-soon)
        * [Exit](#exiting-exit)
        * [Load & Save Data (Coming Soon)](#loading-and-saving-the-data-coming-soon)
    * [FAQ](#faq)
    * [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `sellah.jar` from [here](https://github.com/AY2122S1-CS2103T-T12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Sellah.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add NAME`, `NAME` is a parameter which can be used as `add Ben`.

* Items in square brackets are optional.<br>
  e.g `NAME [-e EMAIL]` can be used as `Ben -e ben@gmail.com` or as `Ben`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-pn PHONE_NUMBER -e EMAIL`, `-e EMAIL -pn PHONE_NUMBER` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `-pn 12341234 -pn 56785678`, only `-pn 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Adding: `add` [coming soon]

Adds a new client or product to the application with an automatically generated ID.

Format:

* Add a client: `add -c NAME -pn PHONE_NUMBER [-e EMAIL] [-a ADDRESS]`
* Add a product: `add -p NAME -$ UNIT_PRICE [-q QUANTITY]`

Notes:

* Adds a client with name, phone number and optional email and address.
* Adds a product with name, unit price and optional quantity.
* The user will be informed if the format of a field is incorrect, e.g. input `ten dollars` for the field `price`.
* The user will be informed if the client/product to be added already exits.

Examples:

* `add -c Ben -pn 98765432` adds a new `client` `Ben`, whose `phone number` is `98765432`.
* `add -p pen -$ 10.0 -q 150` adds a new `product` `pen` with a `unit price` of `$10.0` and there are `150`
  pens in stock.

### Editing: `edit` [coming soon]

Edits an existing client or product in the application.

Format:

* Edit a client: `edit -c ID [-n NAME] [-pn PHONE_NUMBER] [-e EMAIL] [-a ADDRESS]`
* Edit a product: `edit -p ID [-n NAME] [-$ UNIT_PRICE] [-q QUANTITY]`

Notes:

* Edits the client/product with the specified `ID`. The `ID` can be found by [`list`](#listing-list)
  or [`view`](#viewing-view) commands.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* User will be informed if the client/product of the give `ID` does not exist.

Examples:

* `edit -c 1 -n Ben` Edits the name of the client with `ID` of `1` to `Ben`.
* `edit -p 3 -n Ben10 -q 20` Edits the name of the product with `ID` of `3` to `Ben10` and the quantity to `20`.

### Viewing: `view` [coming soon]

Views a current client/product from the application.

Format:

* View a client: `view -c ID`
* View a product: `view -p ID`

Notes:

* If the product/client doesn't exist, then we inform the user that such a product/client doesn't exist.

Examples:

* `view -c 20` Views all the details of the client with `ID` of `20` including name, address, etc.
* `view -p 5` Views all the details of the product with `ID` of `5` including name, price, etc.

### Deleting: `delete` [coming soon]

Deletes the specified client/product from the tracker.

Format:

* Delete a client: `delete -c ID`
* Delete a product: `delete -p ID`

Notes:

* Deletes the client/product based on the client/product’s ID.
* The ID refers to the id shown in the displayed client/product list.
* If the product/client doesn't exist, then we inform the user that such a product/client doesn't exist.

Examples:

* `delete -c 135` deletes the client with id 135 in the tracker.
* `delete -p 197` deletes the product with id 197 in the tracker.

### Listing: `list` [coming soon]

Shows a list of all clients/products in the application

Format:

* List clients: `list -c`
* List products: `list -p`

Notes:

* Shows a list of clients and products if -c/-p is not specified.

Examples:

* `list -c` Shows a list of all clients in the application
* `list -p` Shows a list of all products in the application

### Exiting: `exit`

Exits the program.

Format: `exit`

### Loading and Saving the data [coming soon]

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: ...<br>
**A**: ...<br>

**Q**: ...<br>
**A**: ...<br>

--------------------------------------------------------------------------------------------------------------------

## Command Summary

<table>
    <tr>
        <th>Action</th>
        <th>Format, Examples</th>
    </tr>
    <tr>
        <td>
            <a href="#adding-add-coming-soon">Add</a>
        </td>
        <td>
            Add a client: <code>add -c NAME -pn PHONE_NUMBER [-e EMAIL] [-a ADDRESS]</code><br> 
            e.g., <code>add -c James Tan -pn 98765432 -e james.email@example.com -a 123, Clementi Rd</code><br>
            <br> 
            Add a product: <code>add -p NAME -$ UNIT_PRICE [-q QUANTITY]</code><br> 
            e.g., <code>add -p pen -$ 10.0 -q 120</code>
        </td>
    </tr>
    <tr>
        <td>
            <a href="#editing-edit-coming-soon">Edit</a>
        </td>
        <td>
            Edit a client: <code>edit -c ID [-n NAME] [-pn PHONE_NUMBER] [-e EMAIL] [-a ADDRESS]</code><br>
            e.g., <code>edit -c 1 -n Ben</code> Edits the name of the client with <code>ID</code> of <code>1</code> to
            <code>Ben</code>.<br>
            <br>
            Edit a product: <code>edit -p ID [-n NAME] [-$ UNIT_PRICE] [-q QUANTITY]</code><br>
            e.g., <code>edit -p 3 -n Ben10 -q 20</code> Edits the name of the product with <code>ID</code> of
            <code>3</code> to <code>Ben10</code> and the quantity to <code>20</code>.
        </td>
    </tr>
    <tr>
        <td>
            <a href="#viewing-view-coming-soon">View</a>
        </td>
        <td>
            View a client: <code>view -c ID</code><br>
            e.g., <code>view -c 20</code> Views all the details of the client with <code>ID</code> of <code>20</code>
            including name, address, etc.<br>
            <br>
            View a product: <code>view -p ID</code><br>
            e.g., <code>view -p 5</code> Views all the details of the product with <code>ID</code> of
            <code>5</code> including name, price, etc.
        </td>
    </tr>
    <tr>
        <td>
            <a href="#listing-list-coming-soon">List</a>
        </td>
        <td>
            List all client: <code>list -c</code><br>
            e.g., <code>list -c</code> Lists all the clients in the application.<br>
            <br>
            List all product: <code>list -p</code><br>
            e.g., <code>list -p</code> Lists all the products in the application.
        </td>
    </tr>
    <tr>
        <td>
            <a href="#deleting-delete-coming-soon">Delete</a>
        </td>
        <td>
            Delete a client: <code>delete -c ID </code><br> 
            e.g., <code>delete -c 20</code> Deletes all the details of the client with <code>ID</code> of 
            <code>20</code> including name, address, etc.<br>
            <br>
            Delete a product: <code>delete -p ID </code><br> 
            e.g., <code>delete -p 5</code> Deletes all the details of the product with <code>ID</code> of 
            <code>5</code> including name, price, etc.
            <br>
        </td>
    </tr>
</table>
