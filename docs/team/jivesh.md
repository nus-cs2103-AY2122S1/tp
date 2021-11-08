---
layout: page
title: Jivesh Mohan's Project Portfolio Page
---

### Project: ePoch

ePoch is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
It has been designed for the busy NUS student, allowing the user to create contacts for persons and to create CCAs, link those persons and CCAs together, and to create periodic reminders for those CCAs.

My contributions to the project are listed below:
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t14-2&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jivesh&tabRepo=AY2122S1-CS2103-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements implemented**:
  * Added `editr` command to edit reminders
  * Added `deletec` command to delete CCAs
  * Made every `find` command show associated objects
    * `findc` shows matching CCAs, people in the matching CCAs, and reminders attached to those CCAs
    * `findp` shows the CCA the matching people are in as well as the reminders attached to those CCAS
    * `findr` also shows the CCA attached to that reminder
  * Added custom predicates for use in `findc`, `findp` and `findr`
  * Added rounded edges for everything in UI

* **Documentation**:
  * User Guide:
    * Updated adding command in feature section of UG [link here](https://github.com/AY2122S1-CS2103-T14-2/tp/issues/15)
    * Did cosmetic tweaks to existing documentation of a few features
    * Enhanced documentation of `find` and `...p` commands
  * Developer Guide:
    * Added NFRs in DG [link here](https://github.com/AY2122S1-CS2103-T14-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Ajivesh#:~:text=1-,Add%20NFRs%20for%20DG,-priority.High)
    * Added UML diagram for models [here](https://github.com/AY2122S1-CS2103-T14-2/tp/pull/296)

* **Tools**:
  * Made a [project](https://github.com/orgs/AY2122S1-CS2103-T14-2/projects/1) on Github to track issues
