---
layout: page
title: Sherwin Poh Kai Xun's Project Portfolio Page
---

### Project: SIASA (Student Insurance Agent Sales Assistant)

SIASA (Student Insurance Agent Sales Assistant) is a desktop app for students who are also part-time insurance agents to
manage their contacts (clients and potential clients) and their policies.
The user interacts with it mainly using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=sherrpass&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=sherrpass&tabRepo=AY2122S1-CS2103-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements implemented**
    * **Create Policy Model** : Designed and constructed Policy, and it's details, e.g Commission, Expiry Date.
      Created an association between Policy and Contact and built UniquePolicyList. [PR #19](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/19)
        * What it does: Enables users to create and edit the details of a Policy and associate a Policy to a Contact it belongs to.
        * Justification: This is the most fundamental model for SIASA which aims to manage contacts and their policies. Therefore,
          a model is required for it and to encapsulate its attributes.
        * Highlights: It is entirely immutable which makes it easier to design, implement and use the application as
          code complexity increases. Invalid inputs are accounted for. Uni-directional association between policies and contacts
          was decided to minimise multiple sources of data and to simplify implementing CRUD for policies.
    * **Enhance the payment and commission structure for policies**: Replaced Price and Commission with a more comprehensive PaymentStructure and Commission. [PR #68](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/68)
        * What it does: Allows the creation of policies with more complex payment structures that vary payment frequency, amount and number of total payments and commission structures.
        * Justification: This ensures that the user will not be limited in the variation of policies that he can manage in the system.
        * Challenges faced: Unlike other attributes of Policy and Contact, there is a dependency between PaymentStructure and Commission for purposes
          such as calculating total commission received or checking for invalid combinations which requires more careful design and OOP considerations.
    * **Sorting policy by total commission and total payments**: [PR #150](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/150)
        * What it does: Enables policies to be sorted by total commission and total payments instead of commission percentage and single payment amount.
        * Justification: Users are insurance agents that are profit-incentivised, hence being able to visualise the
          profitability (total commission) of their policies is important.
    * **Add tags to policies** [PR #60](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/60)
      * What it does: Enables users to have multiple tags on policies.
      * Justification: Users can now classify, and distinguish visually different policies.
* Contributions to the UG
    * Added the documentation for addpolicy, editpolicy commands. [PR #69](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/69)
    * Reorganised UG by grouping features according to their functionality. [PR #147](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/147)
* Contributions to the DG
    * Updated model component, class diagram and description. [PR #34](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/34)
    * Added Implementations and design considerations for addpolicy and editcontact commands. [PR #199](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/199)
* Contributions to team-based tasks
    * Set up Github team org/repo and team project notes.
    * Helped to rename address book to SIASA throughout codebase.
    * Refactored the model component and helped to refactor the storage component of AB3 for SIASA.
    * Helped to maintain the issue tracker actively.
* Contributions to community:
    * Helped to identify bugs for other project teams. [PR #242](https://github.com/AY2122S1-CS2103T-W13-3/tp/issues/242)
      [PR #243](https://github.com/AY2122S1-CS2103T-W13-3/tp/issues/243)
* Review/mentoring contributions
    * Provided reviews of code quality and OOP for PRs: [PR #186](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/186)
      [PR #58](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/58)
      [PR #22](https://github.com/AY2122S1-CS2103-F10-4/tp/pull/22)

