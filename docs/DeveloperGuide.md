---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Libraries used: [git-hooks-gradle-plugin](https://github.com/jakemarsden/git-hooks-gradle-plugin),
  [Jackson](https://github.com/FasterXML/jackson), [JavaFX](https://openjfx.io/), [Jekyll](https://jekyllrb.com/),
  [JUnit5](https://github.com/junit-team/junit5), [PlantUML](https://plantuml.com/)

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/MainApp.java).
It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user
issues the command `delete-c 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `HomeTab`,
`StatTab` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the
commonalities between classes that represent parts of the visible GUI.

There are 2 main tab panes contained in `MainWindow`:
* `HomeTab`: Displays all the orders, client bookmarks and recipe bookmarks.
* `StatTab`: Displays all the ingredients and also business statistics such as revenue for each month, top 10 most
  frequent clients and top 10 most popular recipes

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
that  are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteClientCommand`)
   which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a client).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.
1. Lastly the `LogicManager` communicates with `Storage` to save the changes.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete-c 1")`
API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteClientSequenceDiagram.png)

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddOrderCommandParser`) which uses the other classes shown above to
  parse the user command and create a `XYZCommand` object (e.g., `AddOrderCommand`) which the `AddressBookParser`
  returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddOrderCommandParser`, `DeleteOrderCommandParser`, ...) inherit from the
  `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/model/Model.java)

![ModelClassDiagram](images/ModelClassDiagram.png)

The `Model` component,

* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a
  `ReadOnlyUserPref` objects.
* stores the application data in the following components:
  * a `UniqueClientList` containing `Client` objects.
  * a `UniqueIngredientList` containing `Ingredient` objects.
  * a `UniqueOrderList` containing `Order` objects.
  * a `UniqueRecipeList` containing `Recipe` objects.
* exposes an unmodifiable `ObservableList<Client>`, `ObservableList<Ingredient>`, `ObservableList<Order>` and
  `ObservableList<Recipe>` which can be observed by the UI. This allows the UI to automatically update the displayed
  list when the data in the list is modified.
* does not depend on any of the other three components (`Ui`, `Logic`, `Storage`).

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-W16-2/tp/blob/master/src/main/java/ay2122s1_cs2103t_w16_2/btbb/storage/Storage.java)

![StorageClassDiagram](images/StorageClassDiagram.png)

The `Storage` component,
* can save both application data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `ay2122s1_cs2103t_w16_2.btbb.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add feature

#### Overview

Add operations can be executed for the following entities: clients, orders, ingredients and recipes.

#### Mechanism

This is how the add mechanism works in general:

1. User enters an add command.
1. A relevant `AddXCommandParser`, where `X` is one of the entities, parses the command to generate a `AddXCommand`.
1. The `AddXCommandParser` generates a relevant `XDescriptor` that contains the details of the entity `X` to be created.
1. The `AddXCommand` is executed. During execution, the `XDescriptor` is converted to its respective `X` entity.
1. `Model` adds the entity.
1. `Storage` saves the changes.
1. Feedback about the status of the add is shown to the user.

The following sequence diagram shows how the add order operation works. The sequence diagram for other
entities (client, ingredient and recipe) in the model is similar. However, for add order,
there is an additional optional step of subtracting the ingredient quantities for each matching ingredient in
the inventory.

![AddOrderSequenceDiagram](images/AddOrderSequenceDiagram.png)

#### Usage scenarios

The following activity diagram summarizes what happens when a user executes an add order command:

![AddOrderActivityDiagram](images/AddOrderActivityDiagram.png)

Example of a successful addition using the add order command:

1. The user wishes to add an order.
1. The user executes an appropriate add order command to add an order. The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app adds the order and the order is shown in the list.

### Copy bookmark details to order feature

#### Overview

Copy operations can be executed for the following entities: clients and recipes. Instead of keying in all the
details of a client when adding an order, the user can specify the index of the client displayed in the client list
to copy over the details of the client to the order. This can also be done for recipes.

Example:
* With both client and recipe index `add-o c/1 r/3 od/12-12-2021 1800 oq/2`

#### Mechanism

This is how the copy mechanism works in general:
1. User enters an add order command using `X` index where `X` is one of the entities.
1. A relevant `AddOrderCommandParser` parses the command to generate a `AddOrderCommand`.
1. The `AddOrderCommand` is executed.
1. `Model` gets `X` from `X` list using the specified index.
1. Details of `X` is copied into the order.
1. `Model` adds the order.
1. `Storage` saves the changes.
1. Feedback about the status of the addition is shown to the user.

The sequence diagram is similar to the add feature.

#### Usage scenarios

The following activity diagram summarizes what happens when a user executes an add order command using a client index.
The activity diagram for adding an order using a recipe index is similar.

![CopyClientBookmarkDetailsToOrderActivityDiagram](images/CopyClientBookmarkDetailsToOrderActivityDiagram.png)

Example of a successful addition using the add order command using a client index:

1. The user wishes to add an order for the first client in the displayed client list.
1. The user executes `add-o c/1 rn/Chicken Rice od/12-12-2021 1800 op/4.00 oq/2` The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app adds an order containing the details of the first client and the order is shown in the list.

### Delete feature

#### Overview

Delete operations can be executed for the following entities: clients, orders, ingredients and recipes.

#### Mechanism

This is how the delete mechanism works in general:
1. User enters a delete command.
1. A relevant `DeleteXCommandParser`, where `X` is one of the entities, parses the command to generate a
   `DeleteXCommand`.
1. The `DeleteXCommand` is executed.
1. `Model` deletes the entity.
1. `Storage` saves the changes.
1. Feedback about the status of the delete is shown to the user.

The following sequence diagram shows how the delete order operation works. The sequence diagram for other
entities (client, ingredient and recipe) in the model is similar. However, for delete order, there is an additional
optional step of adding ingredient quantities back to matching ingredients in the inventory.

![DeleteOrderSequenceDiagram](images/DeleteOrderSequenceDiagram.png)

#### Usage scenarios

The following activity diagram summarizes what happens when a user executes a delete order command:

![DeleteOrderActivityDiagram](images/DeleteOrderActivityDiagram.png)

Example of a successful deletion using the delete order command:

1. The user wishes to delete the first order in the list.
1. The user executes `delete-o 1` command to delete the first order. The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app deletes the order and the order is no longer shown in the list.

### Edit feature

#### Overview

Edit operations can be executed for the following entities: clients, orders, ingredients and recipes.

#### Mechanism

This is how the edit mechanism works in general:

1. User enters an edit command.
1. A relevant `EditXCommandParser`, where `X` is one of the entities, parses the command to generate a `EditXCommand`.
1. The `EditXCommandParser` generates a relevant `XDescriptor` that contains the details of the entity `X` to be edited.
1. The `EditXCommand` is executed. During execution, the `XDescriptor` is converted to its respective `X` entity.
1. `Model` updates the entity.
1. `Storage` saves the changes.
1. Feedback about the status of the edit is shown to the user.

The following sequence diagram shows how the edit order operation works. The sequence diagram for other
entities (client, ingredient and recipe) in the model is similar. However, for edit order,
there is an additional optional step of adding/subtracting the ingredient quantities for each matching ingredient in
the inventory when there is a change in order quantity.

![EditOrderSequenceDiagram](images/EditOrderSequenceDiagram.png)

#### Usage scenarios

The following activity diagram summarizes what happens when a user executes an edit order command:

![EditOrderActivityDiagram](images/EditOrderActivityDiagram.png)

Example of a successful edit using the edit order command:

1. The user wishes to edit an order.
1. The user executes an appropriate edit order command to edit an order. The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app edits the order and the order is shown in the list.

### Edit ingredients in order/ recipe feature

#### Overview
Each order and recipe can contain a list of ingredients.
The list is edited through add and delete operations.

#### Mechanism - Add order/ recipe ingredient

This is how the mechanism works in general:

1. User enters an add ingredient command.
1. A relevant `AddXIngredientCommandParser`, where `X` is either `Order` or `Recipe`, parses the command to generate a `AddXIngredientCommand`.
1. The `AddXIngredientCommand` is executed.
1. The new ingredient is added to a copy of the ingredients list of the specified order or recipe, forming a new ingredients list.
1. `Model` updates the order or recipe with the new ingredients list.
1. `Storage` saves the changes.
1. Feedback about the status of the addition is shown to the user.

The sequence diagram is similar to the edit feature.

#### Mechanism - Delete order/ recipe ingredient

This is how the mechanism works in general:

1. User enters a delete ingredient command.
1. A relevant `DeleteXIngredientCommandParser`, where `X` is either `Order` or `Recipe`, parses the command to generate a `DeleteXIngredientCommand`.
1. The `DeleteXIngredientCommand` is executed.
1. A copy of the ingredients list of the specified order or recipe is made and the specified ingredient is removed.
1. `Model` updates the order or recipe with the new ingredients list.
1. `Storage` saves the changes.
1. Feedback about the status of the deletion is shown to the user.

The sequence diagram is similar to the edit feature.

#### Usage Scenario - Add order/ recipe ingredient

Example of a successful addition of ingredient using the add recipe ingredient command:

1. The user wishes to add an ingredient to a recipe in the list.
1. The user executes `add-ri 1 in/Rice iq/400 iu/g` command to add 400 grams of Rice to the first recipe. The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app adds the ingredient to the recipe and the edited recipe is displayed.

![AddRecipeIngredientActivityDiagram](images/AddRecipeIngredientActivityDiagram.png)

#### Usage Scenario - Delete order/ recipe ingredient

Example of a successful deletion of ingredient using the delete recipe ingredient command:

1. The user wishes to delete an ingredient from a recipe in the list.
1. The user executes `delete-ri 1 i/2` command to delete the second ingredient from the first recipe. The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app deletes the ingredient from the recipe and the edited recipe is displayed.

![DeleteRecipeIngredientActivityDiagram](images/DeleteRecipeIngredientActivityDiagram.png)

### Find feature

#### Overview

Find operations can be executed for all entity types in the model, ie.
clients, orders, ingredients and recipes.

#### Mechanism

The find mechanism is facilitated by 4 generic classes that implement the
Java `Predicate` interface. The 4 classes are `PredicateCollection`,
`StringContainsKeywordsPredicate`, `ValueInListPredicate` and
`ValueWithinRangePredicate`. All of these classes are generic so that their
functionality can be reused for all entity types. The purposes of these classes
are as follows:
* `StringContainsKeywordsPredicate` - Tests if a field in an object matches
  any of the keywords provided by the user. This class can perform partial and
  case-insensitive matches.

* `ValueInListPredicate` - Tests if a value of a field in an object
  exists in the list of values provided by the user.

* `ValueWithinRangePredicate` - Tests if a value of a field in an object
  is within a range of values provided by the user.

* `PredicateCollection` - Tests if an object satisfies all the predicates
  contained in this class. This class enables finding entities based on
  multiple search criteria.

#### Usage Scenario

1. The user executes the `find-i in/avo gin iqf/1 iqt/10` command to find ingredients whose name matches
   the keywords 'avo' or 'gin' and whose quantities are from the range 1-10.

2. The `FindIngredientCommandParser` parses the command into `FindIngredientCommand`. A `StringContainsKeywordsPredicate`
   is created with the keywords 'avo' and 'gin'. A `ValueWithinRangePredicate` is created with the start value set to
   '1' and end value set to '10'. Both of these predicates are added to a `PredicateCollection`. A `FindCommand` is
   created with the `PredicateCollection`.

3. The `ModelManager#updateFilteredIngredientList` method gets called with the `PredicateCollection` which causes
   the `FilteredIngredientList` in `ModelManager` to only contain ingredients that match all the find criteria.

4. The `FilteredIngredientList` is a JavaFX `ObservableList` that is observed by the `IngredientListPanel`. The change
   in `FilteredIngredientList` will cause the `IngredientListPanel` to re-render, showing only the ingredients that
   match the find criteria.

The following sequence diagram shows how the find operation for the above scenario works:

![FindSequenceDiagram](images/FindSequenceDiagram.png)

### List feature

#### Overview

List operation can be executed for the following entities: clients, orders, ingredients and recipes.

#### Mechanism
This section explains the List Mechanism:

The list mechanism is facilitated by updating the filtered list in `ModelManager` with the predicate that shows all objects in the specified entity list.

The Filtered List is a JavaFX `ObservableList` that is observed by the respective entity Ui Panel.
Changes made to this list will cause the Ui panel to re-render, showing all objects of the specified entity.

#### Usage Scenario

The following sequence diagram shows how the list order operation works:
![ListSequenceDiagram](images/ListSequenceDiagram.png)

Example of a successful outcome using the List command:
1. The user wishes to list all orders in the order list.
1. The user executes `list-o` command to display everything stored in the specified order list. The `commandText` is received by `MainWindow#executeCommand()`.
1. The `ListOrderCommandParser` parses the command into a `ListOrderCommand`, which is passed back to the `Logic` to be executed.
1. The `Logic` Component then executes the command.
1. The Model's `filteredOrderList` will be updated to show all orders.
1. The `CommandResult` created would then be passed to the Ui components, to display the updated order list and result message to the user.
1. The app shows all orders in the order list.

### Statistics feature

#### Overview

Statistics will always be displayed to the user.
The following order operations can be executed to update the statistics:
* add-o
* delete-o
* edit-o (with changes made to recipe price and/or client phone number and client name)

#### Mechanism

This is how the statistics mechanism works in general:

1. User enters any order command that updates statistics.
1. A relevant `XOrderCommandParser`, where `X` is one of the operations, parses the command to generate a `XOrderCommand`.
1. The `XOrderCommand` is executed.
1. `Model` updates the `UniqueOrderList` and `Storage` saves the changes.
1. Feedback about the status of the operation is shown to the user.
1. The relevant data is retrieved from `Model` via `Logic` and passed into a new `StatTabContent` object.
1. `StatTab` which contains the statistic charts, is updated with the contents of the `StatTabContent`.

The following sequence diagram shows how the statistics work:

![StatsSequenceDiagram](images/StatsSequenceDiagram.png)

#### Usage scenarios

The following activity diagram summarizes what happens when statistics are updated through an order command:

![StatsActivityDiagram](images/StatsActivityDiagram.png)

Example of a successful update to statistics, by adding an order:

1. The user wishes to add an order.
1. The user executes an appropriate add order command to add an order. The `commandText` is received by
   `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app adds the order and the order is shown in the list.
1. Since the command will update the statistic charts, the new chart data is retrieved from Model.
1. All charts are updated with the new chart data.

### Tab feature

#### Overview
Tabs are switched programmatically in 2 ways:
1. Tab command
1. Executing a command that affects the view of contents in a tab

#### Mechanism
The tab switch mechanism is facilitated by `CommandResult`.
This is because any programmatic tab switch will only occur after the execution of a command
and information about the switch has to be passed to `Ui` components.
After executing a command, if a tab switch should occur,
the tab to switch to is stored in a `CommandResult`.
This `CommandResult` is created in a `Ui` object through `MainWindow#executeCommand()`.
Hence, data about whether there is a tab switch and which tab to switch to
can be passed to `Ui` components and rendered accordingly.

The following sequence diagram shows how the tab operation works:

![TabSequenceDiagram](images/TabSequenceDiagram.png)

#### Usage Scenarios

The following activity diagram summarizes what happens when a user executes a command:

![TabActivityDiagram](images/TabActivityDiagram.png)

Example of a successful switch using the Tab command:
1. The user wishes to switch to the Home tab.
1. The user executes `tab 1` command to switch to the Home tab. The `commandText` is received by `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app shows the Home tab.

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

* Home chef
* Prepares meal orders at home
* Sells meals to clients through delivery
* Individual business owner
* Tracks orders, inventory and revenue
* Types quickly
* Prefers typing to mouse interactions
* Comfortable with CLI applications


**Value proposition**: The application allows home chefs who are individual business owners to track orders and revenue.
They can easily and quickly create new orders by copying bookmarked client and recipe details.
They can also use the app to track the status of their inventory.
Some business statistics are available to help home chefs make business plans.


### User stories

Priorities: High - (must have), Medium - (nice to have), Low -  (unlikely to have)

<table>
  <tr>
   <th style="text-align: center" colspan="3"><h3><strong>As a home chef</strong></h3></th>
  </tr>
  <tr>
    <th style="text-align: center"><h3><strong>Priority</strong></h3></th>
    <th style="text-align: center"><h3><strong>I can...</strong></h3></th>
    <th style="text-align: center"><h3><strong>So that...</strong></h3></th>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add an ingredient and quantity to the inventory</td>
    <td>I can keep track of my inventory</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete ingredients from the inventory</td>
    <td>My inventory is always up to date</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit ingredients in the inventory</td>
    <td>I can update my inventory with ingredients that I recently bought or used.</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find ingredients by keywords</td>
    <td>I know the quantity of a specific ingredient</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all ingredients in my inventory</td>
    <td>I can see the remaining quantity of all my ingredients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate ingredients</td>
    <td>I do not clutter the application with duplicate ingredients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a client</td>
    <td>I can copy details from a client to easily fill up the details of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete clients</td>
    <td>I can remove former clients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit clients</td>
    <td>I can update their contact information</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find clients by keywords</td>
    <td>I can find client information to fill up the details of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all clients</td>
    <td>I can view all my current clients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate clients</td>
    <td>I do not clutter the application with duplicate clients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add an order</td>
    <td>I can keep track of my orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a quantity to an order</td>
    <td>I can create multiple orders for the same client</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a price to an order</td>
    <td>I can keep track of my revenue</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete orders</td>
    <td>I can delete cancelled orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit orders</td>
    <td>I can update the details of my orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find orders by keywords</td>
    <td>I can find a specific order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all orders</td>
    <td>I can view all my current orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate orders</td>
    <td>I do not clutter the application with duplicate orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Mark an order as done</td>
    <td>I can set the status of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Mark an order as undone</td>
    <td>I can reverse the changes made to the status of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a recipe</td>
    <td>I can copy details from a recipe to easily fill up the details of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete recipes</td>
    <td>I can delete unused recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit recipes</td>
    <td>I can update recipe details, like changing the ingredients used or recipe name</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find recipes by keywords</td>
    <td>I can reference certain recipes when creating an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all recipes</td>
    <td>I can see all the current recipes used</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate recipes</td>
    <td>I do not clutter the application with duplicate recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my most popular recipes</td>
    <td>I can focus on growing my business with the popular recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my monthly revenue</td>
    <td>I can tell if my business is growing</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my most frequent clients</td>
    <td>I can reinforce business relationships with my most frequent clients</td>
  </tr>
</table>


### Use cases

(For all use cases below, the **System** is the `BobTheBistroBoss (BTBB)` and the **Actor** is the `Home Chef`, unless specified otherwise)

**Use case: UC01 Add client**

**MSS**

1.  Home Chef chooses to add a client.
2.  Home Chef enters details in required format.
3.  BTBB displays the new client.

    Use case ends.

**Extensions**

* 2a. Client entered already exists in the system.
    * 2a1. BTBB informs Home Chef that client already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why addition of client failed.

      Use case ends.

**Use case: UC02 Find client**

**MSS**

1.  Home Chef chooses to find a client by a field.
2.  Home Chef enters search terms in required format.
3.  BTBB displays all client(s) that match the terms.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why finding a client failed.

      Use case ends.

**Use case: UC03 Delete client**

**MSS**

1.  Home Chef chooses to delete a client.
2.  Home Chef enters details to delete a client.
3.  BTBB informs Home Chef that client has been deleted.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why deletion of client failed.

      Use case ends.

**Use case: UC04 View all clients**

**MSS**

1.  Home Chef chooses to view all clients.
2.  Home Chef enters command to see all clients.
3.  BTBB displays all clients.

    Use case ends.

**Use case: UC05 Edit client**

**MSS**

1.  Home Chef chooses to edit a client’s details.
2.  Home Chef enters details in required format.
3.  BTBB displays the edited client.

    Use case ends.

**Extensions**

* 2a. Client entered already exists in the system.
    * 2a1. BTBB informs Home Chef that client already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why editing of client failed.

      Use case ends.

**Use case: UC06 Add ingredient**

**MSS**

1.  Home Chef chooses to add an ingredient.
2.  Home Chef enters details in required format.
3.  BTBB displays the new ingredient.

    Use case ends.

**Extensions**

* 2a. Ingredient entered already exists in the system.
    * 2a1. BTBB informs Home Chef that ingredient already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why addition of ingredient failed.

      Use case ends.

**Use case: UC07 Find Ingredient**

**MSS**

1.  Home Chef chooses to find an ingredient by a field.
2.  Home Chef enters search terms in required format.
3.  BTBB displays all ingredient(s) that match the terms.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why finding an ingredient failed.

    Use case ends.

**Use case: UC08 Delete Ingredient**

**MSS**

1.  Home Chef chooses to delete an ingredient.
2.  Home Chef enters details to delete an ingredient.
3.  BTBB informs Home Chef that ingredient has been deleted.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why deletion of ingredient failed.

      Use case ends.

**Use case: UC09 View all ingredients**

**MSS**

1.  Home Chef chooses to view all ingredients.
2.  Home Chef enters command to see all ingredients.
3.  BTBB displays all ingredients.

    Use case ends.

**Use case: UC10 Edit Ingredient**

**MSS**

1. Home Chef chooses to edit an ingredient.
2. Home Chef enters details in required format.
3. BTBB displays the edited ingredient.

    Use case ends.

**Extensions**

* 2a. Ingredient entered already exists in the system.
    * 2a1. BTBB informs Home Chef that ingredient already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why editing of ingredient failed.

      Use case ends.

**Use case: UC11 Add Order**

**MSS**

1. Home Chef chooses to add an order.
2. Home Chef enters details in required format.
3. BTBB displays the new order.

    Use case ends.

**Extensions**

* 2a. Order entered already exists in the system.
    * 2a1. BTBB informs Home Chef that order already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why addition of order failed.

    Use case ends.

**Use case: UC12 Find order**

**MSS**

1. Home Chef chooses to find an order by a field.
2. Home Chef enters search terms in required format.
3. BTBB displays all order(s) that match the terms.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why finding an order failed.

      Use case ends.

**Use case: UC13 Delete Order**

**MSS**

1. Home Chef chooses to delete an order.
2. Home Chef enters details in required format.
3. BTBB informs Home Chef that order has been deleted.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why deletion of order failed.

      Use case ends.

**Use case: UC14 View all orders**

**MSS**

1.  Home Chef chooses to view all orders.
2.  Home Chef enters command to see all orders.
3.  BTBB displays all orders.

    Use case ends.

**Use case: UC15 Edit order**

**MSS**

1. Home Chef chooses to edit an order.
2. Home Chef enters details in required format.
3. BTBB displays the edited order.

   Use case ends.

**Extensions**

* 2a. Order entered already exists in the system.
    * 2a1. BTBB informs Home Chef that order already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why editing of order failed.

      Use case ends.

**Use case: UC16 Mark order as done**

**MSS**

1. Home Chef chooses to mark an order as done.
2. BTBB displays order as done.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why marking an order as done failed.

      Use case ends.

**Use case: UC17 Mark order as undone**

**MSS**

1. Home Chef chooses to mark an order as undone.
2. BTBB displays order as undone.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why marking an order as undone failed.

      Use case ends.

**Use case: UC18 Add recipe**

**MSS**

1. Home Chef chooses to add a recipe.
2. Home Chef enters details in required format.
2. BTBB displays the new recipe.

   Use case ends.

**Extensions**

* 2a. Recipe entered already exists in the system.
    * 2a1. BTBB informs Home Chef that recipe already exists.

      Use Case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why addition of recipe failed.

      Use case ends.

**Use case: UC19 Find recipe**

**MSS**

1. Home Chef chooses to find a recipe by a field.
2. Home Chef enters search terms in required format.
3. BTBB displays all recipe(s) that match the terms.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why finding a recipe failed.

      Use Case ends.

**Use case: UC20 Delete recipe**

**MSS**

1. Home Chef chooses to delete a recipe.
2. Home Chef enters details in required format.
3. BTBB informs Home Chef that recipe has been deleted.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why deletion of recipe failed.

      Use Case ends.

**Use case: UC21 View all recipes**

**MSS**

1. Home Chef chooses to view all recipes.
2. Home Chef enters command to see all recipes.
3. BTBB displays all recipes.

   Use case ends.

**Use case: UC22 Edit recipe**

**MSS**

1. Home Chef chooses to edit a recipe.
2. Home Chef enters details in required format.
3. BTBB displays the edited recipe.

   Use case ends.

**Extensions**

* 2a. Recipe entered already exists in the system.
    * 2a1. BTBB informs Home Chef that recipe already exists.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why editing of recipe failed.

      Use case ends.

**Use case: UC23 View revenue for each month for past 12 months**

**MSS**

1. Home Chef wants to view revenue earned each month for past 12 months.
2. Home Chef switches to statistics tab.
3. BTBB displays revenue earned each month for the past 12 months in a bar chart.

   Use case ends.

**Use case: UC24 View top 10 most frequent clients**

**MSS**

1. Home Chef chooses to view top 10 most frequent clients.
2. Home Chef switches to statistics tab.
3. BTBB displays top 10 most frequent clients in a pie chart.

   Use case ends.

**Use case: UC25 View top 10 most popular recipes**

**MSS**

1. Home Chef chooses to view top 10 most popular recipes.
2. Home Chef switches to statistics tab.
3. BTBB displays top 10 most popular recipes in a pie chart.

   Use case ends.

### Non-Functional Requirements

1. Usability Requirements:
    * Should work on systems with Java 11 and above.
    * Should work on Windows, Linux and macOS.
    * Should function fully offline without access to the internet.
    * Cannot be used on mobile devices.
    * Should function smoothly in English, there are no guarantees for other languages.

2. Data Requirements:
    * Data of clients, orders and ingredients should persist after the app closes.
    * Should be able to handle up to 20000 orders.
    * Data should be transferable from 1 computer to another.

### Glossary

* **Mainstream OS**: Windows, Linux and macOS.
* **BTBB**: BobTheBistroBoss.
* **Client**: Client information that can be copied to orders.
* **Ingredient**: Ingredient information that is stored in inventory, orders and recipes.
* **Inventory**: Keeps track of ingredients and their quantities.
* **Order**: Meal order sold to a client.
* **Recipe**: Recipe information that can be copied to orders.

#### Prefix Glossary

| Prefix | Parameter                | Associated with |
|:------:|:------------------------:|:---------------:|
| c/     | CLIENT_INDEX             | Client          |
| ca/    | CLIENT_ADDRESS           | Client          |
| ce/    | CLIENT_EMAIL             | Client          |
| cn/    | CLIENT_NAME              | Client          |
| cp/    | CLIENT_PHONE             | Client          |
| i/     | INGREDIENT_INDEX         | Ingredient      |
| in/    | INGREDIENT_NAME          | Ingredient      |
| iq/    | INGREDIENT_QUANTITY      | Ingredient      |
| iqf/   | INGREDIENT_QUANTITY_FROM | Ingredient      |
| iqt/   | INGREDIENT_QUANTITY_TO   | Ingredient      |
| iu/    | INGREDIENT_UNIT          | Ingredient      |
| od/    | ORDER_DEADLINE           | Order           |
| of/    | ORDER_FINISHED           | Order           |
| op/    | ORDER_PRICE              | Order           |
| oq/    | ORDER_QUANTITY           | Order           |
| r/     | RECIPE_INDEX             | Recipe          |
| ri/    | RECIPE_INGREDIENT        | Recipe          |
| rn/    | RECIPE_NAME              | Recipe          |
| rp/    | RECIPE_PRICE             | Recipe          |

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Viewing help and Switching tabs

1. Viewing help
    1. Test case: `help`
       <br>Expected: A pop up window is displayed with a link to BTBB's user guide.
    1. Test case: `help 123`
       <br>Expected: A pop up window is displayed with a link to BTBB's user guide.

1. Switching tabs
    1. Test case: `tab 1`
       <br>Expected: UI switches to the Home tab.
    1. Test case: `tab 2`
       <br>Expected: UI switches to the Inventory & Statistics tab.
    1. Test case: `tab -1`
       <br>Expected: UI does not switch tabs. Error details shown in the result display box.
    1. Test case: `tab abc`
       <br>Expected: UI does not switch tabs. Error details shown in the result display box.
    1. Test case: `tab 5`
       <br>Expected: UI does not switch tabs. Error details shown in the result display box.

### Client

1. Adding a client
    1. Prerequisite: Client bookmarks list does not contain a client with `99887766` as his/her phone number.
    1. Test case: `add-c cn/Richard Roe cp/99887766 ce/richardr@example.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: A new client is added to client bookmarks list with the given details. Details of the added
       client are shown in the result display box.
    1. Test case: `add-c cn/John Doe cp/98765432 ce/johnd@example.com`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c cn/John Doe cp/98765432 ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c cn/John Doe ce/johnd@example.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c cp/98765432 ce/johnd@example.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c cn/John Doe cp/98765432 ce/johndexample.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c cn/John Doe cp/98765432 ce/+johnd@example.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c cn/John Doe cp/987654321098765432100 ce/johnd@example.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c 1 cn/John Doe cp/987654321098762100 ce/johnd@example.com ca/311, Clementi Ave 2, #02-25`
       <br>Expected: No client is added. Error details shown in the result display box.
    1. Test case: `add-c`
       <br>Expected: No client is added. Error details shown in the result display box.

1. Deleting a client
    1. Prerequisites: Client bookmarks list shows at least 1 client and at most 3 clients.
    1. Test case: `delete-c 1`
       <br>Expected: First client is deleted from the client bookmarks list. Details of the deleted client are
       shown in the result display box.
    1. Test case: `delete-c -1`
       <br>Expected: No client is deleted. Error details shown in the result display box.
    1. Test case: `delete-c 4`
       <br>Expected: No client is deleted. Error details shown in the result display box.
    1. Test case: `delete-c abc`
       <br>Expected: No client is deleted. Error details shown in the result display box.
    1. Test case: `delete-c`
       <br>Expected: No client is deleted. Error details shown in the result display box.

1. Editing a client
    1. Prerequisites: Client bookmarks list shows at least 1 client and at most 3 clients.
    1. Test case: `edit-c 1 cn/Marcus Goh ce/marcusg@gmail.com`
       <br>Expected: First client in the client bookmarks list is edited to have the name 'Marcus Goh' and email
       'marcusg@gmail.com'. Its position in the client bookmarks list may change. Details of the edited client are
       shown in the result display box.
    1. Test case: `edit-c 1 ca/333, Serangoon North Ave 1`
       <br>Expected: First client in the client bookmarks list is edited to have the address '333, Serangoon North Ave 1'.
    1. Test case: `edit-c cn/Ryan Lim`
       <br>Expected: No client is edited. Error details shown in the result display box.
    1. Test case: `edit-c abc cn/Ryan Lim`
       <br>Expected: No client is edited. Error details shown in the result display box.
    1. Test case: `edit-c`
       <br>Expected: No client is edited. Error details shown in the result display box.
    1. Test case: `edit-c 1`
       <br>Expected: No client is edited. Error details shown in the result display box.
    1. Test case: `edit-c -1 cn/Ryan Lim`
       <br>Expected: No client is edited. Error details shown in the result display box.
    1. Test case: `edit-c 5 cn/Ryan Lim`
       <br>Expected: No client is edited. Error details shown in the result display box.
    1. Test case: `edit-c 1 cp/98`
       <br>Expected: No client is edited. Error details shown in the result display box.

1. Finding clients by keywords
    1. Prerequisites: There are exactly 2 clients in the client bookmarks list. The client details are as follows:
       1. Name: John Doe, Phone: 98765432, Address: 311, Clementi Ave 2, #02-25, Email: johnd@gmail.com
       1. Name: Gary Lim, Phone: 99887766, Address: 333, Buona Vista Ave 2, #03-37, Email: garyl@gmail.com
    1. Test case: `find-c cn/john`
       <br>Expected: Client bookmarks list only shows the client with the name 'John Doe'.
    1. Test case: `find-c cn/gary`
       <br>Expected: Client bookmarks list only shows the client with the name 'Gary Lim'.
    1. Test case: `find-c ca/311`
       <br>Expected: Client bookmarks list only shows the client with the address '311, Clementi Ave 2, #02-25'.
    1. Test case: `find-c ca/333`
       <br>Expected: Client bookmarks list only shows the client with the address '333, Buona Vista Ave 2, #03-37'.
    1. Test case: `find-c cp/9876`
       <br>Expected: Client bookmarks list only shows the client with the phone '98765432'.
    1. Test case: `find-c cp/9988`
       <br>Expected: Client bookmarks list only shows the client with the phone '99887766'.
    1. Test case: `find-c ce/john`
       <br>Expected: Client bookmarks list only shows the client with the email 'johnd@gmail.com'.
    1. Test case: `find-c ce/gary`
       <br>Expected: Client bookmarks list only shows the client with the email 'garyl@gmail.com'.
    1. Test case: `find-c cn/`
       <br>Expected: No change in the client bookmarks list display. Error details shown in the result display box.
    1. Test case: `find-c`
       <br>Expected: No change in the client bookmarks list display. Error details shown in the result display box.
    1. Test case: `find-c 1 cn/john`
       <br>Expected: No change in the client bookmarks list display. Error details shown in the result display box.

1. Listing all clients
    1. Prerequisite: Client bookmark list has at least 1 client.
    1. Test case: `list-c`
       <br>Expected: Client bookmarks list displays all clients.
    1. Test case: `list-c 123`
       <br>Expected: Client bookmarks list displays all clients.

### Ingredient

1. Adding an ingredient
    1. Prerequisites: Inventory has no ingredients.
    1. Test case: `add-i in/Chocolate Syrup iq/3 iu/bottles`
       <br>Expected: A new ingredient is added to inventory with the given details.
       Details of added ingredient will be shown in the result display box.
    1. Test case: `add-i in/Chocolate iq/4`
       <br>Expected: No ingredient is added. Error details shown in the result display box.
    1. Test case: `add-i iq/4 iu/bars`
       <br>Expected: No ingredient is added. Error details shown in the result display box.
    1. Test case: `add-i in/Chocolate iu/bars`
       <br>Expected: No ingredient is added. Error details shown in the result display box.
    1. Test case: `add-i in/Chocolate iq/0 iu/bars`
       <br>Expected: No ingredient is added. Error details shown in the result display box.
    1. Test case: `add-i 2 in/Chocolate iq/3 iu/bars`
       <br>Expected: No ingredient is added. Error details shown in the result display box.
    1. Test case: `add-i`
       <br>Expected: No ingredient is added. Error details shown in the result display box.

1. Deleting an ingredient
    1. Prerequisites: Inventory has at least 1 ingredient and at most 3 ingredients.
    1. Test case: `delete-i 1`
       <br>Expected: First ingredient in inventory is deleted.
       Details of deleted ingredient will be shown in the result display box.
    1. Test case: `delete-i 0`
       <br>Expected: No ingredient is deleted. Error details shown in the result display box.
    1. Test case: `delete-i -2`
       <br>Expected: No ingredient is deleted. Error details shown in the result display box.
    1. Test case: `delete-i abc`
       <br>Expected: No ingredient is deleted. Error details shown in the result display box.
    1. Test case: `delete-i`
       <br>Expected: No ingredient is deleted. Error details shown in the result display box.

1. Editing an ingredient
    1. Prerequisites: Inventory only has these 3 ingredients:
        * name: Apple, unit: whole
        * name: Banana, unit: whole
        * name: Carrot, unit: pack
    1. Test case: `edit-i 1 in/Rice iq/4 iu/bags`
       <br>Expected: First ingredient in the inventory is edited to have ingredient name 'Rice', quantity '4' and unit 'bags'.
       Its position in the inventory may change. Details of the edited ingredient are shown in the result display box.
    1. Test case: `edit-i 3 in/Apricot`
       <br>Expected: Third ingredient in the inventory is edited to have ingredient name 'Apricot'.
       Its position in the inventory may change. Details of the edited ingredient are shown in the result display box.
    1. Test case: `edit-i 0 in/Apple`
       <br> Expected: No ingredient is edited. Error details shown in the result display box.
    1. Test case: `edit-i -2 in/Apple`
       <br> Expected: No ingredient is edited. Error details shown in the result display box.
    1. Test case: `edit-i in/Apple`
       <br> Expected: No ingredient is edited. Error details shown in the result display box.
    1. Test case: `edit-i 1`
       <br> Expected: No ingredient is edited. Error details shown in the result display box.

1. Finding ingredients by keywords
    1. Prerequisites: There are exactly 3 ingredients in the inventory.
        * name: Green Apple, quantity: 10, unit: whole
        * name: Banana, quantity: 13, unit: whole
        * name: Carrot, quantity: 20, unit: pack
    1. Test case: `find-i in/apple iq/10`
       <br>Expected: Inventory only shows the ingredient with the ingredient name 'Green Apple'.
    1. Test case: `find-i in/apple iq/11`
       <br>Expected: Inventory shows 0 ingredients.
    1. Test case: `find-i iq/13`
       <br>Expected: Inventory only shows the ingredient with the ingredient name 'Banana'.
    1. Test case: `find-i iqf/13`
       <br>Expected: Inventory shows 2 ingredients: 'Banana' and 'Carrot'.
    1. Test case: `find-i iqf/10 iqt/13`
       <br>Expected: Inventory shows 2 ingredients: 'Green Apple' and 'Banana'.
    1. Test case: `find-i iq/10 13`
       <br>Expected: Inventory shows 2 ingredients: 'Green Apple' and 'Banana'.
    1. Test case: `find-i iq/13 iqf/10 iqt/20`
       <br>Expected: Inventory only shows the ingredient with the ingredient name 'Banana'.
    1. Test case: `find-i in/`
       <br>Expected: No change in the inventory. Error details shown in the result display box.
    1. Test case: `find-i iqf/20 iqt/10`
       <br>Expected: No change in the inventory. Error details shown in the result display box.
    1. Test case: `find-i 2 iq/13`
       <br>Expected: No change in the inventory. Error details shown in the result display box.
    1. Test case: `find-i`
       <br>Expected: No change in the inventory. Error details shown in the result display box.

1. Listing all ingredients
    1. Prerequisite: Inventory has at least 1 ingredient.
    1. Test case: `list-i`
       <br>Expected: Inventory displays all ingredients.
    1. Test case: `list-i abc`
       <br>Expected: Inventory displays all ingredients.

### Order

1. Adding an Order
    1. Prerequisites (should be satisfied for each individual test case):
       1. Order list does not contain an order with the following details:
          * Client Name: Alex Yeoh
          * Client Phone: 87438807
          * Client Address: Blk 30 Geylang Street 29. #06-40
          * Recipe Name: Chicken Rice
          * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
          * Order Price: $4.00
          * Order Deadline: 12 December 2021 at 12 pm
          * Order Quantity: 1
          * Completion status: Uncompleted
       1. Client bookmark list contains at least one client and the first client in the list has the following details:
          * Client Name: Alex Yeoh
          * Client Phone: 87438807
          * Client Address: Blk 30 Geylang Street 29. #06-40
          * Client Email: alexyeoh@gmail.com
       1. Recipe bookmark list contains at least one recipe and the first recipe in the list has the following details:
          * Recipe Name: Chicken Rice
          * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
          * Recipe Price: 4
    1. Test case (all client and recipe details specified): `add-o cn/Alex Yeoh cp/87438807 ca/Blk 30 Geylang Street 29. #06-40 rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: A new order is added to order list with the given details. Details of the added
       order are shown in the result display box.
    1. Test case (only client index used): `add-o c/1 rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: A new order is added to order list with the given details (client details are copied from the first client). Details of the added
       order are shown in the result display box.
    1. Test case (only recipe index used): `add-o cn/Alex Yeoh cp/87438807 ca/Blk 30 Geylang Street 29. #06-40 r/1 op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: A new order is added to order list with the given details (recipe details are copied from the first recipe). Details of the added
       order are shown in the result display box.
    1. Test case (both client and recipe index used): `add-o c/1 r/1 op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: A new order is added to order list with the given details (client and recipe details are copied from the first client and recipe respectively). Details of the added
       order are shown in the result display box.
    1. Test case: `add-o cn/Alex Yeoh cp/87438807 ca/Blk 30 Geylang Street 29. #06-40 rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole op/4 od/12-12-2021 1200`
       <br> Expected: A new order is added to order list with the given details. Details of the added
       order are shown in the result display box.
    1. Test case: `add-o cn/Alex Yeoh cp/87438807 ca/Blk 30 Geylang Street 29. #06-40 rn/Milo op/1.40 od/12-12-2021 1200`
       <br> Expected: A new order is added to order list with the given details. Details of the added
       order are shown in the result display box.
    1. Test case: `add-o cn/Alex Yeoh cp/87438807 ca/Blk 30 Geylang Street 29. #06-40 rn/Nasi Lemak op/1.40`
       <br> Expected: No order is added. Error details shown in the result display box.
    1. Test case (all client and recipe details specified): `add-o cn/Alex Yeoh cp/847521698521145366247893451125 ca/Blk 30 Geylang Street 29. #06-40 rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: No order is added. Error details shown in the result display box.
    1. Test case: `add-o c/0 r/1 op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: No order is added. Error details shown in the result display box.
    1. Test case: `add-o op/4 od/12-12-2021 1200 oq/1`
       <br> Expected: No order is added. Error details shown in the result display box.
    1. Test case: `add-o`
       <br> Expected: No order is added. Error details shown in the result display box.

1. Adding an order ingredient
    1. Prerequisites: Order list shows at least 1 order and at most 3 orders. The first order in the list does not have rice (in grams) as one of its ingredients.
    1. Test case: `add-oi 1 in/Rice iq/400 iu/g`
       <br> Expected: The new ingredient is added to the first order.
       Details of the new ingredient and the edited order are shown in the result display box.
    1. Test case: `add-oi 4 in/Rice iq/400 iu/g`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi 1 in/Chicken iq/0 iu/whole`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi 1 in/Chicken iq/500000 iu/whole`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi 1 in/Chi$ken iq/4 iu/whole`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi 1 in/Chicken iq/4 iu/who%le`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi 1 in/Rice iu/g`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi in/Rice iq/400 iu/g`
       <br> Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `add-oi`
       <br> Expected: No order is edited. Error details shown in the result display box.

1. Deleting an order
    1. Prerequisites: Order list shows at least 1 order and at most 3 orders.
    1. Test case: `delete-o 1`
       <br>Expected: First order is deleted from the order list. Details of the deleted order are
       shown in the result display box.
    1. Test case: `delete-o -1`
       <br>Expected: No order is deleted. Error details shown in the result display box.
    1. Test case: `delete-o 4`
       <br>Expected: No order is deleted. Error details shown in the result display box.
    1. Test case: `delete-o abc`
       <br>Expected: No order is deleted. Error details shown in the result display box.
    1. Test case: `delete-o`
       <br>Expected: No order is deleted. Error details shown in the result display box.

1. Deleting an order ingredient
    1. Prerequisites: Order at index 1 has at least 1 ingredient and at most 3 ingredients.
    1. Test case: `delete-oi 1 i/1`
       <br>Expected: First ingredient is deleted from the ingredient list in the first order of the order list.
       Details of the deleted ingredient and edited order are shown in the result display box.
    1. Test case: `delete-oi 1 i/10`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `delete-oi abc i/1`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `delete-oi 1 i/abc`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `delete-oi 1`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `delete-oi i/1`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `delete-oi`
       <br>Expected: No order is edited. Error details shown in the result display box.

1. Editing an order
    1. Prerequisites (should be satisfied for each individual test case):
       1. Order list contains at least one order and the first order in the list has the following details:
          * Client Name: Alex Yeoh
          * Client Phone: 87438807
          * Client Address: Blk 30 Geylang Street 29. #06-40
          * Recipe Name: Chicken Rice
          * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
          * Order Price: $4.00
          * Order Deadline: 12 December 2021 at 12 pm
          * Order Quantity: 1
          * Completion status: Uncompleted
       1. Client bookmark list contains at least one client and the first client in the list has the following details:
          * Client Name: Richard Yeoh
          * Client Phone: 96847225
          * Client Address: Blk 2 Bedok Street 12. #06-41
          * Client Email: richard@gmail.com
       1. Recipe bookmark list contains at least one recipe and the first recipe in the list has the following details:
          * Recipe Name: Nasi Lemak
          * Recipe Ingredients: Rice x 200 g, Fish x 1 whole
          * Recipe Price: 3
    1. Test case: `edit-o 1 cn/Alexa White cp/91542652`
       <br>Expected: The client name and phone in the first order is edited to 'Alexa White' and '91542652' respectively.
       Details of the edited order are shown in the result display box.
    1. Test case: `edit-o 1 c/1`
       <br>Expected: The details of the first client in the client bookmarks list is copied to the first order in the order list.
       Details of the edited order are shown in the result display box.
    1. Test case: `edit-o 1 r/1`
       <br>Expected: The details of the first recipe in the recipe bookmarks list is copied to the first order in the order list.
       Details of the edited order are shown in the result display box.
    1. Test case: `edit-o 1 op/1 od/12-12-2021 1900 oq/2`
       <br>Expected: First order in the order list is edited to have order price of '1', order deadline at '12 December 2021 7pm' and order quantity of '2'.
       Its position in the order list may change. Details of the edited order are shown in the result display box.
    1. Test case: `edit-o rn/Chocolate Cupcake op/20`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `edit-o`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `edit-o 1`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `edit-o abc`
       <br>Expected: No order is edited. Error details shown in the result display box.
    1. Test case: `edit-o 1 op/-2`
       <br>Expected: No order is edited. Error details shown in the result display box.

1. Finding orders by keywords
    1. Prerequisites: There are exactly 2 orders in the order list.
       1. First order has the following details:
          * Client Name: Bernice Yu
          * Client Phone: 98762159
          * Client Address: Blk 12 Holland Village Street 1. #01-40
          * Recipe Name: Nasi Lemak
          * Recipe Ingredients: Rice x 200 g, Fish x 1 whole
          * Order Price: $3.00
          * Order Deadline: 15 December 2021 at 1 pm
          * Order Quantity: 2
          * Completion status: Uncompleted
       1. Second order has the following details:
          * Client Name: Alex Yeoh
          * Client Phone: 87438807
          * Client Address: Blk 30 Geylang Street 29. #06-40
          * Recipe Name: Chicken Rice
          * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
          * Order Price: $4.00
          * Order Deadline: 12 October 2021 at 12 pm
          * Order Quantity: 1
          * Completion status: Completed
    1. Test case: `find-o cn/alex`
       <br>Expected: Order list only shows the second order.
    1. Test case: `find-o of/yes`
       <br>Expected: Order list only shows the second order.
    1. Test case: `find-o of/no`
      <br>Expected: Order list only shows the first order.
    1. Test case: `find-o rn/Nasi Lemak`
      <br>Expected: Order list only shows the first order.
    1. Test case: `find-o rn/Chicken Rice`
      <br>Expected: Order list only shows the second order.
    1. Test case: `find-o cn/charlie`
       <br>Expected: Order list shows 0 orders.
    1. Test case: `find-o cn/`
       <br>Expected: No change in the order list display. Error details shown in the result display box.
    1. Test case: `find-o`
       <br>Expected: No change in the order list display. Error details shown in the result display box.

1. Listing all orders
    1. Prerequisite: Order list has at least one order.
    1. Test case: `list-o`
        <br>Expected: Order list displays all orders.
    1. Test case: `list-o abc`
       <br>Expected: Order list displays all orders.

1. Marking an order as done
    1. Prerequisites: There are exactly 2 orders in the order list.
        1. First order has the following details:
            * Client Name: Bernice Yu
            * Client Phone: 98762159
            * Client Address: Blk 12 Holland Village Street 1. #01-40
            * Recipe Name: Nasi Lemak
            * Recipe Ingredients: Rice x 200 g, Fish x 1 whole
            * Order Price: $3.00
            * Order Deadline: 15 December 2021 at 1 pm
            * Order Quantity: 2
            * Completion status: Uncompleted
        1. Second order has the following details:
            * Client Name: Alex Yeoh
            * Client Phone: 87438807
            * Client Address: Blk 30 Geylang Street 29. #06-40
            * Recipe Name: Chicken Rice
            * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
            * Order Price: $4.00
            * Order Deadline: 12 October 2021 at 12 pm
            * Order Quantity: 1
            * Completion status: Completed
    1. Test case: `done-o 1`
       <br>Expected: The first order is marked as completed. It's position in the Order list may change. Details of
           marked order are shown in the result display.
    1. Test case: `done-o 2`
       <br>Expected: The second order is still marked as completed. Details of marked order are shown in the result display.
    1. Test case: `done-o 3`
       <br>Expected: No change in the order list display. Error details shown in the result display box.
    1. Test case: `done-o -1`
       <br>Expected: No change in the order list display. Error details shown in the result display box.

1. Marking an order as undone
    1. Prerequisites: There are exactly 2 orders in the order list.
        1. First order has the following details:
            * Client Name: Bernice Yu
            * Client Phone: 98762159
            * Client Address: Blk 12 Holland Village Street 1. #01-40
            * Recipe Name: Nasi Lemak
            * Recipe Ingredients: Rice x 200 g, Fish x 1 whole
            * Order Price: $3.00
            * Order Deadline: 15 December 2021 at 1 pm
            * Order Quantity: 2
            * Completion status: Uncompleted
        1. Second order has the following details:
            * Client Name: Alex Yeoh
            * Client Phone: 87438807
            * Client Address: Blk 30 Geylang Street 29. #06-40
            * Recipe Name: Chicken Rice
            * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
            * Order Price: $4.00
            * Order Deadline: 12 October 2021 at 12 pm
            * Order Quantity: 1
            * Completion status: Completed
    1. Test case: `undone-o 2`
       <br> The second order is marked as uncompleted. It's position in the Order list may change. Details of
       marked order are shown in the result display.
    1. Test case: `undone-o 1`
       <br>Expected: The first order is still marked as uncompleted. Details of marked order are shown in the result display.
    1. Test case: `undone-o 3`
      <br>Expected: No change in the order list display. Error details shown in the result display box.
    1. Test case: `undone-o -1`
      <br>Expected: No change in the order list display. Error details shown in the result display box.

### Recipe

1. Adding a Recipe
    1. Prerequisites (should be satisfied for each individual test case):
        1. Recipe list does not contain a recipe with the following details:
            * Recipe Name: Chicken Rice
            * Recipe Ingredients: Rice x 200 g, Chicken x 1 whole
            * Recipe Price: $4.00
    1. Test case: `add-r rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole rp/4`
        <br> Expected: A new recipe is added to the recipe list with the given details. Details of the added
        recipe are shown in the result display box.
    1. Test case: `add-r rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole`
       <br> Expected: No recipe is added. Error details shown in the result display box.
    1. Test case: `add-r ri/Rice-200-g, Chicken-1-whole rp/4`
       <br> Expected: No recipe is added. Error details shown in the result display box.
    1. Test case: `add-r rn/Chicken Rice$$ ri/Rice-200-g, Chicken-1-whole rp/4`
       <br> Expected: No recipe is added. Error details shown in the result display box.
    1. Test case: `add-r rn/Chicken Rice ri/Rice, Chicken-1-whole rp/4`
       <br> Expected: No recipe is added. Error details shown in the result display box.
    1. Test case: `add-r rn/Chicken Rice ri/Rice-200-g, Chicken-1-whole rp/$4`
        <br> Expected: No recipe is added. Error details shown in the result display box.

1. Adding a recipe ingredient
    1. Prerequisites: Recipe list shows at least 1 recipe and at most 3 recipes. The first recipe in the list does not have rice (in grams) as one of its ingredients.
    1. Test case: `add-ri 1 in/Rice iq/400 iu/g`
          <br> Expected: The new ingredient is added to the first recipe.
          Details of the new ingredient and the edited recipe are shown in the result display box.
    1. Test case: `add-ri 4 in/Rice iq/400 iu/g`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri 1 in/Chicken iq/0 iu/whole`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri 1 in/Chicken iq/500000 iu/whole`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri 1 in/Chi$ken iq/4 iu/whole`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri 1 in/Chicken iq/4 iu/who%le`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri 1 in/Rice iu/g`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri in/Rice iq/400 iu/g`
       <br> Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `add-ri`
        <br> Expected: No recipe is edited. Error details shown in the result display box.

1. Deleting a recipe
    1. Prerequisites: Recipe list shows at least 1 recipe and at most 3 recipes.
    1. Test case: `delete-r 1`
       <br>Expected: First recipe is deleted from the recipe list. Details of the deleted recipe are
       shown in the result display box.
    1. Test case: `delete-r -1`
       <br>Expected: No recipe is deleted. Error details shown in the result display box.
    1. Test case: `delete-r 4`
       <br>Expected: No recipe is deleted. Error details shown in the result display box.
    1. Test case: `delete-r abc`
       <br>Expected: No recipe is deleted. Error details shown in the result display box.
    1. Test case: `delete-r`
       <br>Expected: No recipe is deleted. Error details shown in the result display box.

1. Deleting a recipe ingredient
    1. Prerequisites: Recipe at index 1 has at least 1 ingredient and at most 3 ingredients.
    1. Test case: `delete-ri 1 i/1`
       <br>Expected: First ingredient is deleted from the ingredient list in the first recipe of the recipe bookmarks list.
       Details of the deleted ingredient and edited recipe are shown in the result display box.
    1. Test case: `delete-ri 1 i/10`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `delete-ri abc i/1`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `delete-ri 1 i/abc`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `delete-ri 1`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `delete-ri i/1`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `delete-ri`
       <br>Expected: No recipe is edited. Error details shown in the result display box.

1. Editing a recipe
    1. Prerequisites: Recipe bookmarks list only has these 3 recipes:
        * name: Banana split, price: $10
        * name: Orange cake, price: $12
        * name: Pecan pie, price: $10
    1. Test case: `edit-r 1 rn/Truffle fries rp/8.00`
       <br>Expected: First recipe in the recipe bookmarks list is edited to have recipe name 'Truffle fries' and recipe price of '$8.00'.
       Its position in the recipe bookmarks list may change. Details of the edited recipe are shown in the result display box.
    1. Test case: `edit-r 1 rn/Apple pie`
       <br>Expected: First recipe in the recipe bookmarks list is edited to have recipe name 'Apple pie'.
       Its position in the recipe bookmarks list may change. Details of the edited recipe are shown in the result display box.
    1. Test case: `edit-r rn/Apple pie`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `edit-r`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `edit-r 1`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `edit-r abc`
       <br>Expected: No recipe is edited. Error details shown in the result display box.
    1. Test case: `edit-r 1 rp/-2`
       <br>Expected: No recipe is edited. Error details shown in the result display box.

1. Finding recipes by keywords
    1. Prerequisites: There are exactly 2 recipes in the recipe bookmarks list.
       One has the recipe name 'Apple Pie', the other has the recipe name 'Banana split'.
    1. Test case: `find-r rn/apple`
       <br>Expected: Recipe bookmarks list only shows the recipe with the recipe name 'Apple Pie'.
    1. Test case: `find-r rn/chocolate`
       <br>Expected: Recipe bookmarks list shows 0 recipes.
    1. Test case: `find-r rn/`
       <br>Expected: No change in the recipe bookmarks list display. Error details shown in the result display box.
    1. Test case: `find-r`
       <br>Expected: No change in the recipe bookmarks list display. Error details shown in the result display box.

1. Listing all recipes
    1. Prerequisites: Recipe bookmarks list has at least 1 recipe.
    1. Test case: `list-r`
       <br>Expected: Recipe bookmarks list displays all recipes.
    1. Test case: `list-r abc`
       <br>Expected: Recipe bookmarks list displays all recipes.

### Exiting the program

1. Exit the application
    1. Prerequisite: Application is running.
    1. Test case: `exit`
       <br>Expected: Application window closes. Data is saved to the json file.

### Saving data and Editing the data file

1. Dealing with missing data files
    1. Move your copy of `btbb.jar` to an empty directory.
    1. If that directory has a directory named `data`, delete the `data` directory.
    1. Double-click `btbb.jar` to launch the application.
      If that does not work, use your terminal to navigate to the directory containing `btbb.jar`
      and execute `java -jar btbb.jar`.
      <br>Expected: The application GUI shows sample data.

1. Dealing with corrupted data files
    1. Replace all of the content in `data/btbb.json` with `Corrupted file`.
    1. Double-click `btbb.jar` to launch the application.
       If that does not work, use your terminal to navigate to the directory containing `btbb.jar`
       and execute `java -jar btbb.jar`.
       <br>Expected: The application GUI does not show any data.
