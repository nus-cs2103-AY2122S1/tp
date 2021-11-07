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
  

## Contributions to the DG

###`task` and `order` package
This section describes the implementation of the `task` and `order` packages in the application. These two packages are
similar in functionality to the `person` package, now allowing the user to track tasks and orders. Below is a diagram
showing the partial implementation of these packages in the application:

![`Updated Model Diagram`](images/UpdatedModelClassDiagram.png)

`OrderList` and `TaskList` manage `Order` and `Task` objects, in the same way a `UniquePersonList` manages `Person`
objects, and there are a few significant points about this implementation.

The first is the distinction between the `Person` class, and the `Customer` class. Since every order is made by
a customer, and the `Person` class is used to track customers, we initially considered linking the two classes, and tying
every `Order` to a `Person`. However, the issue with this is that we did not want deleting of a `Person` to affect
sales records, which should continue to show all completed orders. We thus decided instead to create the `Customer`
class which essentially serves as a field for the `Order` class, implementing validity checks for the input, similar to
how the `Name` field works for the `Person` class.

The next note is that a `UniquePersonList` has a `AddressBook` wrapper that contains other functionalities needed in
the application (e.g. storage related functions). Our Implementation intends to mirror this with a `TaskBook` and
`OrderBook` wrapper around `TaskList` and `OrderList` respectively, but this was not handled by me, and hence these
were omitted from the diagram above.

Finally, as mentioned partially above, the `Amount` `Customer` `Date` and `Label` classes are what handle checking the
validity of fields, similar to the implementation in the associated classes for Person, and also respect a whole-part
relationship. The validity checking in all cases was implemented using regular expressions, and they respect the
following guarantees:

`Amount` Begins with 1 or more numbers, followed optionally by a block that consists of a '.' followed by 1 or 2 numbers.

`Customer` Blocks of 1 or more alphanumeric characters, separated by at most one space.

`Date` `Label` Nonempty block of alphanumeric characters of length at most 100 characters. We felt this was a reasonable
length for both fields, and would guarantee the UI display worked the way we intended.'

### Addressing feature flaws

A small and related task I addressed was input validation for customers, and adjusting the way we treated equality
between person objects. The original AB3 treated two people as equal only if their names were spelt exactly the same,
with this being case-sensitive. When we discussed this as a group, we decided that multiple clients having the exact same name was rare
enough that this notion of equality made sense. However, we felt it should apply regardless of case, i.e. john doe
should be recognised as the same person as JOHN DOE. I updated the implementation to take care of this, and also changed
the input validation for `Name` to allow at most one space between blocks of characters.

### Implementing commands

Lastly, I implemented several commands related to the `Task` and `Order` classes. These are fairly self-explanatory,
and their implementation closely mirrors that of similar commands for the `Person` class. The exception is the marktask
and markorder commands, which allow the user to mark tasks and orders as completed. The list of implemented commands
is below:

* addtask
* deletetask
* listtasks
* marktask
* markorder
