package seedu.programmer.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n ");
    public static final Prefix PREFIX_STUDENT_ID = new Prefix("-sid ");
    public static final Prefix PREFIX_CLASS_ID = new Prefix("-cid ");
    public static final Prefix PREFIX_GRADE = new Prefix("-grade ");

    //todo for test purposes
    public static final Prefix PREFIX_INDEX = new Prefix("-i ");
    public static final Prefix PREFIX_LAB_TITLE = new Prefix("-task ");
    public static final Prefix PREFIX_LAB_RESULT = new Prefix("-s ");
    public static final Prefix PREFIX_LAB_TOTAL = new Prefix("-t ");
}
