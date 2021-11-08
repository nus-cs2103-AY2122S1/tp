---
layout: page
title: Zi Yang's Project Portfolio Page
---

### Project: SafeFor(H)All

SafeFor(H)All is a desktop app for hall admins to keep track of hall residents’ information to keep hall residents safe during the COVID-19 pandemic via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to list residents by their Fast and Easy Testing(FET) and Test Kit Collection Deadlines.
  * What it does: allows the user to list all the residents whose fet or collection is due within a period of time. The user can also list the residents who are late for their fet and collection deadline given a date.
  * Justification: This feature contains the main logic to allow the residents whose deadline is due or over to be listed to be used as a building block for the export function.
  * Highlights: This enhancement affects the export feature. It required a good analysis of the existing model classes to create a new model class for Date. The implementation moderately difficult as there many new classes that had to be written and it is error-prone which required a good deal of tests.

* **New Feature**: Added the ability to add multiple residents to an event.
  * What it does: allows the user to add multiple residents to an event at a time. The user can choose to add the residents by their names or rooms.
  * Justification: This feature is the main building block to allow residents to be traced by their events they attended.
  * Highlights: This enhancement affects the trace command. The feature needs to convert the name or room from String to a Resident, which required a good deal of communication with the model manager and addressbook. Since it is storing multiple data in a field, it required an in-depth analysis of json storage alternatives and design of several classes. The implementation was challenging as the user can choose to input either names or rooms which will have to be converted to Person through calls to the model manager and the conversion from storage string to data is not easy as well.

* **New Feature**: Added the ability to remove multiple residents from an event.
  * What it does: allows the user to remove multiple residents from an event at a time. The user can choose to add the residents by their names or rooms.
  * Justification: This feature complements the include feature to allow the user to remove the users from an event if they added them by mistake or the residents themselves decide to not participate in an evnet
  * Highlights: This enhancement builds on top of the work done by include feature, with an existing design, this enhancement is easier than the last.

* **New Feature**: Added a command suggestion feature that suggests a command's parameters.
  * What it does: allows the user to look at the parameters as they type without having to refer to user guide. The parameters that are correctly typed are removed from the suggestion bar as the user types.
  * Justification: This feature improves the user experience and makes it more productive for a CLI user since the user does not need to navigate to an external site or enter a new command to view the parameters required. It also lowers the learning curve of the application and make it easier to pick up.
  * Highlights: This enhancement is used by all commands implemented. It required an in-depth analysis of event handling and the Observer Design Pattern. The implementation was challenging as the feature had to handle many cases of user input and there are changes to the UI part.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=gordonlzy&tabRepo=AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Refactoring `list` to `deadline` to better suit its function name [\#122](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/122)
  * Contributions to testing:
    * Added testing for the `include`, `exclude` and `deadline` features as well as new predicates, `ResidentList`, `LastDate`, `ModelManager` and `AddressBook`
    * Added ~1.4 kLoC in total for testing
    * Created new test utils, `TypicalEvents` and `EventBuilder`, for `Event` class
    * Updated `PersonBuilder` to build person with new `LastDate`
  * Contributions to the UI:
    * Show the number of days a resident is late for FET [\#59](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/59)
    * Coming up with a Figma mockup for the UI design [Figma](https://www.figma.com/file/Xt0MjUdFjvB438sHOwurRm/safeforhall?node-id=0%3A1)
    * Revamped original GUI color scheme, css styles and fxml files to better fit the UI mockup (Pull requests [\#149](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/149))
  * Contributions to Storage:
    * Added a way to store multiple residents in a string and a way to extract them for JsonAdaptedEvent [\#128](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/128)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `list`(now `deadline`) and `include` [\#93](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/93)
    * Added documentation for the features `exclude` and `command suggestion` [\#152](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/152)
    * Did cosmetic tweaks to existing documentation of command summary: [\#228](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/228)
    * Added annotated diagrams for `deadline`, `include` and `exclude`[\#261](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/261).
  * Developer Guide:
    * Updated target user profile [\#21](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/21).
    * Added implementation details of the `include` feature [\#94](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/94).
    * Added manual testing for `include`, `exclude` and `deadline` [\#260](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/260).
    * Added use cases for `include`, `exclude` and `deadline` [\#260](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/260).
    * Updated storage uml diagram [\#260](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/260).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#63](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/63), [\#145](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/145), [\#146](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/146), [\#151](https://github.com/AY2122S1-CS2103T-T15-4/tp/pull/151)
  * Reported [bugs and suggestions](https://github.com/gordonlzy/ped/issues) for other teams in the class.
