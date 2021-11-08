package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_BOB;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;
import seedu.address.testutil.ClientBuilder;

public class ClientTest {
    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(AMY.isSameClient(AMY));

        // null -> returns false
        assertFalse(AMY.isSameClient(null));

        // same id, same data fields
        Client copiedAmy = new ClientBuilder(AMY).build();
        assertTrue(AMY.isSameClient(copiedAmy));

        // same id, different data fields
        Client editedAmy = new ClientBuilder(AMY)
                .withName(VALID_NAME_BOB)
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(AMY.isSameClient(editedAmy));

        // different id, same data fields
        editedAmy = new ClientBuilder(AMY).withId(BOB).build();
        assertFalse(AMY.isSameClient(editedAmy));

        // different id, different data fields
        editedAmy = new ClientBuilder(BOB).build();
        assertFalse(AMY.isSameClient(editedAmy));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // same values but different id -> returns false
        Client amyCopy = new ClientBuilder(AMY).withId(BOB).build();
        assertFalse(AMY.equals(amyCopy));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different client -> returns false
        assertFalse(AMY.equals(BOB));

        // different name -> returns false
        Client editedAmy = new ClientBuilder(AMY).withName(VALID_NAME_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new ClientBuilder(AMY).withPhoneNumber(VALID_PHONE_NUMBER_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new ClientBuilder(AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new ClientBuilder(AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(AMY.equals(editedAmy));
    }

    @Test
    public void addOrder_success() {
        Order order = new Order(new Name("product"), new Quantity("1"), LocalDate.MAX);
        AMY.addOrder(order);
        Client editedAmy = new ClientBuilder(AMY)
                .withOrder(new Name("product"), new Quantity("1"), LocalDate.MAX)
                .build();
        assertTrue(AMY.equals(editedAmy));
    }

    @Test
    public void hasOrder_clientHasOrder_returnsTrue() {
        assertTrue(AMY.hasOrder(new Name(VALID_NAME_DAISY)));
    }

    @Test
    public void hasOrder_cannotFindOrder_returnsFalse() {
        assertFalse(AMY.hasOrder(new Name("random product")));
    }

    @Test
    public void removeOrder_cannotFindOrder_returnsNull() {
        assertEquals(null, AMY.removeOrder(new Name("random product")));
    }

    @Test
    public void removeOrder_clientHasOrder_returnsOrderToRemove() {
        assertTrue(AMY.removeOrder(new Name(VALID_NAME_CANNON))
                .equals(new Order(new Name(VALID_NAME_CANNON), new Quantity("1"), LocalDate.MAX)));
    }
}
