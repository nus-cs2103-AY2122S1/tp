package seedu.address.logic.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Parses CSV files for import function.
 */
public class CsvParser {
    public static final String MESSAGE_TOO_MANY_COLUMNS = "Too many columns are present";
    public static final String MESSAGE_MISSING_HEADERS = "Missing required headers";
    public static final String MESSAGE_CSV_FILE_IS_EMPTY = "Failed!\nCsv file is empty";
    public static final String MESSAGE_WRONGLY_FORMATTED_HEADER = "Failed! "
            + "Entries at following rows are wrongly formatted:";

    private static final int NUMBER_OF_FIELDS = 5;

    private static final String NAME_HEADER = "name";
    private static final String PHONE_NUMBER_HEADER = "phone";
    private static final String EMAIL_HEADER = "email";
    private static final String ADDRESS_HEADER = "address";
    private static final String TAGS_HEADER = "tags";

    private final BufferedReader br;
    private final ArrayList<String> wronglyFormattedEntries = new ArrayList<>();
    private final ArrayList<Name> names = new ArrayList<>();
    private final ArrayList<Phone> phoneNumbers = new ArrayList<>();
    private final ArrayList<Email> emails = new ArrayList<>();
    private final ArrayList<Address> addresses = new ArrayList<>();
    private final ArrayList<Set<Tag>> tags = new ArrayList<>();

    private int assignedRowNames = -1;
    private int assignedRowPhoneNumbers = -1;
    private int assignedRowEmails = -1;
    private int assignedRowAddresses = -1;
    private int assignedRowTags = -1;

    public CsvParser(File csvFile) throws IOException {
        br = new BufferedReader(new FileReader(csvFile));
    }

    /**
     * Parses CSV files for import function.
     *
     * @throws ParseException if csv file is formatted wrongly
     * @throws IOException if csv file cannot be read
     */
    public void parse() throws ParseException, IOException {
        parseHeader();
        parseBody();

        if (wronglyFormattedEntries.size() > 0) {
            StringBuilder errorString = new StringBuilder(MESSAGE_WRONGLY_FORMATTED_HEADER);

            for (String entry: wronglyFormattedEntries) {
                errorString.append(System.lineSeparator());
                errorString.append(entry);
            }

            throw new ParseException(errorString.toString());
        }

    }

    private void parseHeader() throws ParseException, IOException {
        String headerRow = br.readLine();

        if (headerRow == null) {
            throw new ParseException(MESSAGE_CSV_FILE_IS_EMPTY);
        }

        String[] inputtedHeaders = headerRow.split(",");

        if (inputtedHeaders.length > NUMBER_OF_FIELDS) {
            throw new ParseException(MESSAGE_TOO_MANY_COLUMNS);
        }

        for (int i = 0; i < inputtedHeaders.length; i++) {
            switch (inputtedHeaders[i]) {
            case NAME_HEADER:
                assignedRowNames = i;
                break;
            case PHONE_NUMBER_HEADER:
                assignedRowPhoneNumbers = i;
                break;
            case EMAIL_HEADER:
                assignedRowEmails = i;
                break;
            case ADDRESS_HEADER:
                assignedRowAddresses = i;
                break;
            case TAGS_HEADER:
                assignedRowTags = i;
                break;
            default:
                throw new ParseException("Unrecognized Header at column" + i);
            }
        }

        if (assignedRowNames == -1 || assignedRowPhoneNumbers == -1
                || assignedRowEmails == -1 || assignedRowAddresses == -1) {
            throw new ParseException(MESSAGE_MISSING_HEADERS);
        }
    }

    private void parseBody() throws ParseException, IOException {
        String line;
        int rowCounter = 1;

        // @@author Scott Robinson-reused
        // Reused regex from https://stackabuse.com/regex-splitting-by-character-unless-in-quotes/
        String regex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        while ((line = br.readLine()) != null) {
            String[] values = line.split(regex);
            rowCounter += 1;

            try {

                Name name = ParserUtil.parseName(values[assignedRowNames]);
                names.add(name);

                Phone phone = ParserUtil.parsePhone(values[assignedRowPhoneNumbers]);
                phoneNumbers.add(phone);

                Email email = ParserUtil.parseEmail(values[assignedRowEmails]);
                emails.add(email);

                String strAddress = values[assignedRowAddresses];
                if (strAddress.startsWith("\"") && strAddress.endsWith("\"")) {
                    strAddress = strAddress.substring(1, strAddress.length() - 1);
                }
                Address address = ParserUtil.parseAddress(strAddress);
                addresses.add(address);

                Set<Tag> tags = ParserUtil.parseTags(new ArrayList<String>());
                if (assignedRowTags != -1 && values.length == NUMBER_OF_FIELDS) {
                    String strTags = values[assignedRowTags];
                    if (!strTags.isBlank()) {
                        tags = ParserUtil.parseTags(Arrays.asList(strTags.split(" ")));
                    }
                }

                this.tags.add(tags);

            } catch (ParseException e) {
                wronglyFormattedEntries.add("Row" + rowCounter + " : " + e.getLocalizedMessage());
            }
        }
        if (rowCounter == 1) {
            throw new ParseException(MESSAGE_CSV_FILE_IS_EMPTY);
        }
    }

    public ArrayList<Name> getNames() {
        return names;
    }

    public ArrayList<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<Set<Tag>> getTags() {
        return tags;
    }

}
