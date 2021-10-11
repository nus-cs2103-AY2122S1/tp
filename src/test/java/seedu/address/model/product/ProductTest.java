package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_DAISY;
import static seedu.address.testutil.TypicalProducts.IPAD;
import static seedu.address.testutil.TypicalProducts.IPHONE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProductBuilder;

public class ProductTest {
    @Test
    public void isSameProduct() {
        // same object -> returns true
        assertTrue(IPHONE.isSameProduct(IPHONE));

        // null -> returns false
        assertFalse(IPHONE.isSameProduct(null));

        //Todo: testing same id, all other attributes different -> returns true

        // same name, all other attributes different -> returns false
        Product editedIphone = new ProductBuilder(IPHONE).withUnitPrice(VALID_UNIT_PRICE_CANNON)
                .withQuantity(VALID_QUANTITY_CANNON).build();
        assertFalse(IPHONE.isSameProduct(editedIphone));

        // different name, all other attributes same -> returns false
        editedIphone = new ProductBuilder(IPHONE).withName(VALID_NAME_CANNON).build();
        assertFalse(IPHONE.isSameProduct(editedIphone));

        // name differs in case, all other attributes same -> returns false
        Product editedIpad = new ProductBuilder(IPAD).withName(VALID_NAME_CANNON.toLowerCase()).build();
        assertFalse(IPAD.isSameProduct(editedIpad));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_CANNON + " ";
        editedIpad = new ProductBuilder(IPAD).withName(nameWithTrailingSpaces).build();
        assertFalse(IPAD.isSameProduct(editedIpad));
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(IPHONE.equals(IPHONE));

        // same values but different id -> returns false
        Product iphoneCopy = new ProductBuilder(IPHONE).build();
        assertFalse(IPHONE.equals(iphoneCopy));

        // null -> returns false
        assertFalse(IPHONE.equals(null));

        // different type -> returns false
        assertFalse(IPHONE.equals(5));

        // different person -> returns false
        assertFalse(IPHONE.equals(IPAD));

        // different name -> returns false
        Product editedIphone = new ProductBuilder(IPHONE).withName(VALID_NAME_DAISY).build();
        assertFalse(IPHONE.equals(editedIphone));

        // different unit price -> returns false
        editedIphone = new ProductBuilder(IPHONE).withUnitPrice(VALID_UNIT_PRICE_DAISY).build();
        assertFalse(IPHONE.equals(editedIphone));

        // different quantity -> returns false
        editedIphone = new ProductBuilder(IPHONE).withQuantity(VALID_QUANTITY_DAISY).build();
        assertFalse(IPHONE.equals(editedIphone));


    }
}
