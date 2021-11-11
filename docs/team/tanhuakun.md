---
layout: page
title: Tan Hua Kun's Project Portfolio Page
---

My name is Tan Hua Kun and I am one of the developers of Tutor Master.

#### Project Tutor Master

Tutor Master is a desktop application aimed to help Tutors manage and organise their many students and lessons. It is CLI oriented and is made for those who are good with typing.

#### What I have contributed:

View my code contribution here: [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tanhuakun&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=tanhuakun&tabRepo=AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* ##### Features
    * Logic
        * Created Parsers and Commands to deal with lessons and exams, adding people to a group.
        * Created command to view schedule.
    * Model
        * Person
            * Added Lessons List and Exam to [Person.java](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/person/Person.java).
            * Added [PersonWithDetails.java](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/person/PersonWithDetails.java) for the UI to get a person with all his details.
        * Lesson
            * Created the entire [lesson package](https://github.com/AY2122S1-CS2103T-W16-4/tp/tree/master/src/main/java/seedu/address/model/lesson).
        * Model
            * Update [ModelManager.java](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/ModelManager.java) to include methods for ViewingType and viewing contents.
        * Id.UniqueIdMapper
            * [Implemented this interface](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/model/id/UniqueIdMapper.java) for retrieving HasUniqueId objects when given UniqueIds.
    * Storage
        * Enabled storage for Lessons and Exams. Basically [JsonAdaptedLesson](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/storage/JsonAdaptedLesson.java),
          [JsonAdaptedExam](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/storage/JsonAdaptedExam.java)
          and [JsonAdaptedTimeslot](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/storage/JsonAdaptedTimeslot.java).
    * UI
        * Viewing Panel
            * Created [ViewingPanel](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/ViewingPanel.java),
              [PersonViewCard](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/PersonViewCard.java),
              [LessonScheduleCard](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/LessonScheduleCard.java)
              and [LessonSchedulePanel](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/ui/LessonSchedulePanel.java) for the viewing panel.
            * Modified other UI components, css and FXML files to enable the viewing panel.
* ##### Documentation
    * User Guide
        * Student lesson and exam commands
        * Schedule Command
        * [Quick start: Managing Students](https://ay2122s1-cs2103t-w16-4.github.io/tp/UserGuide.html#managing-students)
    * Developer Guide
        * Wrote some user stories
        * Add implementation details for [Changing View Panel](https://ay2122s1-cs2103t-w16-4.github.io/tp/DeveloperGuide.html#changing-view-panel) and [Lessons](https://ay2122s1-cs2103t-w16-4.github.io/tp/DeveloperGuide.html#lessons).
        * Add some instructions for manual testing and an adding lesson use case.
* ##### Testing
    * [Tests for lesson](https://github.com/AY2122S1-CS2103T-W16-4/tp/tree/master/src/test/java/seedu/address/model/lesson)
    * Logic.Parser
        * [Add exams parser](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/test/java/seedu/address/logic/parser/persons/PersonAddExamParserTest.java) and [remove exam parser](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/test/java/seedu/address/logic/parser/persons/PersonRemoveExamParserTest.java)
        * [Add lesson parser](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/test/java/seedu/address/logic/parser/persons/PersonAddLessonParserTest.java) and [remove lesson parser](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/test/java/seedu/address/logic/parser/persons/PersonRemoveLessonParserTest.java)
        * [Add group parser](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/test/java/seedu/address/logic/parser/groups/AddGroupCommandParserTest.java).
    * Logic.Commands
        * [Add group command](https://github.com/AY2122S1-CS2103T-W16-4/tp/blob/master/src/test/java/seedu/address/logic/commands/groups/AddGroupCommandIntegrationTest.java).
    * Storage
        * JsonAdaptedLessonTest and JsonAdaptedTimeslotTest.
    * Modified existing test cases such as JsonAdaptedPerson and Person.
* ##### Team Tasks:
    * [Refactor commands](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/45) to be split into subsections. Original AB3 used "add, delete, edit" but refactored it to use
    "student -a, student -d, student -e" respectively.
    * [Update almost every command](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/206)
    to refresh viewing panel accordingly. This is so that details in the viewing panel are up to date when a command affects the Model.
    * Closed duplicate issues from PED before team members start working on it. For example [here](https://github.com/AY2122S1-CS2103T-W16-4/tp/issues/143) and [here](https://github.com/AY2122S1-CS2103T-W16-4/tp/issues/148) and many more.
    * [Update all commands](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/197) to validate indexes in the preamble properly. An issue highlighted in PED.
* ##### Reviewing PRs
    * Provided some comments, such as [here](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/127) and [here](https://github.com/AY2122S1-CS2103T-W16-4/tp/pull/135).
