package ay2122s1_cs2103t_w16_2.btbb.model;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_EGG_PRATA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_LAKSA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {
    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        assertTrue(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(addressBook.hasClient(editedAlice));
    }

    @Test
    public void deleteClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeClient(null));
    }

    @Test
    public void deleteClient_clientNotInAddressBook_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> addressBook.removeClient(ALICE));
    }

    @Test
    public void deleteClient_clientInAddressBook_success() throws NotFoundException {
        addressBook.addClient(ALICE);
        addressBook.removeClient(ALICE);
        assertFalse(addressBook.hasClient(ALICE));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasOrder(null));
    }

    @Test
    public void hasOrder_orderNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasOrder(ORDER_FOR_ALICE));
    }

    @Test
    public void hasOrder_orderInAddressBook_returnsTrue() {
        addressBook.addOrder(ORDER_FOR_ALICE);
        assertTrue(addressBook.hasOrder(ORDER_FOR_ALICE));
    }

    @Test
    public void deleteOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeOrder(null));
    }

    @Test
    public void deleteOrder_orderNotInAddressBook_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> addressBook.removeOrder(ORDER_FOR_ALICE));
    }

    @Test
    public void deleteOrder_orderInAddressBook_success() throws NotFoundException {
        addressBook.addOrder(ORDER_FOR_ALICE);
        addressBook.removeOrder(ORDER_FOR_ALICE);
        assertFalse(addressBook.hasOrder(ORDER_FOR_ALICE));
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasIngredient(null));
    }

    @Test
    public void hasIngredient_ingredientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasIngredient(APPLE));
    }

    @Test
    public void hasIngredient_ingredientInAddressBook_returnsTrue() {
        addressBook.addIngredient(APPLE);
        assertTrue(addressBook.hasIngredient(APPLE));
    }

    @Test
    public void hasIngredient_ingredientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addIngredient(APPLE);
        Ingredient editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_QUANTITY_BEEF).build();
        assertTrue(addressBook.hasIngredient(editedApple));
    }

    @Test
    public void addIngredientQuantity_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                addressBook.addIngredientQuantity(null, new Quantity("1")));
    }

    @Test
    public void addIngredientQuantity_nullMultiplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                addressBook.addIngredientQuantity(APPLE, null));
    }

    @Test
    public void addIngredientQuantity_validTargetAndMultiplier_success() {
        addressBook.addIngredient(new IngredientBuilder(BEEF).withQuantity(VALID_QUANTITY_BEEF).build());
        Ingredient target = new IngredientBuilder(BEEF).withQuantity("1").build();
        Ingredient expectedIngredient = new IngredientBuilder(BEEF).withQuantity("32").build();
        addressBook.addIngredientQuantity(target, new Quantity("2"));
        assertTrue(addressBook.hasIngredient(expectedIngredient));
    }

    @Test
    public void minusIngredientQuantity_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> addressBook.minusIngredientQuantity(null, new Quantity("1")));
    }

    @Test
    public void minusIngredientQuantity_nullMultiplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> addressBook.minusIngredientQuantity(APPLE, null));
    }

    @Test
    public void minusIngredientQuantity_validTargetAndMultiplier_success() {
        addressBook.addIngredient(new IngredientBuilder(BEEF).withQuantity(VALID_QUANTITY_BEEF).build());
        Ingredient target = new IngredientBuilder(BEEF).withQuantity("1").build();
        Ingredient expectedIngredient = new IngredientBuilder(BEEF).withQuantity("28").build();
        addressBook.addIngredientQuantity(target, new Quantity("2"));
        assertTrue(addressBook.hasIngredient(expectedIngredient));
    }

    @Test
    public void setOrder_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setOrder(null, ORDER_FOR_ALICE));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setOrder(ORDER_FOR_ALICE, null));
    }

    @Test
    public void setOrder_invalidTarget_throwsNullPointerException() throws NotFoundException {
        assertThrows(NotFoundException.class, () -> addressBook.setOrder(ORDER_FOR_ALICE, ORDER_FOR_AMY));
    }

    @Test
    public void setOrder_validTargetAndEditedOrder_success() throws NotFoundException {
        addressBook.addOrder(ORDER_FOR_ALICE);
        addressBook.setOrder(ORDER_FOR_ALICE, ORDER_FOR_AMY);
        assertFalse(addressBook.hasOrder(ORDER_FOR_ALICE));
        assertTrue(addressBook.hasOrder(ORDER_FOR_AMY));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasRecipe(RECIPE_EGG_PRATA));
    }

    @Test
    public void hasRecipe_recipeInAddressBook_returnsTrue() {
        addressBook.addRecipe(RECIPE_EGG_PRATA);
        assertTrue(addressBook.hasRecipe(RECIPE_EGG_PRATA));
    }

    @Test
    public void deleteRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeRecipe(null));
    }

    @Test
    public void deleteRecipe_recipeNotInAddressBook_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> addressBook.removeRecipe(RECIPE_EGG_PRATA));
    }

    @Test
    public void deleteRecipe_recipeInAddressBook_throwsNotFoundException() throws NotFoundException {
        addressBook.addRecipe(RECIPE_EGG_PRATA);
        addressBook.removeRecipe(RECIPE_EGG_PRATA);
        assertFalse(addressBook.hasRecipe(RECIPE_EGG_PRATA));
    }

    @Test
    public void setRecipe_nullTargetRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setRecipe(null, RECIPE_EGG_PRATA));
    }

    @Test
    public void setRecipe_nullEditedRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setRecipe(RECIPE_EGG_PRATA, null));
    }

    @Test
    public void setRecipe_targetRecipeNotInAddressBook_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> addressBook.setRecipe(RECIPE_EGG_PRATA, RECIPE_LAKSA));
    }

    @Test
    public void setRecipe_targetRecipeInAddressBook_success() throws NotFoundException {
        addressBook.addRecipe(RECIPE_EGG_PRATA);
        addressBook.setRecipe(RECIPE_EGG_PRATA, RECIPE_LAKSA);
        assertFalse(addressBook.hasRecipe(RECIPE_EGG_PRATA));
        assertTrue(addressBook.hasRecipe(RECIPE_LAKSA));
    }

    @Test
    public void getFilteredRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRecipeList().add(RECIPE_EGG_PRATA));
    }

    /**
     * A stub ReadOnlyAddressBook whose clients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Order> orders = FXCollections.observableArrayList();
        private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Order> getOrderList() {
            return orders;
        }

        @Override
        public ObservableList<Recipe> getRecipeList() {
            return recipes;
        }

        @Override
        public ObservableList<Ingredient> getIngredientList() {
            return ingredients;
        }
    }
}
