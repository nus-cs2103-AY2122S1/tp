---
layout: page
title: Liu Chen En's Project Portfolio Page
---

### Project: RecruitIn

RecruitIn is a desktop app for recruiters in Singapore to keep track of the plethora of clients with different skill sets, availability and experience. It is optimised for quick text-based inputs via a Command Line Interface (CLI) while still having the ease of use of a Graphical User Interface (GUI).
This product will make recruiters’ lives easier through categorisation and filter features to easily access candidates they have in mind.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=twothicc)

* **New Features Implemented**: Added `show` command
[\#145](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/145),
[\#156](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/156),
[\#166](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/166),
[\#380](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/380)
  * What it does: The `show` command parses the user input and only returns unique search terms for the first prefix
  provided by the user.
  * Justification: As we expect users to add a large number of applicants to RecruitIn, this command will assist
  forgetful users in finding certain users.
  * Changed `ShowCommand` implementation to accumulate unique search terms from the list of all applicants rather than
  from the filtered list of applicants.
  * Added `ArgumentTokenizer#findAllPrefixSorted` method to return a list of user input prefixes sorted by their order
  of input. This is used to identify the first prefix provided by the user.
  * Drastically reduced the length of `ShowCommand` and `ShowCommandParser` by extracting parts of `ShowCommand#getUniqueCategoryInputs`
  into private methods.
  * Updated tests for `ShowCommand` and `ShowCommandParser` to account for changes in the implementation of `show` command.
  * Wrote tests for `ArgumentTokenizer#findAllPrefixSorted` method.

* **Enhancements to existing features**:
  * Modified Find Command to take in multiple prefix inputs.
  [\#56](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/56),
  [\#153](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/153)

  * Added `Role` category and integrated it for `add`, `edit` and `find` command:
  [\#56](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/56),
  [\#103](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/103)

  * Wrote tests for `Role` and `RoleContainsKeywordPredicate`:
  [\#84](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/84)

  * Wrote tests for `ArgumentTokenizer#tokenizeWithoutPreamble` method:
  [\#84](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/84)

  * Wrote integration tests in `FindCommandTest` for `Role`, `Email`, `Phone` and `Name` classes:
  [\#84](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/84)

  * Modify `Email` regex to ensure safer email inputs and wrote tests for the changes:
  [\#296](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/296)

  * Modify `Person#isSamePerson` to identify applicants with either same phone number or same email or both as duplicate
  and integrated changes into `add` and `edit` commands:
  [\#144](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/144),
  [\#297](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/297)

  * Wrote new tests and updated existing tests to account for changes to `Person#isSamePerson` method:
  [\#144](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/144),
  [\#297](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/297)

  * Modified the UI:
  [\#179](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/179),
  [\#183](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/183),
  [\#213](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/213),
  [\#244](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/244),
  [\#245](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/245),
  [\#335](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/335)
  
* **Documentation**:
  * User Guide:
    * Updated documentation for the feature `delete`:
    [\#10](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/10),
    [\#26](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/26),
    [\#34](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/34)
    * Added documentation for `find` command input specifications for `Name`, `Email`, `Phone` and `Role` prefixes:
    [\#104](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/104),
    [\#320](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/320)
    * Added and updated documentation for `show` command:
    [\#166](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/166),
    [\#166](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/166)
    * Added documentation for `add` command input specifications for `Name`, `Email`, `Phone` and `Role` prefixes:
    [\#173](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/173),
    [\#320](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/320)
    * Updated UI picture:
    [\#183](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/183)
    [\#355](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/355)
    * Added Table of Contents and Quick Introduction. Also introduced `{Advanced}` formatting:
    [\#189](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/189),
    [\#294](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/294),
    [\#320](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/320)
    * Updated documentation for the features `Mark` and `Unmark`:
    [\#294](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/294)
    * Updated documentation for the features `add`, `find` and `edit`:
    [\#294](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/294)
    * Added documentation for duplicate applicant specification:
    [\#297](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/297)
    * Added documentation for sample workflow in Quick Start:
    [\#320](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/320)
    * Added diagram to show parts of the UI:
    [\#203](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/203),
    [\#320](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/320)
    * Updated links to Java 11 download:
    [\#326](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/326)
    * Added Glossary and Basic Command Format:
    [\#335](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/335)
    * Added RecruitIn icon:
    [\#335](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/335)
    * Added warning against editing `preferences.json`:
    [\#407](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/407)

  * Developer Guide:
    * Added implementation details for the feature `show`:
    [\#203](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/203)
    * Updated activity diagrams for feature `add` and `edit`:
    [\#347](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/347)
    * Added design considerations for feature `show`:
    [\#347](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/347)
    * Added manual testing for feature `show`:
    [\#378](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/378)
    * Updated links to code and acknowledgements:
    [\#401](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/401)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  [\#160](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/160#discussion_r732770997),
  [\#160](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/160#discussion_r732779286),
  [\#337](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/337#discussion_r744070318),
  [\#337](https://github.com/AY2122S1-CS2103T-F11-2/tp/pull/337#discussion_r744070540)

  * Reported bugs for other team during PE Dry Run:
  [\#146](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/146),
  [\#148](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/148),
  [\#150](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/150),
  [\#153](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/153),
  [\#157](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/157),
  [\#163](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/163),
  [\#175](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/175),
  [\#180](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/180),
  [\#182](https://github.com/AY2122S1-CS2103-W14-3/tp/issues/182)





