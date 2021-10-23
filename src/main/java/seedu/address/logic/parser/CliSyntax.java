package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_RELATIONSHIP = new Prefix("r/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_FROM = new Prefix("fr/");
    public static final Prefix PREFIX_TO = new Prefix("to/");
    public static final Prefix PREFIX_GOAL = new Prefix("goal/");
    public static final Prefix PREFIX_END_DATE = new Prefix("bydate/");
    public static final Prefix PREFIX_END_TIME = new Prefix("bytime/");
    public static final Prefix PREFIX_VALUE = new Prefix("val/");
}
