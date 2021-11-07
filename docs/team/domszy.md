---
layout: page
title: Dominic's Project Portfolio Page
---

### Project: LeadsForce

LeadsForce - LeadsForce is a client management software that was created to help student financial advisors to keep track of client information and client meetings. Optimised with a command-line interface (CLI), the application seeks to streamline the way financial advisors have been managing clients.

Given below are my contributions to the project.

* **Added several client attributes (Pull Request: [#45](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/45))**:
  * The attributes I have added to LeadsForce includes `current financial plans` and `Last Met`. 
  * Current financial plans allows financial advisors to keep track of current financial plans, so that they can have a more comprehensive idea of the financial state of their clients
  * Client ID is used in order to make it easier for commands to be runned, and is used extensive throughout LeadsForce. 
  * Amended the `edit`, `add` commands to allow the app to take in these attributes
  * Highlights: this feature involved changing many features throughout the application since alot of the features relies on these attributes. Since the attributes are quite pervasive throughout the application, I had the chance to work with every component of the application.

* **New Command: `Schedule` (Pull Request: [#111](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/111), [#145](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/145))**
  * What it does: the command allows financial advisors to see the schedule of meetings they have with clients
  * Justification: Clients can simply use this command to determine the schedule of meetings that they have on the day.
  * Highlights: this feature was implemented with several team mates. Since I was doing the backend portion, the implementation came with several challenges in understanding my team mates portion (the GUI portion) and how it can be linked to my implementation of the schedule command. 

* **New Command: `View` (Pull Request: [#45](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/45/files))**
  * What it does: view in-depth information of a client
  * Justification: To be able to view the client's in-depth information 
  * Mainly did the backend of the `view` command

* **Enhancements to existing features**:
  * Updated the `edit` and `add` command to accomodate the new attributes in LeadsForce (Pull Request: [#67](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/67))
  * Wrote additional tests for existing features to increase coverage

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t17&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Domszy&tabRepo=AY2122S1-CS2103T-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide (Pull Request: [#205](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/205)):
    * Overhauled the User Guide to using icons and tables to increase readability
    * Removed bugs and misunderstandings from user guide
    * Wrote the `edit` and `view` commands in the user guide, the QnA portion, troubleshooting portion, client information portion, bugs reporting portion and the introduction.
    * Added the expected outcomes of commands and 
    * Organisation and dividing of the user guide
  * Developer Guide (Pull Request: [#205](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/205)):
    * Added implementation details of the `edit` and `view` command ([#24](https://github.com/AY2122S1-CS2103T-T17-3/tp/pull/24))
    * Wrote the introduction, expanded on use cases, manual testing portion, product scope and the different components.
    * Color coded the different components of the application.
    * standardising the diagrams to make it easier to read, and changing the colours of the diagrams as well.

