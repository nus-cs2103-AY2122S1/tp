---
layout: page
title: Nigel Tan's Project Portfolio Page
---

### Project: NewAddressBook

NewAddressBook is a desktop address book application used by Secondary school teachers to manage their 
student and teacher contacts, optimized for use via a Command Line Interface (CLI). The user interacts with it using a CLI, and
it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the feature to record a student's medical history
  * What it does: Allows the user to record a student's medical history.
  * Justification: This feature improves the product significantly because a user might want to know the medical 
    history of the student as the user might want to make special provisions for that particular student.
  * Highlights: This enhancement  required an in-depth analysis of design alternatives. An alternative to allow this 
    field to be editable using the `editStudent` command was considered, but rejected as this field is only used in 
    rare circumstances, and should not add to the complexity of other commands in general.

* **New Feature**: Added the feature to display a student's medical history in a separate window
  * What it does: Allows the user to open a popup window displaying the student's full medical history in case the 
    medical history is too long.
  * Justification: This feature improves the product significantly as it keeps the UI clean when the medical history 
    of a student is long.
  * Highlights: This base code was then used for the `showMeeting` command to pop up the meeting window.

* **New Feature**: Added an `involvement` field to Students and Teachers
  * What it does: Allows the user to add an involvement field to students/teachers to keep track of how the user is 
    related to or involved with the student/teacher.
  * Justification: The user might want to note important details like "Class Monitor", and this feature allows the 
    user to do so.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an 
    in-depth analysis of design alternatives. The implementation too was challenging as it required changes to 
    existing commands like the `addStudent` command.

<div style="page-break-after: always;"></div>
* **New Feature**: Added `showMeeting` and `quitMeeting` functions
  * What it does: Allows the user to display the `meetingWindow` as a separate pop-up window, and allows the user to 
    quit the window to return to the `mainWindow`
  * Justification: The `meetingWindow` is separate from the `mainWindow` and should have its own quit command. This 
    also ensures that the app is CLI friendly, which is one of its main aims.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t16&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=nniiggeell&tabRepo=AY2122S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Enhancements implemented** 
  * Contributed to the refactoring of necessary classes in order to facilitate splitting the commands into separate 
    `student` and `teacher` related classes. (Pull request [\#53](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/53))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `medical`, `showMedical` and `showMeeting` (Pull requests 
      [\#55](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/55), [\#104](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/104/files), [\#136](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/136/files))
  * Developer Guide:
    * Added implementation details of the `medical` feature. [\#104](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/104), [\#105](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/105), [\#107](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/107)
    * Did cosmetic tweaks for the overall Developer Guide. [\#189](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/189)
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#75](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/75), 
    [\#39](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/39), [\#78](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/78)
  
