[![CI Status](https://github.com/AY2122S1-CS2103-F09-1/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2122S1-CS2103-F09-1/tp/actions)

![Ui](docs/images/Ui.png)

* This is **a project for NUS School of Computing students** from CS2103 module (AY2122S1-CS2103-F09-1).<br>
* This desktop application is used for **NUS Teaching Assistant** to keep track the modules they teach and the status of students enrolled in the specific modules.
* It is named `Teaching Assistant’s Buddy (TAB)` (`TAB` for short).
* For the detailed documentation of this project, see the **[Teaching Assistant’s Buddy Product Website](https://ay2122s1-cs2103-f09-1.github.io/tp/)**.


## Features

### Module

#### Module add

* Create a new module
* Format: `add module m/<module name>`

#### Module add task

* Create a new task for a specific module
* Format: `module add <module name>, <task name>`

#### Module add student

* Add a student to a specific module
* Format: `add student m/<module name> i/<student id> n/<student name> t/<tele_handle> e/<student email>`

#### Module delete student

* Delete a student from a specific module
* Format: `module delete <module name>, <student id>`

#### Module delete

* Delete a module
* Format: `module delete <module name>`


### Task

#### Task done

* Mark a student’s task as complete
* Format: `task done <module name>, <task name>, <student id>`

#### Task undone

* Mark a student’s task as incomplete
* Format: `task undone <module name>, <task name>, <student id>`


### Student

#### Student get

* Retrieve a student's information
* Format: `student get <student id>`
