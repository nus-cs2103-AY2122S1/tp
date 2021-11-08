---
layout: page
title: Lim Ji Wei's Project Portfolio Page
---

### Project: CONNECTIONS

CONNECTIONS is a desktop app adapted from [AddressBook - Level 3](https://se-education.org/addressbook-level3/) for managing contacts and keeping track of birthdays.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) that is created with JavaFX.
It is written in Java and has about 10 KLOC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Jiwei99&tabRepo=AY2122S1-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **New Feature**: Added the ability to untag tags from contacts.
  * What it does: allows the user to remove one or multiple tags from a contact.
  * Justification: This feature improves the product because the user can now remove individual tags from a list of tags that the contact has, instead of having to rebuild the whole list of remaining tags.
  * Highlights: This enhancement improves usability of CONNECTIONS. Untag also informs the user if a tag to be removed from a contact is not found in the contact.


* **New Feature**: Added the ability to retrieve previous commands using `UP` and `DOWN` keys.
  * What it does: allows the user to retrieve and use previously entered commands using the `UP` and `DOWN` keys. Allows users to retrieve and use frequently used commands easily.
  * Justification: This feature improves the product because the user can trace back previously used commands to check what changes have been made, and also reuse previous commands without having to retype the whole command again.
  * Highlights: Changes in code has to be made for CONNECTIONS to read and process keystrokes. Future extensions include adding Command History to storage to allow users to retrieve commands used in previous program runs.


* **New Feature**: Added command assistant to assist users in forming commands.
  * What it does: allows the user to look at the command parameters and examples while the user is keying in the command.
  * Justification: This feature improves the product because users can now double-check to make sure that the command keyed is in the right format. It reduces the need for users to re-enter commands due to errors in the command.
  * Highlights: This enhancement provides a rudimentary version of input validation. As this function works using keystroke validation, steps have to be taken to exclude certain keystrokes, e.g. `ENTER`, so that it does not affect the current functionalities. A separate type of command, which is initiated by the `System` instead of `User` input, has to be created. Future extensions include showing users exactly what parameters are missing or entered wrongly.


* **Enhancements to existing feature**: Improved `Help` command usability.
  * Added functionality to allow `Help` to take in additional parameters so that users can easily obtain support for usage of commands. For example, users can now use `help` to obtain a list of available commands, and `help COMMAND` to get help for a specific command.
  * Refactored help messages for all commands to improve quality of help messages.


* **Enhancements to existing feature**: Added unit test for functions.
  * Added unit tests to for new and existing features to increase test coverage.
  * Features include `Help`, `Untag` and `Command History`.


* **Project management**:
  * Managed release `v1.3` on GitHub.
  * Created, managed and closed issues and milestones on GitHub to track todos and project progress.
  * Managed issues and milestones for `v1.3` and `v1.3b`. 
 

* **Documentation**:
  * User Guide:
    * Updated and refactored User Guide to align with features of CONNECTIONS ([\#20](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/20))
    * Added documentation for BIRTHDAY parameter ([\#20](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/20))
    * Added documentation for the `Tag`, `Untag` and `Help` commands ([\#20](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/20), [\#112](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/112))
    * Added documentation for `Command History` and `Command Assistant` features ([\#112](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/112))
  * Developer Guide:
    * Added implementation for features `Tags`, `Help`, `Command Assistant` and `Command History` ([\#94](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/94))([\#221](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/221))
    * Added use case for `Help` feature ([\#94](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/94))
    * Added activity diagram for `Command History` ([\#221](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/221))
    * Added sequence diagram for `Help` command ([\#221](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/221))
  * Index:
    * Refactored Index file for CONNECTIONS ([\#71](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/71))
  
    
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#177](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/177), [\#183](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/183)
  * Reported bugs and suggestions for other teams in the class: [Bug Report](https://github.com/Jiwei99/ped)
