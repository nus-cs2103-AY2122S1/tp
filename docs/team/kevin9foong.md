kevin9foong.md---
layout: page
title: Kevin Foong's Project Portfolio Page
---

### Project: gitGud

[gitGud](https://ay2122s1-cs2103t-w13-4.github.io/tp/) is a typing-focused desktop application for managing friends' gaming information. 
It is dedicated to busy gamers and aims to solve their key problem by helping them find available and highest skilled 
friends to win competitive games with. <br>

Given below are my contributions to the project.
* Code contributed: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=kevin9foong&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
* New Features
  1. Add Friend functionality ([PR #68](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/69)) <br>
     **What it does**: Allows users to add friends to the gitGud friends list.<br>
     **Justification**: Core functionality for users to store list of friends.
  2. Edit Friend functionality ([PR #93](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/93)) <br>
     **What it does**: Allows users to edit friend's name.<br>
     **Justification**: Allows user to update friend's name without having to re-create friend and having to re-provide same information.
  3. Add Game functionality ([PR #68](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/69)) <br>
     **What it does**: Allows users to add games to the gitGud games list.<br>
     **Justification**: Core functionality for users to store list of games.
  4. Implemented Storage support for Friends and Games ([PR #68](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/69)) <br>
     **What it does**: Allows friends and games data to be stored across multiple application runs by storing data in JSON files on the hard drive. <br>
     **Justification**: Allows users to store gaming information permanently without having to re-enter each time gitGud is used.<br>
  5. Add friend skill functionality ([PR #102](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/102)) <br>
     **What it does**: Allows user to store and update friends' skill at different games.<br>
     **Justification**: Core functionality for users to keep track of and remember the most-highly skilled friends to play with.
  6. Friend recommendation functionality ([PR #153](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/153)) <br>
     **What it does**: Recommends friends for user to play with for a given game and given time based on highest skill value of friend.<br>
     **Justification**: Core functionality which helps users find optimal friends to play with that both fits into their busy schedules and 
     is the most skilled.

* Testing 
  1. Wrote unit/integration tests achieving >96% lines and 100% method coverage for Storage component, Add Friend, 
     Add Game, Add Friend Skill and Recommend functionality (including e.g. integration tests which ensures recommend works with list command in succession)
  2. Manual testing and reporting of bugs in application, UG and DG. ([Issues raised and resolved](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues?q=is%3Aissue+author%3Akevin9foong+is%3Aclosed))
  3. Discovery of testing bugs (e.g. schedule tests which use shared mutable references) ([PR #153](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/153/files))  

* Contribution to team based tasks
  1. Project-wide refactoring of codebase to adapt AB3 to gitGud's needs (mostly implemented in [PR #45](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/45)). <br>
        * Refactoring of Persons and Tags to Friends and Games classes and updating of test cases.
  2. Setting up of tools 
     1. Integrated the GitHub plugin (codecov) to the team repo.
  3. Miscellaneous tasks: 
     1. Set up initial project website.
     2. Initial set up for team repo & organization.
     3. Facilitation of weekly team meeting. 
     
* Documentation
  * User Guide (UG) ([PR #209](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/209), [PR #133](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/133) and [PR #45](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/133)):
    1. Added initial documentation for the features `add friend`, `add game`, `delete game` and 
       `link game and friend`.
    2. Added documentation for `edit`, `add friend skill` and `recommend` features.
    3. Overhauled UG to make it more conversational and directed to target user. 
    4. Added icons/symbols, command constraints and warnings to each user command. 
    5. Added the user guide structure, glossary, application UI overview and FAQ sections.
  * Developer Guide (DG):
    1. Added the value proposition of gitGud
    2. Added use case for `add friend`.
    3. Highlighting of bugs (e.g. Use Case Extension numbering ([Issue #243](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/243)) and UML diagram bugs (e.g. bugs with reference frame notation) ([Issue #251](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/251)))
    4. Added documentation for Storage component (including UML class diagram) and `recommend` command implementation (including UML activity and sequence diagrams).
  * README:
    1. Updated GitHub Actions build status badge and codecov badge, description and layout of README.

* Review/mentoring contributions (with non-trivial comments)
  1. [Ui Recommendations and bugs from Review](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/107)
  2. [Discovery of 'hidden bug' which led to defensive programming fix in Schedule PR](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/113)
  3. [Highlighting of important DG bugs in UML diagrams and guide on how to fix](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/255)

* Contributions beyond project team
  * Evidence of helping others: 
    1. Forum responses:
       1. [When to use assertions](https://github.com/nus-cs2103-AY2122S1/forum/issues/190#issuecomment-913379752)
       2. [Git on removing tags from commits](https://github.com/nus-cs2103-AY2122S1/forum/issues/24#issuecomment-899956054)
    2. Helping of other team with bug testing
    [Bugs reported](https://docs.google.com/document/d/1nXaZGo2nbEuU-jgpz8IDph73P4WFkGlo9_yW_paeNDE/edit)
