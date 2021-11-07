---
layout: page
title: Lethicia Renissa Santoso's Project Portfolio Page
---

### Project: WhereTourGo

**WhereTourGo (WTG)** is a desktop app for managing your tour contacts, optimized for use via a Command-Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added a `filter` command to filter contacts by category.
    * What it does: This feature filters the contacts in the address book by category.
    * Justification: This feature allows users to find contacts based on a given criteria.
    * Highlights: This feature required careful consideration in the filtering mechanism and required the implementation of a new Predicate for the filtering mechanism.


* **New Feature**: Added a `view` command to display contact details one right panel
    * What it does: This feature displays the details of the contact specified (by name or index) on the display panel.
    * Justification: This feature allows users to easily view contact details such as address, phone number, email, etc.
    * Highlights: This feature involved both the logic components and GUI components. The CommandResult had to be modified to trigger the display panel to display the contact specified.


* **New Feature**: Added a `undo` and `redo` command to restore previous versions of the address book.
    * What it does: This feature allows users to undo or redo previous commands that changes the data in the address book (e.g. `delete`, `edit`, `add`, `clear`)
    * Justification: This feature allows users to rectify any unintended changes to the address book, especially commands that remove data from the address book such as `clear` and `delete`. Thus, allowing them to recover data.
    * Highlights: This feature required adding a system to keep track of the current address book and store the different versions of the address book every time a change is made. Additionally,the implementation needed to be able to support different commands.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=lethiciars&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=Imerbear&tabRepo=AY2122S1-CS2103T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=lethiciars&zR=AY2122S1-CS2103T-T12-2%2Ftp%5Bmaster%5D&zACS=100.6923076923077&zS=2021-09-17&zFS=lethiciars&zU=2021-11-03&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)



* **Project management**
    * Reviewed and merged Pull Requests of other teammates


* **Enhancements to existing features**:
    * Reimplemented `clear` to disallow users from clearing an empty address book.
    * Added the option to `filter` by `Tag` and by multiple `CategoryCode` to increase flexibility.
    * Improved GUI by formatting the contact details in the display panel.


* **Documentation**:
    * User Guide:
        * Contributed to User Guide Introduction and Purpose.
        * Added documentation for the features `filter`, `view`, `undo`, and `redo`.

    * Developer Guide:
        * Added implementation details of the `filter`, `view`, `undo`, and `redo` command.


* **Community**:
    * PRs reviewed (with non-trivial review comments)
    * Reported bugs and suggestions for other teams in the class (examples:)
