package seedu.address.ui;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.logic.ai.Ai;
import seedu.address.logic.ai.ThreadProcessor;
import seedu.address.model.Model;
import seedu.address.model.person.FindABuddyPredicate;
import seedu.address.model.person.IsEventTagPredicate;
import seedu.address.model.person.IsFavoritePredicate;

/**
 * Ui component for navigating between different tabs.
 */
public class TabPaneHeader extends UiPart<Region> {

    private static final String FXML = "TabPane.fxml";

    private final Thread fabLoader;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab contacts;

    @FXML
    private Tab favorite;

    @FXML
    private Tab events;

    @FXML
    private Tab findABuddy;

    /**
     * Creates a {@code TabPaneHeader} with the given {@code Logic} and {@code ProgressIndicatorRegion}.
     */
    public TabPaneHeader(Logic logic, ProgressIndicatorRegion indicator) {
        super(FXML);

        AtomicReference<AtomicBoolean> isFabLoaderRunning = new AtomicReference<>(new AtomicBoolean(false));

        fabLoader = new Thread(() -> {
            while (tabPane.getSelectionModel().isSelected(3) && !ThreadProcessor.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //
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
                stopFabLoader();
            } else if (newValue.equals(favorite)) {
                logic.updateFilteredPersonList(new IsFavoritePredicate(true));
                logic.getPersonList().getRoot().setVisible(true);
                indicator.getRoot().setVisible(false);
                logic.sort();
                stopFabLoader();
            } else if (newValue.equals(events)) {
                logic.updateFilteredPersonList(new IsEventTagPredicate());
                logic.getPersonList().getRoot().setVisible(true);
                indicator.getRoot().setVisible(false);
                logic.sort();
                stopFabLoader();
            } else if (newValue.equals(findABuddy)) {
                logic.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
                if (!getFab(logic, indicator)) {
                    if (!isFabLoaderRunning.get().get()) {
                        fabLoader.start();
                        isFabLoaderRunning.get().set(true);
                    }
                }
            }
        });
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    /**
     * Sorts contacts based on GitHub similarity, using {@link Ai}.
     *
     * @param logic Logic instance of CohortConnect.
     * @param indicator Loading indicator component.
     * @return Whether the contacts have been sorted.
     */
    public boolean getFab(Logic logic, ProgressIndicatorRegion indicator) {
        boolean isDone = Ai.sortProfiles(logic.getUserProfile(), logic.getModifiableList());
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

    /**
     * Interrupt fab loader thread
     */
    public void stopFabLoader() {
        fabLoader.interrupt();
    }

    /**
     * Activates the Contacts tab.
     *
     * @param logic Logic instance of CohortConnect.
     */
    public void activateContacts(Logic logic) {
        tabPane.getSelectionModel().select(0);
    }

    /**
     * Activates the Favorites tab.
     *
     * @param logic Logic instance of CohortConnect.
     */
    public void activateFavorites(Logic logic) {
        tabPane.getSelectionModel().select(1);
    }

    /**
     * Activates the Events tab.
     *
     * @param logic Logic instance of CohortConnect.
     */
    public void activateEvents(Logic logic) {
        tabPane.getSelectionModel().select(2);
    }

    /**
     * Activates the Find A Buddy tab.
     *
     * @param logic Logic instance of CohortConnect.
     */
    public void activateFindABuddy(Logic logic) {
        tabPane.getSelectionModel().select(3);
    }
}
