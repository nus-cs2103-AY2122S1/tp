package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.MEETING;
import static seedu.address.testutil.TypicalTasks.MEETING_DONE;
import static seedu.address.testutil.TypicalTasks.PROJECT;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TypicalTasks;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(MEETING));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(MEETING);
        assertTrue(taskList.contains(MEETING));
    }

    @Test
    public void isEmpty_emptyTaskList_returnsTrue() {
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyTaskList_returnsFalse() {
        taskList.add(MEETING);
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(MEETING);
        assertThrows(DuplicateTaskException.class, () -> taskList.add(MEETING));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, MEETING));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(MEETING, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(MEETING, MEETING));
    }

    @Test
    public void setTask_editedTaskIsSameMember_success() {
        taskList.add(MEETING);
        taskList.setTask(MEETING, MEETING);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(MEETING);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        taskList.add(MEETING);
        taskList.setTask(MEETING, MEETING_DONE);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(MEETING_DONE);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        taskList.add(MEETING);
        taskList.setTask(MEETING, PROJECT);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(PROJECT);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setMember_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        taskList.add(MEETING);
        taskList.add(PROJECT);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTask(MEETING, PROJECT));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(MEETING));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(MEETING);
        taskList.add(PROJECT);
        taskList.remove(PROJECT);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(MEETING);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void seTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(MEETING);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(MEETING_DONE);
        expectedTaskList.add(PROJECT);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(MEETING);
        List<Task> tasks = Collections.singletonList(PROJECT);
        taskList.setTasks(tasks);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(PROJECT);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void equals_test_notEqual() {
        taskList.setTasks(TypicalTasks.getTypicalTasks());
        TaskList otherTaskList = new TaskList();
        otherTaskList.setTasks(TypicalTasks.getTypicalTasksDone());
        assertNotEquals(taskList, otherTaskList);
    }

    @Test
    public void equals_test_equal() {
        taskList.setTasks(TypicalTasks.getTypicalTasks());
        TaskList otherTaskList = new TaskList();
        otherTaskList.setTasks(TypicalTasks.getTypicalTasks());
        assertEquals(taskList, otherTaskList);
    }
}
