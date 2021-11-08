package seedu.smartnus.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("i/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_QUESTION = new Prefix("qn/");
    public static final Prefix PREFIX_OPTION = new Prefix("opt/");
    public static final Prefix PREFIX_ANSWER = new Prefix("ans/");
    public static final Prefix PREFIX_KEYWORD = new Prefix("k/");
    public static final Prefix PREFIX_NUMBER = new Prefix("n/");
    public static final Prefix PREFIX_LIMIT = new Prefix("lim/");

    public static final Prefix PREFIX_NOTE = new Prefix("note/");

}
