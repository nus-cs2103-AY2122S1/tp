package seedu.address.logic.candidate;

import static seedu.address.logic.candidate.CommandTestUtil.assertListCommandSuccess;
import static seedu.address.logic.candidate.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCandidateCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHrManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertListCommandSuccess(new ListCandidateCommand(), model, ListCandidateCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertListCommandSuccess(new ListCandidateCommand(), model, ListCandidateCommand.MESSAGE_SUCCESS,
                expectedModel);
    }
}
