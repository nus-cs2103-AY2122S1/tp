package seedu.edrecord.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.ModelManager;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.testutil.TypicalGroups;
import seedu.edrecord.testutil.TypicalModules;

public class MakeGroupCommandTest {
    @Test
    public void constructor_nullGroupAndModule_throwsNullPointerException() {
        Group validGroup = new Group("T12");
        Module validModule = TypicalModules.CS2103;
        assertThrows(NullPointerException.class, () -> new MakeGroupCommand(validGroup, null));
        assertThrows(NullPointerException.class, () -> new MakeGroupCommand(null, validModule));
    }

    @Test
    public void execute_moduleDontExist_unsuccessful() {
        ModelManager expectedModel = new ModelManager(new EdRecord(),
            TypicalModules.getTypicalModuleSystem(), new UserPrefs());
        Module moduleNotInModuleSystem = new Module("CS1111", TypicalGroups.getTypicalGroupSystem());
        Group validGroup = new Group("T12");
        assertThrows(CommandException.class, () -> new MakeGroupCommand(validGroup, moduleNotInModuleSystem)
            .execute(expectedModel));

    }

    @Test
    public void execute_groupAlreadyExists_unsuccessful() {
        ModelManager expectedModel = new ModelManager(new EdRecord(), TypicalModules.getTypicalModuleSystem(),
            new UserPrefs());
        Module validModule = new Module("CS1111", TypicalGroups.getTypicalGroupSystem());
        Group groupThatAlreadyExistsInModule = TypicalGroups.T03;
        assertThrows(CommandException.class, () -> new MakeGroupCommand(groupThatAlreadyExistsInModule, validModule)
            .execute(expectedModel));
    }

    @Test
    public void execute_groupAddedToModule_makeGroupSuccessful() throws Exception {
        Group groupThatDoesNotExist = new Group("T12");
        GroupSystem typicalGroupSystem = new GroupSystem();

        ModuleSystem moduleSystem = new ModuleSystem();
        Module validModule = new Module("CS2103", typicalGroupSystem);
        moduleSystem.setModules(new ArrayList<>(Arrays.asList(validModule)));

        ModelManager expectedModel = new ModelManager(new EdRecord(), moduleSystem, new UserPrefs());
        assertEquals(String.format(MakeGroupCommand.MESSAGE_SUCCESS, groupThatDoesNotExist, validModule),
                new MakeGroupCommand(groupThatDoesNotExist, validModule).execute(expectedModel).getFeedbackToUser());
    }

    @Test
    public void execute_groupsAddedToDifferentModule_makeDifferentGroupsSuccessful() throws Exception {
        Group groupThatDoesNotExist = new Group("T12");

        Module validModule1 = new Module("CS1111", new GroupSystem());
        Module validModule2 = new Module("CS2222", new GroupSystem());
        ModuleSystem moduleSystem = new ModuleSystem();
        moduleSystem.setModules(new ArrayList<>(Arrays.asList(validModule1, validModule2)));

        ModelManager expectedModel = new ModelManager(new EdRecord(), moduleSystem, new UserPrefs());
        assertEquals(String.format(MakeGroupCommand.MESSAGE_SUCCESS, groupThatDoesNotExist, validModule1),
            new MakeGroupCommand(groupThatDoesNotExist, validModule1).execute(expectedModel).getFeedbackToUser());
        assertEquals(String.format(MakeGroupCommand.MESSAGE_SUCCESS, groupThatDoesNotExist, validModule2),
            new MakeGroupCommand(groupThatDoesNotExist, validModule2).execute(expectedModel).getFeedbackToUser());
    }
}
