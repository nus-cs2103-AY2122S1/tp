---
layout: page
title: Ng Seng Leng's Project Portfolio Page
---

### Project: GoMedic

GoMedic is a **cross-platform desktop application written in Java and designed for doctors and medical residents to
manage contacts and patient details**. We aim for GoMedic to be used by someone who can type fast and take advantage of the
optimized features for Command Line Interface.

GoMedic is bootstrapped using SE-EDU Address Book 3 and inherits some of its features such as `clear`, parameter
formatting, etc.

Given below are some of my notable contributions to the project.

* **New Feature**: Implement the Suggestions feature
    * What it does: Provides correct command suggestions in text form if user inputs a wrong command (typically as a typo)
    * Justification: This is to provide a better user experience such that the user can receive instant feedback in case 
      of erroneous inputs and do not have to refer to external manuals or the internal help command to figure out
      what is wrong with their inputs.
    * Highlights: This feature makes use of the Levenshtein Distance metric, a commonly used metric in determining the similarity
      between strings, to rank the suggestions in terms of its relevance to the incorrect command.
    * Notable PRs: [PR #103](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/103), [PR #145](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/145)
    * Credits:
        * Code written is original, Levenshtein Distance algorithm is done using the Apache Commons Library in Java.

* **New Feature**: Implement the `help` command.
    * What it does: Keeps track of all commands in GoMedic, its format and a brief explanation of what each command does.
    * Justification: As per all command line interface applications, this is the usual help function ported to a GUI interface
      and provides quick access to a summary of what each command does and how to use it should the user require some familiarisation.
    * Highlights: This builds upon the modified help window that comes with the original AB3 with some modifications on how 
      the text is generated.
    * Notable PRs: [PR #106](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/106)

* **New Feature**: Implement the command history feature
    * What it does: Keeps track of all commands entered into GoMedic regardless of its validity. It can be navigated using
      up and down arrow keys to show previous and next commands, if any.
    * Justification: Aids in manual testing without the hassle of copy-pasting commands. Also useful in referring to what has 
      been entered into the app in the current instance without checking through the app itself.
    * Notable PRs: [PR #203](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/203)
    * Credits:
        * Simon made some bug fixes to the feature
        * Referenced code from [this PR](https://github.com/AY2021S1-CS2103T-T10-4/tp/pull/113)
  
* **Enhanced feature**: Implement `Appointment` as a type of `Activity`
    * What it does: Adds a boolean field to each `Activity` object so that it becomes an appointment instead that only exists if 
      the patient which the appointment is for exists.
    * Justification: This is similar in nature to the `Activity` model, which is implemented by Simon, but it separates appointments from
      typical activities that a doctor might have. This allows for some differentiation in the nature of descriptions and 
      makes the app more realistic for a doctor's daily activities.
    * Highlights: This builds upon the `Activity` model with the additional condition that it is also removed if the patient
      it is referencing does not exist in GoMedic anymore.
    * Notable PRs: [PR #162](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/162), [PR #201](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/201)
    
* **Overall Code Contribution**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=ngsengleng&tabRepo=AY2122S1-CS2103T-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Opened issues that sought to improve to code quality (E.g. [Issue #285](https://github.com/AY2122S1-CS2103T-T15-1/tp/issues/285))
    * Reviewed and approved team members' PRs for merging (more details in the **Community section**)

* **Documentation**:
    * User Guide:
        * Wrote the [Suggestion feature](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#42-suggestions) section.
        * Wrote the [Help](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#353-viewing-help--help) section.
        * Wrote the [Add appointment command](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#333-adding-a-new-appointment-add-tappointment) section.
        * Reformatted the [Profile](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#352-customizing-your-own-profile-profile) section
          and the [Referral](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#351-generating-a-referral-referral) section.
        * Reformatted `clear` into 4 subsections under their respective sections ([Doctor](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#32-doctors-related-features), [Patient](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#31-patients-related-features), [Activity](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#33-activities-related-features), [Clear](https://ay2122s1-cs2103t-t15-1.github.io/tp/UserGuide.html#354-clearing-all-entries--clear))
    * Developer Guide:
        * Revamped the [Model Component](https://ay2122s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#model-component)
          segment of the original AB3 DG. This included updating the Class Diagram, adding an additional Class Diagram as well as adding more details about the implementation of the `Model` class in general.
        * Wrote the section about the implementation of the [suggestions feature execution](https://ay2122s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#suggestions-feature).
        * Wrote the section about the implementation of the [command history feature execution](https://ay2122s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#command-history-feature).
        * Wrote Use Case 4 in the [Use cases](https://ay2122s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#use-cases) section.
        * Wrote manual testing cases for [suggestions](https://ay2122s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#displaying-suggestions-for-commands-that-are-misspelled) and [command history](https://ay2122s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#navigating-between-all-commands-input-in-the-current-session)

* **Community**:
    * Reviewed PRs of fellow teammates (Some with non-trivial review comments, E.g. [PR #222](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/222), [PR #123](https://github.com/AY2122S1-CS2103T-T15-1/tp/pull/123))
    * Reported bugs and suggestions for other teams during [PE-D](https://github.com/ngsengleng/ped). 

