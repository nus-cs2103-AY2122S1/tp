---
layout: page
title: Neo Jing Xuan's Project Portfolio Page
---

### Project: SmartNUS

SmartNUS is a revision tool for students. It allows students to create their own question bank and quiz themselves.
It is written in Java, and has about 17 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Short Answer Questions (SAQs) ([\#98](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/98))
    * What it does: Allows users to add SAQs and quiz themselves on SAQs.
    * Justification: Provides users with additional flexibility when creating and answering questions.
      Users do not have to come up with multiple choices or phrase their questions as True/False questions.
      During a quiz, users can input any answer when answering an SAQ, rather than choosing from fixed options.
      This feature also helps users prepare for examinations that include SAQs.
    * Highlights: Since users can input any answer, rather than selecting fixed choices, it is more difficult to
      determine if a user's answer is correct. The current implementation, checking for keywords and not a whole answer,
      was chosen by considering integration with the existing features and convenience and usefulness to the user.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=neojxuan&tabRepo=AY2122S1-CS2103T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Enhanced the Find Command to find by questions' tags and importance ([\#93](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/93))
    * Enhanced the Edit Command to edit a question's choices/answers ([\#85](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/85))
        * Implementation was more complex due to SmartNUS having three Question subclasses,
          with different parsing methods and conditions for answers

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit`, `find` and `saq` ([\#106](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/106))
    * Developer Guide:
        * Added description and diagram of `Question` class and implementation details of the `find` feature ([\#240](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/240))

* **Team-Based Tasks**:
    * Morphed AB3 into SmartNUS question bank, refactored AB3's Person into abstract Question class from which all new Question types extend
      ([\#8](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/8), [\#25](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/25),
      [\#28](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/28))
    * Managed releases `v1.1` and `v1.2` on GitHub
    * PRs reviewed (with non-trivial review comments):
      [\#36](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/36), [\#30](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/30)
    * Reported bugs and suggestions for other teams ([PED](https://github.com/neojxuan/ped/issues))
