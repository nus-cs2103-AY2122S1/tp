package seedu.fast.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.util.AppUtil.checkArgument;

import seedu.fast.commons.util.TagUtil;
/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric "
            + "and not more than 20 characters long, or follow the specified \n"
            + "format for a PriorityTag (low, med or high) or InvestmentPlanTag ()";


    public static final String MESSAGE_USAGE = "tag: label a client with a keyword or term. \n"
        + "Tags can be applied using the Add,Edit or Tag command.\n\n"
        + "Parameters (using Edit): \n"
        + "edit INDEX t/TAG\n\n"
        + "Example: \n"
        + "edit 1 t/High Value Client\n\n"
        + "Parameters (using Tag): \n"
        + "tag INDEX a/ADD_TAG d/DELETE_TAG\n\n"
        + "Example: \n"
        + "tag 1 a/Low Value Client d/Medium Value Client";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    public final int priority;


    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.priority = setPriority(tagName);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX) && isValidTagLength(test);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagTerm(String test) {
        return (test.matches(VALIDATION_REGEX) || test.matches(PriorityTag.PRIORITY_VALIDATION_REGEX))
                && isValidTagLength(test);
    }

    /**
     * Returns true if a given string has a valid length.
     */
    public static boolean isValidTagLength(String test) {
        return test.length() <= TagUtil.MAX_LENGTH_TAG;
    }

    /**
     * Create either an instance of a Tag or a PriorityTag depending on the input term.
     */
    public static Tag createTag(String term) {
        boolean hasPriorityTagTerm = term.matches(PriorityTag.PRIORITY_VALIDATION_REGEX);
        if (hasPriorityTagTerm) {
            return new PriorityTag(term);
        }
        boolean hasInvestmentPlanTerm = term.matches(InvestmentPlanTag.INVESTMENT_PLAN_VALIDATION_REGEX);
        if (hasInvestmentPlanTerm) {
            return new InvestmentPlanTag(term);
        }
        return new Tag(term);
    }

    public static int setPriority(String tagName) {
        if (tagName.equals(PriorityTag.LowPriority.NAME)) {
            return PriorityTag.LowPriority.PRIORITY;
        }
        if (tagName.equals(PriorityTag.MediumPriority.NAME)) {
            return PriorityTag.MediumPriority.PRIORITY;
        }
        if (tagName.equals(PriorityTag.HighPriority.NAME)) {
            return PriorityTag.HighPriority.PRIORITY;
        }
        return TagUtil.NO_PRIORITY;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
