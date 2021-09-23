package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix for academic details */
    public static final Prefix PREFIX_SCHOOL = new Prefix("sch/");
    public static final Prefix PREFIX_ACAD_STREAM = new Prefix("stream/");
    public static final Prefix PREFIX_ACADEMIC_LEVEL = new Prefix("lvl/");
}
