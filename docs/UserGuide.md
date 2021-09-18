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
