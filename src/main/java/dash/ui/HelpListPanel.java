package dash.ui;

import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import dash.model.help.HelpCommand;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of help commands.
 */
public class HelpListPanel extends UiPart<Region> {
    private static final String FXML = "HelpListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HelpListPanel.class);

    @FXML
    private ListView<HelpCommand> helpListView;

    /**
     * Creates a {@code HelpListPanel} with the given {@code ObservableList}.
     */
    public HelpListPanel(ObservableList<HelpCommand> helpList) {
        super(FXML);
        helpListView.setItems(helpList);
        helpListView.setCellFactory(listView -> new HelpListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code HelpCommand} using a {@code HelpCard}.
     */
    class HelpListViewCell extends ListCell<HelpCommand> {
        @Override
        protected void updateItem(HelpCommand helpCommand, boolean empty) {
            super.updateItem(helpCommand, empty);

            if (empty || helpCommand == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HelpCard(helpCommand, getIndex() + 1).getRoot());
            }
        }
    }

}
