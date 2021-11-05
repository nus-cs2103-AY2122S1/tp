---
layout: page
title: Lim Yu Zher's Project Portfolio Page
---

### Project: gitGud

gitGud is a desktop application for managing friends' gaming information. gitGud uses a simple Graphical User Interface (GUI) combined with an optimized Command Line Interface (CLI) to give you a smooth and efficient experience. If you can type fast enough, gitGud can get you contact management tasks done faster than traditional GUI-based applications.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=lzher385&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=LZher385&tabRepo=AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFilesGlob=)

* **New Feature**:
  1. Added the ability to link a friend with a game.
     1. What it does: allows the user to associate a friend in their contact list with a game that was previously recorded.
     2. Justification: This feature improves the product significantly because a user is able to note down what games their friends play.
  2. Added the ability to unlink a friend from a game
     1. What it does: allows the user to remove the association between a friend and a game that the friend is currently linked to.
     2. Justification: This feature improves the product significantly as it provides greater flexibility in case a friend no longer plays a particular game.
  3. Added the ability for the UI to display tables related to a game or a friend.
     1. What it does: allows the user to see all relevant information related to particular friend/game.
     2. Challenge: This feature required self-directed learning to learn about JavaFX's TableView and how to use it responsively display data.
     3. Justification: This feature allows the user to see all information at a glance in neat and tidy format.
  4. Added the logic for the UI to persist in displaying the previous friend/game that was retrieved using the "get" command.
     1. What it does: allows the user to continue seeing the previous friend/game that he ran the "get" command for, even when other commands are run.
     2. Challenge: This feature required thinking through and applying the logic of when to change what was currently displayed, and tracking what the previous friend/game retrieved was.
     3. Justification: This feature significantly improved user experience as the central display of the UI would not be constantly and unnecessarily refreshed after every command.
     
* **Testing**:
  * Added unit tests for link feature.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `friend --get`, `game --get`, `friend --link` and `game --link`.
    * Edited the introduction to make it more user-focused and gamer-themed.

  * Developer Guide:
    * Added glossary for gitGud.
    * Added user story for `link` feature.
    * Updated UI component section to match gitGud.
    * Added implementation section of `friend --link` and `friend --unlink` feature.

* **Contribution to team-based tasks:**
  * Work closely with teammates to fully revamp the UI and kept them up to date about changes to the logic in the UI, so they could continue working on the UI in parallel.
