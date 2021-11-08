package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT_PRICE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProducts.CANNON;
import static seedu.address.testutil.TypicalProducts.DAISY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.logic.commands.EditProductCommand.EditProductDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientContainsKeywordsPredicate;
import seedu.address.model.product.Product;
import seedu.address.model.product.ProductContainsKeywordsPredicate;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.EditProductDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_NUMBER_AMY = "11111111";
    public static final String VALID_PHONE_NUMBER_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ORDER_ONE = CANNON.getName() + " 1 1/1";
    public static final String VALID_ORDER_TWO = DAISY.getName() + " 455 2020/12/31";

    public static final String VALID_NAME_CANNON = "Cannon";
    public static final String VALID_NAME_DAISY = "Daisy";
    public static final String VALID_UNIT_PRICE_CANNON = "1";
    public static final String VALID_UNIT_PRICE_DAISY = "4";
    public static final String VALID_QUANTITY_CANNON = "1";
    public static final String VALID_QUANTITY_DAISY = "456";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_NUMBER_DESC_AMY = " " + PREFIX_PHONE_NUMBER + VALID_PHONE_NUMBER_AMY;
    public static final String PHONE_NUMBER_DESC_BOB = " " + PREFIX_PHONE_NUMBER + VALID_PHONE_NUMBER_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ORDER_DESC_ONE = " " + PREFIX_ORDER + CANNON.getId() + " 1 1/1";
    public static final String ORDER_DESC_TWO = " " + PREFIX_ORDER + DAISY.getId() + " 455 2020/12/31";

    public static final String NAME_DESC_CANNON = " " + PREFIX_NAME + VALID_NAME_CANNON;
    public static final String NAME_DESC_DAISY = " " + PREFIX_NAME + VALID_NAME_DAISY;
    public static final String UNIT_PRICE_DESC_CANNON = " " + PREFIX_UNIT_PRICE + VALID_UNIT_PRICE_CANNON;
    public static final String UNIT_PRICE_DESC_DAISY = " " + PREFIX_UNIT_PRICE + VALID_UNIT_PRICE_DAISY;
    public static final String QUANTITY_DESC_CANNON = " " + PREFIX_QUANTITY + VALID_QUANTITY_CANNON;
    public static final String QUANTITY_DESC_DAISY = " " + PREFIX_QUANTITY + VALID_QUANTITY_DAISY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_NUMBER_DESC = " " + PREFIX_PHONE_NUMBER + "911a"; // 'a' not allowed in pn
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ORDER_DESC = " " + PREFIX_ORDER + "1"; // order needs id, quantity and time
    public static final String INVALID_UNIT_PRICE_DESC = " " + PREFIX_UNIT_PRICE + "2s"; // 's' not allowed for prices
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "3f"; // 'f' not allowed for quantities

    public static final EditClientDescriptor DESC_AMY;
    public static final EditClientDescriptor DESC_BOB;
    public static final EditProductDescriptor DESC_CANNON;
    public static final EditProductDescriptor DESC_DAISY;

    static {
        DESC_AMY = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhoneNumber(VALID_PHONE_NUMBER_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withOrders(VALID_ORDER_ONE)
                .build();
        DESC_BOB = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withOrders(VALID_ORDER_TWO)
                .build();

        DESC_CANNON = new EditProductDescriptorBuilder()
                .withName(VALID_NAME_CANNON)
                .withUnitPrice(VALID_UNIT_PRICE_CANNON)
                .withQuantity(VALID_QUANTITY_CANNON)
                .build();
        DESC_DAISY = new EditProductDescriptorBuilder()
                .withName(VALID_NAME_DAISY)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .withQuantity(VALID_QUANTITY_DAISY)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(
            Command command, Model actualModel, CommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(
            Command command, Model actualModel, String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     * - the address book, filtered product list and selected product in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredClientList = new ArrayList<>(actualModel.getFilteredClientList());
        List<Product> expectedFilteredProductList = new ArrayList<>(actualModel.getFilteredProductList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredClientList, actualModel.getFilteredClientList());
        assertEquals(expectedFilteredProductList, actualModel.getFilteredProductList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new ClientContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the product at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showProductAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProductList().size());

        Product product = model.getFilteredProductList().get(targetIndex.getZeroBased());
        final String[] splitName = product.getName().fullName.split("\\s+");
        model.updateFilteredProductList(new ProductContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProductList().size());
    }
}
