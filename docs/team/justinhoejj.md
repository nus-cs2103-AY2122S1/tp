---
layout: page
title: Justin Hoe's Project Portfolio Page
---

### Project: CONNECTIONS

CONNECTIONS is a desktop address book application adapted from AB3. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java and has about 10 KLOC.

Given below are my contributions to the project.

* **New Feature**: Added a Birthday Reminder List
    * The list displays contacts sorted by birthdays in ascending order, starting with the
      nearest upcoming birthday all the way through the latest birthday and then looping back
      to the earliest birthday.
    * The list is responsive to changes in contact details be it using add, delete or edit command. The list
      reorders contacts where appropriate to ensure it stays sorted as described above.
    * Birthday at present day and upcoming birthdays within 7 days are color coded and provided with personalised message.
    * This feature would allow our target user, a person who loves birthday celebrations, always wish contacts on
    their birthday.
    * Account for birthdays that fall on leap days by ensuring they are brought forward to 28th Feb on non leap years.

* **New Feature**: Added Delete Multiple Command
    * Delete Multiple Command allows the user to delete multiple contacts with a single command by providing
      a start and end index.
    * This feature will allow our target user to delete contacts of the same group. Groups are usually identifiable
      with find by tags, upon filtering contacts of the same group the user can selectively delete those contacts.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Justinhoejj&tabRepo=AY2122S1-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Create and configure team repo with git hub actions, code cov and branch protection rules.
    * Create labels and milestones for tracking issues and managing deadlines.

* **Enhancements to existing features**:
    * Implement additional test cases for Birthday attribute [\#51](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/51).
    * Implement an additional panel in UI to contain the birthday reminder list [\#81](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/81).
    * Increase size of result display box minimum width of application [\#175](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/175).

* **Documentation**:
    * User Guide:
        * Specify Birthday field is optional [\#116](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/116).
  * Developer Guide:
    * Describe implementation details of birthday reminder list [\#93](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/93).
    * Add instructions for manual testing of all commands [\#204](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/204).
    * Fix UML sequence diagrams [\#206](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/206).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#74](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/74), [\#86](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/86), [\#87](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/87).
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/91), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/221), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/328)).
    * Reported [bugs and suggestions](https://github.com/Justinhoejj/ped/issues) for other teams in the class.
