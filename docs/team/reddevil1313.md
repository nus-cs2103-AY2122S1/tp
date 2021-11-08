---
layout: page
title: Samay Sagar's Project Portfolio Page
---

### Project: ProgrammerError

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) is a desktop application which helps CS2100 tutors manage
their students’ lab results in a simple and efficient manner, allowing them to spend less time on administrative
processes and more time teaching students.

The following is a summary of my contributions to the
project. ([View Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=reddevil1313&tabRepo=AY2122S1-CS2103-F09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zA=reddevil1313&zR=AY2122S1-CS2103-F09-3%2Ftp%5Bmaster%5D&zACS=213.68089453967534&zS=2021-09-17&zFS=&zU=2021-11-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false))

### Features

1. Added the ability to purge existing data and fill it with using sample data

    * What it does: Allows the TA to purge existing data using the purge command and fill it with sample data using the fill
      command.
    * Justification: So that the TA can remove existing data to input his/her own data or try out the commands on a sample
      database.
    * Highlights: This feature seemed simple at first as it just required to modify the existing clear feature but the fill
      command required understanding of how the data was stored in the json file and how to perform checks on whether the
      program already had data or not.

2. Added the ability to add labs and edit their details for the entire student base

    * What it does: Allows the TA to add and edit labs ranging from 0 to 14 for the entire student base in the application.
    * Justification: Primary purpose of the application; So that the TA can keep track of the scores of labs under him/her.
    * Highlights: This feature posed various challenges along the way. Earlier we had implemented the attributes of the Lab
      class using data values but then shifted to wrapper classes which required very tedious refactoring and debugging as a
      lot of unwanted bugs came about. Changes made to the storing of the data in the json file to accomodate for the new
      labs encountered a lot of bugs as well.

3. Added the ability to store individual grades for each student

    * What it does: Allows the TA to edit individual grades for each student in the program.
    * Justification: So that the TA can keep track of how many labs he/she has marked and how many of them are 
      still left to mark.
    * Highlights: This feature required changes to the existing edit command and thus, understanding of the command's  
      optional inputs. One of the major highlights was solving a bug where once a newly added lab was edited for one  
      student, it was being edited to all.

### Other Contributions

#### **Project management**:

* Managed the issue tracker and made sure that all issues were labelled, assigned to teammate and belonged to a
  milestone.

#### **Enhancements to existing features**:

* Improve Testing: e.g. [#84](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/84)
  , [#85](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/85)
  , [#170](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/170)
  , [#220](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/220)
  , [#224](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/224)
  , [#233](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/233)
  , [#234](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/234)
  , [#235](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/235)
  , [#241](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/241)
  , [#417](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/417)
  , [#419](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/419)
  , [#447](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/447)
  , [#579](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/579)
  , [#583](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/583)
* Bug fixes: e.g. [#417](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/417)
  , [#425](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/425)
  , [#432](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/432)
  , [#447](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/447)
  , [#500](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/500)
  , [#561](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/561)
  , [#591](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/591)

#### **Review and Mentoring Contributions**:

* PRs reviewed (with non-trivial review comments): [#47](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/47)
  , [#113](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/113)
  , [#232](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/232)
  , [#244](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/244)
  , [#324](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/324)
*   Reported bugs and suggestions for other teams in the class: [#1](https://github.com/reddevil1313/ped/issues/1)
  , [#2](https://github.com/reddevil1313/ped/issues/2), [#3](https://github.com/reddevil1313/ped/issues/3)
  , [#4](https://github.com/reddevil1313/ped/issues/4), [#5](https://github.com/reddevil1313/ped/issues/5)
  , [#9](https://github.com/reddevil1313/ped/issues/9), [#12](https://github.com/reddevil1313/ped/issues/12)

#### **Documentation**:

* User Guide:
    * Added documentation for the features `purge`
          and `fill` [#31](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/31)
    * Added documentation for all the lab commands: `addlab`, `editlab` and `deletelab`:
          [#236](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/236)
* Developer Guide:
    * Added high priority user stories [#43](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/43)
    * Sequence and Activity Diagram for EditLabCommand [#519](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/519)
    * Added feature description for purge and editlab commands 
          [#571](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/571)
          , [#574](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/574)

