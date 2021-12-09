package seedu.times.logic.commands.classcommands;

import static seedu.times.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.logic.commands.classcommands.DeleteClassCommand.MESSAGE_SUCCESS;
import static seedu.times.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.times.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.Messages;
import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.CommandTestUtil;
import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;
import seedu.times.model.tuitionclass.TuitionClass;

public class DeleteClassCommandTest {

    private Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST);
        TuitionClass tuitionClass = model.getFilteredTuitionClassList().get(0);

        ModelManager expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        expectedModel.deleteTuitionClass(tuitionClass);


        String expectedMessage = String.format(MESSAGE_SUCCESS, tuitionClass);
        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTuitionClassList().size() + 1);
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(outOfBoundIndex);
        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showClassAtIndex(model, INDEX_SECOND);

        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(INDEX_FIRST);
        TuitionClass tuitionClass = model.getFilteredTuitionClassList().get(INDEX_FIRST.getZeroBased());

        ModelManager expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        expectedModel.deleteTuitionClass(tuitionClass);
        expectedModel.updateFilteredClassList(p -> false);

        String expectedMessage = String.format(MESSAGE_SUCCESS, tuitionClass);
        assertCommandSuccess(deleteClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        CommandTestUtil.showClassAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = Index.fromOneBased(2);
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(outOfBoundIndex);
        assertCommandFailure(deleteClassCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

}
