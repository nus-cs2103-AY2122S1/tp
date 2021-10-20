package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalPersons;
import seedu.address.ui.CsvFileSelector;

public class ImportCommandParserTest {
    private static final String ERROR_FORMAT = ImportCommandParser.MESSAGE_WRONGLY_FORMATTED_HEADER
            + System.lineSeparator()
            + "Row2 : "
            + Name.MESSAGE_CONSTRAINTS
            + System.lineSeparator()
            + "Row3 : "
            + Phone.MESSAGE_CONSTRAINTS
            + System.lineSeparator()
            + "Row4 : "
            + Email.MESSAGE_CONSTRAINTS
            + System.lineSeparator()
            + "Row5 : "
            + Tag.MESSAGE_CONSTRAINTS;

    @Test
    public void execute_parseAllColumnsPresent_success() throws Exception {
        CsvParser csvParser = new CsvParserStubProvidingValidEntries();
        csvParser.parse(new CsvFileSelectorDummy());
        ImportCommand producedCommand = new ImportCommandParser().parse(csvParser);
        assertEquals(producedCommand, new ImportCommand(TypicalPersons.getTypicalPersons()));
    }

    @Test
    public void execute_parseMissingOptionalColumns_success() throws Exception {
        CsvParser csvParser = new CsvParserStubMissingOptionalColumns();
        csvParser.parse(new CsvFileSelectorDummy());
        ImportCommand producedCommand = new ImportCommandParser().parse(csvParser);
        assertEquals(producedCommand, new ImportCommand(TypicalPersons.getTypicalPersonsNameOnly()));
    }

    @Test
    public void execute_parseMissingNameColumn_failure() throws Exception {
        CsvParser csvParser = new CsvParserStubMissingNameColumn();
        csvParser.parse(new CsvFileSelectorDummy());

        assertThrows(ParseException.class,
                ImportCommandParser.MESSAGE_NAME_COLUMN_MISSING, () -> new ImportCommandParser().parse(csvParser));
    }

    @Test
    public void execute_parse_failure() throws Exception {
        CsvParser csvParser = new CsvParserStubProvidingInvalidEntries();
        csvParser.parse(new CsvFileSelectorDummy());

        assertThrows(ParseException.class,
                ERROR_FORMAT, () -> new ImportCommandParser().parse(csvParser));
    }

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

    private class CsvParserStubMissingOptionalColumns extends CsvParserStubProvidingValidEntries {
        @Override
        public List<String> get(String columnName) {
            if (columnName != "name") {
                return null;
            }
            return super.get(columnName);
        }
    }

    private class CsvParserStubMissingNameColumn extends CsvParserStubProvidingValidEntries {
        @Override
        public List<String> get(String columnName) {
            if (columnName == "name") {
                return null;
            }
            return super.get(columnName);
        }
    }

    private class CsvParserStubProvidingInvalidEntries extends CsvParser {
        private final Map<String, List<String>> data = new HashMap<>();
        private final List<String> names = new ArrayList<>(Arrays.asList("@lice Pauline",
                    "Benson Meier",
                    "Carl Kurz",
                    "Daniel Meier"));

        private final List<String> phones = new ArrayList<>(Arrays.asList("94351253",
                    "9@765432",
                    "95352563",
                    "87652533"));

        private final List<String> emails = new ArrayList<>(Arrays.asList("alice@example.com",
                    "johnd@example.com",
                    "heinz.com",
                    "cornelia@example.com"));


        private final List<String> addresses = new ArrayList<>(Arrays.asList("123, Jurong West Ave 6, #08-111",
                    "311, Clementi Ave 2, #02-25",
                    "wall street",
                    "10th street"));

        private final List<String> tags = new ArrayList<>(Arrays.asList("friends",
                    "owesMoney friends",
                    "",
                    "#friends"));

        @Override
        public void parse(CsvFileSelector csvFileSelector) {
            data.put("name", names);
            data.put("phone", phones);
            data.put("email", emails);
            data.put("address", addresses);
            data.put("tags", tags);
        }

        @Override
        public int size() {
            return 4;
        }

        @Override
        public List<String> get(String columnName) {
            return data.get(columnName);
        }

    }

    private class CsvFileSelectorDummy extends CsvFileSelector {
        @Override
        public File selectFile() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
