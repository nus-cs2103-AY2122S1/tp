---
layout: page
title: #Huang Ching's Project Portfolio Page
---

### Project: GameBook

GameBook is a desktop app designed for gamblers to track their gambling games. It is optimized for use via a Command
Line Interface (CLI), but still possesses the benefit of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=benjaminlhr&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=BenjaminLHR&tabRepo=AY2122S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zA=BenjaminLHR&zR=AY2122S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&zACS=220.43386537126995&zS=2021-09-17&zFS=&zU=2021-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Modifications to existing features**:
    * Parser:
      * Initially in charged of adapting AB3's Parser component to GameBook (v1.2). This includes adding new flags for parsing,
      checking for invalid arguments in user's inputs, and parsing them into commands. Most of the code written were later
      refactored. [Related PR]
      
    * Help:
      * Removed the help window from AB3 and created CommandNoteListPanel.  [Related PR]
        * CommandNoteListPanel takes in CommandNoteCard objects as inputs, which are generated from dummy command class objects, 
        and generates a command summary for the user. It is prompted when user enters `help` or when the user presses the help icon,
        and it takes the place of the game entry list when it is prompted.  
      * Adjusted help command and parser classes to allow user to ask for help for specific command. Ex. `help add`
      * Take away:
        * In depth understanding of how the command, parser, Ui classes work together.
        * Learned to think more about implementation details, such as whether to disallow certain commands when 
        CommandNoteListPanel is shown and the game list is not shown.

    * Clear:
      * Created a confirmation window and implemented feature to allow user to confirm before clearing data. 
        * A clear command will now prompt a confirmation window, and a data would only be executed when
        the user confirms to clear. 
      * Take away:
        * In depth understanding of how the logic component works with the UI.
        * Integration testing is important. Needed to update the graph and statistics after a clear command
        is executed. 
     
* **Enhancements to existing features**:
    * Wrote the initial code for adding `/p PROFIT` as an argument for add command. 
    * Difficulty encountered: 
      * Finding a way for profit to be inputted with the given structure of the Model class, 
and making sure that it works well with other cash values, such as INITIAL_CASH and FINIAL_CASH. 
    * Take away:
      *  


* **Testing**
  * Wrote test cases for Parser classes 
  * Create testing utils for logic component
  
* **Documentation**:
    * User Guide:
      - Added clear, help, and find features
      - Added notes for commands 
    * Developers' Guide:
      - Added parts about delete feature
      - Diagrams include
      
* **Project management**:


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#113](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/113), [\#114](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/114), [\#136](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/136)
    * Reported  bugs and suggestions for another team during PE-D.
