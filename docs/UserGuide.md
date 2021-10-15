---
layout: page
title: User Guide
---

AniList is a **desktop app for managing anime you have watched, optimized for use via
a Command Line Interface** (CLI) while still having the benefits of a Graphical User
Interface (GUI). If you can type fast, AniList can get your anime management tasks done
faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `anilist.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AniList.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `delete n/Doraemon`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EPISODE]` can be used as `n/Doraemon` or as `n/Doraemon e/1.`
</div>

### Adding anime: `add`

Adds an anime into the list.<br>
Format: `add n/NAME [e/EPISODE]`
- `EPISODE` refers to the latest episode watched for the anime `NAME`
- `EPISODE` must be a non-negative integer 0, 1, 2...
- `EPISODE` must be a non-negative integer 0, 1, 2...

Examples:
* `add n/Shingeki no Kyojin e/2`

### Deleting an anime : `delete`

Deletes the specified anime from the list.<br>
Format: `delete INDEX`

- Deletes the anime at the specified `INDEX`.
- The index must be a positive integer 1, 2, 3, ...

Examples:
*  `delete 1` deletes the 1st anime in the anime list.

### Viewing help : `help`

Lists all currently supports commands, and provides a link to this user guide.<br>
Format: `help`

### Listing all anime: `list`

Shows a list of all anime names the user has added.<br>
Format: `list`

### Updating an anime's episode: `update`

Updates the episode that you are currently on for a specified anime.<br>
Format: `update INDEX e/NEWEPISODE`

- `NEWEPISODE` refers to the latest episode watched.
- Updates the anime at the specified `INDEX`.
- The index must be a positive integer 1, 2, 3, ...

Examples:
*  `update 1 e/3`

### Updating an anime's status: `status`

Updates the status of a specified anime.<br>
Format: `status INDEX s/UPDATEDSTATUS`

- `UPDATEDSTATUS` refers to the updated status of the anime.
- Available statuses: "towatch", "watching", "finished"
- Shortforms: "t", "w", "f"
- Updates the anime at the specified `INDEX`.
- The index must be a positive integer 1, 2, 3, ...

Examples:
*  `status 1 s/f`

### Add/Delete a genre from an anime: `genre`

Adds or deletes a genre from a specified anime.<br>
All genres will be automatically changed to lowercase, and duplicate genres are not allowed.
Format: `genre INDEX c/ACTION g/GENRE [g/GENRE]`

- `ACTION` specifies whether you are adding or deleting genres.
- Current list of available actions: `add`, `delete`.
- Updates the anime at the specified `INDEX`.  
- The index must be a positive integer 1, 2, 3, ...
- `GENRE` can only contain alphabets and spaces. Two or more consecutive spaces are not allowed.
- You need to provide at least 1 `GENRE`.

Examples:
*  `genre 1 c/add g/shounen g/medieval fantasy g/isekai`
*  `genre 1 c/delete g/shounen g/medieval fantasy g/isekai`
