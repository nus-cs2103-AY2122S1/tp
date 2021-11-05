tau-bar.md---
layout: page
title: Taufiq's Project Portfolio Page
---

### Project: gitGud

gitGud is a desktop application for managing friends' gaming information. gitGud uses a simple Graphical User Interface
(GUI) combined with an optimized Command Line Interface (CLI) to give you a smooth and efficient experience. If you can
type fast enough, gitGud can get you contact management tasks done faster than traditional GUI-based applications.

Given below are my contributions to the project.

* **New Feature**:
  1. Added the ability to add friends.
     - What it does: allows the user to add a friend to gitGud by adding the friend's unique `FRIEND_ID` and
            optionally, `FRIEND_NAME`. `Refactor - Enhancement`
     - Justification: This feature improves the product significantly, as it is a core feature, which allows the
            user to add the friend to gitGud to start planning their gaming schedule with that friend.
  2. Added the ability to delete friends.
     - What it does: allows the user to delete a friend from gitGud using the friend's unique `FRIEND_ID`. `Refactor - Enhancement`
     - Justification: This feature improves the product significantly because a user can easily delete a friend from
              gitGud in the event that they made a mistake in the details or simply wishes to remove a friend.
  3. Added the ability to delete games.
     - What it does: allows the user to delete a game from gitGud using the game's unique `GAME_ID`. `New Feature` 
     - Justification: This feature improves the product significantly because a user can easily delete a game from
         gitGud in the event that they made a mistake in the details or simply wishes to remove a game.
  4. Restructured the UI to be in its current state with 2 lists and a main card in the middle to display the 
     requested information by the user.
     - What it does:  allows the user to see details about a friend or a game when the user runs the `friend --get` 
       or `game --get` command.`New Feature`
     - Justification: This feature improves the product significantly, as the point of gitGud is to be able to view 
       the users' data easily, such as their in-game usernames as well as their skill levels, in-game usernames as 
       well as the friend's availabilities, to make it easy for the user to choose who to play what game at what 
       time.  
         
* **Testing**
  1. Adapted the previous tests for our implementation of AddFriendCommand and DeleteFriendCommand.
  2. Added tests for DeleteGameCommand and DeleteGameCommandParser.
  3. Added tests for LinkFriendCommand and UnlinkFriendCommand, and linking methods in other classes.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tau-bar&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MarcusTXK&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `friend --delete`.
        * Added screenshots for the User Guide for gitGud v1.3.

    * Developer Guide:
        * Added user stories for gitGud.
        * Added use case for `delete` feature.
        * Added implementation details for `delete` feature.

    * README:
        * Added acknowledgements section and acknowledgements for project. 
