---
layout: page
title: Saketh's Project Portfolio Page
---

### Project: CallMeMaybe(CMM)

CallMeMaybe is a desktop application developed for freelance telemarketers.
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **Features worked on:**
  * **Display command**: Added the ability for users to display additional details about a chosen contact.
      * What it does: Allows the user to view additional information about a particular contact. The additional details include things like
        age, called status, gender, address, interests. The feature allows them to view details of one contact at a time chosen based on index. 
      * Justification: This feature improves the product significantly because often times as a telemarketer you are looking for more information 
        about a particular contact rather than just the name and phone number. The additional information such as age, gender etc. can play a role
        in the telemarketer deciding how to approach calling the particular contact and how to handle the call. 
      * Difficulties: It was rather difficult to include this new functionality, since it required a lot of change in the UI as well as the Logic.
        However, through a team effort we were able to implement this feature. It was integral that this function was not just a stand-alone feature,
        but had to be modified such that it updated the details when other functions were called. It was quite difficult to write tests for it, and 
        catch errors in an appropriate manner. 


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=loose-bus-change&tabRepo=AY2122S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed project milestones on GitHub


<div style="page-break-after: always;"></div>


* **Team-based tasks**: Updating user/developer docs that are not specific to a feature
    * Modifying target user profile. (Pull request [#129](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/129))
    * Modifying use cases for project. (Pull request [#196](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/196))
    * Editing final documentation (Pull request [#196](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/196))


* **Enhancements to existing features**:
    * Modified the original `add` command for the v1.2 release. (Pull request [#45](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/45))
    * Modified the implementation of the `clear` command to change the updated UI of `display`. (Pull request [#192](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/182))
    * Modified error messages of existing functions to provide more clarity. (Pull request [#45](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/192))
    * Changed the way in which an input index is parsed. (Pull request [#192](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/192))


* **Community:** 
    * Sample of PR reviewed (with non-trivial comments): (Pull request [#194](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/194))
    * Reported bugs for other teams: [Bugs found in PE-D](https://github.com/loose-bus-change/ped/issues)


* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`, `display`, `list`, `done`, `exit`
          (Pull requests [#13](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/13), [#45](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/45),
          [#107](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/107))
        * Contributed to the 'navigating user guide' section and restructured the UG (Pull request [#196](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/196))
    * Developer Guide:
        * Added implementation details of the `display` feature. (Pull request [#129](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/129))
        * Edited the use cases, testing information (Pull request [#129](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/129))
        * Edited the target user profile, user stories (Pull request [#196](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/196))
        * Diagrams drawn: Display Activity Diagram  and Display Sequence Diagram
