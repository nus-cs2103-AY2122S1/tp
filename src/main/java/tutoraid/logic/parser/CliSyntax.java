package tutoraid.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_STUDENT_NAME = new Prefix("sn/");
    public static final Prefix PREFIX_STUDENT_PHONE = new Prefix("sp/");
    public static final Prefix PREFIX_PARENT_NAME = new Prefix("pn/");
    public static final Prefix PREFIX_PARENT_PHONE = new Prefix("pp/");
    public static final Prefix PREFIX_LESSON_NAME = new Prefix("n/");
    public static final Prefix PREFIX_LESSON_PRICE = new Prefix("p/");
    public static final Prefix PREFIX_LESSON_TIMING = new Prefix("t/");
    public static final Prefix PREFIX_LESSON_CAPACITY = new Prefix("c/");
    public static final Prefix PREFIX_LIST_ALL = new Prefix("-a");
    public static final Prefix PREFIX_STUDENT = new Prefix("s/");
    public static final Prefix PREFIX_LESSON = new Prefix("l/");
}
