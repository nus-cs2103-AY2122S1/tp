package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.summary.Summary;

/**
 * Panel containing the list of contacts.
 */
public class ContactListPanel extends UiPart<Region> {
    private static final String FXML = "ContactListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactListPanel.class);

    private SelectedContactCard selected = new SelectedContactCard();

    @FXML
    private ListView<Contact> contactListView;

    @FXML
    private ScrollPane selectedContactPanelPlaceholder;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ContactListPanel(ObservableList<Contact> contactList, Summary summary, CommandBox commandBox) {
        super(FXML);

        setSelectedContactPanel();
        selected.updateSummary(summary);
        selected.setSummary();
        selectedContactPanelPlaceholder.setContent(selected.getRoot());
        updateDetailsIfChanged(contactList);
        contactListView.setItems(contactList);
        contactListView.setCellFactory(listView -> new ContactListViewCell());
        contactListView.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                // Get Model of which contact is selected in the left pane.
                MultipleSelectionModel<Contact> selectedContactModel = contactListView.getSelectionModel();
                Contact selectedContact = selectedContactModel.getSelectedItem();
                if (selectedContact != null) {
                    commandBox.handleViewSelected(selectedContactModel);
                    contactListView.getSelectionModel().clearSelection();
                    logger.info("selected " + selectedContact);
                    selectedContactPanelPlaceholder.setVvalue(0.0);
                    selected.updateContact(selectedContact);
                    selected.setContactDetails();
                }
            }
        });
    }

    /**
     * Displays the details of the specified contact in the selectedContactPanel.
     */
    public void handleDisplay(Contact contactToDisplay) {
        selected.updateContact(contactToDisplay);
        selected.setContactDetails();
    }

    /**
     * Displays the details of the specified contact in the selectedContactPanel.
     */
    public void handleDisplay(Summary summary) {
        selected.updateSummary(summary);
        selected.setSummary();
    }

    public void setSelectedContactPanel() {
        selected.setContactDetails();
    }

    private void updateDetailsIfChanged(ObservableList<Contact> contactList) {
        contactList.addListener(new ListChangeListener<Contact>() {
            @Override
            public void onChanged(Change<? extends Contact> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        int indexToChange = change.getFrom();
                        Contact updatedContact = change.getList().get(indexToChange);
                        contactListView.scrollTo(indexToChange);
                        contactListView.getSelectionModel().select(indexToChange);
                        contactListView.getFocusModel().focus(indexToChange);
                        selected.updateContact(updatedContact);
                    } else if (change.wasRemoved()) {
                        if (contactList.size() > 0) {
                            contactListView.scrollTo(0);
                            contactListView.getSelectionModel().select(0);
                            contactListView.getFocusModel().focus(0);
                        }
                        setSelectedContactPanel();
                    }
                }
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Contact} using a {@code ContactCard}.
     */
    class ContactListViewCell extends ListCell<Contact> {

        @Override
        protected void updateItem(Contact contact, boolean empty) {
            super.updateItem(contact, empty);

            if (empty || contact == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContactCard(contact, getIndex() + 1).getRoot());
            }
        }
    }

}
