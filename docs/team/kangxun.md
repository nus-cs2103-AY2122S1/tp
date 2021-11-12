---
layout: page
title: Kangxun's Project Portfolio Page
---

### Project: CallMeMaybe(CMM)

CallMeMaybe (CMM) is a **desktop app** centered for Telemarketers in aiding them in customer contact management.
CMM provides a solution to quickly catalog people based on who has/yet to be called. It caters to Telemarketers 
by providing in-app tracking functionality to quickly contact previously unreachable customers. CMM also provides 
importing and exporting capabilities to allow ease of collaboration in team-based environments.

CMM is developed for use via a **Command Line Interface** (CLI) while providing the additional benefits of a 
Graphical User Interface (GUI). It is written in Java and the GUI is created with JavaFX. CMM has about 17 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter contacts (Pull request [\#122](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/122))
  * What it does: Allows the user to filter contacts displayed based on category and count. 
  * Justification: This feature enhances the app's ability to support telemarketers by allowing them to view contacts that they want to see.
  * Highlights: The filter command involves both sorting and filtering the list of contacts. The implementation was challenging as it involved working around the 
  current implementation of an Observer design pattern using a FilteredList. Considerations of different design options were made before finalising on the existing
  implementation. Modifications to code base can complement future enhancements that require similar functionality.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Kangxun&tabRepo=AY2122S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Added new person attribute `Called` class for tracking functionality (Pull request [\#40](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/40))
  * Modified writing to storage to support tracking functionality (Pull request [\#40](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/40))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the feature `help`, `add`, `done`, `delete`, `clear`, `filter` 
                       [\#72](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/72),
                       [\#73](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/73),
                       [\#124](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/124),
                       [\#175](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/175)
    * Did cosmetic tweaks to existing documentation: 
                       [\#100](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/100)
  * Developer Guide:
    * Added implementation details of the `filter` feature:
                       [\#124](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/124)
    * Updated for CMM: [\#66](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/66)
  * README, config and index:
    * Updated for CMM: [\#60](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/60), 
                       [\#61](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/61),

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
                       [\#65](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/65),
                       [\#71](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/71), 
                       [\#102](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/102), 
                       [\#121](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/121),
                       [\#172](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/172),
                       [\#180](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/180)
