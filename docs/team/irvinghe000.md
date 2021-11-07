---
layout: page title: Outong's Project Portfolio Page
---

### Project: Staff'd

Staff'd is a desktop address book application used for teaching Software Engineering principles. The user interacts with
it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an add shift command that allows the user to add shift to a staff's schedule.
    * What it does: It searches the target staff with given index or name, and add a shift to
    * Justification: This is a fundamental command user needs when they manipulate the schedule of staffs. User can add
      a new shift to a specific slot on a specific day to the target staff.

* **New Feature**: Added the ability to change the time of a specific shift.
    * What it does: It locates a shift first with the given index, staff's name, date and slot, then it updates the time
      of that shift with the given start time and end time.
    * Justification: This functionality improves the software's flexibility significantly, because when a shift is
      added, it will only be set to the default time. With this command, users can update the time according to the real
      situation.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2021-09-17&tabOpen=true&tabAuthor=IrvingHe000&tabRepo=AY2122S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub
    * Managed milestone tracking

* **Enhancements to existing features**:
    * Updated existing fields name to adapt our product (Pull requests
      [\#29](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/29))
    * Add new attributes to Person (Pull requests
      [\#37](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/37)
      [\#93](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/93))

* **Documentation**:
    * User Guide:
        * Add documentation for features `addShift` and `setShiftTime`
          (Pull requests
          [\#73](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/73)
          [\#132](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/132)
          [\#241](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/241))
        * Add UI mockup at the beginning
          (Pull requests
          [\#16](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/16)
          [\#40](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/40))

    * Developer Guide:
        * Add documentation for implementing `addShift` command
          (Pull requests
          [\#116](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/116)
          [\#120](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/120))
        * Add documentation for acknowledgement, manual testing, and proposed future features
          (Pull requests
          [\#292](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/292))

* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#47](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/47),
      [\#84](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/84),
      [\#92](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/92),
      [\#124](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/124),
      [\#233](https://github.com/AY2122S1-CS2103T-W11-2/tp/pull/233)
