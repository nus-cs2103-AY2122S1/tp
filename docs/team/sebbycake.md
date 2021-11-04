---
layout: page
title: Sebastian's Project Portfolio Page
---

### Project: UNIon

UNIon is a desktop app for organizing various types of contacts, optimized for use for the vast majority of computing students. 
If you are already familiar with Unix commands, then UNIon will be easy for you to use.

Given below are my contributions to the project.

* **New Feature**: Display folder list [#48](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/48)
  * What it does: Allows users to view list of folders create. Data shown: Folder name and list of contact names
  * Justification: By having a component to view the folders that contain the name and the list of contacts, it allows the user to visualize, organize and manage efficiently and effectively with the massive number of contacts they collected while in college.
  * Highlights: This has data dependency with the person's data. If any of the person data is modified, we need to be able to update in the folder list as well. In particular, we need to update the person in the folder that contains the updated person data.

* **New Feature**: Implement command to clear all folders [#108](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/108)
  * What it does: Allows users to clear all folders
  * Justification: With the marching of time, some folders, if not all, become obsolete. This feature comes in handy when they want to remove all folders at one go. This helps to improve user experience as they do not have to delete one by one, especially if he/she has created a significant number of it.
  * Highlights: This command is similar to that of clearing all persons. However, there is one key difference. When clearing the folders, we need to ensure that the list of person/contacts still remain. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=sebbycake)

* **Project management**:
  * Conducted team meetings and drove discussions on Zoom
  * Prepared weekly meeting notes and wrote minutes
  * Reviewed other team member's PRs and gave constructive feedback where necessary
  * Managed day-to-day activities and addressed key issues or challenges brought up by the members

* **Critical bugs fixes**:
  * Contacts' data inconsistency between folder list and contact list due to change(s) to contacts [#107](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/107)
  * Inability to remove folders with contacts populated [#62](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/62)

* **Contributions to team-based tasks**:
  * Setting up [GitHub team org/repo](https://github.com/AY2122S1-CS2103-T16-1/tp) and [Codecov](https://app.codecov.io/gh/AY2122S1-CS2103-T16-1/tp)
  * Changed product icon
  * Documenting project details like description, target audience and value proposition
  
* **Documentation**:
  * User Guide:
    * Added documentation for the feature to view all folders [#29](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/29) and `rm -folders` [#108](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/108)
  * Developer Guide:
    * Added implementation details of the `ls -folders` feature [#85](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/85).

* **Community**:
  * Reported bugs and suggestions for other teams in the class ([examples](https://github.com/sebbycake/ped/issues))
