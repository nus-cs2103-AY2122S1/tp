package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
// import seedu.address.model.policy.Policy; TO BE COMPLETED
import seedu.address.model.person.Person;

public class PolicyListPanel extends UiPart<Region> {
    private static final String FXML = "PolicyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PolicyListPanel.class);

    @FXML
    private ListView<Person> policyListView;

    /**
     * Creates a {@code PolicyListPanel} with the given {@code ObservableList}
     */
    public PolicyListPanel(ObservableList<Person> policyList) {
        super(FXML);
        policyListView.setItems(policyList);
        policyListView.setCellFactory(listView -> new PolicyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Policy} using a {@code PolicyCard}
     */
    class PolicyListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PolicyCard(person, getIndex() +1).getRoot());
            }
        }
    }
}
