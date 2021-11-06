---
layout: page
title: Ang Koon Hwee's Project Portfolio Page
---

### Project: TuitionAddressBook (TAB)

Tuition Address Book (TAB) is an all-in-one desktop application for 1-to-1 private home tutors that helps to keep track of students and their respective lesson information. 
TAB has a Command Line Interface (CLI) that allows fast typers to perform tasks quickly complemented with a clean Graphical User Interface (GUI) to facilitate the user's viewing experience.
TAB helps user track all the nitty-gritty details such as fees collection, homework, student contacts so that users will have more time to deliver quality lessons!

Given below are my contributions to the project.

* **New Feature**: Added Undo and Redo command for commands that modify data in TAB.
  * What it does: This feature allows users to undo an accidental command with incorrect/undesired input. Users can also redo an undone command if the undone command was accidental.
  * Justifications: This feature improves the user experience as users can be less afraid to enter an incorrect command or make a mistake. Undo/Redo feature is also a very common feature across modern applications.
  * Highlights: This feature is compatible across all commands that modify data. The implementation of this feature was quite tedious due to AB3's implementation of the `FindCommand`. When executing `undo` on a filtered list,
  it is crucial to ensure that the indexes which the commands are operating on are consistent across all types of list (UniquePersonsList vs FilteredPersonsList). Furthermore, to ensure that the displayed panels in JavaFX would follow the change caused by `undo` command 
  e.g. `undo` a command that edited a student, then execute a `day` command to view calendar. Executing `undo` should result in Center panel to change back to UniquePersonList person list panel. Similar challenges were faced for `redo` command.
  * Credits: For `UndoRedoStack` implementation - [SE-EDU AddressBook 4](https://github.com/nus-cs2103-AY1718S2/addressbook-level4/blob/master/src/main/java/seedu/address/logic/UndoRedoStack.java)

* **New Feature**: Automated fees update feature that updates the fees for lessons that have passed automatically.
  * What it does: This feature is a core feature of TAB, to allow users to not manually keep track of their fees manually, especially for users with multiple students with different lesson rates.
  * Justifications: This feature would significantly reduce our target user's need to keep track of their many students' lesson fees and be a great add-on to their careers. This feature would greatly improve the user experience.
  * Highlights: This feature requires an in-depth understanding of Lesson and AddressBook model as well as Storage to implement all the different fields to make this feature work. Furthermore, the algorithm to calculate the number of lessons that have passed was complicated and have undergone extensive testing by our team.
  * Credits: [Eliana](http://github.com/eeliana) and [Lingshan](http://github.com/lingshanng) helped in contributing to the algorithm to calculate the number of lessons that have passed in `FeesCalculator#getNumOfLessonsSinceLastUpdated()`.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=f13-3&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=angkoonhwee&tabRepo=AY2122S1-CS2103T-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Other Enhancements**:
  * Improve the Command Result by ensuring that display follows the student that command was executed on (Pull request [\#312](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/312))
  * Add Paid Command (Pull request [\#191](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/191))
  * Add Lesson Rates field to lesson (Pull request [\#146](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/146))
  * Refactor Money and its subclasses (Pull request [\#191](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/191))

* **Documentation**:
  * User Guide:
    * Added documentation for the section Managing Lesson Fees and for `undo` and `redo` features. (Pull request [\#314](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/314))
  * Developer Guide:
    * Added implementation details of Fees Calculation and Undo/Redo feature. (Pull requests [\#346](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/346) and [\#142](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/142))
    * Added multiple test cases for Fees Calcalation feature. (Pull request [\#346](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/346))
    * Modified multiple diagrams (e.g. `Logic`)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#25](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/25)
    [\#69](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/69)
    [\#144](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/144)
    [\#208](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/208)
    [\#279](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/279)

#### Contributions to the User Guide

* [Managing Lesson Fees](../UserGuide.md#managing-lesson-fees)
* [Undoing previous commands](../UserGuide.md#undoing-previous-commands-undo)
* [Redoing undone commands](../UserGuide.md#redoing-undone-commands-redo)

#### Contributions to the Developer Guide

Major contributions:<br>
* [Implementation - Undo/redo](../DeveloperGuide.md#undoredo)
* [Implementation - Fees Management](../DeveloperGuide.md#fees-management)
* [Manual Testing - Fees Calculator](../DeveloperGuide.md#fees-calculator)

Minor contributions:<br>
* [Use cases](../DeveloperGuide.md#use-cases) 10 and 11
* Labelling the majority of the diagrams
    
