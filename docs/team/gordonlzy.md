---
layout: page
title: Zi Yang's Project Portfolio Page
---

### Project: SafeFor(H)All

SafeFor(H)All is a desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to list residents by their Fast and Easy Testing(FET) and Test Kit Collection Deadlines.
  * What it does: allows the user to list all the residents whose fet or collection is due within a period of time. The user can also list the residents who are late for their fet and collection deadline given a date.
  * Justification: This feature allows the residents whose deadline is due or over to be listed to be used for the export function.
  * Highlights: This enhancement affects the export feature. It required a good analysis of the existing model classes. The implementation is moderately difficult as there many new classes and tests that had to be written.

* **New Feature**: Added the ability to add multiple residents to an event.
  * What it does: allows the user to add multiple residents to an event at a time. The user can choose to add the residents by their names or rooms.
  * Justification: This feature is the main building block to allow residents to be traced by their events they attended.
  * Highlights: This enhancement affects the trace command. The feature stores multiple data in a field, so it required an in-depth analysis of json storage alternatives and storage classes. The difficulty of this implementation arises from the conversion multiple residents data in String to Person class and vice versa.

* **New Feature**: Added the ability to remove multiple residents from an event.
  * What it does: allows the user to remove multiple residents from an event at a time. The user can choose to add the residents by their names or rooms.
  * Justification: This feature complements the include feature to allow the user to remove the users from an event if they added them by mistake or the residents themselves decide to not participate in an evnet
  * Highlights: This enhancement builds on top of include feature, with an existing design, this enhancement is easier than the last.

* **New Feature**: Added a command suggestion that suggests a command's parameters.
  * What it does: allows the user to look at the parameters as they type without having to refer to user guide. The parameters that are correctly typed are removed from the suggestion bar as the user types.
  * Justification: This feature improves the user experience by making reminding users the parameters of a command and flattens the learning curve of the application.
  * Highlights: This enhancement is used by all commands implemented. It required an in-depth analysis of event handling. The implementation was challenging as the feature had to handle many cases of user input and there are changes to the UI part.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=gordonlzy&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed [releases](https://github.com/AY2122S1-CS2103T-T15-4/tp/releases) `v1.4` on GitHub

* **Enhancements to existing features**:
  * Refactored `list` to `deadline` to better suit its function name [PR #122](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/122)
  * Implemented storage of multiple residents in a string and extract them [PR #128](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/128)
  * Contributions to testing:
    * Added ~1.4 kLoC in total for testing
    * Created new test utils, `TypicalEvents` and `EventBuilder`, for `Event` class
  * Contributions to the UI:
    * Showed the number of days a resident is late for FET [PR #59](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/59)
    * Came up with a Figma mockup for the UI design [Figma](https://www.figma.com/file/Xt0MjUdFjvB438sHOwurRm/safeforhall?node-id=0%3A1)
    * Revamped original GUI color scheme to better fit the UI mockup [PR #149](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/149)

* **Documentation**:
  * User Guide:
    * Added documentation for `list`(now `deadline`) and `include` [PR #93](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/93)
    * Added documentation for `exclude` and `command suggestion` [PR #152](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/152)
    * Did cosmetic tweaks to existing documentation of command summary: [PR #228](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/228)
    * Added annotated diagrams for `deadline`, `include` and `exclude`[PR #261](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/261).
  * Developer Guide:
    * Updated target user profile [PR #21](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/21).
    * Added implementation details of the `include` feature [PR #94](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/94).
    * Added manual testing for `include`, `exclude` and `deadline` [PR #260](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/260).
    * Added use cases for `include`, `exclude` and `deadline` [PR #260](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/260).
    * Updated storage uml diagram [PR #260](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/260).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [PR #63](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/63), [PR #145](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/145), [PR #146](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/146), [PR #151](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/151)
  * Reported [bugs and suggestions](https://github.com/gordonlzy/ped/issues) for other teams in the class.
