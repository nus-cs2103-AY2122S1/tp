---
layout: page
title: Keith's Project Portfolio Page
---

### Project: LeadsForce

LeadsForce - LeadsForce is a desktop application catered towards student financial advisors (FA) for managing their leads. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added numerous client fields (Pull requests: [\#62]())
  * What it does: allows the user to store financial specific client information (etc risk appetite, disposable income)

* **New Feature**: Added the ability to sort clients based off different client fields. (Pull requests: [\#64]())
  * What it does: allows the user to sort clients in ascending or descending order based off a declared client field
  * Justification: This feature improves the user experience for FAs significantly when managing a large address book as it allows them to identify key clients 
  * Highlights: This implementation was challenging as there were numerous client fields to take account for. This required a comparator that took into account the values of the fields as well as take into account that certain fields might be empty as they are optional fields.

* **New Feature**: Added meeting list GUI (Pull requests: [\#120](), [\#136]())
  * What it does: allows the user to quickly sieve through all scheduled upcoming meetings
  * Justification: This feature improves the user experience for FAs significantly when managing a large address book as it allows them to be able to quickly refer to all upcoming meetings sorted based on date and time.
  * Highlights: This implementation was challenging as it requires a lot of changes to logic, storage and Gui. Also, when linking the GUI to the edit command as NextMeeting object would have to also listen to the any changes in the name field for its associated client.

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features**:
  * Improved Add command to cater to optional fields (Pull requests: [\#62]())
    * Implemented numerous client interfaces to allow for optional fields to be passed empty inputs
    * Modified parser to only require compulsory fields for add command
  * Updated the delete command (Pull requests: [\#62]())

* **Documentation**:
  * User Guide:
    * Did cosmetic tweaks to existing documentation of features `add`: [\#79]()
  * Developer Guide:
    * Updated class diagrams for model, ui and logic component [\#124]()
    * Added implementation details of the `sort` feature. [\#124]()

* **Community**:
  * Reported bugs and suggestions for other teams in the class
