---
layout: page
title: Daniel Tan's Project Portfolio Page
---

### Project: CONNECTIONS

CONNECTIONS is a Software Engineering Project built on top of [AddressBook - Level 3](https://se-education.org/addressbook-level3/)  
It adds new features to keep track of contacts' birthdays and to message groups of friends.


Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=cookiedan42&tabRepo=AY2122S1-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature** Added the ability to add tags to a contact without replacement
  * What it does: allows the user to add a tag to a contact without typing in all the existing tags
  * Justification: This feature improves the product significantly because a user will often want to add a new tag to an existing contact and should not need to input all the existing tags that contact previously had to do this action.

* **New Feature** Added the ability for users to export their current view as a mailing list
  * What it does: allows the user to construct a mailing list to contact multiple people based on their filtered address book
  * Justification: inviting people to events would require contacting multiple people with the same message, instead of sending individual messages, the user can just export all the contacts at once and use that to contact them
  * Credits: Uses [OpenCSV](http://opencsv.sourceforge.net/) to create the CSV files
  * Credits: Inspired by LUMINUS allowing Instructors to export CSV file of students details
  
* **Testing**
  * Wrote test cases for handling `tag` command
  * Wrote test cases for handling `mailingList` command
  * Wrote test cases for handling `Tag` command

* **Documentation**:
  * User Guide:
    * Did cosmetic and grammatical tweaks to existing documentation of features: [\#192](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/192)
  * Developer Guide:
    * Added user stories for versions 1.1 and 1.2 (Pull requests [\#13](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/13), [\#35](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/35))
    * Product scope for version 1.2 (Pull request [#39](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/39))
    * Added implementation details of the `mailingList` feature.
<div style="page-break-after: always;"></div>
* **Tools**:
  * Integrated a third party library (OpenCSV) to the project ([\#91](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/91), 
  * Improved `TestUtils` by adding the ability to create different AddressBook states for testing ([\#52](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/52))
  
* **Community**:
  * Reported bugs and suggestions for other teams in the class ([1](https://github.com/cookiedan42/ped/issues/6), [2](https://github.com/cookiedan42/ped/issues/7), [3](https://github.com/cookiedan42/ped/issues/9))
  * PRs reviewed (with non-trivial review comments): [\#42](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/42), [\#53](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/53)
