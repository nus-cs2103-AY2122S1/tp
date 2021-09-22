package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Date;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

class MarkDoneCommandTest {
    private static final Task testTask = new Task(new Label("test label"), new Date("test date"));

    //I followed the style of AddCommand test instead of DeleteCommand test since I thought using a modelStub
    //was more stylistically appropriate for testing.

    @Test
    public void execute_validIndexMarkDone_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOnePerson modelStub = new ModelStubWithOnePerson();

        Task secondTestTask = new Task(new Label("test label"), new Date("test date"));
        secondTestTask.setIsDone(true);

        CommandResult commandResult = new MarkDoneCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_SUCCESS, testTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(secondTestTask), modelStub.listWithOneTask);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        ModelStubWithOnePerson modelStub = new ModelStubWithOnePerson();

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, (
        ) -> deleteTaskCommand.execute(modelStub));
    }

    private class ModelStubWithOnePerson extends ModelStub {
        private final ObservableList<Task> listWithOneTask = FXCollections.observableArrayList(testTask);

        @Override
        public void markDone(Task task) {
            listWithOneTask.get(0).setIsDone(true);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return listWithOneTask;
        }
    }
}
