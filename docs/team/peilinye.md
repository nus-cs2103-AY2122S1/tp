---
layout: page
title: Ye Pei Lin's Project Portfolio Page
---

# Project: Trace2Gather

Trace2Gather is a desktop address book application used for managing hotel guests and rooms to aid in contact tracing efforts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed:**
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=peilinye&tabRepo=AY2122S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **New Features:**
  * Added the functionality of listing rooms by vacancy status.
    * What it does: Shows users the rooms that are either vacant or occupied in the GUI.
    * Justification: This feature improves the product significantly by making it more convenient for users to view rooms of a certain vacancy status to easily check in guests into a vacant room or checkout an occupied room.
    * Credits: This feature was built on the existing `list rooms` feature.
  * Added the functionality of adding rooms with tags.
    * What it does: Allows the user to add a specified number of rooms with one or more tags.
    * Justification: This feature improves the product significantly by providing a way for users to add more rooms with additional information of the rooms.
    * Highlights: This feature affected the current implementation of the `room` object as there was an additional attribute of tags. The implementation for storing and loading information from JSON files had to be updated. Support for parsing the new command had to be added as well.

* **Enhancements to existing features:**
  * Added UI components necessary for displaying the list of rooms, such as `RoomCard` and `RoomListPanel`.
  * Added Tags as an attribute of rooms to represent different types of rooms or any optional additional information of the rooms.
  * Added validity checking of input for the `room` command to search for specific rooms, so an error message will be shown when user input is invalid.

* **Documentation:**
  * User Guide:
    * Added documentation for the features `list rooms vacant` and `list rooms occupied`.
    * Added documentation for the `addroom` feature.
    * Ensure overall formatting of user guide is shown correctly. [#133](https://github.com/AY2122S1-CS2103T-T13-3/tp/pull/133)
    * Added the screenshots for the following commands: `guest`, `checkin`, `checkout`, `room`.
    * Updated the command summary table.
  * Developer Guide:
    * Added use cases, user stories.
    * Added the part on listing rooms by vacancy feature under the implementation section, including the sequence diagram. [#189](https://github.com/AY2122S1-CS2103T-T13-3/tp/pull/189/files)
    * Added manual testing instructions for dealing with missing or corrupted data files. [#206](https://github.com/AY2122S1-CS2103T-T13-3/tp/pull/206/files)
    * Added acknowledgements to the AB3 project.

* **Testing:**
  * Logic:
    * `AddressBookParserTest`: `AddRoomCommand`
    * `AddRoomCommandParserTest`, `AddRoomCommandTest`
    * `FindRoomCommandParserTest`
    * `ListCommandParserTest`, `ListCommandTest`
  * Model:
    * `RoomBuilder`

* **Team-Based tasks:**
  * Updated User Guide based on peer feedback.
  * Ensured overall formatting of User Guide and Developer Guide was correct.
  * Added user stories to Developer Guide.
  * Maintained issue tracker alongside teammates.

* **Community:**
  * PRs reviewed (with non-trivial review comments): [#215](https://github.com/nus-cs2103-AY2122S1/ip/pull/215), [#32](https://github.com/nus-cs2103-AY2122S1/ip/pull/183)
  * Reported bugs of another team during PE-D
