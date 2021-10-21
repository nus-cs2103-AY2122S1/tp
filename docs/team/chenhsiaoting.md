---
layout: page
title: Chen Hsiao Ting's Project Portfolio Page
---

### Project: Socius

Socius is a simple desktop app for managing CS2103T tutorial classmates’ contacts for international students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Socius can get your contact management tasks done faster than traditional GUI apps.

Socius helps you to gather information about your CS2103T tutorial classmates and facilitate the forming of project groups with them.

Given below are my contributions to the project.

* **New Feature**: Added the Nationality and Remark models to a Person.
  * What it does: allows a person's contact to contain nationality and a unique remark defined by the user. 
  * Justification: This feature improves the product by offering more personal details of a classmate and allows user to customise messages for his classmate based on his impression of the person.
  * Highlights: This enhancement affects existing commands and commands to be added in future as it introduces new parameters to the Person class.
  * Credits: The remark model was taught in Week 6, extracted from AB-3.

* **New Feature**: Added the ability to delete multiple contacts given any keywords.
  * What it does: allows the user to delete all contacts with matching keywords, and support matching all properties of a Person.
  * Justification: This feature improves the product significantly because a user may want to delete multiple contacts and the app should provide a convenient way to do it all with one command.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * [SAMPLE] Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub
  * Arranged and led the weekly team discussions in setting goals and dividing work amongst members.
  * Set up issues and milestones on GitHub.
  * Keep track of team progress and weekly submission deadlines. 

* **Enhancements to existing features**:
  * [SAMPLE] Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * [SAMPLE] Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for AboutUs, including roles and responsibilities [\#49]()
  * Developer Guide:
    * Added documentation for user profile and value proposition [\#17]()
    * Added use cases of the `deletem` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#60](), [\#91]()
  * [SAMPLE] Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * [SAMPLE] Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * [SAMPLE] Integrated a third party library (Natty) to the project ([\#42]())
  * [SAMPLE] Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
