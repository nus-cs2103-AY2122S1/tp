package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.DUPLICATE_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.DUPLICATE_TAG_MODULE;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_MODULE;
import static seedu.unify.testutil.Assert.assertThrows;
import static seedu.unify.testutil.TypicalTasks.CS1234_QUIZ;
import static seedu.unify.testutil.TypicalTasks.QUIZ;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.unify.model.task.exceptions.DuplicateTaskException;
import seedu.unify.model.task.exceptions.TaskNotFoundException;
import seedu.unify.testutil.TaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(CS1234_QUIZ));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(CS1234_QUIZ);
        assertTrue(uniqueTaskList.contains(CS1234_QUIZ));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(CS1234_QUIZ);
        Task editedAlice = new TaskBuilder(CS1234_QUIZ).withDate(DUPLICATE_DATE_QUIZ).withTags(DUPLICATE_TAG_MODULE)
                .build();
        assertTrue(uniqueTaskList.contains(editedAlice));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(CS1234_QUIZ);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(CS1234_QUIZ));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, CS1234_QUIZ));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(CS1234_QUIZ, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(CS1234_QUIZ, CS1234_QUIZ));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(CS1234_QUIZ);
        uniqueTaskList.setTask(CS1234_QUIZ, CS1234_QUIZ);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(CS1234_QUIZ);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(CS1234_QUIZ);
        Task editedAlice = new TaskBuilder(CS1234_QUIZ).withDate(VALID_DATE_QUIZ).withTags(VALID_TAG_MODULE)
                .build();
        uniqueTaskList.setTask(CS1234_QUIZ, editedAlice);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedAlice);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(CS1234_QUIZ);
        uniqueTaskList.setTask(CS1234_QUIZ, QUIZ);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(QUIZ);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(CS1234_QUIZ);
        uniqueTaskList.add(QUIZ);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(CS1234_QUIZ, QUIZ));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(CS1234_QUIZ));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(CS1234_QUIZ);
        uniqueTaskList.remove(CS1234_QUIZ);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(CS1234_QUIZ);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(QUIZ);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(CS1234_QUIZ);
        List<Task> taskList = Collections.singletonList(QUIZ);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(QUIZ);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(CS1234_QUIZ, CS1234_QUIZ);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
