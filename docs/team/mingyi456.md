---
layout: page
title: Chen Mingyi's Project Portfolio Page

---

## Project: TaskMaster2103

TaskMaster2103 is an extension to [AddressBook - Level 3](https://se-education.org/addressbook-level3/), a CLI-based address book JavaFX application.
It adds task-tracking functionalities to the base application, while boasting intuitive GUI features that complement the main CLI workflow.

Given below are my contributions to the project.

- **New Feature:** Added the ability to delete tasks by index ([`#44`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/44)).
  - What it does: Allows the user to delete a task specified by its index
  - Justification: This feature allows the user to remove tasks that are no longer needed to be kept track of by TaskMaster2103, or remove tasks that were entered erroneously entered, reducing the amount of clutter.
- **New Feature:** Added the ability to search for tasks by keywords ([`#76`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/76)).
  - What it does: Allows the user to search for tasks which match any of the specified keywords. 
  - Justification: This features allows the user to narrow down the list of tasks displayed.
  - Highlights: To integrate this feature with the existing filtering feature, a special filter that corresponds to keyword searches needs to be created. There can only exist one such keyword filter in the `Model`, so any existing keyword filter needs to be removed before a new one is added. Also, to support clearing the current keyword filter when no keyword arguments are specified, a special `Predicate` which indicates not to add any new keyword filter had to be made.
- **Code contributed**: [Reposense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
- **Enhancement to existing features**
  - Addressed a bug with the GUI that could cause the checkbox denoting task completion status to obscure the task title ([`#197`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/197)).
- **Testing**
  - Add test cases for `task delete`, `task find`, and helper function `limitString()` ([`#81`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/81), [`#76`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/76), [`#137`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/137)).
- **Documentation**
  - Added use cases for the inherited AB3 features ([`#36`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/36)).
  - Added use cases for `task done`, `task list`, `task find`, and `undo` ([`#93`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/93)).
  - Added `task delete` and `task find` implementation to the DG ([`#80`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/80), [`#93`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/93)). 
  - Added `task find` command usage to the UG ([`#101`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/101)).
- **Tools**
  - Added a utility function that truncates a target string if it exceeds a specified length, replacing the last few characters with a specified replacement string ([`#76`](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/76)).
