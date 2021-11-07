---
layout: page
title: Ruofan's Project Portfolio Page
---

### Project:TAB

TAB is a desktop address book application that can help Teaching Assistants (TAs) manage their students' progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 24 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Added the ability to delete modules: ([\#68](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/68))
    * What it does: allows the user to delete a specified module one at a time.
    * Justification: This feature helps the TA to clear modules that he/she no longer needs to keep track of, e.g. from a previous semester.
  * Added the ability for the UI to display a list of module that gets updated whenever a module-related command is entered. ([\#75](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/75))
    * What it does: display to users a list of modules based on users' commands. It gets updated smoothly and immediately with any module-related commands.
    * Justification: Users would like to see immediate visual changes/updates after interacting with the application by entering commands.

* **Enhancements to existing features**:
  * Styled UI to the color theme of NUS: ([\#128](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/128))
    * What it does: makes the UI more familiar, unique and appealing to users.
    * Justification: The Teaching Assistant Buddy is an application of the National University of Singapore, by the National University of Singapore and for the National University of Singapore, so why not give it NUS's color theme?
    * Credits: [The official NUS corporate colours](https://www.nus.edu.sg/identity/guidelines/corporate-colours) that build visual awareness and set our application apart from other applications, thus reinforcing its unique character.
  * Updated sample data that informs and guides first-time users on the functionalities of the application, for example, adding module, student, tasks with completion status and deadline.
   ([\#139](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/139))

* **Testing**:
  * Wrote test cases for `delete module` feature that increased test coverage by 11.70%. ([\#99](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/99))
  
* **Team-based contributions**:
  * Created object builders used for team-wide testing purposes. ([\#99](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/99))
  * Updated CI build status badge and Jekyll site-settings. ([\#23](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/23))
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Ruofan1023&tabRepo=AY2122S1-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).

* **Project management**:
  * Set up the [project website](https://ay2122s1-cs2103-f09-1.github.io/tp/).
  * Set up the GitHub [team organisation](https://github.com/AY2122S1-CS2103-F09-1) and [repository](https://github.com/AY2122S1-CS2103-F09-1/tp).
  * Set up [GitHub Actions](https://github.com/AY2122S1-CS2103-F09-1/tp/actions)
  * Set up team repository [Codecov](https://app.codecov.io/gh/AY2122S1-CS2103-F09-1/tp/) that enables:
    * Build automation.
    * Continuous integration, including code coverage and repository-wide checks.
    
* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete module`. ([\#70](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/70))
    * Did cosmetic tweaks to website layout: ([\#212](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/212))
  * Developer Guide:
    * Added implementation details of the `delete module` feature. ([\#120](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/120))
    * Drew UML diagrams for all module- and task-related implementations. ([\#212](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/212))
    * Added application-wide design details, including Architecture, UI, Logic, Model and Storage, each with detailed UML diagrams. ([\#212](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/212), [\#225](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/225))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#98](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/98), [\#102](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/102), [\#129](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/129), [\#207](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/207), [\#219](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/219)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/369), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/179), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/17))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Ruofan1023/ped/issues/2), [2](https://github.com/Ruofan1023/ped/issues/3), [3](https://github.com/Ruofan1023/ped/issues/4))

[comment]: <> (* **Tools**:)

[comment]: <> (  * Integrated a third party library &#40;Natty&#41; to the project &#40;[\#42]&#40;&#41;&#41;)

[comment]: <> (  * Integrated a new Github plugin &#40;CircleCI&#41; to the team repo)

[comment]: <> (* _{you can add/remove categories in the list above}_)
