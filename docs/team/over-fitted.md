--- 
layout: page
title: Andrew Wirawan Sutjipto's Project Portfolio Page
---

### Project: Track2Gather

Track2Gather is a desktop application that will manage up to a few thousand contacts, providing basic features for
contact tracing personnel to organise and search through them via personal information, case contacts and quarantine
period. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has
about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the case number field to persons. [\60](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/60)
    * What it does: Enforces constraint that users have to add an index-unique case number to each person
    * Justification: Provides an identifier guaranteed to be unique to each person, primarily used to communicate about
      specific cases without unnecessarily using personal information.
    * Highlight: Through implementing tests for case number, the testing methodology implemented in AB3 for person 
      attributes was charted in sanityCheck.md for future reference [\80](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/80).

* **New Feature**: Added the ability to initialise enforcement sessions (session) and switch to pre-existing enforcement 
  session view (schedule) [\114](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/114)
    * What it does: Shows the user who has not been called for the current enforcement session.
    * Justification: This provides a convenient view of only who needs to be called.
    * Highlights: Procedure for writing list filter-updating feature was defined in the course of writing this function.

* **New Feature**: Added the ability to store whether a person has been called for the day. [\114](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/114)
    * What it does: Allows the user to record whether a call has been attempted on a person for the current enforcement
      session
    * Justification: This allows the user to identify who has yet to be called easily.
    * Credits: Ability to graphically view call status outside of enforcement mode from the UI in the form of an icon was
      implemented by [Frederick](https://github.com/frederickpek)

* **New Feature**: Added the ability to track the total number of failed calls on a person. [\114](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/114)
    * What it does: Allows the user to track the total number of failed calls on a person across enforcement sessions.
    * Highlight: Failed call tracking can also be done across save states by copying JSON data over manually
    * Credits: Naming convention and design refinements by [Lauren](https://github.com/lrnshk) and [Wei En](https://github.com/wei2912)

* **New Feature**: Added the ability to indicate successful and failed calls to a person for the given session. [\114](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/114)
    * What it does: Allows the user to indicate that a person has been called, removing that person from the
      enforcement view, in addition to whether the person has been non-compliant to be recorded by the prior feature.
    * Justification: This allows the user to remove called persons from the schedule without unnecessary modification to
      underlying person, facilitating linear workflow of enforcement mode.
    * Highlight: This attribute implements modification of person attribute without the use of EditCommand in order to 
      restrict unnecessary write access.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=over-fitted&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=over-fitted&tabRepo=AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=over-fitted&zR=AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D&zACS=222.8&zS=2021-09-17&zFS=over-fitted&zU=2021-11-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
    * Audited tests prior to `v1.5`
    * Filed issues pertaining to suboptimal testing and informed relevant parties such as through Github with [\#244](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/244) and [#243](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/243),
      in addition to informal means such as by messaging app to reduce issue maintenance complexity.
    * Audited enforcement mode.
    * Provided advice on writing tests and how to navigate pre-existing tests.

* **Enhancements to existing features**:
    * Added tests to miscellaneous items over the course of auditing tests such as [\246](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/246) and [\240](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/240)

* **Documentation**:
    * Wrote initial draft for enforcement DG and UG documentation before it was superseded by the current iteration of the docs [\133](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/133)

* **Community**:
    * PRs reviewed (with non-trivial comments): [\59](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/59), [\84](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/84), [\225](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/225), [\248](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/248)
    * Issues added for other classes: [\191](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/191), [\178](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/178), [\187](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/187), [\188](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/188), [\193](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/193), [\194](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/194), [\195](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/195), [\196](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/196), [\197](https://github.com/AY2122S1-CS2103T-W13-4/tp/issues/197)

* **Tools**:
    * Interpreted CodeCov report and analysed areas of improvement for testing.
