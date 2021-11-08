---
layout: page
title: Chi Xu's Project Portfolio Page
---

### Project: Ailurus

Ailurus helps organizing committees account for details of their members. 
It provides users with convenient viewing and editing access to all information, thus providing much convenience in their work.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

#### New Features
1. Implemented Task and TaskList
  * What it does: Task contains name, deadline and allows users to mark and store the completion status of that task.
    And each member has a task list that contains all the task of the member. The task list can be saved and stored in json array as an attribute of member.
  * Justification: This feature enhances our product by allowing users to keep track of the tasks assigned to each member.
  * Highlights: This enhancement affects the model, storage and future commands. It required an in-depth analysis of design alternatives such as whether to have a global task list or each member has its task list.
  * Notable PRs: [PR#101](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/101), [PR#140](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/140), [PR#236](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/236)

2. Implemented task editing commands
  * What it does: `tedit` command can edit the name and deadline of the task, `tdone` command can mark a task as done, `tundone` command can mark a task as uncompleted.
  * Justification: This feature improves the product by allowing users to edit the detailed information of an existing task.
  * Highlights: `tedit` will only change the fields specified in the command parameters. `tdone` and `tundone` can edit multiple tasks' status.
  * Notable PRs: [PR#146](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/146), [PR#150](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/150), [PR#157](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/157)

3. Implemented `mtfind` command
   * What it does: `mtfind` command can find members whose tasks' names contain the given keyword.
   * Justification: This feature allows user to filter members by their tasks and improves the searching functionality of Ailurus.
   * Notable PR: [PR#162](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/162)

#### Code contributed 
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tsiyuk)

#### Project management
1. Opened issues that sought to properly track the progress of adding and enhancing features.
2. Reviewed and approved team members' PRs for merging (more details in the [Community](#community) section)

#### Enhancements to existing features
1. Changed the address and email of member to optional fields
    * Previously the address and email are compulsory, now the user can add a new member without address and email.
    * Notable PR:[PR#118](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/118)

#### Documentation
* Developer guide
    1. Add implementation of `tadd` command and `tdone` command and the sequence diagrams. [PR#270](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/270)
    2. Modify `Model` component in DG, add an alternative design of `Model`, and modify command activity diagram. [PR#276](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/276)
    3. Add manual testing guide for task. [PR#278](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/278)

* User guide
    1. Change all person to member. [PR#83](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/83)
    2. Add user guide to `tundone` command. [PR#157](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/157)

#### Community
1. Reviewed PRs of fellow teammates. (Some with non-trivial review comments, E.g. [PR#149](https://github.com/AY2122S1-CS2103T-T15-2/tp/pull/149))
2. Reported bugs and suggestions for other teams during [PE-D](https://github.com/tsiyuk/ped/issues).
