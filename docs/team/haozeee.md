---
layout: page
title: Hao Ze's Project Portfolio Page
---

### Project: PlannerMD

PlannerMD is an all-in-one desktop application to help clinic receptionists manage patients, doctors and appointments in a clinic. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete doctors and add remarks and tags to doctors ([#62](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/62), [#65](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/65))
   * What it does: Allows the users to delete doctors from the doctor list and also add tags and remarks to doctors.
   * Justification: This features improves the application as the user can now store important information about a doctor using remarks and tags. Users can also now delete unneeded doctors from the database so that the doctor list will not be filled with unnecessary entries.
   * Highlights: Since the Patient class also had similar features such as the ability to add tags and remarks, there would have been a lot of overlapping code when implementing this feature. Thus, some design considerations and OOP principles had to be taken into account in order to minimize the amount of overlapping code.  

* **New Feature**: Added ability to show doctors and appointments information in the UI ([#55](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/55), [#85](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/85), [#104](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/104))
   * What it does: Allows the user to also be able to view the doctor list and appointment list in the UI, in addition to the patient list.
   * Justification: This features is essential to our application as the users needs to be able to view the doctors and appointments information in order for the application to be useful.


* **New Feature**: Added ability for users to list the current day's appointments ([#115](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/115))
   * What it is: Allowed the users to quickly view all the appointments a clinic has for the current day.
   * Justification: This feature is essential for the daily operations of a clinic as the clinic receptionists needs to be able to quickly view all the appointments for the current day in order to manage the clinic well. 

<div style="page-break-after: always;"></div>

* **New Feature**: Added ability for users to filter appointments ([#115](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/115))
   * What it does: Allows the users to filter through the appointments in the appointment list using filter parameters such as patient name and start date. Users have the option to filter through either all appointments or only the upcoming appointments.
   * Justification: This feature is essential to our application as it allows users to easily and quickly search for the relevant appointments in the appointment list. For instance, users can use this feature to see what appointments are there on a particular day or look through all the appointments a doctor has in order to locate free appointment slots to schedule a new appointment.
   * Highlights: This was a large feature and one of the difficulty when implementing it is testing the parser that parses the `FilterAppointmentCommand` input from the user. As the command has multiple optional inputs(start date, end date, patient name, doctor name), there were a lot of combinations of valid and invalid parameters that needed to be tested in order to ensure that the parser works as intended. Thus, test cases had to be carefully chosen so that the testing is both efficient and effective.  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Haozeee&tabRepo=AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Created and managed project milestone on Github
    * Assigned and kept track of issues on Github
    * Created a product demo for each iteration
    * Fixed bugs found in the PED
    * Added "skeleton" code for the DG to have less merge conflicts ([#149](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/149))


* **Enhancements to existing features**:
    * Updated the UI colours and layout to match the mockup ([#35](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/35))
    * Updated the UI to allow wrapping for long texts ([#205](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/205), [#231](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/231))
    * Changed the default appointment list to show only today's appointments ([#133](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/133))
    * Changed person equality checks to be case-insensitive ([#67](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/67))


* **Contributions to the UG**:
    * Added filter all appointments command documentation ([#139](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/139))
    * Added filter upcoming appointments command documentation ([#139](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/139))
    * Added list today's appointments command documentation ([#139](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/139))
    * Added tag patient and tag doctor documentation ([#116](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/116))
    * Reviewed, raised and fixed issues throughout the UG

<div style="page-break-after: always;"></div>

* **Contributions to the DG**:
    * Updated UI component and UI class diagram ([#111](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/111), [#192](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/192))
    * Added implementation for filtering appointments ([#192](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/192))
    * Added a sequence diagram to illustrate how appointments are filtered ([#192](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/192))
    * Added use cases for filtering appointments ([#228](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/228))
    * Added use cases for listing today's appointments ([#228](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/228))
    * Added manual testing instructions for filtering all appointments ([#228](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/228))
    * Added manual testing instructions for filtering upcoming appointments ([#228](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/228))
    * Added manual testing instructions for listing today's appointments ([#228](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/228))
    * Reviewed, raised and fixed issues throughout the DG

  
* **Review/mentoring contributions**:
    * Reviewed team member's PR and provided some non-trivial comments and suggestions ([#36](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/36), [#38](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/38), [#39](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/39), [#58](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/58), [#95](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/95), [#122](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/122), [#250](https://github.com/AY2122S1-CS2103T-T11-3/tp/pull/250)) 


* **Contributions beyond the project team**:
    * Reviewed and reported bug in other team's product ([link](https://github.com/Haozeee/ped/issues))

