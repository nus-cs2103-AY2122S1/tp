package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.ModelStub;

public class AddGroupCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_validGroup_addSuccessful() throws Exception {
        ModelStubAcceptingGroupsAdded modelStub = new ModelStubAcceptingGroupsAdded();
        Group validGroup = new GroupBuilder().build();
        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.groupsAdded);

    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() throws Exception {
        Group validGroup = new GroupBuilder().build();
        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);

        assertThrows(CommandException.class,
                AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () -> addGroupCommand.execute(modelStub));
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.isSameGroup(group);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingGroupsAdded extends ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            return groupsAdded.contains(group);
        }

        @Override
        public void addGroup(Group group) {
            groupsAdded.add(group);
        }

    }
}
