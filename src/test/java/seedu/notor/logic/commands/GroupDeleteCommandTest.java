package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteFailure;
import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteSuccess;
import static seedu.notor.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.notor.testutil.TypicalIndexes.INDEX_SECOND_GROUP;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.group.GroupDeleteCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.group.GroupDeleteExecutor;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;

/**
 * Contains integration tests (interaction with the Model) for {@code GroupCreateCommand}.
 */
public class GroupDeleteCommandTest {

    private final Model model = new ModelManager(getTypicalNotor(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Executor.setup(model);
    }

    @Test
    public void execute_validIndexUnfilteredSuperGroupList_success() {
        model.listSuperGroup();
        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());

        GroupDeleteCommand deleteCommand = new GroupDeleteCommandStub(INDEX_FIRST_GROUP);

        String expectedMessage = String.format(GroupDeleteExecutorStub.MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        if (groupToDelete instanceof SuperGroup) {
            expectedModel.deleteSuperGroup((SuperGroup) groupToDelete);
        }
        if (groupToDelete instanceof SubGroup) {
            expectedModel.deleteSubGroup((SubGroup) groupToDelete);
        }
        assertExecuteSuccess(deleteCommand, model, expectedMessage, expectedModel);


    }

    @Test
    public void execute_validIndexUnfilteredSubGroupList_success() {
        model.listSubGroup(INDEX_FIRST_GROUP);
        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());

        GroupDeleteCommand deleteCommand = new GroupDeleteCommandStub(INDEX_FIRST_GROUP);

        String expectedMessage = String.format(GroupDeleteExecutorStub.MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        if (groupToDelete instanceof SuperGroup) {
            expectedModel.deleteSuperGroup((SuperGroup) groupToDelete);
        }
        if (groupToDelete instanceof SubGroup) {
            expectedModel.deleteSubGroup((SubGroup) groupToDelete);
        }
        assertExecuteSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredSuperGroupList_throwsCommandException() {
        model.listSuperGroup();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        GroupDeleteCommand deleteCommand = new GroupDeleteCommandStub(outOfBoundIndex);

        assertExecuteFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexUnfilteredSubGroupList_throwsCommandException() {
        model.listSubGroup(INDEX_FIRST_GROUP);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        GroupDeleteCommand deleteCommand = new GroupDeleteCommandStub(outOfBoundIndex);

        assertExecuteFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredSuperGroupList_success() {
        model.listSuperGroup();
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());

        GroupDeleteCommand deleteCommand = new GroupDeleteCommandStub(INDEX_FIRST_GROUP);

        String expectedMessage = String.format(GroupDeleteExecutorStub.MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        if (groupToDelete instanceof SuperGroup) {
            expectedModel.deleteSuperGroup((SuperGroup) groupToDelete);
        }
        if (groupToDelete instanceof SubGroup) {
            expectedModel.deleteSubGroup((SubGroup) groupToDelete);
        }

        assertExecuteSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_validIndexFilteredSubGroupList_success() {
        model.listSubGroup(INDEX_FIRST_GROUP);
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());

        GroupDeleteCommand deleteCommand = new GroupDeleteCommandStub(INDEX_FIRST_GROUP);

        String expectedMessage = String.format(GroupDeleteExecutorStub.MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        if (groupToDelete instanceof SuperGroup) {
            expectedModel.deleteSuperGroup((SuperGroup) groupToDelete);
        }
        if (groupToDelete instanceof SubGroup) {
            expectedModel.deleteSubGroup((SubGroup) groupToDelete);
        }

        assertExecuteSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        GroupDeleteCommand deleteFirstCommand = new GroupDeleteCommandStub(INDEX_FIRST_GROUP);
        GroupDeleteCommand deleteSecondCommand = new GroupDeleteCommandStub(INDEX_SECOND_GROUP);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        GroupDeleteCommand deleteFirstCommandCopy = new GroupDeleteCommandStub(INDEX_FIRST_GROUP);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different Group -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    private static class GroupDeleteExecutorStub extends GroupDeleteExecutor {
        public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";

        public GroupDeleteExecutorStub(Index index) {
            super(index);
        }

        @Override
        public CommandResult execute() throws ExecuteException {
            Group deletedGroup = super.getGroup();
            if (deletedGroup instanceof SuperGroup) {
                model.deleteSuperGroup((SuperGroup) deletedGroup);
            } else if (deletedGroup instanceof SubGroup) {
                model.deleteSubGroup((SubGroup) deletedGroup);
            } else {
                throw new ExecuteException(Messages.MESSAGE_UNEXPECTED_ERROR);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, deletedGroup));
        }

    }
    private static class GroupDeleteCommandStub extends GroupDeleteCommand {

        private final GroupDeleteCommandTest.GroupDeleteExecutorStub executor;

        /**
         * Constructor for a PersonDeleteCommand.
         *
         * @param index Index of the person to be deleted.
         */
        public GroupDeleteCommandStub(Index index) {
            super(index);
            requireNonNull(index);
            this.executor = new GroupDeleteCommandTest.GroupDeleteExecutorStub(index);
        }

        @Override
        public CommandResult execute() throws ExecuteException {
            return executor.execute();
        }
    }


}
