---
layout: page
title: John Doe's Project Portfolio Page
---

My name is Tan Hua Kun and I am one of the developers of Tutor Master. My role is a full stack developer, and I am mostly in
charge of lessons, exams and the viewing panel.

View my code contribution here: [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tanhuakun&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=tanhuakun&tabRepo=AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

What I have contributed:
* Features
    * Logic
        * Created Parsers and Commands to deal with lessons and exams.
        * Created Parser and Command for adding people to a group.
        * Created command to view schedule.
    * Model
        * Person
            * Added Lessons List and Exam to [Person.java](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/person/Person.java).
            * Added [PersonWithDetails.java](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/person/PersonWithDetails.java) for the UI to get a person with all his details.
        * Lesson
            * Created the entire [lesson package](https://github.com/AY2122S1-CS2103T-W16-4/tp/tree/master/src/main/java/seedu/address/model/lesson).
        * Model
            * Added enum ViewingType for viewing particular information
            * Update [ModelManager.java](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/ModelManager.java) to include methods for ViewingType and viewing contents.
                * This includes `ObjectProperty<ViewingType>`, `ObservableList<LessonWithAttendees>` and `ObjectProperty<PersonWithDetails>` members in ModelManager.java.
        * Id.UniqueIdMapper
            * [Implemented this interface](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/id/UniqueIdMapper.java) and its method for retrieving HasUniqueId objects when given UniqueIds.
    * Storage
        * Enabled storage for Lessons and Exams. Basically [JsonAdaptedLesson](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/storage/JsonAdaptedLesson.java),
          [JsonAdaptedExam](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/storage/JsonAdaptedExam.java) 
          and [JsonAdaptedTimeslot](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/storage/JsonAdaptedTimeslot.java).
    * UI
        * Viewing Panel
            * Created [ViewingPanel](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/ViewingPanel.java) and the logic behind it to view different things.
            * Created [PersonViewCard](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/PersonViewCard.java) to support viewing a single person.
            * Created [LessonScheduleCard](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/LessonScheduleCard.java)
              and [LessonSchedulePanel](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/LessonSchedulePanel.java) for viewing schedule.
            * Modified other UI components, css and FXML files to enable the viewing panel.
* Documentation
    * User Guide
        * Student lesson and exam commands
        * Schedule Command
        * Added spoiler tag for many student commands
    * Developer Guide
        * Wrote some user stories
        * Add implementation details for **Changing View Panel** and **Lessons**
* Testing

Other contributions:
* Team Tasks:
  * Refactor Student
  * Update each command to refresh view
  * Closed duplicate issues from PED
  * Update all commands to check number of indexes in preamble properly
* Reviewing PRs