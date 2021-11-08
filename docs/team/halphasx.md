---
layout: page
title: Jonathan's Project Portfolio Page
---

### Project: Notor

Notor aims to help mentors who wish to informally keep tabs on their mentees, by providing a quick way to take notes and
mantain records on their mentees. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is
written in Java, and has about 13 kLoC.

## My Contributions

### **Code contributed**

My code contributed can be found at [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?tabOpen=true&tabType=authorship&tabAuthor=HalphasX&tabRepo=AY2122S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipFileTypes=docs~functional-code~test-code)

### **New Features**

* [View Pane UI](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/103)
  * The UI now have a split pane, the right side for the list pane (to show the lists of person, group, and subgroup),
    and the left side for the view pane (to view general notes).
  * Justification: The UI for Notor should be able to support ease of access on viewing notes for mentor, and that includes the general notes.
  To be able to view the notes easily, we want to implement a view pane to show the notes that mentors have.
  * Modified the main window UI to support viewing pane, so that mentors can easily view the notes they want.
  
* [Group Card UI and Subgroup Card and List Panels UI](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/143)
  * The UI now will support group and subgroup card templates. In group card, you can check the number of subgroups and number of persons.
    The subgroup card stores the data of the parent group, and the number of person in that subgroup. Both groups will have their own notes
    to describe their groups.
  * Justification: This is to support the group and subgroup so that it can be viewed in the main UI, so that they can view
  the list of groups and subgroups on the list pane.
  * The UI concept is adapted from the person card from AB3.

* [Command for listing persons in a group](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/147)
  * The list command now supports listing people in a particular group. It allows users to view the people in a group they organized.
    We wanted mentors to have an easy access to their mentees without scrolling through the general person list, hence we
    added this feature to support these functionalities.
  * Justification: Mentors should be able to access their mentees easily without having to scroll through each page to view their students.
    By having the command to list people in a group, the mentors can filter out the groups that they are not currently interested in, and view the 
    filtered list easier.


### **Enhancements to existing features**:

* [GUI Improvements on Main Window](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/141/commits)
  * The GUI now supports view commands, and can also list out all the groups and subgroups in each group through the list and find commands.
    We improved the color so that it suits Notor's color scheme and increases navigability so that you can navigate through the pages easier.
  * Justification: The UI for Notor should be easy to understand to mentors, and each feature's functionalities should be clear and concise.
    Hence, the improvements on the UI are to adhere to this condition.
    
* [Redesign Person Card UI](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/141/commits)
  * Person Card now supports groups and subgroups functionalities, as well as different color scheme to suit Notor.
  * Justification: The UI for person card should be redesigned to match up Notor's current main window, so that
  the UI does not look out of place and matches the current needs of the mentors.

### **Project management**:
  * Handled most of the UI changes and the new UI design.

## **Documentation**:
    * Developer Guide: NFRs and Glossary, and FXML handling
    * User Guide: Feature list & Command Summary(with [Elton](eltongohjh.md))
                  Revised the whole UG to adhere to the current commands,
                  Fixes :
                  - Filled out missing commands
                  - Added some tips, caution, and information boxes to improve the UG readability
                  - Fixed the structures of the contents (FAQ and Command Summary are flipped)
                  - Fixed the links of the UG
                  - Fixed typos
                  - Completed some descriptions regarding groups and subgroups
                  - Included some screenshots for visual cues, and updated UI to match up Notor



## **Community**:
  As I have less experience with Java and I am still adapting to the workflow, I usually give teammates my suggestion and tried
  to help as much as I can on suggestions on certain features. As I was in charge of UI, I also coordinated with my fellow teammates
  regarding the commands as well as the supported features.
  I asked for help on handling CI testing in the forums(e.g. https://github.com/nus-cs2103-AY2122S1/forum/issues/323 ,  https://github.com/nus-cs2103-AY2122S1/forum/issues/363)

## **Tools**: I used IntelliJ and Git Bash for my main source code window and my terminal window, 
  and to create the UI, I used Scenebuilder.