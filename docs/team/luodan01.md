---
layout: page
title: Luo Dan's Project Portfolio Page
---

### Project: AniList

AniList is a desktop Command Line Interface app for managing animes that the user is watching, has watched, and finished watching. AniList is written in Java and the GUI is created with JavaFX.

AniList was created based off AddressBook - Level 3, an address book application used for teaching Software Engineering Principles.

Given below are my contributions to the project.

* **New Feature**: Added the functionality to view user statistics and created the associated UI.
  * What it does: allows users to view their user statistics (such as top genres) calculated from anime(s) in the list.
    The user statistics are represented with a bar chart and pie chart, displayed in a pop-up window upon
    executing the `stats` command.
  * Justification: Since our app is an anime tracker, this feature greatly enhances the user experience by
    granting users insights into their own preferences. It fits together well with our other features (such as the functionality to 
    tag anime(s) with genres) to form a cohesive product.
  * Highlights: Due to the limitations of JavaFX, many UI bugs materialised which required workarounds. We also
    discussed various alternative design considerations before going with the current implementation.
  * Credits: Other team members that assisted with this feature: Muhammad Faruq [[github](https://github.com/muhammad-faruq)],  
    who helped with theme switching for the statistics window and disabling the command box when the user statistics are on display.

* **Enhancements to existing features**:
  * Initial refactoring of code (PRs [\#41](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/41),  [\#57](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/57),  [\#59](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/59))
  * Wrote additional tests for existing features to increase coverage from 66.81% to 70.29% (PR [\#210](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/210))
  * Revamped the Clear command.
    * What it does: entering `clear` now prompts the user for a confirmation. The user can choose to confirm the command
      by entering `clear` again or cancel the command by entering any other input. In addition, anime(s) are now cleared
      based on the filtered list, meaning only what is currently displayed on screen is cleared.
      (PR [\#77](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/77))
    * Justification: As clearing anime(s) is an irreversible action that has relatively drastic consequences,
      a confirmation message is helpful in preventing unintended clearing of the anime list.
      The command is also now more flexible, as users can `find` anime(s) that fit particular criteria and
      clear only those anime(s).
    * Highlights: The implementation of this feature allows future developers to easily change an existing
    command to include a confirmation, or to implement new commands that require follow-up user inputs. 
    The implementation was challenging as it required changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=luodan01&tabRepo=AY2122S1-CS2103T-T10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the `clear` command and user statistics feature (PR [\#219](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/219/files))
  * Developer Guide:
    * Added implementation details of the `clear` command and user statistics feature (PRs [\#89](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/89), [\#219](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/219/files))

* **Community**:
  * Examples of PRs reviewed (with non-trivial review comments): [\#37](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/37#discussion_r722173369), [\#81](https://github.com/AY2122S1-CS2103T-T10-4/tp/pull/81#issuecomment-947517982) etc.
  * Reported 14 bugs for other teams during Mock PE.
