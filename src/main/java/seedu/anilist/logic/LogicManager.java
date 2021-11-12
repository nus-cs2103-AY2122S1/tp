package seedu.anilist.logic;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.logic.commands.Command;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.logic.parser.AnimeListParser;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.Model;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.stats.Stats;
import seedu.anilist.storage.Storage;
import seedu.anilist.ui.TabOption;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private Command lastCmd;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AnimeListParser animeListParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.lastCmd = null;
        this.model = model;
        this.storage = storage;
        this.animeListParser = new AnimeListParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        this.logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        if (this.lastCmd == null || !this.lastCmd.requiresConfirmation()) {
            Command command = animeListParser.parseCommand(commandText);
            assert command != null;
            commandResult = command.execute(model);
            this.lastCmd = command;
        } else {
            //last command present and requires confirmation
            Command command = animeListParser.parseConfirmationCommand(commandText);
            assert command != null;
            commandResult = command.execute(model);
            this.lastCmd = command;
        }

        try {
            storage.saveAnimeList(model.getAnimeList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAnimeList getAnimeList() {
        return model.getAnimeList();
    }

    @Override
    public ObservableList<Anime> getFilteredAnimeList() {
        return model.getFilteredAnimeList();
    }

    @Override
    public Path getAnimeListFilePath() {
        return model.getAnimeListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void setCurrentTab(TabOption.TabOptions currentTab) {
        model.setCurrentTab(currentTab);
    }

    @Override
    public TabOption getCurrentTab() {
        return model.getCurrentTab();
    }

    @Override
    public Stats getStats() {
        return model.getUserStats();
    }

    @Override
    public String getThemeCss() {
        return model.getThemeCss();
    }

    @Override
    public void setThemeCss(String themeCss) {
        requireNonNull(themeCss);
        model.setThemeCss(themeCss);
    }
}
