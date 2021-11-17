---
layout: page
title: Stanley Tan's Project Portfolio Page
---
### Project: SociaLite

**SociaLite** is a desktop app for **connecting avid social media users with their contacts’ social media pages**, optimized for use via a command line interface (CLI).
At the same time, it has a graphical user interface (GUI) created with JavaFX. 
It is written in Java, and has about 10 kLoC.


A summary of my contributions to the project is detailed below.



* **Altered Feature**: Redefined criteria for duplicate persons to be detected. [\#68](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/68)
  * What it does:
    * SociaLite detects duplicate entries by comparing contacts' phone number instead of name.
    * Users can now add contacts who might have the same names.
  * Justification:
    * There can be instances where users meet acquaintances who coincidentally share the same name but are separate persons altogether.
    * Hence, the phone number (which is a compulsory field in SociaLite) is now used as a determinant for duplicate persons as no two persons have the same phone number.
  * Highlights:
    * Addresses the inconvenience that users might have in adding contacts with similar names.
    

* **Revamped Feature**: Enhanced ability for users to obtain help or in-app guidance. [\#50](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/50), [\#127](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/127)
  * What it does: 
    * Allows users to access in-app guidance by typing in `help <COMMAND>` where `<COMMAND>` refers to a command of the user's choice.
  * Justification:
    * This command allows users to quickly refer to in-app guidance and perform their desired function correctly, without having to open the User Guide.
  * Highlights:
    * Serves as an umbrella command which abstracts away the appearance of Help Messages for all other valid commands.
    

* **New Feature**: Added support for Facebook and Instagram social media handles. [\#36](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/36), [\#37](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/37)
  * What it does: 
    * Allows users to add Facebook & Instagram handles that conform with prescribed regex, for a contact.
  * Justification:
    * Users can conveniently add and access a contact's Facebook and Instagram pages as SociaLite supports these two commonly used platforms.
  * Highlights:
    * Adhered to teammate's (Xiaozhi's) pre-designed framework for storing social media handles when enhancing support for Facebook and Instagram handles.
    * Refined regex of Facebook, Instagram and Telegram handles to meet stricter requirements imposed by social media platforms. [\#145](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/145)
    
<br>

* **Code contributed**: [RepoSense link](https://tinyurl.com/f11-4-stanley)

* **Project management**:
  * Designed interim UI mock-up for `v1.2`. [\#30](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/30)
  * Managed release `v1.2` on GitHub.
  * Acted as minute-taker to take note and summarize points raised in our team meetings held twice a week.
  * Reviewed and suggested non-trivial improvements to documentation submissions by all members.
  * Refactored components of the codebase for clarity. [\#154](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/154)
  
<br>

* **Documentation**:
  * User Guide:
    * Supported team members in the initial revamp of User Guide to match SociaLite
      * Drafted documentation for the features on our initial Google Doc.
    * Major changes for `v1.3`: [\#70](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/70), [\#73](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/73), [\#79](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/79)
      * Added documentation for the feature `help COMMAND`
      * Updated all sections and features to match our new input format for SociaLite.
      * Added around 10 missing commands and examples to Command Summary.
    * Performed major overhaul of UG for enhanced readability following PE-D review: [\#138](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/138)
      * Added Navigation Menu and consolidated Table of Commands (both with hyperlinks for users to access their desired section/feature efficiently).
      * Revamped layout and organization of all features - related commands are now found in the same section of the UG.
      * Re-designed how format and examples are presented to readers for all features through standardized notations.
      * Included screenshots for relevant examples. [\#160](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/160)
      * Updated FAQ section.
  * Developer Guide:
    * Aided in conceptualization of Product Scope, including the definition of our target user profile and value proposition.
    * Added and organized majority of User Stories and Use Cases. [\#11](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/11)
    * Added implementation details on Help Command and accompanying UML Sequence Diagram. [\#139](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/139), [\#155](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/155)
    * Updated details on Architecture of SociaLite. [\#155](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/155)
    
<br>

* **Community**:
  * Reviewed 50 PRs in total. Selection of PRs with non-trivial review comments: [\#52](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/52), [\#57](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/57), [\#77](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/77), [\#129](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/129), [\#158](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/158), [\#162](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/162), [\#171](https://github.com/AY2122S1-CS2103T-F11-4/tp/pull/171)
  * Reported 14 bugs and suggestions for peers in PE-Dry Run
    * [Unable to change password](https://github.com/AY2122S1-CS2103T-W13-2/tp/issues/186)
    * [HelpWindow does not show User Guide on subsequent attempts](https://github.com/AY2122S1-CS2103T-W13-2/tp/issues/138)
    * [Lack of guidance on adding multiple tags](https://github.com/AY2122S1-CS2103T-W13-2/tp/issues/159)
    
