package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CsvWriter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.testutil.ModelStubProvidingValidListAllFields;

public class ExportCommandTest {
    public static final String VALID_FILE_PATH = "src"
            + File.separator
            + "test"
            + File.separator
            + "data"
            + File.separator
            + "ExportTest"
            + File.separator
            + "test.csv";

    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExportCommand(null, new CsvWriterStubDoesNothing()));
    }

    @Test
    public void constructor_nullCsvWriter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExportCommand(VALID_FILE_PATH, null));
    }

    @Test
    public void execute_csvWriterIoException_throwsCommandException() {
        ExportCommand command = new ExportCommand(VALID_FILE_PATH, new CsvWriterStubThrowsIoException());
        Model model = new ModelStubProvidingValidListAllFields();
        assertThrows(CommandException.class, ExportCommand.MSG_FILE_WRITE_ERROR, () -> command.execute(model));
    }

    private static class CsvWriterStubDoesNothing extends CsvWriter {

        @Override
        public void write(String filePath, String[] headers, Map<String, List<String>> data) {
        }
    }

    private static class CsvWriterStubThrowsIoException extends CsvWriter {
        @Override
        public void write(String filePath, String[] headers, Map<String, List<String>> data) throws IOException {
            throw new IOException(ExportCommand.MSG_FILE_WRITE_ERROR);
        }
    }
}
