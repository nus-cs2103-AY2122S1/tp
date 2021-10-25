package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_DAISY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.product.Product;

/**
 * A utility class containing a list of {@code Product} objects to be used in tests.
 */
public class TypicalProducts {
    public static final Product IPHONE = new ProductBuilder().withName("IPhone 13")
            .withUnitPrice("1").withQuantity("1")
            .build();
    public static final Product IPAD = new ProductBuilder().withName("IPad Pro")
            .withUnitPrice("1").withQuantity("5")
            .build();
    public static final Product AIRPODS = new ProductBuilder().withName("Air Pods Pro")
            .withUnitPrice("200").withQuantity("20")
            .build();
    public static final Product MACBOOK = new ProductBuilder().withName("MacBook Pro")
            .withUnitPrice("2500").withQuantity("15")
            .build();
    public static final Product KEYBOARD = new ProductBuilder().withName("Mechanical Keyboard")
            .withUnitPrice("250").withQuantity("30")
            .build();
    public static final Product PANADOL = new ProductBuilder().withName("Panadol")
            .withUnitPrice("8").withQuantity("50")
            .build();
    public static final Product MASK = new ProductBuilder().withName("Mask")
            .withUnitPrice("0").withQuantity("1000")
            .build();

    // Manually added
    public static final Product CALCULATOR = new ProductBuilder().withName("Scientific Calculator")
            .withUnitPrice("20").withQuantity("45")
            .build();
    public static final Product TISSUE = new ProductBuilder().withName("Facial Tissue")
            .withUnitPrice("0").withQuantity("400")
            .build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Product CANNON = new ProductBuilder().withName(VALID_NAME_CANNON)
            .withUnitPrice(VALID_UNIT_PRICE_CANNON).withQuantity(VALID_QUANTITY_CANNON)
            .build();
    public static final Product DAISY = new ProductBuilder().withName(VALID_NAME_DAISY)
            .withUnitPrice(VALID_UNIT_PRICE_DAISY).withQuantity(VALID_QUANTITY_DAISY)
            .build();

    public static final String KEYWORD_MATCHING_CALCULATOR = "Calculator"; // A keyword that matches MEIER

    private TypicalProducts() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical products.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Product product : getTypicalProducts()) {
            ab.addProduct(product);
        }
        return ab;
    }

    public static List<Product> getTypicalProducts() {
        return new ArrayList<>(Arrays.asList(IPHONE, IPAD, AIRPODS, MACBOOK, KEYBOARD, PANADOL, MASK));
    }
}
