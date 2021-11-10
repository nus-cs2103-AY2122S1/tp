package seedu.address.ui;

import java.util.Hashtable;
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
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of persons.
 */
public class TagListPanel extends UiPart<Region> {

    private static final String FXML = "TagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    private final Hashtable<Tag, Integer> tagInfoHashTable;
    private final StackPane listLimit;
    private final StackPane tagTitle;
    private final ListView<Tag> tagListView;


    @FXML
    private FlowPane tagListContainer;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TagListPanel(Hashtable<Tag, Integer> tagInfoHashTable,
                        ObservableList<Tag> tagList) {
        super(FXML);
        this.tagInfoHashTable = tagInfoHashTable;
        tagListContainer.setBackground(new Background(new BackgroundFill(Color.WHITE,
                new CornerRadii(30), new Insets(10))));

        Text tagTitleText = new Text();
        tagTitleText.setText("Tags");
        tagTitleText.setFont(Font.font ("Verdana", 40));
        tagTitleText.setFill(Color.BLACK);
        tagTitleText.setTextAlignment(TextAlignment.LEFT);

        tagTitle = new StackPane();
        tagTitle.getChildren().add(tagTitleText);
        tagTitle.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), new Insets(5))));
        tagTitle.setMaxHeight(40);

        tagListView = new ListView<>();
        tagListView.setItems(tagList);
        tagListView.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), new Insets(5))));
        tagListView.setCellFactory(listView -> new TagListViewCell());
        tagListView.setMinWidth(350);
        listLimit = new StackPane();
        listLimit.getChildren().add(tagListView);
        listLimit.setMaxSize(500, 330);

        tagListContainer.setOrientation(Orientation.VERTICAL);
        tagListContainer.setVgap(5);
        tagListContainer.getChildren().addAll(tagTitle, listLimit);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TagListViewCell extends ListCell<Tag> {
        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagCard(tag, tagInfoHashTable.get(tag)).getRoot());
                setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), Insets.EMPTY)));
            }
        }
    }

}
