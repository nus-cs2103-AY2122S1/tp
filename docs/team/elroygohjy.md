---
layout: page
title: Elroy's Project Portfolio Page
---

### Project: Notor

Notor aims to help mentors who wish to informally keep tabs on their mentees, by providing a quick way to take notes and
mantain records on their mentees. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is
written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * [Advanced Note Command](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/76)
    * What it does: Allows mentor to type notes for Persons and general Note in Notor via Note Window.
      Allows multiple instances of Note Window to be opened at once.
    * Justification: We wanted mentors to have more control over typing of notes, as it is an important aspect
      of mentoring.
    * Credits: No code reused.

  * [Warning Window and Confirmation Window](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/76)
      * What it does: Prompts Warning Window when mentor runs any clear commands. Prompts Confirmation Window when
        mentor attempts to close Note Window without saving. Prompts Warning window when mentor attempts to exit Notor
        with unsaved Note Windows opened.
      * Justification: We wanted to add a layer of precaution for mentors to prevent them from unintentionally executing
        commands.
      * Credits: No code reused.

  * [Clear Note Command](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/115)
      * What it does: Prompts Warning Window and clears note upon confirmation.
      * Justification: Mentor should be able to clear note if they choose to do so.
      * Credits: No code reused.

  * [Command History](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/145)
      * What it does: Allows mentors to easily access previous typed commands via Up and Down arrow keys.
      * Justification: We wanted mentors to be conveniently accessed previous commands so as they can use Notor at a
        fast pace.
      * Credits: No code reused but inspired by Window Terminal.

  * [Note Window Shortcut keys](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/77)
      * What it does: Allows mentors to save and quit Note Window using shortcut keys.
      * Justification: We wanted mentors fully immersive, hand-free experience.
      * Credits: No code reused.

  * [Gui Testing Not implemented](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/229)
    * What it does: Tests Gui aspects of Notor.
    * Justification: New features such as Note Window, Confirmation Window, Warning Window and
      General Note pane on Notor uses GUI, which is not possible to test via JUNIT 5. Not implemented due
      to CI failure.
    * Credits: No code reused but followed closely to [TestFx](https://github.com/TestFX/TestFX) to setup.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=elroygohjy&tabRepo=AY2122S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=elroygohjy&zR=AY2122S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&zACS=213.98121387283237&zS=2021-09-17&zFS=&zU=2021-11-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

**Review contributions:**: Reviewed [8 PRs](https://github.com/AY2122S1-CS2103T-W08-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
* **Enhancements to existing features**:
    * [Dummy data](https://github.com/AY2122S1-CS2103T-W08-1/tp/pull/241)
        * What it does: Add dummy data is relevant to our target user mentor.
        * Justification: Allows mentor to visualise and understand how Notor works.
        * Credits: No code reused.

* **Documentation**:
    * User Guide
        * Person commands
        * Table for Keyboard shortcut for Note
        * Command History
        * Quality Assurance fixes throughout User Guide
    * Developer Guide 
      * Sequence Diagram and description for Person Note Command
      * Use case for add Note and Exit
      * Manual Testing for Delete Person, Person Note and General Note.
      * CommandHistory
