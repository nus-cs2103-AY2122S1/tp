---
layout: page
title: Lim Wei Chang's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA<sup>2</sup> is a desktop productivity application developed for teaching assistants to better manage student contacts and keep track of student's assignments.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to display assignments on the assignment list panel.
  * What it does: Allows the user to display the assignments of any student that wish to look at.
  * Justification: This feature is the main feature to allow our users to enable the assignment list panel to provide a visual aid with keeping track of assignments.
  * Highlights: This feature was challenging implementing as it is the only feature to enable the display of the assignments,
    and I had to ensure that the display refreshes when trying to display different students' assignments,
    and not show any unnecessary information.

* **New Feature**: Added the ability for the assignment list to auto sort by status of completion and due date.
  * What it does: Allows the assignments to be sorted in order whenever there is new command executed that will modify the assignment list.
  * Justification: This feature enables the user to be more efficient when tracking assignments as assignments with higher priority will always be found at the top of the list.
  * Highlights: This feature was challenging implementing as I have to analyse the current code and identify the best line to insert the sorting of the list such that it refreshes when
    any command modifying the assignments is executed, such that there is a seamless transition between the old and new list.

* **New Feature**: Added validation checks when inputting due dates when creating assignments.
  * What it does: Sends an error message whenever the user input an invalid date. Additionally, when the user did input a time, the defaulted time will be set to 11:59pm.
  * Justification: This ensures that the user do not input the wrong date for the assignments. This also increases efficiency,
    as most assignments that do not have strict submission time tends to have a cut off time at 11:59pm of the day of submission.
  * Highlights: This feature was challenging implementing as I have to create the most efficient validation checks for all possible dates, including leap year.
  * Credits: The validation check is modified from an existing one that can be found [here](https://stackoverflow.com/questions/4374185/regular-expression-match-to-test-for-a-valid-year)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=weichang18&tabRepo=AY2122S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Ensuring that team members individual parts are integrated well together.

* **Enhancements to existing features**:
  * Updated the validation checks for email to check for maximum length of the entire email and its individual portions.[\#217](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/217)
  * Updated the format of Student's name to ensure that the first letter of every part of name is capitalised.[\#96](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/96)

* **Documentation**:
  * User Guide:
    * Improved on explaining and included possible usage of every feature in user guide. [\#230](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/230)
    * Updated the command summary of user guide. [\#230](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/230)
    * Added a glossary page to include formatting of email and examples of friendly commands. [\#247](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/247)
  * Developer Guide:
    * Added implementation details of the display assignment feature. [\#114](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/114) <br>
      Updated implementation details of display assignment feature. [\#247](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/247)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#62](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/62), [\#98](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/98), [\#102](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/102)
  
