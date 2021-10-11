package seedu.fast.model.tag;

import seedu.fast.logic.parser.ParserUtil;

/**
 * A PriorityTag is a subset of a Tag, having a fixed name uneditable by the user.
 * Guarantees: immutable; name is fixed and valid as declared in {@link #isValidTagName(String)}
 */
public class PriorityTag extends Tag {

    public static final String PRIORITY_TAG_PREFIX = "pr/";

    public static final String MESSAGE_USAGE = "priority tag: label a person with a priority. "
        + "The priority can be high, med, or low\n"
        + "Similar to tag, to apply a priority tag, you have to use Add or Edit command.\n\n"
        + "Parameters (using Edit): \n"
        + "edit INDEX t/ pr/PRIORITY\n\n"
        + "Example: \n"
        + "edit 1 t/ pr/low\n"
        + "edit 3 t/ pr/high";
    public static final String PRIORITY_VALIDATION_REGEX = LowPriority.COMMAND + "|"
            + MediumPriority.COMMAND + "|"
            + HighPriority.COMMAND;

    /**
     * Constructs a {@code PriorityTag}.
     *
     * @param tagName A valid tag name.
     */
    public PriorityTag(String tagName) {
        super(ParserUtil.parsePriorityTag(tagName));
    }

    /**
     * Class containing relevant fields for a Low Priority Tag.
     */
    public class LowPriority {

        public static final String NAME = "LowPriority";

        public static final String TERM = "low";

        public static final String COMMAND = PRIORITY_TAG_PREFIX + TERM;

    }

    /**
     * Class containing relevant fields for a Medium Priority Tag.
     */
    public class MediumPriority {

        public static final String NAME = "MediumPriority";

        public static final String TERM = "medium";

        public static final String COMMAND = PRIORITY_TAG_PREFIX + TERM;

    }

    /**
     * Class containing relevant fields for a High Priority Tag.
     */
    public class HighPriority {

        public static final String NAME = "HighPriority";

        public static final String TERM = "high";

        public static final String COMMAND = PRIORITY_TAG_PREFIX + TERM;

    }

}
