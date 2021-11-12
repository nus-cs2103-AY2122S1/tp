---
layout: page
title: Ngo Phuc Cuong's Project Portfolio Page
---

## Project: Academy Directory

Academy Directory is a desktop address book application used for CS1101S tutors to keep track of information related to their students. The user interacts with it using a CLI and mouse, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to visualize grades of the entire class.
    * What it does: allows the user to quickly visualize the performance of the entire class for all assessments with box and whisker plot, showing clearly min, max, median, 25% and 75% percentile grades for all assessments.
    * Justification: This feature improves the product significantly because it allows users to comprehend and analyse data in a more intuitive way, it's the only command that generates graphical feedback for users.
    * Highlights: This enhancement cannot be implemented directly on AB-3 default structure. It's hard due to these reasons:
      * Our project uses JavaFx library for GUI, however default graphing tools in `JavaFx` is insufficient. `JFreeChart` library provides powerful graphing APIs but incompatible with `JavaFx`. Much of initial work went into finding the right `JFreeChart` fork that provides a bridge to `JavaFx` render.
      * Current design of AB-3 only allows for String based data to be returned to `UI` through `CommandResult`. As such, an additional UI architecture design need to be done to support all sort of data (statistics data, student object data). Created easily extensible architecture to display all kinds of data in the newly added `VisualizerDisplay` in the bottom right corner.
      * Reduce Jar filesize by digging deeper to the package structures, removing unnecessary runtime import within a package, reducing our jar file from ~60mb -> ~20mb 
      * Credits: Make use of APIs from [JFreeChart](https://www.jfree.org/jfreechart/) 

* **Enhancements to existing features**:
    * Initial refactoring most of AB3 names & attributes to what's needed in AcademyDirectory
    * Removed unnecessary fields like `ADDRESS`, and added `TELEGRAM` field
    * Make `PHONE` attribute optional.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=phuccuongngo99&tabRepo=AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Setup Team repo, Codecov, CI, branch protection on behalf of team
    * Organize meeting minutes for team's weekly meeting

* **Documentation**:
    * User Guide:
        * Added documentation for the features `visualize`, `add`, ...
        * Check through UserGuide, proofreading and make small changes
    * Developer Guide:
        * Add implementation details and sequence diagrams to morphed AB3 features like `add`, `delete`, `edit` (Pull Requests [/#234](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/234), [\#244](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/244), [\#245](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/245), [\#245])
        * Add most of Non-Functional Requirements. 
        * Add testing guide for a few commands. (Pull Request: [\#247](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/247))
        * Change overall architecture diagram from AB3 -> AcademyDirectory
        * Double check other UML diagram of teammates and make small necessary changes.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#90](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/90), [\#56](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/56), etc...
    * Reported bugs and suggestions for other teams in the class: [\#208](https://github.com/AY2122S1-CS2103T-T10-2/tp/issues/208), [\#196](https://github.com/AY2122S1-CS2103T-T10-2/tp/issues/196), [\#193](https://github.com/AY2122S1-CS2103T-T10-2/tp/issues/183)
    * Joined all team meeting and discussed throughout the duration of the project.

* **Tools**:
    * Integrated a third party library (JFreeChart) to the project ([\#129](https://github.com/AY2122S1-CS2103T-T15-3/tp/pull/129))
