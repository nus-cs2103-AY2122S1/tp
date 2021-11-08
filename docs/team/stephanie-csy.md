---
layout: page
title: Stephanie Chen's Project Portfolio Page
---

### Introduction

This page serves to document my contributions to the project ezFoodie under NUS module CS2103T in AY21/22 semester 1.

### Project: ezFoodie

ezFoodie is a desktop application that helps restaurants **keep track of their ever-growing list of members**.
Restaurant managers and staffs can easily view and update member status (e.g. personal information, tier, reservation, transaction, etc.) to manage and analyze members.
Restaurant managers and staffs interact with ezFoodie using a Command Line Interface (CLI), and has a Graphical User Interface (GUI) created with JavaFX.

It is written in Java, and has about 35 kLoC, of which I contributed about 1 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?breakdown=true&search=stephanie-csy)

* **New Model**: Created the `Transaction` model to support adding transaction details for each member. [\#69](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/69)
    * **What it does**: stores the details of each transaction under each `member`.
    * **Justification**: This model is essential as it is one of the core features of ezFoodie.
    * **Highlights**: This enhancement requires understanding on how `hash` works. The implementation was challenging as the `Account` information need to be hashed when it is storing in the file for the purpose of high security.

* **New Feature**: Added the ability to `delete` `Reservation` by `Reservation ID` [\#106](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/106)
    * **What it does**: allows the manager to `delete` `Reservation` by `Reservation ID`
    * **Justification**: This feature is essential, since each `Reservation` has its own `ID`, and the application is not able to list all the `Reservations`, it is impossible to `delete` `Reservation` by `Index`. Therefore, the `Reservation ID` is the only attribute to identify the `Reservation`.
    * **Highlights**: This enhancement requires understanding on how polymorphism works and implement it so that the application will use Object-oriented programming (OOP) sufficiently. The implementation was challenging as the `DeleteCommand` need to be abstracted, and inherit `DeleteCommand` by `DeleteCommandPrefixParser`, and further inherit `DeleteCommandPrefixParser` by `DeleteReservationCommand`. Similarly, the `DeleteCommandParser` also need to be abstracted, and inherit it with `DeleteReservationCommandParser`.

* **Enhancements to existing models**:
    * Added the `ID` for the `Reservation` model so that the staff and manager can do operations based on the `Reservation ID` [\#106](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/106)

* **Enhancements to existing features**:
    * Updated the regex of `Phone` such that it will only accept numbers that are strictly 8 digits long, compared to the original which allowed any number at least 3 digits long. [\#184](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/184)

* **Project management**:
    * Maintained the [milestones](https://github.com/AY2122S1-CS2103T-F12-4/tp/milestones)

* **Documentation**:
    * User Guide:
        * Updated according to changes to code, fixed mistakes
          [\#197](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/197)
    * Developer Guide:
        * Added documentation for the `Delete Reservation` feature
          [\#205](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/205)
          
* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#73](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/73), 
      [\#94](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/94)
    * Reported bugs and suggestions for other teams in the class:
      [\#1](https://github.com/stephanie-csy/ped/issues/1), 
      [\#2](https://github.com/stephanie-csy/ped/issues/2), 
      [\#3](https://github.com/stephanie-csy/ped/issues/3), 
      [\#4](https://github.com/stephanie-csy/ped/issues/4), 
      [\#5](https://github.com/stephanie-csy/ped/issues/5), 
      [\#6](https://github.com/stephanie-csy/ped/issues/6)
