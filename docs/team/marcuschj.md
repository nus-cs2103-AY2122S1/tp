---
layout: page
title: Marcus Chua's Project Portfolio Page
---

### Project: WhereTourGo

**WhereTourGo (WTG)** is a desktop app for managing your tour contacts, optimized for use via a Command-Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added `Rating` field for to tier contacts.
  * What it does: Allows users to assign between 1 and 5 'stars' to each contact. They can also filter for contacts using ratings. Ratings are implemented as an optional field for contacts which would otherwise be labeled as unrated.
  * Justification: This feature allows the users to further customise their experience and have a quick reference point of their previous experiences with a contact.
  * Highlights: This feature required changing other features to support it, such as `edit`, `add` and `filter`. The GUI design also had to be tweaked to accommodate the ratings field for each Contact card. Furthermore, the implementation underwent numerous iterations to accurately handle the optionality of this field.


* **New Feature**: Added `export` command to quickly share contact details.
  * What it does: Allows users to select individual contacts, or the entire contact list to be exported into a text file. The exported file will be available to users in the data directory.
  * Justification: This feature allows users to quickly retrieve the information which they may require on-the-go during a tour.
  * Highlights: This feature required changes to many components in the `Storage` class and intricate changes to the initialisation process for the application.


* **New Feature**: Reimplemented `filter` command to filter by multiple fields.
  * What it does: Filtering can be done by `rating` instead of previously where it was solely for categories.
  * Justification: This feature allows users to achieve more flexibility with the `filter` command.
  * Highlights: This feature required major changes to the filtering predicate and extensive testing to cover all possible edge cases.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=marcuschj&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true)


* **Project management**:
  * Tracked team progress to meet deadlines
  * Reviewed and merged Pull Requests of other teammates
  * Organised issues on GitHub, managing issue tracker
  * Managed quality and progress of User Guide and Developer Guide
  * Managed the different pages of the project. (index.md, README etc.)


* **Enhancements to existing features**:
  * Revamped the predicate and parser for Filter command to take in multiple fields.
  * Adjusted regex expressions for various information fields to restrict the input lengths.
  * Changed the GUI by changing the product icon, redesigning colour scheme and replacing all the fonts.


* **Documentation**:
  * User Guide:
    * Created Table of Contents [\#78](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/78).
    * Vetted language for the entire document [\#180](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/180).
    * Added entire About section for UG [\#160](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/160).
    * Added all usage scenarios for the commands [\#180](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/180).
    * Added documentation for the features `list`, `filter` and `export` [\#78](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/78).

  * Developer Guide:
    * Added implementation details of the `ratings` field and `export` command [\#69](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/69) [\#191](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/191).
    * Added use cases [\#19](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/19).
    * Added Effort section [\#201](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/201).
    * Conducted final proofread [\#201](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/201).


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#159](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/159), [\#68](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/68), [\#98](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/98)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/marcuschj/ped/issues/13), [2](https://github.com/marcuschj/ped/issues/12), [3](https://github.com/marcuschj/ped/issues/5), [4](https://github.com/marcuschj/ped/issues/2))
