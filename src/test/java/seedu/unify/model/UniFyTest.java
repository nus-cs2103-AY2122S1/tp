package seedu.unify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.unify.testutil.Assert.assertThrows;
import static seedu.unify.testutil.TypicalTasks.ALICE;
import static seedu.unify.testutil.TypicalTasks.getTypicalAddressBook;

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
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        UniFy newData = getTypicalAddressBook();
        uniFy.resetData(newData);
        assertEquals(newData, uniFy);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withDate(VALID_DATE_BOB).withTag(VALID_TAG_HUSBAND)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        UniFyStub newData = new UniFyStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> uniFy.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniFy.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(uniFy.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        uniFy.addTask(ALICE);
        assertTrue(uniFy.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        uniFy.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withDate(VALID_DATE_BOB).withTag(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniFy.hasTask(editedAlice));
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
