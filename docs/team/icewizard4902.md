---
layout: page
title: Nguyen Quang Vinh's Project Portfolio Page
---

### Project: ForYourInterest

ForYourInterest - ForYourInterest is a desktop app for managing members in university clubs/societies, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added more functionalities to the `clear` command
    * What it does: allows the user to clear the elements they want (persons/events) in a specific range that they want.
    * Justification: This feature improves the product significantly because a user can delete multiple entries at once using a single `clear` command instead of using multiple `delete` commands.
    * Highlights: This enhancement suggests the use of `flags` to differentiate commands operating on the member or event list. This feature was initially planned to delete entries before/after a certain date but was later discarded due to lack of time for proper development. I was able to come up with a version of `clear` that provides a sensible improvement in user experience.
    
* **New Feature**: Added functionality in the Storage component to store the event list in a Json format
    * What it does: allows the user to save the event list of the program in a Json format. This help the user retain the event list for later use of the program.
    * Justification: Since the product has a new addition - the `event`, it is logical to configure the Storage component to store this data.
    * Highlights: This feature touched many components (`model`, `ui`, `Storage` involved). I have to make sure that the saved file in Json format works well (mitigate as many bugs as possible) with the iteration of the product at the time of development. Also this feature requires a thorough understanding of the `Storage` component.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T16-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=IceWizard4902&tabRepo=AY2122S1-CS2103-T16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Lead the discussion for some of the team's weekly meeting
    * Show up on time for meetings.

* **Enhancements to existing features**:
    * Improved the `clear` command as mentioned above in the new feature.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `clear`, `list`
    * Developer Guide:
        * Added updated view of the `Storage` component and `clear` command enhancement.
