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
            + "and not more than 20 characters long,\nor follow the specified "
            + "format for a PriorityTag (\"pr/\" followed by low, med or high)\nor a InvestmentPlanTag "
            + "(\"ip/\" followed by health, invest, life, motor, property, save or travel)";

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

    public static final String MESSAGE_SPECIAL_TAG_ENTERED = "A special tag may not be added using its name!\n"
            + "Please use the special syntax found in the Help window or User Guide!";

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
     * Checks if the name of a special tag was entered for a normal tag.
     *
     * @param tagName The tag name.
     * @return A boolean depending on the result.
     */
    public static boolean isSpecialTag(String tagName) {
        String lowercaseTagName = tagName.toLowerCase();
        for (String name: PriorityTag.LOWERCASE_PRIORITY_TAG_NAMES) {
            if (name.equals(lowercaseTagName)) {
                return true;
            }
        }
        for (String name: InvestmentPlanTag.LOWERCASE_INVESTMENT_PLAN_TAG_NAMES) {
            if (name.equals(lowercaseTagName)) {
                return true;
            }
        }
        return false;
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
        boolean isNormalTag = test.matches(VALIDATION_REGEX);
        boolean isPriorityTag = test.matches(PriorityTag.PRIORITY_VALIDATION_REGEX);
        boolean isInvestmentPlanTag = test.matches(InvestmentPlanTag.INVESTMENT_PLAN_VALIDATION_REGEX);
        boolean isValidLength = isValidTagLength(test);
        return (isNormalTag || isPriorityTag || isInvestmentPlanTag) && isValidLength;
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
