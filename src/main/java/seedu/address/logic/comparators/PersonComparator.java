package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.logic.comparators.exceptions.ComparatorException;
import seedu.address.model.category.Category;
import seedu.address.model.person.Person;

/**
 * A general comparator for Persons. Can be specified to compare any Person attributes.
 */
public class PersonComparator {
    public static final String MESSAGE_INVALID_CATEGORY = "Category can only be either \"called\" or \"gender\"\n";
    public static final String VALIDATION_REGEX = "CALLED|GENDER";

    /**
     * Returns the appropriate comparator based on the {@code category} given.
     * @throws ComparatorException if unable to create the appropriate comparator.
     */
    public static Comparator<Person> getComparator(Category category) throws ComparatorException {
        switch (category.category) {
        case "CALLED":
            return new CalledComparator();
        case "GENDER":
            return new GenderComparator();
        default:
            throw new ComparatorException(MESSAGE_INVALID_CATEGORY);
        }
    }
}
