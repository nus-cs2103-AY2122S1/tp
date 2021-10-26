package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TELEGRAM_HANDLE = new Prefix("t/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_GROUP_NAME = new Prefix("g/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix ("d/");
    public static final Prefix PREFIX_NOTE = new Prefix("no/");
    public static final Prefix PREFIX_ASSESSMENT_NAME = new Prefix("a/");
    public static final Prefix PREFIX_SCORE = new Prefix("s/");
}
