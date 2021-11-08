---
layout: page
title: Cheng Chuan's Project Portfolio Page
---

### Project: ManageMe

ManageMe is a **lightweight but powerful desktop application built to help university students manage their school life, available on Windows, Linux and Mac**. You can add your modules, tasks, schedules and online learning resources easily into ManageMe and access them with simple commands.

Given below are my contributions to the project.

* **Major Enhancements**
  * Added Time Component and its related managers and interfaces (PR #112)
    * Description: An additional component (on top of Logic, Model, Storage, UI and Commons)
    * Reason: To facilitate the implementation of the Reminder function and other time related components.
    * Difficulty: This was rather difficult to implement despite the few lines of code it is represented by.  
    Ultimately, I settled with the Threads class to constantly keep track of the current time, which allowed for subsequent implementation of time related components.
      <br><br>
  * Added Reminder Function (PR #112)
    * Description: The reminder function is a key part of ManageMe in that it reminds the user in the form of a pop up
      when the start time is reached or when the task is already in progress.
    * Reason: As a time-and-task management application, it is of utmost importance for there to be a feature to remind
      users when an upcoming important task is happening. For our target users, which are students, it can serve as a way
      to keep track of time and maximise productivity.
    * Difficulty: Most of the difficulty came from the implementation of the time component as written above.
      <br><br>
  * Added Edit Task Command (PR #59)
    * Description: The Edit Task Command allows the user to edit the specified task.
    * Reason: For ManageMe, adding tasks are a core feature we have but in the event that the user typed in something
      wrong, they can use the edit task function to edit.
    * Difficulty: Moderate
      <br><br>
  * Added Delete Done Task Command (PR #125)
    * Description: The Delete Done Task Command deletes ALL tasks that have been marked as done previously using the
      markTask Command.
    * Reason: As a user uses ManageMe, over time tasks that have been completed will be marked as Done. However, we
      as developers, decided not to automatically remove those "Done" tasks to prevent cases of accidental marking.
      This command allows specifically all "Done" tasks to be cleared from the database.
    * Difficulty: Moderate
      <br><br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=zhangchengchuan&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=zhangchengchuan&tabRepo=AY2122S1-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
  <br><br>

* **Other Contributions**
  <br><br>
  * **Contributions to User Guide**
    * Added alert boxes to sections that needed more attention from users.
    * Made QoL changes such as a hyperlink back to the Table of Contents at the end of every section
      <br><br>

  * **Contributions to Developer Guide**
    * Responsible for the writing and editing of the storage component. (PR#112)
    * Wrote the implementation of the time component as well as the reminder functions. (PR #242)
    * Helped to type out all the user stories and contributed to brainstorming of Glossary terms and Non-Functioning Requirements.(PR#46)
      <br><br>

  * **Contributions to Team-based Tasks**
    * Responsible for the writing and editing of the other website files like settings, testing. (PR #4)
    * Responsible for setting of the team's Continuous Integration and Continuous Deployment. (PR #4)
      <br><br>

  * **Review Contributions**
    * As I was in charge of the Task functionalities with Markus, most of the reviews for his PRs were done by me.
      I approved and merge all of his PRs after reviewing to make sure there was no erroneous code.
      <br><br>
