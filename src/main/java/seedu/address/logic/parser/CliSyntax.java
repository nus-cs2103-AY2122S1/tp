package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("/n ");
    public static final Prefix PREFIX_PHONE = new Prefix("/ph ");
    public static final Prefix PREFIX_EMAIL = new Prefix("/em ");
    public static final Prefix PREFIX_ADDRESS = new Prefix("/a ");
    public static final Prefix PREFIX_POSITION = new Prefix("/p ");
    public static final Prefix PREFIX_MEMBER_INDEX = new Prefix("/m ");
    public static final Prefix PREFIX_DATE = new Prefix("/d ");
    public static final Prefix PREFIX_EVENT_INDEX = new Prefix("/e ");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("/t ");
    public static final Prefix PREFIX_ATTEND = new Prefix("/att ");
    public static final Prefix PREFIX_ABSENT = new Prefix("/abs ");
    public static final Prefix PREFIX_DONE = new Prefix("/dn ");
    public static final Prefix PREFIX_OVERDUE = new Prefix("/ovd ");
}
