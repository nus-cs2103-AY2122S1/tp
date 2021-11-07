---
layout: page
title: User Guide
---

ForYourInterest is a **desktop app for managing members in university clubs/societies, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ForYourInterest can get your member management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `foryourinterest.jar` from [here](https://github.com/AY2122S1-CS2103-T16-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ForYourInterest.

1. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : View all persons.

   * **`add`**`n/Xiao Ming p/61234567 t/@xiao_ming e/xiaoming@gmail.com` : Adds a contact named `Xiao Ming` to the application.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`**`-p 1-10` : Deletes all entries in person list from index 1 to 10 inclusive.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [tag/TAG]` can be used as `n/John Doe tag/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/friend`, `tag/friend tag/family` etc.

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

### Adding a person : `add`

Adds a person to the Interest Group.

Format: `add n/NAME [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [tag/TAG]`

* Only person's name must be entered to add the person. All other details are optional.
* `Person` added should not have the same name (case-sensitive) as `Person` that is already in the application.

Examples:
* `add n/Smith p/91234567 t/smith18 tag/TeamCaptain tag/TeamA`
* `add n/Xiao Ming p/61234567 t/@xiao_ming e/xiaoming@gmail.com`
* `add n/John Doe`

### Listing all persons : `list`

Shows the list of all persons in the IG.

Format: `list`

* The list will display the names of all the persons in the IG
* The user can use the `details` command with the name to show further details of the person (name, phone number, Telegram handle, Email)

### Editing a person : `edit`

Edits an existing person in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TELEGRAM_HANDLE] [tag/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `tag/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower tag/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Finding a person : `find`

Finds the persons whose details contain any of the given keywords and shows the filtered list of persons in the IG. These include their name, mobile number, telegram handle and email.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. E.g `xiaomings` will match `Xiaomings`
* The order of the keywords does not matter. e.g. `Xuan Ming` will match `Ming Xuan`
* The name, mobile number, telegram handle and email will be searched for the keyword.
* Only full words will be matched e.g. `Han` will not match `Hanna`
* Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hanna Bo` will return `Hanna Bana`, `Ling Bo`

Examples:
* `find xuan` returns `Xuan Ming` and `Xuan Xuan`
* `find xiaowen@gmail.com` returns `Xiao Wen`
* `find Alex Yeoh`

### Deleting a person : `delete`

Deletes the specified person from the Interest Group

Format: `delete [-e] INDEX`

* Deletes the person or event at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​
* -e specifies that the delete command targeting the event list.

Examples:
* `list` followed by `delete 3` deletes the 3rd person in the displayed person list.
* `find Jason` followed by `delete 1` deletes the 1st person in the results of the `find` command.
*  `delete -e 1` deletes the 1st event in the displayed event list.

### View a person's details : `details`

Shows the detailed view of a particular person in the IG. Typing any command other than the `details` command subsequently will cause the view to show the summarized view of the same person.

Format: `details NAME` or `details INDEX`

* The argument interprets as an `INDEX` if it is a positive integer, `NAME` otherwise.
* Displays the details of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The NAME entered must match the person’s name exactly (case-sensitive).

Examples:
* `list` followed by `details 3` displays details of the 3rd person in the displayed person list.
* `details Xiao Ming`
* `details Alex Yeoh`

### Creating an event : `event`

Creates an event in the application.

Format: `event EVENT_NAME`

* Putting any valid event name will create an event with no attendee. To add attendees, see `addPersonToEvent` command.
* `Event` added should not have the same event name (case-sensitive) as `Event` that is already in the application.

Examples:
* `event Dinner Event`
* `event Skating Event`

### Renaming an event : `renameEvent`

Changes the name of an event in the application.

Format: `renameEvent INDEX ev/NEW_EVENT_NAME`

* `INDEX` refers to the index of the event in the events list.
* `INDEX` must be a valid index for an existing event.

Examples:
* `renameEvent 1 ev/Dinner Event`
* `renameEvent 2 ev/Skating Event`

### Adding person to an event : `addPersonToEvent`

Adds a person in the application to an event in the application. Upon execution of this command, all `Person` and `Event` will be displayed.

Format: `addPersonToEvent n/NAME ev/EVENT_NAME`

* Both the `Person` and `Event` must exist in the application.
* Removing a `Person` associated to an event from the application does not remove the `Person` from the `Event`. The `Person` may have left the interest group, but it remains a fact that they have attended the `Event`.

Examples:
* `addPersonToEvent n/John Doe ev/Dinner Event`
* `addPersonToEvent n/Jane Doe ev/Skating Event`

### Removing person from an event : `removePersonFromEvent`

Removes a person from an event in the application.

Format: `removePersonFromEvent n/NAME ev/EVENT_NAME`

* `Event` must exist in the application.
* `Person` must exist in the specified event.

Examples:
* `removePersonFromEvent n/John Doe ev/Dinner Event`
* `removePersonFromEvent n/Jane Doe ev/Skating Event`

### Clearing entries : `clear`

Clears entries from either the member list or the event list of the Interest Group.

Format: `clear FLAG RANGE`
* `FLAG` can either be `-p` for clearing the member list or `-e` for clearing the event list.
* `RANGE` the range for clearing either the member or event list (specified by the flag used).
* There are 2 modes to clear entries: `all` - clear every entry or `begin-end` (one-based index) to clear entry in the specified range (inclusive)
* For the range specified as `begin-end`, both `begin` and `end` must be integers. The range must be valid - there should exist entries from `begin` to `end` inclusively.

Examples:
* `clear -p all`
* `clear -e 1-3`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Chaining commands : `&&`

Chains multiple commands together.

Format: `COMMAND && COMMAND`

Examples:
* `find Jon && delete 1` will:

Execute `find Jon` and then execute `delete 1`

### Setting alias : `alias`

Temporarily sets a command to a specified alias as a shortcut

Format: `alias a/ALIAS c/COMMAND`

* Alias will be set until the program is restarted.
* Setting an alias to an existing command word will not override existing commands.
* Chaining multiple commands using `&&` is not supported by alias.

Examples:
 `alias a/d1 c/details 1` will set `d1` to `details 1`

Executing `d1` will be equivalent to executing `details 1`.

### Saving the data

ForYourInterest data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ForYourInterest data are saved as a JSON file `[JAR file location]/data/foryourinterest.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ForYourInterest will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Tabbing command `[coming in v2.0]`

Users can use `ctrl + tab` to switch between tabs for now and `tab` to reach the input box. Note that the user may have to tab a few times to tab out of the input box first.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ForYourInterest home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [t/TELEGTAM_HANDLE] [tag/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com t/jameshoho tag/friend tag/colleague`
**List** | `list`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TELEGRAM_HANDLE] [tag/TAG]…​`<br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Alex Yeoh`
**Delete** | `delete [-e] INDEX`<br> e.g., `delete 3`
**Details** | `details NAME` or `details INDEX` <br> e.g., `details Alex Yeoh` or `details 1`
**Event** | `event EVENT_NAME` <br> e.g., `event Dinner Event`
**Rename Event** | `renameEvent INDEX ev/NEW_EVENT_NAME` <br> e.g., `renameEvent 1 ev/Lunch Event`
**Add Person To Event** | `addPersonToEvent n/NAME ev/EVENT_NAME` <br> e.g., `addPersonToEvent n/John Doe ev/Dinner Event`
**Remove Person From Event** | `removePersonFromEvent n/NAME ev/EVENT_NAME` <br> e.g., `removePersonFromEvent n/John Doe ev/Dinner Event`
**Clear** | `clear FLAG RANGE` <br>e.g.,`clear -p all` <br>e.g.,`clear -e 1-3`
**Exit** | `exit`
**&&** | `COMMAND && COMMAND` <br> e.g., `find Jon && delete 1`
**Alias** | `alias a/ALIAS c/COMMAND` <br> e.g., `alias a/d1 c/details 1`
