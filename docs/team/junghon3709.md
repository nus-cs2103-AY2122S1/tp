---
layout: page
title: Yip Jung Hon's Project Portfolio Page
---

### Project: NewAddressBook

NewAddressBook is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: `undo` command.
  * What it does: allows the user to undo all previous commands one at a time.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: The implementation was challenging as it required changes to existing commands. Moreover, `undo` was implemented in a such a way such that if the user keyed in commands that did not change the content of the application, executing `undo` takes the user back to the last command which changed the content of the application, rather than the last command the user executed. Doing this required some analysis which commands could leave the application state unchanged. (Pull requests: [\#192](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/192/commits/2dfd0abfafdf3c0aa5de24d8e607e794fc5913fc), [\#245](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/245), done by Yong Chuan). 

* **New Feature**: `copyStudent/copyTeacher` command.
  * What it does: it gives users the ability to copy phone numbers, email addresses and names from the displayed list.
  * Justification: This feature improves the user's productivity. For example, should one want to send an email to a subset of students in address book, one can simply filter the students and copy their email addresses. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=junghon3709&tabRepo=AY2122S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Project management**:
  * Opened up most issues during team meetings to keep track of the tasks needed to be done.
  * Highlighted bugs found in the product to my team. (Issues: [\#117](https://github.com/AY2122S1-CS2103-T16-3/tp/issues/117), [\#110](https://github.com/AY2122S1-CS2103-T16-3/tp/issues/110), [\#191](https://github.com/AY2122S1-CS2103-T16-3/tp/issues/191), [\#248](https://github.com/AY2122S1-CS2103-T16-3/tp/issues/248))
  * Split command involving students and teachers into separate catergories. This was a massive effort as it required many test cases and classes to be updated. ([\#75](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/75))

* **Enhancements to existing features**:
  * Add code skeleton when our group was working on the meeting window. This is needed because back then, we needed some commands to work for the main window, but not for the meeting window, and vice versa. Hence some sort of mechanism was needed for the application to detect which window the user was executing the command from. This PR implements that detection mechanism. ([\#102](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/102))
  * Wrote additional tests for existing features to increase coverage ([\#78](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/b309774979bea3733f3318054af47d017835b6ae), [\#75](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/be7beeaea550ccc5c475ddc578afe4ecaa6fada6), [\#51](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/153080a5848a458763f8b838ba8a26370de6a986))

* **Documentation**:
  * User Guide:
    * Added Glossary to user guide ([\#201](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/9196add4c790b93839569b68dce8b4cd03b8069b))
    * Made the order the commands appear in the UG the same alphabetical ([\#140](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/f0313140abfce5eb95381b2f6207654f61985e0e))
    * Did the `copy` command section for the UG ([\#138](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/ab727689ae4f96a4e1bc6ff71cc0037f37fe0987))
    * Reviewed and recommended some improvements to the UG for my teammates ([\#138](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/52d04c48f32a2eab7fb0ba4e7bb8eb853f3d8efb), [\#138](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/ab727689ae4f96a4e1bc6ff71cc0037f37fe0987), [\#140](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/f0313140abfce5eb95381b2f6207654f61985e0e))
  * Developer Guide:
    * Added implementation details of the `undo` and `copy` feature. ([\#138](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/ab727689ae4f96a4e1bc6ff71cc0037f37fe0987), [\#91](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/d5e7cd7a6ba60320e2a285ebb368a3bc23e3adc9))
    * Created the UML diagrams to suit our code structure. ([\#192](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/4d32b5d0a7e05268995ba038518c4c46f5b9e5f3), [\#192](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/e17b9c16e4ee8b6164fdf01b670d1bbeb39ced3a), [\#192](https://github.com/AY2122S1-CS2103-T16-3/tp/commit/e17b9c16e4ee8b6164fdf01b670d1bbeb39ced3a))
    * Added the manual testing section of the DG ([\#263](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/263))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#243](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/243), [\#99](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/99), [\#106](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/106)
