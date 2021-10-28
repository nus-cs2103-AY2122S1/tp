package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilterInterviewFutureCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterInterviewFutureCommand firstFilterFutureInterviewCommand = new FilterInterviewFutureCommand();
        FilterInterviewFutureCommand secondFilterFutureInterviewCommand = new FilterInterviewFutureCommand();

        // same object -> returns true
        assertEquals(firstFilterFutureInterviewCommand, firstFilterFutureInterviewCommand);

        // same values -> returns true
        assertEquals(firstFilterFutureInterviewCommand, secondFilterFutureInterviewCommand);

        // different types -> returns false
        assertNotEquals(firstFilterFutureInterviewCommand, 1);

        // null -> returns false
        assertNotEquals(firstFilterFutureInterviewCommand, null);
    }

    @Test
    public void execute_modelUnfilteredList_success() {

        FilterInterviewFutureCommand filterInterviewFutureCommand = new FilterInterviewFutureCommand();

        String expectedMessage = String.format(FilterInterviewFutureCommand.MESSAGE_SUCCESS, 1);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(filterInterviewFutureCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_modelFilteredList_success() {

        showPersonAtIndex(model, INDEX_THIRD_PERSON); // Third person has empty interview

        FilterInterviewFutureCommand filterInterviewFutureCommand = new FilterInterviewFutureCommand();

        String expectedMessage = String.format(FilterInterviewFutureCommand.MESSAGE_SUCCESS, 1);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(filterInterviewFutureCommand, model, expectedMessage, expectedModel);
    }
}
