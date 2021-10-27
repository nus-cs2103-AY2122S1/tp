package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;

public class FilterInterviewPastCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_modelUnfilteredList_success() {

        FilterInterviewPastCommand filterInterviewPastCommand = new FilterInterviewPastCommand();

        String expectedMessage = FilterInterviewPastCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Second person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(filterInterviewPastCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_modelFilteredList_success() {

        showPersonAtIndex(model, INDEX_THIRD_PERSON); // Third person has empty interview

        FilterInterviewPastCommand filterInterviewPastCommand = new FilterInterviewPastCommand();

        String expectedMessage = FilterInterviewPastCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Second person is the only applicant with passed interview in Typical Persons
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(filterInterviewPastCommand, model, expectedMessage, expectedModel);
    }
}
