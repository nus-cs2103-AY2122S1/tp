package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix definitions exclusive to Vendor */
    public static final Prefix PREFIX_VENDOR_ID = new Prefix("vid/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_COST = new Prefix("c/");
    public static final Prefix PREFIX_OPERATING_HOURS = new Prefix("oh/");
    public static final Prefix PREFIX_SERVICE_NAME = new Prefix("sn/");

    /* Prefix definitions exclusive to Guest */
    public static final Prefix PREFIX_ROOM_NUMBER = new Prefix("r/");
    public static final Prefix PREFIX_PASSPORT_NUMBER = new Prefix("pn/");

}
