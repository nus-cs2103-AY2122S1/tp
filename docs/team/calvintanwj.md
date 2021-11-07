---
layout: page
title: Calvin Tan's Project Portfolio Page
---

### Project: Pocket Hotel

**Pocket Hotel (PH)** is a desktop app for hotel front-desk receptionists that provides a centralized location to manage their guests and vendors, and helps to automate front-desk operations.


Given below are my contributions to the project.

* **New Feature**: Added the ability to filter through your checked in guests.
    * What it does: Allows the user to filter your guest list via any of their fields.
    * Justification: At any given time, there could potentially be hundreds of guests currently checked in. Being able
      to filter through allows the receptionist to search up needed guests a lot faster in comparison to scrolling
      through the entire guest list.
    * Highlights: There were several design decisions considered when implementing this feature. Depending on the field
      inputted by the user, the filter should act differently. For example, filtering guests by giving a room number
      value of 2, would filter all the guests that have a room number starting with 2, not containing 2. We felt that
      this would be more useful, as it reflects the real world where room numbers in hotels typically start with the
      floor level they are on. Hence, this decision would make it easier for the receptionist to view all checked in
      guests on any specified floor. You may read more about this in the [user guide](../UserGuide.md)

* **New Feature**: Added the ability to filter all cooperating vendors.
    * What it does: Allows the user to filter your vendor list via any of their fields.
    * Justification: Similar to filtering of guests, where the receptionist can quickly find vendors that suit to their
      guest's requirements.
    * Highlights: Same as filtering for guests, where each field was tailored to be filtered in a way that would benefit
      the receptionist the most. You may read more about this in the [user guide](../UserGuide.md)

* **New Feature**: Added the ability to toggle between the guest and vendor list via the GUI.
    * What it does: Pressing the Guests and Vendors tab will help you to toggle between the lists.
    * Justification: Although a CLI app, you may execute this toggling with the list commands. However, we felt it may
      be intuitive for the user to allow them to also toggle between the two via the GUI.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=calvintanwj)

* **Project management**:
    * Assisted in managing all `v1.1` - `v1.4` milestones and releases on GitHub
    * Enabled assertions for Gradle
    * Helped in setting up project issues, assigned them relevant issue tags and delegated them.

* **Enhancements to existing features**:
    * Adapted model code from Address Book 3 to suit the context of Pocket Hotel (Pull
      request [\#146](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/146))
    * Adapted GUI from Address Book 3 to suit the context of Pocket Hotel (Pull
      request [\#146](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/146))
    * Adapted tests from Address Book 3 to pass for the newly adapted Pocket Hotel code (Pull
      requests [\#159](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/159)
      , [\#161](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/161)
      , [\#165](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/165))
    * Wrote additional tests for existing features (Pull
      request [\#261](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/261))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `filterguest`, `filtervendor`, `listguest` and `listvendor` (Pull
          request [\#184](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/184)
        * Added cosmetic tweaks to the UG (Pull request [\#272](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/272))
    * Developer Guide:
        * Updated design section to suit the context of Pocket Hotel(Pull
          request [\#278](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/278))
        * Added implementation details of the `filterguest`, `filtervendor` feature (Pull
          request [\#288](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/288))
        * Added implementation details for the various books that make up the `Model`, toggling between lists, and wrote
          use cases(Pull request [\#284](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/284))

* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull
      requests [\#163](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/163)
      , [\#115](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/115)
      , [\#38](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/38))
    * Reported bugs and suggestions for other teams in the class (
      examples: [\#180](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/180)
      , [\#181](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/181)
      , [\#186](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/186))
