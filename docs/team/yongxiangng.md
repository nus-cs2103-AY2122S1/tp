---
layout: page
title: Ng Yong Xiang's Project Portfolio Page
---

### Project: ForYourInterest

ForYourInterest - ForYourInterest is a desktop app for managing members in university clubs/societies, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the details command.
  * What it does: Allows the user to view the details of a member.
  * Justification: This feature improves the product significantly as the user is able to view the details of a member when needed. This declutters the other views which improves the product.
  * Highlights: This feature required a UI modification. I was able to implement the feature while adhering to most of the components used in the original code by creating a detailed version of the person card component in the UI.

* **New Feature**: Added event tracking features (`event` command and `addPersonToEvent` command).
  * What it does: Allows the user to create an event and add members into the attendee list.
  * Justification: This is a key feature of our application. This allows for member management when the members attend an event.
  * Highlights: This feature touched many components of the code base (`model`, `ui`, `logic` involved). It required the application to store a list of events, and a new view for the list of events.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=yongxiangng&tabRepo=AY2122S1-CS2103-T16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.1` - `v1.3` (4 releases) on GitHub
  * Managed opening and closing of milestones `v1.1` - `v1.3` (3 milestones) on GitHub

* **Enhancements to existing features**:
  * Added a tabbing feature [\#50](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/50)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `details`, `event` and `addPersonToEvent` [\#79](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/79)
    * Did cosmetic tweaks to existing documentation of command summary [\#85](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/85)
  * Developer Guide:
    * Added implementation details of the `event` feature [\#64](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/64)

* **Community**:
  * PRs reviewed with non-trivial review comments [\#45](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/45), [\#46](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/46), [\#65](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/65), [\#84](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/84)
  * Proactively help teammate to find cause of their own bugs [\#30](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/30)
  * Provided technical help in [forum discussions](https://github.com/nus-cs2103-AY2122S1/forum/issues/134#issuecomment-908405186)
  * Notified Team F10-3 and Team T16-2 that they have set up their organisation repo under the wrong name.

* **Tools**:
  * Set up GitHub pages
  * Set up GitHub actions with codecov

* **Team tasks**:
  * Set up organisation repo
  * Set up issues tracker
  * Added screenshots for v1.3
