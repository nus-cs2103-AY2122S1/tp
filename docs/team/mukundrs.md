---
layout: page
title: Raja Sudalaimuthu Mukund's Project Portfolio Page
---

### Introduction

This page serves to document my contributions to the project ezFoodie under NUS module CS2103T in AY21/22 semester 1.

### Project: ezFoodie

ezFoodie is a desktop application that helps restaurants **keep track of their ever-growing list of members**.
Restaurant managers and staffs can easily view and update member status (e.g. personal information, tier, reservation, transaction, etc.) to manage and analyze members.
Restaurant managers and staffs interact with ezFoodie using a Command Line Interface (CLI), and has a Graphical User Interface (GUI) created with JavaFX.

It is written in Java, and has about 35 kLoC, of which I contributed about 1 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=mukundrs)

* **New Model**: Created the `Reservation` model to support adding reservation details for each member. [\#94](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/94)
    * **What it does**: stores the details of each reservation under each `member`.
    * **Justification**: This model is essential as it is one of the core features of ezFoodie.
    * **Highlights**: This enhancement requires understanding on how `hash` works. The implementation was challenging as the `Account` information need to be hashed when it is storing in the file for the purpose of high security.

* **New Feature**: Added the ability to `add``Reservation` to a member. [\#94](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/94)
    * **What it does**: adds a new reservation to the reservation list of a `member`.
    * **Justification**: This feature is essential as it is one of the core features of ezFoodie.
    * **Highlights**: This enhancement requires understanding on how polymorphism works and implement it so that the application will use Object-oriented programming (OOP) sufficiently. The implementation was challenging as the `AddCommand` need to be abstracted, and inherit `AddCommand` by `AddCommandPrefixParser`, and further inherit `AddCommandPrefixParser` by `AddReservationCommand`. Similarly, the `AddCommandParser` also need to be abstracted, and inherit it with `AddReservationCommandParser`.

* **Enhancements to existing features**:
    * Updated the help window UI to suit the needs of ezFoodie [\#56](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/56)

* **Project management**:
    * Maintained the [milestones](https://github.com/AY2122S1-CS2103T-F12-4/tp/milestones)

* **Documentation**:
    * User Guide:
        * Updated according to changes to code, fixed mistakes
          [\#185](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/185),
          [\#193](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/193),
          [\#199](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/199),
          [\#208](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/208),
          [\#218](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/218)
    * Developer Guide:
        * Added documentation for add reservation feature, fixed mistakes and bugs
          [\#199](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/199),
          [\#218](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/218)
        
* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\217](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/217),
      [\215](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/215),
      [\212](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/212),
      [\207](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/207),
      [\205](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/205),
      [\204](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/204),
      [\197](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/197),
      [\55](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/55),
      [\51](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/51),
      [\48](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/48),
      [\45](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/45),
      [\37](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/37)
    * Reported bugs and suggestions for other teams in the class:
      [\#1](https://github.com/mukundrs/ped/issues/1),
      [\#2](https://github.com/mukundrs/ped/issues/2),
      [\#3](https://github.com/mukundrs/ped/issues/3),
      [\#4](https://github.com/mukundrs/ped/issues/4),
      [\#5](https://github.com/mukundrs/ped/issues/5),
      [\#6](https://github.com/mukundrs/ped/issues/6)
