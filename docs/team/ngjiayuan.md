---
layout: page
title: Ng Jia Yuan's Project Portfolio Page
---

### Project: ModuLink

ModuLink is a desktop-based application for Computer Science (CS) students at NUS to facilitate finding teammates for group-based modules. It allows you to find students taking the modules you are interested in, search by their group status (to find students available to form or join groups), and much more. ModuLink has been developed using Java and has about 6k LoC.

Given below are my contributions to the project.

* **New Feature**: Added the create feature to allow new users to create their profile 
  * What it does: allows new user to create their profile for the first time.
  * Justification: This feature was necessary as each user should be required to create their profile with their contact details. This allows other users to search them up and obtain their contact details in order to reach out to them to form groups.
  * Highlights: This command is restricted to be called only once as we expect only 1 profile creation per new user. Any amendments should be made with the edit command instead. Users are also required to create their profile prior to using other commands of ModuLink as part of the Get Started process.

* **New Feature**: Added the remFav feature to remove a favourited profile as a favourite
  * What it does: allows user to remove a favourited profile as their favourites.
  * Justification: This feature was necessary as users may add a profile to favourite by accident or has a change in mind of keeping a profile as their favourites.
  * Highlights: This command is a new command and complements the addFav command.

* **Feature Enhancements**: Moved the user profile to the top of the list
  * Justification: This feature was necessary as it provides users a better user experience to be able to view their own profile at the top of the list.
  * Highlights: This feature was achieved by adding an addToTop function for the personsList.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ngjiayuan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Project management**:
  * Organised weekly meetings
  * Opening of issues and delegation of tasks
  * Managed release `v1.3.2` on GitHub
  * Checking and merging of PRs
  
* **Documentation**:
  * User Guide:
    * Updated instructions on UG to be more reader focused and reader friendly. 
    * Fixed documentation bugs from PE-D.
  * Developer Guide:
    * Assisted Zachary in crafting sequence diagrams.
