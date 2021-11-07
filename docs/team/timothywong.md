---
layout: page
title: Timothy Wong's Project Portfolio Page
---

### Project: ComputingConnection

*ComputingConnection* is a desktop application for entrepreneurial students in NUS Computing who want to keep track of other students’ details so that they can easily look for suitable people to work with on future projects by filtering contacts via information such faculty, major, programming languages, remarks, etc. *ComputingConnection* is optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. The programme is written in Java. 

Given below are my contributions to the project. My contributions to the project code base can be found at  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=timothywongej&tabRepo=AY2122S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### Features

1. **New Feature**: `append` command <br> 
  Added the ability to append elements to certain optional data fields. 
  * What it does: Allows the user to append a new element to an optional data field of an existing contact, so that the user can add new **skill**, **programming language**, **framework**, **tag**, and **remark** data fields to a contact. 
  * Justification: This feature improves the product significantly because a user can add multiple elements to a data field over time, without having to re-enter all of them (As was the case previously for AB-3 `edit` command).
  * Highlights: The implementation was done using the HashSet data structure so that the amortized time complexity for operations takes O(1) time. The HashSet is then sorted via the frontend alphanumerically.
  <br><br/>
    
2. **New Feature**: `rm` command <br>
  Added the ability to remove specific elements from certain optional data fields.
  * What it does: Allows the user to remove an element from an optional data field of an existing contact, so that the user can remove specific **skill**, **programming language**, **framework**, **tag**, **remark** and **interaction** data fields of a contact.
  * Justification: This feature improves the product significantly by allowing precise adjustments to data fields. A user can remove specific elements at indexes in a data field in case a mistake was made to an entry. (The previous AB-3 implementation of `edit` would remove all elements).
  * Highlights: This feature required consideration over what data structure to use to overcome the project constraint of not using a database. The final implementation was done using the HashSet data structure for the storage of elements in each data field, and the removal was implemented using the ArrayList data structure in order to support Java generics.
<br><br/>

#### Other contributions
As the team lead, I took a large initiative in ensuring tasks were done timely and properly.  

1. **Project management**:
  * Set up and maintained the internal project notes and overall team direction.  
  * Set up the GitHub team org/repo.
  * Maintained the issue tracker.
  * Scheduled, initiated and led productive meetings and discussions.
  * Managed releases `v1.1` - `v1.3` (3 releases) on GitHub.
    <br><br/>
  
2. **General code enhancements**:
  * Idea generation and work related to the value proposition of the application.
  * Updated front matters of the *ComputingConnection* code and website: [\#22](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/22), [\#81](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/81)
    <br><br/>

3. **Enhancements to existing features**:
  * Updated the Person class to make it relevant to the value proposition of ComputingConnection: [\#39](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/39), [\#57](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/57) 
  * Updated the `add` and `edit` commands to support new data fields and integration: [\#39](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/39), [\#57](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/57)
  * Updated the GUI tags and structure: [\#69](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/69), [\#75](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/75), [\#98](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/98) 
  * Ensure test cases are compatible and integrated with new features.
    <br><br/>

4. **Documentation**: <br>
    * In charge of the organisation, arrangement and formatting: [\#83](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/83), [\#89](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/89) [\#112](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/112)
    * User Guide: Added most front matters including the product overview, about, syntax, and understanding the 'Features' 
 section: [\#81](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/81)
    * User Guide: Added documentation for the features `append` and `remove`: [\#67](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/67), [\#71](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/71)
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#21](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/21), [\#24](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/24)
    * Developer guide:
    * Added implementation details of the `delete` feature.
      <br><br/>

5. **Review and Mentoring Contributions**:
  * PRs reviewed (with non-trivial review comments): [\#66](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/66), [\#181](https://github.com/AY2122S1-CS2103T-W10-3/tp/pull/181)
  * Contributed to forum discussions: [\#197](https://github.com/nus-cs2103-AY2122S1/forum/issues/197)
  * Reported bugs and suggestions for other teams in the class (examples: [\#4](https://github.com/timothywongej/ped/issues/4#issuecomment-955693194), [\#11](https://github.com/timothywongej/ped/issues/11), [\#12](https://github.com/timothywongej/ped/issues/12)).
