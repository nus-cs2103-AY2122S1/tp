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
    public static final Prefix PREFIX_SELLER = new Prefix("s/");
    public static final Prefix PREFIX_PRICE = new Prefix("$/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ADD_TAG = new Prefix("ta/");
    public static final Prefix PREFIX_DELETE_TAG = new Prefix("td/");
    public static final Prefix PREFIX_MIN_PRICE = new Prefix("$min/");
    public static final Prefix PREFIX_MAX_PRICE = new Prefix("$max/");
}
