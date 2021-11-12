package seedu.address.model.person.predicates;

import java.util.function.Predicate;

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
    private final Person correctPerson;

    /**
     * Constructs a StaffHasCorrectIndexPredicate object which returns true if the person tested against is the same as
     * the person corresponding to the index inputted.
     *
     * @param index The index that the user inputted.
     * @param model The model containing the list of Persons who will be searched from.
     */
    public StaffHasCorrectIndexPredicate(int index, Model model) {
        this.index = index;
        this.model = model;
        this.correctPerson = model.getFilteredPersonListByIndex(index);
    }

    @Override
    public boolean test(Person person) {
        return correctPerson.isSamePerson(person);
    }

    /**
     * Returns the index of a StaffHasCorrectIndexPredicate object.
     *
     * @return the index of a StaffHasCorrectIndexPredicate object.
     */
    public int getIndex() {
        return this.index;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffHasCorrectIndexPredicate // instanceof handles nulls
                && index == (((StaffHasCorrectIndexPredicate) other).getIndex())); // state check
    }
}
