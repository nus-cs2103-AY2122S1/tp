---
layout: page
title: Huang Ching's Project Portfolio Page
---

### Project: GameBook

GameBook is a desktop app designed for gamblers to track their gambling games. It is optimized for use via a Command
Line Interface (CLI), but still possesses the benefit of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ching&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~other~test-code&since=2021-09-17&tabOpen=true&tabAuthor=chingh20&tabRepo=AY2122S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false&until=2021-11-04&tabType=authorship)

* **Modifications to existing features**:
    * Parser:
      * Initially in charged of adapting AB3's Parser component to GameBook. This includes adding new flags for parsing,
      checking for invalid arguments in user's inputs, and parsing them into commands. Most of the code written were later
      refactored by my teammates. 
      
    * Help:
      * Removed the help window from AB3 and created CommandNoteListPanel.
        * CommandNoteListPanel takes in CommandNoteCard objects as inputs, which are generated from dummy command class objects, 
        and generates a command summary for the user. It is prompted when user enters `help` or when the user presses the help icon,
        and it takes the place of the game entry list when it is prompted.
      * Adjusted help command and parser classes to allow user to ask for help for specific command. Ex. `help add`
      * Take away:
        * In depth understanding of how the command, parser, Ui classes work together.
        * Learned to think more about implementation details and how one component might affect another, 
        such as whether to disallow certain commands when CommandNoteListPanel is shown and the game list is not shown.

    * Clear:
      * Created a confirmation window that allow user to confirm if he/she wants to clear data before actually clearing data. 
        * Modified the clear command so that it could prompt the confirmation window or clear data when executed.
      * Take away:
        * In depth understanding of how the logic component works with the UI.
      * Improvement in the future:
        * To improve code quality and preventing user from intentionally breaking the clear command, I should probably create two separate
        classes in the future instead of using one clear command class and allowing two execution behaviors.
    * Major PRs: [Parser](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/36) [Help](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/99)
    [Clear](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/105) 


* **Enhancements to existing features**:
    * Wrote the initial code for adding `/p PROFIT` as an argument for add command. 
    * Difficulty encountered: 
      * Thinking of ways for profit to be parsed and stored in a game entry given the structure of a game entry object, 
      and making sure that it works well with other cash values, such as INITIAL_CASH and FINIAL_CASH. 
    * Major PR: [Profit](https://github.com/AY2122S1-CS2103T-W13-3/tp/pull/106)
 
 
* **Testing**
  * Wrote test cases for Parser classes 
  * Created testing utils for logic component
  

* **Documentation**:
    * User Guide:
      - Added clear, help, and find features
      - Added notes for commands
      - Miscellaneous formatting and edits
      
    * Developers' Guide:
      - Added implementation details, manual testing, and use cases for delete feature
      - Created delete sequence and activity diagrams
      - Wrote use cases for help
   
   
* **Project management**:
  * Allocated issues from PE-D
  

* **Community**:
    * Participated in reviewing PRs.
    * Reported 13 bugs and suggestions for another team during PE-D.
