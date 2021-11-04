---
layout: page
title: Felix Ong's Project Portfolio Page
---

### Project: SportsPA

SportsPA is a desktop application used to manage membership and training sessions of NUS sports CCAs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Created availability field and added the ability to add and set member availability.
    * What it does: Allows the user to add a new member with the given availability and set the availability of existing members.
    * Justification: This feature sets the basis for one of the core features of our application, the splitting of members to facilities based on availability.
    * Highlights: Implementing the availability field required some analysis regarding the type of variables to use to represent it to ensure consistency and ease of conversion from user input. The use of the DayOfWeek enum from the java.time package was slightly tricky
      due to the initial lack of familiarity with enums and the package.
    * Pull Request [\#109](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/109)

* **New Feature**: Added the ability to allocate and deallocate members from facilities.
    * What it does: Allows the user to allocate members to facilities and deallocate members from facilities manually on a given day.
    * Justification: Provides an alternative for the user to allocate members other than the split command, as the split command may not provide the ideal allocation mapping of members to facilities.
    * Highlights: Changes had to be made to all commands that affects a member as changes to a member may result in an invalid allocations.
      For example, there is a need to ensure that a member that has been deleted from the member list is removed from all allocations.
      Ensuring that the UI displaying the facilities updates immediately when member-related commands were executed was challenging.
    * Pull Request [\#160](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/160)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=felix-ong)

* **Team Tasks**:
    * Set up project notes document and suggested initial idea for the project.
    * Opened milestone v1.3 and v1.4.
    * Updated User Guide to include a section explaining the usage of the User Guide to improve user-friendliness. (Pull request [\#152](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/152))

* **Enhancements to existing features**:
    * Added realistic constraints to facility-related fields and added tests related to these constraints, improved the format of the displayed values in the UI.
      (Pull request [\#196](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/196))
    * Improve `split` feature by allowing splitting members into facilities on different days and displaying all the allocations by day.(Pull request [\#218](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/218))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `listf`,`findf` and `deletef` (Pull Request [\#66](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/66))
        * Added documentation for the features `allocate` and `deallocate` command (Pull Request [\#160](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/160))
        * Updated screenshots and notes to users for `editm`, `editf`, `split`, `allocate` and `deallocate` commands (Pull Request [\#227](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/227))
    * Developer Guide:
        * Added implementation details of the `split` feature as well as UML diagrams for illustration

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#98](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/98), [\#89](https://github.com/AY2122S1-CS2103T-W12-1/tp/pull/89))
