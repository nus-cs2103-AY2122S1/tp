package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */

    // Common
    public static final Prefix PREFIX_NAME = new Prefix("-n ");

    // Client
    public static final Prefix PREFIX_PHONE_NUMBER = new Prefix("-pn ");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e ");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a ");

    // Product
    public static final Prefix PREFIX_UNIT_PRICE = new Prefix("-$ ");
    public static final Prefix PREFIX_QUANTITY = new Prefix("-q ");

    // todo remove later
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
}
