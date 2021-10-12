package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The command is a {@Code friend} command. */
    private final CommandType commandType;

    /**
     * The friend to get during {@Code get} commands or if the {@Code list} command
     * only returns one friend.
     */
    private final Friend friendToGet;

    /**
     * The game to get during {@Code get} commands or if the {@Code list} command
     * only returns one game.
     */
    private final Game gameToGet;

    //TODO : Remove this once merged with main code.
    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.friendToGet = null;
        this.exit = exit;
        this.commandType = null;
        this.gameToGet = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.commandType = commandType;
        this.friendToGet = null;
        this.gameToGet = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, Friend friendToGet) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.commandType = commandType;
        this.friendToGet = friendToGet;
        this.gameToGet = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, Game gameToGet) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.commandType = commandType;
        this.friendToGet = null;
        this.gameToGet = gameToGet;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Friend getFriendToGet() {
        return friendToGet;
    }

    public Game getGameToGet() {
        return gameToGet;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    /** Returns true if the command is a {@Code help} command. */
    public boolean isShowHelp() {
        return showHelp;
    }

    /** Returns true if the command is a {@Code help} command. */
    public boolean isExit() {
        return exit;
    }

    /** Returns true if the command is a {@Code friend --get} command. */
    public boolean isFriendGet() {
        return commandType.equals(CommandType.FRIEND_GET);
    }

    /** Returns true if the command is a {@Code game --get} command. */
    public boolean isGameGet() {
        return commandType.equals(CommandType.GAME_GET);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
