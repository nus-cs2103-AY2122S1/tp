package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import dash.testutil.Assert;
import dash.testutil.TypicalTasks;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskList.getObservableTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.resetData(null));
    }

    @Test
    public void resetData_withValidTaskList_replacesData() {
        TaskList newData = TypicalTasks.getTypicalTaskList();
        taskList.resetData(newData);
        assertEquals(newData, taskList);
    }

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInTaskList_returnsFalse() {
        assertFalse(taskList.contains(TypicalTasks.ASSIGNMENT));
    }

    @Test
    public void contains_taskInTaskList_returnsTrue() {
        taskList.add(TypicalTasks.ASSIGNMENT);
        assertTrue(taskList.contains(TypicalTasks.ASSIGNMENT));
    }

    @Test
    public void getObservableTaskList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> taskList
                .getObservableTaskList().remove(0));
    }
}
