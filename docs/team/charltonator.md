---
layout: page
title: Charlton Tan's Project Portfolio Page
---

### Project: ModuLink

ModuLink is a desktop-based application for Computer Science (CS) students at NUS to facilitate finding teammates for group-based modules. It allows you to find students taking the modules you are interested in, search by their group status (to find students available to form or join groups), and much more. ModuLink has been developed using Java and has about 18k LoC.

Given below are my contributions to the project.

* **New Feature**: Added the findId feature.
  * What it does: allows users to search for other profiles by Student ID.
  * Justification: This feature was necessary as each profile has a unique Student ID and thus searching for a specific profile is much easier.
  * Highlights: Only profiles whose student ID match the specified ID exactly will be shown. Implementation was also relatively simple as it was simply checking every profile.

* **New Feature**: Added the addFav feature.
  * What it does: allows users to add another profile as a favourite in ModuLink.
  * Justification: This feature was necessary as having favourited profiles is a key feature of ModuLink. It allows users to keep track of profiles they are interested in.
  * Highlights: This feature is complemented by the remFav feature. Together they form a key part of ModuLink's functionality.

* **Feature Enhancements**: Enhanced the GUI by adding rounded corners to each Card in the list, expanding result box and adding colour to highlight the current User's profile.
  * Justification: This feature was necessary as it would make the GUI more aesthetically pleasing and also allow the User to easily identify his own profile.
  * Highlights: These enhancements were done by gaining a better understanding of how JavaFx works.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=charltonator&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Project management**:
    * Managed release `v1.3.trial` on GitHub
    * Assigned issues and merged PRs for team.

* **Documentation**:
  * User Guide:
    * Fixed unclear instructions.
    * Added features to User Guide (Pull requests [\#39](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/39))
  * Developer Guide:
    * Added function table to Developer Guide. (Pull requests [\#48](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/48))
    * Checked for areas which needed diagrams to be updated. (Pull requests [\#84](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/84))
