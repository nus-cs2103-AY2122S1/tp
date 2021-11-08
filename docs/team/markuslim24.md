---
layout: page
title: Markus's Project Portfolio Page
---

### Project: ManageMe

ManageMe is a **lightweight but powerful desktop application built to help university students manage their school life, available on Windows, Linux and Mac**. You can add your modules, tasks, schedules and online learning resources easily into ManageMe and access them with simple commands.

Given below are my contributions to the project.

* **Major Enhancements**:
  * Added addTask command
    * Description:The addTask command allows users to add a specified task.
    * Reason: addTask is the main method for which users can add a task of their own into ManageMe.
    * Difficulty: Though not too difficult, implementing addTask was quite tedious since it was one of the first few features that we had to implement early on. Hence, this involved creating all the class models related to a task such as TaskName and TaskDescription. The Task related models also had to be modified quite a bit during the development stage before we settled on the final specifications.
  * Added markTask command
    * Description: The markTask command allows users to mark and unmark a specific task as done.
    * Reason: Applications with a task functionality often allow users to mark tasks as done since it is natural for users to want to mark certain tasks as done to distinguish them from undone tasks.
    * Difficulty: The markTask command was quite straightforward for me as it involved creating a task field model and a task command which I was already familiar with when working on addTask.
  * Added archive command
    * Description:The archive command allows users to archive the current data into a timestamped file in the data 
      folder and resets the application with a new data file.
    * Reason: For long-term users who may have plenty of data stored in the application or for users who plan to migrate their data onto a new computer, the archive command provides a convenient way for them to store all their current data into an easy-to-find file for manipulation.
  * Added help command
    * Description: The help command shows a command summary of the application along with the link to the user guide.
    * Reason: The help commands provides a convenient and fast way of showing the available commands in the application to users, especially new ones.
    * Difficulty: Pretty simple as the window for the help command was already in place, though it involved converting the window into a scroll pane so users could easily view the command summary in a smaller window.

* **Minor Enhancements**:
  * Helped create the UI mockup for ManageMe using Figma(Pull request [\#18](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/18))
  * Initialized the code skeleton for Task-related components(Pull request [\#48](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/48))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=markuslim24&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Other Contributions**
  * **Documentation**:
    * User Guide:
      * Created the Introductions and Quick start section (Pull request [\#145](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/145))
      * Added documentation for the `addTask`, `editTask`, `findTask`, `listTask` , `markTask`
        `archive` and `help`features: (Pull requests [\#86](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/86), 
        [\#129](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/129))
    * Developer Guide:
      * Responsible for writing implementation details and diagrams for the Task feature
      * Responsible for updating the model component description and diagrams (Pull request [\#111](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/111))
      * Contributed to the user stories, use cases and non-functional requirements.(Pull request [\#46](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/46))

  * **Contributions to Team-based Tasks**
    * Responsible for checking user guide before each milestone release.
    * Responsible for leading team meetings. This involved setting agendas, facilitating discussions and overall,
      keeping the meeting focused.

  * **Review/mentoring contributions**:
    * As I was in charge of the Task functionalities with Cheng Chuan, most of the reviews for his PRs were done by me.I approved and merge all of his PRs after reviewing to make sure there was no erroneous code.
    * Helped test other team's software and provided feedback and bug reports([Link to feedback](https://github.com/markuslim24/ped/issues))
    * Responsible for writing testcases for all Task-related models (Pull requests [\#56](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/56), [\#60](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/60), [\#77](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/77))
    * Created Task-related testing utilities and wrote out testcases for the `addTask` command (Pull request [\#77](https://github.com/AY2122S1-CS2103T-W11-3/tp/pull/77))
