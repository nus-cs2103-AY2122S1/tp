---
layout: page
title: Wen Jun's Project Portfolio Page
---

### Project: UNIon

UNIon is a desktop app for organizing various types of contacts, optimized for use for the vast majority of computing students. If you are already familiar with Unix commands, then UNIon will be easy for you to use.

Given below are my contributions to the project.

* **New Feature**: Convert old command format to Unix commands [\#44](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/44)
  * What it does: Allows the user to use a familiar set of Unix commands in UNIon. This includes changing the command words (e.g. from `list` to `ls -contacts`, as well as replacing the old prefixes `n/` with flags like `-n`.
  * Justification: This feature allows UNIon to be relatively easy to pick up and remember for computing students who already have some knowledge of Unix commands.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. Since this affected a core part of the original AB3, many files needed to be changed while monitoring for possible regressions. One change required was that command words could now comprise multiple words, which the original AB3 did not allow. The other change was that the AB3 prefixes did not need to end with a space e.g. `n/John Doe`, but our flags would need an extra space at the end such as `-n John Doe`. Hence, the implementation was non-trivial and was not simply a matter of replacing command words.

* **New Feature**: Implement command to search for folders [\#77](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/77)
  * What it does: Allows the user to search for folders by keyword
  * Justification: As UNIon allows users to create folders, users may want to search for folders to narrow down the list of folders they can see.
  * Highlights: This enhancement is similar to the original `find` command for persons. However, the command to find persons only matches full words while the command to find folders allows partial words to match e.g. the keyword `CS` matches `CS2103`. Furthermore, by adding the `-folders` tag to the command, some validation checks needed to be updated in order to ignore the flags from the search terms.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=xlzior)

* **Project management**:
  * Managed project deliverables to ensure module deadlines are met
  * In charge of reviewing other team member's PRs (together with one other team member)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Unix commands` [\#41](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/41) and `find -folders` [\#55](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/55)
  * Developer Guide:
    * Added implementation details of the `find -folders` feature [\#84](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/84).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#46](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/46), [\#57](https://github.com/AY2122S1-CS2103-T16-1/tp/pull/57)
  * Contributed to module forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/4#issuecomment-898862824), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/102#issuecomment-905653729), [3](https://github.com/nus-cs2103-AY2122S1/forum/issues/142#issuecomment-908973467), [4](https://github.com/nus-cs2103-AY2122S1/forum/issues/241#issuecomment-920977286), [5](https://github.com/nus-cs2103-AY2122S1/forum/issues/277#issuecomment-928639397), [6](https://github.com/nus-cs2103-AY2122S1/forum/issues/286#issuecomment-934306682))
  * Reported bugs and suggestions for other teams in the class ([examples](https://github.com/xlzior/ped/issues))
