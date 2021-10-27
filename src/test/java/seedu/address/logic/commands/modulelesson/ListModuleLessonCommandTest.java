package seedu.address.logic.commands.modulelesson;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalModuleLessons.getTypicalConthacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListModuleLessonCommand.
 */
public class ListModuleLessonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalConthacks(), new UserPrefs());
        expectedModel = new ModelManager(model.getConthacks(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListModuleLessonCommand(), model,
                ListModuleLessonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showLessonAtIndex(model, INDEX_SECOND);
        assertCommandSuccess(new ListModuleLessonCommand(), model,
                ListModuleLessonCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
