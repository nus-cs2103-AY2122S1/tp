package seedu.address.model.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class IdTest {
    @Test
    public void constructor_success() {
        int expectedClient = ID.getNewClientID().getId() + 1;
        int actualClient = ID.getNewClientID().getId();
        assertEquals(expectedClient, actualClient);

        String expectedProduct = Integer.toString(ID.getNewProductID().getId() + 1);
        String actualProduct = ID.getNewProductID().toString();
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void equals() {
        ID idClient = ID.getNewClientID();
        ID idProduct = ID.getNewProductID();

        // same object -> returns true
        assertEquals(idClient, idClient);
        assertEquals(idProduct, idProduct);

        // different id -> returns false
        assertNotEquals(idClient, ID.getNewClientID());
        assertNotEquals(idProduct, ID.getNewProductID());
    }
}
