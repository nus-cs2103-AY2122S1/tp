---
layout: page
title: Zhang Zhiyao's Project Portfolio Page
---

### Introduction

This page serves to document my contributions to the project ezFoodie under NUS module CS2103T in AY21/22 semester 1.

### Project: ezFoodie

ezFoodie is a desktop application that helps restaurants **keep track of their ever-growing list of members**.
Restaurant managers and staffs can easily view and update member status (e.g. personal information, tier, reservation, transaction, etc.) to manage and analyze members.
Restaurant managers and staffs interact with ezFoodie using a Command Line Interface (CLI), and has a Graphical User Interface (GUI) created with JavaFX.

It is written in Java, and has about 35 kLoC, of which I contributed about 4 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?breakdown=true&search=zzybluebell)
<br>

* **Project management**:
  * Helps the team to assign the task and review the pull request.
<br>

* **New Feature**: Add Member [\#71](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/71)
* **What it does**: allows the user (manager or staff) to `add` members in the ezFoodie.
* **Justification**: 
  * The feature is essentials, and the priority is high. 
  * The staffs and manager should add members based on name, phone, email, address, tags and related information. 
  * It is necessary to add member by different `Phone` and `Email` of each member. This is only significant way to differentiate the different members.
* **Highlights**:  
  * This enhancement requires understanding on how polymorphism works and implement it so that the application will use Object-oriented programming (OOP) sufficiently. 
  * The implementation was challenging as the `AddCommand` need to be abstracted. Similarly, the `AddCommandParser` also need to be abstracted, and inherit it with `AddMemberCommandParser`.
* **Special** Add a few testcases to ensure testing coverage.
<br>

* **New Feature**: Update credits [\#96](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/96)
* **What it does**: Update credit for member in ezFoodie.
* **Justification**: 
  * Credits refer to the overall accumulated transaction billing of a member, and depends on amounts of billing in add transaction, delete transaction, and edit transaction.
  * The feature is essentials, and the priority is medium. 
  * It also refers to the level of Tier. 
  * Credit amount ranges from `0` to `99999999`.
* **Highlights**:  This enhancement requires understanding on how polymorphism works and implement it so that the application will use Object-oriented programming (OOP) sufficiently. It requires to understand the whole process and code working for add transaction, edit transaction and delete transaction based on the essential contrainsts.
* **Special** Add a few testcases to ensure testing coverage.
<br>

* **New Feature**: Redeem points [\#103](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/103)
* **What it does**: Redeemed as discounts to a member.
* **Justification**: 
  * The feature is essentials, and the priority is medium.
  * The point accumulation is similar to credit.
  * However, point can be redeemed as amounts of cash deduction to a member, and point will be deducted accordingly with redemption.
  * In delete transaction, the point will not be affected and keep the same.
  * In edit transaction, the point will be increased when billing amount is greater than the billing amount added in last time. on the contrary the point will not be affected and keep the same when billing amount is lesser than the billing amount added in last time.
  * Point amount is range from `0` to `99999999`.
* **Highlights**:  This enhancement requires understanding on how polymorphism works and implement it so that the application will use Object-oriented programming (OOP) sufficiently. It requires to understand the whole process and code working for add transaction, edit transaction and delete transaction based on the essential contrainsts.
* **Special** Add a few testcases to ensure testing coverage.

* **Documentation**:

  * Javadoc:[\#186](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/186)
      *  Update the whole public method, public class and releted java file.

  * README:[\#28](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/28)
      * Create and edit the first version of README.

  * Index: Editt Index file [\#54](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/54)

  * User Guide:[\#198](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/198)
      * Re-design the whol foramt.
      * Add the testing pictures.
      * Unified terminology.
      * etc.

  * Developer Guide: [\#211](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/211)
      * Update `add member`, `Redeem Point` features and etc.

  * About Us: [\#206](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/206)
      * update essential self-information.

* **Community**:
  * Contributed to commnents:
      * PRs reviewed:
      [\#202](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/202), 
      [\#200](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/200), 
      [\#196](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/196), 
      [\#193](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/193), 
      [\#192](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/192), 
      [\#185](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/185), 
      [\#183](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/183), 
      [\#182](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/182), 
      [\#180](https://github.com/AY2122S1-CS2103T-F12-4/tp/pull/180),


      * Reported bugs and suggestions for other teams in the class: 
          [\#5](https://github.com/zzybluebell/ped/issues/5), 
          [\#4](https://github.com/zzybluebell/ped/issues/4),  
          [\#3](https://github.com/zzybluebell/ped/issues/3), 
          [\#2](https://github.com/zzybluebell/ped/issues/1), 
          [\#1](https://github.com/zzybluebell/ped/issues/1)
