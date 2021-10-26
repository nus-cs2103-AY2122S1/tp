package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A Model stub that provides empty filteredList and selectedList
 */
public class ModelStubProvidingEmptyList extends ModelStub {
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.observableArrayList();
    }

    @Override
    public ObservableList<Person> getSelectedPersonList() {
        return FXCollections.observableArrayList();
    }
}
