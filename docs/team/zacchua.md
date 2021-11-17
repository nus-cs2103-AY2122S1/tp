---
layout: page
title: Zachary Chua's Project Portfolio Page
---

### Project: Socialite

Socialite is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Twitter and TikTok handles to a person's contact card
  * What it does: allows user to keep track of friend's twitter and tiktok handles. 
  * Justification: This feature improves the product significantly as it is one of the main selling points of the app: Integration with social media
  * Highlights: This feature is necessary for the implementation of the Social Media Handle Hyperlinks feature elaborated below.
  
* **New Feature**: Added social media handle hyperlinks
  * What it does: allows user to go to a friend's profile on a particular social media platform in the default browser by clicking on the friend's social media handle. 
  * Justification: This feature increases integration with social media, and improves the entire user experience as it allows for seamless transitioning from socialite to social media platform. This is as compared to
  having to copy the handle from Socialite, then go to the social media website, and search for the handle.
  * Highlights: The implementation of this feature required integration between the GUI, the fields in the person and the browser.
  
* **New Feature**: Added Profile Picture to the person card
  * What it does: allows user to add a profile picture to a contact, by choosing from a pop-up file browser. Chosen file is copied to the `data/profilepictures` filder
  * Justification: This feature allows for greater customisation of contacts, and personalisation of the app.
  * Highlights: The implementation of this feature was challenging as it required changes to the storage and GUI, as well as knowledge of how to work with file systems in Java.
  * Credits: [credits](https://stackoverflow.com/questions/32781362/centering-an-image-in-an-imageview) for how to centre the image in an image view.
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Zacchua&tabRepo=AY2122S1-CS2103T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancement to Existing Features**:
  * Refactored Storage package from Addressbook to ContactList. [\#153](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/153)
  * Wrote tests to test profile picture related commands, parser, model and storage, raising coverage by 2.12% and 0.80% respectively [\#137](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/137). [\#144](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/144)

* **Documentation**:
  * User Guide:
    * Did the initial update from Addressbook 3 User Guide to Socialite User Guide [\#10](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/10)
    * Added documentation for the hyperlinks feature [\#54](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/54)
    * Added documentation for the profile picture feature [\#75](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/75)
  * Developer Guide:
    * Updated Storage package portion of DG, which includes documentation and UML diagrams [\#158](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/158)
    * Added Picture Command portion into Implementation section of DG, including sequence diagram and write-up [\#158](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/158)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#50](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/50), [\#52](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/52), [\#56](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/56), [\#135](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/135), [\#140](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/140),
