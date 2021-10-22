---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `AnimeListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Anime` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AnimeListParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a anime).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AnimeListParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AnimeListParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Anime` objects (which are contained in a `UniqueAnimeList` object).
* stores the currently 'selected' `Anime` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Anime>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AnimeList`, which `Anime` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Anime` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both anime list data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AnimeListStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.anilist.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAnimeList`. It extends `AnimeList` with an undo/redo history, stored internally as an `animeListStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAnimeList#commit()` — Saves the current anime list state in its history.
* `VersionedAnimeList#undo()` — Restores the previous anime list state from its history.
* `VersionedAnimeList#redo()` — Restores a previously undone anime list state from its history.

These operations are exposed in the `Model` interface as `Model#commitAnimeList()`, `Model#undoAnimeList()` and `Model#redoAnimeList()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAnimeList` will be initialized with the initial anime list state, and the `currentStatePointer` pointing to that single anime list state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th anime in the anime list. The `delete` command calls `Model#commitAnimeList()`, causing the modified state of the anime list after the `delete 5` command executes to be saved in the `animeListStateList`, and the `currentStatePointer` is shifted to the newly inserted anime list state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new anime. The `add` command also calls `Model#commitAnimeList()`, causing another modified anime list state to be saved into the `animeListStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAnimeList()`, so the anime list state will not be saved into the `animeListStateList`.

</div>

Step 4. The user now decides that adding the anime was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAnimeList()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous anime list state, and restores the anime list to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AnimeList state, then there are no previous AnimeList states to restore. The `undo` command uses `Model#canUndoAnimeList()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAnimeList()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the anime list to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `animeListStateList.size() - 1`, pointing to the latest anime list state, then there are no undone AnimeList states to restore. The `redo` command uses `Model#canRedoAnimeList()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the anime list, such as `list`, will usually not call `Model#commitAnimeList()`, `Model#undoAnimeList()` or `Model#redoAnimeList()`. Thus, the `animeListStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAnimeList()`. Since the `currentStatePointer` is not pointing at the end of the `animeListStateList`, all anime list states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire anime list.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the anime being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

### \[Proposed\] Clear Feature

#### Proposed Implementation

The proposed clear mechanism is facilitated by 3 `Command`s, namely `ClearCommand`,
`AbortClearCommand` and `ConfirmClearCommand`. This results in a confirmation message to be displayed
to the user when the user executes `clear`, after which it can either be confirmed (by entering `clear` again) or 
aborted (by entering any other input).

The last command executed by the user is stored internally as a `Command` in `LogicManager`.
`LogicManager` makes use of the method `Command#requiresConfirmation` to check whether the
last command requires a user confirmation. If this is the case, the `Parser` parses the next user input
as a confirmation message instead.

{To be added later}

#### Design considerations:

**Aspect: Determining valid confirmation input messages:**

* **Alternative 1 (current choice):** User enters `clear` again to actually clear displayed animes; 
  else to abort.
    * Pros: The least ambiguous; message reflects what is to be done. Only two scenarios (user input is 
      `clear` vs user input is not `clear`) to handle.
    * Cons: Entering regular commands such as `list` will abort the `clear` command instead of
       executing as per normal.

* **Alternative 2:** User enters `y` to actually clear displayed animes and `n` to abort; otherwise
  the input is deemed invalid and an exception is thrown.
    * Pros: These are common CLI confirmation formats that the user may be familiar with.
    * Cons: Entering `clear` again will simply result in an exception thrown, which may add to the
    confusion. In addition, if the input is invalid, the `clear` command is aborted anyway,
      making having invalid inputs redundant.

### \[Proposed\] Find Feature

#### Proposed Implementation

The proposed find mechanism is implemented through the use of 2 `Predicate<Anime>`, one for the tab options and one for
regular filter.

{To be added later}

#### Design considerations:

**Aspect: How find executes:**

* **Alternative 1 (current choice):** Search the keywords with the prefixes `/g` and `/n`. Does not reset tab and 
resets after each search. 
    * Pros: Allow for search with multiple spaces in between, allow for searching within tabs.
    * Cons: Complicated to implement.

* **Alternative 2:** Search each keyword split by whitespace. Resets tab after each search.
    * Pros: Less complicated and less coupling.
    * Cons: Does not allow for searches with specific criteria nor keywords with whitespaces in between, does not allow 
    searching within tabs.

_{more aspects and alternatives to be added}_

### \[Proposed\] Themes Feature

#### Propose Implementation

The proposed theme mechanism is simply done by completely altering the base css file of the program. The active css 
file will be saved in `preferences.json` upon exiting the application.

If `preferences.json` is empty or the theme css file given is not valid, the default theme will be used instead and 
will replace the invalid css file specification in `preferences.json`.

### \[Proposed\] Genre tagging feature

#### Current Implementation

The `Genre` command allows the user to specify any `Genre` for an `Anime`, and stores it in a `Set` in the `Anime`.
However, the `Genre` specified isn't tracked anywhere else.<br>
If a user wishes to specify the same `Genre` for a different `Anime`, the user will have to type the `Genre`'s name 
again exactly, and the system will create a new `Genre` and store it inside that `Anime` as well. 
* Pros: Easy to implement, and easy to manage as we don't have to worry about syncing the `Genres` in multiple lists
* Cons: Inconvenient for the user, as `Genres` with slightly different names are considered as different, even if the user may have meant for them to be the same.
    For example, `science fiction`, `sci fi`, `scifi`

#### Proposed Implementation

Instead of directly adding a `Genre` to an `Anime`, we can instead add it to a `GenresList`, and keep track of all user defined `Genres` there.<br>
The user can then tag an `Anime` with a `Genre` in this list.
* Pros: User no longer have to keep track of all the `Genres` that has been added, and this prevents "similar `Genres`", which improves `find` by `Genre` functionality
* Cons: More storage intensive, as we have to now store a list of `Genre`. 
  `Genre` must be synced in multiple locations, and deletion of a `Genre` from the `GenreList` would require the system to remove the `Genre` from all tagged `Animes`.
  

Given below is a MSS of an example usage scenario of a user adding a `Genre` to an `Anime`.

**Use case: UCP1 - Add genre to an anime**

**MSS**
1.  <ins>User lists all anime(UC01)</ins>
2.  User decides which `Anime` to add a `Genre`
3.  User requests a list of all previously added `Genre`
4.  System displays a list of all previously added `Genre`
5.  User chooses a `Genre` to tag the `Anime` with
6.  User requests to tag an `Anime` with a `Genre`
7.  System tags the Anime with the specified `Genre`
    
    Use case ends. 

**Extensions**

- 4a. The `Genre` user wishes to tag is not in the list

    - 4a1. User adds a new `Genre` to the list
    
    Use case resumes at step 4
  
- 4a1. The `Genre` user wishes to add is already in the list
  
    - 4a1a. System displays an error message, telling user that the `Genre` already exists
    
    Use case resumes at step 4

#### Design considerations:

**Aspect: Interaction with `find`:**

* **Alternative 1** `Genre` keeps track of which anime is tagged by this `Genre`. Find searches from the `GenreList`.
    * Pros: Very efficient, as we do not need to go through the list of anime checking each of the anime. Works well when number of `Anime` is large.
    * Cons: More storage intensive, requires us to store the list of tagged `Anime` for every `Genre`. 
      Could also cause problems if the save file is tempered with, as there is bi-directional referencing between `Anime` and `Genre`
      
* **Alternative 2 (Suggested)** Find searches through the list of `Anime` and see if each `Anime` is tagged with this `Genre`
    * Pros: Less bug prone, easy to implement, doesn't require a bi-directional navigability between `Anime` and `Genre`
    * Cons: Can have an effect on performance when a large number of `Anime` is in the list
    
**Aspect: How to implement `GenreList`**
* `GenreList` as a `HashSet`
    * Pros: Prevents duplication, easy to implement.
    
    


## **Glossary, Naming Conventions**

### Action
Used to provide more information to some commands, like `genre`.

### AniList
The name of our app. Only used for package name.

### Anime
Represents a single season of an anime series.

### AnimeList
A list of Anime, also the overall structure of the data.
I.e. the user data is stored as an instance of AnimeList in json format.

### Animes
We chose this to denote multiple anime even though the plural for anime is anime.
This is to distinct between a single anime and multiple animes.

### Genre
Represents a genre of an anime.

### Status
Represents the watch status of an anime



--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of anime
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Manage anime faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                             | So that I can…​                                                     |
| -------- | ------------------------------------------ | ------------------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions                      | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new anime                             | keep track of the anime                                                |
| `* * *`  | user                                       | delete an anime                             | remove anime that I no longer need                                     |
| `* * *`  | user                                       | list all anime                              | see all animes I have added                                            |
| `* *`  | user                                         | update anime episode                        | ensure I am on the right episode of the anime                          |
| `* *`  | user                                         | update anime status                         | know if I have watched or am watching the anime                        |
| `* *`  | user                                         | change anime name                           | rename the anime if I made a mistake                                   |
| `* *`  | user                                         | add a genre to an anime                     | keep track of what genre the anime is in                               |
| `* *`  | user                                         | delete a genre from an anime                | remove unnecessary genres from an anime                                |
| `* *`  | user                                         | clear my anime list                         | remove multiple animes at once                                         |
| `* *`  | user                                         | list all anime with a specific watch status | see all animes in each watch status                                    |
| `* *`  | user                                         | find specific animes                        | see all animes with specific genres or names                           |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Anilist` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - List all anime**

**MSS**

1.  User requests to list anime
2.  AniList shows a list of anime

    Use case ends.

**Use case: UC02 - Add an anime**

**MSS**

1.  User requests to add an anime to the list
2.  AniList adds the anime

    Use case ends.

**Extensions**

- 1a. The anime name is a duplicate.

  - 1a1. AniList shows an error message.

    Use case resumes at step 1.

**Use case: UC03 - Delete an anime**

**MSS**

1.  User requests to list anime
2.  AniList shows a list of anime
3.  User requests to delete a specific anime in the list
4.  AniList deletes the anime

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AniList shows an error message.

      Use case resumes at step 2.

**Use case: UC04 - Update the episode of an anime**

**MSS**

1.  User requests to list anime
2.  AniList shows a list of anime
3.  User requests to update the episode number of a specific anime
4.  AniList updates the episode number of the anime

    Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. AniList shows an error message.

      Use case resumes at step 2.
      
**Use case: UC05 - Update the status of an anime**

**MSS**

1.  User requests to list anime
2.  AniList shows a list of anime
3.  User requests to update the status of a specific anime
4.  AniList updates the status of the anime

    Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. AniList shows an error message.

      Use case resumes at step 2.

**Use case: UC06 - Find anime by name and genre**

1. User requests to find anime based on some name and genre
2. AniList shows a list of anime filtered based on these criteria and the current 
tab.

    Use case ends.

**Extensions**

* 1a. The given request is invalid.

    * 1a1. AniList shows an error message.

      Use case ends.

*{More to be added}*

**Use case: UC07 - Change application theme**

1. User changes the anime theme based on the menu bar theme options
2. AniList applies the required css file based on the theme selected

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 anime without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to work offline.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Anime**: a style of Japanese film and television animation, typically aimed at adults as well as children.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting an anime

1. Deleting an anime while all animes are being shown

   1. Prerequisites: List all animes using the `list` command. Multiple animes in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No anime is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
