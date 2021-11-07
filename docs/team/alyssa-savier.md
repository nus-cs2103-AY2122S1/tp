---
layout: page
title: Alyssa Savier's Project Portfolio Page
---

### Project: Pocket Hotel

**Pocket Hotel (PH)** is a desktop app for hotel front-desk receptionists that provides a centralized location to manage their guests and vendors, and helps to automate front-desk operations.

Given below are my contributions to the project.

* **New Feature**: Ability to check in new guests.
    * What it does: Checks in new guests.
    * Justification: Checking in new guest is a core feature of our application, given that guests need to be added to the guest list shown in the GUI with relevant fields to a hotel patron.
    * Highlights: Checking in a guest also deals with the archive to check if the passport number belonged to a guest that was new or returning. Implementing this feature was non-trivial as it involved changing many related classes like Archive.
    * Credits: Adapted the existing Add Command in AB3.


* **New Feature**: Ability to check in returning guests.
    * What it does: Checks in returning guests.
    * Justification: Checking in returning guests is a core feature of our application, given that guests in the archive need to be added to the guest list shown in the GUI while retrieving relevant fields to a hotel patron.
    * Highlights: Checking in a returning guest also deals with the archive to check if the passport number belonged to a guest that was new or returning. It also required editing fields from archived guests. Implementing this feature was non-trivial as it involved changing many related classes like Archive.
    * Credits: Adapted the existing Add Command in AB3.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=alyssa-savier)


* **Enhancements to existing features**:
    * Enhance the existing Model in AB3 by adding an Archive, which would store guests that have been checked out of
      the hotel. The code for this was largely adapted from AB3's GuestBook/Storage.


* **Documentation**:
    * User Guide:
        * Added documentation for the features checkin and returncheckin

    * Developer Guide:
        * Added implementation details of the checkin and returncheckin feature


* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [#46](https://github.com/nus-cs2103-AY2122S1/ip/pull/110),
      [#168](https://github.com/nus-cs2103-AY2122S1/ip/pulls/eltonyeh)
    * Reported meaningful bugs and suggestions for other teams ([PED](https://github.com/alyssa-savier/ped/issues/))
