---
layout: page
title: Shiyuan Yang's Project Portfolio Page
---

### Project: TuitiONE

"TuitiONE" is carried out as a team project and implemented under the guidance of NUS CS Module CS2103T: Software Engineering. It is built as a brown-field project based on the pre-existing project _"AddressBook - Level 3"_ (A desktop address book application used for teaching Software Engineering principles).

"TuitiONE" is a Command Line Interface (CLI) focused desktop application that aims to simplify the work of Customer Servicing Officers (CSO) in a tuition centre. It allows CSOs to input the details of students and parents through simple and intuitive commands. Incorporated with JavaFX and FXML, the application also offers a Graphical User Interface (GUI) for a better user experience.

With the primary programming language written in Java, the team's contribution totals up to 10 kLoC.

#### Given below are my contributions to the project.

* **New Feature**: Added the ability to add lessons into the application - [`add-l`](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/89)
  * What it does:<br> Adds a unique lesson into the application for students to enroll in.
  * Justification:<br> This feature is necessary to be implemented in the project. This is because we need to create a lesson in the application in order for students to be enrolled in.
  * Highlights:<br>This feature will parse the lesson-related parameters to ensure validity of each of the parameters. This function will ensure the lesson added is unique and bounded within the acceptable timing of 0900-1900.

* **New Feature**: Added roster command - [`roster`](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/179)
  * What it does:<br> This command will display all the students who are enrolled in a specific lesson to the GUI.
  * Justification:<br> This feature is a good to have to allow CSOs to quickly see who are the students enrolled in a specific lesson.
  * Highlights:<br>This feature will display a filtered student list and lesson list to the GUI. This will present a clean view of all students who are enrolled in a specific lesson.

* **New Feature**: GUI Add [Lesson Panel](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/97)
  * What it does:<br>A lesson panel that displays all lessons available in the tuition center.
  * Justification:<br>This feature is a must to have in order to incorporate the lesson list and student list together.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=timothyoung97)

* **Project management**:
  * Helped releases `v1.3` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#200](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/200), [\#189](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/189), [\#73](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/73), [\#97](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/97))
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#113](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/113))

* **Documentation**:
  * User Guide:
    * [Update Project Page](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/26)
    * [UG Formatting](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/91)
    * [UG Mass Update](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/154)
    * [Image update and label](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/226)
  * Developer Guide:
    * [Update DG Use Case - Update Student's Details](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/43)
    * [DG Mass Update 1](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/160)
    * [DG Mass Update 2](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/162)
    * [Update Add Lesson Implementation in DG](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/165)
    * [Update Add Lesson Command's activity diagram](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/171)
    * [Fix Add Lesson Seq Diagram](https://github.com/AY2122S1-CS2103T-F13-4/tp/pull/176)

* **Tools**:
  * Integrated a new Github plugin (CircleCI) to the team repo
  * Utilize SceneBuilder to build GUI Layout
