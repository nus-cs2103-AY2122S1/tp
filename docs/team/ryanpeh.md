---
layout: page
title: Ryan Peh's Project Portfolio Page
---

### Project: SmartNUS

**SmartNUS** is a desktop app specifically designed for students to revise for their exams. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 17 kLoC.

Given below are my contributions to the project.

- **New Feature**: Implemented Overall Quiz Functionality

  - Implemented quiz functionality to allow the user to quiz themselves on various question types from the question bank. This includes answering questions, navigation between questions and tracking of quiz progress.
  - Justification: This is a core feature of SmartNUS, as the application is designed to promote active recall through quizzing yourself.
  - Highlights: This enhancement required an analysis on various design implementations. The eventual design included the implementation of an alternative parser during to quiz to parse user inputs without affecting the main window, as well as a `QuizManager` class to manage the logic behind the quiz.

- **New Feature**: Designed and Implemented Quiz User Interface

  - Designed and implemented a graphical user interface (UI) for the quiz feature of SmartNUS, taking into account user (UX) to ensure an intuitive and easy-to-use user experience of the quiz functionality.
  - Justification: As one of the core features of SmartNUS, it is important for the quiz to have a well-developed UI/UX to keep the user interested in using the application.
  - Highlights: The interface includes the support for displaying various question types during the quiz (Multiple Choice Questions, True False Questions, Short Answer Questions), and is easily extensible to allow for easy addition of additional question types in the future.
  - Credits: The code for the user interface was adapted from the AddressBook-Level3 project by [SE-EDU initiative](https://se-education.org).

- **Other Features and Enhancements to Existing Features**:

  - Wrote additional tests for existing features to increase coverage (Pull requests [\#60](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/60/files), [\#78](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/78)).
  - Resolved issues raised during Practical Exam Dry Run (Pull requests [\#194](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/194), [\#206](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/206), [\#216](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/216), [\#221](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/221))

- **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=ryanpeh)

- **Documentation**:

  - User Guide (Pull requests [\#117](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/117), [\#194](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/194) [\#203](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/203)):
    - Added documentation for all quiz-related features.
    - Revised the layout of user guide to make it more intuitive and easy to use.
    - Added the `About` section to instruct the user on how to go about understanding the user guide.
    - Resolved various issues raised during the Practical Examination Dry Run.
  - Developer Guide (Pull requests [\#76](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/76), [\#220](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/220), [\#246](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/246)):
    - Elaborated on changes to `Logic` component documentation.
    - Added design details for `Quiz` component.
    - Added documentation for quiz related features.

- **Project Management**:

  - Set up team GitHub organisation, repository and project website.
  - Integrated a new Github plugin (CodeCov) to the team repo.
  - Managed deadlines and deliverables for the group, including updating the issue tracker and milestones.
  - Managed issues raised during the Practical Examination Dry Run.

- **Team-Based Tasks**:
  - PRs reviewed (with non-trivial review comments): Pull requests [\#96](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/96), [\#207](https://github.com/AY2122S1-CS2103T-F12-1/tp/pull/207)
  - Reported bugs for other teams ([bugs reported](https://github.com/ryanpeh/ped/issues))
