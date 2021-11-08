package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.SEW;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskBookTest {

    private final TaskBook taskBook = new TaskBook();

    @Test
    public void taskBookConstructor() {
        assertEquals(Collections.emptyList(), taskBook.getTaskList());
    }

    @Test
    public void taskBookResetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskBook_replacesData() {
        TaskBook newData = getTypicalTaskBook();
        taskBook.resetData(newData);
        assertEquals(newData, taskBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedSew = new TaskBuilder(SEW).build();
        List<Task> newTasks = Arrays.asList(SEW, editedSew);
        TaskBookStub newData = new TaskBookStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskBook.resetData(newData));
    }

    @Test
    public void hasTasks_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskBook_returnsFalse() {
        assertFalse(taskBook.hasTask(SEW));
    }

    @Test
    public void hasTask_taskInTaskBook_returnsTrue() {
        taskBook.addTask(SEW);
        assertTrue(taskBook.hasTask(SEW));
    }


    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskBook whose tasks list can violate interface constraints.
     */
    private static class TaskBookStub implements ReadOnlyTaskBook {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskBookStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
