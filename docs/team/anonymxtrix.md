---
layout: page
title: Loh Xian Ze, Bryan's Project Portfolio Page
---

### Project: SPAM

SPAM is a desktop application that is made for fast-typing marketers to handle large quantities of marketing leads and
contacts efficiently and precisely. It is a CLI application that has UNIX-like command line syntax, and sports an
elegant and modern GUI created with JavaFX. The application is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Command History.
    * What it does: Allows users to scroll through previously executed commands by using the `↑` and `↓` arrow keys.
    * Justification: Allows ease of use to users by allowing them to use previous commands to form new commands.
    * Highlights: The implementation of this feature requires saved instances of the objects to not be mutated. As such,
                  I had to take extra care in making sure that the implementation of the feature was defensive.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Anonymxtrix&tabRepo=AY2122S1-CS2103T-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Responsible for monitoring and keeping up the team's coding standards.
    
* **Enhancements to existing features**:
    * Added truncation for tag display in person [#256](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/256)

* **Documentation**:
    * User Guide:
        * Added user controls section [\#122](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/122)
        * Did cosmetic tweaks to user guide [\#122](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/122)
        * Adjusted guide for better navigation and usability [\#122](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/122)
    * Developer Guide:
        * Added implementation details of the command input history feature [\#99](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/99)

* **Community**:
    * PR reviewed (with non-trivial review comments): [#50](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/50)

* **Tools**:
    * Added [bootstrap](https://getbootstrap.com/) stylesheets to the project. [\#122](https://github.com/AY2122S1-CS2103T-W13-2/tp/pull/122)
