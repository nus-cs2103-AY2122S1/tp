---
layout: page title: Kar Wi's Project Portfolio Page
---

### Project: AddressBook Level 3

LeadsForce - LeadsForce is a desktop application catered towards student financial advisors (FA) for managing their leads.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Add a filter tag panel. (Pull requests [\#138](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/138))
  * What it does: It provides an at-a-glance view of the tags associated to all the clients. 
  * Justification: It complements the filter feature since it gives the user an idea of what tags and how many tags are currently assigned, thus allowing them to filter their desired clients more quickly. 
  * Future improvements: Each tag in the tag panel can have a client count displayed to give the user an idea of how many clients are currently associated to this tag.
  * Highlights: The implementation of tag panel was rather challenging since it involves setting up a custom listener as opposed to `ListView`, which by default, already has listener set up. A garbage collection mechanism will also need to be set up to ensure that any lingering unreferenced tags are purged. For this, I have to create another listener to watch for any changes in the `UniqueClientList` and trigger the removal of unreferenced tags from there.

* **New Feature**: Implemented clickable filter tag buttons. (Pull requests [\#138](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/138))
  * What it does: Every tag is now a clickable button. Clicking on each tag allows the user to filter the client list based on the tag name.
  * Justification: It allows users even the fastest typists to gain an extra speed when it comes up filtering by tags. e.g. instead of typing `filter t/thisisaveryextremelylongtagname`, you can just click on the button and LeadsForce will write the command for you.

* **New Feature**: Implemented field interface. (Pull requests [\#55](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/55))
  * What it does: Enforces developers to declare field options for the client attribute class they created.
  * Justification: It provides an encapsulation of the constraints that a certain field has e.g. is it a required field? can this field be empty? etc.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=CrownKira&tabRepo=AY2122S1-CS2103T-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Manage releases `v1.2.1` (1 releases) on [GitHub](https://github.com/AY2122S1-CS2103T-T17-3/tp/releases).
    * Create the team repo. 
    * Configure the initial state of our project e.g. update site headers, refactor the file names to tailor to our project specifications.
    * Propose the usage of GitHub project Kanban and migrate all the todos over. 
    
* **Enhancements to existing features**:
    * Revamp the UI to give LeadsForce a whole new look. (Pull requests [\#133](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/133))
    * Create more themes: SpaceTheme, DarkTheme, TwilightTheme. (Pull requests [\#202](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/202))
    * Reimplement Tag class to be more OOP. (Pull requests [\#104](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/104))

* **Documentation**:
    * User Guide:
        * Add documentation for client information. [\#230](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/230)
        * Standardize the command format and terms used and rewrite the section `Notes about the command format`. [\#233](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/233)
        * Add glossary and introduced the term `multiplicity`. [\#233](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/233)
    * Developer Guide:
        * Update model architecture section due to `Tag` class reimplementation. [\#109](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/109)
        * Add design details of `Field` interface. [\#109](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/109)
        * Add `GUI Feature` section and implementation details for `Filter tag panel`. [\#233](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/233)
        * Add `Extensions and customisations` section and recommend a workflow for developers to follow to tweak or add new themes. [\#233](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/233)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#51](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/51), [\#67](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/67), [\#64](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/64)
