## **EdRecord**
EdRecord is a **desktop app for managing student contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a TA that can type fast, EdRecord can get your student contact management tasks done faster than traditional GUI apps.

---
* [Code Contributed](#code-contributed)
* [New Features Implemented](#new-features-implemented)
* [Enhancements implemented](#enhancements-implemented)
* [User Guide](#user-guide)
* [Developer Guide](#developer-guide)
* [Team-based tasks](#team-based-tasks)
* [Reviewing/Mentoring](#reviewingmentoring)
* [Beyond the project](#beyond-the-project)

* [Code Contributed](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=FergusMok&tabRepo=AY2122S1-CS2103-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **New Features implemented**
* Make and Integrate ModuleSet
    * The ModuleSet component is the component for holding all the modules and classes for students. Furthermore, it is one of the main components in commands, such as RemoveCommand, MoveCommand and AddCommand
* **Enhancements implemented**
* Enable Info to be optional field
    * This allows the user to choose whether to specify Info, matching the behavior for Tags. Furthermore, the UI is configured to not display the Info field if Info is blank.
* Bug fixing
    * 2.1 Fix module letter casing
    * 2.2 Fix allowing non-existent groups in AddCommand
* Tests Creation
    * 3.1 Added tests for MakeGroupCommand
    * 3.2 Added tests for ModuleSet
* Fix Bugs:
	4.1 Standardize all modules to have upper letter casing 
	4.2 Fix bug where adding people with non-existent class 
	4.3 Fix bug where adding non-existent class gives incorrect message.
* Validation:
	5.1 Validate phone numbers
* Refactoring work
    * 6.1 Refactor AB3 into EdRecord
    * 6.2 Refactor AddAssignment to MakeAssignment
    * 6.3 Refactor Address to Info
* **User Guide**
* Updated User Guide for v1.3 
* **Developer Guide**
* ModuleSet implementation explanation
* Effort appendix
* Use and images cases for Remove and AddCommand
* **Team-based tasks**
* Set up the team's repo and RepoSense.
* Set up the team's project website.
* Design the initial UI mockup.
* Completed Demo v1.3 based on v1.2

* **Reviewing/Mentoring**
* Contributed to forum discussion (example: [#60](https://github.com/nus-cs2103-AY2122S1/forum/issues/60))
* Reported major bugs for other teams which I was the sole reporter. (examples: [#214](https://github.com/AY2122S1-CS2103T-F11-1/tp/issues/214), [#198](https://github.com/AY2122S1-CS2103T-F11-1/tp/issues/198))
* Reviewed and gave suggestions for teammates PRs (examples: [#55](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/55), [#92](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/92) (which was followed up in [#102](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/102)))
