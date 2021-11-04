package seedu.unify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.DUPLICATE_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.DUPLICATE_TAG_MODULE;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_MODULE;
import static seedu.unify.testutil.Assert.assertThrows;
import static seedu.unify.testutil.TypicalTasks.CS1234_QUIZ;
import static seedu.unify.testutil.TypicalTasks.getTypicalUniFy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.exceptions.DuplicateTaskException;
import seedu.unify.testutil.TaskBuilder;

public class UniFyTest {

    private final UniFy uniFy = new UniFy();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), uniFy.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniFy.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUniFy_replacesData() {
        UniFy newData = getTypicalUniFy();
        uniFy.resetData(newData);
        assertEquals(newData, uniFy);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(CS1234_QUIZ).withDate(DUPLICATE_DATE_QUIZ).withTags(DUPLICATE_TAG_MODULE)
                .build();
        List<Task> newTasks = Arrays.asList(CS1234_QUIZ, editedAlice);
        UniFyStub newData = new UniFyStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> uniFy.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniFy.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInUniFy_returnsFalse() {
        assertFalse(uniFy.hasTask(CS1234_QUIZ));
    }

    @Test
    public void hasTask_taskInUniFy_returnsTrue() {
        uniFy.addTask(CS1234_QUIZ);
        assertTrue(uniFy.hasTask(CS1234_QUIZ));
    }

    @Test
    public void hasTask_taskWithSameTaskNameInUniFy_returnsFalse() {
        uniFy.addTask(CS1234_QUIZ);
        Task editedAlice = new TaskBuilder(CS1234_QUIZ).withDate(VALID_DATE_QUIZ).withTags(VALID_TAG_MODULE)
                .build();
        assertFalse(uniFy.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniFy.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyUniFy whose tasks list can violate interface constraints.
     */
    private static class UniFyStub implements ReadOnlyUniFy {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        UniFyStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
