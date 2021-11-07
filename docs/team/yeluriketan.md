---
layout: page
title: Yeluri Ketan's Project Portfolio Page
---

### Project: gitGud

#### What is gitGud?

gitGud is a desktop application for managing friends' gaming information. gitGud uses a simple Graphical User Interface
(GUI) combined with an optimized Command Line Interface (CLI) to give you a smooth and efficient experience. If you can
type fast enough, gitGud can get you contact management tasks done faster than traditional GUI-based applications.

* **Notable Code Contributions**:
    1. Created the structure and support for Games and GameFriendLinks `New Addition`
       - Created the necessary objects, lists, and integrated them into the gitGud's model.
    2. Added the ability to get a friend.
       - What it does: allows the user to get all information linked to a friend. `New Feature`
       - Justification: This feature improves the product significantly because a user is now able to view all the information
       about a friend at one place, which includes the schedule, all the games that friend plays, their respective in-game
       usernames and the skill level.
    3. Added the ability to get a game.
       - What it does: allows the user to get all information linked to a game. `New Feature`
       - Justification: This feature improves the product significantly because a user is now able to view all the information
       about a game at one place, which includes all the friends that play the game, their respective in-game usernames
       and the skill level for the game.
    4. Update Validation Regex `Refactor - Enhancement`
       - Update and standardize a validation regex for data classes
    5. Update command usage messages `Refactor - Enhancement`
       - Update and standardize the command usage messages for various commands and subcommands.
<br>
* **Testing**
    1. Wrote unit/integration tests for Game, GameFriendLink and related classes.
    2. Wrote unit/integration tests for `friend --get` and `game --get` commands.
    3. Update unit/integration tests for `model` component to reach 100% class coverage, 100% method coverage, and 99% line coverage.
<br>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=YeluriKetan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=YeluriKetan&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:

* **Enhancements to existing features**:
    1. Refactor `Friend` to keep track of the `GameFriendLink`s in the form of a Map instead of the traditional Set used in AB3.
    2. Purge `find` command from the codebase. Replace with `--list` subcommand.
<br>
* **Documentation**:
    * User Guide:
        * Added the Introduction, Quick start, FAQ and Command Summary to complement the rest of the documentation.
        * Update documentation for `friend --get` and `game --get`
    * Developer Guide:
        * Added use case for the `get` feature.
        * Added the Non-Functional Requirements (NFR) section.
        * Update the `Model` component
    * README:
        * Created and added the product logo.
<br>
* **Community**:

* **Tools**:
