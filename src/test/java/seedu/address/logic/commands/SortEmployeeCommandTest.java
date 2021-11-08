package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.getTypicalRhrhEmployees;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Rhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.employee.EmployeeComparator;


public class SortEmployeeCommandTest {

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String LEAVES_DESC = "leaves";
    private static final String SALARY_DESC = "salary";
    private static final String JOB_TITLE_DESC = "job title";
    private static final String ASCENDING_DESC = "ascending";
    private static final String DESCENDING_DESC = "descending";
    private Model model = new ModelManager(getTypicalRhrhEmployees(), new UserPrefs());
    private Rhrh ab = new Rhrh();

    // comparator field is null
    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortEmployeeCommand(null, LEAVES_DESC, ASCENDING_DESC));
    }

    // sort by field is null
    @Test
    public void constructor_nullSortBy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortEmployeeCommand(EmployeeComparator.getSalaryComparator(true), null,
                        ASCENDING_DESC));
    }

    // sorting order is null
    @Test
    public void constructor_nullSortingOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortEmployeeCommand(EmployeeComparator.getJobTitleComparator(true),
                        SALARY_DESC, null));
    }

    // all fields are null
    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortEmployeeCommand(null, null, null));
    }

    // Sort by name in ascending order
    @Test
    public void execute_sortNameAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getNameComparator(true),
                        NAME_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, NAME_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getNameComparator(true));
        System.out.println(expectedModel.getSortableEmployeeList());

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by name in descending order
    @Test
    public void execute_sortNameDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getNameComparator(false),
                        NAME_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, NAME_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getNameComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by address in ascending order
    @Test
    public void execute_sortAddressAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getAddressComparator(true),
                        ADDRESS_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, ADDRESS_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getAddressComparator(true));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by address in descending order
    @Test
    public void execute_sortAddressDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getAddressComparator(false),
                        ADDRESS_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, ADDRESS_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getAddressComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by phone number in ascending order
    @Test
    public void execute_sortPhoneAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getPhoneComparator(true),
                        PHONE_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, PHONE_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getPhoneComparator(true));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by phone number in descending order
    @Test
    public void execute_sortPhoneDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getPhoneComparator(false),
                        PHONE_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, PHONE_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getPhoneComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by email in ascending order
    @Test
    public void execute_sortEmailAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getEmailComparator(true),
                        EMAIL_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, EMAIL_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getEmailComparator(true));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by email in descending order
    @Test
    public void execute_sortEmailDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getEmailComparator(false),
                        EMAIL_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, EMAIL_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getEmailComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by withstanding leaves in ascending order
    @Test
    public void execute_sortLeavesAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getLeavesComparator(true),
                        LEAVES_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, LEAVES_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getLeavesComparator(true));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by withstanding leaves in descending order
    @Test
    public void execute_sortLeavesDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getLeavesComparator(false),
                        LEAVES_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, LEAVES_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getLeavesComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by salary in ascending order
    @Test
    public void execute_sortSalaryAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getSalaryComparator(true),
                        SALARY_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, SALARY_DESC,
                ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getSalaryComparator(true));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by salary in descending order
    @Test
    public void execute_sortSalaryDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getSalaryComparator(false),
                        SALARY_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, SALARY_DESC,
                DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getSalaryComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by job title in ascending order
    @Test
    public void execute_sortJobTitleAscending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getJobTitleComparator(true),
                        JOB_TITLE_DESC, ASCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, JOB_TITLE_DESC,
                        ASCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getJobTitleComparator(true));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    // Sort by job title in descending order
    @Test
    public void execute_sortJobTitleDescending_success() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getJobTitleComparator(false),
                        JOB_TITLE_DESC, DESCENDING_DESC);

        String expectedMessage =
                String.format(seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_SUCCESS, JOB_TITLE_DESC,
                        DESCENDING_DESC);

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getJobTitleComparator(false));

        assertCommandSuccess(sortEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmptyFilteredList_throwsCommandException() {
        SortEmployeeCommand sortEmployeeCommand =
                new SortEmployeeCommand(EmployeeComparator.getSalaryComparator(true),
                        SALARY_DESC, ASCENDING_DESC);
        Model emptyModel = new ModelManager(new Rhrh(), new UserPrefs());
        assertCommandFailure(sortEmployeeCommand, emptyModel,
                seedu.address.logic.commands.SortEmployeeCommand.MESSAGE_EMPTY_FILTERED_LIST);
    }

    @Test
    public void equals() {

        SortEmployeeCommand sortByNameDescending =
                new SortEmployeeCommand(EmployeeComparator.getNameComparator(false),
                        NAME_DESC, DESCENDING_DESC);
        SortEmployeeCommand sortByNameAscending =
                new SortEmployeeCommand(EmployeeComparator.getNameComparator(true),
                        NAME_DESC, ASCENDING_DESC);
        SortEmployeeCommand sortByDeliveryDetailsAscending =
                new SortEmployeeCommand(EmployeeComparator.getLeavesComparator(true),
                        LEAVES_DESC, ASCENDING_DESC);

        // same object -> returns true
        assertTrue(sortByNameDescending.equals(sortByNameDescending));

        // same values -> returns true
        SortEmployeeCommand sortByNameDescendingCopy =
                new SortEmployeeCommand(EmployeeComparator
                        .getNameComparator(false), "name", "descending");
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
