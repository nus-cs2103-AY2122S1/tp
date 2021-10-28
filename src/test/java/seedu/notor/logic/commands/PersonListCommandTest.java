package seedu.notor.logic.commands;

import static seedu.notor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.person.PersonListCommand;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class PersonListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNotor(), new UserPrefs());
        expectedModel = new ModelManager(model.getNotor(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertExecuteSuccess(new PersonListCommand(), model, PersonListCommand.MESSAGE_SUCCESS,
            expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        CommandTestUtil.assertExecuteSuccess(new PersonListCommand(), model, PersonListCommand.MESSAGE_SUCCESS,
            expectedModel);
    }
}
