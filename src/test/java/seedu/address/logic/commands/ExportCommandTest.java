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
        Path path = getTempFilePath("execute.csv");
        ExportCommand command = new ExportCommand(path);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(command, model,
                String.format(ExportCommand.MESSAGE_SUCCESS, path.toString()), expectedModel);
    }

    @Test
    public void exportImport_sameAb_success() throws Exception {
        Path path = getTempFilePath("exportImport.csv");
        ExportCommand command = new ExportCommand(path);
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
        ExportCommand command = new ExportCommand(Path.of("abc.csv"));
        ExportCommand otherCommand = new ExportCommand(Path.of("abc.csv"));

        // same object
        assertEquals(command, command);

        // same path
        assertEquals(otherCommand, command);

        otherCommand = new ExportCommand(Path.of("def.csv"));
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
