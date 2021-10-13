package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.TypicalPersons;
import seedu.address.ui.CsvFileSelector;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportCommandParserTest {

    @Test
    public void execute_parse_success() throws Exception {
        CsvParser csvParser = new CsvParserStubProvidingValidEntries();
        csvParser.parse(new CsvFileSelectorDummy());
        ImportCommand producedCommand = new ImportCommandParser().parse(csvParser);
        assertEquals(producedCommand, new ImportCommand(TypicalPersons.getTypicalPersons()));
    }

    /*
    @Test
    public void execute_parse_failure() throws Exception {
        CsvParser csvParser = new CsvParserStubProvidingInvalidEntries();
        csvParser.parse(new CsvFileSelectorDummy());
        ImportCommand producedCommand = new ImportCommandParser().parse(csvParser);
        assertEquals(producedCommand, new ImportCommand(TypicalPersons.getTypicalPersons()));
    }
*/

    private class CsvParserStubProvidingValidEntries extends CsvParser {
        private final Map<String, List<String>> data = new HashMap<>();

        @Override
        public void parse(CsvFileSelector csvFileSelector) {
            data.put("name", TypicalPersons.getTypicalNamesStringForm());
            data.put("phone", TypicalPersons.getTypicalPhonesStringForm());
            data.put("email", TypicalPersons.getTypicalEmailsStringForm());
            data.put("address", TypicalPersons.getTypicalAddressesStringForm());
            data.put("tags", TypicalPersons.getTypicalTagsStringForm());
        }

        @Override
        public int size() {
            return 7;
        }

        @Override
        public List<String> get(String columnName) {
            return data.get(columnName);
        }

    }
/*
    private class CsvParserStubProvidingInvalidEntries extends CsvParser {
        private final Map<String, List<String>> data = new HashMap<>();

        @Override
        public void parse(CsvFileSelector csvFileSelector) {
            data.put("name", TypicalPersons.getTypicalNamesStringForm());
            data.put("phone", TypicalPersons.getTypicalPhonesStringForm());
            data.put("email", TypicalPersons.getTypicalEmailsStringForm());
            data.put("address", TypicalPersons.getTypicalAddressesStringForm());
            data.put("tags", TypicalPersons.getTypicalTagsStringForm());
        }

        @Override
        public int size() {
            return 7;
        }

        @Override
        public List<String> get(String columnName) {
            return data.get(columnName);
        }

    }
*/
    private class CsvFileSelectorDummy extends CsvFileSelector {
        @Override
        public File selectFile() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
