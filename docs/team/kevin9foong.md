kevin9foong.md---
layout: page
title: Kevin Foong's Project Portfolio Page
---

### Project: gitGud

#### What is gitGud?

gitGud is a desktop application for managing friends' gaming information. gitGud uses a simple Graphical User Interface
(GUI) combined with an optimized Command Line Interface (CLI) to give you a smooth and efficient experience. If you can
type fast enough, gitGud can get you contact management tasks done faster than traditional GUI-based applications.

Given below are my contributions to the project.

#### New Features
  1. Add Friend functionality <br>
     **What it does**: Allows users to add friends to the gitGud friends list.<br>
     **Justification**: Core functionality for users to store list of friends.  
     
  2. Edit Friend functionality <br>
     **What it does**: Allows users to edit friend's name.<br>
     **Justification**: A friend can store a lot of information linked to it - e.g. linked games, skill values, schedule etc. 
     Hence, it is important to be able to update information tied to a friend without having to delete and add again which 
     requires user to have to re-provide the previous information of the deleted friend when adding again.
     
  3. Add Game functionality <br>
     **What it does**: Allows users to add games to the gitGud games list.<br>
     **Justification**: Core functionality for users to store list of games. 
     
  4. Implemented Storage support for Friends and Games <br>
     **What it does**: Allows friends and games data to be persisted across multiple application runs by storing in json files. This 
     allows our application to fulfill its main objective of being able to record friends' gaming information.<br>
     **Justification**: Without support for persistent storage, users would have to re-enter the same information with each run of 
     our application to use other functionality, making the use of our application cumbersome and unproductive.
     
  5. Add friend skill functionality <br>
     **What it does**: Allows user to store and update friends' skill at different games.<br>
     **Justification**: A core feature which allows our application of fulfilling its main objective of being able to record friends' 
     skill values at specific games, so they can keep track of and find the most-highly skilled friends to play with.
     
  6. Friend recommendation functionality <br>
     **What it does**: Recommends friends for user to play with for a given game and given time based on highest skill value of friend.<br>
     **Justification**: This functionality solves our user's problem of finding optimal friends to play with that both fits into their busy schedules and 
     provides the highest potential win rate for competitive gaming by providing preferred(highest skilled) friends available during a specified time 
     with a single command.

#### Testing 
  1. Wrote unit/integration tests for Storage component.
  2. Wrote unit/integration tests for Add Friend and Add Game commands.
  3. Wrote unit/integration tests(with Model) for Edit Friend functionality.
  4. Wrote unit/integration tests for Add Friend Skill functionality.
  5. Wrote unit/integration tests for Recommend functionality - ensuring it works with e.g. list command in succession.
  6. Manual testing and highlighting of bugs and reporting of issues observed in application, UG and DG.
  7. Discovery of testing bugs with Schedule tests which use shared mutable references to the same Friend instance 
     (fixed in [PR #153](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/153/files))  

#### Contribution to team based tasks
1. Project-wide refactoring of codebase to adapt AB3 to gitGud's needs. <br>
    * **What it involves**:
      * Refactoring of Persons and Tags to Friends and Games classes for team usage.
      * Updating of relevant test cases
    * **Justification**: Refactoring the codebase for gitGud's structure provides a base for our team to extend and develop gitGud specific features on top of.

2. Setting up of tools 
   1. Integrated the Github plugin (codecov) to the team repo.

3. Miscellaneous tasks: 
   1. Set up initial project website.
   2. Initial set up for team repo & organization.
   3. Facilitation of weekly team meeting. 

#### Code contributed 
  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=kevin9foong&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

#### Documentation
  * User Guide:
    1. Added the initial documentation for the features `add friend`, `add game`, `delete game` and 
       `link game and friend`.
    2. Maintenance of `add friend` and `add game` features in the UG.  
    3. Added documentation for `add friend skill` and `recommend` features. 
    4. Added the documentation for the features `assign skill`, `edit friend` and `recommend friend to play with`.
    5. Made changes to formatting and optimization of words used to make user guide more conversational and directed to target user. 
    6. Added symbols, constraints and warnings to each user command for user's reference. 
    7. Added the user guide structure (including icons), application UI overview and FAQ sections. 
    8. Added glossary section including terminologies used in our project such as `FRIEND_ID` for users to know what it means. 
  * Developer Guide:
    1. Added the value proposition for gitGud.
    2. Added use case for `add friend`.
    3. Highlighting of bugs related to Use Case Extension numbering. [Issue #243](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/243)  
    4. Highlighting of UML diagram bugs in delete and schedule sequence diagrams (eg. found notation bugs for Reference frame etc.) [Issue #251](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/251)
    5. Added documentation for Storage component (including UML class diagram for Storage). 
    6. Added implementation documentation for `recommend` command (including UML activity diagram and sequence diagrams for Recommend). 
  * README:
    1. Updated Github Actions build status badge to reflect status of gitGud team repository.
    2. Updated description and organised layout of README.

#### Review/mentoring contributions (with non-trivial comments): 
1. [Ui Recommendations and bugs from Review](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/107)
2. [Discovery of 'hidden bug' which led to defensive programming fix in Schedule PR](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/113)
      The hidden bug is for corrupted data files not containing schedule fields in the json format data, the schedule commands fail.
3. [Highlighting of important DG bugs in UML diagrams and guide on how to fix](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/255)

#### Contributions beyond project team:
* Evidence of helping others: 
  1. Forum responses:
  [When to use assertions](https://github.com/nus-cs2103-AY2122S1/forum/issues/190#issuecomment-913379752)
  [Git on removing tags from commits](https://github.com/nus-cs2103-AY2122S1/forum/issues/24#issuecomment-899956054)
  2. Helping of other team with bug testing
  [Bugs reported](https://docs.google.com/document/d/1nXaZGo2nbEuU-jgpz8IDph73P4WFkGlo9_yW_paeNDE/edit)
