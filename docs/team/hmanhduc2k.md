---
layout: page
title: Hoang Manh Duc's Project Portfolio Page
---

### Project: Academy Directory

Academy Directory is a desktop application that aims to assist CS1101S tutors in the process of teaching Programming Methodology I in NUS, with features of recording student personal contacts, recording student participation and grades, visualizing and analyzing student data
from the grades recorded

Given below are my contributions to the project.

#### Major Contribution:

* **New Feature**: Add the ability to view general and specific help relating to each command
  * What it does: Allows user to view a general help page or a specific help page relating to each command.
  * Justification: This features allow users to see a quick table (in Markdown) that summarizes command format and usage for quick reference. In addition, viewing specific help for each command helps users to gain insight on each command better, for more efficient usage of Academy Directory
  * Highlights: This features require constant updating to match the progress of Academy Directory development - considering that we need to add a new specific help page each time a new command is created.
  * Credits: In process of implementing HelpCommand, I have used MDFX, a library that parses Markdown input and rendering it to the JavaFX GUI.

* **New Feature**: Add the ability to view details of each students
  * What it does: This features allow users to see all related details to one student - contact information, grades, participation, tags, and others...
  * Justification: This feature is necessary to reorganize UI into a more compact view, while allowing Avengers to see academic details of students better (by shortening focus to one student only)
  * Highlights: This features is a separate model-related command that also relies on the User Interface design.
  * Credits: None.

* **New Feature**: Graphics User Interface
  * What it does: Refactor and implement almost everything related to Graphic User Interface of Academy Directory. Theme of our GUI is `everything space related and science fiction-ey feel`. This includes:
    * Changing the style and functionality of Menu Bar to add some equivalent commands in forms of a clickable menu item
    * Changing the background color and image of Academy Directory
    * Refactor style, opacity, border color, size, stretch, and other appearance-related minor attributes of the various components in Academy Directory to improve user experience and prevent cases when shrinking or expanding GUI will hamper usage
    * Changing how list and student cards look like, as well as how the detailed view of View Command will look like
    * Reorganize the structure of Academy Directory front-end for better usage
    * Implemented (with assitance of Cuong) a new layout called Additional Pane that visualizes data separately from the result display logger initially appeared in AB3
    * Changing the visual of how commands are entered in Academy Directory
    * Changing how message usage, error message, and logger message appear on the result display logger, and what the content should be for better usage
    * Adding animation effects and color effects on components and transition of UI for better aesthetics
    * Changing application logo
  * Justification: Refactoring and implementing a new Graphical User Interface is necessary as Academy Directory is an application that relies on User Interface to demonstrates the result of each command. By making the results more visible and better suited to user experience, the purpose of assisting tutors are achieved henceforth. In addition, refactoring style and front-end is important to make Academy Directory a separate product from Address Book 3.
  * Highlights: This feature needs constant changes and improvement as GUI is needed to deliver a new command result and type.
  * Credits: In the process of implementing a new GUI, I have relied on StackOverflow to test GUI features and appearance. I have also used Source Academy's image (which are explicitly released for commercial use with proper acknowledgement, so it is allowed by the module guidelines), in the process of designing the new front-end for Academy Directory.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T15&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=hmanhduc2k&tabRepo=AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Created CS2103T G15-T3 organization on behalf of the team
  * Created and merge pull requests to the codebase throughout the process of the project
  * Releases `v1.3` to GitHub on behalf of the team.

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `help` and `view`
    * Assisted with adding introduction and instruction
    * Added one Q&A segment to the User Guide
    * Added anything GUI-related to the User Guide (how to use the menu or click on the student card)
    * Proofread other parts and review writing of my teammates
  * Developer Guide:
    * Added implementation details of the `view`, `help`, (proposed: `clear`, `exit`, `list`)
    * Added architectural implementation of UI

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  * Contributed to forum discussions
  * Reported bugs and suggestions for other teams in the class
  * Some parts of the history feature I added was adopted by several other class mates
  * Participated in all team meeting and discussion throughout the duration of the project.
