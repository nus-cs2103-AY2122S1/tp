---
layout: page
title: Wong Zhi Chester's Project Portfolio Page
---

------------------------------------------------------------------------------------------------------------------------

## Project: TuitionAddressBook (TAB)

### Overview

The Tuition Address Book (TAB) is an all-in-one desktop application for 1-to-1 private home tutors that manages student and lesson information.
TAB empowers tutors to provide the best quality home tuition service by making admin tasks a breeze through its powerful management capabilities, organisation tools, smart automation, and great ergonomics.

### Summary of Contributions

#### Code contributed

* ~3.5kLoC 
* ~60 hours 
* [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=chester&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabAuthor=Chesterwongz&tabRepo=AY2122S1-CS2103T-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)

#### Enhancements implemented

* **New Feature**: Calendar interface ([/#126](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/126), [/#181](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/181))
  * What it does: 
  * Justification:
  * Highlights: 
  * Credits: [CalendarFX](https://github.com/dlsc-software-consulting-gmbh/CalendarFX) was the library used to display the calendar

* **New Feature**: UI switching panels 
  * What it does:
  * Justification:
  * Highlights:
  * Credits: Inspired by [this](https://stackoverflow.com/questions/16176701/switch-between-panes-in-javafx#:~:text=Replace%20just%20a%20specific%20pane,of%20the%20stack's%20child%20list.) answer on StackOverflow.
  
* **New Feature**: Clashing lessons detection
  * What it does:
  * Justification:
  * Highlights:

* **New Feature**: Parent contact fields ([/#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/67))
  * What it does:
  * Justification:
  * Highlights:
  * Credits:

* **New Feature**: Student remarks ([/#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/19))
  * What it does:
  * Justification:
  * Highlights:
  * Credits:

* **New Feature**: Parsing money fields ([/#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/67))
  * What it does: Parses money inputs.
  * Justification: Tutors might want to store lesson rates and outstanding fees of their students.
  * Highlights: Intelligent parsing of money fields can be difficult due to integer limits, floating point errors, etc

* **New Feature**: Interactive GUI ([/#190](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/190), [/#202](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/202))
  * What it does: Selecting a student in the list displays their lessons in the lesson panel.
  * Justification: Without this feature, users would have to type the `view` command view each individual student,
    without any good way to browse through their student's lessons. 
    This feature allows them to 
  * Highlights: Users can view a student's lessons by clicking on a student, or by focusing on the student panel using 
    the <kbd>Tab</kbd> key, and then scrolling through the students with the <kbd>&#8593</kbd> and <kbd>&#8595</kbd> keys.
    This allows extremely quick and easy browsing through the list of students to view lessons.

* **New Feature**: Keyboard shortcuts
  * What it does: Allows users to speed up certain common functions with just a press of the shortcut
  * Justification: Greatly increases speed of the CLI app.
  * Highlights: Users can use <kbd>F1</kbd> <kbd>F2</kbd> <kbd>F3</kbd> <kbd>F4</kbd> and <kbd>F5</kbd> function keys 
    to navigate within TAB, eliminating the need for navigation commands for expert users.
  * Credits:
  
* **Enhancements to existing features**: Finding students by their lessons ([/#210](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/210))
  * 


#### Contributions to the User Guide



#### Contributions to the Developer Guide



#### Contributions to team-based tasks

* Set up the Team Repo
* Set up and maintained Issue Tracker
* Set up Milestones
* Updated Gradle ([/#217](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/217/files), ) and Gemfile [/#4](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/4/files)
* Managed two releases (v1.3 & v1.4)
* Incorporated CalendarFX library into the product

#### Community

* PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
* Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
* Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())

#### Tools

* Integrated a third party library (CalendarFX) to the project ([\#42]())
