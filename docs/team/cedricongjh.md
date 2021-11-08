---
layout: page
title: Cedric Ong's Project Portfolio Page
---

### Project: SmartNUS

SmartNUS is a revision tool for students. It allows students to create their own question bank and quiz themselves.
It is written in Java, and has about 17 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Multiple Choice Questions (MCQs) ([\#30](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/30))
    * What it does: Allows users to add MCQs and quiz themselves on MCQs.
    * Justification: The core feature of the application, enables users to add questions to test themselves via the Quiz functionality.
      
* **New Feature**: Added True False Questions (TFQs) ([\#63](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/63))
  * What it does: Allows users to add TFQs and quiz themselves on TFQs.
  * Justification: Gives the user more flexibility with regards to the types of questions that they can test themselves with.
    * Ensured that the code base is flexible and can deal with the different question types we might add in future.
    
* **New Feature**: Added ability to limit and filter questions for quiz ([\#89](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/89))
  * What it does: Allows users set the number of questions and filter the questions by tag.
  * Justification: Users might have too many questions to quiz themselves with or just want to revise particular topics that they are weaker in.
  * Highlights: Makes use of tags that the user tagged the question with to filter them. When limiting the number of questions, the quiz also prioritises questions
  by the importance that the user gave the question, as well as the questions that they performed more badly in. 

* **New Feature**: Added searching for statistics ([\#113](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/113))
  * What it does: Allows users to search for how well they performed on particular questions. (based on tags)
  * Justification: Users would want to see the statistics for specific tags, to see how well they are performing for those tags, in order to gauge how well they are doing.
  * Highlights: The statistics are also ordered by worst to best. The lower the % of corrects, the higher it will be shown in the list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=F12&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=cedricongjh&tabRepo=AY2122S1-CS2103T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `tfq`, `stat` and limiting and filtering quiz questions ([\#251](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/251))
    * Developer Guide:
        * Updated class diagrams of `Logic`, `Model`, `Storage` and implementation details of the `add question` feature ([\#251](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/251))

* **Team-Based Tasks**:
    * Updated class diagrams of various classes that were changed due to morphing from AB3 to SmartNus ([\#251](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/251))
    * Managed release `v1.3` on GitHub
    * PRs reviewed (with non-trivial review comments):
      [\#51](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/51), [\#214](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/214)
    * Reported bugs and suggestions for other teams ([PED](https://github.com/cedricongjh/ped/issues))
