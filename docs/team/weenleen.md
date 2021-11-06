---
layout: page
title: Win Lynn's Project Portfolio Page
---

### Project: tApp

tApp is a desktop app for managing tutorial groups and personal tasks, optimized for use via a Command Line Interface (CLI). It has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=weenleen)


* **New Feature**: Added the Task list and associated Storage, Parser etc. classes to the application (Pull Request: [\#79](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/79))
    * What it does: allows the user to store data regarding their tasks in tApp.
    * Justification: This feature improves the product significantly as it adds another layer of functionality to the application. As CS2103/T TAs are likely to have many tasks to complete in their schedule, such a feature will help them store data regarding those tasks, and access those tasks at their convenience.
    * Highlights: This enhancement required extensive knowledge on how AB3 was implemented, in order to expand on its functionality. The implementation was difficult in that it required adding many additional classes, as well as editing existing classes in order to accommodate a new entity type.
    * Credits: This feature is based on the implementation of the `Person` entity type in [AB3](https://se-education.org/addressbook-level3/DeveloperGuide.html).


* **New Feature**: Added a terminal display to the GUI (Pull Request: [\#125](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/125))
    * What it does: allows users to see their previous inputs and their respective outputs.
    * Justification: This feature improves the user experience as it allows them to view the previous changes made to the data in tApp, allowing for better management of their stored data.
    * Highlights: This feature required an understanding of JavaFX frameworks and how they respond to user inputs dynamically. The implementation was difficult in that other design alternatives had to be explored and implemented, that differed from the original AB3 design.


* **Enhancement**: Implemented auto-sorting the task list (Pull Request: [\#121](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/121))
    * What it does: automatically sorts the task list in tApp according to the task's date, completion status and priority level.
    * Justification: This allows users to see which tasks most urgently need their attention, adding more functionality to the application.
    * Highlights: This enhancement required being well verse in the `Model` component of tApp and how it is implemented. This feature required changing the methods of some classes.


* **Enhancement**: Marking Multiple tasks as Done (Pull Request: [\#134](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/134))
    * What it does: improves on the previously implemented `doneTask` command to allow it to accept multiple indexes as inputs.
    * Justification: This allows users to mark multiple tasks as complete in a single command input, making managing one's data more efficient.
    * Highlights: This enhancement required being well versed in the `Model` and `Logic `components of tApp, how they are implemented and how they interact. The implementation was difficult in that multiple design alternatives had to be explored, and that it required changing many classes, in addition to their methods, and test cases.


* **Enhancement**: Add a Command Summary table to the Help Window pop up (Pull Request: [\#121](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/121))
  * What it does: Displays a summary table of all commands in tApp, and their usage.
  * Justification: This feature gives an easier way for the user to refer to the documentation of the commands, that is built into the application.
  * Highlights: This enhancement required being well-versed in JavaFX frameworks.


* **New Feature**: Added UI for Attendance and Participation (Pull Request: [\#138](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/138))
    * What it does: highlights the weeks of attendance or participation marked for each student in the tApp.
    * Justification: This feature improves the product significantly as it represents the attendance and participation data of students in a way that is easy and intuitive to understand, as well as visually pleasing.
    * Highlights: This feature required a thorough understanding of JavaFX. Its difficulty in implementation lies in the creation of multiple java and fxml files that can enable the app data to be presented in an aesthetically pleasing way.
    * Credits: Inspiration taken from the [CS2103/T Participation Dashboards](https://nus-cs2103-ay2122s1.github.io/dashboards/contents/participation.html).


* **Enhancement**: Further improvements on GUI (Pull Request: [\#138](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/138))
    * This includes: 
        * Adding a text that states the type of list being shown.
        * Implementing the `Priority` field tag, and the task type tag, of `Task`s in the task list
        * Implementing the `Group` tag label of the `Student`s in the student list
        * Editing the overall GUI colour scheme
        * Ensuring displayed UI visuals are easily readable at all times, for all window sizes.
    * Justification: This feature improves the product significantly as it presents the data stored in tApp, in a way that is enhances the functionality of the app, and visually pleasing. This enhancement affects how different entity types are presented in the GUI, as well as respond to the changes in the data after the user executes commands. It also ensures that all data is presented well (i.e. no text overlap, no important data being cut off etc.) so that the functionality of the app is maintained regardless of the device used to view the application, or the window size.
    * Highlights: This required thorough knowledge on the implementation of AB3's UI, as well as being well-versed in Javafx libraries. The implementation was difficult as it required extensive testing for different scenarios to test the appearance of the UI.


* **Documentation**:
    * User Guide:
        * Added documentation for the features `editTask`, `editGroup` (Pull requests: [\#150](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/150/files))
        * Did cosmetic tweaks to existing documentation of features, and fixed bugs such as typos (Pull requests: [\#150](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/150/files))
    * Developer Guide:
        * Added implementation details of the `editGroup` feature (Pull requests: [\#242](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/242)).
        * Added diagrams and documentation for the `Model`, `UI` and `Task` components (Pull requests: [\#242](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/242)).


* **Team tasks**:
  * Updating v1.2 release demo to the team project document.
  * General code enhancements: Refactoring commands.
  * Updating user/developer docs: Adding NFR, Glossary, user stories, use cases, manual testing.
