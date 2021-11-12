package seedu.notor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.notor.testutil.Assert.assertThrows;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.group.SubGroupCreateCommand;
import seedu.notor.logic.commands.group.SuperGroupCreateCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.SubGroupCreateExecutor;
import seedu.notor.logic.executors.group.SuperGroupCreateExecutor;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.common.Name;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;

public class GroupCreateCommandTest {

    private final Model model = new ModelManager(getTypicalNotor(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Executor.setup(model);
    }


    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuperGroupCreateCommand(null, null));
    }


    @Test
    public void execute_superGroupAcceptedByModel_addSuccessful() throws Exception {
        // Runs the command.
        model.listSuperGroup();
        SuperGroup validGroup = new SuperGroup(new Name("test"));
        CommandResult commandResult = new SuperGroupCreateCommand(null, validGroup).execute(model);

        // Create a new model and performs the command manually.
        ModelManager expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        expectedModel.listSuperGroup();
        expectedModel.addSuperGroup(validGroup.toString());


        assertEquals(String.format(SuperGroupCreateExecutor.MESSAGE_SUCCESS, validGroup),
                commandResult.getFeedbackToUser());
        assertEquals(model.getFilteredGroupList(), expectedModel.getFilteredGroupList());
    }

    @Test
    public void execute_subGroupAcceptedByModel_addSuccessful() throws Exception {
        // Runs the command.
        model.listSuperGroup();
        SubGroup validGroup = new SubGroup(new Name("Test"), null);
        CommandResult commandResult = new SubGroupCreateCommand(INDEX_FIRST_GROUP, validGroup).execute(model);
        model.listSubGroup(INDEX_FIRST_GROUP);

        // Create a new model and performs the command manually.
        ModelManager expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        expectedModel.listSuperGroup();
        SubGroup expectedSubGroup = new SubGroup(new Name("Test"), null);
        expectedModel.addSubGroup(INDEX_FIRST_GROUP, expectedSubGroup);
        expectedModel.listSubGroup(INDEX_FIRST_GROUP);

        assertEquals(String.format(SubGroupCreateExecutor.MESSAGE_SUCCESS, validGroup),
                commandResult.getFeedbackToUser());
        assertEquals(model.getFilteredGroupList(), expectedModel.getFilteredGroupList());
    }

    @Test
    public void execute_duplicateSuperGroup_throwsCommandException() throws Exception {
        model.listSuperGroup();
        SuperGroup existingGroup = (SuperGroup) model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        SuperGroup dupeGroup = new SuperGroup(new Name(existingGroup.getName()));
        SuperGroupCreateCommand command = new SuperGroupCreateCommand(null, dupeGroup);

        assertThrows(ExecuteException.class, SuperGroupCreateExecutor.MESSAGE_DUPLICATE_GROUP, ()
            -> command.execute(model));
    }

    @Test
    public void execute_duplicateSubGroup_throwsCommandException() throws Exception {
        model.listSubGroup(INDEX_FIRST_GROUP);
        SubGroup existingGroup = (SubGroup) model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        model.listSuperGroup();
        SubGroup dupeGroup = new SubGroup(new Name(existingGroup.getName()), null);
        SubGroupCreateCommand command = new SubGroupCreateCommand(INDEX_FIRST_GROUP, dupeGroup);

        assertThrows(ExecuteException.class,
            String.format(SubGroupCreateExecutor.MESSAGE_DUPLICATE_GROUP, existingGroup), () -> command.execute(model));
    }

    @Test
    public void equalsSuperGroupCreateCommand() {
        SuperGroup testGroup = new SuperGroup(new Name("test"));
        SuperGroup testGroup2 = new SuperGroup(new Name("test2"));

        SuperGroupCreateCommand groupCreateCommand = new SuperGroupCreateCommand(null, testGroup);
        SuperGroupCreateCommand groupCreateCommand2 = new SuperGroupCreateCommand(null, testGroup2);

        // same object -> returns true
        assertEquals(testGroup, testGroup);

        // same values -> returns true
        SuperGroupCreateCommand groupCreateCommandCopy = new SuperGroupCreateCommand(null, testGroup);
        assertEquals(groupCreateCommand, groupCreateCommandCopy);

        // different types -> returns false
        assertNotEquals(1, groupCreateCommand);

        // null -> returns false
        assertNotEquals(null, groupCreateCommand);

        // different group -> returns false
        assertNotEquals(groupCreateCommand, groupCreateCommand2);
    }

    @Test
    public void equalsSubGroupCreateCommand() {
        SubGroup testGroup = new SubGroup(new Name("Test"), null);
        SubGroup testGroup2 = new SubGroup(new Name("Test2"), null);;

        SubGroupCreateCommand groupCreateCommand = new SubGroupCreateCommand(INDEX_FIRST_GROUP, testGroup);
        SubGroupCreateCommand groupCreateCommand2 = new SubGroupCreateCommand(INDEX_FIRST_GROUP, testGroup2);

        // same object -> returns true
        assertEquals(testGroup, testGroup);

        // same values -> returns true
        SubGroupCreateCommand groupCreateCommandCopy = new SubGroupCreateCommand(INDEX_FIRST_GROUP, testGroup);
        assertEquals(groupCreateCommand, groupCreateCommandCopy);

        // different types -> returns false
        assertNotEquals(1, groupCreateCommand);

        // null -> returns false
        assertNotEquals(null, groupCreateCommand);

        // different group -> returns false
        assertNotEquals(groupCreateCommand, groupCreateCommand2);
    }
}
