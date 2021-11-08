---
layout: page
title: Elton's Project Portfolio Page
---

### Project: Notor

Notor aims to help mentors who wish to informally keep tabs on their mentees, by providing a quick way to take notes and
mantain records on their mentees. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is
written in Java, and has about 13 kLoC.

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=EltonGohJH&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=EltonGohJH&tabRepo=AY2122S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Features**:
    * [Group and Subgroup](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/78)
        * What it does: Allows person to be added into group and subgroup.
        * Justification: This feature is needed to allow mentors to put students into different groups such as their classes
            or interests. Through this, it will be easier for mentors to organise and access students data easily.
        * Credits: Yukun helped with ideation of the data structure of groups and subgroups.
    * [Group and Subgroup note](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/127)
        * What it does: Allows users to add note to group and subgroup.
        * Justification: It feels too restrictive to just be able to add notes to a person. We felt that it is very possible for
            mentors to have note written for the whole group and subgroup.
        * Credits: The note model is already done by Elroy, so I can easily adapt it for groups and subgroups.
    * [Group and Subgroup List Views](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/143)
        * What it does: Allows users to view the data of groups/subgroups created.
        * Justification: This feature is needed to allow users to see how to see what information is updated in group.
            For instance, users may want to see what notes and what subgroups are in the group.
        * Credits: Jonathan for the Group, Subgroup card, and the idea on how to implement it.
    * [General Note Mainwindow UI](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/150)
        * What it does: Allows user to see the general note added easily. User will be able to access the reminders/todos
        easily on the UI.
        * Justification: Initially, this large panel was supposed to allow user to highlight any notes that they want to highlight.
        Due to the lack of time, we did not manage to implement that. Even though user cannot highlight any notes,
        the general note UI is very useful and can be used to stored reminders, todos and follow-ups.
        * Credits: Jonathan for the redesign of the UI that allows for this to exist. Elroy for the general note implementation.
    * [Export to CSV](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/138)
        * What it does: Allows user to export their data into CSV format.
        * Justification: At first, we wanted to support importing of CSV but due to the lack of time we only manage to do exporting.
            This feature allows users to export their data into other platforms. For instance, users may want to use Excel to view
            person notes. It also allows user wants to change to a different software to easily do so through the user-friendly CSV format.
        * Credits: OpenCSV library made it very easy to implement this feature.

* **Test Cases**:
    1. Updated some test cases for persons.
    1. Added test cases for groups and subgroups.
* **Documentation**:
    * User Guide:
        1. Feature list & Command Summary (with [Jonathan](halphasx.md) and [Elroy](elroygohjy.md)).
        1. Updated User Guide with updated group and subgroup commands.
        1. Fixed User Guide bugs.
  * Developer Guide:
    1. Updated UML Diagrams for UI and Model Changes.
        1. UI diagram is updated with new fields such as general note.
        1. Model is updated with Group and SubGroup.
    1. Added Activity Diagram that shows how person command is executed.
    1. Updated Use Cases.
    1. Updated Instructions for manual testing.
    1. Helped with the Effort Appendix.
* **Contributions to team-based tasks**:
  1. Set up team repo.
  1. Added OpenCSV library for exporting to CSV.

* **Review contributions:**: Reviewed [23 PRs](https://github.com/AY2122S1-CS2103T-W08-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3AEltonGohJH)
