---
layout: page
title: Chan Choon Yong's Project Portfolio Page
---
### Project: Socius

**About the project**

Socius is a contact management desktop application used for CS2103T module takers. Socius is a platform to help students
find potential project group mates for CS2103T. The user interacts with it using a CLI, and it has a GUI created with
JavaFX. Socius is written in Java, and has about 12 kLoC.

**Code contributed**

Click on the following link to view the code that I have contributed to the project: [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=w08&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=choonyongchan&tabRepo=AY2122S1-CS2103T-W08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

**3 New features and enhancements added**

The following describes the enhancements and new features that I have added to the project.

**3.1 Add and Delete Tags to people (Feature)** [#168](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/168), [#169](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/169)
* What it does: This feature allows the user to add and delete meaningful tags to people to better categorise and manage their contact list.
* Justification: This feature allows users to tag people according to their relationship (e.g. CS2103T Group Mate, CAPT Neighbour, etc.). 
This greatly helps users to classify and manage their contact list, especially if there are many people in their contact list.
* Highlights: This enhancement affects existing commands and commands to be implemented. The implementation was challenging as it changes the Person model which is depended on by many other classes, including the GUI.

**3.2 Find contacts (Feature)** [#60](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/60), [#194](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/194)
* What it does: This feature allows the user to search through their contacts using any keyword. 
This enables user to efficiently manage their contact list and improve ease-of-use of Socius.
* Justification: This feature allows users to find people in their contact list by any personal parameters that Socius supports.
This helps users to navigate and manage through their contact list with great ease by filtering their displayed contacts 
down to what they expect to see.
* Highlights: This enhancement is particularly tedious to implement. New Predicate classes were written for each parameter to support this feature while adhering to the Object-Oriented Principles applied
throughout this program.

**3.3 Test Classes and Test Data** [#287](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/287)
* What it does: New test classes and test data were written to rigorously test the correctness of Socius. There is at least one test class
for each class in the main program. 
* Justification: As my team builds on the existing Address Book 3 code, many classes are modified and added quickly. The functionality of
the program also differs greatly with these modifications. New test classes and test data have to be written to maintain the integrity of 
our programme and to prevent regression errors.
* Highlights: As the team's gatekeeper for program testing, each test class was inspected and new test cases were added to the test classes
wherever test coverage can be increased for Socius. Edge cases like emojis and non-Latin characters are also tested against Socius. Many lines
of test cases and test data were written to rigorously test the different aspects of Socius and to minimise program abuse.

**4 Contributions to Documentations**

**4.1 About Us**
* Set up the hyperlinks for profile picture, Github profile and Project Portfolio Page for each team member. [#12](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/23), [#29](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/29)

**4.2 User Guide**
* Standardised the format of the content in the User Guide. [#328](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/328)
* Added the 'Add Tag' and 'Delete Tag' section to the User Guide. [#206](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/206/files)
* Added a 'Table of Contents' to the User Guide. [#58](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/58/files)

**4.3 Developer Guide**
* Added the 'Find Command' section in the Developer Guide. [#205](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/205)
* Updated the 'Non-functional Requirements' section in the Developer Guide. [#26](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/26/files)

**5 Contributions to team-based tasks**
* Set up the team's Github Repository, Google Drive folder and initial project management using Notion.
* In charge of reviewing and approving the team's Github pull requests.
* Managed the milestones `v1.2` - `v1.3b` on Github.

**6 Other contributions**
* PRs reviewed (with non-trivial review comments): [#91](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/91), [#94](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/94)
