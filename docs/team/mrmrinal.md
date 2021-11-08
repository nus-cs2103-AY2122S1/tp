---
layout: page
title: Mrinal's Project Portfolio Page
---

### Project: DonnaFin.io

DonnaFin.io is a desktop application used by financial advisors to keep track of their client information and related 
tasks. The ‘.io’ in our name is a reflection of our belief that you deserve a faster workflow for input and output.
If you can type fast, you can use our CLI-like commands to manage your client information and view your notes much faster
than your typical customer relationship manager apps.

Given below are my contributions to the project.

* **New Feature**: Created the platform to edit client contact details, and append/remove assets, liabilities and policies [#57](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/57/files)
    * What it does: It allows for current contact information to be edited, as allows for the user to append and/or remove assets, liabilities and polices. 
    * Justification: This feature improves the application significantly, as it allows for the user to edit and update their client information. Without this functionality
      the main functions of our application would not have able to been implemented as it would not be possible to update the real time information of the clients.
    * Highlights: This feature needed a reimplementation of the existing Person class was immutable and did not support modifications to a client's attributes. The workaround this was to
      implement a PersonAdapter wrapper class that supports for editing and adding of certain attributes by wrapping a single Person object and replacing it with an edited copy. 
      This helped us implement real time updating and now all changes are reflected immediately in all parts of our application.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*


* **New Feature**: Created the Tabs in the Client Window to display the various client information in the user interface. [#126](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/126/files)
  * What it does: This allows for all the categorised information of a single client to be displayed in an organized yet intuitive user interface across 5 tabs.
  * Justification: This feature improves the application significantly, as it allows for users to access client information by just using simple keyboard commands, and accessing information in five 
    rather than having a scrollable interface.
  * Highlights: This feature required the reimplementation of our existing ClientView tab through using JavaFX. Our tabs do not use the existing Tab feature present in JavaFX, but we rather had
    a VBox that got updated with the appropriate content when a tab button was pressed or a tab command was entered.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=mrmrinal&tabRepo=AY2122S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Took down meeting minutes.
  * Recorded the tasks to do for each person to make it clear for teammate assigning issues.
  * Performed manual testing on PRs by cloning teammates branches locally and testing.
  * Examine PRs and alert teammates in the event of any bugs/errors present.


* **Enhancements to existing features**:
  * Fix a bug where adding an asset with no remarks did not throw the correct error message. (PR [#309](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/309))
  * Fixed a bug where list command showed an inaccurate output on the result display. (PR [#305](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/305))
  * Wrote additional tests for the Parser class to raise CodeCov percentage from 69-76%.  (PR [#309](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/309))
  * Wrote additional tests for the PersonAdapter class to raise CodeCov percentage from 62-66%. (PR [#85](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/85))

* **Documentation**:
    * User Guide:
      * Make minor corrections and improve structure to the UG (PR [#175](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/175))
      * Added documentation and images for the various screens and tabs of the UI. (PR [#336](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/336))
      * Optimize images for the user guide. (PR [#345](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/345))
    * Developer Guide: 
      * Added the user story for the feature `edit` and added explanation for the sequence diagram. (PR [#175](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/175))
      * Restructure the developer guide, and add introduction to the developer guide. (PR [#314](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/314))
      * Added the user story for the feature `edit` and added explanation for the sequence diagram. (PR [#175](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/175))
      * Added the overview of DonnaFina nad the problem it aimed to solve  (PR [#329](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/329))
        * Drew PUML OODM diagram to show the problem domain that DonnaFin is able to solve.


* **Community**:
  * PRs reviewed: 
  * Forum Posts

* **Tools**:

