package seedu.notor.logic.parser.person;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUPINDEX;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

public class PersonCreateCommandParser extends PersonCommandParser {
    private final String uncheckedName;

    /**
     * Constructor for a PersonCreateCommandParser instance.
     *
     * @param uncheckedName Unchecked name of the person to be created.
     * @param arguments Arguments to be parsed.
     * @throws ParseException If arguments cannot be successfully parsed.
     */
    public PersonCreateCommandParser(String uncheckedName, String arguments) throws ParseException {
        super(null, arguments);
        this.uncheckedName = uncheckedName;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PersonCreateCommand parse() throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG, PREFIX_GROUPINDEX);

        if (!Name.isValidName(uncheckedName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonCreateCommand.MESSAGE_USAGE));
        }

        Name name = new Name(uncheckedName);
        Phone phone = new Phone(null);
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        Email email = new Email(null);
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        Set<Tag> tagList = new HashSet<>();
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagList = ParserUtil.parseTags(argMultimap.getValue(PREFIX_TAG).get());
        }

        if (argMultimap.getValue(PREFIX_GROUPINDEX).isPresent()) {
            index = ParserUtil.parseGroupIndex(argMultimap.getValue(PREFIX_GROUPINDEX).get());
        }

        Person person = new Person(name, phone, email, Note.EMPTY_NOTE, tagList);

        return new PersonCreateCommand(index, person);
    }
}
