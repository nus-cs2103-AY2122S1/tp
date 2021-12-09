---
layout: page
title: Bernard Wan De Yuan's Project Portfolio Page
---
### Project: TimesTable

TimesTable is a desktop address book plus planner application for tuition teachers to keep track of their students
and classes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java,
and has about 25 kLoC.

Given below are my contributions to the project.


* **New Feature**: Added the `removefromclass` command to remove one or more students from an existing class.
    * Justification: A core feature to remove students that left.
    * Highlights: The implementation of this command was especially challenging as the only information that the command has
      are the student indices and the class index. These indices are based on the view displayed by the GUI, which is 
      affected by `sort` and `find` commands. Furthermore, only student names are stored in the `TuitionClass` object to reduce
      update cascading. As such, the correct filters and sorting must be applied to the overall student list to obtain the student
      names in the right order as displayed by the GUI.
    * Pull requests: [\#117](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/117), [\#147](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/147),
      [\#260](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/260), [\#266](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/266)
      , [\#301](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/301)
      

* **New Feature**: Added the `deleteclass` command to delete existing tuition classes.
    * Justification: A core feature for the user to get rid of older classes that they have finished teaching.
    * Highlights: Deleting the entire class is much simpler than removing students from a class. The most challenging aspect
    would be to ensure that the index of the class to be deleted corresponds to the actual class list order displayed on the GUI
      after any `sort` or `find` commands.
      * Pull requests: [\#104](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/104), [\#273](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/273)


* **New Feature**: Updated the behaviour of the `findname` and `findtag` command to support more natural use cases.
    * What it does: Changed the functionality of the two `find` commands for students to support partial matches
      and to allow for multi-word searches.
      * Justification: The different types of `find` commands needs different logic as what the user will want to do varies.
      For name searches, users would often want to search for first name together with last name. As such, the command should treat
      the multi-word keyword as a single sequence. For the `findtag` command, users might search for just `math` and expect to find
      all tags that contain math. As such, partial matching is required.
    * Highlights: Previously, whitespace was used as a delimiter to obtain separate keywords. However, to use multi-word keywords,
      another delimiter, a comma, was used. Also, the logic had to be adjusted to allow for partial matches, ignoring case.
    * Pull requests: [\#145](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/145), [\#159](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/104),
      [\#163](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/163)
      

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=bernardwan&tabRepo=AY2122S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
    * Added some initial code for the class feature in the model - UniqueClassList and JsonAdaptedTuitionClass. ([\#104](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/104))
    * Wrote additional tests for existing features to increase test coverage ([\#260](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/260), [\#266](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/266), [\#273](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/273))
    * Updated the sample data to fill out the timetable, have more students with realistic data. ([\#145](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/145), [\#164](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/164))
    * Add tests for NOK feature ([\#66](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/66))
    * Added location field ([\#58](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/58))


* **Documentation**:
    * User Guide:
        * Added section for location field. ([\#37](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/37))
        * Updated behavior for find features. ([\#145](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/145))
        * Update UG and examples for findname and findtag. ([\#163](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/163))
        * Added section for proposed feature of invoice generation. ([\#323](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/323))
    * Developer Guide:
        * Updated NFRs section to include relevant information. ([\#12](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/12))
        * Added implementation details of the `removefromclass` feature. Added UML diagrams for the command. ([\#82](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/82), [\#301](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/301))

* **Contributions to team-based tasks**
    * Set up project website:
        * Set up github pages, changed settings, changed names and links from AddressBook to TimesTable
    * Landing Page ([\#133](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/133) , [\#306](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/306)):
        * Updated landing page to reflect TimesTable contents, added a section for each major feature, added pictures.
    * Project Demo for 1.3:
        * Recorded gifs and screenshots for different features.
    * Helped maintained issue tracker:
        * Created new issues, added milestones and assignees, closed relevant issues.

* **Community**:
    * PRs reviewed (with non-trivial review comments):  [\#303](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/303)
    * Reported bugs for other teams during PE-D.  [PE-D](https://github.com/bernardwan/ped/issues)
  
