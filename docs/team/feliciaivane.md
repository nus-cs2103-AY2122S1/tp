---
layout: page
title: Felicia's Project Portfolio Page
---

### Project: InternSHIP

InternSHIP is a desktop app for university students to manage their internship applications.
InternSHIP has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the priority field.
  * What it does: Allows internship applications to be tagged with 3 possible priority levels: Low, Medium or High (Pull request [#107](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/107)).
  * Justification: This feature improves the user experience significantly since users can now tag their applications with different priorities. This allows the user to see which applications are more important or should be completed first.
  * Highlights: This enhancement affected existing commands and many test cases needed to be changed too.

* **New Feature**: Added the ability to sort applications in the list.
    * What it does: Allows the user to sort the currently displayed application list by a specified field (Pull request [#107](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/107)).
    * Justification: This feature improves the product significantly since users can now view their applications in a more organised manner.
    * Highlights: This enhancement allows users to sort by almost all the fields in the application, including any new fields that might have been added after this enhancement was implemented.
    * For example, the Interview Time field was added after the initial implementation of this command, so additional changes had to be made to accommodate sorting by interview time as well (Pull request [#140](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/140)).

* **Enhancements to existing features**:
  * Finished implementation of Complete class (Pull request [#58](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/58)).
  * Refactored Complete class to Completion (Pull request [#58](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/58)).
  * Added validity checks for Completion and Status.
  * Made small changes to GUI to accommodate for changes in formatting when a JAR file is created (Pull request [#227](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/227)).
  * Wrote additional tests for existing features to increase coverage (Pull requests [#63](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/63), [#68](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/68), [#79](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/79), [#109](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/109), [#238](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/238)). Notably, pull request [#238](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/238) increased coverage by 6.62%.
  * Fixed bugs from Practical Examination Dry Run (Pull request [#222](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/222), [#231](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/231)).
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=feliciaivane&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)


* **Project management**:
    * Managed release `v1.2.1` - (1 release) on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete`, `clear` and `sort`.
        * Added "Information about this User Guide" section.
        * Added "How to use this User Guide" section.
        * Added "Tips" section.
        * Added "Prefix summary" section.
        * Added "Glossary" section.
        * Updated "Acknowledgements" section to include all sources used.
        * Split "Command summary" section into different tables.<br>
      (Pull requests [#32](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/32), [#108](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/108), [#129](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/129), [#132](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/132), [#138](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/138), [#147](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/147), [#162](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/162), [#257](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/257), [#260](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/260)).
    * Developer Guide:
        * Added detailed UML diagram and implementation details of the `sort` feature.
        * Added use cases for `add`, `edit`, `complete`, `accept`, `reject`, `delete` and `sort` commands.
        * Updated Appendix 2: Instructions for manual testing.<br>
        (Pull requests [#40](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/40), [#104](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/104), [#150](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/150), [#257](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/257), [#260](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/260)).
    * Miscellaneous:
      * Update other `.md` files to fit InternSHIP (e.g. `README.md`, `SettingUp.md`) <br> 
      (Pull requests [#27](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/27), [#134](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/134), [#139](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/139), [#257](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/257)).

* **Team tasks**:
  * Set up collaborative doc folder (Google Drive) for project notes.
  * Take meeting minutes for all meetings.
  * Set up [tP Team Organization](https://github.com/orgs/AY2122S1-CS2103T-W17-1/people).
  * Set up tP Team Repo.
  * Set up and consistently update [project board on GitHub](https://github.com/AY2122S1-CS2103T-W17-1/tp/projects).
  * Record video for [v1.3 features demo](https://drive.google.com/file/d/1s_Yq3-z0y3xv4j2otn74YgXDKLdJ1LPK/view?usp=sharing).

* **Community**:
  * Reported an above-average number of bugs in the PE-D (Examples: Bug [#2](https://github.com/feliciaivane/ped/issues/2), Bug [#5](https://github.com/feliciaivane/ped/issues/5), Bug [#10](https://github.com/feliciaivane/ped/issues/10)).
