---
layout: page
title: SoConnect User Guide
---

<!-- @@author Gordon25 -->

Made by [School of Computing](https://www.comp.nus.edu.sg/) (SoC) students, for SoC students, SoConnect is a **desktop app for SoC students to manage contacts
of Professors and Teaching Assistants, and keep track of noteworthy events.** <!-- @@author -->
With SoConnect, you can **save 
your contacts and events in one location**, and even **link events to multiple contacts**, or **bookmark
your favorite contact**.

SoConnect is **optimized for use via a _Command Line Interface (CLI)_** while still having 
the benefits of a _Graphical User Interface (GUI)_. If you can type fast, managing your contacts will be
**very easy** with SoConnect.

This user guide will help you to familiarise yourself with your SoConnect quickly and teach you the
full range of features it offers.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## How to use SoConnect User Guide

* You can click on the titles in the Table of Contents to jump to the section that you are interested in.
* You will find these icons in this user guide:
    * **:bulb: Tip** provides additional information that might be useful to you.
    * **:information_source: Note** provides supplementary information that helps you to understand this User Guide.
    * **:exclamation: Caution** cautions you against certain actions that will lead to undesirable consequences.
* You can find explanations of _italicized_ words in the [Glossary](#glossary).
* You can refer to the [Command Summary](#command-summary) for a complete overview of all SoConnect features and _commands_.


### How to read _commands_ in SoConnect

You will see _commands_ throughout this User Guide and each of them has its own _Command Format_.

This is one example of a _command_:

![Command Example](images/demo-screenshots/commandSyntax.png)

There are only 3 different parts of inputs within each _command_:
1. **_COMMAND_ WORD**

   You need to specify the _command_ word to tell SoConnect what action you want to do.

   For example, specifying `cedit` in the _command_ above will tell SoConnect to edit a contact for you.

2. **_PARAMETER_**

   The _parameter_ is the supplementary information that you need to provide for the action you want to do.

   Using the same example, specifying `2` tells SoConnect to edit the contact at **index 2** (the second contact in the list) to the name `Betsy Crower`.

3. **_PREFIX_**

   The *prefix* separates the different types of *parameters*.

   Each _prefix_ always ends with a `/`. See the [list of _prefixes_](#list-of-prefixes) for all the _prefixes_ that you can use in SoConnect.

   For example, if you use `n/`, SoConnect will recognise that the information following this _prefix_ should be a **name**.

You may view the entire list of _commands_ that you can type in SoConnect [here](#features).

<div markdown="block" class="alert alert-info">

**:information_source: About the _Command_ format:**<br>

* Words in `UPPER_CASE` are the _parameters_ that you should provide.<br>
  e.g. in `cadd n/NAME`, `NAME` is a _parameter_ that can be used as `cadd n/John Doe`.

* _Fields_ in **square brackets** are **optional**.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* _Fields_ with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family`, etc.

* Each pair of _prefixes_ and _parameters_ can be in any order.<br>
  e.g. if the _command_ specifies `n/NAME [p/PHONE_NUMBER]`, `[p/PHONE_NUMBER] n/NAME` is also acceptable.

* If a _parameter_ is expected only once in the _command_, but you specify it multiple times, **only the last occurrence** of the _parameter_ will be taken (unless otherwise stated).<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous _parameters_ for _commands_ that do not take in _parameters_ (such as `help` and `exit`) will be **ignored**.<br>
  e.g. if the _command_ specifies `help 123`, it will be interpreted as `help`.

* Date and time must follow **dd-MM-yyyy HH:mm** format (day-month-year hours:minutes).
  e.g. if the date and time is 1 May 2021 6.30pm, you should specify it as `01-05-2021 18:30`

</div>

### What happens if my _command_ is invalid?

After you enter a _command_ in SoConnect, a success message will be displayed on the message box of SoConnect.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

Unsure of where to find the **message box**? Click [here](#overview-of-soconnect) to check out the
overview of different components in SoConnect.
</div>

For example, after entering [`clist`](#listing-all-contacts-clist), SoConnect will tell you that it has successfully listed all contacts.

![Success Message](images/demo-screenshots/successMessage.png)

<br>

However, if the _command_ you entered does not fulfill the _Command Format_ specified in 
[Features](#features), an **error message** will be shown on the message box instead.
You can then correct your mistakes in the _command_ and try again.

For example, if you enter [`cview`](#viewing-a-contact-cview) **without** specifying which contact to view,
SoConnect will tell you that the _Command Format_ is invalid.

![Error Message](images/demo-screenshots/invalidCommand.png)

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed on your computer.

   <div markdown="span" class="alert alert-primary">:bulb: **Tip:**

   [Here](https://www.java.com/en/download/help/version_manual.html) is how you can check the Java Version installed on your computer.
   </div>

2. Download the latest `soconnect.jar` from [here](https://github.com/AY2122S1-CS2103T-W15-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SoConnect.

<!-- @@author Gordon25 -->
![_Home Folder_](images/demo-screenshots/homeFolder.png)
<!-- @@author -->

4. Double-click the file to start SoConnect. This is how SoConnect looks like:<br>

   ![Overview](images/demo-screenshots/overview.png)

    <div markdown="span" class="alert alert-info">:information_source: **Note:**
   SoConnect comes with some sample contacts and events upon installation.
    </div>
    <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
   If SoConnect does not start by double-clicking, you can check this 
   [alternative](#how-to-start-soconnect-using-terminal) way of starting SoConnect.
    </div>

5. Type the _command_ in the _command_ box and **press Enter** to execute it.

    <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
   Unsure of where to find the **_command_ box**? Click [here](#overview-of-soconnect) to check out the
   overview of different components in SoConnect.
    </div>

   For example, typing `help` and **pressing Enter** will open the [help window](#help-window-help).<br>
   Here are some example _commands_ you can try:

    * `elist`: [Lists all events](#listing-all-events-elist).

    * `cadd n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`:
   [Adds a contact](#adding-a-contact-cadd) named `John Doe` to SoConnect.

    * `cdelete 3` : [Deletes the third contact](#deleting-a-contact-cdelete) shown in SoConnect.

    * `eclear` : [Clears all entries of events](#clearing-all-events-eclear) from SoConnect.

    * `exit` : [Exits](#exiting-soconnect-exit) SoConnect.

6. Refer to the [Features](#features) below for details of each _command_.

_See also: [What happens if my command is invalid](#what-happens-if-my-command-is-invalid)_

--------------------------------------------------------------------------------------------------------------------

## Overview of SoConnect

This is what you will see when you open SoConnect!
![Labelled SoConnect](images/demo-screenshots/labelledSoconnectOverview.png)

1. **Menu Bar**

   The Menu Bar consists of 2 tabs: `File` and `Help`.

   Upon clicking `File`, you can choose to:
    1. **Exit** SoConnect
    2. Open **Calendar** of SoConnect

   ![File Tab](images/demo-screenshots/fileTab.png)

    <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    Click the following links to see how to [exit SoConnect](#exiting-soconnect-exit) and [open the calendar](#calendar-window-calendar) using _commands_.
    </div> <br>

   Upon clicking `Help`, you can choose to open the **Help Window** of SoConnect.
   ![Help Tab](images/demo-screenshots/helpTab.png)

    <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
   Check out how to open [the help window](#help-window-help) using _commands_.
    </div>

2. **_Command_ Box**

   This is where you can type all your _commands_ to use the [features of SoConnect](#features).

3. **Message Box**

   This is where the message from SoConnect is displayed after you have executed one _command_.

   _See Also: [What happens if my command is invalid?](#what-happens-if-my-command-is-invalid)_

5. **Contact List**

   Displays information of the contacts you saved in SoConnect.

   Refer to [Icons in Contact List](#icons-in-contact-list) to learn about the different _fields_ that each icon represents in Contact List.

6. **Event list**

   Displays information of the events you saved in SoConnect.

   Refer to [Icons in Event List](#icons-in-event-list) to learn about the different _fields_ that each icon represents in Event List.



### Icons in Contact List

![Contact Card](images/demo-screenshots/contactCard.png)
You will always find these 2 information of each contact displayed in SoConnect Contact List:
1. Contact Index
2. Contact Name

Here are the icons you might see under each contact:

Icon | _Field_
--------|------------------
**![Bookmark Icon](images/demo-screenshots/bookmarkIcon.png)** | Bookmark*
**![Email Icon](images/demo-screenshots/emailIcon.png)** | Email Address
**![Phone Number Icon](images/demo-screenshots/phoneNumberIcon.png)** | Phone Number
**![Address Icon](images/demo-screenshots/addressIcon.png)** | Address
**![Telegram Icon](images/demo-screenshots/telegramIcon.png)** | Telegram Handle
**![Website Icon](images/demo-screenshots/websiteIcon.png)** | Links/ Websites
**![Event Icon](images/demo-screenshots/eventIcon.png)** | Linked Events
**![Tag Icon](images/demo-screenshots/tagIcon.png)** | Tag

*The bookmark icon is only visible if the contact has been marked.
_See also: [Marking a contact](#marking-a-contact-cmark)_


### Icons in Event List

![Event Card](images/demo-screenshots/eventCard.png)
You will always find these 2 information of each event displayed in SoConnect Event List:
1. Event Index
2. Event Name

Here are the icons you might see under each event:

Icon | _Field_
--------|------------------
**![Bookmark Icon](images/demo-screenshots/bookmarkIcon.png)** | Bookmark*
**![Start Time Icon](images/demo-screenshots/startTimeIcon.png)** | Start Time
**![End Time Icon](images/demo-screenshots/endTimeIcon.png)** | End Time
**![Website Icon](images/demo-screenshots/websiteIcon.png)** | Links/ Websites
**![Description Icon](images/demo-screenshots/descriptionIcon.png)** | Description
**![Contact Icon](images/demo-screenshots/contactsIcon.png)** | Linked Contacts
**![Tag Icon](images/demo-screenshots/tagIcon.png)** | Tag

*The bookmark icon is only visible if the event has been marked.
_See also: [Marking an event](#marking-an-event-emark)_

--------------------------------------------------------------------------------------------------------------------

## Features

There are three main sections to SoConnect Features:
[Contact Management](#contact-management),
[Event Management](#event-management), and
[General](#general).

For each feature, you are provided with:
* Function and Description of the feature
* _Command Format_ of the feature
* Examples of some usages of the feature (All examples use the **initial sample** of contacts and events)

--------------------------------------------------------------------------------------------------------------------

### Contact Management

This section details all the features and _commands_ available in SoConnect that can help you manage your contacts:

* [Adding a contact](#adding-a-contact-cadd)
* [Clearing all contacts](#clearing-all-contacts-cclear)
* [Deleting a contact](#deleting-a-contact-cdelete)
* [Editing a contact](#editing-a-contact-cedit)
* [Finding contacts](#finding-contacts-cfind)
* [Listing all contacts](#listing-all-contacts-clist)
<!-- @@author Gordon25 -->
* [Marking a contact](#marking-a-contact-cmark)
* [Unmarking a contact](#removing-mark-of-a-contact-cunmark)
<!-- @@author -->
* [Viewing a contact](#viewing-a-contact-cview)


#### Adding a contact: `cadd`

Adds a contact to SoConnect.

**Format:** `cadd n/NAME e/EMAIL [p/PHONE_NUMBER] [a/ADDRESS] [th/TELEGRAM_HANDLE] [z/ZOOM] [t/TAG]…`

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* A contact can have any number of tags (including 0)
* You **cannot** add a contact with the **same name** as an existing contact.
</div>

**Examples:**

Input | Expected Output
------|------------------
`cadd n/Alex Doe e/e0123456@u.nus.edu a/COM1 #99-99 th/johnDoe99 t/Professor` | You should see this message in the message box: `New contact added: Alex Doe; Email: e0123456@u.nus.edu; Address: COM1 #99-99; Telegram: johnDoe99; Tags: [Professor]` <br><br> You should also see `Alex Doe` added **at the end** of your contact list: ![New Contact 1](images/demo-screenshots/caddEx1.png)
`cadd n/ Jon Cheng t/TA e/e7654321@u.nus.edu a/COM1-0201 p/87654321 t/Senior th/jonnyjohnny z/https://nus-sg.zoom.us/j/0123456789?pwd=ABCDEFGHIJKLMNOPDJFHISDFSDH` | You should see this message in the message box: `New contact added: Jon Cheng; Email: e7654321@u.nus.edu; Phone: 87654321; Address: COM1-0201; Zoom Link: https://nus-sg.zoom.us/j/0123456789?pwd=ABCDEFGHIJKLMNOPDJFHISDFSDH; Telegram: jonnyjohnny; Tags: [Senior][TA]`<br><br> You should also see `Jon Cheng` added **at the end** of your contact list: ![New Contact 2](images/demo-screenshots/caddEx2.png)

*Index of the newly added contact will be one more than the previous number of contacts.


#### Clearing all contacts: `cclear`

Clears **all** entries of contacts in SoConnect.

**Format:** `cclear`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

This **will not change the events** saved in SoConnect.
</div>


#### Deleting a contact: `cdelete`

Deletes the specified contact(s) from SoConnect.

**Format:** `cdelete INDEX1[-INDEX2]`

* Deletes the contact(s):
    * at the specified `INDEX1` or
    * between the specified range from `INDEX1` to `INDEX2` inclusively (if you provide `INDEX2`).
* `INDEX1` and `INDEX2` refer to the index number shown in the displayed contact list.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX1` and `INDEX2` **must be a positive integer**. e.g. 1, 2, 3, …
* `INDEX1` and `INDEX2` must **not be greater** than the **number of contacts** in the contact list.
</div>

**Examples:**

Input | Expected Output
--------|------------------
[`clist`](#listing-all-contacts-clist) followed by `cdelete 2` | Deletes the second contact in the contact list. <br><br> You should see these messages in the message box: <br> 1. After `clist`: `Listed all contacts` <br> 2. After `cdelete 2`: `Deleted Contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends]`
[`cfind Bernice`](#finding-contacts-cfind) followed by `cdelete 1` | Deletes the first contact in the **results of the `cfind` _command_**. <br><br> You should see these messages in the message box: <br> 1. After `cfind Bernice`: `1 contacts listed!` <br> 2. After `cdelete 1`: `Deleted Contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends]`
`cdelete 3-5` | Deletes contacts from indexes 3 to 5 (inclusive) from the **currently displayed** contact list. <br><br> You should see these messages in the message box:<br>`Deleted Contact: Charlotte Oliveiro; Email: charlotte@example.com Deleted Contact: David Li; Email: lidavid@comp.nus.edu.sg; Address: COM1-B1-0931; Telegram: lidavid; Tags: [professor][CS2103T] Deleted Contact: Irfan Ibrahim; Email: irfan@example.com; Address: Blk 47 Tampines Street 20, #17-35; Telegram: irfanx; Tags: [classmates]`


#### Editing a contact: `cedit`

Edits an **existing** contact in SoConnect.

**Format:** `cedit INDEX [n/NAME] [e/EMAIL] [p/PHONE] [a/ADDRESS] [th/TELEGRAM_HANDLE] [z/ZOOM] [dt/TAG_DELETED]… [t/TAG_ADDED]… `

* Edits the contact at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed contact list.
* You must provide **at least one** of the optional _fields_.
* The input values you provide will be used to update the existing values.
* You can use `t/` to add a tag.
* You can remove a specific tag by typing `dt/` followed by the name of the tag that you wish to remove.
* You can remove all existing tags of a contact by typing `dt/*`.
* When editing tags, the tags to be deleted will be removed first, before new tags are added.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX` **must be a positive integer**. e.g. 1, 2, 3, …
* `INDEX` must **not be greater** than the **number of contacts** in the contact list.
* You **cannot** edit a contact to the **same name** as an existing contact.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`cedit 2 p/91234567 e/agentX@thehightable.com` | Edits the **phone number** and **email address** of the second contact in the **currently displayed** contact list to be `91234567` and `agentX@thehightable.com` respectively. <br> <br>You should see this message in the message box:<br> `Edited Contact: Bernice Yu; Email: agentX@thehightable.com; Phone: 91234567; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends]` <br><br> You should also the following changes: ![Edit Contact 1](images/demo-screenshots/ceditEx1.png)
`cedit 1 n/Betsy Crower dt/*` | Edits the **name** of the first contact in the **currently displayed** contact list to be `Betsy Crower` and **clears all existing tags**. <br><br>You should see this message in your message box: `Edited Contact: Betsy Crower; Email: alexyeoh@example.com; Phone: 87438807; Address: Blk 30 Geylang Street 29, #06-40; Telegram: yeoh_alex` <br><br> You should also see the following changes: ![Edit Contact 2](images/demo-screenshots/ceditEx2.png)


#### Finding contacts: `cfind`

Finds all contacts that contain any of the given keywords that you specify.

**Format:** `cfind [KEYWORD]… [e/KEYWORD…] [p/KEYWORD…] [a/KEYWORD…] [th/KEYWORD…] [z/KEYWORD…] [t/KEYWORD…]`

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

There are **two** types of contact searches you can do in SoConnect:
1. If you **do not specify any optional _fields_** in front of the keywords you specify, e.g. `cfind KEYWORD1 KEYWORD2`,

   SoConnect will only search the names of the contacts using the keywords you provide.

2. If you specified optional _field(s)_ before a keyword, e.g. `cfind e/KEYWORD1 p/KEYWORD2`,

   SoConnect will search the emails and phone numbers of the contacts using `KEYWORD1` and `KEYWORD2` respectively.
</div>

* You must provide **at least one keyword**.
* You can provide multiple keywords without specifying any optional _fields_ e.g. `cfind John David`.
* You can only **specify each optional _field_ once**.
* You can the keywords **in any order**. e.g. Both `Hans Bo` and `Bo Hans` will return the same result.
* Partial words can be matched e.g. `Han` will match `Hans`.
* The contact(s) matching at least one keyword you provide will be returned.
  e.g. `Hans Bo` will return `Hans Gruber` and `Bo Yang`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

The search by `cfind` is case-insensitive. e.g. `hans` will match `Hans`.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`cfind alex david` | Returns contacts of `Alex Yeoh` and `David Li`. <br><br>You should see this message in the message box:<br>`2 contacts listed!`<br><br> You should also see only these **2 contacts**: ![Find Contact 1](images/demo-screenshots/cfindEx1.png)
`cfind p/926 e/nus.edu` | Returns contacts with phone numbers that contain `926` and with emails that contain `nus.edu`.<br><br>You should see this message in the message box:<br>`2 contacts listed!`<br><br> You should also see only these **2 contacts**: ![Find Contact 2](images/demo-screenshots/cfindEx2.png)


#### Listing all contacts: `clist`

Shows **all contacts** in the SoConnect, with all available details by default.

**Format:** `clist [e/] [p/] [a/] [th/] [z/] [t/]`

* Names of contacts are always shown.
* If you do not provide any optional _prefixes_, e.g `clist`, all available details of each contact will be shown.
* If you provide optional _prefixes_, it will only show the names and the _fields_ corresponding to specified _prefixes_ for each contact.
* You can provide more than one optional _prefix_.
* You can specify the optional _prefix_ **in any order**. e.g. both `clist e/ p/` and `clist p/ e/` will show only the names, email addresses and phone numbers of each contact.
* _Fields_ of a contact that have no value will not appear e.g. if a contact does not have a zoom link, typing `clist z/` will not display the zoom link of this contact.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

**Do not** add extraneous values after each optional _prefix_ you specify.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`clist` | Shows **all available details** of each contact in SoConnect. <br><br> You should see this message in the message box:<br> `Listed all contacts`
`clist e/ p/` | Shows **only the names, email addresses and phone numbers** (if available) of each contact in SoConnect. <br><br> You should see this message in the message box:<br> `Listed all contacts` <br><br> You should also see the contacts list displays **only the specified _fields_**: ![List Contact 1](images/demo-screenshots/clistEx.png)

<!-- @@author Gordon25 -->

#### Marking a contact: `cmark`

Marks the specified contact(s).

**Format:** `cmark INDEX [INDEX]...`

* Marks the contact at `INDEX` and **pins it at the top** of the contact list.
* You can specify more than one `INDEX`, e.g `cmark 1 2`, the order in which the marked contacts appear will be in reverse order to the order you specify their corresponding indexes.
* `INDEX` refers to the index number shown in the **currently displayed** contact list.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX` **must be a positive integer**, e.g. 1, 2, 3,…
* `INDEX` must **not be greater** than the **number of contacts** in the contact list.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`cmark 2` | Marks the second contact of the **currently displayed** contact list in SoConnect. <br><br> You should see this message in the message box:<br> `Marked Contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends]` <br><br> You should see `Bernice Yu` **pinned to the top** of your contact list with the **Bookmark Icon**: ![Mark Contact](images/demo-screenshots/cmarkEx1.png)
`cmark 4 5` | Marks the fourth and fifth contacts of the **currently displayed** contact list in SoConnect. <br><br> You should see this message in the message box:<br> `Marked contact: Irfan Ibrahim; Email: irfan@example.com; Address: Blk 47 Tampines Street 20, #17-35; Telegram: irfanx; Tags: [classmates] Marked contact: David Li; Email: lidavid@comp.nus.edu.sg; Address: COM1-B1-0931; Telegram: lidavid; Tags: [professor][CS2103T]` <br><br> You should see `Irfan Ibrahim` and `David Li` **pinned to the top** of your contact list in that order with the **Bookmark Icon**: ![Mark Contact](images/demo-screenshots/cmarkEx2.png)

_See also: [Unmarking a contact](#removing-mark-of-a-contact-cunmark)_


#### Removing mark of a contact: `cunmark`

Unmarks the specified contact(s).

**Format:** `cunmark INDEX [INDEX]...`

* Unmarks the contact at `INDEX1`.
* You may unmark **more than one contact at a time** by specifying multiple indexes, eg `cunmark 1 2`, the indexes in which the contacts appear will be **in the order in which** you specify them
* `INDEX`  refers to the index number shown in the displayed contact list.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX` **must be a positive integer**, e.g. 1, 2, 3,…
* `INDEX` must **not be greater** than the **number of contacts** in the contact list.
*  You must check that the contact indexed at `INDEX` is **initially marked**.
</div>

**Examples:**

Input | Expected Output
--------|------------------
[`cmark 2 3`](#marking-a-contact-cmark) followed by `cunmark 1` | Unmarks the first contact of the **currently displayed** contact list in SoConnect. <br><br> You should see these messages in the message box:<br> 1. After `cmark 2 3`: `Marked contact: Charlotte Oliveiro; Email: charlotte@example.com Marked contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends]` <br> 2. After `cunmark 1`: `Unmarked contact: Charlotte Oliveiro; Email: charlotte@example.com` <br><br> You should see `Charlotte Oliveiro` moved to the **bottom of your marked contact list** without the **Bookmark Icon**: ![Unmark Contact](images/demo-screenshots/cunmarkEx1.png)
[`cmark 1 2 3`](#marking-a-contact-cmark) followed by `cunmark 1 2` | Unmarks the first and second contact of the **currently displayed** contact list in SoConnect. <br><br> You should see these messages in the message box:<br> 1. After `cmark 1 2 3`: `Marked contact: Charlotte Oliveiro; Email: charlotte@example.com Marked contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends] Marked contact: Alex Yeoh; Email: alexyeoh@example.com; Phone: 87438807; Address: Blk 30 Geylang Street 29, #06-40; Telegram: yeoh_alex; Tags: [friends]` <br> 2. After `cunmark 1 2`: `Unmarked contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends] Unmarked contact: Charlotte Oliveiro; Email: charlotte@example.com` <br><br> You should see `Bernice Yu` and `Charlotte Oliveiro` moved to the **bottom of your marked contact list** without the **Bookmark Icon**: ![Unmark Contact](images/demo-screenshots/cunmarkEx2.png)

_See also: [Marking a contact](#marking-a-contact-cmark)_

<!-- @@author -->

#### Viewing a contact: `cview`

Views a contact with all details fully shown.

**Format:** `cview INDEX`

* displays only the contact at the specified `INDEX`.
* All truncated details of the contact you want to view will be expanded fully.
* `INDEX` refers to the index number shown in the displayed contact list.

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:** <br>
* `INDEX` **must be a positive integer**. e.g. 1, 2, 3, … <br>
* `INDEX` must **not be greater** than the **number of contacts** in the contact list.

</div>

**Examples:**

Input | Expected Output
--------|------------------
`cview 2` | Shows all details of the second contact of the **currently displayed** in SoConnect **fully**. <br> You should see this message in the message box:<br> `Viewing Contact: Bernice Yu; Email: berniceyu@example.com; Phone: 99272758; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18; Zoom Link: nus-sg.zoom.us/j/08382435376?pwd=Oow3u9N098nh8nsdLp0; Telegram: bbernicee; Tags: [TA][friends]` <br><br> You should see this change of your **viewed** contact: ![View Contact](images/demo-screenshots/cviewEx.png)


### Event Management

This section details all the features and _commands_ available in SoConnect that can help you with managing your events:
* [Adding an event](#adding-an-event-eadd)
* [Clearing all event](#clearing-all-events-eclear)
* [Deleting an event](#deleting-an-event-edelete)
* [Editing an event](#editing-an-event-eedit)
* [Finding events](#finding-events-efind)
* [Linking an event to contacts](#linking-an-event-to-contacts-elink)
* [Listing all events](#listing-all-events-elist)
* [Marking an event](#marking-an-event-emark)
* [Removing mark of an event](#removing-mark-of-an-event-eunmark)
* [Sorting events](#sorting-events-esort)
* [Unlinking an event from contacts](#unlinking-an-event-from-contacts-eunlink)
* [Viewing an event](#viewing-an-event-eview)



#### Adding an event: `eadd`

Adds an event to SoConnect.

**Format:** `eadd n/NAME at/START_TIME [end/END_TIME] [d/DESCRIPTION] [a/ADDRESS] [z/ZOOM] [t/TAG]…​`

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* An event can have any number of tags (including 0)
* You **cannot** add an event with the **same name** as an existing event.
</div>

<div markdown="block" class="alert alert-info">:information_source: **Note:**

* Start time and End Time should be of format **dd-MM-yyyy HH:m** (date-MONTH-year HOUR:minute in 24-hour format).
* End Time should be **chronologically after** the Start Time.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`eadd n/Summer Party at/12-12-2021 15:12 a/123, Clementi Rd, 1234665 t/fun` | You should see this message in the message box:<br> `New event added: Summer Party; Start: 12-12-2021 15:12; Address: 123, Clementi Rd, 1234665; Tags: [fun]` <br><br> You should also see `Summer Party` **at the end** of your event list: ![New Event](images/demo-screenshots/eaddEx.png)

*Index of the newly added event will depend on your previous number of events.

<!-- @@author Gordon25 -->

#### Clearing all events: `eclear`

Clears all entries of events from SoConnect.

**Format:** `eclear`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This will not change the contacts saved in SoConnect. 
</div>


#### Deleting an event: `edelete`

Deletes the specified event(s) from SoConnect.

**Format:** `edelete INDEX1[-INDEX2]`

* Deletes the event(s) at:
    * the specified `INDEX1` or
    * between the specified range from `INDEX1` to `INDEX2` inclusively (if you provide `INDEX2`).
* `INDEX1` and `INDEX2` refer to the index numbers shown in the displayed event list.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX1` and `INDEX2` **must be a positive integer**. e.g. 1, 2, 3, …
* `INDEX1` and `INDEX2` must **not be greater** than the **number of events** in the event list.
</div>

**Examples:**

Input | Expected Output
--------|------------------
[`elist`](#listing-all-events-elist) followed by `edelete 2` | Deletes the second event from SoConnect. <br><br> You should see these messages in the message box: <br>1. After `elist`: `Listed all events` <br>2. After `edelete 2`: `Deleted Event: Basketball training; Start: 01-11-2021 20:00; End: 01-11-2021 21:00; Description: Meeting every week; Address: NUS Sport Centre; Tags: [CCA][Recurring]`
[`efind Class`](#finding-events-efind) followed by `edelete 1` | Deletes the first event from the **results of the `efind` _command_**.<br><br> You should see these messages in the message box: <br>1. After `efind class`: `1 events listed!` <br>2. After `edelete 1`: `Deleted Event: Dance class; Start: 13-11-2021 20:00; End: 13-11-2021 22:00; Description: Dancing is my passion. I like pole dancing.; Address: NUS UTown; Tags: [CCA][Recurring]`
`edelete 1-2` | Deletes events from index 1 to 2 from the **currently displayed** event list. <br><br> You should see these messages in the message box: <br> `Deleted Event: CS2103T project meeting; Start: 10-10-2021 21:00; End: 10-10-2021 22:00; Zoom Link: nus-sg.zoom.us/j/21342513543; Tags: [Recurring][CS2103T]` <br> `Deleted Event: Basketball training; Start: 01-11-2021 20:00; End: 01-11-2021 21:00; Description: Meeting every week; Address: NUS Sport Centre; Tags: [CCA][Recurring]`


#### Editing an event: `eedit`

Edits an existing event in SoConnect.

**Format:** `eedit INDEX [n/NAME] [at/START_TIME] [end/END_TIME] [d/DESCRIPTION] [a/ADDRESS] [z/ZOOM] [dt/TAG_DELETED]…​ [t/TAG_ADDED]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** 

You must provide **at least one** of the optional _fields_.
</div>

* Edits the event at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed event list.
* Existing values will be updated to the input values.
* You can use `t/` to add a tag.
* You can remove a specific tag by typing `dt/` followed by the name of the tag that you wish to remove.
* You can remove all existing tags of an event by typing `dt/*`.
* When editing tags, the tags to be deleted will be removed first, before new tags are added.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX` **must be a positive integer**. e.g. 1, 2, 3, …
* Start time and End Time should be of format **dd-MM-yyyy HH:mm** (date-MONTH-year HOUR:minute in 24-hour format).
* End Time should be **chronologically after** the Start Time.
* You **cannot** edit an event to the **same name** as an existing event.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`eedit 2 n/CS2103T Exam dt/CCA t/Hard` | Changes the name of the second event in the **currently displayed** event list to `CS2103T Exam`, deletes the tag `CCA` and adds the tag `Hard`. <br><br> You should see this message in the message box: <br> `Edited Event: CS2103T Exam; Start: 01-11-2021 20:00; End: 01-11-2021 21:00; Description: Meeting every week; Address: NUS Sport Centre; Tags: [Recurring][Hard]` <br><br> You should also see these changes: ![Edit Event](images/demo-screenshots/eeditEx.png)



#### Finding Events: `efind`

Finds all events that contain any of the given keywords based on your search type.

**Format:** `efind [KEYWORD]… [at/KEYWORD…] [end/KEYWORD…] [d/KEYWORD…] [a/KEYWORD…] [z/KEYWORD…] [t/KEYWORD…]`
<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

There are two types of event searches you can do in SoConnect:
1. If you **do not specify any optional _fields_ before your keyword(s)**, e.g. `efind KEYWORD1 KEYWORD2`,

   You will only search the names of the events based on the keyword(s) provided.

2. If you specify any _prefix_ before your keyword(s), e.g. `efind a/KEYWORD1 d/KEYWORD2`,

   You will search the addresses and descriptions of the events based on `KEYWORD1` and `KEYWORD2` respectively.
</div>

* You need to provide **at least one keyword**.
* You can provide multiple keywords without specifying any _prefixes_.
* You can only **specify each optional _field_ once**.
* Partial words can be matched e.g. `Exa` will match `CS2103T Exam`.
* Events matching at least one keyword will be returned.
  e.g. `Exam Hard` will return `Hard Exam`, `CS1101S Exams`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

The search by `efind` is case-insensitive. e.g. `exams` will match `Exams`.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`efind meet` | Displays events with names that contain `meet`. <br><br> You should see this message in the message box:<br> `1 events listed!` <br><br> You should also see only this **one event**: ![Find Event 1](images/demo-screenshots/efindEx1.png)
`efind t/CS2103T Intern` | Displays events with tags that contain `CS2103T` and `Intern`.<br><br> You should see this message in the message box:<br> `2 events listed!` <br><br> You should also see only these **two events**: ![Find Event 2](images/demo-screenshots/efindEx2.png)

<!-- @@author -->

#### Linking an event to contacts: `elink`

Links the specified event to one or more contacts.

**Format:** `elink EVENT_INDEX c/CONTACT_INDEX [c/CONTACT_INDEX]…`

* Links the event at `EVENT_INDEX` to the contact(s) at `CONTACT_INDEX`.
* `EVENT_INDEX` refers to the index number shown in the displayed event list.
* `CONTACT_INDEX` refers to the index number shown in the displayed contact list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

`EVENT_INDEX` and `CONTACT_INDEX` **must be a positive integer**, e.g. 1, 2, 3,…
</div>

**Examples:**

Input | Expected Output
--------|------------------
`elink 2 c/1 c/2` | Links the second event in the **currently displayed** event list to the contacts with index **1 and 2** in the **currently displayed** contact list. <br><br>You should see this in your SoConnect: ![Link](images/demo-screenshots/elinkEx.png)

_See Also: [Unlinking an event from contacts](#unlinking-an-event-from-contacts-eunlink)_


#### Listing all events: `elist`

Displays all events in SoConnect, with all details by default.

**Format:** `elist [at/] [end/] [d/] [a/] [z/] [t/]`

* Event names are always displayed.
* When no optional _prefixes_ are provided, e.g. `elist` , all available details of each event will be displayed.
* When optional _prefixes_ are provided, only the names and the corresponding specified _fields_ for each event will be shown.
* You can provide more than one optional _prefixes_.
* The order of the _prefixes_ does not matter. e.g. both `elist d/ at/` and `elist at/ d/` will only show the names, descriptions and starting times of each event.
* _Fields_ of an event that have no value will not be shown.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

**Do not** add extraneous values after each optional _prefix_ you specify.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`elist` | Shows **all events** in SoConnect with **all available details** for each event. <br><br> You should see this message in the message box: <br>`Listed all events`
`elist d/ at/` | Shows **all events** in SoConnect with **only their names, start date and time and description** (if available). You should see this message in the message box: <br>`Listed all events` <br><br> You should also see the event list displays all events with **only the specified _fields_**: ![List Events](images/demo-screenshots/elistEx.png)


#### Marking an event: `emark`

Marks the specified event(s).

**Format:** `emark INDEX [INDEX]…`

* Marks the event at `INDEX` and **pins it to the top** of the event list.
* You can specify more than one `INDEX`, e.g. `emark 1 2`, the order in which the marked events appear will be in reverse order to the order you specify their corresponding indexes.
* `INDEX` refers to the index number shown in the **currently displayed** event list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

`INDEX` **must be a positive integer**, e.g. 1, 2, 3,…<br>
`INDEX` must **not be greater** than the **number of events** in the event list.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`emark 2` | Marks the second event of **currently displayed** event list in SoConnect. <br><br> _The expected display is similar to [marking a contact](#marking-a-contact-cmark)_

_See Also: [Removing mark of an event](#removing-mark-of-an-event-eunmark)_


#### Removing mark of an event: `eunmark`

Unmarks the specified event(s).

**Format:** `eunmark INDEX [INDEX]...`

* Unmarks the event at `INDEX`.
* You may unmark **more than one event** by specifying multiple indexes, eg `eunmark 1 2`, the indexes in which the events appear will be **in the order in which** you specify them.
* `INDEX` refers to the index number shown in the displayed event list.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* `INDEX` **must be a positive integer**, e.g. 1, 2, 3,…
* `INDEX` must **not be greater** than the **number of events** in the event list.
* You must ensure that the event indexed at `INDEX` is **initially marked**.
</div>

**Examples:**

Input | Expected Output
--------|------------------
`eunmark 2` | Unmarks the second event of **currently displayed** event list in SoConnect.<br><br> _The expected display is similar to [Removing mark of a contact](#removing-mark-of-a-contact-cunmark)_
`eunmark 2 4` | Unmarks the second and fourth event in SoConnect.

_See Also: [Marking an event](#marking-an-event-emark)_


#### Sorting events: `esort`

Sorts all events by start time and displays all upcoming or ongoing events.

**Format:** `esort`

**Examples:**
![Sort example](images/demo-screenshots/sortResult.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:**

Events that have ended **will not be shown**
</div>


#### Unlinking an event from contacts: `eunlink`

**Format:** `eunlink EVENT_INDEX c/CONTACT_INDEX [c/CONTACT_INDEX]…`

Unlinks the specified event and the specified contact(s).

**Examples:**

Input | Expected Output
--------|------------------
`eunlink 2 c/1` | Unlinks the second event in the **currently displayed** event list from the first contact in the **currently displayed** contact list. <br><br>You should see this in your SoConnect: ![unlink contact](images/demo-screenshots/eunlinkEx1.png)
`eunlink 2 c/*` | Unlinks the second event in the **currently displayed** event list from **all of its linked contacts**. <br><br>You should see this in your SoConnect: ![unlink all contacts](images/demo-screenshots/eunlinkEx2.png)

_See Also: [Linking an event to contacts](#linking-an-event-to-contacts-elink)_


#### Viewing an event: `eview`

Views an event with all details fully shown.

**Format:** `eview INDEX`

* Views the event at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed event list.
* All truncated details of the event will be shown fully.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

`INDEX` **must be a positive integer**. e.g. 1, 2, 3, …
</div>

**Examples:**

Input | Expected Output
--------|------------------
`eview 1` | Shows all details of the first event in the **currently displayed** event list **fully**. <br><br> _The expected display is similar to [Viewing a contact](#viewing-a-contact-cview)_


### General

This section details all the other features and _commands_ available in SoConnect that can enhance your SoConnect experience:
* [Calendar Window](#calendar-window-calendar)
* [Exiting SoConnect](#exiting-soconnect-exit)
* [Help Window](#help-window-help)
* [Redo a _command_](#redo-a-command-redo)
* [Undo a _command_](#undo-a-command-undo)

#### Calendar Window: `calendar`

Shows a calendar of all the events.

**Format:** `calendar`

* You can view the calendar in 4 different views:
    * Daily
    * Weekly
    * Monthly
    * Yearly
      ![calendar](images/demo-screenshots/Calendar.png)


<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* Alternatively, you can view the calendar using the top menu bar via `File -> Calendar` or press `F2`.
* If an event does not have a specified end date and time, the calendar will treat the duration of the event as **one hour**.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**

[Undo](#undo-a-command-undo) and [redo](#redo-a-command-redo) will not change the state of the calendar.
You should close the calendar window before performing any undo or redo operations.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Any changes made in the calendar window will not be saved. 
**Do not attempt to add new events using the calendar window.** 
Doing so might result in a crash and your data may be lost.
</div>



#### Exiting SoConnect: `exit`

Exits and closes SoConnect.

**Format:** `exit`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

Alternatively, you can exit SoConnect using the top menu bar via `File -> Exit`.
</div>


#### Help Window: `help`

Displays a summary of all _commands_ in SoConnect User Guide.

**Format:** `help`

![help message](images/demo-screenshots/helpWindow.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

Alternatively, you can view the help window using the top menu bar via `Help -> Help` or press `F1`.
</div>


#### Redo a _command_: `redo`

Restores SoConnect to a previously undone state from its history.

**Format:** `redo`

**Examples:**

Input | Expected Output
--------|------------------
[`edelete 1`](#deleting-an-event-edelete) followed by [`undo`](#undo-a-command-undo) then `redo` | First **restores the deleted event** in the event list. <br>Then `redo` will **delete the same event again**.

_See Also: [Undo a command](#undo-a-command-undo)_


#### Undo a _command_: `undo`

Restores SoConnect to its previous state from its history.

**Format:** `undo`

**Examples:**

Input | Expected Output
--------|------------------
[`cadd n/John Doe e/john@gmail.com`](#adding-a-contact-cadd) followed by `undo` | **Removes the added** contact from the contact list.

_See Also: [Redo a command](#redo-a-command-redo)_

<div markdown="span" class="alert alert-info">:information_source: **Note:**

[Undo](#undo-a-command-undo) and [redo](#redo-a-command-redo) will only work for _commands_ listed in the
[Contact Management](#contact-management) and [Event Management](#event-management) features section.

_Commands_ listed in the [General](#general) section are not undoable.
</div>

--------------------------------------------------------------------------------------------------------------------

## SoConnect Saved Data

### Saving the data

SoConnect data is saved in the _hard disk_ automatically after any _command_ that changes the data.
There is no need to save manually.

### Editing the data file

SoConnect data are saved as a _JSON file_ `[JAR file location]/data/soconnect.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file make its format invalid, 
SoConnect will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Others

### FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install SoConnect in the other computer and copy over the contents from your previous SoConnect _JSON file_ to the
empty data file SoConnect creates on the other Computer.


#### Copying Details and Opening Hyperlinks

![clickable links](images/demo-screenshots/clickableLinkExample.png)

**Q**: How do I copy the email address of a contact?<br>
**A**: You can copy any specific details of a contact or an event just by clicking on that detail! <br>
As shown on the image above, clicking on the `email` of `Charlotte Oliveiro` will copy her Email Address.

**Q**: Can SoConnect automatically open a hyperlink on my browser?<br>
**A**: You can open any hyperlinks that you have included in a contact or in an event. This includes
telegram handles and Zoom meeting links.<br>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

Clickable hyperlinks are underlined in blue
</div>

Referring back to the same image, if you click on the zoom link saved under `Charlotte Oliveiro`,
SoConnect will help you open the link on your browser automatically.


#### Linked Contacts and Events

**Q**: How do I view the contacts linked to a particular event?<br>
**A**: Click on the particular event card in the panel containing events.
Then click on the yellow boxes which are links to the contacts.
The linked contacts will be displayed on the contact panel on the left.
![View links of event](images/demo-screenshots/ClickLinksEvent.png)

**Q**: How do I view the events linked to a particular contact?<br>
**A**: Click on the particular contact card in the panel containing contacts.
Then click on the yellow boxes which are links to the events.
The linked events will be displayed on the event panel on the right.
![View links of contact](images/demo-screenshots/ClickLinksContact.png)

**Q**: What is the purpose of using links?<br>
**A**: Links are a form of **relationship between the contacts and the events** saved in SoConnect.
Typically, we link an event to a contact if the contact of a **participant** of the event.
For instance, you can link your *professor* to the lecture.


### How to start SoConnect using Terminal

1. Open the terminal (For [MacOS](https://support.apple.com/en-sg/guide/terminal/welcome/mac) or Linux)
   or Command Prompt (For [Windows](https://www.howtogeek.com/235101/10-ways-to-open-the-command-prompt-in-windows-10/)).

2. Navigate to the folder containing `soconnect.jar`.
   See the tutorial for [Windows](https://www.howtogeek.com/659411/how-to-change-directories-in-command-prompt-on-windows-10/),
   [MacOS or Linux](https://www.macworld.com/article/221277/command-line-navigating-files-folders-mac-terminal.html)
   (Linux uses the same _command_ for navigating folders).

3. Enter the following _command_: `java -jar soconnect.jar`. The SoConnect window should open.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**

* If you are still unable to open the file,
  [check your Java version](https://www.java.com/en/download/help/version_manual.html)
  again and make sure it is version 11.
* [Download Java 11](https://www.oracle.com/java/technologies/downloads/) if you have not done so.
</div>


--------------------------------------------------------------------------------------------------------------------
## List of _Prefixes_

**Contact Management**

_Prefix_ | _Parameter_ Type
--------|------------------
**`a/`** | Address
**`dt/`** | Tag to be deleted
**`e/`** | Email Address
**`n/`** | Name
**`p/`** | Phone Number
**`t/`** | Tag
**`th/`** | Telegram Handle
**`z/`** | Links/ Websites

**Event Management**

_Prefix_ | _Parameter_ Type
--------|------------------
**`a/`** | Address
**`at/`** | Start Date and Time
**`c/`** | Contact index (for linking)
**`d/`** | Description
**`dt/`** | Tag to be deleted
**`end/`** | End Date and Time
**`n/`** | Name
**`t/`** | Tag
**`z/`** | Links/ Websites


## _Command_ Summary

**Contact Management**

Action | Format, Examples
--------|------------------
**[Add](#adding-a-contact-cadd)** | `cadd n/NAME e/EMAIL [p/PHONE_NUMBER] [a/ADDRESS] [th/TELEGRAM_HANDLE] [z/ZOOM] [t/TAG]…​` <br> e.g., `cadd n/Alex Doe e/e0123456@u.nus.edu a/COM1 #99-99 th/johnDoe99 t/Professor`
**[Clear](#clearing-all-contacts-cclear)** | `cclear`
**[Delete](#deleting-a-contact-cdelete)** | `cdelete INDEX1[-INDEX2]`<br> e.g. `cdelete 3` <br> e.g. `cdelete 1-5`
**[Edit](#editing-a-contact-cedit)** | `cedit INDEX [n/NAME] [e/EMAIL] [p/PHONE] [a/ADDRESS] [th/TELEGRAM_HANDLE] [z/ZOOM] [dt/TAG_DELETED]…​ [t/TAG_ADDED]…​​`<br> e.g.`cedit 2 p/91234567 e/agentX@thehightable.com` <br> e.g. `cedit 1 n/Betsy Crower dt/*`
**[Find](#finding-contacts-cfind)** | `cfind [KEYWORD]… [e/KEYWORD…] [p/KEYWORD…] [a/KEYWORD…] [th/KEYWORD…] [z/KEYWORD…] [t/KEYWORD…]`<br> e.g. `cfind alex david`
**[List](#listing-all-contacts-clist)** | `clist [e/] [p/] [a/] [th/] [z/] [t/]` <br> e.g. `clist` <br> e.g. `clist e/ p/`
**[Mark](#marking-a-contact-cmark)** | `cmark INDEX`<br> e.g. `cmark 2`
**[Remove mark](#removing-mark-of-a-contact-cunmark)** | `cunmark INDEX [INDEX]…`<br> e.g. `cunmark 2` <br> e.g. `cunmark 2 3`
**[View](#viewing-a-contact-cview)** | `cview INDEX`<br> e.g. `cview 2`

**Event Management**

Action | Format, Examples
--------|------------------
**[Add](#adding-an-event-eadd)** | `eadd n/NAME at/START_TIME [end/END_TIME] [d/DESCRIPTION] [a/ADDRESS] [z/ZOOM] [t/TAG]…​ ` <br> e.g., `eadd n/Summer Party at/12-12-2021 15:12 a/123, Clementi Rd, 1234665 t/fun`
**[Clear](#clearing-all-events-eclear)** | `eclear`
**[Delete](#deleting-an-event-edelete)** | `edelete INDEX1[-INDEX2]`<br> e.g., `edelete 3` <br> e.g., `edelete 1-5`
**[Edit](#editing-an-event-eedit)** | `eedit INDEX [n/NAME] [at/START_TIME] [end/END_TIME] [d/DESCRIPTION] [a/ADDRESS] [z/ZOOM] [dt/TAG_DELETED]…​ [t/TAG_ADDED]…​`<br> e.g.,`eedit 2 n/CS2103T Exam dt/CCA t/Hard` <br> e.g., `eedit 3 dt/*`
**[Find](#finding-events-efind)** | `efind [KEYWORDS]… [at/KEYWORD…] [end/KEYWORD…] [d/KEYWORD…] [a/KEYWORD…] [z/KEYWORD…] [t/KEYWORD…]` <br> e.g., `efind meet` <br> e.g., `efind t/CS2103T Intern`
**[Link](#linking-an-event-to-contacts-elink)** | `elink EVENT_INDEX c/CONTACT_INDEX [c/CONTACT_INDEX]...`<br> `elink 2 c/1 c/2`
**[List](#listing-all-events-elist)** | `elist [at/] [end/] [d/] [a/] [z/] [t/]` <br> e.g., `elist` <br> e.g., `elist d/ at/`
**[Mark](#marking-an-event-emark)** | `emark INDEX`<br> e.g. `emark 2`
**[Remove mark](#removing-mark-of-an-event-eunmark)** | `eunmark INDEX [INDEX]…`<br> e.g. `eunmark 2` <br> e.g. `eunmark 2 4`
**[Sort](#sorting-events-esort)** | `esort`
**[Unlink](#unlinking-an-event-from-contacts-eunlink)** | `eunlink EVENT_INDEX c/CONTACT_INDEX [c/CONTACT_INDEX]...`<br> e.g., `eunlink 2 c/1` <br> e.g., `eunlink 3 c/*`
**[View](#viewing-an-event-eview)** | `eview INDEX`<br> e.g. `eview 1`

**General**

Action | Format, Examples
--------|------------------
**[Calendar](#calendar-window-calendar)** | `calendar`
**[Exit](#exiting-soconnect-exit)** | `exit`
**[Help](#help-window-help)** | `help`
**[Redo](#redo-a-command-redo)** | `redo`
**[Undo](#undo-a-command-undo)** | `undo`

________________________________________________________________________________________________________________

## Glossary

Word | Explanation
--------|------------------
**Command Line Interface (CLI)** | Text-based application where users interact with the application by **typing in texts/ commands**.
**Command** | A string of words and characters you type to perform an action, each command has its own **_Command format_**.
**Command Format** | The structure that a _command_ must abide by for it to be executed.
**Parameter(s)** | Information supplied by the user to the application when executing certain _commands_.
**Prefix** | A tag, consisting of a slash - "/", with one or a few characters to denote information about a certain **_field_**.
**Field(s)** | The information type within each contact or event <br> For example, **Name** and **Address** of a contact are _fields_ of a contact.
**Graphical User Interface (GUI)** | How the application appears to the user on his/her screen.
**Hard Disk** | Device in a computer that is specialized in storing data permanently.
**Home Folder** | Folder which the application file is saved on the computer.
**JavaScript Object Notation (JSON) File** | The file that is used by the application to load and save data of the application in a human-readable format.
