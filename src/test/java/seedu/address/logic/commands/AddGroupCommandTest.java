package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

public class AddGroupCommandTest {

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_groupAcceptedByModel_addSuccessful() throws Exception {
        CommandTestUtil.ModelStubAcceptingGroupAdded modelStub = new CommandTestUtil.ModelStubAcceptingGroupAdded();
        Group validGroup = new GroupBuilder().build();

        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.groupsAdded);
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group validGroup = new GroupBuilder().build();
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);
        CommandTestUtil.ModelStub modelStub = new CommandTestUtil.ModelStubWithGroup(validGroup);

        assertThrows(CommandException.class,
                AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () -> addGroupCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Group g1 = new GroupBuilder().withName("w14-4").build();
        Group g2 = new GroupBuilder().withName("w11-1").build();
        AddGroupCommand addG1Command = new AddGroupCommand(g1);
        AddGroupCommand addG2Command = new AddGroupCommand(g2);

        // same object -> returns true
        assertTrue(addG1Command.equals(addG1Command));

        // same values -> returns true
        AddGroupCommand addG1CommandCopy = new AddGroupCommand(g1);
        assertTrue(addG1Command.equals(addG1CommandCopy));

        // different types -> returns false
        assertFalse(addG1Command.equals(1));

        // null -> returns false
        assertFalse(addG1Command.equals(null));

        // different task -> returns false
        assertFalse(addG1Command.equals(addG2Command));
    }

}
