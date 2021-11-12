package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_CALLED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_5;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.comparators.PersonComparator.MESSAGE_INVALID_CATEGORY;
import static seedu.address.model.ModelManager.MESSAGE_INVALID_LIMIT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.comparators.CalledComparator;
import seedu.address.logic.comparators.exceptions.ComparatorException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.category.Category;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Category validCategoryCalled = new Category(VALID_CATEGORY_CALLED);
    private Category validCategoryGender = new Category(VALID_CATEGORY_GENDER);
    private Integer invalidCount = Integer.valueOf(INVALID_COUNT);
    private Integer validCountThree = Integer.valueOf(VALID_COUNT_3);
    private Integer validCountFive = Integer.valueOf(VALID_COUNT_5);

    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterCommand(null, validCountThree));
    }

    @Test
    public void constructor_nullCount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterCommand(validCategoryCalled, null));
    }

    @Test
    public void execute_validCategoryAndCount_filterSuccessful() throws Exception {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.sortFilteredPersonList(new CalledComparator());
        expectedModel.limitFilteredPersonList(3);

        assertCommandSuccess(new FilterCommand(validCategoryCalled, validCountThree),
                model, String.format(MESSAGE_SUCCESS, "[CALLED]"), expectedModel);
    }

    @Test
    public void execute_invalidCategoryValidCount_throwsComparatorException() {
        FilterCommand filterCommand = new FilterCommand(new Category("Phone"), validCountThree);
        assertThrows(ComparatorException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_INVALID_CATEGORY + FilterCommand.MESSAGE_USAGE), () -> filterCommand.execute(model));
    }

    @Test
    public void execute_validCategoryInvalidCount_throwsIndexOutOfBoundsException() {
        FilterCommand filterCommand = new FilterCommand(validCategoryCalled, invalidCount);
        assertThrows(IndexOutOfBoundsException.class, MESSAGE_INVALID_LIMIT, () -> filterCommand.execute(model));
    }


    @Test
    public void equals() {
        final FilterCommand standardCommand = new FilterCommand(validCategoryCalled, validCountThree);

        // same values -> returns true
        FilterCommand commandWithSameValues = new FilterCommand(validCategoryCalled, validCountThree);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different category -> returns false
        assertFalse(standardCommand.equals(new FilterCommand(validCategoryGender, validCountThree)));

        // different count -> returns false
        assertFalse(standardCommand.equals(new FilterCommand(validCategoryCalled, validCountFive)));
    }
}
