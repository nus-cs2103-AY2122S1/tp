package seedu.anilist.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Status;

/**
 * Panel containing the list of anime.
 */
public class AnimeListPanel extends UiPart<Region> {
    private static final String FXML = "AnimeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AnimeListPanel.class);
    private final TabOption currentTab;

    //stats to be displayed
    private int numWatching;
    private int numToWatch;
    private int numFinished;

    @FXML
    private TabPane animeListTabPane;

    @FXML
    private ListView<Anime> animeListView;

    @FXML
    private ListView<Anime> watchingListView;

    @FXML
    private ListView<Anime> toWatchListView;

    @FXML
    private ListView<Anime> finishedListView;

    @FXML
    private Tab allTab;

    @FXML
    private Tab watchingTab;

    @FXML
    private Tab toWatchTab;

    @FXML
    private Tab finishedTab;

    /**
     * Creates a {@code AnimeListPanel} with the given {@code ObservableList}.
     */
    public AnimeListPanel(ObservableList<Anime> animeList, TabOption currentTab, CommandExecutor commandExecutor) {
        super(FXML);
        animeListView.setItems(animeList);
        animeListView.setCellFactory(listView -> new AnimeListViewCell());

        watchingListView.setItems(animeList);
        watchingListView.setCellFactory(listView -> new AnimeListViewCell());

        toWatchListView.setItems(animeList);
        toWatchListView.setCellFactory(listView -> new AnimeListViewCell());

        finishedListView.setItems(animeList);
        finishedListView.setCellFactory(listView -> new AnimeListViewCell());

        animeListTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                        try {
                            if (newValue.equals(allTab)) {
                                commandExecutor.execute("list");
                            } else if (newValue.equals(watchingTab)) {
                                commandExecutor.execute("list s/watching");
                            } else if (newValue.equals(toWatchTab)) {
                                commandExecutor.execute("list s/towatch");
                            } else if (newValue.equals(finishedTab)) {
                                commandExecutor.execute("list s/finished");
                            }
                        } catch (CommandException | ParseException e) {
                            logger.log(Level.WARNING, "Wrongly parsed tab commands");
                        }
                    }
                });

            //Update anime stats upon change
            animeList.addListener((ListChangeListener<Anime>) anime -> {
                if (anime.next()) {
                    setStats();
                }
            });

        System.out.println("STATS UPDATED: WATCHING " + numWatching);
        this.currentTab = currentTab;
        setStats();
    }

    /**
     * Resets the stats when the anime list changes so they could be recalculated.
     */
    private void resetStats() {
        //resets the stats when the anime list changes so they could be recalculated
        numWatching = 0;
        numToWatch = 0;
        numFinished = 0;
    }

    /**
     * Updates anime list stats.
     */
    private void setStats() {
        resetStats();
        for (Anime anime: animeListView.getItems()) {
            Status.WatchStatus watchStatus = anime.getStatus().status;
            if (watchStatus.equals(Status.WatchStatus.WATCHING)) {
                numWatching += 1;
            } else if (watchStatus.equals(Status.WatchStatus.TOWATCH)) {
                numToWatch += 1;
            } else {
                //TODO add assertion that it's finished
                numFinished += 1;
            }
        }
    }

    public int getNumWatching() {
        return this.numWatching;
    }

    public int getNumToWatch() {
        return this.numToWatch;
    }

    public int getNumFinished() {
        return this.numFinished;
    }

    public void setActiveTab() {
        if (currentTab.getCurrentTab() == TabOption.TabOptions.ALL) {
            animeListTabPane.getSelectionModel().select(0);
        } else if (currentTab.getCurrentTab() == TabOption.TabOptions.TOWATCH) {
            animeListTabPane.getSelectionModel().select(toWatchTab);
        } else if (currentTab.getCurrentTab() == TabOption.TabOptions.WATCHING) {
            animeListTabPane.getSelectionModel().select(watchingTab);
        } else if (currentTab.getCurrentTab() == TabOption.TabOptions.FINISHED) {
            animeListTabPane.getSelectionModel().select(finishedTab);
        } else {
            animeListTabPane.getSelectionModel().select(allTab);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Anime} using a {@code AnimeCard}.
     */
    class AnimeListViewCell extends ListCell<Anime> {
        @Override
        protected void updateItem(Anime anime, boolean empty) {
            super.updateItem(anime, empty);
            if (empty || anime == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AnimeCard(anime, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.anilist.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}
