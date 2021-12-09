package seedu.times.logic.commands.classcommands;

import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.logic.commands.CommandTestUtil.showClassAtIndex;
import static seedu.times.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;

class ListClassCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        expectedModel = new ModelManager(model.getTimesTable(), new UserPrefs());
    }

    @Test
    public void execute_classListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListClassCommand(), model, ListClassCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_classListIsFiltered_showsEverything() {
        showClassAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListClassCommand(), model, ListClassCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
