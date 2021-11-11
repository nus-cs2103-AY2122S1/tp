---
layout: page
title: Yeh Yu Cheng's Project Portfolio Page
---

### Project: Tutor Master

**Tutor Master** is a desktop application for experienced freelance tutors to keep track of their students. More specifically, it helps tutors to record student details and the tasks, lessons and exams assigned to them. Besides, tutors can view their teaching schedule in Tutor Master. The app is mainly written in Java and has about 12k LoC.

Users can interact with Tutor Master using a CLI (command-line interface), while also benefit from the GUI we provided.

Contributed code: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=eltonyeh&tabRepo=AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements

- **Internal Design**: Create the `UniqueId` class
  - What it does: this allows we to uniquely identify entities including students, groups, tasks, lessons and exams by generating unique id for each object.
  - Justification: the idea of "unique" id is not that trivial; we need to ensure that there is nearly no possibility to have duplicate ids while trying to make the system easy to implement. [Detail explanation](https://ay2122s1-cs2103t-w16-4.github.io/tp/DeveloperGuide.html#design-consideration)
- **Internal Design**: Create the interfaces `HasUniqueId` and `TaskAssignable`
  - What it does: `HasUniqueId` is used when a class has a unique id. `TaskAssignable` is used when a class can be assigned a task.
  - Justification: The main reason to implement these interfaces is to make the code structure more inheritable and decrease duplicate code. For instance, we can (un)assign tasks to `TaskAssignable` instead of `Student` and `Group` separately.
- **New Feature**: Ability to add tasks to the app
  - What it does: This allows users to create new tasks and store them in Tutor Master through a command. They can assign tasks to students/groups later. Task added is reusable, i.e., tasks can be assigned to multiple student/group.
- **New Feature**: Ability to assign tasks to `TaskAssignable` (Students and Groups)
  - What it does: this allows users to assign tasks to their students or student groups through a command.
  - Justification: My main contribution is
    - Combine the command of assigning tasks to students and assigning tasks to groups into a more generic command.
    - Reduce duplicate code and increase the code reusability by interfaces and inheritance.
  - Credit: this feature is based on the command of assigning tasks to students created by my teammate. 
- **New Feature**: Ability to unassign tasks from `TaskAssignable` (Students and Groups)
  - This allows users to unassign tasks from students/groups. It is the reverse action compared to the feature above.

#### Contributions to the user guide

- Wrote the guide of the commands about:
  - clearing all entries
  - viewing student detail
  - editing student detail
  - adding a task
  - assigning and unassigning a task
- Fix some command mismatches
- Draft the command summary

#### Contributions to the developer guide

- Add implementation detail of the `UniqueId` interface, including the related UML diagram.

#### Contributions to team-based tasks

- Update site-wide settings, i.e., change some `Addressbook` to `TutorMaster` in `README.md`, `_config.yml`, etc.
- Wrote the acknowledgement note

#### Community
  - Solved 16 issues assigned to myself. [Full list](https://github.com/AY2122S1-CS2103T-W16-4/tp/issues?q=is%3Aissue+author%3Aeltonyeh)
  - Created 23 pull requests for our team. [Full list](https://github.com/AY2122S1-CS2103T-W16-4/tp/pulls?q=is%3Apr+author%3Aeltonyeh)
    - Some notable PRs: [#62](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/62), [#81](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/81), [#129](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/129), [#213](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/213), [#237](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/237)
  - Reviewed 17 pull requests created by my teammates. [Full list](https://github.com/AY2122S1-CS2103T-W16-4/tp/pulls?q=is%3Apr+reviewed-by%3Aeltonyeh)
    - Some notable reviews: [#71](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/71), [#74](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/74)
  - Report 12 bugs/suggestions for Group W08-2 to help them enhance their product. [Full list](https://github.com/eltonyeh/ped/issues)
