package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_INCOMPLETE_TASKS;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowIncompleteTasks}.
 */
class ShowIncompleteTasksTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        //start with an empty task book
        model = new ModelManager(getTypicalAddressBook(), new TaskBook(),
                getTypicalOrderBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new TaskBook(),
                getTypicalOrderBook(), new UserPrefs());
    }

    @Test
    public void execute_noTasks_noIncompleteTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        ShowIncompleteTasks command = new ShowIncompleteTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_INCOMPLETE_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_oneIncompleteTask_success() {
        Task incompleteTask = new TaskBuilder().withIsDone(false).build();
        model.addTask(incompleteTask);
        expectedModel.addTask(incompleteTask);

        ShowIncompleteTasks command = new ShowIncompleteTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_INCOMPLETE_TASKS);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneCompletedTask_noIncompleteTaskFound() {
        Task completeTask = new TaskBuilder().withIsDone(true).build();
        model.addTask(completeTask);
        expectedModel.addTask(completeTask);

        ShowIncompleteTasks command = new ShowIncompleteTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_INCOMPLETE_TASKS);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }


    @Test
    public void execute_typicalTasks_success() {
        TaskBook typicalTaskBook = getTypicalTaskBook();
        model.setTaskBook(typicalTaskBook);
        expectedModel.setTaskBook(typicalTaskBook);

        ShowIncompleteTasks command = new ShowIncompleteTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_INCOMPLETE_TASKS);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

}

