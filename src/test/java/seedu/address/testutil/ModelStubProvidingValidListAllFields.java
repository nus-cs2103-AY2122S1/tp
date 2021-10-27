package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A Model stub that provides a valid filteredList and selectedList with all optional fields
 */
public class ModelStubProvidingValidListAllFields extends ModelStub {
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.observableArrayList(TypicalPersons.getTypicalPersons());
    }

    @Override
    public ObservableList<Person> getSelectedPersonList() {
        return FXCollections.observableArrayList(TypicalPersons.getTypicalPersons());
    }
}
