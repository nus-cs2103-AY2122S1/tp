---
layout: page
title: Jia Xian's Project Portfolio Page
---

### Project: CSBook

CSBook is a desktop application for Computer Science (CS) Teaching Assistants (TAs) to manage their students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to encrypt and decrypt CSBook's data file.
  * What it does: encrypts CSBook's JSON data file using AES 128.
  * Justification: this feature improves the product's security tremendously because a malware can no longer read the plaintext JSON file and retrieve student's particulars.
  * Highlights: this enhancement was rather challenging as I had no previous experience in implementing an encryption framework.
  
* **New Feature**: Added the ability to add and delete assessments for each student.
  * What it does: keep track of each student's assessments and flag out failing assessments.
  * Justification: this feature addresses the needs of CS TAs as they can now easily keep track of their student's academic performance. The auto-flagging system saves the time of CS TAs to analyse their students' results individually.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=tanjiaxian99&tabRepo=AY2122S1-CS2103T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Team-based tasks**:
  * Removed all instances of `Address`: [\#52](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/52)
  * Renamed all instances of `Phone` to `TelegramHandle` and updated test cases: [\#60](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/60)
  * Renamed all instances of `AddressBook` to `CSBook`: [\#87](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/87)
  * Updated sample data to incorporate new features: [\#123](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/123)
  * Managed releases `v1.3-trial` - `v1.3.1` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `addassessment` and `deleteassessment`: [\#111](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/111)
    * Updated images to reflect the new UI: [\#123](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/123)
    * Fixed bugs: [\#203](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/203)
  * Developer Guide:
    * Added implementation details of the JSON Encryption feature [\#87](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/87)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#39](https://github.com/AY2122S1-CS2103T-T09-3/tp/pull/39)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/119#issuecomment-907666187))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-W17-1/tp/issues/174), [2](https://github.com/AY2122S1-CS2103T-W17-1/tp/issues/190), [3](https://github.com/AY2122S1-CS2103T-W17-1/tp/issues/203))
