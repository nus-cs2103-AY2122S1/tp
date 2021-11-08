package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilterInterviewPastCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterInterviewFutureCommand firstFilterPastInterviewCommand = new FilterInterviewFutureCommand();
        FilterInterviewFutureCommand secondFilterPastInterviewCommand = new FilterInterviewFutureCommand();

        // same object -> returns true
        assertEquals(firstFilterPastInterviewCommand, firstFilterPastInterviewCommand);

        // same values -> returns true
        assertEquals(firstFilterPastInterviewCommand, secondFilterPastInterviewCommand);

        // different types -> returns false
        assertNotEquals(firstFilterPastInterviewCommand, 1);

        // null -> returns false
        assertNotEquals(firstFilterPastInterviewCommand, null);
    }

    @Test
    public void execute_modelUnfilteredList_success() {

        FilterInterviewPastCommand filterInterviewPastCommand = new FilterInterviewPastCommand();

        String expectedMessage = String.format(FilterInterviewPastCommand.MESSAGE_SUCCESS, 1);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Second person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(filterInterviewPastCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_modelFilteredList_success() {

        showPersonAtIndex(model, INDEX_THIRD_PERSON); // Third person has empty interview

        FilterInterviewPastCommand filterInterviewPastCommand = new FilterInterviewPastCommand();

        String expectedMessage = String.format(FilterInterviewPastCommand.MESSAGE_SUCCESS, 1);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Second person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(filterInterviewPastCommand, model, expectedMessage, expectedModel);
    }
}
