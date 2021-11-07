package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2101;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2103T;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.model.CsBook;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Student;
import seedu.address.testutil.ModelStub;

public class DeleteGroupCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelStubWithGroups();
        model.addGroup(TYPICAL_GROUP_CS2103T);
        model.addGroup(TYPICAL_GROUP_CS2101);
    }

    @Test
    public void execute_validGroup_success() {
        // get any group already in the model
        Group groupToDelete = model.getFilteredGroupList().get(0);
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(groupToDelete.getGroupName());

        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelStubWithGroups expectedModel = new ModelStubWithGroups();
        expectedModel.addGroup(TYPICAL_GROUP_CS2103T);
        expectedModel.addGroup(TYPICAL_GROUP_CS2101);
        expectedModel.deleteGroup(groupToDelete);
        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentGroup_throwsCommandException() {
        // try some group that does not exist
        String nonExistentGroupName = "qwertyuiopasdfghjklzxcvbnm";

        assertTrue(GroupName.isValidName(nonExistentGroupName));

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(new GroupName(nonExistentGroupName));

        assertCommandFailure(deleteGroupCommand, model, Messages.MESSAGE_GROUP_NOT_FOUND);
    }

    @Test
    public void equals() {
        final GroupName firstGroupName = model.getFilteredGroupList().get(0).getGroupName();
        final GroupName secondGroupName = model.getFilteredGroupList().get(1).getGroupName();

        DeleteGroupCommand deleteFirstCommand = new DeleteGroupCommand(firstGroupName);
        DeleteGroupCommand deleteSecondCommand = new DeleteGroupCommand(secondGroupName);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteGroupCommand deleteFirstCommandCopy = new DeleteGroupCommand(firstGroupName);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * A Model stub that contains a students.
     */
    private class ModelStubWithGroups extends ModelStub {
        protected final ObservableList<Group> groups;
        protected final FilteredList<Group> filteredGroups;

        ModelStubWithGroups() {
            groups = FXCollections.observableArrayList();
            filteredGroups = new FilteredList<>(groups);
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            requireNonNull(predicate);
            this.filteredGroups.setPredicate(predicate);
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            return this.filteredGroups;
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return new FilteredList<Student>(FXCollections.observableArrayList());
        }

        @Override
        public CsBook getCsBook() {
            return new CsBook();
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            // does no checking nor storing of students, only unit testing that the command successfully calls
            // the model to delete the specified group
            groups.add(group);
        }

        @Override
        public Group getGroupByGroupName(GroupName groupName) {
            FilteredList<Group> tempFilteredGroups = new FilteredList<>(groups);
            tempFilteredGroups.setPredicate(group -> group.getGroupName().equals(groupName));

            // return null if the group is not found
            if (tempFilteredGroups.isEmpty()) {
                return null;
            }

            assert tempFilteredGroups.size() == 1 : "Group names should be unique";

            Group retrievedGroup = tempFilteredGroups.get(0);
            return retrievedGroup;
        }

        @Override
        public void deleteGroup(Group target) {
            // Note: Does not delete all students associated with the group like in actual model
            groups.remove(target);
        }


        @Override
        public boolean equals(Object obj) {
            // short circuit if same object
            if (obj == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(obj instanceof ModelStubWithGroups)) {
                return false;
            }

            // state check
            ModelStubWithGroups other = (ModelStubWithGroups) obj;
            return groups.equals(other.groups);
        }
    }
}
