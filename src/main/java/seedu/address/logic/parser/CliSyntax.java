package seedu.address.logic.parser;

import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EMAIL_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EMPLOYMENT_TYPE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EXPECTED_SALARY_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EXPERIENCE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_INTERVIEW_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_LEVEL_OF_EDUCATION_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_NAME_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_NOTES_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_PHONE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_ROLE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_TAG_SYNTAX;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix(PREFIX_NAME_SYNTAX);
    public static final Prefix PREFIX_PHONE = new Prefix(PREFIX_PHONE_SYNTAX);
    public static final Prefix PREFIX_EMAIL = new Prefix(PREFIX_EMAIL_SYNTAX);
    public static final Prefix PREFIX_TAG = new Prefix(PREFIX_TAG_SYNTAX);
    public static final Prefix PREFIX_ROLE = new Prefix(PREFIX_ROLE_SYNTAX);
    public static final Prefix PREFIX_EMPLOYMENT_TYPE = new Prefix(PREFIX_EMPLOYMENT_TYPE_SYNTAX);
    public static final Prefix PREFIX_EXPECTED_SALARY = new Prefix(PREFIX_EXPECTED_SALARY_SYNTAX);
    public static final Prefix PREFIX_LEVEL_OF_EDUCATION = new Prefix(PREFIX_LEVEL_OF_EDUCATION_SYNTAX);
    public static final Prefix PREFIX_EXPERIENCE = new Prefix(PREFIX_EXPERIENCE_SYNTAX);
    public static final Prefix PREFIX_INTERVIEW = new Prefix(PREFIX_INTERVIEW_SYNTAX);
    public static final Prefix PREFIX_NOTES = new Prefix(PREFIX_NOTES_SYNTAX);

}
