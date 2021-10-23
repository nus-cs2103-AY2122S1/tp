---
layout: page
title: User Guide
---

![gitGud](images/gitgud.png)

gitGud is a **desktop application for managing your friends' gaming information**. It uses a gamer-themed **Graphical User Interface 
(GUI) combined with an optimized Command Line Interface (CLI)** to give you a smooth and efficient experience. 

Keeping track of all of yours friends' gaming information and schedules is difficult and time-consuming.
With gitGud, there is no need to get tilted(frustrated) by this anymore as **how fast you type determines how fast you manage your gaming contacts**.


* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `gitgud.jar` from [here](https://github.com/AY2122S1-CS2103T-W13-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your gitGud application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press `Enter` to execute it. e.g. typing **`help`** and pressing `Enter` will open the help window.<br>
   Some example commands you can try:

   * **`friend --list`** : Lists all friends.

   * **`friend --add`**`Draco --name Marcus` : Adds a friend __Marcus__ with gitGud FRIEND_ID of __Draco__ to the application.

   * **`friend --delete`**`Draco` : Deletes friend with gitGud FRIEND_ID of __Draco__.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `get --game GAME_NAME`, `GAME_NAME` is a parameter which can be used as `get --game CSGO`.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `--name “Marcus Tang” --name Taufiq`, only `--name Taufiq` will be taken.

* If a name provided has spaces in-between, use double quotation marks to wrap the name in the command e.g. wrap the name
  'Apex Legends' as `"Apex Legends"`.

</div>

### Friend commands  

Commands that involve adding, editing, deleting or viewing of friends in your gitGud friend list.

#### Adding a friend: `friend --add`

Adds a new friend to gitGud friend’s list with an associated **unique** friend identifier.

Format: `friend -add FRIEND_ID [--name NAME]`

Examples:
* `friend --add Draco` Adds a brand-new friend into the friends list with the identifier 'Draco' and
  which does not currently have an associated real-life name.
* `friend --add tau_bar --name Taufiq` Adds a brand-new friend into the friends list with the identifier 'tau-bar' and
  has the real-life name 'Taufiq'.

#### Deleting a friend: `friend --delete`

Deletes a friend from gitGud’s friend’s list using gitGud’s unique friend identifier.

Format: `friend --delete FRIEND_ID`

* Deletes the person with the specified `FRIEND_ID`.
* The `FRIEND_ID` must currently exist in the database.

Examples:
* `friend --delete Draco` Deletes friend with gitGud FRIEND_ID of Draco and all their data from the database

#### Link games with a friend: `friend --link`

Links game and the associated in-game username for the game to a friend with the provided FRIEND_ID.

A link between a particular friend and game shows that the friend plays the game. This association is required for subsequent commands, e.g. for the application to display all games that a friend plays or to recommend a friend to play with based on a particular game.

Format: `friend --link FRIEND_ID --game GAME_ID --user IN_GAME_USERNAME`

* Both FRIEND_ID and GAME_ID has to already be inside the database.

Examples:
* `friend --link Draco --game DOTA --user Draco995`
  Links a single game, “DOTA” with the in-game username ‘Draco995’,
  to the friend with the gitGud FRIEND_ID ‘Draco’.

#### Unlinking a game from a friend: `friend --unlink`

Removes the link between a friend and a game.

Format: `friend --unlink FRIEND_ID --game GAME_ID`

* FRIEND_ID has to already be inside the database.
* The friend has to have a current association with the provided GAME_ID.

Examples:
* `friend --unlink Draco --game DOTA` Removes the link between the friend with FRIEND_D 'Draco' and the game with GAME_ID 'DOTA'. 'Draco' is now no longer associated with 'DOTA'.

#### Getting a single friend's complete data: `get --friend`

Takes a look at a particular friend's complete data by searching their `FRIEND_ID`. A friend's complete data includes:
* List of games the friend plays
* In-game username for each game

Format: `get [--friend] FRIEND_ID`
* The `FRIEND_ID` must currently exist in the database

Examples:
* `get --friend Draco` Gets the complete data for friend "Draco", which includes the list of games he plays and his username for each game
* `get kev` Gets the complete data for friend "kev"


#### Listing multiple friends data: `friend --list`

Lists all friends stored in gitGud whose friend id contains any of the given keywords.

Format: `friend --list [KEYWORD]`

* If `KEYWORD` is left empty, all friends stored in gitGud will be listed
* The filter keyword is case insensitive e.g `Tau_bar` will match `tau_bar`
* Only the `FRIEND_ID` of friends is filtered
* Partial matches will be displayed e.g. `tau` will match `tau_bar`

Examples:
* `friend --list` or  `friend --list` Lists all friends stored in gitGud
* `friend --list ta` Lists all friends stored in gitGud that have `ta` in their name

![result for 'game --list'](images/UiListFriend.png)

#### Scheduling a friends: `friend --schedule`

Schedules an existing friend by updating their schedule to indicate the time periods they are free or busy.
* gitGud stores a weekly schedule for each friend, from Monday to Sunday, with each day having 24 blocks of hours that can be marked as free or busy.

Format: `friend --schedule FRIEND_ID --period START_TIME END_TIME DAY --free IS_FREE`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the schedule:**<br>

New friends start off which a schedule with all periods marked as busy.

</div>  

* `FRIEND_ID` must belong to an existing friend in gitGud
* `START_HOUR` and `END_HOUR` are written in 24 Hour notation and must be whole hours (e.g. 0230 and 1201 are not accepted)
* `END_HOUR` must be after `START_HOUR`, the only exception is if `END_HOUR` is `0000` where it is taken as midnight.
* `DAY` must be an integer between 1 and 7 inclusive, with each day representing a day of the week from Monday to Sunday.
* `IS_FREE` is used to mark the period as a free or busy period, with `1` meaning free and `0` meaning busy

Examples:
* `friend --schedule Draco --period 1800 2200 2 --free 1` Schedules "Draco" as free from 1800 to 2200 on Tuesday.
* `friend --schedule Draco --period 1200 0000 7 --free 0` Schedules "Draco" as busy from 1200 to 0000 (midnight) on Sunday.

### Game commands

Commands that involve adding, editing, deleting or viewing of games in your gitGud friend list.

#### Adding a game: `game --add`

Adds a game with the given **unique** GAME_ID into the gitGud game list.

Format: `game --add GAME_ID`

* If the GAME_ID provided already exists, an error will be displayed. 
* The GAME_ID provided must be a single word e.g. `ApexLegends` and not `Apex Legends`.

Examples:
* `game --add Valorant` Adds a brand-new game into the game list with the unique GAME_ID 'Valorant'.

#### Deleting a game: `game --delete`

Deletes a game from the gitGud game list.

Format: `game --delete GAME_NAME`

Examples:
* `game --delete Valorant` Deletes the game record ‘Valorant’ from the game list if it exists.

#### Getting a single game's complete data: `get --game`

Gets all the relevant information for a game that was previously added, this includes:
* All the friends (represented by their `FRIEND_ID`) that play the game

Format: `get --game GAME_NAME`
* The `GAME_NAME` must currently exist in the database

Examples:
* `get --game CSGO` Gets all the relevant information for the game "CSGO". This includes all the friends in your database that play the game

#### Listing multiple games data: `game --list`

Lists all games stored in gitGud whose friend id contains any of the given keywords.

Format: `game --list [KEYWORD]`

* If `KEYWORD` is left empty, all games stored in gitGud will be listed
* The filter keyword is case insensitive e.g `valorant` will match `Valorant`
* Only the `GAME_ID` of games is filtered
* Partial matches will be displayed e.g. `Valo` will match `Valorant`

Examples:
* `game --list` Lists all games stored in gitGud
* `game --list Valo` Lists all friends stored in gitGud that have `Valo` in their name

![result for 'game --list'](images/UiListGame.png)

### Other commands

Miscellaneous useful commands for the user.

#### Viewing help: `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I **_transfer_** my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: How do I _**update**_ a friend’s data that is already on the database?<br>
**A**: You can delete the friend and then add the friend with the new data.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add friend** | `friend --add FRIEND_ID [--name "NAME"]` <br> e.g., `friend --add Draco --name "Marcus Tang"`
**Delete friend** | `friend --delete FRIEND_ID`<br> e.g., `friend --delete Draco`
**Get friend** | `get --friend FRIEND_ID`<br> e.g., `get --friend Draco`
**List Friend** | `friend --list [KEYWORD]`<br> e.g., `friend --list`, `friend --list Tau`
**Schedule Friend** | `friend --schedule FRIEND_ID --period START_TIME END_TIME DAY --free IS_FREE`<br> e.g., `friend --schedule Draco --period 1800 2200 2 --free 1`
**Add game** | `game --add GAME_ID` <br> e.g., `game --add Valorant`, `game --add ApexLegends` 
**Delete game** | `game --delete GAME_ID` <br> e.g., `game --delete Valorant`
**Get game** | `get --game GAME_ID`<br> e.g., `get --game Valorant`
**List Games** | `game --list [KEYWORD]`<br> e.g., `game --list`, `game --list Valorant`
**Link game and friend** | `friend --link FRIEND_ID --game GAME_ID --user IN_GAME_USERNAME`<br> e.g., `friend --link Draco --game Valorant --user taufiq007`
**Unlink game and friend** | `friend --unlink FRIEND_ID --game GAME_ID` <br> e.g., `friend --unlink Draco --game DOTA`
**Viewing Help** | `help`
**Exit program** | `exit`

