---
layout: page
title: Ng Yong Chuan's Project Portfolio Page
---

### Project: NewAddressBook

NewAddressBook is a desktop address book application used by secondary school teachers to manager their student and teacher contacts, optimized for use via a Command Line Interface (CLI). The user interacts with it using a CLI, and
it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `DeleteMeeting`
  * What it does: allows the user to delete a selected meeting with the given index. Meeting will only be deleted when the index input is valid.
  * Justification: This feature improves the product as it allows the user to delete any wrong meeting or any meeting that has passed to ensure that their NewAddressBook is updated and have no error.
  * Highlights: This enhancement paves way for future commands such as clearMeeting / massDeleteMeeting as they both will likely use the base of this code.

* **New Feature**: Added a `FormClass` field to Students
  * What it does: allows the user to add a form class field to students to identify which form class they are from.
  * Justification: This feature improves the product as the target audience (secondary school teacher) will likely require the form class of the student they are working with.

* **New Feature**: Added GUI for Meeting Window
  * What it does: Opens a window for meeting, using the #handleMeeting function.
  * Justification: This feature is the start for meeting window, we decided not to put it together in the main UI as it was meant to be more like a calendar that can be closed / open at any time.
  * Highlights: This enhancement was used for the showMeeting/quitMeeting command.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=t16&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=random689&tabRepo=AY2122S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
<div style="page-break-after: always;"></div>
* **Project Management**:
  * Updated the base code (`Logic`, `AddressBook`, `Model` and json related classes) so that it can handle two different classes student and teacher as opposed to the previous single person class. This allowed the rest to split the commands and work on each side individually [\#73](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/73)

* **Enhancements to existing features**:
    * Updated the GUI to cater to two different list, one for teacher one for students [\#73](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/73)
    * Updated the GUI for almost all the fields so they either are coloured (like tags) or have the identifier [\#50](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/50)
    * Updated the GUI to prevent the view to be bugged (Pull requests [\#124](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/124), [\#190](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/190), [\#244](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/244))
    * Wrote additional tests for existing features to increase coverage (Pull requests [\#146](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/146), [\#149](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/149), [\#254](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/254), [\#73](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/73))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `deleteMeeting` and `formClass` [\#150](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/150)
    * Did cosmetic tweaks to existing documentation of features : [\#188](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/188)
  * Developer Guide:
    * Added implementation details of the `copy` feature. [\#97](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/97)
    * Added implementation details of the `clear` feature. [\#196](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/196)
    * Did cosmetic tweaks to existing documentation of UI, Logic (Pull requests [\#125](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/125))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#103](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/103), [\#141](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/141), [\#55](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/55)

* **Team Based Task**:
  * Updated Product Icon [\#124](https://github.com/AY2122S1-CS2103-T16-3/tp/pull/124)
