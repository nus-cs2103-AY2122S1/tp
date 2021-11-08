---
layout: page
title: Donny's Project Portfolio Page
---

### Project: DonnaFin.io

DonnaFin.io is a desktop address book application used by financial advisors to keep track of their client information 
and related tasks. This is a Java program with about 10 kLoC with a JavaFX GUI.
The '.io' in our name is a reflection of our belief that you deserve a faster workflow for input and output.
If you can type fast, you can use our CLI-like commands to manage and view your notes, upcoming meetings and todo lists 
much faster than your typical customer relationship manager apps.

Given below are my contributions to the project.

* **New Feature**:
    * What it does:
    * Justification:
    * Highlights:
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used 
      in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*
  
1. Added foundation for View Command [#63](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/63/files)
   * **What it does:** <br>
   View command allows the user to examine the client's details with greater attention. It allows the user to access the
   commands that allows the user to make edits to the client's details and financial information.
   
   * **Justification:** <br>
   View command is essential for DonnaFin to function as most of the new commands implemented revolve around the client,
   and it is executed when the user is in the client window.
  
2. Added foundation for Notes field [#122](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/122/files)
   * **What it does:** <br>
   The notes field allows the user to store additional information regarding the client that does not fit in the
   provided fields. This can be accessed by viewing the client and going to the notes tab.

   * **Justification:** <br>
   The notes field is extremely useful to note down additional information such as likes and dislikes of the particular
   client. It is an extremely common feature that can be found in many "address book" applications.

   * **Highlights:** <br>
   Initially, the notes field did not have a save functionality yet. However, this save functionality was added later
   on by [Tee Chin](bluntsord.md)
   

3. Added foundation for Append Command [#155](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/155/files)
  * **What it does:** <br>
  The append command allows the user to add an asset/liability/policy to the client's portfolio. This command can be
  accessed by switching to their respective tab (appending an asset in the Assets tab etc.)

  * **Justification:** <br>
  As our application is targeted towards financial advisors, they need to be able to keep track their client's
  assets/liabilities/policies in order to gain more utility out of DonnaFin.

4. Added foundation for Remove Command [#155](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/155/files)
  * **What it does:** <br>
  The remove command allows the user to remove an asset/liability/policy from the client's portfolio. This command can
  be accessed by switching to their respective tab (removing a policy in the Policies tab etc.).

  * **Justification:** <br>
  If the client's asset/liability/policy ever expire or if the client wants to change their plan, then the financial
  advisor can just conveniently remove and add a new asset/liability/policy.
  

* **Code contributed**: [RepoSense link]()


* **Project management**:
  * Worked together with teammates to minimise merge conflicts.


* **Enhancements to existing features**:
    1. Bug Fixes:
       1. Prevent duplicate assets/liabilities/policies from being added.
    2. Tests:
       1. Append Command
       2. Remove Command
    3. Reworked Edit Command [#155](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/155/files) <br>
       Edit command now only works in the client window.
  

* **Documentation**:
    * User Guide:
      * Update commands [#164](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/164/files)
      * Added sections 3.1 and 3.3 [#337](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/337) 
      
    * Developer Guide:
      * Added various Use Cases [#48](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/48)
      * Added various NFRs [#48](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/48)
      * Added Activity Diagram [#330](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/330)
      * Added Manual Test Cases [#357](https://github.com/AY2122S1-CS2103T-W16-1/tp/pull/357)

* **Community**:
  * Provided comments and feedback on teammates' PRs. 

* **Tools**:
  * Used Draw-io to make Activity Diagram.
