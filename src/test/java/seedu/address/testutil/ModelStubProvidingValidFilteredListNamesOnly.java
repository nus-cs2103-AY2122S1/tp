package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A Model stub that provide a valid filteredList
 */
public class ModelStubProvidingValidFilteredListNamesOnly extends ModelStub {
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.observableArrayList(TypicalPersons.getTypicalPersonsNameOnly());
    }
}
