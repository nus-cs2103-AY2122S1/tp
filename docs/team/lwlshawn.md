---
layout: page
title: Shawn Lim's Portfolio page
---

### Project: SalesNote
SalesNote is a **desktop app for tailors, optimized for use via a Command Line Interface** (CLI), suitable for fast
typists. SalesNote aims to help tailors simplify their administrative tasks, with the main features split between helping to manage:

* Client Information (e.g. Contact details, measurements, notes)
* Tasks to be done
* Sales orders and accounts

### Summary of Contributions


#### Code Contributed
* [Reposense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103T-W08-3%2Ftp&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=lwlshawn&tabRepo=AY2122S1-CS2103T-W08-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements Implemented
* A fair amount of bugfixes. After the PE-D, I took care of about 16 surfaced issues, though many were admittedly small issues that were quickly resolved.
* Much of the implementation of our new classes under the Model package, and their related commands were handled by me. A quick summary here:
    * The Task package, and the UniqueTaskList class
    * The Order package, and the UniqueOrderList class
    * Add/Delete/List/Mark Task commands
    * List/Mark Order commands
    * The functionality that links orders and tasks was also handled by me. This concerns the application logic that ensures
    orders can only be added if the appropriate client is already in SalesNote, that related orders are edited when a client
      is edited, and that related orders and tasks are deleted when a client is deleted.

#### Contributions to the UG
* I was responsible for overall re-writing of the document, to meet the requirements of CS2101. In this process, I took care
of adapting the language to more appropriately meet the requirements of cs2101 by making it more user-centric. The language
now more directly addresses the user, and I fixed several formatting errors that were in the document up to that point.
  
* Some specific examples of what I did to make the guide more user friendly was to include links that allowed the 
user to quickly return to the table of contents conveniently at sensible places. I also included many more examples of
  how to use commands and what behavior to expect, in addition to several images to help the user understand exactly how
  commands were meant to behave
  
* Given that the changes are general and are all over the entire document, I thought it was easiest to provide a link
to the [relevant PR](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/287) 

#### Contributions to the DG
* I wrote up a few sections of the "Implementation" portion of the developer guide, to explain various design decisions
made for the project.

#### Contributions to team-based tasks
* Testcases is probably the thing I contributed the most to, as far as team-based tasks go. Although many of the test-cases
I had to write were very straightforward extensions of existing test-cases in the system, we felt this was tedious but still
  necessary work. Throughout the project, I wrote around 1500 lines of test code, as can be checked in the code contributed 
  section above.
  
* I was responsible for some minor refactoring and renaming of classes as well. Eventually when we decided that we did want
all types of objects to be unique for instance, I changed our TaskList and OrderList to UniqueTaskList and UniqueOrderList 
  respectively, with the other necessary edits to their functionality.

#### Review/mentoring contributions
* PRs reviewed 
  [\#262](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/262), 
  [\#260](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/260), 
  [\#249](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/249), 
  [\#247](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/247), 
  [\#229](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/229), 
  [\#228](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/228), 
  [\#122](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/122), 
  [\#119](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/119), 
  [\#99](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/99),
  [\#87](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/87), 
  [\#74](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/74), 
  [\#41](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/41), 
  [\#36](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/36), 
  [\#35](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/35), 
  [\#34](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/34), 
  [\#32](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/32), 
  [\#26](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/26), 
  [\#22](https://github.com/AY2122S1-CS2103T-W08-3/tp/pull/22)
  


