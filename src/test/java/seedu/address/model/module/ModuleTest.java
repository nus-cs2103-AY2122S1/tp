package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModules.MODULE_1;
import static seedu.address.testutil.TypicalModules.MODULE_2;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.CHARLIE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalStudents;
import seedu.address.testutil.TypicalTasks;

class ModuleTest {

    @Test
    void isSameModule() {
        // same object -> returns true
        assertTrue(MODULE_1.isSameModule(MODULE_1));

        // null -> returns false
        assertFalse(MODULE_1.isSameModule(null));

        // different module -> returns false
        assertFalse(MODULE_1.isSameModule(MODULE_2));

        // same name -> returns true
        Module sameName = new ModuleBuilder().withName("CS2103").build();
        assertTrue(MODULE_1.isSameModule(sameName));

        // different name and tasks, same students -> returns false
        Module differentName = new ModuleBuilder().withName("CS2105")
                .withStudents(TypicalStudents.getTypicalStudents())
                .withTasks(TypicalTasks.getTypicalTasksForModule("CS2105"))
                .build();
        assertFalse(MODULE_1.isSameModule(differentName));
    }

    @Test
    void hasStudent() {
        // initialised with the student -> returns true
        assertTrue(MODULE_1.hasStudent(AMY));

        // not initialised with the student -> returns false
        assertFalse(MODULE_1.hasStudent(CHARLIE));
    }

    @Test
    void hasTask() {
        // initialised with the task -> returns true
        Task task1 = new TaskBuilder()
                .withModule("CS2103")
                .withId("T1")
                .withName("Assignment 1")
                .withDeadline("2021-12-31").build();
        assertTrue(MODULE_1.hasTask(task1));

        // not initialised with the task -> returns false
        Task task5 = new TaskBuilder()
                .withModule("CS2103")
                .withId("T5")
                .withName("Task 5")
                .withDeadline("2021-11-17 11:11").build();
        assertFalse(MODULE_1.hasTask(task5));
    }

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(MODULE_1.equals(MODULE_1));

        // not a Module object -> returns false
        assertFalse(MODULE_1.equals(AMY));

        // same attributes -> returns true
        Module sameAttributes = new ModuleBuilder()
                .withName("CS2103")
                .build();
        assertTrue(MODULE_1.equals(sameAttributes));

        // null -> returns false
        assertFalse(MODULE_1.equals(null));

        // different module -> returns false
        assertFalse(MODULE_1.equals(MODULE_2));

        // same name, different tasks -> returns true
        List<Task> taskList = TypicalTasks.getTypicalTasksForModule(MODULE_NAME_0);
        Module differentTask = new ModuleBuilder()
                .withName(MODULE_NAME_0)
                .withStudents(TypicalStudents.getTypicalStudents())
                .withTasks(taskList)
                .build();
        Task task5 = new TaskBuilder()
                .withModule("CS2103")
                .withId("T5")
                .withName("Task 5")
                .withDeadline("2021-11-17 11:11").build();
        differentTask.addTask(task5);
        assertTrue(MODULE_1.equals(differentTask));

        // same name, different students -> returns true
        Module differentStudent = new ModuleBuilder()
                .withName(MODULE_NAME_0)
                .withStudents(TypicalStudents.getTypicalStudents())
                .withTasks(taskList)
                .build();
        differentStudent.addStudent(CHARLIE);
        assertTrue(MODULE_1.equals(differentStudent));
    }
}
