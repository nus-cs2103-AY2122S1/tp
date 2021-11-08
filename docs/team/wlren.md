---
layout: page
title: Ren Weilin's Project Portfolio Page
---
## Project: TaskMaster2103

TaskMaster2103 is an extension to [AddressBook - Level 3](https://se-education.org/addressbook-level3/), a CLI-based address book JavaFX application.
It adds extensive task-tracking functionalities to the base application, and has seamless GUI integration that complement the main CLI commands.

Given below are my contributions to the project.

- **New Feature**: Added time stamp validation to tasks
  - What it does: Add date semantics to the ts/ field for tasks, only taking in dd-MM-YYYY format input.
  - Justifications: This allows the internal `Timestamp` field for `Task` to be stored as utilizing `LocalDate` instead of a simple string message. And helps assures that users
  are only able to key in a proper date in the ts/ field to adhere to its purpose.
  - Highlights: This change allows for new features to be developed, such as highlighting tasks which are overdue (implemented), and filtering tasks by a certain date range or sorting by date (not implemented).
- **Enhancement to existing features**: Update `Storage` features to accommodate for task list
  - What it does: Create a separate storage path for task list related storage under `/data/tasklist.json`.
  - Justifications: A similar JSON convertible architecture was used from `addressbook.json` for `tasklist.json`, the conversion was more flexible as the tasks had many optional fields unlike the addressbook.
  - Highlights: Having two separate lines of storage for two different parts of the TaskMaster2103 helps compartmentalize storage and makes sure that the reading and writing of data after an operation is more efficient as only the part that experiences changes would need to invoke the storage process.
- **Testing**:
  - Updated Task testcases after Date semantics [#89](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/89)
  - Add Storage related testcases for tasks [#205](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/199)
- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=wlren&tabRepo=AY2122S1-CS2103-F09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
- **Documentation**:
  - Developer Guide:
    - Added User Stories, Project Scope.
    - Updated Storage related documentation.
  - User Guide:
    - Updated Command Summary.
    - Overall formatting and structure.
- **Project management**:
  - Managed issues and bug triaging.
  - Managed Github JAR releases and other project submissions.
- **Community**:
  - Set up team meetings and facilitate discussions.
  - Allocated work and act as team lead.
  - PRs reviewed: [#43](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/43), [#74](https://github.com/AY2122S1-CS2103-F09-2/tp/pull/74)

