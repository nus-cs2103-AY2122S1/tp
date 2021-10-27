package seedu.address.logic.commands;

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
    public void execute_modelUnfilteredList_success() {

        FilterInterviewFutureCommand filterInterviewFutureCommand = new FilterInterviewFutureCommand();

        String expectedMessage = FilterInterviewFutureCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(filterInterviewFutureCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_modelFilteredList_success() {

        showPersonAtIndex(model, INDEX_THIRD_PERSON); // Third person has empty interview

        FilterInterviewFutureCommand filterInterviewFutureCommand = new FilterInterviewFutureCommand();

        String expectedMessage = FilterInterviewFutureCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(filterInterviewFutureCommand, model, expectedMessage, expectedModel);
    }
}
