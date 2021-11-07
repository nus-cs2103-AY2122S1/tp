---
layout: page
title: Yat Bun's Porfolio Page
---

## Project: SIASA

SIASA (Student Insurance Agent Sales Assistant) is a desktop app for students who are also part-time insurance agents to manage their clients and their policies. THe user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Created the built-in user guide.
    * What it does: replaces the previous help window with a brand new UI that shows all the basic commands in the current version of SIASA.
    * Justification: This feature improves the product significantly because users can now easily refer to the guide to recall all the basic commands available without the need of internet access to view our user guide.
    * Highlights: This enhancement required a lot of in-depth analysis of design alternatives. This is because there are multiple potential ways to implement the similar pages in the guide. The implementation was challenging as it involves heavy consideration of how to implement this feature in a way such that it minimises any duplicate code.

* **New Feature**: Added a startup summary feature that shows the policies that are expiring soon upon the launch of the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=meerian&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **Project management**:
    * Managed releases `v1.3`, `v1.4-beta` (3 releases) on GitHub
    * Updated index page for our project website
    * Standardised any mentions of `client` and `person` to be `contact` instead in all documents
    * Help managed deliverables for the group, including updating the issue tracker and milestones.

* **Enhancements to existing features**:
    * Refactored the existing commands for contacts to use the term `contact` instead of `client` or `person`
    * Partially refactored the existing `storage` model to store policies in a json format
    * Updated sample address book to contain some sample policies as well
    * Updated test cases for `storage` to test the saving of policies as well

* **Documentation**:
    * User Guide:
        * Added documentation for the features `expiringpolicy`, `sortcontact` and `sortpolicy` [\#72]()
        * Updated the Quick Start section
        * Updated all the old command formats to contain the new refactored command formats
        * Organised the commands under `Features` and `Command Summary` for better readability
    * Developer Guide:
        * Added `Developer Guide Information` section, containing the purpose, target audience and the acknowledgments
        * Updated the `storage` component for the high-level design section
        * Tidied up the `Appendix` section, including the product scope, user stories and non-functional requirements
        * Updated the `Use Cases` section with 8 of the most common use cases
        * Updated the `Manual Testing` section with test cases for adding, editing and deleting contacts and policies, as well as test cases that deals with missing or corrupted save files
    
* **Community**:
    * PRs reviewed some PRs (with non-trivial review comments)
    * Identified bugs for Team CS2103T-F13-4
