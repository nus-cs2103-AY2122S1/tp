---
layout: page
title: Nobel Ang's Project Portfolio Page
---

## Project: HR Manager

HR Manager is a candidate and interview management application used for helping HR Managers of small organisations
or businesses in managing candidates, job positions and interviews all in one application.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### Code contributed
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=angnobel&tabRepo=AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### Enhancement Implemented
* **New Feature: `find_c`**
    * What it does: The existing `find` command was changed to `find_c` and users are allowed to search across all fields present in candidate
    * Justification: This greatly increases the capabilities of the find command, allowing searches specific to all potential fields. Searches could be done across different fields with "and" combinator

* **New Feature: `find_p`**
    * What it does: Similar to the `find_c` command, searches for positions that fulfills the keyword requirement.
    * Justification: This extends the search functionality to position list, which is crucial to manage many positions.

* **New Feature: `find_i`**
    * What it does: Similar to the `find_c` command, searches for interviews that fulfills the keywords supplied for each field. The time field is specially handled to find interviews that occur during the time provided.
    * Justification: Allows the user to handle many interviews and search for interviews that are occuring at a specific date/time.

* **Enhancement: status attribute for candidates**
    * Add a Status enum for candidates to tag the candidate with their status in the hiring pipeline
    * Modified all exisiting commands (add, edit, find) and the GUI to handle and display the data

* **New Feature: Filtered label on GUI**
    * Added a label about each list to indicate what the list represents. The label updates to indicate that the list is filtered if a `find_x` command is run.

* **Various Bugfixes**
  * Fixed small bugs, the non-exhaustive list below 
      * Clear command returns Address book has been cleared
      * Position status not accepting case-insensitive inputs

#### Contributions to the UG
* Added initial documentation of storage (for candidates)
* Added purpose of UG and *How to use this user guide section*
* Added *Table of Inputs* for position and interviews
* Added details and examples for `find_c`, `find_p` and `find_i` commands

#### Contributions to the DG
* Added use case for adding new candidate
* Added introduction and purpose of DG
* Added specific details of the `find_x` commands with class diagram, sequence diagram and design consideration for the logical combination within and across fields

#### Contributions to team-based tasks
* Facilitated weekly meetings by setting agenda and distribution of work
* Handled the creation and management of issues and milestones
* Filtered and tagged bugs after Mock PE

#### Review contributions
* Full list of [PR Reviewed](https://github.com/AY2122S1-CS2103T-W13-1/tp/pulls?q=is%3Apr+reviewed-by%3Aangnobel)
