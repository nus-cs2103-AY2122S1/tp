package seedu.address.logic;

import static seedu.address.MainApp.CIPHER_TRANSFORMATION;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.encryption.Encryption;
import seedu.address.encryption.EncryptionKeyGenerator;
import seedu.address.encryption.EncryptionManager;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private Encryption token;
    private final Path encryptedFilePath;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage, Encryption token, Path encryptedFilePath) {
        this.model = model;
        this.storage = storage;
        this.token = token;
        this.encryptedFilePath = encryptedFilePath;
        addressBookParser = new AddressBookParser();
    }

    /**
     * Executes if the command is a PasswordCommand.
     *
     * @param command The command from user.
     * @return An Optional CommandResult.
     */
    public Optional<CommandResult> executePasswordCommand(Command command) {
        if (command instanceof PasswordCommand) {
            try {
                Encryption temp = new EncryptionManager(EncryptionKeyGenerator
                        .generateKey(((PasswordCommand) command).getOldPassword()), CIPHER_TRANSFORMATION);
                Encryption newToken = new EncryptionManager(EncryptionKeyGenerator
                        .generateKey(((PasswordCommand) command).getNewPassword()), CIPHER_TRANSFORMATION);
                temp.decrypt(encryptedFilePath, storage.getAddressBookFilePath());
                newToken.encrypt(storage.getAddressBookFilePath(), encryptedFilePath);
                token = newToken;
                FileUtil.deleteFile(storage.getAddressBookFilePath());
            } catch (NoSuchPaddingException | InvalidAlgorithmParameterException
                    | UnsupportedPasswordException | InvalidKeyException | NoSuchAlgorithmException e) {
                return Optional.of(new CommandResult(PasswordCommand.MESSAGE_FAIL));
            } catch (IOException e) {
                return Optional.of(new CommandResult(PasswordCommand.MESSAGE_WRONG_PASSWORD));
            }
        }
        return Optional.empty();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);

        Optional<CommandResult> temp = executePasswordCommand(command);

        commandResult = temp.orElse(command.execute(model));
        try { // decrypt -> modify -> encrypt -> delete subroutine
            token.decrypt(encryptedFilePath, storage.getAddressBookFilePath());
            storage.saveAddressBook(model.getAddressBook());
            token.encrypt(storage.getAddressBookFilePath(), encryptedFilePath);
            FileUtil.deleteFile(storage.getAddressBookFilePath());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new CommandException(e.getMessage());
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getSelectedPersonList() {
        return model.getSelectedPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
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
