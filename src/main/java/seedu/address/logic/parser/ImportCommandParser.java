package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ImportCommandParser {
    public static final String MESSAGE_WRONGLY_FORMATTED_HEADER = "Failed! "
            + "Entries at following rows are wrongly formatted:";

    private CsvParser csvParser;
    private final List<Person> personsToAdd = new ArrayList<>();
    private final ArrayList<String> wronglyFormattedEntries = new ArrayList<>();

    private boolean isTagColumnPresent = false;

    private List<String> csvNames;
    private List<String> csvPhones;
    private List<String> csvEmails;
    private List<String> csvAddresses;
    private List<String> csvTags;

    private final List<Name> names = new ArrayList<>();
    private final List<Phone> phones = new ArrayList<>();
    private final List<Email> emails = new ArrayList<>();
    private final List<Address> addresses = new ArrayList<>();
    private final List<Set<Tag>> tags = new ArrayList<>();

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(CsvParser csvParser) throws ParseException {
        this.csvParser = csvParser;
        parseHeader();
        parseColumns();

        if (wronglyFormattedEntries.size() > 0) {
            StringBuilder errorString = new StringBuilder(MESSAGE_WRONGLY_FORMATTED_HEADER);

            for (String entry: wronglyFormattedEntries) {
                errorString.append(System.lineSeparator());
                errorString.append(entry);
            }

            throw new ParseException(errorString.toString());
        }

        for (int i = 0; i < csvParser.size(); i++) {
            Name name = names.get(i);
            Phone phone = phones.get(i);
            Email email = emails.get(i);
            Address address = addresses.get(i);
            Set<Tag> tagsForIndividual = tags.get(i);

            personsToAdd.add(new Person(name, phone, email, address, tagsForIndividual));
        }

        return new ImportCommand(personsToAdd);
    }

    private void parseHeader() throws ParseException {
        csvNames = csvParser.get("name");
        csvPhones = csvParser.get("phone");
        csvEmails = csvParser.get("email");
        csvAddresses = csvParser.get("address");
        csvTags = csvParser.get("tags");

        if (csvTags != null) {
            isTagColumnPresent = true;
        }
        if (csvNames == null) {
            throw new ParseException("Name column is missing");
        }
        if (csvPhones == null) {
            throw new ParseException("Phone Number column is missing");
        }
        if (csvEmails == null) {
            throw new ParseException("Email column is missing");
        }
        if (csvAddresses == null) {
            throw new ParseException("Address column is missing");
        }
    }

    private void parseColumns() {
        int i = 0;

        while (i < csvParser.size()) {
            try {
                names.add(ParserUtil.parseName(csvNames.get(i)));
                phones.add(ParserUtil.parsePhone(csvPhones.get(i)));
                emails.add(ParserUtil.parseEmail(csvEmails.get(i)));
                addresses.add(ParserUtil.parseAddress(csvAddresses.get(i)));

                List<String> inputtedTags = new ArrayList<>();

                if (isTagColumnPresent && !csvTags.get(i).equals("")) {
                    inputtedTags = Arrays.asList(csvTags.get(i).split(" "));
                }

                tags.add(ParserUtil.parseTags(inputtedTags));
            } catch (ParseException e) {
                wronglyFormattedEntries.add("Row" + (i + 2) + " : " + e.getLocalizedMessage());
            }
            i++;
        }

    }
}
