package seedu.plannermd.logic.parser.addcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.plannermd.logic.commands.addcommand.AddDoctorCommand;
import seedu.plannermd.logic.parser.ArgumentMultimap;
import seedu.plannermd.logic.parser.ArgumentTokenizer;
import seedu.plannermd.logic.parser.Parser;
import seedu.plannermd.logic.parser.ParserUtil;
import seedu.plannermd.logic.parser.Prefix;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPatientCommand object
 */
public class AddDoctorCommandParser implements Parser<AddDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddDoctorCommand and returns an AddDoctorCommand object for execution.
     *
     * @param args arguments to be parsed
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDoctorCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_BIRTH_DATE, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_BIRTH_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        BirthDate birthDate = ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTH_DATE).get());
        Remark remark = Remark.getEmptyRemark();
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Doctor doctor = new Doctor(name, phone, email, address, birthDate, remark, tagList);

        return new AddDoctorCommand(doctor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     *
     * @return boolean
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
