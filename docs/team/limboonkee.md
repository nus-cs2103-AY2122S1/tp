---
layout: page
title: Lim Boon Kee's Project Portfolio Page
---

### Project: Socius

### Table of Contents
1. [Introduction](#1-introduction)
  1. [About the team](#11-about-the-team)
  2. [About the project](#12-about-the-project)
2. [Summary of contributions](#2-summary-of-contributions)
  1. [New features and enhancements added](#21-new-features-and-enhancements-added)
    1. [Add remark to a person](#211-add-remark-to-a-person-feature)
    2. [Delete multiple persons](#212-delete-multiple-persons-feature)
    3. [Sort persons](#213-sort-persons-feature)
    4. [Add nationality of a person](#214-add-nationality-of-a-person-enhancement)
    5. [Command history](#215-command-history-enhancement)
  2. [Code contributed](#22-code-contributed)
  3. [Other contributions](#23-other-contributions)
    1. [Project management](#231-project-management)
    2. [Enhancements to existing features](#232-enhancements-to-existing-features)
    3. [Documentation](#233-documentation)
      1. [About Us](#2331-about-us)
      2. [User Guide](#2332-user-guide)
      3. [Developer Guide](#2333-developer-guide)
    4. [Community](#234-community)

### 1. Introduction

This is my project portfolio for Socius. The document outlines my contributions to the project, including the features and enhancements that I have implemented.

#### 1.1 About the team

Our team consists of Hsiao Ting, Choon Yong, Kevin, Boon Kee and Nathan. We are a group of Computer Science students from the National University of Singapore, and members of AY2022S1-CS2103T-W08-4 in the CS2103T Software Engineering module.

#### 1.2 About the project

Socius is a contact management desktop application used for CS2103T module takers. Socius is a platform to help students
find potential project group mates for CS2103T. The user interacts with it using a CLI, and it has a GUI created with
JavaFX. Socius is written in Java, and has about 12 kLoC.

A screenshot of Socius is shown below:
![Ui](../images/Quickstart.png)

### 2. Summary of contributions

I served as developer for the project, and my responsibilities include:
1. Developing features for the program
2. 
3. 

My team responsibilities include:
1. Creating minutes for team meeting

In the following sections, I will illustrate the above-mentioned enhancements in greater detail, along with the corresponding documentation that I have written for them within the user and developer guides.

#### 2.1 New features and enhancements added

The following describes the enhancements and new features that I added to the project.

#### 2.1.1 Add gender field to a person (Feature)
* What it does: allows the user to view the gender of a person
* Justification: The product is catered towards CS2103T students for finding group members and having a good mix of gender is encouraged in the course. Having a good mix of gender could also enhance group dynamics. Therefore, gender is an important piece of information for students.
* Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation was challenging as it required changes to the core Person model which lead to many changes to other classes that relied on the Person model, such as commands and GUI. Test cases and test data also have to be updated accordingly.

#### 2.1.2 Add social handles field to a person (Feature)
* What it does: allows the user to view the social handles (e.g. Telegram handle) of a person
* Justification: This allows user to contact and know more about a person via different social platforms. For example, if a user wants to know about a person's coding style, he/she may go to the person's GitHub portfolio. If a user wants to know about a person's lifestyle, he/she could visit the person's Facebook or Instagram page.
* Highlights: This enhancement was done in two iterations, where the first iteration only supports a single social handle and second iteration added support for more social handles. Editing social handle is cumulative, so that users will not have to retype all the social handles everytime they are edited.

#### 2.1.3 Add icons for both gender and social handles (Feature)
* What it does: displays the gender and social handle platform as icons instead of text
* Justification: This feature improves the user experience of the product as user can see the gender and social handle platform more easily.
* Highlights: This enhancement required knowledge how the GUI works. 

#### 2.2 Code contributed

Click on the following links to view the code that I have contributed:

* [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=bklimey&tabRepo=AY2122S1-CS2103T-W08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### 2.3. Other contributions

The following describes the various other contributions that I have made to the project.

#### 2.3.1 Project management
* Help to compile and publish releases `v1.3` - `v1.4` (5 releases) on GitHub.
* Help to create demo screenshots/videos of features for `Socius v1.3` and `Socius v1.4`

#### 2.3.2 Enhancements to existing features

#### 2.3.3 Documentation

##### 2.3.3.1 About Us
* Draft up the documentations for AboutUs, including roles and responsibilities of each member.

##### 2.3.3.2 User Guide (UG)
* Drafted introduction message
* Added symbol table for showing meaning of each symbol
* Added Parameter Constraints section
* Formatted Command Summary

##### 2.3.3.3 Developer Guide (DG)
* Added implementation details and UML diagrams for the `Support for more social handle` section.
* Formatted User Stories

#### 2.3.4 Community


<!---
AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
--->