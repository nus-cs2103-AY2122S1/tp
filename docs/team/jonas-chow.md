---
layout: page
title: Jonas Chow's Project Portfolio Page
---

### Project: Source Control

Source Control is a desktop application for CS1101S professors to manage the performance of their students. Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to import data from a CSV file.
    * What it does: Allows the user to add students, groups, and assessments in bulk rather than by inputting hundreds of similar commands.
    * Justification: The professor usually wants to input data for many students and assessments. Student data from external sources like Source Academy or lumiNUS can be exported to excel with a CSV file. This command allows the professor to easily import the data from external platforms.
    * Highlights: Relative file paths are relative to some temporary folder on macOS when running the application via double-clicking the JAR file. This rather inconvenient feature required us to find a way to force the user inputted path to be relative to the folder which the application is in. 
    * Credits: [This](https://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file) StackOverflow post on how to get the folder the JAR file is in.

* **New Feature**: Added the ability to add aliases to commands.
    * What it does: Allows the user to rename commonly used commands to shorter ones. The aliases persist even after leaving the application.
    * Justification: We have many long command names such as `addassessment` and `addstudent`. Given that speed and ease of usage via the CLI interface is a priority, we allow the user to define their own shorter, more convenient aliases for commands that they use often.
    * Highlights: There were many non-trivial design decisions considered when implementing this feature. Some of these include preventing cyclic alias dependencies, and a way to remove the alias. You can read more about these in the [developer guide](../DeveloperGuide.md).
    
* **New Feature**: Added the ability to scroll through past commands.
    * What it does: Pressing the `up` and `down` keys will scroll through your previous commands, similar to any conventional CLI interface.
    * Justification: The case where the user would want to add scores for the same assessment to multiple students is one of many cases where a similar command has to be run multiple times. This feature allows one to input consecutive similar commands easily.
    
* **New Feature**: Added the ability to export data.
    * What it does: Allows the user to export the data to a CSV file. 
    * Justification: Exporting the data serves as both a way to share the data with the user's colleagues for them to import to their copy of the application, and a way to back up the data.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jonas-chow)

* **Project management**:
    * Set up and managed the milestones `v1.1` - `v1.3` (4 milestones) on GitHub
    * Managed the releases `v1.1` - `v1.3.trial` (4 releases) on GitHub
    * Enabled assertions for Gradle

* **Enhancements to existing features**:
    * Adapted storage code from Address Book 3 to suit the context of Source Control (Pull request [\#99](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/99))
    * Adapted tests from Address Book 3 to pass for the newly adapted Source Control code (Pull requests [\#103](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/103), [\#104](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/104), [\#107](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/107))
    * Enable the export graph function for the `show` command (Pull request [\#183](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/183))
    * Wrote additional tests for existing features (Pull request [\#257](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/257))

* **Documentation**:
    * User Guide:
        * Adapted content from Address Book 3 to suit the context of Source Control (Pull request [\#33](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/33))
        * Added documentation for the features `import`, `export`, and `alias` (Pull requests [\#33](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/33), [\#181](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/181))
    * Developer Guide:
        * Updated glossary section (Pull request [\#50](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/50))
        * Added implementation details of the `import` and `alias` feature (Pull requests [\#144](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/144), [\#280](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/280))
    
* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests [\#170](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/170), [\#244](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/244), [\#267](https://github.com/AY2122S1-CS2103T-W08-2/tp/pull/267))
    * Reported bugs and suggestions for other teams in the class (Examples: [\#177](https://github.com/AY2122S1-CS2103T-T09-2/tp/issues/177), [\#187](https://github.com/AY2122S1-CS2103T-T09-2/tp/issues/187), [\#1](https://github.com/jonas-chow/ped/issues/1))
