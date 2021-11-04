---
layout: page
title: Xingchen's Project Portfolio Page
---

## Project: Sellah

### Overview
**Sellah** is a desktop application optimized for online sellers who prefer using a `Command Line Interface (CLI)` over
the `Graphical User Interface (GUI)`. It is used to keep track of the contact information of your clients and details of
the products in your inventory, as well as details of the orders placed by your clients. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### Summary of Contributions
Given below are my contributions to the project.

#### Contribution of Code
The code that I have contributed to the team can be found [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Linxcathyyy&tabRepo=AY2122S1-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).

#### Enhancements to Existing Features
* Wrote additional tests for new and existing features to increase coverage from 33% to 70%
* Helped to develop the UI of Sellah
* Helped to remove traces of Person (the old model)
* Helped to remove unused testcases (the old testcases)

#### New Features Implemented
Implemented delete command for client and product
* What it does: allows the user to delete a specific client or product from database
* Justification: This feature improves the product significantly because a user might want to make changes in his/her 
  inventory due to low sales or the some clients might no longer be available, hence the app should provide a way for 
  the user to make changes in the database.
  
Implemented unique client and product's list
* What it does: stores a list of clients or products with different IDs
* Justification: This feature improves the product significantly because a user should not be able to add duplicate
clients or products in the database, as duplicate clients or products might lead to confusion.

Implemented second panel for view, help and stat command together with Benjamin Lui (benluiwj)
* What it does: a panel to show additional information other than clients and products. When a user first opens the app,
  a help message with example commands will be displayed on the second panel. When a user wants to view the details of
  a specific client or product, the details will be shown on the second panel. When a user wants to view the statistics
  of products sold, the statistics will also be shown on the second panel.
* Justification: This feature improves the user experience significantly because a user might want to see a getting
  started page when he/she first opens Sellah so the user can learn to use Sellah. Besides that, it is very wordy and 
  difficult user to find the information that he/she is looking for if the client/product list contains too much 
  information. This is why we have a view function, which is to get more detailed information of a specific 
  client/product. Lastly, we believe it is important for sellers to analyze their client’s buying patterns and 
  understand their needs in order to make necessary improvements to their products, hence we implemented the statistics
  function on the second panel.

#### Contributions to the UG
* Section 1 (Introduction)
* Section 2 (Quick Start)
* Section 3.2.1 (Technical Terms)
* Section 3.2.2 (General Symbols and Syntax)
* Section 4.3.1 (Deleting a Client)
* Section 4.3.2 (Deleting a Product)
* Section 4.12 (Saving Data)
* Section 4.13 (Editing the Data file)
* Section 5 (FAQ)
* Section 6 (Command Summary)

#### Contributions to the DG
* Delete Client/Product Feature
* Appendix: Requirements
* Appendix: Instructions for manual testing

#### Contributions to Team-based Tasks
* Designing the new GUI for Sellah
* Setting up the GitHub team org/repo
* Managing the issue tracker
* In charge of implementing and reviewing testcases

#### Contributions to the Community
* Reported bugs and suggestions for other teams in the class can be found [here](https://github.com/Linxcathyyy/ped/issues).
