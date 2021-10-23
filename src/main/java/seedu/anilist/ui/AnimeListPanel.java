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
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Status;

/**
 * Panel containing the list of anime.
 */
public class AnimeListPanel extends UiPart<Region> {
    private static final String FXML = "AnimeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AnimeListPanel.class);
    private final TabOption currentTab;
    private final ReadOnlyAnimeList animeListMain;

    //stats to be displayed
    private int watchingCount;
    private int toWatchCount;
    private int finishedCount;

    private int episodesCount;

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

    @FXML
    private Tab statsTab;

    /**
     * Creates a {@code AnimeListPanel} with the given {@code ObservableList}.
     */
    public AnimeListPanel(ReadOnlyAnimeList animeListMain, ObservableList<Anime> animeList,
                          TabOption currentTab, CommandExecutor commandExecutor) {
        super(FXML);
        this.animeListMain = animeListMain;

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
            animeListMain.getAnimeList().addListener((ListChangeListener<Anime>) anime -> {
                if (anime.next()) {
                    setStats();
                }
            });

        logger.log(Level.INFO, "Stats updated.");
        this.currentTab = currentTab;
        setStats();
    }

    /**
     * Resets the stats when the anime list changes so they could be recalculated.
     */
    private void resetStats() {
        //resets the stats when the anime list changes so they could be recalculated
        watchingCount = 0;
        toWatchCount = 0;
        finishedCount = 0;
        episodesCount = 0;
    }

    /**
     * Updates anime list stats.
     */
    private void setStats() {
        resetStats();
        for (Anime anime: animeListMain.getAnimeList()) {
            episodesCount += anime.getEpisode().getValue();
            Status.WatchStatus watchStatus = anime.getStatus().status;
            if (watchStatus.equals(Status.WatchStatus.WATCHING)) {
                watchingCount += 1;
            } else if (watchStatus.equals(Status.WatchStatus.TOWATCH)) {
                toWatchCount += 1;
            } else {
                assert watchStatus.equals(Status.WatchStatus.FINISHED);
                finishedCount += 1;
            }
        }
    }

    public int getWatchingCount() {
        return this.watchingCount;
    }

    public int getToWatchCount() {
        return this.toWatchCount;
    }

    public int getFinishedCount() {
        return this.finishedCount;
    }
    
    public int getEpisodesCount() {
        return this.getEpisodesCount();
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
        } else if (currentTab.getCurrentTab() == TabOption.TabOptions.STATS) {
            animeListTabPane.getSelectionModel().select(statsTab);
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
