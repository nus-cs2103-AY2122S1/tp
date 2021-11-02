package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.order.Order;
import seedu.address.model.task.Task;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.TaskBuilder;

class AddTaskCommandTest {

    @Test
    public void constructor_nullLabel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        // test general task
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();
        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelstub = new ModelStubWithOneTask(validTask);

        assertThrows(CommandException.class, AddTaskCommand.MESSAGE_DUPLICATE_TASK, (
            ) -> addTaskCommand.execute(modelstub));
    }

    @Test
    public void execute_taskWithValidTag_addSuccessful() throws Exception {
        Order order = new OrderBuilder().build();
        String orderId = "SO" + order.getId();
        ModelStub modelStub = new ModelStubAcceptingTaskWithOrder(order);

        Task validTask = new TaskBuilder().withTaskTag(orderId).build();
        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
    }


    @Test
    public void execute_taskDeclinedByModel_throwsCommandException() {
        Task validTask = new TaskBuilder().withTaskTag("SO19").build(); //invalid order id
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelStub = new ModelStubAcceptingTaskWithOrder(new OrderBuilder().build());

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_UNFOUND_ORDERID, () -> addTaskCommand.execute(modelStub));
    }

    /**
     * A model stub for testing adding single task
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        private final ArrayList<Task> tasksAdded = new ArrayList<Task>();

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.size() == 1 && tasksAdded.get(0).isSameTask(task);
        }
    }

    private class ModelStubAcceptingTaskWithOrder extends ModelStubAcceptingTaskAdded {
        private final Order order;

        public ModelStubAcceptingTaskWithOrder(Order order) {
            this.order = order;
        }

        @Override
        public boolean hasOrder(long id) {
            return order.getId() == id;
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            super.tasksAdded.add(task);
        }
    }

    private class ModelStubWithOneTask extends ModelStub {
        private final Task task;

        public ModelStubWithOneTask(Task task) {
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }
}
