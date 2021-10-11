package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_FRIEND_NAME = new Prefix("n/");
    public static final Prefix FLAG_FRIEND_ID = new Prefix("--id ");
    public static final Prefix FLAG_DELETE = new Prefix("--delete ");
    public static final Prefix FLAG_FRIEND_NAME = new Prefix("--name ");
    public static final Prefix FLAG_FRIEND_SPACE = new Prefix("--friend ");
    public static final Prefix FLAG_FRIEND = new Prefix("--friend");
    public static final Prefix FLAG_GAME_SPACE = new Prefix("--game ");
    public static final Prefix FLAG_GAME = new Prefix("--game");
    public static final Prefix FLAG_USERNAME = new Prefix("--user ");
}
