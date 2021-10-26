package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PHONE = new Prefix("-p");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a");
    public static final Prefix PREFIX_TAG = new Prefix("-l");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("-d");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("-tn");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("-ti");
    public static final Prefix PREFIX_TASK_DATE = new Prefix("-td");
    public static final Prefix PREFIX_TASK_TIME = new Prefix("-tt");
    public static final Prefix PREFIX_TASK_VENUE = new Prefix("-ta");
    public static final Prefix PREFIX_ALL_FLAG = new Prefix("-A");
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("-impt");

    /* Internal Prefix definitions */
    public static final Prefix PREFIX_KEY_DIRECTION = new Prefix("-qq");

}
