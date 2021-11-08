package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {
    private static final String DEFAULT_CLIENT_NAME = "Amy Bee";
    private static final String DEFAULT_CLIENT_PHONE = "85355255";
    private static final String DEFAULT_CLIENT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final String DEFAULT_RECIPE_NAME = "Egg Prata";
    private static final List<Ingredient> DEFAULT_RECIPE_INGREDIENT_LIST = List.of(
            new Ingredient(new GenericString("Flour"), new Quantity("200"), new GenericString("grams")),
            new Ingredient(new GenericString("Eggs"), new Quantity("1"), new GenericString("whole")));
    private static final String DEFAULT_ORDER_PRICE = "1.50";
    private static final String DEFAULT_ORDER_DEADLINE = "12-12-2021 1500";
    private static final String DEFAULT_ORDER_QUANTITY = "1";
    private static final String DEFAULT_ORDER_COMPLETION_STATUS = "no";

    private GenericString clientName;
    private Phone clientPhone;
    private Address clientAddress;
    private GenericString recipeName;
    private RecipeIngredientList recipeIngredients;
    private OrderPrice orderPrice;
    private Deadline deadline;
    private Quantity quantity;
    private CompletionStatus completionStatus;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        clientName = new GenericString(DEFAULT_CLIENT_NAME);
        clientPhone = new Phone(DEFAULT_CLIENT_PHONE);
        clientAddress = new Address(DEFAULT_CLIENT_ADDRESS);
        recipeName = new GenericString(DEFAULT_RECIPE_NAME);
        recipeIngredients = new RecipeIngredientList(DEFAULT_RECIPE_INGREDIENT_LIST);
        orderPrice = new OrderPrice(DEFAULT_ORDER_PRICE);
        deadline = new Deadline(DEFAULT_ORDER_DEADLINE);
        quantity = new Quantity(DEFAULT_ORDER_QUANTITY);
        completionStatus = new CompletionStatus(DEFAULT_ORDER_COMPLETION_STATUS);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     *
     * @param orderToCopy The data from which to copy from to create a new order.
     */
    public OrderBuilder(Order orderToCopy) {
        clientName = orderToCopy.getClientName();
        clientPhone = orderToCopy.getClientPhone();
        clientAddress = orderToCopy.getClientAddress();
        recipeName = orderToCopy.getRecipeName();
        recipeIngredients = orderToCopy.getRecipeIngredients();
        orderPrice = orderToCopy.getOrderPrice();
        deadline = orderToCopy.getDeadline();
        quantity = orderToCopy.getQuantity();
        completionStatus = orderToCopy.getCompletionStatus();
    }

    /**
     * Sets the {@code clientName} of the {@code OrderBuilder} that we are building.
     *
     * @param clientName The client's name associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withClientName(String clientName) {
        this.clientName = new GenericString(clientName);
        return this;
    }

    /**
     * Sets the {@code clientPhone} of the {@code OrderBuilder} that we are building.
     *
     * @param clientPhone The client's phone associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withClientPhone(String clientPhone) {
        this.clientPhone = new Phone(clientPhone);
        return this;
    }

    /**
     * Sets the {@code clientAddress} of the {@code OrderBuilder} that we are building.
     *
     * @param clientAddress The client's address associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withClientAddress(String clientAddress) {
        this.clientAddress = new Address(clientAddress);
        return this;
    }

    /**
     * Sets the {@code recipeName} of the {@code OrderBuilder} that we are building.
     *
     * @param recipeName The order's recipe name associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withRecipeName(String recipeName) {
        this.recipeName = new GenericString(recipeName);
        return this;
    }

    /**
     * Sets the {@code recipeIngredients} of the {@code OrderBuilder} that we are building.
     *
     * @param recipeIngredients The order recipe ingredients associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withRecipeIngredients(RecipeIngredientList recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
        return this;
    }

    /**
     * Sets the {@code orderPrice} of the {@code OrderBuilder} that we are building.
     *
     * @param orderPrice The order orderPrice associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withOrderPrice(String orderPrice) {
        this.orderPrice = new OrderPrice(orderPrice);
        return this;
    }

    /**
     * Sets the {@code deadline} of the {@code OrderBuilder} that we are building.
     *
     * @param deadline The order deadline associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code quantity} of the {@code OrderBuilder} that we are building.
     *
     * @param quantity The order quantity associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code quantity} of the {@code OrderBuilder} that we are building.
     *
     * @param completionStatus The order quantity associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withCompletionStatus(String completionStatus) {
        this.completionStatus = new CompletionStatus(completionStatus);
        return this;
    }

    /**
     * Builds the order.
     *
     * @return An order object.
     */
    public Order build() {
        return new Order(clientName, clientPhone, clientAddress,
                recipeName, recipeIngredients, orderPrice, deadline, quantity, completionStatus);
    }
}
