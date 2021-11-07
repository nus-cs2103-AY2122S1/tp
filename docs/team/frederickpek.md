--- 
layout: page
title: Frederick Pek's Project Portfolio Page
---

### Project: Track2Gather

Track2Gather is a desktop application that will manage up to a few thousand contacts, providing basic features for contact tracing personnel to organise and search through them via personal information, case numbers, and Stay-Home-Notice periods. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

A full list of my code contribution to this project can be found here: [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=frederickpek&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=frederickpek&tabRepo=AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

Given below are my contributions to the project.

* **New Features**
  * Added functionality to batch-update the stay-home-notice (SHN) period end dates of contacts (`tshift` command). [#89](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/89)
    * What it does: Allows our contact tracing personnels to either extend or bring forward the SHN period end dates of everyone in the Track2Gather system. User does not need to manually check which persons have an SHN period assigned to them as the functionality is performed automatically and internally with a single command.
    * Justification: This feature improves the product significantly because the user does not need to manually edit the SHN periods of everyone in the Track2Gather system. The application should provide a functionality to apply this change to all contacts easily.


* **Enhancements Implemented**
  * Enhancements to the GUI of Track2Gather for `v1.2` - `v1.4` milestones. [#68](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/68), [#132](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/132), [#206](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/206), [#259](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/259)
    * Notable changes made: Improved `PersonCard` in the ui to dynamically show/hide optional person fields when they are present/empty.
    * Sample image: <div align="center"><img src="https://user-images.githubusercontent.com/66522537/140643414-7745355d-feae-444e-ad54-9d6b5576fa87.png" width="350"/><br><em>Alexander has no optional fields, headers hidden. Bernice has all optional fields.</em></div>
    * Justification: Track2Gather is able to save numerous contact details, many of them are optional. By dynamically hiding and displaying these fields only when necessary, we can declutter the ui during usage.
    * Credits: The initial ui mockup image was created by [Lutfi](https://github.com/luffingluffy) with Figma.
  * Improved edit command to allow for editing newly implemented fields added in `v1.3` milestone. [#85](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/85)
    * Justification: A quality of life improvement for our contact tracing personnels for when they want to edit a field without having to re-enter a person's details.
    * Credits: Code was based on the original AB3 edit command, but significantly modified after to cater for optional used by Track2Gather.


* **Contributions to Team-Based Tasks**
  * Feature demo for milestones `v1.2` and `v1.3`. [v1.2 feature demo](https://docs.google.com/document/d/16MdGsRMtNYmiUIOaVCsEShnIcHdtWr4oaSgCdBN66Pc), [v1.3 feature demo](https://docs.google.com/document/d/1YtZlDEB2hm7GwVxv8-0BUAfs02pK5jJkFZvG8yA2DQ8)
    * Includes usage of all commands, a before and after image of usage and a brief description of the scenarios used.


* **Documentation**:
  * Project Website:
    * Updated the Landing Page for for `v1.4` milestone. [#237](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/237)
  * User Guide:
    * Added documentation for the features `edit` and `tshift`. [#104](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/104), [#105](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/105)
    * Ensured overall cohesion for `v1.2` and `v1.4` milestones. [#82](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/82), [#219](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/219)
  * Developer Guide:
    * Added documentation for the features `edit` and `tshift`. [#104](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/104), [#105](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/105)
    * Ensured overall cohesion for `v1.1` milestone. [\#15](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/15)


* **Community**:
  * Audited and gave improvement suggestions to `Person` model and its attributes. [#204](https://github.com/AY2122S1-CS2103-W14-2/tp/issues/204)
  * Major PRs reviewed (with non-trivial review comments): [#61](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/61), [#112](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/112), [#223](https://github.com/AY2122S1-CS2103-W14-2/tp/pull/223)
  * Here are some of my contributions to forum: [#13](https://github.com/nus-cs2103-AY2122S1/forum/issues/13), [#25](https://github.com/nus-cs2103-AY2122S1/forum/issues/25), [#131](https://github.com/nus-cs2103-AY2122S1/forum/issues/131)
