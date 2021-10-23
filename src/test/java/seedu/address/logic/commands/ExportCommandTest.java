package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_GROUP_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_TAG_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ExportCommandTest {
    @TempDir
    public Path testFolder;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_success() {
        ExportCommand command = new ExportCommand();
        Path path = getTempFilePath("execute.csv");
        command.setPath(path);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(command, model,
                String.format(ExportCommand.MESSAGE_SUCCESS, path.toString()), expectedModel);
    }

    @Test
    public void exportImport_sameAb_success() throws Exception {
        ExportCommand command = new ExportCommand();
        Path path = getTempFilePath("exportImport.csv");
        command.setPath(path);
        command.execute(model);

        ImportCommand importCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT, path);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        importCommand.execute(expectedModel);

        assertEquals(model, expectedModel);
    }

    @Test
    public void equals() {
        ExportCommand command = new ExportCommand();
        ExportCommand otherCommand = new ExportCommand();

        // same type
        assertEquals(otherCommand, command);

        otherCommand.setPath(Path.of("abc.csv"));
        // different path
        assertNotEquals(otherCommand, command);

        // null
        assertNotEquals(command, null);

        // different commands
        assertNotEquals(new ClearCommand(), command);

        // different type
        assertNotEquals(command, 1);
    }
}
