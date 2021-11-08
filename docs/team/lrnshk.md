--- 
layout: page
title: Lauren Shak Luo Jun's Project Portfolio Page
---

### Project: Track2Gather

Track2Gather is a desktop application that will manage up to a few thousand contacts, providing basic features for contact tracing personnel to organise and search through them via personal information, case numbers, and Stay-Home-Notice periods. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has
about 10 kLoC.

Given below are my contributions to the project.

* **Project management**:
    * Managed all 4 milestones `v1.1` - `v1.4` [1](https://docs.google.com/document/d/1jbtRSaHlnexnYxPhYpj3gJPLz4OIChug_-JXG9mZzu4/edit?usp=sharing), [2](https://docs.google.com/document/d/1AymhqStdinlqTXnOp1R0E5SoCpsFksR5JHcFaOF0xq8/edit?usp=sharing), [3](https://docs.google.com/document/d/1WiQIc3bFRgt9c7Haxpm9BsOh-TZ-Lq9N0HgvqPpppVo/edit?usp=sharing), [4](https://docs.google.com/document/d/1FFBa9NwFsEhYte5eWRV_goOYHk_BOCp67Wfmp_sREk0/edit?usp=sharing)
    * Managed the issue tracker
    * Prepared agendas for and headed weekly project team meetings:
        * to guide conceptualisation of features
        * to delegate project work
        * to set goals for the iterations
        * to set timelines
        * to track all deliverables and their progress


* **New Feature**: Added the ability to clear all persons whose SHN periods have been completed [\#88](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/88)
    * What it does: allows the user to remove all persons whose SHN periods have been completed at current system time. 
      User does not need to manually check which persons have completed their SHN as the functionality is performed automatically 
      and internally with a single command.
    * Justification: This feature improves the product significantly because a user does not need to maintain persons whose SHN is completed as they no longer need to be tracked. 
      The app should provide a functionality to remove them easily.


* **New Feature**: Added the ability to delete multiple persons in any order [\#87](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/87), [\#123](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/123)
    * What it does: allows the user to delete multiple persons at a time in any order.
    * Justification: This feature improves the product significantly because a user may wish to mass-delete unneeded persons without 
      having to worry about in which order they specify these persons.


* **New Feature**: Conceptualised SHN enforcement mode [\#134](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/134)
    * What it does: allows the user to track the call statuses of persons in the app in order to enforce SHN. Best explained in the [User Guide](https://ay2122s1-cs2103-w14-2.github.io/tp/UserGuide.html#shn-enforcement-mode).
    * Justification: This feature improves the product significantly because a user of our target profile will need to call the persons who are on SHN in order to enforce the relevant regulations, as part of their contact tracing duties. 
      With this feature, a user can easily record whether they have called each person in the current session, as well as track non-compliance instances in order to subject these offenders to further discipline, if needed.
    * Highlights: This enhancement affects four commands: `session`, `schedule`, `scall`, and `fcall`, which work together to implement SHN enforcement mode.
    * Credits: Conceptualisation of this entire feature was done in conjunction with [Wei En](https://github.com/wei2912), whereas implementation was done by [Andrew](https://github.com/over-fitted).


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=lrnshk&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=lrnshk&tabRepo=AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing and new features**:
    * Improved the existing features to track the SHN periods of persons [\#47](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/47)
    * Improved the existing features to work with persons of the same name [\#121](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/121)
    * Improved the existing and new features to display more user-friendly and comprehensible output messages [\#221](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/221)


* **Documentation**:
    * Project Website
        * Updated Landing Page for `v1.3` milestone. [\#118](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/118)
    * User Guide:
        * Added all documentation for the features `delete`, `clear`, `session`, `schedule`, `scall`, and `fcall`. [\#124](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/124), [\#135](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/135), [\#209](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/209)
        * Ensured overall cohesion for milestones `v1.3` and `v1.4`. [\#137](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/137), [\#219](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/219)
    * Developer Guide:
        * Added all documentation for the features `delete`, `clear`, `session`, `schedule`, `scall`, and `fcall`. [\#92](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/92), [\#93](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/93), [\#135](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/135)
        * Updated all UML diagrams for `v1.3` milestone. [\#117](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/117)
        * Ensured overall cohesion for milestones `v1.2` - `v1.4`. [\#81](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/81), [\#137](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/137), [\#218](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/218)


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#89](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/89), [\#207](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/207), [\#247](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/247)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-W10-2/tp/issues/205), [2](https://github.com/AY2122S1-CS2103T-W10-2/tp/issues/207), [3](https://github.com/AY2122S1-CS2103T-W10-2/tp/issues/200), [4](https://github.com/AY2122S1-CS2103T-W10-2/tp/issues/197), [5](https://github.com/AY2122S1-CS2103T-W10-2/tp/issues/193), [6](https://github.com/AY2122S1-CS2103T-W10-2/tp/issues/189))
