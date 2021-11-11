---
layout: page
title: Gu Geng's Project Portfolio Page
---

### Project: Intern Watcher

Intern Watcher (IW) is a desktop app for Human Resource Managers (HRs) to manage internship applicants. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 17 kLoC. 

Given below are my contributions to the project.

* **New Feature**: Added the ability to do mass filtering against a combined collection of criteria at once.
    * What it does: Allows the user to filter the applicant list with various and multiple criteria specified against different factors: Grade, GraduationYearMonth, Skill, Status, Institution, Course and Job.
    * Justification: This feature improves the product significantly because a HR can select potential applicants based on different requirements with great flexibility and efficiency.
    * Highlights: This enhancement required an in-depth analysis of design alternatives via careful and thorough consideration of the target users' demands and the most probable use cases. For example, for filter on Grade, only one instead of multiple filters will be accepted and only applicants with grades not less than the specified threshold will be displayed as HRs should be more interested in finding applicants that at least meet the requirement specified in regard to grade. The implementation too was challenging as it required to combine filters of different factors and deal with their respective behaviours and specifications.


* **New Feature**: Added GraduationYearMonth field to applicants.

* **New Feature**: Added Job field to applicants.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jackgugz&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Enhancements to existing features**:
    * Added feature to allow Skill to take in symbols `+#` to accomodate skills such as `C++` and `C#` (Pull request [\#152](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/152))
    * Changed the field `value` (of type `String`) of GraduationYearMonth to type `YearMonth` as it facilitated the comparison between GraudationYearMonth. (Pull request [\#152](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/152))
    * Changed the case-sensititvity of `Name` for `AddCommand` to case-insensitive for more practical dertermination of duplicate applicants. (Pull request [\#222](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/222))
    * Wrote additional and maintain tests for newly added fields (`GraduationYearMonth` and `Job`) and feature (`Filter`) to maintain coverage (Pull requests [\#24](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/24), [\#119](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/119))

* **Documentation**:
    * User Guide:
        * Refined documentation for the features `filter` and `add` [\#144](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/144), [\#222](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/222)
        * Report bugs for User Guide [\#242](https://github.com/AY2122S1-CS2103T-F12-2/tp/issues/242)
    * Developer Guide:
        * Added implementation details of the `filter` feature including the sequence diagram for a `filter` command. [\#109](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/109)
        * Refined documentation for the features `filter` and `add` [\#222](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/222)
        * Report and fix bugs for Developer Guide [\#243](https://github.com/AY2122S1-CS2103T-F12-2/tp/issues/243), [\#262](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/262)
    * AboutUs:
      * Update and refine details for roles, responsibilities and features [\#257](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/257)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#85](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/85), [\#97](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/97), [\#115](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/115), [\#129](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/129), [\#137](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/137), [\#205](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/205), [\#252](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/252), [\#253](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/253)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/54), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/308), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/310), [4](https://github.com/nus-cs2103-AY2122S1/forum/issues/141#issuecomment-960573021))
    * Reported bugs and suggestions for other teams in the class (examples: [ped](https://github.com/jackgugz/ped/issues))

* **Contributions to team-based tasks**:
    * Morphing the product into a different product by adding the distinguished `filter` feature ([\#97](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/97))
    * Maintain Issue Trackers
    * Updating user/developer docs that are not specific to a feature ([\#42](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/42)):
      * update product scope
      * update user stories
      * update NFRs
      * update Glossary

* **Contributions to Project Management and Organisation:**:
  *  Managed and organised project resources/minutes and facilitated discussions/meetings ([Google Drive](https://drive.google.com/drive/u/0/folders/1jFDuJ2T-VEtskOZSgQ4Ce28nM-mkgGDa))
