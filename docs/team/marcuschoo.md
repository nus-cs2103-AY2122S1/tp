# EdRecord

## Overview

EdRecord is a **desktop app for managing student contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a TA that can type fast, EdRecord can get your student contact management tasks done faster than traditional GUI apps.

---

## Contributions

### New features implemented

- **Add ability to grade student's assignments** [#119](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/119)
  - **What it does:** allows the TA to grade a student's assignment. The TA can mark a student's assignment as not submitted, submitted or graded, and can optionally provide a score to be saved.
  - **Justification:** This feature improves the product because a key part of a TA's job is grading student's assignments and keeping track of their progress. Saving a student's grades in EdRecord lets TAs keep track of student's progress and performance at a glance.
  - **Highlights:** This enhancement affects many existing components in the model, as the assignment to be graded had to be retrieved from the module and saved to the individual students. A HashMap was used to implement the association between an Assignment and a Grade.

### Enhancements implemented

- **Implement parser for MakeAssignmentCommand** [#83](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/83)
  - This allows the user's command for adding an assignment to be parsed into a valid assignment name, assignment weightage and maxmium attainable score, which would then be passed on to the model to add the assignment into the model.
- **Implemented assignment ID** [#210](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/210)
  - This allows assignments in a module to be referenced by a unique numerical ID, which reduces the hassle of typing the entire assignment name into commands such as `edasg` and `dlasg`.
  - Update `edasg`, `dlasg`, `grade` and `dlgrade` to accept assignment ID as input.
- **Test cases for grades** [#219](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/219)
  - Implmented testing for grade and deleting grade commands, and for saving grades to the JSON file.

### Code contributed

- [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=marcusc55&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=marcusc55&tabRepo=AY2122S1-CS2103-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)
- [Pull requests](https://github.com/AY2122S1-CS2103-W14-3/tp/pulls?q=is%3Apr+author%3Amarcusc55)

### Documentation

- **User Guide**
  - Updated user guide to include instructions for `grade` and `dlgrade` commands.
  - Provided additional detail for assignment commands (`mkasg`, `edasg` and `dlasg` commands).
- **Developer Guide**
  - Documented implementation for grading feature.
  - Updated UML diagrams to accurately reflect classes used to implement grades.

### Community

- Reported bugs and suggestions for other teams in the class (examples: [#171](https://github.com/AY2122S1-CS2103T-W10-4/tp/issues/171), [#137](https://github.com/AY2122S1-CS2103T-W10-4/tp/issues/137), [#149](https://github.com/AY2122S1-CS2103T-W10-4/tp/issues/149))
- Review and give comments for teammates' PRs (examples: [#78](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/78), [#94](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/94), [#136](https://github.com/AY2122S1-CS2103-W14-3/tp/pull/136))
