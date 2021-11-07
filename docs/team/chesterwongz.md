---
layout: page
title: Wong Zhi Chester's Project Portfolio Page
---

------------------------------------------------------------------------------------------------------------------------

## Project: Tuition Address Book

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
  * What it does: Allows tuition teachers to use a calendar to keep track of lessons and plan ahead.
  * Justification: This is a core feature for a tuition teacher. TAB cannot be an all-in-one app without it. 
    A huge part of a tutor's worry is scheduling his/her lessons. Do they have time for a new student? Which days are free and which days are too busy?
    Without a calendar interface, tutors must use another app, resulting in a decentralised workflow. TAB's value proposition would be limited.
    Having a calendar in a CLI application like ours is a game changer as fast typists no longer need to work with GUI bases calendars.
  * Highlights: The calendar interface comes with a day page, week page, month page, and year page, with easy navigation between them.
    Each CLI command has a button in the GUI for users who prefer clicking, and transitions have satisfying animations.
    Several shortcuts were introduced to allow users to work with the calendar and their students/lessons seamlessly.
  * Credits: [CalendarFX](https://github.com/dlsc-software-consulting-gmbh/CalendarFX) was the library used to display the calendar
  
* **New Feature**: Clashing lessons detection
  * What it does: Detects for clashing lessons and disallows them.
  * Justification: A private home tutor cannot be in two homes at once! This feature prevents important scheduling mistakes for our busy tutors. 
  * Highlights: Users cannot add or make edits to a lesson that would lead to schedule clashes.

* **New Feature**: Contact fields ([/#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/67))
  * What it does: Tracks a student's phone and email, and their parent's phone and email. Requires at least one contact detail to add an entry.
  * Justification: An address book entry without a contact is pointless. If you cannot contact your student how do you teach him/her?
    However, often for tutors, one form of contact is sufficient, so we took that into account as well.
    Tutors also often communicate with parents, so having a separate field for parent contacts is necessary.
  * Highlights: Contact fields are optional, but TAB smartly recognises when no contact details are provided.
    Entries **must** have **at least one** form of communication!

* **New Feature**: Student remarks ([/#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/19))
  * What it does: Support managing textual information about a student.
  * Justification: To a tutor, students are their customers. The remark field allows them to use TAB as a Customer Relationship Manager,
    and is important for TAB to be an all-in-one app.
  * Highlights: No word limits. Tutors can note down whatever they want!

* **Other Enhancements**:
  * Allow find command to find students by their lessons, to enable users to quickly find the student and lessons they need to edit ([/#210](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/210)).
  * Intelligent parsing of money fields can be difficult due to integer limits, floating point errors, etc.
    My regex checks figures them out ([/#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/67)) (inspired by [StackOverflow](https://stackoverflow.com/a/17867041)).
  * JavaFX and FXML for toggling between TAB's multiple interfaces ([/#126](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/126) (inspired by [StackOverflow](https://stackoverflow.com/questions/16176701/switch-between-panes-in-javafx#:~:text=Replace%20just%20a%20specific%20pane,of%20the%20stack's%20child%20list.)).
  * Accelerators and menu buttons for easy navigation ([/#202](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/202)).
  * Selecting a student in the list displays their lessons in the lesson panel, allowing easy browsing ([/#190](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/190)).
  * CommandBox automatically focuses when user starts typing a command, greatly increasing speed and productivity drastically improving UX ([/#202](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/202)).


#### Contributions to the User Guide

* [Managing Your Schedule](../UserGuide.md#managing-your-schedule) section
* [Saving the Data](../UserGuide.md#saving-the-data) section
* Most of the [Menu Bar Shortcuts](../UserGuide.md#menu-bar-shortcuts) section
* Table of contents for each section and back to table of contents button ([/#338](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/338))
* [`add`](../UserGuide.md#adding-a-student-add) and [`edit`](../UserGuide.md#editing-a-student-edit) commands

#### Contributions to the Developer Guide

* The [Calendar View](../DeveloperGuide.md#calendar-view) section
* [Switching between students, calendar, and tags](../DeveloperGuide.md#switching-between-students-calendar-and-tags) section
* Most of [Appendix A: Requirements](../DeveloperGuide.md#appendix-a-requirements), including the [product scope](../DeveloperGuide.md#product-scope), [user stories](../DeveloperGuide.md#user-stories), and [use cases](../DeveloperGuide.md#use-cases). 
* Most of [Appendix B: Glossary](../DeveloperGuide.md#appendix-b-glossary)
* Manual testing instructions for [navigating](../DeveloperGuide.md#navigation) the app and testing [shortcuts](../DeveloperGuide.md#shortcuts)

#### Contributions to team-based tasks

* Set up the Team Repo
* Set up and maintained Issue Tracker
* Set up Milestones
* Updated Gradle ([/#217](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/217/files), ) and Gemfile [/#4](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/4/files)
* Managed two releases (v1.3 & v1.4)
* Incorporated CalendarFX library into the product

#### Community

* 62 PRs reviewed: [GitHub](https://github.com/AY2122S1-CS2103T-F13-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
* non-trivial comments on others PRs: [59 comments](https://nus-cs2103-ay2122s1.github.io/dashboards/contents/tp-comments.html#43-wong-zhi-chester-chesterwongz-59-comments)
* Contributed to forum discussions: [18 posts](https://nus-cs2103-ay2122s1.github.io/dashboards/contents/forum-activities.html#24-wong-ster-chesterwongz-18-posts)
* Reported bugs and suggestions for other teams in the class: [15 issues for CS2103T-F13-1](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/created_by/Chesterwongz) & [15 issues for CS2103T-W13-3](https://github.com/Chesterwongz/ped/issues)

#### Tools

* Integrated a third party library (CalendarFX) to the project ([\#42]())
