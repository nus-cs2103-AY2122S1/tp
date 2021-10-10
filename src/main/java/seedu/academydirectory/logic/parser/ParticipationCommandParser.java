package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ADD_PARTICIPATON;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_STUDIO_SESSION;

import java.util.ArrayList;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.ParticipationCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class ParticipationCommandParser implements Parser<ParticipationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceCommand
     * and returns an AttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ParticipationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_STUDIO_SESSION, PREFIX_ADD_PARTICIPATON);
        ArrayList<Index> indexArrayList = new ArrayList<>();
        Integer participationUpdate;
        Integer studioSession;

        try {
            String indexString = argMultimap.getPreamble();
            String[] indexArray = indexString.split(",");
            for (String str : indexArray) {
                String strippedStr = str.trim();
                indexArrayList.add(ParserUtil.parseIndex(strippedStr));
            }
            participationUpdate = ParserUtil.parseParticipation(argMultimap.getValue(PREFIX_ADD_PARTICIPATON).get());
            studioSession = ParserUtil.parseStudioRecord(argMultimap.getValue(PREFIX_STUDIO_SESSION).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParticipationCommand.MESSAGE_USAGE),
                    pe
            );
        }
        return new ParticipationCommand(participationUpdate, studioSession, indexArrayList);
    }
}
