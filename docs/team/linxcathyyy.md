---
layout: page
title: Xingchen's Project Portfolio Page
---

### Project: Sellah

**Sellah** is a desktop application optimized for online sellers who prefer using a `Command Line Interface (CLI)` over
the `Graphical User Interface (GUI)`. It is used to keep track of the contact information of your clients and details of
the products in your inventory, as well as details of the orders placed by your clients. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**:
  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Linxcathyyy&tabRepo=AY2122S1-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**: Implemented delete command for client and product
  * What it does: allows the user to delete a specific client or product from database
  * Justification: This feature improves the product significantly because a user might want to make changes in his/her
    inventory due to low sales or the some clients might no longer be available, hence the app should provide a way for
    the user to make changes in the database.
  * Pull request: [\#73](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/73)
  
* **Enhancements to existing features**: Implemented unique client and product's list
  * What it does: stores a list of clients or products with different IDs
  * Justification: This feature improves the product significantly because a user should not be able to add duplicate
    clients or products in the database, as duplicate clients or products might lead to confusion.
  * Pull requests:
    [\#65](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/65),
    [\#70](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/70)

* **Enhancements to existing features**: Wrote additional tests for new and existing features to increase coverage from 33% to 70%.
  * Pull requests:
  [\#112](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/112),
  [\#114](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/114),
  [\#119](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/119),
  [\#213](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/213)

* **New feature**: Implemented second panel for view, help and stat command together with Benjamin Lui (benluiwj)
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
  * Pull request:
    [\#144](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/144)
    
* **Documentation**:
  * User Guide:
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
    * (Pull requests
    [\#41](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/41),
    [\#51](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/51),
    [\#147](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/147),
    [\#189](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/189),
    [\#190](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/190),
    [\#222](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/222),
    [\#224](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/224))
      
  * Developer Guide:
    * Section 1 (Introduction)
    * Section 2 (Setting Up & Getting Started)
    * Section 3.1 (Architecture)
    * Section 3.2 (UI component)
    * Section 3.3 (Logic Component)
    * Section 3.4 (Model Component)
    * Section 3.5 (Storage Component)
    * Section 3.6 (Common Classes)
    * Section 4.2 (Delete Client/Product Feature)
    * Section 6.1 (Product Scope)
    * Section 6.2 (User Stories)
    * Section 6.3 (Use Cases)
    * Section 6.4 (Non-Functional Requirements)
    * Section 6.5 (Glossary)
    * Section 7.1 (Launch and Shutdown)
    * Section 7.2 (Viewing Help)
    * Section 7.3 (Deleting a Client/Product)
    * (Pull requests
    [\#42](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/42),
    [\#148](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/148),
    [\#302](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/302),
    [\#311](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/311),
    [\#312](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/312),
    [\#326](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/326))

* **Contributions to team-based tasks**:
  * Drawing all UML diagrams for DG
  * Setting up the GitHub team org/repo
  * Managing the issue tracker
  * Designing the new GUI/icon for Sellah

* **Contributions to the community**:
  * Reported bugs and suggestions for other teams in the class can be found [here](https://github.com/Linxcathyyy/ped/issues).

* **Bug fixed**:
  * GUI Display (Pull request [\#290](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/290))
  * Error Message (Pull request [\#291](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/291))
  * Email Validation (Pull request [\#292](https://github.com/AY2122S1-CS2103T-T12-1/tp/pull/292))
