---
layout: page
title: Marcus Tang's Project Portfolio Page
---

### Project: gitGud

gitGud is a **desktop application for storing and managing your friends' gaming information and schedules**.
It is dedicated to gamers like you and provides a gamer-themed experience with a focus on increasing your efficiency through
its typing-based interface.

Given below are my contributions to the project.

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=MarcusTXK&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MarcusTXK&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: 
  * Added the ability to list/filter friends.
    * What it does: allows the user to list all friends stored in gitGud, with the ability to optionally filter by `FRIEND_ID` containing any of the given keywords.
    * Justification: This feature improves the product significantly because a user can easily view all friends they have and apply any filters to easily find specific friends.
  * Added the ability to list/filter games.
    * What it does: allows the user to list all games stored in gitGud, with the ability to optionally filter by `GAME_ID` containing any of the given keywords.
    * Justification: This feature improves the product significantly because a user can easily view all games they have and apply any filters to easily find specific games.
  * Added the schedule friend's availability command.
    * What it does: allows the user to store the schedule of their friend, to record when they are free to play or busy during the week.
    * Challenge: This feature required creating of the `Schedule` and `Day` fields, so the logic (command, parser), model (schedule, day, util for to handle time conversion) and storage (Serialization/Deserialization of new fields into/out of JSON) packages all had to be updated to support the feature.
    * Justification: This feature improves the product significantly because a user can easily save the schedule of their friend and not have to manually remember when they are free or busy.
  * Added the UI to display schedule of friend when friend get command is input.
    * What it does: allows the user to view the schedule of a friend to view their blocks of free time.
    * Challenge: This feature required designing and implementing how to effectively display the schedule of a user in limited space, which was ultimately done by grouping adjacent 1h timeslots into a larger timeslots (e.g. `0800 - 0900` and `0900 - 1000` will be displayed as `0800 - 1000`) to save space. 
    * Justification: This feature improves the product significantly because a user can easily view the free timeslots of their friend to know when they are able to play with them.

* **Testing**: 
  * Added unit/integration tests for all classes in the logic package to help attain 100% methods and 98% lines coverage.  
  * Added unit/integration tests for all relevant classes (logic/util/model etc.) involved in the list friend, list game and schedule friend features.
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `list --friend`, `list --game`, `friend --schedule` and `exit`.
    * Designed and created mockup/wireframe GUI for gitGud `v1.1` - `v1.3`.
    * Added command format notes.
    * Updated structure of User Guide commands into a more coherent flow by grouping them into respective Friend, Game and Others section and added a writeup for each section header.

  * Developer Guide:
    * Added target user profiles for gitGud.
    * Added user story `list` feature.
    * Updated the logic component section of the Developer Guide.
    * Added implementation section of `friend --schedule` feature.
    * Added use cases for all newly implemented features.
  
* **Contributions to team-based tasks**:
  * Refactored the parser to support new syntax of commands for games and friends each having their own CRUD commands using the flag system.
  * Updated gitGud application name and logo.
  * Updated `Ui.png` and README.md for initial project setup (`v1.1`).
  * Created and managed milestones `v1.3b` and `v1.4`.  
  * Added branch protection for `master` branch to support transition to feature branch workflow and prevent accidental pushes to `master` branch.
  * Added Pull Request checks, for at least 1 Approval and for all checks to pass before allowing the PR to be merged, to ensure `master` branch code is always working.
  
* **Review/mentoring contributions**:
  * Some examples:
    * [Found bugs after testing of new UI for v1.3b](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/128)
    * [Found bugs after testing of new UI for v1.3](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/107)
    * [Prevented broken links and wrong sequence diagram for DG from being pushed](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/246)
