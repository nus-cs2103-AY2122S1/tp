---
layout: page title: Nathan Wong Hung Rui's Project Portfolio Page
---

### Project: Socius

### 1 About the project

Socius is a contact management desktop application used for CS2103T module takers. Socius is a platform to help students
find potential project group mates for CS2103T. The user interacts with it using a CLI, and it has a GUI created with
JavaFX. Socius is written in Java, and has about 12 kLoC.

### 2 Code contributed

Click on the following link to view the code that I have contributed to the project:

* [Nathan's Code Contribution](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=e0543978&tabRepo=AY2122S1-CS2103T-W08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

### 3 New features and enhancements added

The following describes the enhancements and new features that I have contributed to the project.

#### 3.1 Make Tag Colours Customisable

* **New Feature**: Added the ability to customise the colour of a tag by preference.
    * What it does: Allows the user to input their preferred tag colour either by colour code or colour name.
    * Justification: This feature improves the aesthetic appeal, and more importantly, the flexibility and customisability, which is what we pride our application in.
    * Highlights: This feature affects existing commands and commands to be added in future. The implementation was somewhat challenging as it required some significant changes to how data would be stored in the JSON file to accomodate the tag colours now that it was customisable. Test cases and test data also have to be updated accordingly.

#### 3.2 Change GUI

* **New Feature**: Made changes to the GUI to better suit our application.
    * What it does: Changed the colour scheme and layout of the GUI of our application.
    * Justification: This feature allows users a better user experience.
    * Highlights: The implementation of this feature was challenging as it required knowledge of how the GUI works, which meant knowledge of how JavaFX and CSS was integrated into the program. 

#### 3.3 Make Person's Fields Optional  

* **New Enhancement**: Added the ability to not include additional fields aside from `Name` when adding a new contact.
    * What it does: Allows the user to add a new contact without having to include every field.
    * Justification: We took into consideration the scenario that users might not wish to share certain personal contact details and felt this change was necessary. This feature improves user experience and the flexibility and customisability of our application.
    * Highlights: This enhancement affects existing commands and commands to be added in future. The implementation was tedious, but slightly more straightforward as it required minor adjustments to all existing fields. It also required some adjustments to how data would be stored now that majority of the fields were customisable. Test cases and test data also have to be updated accordingly.

### 4 Contributions to Documentations

#### 4.1 README
* Prepared the initial iterations of the README document (Pull request [\#32](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/32/files))

#### 4.2 User Guide
* Edited the User Guide to fit our product (Pull request [\#34](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/34/files))
* Updated the User Guide for release v1.2 (Pull request [\#96](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/96))

#### 4.3 Developer Guide
* Added Use Cases (Pull request [\#27](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/27/files))
* Updated the User Stories, Use Cases NFR and other miscellaneous portions (Pull request [\#275](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/275))
* Added UML diagrams and implementation details for the AddCommand feature (Pull request [\#95](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/95/files))

### 5 Community Contributions
* Helped to create demo screenshots
* Helped with general UI-related issues
* PRs reviewed (with non-trivial review comments): (Pull requests [\#90](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/90),
[\#54](https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/54))
* Reported bugs for other teams: (E.g. [\#2](https://github.com/e0543978/ped/issues/2), [\#3](https://github.com/e0543978/ped/issues/3), [\#4](https://github.com/e0543978/ped/issues/4), [\#6](https://github.com/e0543978/ped/issues/6))
* Managed 32 issues on GitHub.
