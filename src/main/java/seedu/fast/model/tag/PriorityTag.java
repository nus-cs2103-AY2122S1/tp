package seedu.fast.model.tag;

import static seedu.fast.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;

import seedu.fast.logic.parser.ParserUtil;

/**
 * A PriorityTag is a subset of a Tag, having a fixed name uneditable by the user.
 * Guarantees: immutable; name is fixed and valid as declared in {@link #isValidTagName(String)}
 */
public class PriorityTag extends Tag {

    public static final String MESSAGE_USAGE = "Priority tag: label a client with a priority. "
            + "The priority can be high, med, or low\n"
            + "Similar to tag, to apply a priority tag, you have to use Add, Edit or Tag command.\n\n"
            + "Parameters (using Edit): \n"
            + "edit INDEX t/ pr/PRIORITY\n\n"
            + "Example: \n"
            + "edit 1 t/ pr/low\n"
            + "edit 3 t/ pr/high\n\n"
            + "Parameters (using Tag): \n"
            + "tag INDEX a/ pr/PRIORITY d/ pr/PRIORITY\n\n"
            + "Example: \n"
            + "tag 1 a/ pr/low d/ pr/high";;


    public static final String PRIORITY_VALIDATION_REGEX = LowPriority.COMMAND + "|"
            + MediumPriority.COMMAND + "|"
            + HighPriority.COMMAND;

    public static final String[] LOWERCASE_PRIORITY_TAG_NAMES = new String[] {LowPriority.NAME.toLowerCase(),
            MediumPriority.NAME.toLowerCase(),
            HighPriority.NAME.toLowerCase()};

    /**
     * Constructs a {@code PriorityTag}.
     * @param tagName A valid tag name.
     */
    public PriorityTag(String tagName) {
        super(ParserUtil.parsePriorityTag(tagName));
    }

    /**
     * Class containing relevant fields for a Low Priority Tag.
     */
    public static class LowPriority {
        public static final String NAME = "LowPriority";

        public static final String TERM = "low";

        public static final String COMMAND = PREFIX_PRIORITY_TAG + TERM;

        public static final int PRIORITY = 3;

        //prevent instantiation of this class
        private LowPriority() {
        }
    }

    /**
     * Class containing relevant fields for a Medium Priority Tag.
     */
    public static class MediumPriority {
        public static final String NAME = "MediumPriority";

        public static final String TERM = "med";

        public static final String COMMAND = PREFIX_PRIORITY_TAG + TERM;

        public static final int PRIORITY = 2;

        //prevent instantiation of this class
        private MediumPriority() {
        }
    }

    /**
     * Class containing relevant fields for a High Priority Tag.
     */
    public static class HighPriority {
        public static final String NAME = "HighPriority";

        public static final String TERM = "high";

        public static final String COMMAND = PREFIX_PRIORITY_TAG + TERM;

        public static final int PRIORITY = 1;

        //prevent instantiation of this class
        private HighPriority() {
        }
    }

}
