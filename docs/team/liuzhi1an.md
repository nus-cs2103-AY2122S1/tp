---
layout: page
title: Liu Zhilan's Project Portfolio Page
---

### Project: Ailurus

Ailurus helps organizing committees account for details of their members. 
It provides users with convenient viewing and editing access to all information, thus providing much convenience in their work.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

#### New Feature
* Implement the TaddCommand
    * What it does: Allows the user to add a task to an existing member.
    * Justification: This feature is an essential part of the CRUD methods for tasks. 
    * Highlights: This command originally only supports adding a single task to the selected member, 
    the mass operation was implemented later.
    * Notable PRs: [PR #112](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/112) [PR #148](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/148)
* Implement the TlistCommand
    * What it does: Allows the user to list tasks of a member.
    * Justification: This feature is an essential part of the CRUD methods for tasks.
    * Notable PRs: [PR #130](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/130)
* Implement the TfindCommand
    * What it does: Allows the user to find a task of a member given keywords of the task name.
    * Justification: This feature is helpful for the users to filter wanted information.
    * Notable PRs: [PR #152](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/152)
* Implement the TdelCommand
    * What it does: Allows the user to delete a task from a given member.
    * Justification: This feature is an essential part of the CRUD methods for tasks.
    * Notable PRs: [PR #112](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/112)


#### **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=liuzhi1an)

#### **Project management**:
* Opened issues that sought to properly track the progress of adding and enhancing features.
* Reviewed and approved team members' PRs for merging (more details in the **Community** section)


#### **Enhancements to existing features**:
* Refactored the Tag class in the original code for Address Book 3 to Position. (Notably in [PR #112](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/112))
* Increase test coverage for the task section (including task commands, task command parsers and models).

#### **Documentation**:
* User Guide:
    * Reviewed and corrected grammar and formatting issues of team members' sections of the UG 
      (E.g. Corrected the mismatch between command summary and actual command formatting in [PR #264](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/264),)
    
* Developer Guide:
    * Add constraints about NFRs (in [PR #84](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/84)).
    * Add use cases for tasks.
    * Update the document for tadd, tdel, tlist (in [PR #134](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/134)).
    

#### **Community**:
* Reviewed PRs of fellow teammates (Some with non-trivial review comments, E.g. [PR #84](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/84))
* Reported bugs and suggestions for other teams during PE-D.
