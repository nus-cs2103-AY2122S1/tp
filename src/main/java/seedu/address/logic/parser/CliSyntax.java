package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_SALARY = new Prefix("$/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_DAY_SHIFT = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DATE = new Prefix("da/");
    public static final Prefix PREFIX_SHIFT_TIME = new Prefix("st/");
    public static final Prefix PREFIX_MORNING = new Prefix("morn/");
    public static final Prefix PREFIX_AFTERNOON = new Prefix("aft/");

    // Used when searching
    public static final Prefix PREFIX_DASH_INDEX = new Prefix("-i");
    public static final Prefix PREFIX_DASH_NAME = new Prefix("-n");
    public static final Prefix PREFIX_DASH_PHONE = new Prefix("-p");
    public static final Prefix PREFIX_DASH_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_DASH_ROLE = new Prefix("-r");
    public static final Prefix PREFIX_DASH_SALARY = new Prefix("-$");
    public static final Prefix PREFIX_DASH_TAG = new Prefix("-t");
    public static final Prefix PREFIX_DASH_STATUS = new Prefix("-s");
    public static final Prefix PREFIX_DASH_DAY_SHIFT = new Prefix("-d");
    public static final Prefix PREFIX_DASH_TIME = new Prefix("-ti");

}
