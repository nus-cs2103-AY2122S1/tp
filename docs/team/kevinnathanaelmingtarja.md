---
layout: page
title: Kevin Nathanael Mingtarja's Project Portfolio Page
---

### Project: Socius

Socius is a contact management desktop application used for CS2103T module takers. Socius is a platform to help students
find potential project group mates for CS2103T. The user interacts with it using a CLI, and it has a GUI created with
JavaFX. Socius is written in Java, and has about 12 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to import/export JSON file.
  * What it does: allows the user to import contacts from a JSON file, and also export the current data to a JSON file.
  * Justification: This feature improves the product significantly because without it, the user will need to manually add data which is tedious.
  * Credits: Adapted some file related functionalities from AB3

* **New Feature**: Added a statistics command that allows the user to see the nationality statistics of a tutorial group.
  * What it does: allows the user to see a pie chart containing the breakdown of nationalities in a tutorial group.
  * Justification: This feature improves the product because it can help users get an overview of how their tutorial group looks like.
  * Highlights: The main challenge when implementing this feature was displaying the pie chart properly

* **New Feature**: Added an alias command that allows the user to give an alias to any commands.
  * What it does: allows the user to bind a particular keyword to a command.
  * Justification: This feature improves the product significantly because it allows user to save the time and effort of typing long commands.

* **New Enhancement**: Added an input suggestion enhancement that suggests potential keyword(s) when a user types in something wrongly.
  * What it does: Provides word suggestions when there is a typo by the user, similar to the "Did You Mean" feature in Google.
  * Justification: This feature improves the user experience quite significantly especially as new users might not yet be accustomed to our command keywords.
  * Highlights: Implemented using a dynamic programming algorithm called `Wagner-Fischer algorithm`, which computes the `Levenshtein distance` of any two words.
  The dynamic programming algorithm used is significantly faster (quadratic time) than the more straightforward but slower recursive algorithm which takes exponential time.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=kevinmingtarja)

* **Project management**:
  * Set up the GitHub team organization

* **Review contributions**:
  * In charge of reviewing and approving pull requests with one other teammate.
  * Reviewed 50 [pull requests](https://github.com/AY2122S1-CS2103T-W08-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Akevinmingtarja).

* **Documentation**:
  * User Guide:
    * Added documentation for the features `alias`, `import`, `export`, and `import`
  * Developer Guide:
    * Added implementation details of the `alias`, `import`, `export`, `import`, and `input suggestion` feature.

* **Tools**:
  * Integrated Codecov into the team repo
