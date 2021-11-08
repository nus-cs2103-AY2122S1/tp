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

* **New Features**
  1. Project-wide refactoring of codebase to adapt AB3 to gitGud's needs. <br>
     What it involves:
       * Refactoring of Persons and Tags to Friends and Games classes for team usage.
       * Updating of relevant test cases
     <br>
    Justification: Refactoring the codebase for gitGud's structure provides a base for our team to extend
         and develop gitGud specific features on top of. 
     
  2. Add Friend functionality <br>
     What it does: Allows users to add friends to the gitGud friends list. 
     <br>
     Justification: Core functionality for users to store list of friends.  
     
  3. Edit Friend functionality <br>
     What it does: Allows users to edit friend's name.  
     <br>
     Justification: A friend can store a lot of information linked to it - e.g. linked games, skill values, schedule etc. 
     Hence, it is important to be able to update information tied to a friend without having to delete and add again which 
     requires user to have to re-provide the previous information of the deleted friend when adding again.
     
  4. Add Game functionality <br>
     What it does: Allows users to add games to the gitGud games list.<br>
     Justification: Core functionality for users to store list of games. 
     
  5. Implemented Storage support for Friends and Games <br>
     What it does: Allows friends and games data to be persisted across multiple application runs by storing in json files. This 
     allows our application to fulfill its main objective of being able to record friends' gaming information.
     Justification: Without support for persistent storage, users would have to re-enter the same information with each run of 
     our application to use other functionality, making the use of our application cumbersome and unproductive.
     
  6. Friend recommendation functionality<br>
     Justification: A core feature which allows our application of fulfilling its main objective of being able to record friends' 
     gaming information and availability to find a suitable time to play competitive games with stored friends.
     
  6. Friend recommendation functionality <br>
     What it does: Recommends friends for user to play with for a given game and given time based on highest skill value of friend.<br>
     Justification: This functionality solves our user's problem of finding optimal friends to play with that both fits into their busy schedules and 
     provides the highest potential win rate for competitive gaming by providing preferred(highest skilled) friends available during a specified time 
     with a single command.

* **Testing**
  1. Wrote unit/integration tests for storage, add friend and add game commands.
  2. Wrote unit/integration tests(with Model) for Edit Friend functionality
  3. Wrote unit/integration tests for recommend functionality - ensuring it works with e.g. list command in succession.

* **Code contributed**: 
  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=kevin9foong&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
  
* **Project management**:

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:
    1. Added the initial documentation for the features `add friend`, `add game`, `delete game` and 
       `link game and friend`.
    2. Maintenance of `add friend` and `add game` features in the UG.  
    3. Added the documentation for the features `assign skill`, `edit friend` and `recommend friend to play with`.
    4. Made changes to formatting and optimizing words used. 
    5. Added symbols, constraints and warnings to each user command for user's reference. 
    6. Added the application UI overview and FAQ sections. 
    7. Added glossary section including terminologies used in our project such as `FRIEND_ID` for users to know what it means. 
  * Developer Guide:
    1. Added the value proposition for gitGud.
    2. Added use case for `add friend`.
    3. Discovery of bugs related to Use Case Extension numbering.  
    4. Added documentation for Storage component (e.g. UML diagram, description etc). 
    5. Added implementation details for Recommend command. 
  * README:
    1. Updated Github Actions build status badge to reflect status of gitGud team repository.
    2. Updated description and organised layout of README.

* **Community**:
  1. PRs reviewed (With non-trivial review comments):
    * [Ui Recommendations and bugs from Review](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/107)
    * [Discovery of 'hidden bug' which led to defensive programming fix in Schedule PR](https://github.com/AY2122S1-CS2103T-W13-4/tp/pull/113)
      The hidden bug is for corrupted data files not containing schedule fields in the json format data, the 
      schedule commands fail.
  
* **Tools**:
  1. Integrated the Github plugin (codecov) to the team repo.

* **Miscellaneous**
  1. Set up initial project website.
  2. Initial set up for team repo & organization.
