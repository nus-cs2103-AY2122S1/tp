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

* **New Feature**: Automated fees update feature that updates the fees for lessons that have passed automatically.
  * What it does: This feature is a core feature of TAB, to allow users to not manually keep track of their fees manually, especially for users with multiple students with different lesson rates.
  * Justifications: This feature would significantly reduce our target user's need to keep track of their many students' lesson fees and be a great add-on to their careers. This feature would greatly improve the user experience.
  * Highlights: This feature automatically calculates the different 