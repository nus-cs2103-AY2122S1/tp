---
layout: page
title: Kan Jitpakdi's Project Portfolio Page
---

### Project: Managera

Managera is a **desktop app that provides event organisers with a convenient method of keeping track of upcoming events and the details of their participants.**
It does not handle communication between the event organiser and the participants. This project is adapted from SE-EDU AddressBook - Level 3.

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

    **New UI Component**: `UniqueParticipantList`
      * Implementation of `UniquePersonList` in AB3 but for `Participant`
        
  * **Shift from Person Class to Participant Class:**
    
    AB3 uses the Person class as the class for dealing with abstraction of Person on the addressbook. For our 
    application, our team decided to shift from Person to Participant class in which I was responsible for 
    refactoring the application to fit the criteria on [Pull request #51](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/51).
    
    * **JSON Serialization/Deserialization** - Participant, Next Of Kin, and Event classes come with multiple information to be 
      stored in the JSON file. I was one of the teammates for dealing with serialization and deserialization of 
      object/string on [Pull request #51](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/51).

* **Enhancement to and Implementation of new features**:
  * **New model**: `ParticipantId`
    * Used for identifying each unique participant in the participant list
    * This is particularly useful when dealing with JSON file and encoding it
    * By the end of the project, we have decided to not have holistic usage of this as we decide to revert to using index instead. This is because at first 
    * As a result, many features are also scraped because of this.
  * **New Model**: `Participant`
    * As explained in _Shift from Person Class to Participant Class_
  * **New model**: `NextOfKin`
    * Similar to Participant but much simpler and require less number of fields
    * Next Of Kin is supposed to be used as a person of contact of each participant but has only few required fields including name, phone, and tag which symbolise the relationship fo next of kin to that participant
    * The question might be that why don't we use Person class that was already in AB3 and the reason is because we intend Next of Kin to be even simpler than the Person class present in AB3
  * **New Feature**: `Add Participants to Event`
    * Allow users to quickly add existing participants to Events
    * This feature was initially implemented using ParticipantId to identify different participants in the participant list but the design of the application change and plan evolves.
  * **New Feature**: `Remove Participants from Event`
    * Created Classes the same as `Add Participant to Event` but for removing
  * **New Feature**: `Add Next of Kin `
    * Allow user to create Next of Kin object to be tied to different participant
  * **New Feature**: `Remove Next of Kin`
    * Created Classes the same as `Add Next of Kin` but for removing

 

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addParticipant - enroll` and `removeParticipant - expel`
    * Added FAQ for Managera inspiration and the origin of the name    - parts of the FAQ initially added was scraped by teammates to make the FAQ overall more concise
  * Developer Guide:
    * Added documentation for features - `addParticipant - enroll` and `removeParticipant - expel`
      * Includes implementation details, implementation rationale, and 2 activity diagrams

* **Team based Task**:
  * Create product icon and product name
* **Community**:
  * PRs reviewed (with non-trivial review comments: [\#20](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/20), [\#91](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/91), [\#102](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/102), [\#145](https://github.com/AY2122S1-CS2103T-T10-2/tp/pull/145))

  

