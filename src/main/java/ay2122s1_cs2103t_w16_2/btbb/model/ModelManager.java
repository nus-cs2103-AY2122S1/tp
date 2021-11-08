package ay2122s1_cs2103t_w16_2.btbb.model;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.GuiSettings;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Ingredient> filteredIngredients;
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<Recipe> filteredRecipes;
    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        filteredIngredients = new FilteredList<>(this.addressBook.getIngredientList());
        filteredOrders = new FilteredList<>(this.addressBook.getOrderList());
        filteredRecipes = new FilteredList<>(this.addressBook.getRecipeList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== Client ====================================================================================

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) throws NotFoundException {
        addressBook.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) throws NotFoundException {
        requireAllNonNull(target, editedClient);

        addressBook.setClient(target, editedClient);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    //=========== Ingredient ======================================================================================

    /**
     * Implements addIngredient method.
     * {@code ingredient} must not already exist in the address book.
     *
     * @param ingredient to add.
     */
    @Override
    public void addIngredient(Ingredient ingredient) {
        addressBook.addIngredient(ingredient);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    /**
     * Implements hasIngredient method.
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the address book.
     *
     * @param ingredient to check.
     */
    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return addressBook.hasIngredient(ingredient);
    }

    /**
     * Implements deleteIngredient method.
     * Deletes the given ingredient from the address book. The given ingredient
     * must exist in the address book.
     *
     * @param target The ingredient to delete.
     * @throws NotFoundException when the ingredient given does not exist in the address book.
     */
    @Override
    public void deleteIngredient(Ingredient target) throws NotFoundException {
        requireNonNull(target);
        addressBook.removeIngredient(target);
    }

    /**
     * Implements setIngredient method.
     * Replaces the existing Ingredient in the address book with an edited Ingredient.
     *
     * @param target The target ingredient to replace.
     * @param editedIngredient The edited ingredient to replace with.
     * @throws NotFoundException if the target ingredient was not found in the address book.
     */
    @Override
    public void setIngredient(Ingredient target, Ingredient editedIngredient) throws NotFoundException {
        requireAllNonNull(target, editedIngredient);
        addressBook.setIngredient(target, editedIngredient);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Ingredient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return filteredIngredients;
    }

    /**
     * Implements addIngredientQuantity method.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    @Override
    public void addIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);
        addressBook.addIngredientQuantity(target, multiplier);
    }

    /**
     * Implements minusIngredientQuantity method.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    @Override
    public void minusIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);
        addressBook.minusIngredientQuantity(target, multiplier);
    }

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     *
     * @param predicate given filter to filter list by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
        requireNonNull(predicate);
        filteredIngredients.setPredicate(predicate);
    }

    //=========== Order ====================================================================================

    @Override
    public void addOrder(Order order) {
        addressBook.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return addressBook.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) throws NotFoundException {
        requireNonNull(target);
        addressBook.removeOrder(target);
    }

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the address book.
     *
     * @param target Order being replaced.
     * @param editedOrder Order to replace with.
     * @throws NotFoundException If target does not exist in currently shown order list.
     */
    @Override
    public void setOrder(Order target, Order editedOrder) throws NotFoundException {
        requireAllNonNull(target, editedOrder);
        addressBook.setOrder(target, editedOrder);
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    //=========== Recipe ====================================================================================

    @Override
    public void addRecipe(Recipe recipe) {
        addressBook.addRecipe(recipe);
        updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return addressBook.hasRecipe(recipe);
    }

    @Override
    public void deleteRecipe(Recipe target) throws NotFoundException {
        requireNonNull(target);
        addressBook.removeRecipe(target);
    }

    /**
     * Replaces the given recipe {@code target} with {@code editedRecipe}.
     * {@code target} must exist in the address book.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the address book.
     *
     * @param target Recipe being replaced.
     * @param editedRecipe Recipe to replace with.
     * @throws NotFoundException
     */
    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) throws NotFoundException {
        requireAllNonNull(target, editedRecipe);
        addressBook.setRecipe(target, editedRecipe);
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return filteredRecipes;
    }

    @Override
    public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
        requireNonNull(predicate);
        filteredRecipes.setPredicate(predicate);
    }

    //=========== Statistics ===============================================================================

    @Override
    public List<Entry<YearMonth, Double>> getRevenueForPastTwelveMonths() {
        return addressBook.getRevenueForPastTwelveMonths();
    }

    @Override
    public List<Entry<OrderClient, Long>> getTopTenOrderClients() {
        return addressBook.getTopTenOrderClients();
    }

    /**
     * Returns the top 10 recipes.
     *
     * @return List containing the top 10 recipes.
     */
    @Override
    public List<Entry<GenericString, Long>> getTopTenOrderRecipes() {
        return addressBook.getTopTenOrderRecipes();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && filteredOrders.equals(other.filteredOrders)
                && filteredIngredients.equals(other.filteredIngredients);
    }
}
