---
layout: page
title: Yat Bun's Portfolio Page
---

## Project: SIASA

SIASA (Student Insurance Agent Sales Assistant) is a desktop app for students who are also part-time insurance agents to manage their clients and their policies. THe user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added Policy Commands
  * Added CRD commands for Policies and special command to delete all policies belonging to a specific contact (PR [\#31](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/31)).
  * Added U command for Policies (PR [\#67](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/67)).
  * What it does: Allows the creation, viewing, editing and deletion of Policies. Policies are a key feature of SIASA as they are the things that SIASA helps the user keep track of.
  * Justification: This feature is a core aspect of the application and hence greatly improves the product.
  * Highlights: Before implementing this feature, I did an extensive analysis of design alternatives as I wanted to use the cleanest implementation of creating the association between Contacts and Policies. I considered passing the raw user input to the command and also using a shell policy with an empty owner field. However, I ended up passing the contact's `Index` to the command for it to extract the Contact reference from the model.

* **New Feature**: Added Sorting Capabilities
  * Implemented filtering back-end and commands (PR [\#71](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/71)).
  * What it does: It allows users to sort both the Contacts and Policies according to a few different fields.
  * Justification: This feature enhances the user experience as it allows the user to easily navigate the data in the application.
  * Highlights: I aimed to implement the sorting generally, which are dictated by Comparators. This allows for the extension of the sorting feature by just introducing more comparators in the future. It was implemented with a `SortedList` wrapped within the `FilteredList` in the Model.

* **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=yatbun&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=yatbun&tabRepo=AY2122S1-CS2103-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project Management**:
  * Triage issues created by team, beta testers or PE-D
  * Coordinated team meetings and deadlines
  * Contributed to team ideation and direction
  * Helped review Pull Requests and give feedback from improving code quality
  * Increased testing coverage (PR [\#162](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/162))
  * Fixed bugs in application (PRs [\#135](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/135), [\#137](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/137), [\#140](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/140), [\#141](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/141), [\#149](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/149) and [\#156](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/156))

* **Enhancement to Existing Features**:
  * Added `contactpolicy` command that made use of a filter that took in a `Contact` as parameter (PR [\#31](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/31)).
  * Added `expiringpolicy` command taht made use of a filter that displayed policies with expiry dates within the range of a month from the current date (PR [\#141](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/141)).

* **Documentation**:
  * User Guide
    * Added features and commands to be implemented in the future (PR [\#16](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/16))
  * Developer Guide
    * Updated Logic Class section (PRs [\#45](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/45) and [\#201](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/201))
    * Described Sorting and Filtering implementation (PRs [\#191](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/191) and [\#201](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/201))

* **Community**:
  * Regular PR reviews (with non-trivial comments).
  * Helped find bugs for Team CS2103T-T13-1 during PE-D.
