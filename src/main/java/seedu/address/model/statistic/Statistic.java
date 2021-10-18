package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Represents a Statistic in the address book.
 * Guarantees: immutable
 */
public class Statistic {

    public final Model model;

    /**
     * Constructs a {@code Statistic}.
     *
     * @param model A valid model.
     */
    public Statistic(Model model) {
        requireNonNull(model);
        this.model = model;
    }

//    private GenderStatistic computeGenderStatistic() {
//        int numberOfMales = 0;
//        int numberOfFemales = 0;
//        int numberOfOthers = 0;
//
//        for (Person p: model.getAddressBook().getPersonList()) {
//            p.
//        }
//    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "";
    }

}
