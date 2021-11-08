package ay2122s1_cs2103t_w16_2.btbb.model;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.GuiSettings;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;
    Predicate<Ingredient> PREDICATE_SHOW_ALL_INGREDIENTS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;
    Predicate<Recipe> PREDICATE_SHOW_ALL_RECIPES = unused -> true;

    //=========== UserPref ===================================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    //=========== AddressBook ================================================================================

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //=========== Client =====================================================================================

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client target) throws NotFoundException;

    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient) throws NotFoundException;

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    //=========== Ingredient ======================================================================================

    /**
     * Adds the given ingredient.
     * {@code ingredient} must not already exist in the address book.
     *
     * @param ingredient to add.
     */
    void addIngredient(Ingredient ingredient);

    /**
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the address book.
     *
     * @param ingredient to check.
     */
    boolean hasIngredient(Ingredient ingredient);

    /**
     * Deletes the given ingredient.
     *
     * @param target The ingredient to delete.
     * @throws NotFoundException when the ingredient does not exist in the address book.
     */
    void deleteIngredient(Ingredient target) throws NotFoundException;

    /**
     * Replaces the existing target Ingredient in the address book with an edited Ingredient.
     *
     * @param target The target ingredient to replace.
     * @param editedIngredient The edited ingredient to replace with.
     * @throws NotFoundException if the target ingredient does not exist in the address book.
     */
    void setIngredient(Ingredient target, Ingredient editedIngredient) throws NotFoundException;

    /** Returns an unmodifiable view of the filtered ingredient list */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Adds the quantity of the ingredient that is the same as the {@code target} ingredient if it exists.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    void addIngredientQuantity(Ingredient target, Quantity multiplier);

    /**
     * Reduces the quantity of the ingredient that is the same as the {@code target} ingredient if it exists.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    void minusIngredientQuantity(Ingredient target, Quantity multiplier);

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<Ingredient> predicate);

    //=========== Order ======================================================================================

    /**
     * Adds the given order.
     * {@code order} must not already exist in the address book.
     */
    void addOrder(Order order);

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     *
     * @param target The order to remove from the orders list.
     * @throws NotFoundException when the given order does not exist in the orders list.
     */
    void deleteOrder(Order target) throws NotFoundException;

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the address book.
     *
     * @param target Order being replaced.
     * @param editedOrder Order to replace with.
     * @throws NotFoundException If target does not exist in currently shown order list.
     */
    void setOrder(Order target, Order editedOrder) throws NotFoundException;

    /** Returns an unmodifiable view of the filtered order list */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    //=========== Recipe ======================================================================================

    /**
     * Adds the given recipe.
     * {@code recipe} must not already exist in the address book.
     */
    void addRecipe(Recipe recipe);

    /**
     * Returns true if an recipe with the same identity as {@code recipe} exists in the address book.
     */
    boolean hasRecipe(Recipe recipe);

    /**
     * Deletes the given recipe.
     * The recipe must exist in the address book.
     *
     * @param target The recipe to remove from the recipes list.
     * @throws NotFoundException when the given recipe does not exist in the recipes list.
     */
    void deleteRecipe(Recipe target) throws NotFoundException;

    /**
     * Replaces the given recipe {@code target} with {@code editedRecipe}.
     * {@code target} must exist in the address book.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the address book.
     *
     * @param target Recipe being replaced.
     * @param editedRecipe Recipe to replace with.
     * @throws NotFoundException If target does not exist in currently shown recipe list.
     */
    void setRecipe(Recipe target, Recipe editedRecipe) throws NotFoundException;

    /** Returns an unmodifiable view of the filtered recipe list */
    ObservableList<Recipe> getFilteredRecipeList();

    /**
     * Updates the filter of the filtered recipe list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecipeList(Predicate<Recipe> predicate);

    //=========== Statistics =================================================================================

    /**
     * Returns the revenue for the past 12 months.
     *
     * @return List containing the month details and the revenue for each month for the past 12 months.
     */
    List<Entry<YearMonth, Double>> getRevenueForPastTwelveMonths();

    /**
     * Returns the top 10 clients with the most orders.
     *
     * @return List containing the top 10 clients with most orders.
     */
    List<Entry<OrderClient, Long>> getTopTenOrderClients();

    /**
     * Returns the top 10 recipes.
     *
     * @return List containing the top 10 recipes.
     */
    List<Entry<GenericString, Long>> getTopTenOrderRecipes();
}
