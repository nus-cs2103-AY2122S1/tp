package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RESERVATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.util.Set;

import seedu.address.logic.commands.ReserveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Remark;
import seedu.address.model.tag.Tag;

public class ReserveCommandParser implements Parser<ReserveCommand> {

    @Override
    public ReserveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_PHONE, PREFIX_TIME, PREFIX_REMARK, PREFIX_TAG
        );

        if (argMultimap.getValue(PREFIX_PHONE).isEmpty()
                || argMultimap.getValue(PREFIX_TIME).isEmpty()
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReserveCommand.MESSAGE_USAGE));
        }

        int numberOfPeople = ParserUtil.parseNumberOfPeople(argMultimap.getPreamble());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        LocalDateTime time = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_TIME).get());

        if (time.getMinute() != 0) {
            throw new ParseException(MESSAGE_INVALID_RESERVATION_MINUTES);
        }

        assert numberOfPeople > 0;
        assert phone.value.length() > 0;

        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new ReserveCommand(phone, numberOfPeople, time, remark, tagList);
    }
}
