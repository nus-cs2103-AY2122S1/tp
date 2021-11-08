---
layout: page
title: Yang Yuzhao's Project Portfolio Page
---

### Introduction

This page serves to document my contributions to the project ezFoodie under NUS module CS2103T in AY21/22 semester 1.

### Project: ezFoodie

ezFoodie is a desktop application that helps restaurants **keep track of their ever-growing list of members**.
Restaurant managers and staffs can easily view and update member status (e.g. personal information, tier, reservation, transaction, etc.) to manage and analyze members.
Restaurant managers and staffs interact with ezFoodie using a Command Line Interface (CLI), and has a Graphical User Interface (GUI) created with JavaFX.

It is written in Java, and has about 35 kLoC, of which I contributed about 2.1 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?breakdown=true&search=morrow1ndy)


* **New Feature**: Added the ability to `add` `Transaction` by `Member ID` [\#93](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/93), [\#90](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/90), [\#73](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/73).

  * **What it does**: Allows the staff and manager to `add` `Transaction` to a specified member,
    identified by his `Member ID`.

  * **Justification**: The staff and manager should be able to add a new `Tranasction` to ezFoodie
    when it is made by a member at the restaurant.

  * **Highlights**: This feature requires an understanding on how inheritance and polymorphism in OOP is used,
    since `AddMember` command and `AddTransaction` command share the same command word `add`. In the first implementation,
    `AddMember` command and `AddTransaction` command both inherit from `AddCommand`,
    and the same for two `Parser` classes of these two commands.
    `AddCommand` are parsed by `AddCommandPrefixParser` first
    to distinguish between two commands before any further actions.
    This obeys the original code structure.


* **New Feature**: Added the ability to `show` `Member` details by `Member ID` in a separate pop-up window
  [\#102](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/102)
  *(The Java class is named `ViewCommand`, while the command is named `show` to fit the command naming in our UG.)*.

  * **What it does**: Allows the staff and manager to `show` a specific `Member`'s details
    that are not shown in main window, such as `Transaction` and `Reservation`.
    The member is identified by his `Member ID`.

  * **Justification**: It is too long and tedious if all information is shown in `MainWindow` and is not user-friendly.
    Moreover, viewing a `Member`'s details is most probably a one time action in daily restaurant routine.
    Hence, `MainWindow` should remain open, and a separate pop-up window would be a good choice for staff and managers to
    `show` a specific `Member`'s `Transaction` and `Reservation` details when they want to.

  * **Highlights**: This feature requires an understanding on full stack integration and object references in Java.
    `MemberViewWindow` is a new pop-up window and hence it should be a separate stage in JavaFX.
    `Model` and `CommandResult` are changed according to include a boolean value,
    indicating whether `MemberViewWindow` should be displayed or hidden.
    Since the feature uses the same `filterMemberList` method as `FindCommand` due to abstraction and encapsulation,
    a deep copy of `updatedMemberList` is used for `MemberViewWindow` usage only, so that when the user asks for
    a specific `Member`'s details, the deep copy is updated instead and
    the copied `updatedMemberList` shown in `MemberViewWindow` will not affect the
    `MemberListPanel` *(which uses the original `updatedMemberList` and refers to all members stored in ezFoodie)*
    shown in `MainWindow`.


* **New Feature**: Added the ability to view `summary` of data stored in ezFoodie in a separate pop-up window.
  [\#111](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/111)

  * **What it does**: Allows managers to view `summary` of
    total `Member` existing in ezFoodie as well as time-series statistics of `Transaction` data
    *(i.e. aggregate number and amount of transactions recorded in ezFoodie in past 1/3/6 months)*.

  * **Justification**: This a manger-restricted function. Managers should have the ability to view the summary of
    essential statistics to get an understanding of how well the restaurant is performing.
    This information should not be visible in `MainWindow` as permission is required to view `summary`.
    Moreover, it may be too troublesome for managers to type another `list` command to get back to view `MemberListPanel`
    after viewing `summary`.
    Hence, a pop-up window is a good choice for `summary`.

  * **Highlights**: This feature requires an understanding on full stack integration.
    User needs to log-in as a manager first before it can perform view `summary` action in ezFoodie.
    `SummaryWindow` is a new pop-up window and hence it should be a separate stage in JavaFX.
    `Model` and `CommandResult` are changed according to include a boolean value,
    indicating whether `SummaryWindow` should be displayed or hidden.


* **Enhancements to existing features**:
  * Updated the `help` feature to abstract `HelpBox` from `HelpWindow` for further abstraction(OOP).
    [\#200](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/200)
  * Updated the `help` feature for a prettier GUI of `HelpWindow` by modifying layouts, fonts and colors used.
    [\#200](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/200)
  * Updated the `summary` feature to abstract `SummaryBox` from `SummaryWindow` for further abstraction(OOP).
    [\#200](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/200)
  * Updated the `ReservationPanel` in `summary` feature to sort the reservations of a specific member in correct order
    [\#200](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/200)
  * Updated the `MainWindow` layout for a prettier GUI.
    [\#200](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/200)

* **Project management**:
  * Maintained the [issues](https://github.com/AY2122S1-CS2103T-F12-4/tp/issues),
    [milestones](https://github.com/AY2122S1-CS2103T-F12-4/tp/milestones) and
    [projects](https://github.com/AY2122S1-CS2103T-F12-4/tp/projects)
  * Managed releases `v1.2.1`, `v1.3`, `v1.4` ([releases](https://github.com/AY2122S1-CS2103T-F12-4/tp/releases)),
    and largely contributed to wrap-up of `v1.3` before the project demo and pitch.

* **Documentation**:
  * AboutUs:
    * Updated AboutUs for member information. 
    [\#29](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/29)
    [\#31](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/31)
    [\#36](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/36)
    [\#39](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/39)
  * User Guide:
    * Added documentation for the features `edit` and bug fixes.
      [\#62](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/62)
      [\#191](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/191)
  * Developer Guide:
    * Added documentation for the features `addTransaction` and `Summary` and `ShowMemberProfile`
      [\#216](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/216)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#217](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/217),
    [\#208](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/208),
    [\#206](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/206),
    [\#207](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/207),
    [\#203](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/203),
    [\#199](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/199),
    [\#103](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/103)
  * Reported bugs and suggestions for other teams in the class:
    [\#1](https://github.com/morrow1ndy/ped/issues/1),
    [\#2](https://github.com/morrow1ndy/ped/issues/2),
    [\#3](https://github.com/morrow1ndy/ped/issues/3),
    [\#4](https://github.com/morrow1ndy/ped/issues/4),
    [\#5](https://github.com/morrow1ndy/ped/issues/5),
    [\#6](https://github.com/morrow1ndy/ped/issues/6)
