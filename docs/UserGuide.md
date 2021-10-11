---
layout: page
title: User Guide
---

TutAssistor is a ***desktop app for private tutors to manage tuition class time slots, optimized for use via a Command Line Interface (CLI)***. It enables tutors to resolve conflicts in their tuition schedule and manage their students more efficiently.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

TBA

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding students/tuition classes
#### Adding a student: `add`
Adds a student’s information such as n/NAME, p/PHONE_NUMBER. 

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK] [t/TAG]…`

Examples:
- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add n/Richard Ng p/97865342 e/richardng@example.com a/Yishun Ave 1 block 60, #07-12 r/Can only attend on even weeks t/paid`

#### Adding a tuition class: `addclass`
Adds a tuition class with a set l/LIMIT of students at specified t/TIME.

Format: `addclass n/NAME l/LIMIT c/CAPACITY ts/TIMESLOT n/NAME,NAME,NAME... [r/REMARK] [t/TAG]…`

Examples:
- `addclass n/Chemistry l/16 c/4 ts/Thu 15:00-17:00 s/Bernice Yu`
- `addclass n/Math l/8 c/5 ts/Mon 11:00-14:00 s/Alex Yeoh r/Quiz on final lesson`

### Viewing students/tuition classes
Provides a view of a student or class if an INDEX is provided. Otherwise, a full list is provided.

#### Viewing a student: `student`
Format: `student [INDEX]`

#### Viewing a tuition class: `class`
Format: `class [INDEX]`

### Editing students/tuition classes
Edits a student’s information such as n/NAME, p/PHONE_NUMBER. 
Edits a tuition class at specified t/TIME.

#### Editing a student: `editstudent`
Format: `editstudent INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`

#### Editing a class: `editclass`
Format: `editclass INDEX l/limit t/time n/NAME,NAME,NAME... [t/TAG]…`


### Deleting students/tuition classes
Deletes a student or a tuition class given INDEX.

#### Deleting a student: `deletestudent`
Format: `deletestudent [INDEX]`

#### Deleting a tuition class: `deleteclass`
Format: `deleteclass [INDEX]`

### Adding/Removing an existing student from class
Move a student to/from classes by adding or removing them.

#### Adding an existing student to a class: `addtoclass`
Format: `addtoclass [INDEX_STUDENT] [INDEX_CLASS]`

#### Removing an existing student from a class: `remove`
Format: `remove [INDEX_STUDENT] [INDEX_CLASS]`

### Exiting the app: `exit`
Exits the program.<br>
Format: `exit`

## Command Summary

Action | Format
--------|------------------
***Add Student*** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK] [t/TAG]…`
***Add Class*** | `addclass l/limit t/time n/NAME,NAME,NAME... [r/REMARK] [t/TAG]…`
***View Student*** | `student [INDEX]`
***View Class*** | `class [INDEX]`
***Edit Student*** | `editstudent [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]  [t/TAG]…`
***Edit Class*** | `editclass [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`
***Delete Student*** | `deletestudent [INDEX]`
***Delete Class*** | `deleteclass [INDEX]`
***Add Student to Class*** | `addtoclass [INDEX_STUDENT] [INDEX_CLASS]`
***Remove Student from Class*** | `remove [INDEX_STUDENT] [INDEX_CLASS]`
***Add Remarks to Student*** | `remark [INDEX_STUDENT] r/REMARK`
***Add Remarks to Class*** | `remarkclass [INDEX_CLASS] r/REMARK`
***Exit*** | `exit`


