package seedu.address.ui;

import java.util.Collections;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.logic.ai.AI;
import seedu.address.logic.ai.ThreadProcessor;
import seedu.address.model.Model;
import seedu.address.model.person.FindABuddyPredicate;
import seedu.address.model.person.IsFavouritePredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class TabPaneHeader extends UiPart<Region> {

    private static final String FXML = "TabPane.fxml";

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab contacts;

    @FXML
    private Tab favorite;

    @FXML
    private Tab events;

    @FXML
    private Tab randomPG;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public TabPaneHeader(Logic logic, ProgressIndicatorRegion indicator) {
        super(FXML);

        Thread fabLoader = new Thread(() -> {
            while (tabPane.getSelectionModel().isSelected(3) && !ThreadProcessor.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (tabPane.getSelectionModel().isSelected(3)) {
                Platform.runLater(() -> getFab(logic, indicator));
            }
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(contacts)) {
                logic.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
                logic.getPersonList().getRoot().setVisible(true);
                indicator.getRoot().setVisible(false);
                logic.sort();
            } else if (newValue.equals(favorite)) {
                logic.updateFilteredPersonList(new IsFavouritePredicate(true));
                logic.getPersonList().getRoot().setVisible(true);
                indicator.getRoot().setVisible(false);
                logic.sort();
            } else if (newValue.equals(events)) {
                logic.updateFilteredPersonList(
                        new TagContainsKeywordsPredicate(Collections.singletonList("colleagues")));
                logic.getPersonList().getRoot().setVisible(true);
                indicator.getRoot().setVisible(false);
                logic.sort();
            } else if (newValue.equals(randomPG)) {
                logic.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
                if (!getFab(logic, indicator)) {
                    fabLoader.start();
                }
            }
        });
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public boolean getFab(Logic logic, ProgressIndicatorRegion indicator) {
        boolean isDone = AI.sortProfiles(logic.getUserProfile(), logic.getModifiableList());
        if (!isDone) {
            indicator.getRoot().setVisible(true);
            logic.updateFilteredPersonList(person -> false);
        } else {
            logic.updateFilteredPersonList(new FindABuddyPredicate());
            indicator.getRoot().setVisible(false);
            logic.getPersonList().getRoot().setVisible(true);
        }
        return isDone;
    }
}
