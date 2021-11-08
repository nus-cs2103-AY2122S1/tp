package seedu.address.model;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Captures the entire state of a ModelManager
 */
public class ModelManagerState {
    private final AddressBook addressBookSnapshot;
    private final UserPrefs userPrefsSnapshot;
    private final Predicate<Person> filterPredicateSnapshot;

    /**
     * Returns a ModelManagerState that fully encapsulates the state of Model
     * @param addressBookSnapshot address book of model to be captured
     * @param userPrefsSnapshot userPrefs of model to be captured
     * @param filterPredicateSnapshot filterPredicate of model to be captured
     */
    public ModelManagerState(AddressBook addressBookSnapshot,
                             UserPrefs userPrefsSnapshot,
                             Predicate<Person> filterPredicateSnapshot) {
        this.addressBookSnapshot = addressBookSnapshot;
        this.userPrefsSnapshot = userPrefsSnapshot;
        this.filterPredicateSnapshot = filterPredicateSnapshot;
    }

    public AddressBook getAddressBook() {
        return addressBookSnapshot;
    }

    public UserPrefs getUserPrefs() {
        return userPrefsSnapshot;
    }

    @SuppressWarnings("unchecked")
    public Predicate<Person> getFilterPredicate() {
        return this.filterPredicateSnapshot != null
                ? filterPredicateSnapshot
                : PREDICATE_SHOW_ALL_PERSONS;
    }
}
