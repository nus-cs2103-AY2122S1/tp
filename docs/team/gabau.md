---
layout: page
title: Gabriel's Project Portfolio Page
---

### Project: Staff'd

Staff'd is a desktop staff tracking application used managing F&B staff. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark and unmark a staff from attendance.
  * What it does: Allows the user to mark a staff as absent for a range of dates.
  * Justification: This feature improves the product significantly because an employee may be on vacation over a group of dates, and this feature simplifies the recording of such information.
  * Highlights: This enhancement used the start and endpoint to record the startdate and enddate to be marked, which meant that to remove and add such dates, some form of complement and union had to be implemented. 

* **New Feature**: Added the ability to add a duration for which a shift is active.
   * What it does: Allows the user to select a group of dates where a staff is working a shift.
   * Justification: In order for obtaining the salary over a month, the shifts must be assigned to dates to make it clear for the user when the staff is working and when the staff is not.
   * Highlights: How these commands interacted with the `setShiftTIme` command had to be addressed. Delete and add ignored the setShiftTime and just delete by the range of dates. How the enhancement was stored also needed to be dealt with, as a list of all the assigned duration needed to be maintained for each shift.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Gabau&tabRepo=AY2122S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false#:~:text=Click%20to%20view%20author%27s%20contribution.)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub
  * Managed milestone tracking

* **Enhancements to existing features**:
  * Enhanced `find` command to take in tags. [\#233](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/#233), [\#47](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/#47)

[comment]: <> (  * Wrote additional tests for existing features to increase coverage from 88% to 92% &#40;Pull requests [\#36]&#40;&#41;, [\#38]&#40;&#41;&#41;)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `mark`, `unmark` [\#106](https://github.com/AY2122S1-CS2103T-W11-2/tp/#106), `staff`, `istaff` [\#124](https://github.com/AY2122S1-CS2103T-W11-2/tp/#124)
    * Added documentation for shift modifications with the active periods.
  * Developer Guide:
    * Added implementation details of the `mark` and `unmark` feature.
    * Updated glossary and non-functional requirements.
    * Updated diagrams of Logic, Model, Storage and UI component [\#287](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/287).
    * Updated diagrams of attendance feature in [\#106](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/106).
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#73](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/73), [\#64](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/64), [\#28](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/28), [\#95](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/95), [\#120](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/120)
  


