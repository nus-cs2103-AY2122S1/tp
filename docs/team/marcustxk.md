---
layout: page
title: Marcus Tang's Project Portfolio Page
---

### Project: gitGud

gitGud is a desktop application for managing friends' gaming information. gitGud uses a simple Graphical User Interface
(GUI) combined with an optimized Command Line Interface (CLI) to give you a smooth and efficient experience. If you can
type fast enough, gitGud can get you contact management tasks done faster than traditional GUI-based applications.

Given below are my contributions to the project.

* **New Feature**: Added the ability to list/filter friends.
  * What it does: allows the user to list all friends stored in gitGud, with the ability to optionally filter by `FRIEND_ID` containing any of the given keywords.
  * Justification: This feature improves the product significantly because a user can easily view all friends they have and apply any filters to easily find specific friends.
* **New Feature**: Added the ability to list/filter games.
  * What it does: allows the user to list all games stored in gitGud, with the ability to optionally filter by `GAME_ID` containing any of the given keywords.
  * Justification: This feature improves the product significantly because a user can easily view all games they have and apply any filters to easily find specific games.
* **Refactor**: Refactored the parser to support new syntax of commands for games and friends each having their own CRUD commands using the flag system.
* **New Feature**: Added the schedule friend command.
  * What it does: allows the user save the schedule of their friend to record when they are free or busy during the week.
  * Challenge: This feature required creating of the `Schedule` and `Day` fields, so the logic (command, parser), model (schedule, day) and storage(Jackson serialization of new fields) packages all had to be updated to support the feature, as well as relevant tests for all the mentioned layers.
  * Justification: This feature improves the product significantly because a user can easily save the schedule of their friend and not have to manually remember when they are free or busy.
* **New Feature**: Added the UI to display schedule of friend when friend get command is input.
  * What it does: allows the user to view the schedule of a friend to view their blocks of free time.
  * Challenge: This feature required designing and implementing how to effectively display the schedule of a user in limited space, which was ultimately done by grouping of adjacent 1h timeslots into a larger timeslots (e.g. `0800 - 0900` and `0900 - 1000` will be displayed as `0800 - 1000`) to save space. 
  * Justification: This feature improves the product significantly because a user can easily view the free timeslots of their friend to know when they are able to play with them.
* **Testing**: Added tests for classes in the logic package to help attain 100% methods and almost 100% lines coverage.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=MarcusTXK&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MarcusTXK&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `list --friend`, `list --game` and `friend --schedule`.
    * Designed and created mockup GUI for gitGud `v1.1` - `v1.3`.
    * Added command format notes.
    * Updated structure of User Guide commands into a more coherent flow by grouping them into respective Friend, Game and Others section and added a writeup for each section.

  * Developer Guide:
    * Added personas for gitGud.
    * Added user story for `list` feature.
    * Updated the logic section of the Developer Guide.
    * Added implementation section of `friend --schedule` feature.
  
