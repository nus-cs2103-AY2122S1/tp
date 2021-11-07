---
layout: page
title: Aishwarya Nair's Project Portfolio Page
---

### Project: CohortConnect

CohortConnect is an advanced desktop address book which facilitates networking among Computer Science (CS) students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Features**
  * **Add User Profile**:
    * Pull request [\#128](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/128)
    * What it does: Allows user to create a CohortConnect profile by providing their name, Telegram handle and GitHub username.
    * Justification: Through this feature, the user's GitHub username can be stored and be utilised by the Find a Buddy feature to extract insights from GitHub metadata and
      provide recommendations for potential teammates from their contacts.
    * Highlights: Besides providing an entry point for the functioning of the Find a Buddy feature, the implementation of this feature was pretty challenging 
    as it required integration with the Storage, Model, Logic and UI components and the creation of new Storage interfaces and classes to facilitate saving and access of the user profile details independently.
    
  * **Edit User Profile**:
    * Pull request [\#150](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/150)
    * What it does: Allows user to edit their profile (name, Telegram handle and GitHub username) linked to CohortConnect's address book. 
    * Justification: This feature allows users to keep their own details updated, especially their GitHub username.
    * Highlights: The user's GitHub username remaining updated is crucial since the GitHub metadata is utilised by the Find a Buddy feature to extract insights and 
    provide recommendations for potential teammates from their contacts.
    
  * **Tag Command**
    * Pull requests [\#256](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/256) and [\#264](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/264)
    * What it does: Allows user to directly add tags to or remove tags from a specific contact.
    * Justification: Editing a contact's tags using the edit command causes the new tags to replace the old ones instead of adding onto them and there was no direct way to remove tags from a specified contact.
    Hence, the Tag command allows users to  add or remove tags from a specific contact with ease.
    * Highlights: This feature overcomes the limitation of using the Edit command to edit the tags for a specified contact and enhances user experience.
    
  * **Clickable GitHub Username and Telegram Handle**
    * Pull requests [\#86](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/86) and [\#96](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/96)
    * What it does: Allows users to directly navigate to their contact's GitHub profile and Telegram from their contact's details pane.
    * Justification: Makes navigating to contacts' GitHub profiles and reaching out to them over Telegram very convenient for the user.
  

* **Enhancements to existing features**:
    * Find feature : implemented find by tag, Telegram handle and GitHub username (Pull requests [\#41](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/41), [\#64](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/64), [\#97](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/97))
    * Tag type : identifies whether the tag is a General, Event or Module tag (Pull request [\#95](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/95) )


* **Testing**:
  * Added test cases for the following classes :
    * EditCommandTest (Pull requests [\#274](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/274) )
    * FindCommandTest and FindCommandParserTest (Pull requests [\#64](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/64), [\#97](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/97))
    * TagCommandTest and TagCommandParserTest (Pull requests [\#264](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/264) )


* **Documentation**:
    * User Guide:
        * Added documentation for the features `find`, `edit profile` and `tag` (Pull requests [\#237](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/237), [\#283](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/283), [\#268](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/268))
    * Developer Guide:
        * Added implementation details of the `find`, `edit profile` and `tag` features (Pull requests [\#108](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/108), [\#304](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/304))


* **Community**:
    * Reported bugs and suggestions for my teammates code (examples: [1](https://github.com/AY2122S1-CS2103T-T10-1/tp/issues/158), [2](https://github.com/AY2122S1-CS2103T-T10-1/tp/issues/159), [3](https://github.com/AY2122S1-CS2103T-T10-1/tp/issues/286))
  

* **Code contributed** : [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T10&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=aishh12&tabRepo=AY2122S1-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
    
