package seedu.address.testutil;

import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.UniqueStudentList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalModules {

    public static final String MODULE_NAME_0 = "CS2103";
    public static final String MODULE_NAME_1 = "CS2100";

    public static final String INVALID_MODULE_NAME = "Invalid module name";

    //some samples, can add more/modify for testing
    public static final Module module1 = new ModuleBuilder()
            .withName(MODULE_NAME_0)
            .withStudents(TypicalStudents.getTypicalStudents())
            .withTasks(TypicalTasks.getTypicalTasksForModule(MODULE_NAME_0))
            .build();

    public static final Module module2 = new ModuleBuilder()
            .withName(MODULE_NAME_1)
            .withStudents(TypicalStudents.getTypicalStudents())
            .withTasks(TypicalTasks.getTypicalTasksForModule(MODULE_NAME_1))
            .build();

    /**
     * Returns an {@code TAB} with all the typical modules.
     */
    public static TeachingAssistantBuddy getTypicalTAB() {
        TeachingAssistantBuddy tab = new TeachingAssistantBuddy();
        for (Module module: getTypicalModules()) {
            tab.addModule(module);
        }
        return tab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(module1, module2));
    }

}
