package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;

public class TypicalModules {

    public static final String MODULE_NAME_0 = "CS2103";
    public static final String MODULE_NAME_1 = "CS2100";

    public static final String INVALID_MODULE_NAME = "Invalid module name";

    //some samples, can add more/modify for testing
    public static final Module MODULE_1 = new ModuleBuilder()
            .withName(MODULE_NAME_0)
            .withStudents(TypicalStudents.getTypicalStudents())
            .withTasks(TypicalTasks.getTypicalTasksForModule(MODULE_NAME_0))
            .build();

    public static final Module MODULE_2 = new ModuleBuilder()
            .withName(MODULE_NAME_1)
            .withStudents(TypicalStudents.getTypicalStudents())
            .withTasks(TypicalTasks.getTypicalTasksForModule(MODULE_NAME_1))
            .build();

    /**
     * Returns an {@code TAB} with all the typical modules.
     */
    public static TeachingAssistantBuddy getTypicalBuddy() {
        TeachingAssistantBuddy tab = new TeachingAssistantBuddy();
        for (Module module: getTypicalModules()) {
            tab.addModule(module);
        }
        return tab;
    }

    /**
     * Returns a list of typical modules.
     *
     * @return A list of modules.
     */
    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(MODULE_1, MODULE_2));
    }

}
