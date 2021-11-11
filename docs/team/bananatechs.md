---
layout: page
title: Thomas's Project Portfolio Page
---

### Project: AddressBook Level 3

Trace2Gather is a desktop address book application used for managing hotel guests and rooms. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T13-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&zA=peilinye&zR=AY2122S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&zACS=150.4848484848485&zS=2021-09-17&zFS=T13-3&zU=2021-10-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

### Enhancements Implemented
* `checkin` and `checkout`: Added the ability to check guests in and out of rooms.
    * **What it does**: allows the user to assign guests to a room and keep track of which rooms are occupied, as well as check them out when they leave the hotel.
    * **Justification**: This feature is indispensable for managing hotels.
    * **Highlights**: Conceived, designed and built the entire Residency system as a solution to problems posed by this feature. This system is a foundation for many new features to be built upon. See below for details.
    * **Credits**: Original AB3 team, Darren for the Room system and many misc. fixes and tests.
<br><br>
* **Residency System (Design and Implementation)**
  * The Residency system is responsible for storing the details of the stay of guests in a room. It is easy to work with, simple to expand upon, and relatively compatible with existing architecture.
  * I designed the system to solve many problems otherwise inherent in the architecture. It decouples and serves as a mediator between the Room and Person systems, and is fast, reliable and easy to store.
  * The system also serves as the foundation for many other features. For example:
    * `Residency` provides a previously unavailable place to store additional details about stays, including the dates and times of check-ins / check-outs.
    * `ResidencyBook` centralises the registration, lookup and removal of `Residency`s, and thus simplifies the storage of past stays separate from current stays, by simply having another `ResidencyBook` for them.
<br><br>
* Rewrote `Vacancy` class, greatly simplifying it and vanquishing bugs
<br><br>
* Misc. functionality

### UG Contributions
* Re-writes to simplify language:
  * Introduction
  * Navigation Guide
  * Section introductions
* Documentation for `checkin` and `checkout`
* Proof-reading, grammar improvements

### DG Contributions
* Implementation section: Encapsulation of Hotel Stays (The Residency System)

### Testing
  * `CheckInCommand`, `CheckOutCommand`
  * `CheckInCommandParser`, `CheckOutCommandParser`
  * `ResidencyBook`
  * `JsonAdaptedResidency`
  * Added `TypicalAddressBook`
  * Misc. other tests, fixes and functionality
