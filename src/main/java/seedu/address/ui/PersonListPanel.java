package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ViewTaskListCommand;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    private MainWindow mainWindow;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    public ListView<Person> getPersonListView() {
        return personListView;
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
                assert person != null;

                for (Task task : person.getTasks()) {
                    task.getIsOverdueBooleanProperty().addListener(t -> {
                        this.updateItem(person, empty);
                    });
                    task.getIsDueSoonBooleanProperty().addListener(t -> {
                        this.updateItem(person, empty);
                    });
                }
            }
            this.setOnMouseClicked(e -> {
                if (!this.isEmpty()) {
                    String inputCommand = ViewTaskListCommand.COMMAND_WORD + " " + (getIndex() + 1);
                    mainWindow.handleMouseClicked(inputCommand);
                }
            });
        }
    }

}
