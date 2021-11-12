---
layout: page
title: Quoc Huy's Project Portfolio Page
---

My name is Le Quoc Huy, and I am part of the development team for Tutor Master

### Project: Tutor Master

TutorMaster is a desktop app for freelance tutors to manage their students’ details and related tasks. It is optimised for use via a Command Line Interface (CLI) while still benefiting from a Graphical User Interface (GUI).

Given below are my contributions to the project.

- **Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jeremy-7007&tabRepo=AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

- **Enhancements implemented:**

  - Logic
    - Created commands for assigning and unassigning a task to a student [#78](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/78)
    - Created commands for marking a student's task as *done* and *not done* [#141](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/141)
    - Created command to delete tasks, removing task assignments in the process [#142](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/142)
  - Model
    - Added support for keeping a list of assigned tasks in each student [#78](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/78)
    - Added support for keeping track of whether a student has done a task assigned to him [#141](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/141)
  - Storage
    - Added storage method to keep track of assigned tasks in each student [#78](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/78)
    - Added storage method to keep track of whether a student has done a task assigned to him [#141](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/141)
  - UI
    - Showed in the student details whether tasks assigned to him has been done [#225](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/225)

- **Contributions to documentation:**

  - **User Guide**
    - Added documentation for *add student* command [#34](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/34) [#37](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/37)
    - Added documentation for *help* command [#34](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/34)
  - **Developer Guide**
    - Added use cases for deleting and assigning tasks [#38](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/38)
    - Detailed the implementation of task assignment [#99](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/99)
    - Updated the model diagram and description [#251](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/251)

- **Contributions to team-based tasks:**
  - Set up the GitHub team org and repo
  - Managed releases

- **Review/mentoring contributions:**
  - Discussed and commented on index bug fixing [#197](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/197)
  - Initiated discussion on using unique ids to link different entities like student, task and group
