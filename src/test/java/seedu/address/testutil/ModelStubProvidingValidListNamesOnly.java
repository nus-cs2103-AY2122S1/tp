package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A Model stub that provides a valid filteredList and selectedList with names only
 */
public class ModelStubProvidingValidListNamesOnly extends ModelStub {
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.observableArrayList(TypicalPersons.getTypicalPersonsNameOnly());
    }

    @Override
    public ObservableList<Person> getSelectedPersonList() {
        return FXCollections.observableArrayList(TypicalPersons.getTypicalPersonsNameOnly());
    }
}
