package seedu.notor.logic.parser.person;

import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.executors.person.PersonEditExecutor;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonEditCommandParser extends PersonCommandParser {
    public PersonEditCommandParser(String unparsedIndex, String arguments) throws ParseException {
        super(unparsedIndex, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PersonCommand parse() throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        PersonEditExecutor.PersonEditDescriptor personEditDescriptor = new PersonEditExecutor.PersonEditDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            personEditDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            personEditDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            personEditDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (!personEditDescriptor.isAnyFieldEdited()) {
            throw new ParseException(PersonEditCommand.MESSAGE_NOT_EDITED);
        }

        return new PersonEditCommand(index, personEditDescriptor);
    }
}
