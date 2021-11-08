package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLY_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Rhrh;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;
import seedu.address.testutil.EditSupplierDescriptorBuilder;

/**
 * Contains helper methods for testing Supplier commands.
 */
public class SupplierCommandTestUtil {

    public static final String VALID_SUPPLY_TYPE_BEEF = "Beef";
    public static final String VALID_SUPPLY_TYPE_CHICKEN = "Chicken";
    public static final String VALID_SORT_BY_DELIVERY_DETAILS = "dd";
    public static final String VALID_SORT_BY_SUPPLY_TYPE = "st";
    public static final String VALID_DELIVERY_DETAIL_AMY = "2021-12-25 17:00";
    public static final String VALID_DELIVERY_DETAIL_BOB = "1100 26-12-2021";

    public static final String SUPPLY_TYPE_DESC_AMY = " " + PREFIX_SUPPLY_TYPE + VALID_SUPPLY_TYPE_CHICKEN;
    public static final String SUPPLY_TYPE_DESC_BOB = " " + PREFIX_SUPPLY_TYPE + VALID_SUPPLY_TYPE_BEEF;
    public static final String DELIVERY_DETAILS_DESC_AMY = " " + PREFIX_DELIVERY_DETAILS + VALID_DELIVERY_DETAIL_AMY;
    public static final String DELIVERY_DETAILS_DESC_BOB = " " + PREFIX_DELIVERY_DETAILS + VALID_DELIVERY_DETAIL_BOB;
    public static final String SORT_BY_DELIVERY_DETAILS_DESC = " " + PREFIX_SORT_BY + VALID_SORT_BY_DELIVERY_DETAILS;
    public static final String SORT_BY_SUPPLY_TYPE_DESC = " " + PREFIX_SORT_BY + VALID_SORT_BY_SUPPLY_TYPE;

    // & not allowed in supply types
    public static final String INVALID_SUPPLY_TYPE_DESC = " " + PREFIX_SUPPLY_TYPE + "Chicken & Beef";
    // Not a valid parsable date time
    public static final String INVALID_DELIVERY_DETAILS_DESC = " " + PREFIX_DELIVERY_DETAILS + "Everyday @ 6pm";
    //only "n", "a", "p", "e", "st", and "dd" are allowed for sort by
    public static final String INVALID_SORT_BY_DESC = " " + PREFIX_SORT_BY + "t";

    public static final EditSupplierCommand.EditSupplierDescriptor DESC_AMY;
    public static final EditSupplierCommand.EditSupplierDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditSupplierDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withSupplyType(VALID_SUPPLY_TYPE_CHICKEN)
                .withDeliveryDetails(VALID_DELIVERY_DETAIL_AMY).build();
        DESC_BOB = new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withSupplyType(VALID_SUPPLY_TYPE_BEEF)
                .withDeliveryDetails(VALID_DELIVERY_DETAIL_BOB).build();
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
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
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, false, false, true, false);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - RHRH, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Rhrh expectedRhrh = new Rhrh(actualModel.getRhrh());
        List<Supplier> expectedFilteredList = new ArrayList<>(actualModel.getFilteredSupplierList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRhrh, actualModel.getRhrh());
        assertEquals(expectedFilteredList, actualModel.getFilteredSupplierList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the supplier at the given {@code targetIndex} in the
     * {@code model}'s RHRH.
     */
    public static void showSupplierAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredSupplierList().size());

        Supplier supplier = model.getFilteredSupplierList().get(targetIndex.getZeroBased());
        final String[] splitName = supplier.getName().fullName.split("\\s+");
        model.updateFilteredSupplierList(new SupplierClassContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredSupplierList().size());
    }
}
