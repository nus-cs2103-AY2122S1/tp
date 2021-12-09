---
layout: page
title: Lin Zhiwei's Project Portfolio Page
---
### Project: TimesTable

TimesTable is a desktop address book plus planner application for tuition teachers to keep track of their students
and classes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java,
and has about 25 kLoC.

Given below are my contributions to the project.

* **New Feature**: Integrated the `TuitionClass` into the model
    * What it does: serves as the backbone for our application as our application needs a tuition class entity in its
  model.
    * Justification: Same as above, it serves as backbone for our project. 
    * Highlights: I wrote the initial setup for tuition class and added new classes to model a tuition class's 
attributes such as StudentNameList. I made the design decision for how we keep track of students in classes so as to
minimize dependency.
    * PRs: [\#56](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/56),
      [\#60](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/60),
      [\#104](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/104),
      [\#127](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/127),
      [\#139](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/139),
      [\#151](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/151),
      [\#152](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/152)...
    
* **New Feature**: Added numerous basic commands related to tuition class such as `addclass`, `editclass` 
and `addtoclass`
  * What it does: adds the basic functionality of being able to interact with tuition classes such as adding a class,
editing a class and adding students to classes.
  * Justification: The functionalities mentioned above are the basic features that our application should have given our
goal and target user.
  * Highlights: Add commands and edit commands are relatively easier than addtoclass command which require designing 
a new command format. I ended deciding to use indices of currently displayed list as it is the most convenient and
intuitive to use.
  * PRs: [\#70](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/70),
    [\#104](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/104),
    [\#127](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/127),
    [\#160](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/160),
    [\#237](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/237),
    [\#253](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/253),
    [\#263](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/263)...
  
* **New Feature/Enhancements to existing features**: greatly enhanced the search capabiltiy of our application by 
adding `findtag`, `findclass` and `findclassname` commands
  * What it does: allows user to find students by the tags they have and also find classes by class timing and class
name.
  * Justification: Users should be able to have such search functionalities as mentioned above to find quickly students
or classes they are interested in.
  * Highlights: Adding find commands for each different attributes have various nuances that one has to look out for and 
I have learnt to be more careful around these nuances.
  * PRs: [\#70](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/70),
    [\#112](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/112),
    [\#158](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/158)...
  
* **Code contributed**: [Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabAuthor=softmagnet&tabRepo=AY2122S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)

* **Documentation**
  * User Guide: Added some documentation for updated features
    * PRs: [\#170](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/170), 
          [\#254](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/254)
  * Developer Guide (DG): Added DG for find, addtoclass commands and updated DG for model component
    * PRs: [\#88](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/88),
            [\#293](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/293),
            [\#308](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/308),
            [\#312](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/312),
            [\#326](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/326)...
      
* **Community**
    * PRs reviewed (with non-trivial review comments):  [\#232](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/232), 
  [\#117](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/117),
  [\#53](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/53),
  [\#25](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/25),
  [\#17](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/17)

    * Reported bugs for other teams during PE-D: [ped issues github](https://github.com/softmagnet/ped)
