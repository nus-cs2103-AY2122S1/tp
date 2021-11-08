---
layout: page
title: Bryann Yeap's Project Portfolio Page
---

### Project: RecruitIn

RecruitIn is a desktop app for recruiters in Singapore to keep track of the plethora of clients with different skill sets, availability and experience.
It is optimized for quick text-based inputs via a Command Line Interface (CLI) while still having the ease of use of a Graphical User Interface (GUI). 
This product will make recruiters’ lives easier through categorisation and filter features to easily access candidates they have in mind.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=bryannyeap)

* **New Feature**: Added the `filter_interview` command and wrote tests for it:
    [\#219](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/219),
    [\#240](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/240)
  * What it does: It gives recruiters the ability to filter applicants depending on whether their interviews have passed, or are still upcoming in the future.
  * Justification: This feature improves the product significantly because a user can easily keep track of and see which applicants have interviews dates that are upcoming or already over, and perform operations them accordingly.
  * Highlights: This feature required some decision-making between design alternatives such as whether to implement it as an enhancement to a current command or a new one, as well as whether to use polymorphism and inheritance.

* **New Feature**: Added the `delete_marked` command and wrote tests for it:
    [\#175](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/175)
  * What it does: It allows recruiters to perform a mass delete on all applicants whose status are `Done`.
  * Justification: This feature improves the product significantly because it saves a huge amount of time for the recruiters. Recruiters no longer need to search for **and** manually delete applicants whose status are `Done` individually.

* **Enhancements to existing features**:
  * Modified `delete` command to allow for deletion of multiple indexes at the same time, and wrote tests for it:
    [\#154](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/154)

  * Added `ExpectedSalary` category, and integrated it for `add`, `edit`, and `find`:
    [\#65](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/65),
    [\#128](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/128)
  * Wrote tests for `ExpectedSalary` category:
    [\#79](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/79),
    [\#88](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/88),
    [\#122](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/122)
    
  * Wrote tests for `Tag` and `TagContainsKeywordsPredicate`:
    [\#78](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/78),
    [\#122](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/122)

  * Wrote tests for `Interview`:
    [\#227](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/227)

  * Disallowed duplicate indexes for `delete`, `mark`, and `unmark` command:
    [#\295](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/295)
    
  * Fixed unintended `mark` and `unmark` command behaviour by instantly detecting out-of-range indexes:
    [\#305](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/305)
    
  * Disallowed incorrect `find` command inputs for the `Name`, `Phone`, `Email`, `Role`, `ExpectedSalary`, and `Tags` category, and wrote tests for it:
    [\#122](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/122)
    
  * Modified `show` command to sort user inputs for `Interview` category in chronological order, and wrote tests for it:
    [\#388](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/388)
    
  * Updated Sample Data in RecruitIn:
    [\#230](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/230)
    
  * Added Javadocs:
    [\#414](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/414)

* **Documentation**:
  * User Guide:
    * Added documentation for the `ExpectedSalary` and `Tag` category:
      [\#123](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/123)
    * Added documentation for the features `clear`, `delete` and `delete_marked`:
      [\#181](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/181),
      [\#343](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/343)
    * Updated documentation for the Prefix Parameter Specifications {Advanced}, FAQ, and Command Summary sections:
      [\#343](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/343)
      
  * Developer Guide:
    * Added and updated Use Cases for `add`, `edit`, `filter_interview`, `delete_marked`, `clear` and `exit`:
      [\#181](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/181)
      [\#348](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/348)
    * Updated documentation by removing AB3 Developer Guide content:
      [\#242](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/242/files)
    * Updated implementation details of the `filter_interview` feature:
      [\#287](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/287)
    * Added implementation details of the `add` and `edit` features:
      [\#308](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/308)
    * Added manual test cases for `edit`, `delete` and `missing / corrupted save files`:
      [\#372](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/372)
      [\#393](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/393)
    * Added Appendix C (Effort):
      [\#416](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/416)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#129](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/129),
    [\#167](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/167),
    [\#170](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/170), 
    [\#235](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/235),
    [\#289](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/289),
    [\#300](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/300),
    [\#301](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/301),
    [\#309](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/309),
    [\#322](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/322),
    [\#362](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/362),
    [\#381](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/381)
    
  * Reported bugs for other team during PE Dry Run:
    [\#199](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/199),
    [\#223](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/223),
    [\#235](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/235),
    [\#237](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/237),
    [\#240](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/240),
    [\#244](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/244),
    [\#247](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/247),
    [\#250](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/250),
    [\#251](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/251),
    [\#257](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/257)
    
