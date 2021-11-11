---
layout: page
title: Chi Sern's Project Portfolio Page
---

### Project: SalesNote

SalesNote is a desktop application that helps tailors to manage sales and keep track of a smaller, but more recurrent group of clients. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). It has about 20k lines of code written in Java.

Given below are my contributions to the project.

* **New Features**:
    * Added gender, measurement and remark fields to the Person class and updated the existing commands to make sure of these fields [#30](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/30) [#31](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/31) [#32](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/32)
        * Allows the user to record the gender and body measurements of a client while storing general notes about the client.
        * Justification: This feature extends the product's functionality because it allows the user to keep track of important crafting details of their clients, which will then help them shorten the workflow.
        * Highlight: This enhancement affects existing commands and commands to be added in the future. This makes the implementation challenging as it required changes to existing main commands and a bug could break the entire application.
    * Implemented tag for the task class [#74](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/74)
        * Allows the user to label a task for the purpose of identification.
        * Justification: This feature improves the product significantly because it allows user to categorise different tasks based on their purposes (a task can be either a general task or linked to a specific order).
    * Implemented add and delete orders functionality [#81](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/81) [#82](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/82)
        * What it does: allows the user to create and delete orders.
        * Justification: This feature is crucial to the product because it allows the tailors to manage their sales orders of the business more conveniently.
    * Implemented tabs' auto-switching based on the users' commands [#108](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/108)
        * What it does: allows the user to observe the respective lists of data based on the type of commands inserted (e.g., the tab will be switched to show the tailor's orders if the tailor executed a command related to orders).  
        * Justification: This feature improves the product significantly because the tailors can visually view and check result of a command.
        * Highlight: This features was challenging. It required a strong understanding of how GUI works and an in-depth analysis of design alternatives so that there is no additional coupling elements resulted.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ngchisern&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=ngchisern&tabRepo=AY2122S1-CS2103T-W08-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**: 
    * Set up the GitHub team organisation and repository
    * Managed release `v1.2.1`, `v1.3.1` and `v1.4.1` (3 releases) on Github

* **Enhancements to existing features**:
    * Improved GUI of the application and updated its color scheme [#53](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/53)
    * Update clear command to remove all data in SalesNote [#162](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/162)    

* **Documentation**:
    * User Guide:
        * Added documentation for commands related to tasks [#138](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/138)
        * Tweaked the table of content to increase flexibility when there are many headers (of different formats) in the UG [#167](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/167)
        * Rearranged the order of the commands so that it is more intuitive for the readers [#167](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/167)
        * Reviewed, raised and fixed issues throughout the UG
    
    * Developer Guide:
        * Added use cases for adding and deleting an order [#86](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/86)
        * Updated the Model class diagram [#86](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/86)
        * Tweaked the table of content to increase flexibility when there are many headers (of different formats) in the DG [#86](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/86)
        * Updated diagrams in Logic component [#273](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/273)
        * Updated acknowledgements in the DG [#260](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/260) [#273](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/273)
        * Updated the Model component's description and diagrams [#273](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/273)
        * Added sequence diagrams to show how changes in a person's details are updated in the task or order list [#273](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/273)
        * Reviewed, raised and fixed issues throughout the DG

* **Contributions to team-based tasks**:
  * Updated the `README.md` file to match our project
  * Updated Site Wide Settings in `_config.yml`, `_base.scss` and `index.md` files
  * Wrote test cases for some of the features implemented by other teammates  
  * In charge of versioning of the code, maintaining the code repository, integrating various parts of the software to create a whole.
  * Reviewed, raised and fixed issues in the repository

* **Community**:
    * Few examples of pull requests reviewed (with non-trivial comments): [#21](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/21),
      [#44](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/44),
      [#45](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/45),
      [#68](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/68),
      [#79](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/79)
    * Reviewed and reported bugs in other team's product ([link to the issues](https://github.com/ngchisern/ped/issues))
  

  
