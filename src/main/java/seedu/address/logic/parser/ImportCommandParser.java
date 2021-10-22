package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String MESSAGE_FILE_NOT_FOUND = "No CSV file called %s found.";

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns a ImportCommand object for execution.
     *
     * @param args the user input.
     * @return the ImportCommand object.
     * @throws ParseException if the user input or the CSV file does not conform the expected format.
     */
    @Override
    public ImportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        String line;
        ArrayList<Person> personList = new ArrayList<>();

        try {
            FileReader importFileReader = new FileReader(trimmedArgs);
            BufferedReader br = new BufferedReader(importFileReader);
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 4);

                if (values.length < 4) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
                }
                Name name = ParserUtil.parseName(values[0]);
                Phone phone = ParserUtil.parsePhone(values[1]);
                Availability availability = ParserUtil.parseAvailability(values[2]);
                Set<Tag> tags = parseTagCsv(values[3]);

                Person person = new Person(name, phone, availability, tags);
                personList.add(person);
            }
            return new ImportCommand(personList);
        } catch (FileNotFoundException e) {
            throw new ParseException(String.format(MESSAGE_FILE_NOT_FOUND, trimmedArgs));
        } catch (IOException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Helper function to parse the tags from a CSV file.
     * Returns a {@codeSet<Tags>} object.
     *
     * @param tags the given tags from the CSV file
     * @return the set of tags after being parsed
     * @throws ParseException if the tags from the CSV file do not conform with the expected format.
     */
    private Set<Tag> parseTagCsv(String tags) throws ParseException {
        Set<Tag> tagSet;
        String trimmedTags;
        if (tags.isEmpty()) {
            tagSet = new HashSet<>();
        } else if (tags.charAt(0) == '"') {
            trimmedTags = tags.substring(1, tags.length() - 1);
            List<String> tagList = Arrays.asList(trimmedTags.split(","));
            tagSet = ParserUtil.parseTags(tagList);
        } else {
            trimmedTags = tags;
            List<String> tagList = Arrays.asList(trimmedTags.split(","));
            tagSet = ParserUtil.parseTags(tagList);
        }
        return tagSet;
    }
}
