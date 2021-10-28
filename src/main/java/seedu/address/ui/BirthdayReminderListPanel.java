package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

public class BirthdayReminderListPanel extends UiPart<Region> {
    private static final String FXML = "BirthdayReminderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BirthdayReminderListPanel.class);

    @javafx.fxml.FXML
    private ListView<Person> birthdayReminderListView;

    /**
     * Creates a {@code BirthdayReminderListPanel} with the given {@code ObservableList}.
     */
    public BirthdayReminderListPanel(ObservableList<Person> personList) {
        super(FXML);
        birthdayReminderListView.setItems(personList);
        birthdayReminderListView.setCellFactory(listView -> new BirthdayReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the birthday reminder for a {@code Person}
     * using a {@code BirthdayReminderCard}.
     */
    class BirthdayReminderListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BirthdayReminderCard(person).getRoot());
            }
        }
    }

}
