---
layout: page
title: Ethan Wong's Project Portfolio Page
---

### Project: ModuLink

ModuLink is a desktop-based application for Computer Science (CS) students at NUS to facilitate finding teammates for group-based modules. It allows you to find students taking the modules you are interested in, search by their group status (to find students available to form or join groups), and much more. ModuLink has been developed using Java and has about 18k LoC.

Given below are my contributions to the project.

* **New Feature**: Added the `addMod`/`remMod` commands. (Pull Requests: [#81](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/81))
  * What it does: Allows the user to add a module to their profile. Modules already present in the users profile list can be removed using the `remMod` command.
  * Justification: Previously, users could only edit their module tags with the `Edit` command, which required them to re-enter their entire updated list. This feature allows users to edit their module list one-by-one, making it much more convenient.
  * Highlights: These were new commands, and took roughly 400 LoC to develop both of them. Previous iterations of these commands allowed users to add and remove multiple modules at a time, but in the end we decided on only allowing one module at a time to keep in line with the input conventions of our other commands, as well as to eliminate possible bugs.

* **Feature Enhancements**: Changed `Tag`s to `Mod`s. `Mod`s now also have an additional `Status` field, which determines what colour it has in the UI. (Pull Requests: [#55](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/55))
    * Justification: ModuLink is meant to be used by NUS CS students, thus specialising Tags to act as Modules, and having those Modules show their grouping status, makes it even easier for our users to find group mates.
    * Highlights: Mods follow naming conventions of NUS Modules. Previous experience with HTML and CSS helped a lot when tweaking things in JavaFX.

* **Feature Enhancements**: Added the `isFavourite` field to the profiles. (Pull Requests: [#63](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/63))
    * Justification: To allow users to be able to keep track of people they want to work together with in the future.
    * Highlights: A star icon is added in the UI to indicate a favourited profile. 
    * Credits: The icon used was pulled from [this](https://imgbin.com/png/X9hfA1CP/five-pointed-star-yellow-png) website.

* **Feature Enhancements**: Changed the `Edit` command to only allow users to edit their own profile and removed the ability to edit module tags. [#94](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/94)
    * Justification: As the data present in ModuLink is meant to act as profiles others have created, it wouldn't make sense for users to edit others' profiles. Our team also decided to separate editing module tags from editing other fields in the user profile 

* **Other Code Enhancements**:
    * Fixed bugs pointed out to us during the PED. (Pull Requests: [#174](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/174), [#178](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/178), [#179](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/179))
    * Updated all commands (except `Edit`) to strictly take one input per parameter.
    * Added several utility functions to `Model` and `ModelManger` such as `getProfile` and `refreshFilteredPersonList`.

* **Code contributed**: [RepoSense link - ethanwong6362](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=Ethan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Project management**:
    * Managed release `v1.3.3` on GitHub.

* **Documentation**:
    * User Guide:
        * Updated documentation for all features with the latest examples and screenshots to help users use our app. (Pull Requests: [#179](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/179))
        * Proofread and edited our user guide for grammatical/technical errors and tone.
    * Developer Guide:
        * Added use cases (UC1 - UC4) and edited use cases (UC5 - UC6)
