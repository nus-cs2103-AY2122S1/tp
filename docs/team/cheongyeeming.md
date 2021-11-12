---
layout: page
title: Cheong Yee Ming's Project Portfolio Page
---

### Project: CohortConnect

CohortConnect - CohortConnect is an advanced desktop address book which
facilitates networking among Computer Science (CS) students.
It is optimized for use via a Command Line Interface (CLI) while still
having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

### New Features

* **Telegram and Github Class**:
    * Pull requests 
    [\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54) and
    [\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88)
    * What it does: Allows user to store the Telegram handle and Github username of people.
    * Justification: Provides convenience when looking to connect with any of the
     existing contacts without having to source for ways to communicate and understand.
    * Highlights: The implementation was rather challenging as it required simultaneous
    changes to occur in the Model and Logic component.
    
* **Optional Fields**:
    * Pull requests [\#56](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/56)
    * What it does: Allows user to store the Github usernames of people in their contacts.
    * Justification: This feature allows for greater convenience since people might not be
    comfortable giving away their phone number, email or address. This enhancement also
    makes adding new contacts so much easier.
    * Highlights: This enhancement was simple to implement by tweaking existing code.
    
* **Favorite Command**:
    * Pull requests [\#127](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/127)
    * What it does: Allows user to favorite an existing contact.
    * Justification: Allow users to sort out contacts that are important to them,
     such as their family and friends.
    * Highlights: The implementation was rather challenging as it required simultaneous
    changes to occur in the Model and Logic component.
    
* **Unfavorite Command**:
    * Pull requests [\#131](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/131)
    * What it does: Allows user to unfavorite an existing contact.
    * Justification: Allow users to sort out contacts that are
     no longer important to them.
    * Highlights: The implementation was rather challenging as it required simultaneous
    changes to occur in the Model and Logic component.
    
### Test Cases Written
Wrote test cases for the following classes
* LogicManagerTest (Pull requests
[\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54),
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88)),
CommandTestUtil (Pull requests
[\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54),
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88),
[\#232](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/232))
* PersonTest (Pull requests
[\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54),
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88),
[\#125](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/125),
[\#135](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/135),
[\#262](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/262),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* TelegramTest (Pull requests
[\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54)),
GithubTest (Pull requests
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88))
* JsonAdaptedPersonTest (Pull requests
[\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54),
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88),
[\#125](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/125))
* AddCommandTest (Pull requests
[\#127](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/127),
[\#131](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/131),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* AddCommandParserTest (Pull requests
[\#54](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/54),
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88),
[\#228](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/228),
[\#232](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/232))
* EditCommandParserTest (Pull requests
[\#88](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/88),
[\#232](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/232),
[\#125](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/296))
* FavoriteCommandTest,
UnfavoriteCommandTest (Pull requests
[\#135](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/135),
[\#259](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/259),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* FavoriteCommandParserTest,
UnfavoriteCommandParserTest (Pull requests
[\#135](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/135),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* IsFavouritePredicateTest (Pull requests
[\#135](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/135),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* AddressBookParserTest (Pull requests
[\#232](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/232),
[\#259](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/259),
[\#262](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/262),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* AppParametersTest,
ExportCommandParserTest,
ImportCommandParserTest,
ShowCommandParserTest (Pull requests
[\#232](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/232))
* ParserUtilTest (Pull requests
[\#232](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/232),
[\#262](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/262))
* CommandResultTest (Pull requests
[\#259](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/259)),
UserPrefsTest (Pull requests
[\#262](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/262))
* UniquePersonListTest (Pull requests
[\#262](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/262),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))
* ConfigTest,
GuiSettingsTest,
LogsCenterTest,
MessagesTest (Pull requests
[\#273](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/273))
* StorageManagerTest (Pull requests
[\#273](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/273),
[\#279](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/279))

### Documentation

* **User Guide:**   
    * Added the documentation for the features `add`, `edit`, `fav` and `unfav`.
    (Pull request
    [\#157](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/157),
    [\#217](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/217),
    [\#252](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/252))
  
* **Developer Guide:**
    * Added implementation details of `Telegram`, `Optional Fields`,
    `Github`, `Favorite command` and `Unfavorite command`.
    (Pull request
    [\#113](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/113),
    [\#218](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/218),
    [\#321](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/321))

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=CheongYeeMing&tabRepo=AY2122S1-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false&zA=CheongYeeMing&zR=AY2122S1-CS2103T-T10-1%2Ftp%5Bmaster%5D&zACS=214.17025664527955&zS=2021-09-17&zFS=&zU=2021-11-08&zMG=false&zFTF=commit&zFGS=groupByRepos)
