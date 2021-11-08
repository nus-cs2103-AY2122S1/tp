package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Rhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.customer.CustomerComparator;



public class SortCustomerCommandTest {

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String LOYALTY_POINTS_DESC = "loyalty points";
    private static final String ASCENDING_DESC = "ascending";
    private static final String DESCENDING_DESC = "descending";
    private Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());
    private Rhrh ab = new Rhrh();

    // comparator field is null
    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortCustomerCommand(null, LOYALTY_POINTS_DESC, ASCENDING_DESC));
    }

    // sort by field is null
    @Test
    public void constructor_nullSortBy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortCustomerCommand(CustomerComparator.getLoyaltyPointsComparator(true), null,
                        ASCENDING_DESC));
    }

    // sorting order is null
    @Test
    public void constructor_nullSortingOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortCustomerCommand(CustomerComparator.getLoyaltyPointsComparator(true),
                        LOYALTY_POINTS_DESC, null));
    }

    // all fields are null
    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCustomerCommand(null, null, null));
    }

    // Sort by name in ascending order
    @Test
    public void execute_sortNameAscending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getNameComparator(true),
                        NAME_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, NAME_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getNameComparator(true));
        System.out.println(expectedModel.getSortableCustomerList());

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by name in descending order
    @Test
    public void execute_sortNameDescending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getNameComparator(false),
                        NAME_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, NAME_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getNameComparator(false));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by address in ascending order
    @Test
    public void execute_sortAddressAscending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getAddressComparator(true),
                        ADDRESS_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, ADDRESS_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getAddressComparator(true));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by address in descending order
    @Test
    public void execute_sortAddressDescending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getAddressComparator(false),
                        ADDRESS_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, ADDRESS_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getAddressComparator(false));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by phone number in ascending order
    @Test
    public void execute_sortPhoneAscending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getPhoneComparator(true),
                        PHONE_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, PHONE_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getPhoneComparator(true));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by phone number in descending order
    @Test
    public void execute_sortPhoneDescending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getPhoneComparator(false),
                        PHONE_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, PHONE_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getPhoneComparator(false));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by email in ascending order
    @Test
    public void execute_sortEmailAscending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getEmailComparator(true),
                        EMAIL_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, EMAIL_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getEmailComparator(true));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by email in descending order
    @Test
    public void execute_sortEmailDescending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getEmailComparator(false),
                        EMAIL_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, EMAIL_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getEmailComparator(false));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by withstanding LoyaltyPoints in ascending order
    @Test
    public void execute_sortLoyaltyPointsAscending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getLoyaltyPointsComparator(true),
                        LOYALTY_POINTS_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, LOYALTY_POINTS_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getLoyaltyPointsComparator(true));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    // Sort by withstanding LoyaltyPoints in descending order
    @Test
    public void execute_sortLoyaltyPointsDescending_success() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getLoyaltyPointsComparator(false),
                        LOYALTY_POINTS_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(SortCustomerCommand.MESSAGE_SUCCESS, LOYALTY_POINTS_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getLoyaltyPointsComparator(false));

        assertCommandSuccess(sortCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmptyFilteredList_throwsCommandException() {
        SortCustomerCommand sortCustomerCommand =
                new SortCustomerCommand(CustomerComparator.getLoyaltyPointsComparator(true),
                        LOYALTY_POINTS_DESC, ASCENDING_DESC);
        Model emptyModel = new ModelManager(new Rhrh(), new UserPrefs());
        assertCommandFailure(sortCustomerCommand, emptyModel,
                SortCustomerCommand.MESSAGE_EMPTY_FILTERED_LIST);
    }

    @Test
    public void equals() {

        SortCustomerCommand sortByNameDescending =
                new SortCustomerCommand(CustomerComparator.getNameComparator(false),
                        NAME_DESC, DESCENDING_DESC);
        SortCustomerCommand sortByNameAscending =
                new SortCustomerCommand(CustomerComparator.getNameComparator(true),
                        NAME_DESC, ASCENDING_DESC);
        SortCustomerCommand sortByLoyaltyPointsAscending =
                new SortCustomerCommand(CustomerComparator.getLoyaltyPointsComparator(true),
                        LOYALTY_POINTS_DESC, ASCENDING_DESC);

        // same object -> returns true
        assertTrue(sortByNameDescending.equals(sortByNameDescending));

        // same values -> returns true
        SortCustomerCommand sortByNameDescendingCopy =
                new SortCustomerCommand(CustomerComparator
                        .getNameComparator(false), "name", "descending");
        assertTrue(sortByNameDescending.equals(sortByNameDescendingCopy));

        // different types -> returns false
        assertFalse(sortByNameDescending.equals(1));

        // null -> returns false
        assertFalse(sortByNameDescending.equals(null));

        // different sorting type -> returns false
        assertFalse(sortByNameDescending.equals(sortByLoyaltyPointsAscending));

        //different sorting order -> returns false
        assertFalse(sortByNameDescending.equals(sortByNameAscending));
    }

}
