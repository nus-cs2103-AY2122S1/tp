package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEUDLE_INPUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURR_DAILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURR_WEEKLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURR_YEARLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.exceptions.InvalidScheduleInputException;
import seedu.address.model.event.exceptions.InvalidTimeException;
import seedu.address.model.tag.Tag;

public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddScheduleCommand and returns an AddScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_FROM,
                PREFIX_TO, PREFIX_TAG, PREFIX_RECURR_DAILY, PREFIX_RECURR_WEEKLY, PREFIX_RECURR_YEARLY);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_FROM, PREFIX_TO)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        String date = argMultimap.getValue(PREFIX_DATE).get();
        String from = argMultimap.getValue(PREFIX_FROM).get();
        String to = argMultimap.getValue(PREFIX_TO).get();
        Optional<String> recurrD = argMultimap.getValue(PREFIX_RECURR_DAILY);
        Optional<String> recurrW = argMultimap.getValue(PREFIX_RECURR_WEEKLY);
        Optional<String> recurrY = argMultimap.getValue(PREFIX_RECURR_YEARLY);
        String recurrType = "N";
        String recurrDate = "";
        if (!recurrD.isEmpty()) {
            System.out.println(recurrD.get());
            recurrType = "D";
            recurrDate = recurrD.get();
        }
        if (!recurrW.isEmpty()) {
            recurrType = "W";
            recurrDate = recurrW.get();
        }
        if (!recurrY.isEmpty()) {
            recurrType = "Y";
            recurrDate = recurrY.get();
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        try {
            Schedule schedule = new Schedule(description, date, from, to, false, tagList, recurrType, recurrDate);
            return new AddScheduleCommand(schedule);
        } catch (InvalidTimeException e) {
            throw new ParseException(e.getMsg());
        } catch (InvalidScheduleInputException e) {
            throw new ParseException(MESSAGE_INVALID_SCHEUDLE_INPUT);
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
