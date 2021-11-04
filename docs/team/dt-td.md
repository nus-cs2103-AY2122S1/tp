---
layout: page
title: Dickson's Project Portfolio Page
---

### Project: TutorAid
<!-- PPP format and structure adapted from
https://github.com/samuelfangjw/tp/blob/master/docs/team/samuelfangjw.md
-->
TutorAid is a desktop application used for helping tech-savvy private tutors, who have busy schedules / many students, to keep track of the details of all their students and lessons.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project:

* **TutorAid icon**:
    * Created a product icon for TutorAid
* **Lesson Model**:
    * Implemented base class structure for lesson model
* **`view -s` command**:
    * Added feature to view details of student in student panel and details of lesson(s) the student has in lesson panel
    * Justification: Being able to view the details of a student of interest and the details of lesson(s) the student has allows users to have easy access to a clear overview of the student they are tutoring which makes it a lot more convenient. Without this feature, users would have to find the details of the student in the student panel and find the details of the lesson(s) the student has in the lesson panel separately.
* **`view -l` command**:
    * Added feature to view details of lesson in lesson panel and the details of student(s) that has/have this lesson in the student panel
    * Justification: Being able to view the details of a lesson of interest and the details of student(s) that has/have this lesson allows users to have easy access to a clear overview of the lesson they have which makes it a lot more convenient. Without this feature, users would have to find the details of the lesson in the lesson panel and find the details of the students who have this lesson in the student panel separately.
* **Other features and enhancements to existing features**:
    * Improved formatting for information shown in result display, student panel and lesson panel for better user readability
    * Updated features `delete student` and `delete lesson` to remove the lessons and students they have respectively, and update the student and lesson panels accordingly.
* **Code contributed**: My code contributions can be found [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=dt-td&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=dt-td&tabRepo=AY2122S1-CS2103T-W16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **Documentation**:
    * User Guide:
        * Added documentation for the features `view student` and `view lesson`
        * Standardised documentation for features
    * Developer Guide:
        * Added target user profile, value proposition, user stories, use cases, non-functional requirements, glossary
        * Updated UI Architecture component's description and class diagram
        * Added implementation of features `view student` and `view lesson` and relevant UML diagrams 
* **Contributions beyond the project team**:
    * Reported [bugs and suggestions](https://github.com/dt-td/ped/issues) for Team CS2103T-W08-3 during the Practical Exam - Dry Run.  
