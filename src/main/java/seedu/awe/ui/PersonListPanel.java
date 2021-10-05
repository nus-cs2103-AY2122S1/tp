package seedu.awe.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.awe.commons.core.LogsCenter;
import seedu.awe.model.ReadOnlyAddressBook;
import seedu.awe.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, ReadOnlyAddressBook addressBook) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell(addressBook));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        private final ReadOnlyAddressBook addressBook;

        public PersonListViewCell(ReadOnlyAddressBook addressBook) {
            this.addressBook = addressBook;
        }

        @Override
        protected void updateItem(Person person, boolean isEmpty) {
            super.updateItem(person, isEmpty);

            if (isEmpty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1, this.addressBook).getRoot());
            }
        }
    }

}
