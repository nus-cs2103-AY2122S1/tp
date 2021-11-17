---
layout: page
title: Yan Xiaozhi's Project Portfolio Page
---

### Project: SociaLite

**SociaLite** is a desktop app for **connecting avid social media users with their contacts’ social media pages**, optimized for use via a command line interface (CLI). At the same time, it has a graphical user interface (GUI) created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Command history (PR [\#52](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/52), [\#130](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/130))
  * What it does:
    * Allows users to browse through commands that they have entered by pressing `UP/DOWN` key in the command box.
    * Resembles the behavior of macOS or Windows terminal.
  * Justification:
    * Previously, if a user made a careless typo in the command, he/she would have to re-type the entire command, which can be very frustrating and time-consuming.
    * With command history, users can simply press the `UP` key, make slight edit to the previous command and hit `Enter` again.
    * This feature improves the UX significantly because a user can now rectify his/her typo in an efficient way.
  * Highlights:
    * Command history of the current session, as well as all previous sessions, are stored in a `.json` file. This requires good grasp of json serialisation.
    * The design of this feature involves thorough understanding of the `Storage` component, and multiple new classes are created.

* **New Feature**: Pin/Unpin contact card (PR [\#81](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/81), [\#142](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/142))
  * What it does:
  	* Allows users to pin/unpin specified contact cards to the top of the list, similar to Telegram.
  	* Users can either type `pin/unpin INDEX` in the command box, or press the `Pin/Unpin` button on the top-right hand corner of the contact card.
  * Justification:
  	* Users might want to keep a few of their closest friends/family members at the top of their contact list for easy reference.
  * Highlights:
  	* The design of this feature involves thorough understanding of `ContactList`, `UniquePersonList`.
  	* This feature provides support for both CLI and GUI.

* **New Feature**: Share contact (PR [\#71](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/71), [\#140](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/140))
  * What it does:
  	* Allows users to copy contact information of a selected contact card (e.g. name, phone number, social media handles) to the system clipboard.
  	* Users can either type `share INDEX` in the command box, or press the `Share` button on the top-right hand corner of the contact card.
  * Justification:
  	* The main idea of SociaLite is individualized online interaction. This new feature aligns with the product concept.
  	* By attaching a URL to the product site, contact sharing will introduce more users to try SociaLite in the future.
  * Highlights:
  	* This feature provides support for both CLI and GUI.

* **New Feature**: Telegram handle support (PR [\#31](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/31))
  * What it does:
  	* Allows users to add/edit a contact's Telegram handle using the `tele/` prefix.
  * Justification:
  	* CRUD of social media handles is an integral feature of SociaLite.
  * Highlights:
  	* Being the first PR of the kind, the implementation of this feature provides a scalable framework for teammates regarding the storing of other social media handles (PR [\#35](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/35), [\#36](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/36), [\#37](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/37)).

* **Altered Feature**: Optional remark (PR [\#56](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/56))
  * What it does:
    * Allows users to create/update a contact without remark.
  * Justification:
    * With Zac's implementation of making social media handles optional (PR [\#42](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/42)), it only makes sense to make remark optional as well.
  * Highlights:
    * Being the first PR of the kind, the implementation of this feature provides a scalable framework for teammates regarding the storing of other social media handles (PR [\#35](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/35), [\#36](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/36), [\#37](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/37)).

* **Code contribution**:
  * Please refer to the [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=david-eom&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByNone&breakdown=true&tabOpen=true&tabType=authorship&tabAuthor=david-eom&tabRepo=AY2122S1-CS2103T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&checkedFileTypes=docs~functional-code~test-code~other).

* **Project management**:
  * Team leader of the SociaLite team, facilitated discussions during weekly team meetings and oversaw the entire project. 
  * Created GitHub organization and set up team project repository.
  * Managed all four milestones ([`v1.1`](https://github.com/AY2122S1-CS2103T-F11-4/tp/milestone/1), [`v1.2`](https://github.com/AY2122S1-CS2103T-F11-4/tp/milestone/1), [`v1.3`](https://github.com/AY2122S1-CS2103T-F11-4/tp/milestone/3), [`v1.4`](https://github.com/AY2122S1-CS2103T-F11-4/tp/milestone/4)) on GitHub and assigned issues to respective team members.
  * Managed four releases ([`v1.3`](https://github.com/AY2122S1-CS2103T-F11-4/tp/releases/tag/v1.3), [`v1.3.1`](https://github.com/AY2122S1-CS2103T-F11-4/tp/releases/tag/v1.3.1), [`v1.3.2`](https://github.com/AY2122S1-CS2103T-F11-4/tp/releases/tag/v1.3.2), [`v1.4`](https://github.com/AY2122S1-CS2103T-F11-4/tp/releases/tag/v1.4)) on GitHub.

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage from 61% to 63% (PR [\#80](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/80), [\#177](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/177)).
  * Make parser throw the correct exception when index overflows e.g. `delete 2147483648` (PR [\#131](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/131)).
  * Fixed multiple issues pertaining overflowing in UI (PR [\#152](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/152)).

* **Documentation**:
  * Developer Guide:
  	* Created the entire set of manual testcases and updated implementation & sequence diagram of command history (PR [#162](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/162)).
  	* Updated `Storage` and `Model` diagram (PR [\#57](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/57)).
  * User Guide:
  	* Updated share command (PR [\#77](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/77)).
  	* Updated pin & unpin command (PR [\#129](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/129)).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#11](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/11), [\#16](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/16), [\#35](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/35), [\#38](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/38), [\#42](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/42), [\#49](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/49), [\#50](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/50), [\#51](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/51), [\#58](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/58), [\#65](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/65), [\#70](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/70), [\#84](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/84), [\#128](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/128), [\#137](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/137), [\#138](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/138), [\#143](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/143), [\#149](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/149), [\#150](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/150), [\#158](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/158), [\#171](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/171)
  * Reported 4 bugs for the peer team during [PE-D](https://github.com/david-eom/ped/issues).

