---
layout: page
title: User Guide
---

TutAssistor is a ***desktop app for private tutors to manage tuition class time slots, optimized for use via a Command Line Interface (CLI)***. It enables tutors to resolve conflicts in their tuition schedule and manage their students more efficiently.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Download TutAssitor jar file.
2. Open by double-clicking.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding students/tuition classes

#### Adding a student: `add`

Command Shortcut: `a`

Adds a student’s information such as n/NAME, p/PHONE_NUMBER.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]…`

Examples:

```
add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01
```
```
a n/Richard Ng p/97865342 e/richardng@example.com a/Yishun Ave 1 block 60, #07-12 r/Can only attend on even weeks
```

#### Adding a tuition class: `addclass`

Command Shortcut: `ac`


Adds a tuition class with a set l/LIMIT of students at specified ts/TIMESLOT.

The students to be added are optional. The time slot should follow the format "Www hh:mm-hh:mm" to help TutAssistor check if the time slot is already taken or there is an overlap on time slots. 
If there is any conflict or overlap, TutAssistor will alert the conflict.

Format: `addclass n/NAME l/LIMIT ts/TIMESLOT [s/NAME,NAME,NAME...] [r/REMARK]`

Examples:
```
addclass n/Chemistry l/16 ts/Thu 15:00-17:00 s/Bernice Yu,Richard Ng
```
```
ac n/Math l/8 ts/Mon 11:00-14:00 r/Quiz on final lesson
```

### Viewing students/tuition classes

Provides a view of a student or class if an INDEX is provided. Otherwise, a full list is provided.

#### Viewing a student: `student`

Command Shortcut: `vs`

Format: `student INDEX`

Example:
```
student 2
```
or
```
vs 2
```

#### Viewing a tuition class: `class`

Command Shortcut: `vc`

Format: `class INDEX`

Example:
```
class 3
```
or
```
vc 3
```

### Editing students/tuition classes

Edits a student’s information such as n/NAME, p/PHONE_NUMBER.
Edits a tuition class at specified t/TIME.

#### Editing a student: `edit`

Command Shortcut: `e`

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

Examples:
```
edit 1 n/Jason Tan a/221b Baker Street
```
```
e 2 p/62353535
```

#### Editing a class: `editclass`

Command Shortcut: `ec`

Format: `editclass INDEX [n/NAME] [l/LIMIT] [ts/TIMESLOT]`

Examples:
```
editclass 2 l/10
```
```
ec 3 n/Trigonometry ts/Sun 10:00-11:00
```

### Deleting students/tuition classes

Deletes a student or a tuition class of a given INDEX.

#### Deleting students: `delete`

Command Shortcut: `del`

Format: `delete STUDENT_INDEX [STUDENT_INDEX]...`

Example:
```
delete 1 2
```
```
del 1
```

#### Deleting tuition classes: `deleteclass`

Command Shortcut: `delc`

Format: `deleteclass CLASS_INDEX [CLASS_INDEX]...`

Example:
```
deleteclass 1 2
```
```
delc 4
```

### Adding/Removing an existing student from class

Moves a student to/from classes by adding or removing them.

#### Adding existing students to a class: `addtoclass`

Command Shortcut: `atc`

Adds one or more existing students to an existing class using student NAME or INDEX.

When adding student names, use comma to separate names without space around comma.
When adding student indexes, use space to separate indexes.

Format:

`addtoclass si/STUDENT_INDEX [STUDENT_INDEX]... tc/CLASS_INDEX`

or

`addtoclass s/NAME[,NAME,NAME...] tc/CLASS_INDEX`

Examples:
```
addtoclass si/1 tc/1
```
```
atc si/1 2 3 4 tc/1
```
```
addtoclass s/James,Felicia tc/2
```
```
atc s/James tc/3
```

#### Removing existing students from a class: `remove`

Command Shortcut: `rm`

Removes existing students from a tuition class using student INDEX.

Format: `remove si/INDEX_STUDENT INDEX_STUDENT tc/CLASS_INDEX`

Examples:
```
remove si/1 tc/1
```
```
rm si/1 2 3 4 tc/2
```

### Adding remark to a student/tuition class
Upon entering the command, a pop-up window is displayed for the user to edit the remarks.

#### Adding remarks to a student: `remark`

Command Shortcut: `re`

Add a remark to the identified student.

Format: `remark STUDENT_INDEX`

Examples:
```
remark 1
```
```
re 2
```

#### Adding remarks to a tuition class: `remarkclass`

Command Shortcut: `rec`

Format: `remarkclass CLASS_INDEX`

Examples:
```
remarkclass 2
```
```
rec 1
```
<br>

Users can edit the remarks for a particular student or tuition class in the text area.<br>
![Ui](images/remarkEditor.png)

### Finding students/tuition classes by name

#### Finding students by name: `find`

Command Shortcut: `f`

Filters the list of students to only display all students whose names contain any of the given keywords (case-insensitive).

Format: `find NAME`

Example: 
```
find alice tan
```
or
```
f alice tan
```
TutAssitor will display a list of all students with `alice` or `tan` in their name.

#### Finding classes by name: `findclass`

Command Shortcut: `fc`

Filters the list of classes to only display all classes whose names contain any of the given keywords (case-insensitive).

Format: `findclass NAME`

Example: 
```
findclass physics chemistry
```
or
```
fc physics chemistry
```
TutAssitor will display a list of all classes with `physics` or `chemistry` in their name.

#### Listing all students: `list`

Command Shortcut: `l`

Display list of all students after conducting a search with the `find` command.


#### Listing all classes: `listclass`

Command Shortcut: `lc`

Displays list of all classes after conducting a search with the `findclass` command.

### Sorting tuition classes: `sort`

Command Shortcut: `s`

Sorts tuition class list according to time or alphabetical order.

Without exiting TutAssistor, the list will be auto-sorted
when adding and editing classes after the tutor sorted the list.

Format: `sort [o/ORDER]`

Examples: (examples below are all possible usages of `sort`)
* `sort` (sort by time)
* `sort o/asc` (sort by ascending alphabetical order)
* `sort o/desc` (sort by descending alphabetical order)
* `sort o/time` (sort by time)

### View timetable: `timetable`

Command Shortcut: `tt`

Example:
```
timetable
```

Shows lessons scheduled in this week in a timetable.<br>
![Ui](images/time_table.png)

### View today tuition classes: `today`

Shows all the tuition classes happens today

Format: `today`

### Viewing help: `help`

Command Shortcut: `h`

Shows a command summary and a link to the user guide. <br>
![Ui](images/helpWindow.png)

### Exiting the app: `exit`

Exits the program.<br>
Format: `exit`

## Command Summary

Action | Format | Shortcut
-------|--------|---------
***Add Student*** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]…` | `a`
***Add Class*** | `addclass l/limit t/time [s/NAME,NAME,NAME...] [r/REMARK] [t/TAG]…` | `ac`
***View Student*** | `student INDEX` | `vs`
***View Class*** | `class INDEX` | `vc`
***Edit Student*** | `editstudent INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]  [t/TAG]…` | `e`
***Edit Class*** | `editclass INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…` | `ec`
***Delete Student*** | `deletestudent INDEX [INDEX]...` | `del`
***Delete Class*** | `deleteclass INDEX [INDEX]...` | `delc`
***Add Student to Class*** | `addtoclass si/STUDENT_INDEX [STUDENT_INDEX]... tc/CLASS_INDEX` <br /> or `addtoclass s/NAME,NAME... tc/CLASS_INDEX` | `atc`
***Remove Students from Class*** | `remove si/STUDENT_INDEX [STUDENT_INDEX]... tc/CLASS_INDEX` | `rm`
***Add Remarks to Student*** | `remark INDEX` | `re`
***Add Remarks to Class*** | `remarkclass INDEX` | `rec`
***Find Student by Name*** | `find NAME` | `f`
***Find Class by Name*** | `findclass NAME` | `fc`
***List all Students*** | `list` | `l`
***List all Classes*** | `listclass` | `lc`
***Sort Tuition Class*** | `sort [o/ORDER]` | `s`
***View Timetable*** | `timetable` | `tt`
***View Today's Classes*** | `today`
***Help*** | `help` | `h`
***Exit*** | `exit` | -

