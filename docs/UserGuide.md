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
  e.g `n/NAME [e/EPISODE]` can be used as `n/Doraemon` or as `n/Doraemon e/1.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.<br>
Format: `help`

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
- `delete 1` deletes the 1st anime in the anime list.

### Listing all anime: `list`

Shows a list of all anime names the user has added.<br>
Format: `list`
