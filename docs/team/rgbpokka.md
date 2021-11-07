---
layout: page
title: Jeremy Yeo's Project Portfolio Page
---

### Project: Pocket Hotel

**Pocket Hotel (PH)** is a desktop app for hotel front-desk receptionists that provides a centralized location to manage their guests and vendors, and helps to automate front-desk operations.

Given below are my contributions to the project.

* New Feature: Ability to edit guests.

    * What it does: Edits details of guests.
    * Justification: Editing guest details is a core feature of our application, given that guest details may change 
      over time/ hotel staff may enter guest details incorrectly.
    * Highlights: Instead of locating the guest by the list index, as was done in AB3, editguest finds a guest using
      his/her passport number. Implementing this feature was non trivial as it involved changing many related classes
      like EditGuestDescriptor.
    * Credits: Adapted the existing Edit Command in AB3.

* New Feature: Ability to edit vendors.

    * What it does: Edits details of vendors.
    * Justification: Editing vendor details is a core feature of our application, given that vendor details change 
      over time/ hotel staff may enter vendor details incorrectly. 
    * Highlights: Similar to editguest, editvendor finds a vendor using his/her vendor id instead.
    * Credits: Adapted the existing Edit Command in AB3.

* New Feature: Ability to check out guests from the hotel.

    * What it does: Allows front office staff to check guests out from the hotel. This command deletes the guest from 
      the guest book and adds him/her into the archive. Moreover, an invoice will be generated if the guest engaged any 
      vendor services during his/her stay. 
    * Justification: This is a core feature of our application, given that checking out guests is a common routine of 
      front office staff. This feature works in tandem with returncheckin, which will check if the guest to check in 
      belongs to the archive, eliminating the need to re-enter all of the guest's details upon checking him/her in 
      again.
    * Highlights: This enhancement affects existing commands and commands to be added in future. In particular, as 
      this command deals with a newly implemented component of the Model, Archive, there were several complications 
      which had to be addressed, making bug-free implementation of this command non trivial. 
    * Credits: Adapted the Delete Command in AB3 in implementing CheckOut.

* Code contributed: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=rgbpokka)

* Enhancements to existing features:

    * Enhance the existing Model in AB3 by adding an Archive, which would store guests that have been checked out of
      the hotel. The code for this was largely adapted from AB3's GuestBook/Storage. 

* Documentation:

    * User Guide:
        * Added documentation for the features editguest, editvendor and checkout

    * Developer Guide:
        * Added implementation details of the editguest, editvendor and checkout feature
    
* Community:

    * PRs reviewed (with non-trivial review comments): 
      [#46](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/46),
      [#168](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/168)
      
    * Reported meaningful bugs and suggestions for other teams ([PED](https://github.com/rgbpokka/ped/issues/))
    
