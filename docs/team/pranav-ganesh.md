---
layout: page
title: Pranav's Project Portfolio Page
---

### Project: CallMeMaybe

CallMeMaybe (CMM) is a desktop application developed for freelance telemarketers that aims to enhance the contact tracking process.
While it has a GUI created with JavaFX, most of the user interactions happen via a CLI (Command Line Interface). 
It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implementing the Called command
  * What it does: Allows users to mark the specified contact from the address book as called (i.e. person has already been called)
  * Justification: Telemarketers make numerous calls every single day and must  keep track of who all they called so that they don't
    call the same person more than once and know who they are yet to call. This functionality is an essential part in ensuring
    that telemarketers are easily able to keep track of their call progress during work every day.
  * Highlights: The enhancement did affect the filter command since the filter feature makes use of the 'Called' field to sort contacts.
    Although there were initial difficulties posed since I was new to the codebase, the implementation of this feature was not overly 
   challenging since it didn't require many changes to existing commands and didn't involve many complications.
  

* **New Feature**: Implementing the edit feature for interests list
    * What it does: Allows telemarketers to edit an interests list for every contact in CMM
    * Justification: On an average, telemarketers make several hundreds of calls every single day and most of the customers don't show much 
      interest in their service or product, making their job unproductive and inefficient at times. In an attempt to increase their
      productivity, we decided to include an optional interests list that is associated with every contact in the contact list. The telemarketers 
      can specifically target contacts whose interests are aligned with the service or product they offer. This will accentuate the probability of successful
      calls. The edit feature significantly improves the product by allowing telemarketers to edit this interests list for every 
      contact by adding, deleting or editing a specific interest whenever they want to.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design 
      alternatives. There were several complications caused by the inclusion of a separate interests list index on top of the contact index.
      Such an index allowed telemarketers to edit the interests list by adding, deleting and editing interest values. A complication arises from the fact that a user could make so many kinds of
      errors when typing such a long command. For example, they could enter duplicate indexes, duplicate interest arguments or edit a person
      to someone who already exists in the list. The design of the feature implementation plays a crucial role in helping handle different kinds of exceptions.
      Overall, the implementation of this feature was challenging due to the several intricacies involved.
    

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=pranav-ganesh&tabRepo=AY2122S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub
    * Managed Github Milestones and Labels


* **Enhancements to existing features**:
    * Enhanced the original Edit Command to allow users to edit more fields (age, gender, isCalled, interests list)  (Pull requests [#108](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/108) [\#194](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/194))
    * Wrote additional tests for the Edit Command and Edit Command Parser classes to improve testing (Pull requests [#108](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/108/files) [#187](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/187) [#194](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/194))
    * Added display feature to the Edit Command so that users can view the updated contact details in the full person card as soon as they edit a contact [#132](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/132)


* **Team-based tasks**:
    * Did cosmetic tweaks to the existing code to maintain consistency and improve code quality (for eg modified the name of the DoneCommand class to CalledCommand throughout the codebase) [#183](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/183/commits/fb1e26a641ac5084bfb0004dc5320873142a1e4f)
    * Updated user guide that is not specific to a feature (added a new section called `Interface layout`) [\#211](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/211)


* **Documentation**:
    * User Guide:
        * Added documentation for the feature `edit`
          [\#113](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/113) [\#194](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/194/commits/02ec1b06bb866325fcc172cdb9991605e09f72a3)
        * Added a section `Interface layout` in the user guide to define the layout of CMM's user interface before delving into the actual features
          [\#211](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/211)
        * Did cosmetic tweaks to existing documentation [\#211](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/211/commits/0233215ed1884cb89751880b47a1cb818ceaf67d)
    * Developer Guide:
        * Added implementation details of the `edit` feature [#126](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/126/files) [#194](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/194/commits/17aa4482400a3d5bc15bec98402d392e1f10146b)
        * Edited use cases, test cases for the Edit Command and updated two diagrams (Edit Sequence Diagram and Edit Activity Diagram)  [#194](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/194/commits/17aa4482400a3d5bc15bec98402d392e1f10146b)
    

* **Community**
  * PRs reviewed (with non-trivial review comments): [#177](https://github.com/AY2122S1-CS2103T-T13-4/tp/pull/177)
  * Reported an above-average number of bugs and suggestions for other teams in the class: [Bugs found in PE-D](https://github.com/pranav-ganesh/ped/issues) 
