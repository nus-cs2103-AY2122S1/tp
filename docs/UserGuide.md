## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `contacts`, `tasks` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Tasks (When on tasks tab)

#### Adding a task: [add]
Adds a task to the task list. Only task description is compulsory during creation.

Format: ```add d/DESCRIPTION [t/TAG]...```

#### Editing
Replaces the details of the task at the given index with the new details

Format: ```edit INDEX [d/DESCRIPTION] [t/TAG]...```

* Edits the specified task fields at the specified INDEX
* The index refers to the index number shown in the task list
* The index must be a positive integer i.e. 1, 2, 3, …​

#### Editing task details [edit]
Replaces the details of the task at the given index with the new details

Format: ```edit INDEX [d/DESCRIPTION] [t/TAG]...```

* Edits the specified task fields at the specified INDEX
* The index refers to the index number shown in the task list
* The index must be a positive integer i.e. 1, 2, 3, …​

#### Deleting a task [delete]
Deletes the task at the chosen index.

Format: ```delete INDEX```

* Deletes the person at the specified INDEX
* The index refers to the index number shown in the task list
* The index must be a positive integer i.e. 1, 2, 3, …​

##### Finding task through task description: [find]
Finds all tasks with descriptions that match the search term

Format: ```find DESCRIPTION```

* The search is case-insensitive. e.g job will match Job
* The order of the keywords does not matter. e.g. home work will match work home
* Only the description field is searched.
* Task descriptions matching all keywords will be returned (i.e. AND search). e.g. Do this will return only Do this and Do this (very important!!!). It will not return Do maybe? or This doesn’t matter

#### Clearing all tasks: [clear]
Deletes all tasks

Format: ```clear```
