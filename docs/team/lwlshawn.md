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

####Implementation
The implementation of both of these packages is largely similar to the `person` package. In the original AB3, there is a
`person` class, stored in a `UniquePersonList` that handles list operations, further stored in a `AddressBook` that handled
other utility functions like data management.

Following this structure and outline, we had a `task` class, stored in a `UniqueTaskList` stored in a `TaskBook`, and a
`order` class, stored in a `UniqueOrderList` stored in a `OrderBook`. Below is an updated model diagram reflecting these
changes:

![`Updated Model Diagram`](images/ModelClassDiagram.png)

Similar to the `Person` class, the `Task` and `Order` classes have fields, as seen here:

![`Order Class Diagram`](images/OrderClassDiagram.png)

![`Task Class Diagram`](images/TaskClassDiagram.png))

These fields satisfy the following conditions:
* Both:
  * `Date`, `Label`: Nonempty block of alphanumeric characters of length at most 100 characters. We felt this was a reasonable
    length for both fields, and would guarantee the UI display worked the way we intended.
* Task:
  * `TaskTag`: This is a tag that is either `General`, or `SO{ID}` where `ID` is the `Id` field of some `Order` object.
  * `isDone`: Boolean flag to indicate whether or not the task is done.

* Order:
  * `Amount` Accepts any string that can be parsed by Double.parseDouble(), that results in a non-negative real number.
    Represents the amount the user charges for a given `Order`
  * `Customer` Blocks of 1 or more alphanumeric characters, separated by at most one space. Represents the `Person` the order
    is addressed to.
  * `id` Long used to uniquely identify `Order` objects. In this case, we did not deal with potential overflow given that
    the range of a Long in Java is up to 2^63 (which is > 10^18!). We judged that this should be more than sufficient for
    any realistic use of the application.
  * `isComplete`: Boolean flag to indicate when the `Order` is complete, and payment has been received.

Something alluded to in the fields above, is that there are implicit dependencies between the `Task`, `Order`, and `Person` classes.
To add an `Order` to SalesNote, we decided it made the most sense for there to already be a `Person` in the application
the `Order` was addressed to. So for instance, to add an order from a client named `Jamie Tan`, the user would need to ensure
that a `Person` with `Name` `Jamie Tan` existed in the application first.

Another link we thought would make sense to allow for, was to make it possible to tie a `Order` object to related `Task` objects.
`Task` objects were meant to help users manage their work, and so we felt there should be a way for a user to relate a `Task`
to a specific `Order` if they wanted to.

In both of these cases, we did not link the classes directly, hence there is no arrow between the `Order` and `Person` class
and no arrow between the `Order` and `Task` class in the diagram above. Instead we simply made use of the fact that SalesNote
maintains both a `UniquePersonList` and a `UniqueOrderList`. To relate a `Order` to a `Person`, it is enough to remember the
`Name` field (in `UniquePersonList`, two `Person` objects with the same `Name` are considered equal). To relate a `Task` to
a `Order`, we can make use of the fact that `Order` objects have unique `id` fields.

##### Design choices
A very reasonable alternative one might consider is linking the classes directly. For instance, allowing a `Person`, to
have a list of `Order` objects related to the `Person`, and a `Order`, to have a list of `Task` objects related to the `Order`.
This was an alternative method we considered, that would come with a cost in complexity by relating the `Person`,
`Task` and `Order` objects. We felt that the method we chose that made use of the `UniqueXList` properties and kept the
classes more distinct better adhered to the Separation of concerns and Law of Demeter principle.

#### Addressing related feature flaws present in the original AB3 codebase
In implementing the fields for the `Task` and `Order` object and considering possible feature flaws, we decided to update
the fields for the `Person` class as well. The original AB3 treated two people as equal only if their names were spelt exactly
the same, with this being case-sensitive. We decided that multiple clients having the exact same name was rare
enough that this notion of equality made sense, however, we felt it should apply regardless of case, i.e. a `Person` with `Name` `john doe`
should be recognised as the same a `Person` with `Name` `JOHN DOE`.

We updated the equality check to account for this, and also updated the input validation for `Name` to allow at most one
space between blocks of characters (previously `John   Doe` would be different from `John Doe`. We felt this likely to be
a mistake and should be avoided).

