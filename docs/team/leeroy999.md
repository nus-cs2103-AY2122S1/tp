---
layout: page
title: Leeroy Liu's Project Portfolio Page
---

### Project: Ailurus

Ailurus helps organizing committees account for details of their members. 
It provides users with convenient viewing and editing access to all information, thus providing much convenience in their work.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

#### New Features
* Added CRUD for events using appropriate commands, and find event by name
    * What it does: allows users to add, delete, edit and list events that are registered in Ailurus, and search for a specific event name.
    * Justification: This features allows users to group members in events with date included, to keep track of who are supposed to attend specific events and subsequently mark their attendance if necessary. This is useful for small and large scale events and possibly meetings and conferences as well in a work setting, and allow easier organisation.
* Added marking and unmarking of attendance for members in an event.
  * What is does: allows users to mark a member as present or absent for an event that they are participating in. Also allows marking all members as present in an event.
  * Justification: Marking all as present is a quick way to mark the attendance of all participants, and only unmark the few that are not present. Allows accounting for members who attended, such as in a meeting or conference.
* Added commands to add and delete individual members from an event.
  * Justification: On top of adding new events which can already have members, adding and deleting individual members from participation list reduce the need to create and delete event, and make it easy to amend participation list.
* Overhauled events and logic of Ailurus of above features: ([#149](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/149))
* Enhanced GUI for 1/3 spacings and centralised headers ([#233](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/233))

#### Code contributed
* [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=leeroy999)

#### Project management
* Team lead: manage project contributions and merging of approved pull requests, maintaining Git repository and issue tracking.

#### Enhancements to existing features
* Enhanced listing of members to allow listing of all members in an event. ([#110](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/110))
  * Justification: This feature allows users to check the list of members in an event so that deletion and marking of attendance for members in the event is possible using the appropriate commands, excluding those not in the event to prevent error in selecting wrong members to mark or delete.
* Refactor Person to Member and the relevant commands ([#93](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/93), [#107](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/107))

#### Documentation
* Developer Guide
  * Added use cases ([#56](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/56))
  * Added manual test instructions for member-related new features, and added efforts for the team in appendix ([#280](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/280))
  * Added [ailurusLogo.png](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/ailurusLogo.png), [Ui.png](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/Ui.png)
  * Added / contributed diagrams: [UiClassDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/UiClassDiagram.png), [MdelSequenceDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/member/MdelSequenceDiagram.png), [CommandActivityDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/CommandActivityDiagram.png), [MlistSequenceDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/member/MlistSequenceDiagram.png), [MlistExecutionSequenceDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/member/MlistExecutionSequenceDiagram.png), [EmaddSequenceDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/event/EmaddSequenceDiagram.png), [EmaddExecutionSequenceDiagram](https://ay2122s1-cs2103t-t15-2.github.io/tp/images/event/EmaddExecutionSequenceDiagram.png)
* User Guide
  * Updated docs to suit our Ailurus app ([#38](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/38), [#67](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/67), [#81](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/81))
  * Style and refactor User Guide with Glossary table and Navigation of UI ([#253](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/253))
    * Justification: For easier compilation of all parameters and terminologies that may be confusing for users, and give their constraints directly to the user. Acts as an easy summary table to look out for in case of errors in data entered.
  * Added Navigation of UI ([#271](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/271))

#### Community
* PRs reviewed: [Github Link](https://github.com/AY2122S1-CS2103T-T15-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aleeroy999+)
* Fixed bugs for [Ailurus's PE-D](https://github.com/AY2122S1-CS2103T-T15-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Aleeroy999+%5BPE-D%5D) such as invalid sample data. (in [#233](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/233))
* Submitted issues for bugs and suggestions for [PE-D](https://github.com/leeroy999/ped)

#### Tools
* Continuous Integration: build.gradle ([#132](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/132))
* Codecov: ([#2](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/2))
