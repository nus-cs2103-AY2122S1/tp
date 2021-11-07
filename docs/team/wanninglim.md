---
layout: page
title: Wan Ning's Project Portfolio Page
---

### Project: tApp

tApp is a desktop app for managing tutorial groups and personal tasks, optimized for use via a Command Line Interface (CLI). It has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete a student from a list.
  * What it does:
      - Allows the user to delete a student from the student list. 
      - If the student is in an existing group, this command deletes the reference of the student from the group.
  * Justification: This feature improves the product significantly as a student may drop out of the class within the semester.
  * Credits: This feature was built on AB3's `delete` feature, but is edited to ensure all references to the student were deleted as well.

* **New Feature**: Added the ability to mark students' attendance and participation.
  * What it does: 
      - Allows the user to mark multiple students' attendance and participation for one week at a time.
      - The feature also toggles the attendance/participation status when the command is executed twice, giving the user an easy way to reverse their actions.
  * Justification: This feature allows the user (TA) to keep track of whether a student has attended and participated a tutorial class and improve their workflow. A user can also easily rectify their mistakes by executing the command again.
  * Highlights: This feature required being well-versed in how the `Model` and `Logic` components work in tApp. It was hence challenging, as there were multiple design alternatives to consider.
  
* **New Feature**: Added the ability to find students and groups based on partial name inputs.
  * What it does: 
      - Allows the user to search for students and groups based on a partial word.
      - Removes special characters when parsing the commands.
  * Justification: Searching through partial words offers a better search experience for the user. If there is a student's name the TA cannot seem to remember, searching by his/her partial name will narrow down the search results. Furthermore, in the event of a typo, the user will not need to correct their mistakes everytime as the parser automatically removes certain characters that are not allowed.
  * Highlights: This enhancement was challenging, especially to improve the time complexity of the find feature using `String::contains`. 
  * Credits: Transformed AB3's existing find feature to allow a better user experience for search.

* **New Feature**: Added the ability to clear (purge) students list and groups list.
  * What it does:
      - Allows the user to purge the students and groups list after trying out sample data, or when he wants to start a new semester with new students.
      - Clearing students will clear the student and group list, but keep the group names intact (empty groups with no students).
      - Clearing groups will clear the groups list and clear all references in the attribute `groupName` for all students.
  * Justification: This feature improves the product significantly, on top of the `clearAll` feature, as it allows the user to have more flexibility in choosing what data to purge at any time.
  * Highlights: There was a need to properly understand which data needs to be removed when each command was made. 
  * Credits: Transformed AB3's existing clear feature to allow a better user experience on purging unused data.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=wanninglim)

* **Enhancements to existing features**:
  * Made cosmetic changes to GUI to improve the design (Pull request [\#140](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/140)
  * Added a reusable method to parse multiple commands without duplicates [\#228](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/228)
  * Added sample data for groups and tasks [\#243](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/243)
  * Wrote tests for all new features implemented.

* **Documentation**:
  * User Guide:
      * Added documentation for the features `deleteStudent`, `marka`, `markp`, `findStudent`, `findGroup`, `clearStudents`, `clearGroups`
  * Developer Guide:
      * Added implementation details and diagrams for the `findStudent` and `marka` feature.
      * Added use case descriptions for the features `deleteStudent`, `marka`, `markp`, `findStudent`, `findGroup`, `clearStudents`, `clearGroups`
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#238](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/238), [\#150](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/150), [\#99](https://github.com/AY2122S1-CS2103-W14-4/tp/pull/99)

* **Team tasks**:
  * Setting up the GitHub team org/repo + codecov
  * General code enhancements: Renaming product, changing product icon, changing product image
  * Updating user/developer docs: user stories, use cases, manual testing
