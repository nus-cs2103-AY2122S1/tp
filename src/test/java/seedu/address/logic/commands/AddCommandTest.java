package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimeList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAnimeList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.anime.Anime;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Anime validAnime = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validAnime).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validAnime), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAnime), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Anime validAnime = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validAnime);
        ModelStub modelStub = new ModelStubWithPerson(validAnime);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Anime alice = new PersonBuilder().withName("Alice").build();
        Anime bob = new PersonBuilder().withName("Bob").build();
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

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getAniListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAnime(Anime anime) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAnimeList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAnimeList getAniList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAnime(Anime anime) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAnime(Anime target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAnime(Anime target, Anime editedAnime) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Anime> getFilteredAnimeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAnimeList(Predicate<Anime> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Anime anime;

        ModelStubWithPerson(Anime anime) {
            requireNonNull(anime);
            this.anime = anime;
        }

        @Override
        public boolean hasAnime(Anime anime) {
            requireNonNull(anime);
            return this.anime.isSameAnime(anime);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Anime> personsAdded = new ArrayList<>();

        @Override
        public boolean hasAnime(Anime anime) {
            requireNonNull(anime);
            return personsAdded.stream().anyMatch(anime::isSameAnime);
        }

        @Override
        public void addAnime(Anime anime) {
            requireNonNull(anime);
            personsAdded.add(anime);
        }

        @Override
        public ReadOnlyAnimeList getAniList() {
            return new AnimeList();
        }
    }

}
