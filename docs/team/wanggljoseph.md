---
layout: page
title: Wang Guanlin (Joseph)'s Project Portfolio Page
---

### Project: Teaching Assistant's Buddy (TAB)

TAB is a desktop address book application that can help Teaching Assistants (TAs) manage their students' progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 24 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the Module model. (Pull request [\#66](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/66))
    * What it does: Module models an academic module offered in the National University of Singapore.
    * Justification: Since TAB is aimed at helping TAs manage their students' progress, we implemented the 
      Module model to contain the necessary information such as students and tasks, similar to a real academic Module. 
      As TAs may be tutoring multiple modules, there is a need to distinguish between different modules, which is done
      using the ModuleName that is unique to each module.

* **New Feature**: Added the ability to edit Tasks of Students and Modules. (Pull request [\#117](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/117))
    * What it does: This feature allows the user to edit a specific task's information.
    * Justification: Since the specific details (such as its deadline) of a module's task may change in the real world, 
      this feature will allow TAs to update or correct inaccurate details about the specific task. 
    * Highlights: This feature will edit the task's information for all students under the module, sparing the user from
      having to edit the task's information separately for every student of the module.

* **New Feature**: Added the UI for Students and Tasks. (Pull request [\#76](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/76)) 
    * What it does: Display the graphical representation of Students and Tasks belonging to a Module.
    * Justification: Since TAB is aimed at helping TAs manage their students' progress, being able to view the information
      of a Module's Students and Tasks in an orderly fashion can greatly improve the TA's experience of tracking and updating
      their students' progress.

<div style="page-break-after: always;"></div>

* **Enhancements to existing features**:
    * Styled UI to display done and undone Tasks using intuitive colors. (Pull request [\#137](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/137))
      * What it does: Display a done Task with a green border and description, and display an undone Task with a red border and description
      * Justification: This feature enhancement makes it easier for the user to quickly determine whether a Task is done or
        undone, reducing the time required to track and update students' progress.
    * Standardized the format of Command Result returned upon executing a Command. (Pull request [\#220](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/220))
      * What it does: Reduce the amount of horizontal scrolling a user needs to perform to check out the Command Result message.
      * Justification: This feature enhancement standardized the format of the Command Result received when the user executes a Command.
        The new format is easier to read and makes it easier for new users to learn more about TAB's commands.

* **Testing**:
    * Wrote test cases for the `edit task` feature that increased test coverage by 5.71%. (Pull request [\#144](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/144))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true&tabType=authorship&tabAuthor=WangGLJoseph&tabRepo=AY2122S1-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&checkedFileTypes=docs~functional-code~test-code~other)

* **Team-based contributions**:
    * Documented the target user profile in the User Guide (Pull request [\#214](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/214))

* **Project management**:
    * Managed releases `v1.3-trial` - `v1.4.0` (3 releases) on GitHub ([Github releases](https://github.com/AY2122S1-CS2103-F09-1/tp/releases))

* **Documentation**:
    * README:
      * Updated documentation to reflect the features available as of iteration v1.3.0. (Pull request [\#214](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/214))
    * User Guide:
      * Added documentation for the `edit task` feature. (Pull request [\#118](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/118))
      * Added screenshots for every command except for the `exit` command, which exits the application and therefore does not have a screenshot.
        (Pull request [\#221](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/221))
      * Updated documentation and improved overall readability in fixing all Must-fix documentation bugs. (Pull request [\#214](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/214))
    * Developer Guide:
      * Added implementation details of the `edit task` feature. (Pull request [\#121](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/121))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#129](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/129), [\#130](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/130), [\#146](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/146), [\#212](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/212)
    * Reported bugs and made suggestions for other teams in the class. (examples: [1](https://github.com/WangGLJoseph/ped/issues/1),
      [2](https://github.com/WangGLJoseph/ped/issues/2), [3](https://github.com/WangGLJoseph/ped/issues/3))
