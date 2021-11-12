package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_FRIEND;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD_GAME_SKILL;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_VALUE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.exceptions.GameLinkNotFoundException;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.SkillValue;

public class AddFriendGameSkillCommand extends Command {
    public static final String COMMAND_WORD = "--skill";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_FRIEND + " " + FLAG_ADD_GAME_SKILL + "FRIEND_ID " + FLAG_GAME + "GAME_ID "
            + FLAG_VALUE + "NUMBER_VALUE\n"
            + "Example: "
            + CMD_FRIEND + " " + FLAG_ADD_GAME_SKILL + "myfeely923 " + FLAG_GAME + "CSGO " + FLAG_VALUE + "10";
    public static final String MESSAGE_SUCCESS_ADD_FRIEND_GAME_SKILL = "Updated friend skill - "
            + "FRIEND_ID: %1$s, GAME_ID: %2$s, NUMBER_VALUE: %3$s.";
    public static final String MESSAGE_FRIEND_ID_NOT_IN_MODEL = "Adding skill value failed. "
            + "Could not find FRIEND_ID in friends list.";
    public static final String MESSAGE_NO_GAME_LINK_FOUND = "Adding skill value failed. "
            + "Game_ID provided is not linked to friend.";

    private final FriendId friendIdToAddSkillFor;
    private final GameId gameIdToAddSkillFor;
    private final SkillValue skillValue;

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
        try {
            friendToUpdateSkillValue.updateGameFriendLinkSkillValue(gameIdToAddSkillFor, skillValue);
        } catch (GameLinkNotFoundException glnfe) {
            throw new CommandException(MESSAGE_NO_GAME_LINK_FOUND);
        }
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
