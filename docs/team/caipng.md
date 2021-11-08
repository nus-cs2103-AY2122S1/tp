---
layout: page
title: Cai Kaian's Project Portfolio Page
---
# Project: EdRecord

EdRecord is a **desktop student management app, for teaching assistants to manage their student contacts**. The EdRecord codebase was initially adapted from [AddressBook Level 3](https://se-education.org/addressbook-level3/), a desktop address book application. The app is optimized for users that can type fast and are comfortable with using a CLI. This is a medium sized Java project with roughly 15k LoC. 

---

### Code Contributed

[RepoSense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=w14-3&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=caipng&tabRepo=AY2122S1-CS2103-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

[PR Authored](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=author%3Acaipng)

[PR Reviews](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=reviewed-by%3Acaipng)

[Issues Authored](https://github.com/AY2122S1-CS2103-W14-3/tp/issues?q=author%3Acaipng)


### Enhancements Implemented

1. Assignment View
    * A new command `view (contacts/asg)` was added. This command toggles the current view between contacts and assignment. The contacts view is optimized for looking through contacts, while the assignment view is optimized for displaying the list of assignments and grades for each student.
    * Major changes were made to the UI of the product, including the addition of tables for displaying assignments.
    * This feature required heavy usage of the observer pattern, so that the UI is able to reflect changes to assignments etc. when the user makes changes
    * The assignment view is an important attraction of our product, as teaching assistants are able to easily manage their students' assignment submissions through this interface.

2. `cd` Command to Select a Module
    * This command enables teaching assistants to select a particular module that they will like to work in. After selecting a module, future commands will work based on this particular module.
    * This somewhat emulates the concept of multitenancy and is motivated by the fact that most of the time, users will only be working on a specific module. Hence, keystrokes are saved as users would not need to specify the module in their commands every time.
    * This also included changes to the UI, specifically the status bar footer, which was updated to display the currently selected module.

3. `lsmod` and `lsclass` Command to List all Modules and Classes
    * These commands enable teaching assistants to quickly get a full list of the modules and classes that they have
    * This comes in handy for users to quickly verify that their previous commands (usually adding or deleting modules/classes) worked as intended

4. Arrow Keys to Cycle Through Command History
    * This is a familiar feature to those proficient with a terminal. Users are able to use their up and down arrow keys to cycle through their previous commands.
    * This is a feature targeted at our intended users, and will greatly improve their efficiency in using our app.

### User Guide

1. Refine and elaborate on documentation for `lsmod`, `mkmod`, `mkclass`, etc.
2. Contributed some to the introduction and setting up sections 
2. Proofreading and formatting

### Developer Guide

1. Updated existing UML diagrams: [#221](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/221)
2. Added all instructions for manual testing

### Team-based tasks

1. Helped in setting up Github repo 
2. In charge of maintaining the issue tracker
   1. Authoring issues based on discussions or previous PRs
   2. Assigning issues to respective teammates
   3. Ensuring issues have correct tags and milestones
   4. Ensuring all issues are being worked on, or closed with valid justifications
3. In charge of release management for every iteration
   1. Ensure all relevant issues and PRs are closed, or postponed to next iteration
   2. Ensure product on master is in a state fit for release
   3. Create the Github release and ensure the tags are correct
   4. Close the milestone
4. Non specific UG/DG contributions
   1. Created About Us page
   2. Added all instructions for manual testing
5. In charge of integration: Ensure that all PRs have the correct tags/milestone, appropriate title and description, at least 2 approving reviews and all checks passing before finally merging into master

### Reviewing/Mentoring

1. Contributed to forum discussions (example: [#109](https://github.com/nus-cs2103-AY2122S1/forum/issues/109#issuecomment-907613811))
2. PRs Reviewed: [Full List](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=reviewed-by%3Acaipng); With non-trivial comments: [#82](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/82), [#92](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/92), [#98](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/98)
3. Reported bugs and suggestions for other teams in the class (examples: [#179](https://github.com/AY2122S1-CS2103-T16-1/tp/issues/179), [#174](https://github.com/AY2122S1-CS2103-T16-1/tp/issues/174), [#173](https://github.com/AY2122S1-CS2103-T16-1/tp/issues/173), [#119](https://github.com/AY2122S1-CS2103-T16-1/tp/issues/119), [#133](https://github.com/AY2122S1-CS2103-T16-1/tp/issues/133))
