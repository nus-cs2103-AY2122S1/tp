package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import dash.model.task.exceptions.TaskNotFoundException;
import dash.testutil.Assert;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalPersons;
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
    public void remove_taskNotInTaskList_throwsTaskNotFoundException() {
        Assert.assertThrows(TaskNotFoundException.class, () -> taskList.remove(TypicalTasks.ASSIGNMENT));
    }


    @Test
    public void getObservableTaskList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> taskList
                .getObservableTaskList().remove(0));
    }

    @Test
    public void setTasks_taskInEmptyTaskList_returnsTrue() {
        TaskList taskListToCompare = new TaskList();
        taskListToCompare.add(TypicalTasks.ASSIGNMENT);
        taskList.setTasks(taskListToCompare);
        assertEquals(taskListToCompare, taskList);
    }

    @Test
    public void sort_completedAndUncompletedTasks_returnsTrue() {
        Task completedTask = new TaskBuilder().withTaskDescription("Task").withCompletionStatus(true).build();
        Task uncompletedTask = new TaskBuilder().withTaskDescription("Task").withCompletionStatus(false).build();
        TaskList sortedTask = new TaskList();
        sortedTask.add(completedTask);
        sortedTask.add(uncompletedTask);

        taskList.add(uncompletedTask);
        taskList.add(completedTask);
        taskList.sortTasks();

        assertEquals(sortedTask.getObservableTaskList(), taskList.getObservableTaskList());
    }

    @Test
    public void sort_completedAndUncompletedTasks_returnsFalse() {
        Task completedTask = new TaskBuilder().withTaskDescription("Task").withCompletionStatus(true).build();
        Task uncompletedTask = new TaskBuilder().withTaskDescription("Task").withCompletionStatus(false).build();
        TaskList sortedTask = new TaskList();
        sortedTask.add(completedTask);
        sortedTask.add(uncompletedTask);

        taskList.add(uncompletedTask);
        taskList.add(completedTask);

        assertNotEquals(sortedTask.getObservableTaskList(),taskList.getObservableTaskList());
    }

    @Test
    public void sort_taskWithDateAndTaskWithoutDate_returnsTrue() {
        Task taskWithDate = new TaskBuilder().withTaskDescription("Task").withTaskDate("21/10/2020").build();
        Task taskWithoutDate = new TaskBuilder().withTaskDescription("Task").build();
        TaskList sortedTask = new TaskList();
        sortedTask.add(taskWithoutDate);
        sortedTask.add(taskWithDate);

        taskList.add(taskWithDate);
        taskList.add(taskWithoutDate);
        taskList.sortTasks();


        assertEquals(sortedTask.getObservableTaskList(), taskList.getObservableTaskList());
    }

    @Test
    public void sort_taskWithDateAndTaskWithoutDate_returnsFalse() {
        Task taskWithDate = new TaskBuilder().withTaskDescription("Task").withTaskDate("21/10/2020").build();
        Task taskWithoutDate = new TaskBuilder().withTaskDescription("Task").build();
        TaskList sortedTask = new TaskList();
        sortedTask.add(taskWithoutDate);
        sortedTask.add(taskWithDate);

        taskList.add(taskWithDate);
        taskList.add(taskWithoutDate);

        assertNotEquals(sortedTask.getObservableTaskList(),taskList.getObservableTaskList());

    }

    @Test
    public void sort_differentDateTimeTasks_returnsTrue() {
        Task taskFirstPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("21/10/2020, 1900").build();
        Task taskSecondPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("23/10/2020, 2000").build();
        Task taskThirdPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("24/10/2020, 1000").build();
        Task taskFourthPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("30/11/2020, 1000").build();

        TaskList sortedTask = new TaskList();
        sortedTask.add(taskFirstPosition);
        sortedTask.add(taskSecondPosition);
        sortedTask.add(taskThirdPosition);
        sortedTask.add(taskFourthPosition);

        taskList.add(taskFourthPosition);
        taskList.add(taskThirdPosition);
        taskList.add(taskSecondPosition);
        taskList.add(taskFirstPosition);
        taskList.sortTasks();

        assertEquals(sortedTask.getObservableTaskList(), taskList.getObservableTaskList());
    }

    @Test
    public void sort_differentDateTimeTasks_returnsFalse() {
        Task taskFirstPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("21/10/2020, 1900").build();
        Task taskSecondPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("23/10/2020, 2000").build();
        Task taskThirdPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("24/10/2020, 1000").build();
        Task taskFourthPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("30/11/2020, 1000").build();

        TaskList sortedTask = new TaskList();
        sortedTask.add(taskFirstPosition);
        sortedTask.add(taskSecondPosition);
        sortedTask.add(taskThirdPosition);
        sortedTask.add(taskFourthPosition);

        taskList.add(taskFourthPosition);
        taskList.add(taskThirdPosition);
        taskList.add(taskSecondPosition);
        taskList.add(taskFirstPosition);

        assertNotEquals(sortedTask.getObservableTaskList(),taskList.getObservableTaskList());
    }

    @Test
    public void delete_CompletedTasks_returnsTrue() {
        Task completedTask = new TaskBuilder().withTaskDescription("Task").withCompletionStatus(true).build();
        TaskList copyTaskList = new TaskList();
        copyTaskList.add(completedTask);

        copyTaskList.deleteDoneTasks();
        assertEquals(copyTaskList, taskList);
    }

    @Test
    public void getIndexToEdit_returnsTrue() {
        Task taskFirstPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("21/10/2020, 1900").build();
        Task taskSecondPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("23/10/2020, 2000").build();
        Task taskThirdPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("24/10/2020, 1000").build();
        Task taskFourthPosition = new TaskBuilder().withTaskDescription("Task").withTaskDate("30/11/2020, 1000").build();
        taskList.add(taskFourthPosition);
        taskList.add(taskThirdPosition);
        taskList.add(taskThirdPosition);
        taskList.add(taskSecondPosition);
        taskList.add(taskFirstPosition);

        assertEquals(4, taskList.getIndexToEdit(3, taskThirdPosition, taskList.getObservableTaskList()));
        assertEquals(2, taskList.getIndexToEdit(2, taskThirdPosition, taskList.getObservableTaskList()));
    }

    @Test
    public void replace_personToBeReplaced_returnsTrue() {
        Task taskWithAlice = new TaskBuilder().withTaskDescription("Task").withPeople(TypicalPersons.ALICE).build();
        Task taskWithAmy = new TaskBuilder().withTaskDescription("Task").withPeople(TypicalPersons.AMY).build();
        TaskList taskListWithAlice = new TaskList();
        taskListWithAlice.add(taskWithAlice);
        taskList.add(taskWithAmy);

        taskList.replacePeople(TypicalPersons.AMY, TypicalPersons.ALICE);
        assertEquals(taskListWithAlice.getObservableTaskList(), taskList.getObservableTaskList());
    }

    @Test
    public void delete_personToBeDeleted_returnsTrue() {
        Task taskWithAlice = new TaskBuilder().withTaskDescription("Task").withPeople(TypicalPersons.ALICE).build();
        Task taskWithoutAlice = new TaskBuilder().withTaskDescription("Task").build();
        TaskList taskListWithoutAlice = new TaskList();
        taskListWithoutAlice.add(taskWithoutAlice);

        taskList.add(taskWithAlice);
        taskList.deletePeople(TypicalPersons.ALICE);
        assertEquals(taskListWithoutAlice.getObservableTaskList(), taskList.getObservableTaskList());
    }
}
