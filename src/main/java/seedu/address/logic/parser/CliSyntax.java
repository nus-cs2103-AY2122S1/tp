package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Flag definitions */

    // command flags:
    public static final Flag FLAG_FRIEND = new Flag("--friend ");
    public static final Flag FLAG_GAME = new Flag("--game ");
    public static final Flag FLAG_ADD = new Flag("--add ");
    public static final Flag FLAG_DELETE = new Flag("--delete ");
    public static final Flag FLAG_GET = new Flag("--get ");
    public static final Flag FLAG_LIST = new Flag("--list");
    public static final Flag FLAG_POSTFIX = new Flag(" "); // for flags with optional fields

    // data flags
    public static final Flag FLAG_FRIEND_NAME = new Flag("--name ");
    public static final Flag FLAG_FRIEND_ID = new Flag("--id ");

    // TODO remove once commands are updated
    public static final Flag FLAG_GAME_OLD = new Flag("--g");
    public static final Flag FLAG_FRIEND_SPACE = new Flag("--friend ");



}
