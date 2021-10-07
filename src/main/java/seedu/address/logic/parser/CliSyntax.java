package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_FRIEND_NAME = new Prefix("n/");
    public static final Prefix PREFIX_FRIEND_ID = new Prefix("id/");
    public static final Prefix PREFIX_GAME = new Prefix("g/");
    public static final Prefix PREFIX_FRIEND_LIST = new Prefix("--friend");
    public static final Prefix PREFIX_GAME_LIST= new Prefix("--game");
}
