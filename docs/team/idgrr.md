---
layout: page
title: Irfan's Project Portfolio Page
---

### Project: CallMeMaybe

CallMeMaybe (CMM) is a **desktop app** centered for Telemarketers in aiding them in customer contact management.
The in-built tracking functionality serves as a reminder to reach back on previously unreachable customers.
Importing and exporting of existing customer database is also supported by CMM to facilitate team-based environments.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. This is written in Java, and has about
10 kLoC. The development of this software was used as a medium for students to apply Software Engineering principles
taught during the Software Engineering Module CS2103T.

Given below are my contributions to the project.

* **New Major Feature**: Added Importing functionality
  * **What it does:**<br>
      * Allows users to import contacts into CMM's database quickly. CMM currently imports from Excel Files only
  * **Justification:**<br>
      * Based on the user story, a telemarketer receives his/her daily call list in an Excel sheet. This means that quickly
        importing long list of contacts is crucial
      * This  function allows user to import multiple people at once, as compared to adding in contacts individually
        (previously the two options were to either learn json or to manually input all the data using the add function)
  * **Highlights:** <br>
      * This enhancement required a deep understanding of CMM components, primarily the Storage component 
      * This enhancement required understanding in parser components, primarily `FileUtil`
      * This enhancement required good use of logging as there are multiple contacts being imported and error displayed
        on the feedback box would be too long for user to read/scroll through
      * This enhancement involves modifications in `UI`, `Logic` , `Storage`, `Model` and `Commons`
      * Crucial for the feature to import only valid contacts from the CSV file
      * The implementation was challenging as it required constant updating and tweaking as the team's definition of a 
        valid person to be imported constantly changed throughout the project
  * **Credits:**<br>
      * The previous iteration CallMeMaybe (AddressBookLevel3) 's JSON storage architecture helped me structure
        my Import feature. This taught me how to follow an existing architectural style while implementing a new
        feature. It also helped me rethink the way I approach code abstractions.
<br>
<br>

* **New Major Feature**: Added Exporting functionality
  * **What it does:** <br>
      * Allows users to export contacts found in CMM's database quickly. CMM currently export to Excel Files only
  * **Justification:** <br>
      * Based on the user story, a telemarketer returns the daily call list in an Excel sheet to the manager. This means
        that there is a need to convert the JSON data used in CMM back to an Excel sheet
      * This is allows seamless data conversion between Excel and CMM.
      * The Export function also acts as a safety net should telemarketers wish to start a new database with imported
        contacts. CMM will immediately export the current database in an Excel file before wiping and introducing new
        contacts into the database.
  * **Highlights:**<br>
      * This enhancement required understanding how importing works
      * This enhancement required understanding of how `Person` details are handled within CMM
      * This enhancement required understanding of file writing components, primarily `FileUtil`
  * **Credits:**<br>
      * The previous iteration CallMeMaybe (AddressBookLevel3) 's JSON storage architecture  helped me structure
        my Export feature. This taught me how to follow an existing architectural style while implementing a new 
        feature. It also helped me rethink the way I approach code abstractions.

<div style="page-break-after: always;"></div>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=idgrr&tabRepo=AY2122S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub
  * Managed Github Milestones
  * Managed Github Labels
  * Update demo for v1.3

* **Enhancements to existing features**:
  * Updated the GUI resizing functionality to ensure that features can always be displayed
  * Wrote additional tests for existing features such as CsvUtil, CsvAdaptedPerson, CsvAddressBook


* **Documentation**:
  * User Guide:
    * Added documentation for the features `import` and `export` and `findAny`
      PR for reference:<br> 
      * [#131](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/131/files)
        , [#109](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/109/files)
        , [#97](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/97/files) <br>
  * Developer Guide:
    * Added implementation details of the `import` and `export` feature.<br>
      [#96](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/96/files)
      , [#32](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/32/files) <br>

* **Testing**
  * Testcases for Import export feature
    * [#121](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/121)
  

* **Community**:
  * Total PRs reviewed : 23
  * Examples of PRs reviewed (with non-trivial review comments): <br>
    * [#175](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/175)
    , [#89](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/89)
    , [#93](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/93)
    , [#67](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/67)
    , [#38](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/38)
    , [#34](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/34)
  * Reported bugs and suggestions for other teams in the class:
    * [Bugs found in PE-D](https://github.com/idgrr/ped/issues)
    
