---
layout: page
title: Wilfred Wong Kang Chee's Project Portfolio Page
---

### Project: TeachBook

TeachBook is a desktop app made for primary and secondary school teachers to manage student contacts. It is optimized for
use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is 
written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: Did up the layout of UI components used for the final version of our app. (Pull request [\#52](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/52))
  * What it does: Placed the ClassListPanel and the StudentListPanel side by side such that they can be seen on the main window of our app.
  * Justification: It is such that users can easily reference the classes and students.

* **New Feature 2**: Made the class list to highlight the class according to the which class is being selected. (Pull request [\#90](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/90))
  * What it does: It automatically changes the highlight to the currently selected class using the `select` command.
  * Justification: As we don't want to allow users to select the class by mouseclick, they can only select it using the `select` command. So the class list panel needs to listen to the change in selected class and highlight the correct one accordingly.

* **New Feature 3**: Implemented checkbox for the mark/unmark command for the UI. (Pull request [\#161](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/161))
  * What it does: It is a checkbox that shows the users whether a particular at present or not.
  * Justification: This feature provides a visual cue to the users so that they can easily look through the list to see who is absent or present from the main window.
  * Highlights: This enhancement makes the app looks nicer and more usable as there is colours (red for absent and green for present) added to the checkbox.

<div style="page-break-after: always;"></div>
* **New Feature 4**: Made a small popup tab that appears when user uses the `list all` command. (Pull request [\#178](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/178))
  * What it does: It shows the user which class each student is from.
  * Justification: It is because when the `list all` command is called, there won't be any selected class anymore. We as a team also decided not to make the class to be always shown on the student card, as it is redundant. Thus, for the user to know which class the students are from, this feature is needed.
  * Highlights: There is a small tag box that pop up at the top right of the student card.

* **New Feature 5**: Made class card to show number of students in each class. (Pull request [\#98](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/98))
  * What it does: It shows the total number of students in each class and displays it on the class card.
  * Justification: This feature is better for users so that they can know the total number of students in each class with in just a glimpse
  * Highlight: It updates accordingly everytime a student is added or deleted.

* **Bug Fixed**: Fixed problem that the `clear` command does not clear the student list. (Pull request [\#135](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/135))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=wilfredwongkc&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false)

* **Project management**:
  * Create telegram chat for team
  * Created GitHub organisation for my team
  * Set up team repo and did other things such as forking, enabling issue tracker and GitHub actions, and creating team PR
  * Set up the project website for our project
  * Designed UI mockup for the team
  * Did video recording for demo of our app (v1.3) and uploaded it onto YouTube
  * Create meeting minutes documents on Microsoft Teams for our weekly meetings
  * Facilitated team's submissions to professors on Microsoft Teams

<div style="page-break-after: always;"></div>
* **Documentation**:
  * User Guide:
    * Added the introduction and the "how to use this guide"
    * Added sub-headers for the features and reorder the list
    * Added "back to top" button throughout the UG
    * Did up the Graphical User Interface section of the UG and created an image to explain the different portions of the UI
    * Added `select`, `remark`, `mark`, `unmark`, `undo` and `redo` command feature into UG
    * Added 2 FAQs
    * Fixed bugs and incorporate suggestions given by reviewers given during PE-D
  * Developer Guide:
    * Did up activity diagram for `addClass`
    * Added the product scope 
    * Maintained the UI Component part under the design portion
    * Added user case for `add`, `delete` and `edit` commands
    * Added manual test case for `add`, `addClass` and `find` commands

* **Community**:
  * Discussed with team on the progress and direction of the project.
  * Tested and gave feedbacks to team profBook on their app for PE-D.

  
