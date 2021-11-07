---
layout: page
title: Phua Guan Wei's Project Portfolio Page
---

### Project: Ailurus

Ailurus helps to organize committee accounts for details of their members. It provides users with convenient viewing 
and editing access to all information, thus providing much convenience in their work. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

#### New Features
1. Adding in display windows for Task and Event List.
   * What it does: Allows the user to view all 3 event lists: Event, Member, and Task.
   * Justification: This feature improves the product significantly because the user is required to view and make further commands according to the index shown on the lists.
   * Highlights: There was much difficulty faced in making the cards on the GUI automatically update when the tags, which are shown in flow panes, are being changed due to member/task additions. 
   * Credits: FXML files that are edited with much help from SceneBuilder.

#### Code contributed 
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=gwphua)

#### Enhancements to existing features
1. Automatically updated tags when fields in the cards are changed.
    * Previous GUI implementation does not update the cards immediately after edits to the tags are being done.
    * Related PR's : [\#143](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/143)
2. Changing colours of the tags in GUI.
    * Colour of the tags now represent the attendance of a member, or the status of task completion for a member.
    * Related PR's : [\#153](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/153)
3. Wrote additional tests for existing features to increase code coverage.

#### Documentation
1. Changing occurrences of Address Book to 'Ailurus' on the display side.
    * Related PR's : [\#238](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/238)
2. User Guide write-up
    * Standardise format and order of commands
    * Fix grammatical errors
    * Related PR's : [\#145](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/145), [\#147](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/147)
