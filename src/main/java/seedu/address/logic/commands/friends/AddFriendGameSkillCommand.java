package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_VALUE;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;

public class AddFriendGameSkillCommand extends Command {
    public static final String COMMAND_WORD = "--skill";
    public static final String MESSAGE_SUCCESS_ADD_FRIEND_GAME_SKILL = "Successfully updated skill value for "
            + "friend %1$s for the game %2$s to %3$s.";
    public static final String MESSAGE_USAGE = "To add a skill value for a game linked to friend: \n"
            + "friend "
            + COMMAND_WORD
            + " FRIEND_ID "
            + FLAG_GAME
            + "GAME_ID "
            + FLAG_VALUE
            + "NUMBER_VALUE \n"
            + "Assigns a skill level ranging from 0 - 10 (inclusive) to store friend's game skill. \n"
            + "Example: friend "
            + COMMAND_WORD + " "
            + "myfeely923 "
            + FLAG_GAME
            + "CSGO "
            + FLAG_VALUE
            + "10";
    public static final String MESSAGE_FRIEND_ID_NOT_IN_MODEL = "Adding skill value failed. "
            + "Could not find FRIEND_ID in friends list.";
    public static final String MESSAGE_NO_GAME_LINK_FOUND = "Adding skill value failed. "
            + "Game_ID provided is not linked to friend.";

    private FriendId friendIdToAddSkillFor;
    private GameId gameIdToAddSkillFor;
    private SkillValue skillValue;

    /**
     * Constructor for AddFriendGameSkillCommand that takes in the friend and game to update skill level for
     * and skill value to assign for as the argument.
     *
     * @param friendId   identifier of {@code Friend} to set skill value for.
     * @param gameId     identifier of  {@code Game} to set skill value for.
     * @param skillValue value to set friend's game skill value as.
     */
    public AddFriendGameSkillCommand(FriendId friendId, GameId gameId, SkillValue skillValue) {
        requireNonNull(friendId);
        requireNonNull(gameId);
        requireNonNull(skillValue);

        this.friendIdToAddSkillFor = friendId;
        this.gameIdToAddSkillFor = gameId;
        this.skillValue = skillValue;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasFriendWithId(this.friendIdToAddSkillFor)) {
            throw new CommandException(MESSAGE_FRIEND_ID_NOT_IN_MODEL);
        }
        Friend friendToUpdateSkillValue = model.getFriend(friendIdToAddSkillFor);
        Set<GameFriendLink> friendGameFriendLinks = friendToUpdateSkillValue.getGameFriendLinks();

        Optional<GameFriendLink> linkToUpdate = friendGameFriendLinks.stream().filter(
            gfl -> gfl.getGameId().equals(this.gameIdToAddSkillFor)).findFirst();

        if (linkToUpdate.isEmpty()) {
            throw new CommandException(MESSAGE_NO_GAME_LINK_FOUND);
        }

        linkToUpdate.get().setSkillValue(skillValue);
        return new CommandResult(String.format(MESSAGE_SUCCESS_ADD_FRIEND_GAME_SKILL, friendIdToAddSkillFor,
                gameIdToAddSkillFor, skillValue), CommandType.FRIEND_ADD_GAME_SKILL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFriendGameSkillCommand // instanceof handles nulls
                && friendIdToAddSkillFor.equals(((AddFriendGameSkillCommand) other).friendIdToAddSkillFor)
                && gameIdToAddSkillFor.equals(((AddFriendGameSkillCommand) other).gameIdToAddSkillFor)
                && skillValue.equals(((AddFriendGameSkillCommand) other).skillValue));
    }
}
