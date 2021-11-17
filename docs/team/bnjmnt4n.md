---
layout: page
title: Benjamin Tan's Project Portfolio Page
---

### Project: SociaLite

**SociaLite** is a desktop app for **connecting avid social media users with their contacts’ social media pages**, optimized for use via a command line interface (CLI). At the same time, it has a graphical user interface (GUI) created with JavaFX. It is written in Java, and has about 10 kLoC.

Below are some of my contributions to the project:

* **New Feature**: Added the ability to add dates to each contact ([\#58](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/58), [\#76](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/76))
    * What it does: Allows the user to add dates associated with each contact, such as birthdays, anniversaries, meetings and other miscellaneous events. Dates can be just for a single event, or recurring monthly or yearly. Upcoming dates (occurring within the next 7 days) are also highlighted in the GUI.
    * Justification: This feature improves the product significantly as it allows users to be able to keep track of important interactions with users, meeting our aim of encouraging more personalized connections in today's social media age.
    * Highlights: This change touched a large portion of the codebase, including updates to the models, storage and the GUI.

* **Altered Feature**: Removed unused fields from each contact ([\#38](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/38))
    * What it does: SociaLite no longer stores the email and address fields for each contact.
    * Justification: Given our app is focused primarily on avid social media users, it didn't make sense to maintain fields which were unlikely to be added by the user. This reduces the complexity of the app for the user as well.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=bnjmnt4n&tabRepo=AY2122S1-CS2103T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Assisted members of the team in tools within my area of expertise, such as Git.
    * Reviewed and suggested improvements to code submissions by all members.

* **Documentation**:
    * User Guide:
        * Added documentation for the dates feature ([\#84](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/84))
    * Developer Guide:
        * Added a few user stories
        * Updated documentation and diagram on the UI components and common classes ([\#169](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/169))

* **Community**:
    * Reviewed [27 PRs](https://github.com/AY2122S1-CS2103T-F11-4/tp/pulls?q=reviewed-by%3Abnjmnt4n) in total, with minor review comments. I also gave some feedback on design choices outside of GitHub. 
    * Reported [4 bugs](https://github.com/bnjmnt4n/ped/issues) for the peer team in the PE-Dry Run.
