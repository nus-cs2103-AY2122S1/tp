---
layout: page
title: Houten Teo's Project Portfolio Page
---

### Project: CallMeMaybe(CMM)

CallMeMaybe is a desktop application developed for freelance telemarketers.
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **findAll command**: Added the ability for users to search for all contacts that match all the keywords specified.
    * What it does: Allows the user to search multiple fields at once. For example, the person can search for a particular name
and address. The command will only return contacts that match **BOTH** the name and address. If a contact only matches one of the field,
it would not be returned.
    * Justification: This feature improves the product significantly because often times as a telemarketer, you are looking to narrow
down your target demographic in order to best sell your product. Therefore, this function offers them a quick way to easily find
all contacts that match all the descriptions of their target demographic.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=houtenteo&tabRepo=AY2122S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4rc` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI to show a `FullPersonCard` (Pull requests [#52](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/52) [\#75](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/75))
    * Improved the original `add` command to allow users to add in more optional fields. (Pull requests [#78](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/78))
    * Updated the original `find` command to allow users to search for contacts thought more than 1 field.
Originally, contacts could only be searched by name. Now they can be searched using `name`, `phone`, `email`, `gender`, `age`, `address`
`isDone`, `interest`. (Pull requests [#128](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/128))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`, `display`, `edit` `findAny`, `findAll`, `csv import/export`
    [\#95](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/95), [#110](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/110),
    [#111](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/111), [#114](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/114),
    [#115](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/115), [#116](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/116),
    [#117](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/117), [\#118](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/118),
    [#119](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/119/files)
    * Developer Guide:
        * Added implementation details of the `add` feature. [#89](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/89)
        * Updated the diagrams for the model and Ui portions. [#93](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/93)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#67](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/67), [\#107](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/107), [\#122](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/122),

