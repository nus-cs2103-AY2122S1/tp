package socialite.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");

    /* Social media handle prefix definitions */
    public static final Prefix PREFIX_FACEBOOK = new Prefix("fb/");
    public static final Prefix PREFIX_INSTAGRAM = new Prefix("ig/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("tele/");
    public static final Prefix PREFIX_TIKTOK = new Prefix("tiktok/");
    public static final Prefix PREFIX_TWITTER = new Prefix("twitter/");

    /* Important dates for the person */
    public static final Prefix PREFIX_DATES = new Prefix("dates/");
}
