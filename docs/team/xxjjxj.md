---
layout: page
title: Ye Xinjian's Project Portfolio Page
---

### Project: Managera

Managera is a **desktop app that provides event organisers with a convenient method of keeping track of upcoming events and the details of their participants.**
It does not handle communication between the event organiser and the participants. This project is adapted from SE-EDU AddressBook - Level 3.

Given below are my contributions to the project.

* **New Model**: `Event`
    * Initialize a new class, `Event`, with basic implementation and its components for the team to work on, for their features.

* **New Feature**: Added ability to edit events.
    * What it does: Allow users to edit event details (name, date and time).
    * Justification: Sometimes when users type too fast, they might have typos. Having to delete the event and re-add might reduce their productivity.
      Moreover, in the real world, events may sometimes get postponed too. Hence, this feature caters to these scenarios.
    * Highlights: This feature affects the choice of data structure our team uses. We planned to use priority queue to store events so the event list are automatically
      sorted in chronological order. However, with edit events made possible, we find that it complicates the implementation to re-order it after each edit. Our team decided to
      provide another feature to provide the option for users to choose whether they want to sort the events or leave it as it is.

* **New Feature**: Added ability to filter events by date and optionally time.
    * What it does: Allow users to filter for events occurring on a specific date and optionally time.
    * Justification: When there are many events on Managera, it is hard to eyeball for events that occurs on a specific day.

* **New Feature**: Added ability to sort events in chronological order.
    * What it does: Allow users to rearrange all existing events in chronological order.
    * Justification: After adding multiple events or ad hoc events, the timeline of events may become too messy for users to keep track of.
      Moreover, when events are edited, the timeline of events may get messed up too. Hence, this feature caters to users who want to sort the events.

  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103T-T10-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=XXJJXJ&tabRepo=AY2122S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
    * Improved features to deal with dependencies more accurately (Pull requests [\#115](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/115/files), [\#126](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/126))
    * Modified `showDetails` and `showParticipants` commands to use Index (Pull request [\#147](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/147))
    * Wrote additional tests for existing features to increase coverage from 66.63% to 71.62% (Pull requests [\#89](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/89))


* **Documentation**:
    * User Guide:
      * Added documentation for the features `sortEvents`, `filterEvents` and `editEvent`.
    * Developer Guide:
      * Added implementation details of the `filterEvents` feature.
      * Updated existing class diagrams to fit our project's structure.
      * Improve the phrasing of use cases.


* **Team based Task**:
    * Set up the GitHub team org/repo
    * Set up tools e.g., GitHub workflow, CodeCov


* **Community**:
    * PRs reviewed (with non-trivial review comments: [\#51](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/51), [\#60](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/60#discussion_r720779380), [\#63](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/63), [\#84](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/84), [\#104](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/104), [\#109](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/109), [\#137](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/137), [\#141](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/141), [\#145](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/145), [\#149](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/149))
    * Contributed to forum discussion (Issue: [184](https://github.com/nus-cs2103-AY2122S1/forum/issues/184#issuecomment-913061544))
  
