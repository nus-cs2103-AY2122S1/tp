---
layout: page
title: Ryan Tian's Project Portfolio Page
---

### Project: WhereTourGo

**WhereTourGo (WTG)** is a desktop app for managing your tour contacts, optimized for use via a Command-Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added `summary` to view statistics about your contact list.
    * What it does: Allows the User to view a summary of their contacts, on app launch, or using the `sum` command. Updates whenever edits are done to the contact list.
    * Justification: This feature allows the user to view useful statistics such as the total number of contacts in the contact list, the proportion of contacts in categories and ratings.
    * Highlights: This feature required interfacing with all the different components (especially commands) in the app in order to summarise the data. Additionally, it needed to sync with the UI and refresh whenever there were updates.


* **New Feature**: Added `review` field to allow addition of long paragraphs of text.
    * What it does: Allows the User to add reviews, long paragraphs of text, to their contacts.
    * Justification: This feature allows the user to make notes of their individual contacts.
    * Highlights: This feature required changing other features to support it, such as `edit`, `summary`, `add`.


* **New Feature**: Updated `delete` command to allow deletion by name.
    * What it does: Allows the User to delete contacts by name instead of searching for the index.
    * Justification: This feature allows the user to delete contacts more conveniently.
    * Highlights: This feature required changing the parser, edit command, and the structure of the entire delete command.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ryantianj&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true)


* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub.
    * Setting up the GitHub team org/repo.
    * Setting up tools e.g., GitHub, Gradle.
    * Managed the different pages of the project. (index.md, README etc.).
    * Reviewed and merged Pull Requests of other teammates.


* **Enhancements to existing features**:
    * Tweaked GUI settings such as minimum window size, displaying different information on the display pane.
    * Modified all commands that have no parameters (e.g. list, undo, redo, summary, view) to reject all additional inputs.
    * Modified Parser to not accept integers that have leading zeros (e.g. 000001 is not accepted).
    * Modified Summary class to update only when needed.
    * Modified Command Box to display results when clicking on the Contacts pane.
    * Modified Storage file locations, all in one file (data).
    * Tweaked error messages to be more readable.


* **Documentation**:
    * User Guide:
        * Added documentation for the features `sum` and `delete`: [\#151](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/151).
        * Reorganized the entire User Guide features in a more logical manner (sections) [\#151](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/151).
        * Added screenshots throughout the User Guide [\#183](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/183).
    * Developer Guide:
        * Added implementation details of the `sum` feature: [\#68](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/68).
        * Added implementation details of the `review` feature: [\#68](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/68).
        * Added implementation details of the `delete` (by name) feature: [\#190](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/190)


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#161](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/161), [\#91](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/91)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/ryantianj/ped/issues/8), [2](https://github.com/ryantianj/ped/issues/5), [3](https://github.com/ryantianj/ped/issues/6))
