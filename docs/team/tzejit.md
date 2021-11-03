---
layout: page
title: Tze Jit's Project Portfolio Page
---

### Project: tApp


Given below are my contributions to the project.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tzejit)

* **Enhancement:** Refactoring of Person class and its associated add/edit commands to Student class and changing the data fields of a Student class.
- What it does:
  - Removes irrelevant fields such as phone number and address, and adds in more data relevant to a Student, such as github repo name, username, student number and email.
  - Updates criteria of being a same student to having either the same student number of email since these are unique.
  - Updates the add/edit command to accept these new fields and check for duplicate students based on the new criteria.
- Justification: This feature improves the product significantly because it allows TAs using it to get a good summary of the relevant Student details, and allows TAs to effectively update these details while automatically checking for any errors such as inputting duplicate students.
- Highlights: This enhancement required extensive refactoring of the entire codebase as the classes of numerous fields deemed irrelevant had to be deleted, and all their dependencies had to be updated.
- Credits: New fields added were based on the existing code for other fields, such as name, which was then edited to accommodate new regex limitations and behaviour.

* **New Feature:** Added view switching functionality.
- What it does:
    - Allows the user to switch displayed content between Students, Tasks or Groups.
- Justification: This feature improves the product significantly because it allows TAs using it to selectively display the content type that they are interested in, and easily access all relevant information using a simple command.
- Highlights: This enhancement required more extensive understanding of AB3 and how the JavaFX framework chooses how certain content are displayed. The implementation was hence challenging as extensive debugging was required to analyse at which point should the function for changing content be implemented.
- Credits: The display panel for each data type was based off that of AB3.

* **New Feature:** Added Group class and associated operations.
  - What it does: 
    - Allows the user to create groups that store group information such as group name, group GitHub link, the particulars of group members, and any additional tags.
    - Allows the user to edit groups to change their group name, Github link, and tags.
    - Allows the user to add/delete students as group members of the group.
    - Allows the user to delete groups.
    - Allows the user to view all groups.
  - Justification: This feature improves the product significantly because TAs using it can sort their students into tp groups, and easily access all information regarding a certain group and its respective members in one place
  - Highlights: This enhancement requires the Group class to hold references to multiple students and required changes to the way data is stored. Implementing the commands relating to adding and removing members, as well as deleting of groups
    that already have existing members was challenging as references to both group and student had to be updated whenever either class was edited by a user. Implementing unit tests for this new class and its associated operations was also challenging as many new stubs were required to mimic group functionality
    and existing stubs that created Students also needed to be modified to accommodate the new changes made. Furthermore, modifying the GUI to display groups also required a more in-depth understanding of JavaFX to nest scrollable content.
  - Credits: The Group class was based on the Student class of AB3, but was heavily modified to fit the needs of the application. The Tag class used for groups was adapted completely from the one used in AB3.
  


