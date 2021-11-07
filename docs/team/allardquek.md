---
layout: page
title: Allard Quek's Project Portfolio Page
---

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) is a desktop application which helps CS2100 tutors manage
their students’ lab results in a simple and efficient manner, allowing them to spend less time on administrative
processes and more time teaching students.

The following is a summary of my contributions to the
project. ([View Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=allardquek))

#### Features

1. **Added the ability to upload student data**
    - What it does: Allows the TA to upload a CSV file containing students' student ID, class ID, name and email
    - Justification: So that the TA can automate the process of adding students to their classes without having to
      manually type the add command
    - Highlights: This feature involved trying out different third-party libraries and evaluating their suitability (
      e.g. based on their size, ease-of-use, functionality offered). Challenges included handling the multiple
      possibilities for invalid user input with intuitive error messages and good design.
    - Credits: [opencsv](http://opencsv.sourceforge.net/) (third-party library), Stackoverflow
2. **Added the ability to download student data**
    - What it does: Allows the TA to download the current student data into a CSV format
    - Justification: So that the TA can upload the students' lab results onto Luminus gradebook or share them with other
      TAs.
    - Highlights: This feature was challenging as it required integrating a third-party library into the project for the
      first time and ensuring the feature works across different devices. To improve the user experience, I also created
      a popup message to be which required significant exploration with CSS properties and display sizes.
    - Credits: [org.json](https://mvnrepository.com/artifact/org.json/json) (third-party library), Stackoverflow
3. **Added the ability to view a dashboard of student and lab data**
    - What it does: Shows the total number of students, labs and classes, as well as the number of labs left to marked
      for each class
    - Justification: So that the TA can track their progress in marking their students' labs conveniently, especially
      when they have many students
    - Highlights: This feature required a unique solution to overcome the project constraint of not using a database by
      using a TreeMap to store and retrieve students' lab data. Additionally, this feature required learning more about
      javafx components and CSS properties to make the dashboard interface more user-friendly.

#### Other Contributions

1. **Project management:**
    1. Managed releases 1.3-1.3b (2 releases) and issue tracker on GitHub
    2. Set up the GitHub team org, repo, issue labels, Gradle and Notion
    3. Recorded and edited demo videos for v1.3-v1.4 (2 videos)
2. **Enhancements to existing features:**
    - Refactored code and improve code quality for existing features:
      e.g. [#65](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/65)
      , [#79](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/79/files)
      , [#115](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/115)
      , [#117](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/117)
      , [#128](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/128)
      , [#152](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/152)
      , [#155](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/155)
      , [#176](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/176)
      , [#211](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/211)
      , [#232](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/232)
    - Improve testing: e.g. [#100](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/100)
      , [#103](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/103)
      , [#106](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/106/files)
      , [#108](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/108/files)
      , [#130](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/130/files)
    - Various bug fixes: e.g. [#74](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/74)
      , [#200](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/200)
      , [#268](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/268)
      , [#359](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/359/files)
      , [#360](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/360/files)
3. **Documentation:**
    - User Guide: Added documentation for table of contents, command syntax information, `upload`, `download`
      , `dashboard`
    - Developer Guide: Added implementation details for the `upload`, `download`, `dashboard` commands
4. **Review and Mentoring Contributions:**
    - PRs reviewed (with non-trivial review comments): [#68](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/68)
      , [#113](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/113)
      , [#160](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/160)
      , [#161](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/161)
      , [#211](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/211#issuecomment-950448914)
      , [#375](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/375)
    - Contributed to forum discussions: [#142](https://github.com/nus-cs2103-AY2122S1/forum/issues/142)
      , [#297](https://github.com/nus-cs2103-AY2122S1/forum/issues/297) [#322](https://github.com/nus-cs2103-AY2122S1/forum/issues/322)
      , [#335](https://github.com/nus-cs2103-AY2122S1/forum/issues/335)
      , [#350](https://github.com/nus-cs2103-AY2122S1/forum/issues/350#issuecomment-954135759)
      , [#352](https://github.com/nus-cs2103-AY2122S1/forum/issues/352)
    - Reported bugs and suggestions for other teams in the class: [#10](https://github.com/AllardQuek/ped/issues/10)
      , [#11](https://github.com/AllardQuek/ped/issues/11), [#14](https://github.com/AllardQuek/ped/issues/14)
      , [#16](https://github.com/AllardQuek/ped/issues/16), [#18](https://github.com/AllardQuek/ped/issues/18)
      , [#19](https://github.com/AllardQuek/ped/issues/19), [#255](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/255)
