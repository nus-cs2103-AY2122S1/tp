package seedu.tuitione.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_GRADE = new Prefix("g/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_DELETE_REMARK = new Prefix("dr/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");
    public static final Prefix PREFIX_DAY = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_LESSON = new Prefix("l/");
    public static final Prefix PREFIX_COST = new Prefix("c/");
}
