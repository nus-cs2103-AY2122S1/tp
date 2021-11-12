---
layout: page
title: Manh Duc's Project Portfolio Page
---

### Project: Academy Directory

Academy Directory is a desktop application that aims to assist CS1101S tutors in the process of teaching Programming Methodology I in NUS.

Given below are my contributions to the project.

#### Major Contribution:

* **New Feature**: Add the ability to view general and specific help related to each command
  * What it does: Allows user to view a general or specific help page relating to each command.
  * Justification: To allow new users and existing users a shortcut way of viewing usage instruction for better insights into using Academy Directory.
  * Highlights: This features allow users to view a table (in Markdown) that summarizes command format and usage for quick referencing, or view general help of each command.
  * Credits: I have used MDFX, a library that parses and renders Markdown input to JavaFX GUI.

* **New Feature**: Add the ability to view details of each student
  * What it does: This features allow users to see all related details to one student - contact information, grades, participation, tags, and others...
  * Justification: This feature is necessary to reorganize UI into a more compact view, while allowing Avengers to see all academic details of a student in one place.
  * Highlights: View command wrap student grades and participation in a drop-down menu that Avengers can click on to see student data

* **New Feature**: Graphics User Interface
  * What it does: Refactor and implement most of the Academy Directory GUI, with central theme of science fiction and space.
  * Justification: Refactoring Academy Directory is necessary to restructure how data are shown to users, how command results are displayed either as a log message or as a result shown. In addition, it separates Academy Directory from Address Book 3 completely.
  * Highlights: The major points of GUI refactoring includes:
    * Changing the background color and logo of Academy Directory. Restructure front-end and style for better usage
    * Changing how the student list will look like, how View Command will be shown, and how Help Window should look.
    * Implemented a new layout called Creator that visualizes data separately from the result display logger, using information from VersionedModel.
    * Changing how message usage, error message, and help message appear on the status message display, and what the content should be.
  * Credits: I have used Source Academy's image, Slides Carnival image, PNGTree icons, and IconX8 icons in the process of designing the new front-end for Academy Directory. All images and files used have been released for commercial reuse and distribution with license, and have been properly acknowledged on our User Guide and Developer Guide.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T15&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=hmanhduc2k&tabRepo=AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Ensure that singular commands like `list` or `clear` do not have any arguments to follow.
  * Assist with refactoring some important classes like VersionedModel, CommandResult, or AdditionalViewModel to match with the UI component.

* **Team-based tasks**:
  * Created CS2103T-G15-T3 team organization on behalf of the team.
  * Manage [releases `v1.3`](https://github.com/AY2122S1-CS2103T-T15-3/tp/releases/tag/v1.3.complete) to GitHub on behalf of the team.
  * Help with maintaining issue trackers for v1.2 to v1.4 task board and write meeting notes.
  * Create and added application logo and icon for the team.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `help` ([\#39](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/39/files)), and `view` ([commit](https://github.com/AY2122S1-CS2103T-T15-3/tp/commit/0654cbc9ed719916b3f6c3ee14cd7e1aec5bb177#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)),...
    * Added the Preview Section of UI and write some Q&A parts. [\#240](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/240/files), [commit](https://github.com/AY2122S1-CS2103T-T15-3/tp/commit/6c1685e68788e39d3d28867f6ee44894c13406fd#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb),...
    * Assist with writing introduction and quick start for usage instruction. [\#39](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/39/files)
  * Developer Guide: [\#39](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/39), [#98](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/98/files), [#229](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/229), [#236](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/236/files), [#237](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/237) [#240](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/240/files), [#266](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/266/files)... 
    * Added implementation details, design considerations, use case, and user stories, and manual testings for `view`, `help`, `clear`, `exit`, and `list`
    * Added class diagram and explain the architectural implementation of the UI component
    * Added manual testing instructions to Graphics User Interface of Academy Directory<br>
  * Manage the README.md file ([history](https://github.com/AY2122S1-CS2103T-T15-3/tp/commits/master/README.md)) proofread and adding minor contents of other existing parts on the User Guide and Developer Guide.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#49](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/49), [#79](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/79),...
  * Contributed to forum discussions: Helping others [\#354](https://github.com/nus-cs2103-AY2122S1/forum/issues/354), and asking questions [\#339](https://github.com/nus-cs2103-AY2122S1/forum/issues/339), [\#299](https://github.com/nus-cs2103-AY2122S1/forum/issues/299)
  * Report bugs to other teams in the PED and assist other teams with UI implementations (mostly through personal chat)
