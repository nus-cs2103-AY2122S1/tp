---
layout: page
title: Teo Sin Yee's Project Portfolio Page
---

### Project: SportsPA

SportsPA is a desktop application used to manage membership and training sessions of NUS sports CCAs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find members based on multiple attribute keywords.
    * What it does: Allows the user to filter the member list by specifying one or more attribute keywords.
    * Justification: This feature improves the user experience significantly as users can now find specific members with the specified attribute keywords. This feature essentially filters the member list to the users' liking, thus unimportant data is hidden and only relevant data is displayed to the user.
    * Highlights: Implementing this feature was challenging as it required modifications to the parser to parse multiple prefixes and chain multiple predicates. A unique Predicate class was also created for every attribute.
    * Pull request: [\#161](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/161)
* **New Feature**: Added the ability to sort the member list.
    * What it does: Allows the user to sort the member list, either by name or tags.
    * Justification: This feature improves the user experience significantly because the member data can now be arranged into a meaningful order, making it easier for users to analyse and visualise the data. Presentation of the application is also improved since the data can be presented in a systematic manner.
    * Highlights: Implementing this feature was rather challenging due to the added complexity of `FilteredList` being an unmodifiable list. It required careful examination of the code base to figure out where I could modify the list.
    * Pull request: [\#144](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/144)
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=sinyee&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&zA=tsinyee&zR=AY2122S1-CS2103T-W12-1%2Ftp%5Bmaster%5D&zACS=221.19108280254778&zS=2021-09-17&zFS=w12&zU=2021-11-05&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Team Tasks**:
    * Managed release `v1.3.1` on GitHub
    * Refactored `AddressBook` to `SportsPA` (Pull request [\#241](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/241))

* **Enhancements to existing features**:
    * Removed unneccessary attributes from Member  (Pull request [\#103](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/103))
    * Added facility field validations and related tests (Pull request [\#113](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/113))
    * Fixed bugs from PE-D (Pull request [\#209](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/209))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addm`, `listm`, `sortm`, `findm` (Pull requests [\#131](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/131),
          [\#144](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/144), [\#161](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/161))
        * Updated command syntax section and notes on command format (Pull request [\#161](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/161))
    * Developer Guide:
        * Added implementation details of the `findm` feature. (Pull requests [\#161](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/161), [\#221](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/221))
        * Updated the `Model` section to reflect changes made to it for `SportsPA` (Pull request [\#244](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/244))
        * Updated several UML diagrams to reflect changes made to it for `SportsPA` (Pull request [\#244](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/244))

* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests [\#105](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/105), [\#138](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/138))
    * Reported bugs and suggestions for other teams in the course through [PE-D](https://github.com/tsinyee/ped/issues)
