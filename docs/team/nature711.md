---
layout: page
title: Tianran's Project Portfolio Page
---

### Project: InternSHIP

InternSHIP is a desktop app for university students to manage their internship applications.
InternSHIP has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added Undo and Redo feature
    * What it does: allows the commands that make change to internSHIP application list to be undone/redone (Pull request [#100](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/100).
    * Justification: This feature allows the user to retrieve the information that is accidentally changed by certain commands, such as deleting and editing an application. This prevents the loss of important information and saves the user's effort to manually undo/redo the change.
    * Highlights: This implementation of this feature is achieved by introducing a new class, `VersionedInternship`, which extends from the existing `Internship` class, with an additional list to store the undo/redo history. New classes of testcases are introduced as well.

* **Enhancements to existing features**:
    * Change the implementation of `Requirement` class so that it is stored as a `requirementList` in an application, allowing the user to specify multiple requirements for an application (Pull request [#115](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/115)).
    * Refactor code base to remove all traces of AddressBook, including renaming of classes, methods, files, etc. (Pull request [#59](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/59)).
    * Improve GUI for better display (Pull request [#141](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/141)).
    * Wrote additional tests for existing features to increase test coverage (Pull requests [#234](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/234)).
    * Fixed bugs from Practical Examination Dry Run (Pull request [#215](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/215), Pull request [#219](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/219)).

* **Major contribution in testing**: 
  * Refactor the existing testcases by removing all traces from AB3 and fit into our InternSHIP settings (Pull request [#59](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/59)).
  * Continuous increment and refinement in testcases whenever new features are added (Examples: Pull request [#72](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/72), Pull request [#78](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/78), Pull request [#115](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/115)).
  * Final attempt to increase the project overall code coverage up to 83% (Pull request [#262](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/262), Pull request [#270](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/270)).
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=nature&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `undo` and `redo`
        * Upload screenshots for all features (Pull request [#143](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/143)).
        * Added Acknowledgement section (Pull request [#116](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/116)).
    * Developer Guide:
        * Added implementation details of `undo` and `redo` features (Pull requests [#100](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/100)).
        * Update class, object, sequence, activity diagrams (Pull request [#143](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/143)).
        * Add instructions for manual testings (Pull request [#287](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/287)).

* **Project management and Team tasks**:
    * Actively participated in weekly project meeting.
    * Set up issue tracker with detailed descriptions before each iteration (Examples: Issue [#217](https://github.com/AY2122S1-CS2103T-W17-1/tp/issues/217), Issue [#93](https://github.com/AY2122S1-CS2103T-W17-1/tp/issues/93)).

* **Community**:
    * PRs reviewed with non-trivial review comments (Examples: Review [#231](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/231), Review [#228](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/228), Review [#214](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/214)).
    * Contributed to forum discussions (Examples: Issue [#327](https://github.com/nus-cs2103-AY2122S1/forum/issues/347), Issue [#272](https://github.com/nus-cs2103-AY2122S1/forum/issues/272), Issue [#96](https://github.com/nus-cs2103-AY2122S1/forum/issues/96)).
    * Reported an above-average number of bugs in the PE-D (Examples: Bug [#8](https://github.com/Nature711/ped/issues/8), Bug [#12](https://github.com/Nature711/ped/issues/12), Bug [#11](https://github.com/Nature711/ped/issues/11)).
