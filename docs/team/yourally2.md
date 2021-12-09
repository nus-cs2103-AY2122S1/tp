---
layout: page
title: Kevin Chua's Project Portfolio Page
---

### Project: TimesTable

TimesTable is a Class Management desktop application for tuition teachers to keep track of their students 
and classes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, 
and has about 25 kLoC.

Given below are my contributions to the project.

* **New feature**: Added the GUI and related behaviour for the class tab (Pull Request: [\#103](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/103))
  * What it does: Allows the user to visually see which students belong to each respective class by double clicking on the class
    or using a command `class INDEX` which is described in another section.
  * Justification: Visuals allows the user to quickly tell which students belong to a class at a glance. 
  Forms the backbone of the class feature as all interactions with the class is done in this tab.
  * Highlights: I gained a deep understanding of how the GUI functioned together with greater understanding of different design patterns.

* **New feature**: Add `class` feature to view a specific class without double clicking (Pull Request: [\#103](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/103))
  * What it does: It shows the students belonging to the specified class. Does the same thing as double clicking on a class.
  * Justification: It allows us to perform the same functionality without using a mouse, which is important.
  * Highlights: It was challenging to modify the GUI using a command as it meant a lot of coupling, so after discussing with Stuart we decided to follow the Observer pattern,
    which proved to be very useful.

* **New feature**: Overloaded `edit` and set up base for overloading `add` command to allow user to add the next-of-kin (nok) of the Student. (Pull Requests: [\#26](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/26) [\#47](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/47))
  * What it does: Allows the user to add/edit a Student's nok with name, address, and location when adding/editing a Student.
  * Highlights: This enhancement went through several iterations as we initially implemented the
  `nok` command to specifically to add/edit an nok, but settled on overloading the `add` and `edit` command because it would
  be faster and less confusing to add an `nok` and the Student at once.
 
* **New feature**: Store next-of-kin (nok) information in a nested json (Pull Request: [\#65](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/65))
    * Highlights: I decided to nest the json in a key `nok` instead of having fields like `nok_name`, `nok_phone` etc so it is clearer what the fields are for. Nesting json objects proved to be more complex compared to strings.

* **New feature**: Improved colours for the GUI overall (Pull Request: [\#217](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/217))
    * Highlights: Finding the right combination of colors that the entire team agreed on proved to be more difficult than expected and the colors went through several iterations before the team could decide on a color. In the process, finding the css for specific components to change was surprisingly harder than expected as the documentation for the css in javafx is often unclear.

* **Code contributed**: [Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=yourally2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=yourally2&tabRepo=AY2122S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  
* **Enhancements to existing features**:
    * Make Student and next-of-kin (NOK) inherit from Person to implement DRY principles (Pull Request [\#30](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/30))
    * Limit length and number of tags to prevent GUI from covering other information and added tests (Pull Request [\#319](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/319))
    * Wrote tests for `FindClassCommandParser`, `FindClassCommand`, `SelectClassCommand` and `SelectClassCommandParser` (Pull Request [\#258](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/258) [\#302](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/302))
    * Bug fixes for GUI, `class` command, Student list in `classes` tab and `addtoclass` command (Pull Request 
      [\#248](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/248),
      [\#243](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/243),
      [\#304](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/304))
  
* **Documentation**
  * User Guide:
    * Ensured entire UG flowed well (Pull Request [\#224](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/224), [\#303](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/303))
    * Added section for proposed feature of attendance marking in Payment Management feature. ([Commit](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/319/commits/effd6fbe0d67a6ed821fa16581e7ed3fd3e9cd32))
  * Developer Guide
    * Added UML class diagram, Sequence Diagram for Ui component and the ClassesUi component (Pull Request [\#291](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/291) [\#309](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/309))
    * Added noteworthy implementation details for `nok/` field (Pull Request [\#292](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/292), [\#291](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/291))
    * Added manual testing for `addtoclass`, `listclass`, `delete`, `sort` commands (Pull Request [\#327](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/327) [\#86](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/86))
    * Added user stories (Pull Request [\#22](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/22))
  
* **Community**
  * PRs reviewed (with non-trivial review comments): (Pull Request
    [\#256](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/256),
    [\#81](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/81),
    [\#50](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/50),
    [\#311](https://github.com/AY2122S1-CS2103T-F11-1/tp/pull/311)
  * Reported bugs for other teams during PE-D: [ped issues github](https://github.com/yourally2/ped/issues)
