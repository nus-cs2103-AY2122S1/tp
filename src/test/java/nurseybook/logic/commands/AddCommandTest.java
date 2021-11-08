package nurseybook.logic.commands;

import static java.util.Objects.requireNonNull;
import static nurseybook.commons.core.Messages.MESSAGE_DUPLICATE_ELDERLY;
import static nurseybook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import nurseybook.logic.commands.exceptions.CommandException;
import nurseybook.model.NurseyBook;
import nurseybook.model.ReadOnlyNurseyBook;
import nurseybook.model.VersionedNurseyBook;
import nurseybook.model.person.Elderly;
import nurseybook.model.task.Task;
import nurseybook.testutil.ElderlyBuilder;
import nurseybook.testutil.ModelStub;
import nurseybook.testutil.NurseyBookBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_elderlyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingElderlyAdded modelStub = new ModelStubAcceptingElderlyAdded();
        Elderly validElderly = new ElderlyBuilder().build();

        CommandResult commandResult = new AddCommand(validElderly).execute(modelStub);
        CommandResult expectedCommand = new CommandResult(String.format(AddCommand.MESSAGE_SUCCESS, validElderly),
                CommandResult.ListDisplayChange.ELDERLY);

        assertEquals(expectedCommand, commandResult);
        assertEquals(Arrays.asList(validElderly), modelStub.elderliesAdded);
    }

    @Test
    public void execute_duplicateElderly_throwsCommandException() {
        Elderly validElderly = new ElderlyBuilder().build();
        AddCommand addCommand = new AddCommand(validElderly);
        ModelStub modelStub = new ModelStubWithElderly(validElderly);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_ELDERLY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateElderlyCaseInsensitive_throwsCommandException() {
        Elderly randomCaseAlice = new ElderlyBuilder().withName("alICe").build();
        Elderly existingAlice = new ElderlyBuilder().withName("Alice").build();
        AddCommand addCommand = new AddCommand(randomCaseAlice);
        ModelStub modelStub = new ModelStubWithElderly(existingAlice);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_ELDERLY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Elderly alice = new ElderlyBuilder().withName("Alice").build();
        Elderly bob = new ElderlyBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different elderly -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single elderly.
     */
    private class ModelStubWithElderly extends ModelStub {
        private final Elderly elderly;

        ModelStubWithElderly(Elderly elderly) {
            requireNonNull(elderly);
            this.elderly = elderly;
        }

        @Override
        public boolean hasElderly(Elderly elderly) {
            requireNonNull(elderly);
            return this.elderly.isSameElderly(elderly);
        }
    }

    /**
     * A Model stub that always accept the elderly being added.
     */
    private class ModelStubAcceptingElderlyAdded extends ModelStub {
        final ArrayList<Elderly> elderliesAdded = new ArrayList<>();
        final VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(new NurseyBookBuilder().build());

        @Override
        public boolean hasElderly(Elderly elderly) {
            requireNonNull(elderly);
            return elderliesAdded.stream().anyMatch(elderly::isSameElderly);
        }

        @Override
        public void addElderly(Elderly elderly) {
            requireNonNull(elderly);
            elderliesAdded.add(elderly);
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            return; //do nothing
        }

        @Override
        public void updateFilteredElderlyList(Predicate<Elderly> predicate) {
            return; //do nothing
        }

        @Override
        public void updateTasksAccordingToTime() {
            return; //do nothing since only one task added
        }


        @Override
        public void commitNurseyBook(CommandResult commandResult) {
            versionedNurseyBook.commit(commandResult);
        }

        @Override
        public ReadOnlyNurseyBook getVersionedNurseyBook() {
            return new NurseyBook();
        }
    }

}
