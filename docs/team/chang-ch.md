---
layout: page
title: Chang-CH's Project Portfolio Page
---

### Project: Intern Watcher

Intern Watcher (IW) is a desktop app for Human Resource Managers (HRs) to manage internship applicants. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 17 kLoC. Given below are my contributions to the project.

* **New Feature**: Added the ability to edit fields of multiple applicants at once.
  * What it does: allows the user to edit all currently displayed applicants in one go.
  * Justification: This feature improves the product significantly because a user can handle large volumes of applicants with ease when paired with the filter function.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. A Selection class was added, with a parser to go with it.
   The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added application status field to applicants, with enum values and colour coding.

* **New Feature**: Added institution field to applicants.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=chang-ch)

* **Project management**:
  * Managed releases `v1.3` (1 release) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI CSS (Pull requests [\#55](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/55),
  [\#60](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/60), [\#112](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/112), [\#120](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/120))
  * Wrote additional tests for existing features to increase coverage from 71.33% to 72.36% (Pull requests [\#85](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/85))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit` [\#159](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/159), [\#148](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/148), [\#139](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/139), [\#96](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/96), [\#95](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/95)
    * Updated screenshots for existing InternWatcher commands [\#141](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/141), [\#37](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/37), [\#139](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/139)
    * Refactored AddressBook references [\#193](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/193), [\#150](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/150), [\#65](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/65), [\#63](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/63)
  * Developer Guide:
    * Added implementation details for the features `edit` [\#117](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/117), [\#207](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/207)

* **Community**:
  * PRs reviewed (with non-trivial review comments):  [\#97](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/97), [\#89](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/89), [\#73](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/73), [\#123](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/123), [\#137](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/137),
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/310#issuecomment-942177518), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/141#issuecomment-912311529))
  * Reported bugs and suggestions for other teams in the class (examples: [ped](https://github.com/Chang-CH/ped/issues))
  * Some parts of the selection class I added was adopted by other class mates ([\#92](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/92))

* **Tools**:
