---
layout: page
title: Nicolas Chang's Project Portfolio Page
---

### Project: Pocket Hotel

**Pocket Hotel (PH)** is a desktop app for hotel front-desk receptionists that provides a centralized location to manage their guests and vendors, and helps to automate front-desk operations.

Given below are my contributions to the project.

* **New Feature**: Extended ability to delete guest details from the archive and contact book.
  * What it does: Allows the user to delete a guests details from the contact book using their passport number.
  * Justification: If a guest details was incorrectly keyed in or has to be cleared from the system, the guest details can be deleted using the passport number.
  * Credits: The original [AB3 code](https://github.com/nus-cs2103-AY1920S1/addressbook-level3) for the initial method of deleting via index.


* **New Feature**: Added the ability to delete vendor details from the contact book using their vendor ID.
  * What it does: Allows the user to delete a guests details from the contact book using their vendor ID.
  * Justification: If a vendor is not used by the hotel, the vendor details can be deleted using the vendor ID.
  * Credits: The original [AB3 code](https://github.com/nus-cs2103-AY1920S1/addressbook-level3) for the initial method of deleting via index.


* **New Feature**: Adapted `delete` tests from Address Book 3 to work for `deletevendor` and `deleteguest` commands.


* **New Feature**: Add the ability to generate invoice containing services charged to a guest.
  * What it does: Generate PDF invoice containing all the vendor services used by a guest.
  * Justification: Invoice can be provided to the guest upon check out, to consolidate items they have to pay for.
  * Highlights: Learning and designing an invoice PDF using an external library([iText7 Core](https://itextpdf.com/en/products/itext-7/itext-7-core))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=Nicolascwy)


* **Project management**:
  * Set up and managed the milestone `v1.3` on GitHub
  * Managed the releases `v1.3` on GitHub
  * Create Github Issues for team to keep track of their tasking (Issues [#131](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/131), [\#132](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/132), [\#133](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/133), [\#137](https://github.com/AY2122S1-CS2103T-W12-3/tp/issues/137))
  * Setup Team organisation and issue tracker


* **Documentation**:
  * User Guide:
    * Wrote first draft of new features for UG - Tags, archiving, invoice generation(Pull request [\#189](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/189))
    * Added documentation for the features `deleteguest`, `deletevendor` (Pull request [\#153](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/153))
    * Create guest and vendor command summary table and parameter constraints table (Pull request [#148](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/148/files))

  * Developer Guide:
    * Added implementation details for the generate invoice feature (Pull request [\#292](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/292))
    * Added implentation details of the `deleteguest` and `deletevendor` feature (Pull request [\#285](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/285))


* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests: [#118](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/118), [#173](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/173), [#182](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/182), [#291](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/291))
  * Reported bugs and suggestions for other teams in the class (examples: [#139](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/139), [#146](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/146), [#156](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/153))


* **Tools**:
  * Integrated iText7 Core to the project ([\#167](https://github.com/AY2122S1-CS2103T-W12-3/tp/pull/167))
