---
layout: page
title: Zhi Bin's Project Portfolio Page
---

### Project: ContHACKS

contHACKS is a desktop app for Teaching Assistants (TAs) to manage contacts and lessons. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Feature**: Add alias for different commands: [#49](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/49)
  * What it does: Allow users to use different alias to perform the same command.
  * Justification: This provides advanced user with short alias which leads to less typing.
  * Credits: The design is inspired by this [post](https://stackoverflow.com/questions/41494056/add-alias-to-an-enum-in-java) on stackoverflow.

**New Feature**: Add the ability to list lesson: [#122](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/122)
  * What it does: Allow the user to see all lesson stored in contHACKS
  * Justification: After finding the lesson(s) they want, user might want to view the whole list of lesson again
  * Credits: The design structure is inspired from AddressBook Level 3

**New Feature**: Add the ability to clear lesson: [#123](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/123)
  * What it does: Allow the user to clear all lesson stored in contHACKS
  * Justification: Saves the user from deleting lesson one by one if there is a lot of lesson stored in contHACKS
  * Credits: The design structure is inspired from AddressBook Level 3

**New Feature**: Add the ability to edit lesson: [#137](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/137)
  * What it does: Allow the user to edit a lesson stored in contHACKS with the newly provided details
  * Justification: There might be updates in lesson detail
  * Credits: The design structure is inspired from AddressBook Level 3

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=czhi-bin&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=czhi-bin&tabRepo=AY2122S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

**Enhancements to existing features**:
  * Added the ability to sort the contact by their name and module code, but was removed because sorting by module isn't needed anymore, and our team agreed that sorting by name should be done automatically instead of the user having to type a command just to sort. [52](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/52)
  * Add automatic sorting of contact & lesson after any command is executed. [#75](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/75)

**Testing**:
  * Wrote tests for all new and enhanced features:
    * [#52](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/52)
    * [#122](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/122)
    * [#123](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/123)
    * [#137](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/137)

**Documentation**:
  * User Guide:
      * Update command alias, remove sort and fav: [#83](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/83)
      * Remove fav: [#97](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/97)
      * Add more example for various commands: [#147](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/147)
      * Add documentation for the features `listc`, `editc`, `clearc`: [#150](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/150)
  * Developer Guide:
      * Updated the UML diagram and documentation for Parser component[#101](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/101)

**Team-Based Tasks**:
  * Setting up the GitHub team org/repo
    * [#8](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/8)
    * [#39](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/39)
  * Manged releases `v1.2` on GitHub
  * Updating UG that are not specific to a feature
    * [#154](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/154)
    * [#200](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/200)
    * [#201](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/201)

**Community**:
  * PRs reviewed (with non-trivial review comments):
    * [#45](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/45)
    * [#57](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/57)
    * [#77](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/77)
    * [#84](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/84)
    * [#100](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/100)
    * [#131](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/131)
    * [#135](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/135)
    * [#136](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/136)
    * [#139](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/139)
