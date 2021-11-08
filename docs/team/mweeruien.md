---
layout: page
title: Megan's Project Portfolio Page
---

### Project: Staff'd

Staff'd is a desktop staff tracking application used managing F&B staff. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10k Lines of Code.

Below are my contributions to the project.

* **New Feature**: Added the `viewShift` Command which allows employees to search for staff working on a particular shift.
  * What it does: Allows users to search for a specific shift by indicating the day of the week, and either a specific time or the shift number.
  * Justification: This feature improves the product significantly because employers can now quickly and easily access the list of staff working at any particular shift.

* **New Feature**: Added the ability to set the role requirements for each shift. 
  * What it does: Allows the users to set the requirements for each role every shift. The role requirements will be displayed when the command is called. Additionally, Staff'd will refer to these role requirements to check for staff shortages when the `viewShift` command is utilized.
  * Justification: This feature allows managers to quickly ascertain whether they have sufficient staff of each role working for a particular shift. 
  * Highlights: A data file will be created to store the role requirements information so that users only need to adjust the role requirements once.

* **Enhancements to existing features**:
  * Enhanced `find` command to search by indexes. [\#72](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/72)
  * Enhanced `clear` command to also reset the save file for role requirements

* Conducted manual testing and fixed bugs and help messages for v1.4. [\#299](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/299)

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=mweeruien&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=mweeruien&tabRepo=AY2122S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* Designed and created the **Staff'd Icon**

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub
  * Managed issue tracking
  
* **Documentation**:
  * User Guide:
    * Organized User Guide and added new sections such as GUI Breakdown, User Guide Usage, and Glossary. Improved sections such as Flag Legend and Introduction. [\$330](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/330)
    * Added documentation for the features: `find` [\#72](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/72), `viewShift`[\#102](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/102), `setRoleReq` [\#149](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/149/)
  * Developer Guide:
    * Added implementation details for the `find` and `viewShift` features
    * Updated diagrams for the `find` and `viewShift` features [\#119](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/119)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#47](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/47/), [\#136](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/136). [\#241](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/241), [\#282](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/282)
