package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.commands.EditPositionCommand;
import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.position.Title;

import java.util.stream.Stream;


public class EditApplicantCommandParser implements Parser<EditApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditApplicantCommand
     * and returns an EditApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public EditApplicantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_POSITION);

        Index index;
        Title positionTitle;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicantCommand.MESSAGE_USAGE), pe);
        }



        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editApplicantDescriptor.setName(ApplicantParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editApplicantDescriptor.setPhone(ApplicantParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editApplicantDescriptor.setEmail(ApplicantParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editApplicantDescriptor.setAddress(ApplicantParserUtil
                    .parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            positionTitle = ApplicantParserUtil.parseTitle(argMultimap.getValue(PREFIX_POSITION).get());
        }

        return new EditApplicantCommand(index, positionTitle, editApplicantDescriptor);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
