package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIAL_REQUESTS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Rhrh;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.CustomerClassContainsKeywordsPredicate;
import seedu.address.testutil.EditCustomerDescriptorBuilder;


/**
 * Contains helper methods for testing Customer commands.
 */
public class CustomerCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_LP_AMY = "5000";
    public static final String VALID_LP_BOB = "9000";
    public static final String VALID_ALLERGY_NONSENSE = "Nonsense";
    public static final String VALID_ALLERGY_ALMONDS = "Almonds";
    public static final String VALID_ALLERGY_GRAPEFRUITS = "Grapefruit";
    public static final String VALID_SPECIAL_REQUEST_OFF_LIGHTS = "off lights";
    public static final String VALID_SPECIAL_REQUEST_SILENCE = "Silence";
    public static final String VALID_SPECIAL_REQUEST_ROCK = "Rock music";
    public static final String VALID_SPECIAL_REQUEST_LIVE_BAND = "Live band";
    public static final String VALID_SORT_BY_LOYALTY_POINTS = "lp";

    public static final String ALLERGY_DESC_NONSENSE = " " + PREFIX_ALLERGIES + VALID_ALLERGY_NONSENSE;
    public static final String ALLERGY_DESC_ALMONDS = " " + PREFIX_ALLERGIES + VALID_ALLERGY_ALMONDS;
    public static final String ALLERGY_DESC_GRAPEFRUITS = " " + PREFIX_ALLERGIES + VALID_ALLERGY_GRAPEFRUITS;
    public static final String SPECIAL_REQUEST_DESC_LIVE_BAND = " " + PREFIX_SPECIAL_REQUESTS
            + VALID_SPECIAL_REQUEST_LIVE_BAND;
    public static final String SORT_BY_LOYALTY_POINTS_DESC =
            " " + PREFIX_SORT_BY + VALID_SORT_BY_LOYALTY_POINTS;

    public static final String LP_DESC_AMY = " " + PREFIX_LP + VALID_LP_AMY;
    public static final String LP_DESC_BOB = " " + PREFIX_LP + VALID_LP_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    // points
    public static final String INVALID_ALLERGIES_DESC = " " + PREFIX_ALLERGIES + "!vvdv"; // '!' not allowed
    // symbol
    public static final String INVALID_SPECIAL_REQUESTS_DESC = " " + PREFIX_SPECIAL_REQUESTS + "!vdfv"; // '!'
    //only "n", "a", "p", "e", "st", and "dd" are allowed for sort by
    public static final String INVALID_SORT_BY_DESC = " " + PREFIX_SORT_BY + "t";
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_CUSTOMER_AMY;
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_CUSTOMER_BOB;

    static {
        DESC_CUSTOMER_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withLoyaltyPoints(VALID_LP_AMY)
                .withAllergies(VALID_ALLERGY_GRAPEFRUITS, VALID_ALLERGY_NONSENSE)
                .withSpecialRequests(VALID_SPECIAL_REQUEST_LIVE_BAND, VALID_SPECIAL_REQUEST_ROCK)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_CUSTOMER_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withLoyaltyPoints(VALID_LP_BOB)
                .withAllergies(VALID_ALLERGY_ALMONDS).withSpecialRequests(VALID_SPECIAL_REQUEST_OFF_LIGHTS)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
                false, false, true, false, false, false);
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
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRhrh, actualModel.getRhrh());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the Customer at the given {@code targetIndex} in the
     * {@code model}'s RHRH.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new CustomerClassContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }
}
