---
layout: page
title: Darren Hoon's Project Portfolio Page
---

## Project: Trace2Gather
Trace2Gather is a desktop address book application used for managing hotel guests and rooms. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t13-3&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=darrenhoon&tabRepo=AY2122S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Enhancements Implemented
1. Linked Database's Room details to GUI, where Rooms are displayed on the GUI's list of rooms.
    * <b>What it does</b>: Allows users to visually see the rooms that were saved in the database and their last updated status.
    * <b>Justification</b>: This feature improves the product significantly because being able to visually check a room's status, such as its Occupancy Status, helps it's users to identify a room's status quickly and increases response time to the user requesting such information.
    * <b>Highlights</b>: This enhancement affects user interaction with the application. It required an in-depth analysis and understanding of the GUI codebase and jackSon's syntax, such as @FXML tags having only small characters
    * <b>Credit</b>: jackSon documentation, stackOverflow
2. Find Room command to enable users to search for specific rooms
    * <b>What it does</b>: Allows users to search for specific rooms in the given list of rooms
    * <b>Justification</b>: This feature improves the product significantly as being able to quickly search for specific rooms helps the user to retrieve information about the room faster as compared to manually scrolling down till they find the room
    * <b>Highlights</b>: This enhancement required us to put ourselves in the shoes of a potential user and what were the pain points that might arise from our application without this feature.
    * <b>Credit</b>: Existing UML Diagrams, Well-thought out code structure that enables easy extensibility.
3. Added GUI elements for Room and History card panels in the application.
    * <b>What it does</b>: Allow users to see in detail the changes to the state of the application based on their actions and their accompanying information.
    * <b>Justification</b>: Being able to visually see which guests are in which rooms enable faster retrieval of information. Colour coding for vacancy and guests enables faster information retrieval for the user by allowing users to gain an intuitive understanding of the status of the room card's layout and distinction from one room to the next.
      <br>Displaying the residencies on the frontend via a list affords the user with the choice to interact with the existing database either via the GUI application or via direct JSON accesses. Being able to access it from the GUI along with the commands we added to filter residencies help users to identify residencies of interest at a much faster rate.
    * <b>Highlights</b>: These enhancements enabled us to visually check if our system is registering the changes based on the commands we type and the colour coding enables a clear distinction and focus of each room and its internal details from another.
    * <b>Credit</b>: Original AB3 team for making the code structure highly extensible and their FlowPane's styling in the CSS, [coolors](https://coolors.co/) to help with the colour coordination.


### UG Contributions
* Introduction in each section
* Purpose of Trace2Gather and Purpose of Guide
* Navigation Guide
* Redirected Help Window's link to our project's User Guide Page

### DG Contributions
* Implemented [Use Cases Page](https://ay2122s1-cs2103t-t13-3.github.io/tp/UseCases.html)
* Amended UML Diagrams
* Added "Appendix: Effort" section

### Team-Based Tasks Contributions
1. Main bug finder and user tester for application, reporting bugs to the team to be fixed.
2. Maintaining issues tracker.
3. Designing and making our product icon.
4. Releasing v1.4 (trial) of product.
5. Testing some new features that were not covered by others.
6. Providing boilerplate code for team to start work in first week of team project.

### Review / Mentoring Contributions
1. Coordinated with the team frequently and kept track of each other's progress.
2. Assisted in solving problems raised by the team when developing the product.
3. Reviewed Pull Requests and assisted in adding test cases to ensure that code coverage is not significantly reduced.
4. Assisted in debugging issues raised by the team or others.

### Contributions Beyond the Project Team
* PRs reviewed (with non-trivial review comments): [\#144](https://github.com/nus-cs2103-AY2122S1/ip/pull/144), [\#486](https://github.com/nus-cs2103-AY2122S1/ip/pull/486), [\#173](https://github.com/nus-cs2103-AY2122S1/ip/pull/173)
* Contributed to forum discussions: [\#176](https://github.com/nus-cs2103-AY2122S1/forum/issues/176), [\#183](https://github.com/nus-cs2103-AY2122S1/forum/issues/183), [\#296](https://github.com/nus-cs2103-AY2122S1/forum/issues/296)
* Updated module setup guide for all students to reflect latest intelliJ settings: [\#3](https://github.com/se-edu/guides/pull/3)
