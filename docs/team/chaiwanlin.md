---
layout: page
title: Wan Lin's Project Portfolio Page
---

# Project: EdRecord

EdRecord is a **desktop student management app, for teaching assistants to manage their student contacts**. 
The EdRecord codebase was initially adapted from [AddressBook Level 3](https://se-education.org/addressbook-level3/), a desktop address book application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

---

#Contributions
* [Code](#code)
* [Features implemented](#features-implemented)
* [Documentation](#documentation)
* [Team-based tasks](#team-based-tasks)
* [Community](#community)

### Code
* [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=chaiwanlin&tabRepo=AY2122S1-CS2103-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* [Pull Requests](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=is%3Apr+author%3Achaiwanlin)


### Features implemented
* **Create module**
    * What it does: allows the user to create modules to store classes which students can be assigned to.
    * Justification: This feature improves the product significantly because a user can assign students to classes in the modules to classify them. 
      Reduces mistakes by user by ensuring module is valid (a created module) instead of using tags.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing architecture.
    * Credits: adapted from AB3 `AddressBook` implementation.


* **Delete module**
    * What it does: allows the user to delete stored modules once useless.
    * Justification: This feature improves the product significantly because a user can delete modules once it is useless so that modules saved do not pile up and waste space.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing architecture.
    * Credits:


* **Create class**
    * What it does: allows the user to create classes under modules which students can be assigned to.
    * Justification: This feature improves the product significantly because a user can assign students to classes in the modules to classify them.
      Reduces mistakes by user by ensuring class is valid (a created class) instead of using tags.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing architecture.
    * Credits: adapted from AB3 `AddressBook` implementation.


* **Delete class**
    * What it does: allows the user to delete stored class once useless.
    * Justification: This feature improves the product significantly because a user can delete classes once it is useless so that classes saved do not pile up and waste space.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing architecture.
    * Credits:


* **Move student to class**
    * What it does: allows the user to assign students to classes under modules which they belong.
    * Justification: This feature improves the product significantly because a user can assign students to classes in the modules to classify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing architecture.
    * Credits: 


* **Remove student to class**
    * What it does: allows the user to remove students from classes under modules which they do not belong.
    * Justification: This feature improves the product significantly because a user can remove students from classes under modules which they do not belong to ensure user can rectify mistakes or update student classification.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing architecture.
    * Credits:


* **UI layout and colors**
    * What it does: change layout of UI to closer to that of a terminal CLI and add more distinctive colors.
    * Justification: This feature improves the look and feel of the product.
    * Highlights: It required a moderate understanding of JavaFX.
    * Credits:


### Documentation
* User Guide:
    * Edited documentation to integrate with team project  [\#62](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/62)
    * Updated documentation for the features: `mkmod`, `mkclass`, `dlmod`, `dlclass`, `mv`, `rm`
* Developer Guide:
    * Added implementation details of the `Module` and `Group` features [/#335](https://github.com/nus-cs2103-AY2122S1/tp/pull/335).
    * Diagrams contributed [/#215](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/215):
      <img src="images/ModelClassDiagram.png" width="450" />
      <img src="images/ModuleSystemModelClassDiagram.png" width="450" />
      <img src="images/StorageClassDiagram.png" width="450" />
      <img src="images/BetterModelClassDiagram.png" width="450" />


### Team-based tasks
* Manage milestones `v1.2` - `v1.4` (3 milestones) on GitHub
* Manage user stories on GitHub issues
* Check code quality
* Set up team project site-wide settings.
* Completed Demo v1.2


### Community
* [PRs reviewed (with non-trivial review comments)](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=is%3Apr+reviewed-by%3Achaiwanlin)
* [Reported bugs and suggestions for other teams in the class](https://github.com/AY2122S1-CS2103T-F11-4/tp/issues?q=is%3Aissue+is%3Aclosed+chaiwanlin)







