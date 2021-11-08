---
layout: page
title: Zhuo Yang's Project Portfolio Page
---

### Project: SIASA

SIASA (Student Insurance Agent Sales Assistant) is a desktop app for students who are also part-time insurance agents to manage their clients and their policies. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Add a Warning class.
    * What it does: There are certain commands where it is good to warn the user if the input is suspected to be invalid. The Warning class allows commands to warn the user
      before proceeding with execution of the command.
    * Justification: This feature improves the product because it gives more freedom to the user, instead of blocking suspected invalid inputs. The warning will also help to
      catch potential errors that users may make while performing operations.
    * Highlights: Implementation of the warning window in the UI was challenging because the user interaction with the warning window had to be sent back to the UI component.
    * Credits: *To check similarity of strings, we calculated the edit distance between the uppercase of the string inputs. The method was adapted from [here](https://www.geeksforgeeks.org/check-if-two-given-strings-are-at-edit-distance-one/)*

* **New Feature**: Add ability to download statistics as CSV.
    * What it does: Allows the user to download the commission per contact and number of policies per contact (sorted in descending order) as CSV files.
    * Highlights: This features involved updating of the `Model` component to keep track of the links between policies and contacts. Additional
      methods are added to the method to allow retrieval of the data by the `Download` command.
    * Justification: This feature will be useful for busy student financial advisors, as it provides a summary of their clients and their sales performance. The CSV
      data is also convenient as it can be used in other applications like spreadsheets.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=zhuoyang125&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=zhuoyang125&tabRepo=AY2122S1-CS2103-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub
    * Helped to maintain the GitHub issue tracker and set milestones
    * Made general updates to the user guide and website
  **Review/Helping Contributions**:
    * Fixed bugs found in the application (PR [\#43](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/43) [\#72](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/72), [\#183](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/183))
    * Helped to update/enhance certain parts of the code (PR [\#44](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/44), [\#76](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/76))

* **Documentation**:
    * User Guide:
        * Added documentation for the `download` feature [\#70](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/70/files)
    * Developer Guide:
        * Updated the `UI` component along with the explanation on how the component works
        * Added implementation of the `Download` feature, along with the explanation of design considerations and diagrams
          to explain how the feature works.
        * Added implementation details of the `Warning` class, along with the explanation of design considerations and diagrams
          to explain how the feature works.

* **Community**:
    * PRs reviewed (with non-trivial review comments): PR [\#60](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/60)
    * Reported bugs and suggestions for other teams in the class, which can be found [here](https://github.com/zhuoyang125/ped/issues)
