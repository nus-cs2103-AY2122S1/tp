package seedu.times.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.times.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.times.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.times.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.times.logic.commands.AddCommand;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Nok;
import seedu.times.model.person.Phone;
import seedu.times.model.person.Student;
import seedu.times.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String argsBeforeNok = args;
        String argsAfterNok = "";

        if (args.contains("nok/")) {
            String[] splitArgs = args.split("nok/");
            if (splitArgs.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            argsBeforeNok = splitArgs[0];
            argsAfterNok = splitArgs[1];
        }

        Student student = parseStudent(argsBeforeNok);
        Nok nok = parseNok(argsAfterNok);
        student.setNok(nok);
        return new AddCommand(student);
    }

    private Student parseStudent(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Student(name, phone, email, address, null , tagList);
    }

    private Nok parseNok(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        return new Nok(
                ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()),
                ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()),
                ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()),
                ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get())
        );
    }
}
