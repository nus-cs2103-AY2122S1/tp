package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PocketHotelParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PocketHotelParser pocketHotelParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        pocketHotelParser = new PocketHotelParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = pocketHotelParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveGuestBook(model.getGuestBook());
            storage.saveVendorBook(model.getVendorBook());
            storage.saveArchive(model.getArchive());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyVendorBook getVendorBook() {
        return model.getVendorBook();
    }

    @Override
    public ObservableList<Vendor> getFilteredVendorList() {
        return model.getFilteredVendorList();
    }

    @Override
    public Path getVendorBookFilePath() {
        return model.getVendorBookFilePath();
    }

    @Override
    public ReadOnlyGuestBook getGuestBook() {
        return model.getGuestBook();
    }

    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        return model.getFilteredGuestList();
    }

    @Override
    public Path getGuestBookFilePath() {
        return model.getGuestBookFilePath();
    }

    @Override
    public ReadOnlyGuestBook getArchive() {
        return model.getArchive();
    }

    @Override
    public Path getArchiveFilePath() {
        return model.getArchiveFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
