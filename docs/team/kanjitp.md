---
layout: page
title: Kan Jitpakdi's Project Portfolio Page
---

### Project: Managera

Managera is a **desktop app that provides event organisers with a convenient method of keeping track of upcoming events and the details of their participants.**
It does not handle communication between the event organiser and the participants. This project is adapted from SE-EDU AddressBook - Level 3.

* **New Feature**: `Add Participants to Event`
  * Added Participant class extending from Person class
  * Added BirthDate, Note classes to be used as fields in Participant
  * Highlights: (To be added)
  * Credits: (To be added if any)

* **New Feature**: `Remove Participants from Event`
  * Created Classes the same as `Add Participant to Event`
  * Highlights: (To be added)
  * Credits: (To be added if any)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=AY2122S1-CS2103T-T10-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=kanjitp&tabRepo=AY2122S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * **Enhance the UI of the application:**
    
    **Improve space utilization:**
      * Result display occupy larger space to the left side of the application to allow more information of 
        CommandResult to be displayed to the users allowing the CommandResult to contain much more text
      * Additional layering and coloring of the windows so that different sections are highlighted clearer to the users.
      * EventListPanel and ParticipantListPanel occupy the right side of the application window to allow users to 
        explore and read the information from both events and participants list views
        
    **Add typing animation:**
      * Result display will be shown to the user with typing animation with consistent timing
        regardless of the CommandResult length
        
  * **Shift from Person Class to Participant Class:**
    
    AB3 uses the Person class as the class for dealing with abstraction of Person on the addressbook. For our 
    application, our team decided to shift from Person to Participant class in which I was responsible for 
    refactoring the application to fit the criteria.
    
    * **JSON Serialization/Deserialization** - Participant and Event classes come with multiple information to be 
      stored in the JSON file. I was one of the teammates for dealing with serialization and deserialization of 
      object/string
      

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addParticipant` and `removeParticipant` [\#72]()
    * Added FAQ for Managera inspiration and the origin of the name   

* **Community**:
  
* **Tools**:
  

