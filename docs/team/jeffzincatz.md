---
layout: page
title: Xu Jiheng's Project Portfolio Page
---

## Project: HR Manager

HR Manager is a candidate and interview management application used for helping HR Managers of small organisations
or businesses in managing candidates, job positions and interviews all in one application.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### Code contributed: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=jeffzincatz)

### Enhancement Implemented

* **New Feature: Add job position and position list into the HR Manager**
  * What it does: Implements a model of job positions and position list, containing information including the job position title and posting status of all positions, with relevant test cases.
  * Justification: This feature is the foundation to allow user to access and manage all the stored position data in the system later with other related commands.

* **New Feature: Adds the functionality to list all job positions in the display. `list_p`**
    * What it does: Display a full list of all positions stored in the system, read from storage.
    * Justification: This feature gives the users a comprehensive overview of all the existing job positions for further management.

* **New Feature: Adds interviews and interview list into the HR Manager**
  * What it does: Implements a model of interviews and interview list, containing information including the job position interviewed, date, time, duration, candidates, and interview status of all interviews, with relevant test cases.
  * Justification: This feature is the foundation to allow user to access and manage all the stored interview data in the system with other related commands.

* **New Feature: Adds the functionality to list all job positions in the display. `list_i`**
    * What it does: Display a full list of all interviews stored in the system, read from storage.
    * Justification: This feature gives the users a comprehensive overview of all the existing interviews for further management.

* **Enhancement: Improves the UI to a 3-panel display**
  * What it does: Display all candidates, positions, and interviews in 3 panels.
  * Justification: Compared to the old implementation of 1-panel display, this enables the users to easier view all lists without replacing the previous ones.

* **Enhancement: Applies a new colour scheme for HR Manager**
  * What it does: Changes the graphical appearance of the HR Manager, including highlighting the panel lists, making HR Manager more distinct from other applications.
  * Justification: This makes the HR Manager a more distinct product.

### Contribution to Documentations

* User Guide:
  * Add documentation for `position` and its storage
  * Add documentation for: `list_c`, `add_p`, `delete_p`, `list_p`, `list_i`.

* Developer Guide:
  * Add use cases, including for `list_c`, `delete_c`, `list_p`, `delete_p`, `list_i`, `delete_i`.
  * Add user stories, including for `add_p`, `delete_p`, `list_p`.
  * Added the implementation of the find features (`list_c`, `list_p` and `list_i`), including UML diagrams for relevant classes and general find command execution.

### Contributions to team-based tasks

* Set up team project repo
* Documenting user stories in table format in Developer Guide
* Refactor existing candidate command's format to include a "_c" suffix, after new models (positions and interviews) were added
* Fix various documentation bugs in UG and DG

### Review contributions
* Reviewing team PRs
  * [A full list](https://github.com/AY2122S1-CS2103T-W13-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajeffzincatz)
  of PRs reviewed by me.

* Reporting bugs for other teams
  * [A full list](https://github.com/JeffZincatz/ped/issues) of bugs reported
