---
layout: page
title: Jerome Soh's Project Portfolio Page
---

### Project: Intern Watcher

Intern Watcher (IW) is a desktop app for Human Resource Managers to manage internship applicants, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, IW can get your applicant management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo commands and redo previous undo commands.
  * What it does: Allows the user to undo a previously executed command, reverting the application to the previous state. Undo commands can be reversed by the Redo command.
  * Justification: It is common for users to make mistakes when entering commands. Hence, a redo/undo feature will be really helpful for users who need to rectify those mistakes, and redo them in case they change their mind.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It was challenging as it required existing commands to be modified and a new class that extends the existing InternWatcher class in order to support the undo/redo functionality. Some analysis was needed for design alternatives, such as the data structure to store a history of commands.
  * Credits: Code for classes `VersionedInternWatcher` referenced and reused from [@eugene3231](https://github.com/AY2021S1-CS2103T-W11-4/tp/blob/master/src/main/java/seedu/address/model/VersionedCliniCal.java).
  
  
* **New Feature**: Added `Course` field to applicants. It represents the applicant's course of study.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Jeromesyl&tabRepo=AY2122S1-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
  * Enhanced `undo` and `redo` features to display the previous command that was changed by undo or redo. ([\#127](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/127))
  * Added a lower bound value of `01/2020` for `GraduationYearMonth` field so that only recent or future applicants are valid. ([\#205](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/205))
  * Wrote additional tests for features and fields (`GraduationMonthYear` field, `Course` field, `undo` and `redo` features) ([\#205](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/205), [\#204](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/204), [\#142](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/142))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo` and `redo` ([\#124](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/124))
    * Did cosmetic tweaks to existing documentation of features `help`, `add`, `view`, `edit`, `delete`, `undo`, `redo`, and Command Summary section. Also updated user stories and use cases section. ([\#223](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/223/files))
    * Updated descriptions of new and existing fields ([\#208](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/208))
  * Developer Guide:
    * Added implementation details of the `undo` and `redo` features, including Sequence and Activity diagrams ([\#228](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/228), [\#118](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/118))


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#137](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/137))
  * Reported bugs and suggestions for other teams in the class ([ped](https://github.com/Jeromesyl/ped/issues))


* **Contributions to team-based tasks**:
  * Updated logo of InternWatcher [\#213](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/213)
  * Maintained issue trackers


* **Contributions to Project Management and Organisation**:
  * Organized and facilitated team meetings and discussions
  * Managed team tasks and deliverables
