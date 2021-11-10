package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final StackPane listLimit;
    private final StackPane personTitle;

    private ListView<Person> personListView;

    @FXML
    private FlowPane personListContainer;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListContainer.setBackground(new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(30), new Insets(10))));

        Text personTitleText = new Text();
        personTitleText.setText("Contacts");
        personTitleText.setFont(Font.font ("Verdana", 40));
        personTitleText.setFill(Color.BLACK);
        personTitleText.setTextAlignment(TextAlignment.LEFT);

        personTitle = new StackPane();
        personTitle.getChildren().add(personTitleText);
        personTitle.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(5))));

        personListView = new ListView<>();
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setMinWidth(550);
        listLimit = new StackPane();
        listLimit.getChildren().add(personListView);
        listLimit.setMaxSize(500, 330);

        personListContainer.setOrientation(Orientation.VERTICAL);
        personListContainer.setVgap(5);
        personListContainer.getChildren().addAll(personTitle, listLimit);
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
