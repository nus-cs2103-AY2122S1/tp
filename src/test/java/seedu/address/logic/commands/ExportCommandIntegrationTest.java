package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CsvWriter;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.testutil.ModelStubProvidingEmptyList;
import seedu.address.testutil.ModelStubProvidingValidListNamesOnly;

public class ExportCommandIntegrationTest {
    public static final String TESTED_FILE_PATH = "src"
            + File.separator
            + "test"
            + File.separator
            + "data"
            + File.separator
            + "ExportCommandIntegrationTest"
            + File.separator
            + "testedCsv.csv";

    public static final String EXPECTED_FILE_PATH_NAMES_ONLY = "src"
            + File.separator
            + "test"
            + File.separator
            + "data"
            + File.separator
            + "ExportCommandIntegrationTest"
            + File.separator
            + "expectedCsvNamesOnly.csv";

    @Test
    public void execute_exportCommandNameOnly_success() throws Exception {
        ExportCommand command = new ExportCommand(TESTED_FILE_PATH, new CsvWriter());
        Model model = new ModelStubProvidingValidListNamesOnly();
        command.execute(model);
        assertTrue(FileUtil.areFilesEqual(Paths.get(TESTED_FILE_PATH),
                Paths.get(EXPECTED_FILE_PATH_NAMES_ONLY)));
        FileUtil.deleteFile(Paths.get(TESTED_FILE_PATH));
    }

    @Test
    public void execute_selectedListIsEmpty_throwsCommandException() {
        ExportCommand command = new ExportCommand(TESTED_FILE_PATH, new CsvWriter());
        Model model = new ModelStubProvidingEmptyList();
        assertThrows(CommandException.class, ExportCommand.MSG_NO_CONTACTS, () -> command.execute(model));
    }
}
