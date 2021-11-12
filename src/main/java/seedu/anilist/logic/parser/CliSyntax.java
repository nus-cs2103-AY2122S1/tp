package seedu.anilist.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_ACTION = new Prefix("c/");
    public static final Prefix PREFIX_EPISODE = new Prefix("e/");
    public static final Prefix PREFIX_GENRE = new Prefix("g/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");

    /**
     * Gets all of the supported prefixes
     * @return a Prefix Array containing all of the prefixes
     */
    public static Prefix[] getAllPrefixes() {
        return new Prefix[] {
            PREFIX_ACTION, PREFIX_EPISODE, PREFIX_GENRE, PREFIX_NAME, PREFIX_STATUS
        };
    }
}
