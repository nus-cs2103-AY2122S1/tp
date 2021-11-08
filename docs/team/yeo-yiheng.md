---
layout: page
title: Yeo Yiheng's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA<sup>2</sup> is a desktop productivity application developed for teaching assistants to better manage student contacts and keep track of student's assignments.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find information based on various tags.
  * What it does: This feature allows the user to find students based on names, modules and / or tags.
  * Justification: This feature allows users to filter the list of students should they need to find someone specific.
  * Highlights: This command was challenging to implement due to the fact that many scenarios had to be considered to protect the 
  application from malicious use or unintentional erroneous input. An example is finding with an empty prefix.
  * Credits: The command is an existing overhaul on the original `FindCommand` in the [AddressBook-Level3 (AB3)](https://github.com/nus-cs2103-AY2122S1/tp) project.
  The implementation has been largely revamped, though the concept of finding remains the same.

* **New Feature**: Added a user-friendly command input termed **Friendly Commands** to the giving of assignments.
  * What it does: This feature allows the user to set deadlines for assignments in a very simplified manner
  * Justification: This feature further improves efficiency in information management by reducing the amount of text a user has to input to set a deadline.
  * Highlights: This feature has stretched my creativity in deciding what will be beneficial to the user to improve efficiency, and what will go against
  the efficacy and instead serve as a drawback. Eventually, I settled on the simple and shortened forms of the upcoming days such as **mon** to represent
  the upcoming monday. After adding friendly command inputs for the `find` command, I wanted to extend this idea onto other commands to improve user efficiency
  but had to decide against it in the interest of time.
  * Credits: [Java TemporalAdjusters API](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/TemporalAdjusters.html)
  * Pull Requests: [\#102](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/102)
  
* **New Feature**: Created a Module to represent each student's enrolled module
  * What it does: This feature allows the user to classify students based on their enrolled module.
  * Justification: This feature further improves the classification of students, where users can now easily identify which student and assignment list to look at.
  * Highlights: The regular expression for detecting a module was a challenge to implement due to the vast options of module codes available. I had to decide 
  whether we were strictly confining the pattern to NUS modules only or be flexible enough to accommodate other university's module codes.
  * Credits: The class is an existing overhaul on the address class of [AddressBook-Level3 (AB3)](https://github.com/nus-cs2103-AY2122S1/tp) project. 
  * Pull Requests: [\#62](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/62)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=yiheng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=yeo-yiheng&tabRepo=AY2122S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Ensuring that the overall documentation of the program is up to standard with what is the convention and required.

* **Enhancements to existing features**:
    * Changed the attributes of a defined Person (Pull requests [\#62](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/62))
    * Improved command tolerance to incorrect user inputs such as random alphabet casing
(Pull requests [\#138](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/138), [\#214](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/214)
, [\#214](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/214), [\#220](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/220), [\#228](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/228))
    * Standardized representation of information in the GUI (Pull requests [\#98](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/98))

* **Documentation**:
    * User Guide:
        * Added documentation for the features of `find` [\#89](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/89), [\#131](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/131)
        * Did cosmetic tweaks to existing documentation of features `give`, `find`: [\#237](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/237)
        * Created a table of friendly commands for easier user retrieval. [\#115](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/115)
      
    * Developer Guide:
      * Added documentation for the features of `find` [\#89](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/89)
      * Added documentation for friendly command inputs [\#270](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/270)
      * Updated documentation for the implementation of `give`, formerly named `a-add` [\#115](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/115)
      * Updated documentation for the **Logic** component.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#130](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/130#discussion_r735115596)
