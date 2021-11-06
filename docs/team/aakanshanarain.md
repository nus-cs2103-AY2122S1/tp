---
layout: page
title: Aakansha Narain's Project Portfolio Page
---

### Project: ModuLink

ModuLink is a desktop-based application for Computer Science (CS) students at NUS to facilitate finding teammates for group-based modules. It allows you to find students taking the modules you are interested in, search by their group status (to find students available to form or join groups), and much more. ModuLink has been developed using Java and has about 18k LoC.

Given below are my contributions to the project.

* **New Feature**: Filter by module and optionally group status
    * What it does: Allows the user to filter the list of profiles by a specific module and optionally a group status.
    * Justification: This feature is crucial as it is the basis of what ModuLink aims to do - simplify search for students in a specific module needing to either form, join or add members to their groups. With the filter feature, users can easily find exactly which students are taking the specific module and if they are open to forming/adding members to their groups.
    * Highlights: This was a new command to implement, and took about 400 LoC to develop.

* **New Feature**: Edit group status for modules on your profile
    * What it does: Allows the user to edit the group status of a module on their profile.
    * Justification: This feature makes updating the module tags easier instead of having to delete and add a new module tag every time the user’s group status changes.
    * Highlights: This was a new command to implement, and took about 340 LoC to develop.

* **Feature Enhancements**: Added the Student ID, Telegram handle and GitHub username attribute fields to the profiles.
    * Justification: These identity fields are pivotal for our target audience (CS students) while forming their groups.
    * Highlights: All three fields follow the conventions required by the actual organization (i.e. The required Student ID is in the format of the actual NUS-prescribed format of the Matriculation number, the required GitHub username and Telegram handle follow most of the actual constraints of GitHub usernames and Telegram handles respectively. In total, implementing these 3 fields took about 1k LoC.

* **Other Code Enhancements**:
    * Wrote additional tests for features to increase coverage.
    * Added more specific and situation-appropriate error messages for better UX.
  
* **Code contributed**: [RepoSense link - aakanshanarain](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=aakanshanarain&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Project management**:
    * Managed release v1.3.1 on GitHub
    * Opened issues for the team

* **Documentation**:
  * User Guide:
    * Edited documentation for all new features. (Pull Requests: [\#100](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/100), [\#103](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/103), [\#105](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/105), [\#107](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/107), [\#109](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/109))
    * Added the Introduction and made other cosmetic changes throughout the document. (Pull Requests: [\#111](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/111), [\#184](https://github.com/AY2122S1-CS2103T-W12-4/tp/pull/184))
  * Developer Guide:
    * Added use cases (UC5 - UC10).
    * Added details + sequence diagram for editGroupStatus command.
