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
    private TabPaneHeader tabPaneHeader;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, PersonDetails personDetails,
                           TabPaneHeader tabPaneHeader) {
        super(FXML);
        this.personDetails = personDetails;
        this.tabPaneHeader = tabPaneHeader;
        this.getRoot().setPrefHeight(Region.USE_PREF_SIZE);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        setSelectedIndex(0);
        if (!personList.isEmpty()) {
            personDetails.setPerson(personList.get(0), false);
        }
        personListView.getSelectionModel().selectedItemProperty().addListener((
                observable, oldValue, newValue
        ) -> personDetails.setPerson(newValue, this.tabPaneHeader.getTabPane().getSelectionModel().isSelected(3)));

        personListView.getItems().addListener((ListChangeListener<? super Person>) observable -> setSelectedIndex(0));
    }

    public void setSelectedIndex(int index) {
        if (!personListView.getItems().isEmpty()) {
            personListView.getSelectionModel().select(index);
            personListView.scrollTo(index);
        } else {
            personDetails.setPerson(null, tabPaneHeader.getTabPane().getSelectionModel().isSelected(3));
        }
    }

    public int getSelectedIndex() {
        return personListView.getSelectionModel().getSelectedIndex();
    }

    public void setTabPaneHeader(TabPaneHeader tabPaneHeader) {
        this.tabPaneHeader = tabPaneHeader;
    }

    public int getTabIndex() {
        return tabPaneHeader.getTabPane().getSelectionModel().getSelectedIndex();
    }

    public void setTabIndex(int index) {
        tabPaneHeader.getTabPane().getSelectionModel().select(index);
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

    /**
     * Refreshes the person list GUI
     */
    public void refreshPersonListUI() {
        personListView.refresh();
        if (getTabIndex() != 0) {
            setTabIndex(0);
        }
        personDetails.setPerson(personListView.getItems().get(getSelectedIndex()));
    }

    /**
     * Opens the current user's Telegram.
     */
    public void openTelegram() {
        this.personDetails.openTelegram();
    }

    /**
     * Opens the current user's GitHub.
     */
    public void openGithub() {
        this.personDetails.openGithub();
    }
}
