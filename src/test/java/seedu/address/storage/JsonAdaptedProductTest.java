package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.storage.JsonAdaptedProduct.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProducts.CANNON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

public class JsonAdaptedProductTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_UNIT_PRICE = "+651234";
    private static final String INVALID_QUANTITY = " ";

    private static final String VALID_NAME = CANNON.getName().toString();
    private static final String VALID_UNIT_PRICE = CANNON.getUnitPrice().toString();
    private static final String VALID_QUANTITY = CANNON.getQuantity().toString();

    @Test
    public void toModelType_validProductDetails_returnsProduct() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct(CANNON);
        assertEquals(CANNON, Product.updateProduct(CANNON, product.toModelType()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(INVALID_NAME, VALID_UNIT_PRICE, VALID_QUANTITY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProduct product = new JsonAdaptedProduct(null, VALID_UNIT_PRICE, VALID_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidUnitPrice_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, INVALID_UNIT_PRICE, VALID_QUANTITY);
        String expectedMessage = UnitPrice.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullUnitPrice_throwsIllegalValueException() {
        JsonAdaptedProduct product = new JsonAdaptedProduct(VALID_NAME, null, VALID_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_UNIT_PRICE, INVALID_QUANTITY);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_success() throws IllegalValueException {
        JsonAdaptedProduct product = new JsonAdaptedProduct(VALID_NAME, VALID_UNIT_PRICE, null);
        assertNull(product.toModelType().getQuantity());
    }
}
