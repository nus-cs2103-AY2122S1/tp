---
layout: page
title: Andrew Baruch's Project Portfolio Page
---

### Project: ContactSh

ContactSH is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, ContactSH can get your contact management
tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=leopardmerkava&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)
 
* **New Feature**: Added the importance marker.
  * What it does: Allows the user to mark people as important.
  * Justification: Some people are more important than others. Hence, allowing user to mark people in their contacts as important to make it easier for them to highlight people.
  * Highlights: This enhancement was moderately easy as there was not too many codes to change.

* **New Feature**: Added the cache.
  * What it does: Stores the user input as wel as allow user to access it using up and down arrow key.
  * Justification: Repeating commands is something which people do all the time. Allowing them to access commands they input earlier allows for a convenience. Moreover, since this product
    is aimed for Linux user, a familiar feature such as this one is very beneficial and would feel like second nature.
  * Highlights: This feature was quite difficult to implement. The first issue was to understand how the GUI handles key input. Moreover, it also needs to handle executing the command
  while not allowing user to access the cache manually. After a lot of consideration, I finally ended up creating an internal command and changing the commandResult
  to allow for passing the text straight into the CommandBox.

* **New Feature**: Added the `task`, `addtask`, and `deletetask`.
  * What it does: Allow user to attach task to people and add or delete them.
  * Justification: When working with a lot of people, there is a need to keep track of what's going on with everyone. This
  feature allows for the user to attach any important task to any person. 
  * Highlights: This feature was medium level in terms of implementing. Adding task itself was not too much. However, allowing for
  adding multiple task and deleting task was quite challenging. It required me to fully understand how the model and storage work and
  putting into consideration possible issues such as deleting one task affecting the index of other tasks.

* **New Feature**: Added the `description`.
  * What it does: Add little notes and descriptor to people.
  * Justification: Sometimes merely contact details are not enough. Perhaps more important text are needed. Hence, the description
  added to allow user to add notes to their contacts
  * Highlights: This feature, though just like `remarks`, presented the challenge in adding the GUI. Since we wanted to add
  a separate section for the description, it was quite confusing to implement in terms of JavaFX. I ended up using a feature
  of JavaFX I was not used to which means I had to go through the implementation of said feature first. 
  

* **Documentation**:
  
  * User Guide:
    * Added documentation for the feature `Addtask` and `DeleteTask` [\#30](https://github.com/AY2122S1-CS2103T-W10-1/tp/pull/30)
    * Added documentation for feature `cache` in UG [\#108](https://github.com/AY2122S1-CS2103T-W10-1/tp/pull/108)
    
  * Developer Guide:
    * Added documentation for feature `cache` in DG [\115](https://github.com/AY2122S1-CS2103T-W10-1/tp/pull/115)

  * ReadmeMd:
    * Created Ui Mockup [\#30](https://github.com/AY2122S1-CS2103T-W10-1/tp/pull/30)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#43](https://github.com/AY2122S1-CS2103T-W10-1/tp/pull/43),
    [\#96](https://github.com/AY2122S1-CS2103T-W10-1/tp/pull/96)


