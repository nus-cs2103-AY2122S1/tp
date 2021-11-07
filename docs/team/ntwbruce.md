---
layout: page
title: Bruce Ng's Project Portfolio Page
---

### Project: Managera

Managera is a **desktop app that provides event organisers with a convenient method of keeping track of upcoming events and the details of their participants.** It does not handle communication between the event organiser and the participants. This project is adapted from SE-EDU AddressBook - Level 3.

Given below are my contributions to the project.

* **New Feature**: Added ability to display the details of an Event.
    * What it does: Allow users to obtain all the details of a desired Event.
    * Justification: Users will often need to check the specific date and time of individual Events, especially if they are keeping track of many Events and can't remember the details of each one offhand.

* **New Feature**: Added ability to display the list of Participants of an Event.
    * What it does: Allow users to obtain a list of all the Participants of a desired Event.
    * Justification: Users will often need to obtain a list of Participants for individual Events (e.g. nominal roll for printing purposes, or to check the number of Participants), especially if they are keeping track of many Events and can't remember all the Participants for an Event offhand.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103T-T10-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=ntwbruce&tabRepo=AY2122S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Created ParticipantCard and decouple PersonCard (Pull Request [\#59](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/59))
    * Updated ParticipantId to include numbering, for differentiation of Participants with the same alphabetical Participant ID. (Pull Requests [\#74](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/74), [\#75](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/75) and [\#80](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/80))
    * Wrote additional tests for existing features, improving test coverage by 4.93% (Pull Requests [\#85](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/85), [\#88](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/88), [\#93](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/93))
    * Modified EditCommand to be able to edit Participant birthdates (Pull request [\#109](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/109))
    * Modified `enroll` and `expel` commands to use indexes (Pull Request [\#141](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/141))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `showDetails` and `showParticipants`.
    * Developer Guide:
        * Added implementation details of the `showDetails` feature.
        * Added Use Cases section to Appendix: Requirements.

* **Community**:
    * PRs reviewed (with non-trivial review comments): 
        [\#51](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/51)
        [\#154](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/154)
        [\#227](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/227)

