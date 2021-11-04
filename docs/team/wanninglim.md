---
layout: page
title: Wan Ning's Project Portfolio Page
---

### Project: tApp

tApp is a desktop app for managing tutorial groups and personal tasks, optimized for use via a Command Line Interface (CLI). It has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete a student from a list.
  * What it does: 
  * Justification:
  * Highlights: 
  * Credits: 

* **New Feature**: Added the ability to mark students' attendance and participation.
  * What it does: allows the user to mark multiple students' attendance and participation for one week at a time. The feature also toggles the attendance/participation status when the command is executed twice, giving the user an easy way to reverse their actions.
  * Justification: This feature allows the user (TA) to keep track of whether a student has attended and participated a tutorial class and improve their workflow. A user can also easily rectify their mistakes by executing the command again.
  * Highlights: ??
  
* **New Feature**: Added the ability to find students and groups based on partial name inputs.
  * What it does: allows the user to mark multiple students' attendance and participation for one week at a time. The feature also toggles the attendance/participation status when the command is executed twice, giving the user an easy way to reverse their actions.
  * Justification: This feature allows the user (TA) to keep track of whether a student has attended and participated a tutorial class and improve their workflow. A user can also easily rectify their mistakes by executing the command again.
  * Highlights: ??
  * Credits: Transformed AB3's existing find feature to allow a better user experience on search.

* **New Feature**: Added the ability to clear (purge) students list and groups list.
  * What it does:
  * Justification:
  * Highlights: 
  * Credits: Transformed AB3's existing clear feature to allow a better user experience on purging unused data.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=wanninglim)

* **Enhancements to existing features**:
  * Made cosmetic changes to GUI (Pull request [\#140](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/140))
  * Added a reusable method to parse multiple commands without duplicates
  * Added sample data for groups and tasks [\#243]()

* **Documentation**:
  * User Guide:
      * Added documentation for the features `deleteStudent`, `marka`, `markp`, `findStudent`, `findGroup`, `clearStudents`, `clearGroups` [\#72]()
  * Developer Guide:
      * Added implementation details of the `findStudent`/`findGroups` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#238](), [\#150](), [\#99]()

* **Team tasks**:
  * Setting up the GitHub team org/repo + codecov
  * General code enhancements: Renaming product, changing product icon, changing product image
  * Updating user/developer docs: user stories, use cases
