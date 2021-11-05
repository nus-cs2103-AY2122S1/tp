package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_DAISY;
import static seedu.address.testutil.TypicalProducts.CANNON;
import static seedu.address.testutil.TypicalProducts.DAISY;
import static seedu.address.testutil.TypicalProducts.IPAD;

import org.junit.jupiter.api.Test;

import seedu.address.model.commons.Name;
import seedu.address.testutil.ProductBuilder;

public class ProductTest {
    @Test
    public void isSameProduct() {
        // same object -> returns true
        assertTrue(CANNON.isSameProduct(CANNON));

        // null -> returns false
        assertFalse(CANNON.isSameProduct(null));

        // same id, same data fields
        Product copiedCannon = new ProductBuilder(CANNON).build();
        assertTrue(CANNON.isSameProduct(copiedCannon));

        // same id, different data fields
        Product editedCannon = new ProductBuilder(CANNON)
                .withName(VALID_NAME_DAISY)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .withQuantity(VALID_QUANTITY_DAISY)
                .build();
        assertTrue(CANNON.isSameProduct(editedCannon));

        // different id, same data fields
        editedCannon = new ProductBuilder(CANNON).withId(DAISY).build();
        assertFalse(CANNON.isSameProduct(editedCannon));

        // different id, different data fields
        editedCannon = new ProductBuilder(DAISY).build();
        assertFalse(CANNON.isSameProduct(editedCannon));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CANNON.equals(CANNON));

        // same values but different id -> returns false
        Product cannonCopy = new ProductBuilder(CANNON).withId(DAISY).build();
        assertFalse(CANNON.equals(cannonCopy));

        // null -> returns false
        assertFalse(CANNON.equals(null));

        // different type -> returns false
        assertFalse(CANNON.equals(5));

        // different product -> returns false
        assertFalse(CANNON.equals(IPAD));

        // different name -> returns false
        Product editedCannon = new ProductBuilder(CANNON).withName(VALID_NAME_DAISY).build();
        assertFalse(CANNON.equals(editedCannon));

        // different unit price -> returns false
        editedCannon = new ProductBuilder(CANNON).withUnitPrice(VALID_UNIT_PRICE_DAISY).build();
        assertFalse(CANNON.equals(editedCannon));

        // different quantity -> returns false
        editedCannon = new ProductBuilder(CANNON).withQuantity(VALID_QUANTITY_DAISY).build();
        assertFalse(CANNON.equals(editedCannon));
    }

    @Test
    public void hasEnoughStock_notEnough_returnsFalse() {
        // quantity of the product is null
        Product product = new Product(new Name("productX"), new UnitPrice("1"), null);
        assertFalse(product.hasEnoughStock(new Quantity("1")));
        // stock is not enough
        Product productWithStock = new Product(new Name("productY"), new UnitPrice("1"), new Quantity("1"));
        assertFalse(productWithStock.hasEnoughStock(new Quantity("100")));
    }

    @Test
    public void hasEnoughStock_enough_returnsTrue() {
        Product product = new Product(new Name("productZ"), new UnitPrice("1"), new Quantity("100"));
        assertTrue(product.hasEnoughStock(new Quantity("1")));
        assertTrue(product.hasEnoughStock(new Quantity("100")));
    }
}
