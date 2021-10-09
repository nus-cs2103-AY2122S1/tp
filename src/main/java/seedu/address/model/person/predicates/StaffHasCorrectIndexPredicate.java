package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Index} matches the index given.
 *
 * This class is used in FindCommand.
 */
public class StaffHasCorrectIndexPredicate implements Predicate<Person> {
    private final int index;
    private final Model model;

    public StaffHasCorrectIndexPredicate(int index, Model model) {
        this.index = index;
        this.model = model;
    }

    @Override
    public boolean test(Person person) {
        return person.isSamePerson(model.getFilteredPersonListByIndex(index));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffHasCorrectIndexPredicate // instanceof handles nulls
                && index == (((StaffHasCorrectIndexPredicate) other).index)); // state check
    }
}
