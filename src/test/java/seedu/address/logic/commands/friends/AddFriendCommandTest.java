package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendsList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.testutil.FriendBuilder;

public class AddFriendCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFriendCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Friend validFriend = new FriendBuilder().build();
        CommandResult commandResult = new AddFriendCommand(validFriend).execute(modelStub);
        assertEquals(String.format(AddFriendCommand.MESSAGE_SUCCESS, validFriend), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFriend), modelStub.personsAdded);
    }



    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Friend validFriend = new FriendBuilder().build();
        AddFriendCommand addFriendCommand = new AddFriendCommand(validFriend);
        ModelStub modelStub = new ModelStubWithPerson(validFriend);

        assertThrows(CommandException.class, AddFriendCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addFriendCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Friend alice = new FriendBuilder().withFriendName("Alice").build();
        Friend bob = new FriendBuilder().withFriendName("Bob").build();
        AddFriendCommand addAliceCommand = new AddFriendCommand(alice);
        AddFriendCommand addBobCommand = new AddFriendCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddFriendCommand addAliceCommandCopy = new AddFriendCommand(alice);
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
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public ReadOnlyFriendsList getFriendsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendsList(ReadOnlyFriendsList readOnlyFriendsList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFriend(Friend friend) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFriendWithId(FriendId friendId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFriend(FriendId targetId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFriend(Friend friend) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriend(Friend target, Friend editedFriend) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Friend> getFilteredFriendsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFriendsList(Predicate<Friend> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void linkFriend(Friend toLink, GameFriendLink gameFriendLink) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFriendId(FriendId idToFind) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGamesList(ReadOnlyGamesList readOnlyGamesList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGamesList getGamesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGame(Game game) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGameWithId(GameId gameId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGame(GameId targetId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGame(Game game) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGame(Game target, Game editedGame) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Game> getFilteredGamesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGamesList(Predicate<Game> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGameId(GameId idToFind) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Friend friend;

        ModelStubWithPerson(Friend friend) {
            requireNonNull(friend);
            this.friend = friend;
        }

        @Override
        public boolean hasFriend(Friend friend) {
            requireNonNull(friend);
            return this.friend.equals(friend);
        }

        @Override
        public boolean hasFriendId(FriendId idToFind) {
            return this.friend.getFriendId().equals(friend.getFriendId());
        }

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Friend> personsAdded = new ArrayList<>();

        @Override
        public boolean hasFriend(Friend friend) {
            requireNonNull(friend);
            return personsAdded.stream().anyMatch(friend::equals);
        }

        @Override
        public boolean hasFriendId(FriendId idToFind) {
            return personsAdded.stream().anyMatch(friend -> friend.getFriendId().equals(idToFind));
        }

        @Override
        public void addFriend(Friend friend) {
            requireNonNull(friend);
            personsAdded.add(friend);
        }

        @Override
        public ReadOnlyFriendsList getFriendsList() {
            return new FriendsList();
        }
    }

}
