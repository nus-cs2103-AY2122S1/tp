---
layout: page
title: Arya Nagatama Giat's Project Portfolio Page
---

### Project: SmartNUS

SmartNUS is a revision tool for students. It allows students to create their own question bank and quiz themselves.

Here are the things I have done in this project:

* **New Feature**: Added the Quiz Class and Quiz Command ([@ryanpeh](https://github.com/ryanpeh) implemented the quiz functionality) ([\#33](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/33))
    * What it does: Allows user to quiz themselves based on the questions they added.
    * Justification: The main functionality of the app is to revise through quizes. This feature allows users to test themselves based on the questions they added.
    * Highlights: This feature opens up many opportunities on other quiz features such as testing questions by their tags or selecting a number of questions to be tested on.

* **New Feature**: Added statistics to questions and tags ([\#51](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/51), [\#96](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/96))
    * What it does: Keep track of the number of times the user attempted the question and the number of times the user answered correctly.
    * Justification: This feature allows questions to be sorted based on the performance (the correct percentage). Therefore, the user's review sessions can be more effective as they can see which topics they are struggling on.
    * Highlights: Instead of storing the statistics as primitive data type in each questions, an abstracted type was used which lead to the statistics being able to be used by other things such as quizes and tags.

* **New Feature**: Added theme options ([\#59](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/61))
    * What it does: Allows users to change the theme from a dark theme to a light theme and vice versa. The chosen theme will be saved in the user preference too.
    * Justification: Some users prefer to review their topics on a darker theme while some prefer to review on a lighter theme. According to [research](https://sites.psu.edu/siowfa15/2015/10/02/the-best-place-to-study/), switching things up will help boost your focus, thus, make studying sessions more effective. Changing the theme is one way to switch things up.
    * Highlights: This implementation lead to some changes on how the UI communicates with the model. Previously, the model would tell the UI to perform certain updates. With this feature, the UI fetches information from the model and updates accordingly. This way, it will make it easier for future themes or UI update commands to be implemented.
    * Credits: The light theme was inspired by [LumiNUS](https://luminus.nus.edu.sg/)

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=aryagiat&tabRepo=AY2122S1-CS2103T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to Existing Features**:
    * Enhanced the `list` command to list things by type such as by `question`, `note`, or `tag` ([\#94](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/94))
    * Enhanced the user preference by saving the latest GUI user preference before exiting the app. ([\#66](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/66))

* **Documentation**:
    * User Guide:
        * Refactored the user guide from the previous address book app version to the review app version. This includes updating the `mcq`, `edit`, `delete`
        * Added documentation for the features `list`, `theme`, and get started section ([\#46](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/46), [\#1](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/1))

    * Developer Guide:
        * Updated the old links to an updated one ([\#70](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/70))
        * Created class diagrams for `note`, `theme`, `statistic`
        * Created sequence diagrams for `statistic`, and 'theme'
        * Updated the tutorial for `SmartNus`. Tutorial includes (`AddRemark` and `TracingCode`)
    
* **Team-Based Tasks**:
    * PRs reviewed and merged: [PRs](https://github.com/AY2122S1-CS2103T-F12-1/tp/pulls?q=is%3Apr+reviewed-by%3Aaryagiat)
    * Starting the weekly meetings
    * Reported bugs for other teams ([bugs reported](https://github.com/aryagiat/ped/issues))
