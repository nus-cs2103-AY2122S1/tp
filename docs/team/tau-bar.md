---
layout: page
title: Taufiq's Project Portfolio Page
---

### Project: gitGud

gitGud is a **desktop application for storing and managing your friends' gaming information and schedules**.
It is dedicated to gamers like you and provides a gamer-themed experience with a focus on increasing your efficiency through
its typing-based interface.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tau-bar&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MarcusTXK&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Features**:
  1. Added the ability to add friends in [PR#48](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/48).
     - What it does: allows the user to add a friend to gitGud by adding the friend's unique `FRIEND_ID` and
            optionally, `FRIEND_NAME`. `Refactor - Enhancement`
     - Justification: This feature improves the product significantly, as it is a core feature, which allows the
            user to add the friend to gitGud to start planning their gaming schedule with that friend.
  2. Added the ability to delete friends in [PR#48](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/48).
     - What it does: allows the user to delete a friend from gitGud using the friend's unique `FRIEND_ID`. `Refactor - Enhancement`
     - Justification: This feature improves the product significantly because a user can now delete a friend from
              gitGud in the event that they made a mistake in the details or simply wishes to remove a friend.
  3. Added the ability to delete games in [PR#68](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/68).
     - What it does: allows the user to delete a game from gitGud using the game's unique `GAME_ID`. `New Feature` 
     - Justification: This feature improves the product significantly because a user can easily delete a game from
         gitGud in the event that they wish to remove a game because the user no longer plays the game.
  4. Restructured and styled the UI for v1.2, v1.3 and v1.4 to be in its current state with 2 lists, the friend list 
     and the game list, as well as a main card in the middle to display the requested information by the user in 
  [PR#145](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/145).
     - What it does:  allows the user to see details about a friend or a game when the user runs the `friend --get` 
       or `game --get` command.`New Feature`
     - Justification: This feature improves the product significantly, as one of the purposes of gitGud is to be 
       able to store and view your friends' data easily, such as their in-game usernames as well as their skill levels, 
       as well as the friend's availabilities, to make it easy for the user to choose who to play what game at what time.
         
* **Testing**
  1. Adapted the previous tests for our implementation of AddFriendCommand and DeleteFriendCommand in 
  [PR#48](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/48).
  2. Added unit/integration tests for DeleteGameCommand and DeleteGameCommandParser in 
  [PR#68](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/68)
  3. Added unit/integration tests for LinkFriendCommand and UnlinkFriendCommand, and linking methods in other 
     classes in [PR#184](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/184).
  4. Conducted extensive manual testing for UI.

* **Documentation**:
    * User Guide:
        * Added documentation and examples (eg. feature description) for the `friend --add`, `friend --delete`, `game 
          --add`
          `game --delete` and `friend --link` features in [PR#38](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/38).
        * Updated screenshots for the User Guide for gitGud v1.3b in [PR#124](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/124).
        * Updated screenshots for the User Guide for gitGud v1.4 in [PR#234](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/234).

    * Developer Guide:
        * Added user stories for gitGud in [PR#38](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/38) and 
      [PR#255](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/255)
        * Added use cases for features in [PR#38](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/38)
        * Added extensive implementation details for delete features including 
          sequence diagrams in
      [PR#184](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/184).
        * Added instructions for manual testing in Appendix in 
      [PR#76](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/76)
        * Added value proposition in [PR#38](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/38).
        * Fixed bugs after extensive review and standardise format in DG in 
      [PR#255](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/255)

    * README:
        * Added acknowledgements section and acknowledgements for project. 
        * Updated UI screenshot for gitGud v1.4.

#### Review/mentoring contributions (with non-trivial comments):
1. [Discovery of hidden bug in the `friend --add` functionality](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/61)
2. [Extensive review of DG Use Cases PR](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/262)

#### Contributions beyond project team:
* Evidence of helping others:
  1. [Helping of other team with bug testing](https://docs.google.com/document/d/1nXaZGo2nbEuU-jgpz8IDph73P4WFkGlo9_yW_paeNDE/edit?usp=sharing)
