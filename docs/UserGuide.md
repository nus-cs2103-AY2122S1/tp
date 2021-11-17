---
layout: page
title: User Guide
---
Hello there, fellow SociaLites! Welcome to the User Guide for __SociaLite__, a Command Line Interface (CLI) app that connects you with your contact's social media pages! 

You may be a first-time user trying to set up SociaLite or a returning user hoping to explore advanced features. Fret not, all you need to know about this app can be found in this User Guide!
Simply follow the path that corresponds to your needs.

| If you are a...| You should...                                                            |
| -------------- | ------------------------------------------------------------------------ |
| new user       | View our [Quick Start](#quick_start) guide to set up SociaLite and try out the features. |
| returning user | Jump straight to our [Commands](#commands) section to recall how our features work!   |


--------------------------------------------------------------------------------------------------------------------
## Navigation Menu

<div markdown="span" class="alert alert-warning">**[Quick Start](#quick_start)**<br>
This section teaches you how to install **SociaLite** and includes sample commands for you to try out.
</div>

<div markdown="span" class="alert alert-warning">**[Standard Notations](#standard_notations)**<br>
This section teaches you how to interpret instructions in this User Guide.
</div>

<div markdown="span" class="alert alert-warning">**[Commands](#commands)**<br>
This section gives you comprehensive details of all the commands on SociaLite.
</div>

<div markdown="span" class="alert alert-warning">**[FAQ](#faq)**<br>
This section answers frequently asked questions about SociaLite.
</div>

<div markdown="span" class="alert alert-warning">**[Command Index](#index)**<br>
This section contains a glossary of all the commands and quick examples for reference.
</div>

--------------------------------------------------------------------------------------------------------------------

## Quick Start <a name="quick_start"></a>

1. Ensure you have `Java 11` or above installed on your Computer.
   If you already have `Java` installed, you might want to type in `java -version` in your command window to ensure you are running on the correct version of `Java`!
   If you don't have `Java 11`, simply click [here](https://www.oracle.com/java/technologies/downloads/), and navigate to the "Java SE Development Kit 11.x.xx" section to download the latest Java Development Kit.
2. Download the latest `socialite.jar` from [here](https://github.com/AY2122S1-CS2103T-F11-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your SociaLite.
4. Double-click the file to start the app. SociaLite should start in a few seconds! The app contains some sample data for you to experiment with to get familiar with the commands.<br>
   ![Ui](images/UG/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Here are some sample commands to get you started with SociaLite:

   * **`add`** `add n/Alex Yeoh p/87438807 t/friends fb/alex.yeoh ig/alex.yeoh tele/alyeoh tiktok/alex.yeoh`: Adds a contact named `Alex Yeoh` to SociaLite.

   * **`find`** `find Alex` : Searches SociaLite for a contact named `Alex` and displays the contact's information.

   * **`list`** : Displays all contacts stored on SociaLite.

   * **`edit`** `edit 1 n/Alexis Yeoh` : Edits the name of the contact at the first position of the list to `Alexis Yeoh`.

   * **`delete`** `delete 3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts. You will find this handy when deleting the sample contacts stored on SociaLite.

   * **`exit`** : Exits the app.
6. Refer to the [Commands](#commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Standard Notations <a name="standard_notations"></a> 

<div markdown="block" class="alert alert-info">

**:information_source: Hold your horses SociaLites! Before you venture any further, here are some standard notations for you to take note of:**<br>

* You provide words in `UPPER_CASE` as input.<br>
  e.g. in `add n/NAME`, `NAME` is an input which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used any number of times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* The `|` vertical bar indicates a choice that you have to make between the left and right options.<br>
  e.g. in `[date/NAME:YYYY-MM-DD[:monthly|:yearly]]`, you can enter `date/Birthday:2021-01-01:yearly` to indicate an annual event called `Birthday` that repeats on 1 Jan yearly.

* You may type your inputs in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If an input for a particular field is expected only once in the command but you specified it multiple times, only the last occurrence of the input will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* If you type inputs for commands that do not require any additional information (such as `list`, `exit` and `clear`), they will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

As you explore the features that SociaLite has to offer, look out for these boxes:

The blue boxes tell you the acceptable fields that you can provide for a particular command:

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`add n/NAME p/PHONE_NUMBER [t/TAG]…​ [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​ [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]`
* Detail 1
* Detail 2
</div>

If you still aren't sure of how a particular command works, the green boxes provide you with examples that you can try out:

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `add n/Alex Yeoh p/87438807 t/friends fb/alex.yeoh ig/alex.yeoh tele/alyeoh tiktok/alex.yeoh date/birthday:2000-01-01:yearly`

Adds a new contact called "Alex Yeoh" with phone number "87438807" to SociaLite.
</div>

--------------------------------------------------------------------------------------------------------------------

## Commands <a name="commands"></a>

This section provides you with comprehensive details of all the commands available on SociaLite. 
Simply click on any command below to learn more about it!

| `COMMAND` | Description |
| --------- | ----------- |
| [`add n/NAME p/PHONE_NUMBER ...`](#add) | Add a contact |
| [`list`](#list) | List all contacts |
| [`CLICK`](#click) | Access a contact's social media page |
| [`↑`/`↓`](#scroll) | Track history of commands |
| [`edit INDEX ...`](#edit) | Edit a contact |
| [`edit INDEX [t/TAG]`](#edit_tag) | Create / Edit tags for existing contacts |
| [`edit INDEX t/`](#delete_tag) | Delete all tags from a contact |
| [`edit INDEX [p/PLATFORM]`](#edit_platform) | Modify social media handles for a contact |
| [`edit INDEX [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​`](#edit_dates) | Modify dates for a contact |
| [`delete INDEX`](#delete) | Delete a contact |
| [`clear`](#clear) | Delete all contacts |
| [`find NAME`](#find) | Find a contact by name |
| [`find t/TAG`](#find_tag) | Find a contact through associated tags |
| [`find p/PLATFORM`](#find_platform) | Find a contact through associated social media platforms |
| [`picture INDEX`](#picture) | Modify profile picture of a contact |
| [`share INDEX`](#share) | Copy contact information to system clipboard |
| [`pin INDEX`](#pin) | Pin contact card on the top of the person list |
| [`unpin INDEX`](#unpin) | Unpin contact card from the top of the person list |
| [`remark INDEX r/[TEXT]`](#remark) | Add special notes about a contact |
| [`help`](#help) | Access User Guide |
| [`help COMMAND`](#help_command) | Access in-app guidance for selected commands |
| [`exit`](#exit) | Exit the program |


### Add a person: `add n/NAME p/PHONE_NUMBER ...` <a name="add"></a>

Adds a person to SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`add n/NAME p/PHONE_NUMBER [t/TAG]…​ [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​ [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]`
* A person can have any number of tags and dates (including 0).
* To add a contact to SociaLite, you need to provide the **name** and a **unique phone number** for the person!
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `add n/Annie Wan p/98571082 t/Friend fb/annie.wan ig/ann.wan tele/annie tiktok/annie.wan2 date/birthday:1999-02-12:yearly`

Adds a new contact called "Annie Wan" with phone number "98571082" to SociaLite.
![01_add](images/UG/01_add.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `add n/David Tan p/91082942 t/neighbour fb/tan.david tele/d_av_id`

Adds a new contact called "David Tan" with phone number "91082942" to SociaLite.
</div>

<br>

### List all persons : `list` <a name="list"></a>

Shows a list of all persons stored on SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`list`
</div>

<br>

### Access a contact's social media page <a name="click"></a>

Clicking on a contact's social media handle will immediately launch the user's default browser and open the contact's social media profile.

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** 
Clicking on the Facebook handle `@alex.yeoh` launches the user's default browser and brings the user to Alex Yeoh's Facebook page.

</div>

<br>

### Track history of commands: `↑` or `↓` arrow keys <a name="scroll"></a>

Scroll through your history of commands using the `↑` or `↓` arrow keys.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`↑` or `↓`
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `↑` backtracks to the previous command provided as input.

</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `↓` advances to subsequent commands stored in history.

</div>

<br>

### Edit a person : `edit INDEX ...` <a name="edit"></a>

Edits an existing contact on SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`edit INDEX [n/NAME] [p/PHONE] [r/REMARK] [t/TAG]…​ [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​ [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]`
* Edits the person at the specified `INDEX`. The index refers to the index number shown on the displayed person list. 
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Users can opt to change any fields associated with a contact as long as they prepend the argument with the corresponding flags according to the format above.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tag after it.
* When editing dates, all existing dates of the person will be removed i.e adding of dates is not cumulative.
* You can remove all the person’s dates by typing `date/` without
  specifying any date after it.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 1 p/91234567 fb/Yalex19` 

Edits the phone number and Facebook handle of the 1st person to be `91234567` and `Yalex19` respectively.
![05_edit](images/UG/05_edit.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 4 n/Betsy Crower t/`

Edits the name of the 4th person to be `Betsy Crower` and clears all existing tags.
</div>

<br>

### Create / Edit tags for existing contacts: `edit INDEX [t/TAG]...` <a name="edit_tag"></a>

Modifies tags that are associated with existing contacts stored on SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`edit INDEX [t/TAG]...`
* Adds new tags to persons who did not have tags associated with them when they were first added to SociaLite.
* Deletes all existing tags for the person at the specified `INDEX` and replaces them with tags specified in `[t/TAG]...`
* Each tag can only be up to 50 characters long.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 1 t/buddy`

Creates a tag called `buddy` for the first person on the displayed person list.
![06_edit_tag](images/UG/06_edit_tag.png)
</div>


<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 2 t/friend t/neighbour`

Creates tags called `friend` and `neighbour` for the second person on the displayed person list.
</div>

<br>

### Delete tags associated with contacts: `edit INDEX t/` <a name="delete_tag"></a>

Deletes all tags that are associated with a specified contact on SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`edit INDEX t/`
* Deletes all categories tagged to the person at the specified `INDEX`.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 2 t/` 

Deletes all tags associated with the 2nd person on the displayed person list.
![07_delete_tags](images/UG/07_delete_tags.png)
</div>

<br>

### Add / Edit social media handles for existing contacts: `edit INDEX [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]` <a name="edit_platform"></a>

Modifies social media handles that are associated with existing contacts on SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`edit INDEX [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]`
* Adds a new handle for contacts who did not have that specific handle pegged with them when they were first added to SociaLite.
* Replaces the handle for the person at the specified `INDEX` according to the flag and input provided.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The flag refers to any of the following: `fb/` `ig/` `tele/` `tiktok/` `twitter/`
* User input after the given flag represents the new social media handle associated with the contact.
* Only social media handles specified as input will be altered. Other social media handles remain unchanged.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 1 fb/al3x.ye0h` 

Changes the Facebook handle of the first person on the displayed person list to `al3x.ye0h`.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `edit 1 twitter/xelayeoh` 

Adds the Twitter handle called `xelayeoh` to the first person on the displayed person list. The Facebook handle that was previously modified is unaffected.
![08_edit_handles](images/UG/08_edit_handles.png)
</div>

<br>

### Add / Edit dates of occasions associated with contacts: `edit INDEX [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​` <a name="edit_dates"></a>

Adds / Edits dates of occasions (birthdays, appointments) associated with a contact.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`edit INDEX [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​`
* Adds a date for the contact at the specified `INDEX`.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Dates can have a name to associate them with a specific event (eg. Birthday, Meetup, Anniversary).
* The name associated with the event can only be up to 50 characters long.
* Date must be presented in `YYYY-MM-DD` format.
* Dates can be recurring, either monthly or yearly, by adding `:monthly` or `:yearly` behind the date.
* Each call of `edit INDEX [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​` will replace all previous dates.
* Using the command `edit INDEX date/` will remove all dates from the user.
* Upcoming dates (within 7 days) will be highlighted in the user interface.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `edit 1 date/Meeting:2021-09-14` 

Adds the event “Meeting” which falls on 14 Sep 2021, to Alex Yeoh’s listing.
![09_edit_dates](images/UG/09_edit_dates.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find Bernice` followed by `edit 1 date/Birthday:1999-09-09:yearly` 

Adds the event “Birthday” which falls on 9 Sep every year to Bernice Yu’s listing.
</div>

<br>

### Delete a person : `delete INDEX` <a name="delete"></a>

Deletes the specified person from SociaLite.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`delete INDEX`
* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `delete 7` 

Deletes the 7th person on the displayed person list.
![10_delete](images/UG/10_delete.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find Roy` followed by `delete 1` 

Deletes the 1st person on the displayed person list of the `find` command.
</div>


<br>

### Remove all data: `clear` <a name="clear"></a>

Removes all contacts in the app.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`clear`
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Please be mindful that entering this command will wipe out all your contacts stored in SociaLite!
</div>

<br>

### Locate contacts by name: `find NAME` <a name="find"></a>

Finds persons whose names contain any of the given keywords.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`find NAME [MORE_KEYWORDS]`
* The search is case-insensitive.
* The order of the keywords does not matter.
* Supports partial matching from the start of each word in a contact's name.
* Name is searched by default when no flags (or invalid flags) are given as input.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find david`

Returns `David Li` and `David Tan` <br>
![12_find_name](images/UG/12_find_name.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find Hans Bo` will match `Bo Hans`
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find han` will match `Hans` but not `Khan`
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find Al Y` will match `Alex Yeoh`
</div>

<br>

### Query contacts based on tags: `find t/TAG` <a name="find_tag"></a>

Displays the contacts that are associated with a particular tag.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`find t/TAG`
* Queries all contacts that are tied to `TAG`.
* The tag must be existent and associated with certain contacts stored on SociaLite.
* Tags are matched from the start of their names.
* If multiple tags are queried, only contacts possessing all given tags are returned.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find t/family` 

Checks for contacts which the user categorized as “family”.
![13_find_tag](images/UG/13_find_tag.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find t/classmate` 

Checks for contacts which the user categorized as “classmate” OR "classmates" etc.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find t/mates` 

Checks for contacts which the user categorized as “mates” BUT NOT "classmates".
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find t/colleagues t/friends` 

Checks for contacts which are categorized as BOTH “colleagues” AND "friends".
</div>

<br>

### Query contacts based on social media platform: `find p/PLATFORM` <a name="find_platform"></a>

Shows all contacts with their handles on the specified social media platform.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`find p/PLATFORM`
* The social media platform entered must be supported by SociaLite, 
(i.e.: Only `facebook`, `instagram`, `telegram`, `tiktok`, `twitter` are accepted, case insensitive).
* If multiple platforms are queried, only contacts with handles for ALL specified platforms are returned.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find p/facebook p/instagram` 

Returns all contacts who have BOTH a Facebook and Instagram handle pegged with them.
![14_find_platform](images/UG/14_find_platform.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find p/telegram` 

Returns all contacts who have a Telegram handle pegged with them.
</div>

<br>

### Add / Change a profile picture: `picture INDEX` <a name="picture"></a>

Adds a profile picture to an existing contact or changes the existing profile picture if available.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`picture INDEX`
* Opens file browser allowing you to choose any .jpg or .png file as the profile picture.
* Adds the selected picture to the contact at the specified `INDEX`.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `picture 2` 

Opens the file browser to let you choose a picture to add to Annie Wan’s listing in the app.
![15_pic_window](images/UG/15_pic_window.png)

Updates Annie Wan's profile picture in SociaLite.
![15_pic_ui](images/UG/15_pic_ui.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find Alex` followed by `picture 1` 

Opens the file browser to let you change the profile picture for Alex Yeoh’s listing in the app.
</div>

<br>

### Share a contact card: `share INDEX` <a name="share"></a>

Copies the contact card of the specified person to the system clipboard. The user can paste the plain text in an email or text message and forward the contact card to others thereafter.

Alternatively, the user can click on the "Share" button at the top-right hand corner of the contact card that he/she would like to share instead of entering the command.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`share INDEX`
* Copies the contact card of the person at the specified `INDEX` to the system clipboard.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `share 2` 

Copies the information of the second contact in the list displayed by SociaLite.
![16_share](images/UG/16_share.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** Clicking on the "Share" button of Annie Wan's name card copies her contact information.
</div>

<br>

### Pin a contact card: `pin INDEX` <a name="pin"></a>

Affixes the contact card of the specified person onto the top of the person list. Pinned contact cards will be alphabetically ordered.

Alternatively, the user can click on the "Pin" button at the top-right hand corner of the contact card that he/she would like to pin instead of entering the command.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`pin INDEX`
* Pins the contact card of the person at the specified `INDEX` at the top of the person list.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ...
* The contact card must **not** be previously pinned.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `pin 3`

Provided no contact card has been pinned yet, the above command places the third contact in the list displayed by 
SociaLite, at the top of the person list along with other pinned contacts.
![17_pin](images/UG/17_pin.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** Clicking on the "Pin" button of Annie Wan's name card affixes her contact card onto the top of the person list.

![17_pin_2](images/UG/17_pin_2.png)
</div>

<br>

### Unpin a contact card: `unpin INDEX` <a name="unpin"></a>

Removes the contact card of the specified person from the top of the person list.

Alternatively, the user can click on the "Unpin" button at the top-right hand corner of the contact card that he/she would like to unpin instead of entering the command.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`unpin INDEX`
* Unpins the contact card of the person at the specified `INDEX` at the top of the person list.
* The index refers to the index number shown on the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ...
* The contact card must be previously pinned.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `unpin 1` 

Provided the first 2 contact cards are the pinned contact cards, the above command unpins the first contact in the list displayed by SociaLite.
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** Clicking on the "Unpin" button of Bernice Yu's name unpins her contact card if it was previously pinned.

![18_unpin](images/UG/18_unpin.png)
</div>

<br>

### Add remarks about a specific contact: `remark INDEX r/[TEXT]` <a name="remark"></a>

Adds special notes about a contact.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`remark INDEX r/[TEXT]`

* Adds a short note about the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …
* Remarks must be 150 characters in length or less (inclusive of spaces).

</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `list` followed by `remark 1 r/Doesn’t like vegetables`

Adds the remark “Doesn’t like vegetables” to Alex Yeoh’s listing in the app.
![19_remark_alex](images/UG/19_remark_alex.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `find Annie` followed by `remark 1 r/Loves peas r/Allergic to prawns r/Hates carrots`

Only the remark “Hates carrots” is added to Annie Wan’s listing in the app.
![19_remark_annie](images/UG/19_remark_annie.png)
</div>

<br>

### View help: `help` <a name="help"></a>

Shows a message explaining how to access the User Guide or obtain in-app guidance for selected commands.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`help`
![20_help](images/UG/20_help.png)
</div>

<br>

### View in-app guidance: `help COMMAND` <a name="help_command"></a>

Displays concise instructions for users to learn the given `COMMAND`'s function.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`help COMMAND`
* Acceptable keywords to use in `COMMAND`: `add` `delete` `edit` `find` `remark` `share` `picture` `pin` `unpin`
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `help add` 

Displays a concise guide on how to use the `add` command.
![21_help_add](images/UG/21_help_add.png)
</div>

<div markdown="block" class="alert alert-success">
**:heavy_check_mark: Example:** `help edit` 

Displays a concise guide on how to use the `edit` command.
</div>

<br>

### Exit the program : `exit` <a name="exit"></a>

Exits the program.

<div markdown="block" class="alert alert-primary">
**:mag_right: Format:**
`exit`
</div>

<br>

--------------------------------------------------------------------------------------------------------------------

## FAQ <a name="faq"></a>

**Q**: How is my data saved?<br>
**A**: SociaLite data are saved in the hard disk automatically after any command that alters the data. There is no need to save manually.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SociaLite home folder.

**Q**: Can I edit my data?<br>
**A**: SociaLite data are saved as a JSON file `<JAR file location>/data/contactlist.json`. Advanced users are welcome to update data directly by editing that data file.
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SociaLite will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command Index <a name="index"></a>

Action | Format | Example (if any)
--------|------------------|--------------------
**Access Social Media Page** | Click on social media handle | 
**Add** | `add n/NAME p/PHONE_NUMBER [t/TAG]…​ [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​ [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]` | `add n/Alex Yeoh p/87438807 t/friends fb/alex.yeoh ig/alex.yeoh tele/alyeoh tiktok/alex.yeoh`
**Add / Edit Categories** | `edit INDEX [t/TAG]...` | `edit 1 t/Friends`
**Add / Edit Dates** | `edit INDEX [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​` | `edit 1 date/Meeting:2021-09-14`
**Add / Edit Profile Picture** | `picture INDEX` | `picture 1`
**Add / Edit Social Media Handles** | `edit INDEX [p/PLATFORM]` | `edit 1 fb/Alv1n.y`
**Add Remark** | `remark INDEX r/[TEXT]` | `remark 1 r/Likes to swim`
**Delete** | `delete INDEX`| `delete 3`
**Delete All Categories** | `edit INDEX t/` | `edit 1 t/`
**Delete All Data** | `clear` |
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [r/REMARK] [t/TAG]…​ [date/NAME:YYYY-MM-DD[:monthly|:yearly]]…​ [fb/FACEBOOK] [ig/INSTAGRAM] [tele/TELEGRAM] [tiktok/TIKTOK] [twitter/TWITTER]` | `edit 1 p/91234567 fb/Yalex19`
**Exit** | `exit` |
**Find Contacts by Category** | `find t/TAG` | `find t/Friends`
**Find Contacts by Name** | `find NAME [MORE_KEYWORDS]`| `find James Jake`
**Find Contacts by Platform** | `find p/PLATFORM` | `find p/facebook`
**List** | `list` |
**Pin Contact** | `pin INDEX` | `pin 4`
**Share Contact** | `share INDEX` | `share 2`
**Track History** | `↑` or `↓` arrow keys |
**Unpin Contact** | `unpin INDEX` | `unpin 1`
**View Help** | `help` |
**View In-App Guidance** | `help COMMAND` | `help add`
