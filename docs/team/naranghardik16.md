---
layout: page
title: Hardik's Project Portfolio Page
---

### Project: Uni-Fy

Uni-Fy is a desktop app for managing your university workload and it is designed by university students for university students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=narang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-1)

* **Tag Command**: Added the ability to add multiple tags to task. 
  * What it does: Adds tags to tasks 
  * Justification: This feature improves the app significantly as it allows the user to tag tasks such as assignments, quizzes, meetings, presentations, etc.
  * Highlights: It required in depth understanding of how the optional parameters work. It was also challenging as I had to change the implementation from having one tag for each task to multiple tags for each task.
  * Contribution: Created the `TagCommand` and `TagCommandParser` classes. 

* **Undone Command**: Added the ability to mark a task as TODO.
  * What it does: allows the user to mark a task as TODO. Also updates the progress bar to show number of task that are done.
  * Justification: This feature improves the app significantly as the edit command does not allow the user to change the state of the task from done to ToDo.
  * Contribution: Added the `UndoneCommand` class. 

* **Sort Command**: Added the ability to sort the task list. 
  * What it does: Sorts the task list based on sort type - `time` or `priority` and sort order - `asc`(ascending) or `desc`(descending) order. 
  * Justification: This feature improves the app significantly as the user is able to sort the tasks based on time which allows the user to focus on the tasks due first. The user is also able to sort tasks based on priority which allows the user to prioritize the tasks based on their importance.
  * Highlights: It required in depth understanding of the lambda function, functional interface BiFunction, `model` and `logic` components. The implementation was a bit challenging as it was not done the conventional way using the LocalDateTime date-time object. 
  * Contribution: Created the `SortCommand` and `SortCommandParser` classes. 

* **Command History**: Added the ability to access previous commands.
  * What it does: Allows the user to access the previous command history for a particular session with the help of `/prev`  or `up arrow key` command and `/next` or `down arrow key` command.
  * Justification: This feature improves the app significantly as the user can easily access the previous commands without having to retype the whole command again.  
  * Highlights: It required in depth understanding of the key listeners in the `ui` component and `commands` component. 
  * Contribution: Created `CommandHistory` class and updated the `MainWindow` class in the `ui` component. 
  
* **Project management**:
  * Managed release `v1.3c` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Incorporated the existing priority feature with the edit command.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `tag`, `edit` ,`sort`, `/prev` and `/next`. [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `sort` feature.
    * Added a fair share of user stories.
    * Added some use cases.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#343](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/343), [\#344](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/344), [\#209](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/209), [\#208](https://github.com/AY2122S1-CS2103T-W17-4/tp/pull/208)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-W08-2/tp/issues/198), [2](https://github.com/AY2122S1-CS2103T-W08-2/tp/issues/208), [3](https://github.com/AY2122S1-CS2103T-W08-2/tp/issues/210))
  * Contributed to team discussions: [1](https://docs.google.com/document/d/1TmplkyQY3q2S0ZLLVPJjo6Isc8zxYnXRj32cDAxKhuk/edit)

  
