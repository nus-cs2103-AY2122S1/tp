package seedu.track2gather.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;

import java.util.Optional;

import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.EditCommand;
import seedu.track2gather.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.track2gather.logic.parser.exceptions.ParseException;
import seedu.track2gather.model.person.attributes.NextOfKinAddress;
import seedu.track2gather.model.person.attributes.NextOfKinName;
import seedu.track2gather.model.person.attributes.NextOfKinPhone;
import seedu.track2gather.model.person.attributes.QuarantineAddress;
import seedu.track2gather.model.person.attributes.ShnPeriod;
import seedu.track2gather.model.person.attributes.WorkAddress;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_CASE_NUMBER, PREFIX_HOME_ADDRESS, PREFIX_WORK_ADDRESS, PREFIX_QUARANTINE_ADDRESS,
                PREFIX_SHN_PERIOD, PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE, PREFIX_NEXT_OF_KIN_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            editPersonDescriptor.setCaseNumber(ParserUtil.parseCaseNumber(
                    argMultimap.getValue(PREFIX_CASE_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_HOME_ADDRESS).isPresent()) {
            editPersonDescriptor.setHomeAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(PREFIX_HOME_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_WORK_ADDRESS).isPresent()) {
            Optional<String> workAddressOptional = argMultimap.getValue(PREFIX_WORK_ADDRESS);
            assert workAddressOptional.isPresent();
            editPersonDescriptor.setWorkAddress(new WorkAddress(
                    ParserUtil.parseAddress(workAddressOptional.get())));
        }
        if (argMultimap.getValue(PREFIX_QUARANTINE_ADDRESS).isPresent()) {
            Optional<String> quarantineAddressOptional = argMultimap.getValue(PREFIX_QUARANTINE_ADDRESS);
            assert quarantineAddressOptional.isPresent();
            editPersonDescriptor.setQuarantineAddress(new QuarantineAddress(
                    ParserUtil.parseAddress(quarantineAddressOptional.get())));
        }
        if (argMultimap.getValue(PREFIX_SHN_PERIOD).isPresent()) {
            Optional<String> shnPeriodOptional = argMultimap.getValue(PREFIX_SHN_PERIOD);
            assert shnPeriodOptional.isPresent();
            editPersonDescriptor.setShnPeriod(new ShnPeriod(
                    ParserUtil.parsePeriod(shnPeriodOptional.get())));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME).isPresent()) {
            Optional<String> nextOfKinNameOptional = argMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME);
            assert nextOfKinNameOptional.isPresent();
            editPersonDescriptor.setNextOfKinName(new NextOfKinName(
                    ParserUtil.parseName(nextOfKinNameOptional.get())));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE).isPresent()) {
            Optional<String> nextOfKinPhoneOptional = argMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE);
            assert nextOfKinPhoneOptional.isPresent();
            editPersonDescriptor.setNextOfKinPhone(new NextOfKinPhone(
                    ParserUtil.parsePhone(nextOfKinPhoneOptional.get())));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_ADDRESS).isPresent()) {
            Optional<String> nextOfKinAddressOptional = argMultimap.getValue(PREFIX_NEXT_OF_KIN_ADDRESS);
            assert nextOfKinAddressOptional.isPresent();
            editPersonDescriptor.setNextOfKinAddress(new NextOfKinAddress(
                    ParserUtil.parseAddress(nextOfKinAddressOptional.get())));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }
}
