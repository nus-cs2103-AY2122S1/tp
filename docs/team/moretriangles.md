---
layout: page
title: Adam Oh Zhi Hong's Project Portfolio Page
---

## Project: CohortConnect

CohortConnect is an advanced desktop address book which facilitates networking among Computer Science (CS) students, in a university setting. It contains advanced features for managing multiple contacts and finding teammates from GitHub data. It is optimised for a CLI, but also has a GUI built using JavaFX. It is written in Java 11 and has about 17k lines of code.

**Features Implemented**

My code contributions can be found in the [tP Code Dashboard](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=moreTriangles&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=moreTriangles&tabRepo=AY2122S1-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).

* Export Command
  * Description: Allows users to export their contacts to a JSON or CSV file for sharing and storing of large amounts of contacts.
  * Highlight: Support for CSV was added as it is a more commonly used file format, and can be viewed or modified with Excel.
  * Difficulty: Difficult, as I had to add code to the Logic, Model, Storage and Util components, adhering to the existing design patterns. Furthermore, usage of Jackson CSV module introduced regressions which took a long time to fix.

* Import Command
  * Description: Allows users to import a list of contacts from a JSON or CSV file, populating their address books without entering contacts manually.
  * Highlight: Support for CSV was added as it is a more commonly used file format. Professors will have an easier time populating CSV files in Excel.
  * Difficulty: Intermediate. With experience implementing Export, I was able to code faster.

* Command History
  * Description: Allows users to recall previous commands using Up and Down arrow keys. Users can retype misspelled commands, repeat previous commands quickly, or just to browse through previous commands.
  * Highlight: This feature ties into the target user's preference for the Command Line, as it is a common feature found in terminals.
  * Difficulty: Intermediate. The core idea was simple, but there were many edge cases which I did not consider initially.

* Switching Between Tabs Using Keyboard Shortcuts
  * Description: Allows users to switch between the 4 tabs in CohortConnect using Cmd/Ctrl + 1/2/3/4.
  * Highlight: Our target user, a fast typist, will greatly benefit from keyboard shortcuts for navigation.
  * Difficulty: Intermediate. It was difficult to familiarize with JavaFX's EventHandlers, Accelerators and KeyCombinations.

* GitHub and Telegram Commands
  * Description: Allows users to open the current user's GitHub page or link to Telegram.
  * Highlight: Provides a quick method to network with the contact.
  * Difficulty: Easy.

Credits: [jackson-dataformat-csv](https://github.com/FasterXML/jackson-dataformats-text/tree/2.14/csv) for reading and writing to CSV files.

**Documentation**

* User Guide:
  * Added a labelled user interface diagram for easy familiarization with the UI.
  * Added usage details for Import, Export, Command History, Switching Between Tabs and GitHub and Telegram Commands.
* Developer Guide:
  * Updated requirements as they evolved.
  * Added implementation, diagrams and use cases for Import, Export, Command History, Switching Between Tabs and GitHub and Telegram Commands.

**Team-Based Tasks**

* Managed all releases from `v1.1` to `v1.4`.
* Ensured project deliverables were done correctly and on time.
* Studied [Material Design's Dark Theme Guide](https://material.io/design/color/dark-theme.html) and created the UI mockup on Figma to kick-start the GUI re-work.

**Review Contributions**

* Reviewed numerous PRs of my peers and provided non-trivial feedback regarding code quality. Examples: [#24](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/24), [#65](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/65), [#125](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/125), [#224](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/224), 

**Contributions Beyond Project Team**

* Asked questions and shared useful information to answer queries in the forum with great feedback, for example [this answer](https://github.com/nus-cs2103-AY2122S1/forum/issues/142) about fast-forwarding in Git and [this discussion question](https://github.com/nus-cs2103-AY2122S1/forum/issues/188).
