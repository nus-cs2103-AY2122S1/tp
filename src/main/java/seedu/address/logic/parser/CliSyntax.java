package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_PARENT_PHONE = new Prefix("pp/");
    public static final Prefix PREFIX_PARENT_EMAIL = new Prefix("pe/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_FEE = new Prefix("f/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix definitions for lesson */
    public static final Prefix PREFIX_RECURRING = new Prefix("recurring/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_START_TIME = new Prefix("start/");
    public static final Prefix PREFIX_END_TIME = new Prefix("end/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("subject/");
    public static final Prefix PREFIX_HOMEWORK = new Prefix("hw/");

    /* Prefix definitions for command options */
    public static final Prefix PREFIX_FIND_CONDITION = new Prefix("cond/");
}
