---
layout: page
title: Preshita's Project Portfolio Page
---

### Project: TutorAid

TutorAid is a desktop application used for helping tech-savvy private tutors, who have busy schedules/many students, to keep track of all of their students' and lessons' details. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.
 
* **Code contributed**:
  My contribution to the TutorAid project repository can be found [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=preshita01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)
  
* **Enhancements to existing features**:
  * Improved the _Find_ feature
    - Relevant PR: [#122 Find commands](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/122)
    - Justification: 'Find' feature was enhanced to return all students or lessons that contain the search words instead of requiring an exact word match to increase its functionality.
  * Improved the _Help_ feature
    - Relevant PR: [#109 'Help' feature](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/109)
    - Justification: 'Help' feature was enhanced to serve as a quick offline user guide instead of simply providing users with a link to the complete online user guide. This was done to meet one our non-functional requirements: 
      *The product should be fully functional offline and should not rely on internet connection to carry out any of its functions.* 
  * Introduced the idea of adding another layer of abstraction between the parsing of a command and the actual execution of it
    - Relevant PR: [#89 Refactor parsing of add and delete commands](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/89)
    - Justification: In our current implementation, multiple commands could share the same command word (e.g.`add` can be used to add both students and lessons into TutorAid) and command flags are additionally used to different between such commands (e.g.`add -s` for students and `add -l` for lessons). 
      Thus, instead of expanding the current Parser's responsibility to also extract the command flag, additional classes were added do this. 
      
* **Contributions to UG**:
  * Added the documentation for the features `add -s`, `del -s`, `find -s`, `find -l` and `help`.
  * Added the documentation on adding a student into a lesson and creating a new progress entry for a student in the _Beginner's Tutorial_ section.
  * Added a _Glossary_ section to define some key terms, such as _Progress_, that users may be unsure about.
  
* **Contributions to DG**:
  * Added on to the target user profile, value proposition, user stories, use cases, non-functional requirements and glossary.
  * Added the documentation for the 'Add a student' feature, which includes a summary of the feature's implementation, sequence diagrams and design considerations.
  * Added the documentation for `Logic` component. 
  * Added an _Introduction_ section.

* **Contributions to team-based tasks**:
  * Delivered the product demos for v1.2 and v1.3.
  * Handled the release of TutorAid v1.3.
  * Formatted the whole UG and DG: Ensured that the there is consistency and smooth transitions within every section and between the different sections in each document.

* **Review/mentoring contributions**:
  * PRs reviewed with suggestions on test cases: [#119](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/119) and [#106](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/106)
  * PRs reviewed with suggestions on improving code quality and coding standards: [#118](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/118) and [#105](https://github.com/AY2122S1-CS2103T-W16-3/tp/pull/105)
  
* **Contributions beyond the project team**:
  * Reported [11 bugs and suggestions](https://github.com/Preshita01/ped/issues) for Team CS2103T-F11-2 during the Practical Exam - Dry Run.
