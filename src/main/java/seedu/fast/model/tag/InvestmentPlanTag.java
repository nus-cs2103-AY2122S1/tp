package seedu.fast.model.tag;

import seedu.fast.logic.parser.ParserUtil;

/**
 * An InvestmentPlanTag is a subset of a Tag, having a fixed name uneditable by the user.
 * Guarantees: immutable; name is fixed and valid as declared in {@link #isValidTagName(String)}
 */
public class InvestmentPlanTag extends Tag {

    public static final String INVESTMENT_PLAN_TAG_PREFIX = "ip/";

    public static final String MESSAGE_USAGE = "Investment Plan tag: label a person with a investment plan. "
            + "The priority can be high, med, or low\n"
            + "Similar to tag, to apply an Investment Plan Tag, you have to use Add,Edit, or Tag command.\n\n"
            + "Parameters (using Edit): \n"
            + "edit INDEX t/ pr/PRIORITY\n\n"
            + "Example: \n"
            + "edit 1 t/ pr/low\n"
            + "edit 3 t/ pr/high";
    public static final String PRIORITY_VALIDATION_REGEX = PriorityTag.LowPriority.COMMAND + "|"
            + PriorityTag.MediumPriority.COMMAND + "|"
            + PriorityTag.HighPriority.COMMAND;

    /**
     * Constructs a {@code PriorityTag}.
     * @param tagName A valid tag name.
     */
    public InvestmentPlanTag(String tagName) {
        super(ParserUtil.parseInvestmentPlanTag(tagName));
    }

    /**
     * Class containing relevant fields for a  Tag.
     */

}
