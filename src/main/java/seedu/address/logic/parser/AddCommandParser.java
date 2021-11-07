package seedu.address.logic.parser;

import static seedu.address.logic.commands.AddCommand.MESSAGE_INVALID_COMMAND_FORMAT_NAME_ABSENT;
import static seedu.address.logic.commands.AddCommand.MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NATIONALITY,
                        PREFIX_TUTORIAL_GROUP, PREFIX_SOCIAL_HANDLE, PREFIX_GENDER, PREFIX_REMARK, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT_NAME_ABSENT, AddCommand.MESSAGE_USAGE));
        }
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone;
        if (argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
            phone = ParserUtil.parsePhone("");
        } else {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        Email email;
        if (argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            email = ParserUtil.parseEmail("");
        } else {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        Nationality nationality;
        if (argMultimap.getValue(PREFIX_NATIONALITY).isEmpty()) {
            nationality = ParserUtil.parseNationality("");
        } else {
            nationality = ParserUtil.parseNationality(argMultimap.getValue(PREFIX_NATIONALITY).get());
        }
        TutorialGroup tutorialGroup;
        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isEmpty()) {
            tutorialGroup = ParserUtil.parseTutorialGroup("");
        } else {
            tutorialGroup = ParserUtil.parseTutorialGroup(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get());
        }
        Gender gender;
        if (argMultimap.getValue(PREFIX_GENDER).isEmpty()) {
            gender = ParserUtil.parseGender("");
        } else {
            gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        }
        Remark remark;
        if (argMultimap.getValue(PREFIX_REMARK).isEmpty()) {
            remark = ParserUtil.parseRemark("");
        } else {
            remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Set<SocialHandle> socialHandleList = ParserUtil.parseSocialHandles(
                argMultimap.getAllValues(PREFIX_SOCIAL_HANDLE));

        Person person = new Person(name, phone, email, nationality, tutorialGroup,
                gender, remark, tagList, socialHandleList);
        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
