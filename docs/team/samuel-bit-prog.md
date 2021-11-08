---
layout: page
title: Samuel Lau Yi Ren's Project Portfolio Page
---

### Project: Ailurus

Ailurus helps organizing committees account for details of their members. 
It provides users with convenient viewing and editing access to all information, thus providing much convenience in their work.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

#### New Features
1. Implemented event and module.
  * What it does: Event contains members, name, date and allows us to mark and store the attendance of members. It can be saved and stored in json like member.
    Event extends module. Module is an abstract class for event, task and member.
  * Justification: This feature improves the product by allowing users to plan and organize events for their members.
  * Highlights: This enhancement affects how the GUI will be presented, and we now have to accommodate the presence of events in the model, storage and future commands. It required an in-depth analysis of design alternatives such as whether to have members have events as a field or events to have a set of members.
  * Notable PRs: [PR#99](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/99), [PR#108](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/108), [PR#133](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/133)

2. Implemented filter attributes to list for task and member.
  * What it does: Members can be listed by event and attendance as filters. Task list for a member can be filtered by status or if overdue.
  * Justification: This feature improves the product by making it easier to search and view tasks or members through filters. It aids with administrative matters.
  * Highlights: The enhancement affects the lists displayed in the GUI, only showing the matches. Commands will be required for which to show the full unfiltered list again. 
  * Notable PRs: [PR#154](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/154)

3. Helped with the implementation of editing of event.
  * What it does: Events in Ailurus can be edited to have their names, members and date changed.
  * Justification: This feature improves the product by allowing changes in existing events.
  * Highlights: The enhancement only changes what is specified. If only name is specified, only that will change for the event. Command can also be used to clear an event of all its members.
  * Notable PRs: [PR#243](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/243)

4. Implement the efind command.
  * What it does: Events can be searched for by keywords in their names. Keywords do not have to be exact matches and do not care for alphabetical-casing.
  * Justification: This feature improves the product by making it easier to search and find events by name even if you only know part of the name.
  * Highlights: The enhancement affects the lists displayed in the GUI, only showing the matches. Commands to list the entire list again can be used after the efind command.
  * Notable PRs: [PR#155](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/155)

#### Code contributed ([RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=samuel-bit-prog))

#### Project management
1. Opened issues that sought to properly track the progress of adding and enhancing features.
2. Reviewed and approved team members' PRs for merging (more details in the [Community](#community) section)

#### Enhancements to existing features
1. Changed the implementation of find command to be renamed as mfind and to allow partial matches without caring about alphabetical-casing.
  * Previously find command has to rely on exact matches when searching for keywords. No partial matches were allowed.
  * Notable PR: [PR#98](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/98)

#### Documentation
* Developer guide
  1. Add implementation of `eadd` command and `emark` command. [PR#282](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/282)
  2. Add more into storage and model sections and modify diagrams. ([PR#133](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/133), [PR#279](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/279))
  3. Add manual testing guide for event. [PR#279](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/279)
  4. Add target user profile, value proposition and user stories. ([PR#57](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/57), [PR#63](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/63))
* User guide
  1. Add, edited and/or updated commands, and their usage in the user guide. ([PR#163](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/163), [PR#166](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/166))
  2. Add user guide to the commands used for deleting. [PR#43](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/43)

#### Community
1. Reviewed PRs of fellow teammates. (Some with non-trivial review comments, E.g. ([PR#253](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/253), [PR#140](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/140)))
2. Reported bugs and suggestions for other teams during [PE-D].(https://github.com/Samuel-bit-prog/ped/issues)
