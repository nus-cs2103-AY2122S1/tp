---
layout: page
title: Jian Hong's Project Portfolio Page
---

### Project: Trace2Gather

Trace2Gather is a desktop address book application used for managing hotel guests and rooms to help in contact tracing purposes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jianh0ng&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jianh0ng&tabRepo=AY2122S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) <br><br>
* **Enhancements implemented**: Added the records section of the addressbook to keep track of the history of residencies as well as the search functionality for the records.
    * What it does: Allows the user to view past residencies for contact tracing and search for relevant residencies by person, room number etc...
    * Justification: This feature improves the product significantly because it helps our users retrieve information quickly for contact tracing purposes.
    * Highlights: The search commands allows for users to enter multiple keywords and only displays results that match all the keywords. The design of this search differs from the one for searching guests, because we want to enable users to do more precise searches for records to minimise delays in contact tracing. The implementation also required changes to other commands to ensure that the data displayed is updated accordingly. <br><br>
* **Contributions to the UG**:
    * Added documentation for listing rooms as well as records
    * Details for the search method for records
    * Keep several images in the UG updated with changes to the UI.
    * Updated several other commands in accordance to feedback from peers. <br><br>
* **Contributions to the DG**:
    * Updated several UML diagrams as well
    * Implementation details for the past records feature
    * Updated parts of Instructions for Manual Testing

<div style="page-break-after: always;"></div>

* **Contributions to team-based tasks**:
    * Managed releases v1.2 to v1.3 (final)
    * Managed several weekly team tasks such as product demo for v1.2 and v1.3 alongside teammates.
    * Added target user profile, value proposition and use cases in the developer guide
