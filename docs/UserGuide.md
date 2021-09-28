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
#### Adding a student: `addstudent`
Adds a student’s information such as n/NAME, p/PHONE_NUMBER. 

Format: `addstudent n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

#### Adding a tuition class: `addclass`
Adds a tuition class with a set l/LIMIT of students at specified t/TIME.

Format: `addclass l/LIMIT t/TIME n/NAME,NAME,NAME... [t/TAG]…`

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
***Add Student*** | `addstudent n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`
***Add Class*** | `addclass l/limit t/time n/NAME,NAME,NAME... [t/TAG]…`
***View Student*** | `student [INDEX]`
***View Class*** | `class [INDEX]`
***Edit Student*** | `editstudent [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]  [t/TAG]…`
***Edit Class*** | `editclass [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`
***Delete Student*** | `deletestudent [INDEX]`
***Delete Class*** | `deleteclass [INDEX]`
***Add Student to Class*** | `addtoclass [INDEX_STUDENT] [INDEX_CLASS]`
***Remove Student from Class*** | `remove [INDEX_STUDENT] [INDEX_CLASS]`
***Exit*** | `exit`


