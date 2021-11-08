---
layout: page
title: Richard Li's Project Portfolio Page
---

### Project: InsurancePal

InsurancePal is a client management software specially designed
to help manage the logistics of being an insurance agent.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t17-4&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=rldefa&tabRepo=AY2122S1-CS2103T-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=true)

**Major Enhancements implemented**:
* Implemented `schedule` command [\#23](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/23), [\#104](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/104), [\#120](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/120)
  * **What it does**: Allow user to schedule meetings with their clients.
  * **Justification**: Our target users are insurance agents, who have to meet up with many clients often. This feature allows them to keep track of who they are meeting and when. It also allow them to view all upcoming appointments in a glance.
  * **Classes created**:
    * `Appointment`
    * `ScheduleCommand`
    * `ScheduleCommandParser`
  * **Tests written**:
    * `AppointmentTest`
    * `ScheduleCommandTest`
    * `ScheduleCommandParserTest`
  * **Additional Info**: 
    * Provide list of people sorted by the earliest appointment that is upcoming.
      * Requires understanding JavaFX's ObservableList interface.
      * Requires understanding of sorting and filtering on ObservableLists.
    * Formats date and time using `DateTimeFormatter`, and ignores casing for month (Jan and JAN are both valid).

**Minor Enhancements**:
* Rename files provided by AddressBook to names that are more relevant to our project [\#176](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/176/files)

**Documentation**:
* User Guide:
  * Added documentation for the feature `schedule` [\#29](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/29)
* Developer Guide:
  * Add more non-functional requirements to developers guide. [\#27](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/27)
  * Added documentation for the `schedule` command. [\#108](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/108)
  * Change names in developer guide for all classes and diagrams. [\#179](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/179)
  * Improve layout and readability of all classes and diagrams. [\#198](https://github.com/AY2122S1-CS2103T-T17-4/tp/pull/198)

**Contributions to team-based tasks**
* Advice team members on proper merging procedure to avoid having commits from other people show up in their own PR.
* 21 PRs reviewed on team project repository.
* Vigorous testing of features by teammates to detect bugs before PE.

**Community**:

* Report [bugs](https://github.com/rldefa/ped/issues) in other teams projects for PE-D. 
* Help fellow student in the forum [\#212](https://github.com/nus-cs2103-AY2122S1/forum/issues/212)
