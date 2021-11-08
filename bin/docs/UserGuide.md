---
layout: page
title: User Guide
---

Game Book is a **desktop app for tracking your gambling performance, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Game Book can log and display 
your gambling performance faster than with traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure that you have Java 11 or above installed in your computer.

2. Download the latest `gamebook.jar` from [here](https://github.com/AY2122S1-CS2103T-W13-3/tp/releases).

3. Copy the JAR file to the folder you want to use as the _home folder_ for GameBook.

4. Double-click the JAR file to start the app. The GUI similar to the below should appear in a few seconds.
   <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press “Enter” to execute it. <br>
   Some example commands you can try:

   * **`add /game Poker /start 500 /end 650 /date 20/06/21 /duration 142 /location Sentosa Casino`**: <br>
     Adds an entry of Poker where you started with $500 and ended with $650 (played on 20th June 2021 for 142min at 
     Sentosa Casino) into the Game Book.
   
   * **`delete 2`** : Deletes the 2nd entry shown in the current list

   * **`exit`** : Exits from Game Book.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add /game GAMENAME /start INITIALCASH /end FINALCASH`, `GAMENAME`, `INTIIALCASH`, and `FINALCASH` are 
  parameters which can be used as `add /game poker /start 0.01 /end 1.02`.

* Items in square brackets are optional.<br>
  e.g `add /game GAMENAME /start INITIALCASH /end FINALCASH [/date DATE]` can be used as 
  `add /game poker /start 0.01 /end 1.02` or as `add /game poker /start 0.01 /end 1.02 /date 11/9/21 21:20`.

</div>


### Adding a person: `add`

Adds a person to the game book.

Format: `add /game GAMENAME /start INITIALCASH /end FINALCASH [/date DATE] [/duration DURATION] [/location LOCATION] [/tag TAGS]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A game can have any number of tags (including 0)
</div>

Examples:
* `add /game blackjack /start 12.34 /end -56.78 /date 13/9/21 /duration 1:23 /location Marina Bay Sands`
* `add /game poker /start 0.01 /end 1.02 /date 11/9/21 21:20 /duration 3:14 /location Home`
* `add /game poker /end 0.2 /tag loose run-good`

### Listing all games : `list`

Shows a list of all games in the game book.

Format: `list`

### Reads a specific game : `read`

Shows the details of a specific game.

Format: `read INDEX`

Examples:
* `read 5` shows the game record at index 5 of the game list.

### Editing a game : `edit`

Edits an existing game in the game book.

Format: `edit INDEX [/game GAMENAME] [/start INITIALCASH] [/end FINALCASH] [/date DATE] [/duration DURATION] [/location LOCATION] [/tag TAGS]`

* Edits the game record at the specified `INDEX`. `INDEX` refers to the index of the game within the game list, which 
  **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Only selected properties of the game record will be edited, all other properties will remain unchanged.
* Edited tags will replace existing tags completely.
* If the selected property was initially empty, it would be updated to be the value the user gave in the flag.
* Updated values will be reflected in the file saved to the disk.

Examples:
*  `edit 1 /game roulette /start 1` edits the game type of the 1st game to roulette, and the amount of cash the user 
   started the game with to 1.
*  `edit 3 /start 1 /location John’s house` edits the location where the first game was played to be “John’s house”, 
   regardless of whether the initial location was empty or not.

### Deleting a game: `delete`

Deletes the game at the specified index.

Format: `delete INDEX`

* Deletes the game record at the specified `INDEX`. `INDEX` refers to the index of the game within the game list, which 
  **must be a positive integer** 1, 2, 3, …​
* Selected game will also be deleted from the file in the disk.
* Indices of all remaining tasks will be updated.
  * Suppose game record `3` has been deleted, then all game records with `INDEX > 3` will be decremented by 1.

Examples:
* `delete 2` deletes the 2nd game in the list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

GameBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

GameBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, GameBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous GameBook home folder.

**Q**: Can I edit the data by directly modifying the data file?<br>
**A**: Technically you can if you follow the exact storage format. However, we strongly advise against it as any 
mistakes will cause the app to throw an exception.

**Q**: (Follow up from previous question) What is the storage format of the data file?<br>
**A**: (To be answered)


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add /game GAMENAME /start INITIALCASH /end FINALCASH [/date DATE] [/duration DURATION] [/location LOCATION] [/tag TAGS]` <br> <br> e.g., <br> `add /game blackjack /start 12.34 /end -56.78 /date 13/9/21 /duration 1:23 /location Marina Bay Sands` <br> `add /game poker /start 0.01 /end 1.02 /date 11/9/21 21:20 /duration 3:14 /location Home` <br> `add /game poker /end 0.2 /tag loose run-good`
**List** | `list`
**Read** | `read`
**Delete** | `delete INDEX`<br> <br> e.g., `delete 1`
**Edit** | `edit INDEX [/game GAMENAME] [/start INITIALCASH] [/end FINALCASH] [/date DATE] [/duration DURATION] [/location LOCATION] [/tag TAGS]` <br> <br> e.g., <br>`edit 1 /game roulette /start 1` <br> `edit 3 /start 1 /location John’s house`
**Exit** | `exit`
