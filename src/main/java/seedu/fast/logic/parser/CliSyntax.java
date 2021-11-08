package seedu.fast.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_APPOINTMENT = new Prefix("d/");
    public static final Prefix PREFIX_APPOINTMENT_TIME = new Prefix("t/");
    public static final Prefix PREFIX_APPOINTMENT_VENUE = new Prefix("v/");
    public static final Prefix PREFIX_ADD_TAG = new Prefix("a/");
    public static final Prefix PREFIX_DELETE_TAG = new Prefix("d/");
    public static final Prefix PREFIX_INVESTMENT_PLAN_TAG = new Prefix("ip/");
    public static final Prefix PREFIX_PRIORITY_TAG = new Prefix("pr/");

}
