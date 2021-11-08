package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SupplierCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SupplierCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSuppliers.getTypicalRhrhSuppliers;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Rhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.supplier.SupplierComparator;

public class SortSupplierCommandTest {

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String SUPPLY_TYPE_DESC = "supply type";
    private static final String DELIVERY_DETAILS_DESC = "delivery details";
    private static final String ASCENDING_DESC = "ascending";
    private static final String DESCENDING_DESC = "descending";
    private final Model model = new ModelManager(getTypicalRhrhSuppliers(), new UserPrefs());

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortSupplierCommand(null, DELIVERY_DETAILS_DESC, ASCENDING_DESC));
    }

    @Test
    public void constructor_nullSortBy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(true), null,
                        ASCENDING_DESC));
    }

    @Test
    public void constructor_nullSortingOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(true),
                        DELIVERY_DETAILS_DESC, null));
    }

    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortSupplierCommand(null, null, null));
    }

    @Test
    public void execute_sortNameAscending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getNameComparator(true),
                        NAME_DESC, ASCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, NAME_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getNameComparator(true));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortNameDescending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getNameComparator(false),
                        NAME_DESC, DESCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, NAME_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getNameComparator(false));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortAddressAscending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getAddressComparator(true),
                        ADDRESS_DESC, ASCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, ADDRESS_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getAddressComparator(true));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortAddressDescending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getAddressComparator(false),
                        ADDRESS_DESC, DESCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, ADDRESS_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getAddressComparator(false));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortPhoneAscending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getPhoneComparator(true),
                        PHONE_DESC, ASCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, PHONE_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getPhoneComparator(true));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortPhoneDescending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getPhoneComparator(false),
                        PHONE_DESC, DESCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, PHONE_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getPhoneComparator(false));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmailAscending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getEmailComparator(true),
                        EMAIL_DESC, ASCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, EMAIL_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getEmailComparator(true));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmailDescending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getEmailComparator(false),
                        EMAIL_DESC, DESCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, EMAIL_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getEmailComparator(false));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortSupplyTypeAscending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getSupplyTypeComparator(true),
                        SUPPLY_TYPE_DESC, ASCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, SUPPLY_TYPE_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getSupplyTypeComparator(true));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortSupplyTypeDescending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getSupplyTypeComparator(false),
                        SUPPLY_TYPE_DESC, DESCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, SUPPLY_TYPE_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getSupplyTypeComparator(false));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortDeliveryDetailsAscending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(true),
                        DELIVERY_DETAILS_DESC, ASCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, DELIVERY_DETAILS_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getDeliveryDetailsComparator(true));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortDeliveryDetailsDescending_success() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(false),
                        DELIVERY_DETAILS_DESC, DESCENDING_DESC);

        String expectedMessage = String.format(SortSupplierCommand.MESSAGE_SUCCESS, DELIVERY_DETAILS_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getDeliveryDetailsComparator(false));

        assertCommandSuccess(sortSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmptyFilteredList_throwsCommandException() {
        SortSupplierCommand sortSupplierCommand =
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(true),
                        DELIVERY_DETAILS_DESC, ASCENDING_DESC);
        Model emptyModel = new ModelManager(new Rhrh(), new UserPrefs());
        assertCommandFailure(sortSupplierCommand, emptyModel, SortSupplierCommand.MESSAGE_EMPTY_FILTERED_LIST);
    }

    @Test
    public void equals() {

        SortSupplierCommand sortByNameDescending =
                new SortSupplierCommand(SupplierComparator.getNameComparator(false),
                        NAME_DESC, DESCENDING_DESC);
        SortSupplierCommand sortByNameAscending =
                new SortSupplierCommand(SupplierComparator.getNameComparator(true),
                        NAME_DESC, ASCENDING_DESC);
        SortSupplierCommand sortByDeliveryDetailsAscending =
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(true),
                        DELIVERY_DETAILS_DESC, ASCENDING_DESC);

        // same object -> returns true
        assertTrue(sortByNameDescending.equals(sortByNameDescending));

        // same values -> returns true
        SortSupplierCommand sortByNameDescendingCopy =
                new SortSupplierCommand(SupplierComparator.getNameComparator(false), "name", "descending");
        assertTrue(sortByNameDescending.equals(sortByNameDescendingCopy));

        // different types -> returns false
        assertFalse(sortByNameDescending.equals(1));

        // null -> returns false
        assertFalse(sortByNameDescending.equals(null));

        // different sorting type -> returns false
        assertFalse(sortByNameDescending.equals(sortByDeliveryDetailsAscending));

        //different sorting order -> returns false
        assertFalse(sortByNameDescending.equals(sortByNameAscending));
    }

}
