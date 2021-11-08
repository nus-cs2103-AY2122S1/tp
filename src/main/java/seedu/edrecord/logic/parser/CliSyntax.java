package seedu.edrecord.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_INFO = new Prefix("i/", PrefixIsOptional.YES);
    public static final Prefix PREFIX_MODULE = new Prefix("m/");
    public static final Prefix PREFIX_GROUP = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_WEIGHTAGE = new Prefix("w/");
    public static final Prefix PREFIX_SCORE = new Prefix("s/");
    public static final Prefix PREFIX_STATUS = new Prefix("st/");
    public static final Prefix PREFIX_ID = new Prefix("id/");
}
