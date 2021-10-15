package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FRIENDS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GAMES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.BENSON;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.friend.FriendNameContainsKeywordsPredicate;
import seedu.address.model.game.GameIdContainsKeywordPredicate;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.FriendsListBuilder;
import seedu.address.testutil.GamesListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FriendsList(), new FriendsList(modelManager.getFriendsList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFriendsListFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFriendsListFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasFriend_nullFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFriend(null));
    }

    @Test
    public void hasFriendId_nullFriendId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFriendWithId(null));
    }

    @Test
    public void hasFriend_friendNotInFriendsList_returnsFalse() {
        assertFalse(modelManager.hasFriend(ALICE));
    }

    @Test
    public void hasFriendId_friendIdNotInFriendsList_returnsFalse() {
        assertFalse(modelManager.hasFriendWithId(ALICE.getFriendId()));
    }

    @Test
    public void hasFriend_friendInFriendsList_returnsTrue() {
        modelManager.addFriend(ALICE);
        assertTrue(modelManager.hasFriend(ALICE));
    }

    @Test
    public void hasFriendId_friendIdInFriendsList_returnsTrue() {
        modelManager.addFriend(ALICE);
        assertTrue(modelManager.hasFriendWithId(ALICE.getFriendId()));
    }

    @Test
    public void hasFriendId_sameIdDifferentName_returnsTrue() {
        FriendBuilder amyFriendBuilder = new FriendBuilder();
        modelManager.addFriend(amyFriendBuilder.build());
        FriendBuilder amyNewNameFriendBuilder = new FriendBuilder().withFriendName("Bob");
        assertTrue(modelManager.hasFriendWithId(amyNewNameFriendBuilder.build().getFriendId()));
    }

    @Test
    public void getFilteredFriendList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFriendsList().remove(0));
    }

    @Test
    public void hasGame_nullGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGame(null));
    }

    @Test
    public void hasGameId_nullGameId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGameWithId(null));
    }

    @Test
    public void hasGame_gameNotInGamesList_returnsFalse() {
        assertFalse(modelManager.hasGame(MINECRAFT));
    }

    @Test
    public void hasGameId_gameIdNotInGamesList_returnsFalse() {
        assertFalse(modelManager.hasGameWithId(MINECRAFT.getGameId()));
    }

    @Test
    public void hasGame_gameInGamesList_returnsTrue() {
        modelManager.addGame(MINECRAFT);
        assertTrue(modelManager.hasGame(MINECRAFT));
    }

    @Test
    public void hasGameId_gameIdInGamesList_returnsTrue() {
        modelManager.addGame(MINECRAFT);
        assertTrue(modelManager.hasGameWithId(MINECRAFT.getGameId()));
    }

    @Test
    public void getFilteredGamesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGamesList().remove(0));
    }

    @Test
    public void equals() {
        FriendsList friendsList = new FriendsListBuilder().withFriend(ALICE).withFriend(BENSON).build();
        GamesList gamesList = new GamesListBuilder().withGame(MINECRAFT).withGame(VALORANT).build();
        FriendsList differentFriendsList = new FriendsList();
        GamesList differentGamesList = new GamesList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(friendsList, gamesList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(friendsList, gamesList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(new FriendsList()));

        // different friendsList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFriendsList, gamesList, userPrefs)));

        // different gamesList -> returns false
        assertFalse(modelManager.equals(new ModelManager(friendsList, differentGamesList, userPrefs)));

        // different filteredFriendsList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredFriendsList(new FriendNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(friendsList, gamesList, userPrefs)));

        // different filteredGamesList -> returns false
        modelManager.updateFilteredGamesList(new GameIdContainsKeywordPredicate(MINECRAFT.getGameId().value));
        assertFalse(modelManager.equals(new ModelManager(friendsList, gamesList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFriendsList(PREDICATE_SHOW_ALL_FRIENDS);
        modelManager.updateFilteredGamesList(PREDICATE_SHOW_ALL_GAMES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFriendsListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(friendsList, gamesList, differentUserPrefs)));
    }
}
