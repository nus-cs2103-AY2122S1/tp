---
layout: page
title: Wu Xiao Yun's Project Portfolio Page
---

### Project: TuitionAddressBook (TAB)

Tuition Address Book (TAB) is an all-in-one desktop application for 1-to-1 private home tutors that helps to keep track of their students and their respective lesson information Everything on TAB can be done without ever leaving the keyboard! and empower tutors to provide the best quality home tuition service. TAB helps users manage students’ contact details faster than a typical mouse/GUI driven app.



Given below are my contributions to the project.

* **New Feature**: Added a tag command that allows users to view all existing tags in TAB.
  * What it does: This feature allows users to view a list of all existing tags created for the students. The tag list also includes the number of students labelled with each tag.
  * Justification: This feature improves the user experience as users can add different tags to the students for better organization.
  * Highlights: This feature complements the Find feature. Filtering by tags require users to input the exact tag name(s). 
    If users are unsure of the exact existing tag names, they can first view the tag list then filter students by the tag names they are interested in. 
    This feature required an in-depth analysis of design alternatives as there are multiple ways of implementing this enhancement. The current design is chosen due to the balance between ease of implementation, efficiency as well as space allocation.

* **New Feature**: Added a reminder command.
  * What it does: This feature allows users to view a list of lessons that ends within the next 48 hours.
  * Justification: This feature gives user a heads-up on the list of upcoming lessons so that they have time to prepare the lesson materials needed for upcoming lessons to improve their tuition services.
  * Highlights: This feature complements the Calendar feature. While users can view the sequence of lessons in a systematic manner using the various calendar views, they can only see the student name, subject and type of the lessons in the calendar views.
    Having the reminder command allows users to see the homework as well as outstanding fees for the lessons, if any. 
    This could aid in reminding users to prepare the materials and/or collect fees from their students.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=f13-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Xiaoyunnn&tabRepo=AY2122S1-CS2103T-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Enhancements to existing features**:
    * Added quick tips in result display section whenever users launch the app. (Pull requests [\#39](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/39))
    * Added additional fields regarding academic details (i.e. school, academic stream and academic level) for students. (Pull requests [\#38](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/38), [\#42](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/42))
    * Improved the Help feature by adding a command summary table with each command's description, format and example. (Pull request [\#99](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/99))
    * Updated the GUI color scheme and effects to improve user-friendliness. (Pull requests [\#173](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/173), [\#227](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/227), [\#318](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/318))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `tag` and `remind`. (Pull requests [\#163](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/163))
        * Updated glossary list. (Pull request [\#164](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/164))
        * Added student parameters table for easier reference. (Pull request [\#317](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/317)) 
        * Added annotations for GUI screenshots. (Pull request [\#317](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/317))
    * Developer Guide:
      * Added implementation details and use cases of the `tag` and `remind` features. (Pull requests [\#148](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/148) [\#342](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/342)).
      * Modified some diagrams (e.g. `Model` and `Ui` class diagrams).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()

