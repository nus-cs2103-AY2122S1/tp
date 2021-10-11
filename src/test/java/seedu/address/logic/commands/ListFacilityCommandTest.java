package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFacilityAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListFacilityCommand.
 */
public class ListFacilityCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_facilityListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListFacilityCommand(), model, ListFacilityCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_facilityListIsFiltered_showsEverything() {
        showFacilityAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListFacilityCommand(), model, ListFacilityCommand.MESSAGE_SUCCESS, expectedModel);

    }
}
