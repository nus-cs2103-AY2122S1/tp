package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    public static final String MESSAGE_NAME_COLUMN_MISSING = "Name column is missing";

    private CsvParser csvParser;
    private final List<Person> personsToAdd = new ArrayList<>();
    private final List<String> wronglyFormattedEntries = new ArrayList<>();

    private List<String> csvNames;
    private Optional<List<String>> csvPhones;
    private Optional<List<String>> csvEmails;
    private Optional<List<String>> csvAddresses;
    private Optional<List<String>> csvTags;

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
        csvPhones = Optional.ofNullable(csvParser.get("phone"));
        csvEmails = Optional.ofNullable(csvParser.get("email"));
        csvAddresses = Optional.ofNullable(csvParser.get("address"));
        csvTags = Optional.ofNullable(csvParser.get("tags"));

        if (csvNames == null) {
            throw new ParseException(MESSAGE_NAME_COLUMN_MISSING);
        }
    }

    private void parseColumns() {
        for (int i = 0; i < csvParser.size(); i++) {
            try {
                names.add(ParserUtil.parseName(csvNames.get(i)));
                int finalI = i;
                Optional<String> inputtedPhone = csvPhones.map(x -> x.get(finalI));
                Optional<String> inputtedEmail = csvEmails.map(x -> x.get(finalI));
                Optional<String> inputtedAddress = csvAddresses.map(x -> x.get(finalI));
                Optional<List<String>> inputtedTags = csvTags.map(x -> {
                    if (x.get(finalI).equals("")) {
                        return new ArrayList<>();
                    } else {
                        return Arrays.asList(x.get(finalI).split(";"));
                    }
                });

                phones.add(ParserUtil.parsePhone(inputtedPhone.orElse("")));
                emails.add(ParserUtil.parseEmail(inputtedEmail.orElse("")));
                addresses.add(ParserUtil.parseAddress(inputtedAddress.orElse("")));
                tags.add(ParserUtil.parseTags(inputtedTags.orElse(new ArrayList<>())));
            } catch (ParseException e) {
                wronglyFormattedEntries.add("Row" + (i + 2) + " : " + e.getLocalizedMessage());
            }
        }
    }
}
