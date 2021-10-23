package seedu.plannermd.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("hp/");
    public static final Prefix PREFIX_EMAIL = new Prefix("eml/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_BIRTH_DATE = new Prefix("dob/");
    public static final Prefix PREFIX_RISK = new Prefix("risk/");

    /* Appointment Prefix definitions */
    public static final Prefix PREFIX_PATIENT = new Prefix("p/");
    public static final Prefix PREFIX_DOCTOR = new Prefix("d/");
    public static final Prefix PREFIX_DURATION = new Prefix("dur/");
    public static final Prefix PREFIX_START = new Prefix("s/");
    public static final Prefix PREFIX_END = new Prefix("e/");

    /* Flag definitions */
    public static final String FLAG_ADD = "-a";
    public static final String FLAG_DELETE = "-d";
    public static final String FLAG_EDIT = "-e";
    public static final String FLAG_FILTER = "-f";
    public static final String FLAG_LIST = "-l";

    /* Appointment Flag definitions */
    public static final String FLAG_FILTER_UPCOMING = "-u";

}
