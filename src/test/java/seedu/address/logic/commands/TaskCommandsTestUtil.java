package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

public class TaskCommandsTestUtil {

    public static final String VALID_TASK_NAME_STUDY = "Study";
    public static final String VALID_TASK_NAME_PLAY = "Play";
    public static final String VALID_TASK_DESCRIPTION_STUDY = "Revise for CS2103";
    public static final String VALID_TASK_DESCRIPTION_PLAY = "PLay soccer with Alex";
    public static final String VALID_TASK_TAG_STUDY = "study";
    public static final String VALID_TASK_TAG_WORK = "work";
    public static final String VALID_TASK_TAG_FUN = "fun";
    public static final String VALID_TASK_TAG_EXERCISE = "exercise";

    public static final String TASK_NAME_DESC_STUDY = " " + PREFIX_NAME + VALID_TASK_NAME_STUDY;
    public static final String TASK_NAME_DESC_PLAY = " " + PREFIX_NAME + VALID_TASK_NAME_PLAY;
    public static final String TASK_DESCRIPTION_DESC_STUDY = " " + PREFIX_DESCRIPTION + VALID_TASK_DESCRIPTION_STUDY;
    public static final String TASK_DESCRIPTION_DESC_PLAY = " " + PREFIX_DESCRIPTION + VALID_TASK_DESCRIPTION_PLAY;
    public static final String TASK_TAG_DESC_STUDY = " " + PREFIX_TAG + VALID_TASK_TAG_STUDY;
    public static final String TASK_TAG_DESC_WORK = " " + PREFIX_TAG + VALID_TASK_TAG_WORK;
    public static final String TASK_TAG_DESC_FUN = " " + PREFIX_TAG + VALID_TASK_TAG_FUN;
    public static final String TASK_TAG_DESC_EXERCISE = " " + PREFIX_TAG + VALID_TASK_TAG_EXERCISE;

    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_NAME + "Study&"; // '&' not allowed in names
    public static final String INVALID_TASK_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "&"; // '&' not allowed in desc
    public static final String INVALID_TASK_TAG_DESC = " " + PREFIX_TAG + "fun*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
}
