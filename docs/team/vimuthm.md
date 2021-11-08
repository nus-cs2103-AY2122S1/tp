---
layout: page
title: Vimuth's Project Portfolio Page
---
### Project: SafeFor(H)All

SafeFor(H)All is a desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **Key contribution**: Refactored the AB3 codebase to the context of SafeFor(H)All [#45](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/45)
    * Changed package name from `seedu.address` to `safeforhall`
    * Enhanced the `Person` model to fit that of a resident
    * Updated the ui to show all details of a resident
    * Updated storage for residents and added testing for all new files

* **New Feature**: Added the ability to contact trace residents
    * What it does:
        * Allows the user to get a list of all residents a given resident has been in contact with.
      This is done using information provided by the events data. The user is free to specify how far back to trace (number of days) as well as the depth which indicates if contacts are simply immediate contacts (depth 1) or contacts of contacts (depth 2, 3...)
    * Justification:
        * This feature adds significant value to the product and is strongly in line with the value proposition. It provides the admin with an easy way to find all close contacts in case of a positive case.
    * Highlights:
        * This enhancement required working with the model to access the list of events. There was a need for a good understanding of the design structure to not breaking existing abstraction and design. It was fun and challenging to implement the depth and duration restrictions.

* **New Feature**: Added the ability to import data from a csv
    * What it does:
        * Allows the user to mass import resident data from a provided csv file. This replaces the existing resident data provided no errors were raised while reading and creating `Person` objects. All resident lists of each event is then wiped to remove dependencies to old residents.
    * Justification:
        * This feature greatly improves the transition from an admins existing workflow to our product. It removes the need for entry of countless tedious add commands and improves the user experience.
    * Highlights:
        * This enhancement required thorough reading of the opencsv documentation. It required a number of decisions to be taken where I had to balance usabilty vs complicated implementations.
    * Credits: The third part library, [opencsv](http://opencsv.sourceforge.net/), was used to ease the reading of csv files.

* **New Feature**: Added the ability to traverse the history of command inputs
  * What it does:
    * Allows the user to use the up and down arrow keys when the command box is in focus, to traverse their past command line inputs. There is no hard limit on how many inputs are stored but there is no persistence after exiting the app.
  * Justification:
    * This feature really adds to the ease of use of the application. Since long commands are tedious to type but have a higher chance of being similar, and taking into account repetitive commands, this feature drastically reduces time waste and enhances the user experience.
  * Highlights:
    * This enhancement interacts mostly with the ui and required an understanding of how to send updates to it. Adding event handlers, resetting the text and even moving the cursor to the rightmost end of the text, were interesting and exciting tasks.

* **Enhancements to existing features**:
  * Enhanced the `find` feature to allow filtering by any provided parameter [\#53](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/53)
      * What it does:
          * Allows the user to filter by other variables such as vaccination, faculty, email in addition to name.
      * Justification:
          * This significantly increases the usability of the `find` feature. In the context of the app, there's a need to be able to filter by vaccination status, different blocks, levels and faculties for the admin to disemminate information to the right students.
  * Implement tab-specific behaviour of the same command keyword [\#80](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/80)
  * Contributions to the UI:
      * Show vaccinated icons next to such residents [\#61](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/61)
      * Highlight residents in red when their FET's are due [\#61](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/61)
      * Make tabs resizable [\#149](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/149)
      * Create the left icon bar [\#129](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/129)
  * Contributions to testing:
      * Added 100% coverage for the `Person` model [\#142](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/142)
      * Added testing for the `find`, `trace` and `import` features
      * Added ~2 kLoC in total for testing

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=VimuthM&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipSortBy=lineOfCode)

* **Project management**:
    * Set up the GitHub team org/repo and tools such as CodeCov and Gradle
    * Did work related to renaming the product and changing the product icon
    * Delegated work and issues and managed the issue tracker throughout
    * Managed [releases](https://github.com/AY2122S1-CS2103T-T15-4/tp/releases) `v1.2`, `v1.3.trial`, `v1.3` and `v.1.3.1` (4 releases) on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for `find` [\#98](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/98)
        * Segment UG to resident features, event features and commons [\#98](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/98)
        * Added documentation for `trace`, `import` and `export` [\#154](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/154)
        * Added a section on introducing the ui and included screenshots [\#154](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/154)
    * Developer Guide:
        * Added implementation details of the `find` feature [\#97](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/97)
        * Added list of non-functional requirements, use cases and manual testing [\#26](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/26)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#85](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/85), [\#81](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/81), [\#62](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/62), [\#57](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/57), [\#51](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/51)
    * Contributed to [forum](https://nus-cs2103-ay2122s1.github.io/dashboards/contents/forum-activities.html#6-dina-ndis-vimuthm-38-posts) discussions (some instances: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/109#issuecomment-907317863), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/326#issuecomment-946411200), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/314#issuecomment-942032282), [4](https://github.com/nus-cs2103-AY2122S1/forum/issues/274#issuecomment-927850889))
    * Reported [bugs and suggestions](https://github.com/VimuthM/ped/issues) for other teams in the class.

* **Tools**:
    * Integrated a third party library, [opencsv](http://opencsv.sourceforge.net/), to the project ([\#130](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/130))
