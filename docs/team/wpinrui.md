---
layout: page
title: Ivan's Project Portfolio Page
---

### Project: TutorAid
<!-- PPP format and structure adapted from
https://github.com/samuelfangjw/tp/blob/master/docs/team/samuelfangjw.md
-->
TutorAid is a desktop application used for helping tech-savvy private tutors, who have busy schedules / many students, to keep track of each students' details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.
* **Card-like UI elements**:
  * Added the ability to view lessons in a panel in TutorAid [(PR #106)](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/106)
  * Justification: This feature is important for user functionality as having a separate panel for lessons allows them to manage lessons and students simultaneously.
* **`list -a` command to show all fields while viewing a list of students and lessons [(PR #87)](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/87)**
  * `list` command alone hides fields [(PR #70)](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/70)
  * Justification: Having a list command that can hide details while listing students and lessons allows users to see more entries on a single screen, while having a list command that can show all details can allow users to look at fields of each of their students / lessons. 
* **Edit [student](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/86) / [lesson](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/118) commands**
  * Justification: Being able to edit is a feature that makes it a lot more convenient for users to change fields for students / lessons. Without this feature, they would have to delete the original entry and create a new one which is a lot of trouble, especially if only a single field has changed.
* **Other features and enhancements to existing features**:
  * Add lesson to student / Add student to lesson: fixed a bug where editing the student or lesson would cause the two entities to go out of sync [(PR #143)](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/143)
  * Progress: Improve formatting as it appears on the Student Panel [(PR #142)](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/142)
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=wpinrui&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17).
* **Project management**:
  * Facilitated weekly team meetings
  * Managed deadlines and deliverables
  * Contributed to the issue tracker and milestones
  * Managed some releases on GitHub
* **Documentation**:
  * User Guide:
    * Created Introduction and About sections, including documentation of UI elements and command structure
    * Added documentation for the features `edit -s` and `edit -l`
    * Re-organised feature reference
    * Re-organised command summary
  * Developer Guide:
    * Target user profile, value proposition, user stories, use cases, non-functional requirements, glossary
    * Architecture notes for Model components
    * Implementation of card-like UI elements and related UML diagrams
* Community:
  * PRs reviewed with non-trivial review comments (some examples: [1](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/125#pullrequestreview-788810284), [2](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/90#discussion_r730196584), [3](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/121#discussion_r737666344), [4](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/60#discussion_r724660187)).
  * Contributed to forum discussions (some examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/191), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/148))
  * Answered some queries from peers (some examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/159#issuecomment-913186432), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/192#issuecomment-914275836))
