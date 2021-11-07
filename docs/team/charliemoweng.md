---
layout: page
title: Wang Mengzhe's Project Portfolio Page
---

### Project: Teaching Assistant Buddy (TAB)

TAB is a desktop address book application that can help Teaching Assistants (TAs) manage their students'
progress.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added in Task model. (Pull request [#61](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/61))
    * What it does: It models a task (assignment, quiz, project etc) under a module assigned to a student. It has 
      attributes such as name, which module it is under, and deadline.
    * Justification: Since my team is creating a product to cater to the TAs, models in our project should reflect 
      what TAs deal with in real life. The Task class represents homeworks that a student will have, be it 
      assignments or papers. It is a necessary part of the TAB.


* **New Feature**: Added in the feature to add a task to a module. (Pull request [#74](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/74))
    * What it does: It adds a task to a module and all the students under this module.
    * Justification: As TAs, users will need to populate the modules they have with tasks and assign these tasks to 
      relevant students under them. This task does exactly that.
    * Highlights: This command will automatically add tasks to all students under the module, relieving the pain of 
      assigning all tasks to each individual student from the users.


* **New Feature**: Added in the feature to mark a task under a student as done. (Pull request [#129](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/129))
    * What it does: When users provide the module, the student, and the task, this command will mark the specific 
      task under that module and student as done, which means that the student has completed this task.
    * Justification: TAB aims to make TAs' life easier (thus the name) and by enabling them to mark a task under a 
      student as done, TAB makes it very straightforward for TAs to keep track of the progress of each student under 
      them. It takes just a look and some scrolling to know who has done the task and who has not.


* **New Feature**: Added in the feature to mark a task under a student as undone (not done yet). (Pull request [#129](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/129))
    * What it does: It does just the opposite of what the previous feature does - it marks a task under a student as 
      undone, which means that the student has not completed the task yet.
    * Justification: We all make mistakes. Students do, TA do, sometimes even professors do. This feature provides 
      a way to right the wrong. If a task has been accidentally marked as done but in fact the student has not done 
      it yet, this feature allows TAs to revert it back to default (tasks are set as undone by default).


* **Enhancements to existing features**:
    * Added in checks for duplicate Telegram handles and emails when adding or editing a student. So that no two 
      students under the same module can have the same Telegram handle or email address. (Pull request [#227](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/227))
    * Fixed the bug that will cause an exception to be thrown when a student is added to a module. (Pull request [#111](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/111))
    * Wrote tests for Module, Task, and the feature to mark a task as done or undone to increase coverage.
      (Pull requests [#150](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/150), [#219](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/219), [#224](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/224))
    * Modified and add on some tests written by others to reflect updates in logic. It was not easy to try to better 
      the test cases written by others, especially when you are not the one who wrote the logic. I traced the code multiple 
      times to make sure the test cases that I modified or added will work and improve coverage.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true&tabType=authorship&tabAuthor=charliemoweng&tabRepo=AY2122S1-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&checkedFileTypes=docs~functional-code~test-code~other)


* **Documentation**:
    * README:
        * Updated documentation to reflects the features we had for iteration v1.2. (Pull request [#20](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/20)) 
    * User Guide:
        * Added documentation for the feature to add a task to a module. (Pull request [#21](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/21))
        * Added in features to Command Summary. (Pull request [#147](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/147))
    * Developer Guide:
        * Added documentation for the feature to add a task to a module. (Pull request [#115](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/115))


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#72](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/72), 
      [\#76](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/76), [\#98](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/98), 
      [\#107](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/107), [\#117](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/117), 
      [\#118](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/118), [\#122](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/122),
      [\#130](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/130), [\#144](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/144),
      [\#153](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/153), [\#211](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/211),
      [\#214](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/214), [\#225](https://github.com/AY2122S1-CS2103-F09-1/tp/pull/225).
    * Contributed to forum discussions (examples: [355](https://github.com/nus-cs2103-AY2122S1/forum/issues/355), 
      [256](https://github.com/nus-cs2103-AY2122S1/forum/issues/256#issuecomment-922282844), 
      [328](https://github.com/nus-cs2103-AY2122S1/forum/issues/328), [329](https://github.com/nus-cs2103-AY2122S1/forum/issues/329),
      [338](https://github.com/nus-cs2103-AY2122S1/forum/issues/338#issuecomment-953733592), [354](https://github.com/nus-cs2103-AY2122S1/forum/issues/354#issuecomment-954456108),
      [365](https://github.com/nus-cs2103-AY2122S1/forum/issues/365#issuecomment-957590632))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/charliemoweng/ped/issues/1), 
      [6](https://github.com/charliemoweng/ped/issues/6), [7](https://github.com/charliemoweng/ped/issues/7),
      [12](https://github.com/charliemoweng/ped/issues/12), [13](https://github.com/charliemoweng/ped/issues/13),
      [15](https://github.com/charliemoweng/ped/issues/15), [19](https://github.com/charliemoweng/ped/issues/19))
