package tutoraid.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.testutil.TypicalIndexes;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalStudents.getTypicalStudentBook(),
                TypicalLessons.getTypicalLessonBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ListCommand(), model, Messages.MESSAGE_LIST_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_ITEM);
        CommandTestUtil.assertCommandSuccess(new ListCommand(), model, Messages.MESSAGE_LIST_SUCCESS, expectedModel);
    }
}
