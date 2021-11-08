---
layout: page
title: Ravi Ananya's Project Portfolio Page
---
### Project: WedFast

WedFast is a program that aims to help users plan their wedding efficiently by organising
the contacts of all the parties involved. These contacts include details such as pricing, attendance status, name, email, 
address, and phone number. The program is a desktop based application created using JavaFx GUI, 
and users work with the CLI to input/manage their contact details.

Given below are my contributions to the project.
* **New Feature**: Add Price component to every Person.
  * What it does: Allows the user to include the pricing that a person's service amounts to when adding/editing
    amounts to, and displays this price in the program.
  * Justification: For parties such as caterers, photographers, attire organisers, noting their price points 
    is a critical aspect of wedding planning that should not be overlooked and improves the user's decision-making.
  * Highlights: Adding this feature affected the Person object, and almost all the different types of commands,
    which make use of Person objects. Therefore, several classes had to be updated, as well as their test classes.
  * Credit: structure of the code was based on the other Person components written for the original AB3 program.   

* **New Feature**: Add Info component to every Person.
  * What it does: Lets the user to include any important information for each contact when adding/editing them. Displays
    this information in the program.
  * Justification: For example, if the contact can only communicate with the user during a certain period of the day,
    adding the information will prevent the user from forgetting it.
  * As with the Price component, adding the Info component affected the Person object
    and also required tweaking other classes involved - rather tedious.
  * Credit: same as for Price component.

* **New Feature**: Add Status component to every Person.
  * What it does: Permits the user to add/update the status of each contact as frequently as they please, to verify
    whether they are attending/involved in the wedding. Visibly displays Green, Yellow or Red on the text to indicate 
    confirmed, pending or declined status.
  * Justification: Allows the user to know at all times who is and is not attending and keep their priorities in check.
  * Highlights and Credit: same as for Price and Info Component

* **New Feature**: Add undo command
  * What it does: This command helps to undo the most previous command. Summary of commands that can be undone: 
    add, delete (by index, name or tag), clear, group, and edit.
  * Benefit: Any accidental command implemented can be undone quickly
  * Highlights: Implementing the undo command meant having to understand how to revert each command it works for. Commands
    such as `delete` had 3 methods of deletion, (name, info and tag), so undoing each of these subcommands also had to be taken into
    consideration.
  * Credit: The general structure UndoCommand class was adopted from various other Command subclasses.
    
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=w10-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Banana3021&tabRepo=AY2122S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **Project management**:
    * Managed releases `v1.2.1` - `v1.3` (2 releases) on GitHub

* **Enhancements to Existing Features**:
    * Altered the Person class/objects to include the newer components. PR: [\#42](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/42)
    * Rewrote part of the newer delete features, such as name and tag. PR: [\#179](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/179)   
    * Wrote additional JUnit tests for the undo command to increase coverage by 2.11%. PR: [\#194](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/194)

* **Documentation**:
  * User Guide:
     * Made updates to the `list`, `delete` and `undo` commands during the early stages of implementation. PR:  [\#32](//github.com/AY2122S1-CS2103T-W10-4/tp/pull/32)
     * Added new information and images to the `add` and `undo` command. PR: [\#102](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/102)
     * Changed all display images of the program for examples of `add`,`group`,`find`,`delete`,`report` and`undo`.  PR: [\#221](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/221)

  * Developer Guide:  
     * Modified the user stories and glossary. PR: [\#21](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/21)
     * Added more glossary terms. PR: [\#36](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/36)
     * Updated the information and UML diagrams (sequence, activity and object diagrams)
       for the `undo` command, and wrote out its testcases. PR: [\#212](https://github.com/AY2122S1-CS2103T-W10-4/tp/pull/212)
       
* **Contributions to Team-Based Tasks**:
     * Updating user/developer docs that are not specific to a feature, i.e writing the user stories, glossary
       and editing diagrams and content in the developer guide.
     * Maintaining the issue tracker
     * Setting up milestone

* **Community**:
     * Reported several bugs for other teams in the class (Examples: 
       [\#1](https://github.com/Banana3021/ped/issues/1),
       [\#2](https://github.com/Banana3021/ped/issues/2),
       [\#3](https://github.com/Banana3021/ped/issues/3),
       [\#5](https://github.com/Banana3021/ped/issues/5),
       [\#6](https://github.com/Banana3021/ped/issues/6),
       [\#7](https://github.com/Banana3021/ped/issues/7),
       [\#8](https://github.com/Banana3021/ped/issues/8),
       [\#10](https://github.com/Banana3021/ped/issues/10),
       [\#11](https://github.com/Banana3021/ped/issues/11),
       [\#12](https://github.com/Banana3021/ped/issues/12),
       [\#13](https://github.com/Banana3021/ped/issues/13),
       [\#14](https://github.com/Banana3021/ped/issues/14))
