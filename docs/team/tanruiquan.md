---
layout: page
title: Tan Rui Quan's Project Portfolio Page
---

### Project: contHACKS

contHACKS is a desktop app for Teaching Assistants (TAs) to manage contacts and lessons. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLoC.

Given below are my contributions to the project.

**New Feature**: Added the ability to add lesson. (PR [#135](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/135))
  * **What it does**: allows the user to add lesson into contHACKS by providing information about the lesson.
  * **Justification**: This feature improves the product significantly as user can now refer to contHACKS to know what upcoming lessons they have after they have added the lesson.
  * **Credits**: The design structure is inspired from AddressBook Level 3

**Enhanced Feature**: Enhanced the ability to add person. (PRs [#14](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/14), [#47](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/47))
  * **What it does**: allows the user to add person into contHACKS with information that are relevant to a TA.
  * **Justification**: removed the ability to include address when adding a person as such information is not relevant to a TA.
  * **Justification**: added the ability to include module codes, lessons codes and telegram handle when adding a person as such information are relevant to a TA.
  * **Highlight**: as multiple module codes (e.g.`CS2103T`) and lesson codes (e.g. `T14`) are allowed. There is a need to identify which module code a lesson code belongs to. There were many possible design considerations, but we ultimately settled with making the associate when adding. Such an implementation requires [substanstial change in the model component](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/86) which made the enhancement challenging.

**Enhanced Feature**: Enhanced the ability to edit person. (PR [#74](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/74))
  * **What it does**: allows the user to edit a person that is already in contHACKS.
  * **Justification**: modified the edit functionality so as to correspond with the changes in add functionality

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ruiquan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=tanruiquan&tabRepo=AY2122S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

**Project management**:
  * Managed releases `v1.2.1` and `v1.3.1` (2 releases) on GitHub

**Testing**:
  * Wrote tests for all new and enhanced features:
[#47](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/47),
[#74](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/74),
[#135](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/135)

**Documentation**:
  * User Guide:
    * Added documentation for the features `add`, `edit`: [#104](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/104)
    * Created a structure, updated table of contents and added documentation for the feature `addc`: [#148](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/148)

  * Developer Guide:
    * Updated the UML diagram and documentation for the model component: [#99](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/99)
    * Added documentation to include implementation details about `add` and `addc` features: [#231](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/231)

**Community**:
* PRs reviewed (with non-trivial review comments):
  [#45](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/45),
  [#77](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/77),
  [#105](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/105),
  [#132](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/132),
  [#202](https://github.com/AY2122S1-CS2103T-T09-2/tp/pull/202)
