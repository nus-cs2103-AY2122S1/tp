package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_STUDIO_ATTENDANCE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_STUDIO_SESSION;

import java.util.ArrayList;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.AttendanceCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceCommand
     * and returns an AttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_STUDIO_SESSION, PREFIX_STUDIO_ATTENDANCE);
        ArrayList<Index> indexArrayList = new ArrayList<>();
        boolean attendanceStatus;
        Integer studioSession;

        try {
            String indexString = argMultimap.getPreamble();
            String[] indexArray = indexString.split(",");
            for (String str : indexArray) {
                String strippedStr = str.trim();
                indexArrayList.add(ParserUtil.parseIndex(strippedStr));
            }
            attendanceStatus = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_STUDIO_ATTENDANCE).get());
            studioSession = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDIO_SESSION).get()).getOneBased();
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE),
                    pe
            );
        }
        return new AttendanceCommand(attendanceStatus, studioSession, indexArrayList);
    }
}
