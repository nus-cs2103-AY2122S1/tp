---
layout: page
title: User Guide
---

Sellah is a desktop application optimized for online sellers who prefer CLI over GUI. It is an address book that
contains the contact information and order details related to clients and partners.

* Table of Contents {:toc}
    * [Quick Start](#quick-start)
    * [Features](#features)
        * [Add](#adding-add)
        * [Edit](#editing-edit)
        * [View](#viewing-view)
        * [Delete](#deleting-delete)
        * [List](#listing-list)
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
* Adds a product with name , unit price and optional quantity.
* The user will be informed if the format of a field is incorrect, e.g. input `ten dollars` for the field `price`.

Examples:
* `add -c Ben -pn 98765432` adds a new `client` `Ben`, whose `phone number` is `98765432`.
* `add -p pen -$ 10.0 -q 150` adds a new `product` `pen` with a `unit price` of `$10.0` and there are `150`
  pens in stock.

### Editing: `edit`

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

### Viewing: `view`

Views a current client/product from the application.

Format:
* View a client: `view -c ID`
* View a product: `view -p ID`

Notes:
*If the product/client doesn't exist, then we inform the user
that such a product/client doesn't exist.

Examples:
* `view -c 20` Views all the details of the client with `ID` of `20` including name, address, etc.
* `view -p 5` Views all the details of the product with `ID` of `5` including name, price, etc.

### Deleting: `delete`

1 line intro

Format:

Notes:

Examples:

### Listing: `list`

1 line intro

Format:

Notes:

Examples:

### Exiting: `exit`

Exits the program.

Format: `exit`

### Loading and Saving the data `[coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: ...<br>
**A**: ...<br>

**Q**: ...<br>
**A**: ...<br>

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | Add a client: `add -c NAME -pn PHONE_NUMBER [-e EMAIL] [-a ADDRESS]` <br> e.g., `add -c James Tan -pn 98765432 -e james.email@example.com -a 123, Clementi Rd` <br><br> Add a product: `add -p NAME -$ UNIT_PRICE [-q QUANTITY]` <br> e.g., `add -p pen -$ 10.0 -q 120`
**Edit** | Edit a client: `edit -c ID [-n NAME] [-pn PHONE_NUMBER] [-e EMAIL] [-a ADDRESS]`<br>`edit -c 1 -n Ben` Edits the name of the client with `ID` of `1` to `Ben`.<br><br>Edit a product: `edit -p ID [-n NAME] [-$ UNIT_PRICE] [-q QUANTITY]`<br>`edit -p 3 -n Ben10 -q 20` Edits the name of the product with `ID` of `3` to `Ben10` and the quantity to `20`.
**View** | View a client: `view -c ID` <br> `view -c 20` Views all the details of the client with `ID` of `20` including name, address, etc. <br><br> View a product: `view -p ID` <br> `view -p 5` Views all the details of the product with `ID` of `5` including name, price, etc.
**List** | ...
**Delete** | ...
**Exit** | `exit`
