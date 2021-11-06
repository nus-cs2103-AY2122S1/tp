package safeforhall.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import safeforhall.commons.core.LogsCenter;
import safeforhall.logic.Logic;
import safeforhall.model.person.Person;

/**
 * Panel containing the list of Residents.
 */
public class PersonAdditionalListPanel extends UiPart<Region> {
    private static final String FXML = "PersonAdditionalListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonAdditionalListPanel.class);
    private final Logic logic;

    @FXML
    private ListView<Person> personAdditionalListView;

    /**
     * Creates a {@code PersonAdditionalListPanel} with the given {@code ObservableList}.
     */
    public PersonAdditionalListPanel(ObservableList<Person> personList, Logic logic) {
        super(FXML);
        personAdditionalListView.setItems(personList);
        personAdditionalListView.setCellFactory(listView -> new PersonAdditionalListViewCell());
        this.logic = logic;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonAdditionalCard}.
     */
    class PersonAdditionalListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);
            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonAdditionalCard(person, logic).getRoot());
            }
        }
    }

}
