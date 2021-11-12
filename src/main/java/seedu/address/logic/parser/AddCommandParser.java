package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_STREAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.AcadLevel;
import seedu.address.model.person.AcadStream;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_PARENT_PHONE, PREFIX_PARENT_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SCHOOL, PREFIX_ACAD_STREAM, PREFIX_ACAD_LEVEL,
                        PREFIX_REMARK, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()
                || !areAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS)
                || !areAnyPrefixesPresent(
                        argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_PARENT_PHONE, PREFIX_PARENT_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(""));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(""));
        Phone parentPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PARENT_PHONE).orElse(""));
        Email parentEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_PARENT_EMAIL).orElse(""));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        School school = ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SCHOOL).orElse(""));
        AcadStream acadStream = ParserUtil.parseAcadStream(argMultimap.getValue(PREFIX_ACAD_STREAM).orElse(""));
        AcadLevel acadLevel = ParserUtil.parseAcadLevel(argMultimap.getValue(PREFIX_ACAD_LEVEL).orElse(""));
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        // Don't allow adding of lessons with the adding of person.
        Set<Lesson> lessonList = new TreeSet<>();

        Person person = new Person(name, phone, email, parentPhone, parentEmail,
            address, school, acadStream, acadLevel, remark, tagList, lessonList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if at least one of the prefixes does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap} and is not an empty string.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix ->
                argumentMultimap.getValue(prefix).isPresent() && !argumentMultimap.getValue(prefix).get().equals(""));
    }

}
