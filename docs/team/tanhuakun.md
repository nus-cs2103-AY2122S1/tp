My name is Tan Hua Kun and I am one of the developers of Tutor Master.

What I have contributed so far:
* Documentation
    * User Guide
        * Student lesson and exam commands
        * Schedule Command
        * Added spoiler tag for many student commands
    * Developer Guide
        * Wrote some user stories
        * Add implementation details for **Changing View Panel** and **Lessons**
* Features
    * Logic
        * Created commands to deal with lessons and exams.
        * Created command to view schedule.
        * Ensured refreshing of viewing panel after each command
        * Refactored original AB3 commands to be placed under `student` package
    * Model
        * Person
            * Added Lessons List and Exam to person.
        * Lesson Package
            * Created the entire Lesson package.
        * Model
            * Added ViewingType for viewing particular information
            * Update ModelManager to include methods for ViewingType and viewing contents.
        * Id.UniqueIdMapper
            * Interface that can be used to retrieve the objects represented by UniqueIds.
    * Storage
        * Enabled storage for Lessons and Exams
    * UI
        * Viewing Panel
            * Created ViewingPanel and the logic behind it to view different things.
            * Created PersonViewCard to support viewing a single person.
            * Created LessonScheduleCard and LessonSchedulePanel for viewing schedule.

View my code contribution here: [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=tanhuakun&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=tanhuakun&tabRepo=AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
