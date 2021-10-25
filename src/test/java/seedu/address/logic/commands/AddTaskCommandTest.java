package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.order.Order;
import seedu.address.model.tag.TaskTag;
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

        // test valid id
        Order validOrder = new OrderBuilder().build();
        modelStub = new ModelStubAcceptingTaskAdded();
        modelStub.addOrder(validOrder);
        validTask = new Task(new Label("test label"),
                new Date("1918-10-12"), new TaskTag("SO" + validOrder.getId()));
        commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);

    }

    @Test
    public void execute_taskDeclinedByModel_throwsCommandException() {
        Task validTask = new Task(new Label("test label"), new Date("1918-10-12"), new TaskTag("SO1"));
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelStub = new ModelStubAcceptingTaskAdded();

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_UNFOUND_ORDERID, () -> addTaskCommand.execute(modelStub));
    }

    /**
     * A model stub for testing adding single task
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        private final ArrayList<Task> tasksAdded = new ArrayList<Task>();
        private final ArrayList<Order> ordersAdded = new ArrayList<>();

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }


        @Override
        public boolean hasOrder(long id) {
            return ordersAdded.stream().anyMatch(order -> order.getId() == id);
        }
    }
}
