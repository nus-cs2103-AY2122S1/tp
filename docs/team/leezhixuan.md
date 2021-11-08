---
layout: page
title: Lee Zhi Xuan's Project Portfolio Page
---

### Project: CONNECTIONS

CONNECTIONS is a desktop address book application adapted from AB3. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java and has about 10 KLOC.

Given below are my contributions to the project.

* **New Feature**: FindAny
  * What it does:
    * FindAny is a command that allows users to apply an 'OR' filter with the search terms they have provided.
  * Justification:
    * "OR" search retrieves records that include either terms searched for. Using OR broadens a search and thus returns more results.
      This way, users will be able to filter contacts with greater flexibility.
      <br></br>
* **New Attribute**: Birthday
  * What it does:
    * The birthday attribute records the date of birth of a contact.
  * Justification:
    * Our target audience is people who love celebrating birthdays. It is natural for them to want to keep a
      record of the birthdays of their contacts. This way, they will never miss a birthday.
      <br></br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=leezhixuan&tabRepo=AY2122S1-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Set up Github Pages
  * Created and assigned issues for milestone `v1.3`.
  <br></br>

* **Enhancements to existing features**:
  * Redefine equality amongst contacts.
    * Previously, equality between two contacts hinged on the fact that they had the same name. Since people
      can have friends with the same name but unique phone numbers, I changed CONNECTIONS to check for phone
      numbers instead to raise possible duplicate contacts.
  * Revamp Find
    * Find ('AND'-search) and FindAny ('OR'-search) can be applied names and/or tags.
    * Case Sensitivity to search (Find/FindAny).
      * Users can now opt for case-sensitive searches on Tags using the `c/` flag.
  * Wrote unit and integration tests for existing features.
    * Increase coverage from 72.15% to 72.59% (+0.43%) (Pull request [\#49](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/49))
    * Increase coverage from 75.01% to 72.14% (+0.14%) (Pull request [\#69](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/69))
    * Increase coverage from 73.69% to 74.31% (+0.62%) (Pull request [\#86](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/86))
    * Increase coverage from 74.31% to 74.52% (+0.20%) (Pull request [\#101](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/101))
    * Increase coverage from 70.83% to 71.15% (+0.32%) (Pull request [\#201](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/201))
      <br></br>

* **Documentation**:
  * User Guide:
    * Documented `Upcoming Birthdays`, `Find` as well as `findAny`. (Pull request [\#95](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/95), [\#173](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/173))
  * Developer Guide:
    * Documented `Use Cases`, `Non-functional requirements` and `Glossary`. (Pull request [\#19](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/19))
    * Documented `find` and `findAny` (Pull request [\#101](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/101))
  * Polished User & Developer Guide (Pull request [\#205](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/205))
  <br></br>

* **Community**:
  * Reported 37 bugs and suggestions for other teams in class
    * You can find the entire log [here](https://github.com/leezhixuan/ped)
  * Contributed to forum [discussion](https://github.com/nus-cs2103-AY2122S1/forum/issues/135):
  * PRs reviewed with non-trivial comments: (Some examples: [1](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/42),
    [2](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/94), [3](https://github.com/AY2122S1-CS2103-F09-4/tp/pull/119))
