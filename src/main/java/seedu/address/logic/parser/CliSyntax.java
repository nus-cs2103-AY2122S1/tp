package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Flag definitions */

    // Command-type words:
    public static final String CMD_FRIEND = "friend";
    public static final String CMD_GAME = "game";

    // Command flags:
    // 1) CRUD commands
    public static final Flag FLAG_ADD = new Flag("--add ");
    public static final Flag FLAG_DELETE = new Flag("--delete ");
    public static final Flag FLAG_ADD_GAME_SKILL = new Flag("--skill ");
    public static final Flag FLAG_EDIT = new Flag("--edit ");

    // 2) view commands
    public static final Flag FLAG_LIST = new Flag("--list ");
    public static final Flag FLAG_GET = new Flag("--get ");

    // 3) link commands
    public static final Flag FLAG_LINK = new Flag("--link ");
    public static final Flag FLAG_UNLINK = new Flag("--unlink ");

    // 4) schedule command
    public static final Flag FLAG_SCHEDULE = new Flag("--schedule ");
    public static final Flag FLAG_TIME = new Flag("-t ");
    public static final Flag FLAG_GAME_ID = new Flag("-g ");

    // Shorter data flags:
    public static final Flag FLAG_GAME = new Flag("-g ");
    public static final Flag FLAG_USERNAME = new Flag("-u ");
    public static final Flag FLAG_ID = new Flag("-i ");
    public static final Flag FLAG_FRIEND_NAME = new Flag("-n ");
    public static final Flag FLAG_VALUE = new Flag("-v ");
    public static final Flag FLAG_PERIOD = new Flag("-p ");
    public static final Flag FLAG_FREE = new Flag("-f ");

    // To be used for flags with optional fields
    public static final Flag FLAG_POSTFIX = new Flag(" ");
}
