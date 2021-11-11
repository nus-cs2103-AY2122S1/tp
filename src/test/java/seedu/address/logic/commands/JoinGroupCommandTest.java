package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

public class JoinGroupCommandTest {
    private Set<Index> emptySet = Collections.emptySet();
    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void constructor_nullGroupIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JoinGroupCommand(null, emptySet));
    }

    @Test
    public void constructor_nullPersonIndexSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JoinGroupCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        Group validGroup = model.getFilteredGroupList().get(INDEX_SECOND.getZeroBased());
        Person validPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        Set<Person> personSet = new HashSet<>();
        personSet.add(validPerson);

        Set<Index> personIndexesSet = new HashSet<>();
        personIndexesSet.add(INDEX_FIRST);

        JoinGroupCommand joinGroupCommand = new JoinGroupCommand(INDEX_SECOND, personIndexesSet);

        String expectedMessage = String.format(JoinGroupCommand.MESSAGE_SUCCESS, validGroup);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Group expectedModelValidGroup = expectedModel.getFilteredGroupList().get(INDEX_SECOND.getZeroBased());
        expectedModel.addToGroup(expectedModelValidGroup, personSet);

        assertCommandSuccess(joinGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Set<Index> personIndexesSet = new HashSet<>();
        personIndexesSet.add(INDEX_INVALID);

        JoinGroupCommand joinGroupCommand = new JoinGroupCommand(INDEX_SECOND, personIndexesSet);
        String expectedMessage = JoinGroupCommand.MESSAGE_INVALID_PERSON_INDEX;

        assertCommandFailure(joinGroupCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidGroupIndex_throwsCommandException() {
        Set<Index> personIndexesSet = new HashSet<>();
        personIndexesSet.add(Index.fromZeroBased(1));

        JoinGroupCommand joinGroupCommand = new JoinGroupCommand(Index.fromOneBased(30), personIndexesSet);
        String expectedMessage = JoinGroupCommand.MESSAGE_INVALID_GROUP_INDEX;

        assertCommandFailure(joinGroupCommand, model, expectedMessage);
    }

    @Test
    public void undo_validPrecondition_successfulUndo() {
        Group group = model.getFilteredGroupList().get(INDEX_SECOND.getZeroBased());
        Set<Index> personIndexesSet = new HashSet<>();
        personIndexesSet.add(INDEX_SECOND);
        UndoableCommand joinGroupCommand = new JoinGroupCommand(INDEX_SECOND, personIndexesSet);
        String expectedMessage = String.format(JoinGroupCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, group);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(group)
                .build();
        assertUndoSuccess(joinGroupCommand, model, expectedUndoResult);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group target, Group editedGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<? super Person> getFilteredPersonListPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<? super Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<? super Group> getFilteredGroupListPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addToGroup(Group target, Set<Person> persons) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the group being added.
     */
    private class ModelStubAcceptingGroupAdded extends JoinGroupCommandTest.ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream().anyMatch(group::isSameGroup);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsAdded.add(group);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
