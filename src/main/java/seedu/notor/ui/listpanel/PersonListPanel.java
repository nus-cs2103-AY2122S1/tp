package seedu.notor.ui.listpanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.person.Person;
import seedu.notor.ui.PersonCard;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends ListPanel<Person> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML, personList);
    }

    @Override
    void initializeListView(ObservableList<Person> list) {
        super.listView.setItems(list);
        super.listView.setCellFactory(listView -> new PersonListViewCell());
    }

    public void setPersonList(ObservableList<Person> personList) {
        super.listView.setItems(personList);
        super.listView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
