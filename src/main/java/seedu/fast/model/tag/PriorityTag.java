package seedu.fast.model.tag;

import seedu.fast.logic.parser.ParserUtil;

/**
 * A PriorityTag is a subset of a Tag, having a fixed name uneditable by the user.
 * Guarantees: immutable; name is fixed and valid as declared in {@link #isValidTagName(String)}
 */
public class PriorityTag extends Tag {

    public static final String PRIORITY_VALIDATION_REGEX = LowPriority.command + "|"
            + MediumPriority.command + "|"
            + HighPriority.command;

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
        public static final String name = "Low Priority";

        public static final String term = "low";

        public static final String command = "p/" + term;

    }

    /**
     * Class containing relevant fields for a Medium Priority Tag.
     */
    public class MediumPriority {
        public static final String name = "Medium Priority";

        public static final String term = "medium";

        public static final String command = "p/" + term;

    }

    /**
     * Class containing relevant fields for a High Priority Tag.
     */
    public class HighPriority {
        public static final String name = "High Priority";

        public static final String term = "high";

        public static final String command = "p/" + term;

    }

}
