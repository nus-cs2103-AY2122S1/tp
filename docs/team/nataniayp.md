---
layout: page
title: Natania's Project Portfolio Page
---

### Project: WhereTourGo

**WhereTourGo (WTG)** is a desktop app made for tour guides to ease their contact management, optimized for use via a Command-Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added `Sort` command
    * What it does: allows the user to sort the displayed contact list—filtered or unfiltered—according to the user's preference: by name (in lexicographical order) or by rating (in descending order).
    * Justification: This feature aids the user in planning tours in two ways. Contacts sorted by name is easier to search and analyse visually, while those sorted by rating helps the user in making judgments of which contacts to include in the tours.
    * Highlights: This feature necessitates a permanent modification on the actual contact list for it to work and complement other methods such as `filter` and `find`.
      This requires implementing new methods in different layers to ensure the sorting preference is saved for future use.


* **Enhanced Feature**: Modified `find` command to find contacts based on more fields.
    * What it does: allows the user to find specific contacts using both partial and full keywords based on names, addresses, emails, phone numbers and reviews.
    * Justification: Oftentimes, the user may not remember the name of the contacts in mind. This flexibility in search therefore helps the user find the contacts more easily through other relevant details, such as reviews.
    * Highlights: This enhancement requires a major change in the predicate used by the command,
    so that it includes fields other than Name. Moreover, with more fields involved in the searching of the contacts, more comprehensive testing was implemented.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=nataniayp&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&tabOpen=true&checkedFileTypes=docs~functional-code~test-code~other)


* **Project management**:
    * Reviewed and merged Pull Requests of teammates.
    * Updated User Guide, Developer Guide and README on non-feature-specific sections
      such as FAQ and Installation Guide.


* **Enhancements to existing features**:
    * Adapted the predicate used in the Find command to include other fields defined as `Findable`.
    * Modified GUI to have a side display panel to show the details of a contact and allow the detection of MouseClicks.
    * Enhanced the design of individual contact cards to only display the necessary details.


* **Documentation**:
    * User Guide:
      * Added the documentation for the feature `find` and `sort`.
      * Contributed in writing the introduction of the guide.
      * Crafted more Frequently Asked Questions.

    * Developer Guide:
      * Added implementation details of the feature `find`.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#167](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/167), [\#64](https://github.com/AY2122S1-CS2103T-T12-2/tp/pull/64)
