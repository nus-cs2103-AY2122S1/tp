package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final PersonDetails personDetails;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, PersonDetails personDetails) {
        super(FXML);
        this.personDetails = personDetails;
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.getSelectionModel().selectedItemProperty().addListener((
                observable, oldValue, newValue
        ) -> personDetails.setPerson(newValue));

        setSelectedIndex(0);
        personListView.getItems().addListener((ListChangeListener<? super Person>) observable -> {
            setSelectedIndex(0);
        });
    }

    public void setSelectedIndex(int index) {
        if (!personListView.getItems().isEmpty()) {
            personListView.getSelectionModel().select(index);
            personListView.scrollTo(index);
        } else {
            personDetails.setPerson(null);
        }
    }

    public int getSelectedIndex() {
        return personListView.getSelectionModel().getSelectedIndex();
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
