---
layout: page
title: Yeh Yu Cheng's Project Portfolio Page
---

## Project: Tutor Master

**Tutor Master** is a desktop application for experienced freelance tutors to keep track of their students. More specifically, it helps tutors to record student details and the tasks, lessons and exams assigned to them. Besides, tutors can view their teaching schedule in Tutor Master.

Users can interact with Tutor Master using a CLI (command-line interface), while also benefit from the GUI we provided.

The app is mainly written in Java and has about 12k LoC.

### Contributions

#### Contributed code

[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=eltonyeh&tabRepo=AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Documentation

- README
  - Update site-wide settings
  - Wrote the acknowledgement note
- User Guide
  - Wrote the guide of the commands about:
    - clearing all entries
    - viewing student detail
    - editing student detail
    - adding a task
    - assigning and unassigning a task
  - Draft the command summary
- Developer guide
  - Add implementation detail of the `UniqueId` interface
- General
  - Fix some typos

#### Internal Design: Create the `UniqueId` class

This allows we to uniquely identify entities including students, groups, tasks, lessons and exams.

#### Internal Design: Create the interfaces `HasUniqueId` and `TaskAssignable`

`HasUniqueId` is used when a class has a unique id. `TaskAssignable` is used when a class can be assigned a task.

These two interfaces makes the code structure more inheritable and decrease duplicate code.

#### New Feature: Ability to add tasks to the app

This allows users to create new tasks and store them in Tutor Master through a command. They can assign tasks to students/groups later.

Task added is reusable, i.e., tasks can be assigned to multiple student/group.

#### New Feature: Ability to assign tasks to `TaskAssignable` (Students and Groups)

This allows users to assign tasks to their students or student groups through a command.

This feature is based on the command of assigning tasks to students created by my teammate. My main contribution is:
- Combine the command of assigning tasks to students and assigning tasks to groups into a more generic command.
- Reduce duplicate code and increase the code reusability.

#### New Feature: Ability to unassign tasks from `TaskAssignable` (Students and Groups)

This allows users to unassign tasks from students/groups. It is the reverse action compared to the feature above.
