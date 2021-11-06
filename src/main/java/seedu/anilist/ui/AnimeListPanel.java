package seedu.anilist.ui;

import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.ListCommand;
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
    private final CommandExecutor commandExecutor;
    private final ObservableList<Anime> animeList;
    private CommandBox commandBox;

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
    public AnimeListPanel(ObservableList<Anime> animeList,
                          TabOption currentTab, CommandExecutor commandExecutor) {
        super(FXML);

        this.currentTab = currentTab;
        this.commandExecutor = commandExecutor;
        this.animeList = animeList;

        initTab();
    }


    /**
     * Initializes the tab component of Anime List Panel
     */
    public void initTab() {
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
                        if (!commandBox.isExecuting()) {
                            try {
                                if (newValue.equals(allTab)) {
                                    commandExecutor.execute(ListCommand.COMMAND_WORD);
                                } else if (newValue.equals(watchingTab)) {
                                    commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                                            + PREFIX_STATUS + Status.VALID_STATUS_STRING[2]);
                                } else if (newValue.equals(toWatchTab)) {
                                    commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                                            + PREFIX_STATUS + Status.VALID_STATUS_STRING[0]);
                                } else if (newValue.equals(finishedTab)) {
                                    commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                                            + PREFIX_STATUS + Status.VALID_STATUS_STRING[4]);
                                }
                            } catch (CommandException | ParseException e) {
                                logger.log(Level.WARNING, "Wrongly parsed tab commands");
                            }
                        }
                    }
                }
        );
    }

    /**
     * Sets the UI of the active tab based on the value of currentTab
     */
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
     * Sets the tab value to the next tab, loops to `all` if tab is currently on `finished`
     */
    public void setNextTab() {
        try {
            if (currentTab.getCurrentTab() == TabOption.TabOptions.ALL) {
                animeListTabPane.getSelectionModel().select(watchingTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                        + PREFIX_STATUS + Status.VALID_STATUS_STRING[2]);
            } else if (currentTab.getCurrentTab() == TabOption.TabOptions.TOWATCH) {
                animeListTabPane.getSelectionModel().select(finishedTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                        + PREFIX_STATUS + Status.VALID_STATUS_STRING[4]);
            } else if (currentTab.getCurrentTab() == TabOption.TabOptions.WATCHING) {
                animeListTabPane.getSelectionModel().select(toWatchTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                        + PREFIX_STATUS + Status.VALID_STATUS_STRING[0]);
            } else if (currentTab.getCurrentTab() == TabOption.TabOptions.FINISHED) {
                animeListTabPane.getSelectionModel().select(allTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD);
            } else {
                animeListTabPane.getSelectionModel().select(allTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD);
            }
        } catch (CommandException | ParseException e) {
            logger.log(Level.WARNING, "Wrongly parsed tab commands, resetting to all Tab");
        }
    }

    /**
     * Sets the tab value to the previous tab, loops to `finished` if tab is currently on `all`
     */
    public void setPrevTab() {
        try {
            if (currentTab.getCurrentTab() == TabOption.TabOptions.ALL) {
                animeListTabPane.getSelectionModel().select(finishedTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                        + PREFIX_STATUS + Status.VALID_STATUS_STRING[4]);
            } else if (currentTab.getCurrentTab() == TabOption.TabOptions.TOWATCH) {
                animeListTabPane.getSelectionModel().select(watchingTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                        + PREFIX_STATUS + Status.VALID_STATUS_STRING[2]);
            } else if (currentTab.getCurrentTab() == TabOption.TabOptions.WATCHING) {
                animeListTabPane.getSelectionModel().select(allTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD);
            } else if (currentTab.getCurrentTab() == TabOption.TabOptions.FINISHED) {
                animeListTabPane.getSelectionModel().select(toWatchTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD + " "
                        + PREFIX_STATUS + Status.VALID_STATUS_STRING[0]);
            } else {
                animeListTabPane.getSelectionModel().select(allTab);
                commandExecutor.execute(ListCommand.COMMAND_WORD);
            }
        } catch (CommandException | ParseException e) {
            logger.log(Level.WARNING, "Wrongly parsed tab commands, resetting to all Tab");
        }
    }

    public void setCommandBox(CommandBox commandBox) {
        this.commandBox = commandBox;
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
