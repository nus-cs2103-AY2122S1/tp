---
layout: page
title: Marcus Tang's Project Portfolio Page
---

### Project: gitGud

gitGud is a desktop application for managing friends' gaming information. gitGud uses a simple Graphical User Interface
(GUI) combined with an optimized Command Line Interface (CLI) to give you a smooth and efficient experience. If you can
type fast enough, gitGud can get you contact management tasks done faster than traditional GUI-based applications.

Given below are my contributions to the project.

* **New Feature**: Added the ability to list friends.
  * What it does: allows the user to list all friends stored in gitGud, with the ability to filter by friend id containing any of the given keywords.
  * Justification: This feature improves the product significantly because a user can easily view all friends they have and apply any filters to easily find specific friends.
* **New Feature**: Added the ability to list games.
  * What it does: allows the user to list all games stored in gitGud, with the ability to filter by game id containing any of the given keywords.
  * Justification: This feature improves the product significantly because a user can easily view all games they have and apply any filters to easily find specific games.
* **Refactor**: Refactored the parser to support new syntax of commands for games and friends each having their own CRUD commands using the flag system
* **New Feature**: Added the schedule friend command.
  * What it does: allows the user save the schedule of their friend to record when they are free or busy.
  * Justification: This feature improves the product significantly because a user can easily save the schedule of their friend and not have to manually remember when they are free or busy.
* **New Feature**: Added the UI to display schedule of friend when friend get command is input.
  * What it does: allows the user to view the schedule of a friend to view their blocks of free time.
  * Justification: This feature improves the product significantly because a user can easily view the free timeslots of their friend to know when to play with them.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=MarcusTXK&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MarcusTXK&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `list --friend`, `list --game` and `friend --schedule`.
    * Created mockup GUI for gitGud `v1.1` - `v1.3`.
    * Added command format notes.
    * Updated User Guide flow to ensure coherent flow of commands.

  * Developer Guide:
    * Added personas for gitGud.
    * Added user story for `list` feature.
    * Updated the logic section of the Developer Guide.
  
