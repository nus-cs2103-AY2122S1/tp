package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_COMPLETED_TASKS;
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
 * Contains integration tests (interaction with the Model) for {@code ShowCompletedTasks}.
 */
class ShowCompletedTasksTest {
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
    public void execute_noTasks_noCompletedTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        ShowCompletedTasks command = new ShowCompletedTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_COMPLETED_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_oneIncompleteTask_noCompletedTaskFound() {
        Task incompleteTask = new TaskBuilder().withIsDone(false).build();
        model.addTask(incompleteTask);
        expectedModel.addTask(incompleteTask);

        ShowCompletedTasks command = new ShowCompletedTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_COMPLETED_TASKS);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_oneCompletedTask_success() {
        Task completeTask = new TaskBuilder().withIsDone(true).build();
        model.addTask(completeTask);
        expectedModel.addTask(completeTask);

        ShowCompletedTasks command = new ShowCompletedTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_COMPLETED_TASKS);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_typicalTasks_success() {
        TaskBook typicalTaskBook = getTypicalTaskBook();
        model.setTaskBook(typicalTaskBook);
        expectedModel.setTaskBook(typicalTaskBook);

        ShowCompletedTasks command = new ShowCompletedTasks();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_COMPLETED_TASKS);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

}

