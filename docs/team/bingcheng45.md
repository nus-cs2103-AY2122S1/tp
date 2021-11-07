
---
layout: page
title: Toh Bing Cheng's Project Portfolio Page
---


### Project: Pocket Hotel

**Pocket Hotel (PH)** is a desktop app for hotel front-desk receptionists that provides a centralized location to manage their guests and vendors, and helps to automate front-desk operations.

Given below are my contributions to the project.


* **New Feature**: chargeguest, add ability to charge guest with a service. [#168](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/168) [#183](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/183)
  * What it does: Allows the user to charge a guest with a service provided by a vendor. Able to store
multiple chargeable as well.
  * Justification: Since we have a feature to export the total bill in pdf as an invoice to the guest,
this command compliments it and is required for the workflow to proceed smoothly.


* **Modified Feature**: clearguest,  would clear all guest contacts [#296](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/296)
  * What it does: Removes all guest from the `guest.json` file.
  * Justification: Should have a way to separately remove all guest without checking out.


* **Modified Feature**: clearvendor, would clear all vendor contacts [#296](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/296)
  * What it does: Removes all vendors from the `vendor.json` file.
  * Justification: Should have a way to separately remove all vendors.


* **Enhance Implementation**: Modified AB3 Json files to better suit our needs for Pocket Hotel [#83](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/83) [#86](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/86) [#88](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/88) [#98](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/98)
  * What it does: Now we have 3 Json files, `guest.json`, `vendors.json` & `archive.json` with their respective
format and validations. 
  * Justification: This is to ensure easier management and clear distinction between the json files.
  * Credits: [Jackson Library](https://github.com/FasterXML/jackson)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=bingcheng45)


* **Documentation**:
  * User Guide: 
    * Added documentation for the feature `Saving Data`, `clearguest`, `clearvendor` & `chargeguest` [#149](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/149)
    * Fix PED bugs raised. [Bugs Fix](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Abingcheng45)
  * Developer Guide:
    * Added implementation details of chargeguest, clearguest, clearvendor. [#296](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/296)


* **Community**:
  * In charge of writing minutes for the meeting. [Minutes](https://docs.google.com/document/d/1WAajCPcd0C2JO8ZLb1cu-Q9Xk5BqsKfLgsFQjY3Fnf8/edit?usp=sharing)
  * Reported bugs and suggestions for other team. [PED](https://github.com/bingcheng45/ped/issues)
