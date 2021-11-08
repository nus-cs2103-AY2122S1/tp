package seedu.address.logic.interview;

import static seedu.address.logic.interview.CommandTestUtil.assertListCommandSuccess;
import static seedu.address.logic.interview.CommandTestUtil.showInterviewAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListInterviewCommand.
 */
public class ListInterviewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHrManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertListCommandSuccess(new ListInterviewCommand(), model, ListInterviewCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);
        assertListCommandSuccess(new ListInterviewCommand(), model, ListInterviewCommand.MESSAGE_SUCCESS,
                expectedModel);
    }
}
