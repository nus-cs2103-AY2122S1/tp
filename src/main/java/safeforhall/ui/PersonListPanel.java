package safeforhall.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import safeforhall.commons.core.LogsCenter;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.Logic;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.commands.view.ViewPersonCommand;
import safeforhall.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private boolean isResidentTab = true;
    private final Logic logic;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, Logic logic) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.logic = logic;
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

    @FXML
    private void setSinglePerson() {
        if (isResidentTab) {
            try {
                new ViewPersonCommand(
                        Index.fromZeroBased(personListView.getSelectionModel().getSelectedIndex()))
                        .setSinglePerson(logic.getModel());
            } catch (IndexOutOfBoundsException e) {
                logger.info("Non card area selected");
            } catch (CommandException e) {
                logger.info("Invalid card selected");
            }
        }
    }

    public void setIsResidentTab(boolean isResidentTab) {
        this.isResidentTab = isResidentTab;
    }
}
