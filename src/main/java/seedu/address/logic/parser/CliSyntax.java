package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("name=");
    public static final Prefix PREFIX_PHONE = new Prefix("phone=");
    public static final Prefix PREFIX_EMAIL = new Prefix("email=");
    public static final Prefix PREFIX_ADDRESS = new Prefix("address=");
    public static final Prefix PREFIX_TAG = new Prefix("tag=");
    public static final Prefix PREFIX_REMARK = new Prefix("remark=");
    public static final Prefix PREFIX_STATUS = new Prefix("status=");
    public static final Prefix PREFIX_TITLE = new Prefix("title=");
    public static final Prefix PREFIX_POSITION = new Prefix("position=");
    public static final Prefix PREFIX_POSITION_STATUS = new Prefix("status=");
    public static final Prefix PREFIX_CANDIDATE_INDEX = new Prefix("c=");
    public static final Prefix PREFIX_INTERVIEW_INDEX = new Prefix("i=");
    public static final Prefix PREFIX_DATE = new Prefix("date=");
    public static final Prefix PREFIX_TIME = new Prefix("time=");
    public static final Prefix PREFIX_DURATION = new Prefix("duration=");
    public static final Prefix PREFIX_INTERVIEW_STATUS = new Prefix("interviewed=");

}
