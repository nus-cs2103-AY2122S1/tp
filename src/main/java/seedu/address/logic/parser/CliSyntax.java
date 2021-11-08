package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Prefix definitions */
    /**
     * Stands for PREFIX_MEMBER.
     */
    public static final Prefix PREFIX_MEMBER = new Prefix("-mem/");

    /**
     * Stands for PREFIX_ID.
     */
    public static final Prefix PREFIX_ID = new Prefix("-id/");

    /**
     * Stands for PREFIX_INDEX.
     */
    public static final Prefix PREFIX_INDEX = new Prefix("-i/");

    /**
     * Stands for PREFIX_NAME.
     */
    public static final Prefix PREFIX_NAME = new Prefix("-n/");

    /**
     * Stands for PREFIX_PHONE.
     */
    public static final Prefix PREFIX_PHONE = new Prefix("-p/");

    /**
     * Stands for PREFIX_EMAIL.
     */
    public static final Prefix PREFIX_EMAIL = new Prefix("-e/");

    /**
     * Stands for PREFIX_ADDRESS.
     */
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a/");

    /**
     * Stands for PREFIX_DATE.
     */
    public static final Prefix PREFIX_DATE = new Prefix("-d/");

    /**
     * Stands for PREFIX_CREDIT.
     */
    public static final Prefix PREFIX_CREDIT = new Prefix("-c/");

    /**
     * Stands for PREFIX_REDEEM.
     */
    public static final Prefix PREFIX_REDEEM = new Prefix("-rd/");

    /**
     * Stands for PREFIX_TRANSACTION.
     */
    public static final Prefix PREFIX_TRANSACTION = new Prefix("-txn/");

    /**
     * Stands for PREFIX_BILLING.
     */
    public static final Prefix PREFIX_BILLING = new Prefix("-b/");

    /**
     * Stands for PREFIX_RESERVATION.
     */
    public static final Prefix PREFIX_RESERVATION = new Prefix("-rs/");

    /**
     * Stands for PREFIX_DATE_TIME.
     */
    public static final Prefix PREFIX_DATE_TIME = new Prefix("-dt/");

    /**
     * Stands for PREFIX_REMARK.
     */
    public static final Prefix PREFIX_REMARK = new Prefix("-rm/");

    /**
     * Stands for PREFIX_PASS.
     */
    public static final Prefix PREFIX_PASS = new Prefix("-pass/");

    /**
     * Stands for PREFIX_TAG.
     */
    public static final Prefix PREFIX_TAG = new Prefix("-tag/");


    /* Only used in sort command */
    /**
     * Stands for PREFIX_ASCENDING_ORDER.
     */
    public static final Prefix PREFIX_ASC = new Prefix("-a/");

    /**
     * Stands for PREFIX_DESCENDING_ORDER.
     */
    public static final Prefix PREFIX_DESC = new Prefix("-d/");

}
